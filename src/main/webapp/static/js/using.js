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
		$("#savebox").remove();
        $("#ensavebox").show();
        $("#warn").remove();
        $("#enwarn").show();
	}
    else{
        $("#chwarnningtext").show();
        $("#enwarnningtext").remove();
        $("#L").text("升取水量");
        $("#savebox").show();
        $("#ensavebox").remove();
    }

    if(water == "0.3"){$("#save").text("1/2");}
    if(water == "0.5"){$("#save").text("1");}
    if(water == "1.0"){$("#save").text("2");}
    if(water == "2.0"){$("#save").text("4");}
    
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

//弹出框去掉网址
window.alert = function(name){
var iframe = document.createElement("IFRAME");
iframe.style.display="none";
//iframe.setAttribute("src", 'data:text/plain,');
document.documentElement.appendChild(iframe);
window.frames[0].window.alert(name);
iframe.parentNode.removeChild(iframe);
}