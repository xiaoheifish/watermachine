var openid,language;
function load(){
	openid = getCookie("openid");
	language = getCookie("language");

	if(language != "zh_CN"){
		document.getElementById('50').innerHTML ="¥ 5\n<br/>Gift ¥5";
		document.getElementById('20').innerHTML ="¥ 3\n<br/>Gift ¥3";
		document.getElementById('10').innerHTML ="¥ 2\n<br/>Gift ¥2";
		document.getElementById('5').innerHTML ="¥ 1\n<br/>Gift ¥1";
		$("#recharge").val("recharge");
		$("#rechargewarntext").text("Click 'recharge' means you agree the above rules.");
		$("#rechargepic").attr("src","/watermachine/static/pic/enrechargepic.png");
		$("title").html("Recharge");
	}
}


var money;
//容量选择按钮选中效果
function money1(){
	alert("请您选择3元或3元以下的金额进行充值！");
	/*money = 5;
	$("#money1image").attr("src", "/watermachine/static/pic/aselect.png");
	$("#money2image").attr("src", "/watermachine/static/pic/bselect.png");
	$("#money3image").attr("src", "/watermachine/static/pic/bselect.png");
	$("#money4image").attr("src", "/watermachine/static/pic/bselect.png");
	*/
	
}

function money2(){
	money = 3;
	$("#money1image").attr("src", "/watermachine/static/pic/bselect.png");
	$("#money2image").attr("src", "/watermachine/static/pic/aselect.png");
	$("#money3image").attr("src", "/watermachine/static/pic/bselect.png");
	$("#money4image").attr("src", "/watermachine/static/pic/bselect.png");
}

function money3(){
	money = 2;
	$("#money1image").attr("src", "/watermachine/static/pic/bselect.png");
	$("#money2image").attr("src", "/watermachine/static/pic/bselect.png");
	$("#money3image").attr("src", "/watermachine/static/pic/aselect.png");
	$("#money4image").attr("src", "/watermachine/static/pic/bselect.png");
}

function money4(){
	money = 1;
	$("#money1image").attr("src", "/watermachine/static/pic/bselect.png");
	$("#money2image").attr("src", "/watermachine/static/pic/bselect.png");
	$("#money3image").attr("src", "/watermachine/static/pic/bselect.png");
	$("#money4image").attr("src", "/watermachine/static/pic/aselect.png");
}

var countdown;
//充值
function recharge() {
	$.ajax({
        type:'POST',
        url:'/watermachine/wxpay',
        data: {
            "id":openid,
            "money" : money
        },
        dataType: 'json',
        error: function(XMLHttpRequest, textStatus, errorThrown){
            alert(XMLHttpRequest.status);
        },
        success:function(response){
            WeixinJSBridge.invoke('getBrandWCPayRequest',{
                "appId" : response.appId,                  //公众号名称，由商户传入
                "timeStamp":response.timestamp,          //时间戳，自 1970 年以来的秒数
                "nonceStr" : response.nonce,         //随机串
                "package" : response.packageName,      //商品包信息</span>
                "signType" : response.signType,        //微信签名方式
                "paySign" : response.signature           //微信签名
            },function(res){
                if(res.err_msg == "get_brand_wcpay_request:ok" ) {
                	if(language != "zh_CN"){$("#wait").remove();$("#enwait").show();}
                    else{$("#wait").show();}
                    countdown = 5;
                    settime();
                    function settime() {
                        $("#waittime").text(countdown);
                        countdown--;
                        if (countdown == 0){
                            window.location.href="/watermachine/mainpage?code=tera123bits";
                        }
                        else{
                            setTimeout(function() { settime() },1000);
                        }
                    }
                }
				else{alert("支付失败！");}
            });
        }
    });
	
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