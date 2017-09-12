function load(){
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
	tel = $("#tel").val();
	$.ajax({
            type:'GET',
            url:'/watermachine/????' + tel,
            dataType:'json',
            success:function(data){
                if (data["existence"] === "yes"){
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