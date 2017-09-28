<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>

<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<script language="javascript" src="/watermachine/static/js/jquery-3.2.1.min.js"></script>
<script language="javascript" src="/watermachine/static/js/flexible.js"></script>
<script language="javascript" src="/watermachine/static/js/login.js"></script>
<link rel="stylesheet" href="/watermachine/static/css/bootstrap.css">
<link rel="stylesheet" href="/watermachine/static/css/login.css">

<title>充值记录</title>
</head>

<body onload="loadrechargerecord()" style="font-family: 'Microsoft YaHei' 'Cambria Math';background-color: rgb(240, 240, 240);display: flex;flex-direction: column;align-items: center;padding-top: 0.1rem;">


<!-- 二级菜单-充值记录 -->

<div class="recordcontent" id="record0" style="display:none;">
	<img style="width: 0.35rem" src="/watermachine/static/pic/clock.png">&nbsp;<span id="time" style="font-size: 0.35rem"></span><br>
	<span class="cost">金额:</span>&nbsp;
	<span style="color: #e9ca33;">¥<span style="font-size: 1.5em" id="amount"></span><span class="gift"> 送</span>¥<span id="present"></span></span>
	&nbsp;&nbsp;
	<span class="id">单号:</span><span id="idtext"></span>
</div>

<div id="nothingbox" style="font-size: 0.45rem;text-align: center;display: none;">
	<img style="width: 2rem;margin-top: 2rem;" src="/watermachine/static/pic/nothing.png">
	<p style="margin-top: 1rem;" id="nothingtext"> </p>
</div>

</body>
</html>