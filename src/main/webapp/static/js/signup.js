
function load(){
	
	if(language != "zh_CN"){
		$("#signuppic").attr("src","/watermachine/static/pic/ensignuppic.png");
		$("#tel").val("input telephone number");
		$("#icode").val("input verification code");
		$("#signup").text("register now");
		$("#vcode").text("confirm");
		$("#inputdiv2").remove();
		$("#eninputdiv2").show();
		$("title").html("Register");
	}
	else{
		$("#tel").val("请输入手机号");
		$("#icode").val("请输入验证码");
		$("#signup").text("立即注册");
		$("#vcode").text("确认");
	}
}

var certificate,countdown;
function signup(){
    $("#signup").attr("disabled", true); 
    $("#signup").css("color", "rgb(200, 200, 200)");
    countdown = 60;
    settime();
    
    //发送手机号，回收凭证
    $.ajax({
		type:'GET',
		url:'/watermachine/sendmessage',
		data:{
			"openid":openid,
			"tel":$("#tel").val()
		},
		dataType:'json',
		success:function(data){
			var length = getJsonObjLength(data);
			if(length != 0){
				//凭证
				certificate = data["auth"];
		    }
		}
	});
}

function icode(){
    //验证验证码
	   $.ajax({
			type:'POST',
			url:'/watermachine/testcode',
			data:{
				"openid":openid,
				"tel":$("#tel").val(),
				"auth":certificate,
				"code":$("#icode").val()
			},
			dataType:'json',
			success:function(data){
				if(data["testpass"]=="yes"){
                    open();
                    setTimeout(function() { window.location.href="http://www.terabits-wx.cn/watermachine/mainpage?code=tera123bits"; },3000);
				}
				if(data["testpass"]=="wrongcode"){
                    alert("验证码不正确！");
				}
				else{
					alert("error");
				}
			}
		});
}

//跳转至注册成功提示
function open(){
    $("#inputdiv1").remove();
    $("#inputdiv2").remove();
    if(language != "zh_CN"){$("#ensucdiv").show();}	
    else{$("#sucdiv").show();}
}

function positionhide(){
    $("#bottombar").hide();
}

function positionshow(){
    $("#bottombar").show();
}

//倒计时函数
function settime() {
	if(language != "zh_CN"){
		document.getElementById("signup").innerText = "try again("+countdown+"s)";
	}
	else{
		document.getElementById("signup").innerText = "重试("+countdown+"s)";
	}
    countdown--;
    if (countdown != 0){
        setTimeout(function() { settime() },1000);
    }
    else{
    	if(language != "zh_CN"){
    		document.getElementById("signup").innerText = "try again";
    	}
    	else{
    		document.getElementById("signup").innerText = "重新发送";
    	}
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