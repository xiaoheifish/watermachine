﻿function load(){
	var primiurl = "http://www.terabits-wx.cn/watermachine/getcode?phone=" + phone;
	var encodeurl = encodeURIComponent(primiurl);
    var url = "https://open.weixin.qq.com/connect/oauth2/authorize?appid=wx34690a5342af3858&redirect_uri="+encodeurl+"&response_type=code&scope=snsapi_base&state=1#wechat_redirect";
    window.location.href=url;

    /*if(language != "zh_CN"){
		$("#signuppic").attr("src","/watermachine/static/pic/ensignuppic.png");
		$("#inputdiv1").remove();
		$("#sucdiv").remove();
		$("title").html("On Invitation");
	}
	else{
		$("#eninputdiv1").remove();
		$("#ensucdiv").remove();
	}*/
}

var certificate,countdown;
function signup(){
	$.ajax({
            type:'GET',
            url:'/watermachine/userexist',
            dataType:'json',
            data:{
				"phone":$("#tel").val()
			},
            success:function(data){
                if (data["result"] == "new"){
					$("#signup").attr("disabled", true); 
				    $("#signup").css("color", "rgb(200, 200, 200)");
				    countdown = 60;
				    settime();
				    
				    //发送手机号，回收凭证
				    $.ajax({
						type:'GET',
						url:'/watermachine/sendmessage',
						data:{
							"tel":$("#tel").val()
						},
						dataType:'json',
						success:function(data){
							var length = getJsonObjLength(data);
							if(length != 0){
								//凭证
								certificate = data["auth"];
								setCookie("certificate",certificate);
						    }
						}
					});
                }
                if (data["result"] == "old"){
                    alert("您已经是本系统的老用户了哦~");
                }
            }
    });
}

function icode(){
	certificate = getCookie("certificate");
	if(certificate != null){
		//验证验证码
	   $.ajax({
			type:'POST',
			url:'/watermachine/testcode',
			data:{
				"auth":certificate,
				"tel":$("#tel").val(),
				"code":$("#icode").val(),
				"phone":phone,
				"openid":openid
			},
			dataType:'json',
			success:function(data){
				if(data["testpass"]=="yes"){
                    open();
				}
				else if(data["testpass"]=="wrongcode"){
                    alert("验证码不正确！");
				}
				else{
					alert("error");
				}
			}
		});
	}
    else{
    	alert("请先获取验证码！");
    }
}

//跳转至注册成功提示
function open(){
    
    $("#sucdiv").show();
}

function positionhide(){
    $("#bottombar").hide();
}

function positionshow(){
    $("#bottombar").show();
}

//倒计时函数
function settime() {
		document.getElementById("signup").innerText = "重试("+countdown+"s)";
	
    countdown--;
    if (countdown != 0){
        setTimeout(function() { settime() },1000);
    }
    else{
    	
    		document.getElementById("signup").innerText = "重新发送";
    	
        $("#signup").attr("disabled", false); 
        $("#signup").css("color", "white");
    }
}

/* json数据长度 */
function getJsonObjLength(jsonObj) {
    var Length = 0;
    for (var item in jsonObj) {
        Length++;
    }
    return Length;
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