var openid,language;
function load(){
	openid = getCookie("openid");
	language = getCookie("language");
	
	if(language != "zh_CN"){
		$("#recharge").val("Confirm");
		$("title").text("Information");
		$("#timeoutinfor").remove();
		$("#entimeoutinfor").show();
		$("#tippicture").attr("src", "/watermachine/static/pic/entip.png");
	}
	else{
		$("#recharge").val("确认");
	}

	if(status == "下单中"){
		settime();
	}
}

/* 一秒刷新函数 */
function settime(){
    setTimeout(function() {
    	window.location.href = "http://www.terabits-wx.cn/watermachine/info/" + id;
    	settime();
    },1000);
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


var money,water;
//容量选择按钮选中效果
function money1(){
	money = 0.2;
	water = 0.3;
	$("#money1image").attr("src", "/watermachine/static/pic/aselect.png");
	$("#money2image").attr("src", "/watermachine/static/pic/bselect.png");
	$("#money3image").attr("src", "/watermachine/static/pic/bselect.png");
	$("#money4image").attr("src", "/watermachine/static/pic/bselect.png");
	
}

function money2(){
	money = 0.3;
	water = 0.5;
	$("#money1image").attr("src", "/watermachine/static/pic/bselect.png");
	$("#money2image").attr("src", "/watermachine/static/pic/aselect.png");
	$("#money3image").attr("src", "/watermachine/static/pic/bselect.png");
	$("#money4image").attr("src", "/watermachine/static/pic/bselect.png");
}

function money3(){
	money = 0.5;
	water = 1;
	$("#money1image").attr("src", "/watermachine/static/pic/bselect.png");
	$("#money2image").attr("src", "/watermachine/static/pic/bselect.png");
	$("#money3image").attr("src", "/watermachine/static/pic/aselect.png");
	$("#money4image").attr("src", "/watermachine/static/pic/bselect.png");
}

function money4(){
	money = 0.9;
	water = 2;
	$("#money1image").attr("src", "/watermachine/static/pic/bselect.png");
	$("#money2image").attr("src", "/watermachine/static/pic/bselect.png");
	$("#money3image").attr("src", "/watermachine/static/pic/bselect.png");
	$("#money4image").attr("src", "/watermachine/static/pic/aselect.png");
}

//跳转使用页
function recharge() {
	$("#recharge").attr("disabled", true);
	if(language != "zh_CN"){$("#wait").remove();$("#enwait").show();}
    else{$("#wait").show();}
	var displayid = document.getElementById("id").innerText;
	if(money == null){
		alert("请选择取水量！");
	}
	else{
		//发起扣款查询及跳转
		$.ajax({
			type:'POST',
			url:'/watermachine/consumeorder',
			data:{
				"openid":openid,
				"cost":money,
				"displayid":displayid
			},
			dataType:'json',
			success:function(data){
				//跳转到using页
				if(data["result"] == "success") {
                    window.location.href = "/watermachine/info/" + displayid;
                }
                if(data["result"] == "in order service"){
					if(language != "zh_CN"){alert("Failure to place order");}
					else{alert("下单失败，请稍后重试！");}

					window.location.href = "/watermachine/mainpage?code=tera123bits";
                }
                if(data["result"] == "openid not match"){
                    if(language != "zh_CN"){alert("Failure to place order");}
					else{alert("下单失败，请稍后重试！");}
					window.location.href = "/watermachine/mainpage?code=tera123bits";
                }
                if(data["result"] == "error"){
                    if(language != "zh_CN"){alert("Failure to place order");}
					else{alert("下单失败，请稍后重试！");}
					window.location.href = "/watermachine/mainpage?code=tera123bits";
                }
                if(data["result"] == "order later"){
                    if(language != "zh_CN"){alert("Failure to place order");}
					else{alert("下单失败，请稍后重试！");}
                }
                if(data["result"] == "not enough"){
                    if(language != "zh_CN"){
                    	var flag = confirm("您的余额不足，是否跳转至充值页面？");
                    	if(flag == true){window.location.href = "/watermachine/callback";}
                    	else{window.location.reload();}
                    }
					else{
						var flag = confirm("Not sufficient funds. Would you like to jump to recharge?");
                    	if(flag == true){window.location.href = "/watermachine/callback";}
                    	else{window.location.reload();}
					}
                }
			}
		});
	}
}