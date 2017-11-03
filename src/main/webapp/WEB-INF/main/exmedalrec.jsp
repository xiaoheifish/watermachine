<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>

<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link rel="stylesheet" href="/watermachine/static/css/bootstrap.css">
<link rel="stylesheet" href="/watermachine/static/css/login.css">

<title>勋章兑换记录</title>
</head>

<body onload="exchangerec()" style="font-family: 'Microsoft YaHei' 'Cambria Math';background-color: rgb(240, 240, 240);display: flex;flex-direction: column;align-items: center;padding-top: 0.1rem;">

<!-- 二级菜单-取水记录 -->
<div class="recordcontent" id="record0" style="display:none;">
	<img style="width: 0.4rem" src="/watermachine/static/pic/clock.png">&nbsp;<span id="time"></span><br>
	<p style="margin-left: 0.4rem; margin-top: 0.15rem;">兑换勋章：&nbsp;<span id="medal"><img style='width: 0.4rem' src='/static/pic/silver.png'></span>&nbsp;&nbsp;&nbsp;兑换金额：&nbsp;<span style="color: #e9ca33;">¥</span><span id="money" style="color: #e9ca33;">1</span></p>
</div>

<div id="nothingbox" style="font-size: 0.45rem;text-align: center;display: none;">
	<img style="width: 2rem;margin-top: 2rem;" src="/watermachine/static/pic/nothing.png">
	<p style="margin-top: 1rem;" id="nothingtext"> </p>
</div>

<script language="javascript" src="/watermachine/static/js/jquery-3.2.1.min.js"></script>
<script language="javascript" src="/watermachine/static/js/flexible.js"></script>
<script language="javascript" src="/watermachine/static/js/wallet.js"></script>
</body>
</html>