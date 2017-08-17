var money;
//1、2、3、5元按钮选择改变背景
function money1(){
    money = 1;
    document.getElementById('money1image').innerHTML = ''
    document.getElementById("money1image").innerHTML='<img src="/watermachine/static/pic/aselect.png" height="50%"/>';
    document.getElementById('money2image').innerHTML = ''
    document.getElementById("money2image").innerHTML='<img src="/watermachine/static/pic/bselect.png" height="50%"/>';
}

function money2(){
    money = 2;
    document.getElementById('money1image').innerHTML = ''
    document.getElementById("money1image").innerHTML='<img src="/watermachine/static/pic/bselect.png" height="50%"/>';
    document.getElementById('money2image').innerHTML = ''
    document.getElementById("money2image").innerHTML='<img src="/watermachine/static/pic/aselect.png" height="50%"/>';
}



//跳转充值确认页
function recharge() {
    if(money == null){
        alert("请选择充值金额！");//未选择金额不跳转
    }
    else{

        var finalmoney = money;
        var id = $("#id").html();
        var primiurl = "http://www.terabits-wx.cn/watermachine/callback/"+id+"_"+finalmoney;
        var encodeurl = encodeURIComponent(
            primiurl
        );
        var url = "https://open.weixin.qq.com/connect/oauth2/authorize?appid=wx34690a5342af3858&redirect_uri="+encodeurl+"&response_type=code&scope=snsapi_base&state=1#wechat_redirect";
        window.location.href=url;
    }
}