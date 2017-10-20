<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>

<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<script language="javascript" src="/watermachine/static/js/jquery-3.2.1.min.js"></script>
<script language="javascript" src="/watermachine/static/js/flexible.js"></script>
<script language="javascript" src="/watermachine/static/js/information.js"></script>
<link rel="stylesheet" href="/watermachine/static/css/bootstrap.css">
<link rel="stylesheet" href="/watermachine/static/css/information.css">

<title>欢迎使用智能直饮水机</title>
<script>
	var status = "${status}";
</script>
</head>

<body onload="money1(),load()" style="font-family: 'Microsoft YaHei' 'Cambria Math';background-color: rgb(240, 240, 240);display: flex;flex-direction: column;align-items: center;padding-top: 0.15rem;">


<!-- 信息显示部分 -->
<div class = "infor">
<img style="height:0.6rem;width:0.522rem;" src="/watermachine/static/pic/location.png">&nbsp;&nbsp; 
<span id="location" style="text-overflow: ellipsis;">${location}</span>
</div>

<div class="infor">
<img style="height:0.6rem;width:0.522rem;" src="/watermachine/static/pic/id.png">&nbsp;&nbsp;
<span id="id">${id}</span>
</div>

<div class="infor">
<img style="height:0.6rem;width:0.522rem;" src="/watermachine/static/pic/status.png">&nbsp;&nbsp;
<span id="status">${status}</span></div>


<!-- 按钮选择部分 -->
<div class="moneybuttonbox">
<div onclick="money1();" id="money1" class="moneybutton">
<img id="money1image" src="/watermachine/static/pic/bselect.png" style="height: 0.65rem;"/>
<div style="margin-left: 15%;">¥&nbsp;0.2<br>300ml</div></div>	

<div onclick="money2();" id="money2" class="moneybutton" style="margin-left: 5%;">
<img id="money2image" src="/watermachine/static/pic/bselect.png" style="height: 0.65rem;"/>
<div style="margin-left: 15%;">¥&nbsp;0.3<br>500ml</div>
</div>
</div>

<div class="moneybuttonbox">
<div onclick="money3();" id="money3" class="moneybutton">
<img id="money3image" src="/watermachine/static/pic/bselect.png" style="height: 0.65rem;"/>
<div style="margin-left: 15%;">¥&nbsp;0.5<br>1L</div>	
</div>

<div onclick="money4();" id="money4" class="moneybutton" style="margin-left: 5%;">
<img id="money4image" src="/watermachine/static/pic/bselect.png" style="height: 0.65rem;"/>
<div style="margin-left: 15%;">¥&nbsp;0.9<br>2L</div>
</div>
</div>

<!-- 确认按钮 -->
<input type="submit" value=" " id="recharge" onclick="recharge()">
<!-- 支付类型选择 -->
<div class="paymenttypebox">
<div class="paymenttype" id="wechat">
<img src="/watermachine/static/pic/bselect.png" id="wechatimage" style="height: 0.35rem;"/>&nbsp;&nbsp;微信支付</div>
<div class="paymenttype" id="balance">
<img src="/watermachine/static/pic/bbselect.png" id="balanceimage" style="height: 0.35rem;"/>&nbsp;&nbsp;余额支付</div>
</div>

<img src="/watermachine/static/pic/tip.png" style="height: 1.6rem;margin-top: 0.2rem" id="tippicture"/>

<div class="col-sm-12 col-xs-12" id="bottombar">
天风物业<br>
Powered by Terabits
</div>


<!-- 订单生成功，跳转等待 -->
<div class="wait" id="wait"  style="display: none;">
<div class="loadEffect">
        <span></span>
        <span></span>
        <span></span>
        <span></span>
        <span></span>
        <span></span>
        <span></span>
        <span></span>
</div>
<div class="waitbox">正在为您准备，请稍候</div>
</div>

<!-- 订单生成功，跳转等待 -->
<div class="wait" id="enwait" style="display: none;">
<div class="loadEffect">
        <span></span>
        <span></span>
        <span></span>
        <span></span>
        <span></span>
        <span></span>
        <span></span>
        <span></span>
</div>
<div class="waitbox">We are preparing for you.<br>Please wait a moment.</div>
</div>

</body>
</html>