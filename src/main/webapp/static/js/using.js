var openid,language;
function load(){
	openid = getCookie("openid");
	language = getCookie("language");
	
	if(language != "zh_CN"){
		$("#chwarnningtext").remove();
		$("#enwarnningtext").show();
		$("#L").text("L");
        if(status == "空闲"){
            status = "usable";
        }
        else{status = "using";}
        $("title").text("Using Information");
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

function settime() {
    $("#lefttime").html(timeform(lefttime));
    lefttime--;
    if (lefttime != -1){
        setTimeout(function() { settime() },1000);
    }
    else{
    	window.location.href="http://www.terabits-wx.cn/watermachine/mainpage";
    }
}

//时间显示处理函数
function timeform(time){
        min = parseInt(time/60);
        sec =  parseInt(time) - min*60;
        if(min < 10){
            min = "0" + min;
        }
        if(sec < 10){
            sec = "0" + sec;
        }
        value = min + ":" + sec;
    return value;
}