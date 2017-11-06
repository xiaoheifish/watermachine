function openmenu(){
	$("#menu").animate({left:'0px'});
	$("#closemenudiv").show();
}

function closemenu(){
	$("#menu").animate({left:'-40%'});
	$("#closemenudiv").hide();
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
//关闭广告窗口
function closewindow(){
        $("#adwindow").hide();
}

//输入编码-登录数据交互
function login() {
	id = $("#id").val();
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
function loadlog(){
//中英文切换
    if(language != "zh_CN"){
        $("title").html("Smart Potable Water Fountain");
        $("#RQ").text("Scan QR Code");
        $("#RQ").css("color","white");
        $("#numbertext").text("Input the device serial number");
        $("#idinputtext").text("Input the device serial number");
        $("#cancle").val("cancel");
        $("#login").val("confirm");
        $("#record").text("Transaction List");
        $("#wallet").text("My Wallet");
        $("#invite").text("Invite Friends");
        $("#service").text("Feedback");
        $("#enrule").text("Detail Rules");
        $("#enrule").css("text-align","left");
        $("#enrule").css("padding-left","13%");
        $("#rechargerec").text("Deposit Record");
        $(".menu_1").css("text-align","left");
        $(".menu_1").css("padding-left","13%");
    }
	else{
        $("title").html("欢迎使用智能直饮水机");
         $("#RQ").css("color","white");
        $("#numbertext").text("输入编号取水:");
	}


    //查询语言并保存cookie,查询昵称及头像
		$("#nickname").append(nickname);
		$("#avatar").attr("src", avatar);
		setCookie("openid",openid);
		setCookie("language",language);

	//显示勋章
	$.ajax({
		type:'POST',
		url:'/watermachine/medal/number',
		data:{
			"openid": "o1S07wuDO9ivY_55p3OT4bEMNUL0"
		},
		dataType:'json',
		success:function(data){
            var gold = parseInt(parseInt(data["number"]) / parseInt(100));
            var silver = parseInt((parseInt(data["number"]) - parseInt(gold) * parseInt(100)) / parseInt(10));
            var bronze = parseInt((parseInt(data["number"]) - parseInt(gold) * parseInt(100) - parseInt(silver) * parseInt(10)));
			for (var i=0; i<parseInt(gold); i++){
				$("#medal").append("<img style='width: 0.4rem' src='/watermachine/static/pic/gold.png'>");
			}
			for (var i=0; i<parseInt(silver); i++){
				$("#medal").append("<img style='width: 0.4rem' src='/watermachine/static/pic/silver.png'>");
			}
			for (var i=0; i<parseInt(bronze); i++){
				$("#medal").append("<img style='width: 0.4rem' src='/watermachine/static/pic/bronze.png'>");
			}
		}
	});
	
	
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
				$("nothingbox").remove();
				$("#record0").show();
		            for(i=0; i<length; i++){
		            	var time = data[i]["gmtCreate"];
		            	var reformtime=time.split(".")[0];
		            	$("#record"+i).find("#time").text(reformtime);
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
			 else{
			 	$("#record0").remove();
			 	$("#nothingbox").show();
			 	if (language != "zh_CN") {
			 		$("#nothingtext").text("You don't have any records.");
			 	}
			 	else{
			 		$("#nothingtext").text("您暂时没有取水记录哦~");
			 	}
			 }
		}
	});
	
	if(language != "zh_CN"){
		$(".cost").text("cost: ");
		$(".id").text("id: ");
		$(".water").text("water: ");
		$(".L").text("L");
		$("title").html("Transaction List");
	}
}

//我的客服
function loadservice(){
	loadid();
	if(language != "zh_CN"){
		$("#suggestion").text("Please write down some suggestions and comments to help us improve!");
		$("#emailtext").text("Email");
		$("#suggestionbutton").text("Submit");
		$("#suggestionbutton").css("color","white");
		$("#text").remove();
		$("#entext").show();
		$("title").html("Feedback");
	}
	else{
		$("#suggestion").text("欢迎您提出对智能饮水机的感受和意见，期待您的声音。");
		$("#emailtext").text("您的邮箱");
		$("#suggestionbutton").css("color","white");
		$("#text").show();
		$("#entext").remove();
	}
}

function suggestion(){
	$("#suggestionbutton").attr("disabled", true);
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
				if(language != "zh_CN"){alert("Your suggestions and comments have been submited.");}
				else{alert("您的建议已成功提交！");}
				
			}
        }
    });
}

//充值记录
function loadrechargerecord(){
	loadid();
	$.ajax({
		type:'POST',
		url:'/watermachine/menu/rechargerecord',
		data:{
			"openid":openid
        },
		dataType:'json',
		success:function(data){
			var length = getJsonObjLength(data);
			if(length != 0){
				$("nothingbox").remove();
				$("#record0").show();
		            for(i=0; i<length; i++){
		            	var time = data[i]["gmtCreate"];
		            	var reformtime=time.split(".")[0];
		            	$("#record"+i).find("#time").text(reformtime);
		            	$("#record"+i).find("#amount").text(data[i]["payment"]);
		            	$("#record"+i).find("#present").text(data[i]["present"]);
		            	$("#record"+i).find("#idtext").text(data[i]["orderId"]);
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
			else{
				$("#nothingbox").show();
			 	$("#record0").remove();
			 	if (language != "zh_CN") {
			 		$("#nothingtext").text("You don't have recharge records.");
			 	}
			 	else{
			 		$("#nothingtext").text("您暂时没有充值记录哦~");
			 	}
			}
		}
	});
	if(language != "zh_CN"){
		$(".cost").text("amount: ");
		$(".id").text(" number: ");
		$(".gift").text(" gift ");
		$("title").html("Deposit Record");
	}
}

//邀请好友
function loadinvite(){
    openid = getCookie("openid");
    language = getCookie("language");
	if(language != "zh_CN"){
		$("#ch").remove();
		$("title").html("Invitation");
        $("#sharetip").css("font-size","0.35rem");
        $("#sharetip").text("Please click on the top right corner.");
    }
	else{
		$("#en").remove();
}

	//配置分享接口
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
                //分享相关函数
                wx.config({
                    debug: false,
                    appId: data["appId"],
                    timestamp: data["timestamp"],
                    nonceStr: data["nonceStr"],
                    signature: data["signature"],
                    jsApiList : [ 'checkJsApi','onMenuShareTimeline', 'onMenuShareAppMessage']
                });
                wx.ready(function (){
                        var shareData = {
                            title: '邀您共享高品质饮用水',
                            desc: '接受邀请，好友与您各得5元饮水券！点击立即体验便捷、高质、卫生饮水！',
                            link: 'http://www.terabits-wx.cn/watermachine/invitation/'+ phone,
                            imgUrl: 'http://www.terabits-wx.cn/watermachine/static/pic/sharepic.jpg'
                        };
                        wx.onMenuShareAppMessage(shareData);
                        wx.onMenuShareTimeline(shareData);
                });
                wx.error(function (res) {
                    alert(res.errMsg);
                });
            }
    });
}

/* 提示右上角分享 */
function sharetip(){
    $("#sharetip").show();
    $("#sharetip").fadeOut(2000);
}

//使用页、超时页加载函数
function loadusing(){
	loadid();
	
	if(language != "zh_CN"){
		$("#chwarnningtext").remove();
		$("#enwarnningtext").show();
		$("#L").text("L");
        if(status == "空闲"){
            status = "usable";
        }
        else{status = "using";}
        $("title").text("Using Information");
		$("#savebox").remove();
        $("#ensavebox").show();
        $("#warn").remove();
        $("#enwarn").show();
	}
    else{
        $("#chwarnningtext").show();
        $("#enwarnningtext").remove();
        $("#L").text("升取水量");
        $("#savebox").show();
        $("#ensavebox").remove();
    }

    if(water == "0.3"){$("#save").text("1/2");}
    if(water == "0.5"){$("#save").text("1");}
    if(water == "1.0"){$("#save").text("2");}
    if(water == "2.0"){$("#save").text("4");}
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

//弹出框去掉网址
window.alert = function(name){
var iframe = document.createElement("IFRAME");
iframe.style.display="none";
//iframe.setAttribute("src", 'data:text/plain,');
document.documentElement.appendChild(iframe);
window.frames[0].window.alert(name);
iframe.parentNode.removeChild(iframe);
}