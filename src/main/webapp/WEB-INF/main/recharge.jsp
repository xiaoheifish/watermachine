<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>

<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<script language="javascript" src="/watermachine/static/js/jquery-3.2.1.min.js"></script>
<script language="javascript" src="/watermachine/static/js/flexible.js"></script>
<script language="javascript" src="/watermachine/static/js/recharge.js"></script>
<link rel="stylesheet" href="/watermachine/static/css/bootstrap.css">
<link rel="stylesheet" href="/watermachine/static/css/information.css">
<title>充值</title>
</head>

<body onload="money1(),load();" style="font-family: 'Microsoft YaHei' 'Cambria Math';background-color: rgb(240, 240, 240);display: flex;flex-direction: column;align-items: center;padding-top: 0.15rem;">
<!-- 广告图片 -->
<img src="/watermachine/static/pic/rechargepic.png" style="width: 90%; margin-top:0.5rem;" id="rechargepic"/>

<!-- 按钮选择部分 -->
<div class="moneybuttonbox">
<div onclick="money1();" id="money1" class="moneybutton">
<img id="money1image" src="/watermachine/static/pic/bselect.png" style="height: 0.65rem;"/>
<div id="50" style="margin-left: 15%;">¥ 50<br>送50元</div></div>	

<div onclick="money2();" id="money2" class="moneybutton" style="margin-left: 5%;">
<img id="money2image" src="/watermachine/static/pic/bselect.png" style="height: 0.65rem;"/>
<div id="20" style="margin-left: 15%;">¥ 20<br>送20元</div>
</div>
</div>

<div class="moneybuttonbox">
<div onclick="money3();" id="money3" class="moneybutton">
<img id="money3image" src="/watermachine/static/pic/bselect.png" style="height: 0.65rem;"/>
<div id="10" style="margin-left: 15%;">¥ 10<br>送10元</div>	
</div>

<div onclick="money4();" id="money4" class="moneybutton" style="margin-left: 5%;">
<img id="money4image" src="/watermachine/static/pic/bselect.png" style="height: 0.65rem;"/>
<div id="5" style="margin-left: 15%;">¥ 5<br>送 5 元</div>
</div>
</div>

<!-- 充值按钮 -->
<input type="submit" value="充值" id="recharge" onclick="recharge()">
<p id="rechargewarntext" style="font-size: 0.25rem;margin-top: 0.2rem;">点击“充值”即表示您已同意上述充返规则</p>

<div class="col-sm-12 col-xs-12" id="bottombar">
天风物业<br>
Powered by Terabits
</div>


<!-- 支付成功等候10秒 -->
<div id="wait" class="wait" style="display: none;">
<div class="loadEffect">
        <span></span>
        <span></span>
        <span></span>
        <span></span>
        <span></span>
        <span></span>
        <span></span>
        <span></span>
        <div id="waittime">10</div>
</div>
<div style="margin-top: 90%; font-size: 0.4rem;">
 即将跳转至首页，请稍候
 </div>
 </div>

<div id="enwait" class="wait" style="display: none;">
<div class="loadEffect">
        <span></span>
        <span></span>
        <span></span>
        <span></span>
        <span></span>
        <span></span>
        <span></span>
        <span></span>
        <div id="waittime">10</div>
</div>
<div style="margin-top: 90%; font-size: 0.4rem;">
 Will jump to the home page. Please wait.
 </div>
 </div>

</body>
</html>