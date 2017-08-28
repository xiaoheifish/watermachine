
function load(){
	//查询语言并保存cookie,查询昵称及头像
	setCookie("openid",openid);
	setCookie("language",language);
	setCookie("avatar",avatar);
	setCookie("nickname",nickname);

	if(language != "zh_CN"){
		$("#signuppic").attr("src","/watermachine/static/pic/ensignuppic.png");
		$("#tel").val("input telephone number");
		$("#icode").val("input verification code");
		$("#signup").text("register now");
		$("#vcode").text("confirm");
		$("#inputdiv2").remove();
		$("#eninputdiv2").show();
		$("title").html("register");
	}
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
                    setTimeout(function() { window.location.href="http://www.terabits-wx.cn/watermachine/mainpage"; },3000);

				}else{
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