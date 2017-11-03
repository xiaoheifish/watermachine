<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>

<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link rel="stylesheet" href="/watermachine/static/css/bootstrap.css">
<link rel="stylesheet" href="/watermachine/static/css/login.css">

<title>勋章兑换</title>
</head>

<body onload="loadex()" style="font-family: 'Microsoft YaHei' 'Cambria Math';background-color: rgb(240, 240, 240);display: flex;flex-direction: column;align-items: center;text-align: center;font-size: 0.45rem;padding-top: 1rem;">

<!-- 勋章兑换 -->
<p>已连续使用<span id="day"></span>天！</p>
<p style="margin-top: 0.2rem;">您获得的勋章：<span id="medal" ></span></p>

<div class="box">
<img style='width: 3rem;' src='/watermachine/static/pic/exgold.png'>&nbsp;&nbsp;&nbsp;&nbsp;
<button onclick="minusgold()" class="minus">&nbsp;</button>
&nbsp;&nbsp;<span id="goldnum">0</span>&nbsp;&nbsp;
<button onclick="plusgold()" class="plus">&nbsp;</button>
</div>

<div class="box">
<img style='width: 3rem;' src='/watermachine/static/pic/exsilver.png'>&nbsp;&nbsp;&nbsp;&nbsp;
<button onclick="minussilver()" class="minus">&nbsp;</button>
&nbsp;&nbsp;<span id="silvernum">0</span>&nbsp;&nbsp;
<button onclick="plussilver()" class="plus">&nbsp;</button>
</div>

<div class="box">
<img style='width: 3rem;' src='/watermachinestatic/pic/exbronze.png'>&nbsp;&nbsp;&nbsp;&nbsp;
<button onclick="minusbronze()" class="minus">&nbsp;</button>
&nbsp;&nbsp;<span id="bronzenum">0</span>&nbsp;&nbsp;
<button onclick="plusbronze()" class="plus">&nbsp;</button>
</div>

<button id="RQ" onclick="EX()">兑换</button><br>



<script language="javascript" src="/watermachine/static/js/jquery-3.2.1.min.js"></script>
<script src="http://cdn.bootcss.com/blueimp-md5/1.1.0/js/md5.js"></script>
<script language="javascript" src="/watermachine/static/js/flexible.js"></script>
<script language="javascript" src="/watermachine/static/js/wallet.js"></script>
</body>
</html>