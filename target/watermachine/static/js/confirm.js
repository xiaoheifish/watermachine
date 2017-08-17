//在支付确认界面显示数字
function load(){
    if(money == 1)
        time = 3;
    if(money == 2)
        time = 6;
    $("#time").append(time);
}


function confirm(){
    $.ajax({
        type:'POST',
        url:'/watermachine/wxpay',
        data: {
            "id":id,
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
                            window.location.href="http://www.terabits-wx.cn/watermachine/info/" + id;
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
    window.history.back(-1);
}