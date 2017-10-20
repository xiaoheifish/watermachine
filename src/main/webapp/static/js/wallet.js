//我的钱包
function loadwallet(){
	loadid();
	if(language != "zh_CN"){
		$("#balancetext").text("Balance(¥)");
		$("#rechargetext").text("Recharge Balance(¥)");
        $("#presenttext").text("Present(¥)");
        $("#jumprecharge").text("Recharge");
        $("#jumprecharge").css("color","white");
        $("#jumpreimburse").text("Refund");
        $("#jumpreimburse").css("color","white");
        $("title").html("My Wallet");
    }
else{
        $("#balancetext").text("余额(元)");
        $("#rechargetext").text("可提现余额(元)");
		$("#presenttext").text("赠送余额(元)");
		$("#jumprecharge").css("color","white");
		$("#jumpreimburse").css("color","white");
	}
}

function jumprecharge(){
	window.location.href = "/watermachine/callback";
}

function jumpreimburse(){
	if (recharge == "0.0"){
		if(language != "zh_CN"){alert("Can't withdraw deposit.");}
		else{alert("余额不足，无法提现");}
	}
	else{
        if(language != "zh_CN"){$("#enrefunddiv").show();}
        else{$("#refunddiv").show();}
	} //可提现，跳转
	
}

//提现记录
function loadrefundrecord(){
	loadid();
	$.ajax({
		type:'POST',
		url:'/watermachine/menu/refundrecord',
		data:{
			"openid":openid
        },
		dataType:'json',
		success:function(data){
			var length = getJsonObjLength(data);
			if(length != 0){
				$("#record0").show();
		            for(i=0; i<length; i++){
		            	var time = data[i]["gmtCreate"];
		            	var reformtime=time.split(".")[0];
		            	$("#record"+i).find("#time").text(reformtime);
		            	$("#record"+i).find("#money").text(data[i]["money"]);
		            	$("#record"+i).find("#refundNo").text(data[i]["refundNo"]);
		            	if(i != (length-1)){
		            		i++;
							/*  增加div */
                            object = $("#record0").clone();
                            $(object).attr("id","record"+i);
                            $("body").append(object);
                            i--;
						}
		            }
		    }
			 else{alert("暂无数据！");}
		}
	});
	if(language != "zh_CN"){
		$(".cost").text("money: ");
		$(".id").text(" number: ");
		$("title").html("Deposit Record");
	}
}

//退款操作
function refund(){
	$("#confirm").attr("disabled", true);
	$("#enconfirm").attr("disabled", true);
	var value = openid+"D2FFD4FAEF6778E26813CB08FE3CB3C5";
    var auth = md5(value);
	$.ajax({
		type:'POST',
		url:'/watermachine/refund',
		data:{
			"openid":openid,
			"money":recharge,
			"auth":auth
        },
		dataType:'json',
		success:function(data){
			if(result == "success"){
				if(language != "zh_CN"){alert("Refund succeed!");}
				else{alert("退款申请成功！");}
				window.location.href="/watermachine/mainpage?code=tera123bits";
			}
			if(result == "simpleban"){
				if(language != "zh_CN"){alert("Refund succeed!");}
				else{alert("Your WeChat account is not bound with a card. Please bind your card firstly.");}
			}
		}
	});
}


//加载cookie，语言和用户id
function loadid(){
	openid = getCookie("openid");
	language = getCookie("language");
}


/* json数据长度 */
function getJsonObjLength(jsonObj) {
    var Length = 0;
    for (var item in jsonObj) {
        Length++;
    }
    return Length;
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