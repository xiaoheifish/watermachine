
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
		$("#signup").text("sign up now");
		$("#vcode").text("confirm");
		$("#inputdiv2").remove();
		$("#eninputdiv2").show();
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

var certificate;
function signup(){
    $("#signup").attr("disabled", true); 
    $("#signup").css("color", "rgb(200, 200, 200)");
    countdown = 60;
    settime();
    
    //发送手机号，回收凭证
    $.ajax({
		type:'GET',
		url:'/watermachine/phone',
		data:{
			"openid":openid,
			"tel":tel
		},
		dataType:'json',
		success:function(data){
			var length = getJsonObjLength(data);
			if(length != 0){
				//凭证
				certificate = data["certificate"];
		    }
			else{
				 if (data["errno"] != 0){
		            	alert("无相关数据！");
		            }
			}
		}
	});
}

function icode(){
    //验证验证码
	   $.ajax({
			type:'GET',
			url:'/watermachine/icode',
			data:{
				"openid":openid,
				"tel":tel,
				"certificate":certificate,
				"icode":icode
			},
			dataType:'json',
			success:function(data){
				open();
				//跳转到首页
				
				
					 if (data["errno"] != 0){
			            	alert("无相关数据！");
			            }
			}
		});
}

//跳转至注册成功提示
function open(){
    $("#inputdiv1").remove();
    $("#inputdiv2").remove();
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