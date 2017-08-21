var openid,language;
function load(){
	openid = getCookie("openid");
	language = getCookie("language");


	
	if(language != "zh_CN"){
		document.getElementById('50').innerHTML ="¥ 50\n<br/>Gift ¥30";
		document.getElementById('20').innerHTML ="¥ 20\n<br/>Gift ¥12";
		document.getElementById('10').innerHTML ="¥ 10\n<br/>Gift ¥5";
		document.getElementById('5').innerHTML ="¥ 5\n<br/>Gift ¥2";
		$("#recharge").val("recharge");
		$("#rechargewarntext").text("Click 'recharge' means you agree the above rules.");
		$("#rechargepic").attr("src","/watermachine/static/pic/enrechargepic.png");
		$("title").html("recharge");
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


var money;
//容量选择按钮选中效果
function money1(){
	money = 50;
	$("#money1image").attr("src", "/watermachine/static/pic/aselect.png");
	$("#money2image").attr("src", "/watermachine/static/pic/bselect.png");
	$("#money3image").attr("src", "/watermachine/static/pic/bselect.png");
	$("#money4image").attr("src", "/watermachine/static/pic/bselect.png");
	
}

function money2(){
	money = 20;
	$("#money1image").attr("src", "/watermachine/static/pic/bselect.png");
	$("#money2image").attr("src", "/watermachine/static/pic/aselect.png");
	$("#money3image").attr("src", "/watermachine/static/pic/bselect.png");
	$("#money4image").attr("src", "/watermachine/static/pic/bselect.png");
}

function money3(){
	money = 10;
	$("#money1image").attr("src", "/watermachine/static/pic/bselect.png");
	$("#money2image").attr("src", "/watermachine/static/pic/bselect.png");
	$("#money3image").attr("src", "/watermachine/static/pic/aselect.png");
	$("#money4image").attr("src", "/watermachine/static/pic/bselect.png");
}

function money4(){
	money = 5;
	$("#money1image").attr("src", "/watermachine/static/pic/bselect.png");
	$("#money2image").attr("src", "/watermachine/static/pic/bselect.png");
	$("#money3image").attr("src", "/watermachine/static/pic/bselect.png");
	$("#money4image").attr("src", "/watermachine/static/pic/aselect.png");
}

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
                    $("#wait").show();
                    var countdown = 10;
                    settime();
                    function settime() {
                        $("#waittime").html(countdown);
                        countdown--;
                        if (countdown == 0){
                            window.location.href="/watermachine/callback";
                        }
                        else{
                            setTimeout(function() { settime() },1000);
                        }
                    }
                }
            });
        }
    });
	
}


//充值取消跳回information页
function cancel(){
	//跳转页面
}