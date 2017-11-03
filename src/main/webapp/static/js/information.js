var openid,language;
function load(){
	openid = getCookie("openid");
	language = getCookie("language");
	balance();

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
}

/* 2秒刷新函数 */
function settime(){
    setTimeout(function() {
        $.ajax({
			type:'POST',
			url:'/watermachine/terminal/state',
			data:{
				"displayid": id
			},
			dataType:'json',
			success:function(data){
				if(data["state"] == "下单中"){
					settime();
				}
				if(data["state"] == "使用中"){
					window.location.href = "/watermachine/info/" + id;
				}
				if(data["state"] == "空闲"){
					if(openid == data["openid"]){
						alert("下单失败，您支付的金额将在五分钟内退回微信账户。");
					}
					else{
						window.location.href = "/watermachine/info/" + id;
					}
				}
				if(data["state"] == "不可使用"){
					if(openid == data["openid"]){
						alert("下单失败，您支付的金额将在五分钟内退回微信账户。");
					}
					else{
						window.location.href = "/watermachine/info/" + id;
					}
				}
				$("#state").text(data["state"]);
			}
		});
    },2000);
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
	$("#money3image").attr("src", "/waterm.achine/static/pic/bselect.png");
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

//支付方式选择
var payment_type;
function wechat(){
	payment_type = 1;
	$("#wechatimage").attr("src", "/watermachine/static/pic/bbselect.png");
	$("#balanceimage").attr("src", "/watermachine/static/pic/bselect.png");
}

function balance(){
	payment_type = 2;
	$("#wechatimage").attr("src", "/watermachine/static/pic/bselect.png");
	$("#balanceimage").attr("src", "/watermachine/static/pic/bbselect.png");
}

//跳转使用页
function recharge() {
	if(money == null){
		alert("请选择取水量！");
	}
	else{
		$("#recharge").attr("disabled", true);
		if(payment_type == "2"){
			order();
		}
		else{
			$.ajax({
        		type:'POST',
        		url:'/watermachine/wechatconsume',
        		data: {
           			 "openid": openid,
           			 "cost": money,
           			 "displayid": id
        		},
        		dataType: 'json',
        		error: function(XMLHttpRequest, textStatus, errorThrown){
            		alert(XMLHttpRequest.status);
        		},
        		success:function(response){
        			if(response["result"] == "success"){
                        //判断前后端openid是否一致
                        WeixinJSBridge.invoke('getBrandWCPayRequest',{
                            "appId" : response.appId,                  //公众号名称，由商户传入
                            "timeStamp":response.timestamp,          //时间戳，自 1970 年以来的秒数
                            "nonceStr" : response.nonce,         //随机串
                            "package" : response.packageName,      //商品包信息</span>
                            "signType" : response.signType,        //微信签名方式
                            "paySign" : response.signature           //微信签名
                        },function(res){
                            if(res.err_msg == "get_brand_wcpay_request:ok" ) {
                                window.location.href = "/watermachine/info/" + id;
                            }
                            else{
                                alert("支付失败，请重试！");
                                $("#recharge").removeAttr("disabled");
                            }
                        });
					}
					else{
        				alert("支付失败,请重试。");
        				$("#recharge").removeAttr("disabled");
					}

        		}
    		});
		}
	}
}

//下单函数
function order(){
	if(language != "zh_CN"){$("#wait").remove();$("#enwait").show();}
    else{$("#wait").show();$("#enwait").remove();}

	//发起扣款查询及跳转
	$.ajax({
		type:'POST',
		url:'/watermachine/consumeorder',
		data:{
			"openid":openid,
			"cost":money,
			"displayid": id
		},
		dataType:'json',
		success:function(data){
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
			else{
				//跳转到using页
				if(data["result"] == "success") {
	                window.location.href = "/watermachine/info/" + id;
	            }
	            else if(data["result"] == "in order service"){
					if(language != "zh_CN"){alert("Failure to place order");}
					else{alert("下单失败，请稍后重试！in order service");}
					window.location.href = "/watermachine/mainpage?code=tera123bits";
	            }
	            else if(data["result"] == "openid not match"){
	                if(language != "zh_CN"){alert("Failure to place order");}
					else{alert("下单失败，请稍后重试！openid not match");}
					window.location.href = "/watermachine/mainpage?code=tera123bits";
	            }
	            else if(data["result"] == "error"){
	                if(language != "zh_CN"){alert("Failure to place order");}
					else{alert("下单失败，请稍后重试！error");}
					window.location.href = "/watermachine/mainpage?code=tera123bits";
	            }
	            else if(data["result"] == "order later"){
	                if(language != "zh_CN"){alert("Failure to place order");}
					else{alert("下单失败，请稍后重试！order later");}
	            }
			}    
		}
	});
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

//下单中页面的刷新函数
function orderload(){
	openid = getCookie("openid");
	language = getCookie("language");
	
	if(language != "zh_CN"){
		$("title").text("Information");
	}
	else{
		$("#recharge").val("确认");
	}
	if(status == "下单中"){
        settime();
    } 
}