function openmenu(){
	$("#menu").animate({left:'0px'});
	$("#closemenudiv").show();
}

function closemenu(){
	$("#menu").animate({left:'-40%'});
	$("#closemenudiv").hide();
}

function openrecordbox(){
	//$("#record").css("background-color","#f0f0f4");
	window.location.href = "/watermachine/record";
}

function openwalletbox(){
	//$("#wallet").css("background-color","#f0f0f4");
	window.location.href = "/watermachine/wallet?openid=" + openid;

}

function openservicebox(){
	//$("#service").css("background-color","#f0f0f4");
	window.location.href = "/watermachine/mail";
}

//打开手动输入窗口
function opennumber(){
	$("#bottombar").hide();
	$("#menubutton").hide();
	$("#numberbox").show();
	$("#id").focus();
}

function closenumber(){
	$("#numberbox").hide();
	$("#id").val("");
	$("#bottombar").show();
	$("#menubutton").show();
}


//输入编码-登录数据交互
function login() {
	id = $("#id").val();
	id = "10000"+id;
	if(id == ""){
		alert("请输入编号！");
	}
    else{
        $.ajax({
            type:'GET',
            url:'/watermachine/existence/' + id,
            dataType:'json',
            success:function(data){
                if (data["existence"] === "yes"){
                    window.location.href = "http://www.terabits-wx.cn/watermachine/info/" + id;
                }
                else{
                    alert("编号不存在，请重新输入");
                }
            }
        });

    }
}


//微信接口认证
function load(){

		//查询语言并保存cookie,查询昵称及头像
		$("#nickname").append(nickname);
		$("#avatar").attr("src", avatar);
		setCookie("openid",openid);
		setCookie("language",language);
				
		//中英文切换
		if(language != "zh_CN"){
			$("title").html("Intelligent Water Fountain");
			$("#RQ").text("Scan to Drink");
			$("#numbertext").text("Input the water fountain id");
			$("#idinputtext").text("Input the water fountain id");
			$("#cancle").val("cancle");
			$("#login").val("confirm");
			$("#record").text("My record");
			$("#wallet").text("My wallet");
			$("#invite").text("Invite friends");
			$("#service").text("Customer Feedback");
			$("#enrule").text("Detail Rules");
		}
			
	
	if (isWeiXin5() == false) {
        alert("您的微信版本低于5.0，无法使用微信支付功能，请先升级！");
      }
    else{
        $.ajax({
            url : "/watermachine/wxconfig",
            type : 'post',
            dataType : 'json',
            contentType : "application/x-www-form-urlencoded; charset=utf-8",
            data : {
                'url' : location.href.split('#')[0]
            },
            error: function(XMLHttpRequest, textStatus, errorThrown){
                alert(XMLHttpRequest.status);
            },
            success:function(data){
                //扫一扫相关函数
                wx.config({
                    debug: false,
                    appId: data["appId"],
                    timestamp: data["timestamp"],
                    nonceStr: data["nonceStr"],
                    signature: data["signature"],
                    jsApiList : [ 'checkJsApi',
                        'scanQRCode']
                });
            }
        });
    }
}



//调用扫一扫接口
function RQ(){
    //扫描二维码
    wx.scanQRCode({
        needResult : 1, // 默认为0，扫描结果由微信处理，1则直接返回扫描结果，
        scanType : [ "qrCode", "barCode" ], // 可以指定扫二维码还是一维码，默认二者都有
        success : function(res) {
            var result = res.resultStr; // 当needResult 为 1 时，扫码返回的结果
            var urlList = result.split('/');
            var webId = urlList[4];
            $.ajax({
                type:'GET',
                url:'/watermachine/weburl/' + webId,
                dataType:'json',
                success:function(data){
                    if (data["displayId"] !== null){
                        window.location.href = "http://www.terabits-wx.cn/watermachine/info/" + data["displayId"];
                    }
                    else{
                        alert("扫码故障");
                    }
                }
            });
        }
    });
}

//我的记录
function loadrecord(){
	loadid();
	$.ajax({
		type:'POST',
		url:'/watermachine/menu/record',
		data:{
			"openid":openid
        },
		dataType:'json',
		success:function(data){
			var length = getJsonObjLength(data);

			if(length != 0){
				$("#record0").show();
		            for(i=0; i<length; i++){
		            	$("#record"+i).find("#time").text(data[i]["gmtCreate"]);
		            	$("#record"+i).find("#cost").text(data[i]["payment"]);
		            	$("#record"+i).find("#idtext").text(data[i]["displayId"]);
		            	$("#record"+i).find("#water").text(data[i]["flow"]);
		            	if(i != (length-1)){
		            		i++;
							/*  增加div */
                            object = $("#record0").clone();
                            $(object).attr("id","record"+i);
                            $("body").append(object);
                            i--;
						}
		            }
		    }
		}
	});
	
	if(language != "zh_CN"){
		$(".cost").text("cost: ");
		$(".id").text("id: ");
		$(".water").text("water: ");
		$(".L").text("L");
		$("title").html("My Record");
	}
}

//我的客服
function loadservice(){
	loadid();
	if(language != "zh_CN"){
		$("#suggestion").text("Please give some suggestion to help us improve!");
		$("#emailtext").text("Email");
		$("#suggestionbutton").val("submit");
		$("#text").remove();
		$("#entext").show();
		$("title").html("Customer Feedback");
	}
}

function suggestion(){
    $.ajax({
        type:'POST',
        url:'/watermachine/mail/feedback',
        data:{
            "openid":openid,
            "email":$("#email").val(),
            "suggestion":$("#suggestion").val()
        },
        dataType:'json',
        success:function(data){
			if(data["result"] == "ok"){
				alert("您的建议已成功提交！");
			}
        }
    });
}

//我的钱包
function loadwallet(){
	loadid();
	if(language != "zh_CN"){
		$("#balancetext").text("Balance(¥)");
		$("#jumprecharge").text("Recharge");
		$("title").html("My Wallet");
	}
}

function jumprecharge(){
	window.location.href = "/watermachine/callback";
}

//加载cookie，语言和用户id
function loadid(){
	openid = getCookie("openid");
	language = getCookie("language");
}

//判断微信版本是否支持支付功能
function isWeiXin5() {
    var ua = window.navigator.userAgent.toLowerCase();
    var reg = /MicroMessenger\/[5-9]/i;
    return reg.test(ua);
}

/* json数据长度 */
function getJsonObjLength(jsonObj) {
    var Length = 0;
    for (var item in jsonObj) {
        Length++;
    }
    return Length;
}

/* 设置cookie */
function setCookie(name,value)
{
	  var d = new Date();
	  d.setTime(d.getTime()+(10*24*60*60*1000));
	  var expires = "expires="+d.toGMTString();
	  document.cookie = name + "=" + value;
}

/* 读取cookie */
function getCookie(cname)
{
	var name = cname + "=";
	var ca = parent.document.cookie.split(';');
	for(var i=0; i<ca.length; i++) {
		var c = ca[i].trim();
		if (c.indexOf(name)==0) return c.substring(name.length,c.length);
	}
	return null;
}