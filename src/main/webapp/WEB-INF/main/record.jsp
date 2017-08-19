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

<title>取水记录</title>
</head>

<body onload="loadrecord()" style="font-family: 'Microsoft YaHei' 'Cambria Math';background-color: rgb(240, 240, 240);display: flex;flex-direction: column;align-items: center;padding-top: 0.1rem;">


<!-- 二级菜单-取水记录 -->

<div class="recordcontent" id="record0" style="display:none;">
	<img style="width: 0.4rem" src="/watermachine/static/pic/clock.png">&nbsp;<span id="time"></span><br>
	<span class="cost">取水消费:</span>&nbsp;<span style="color: #e9ca33;">¥<span style="font-size: 1.5em" id="cost"></span></span>&nbsp;&nbsp;&nbsp;<span class="id">
	饮水机编号:</span><span id="idtext"></span>&nbsp;&nbsp;&nbsp;<span class="water">取水量:</span><span id="water"></span><span class="L">升</span>
</div>



</body>
</html>