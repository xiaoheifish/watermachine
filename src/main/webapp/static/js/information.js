var openid,language;
function load(){
	openid = getCookie("openid");
	language = getCookie("language");
	
	if(language != "zh_CN"){
		$("#recharge").val("Confirm");
		if(status == "空闲"){
			status = "usable";
		}
		else{status = "using";}
		$("title").text("Information");
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


var money,water;
//容量选择按钮选中效果
function money1(){
	money = 0.1;
	water = 0.2;
	$("#money1image").attr("src", "/watermachine/static/pic/aselect.png");
	$("#money2image").attr("src", "/watermachine/static/pic/bselect.png");
	$("#money3image").attr("src", "/watermachine/static/pic/bselect.png");
	$("#money4image").attr("src", "/watermachine/static/pic/bselect.png");
	
}

function money2(){
	money = 0.2;
	water = 0.5;
	$("#money1image").attr("src", "/watermachine/static/pic/bselect.png");
	$("#money2image").attr("src", "/watermachine/static/pic/aselect.png");
	$("#money3image").attr("src", "/watermachine/static/pic/bselect.png");
	$("#money4image").attr("src", "/watermachine/static/pic/bselect.png");
}

function money3(){
	money = 0.3;
	water = 1;
	$("#money1image").attr("src", "/watermachine/static/pic/bselect.png");
	$("#money2image").attr("src", "/watermachine/static/pic/bselect.png");
	$("#money3image").attr("src", "/watermachine/static/pic/aselect.png");
	$("#money4image").attr("src", "/watermachine/static/pic/bselect.png");
}

function money4(){
	money = 0.5;
	water = 2;
	$("#money1image").attr("src", "/watermachine/static/pic/bselect.png");
	$("#money2image").attr("src", "/watermachine/static/pic/bselect.png");
	$("#money3image").attr("src", "/watermachine/static/pic/bselect.png");
	$("#money4image").attr("src", "/watermachine/static/pic/aselect.png");
}

//跳转使用页
function recharge() {
	$("#recharge").attr("disabled", true);
	$("#wait").show();
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
                }else{
					alert("error");
				}
			}
		});
	}
}