function load(){
	var primiurl = "http://www.terabits-wx.cn/watermachine/getcode";
	var encodeurl = encodeURIComponent(primiurl);
    var url = "https://open.weixin.qq.com/connect/oauth2/authorize?appid=wx34690a5342af3858&redirect_uri="+encodeurl+"&response_type=code&scope=snsapi_base&state=1#wechat_redirect";
    window.location.href=url;

    //查询openid 和language

    if(language != "zh_CN"){
		$("#signuppic").attr("src","/watermachine/static/pic/ensignuppic.png");
		$("#inputdiv1").remove();
		$("#sucdiv").remove();
		$("title").html("On Invitation");
	}
	else{
		$("#eninputdiv1").remove();
		$("#ensucdiv").remove();
	}
}

var certificate,countdown;
function signup(){
	phone = $("#tel").val();
	$.ajax({
            type:'POST',
            url:'/watermachine/userexist',
            dataType:'json',
            success:function(data){
                if (data["result"] === "new"){
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
                else{
                    alert("您已经是本系统的老用户了哦~");
                }
            }
    });
}

function icode(){
	var url=location.href; 
	var url1=url.split("{")[1];
	var authphone=url.split("}")[0];

    //验证验证码
	   $.ajax({
			type:'POST',
			url:'/watermachine/testcode',
			data:{
				"auth":certificate,
				"tel":$("#tel").val(),
				"code":$("#icode").val(),
				"phone":authphone
			},
			dataType:'json',
			success:function(data){
				if(data["testpass"]=="yes"){
                    open();
				}
				else{
					alert("error");
				}
			}
		});
}

//跳转至注册成功提示
function open(){
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