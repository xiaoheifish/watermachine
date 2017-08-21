var openid,language;
function load(){
	var openid = getCookie("openid");
	var language = getCookie("language");
	
	if(language != "zh_CN"){
		$("#recharge").val("Confirm");
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
	var id = document.getElementById("id").innerText;
	if(money == null){
		alert("请选择取水量！");
	}
	else{
		//发起扣款查询及跳转
		$.ajax({
			type:'POST',
			url:'/watermachine/consume/order',
			data:{
				"id":openid,
				"cost":money,
				"displayid":id
			},
			dataType:'json',
			success:function(data){
				//跳转到using页
				window.location.href = "/watermachine/info/"+id;
			}
		});
	}
}