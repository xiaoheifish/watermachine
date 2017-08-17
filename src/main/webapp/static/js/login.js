function openmenu(){
	$("#menu").animate({left:'0px'});
	$("#mainbody").animate({left:'40%'});
}

function closemenu(){
	$("#menu").animate({left:'-40%'});
}

function record(){
	$("#record").css("background-color","#f0f0f4");
	$("#recordbox").animate({left:'0px'});
}

function closerecordbox(){
	$("#recordbox").animate({left:'-130%'});
	$("#record").css("background-color","transparent");
}



//微信接口认证
function load(){
    if (isWeiXin5() == false) {
        alert("您的微信版本低于5.0，无法使用微信支付功能，请先升级！");
    }
    else{
        $.ajax({
            url : "http://www.terabits-wx.cn/watermachine/wxconfig",
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


//判断微信版本是否支持支付功能
function isWeiXin5() {
    var ua = window.navigator.userAgent.toLowerCase();
    var reg = /MicroMessenger\/[5-9]/i;
    return reg.test(ua);
}

//输入编码-登录数据交互
function login() {
    id = document.getElementById("id").value;
    if(id === ""){
		alert("请输入编号！");//未输入编号不跳转
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
