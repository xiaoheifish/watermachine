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

<title>欢迎使用智能饮水机</title>
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
<div style="margin-left: 15%;">¥&nbsp;0.1<br>200ml</div></div>	

<div onclick="money2();" id="money2" class="moneybutton" style="margin-left: 5%;">
<img id="money2image" src="/watermachine/static/pic/bselect.png" style="height: 0.65rem;"/>
<div style="margin-left: 15%;">¥&nbsp;0.2<br>500ml</div>
</div>
</div>

<div class="moneybuttonbox">
<div onclick="money3();" id="money3" class="moneybutton">
<img id="money3image" src="/watermachine/static/pic/bselect.png" style="height: 0.65rem;"/>
<div style="margin-left: 15%;">¥&nbsp;0.3<br>1L</div>	
</div>

<div onclick="money4();" id="money4" class="moneybutton" style="margin-left: 5%;">
<img id="money4image" src="/watermachine/static/pic/bselect.png" style="height: 0.65rem;"/>
<div style="margin-left: 15%;">¥&nbsp;0.5<br>2L</div>
</div>
</div>

<!-- 确认按钮 -->
<input type="submit" value="确认" id="recharge" onclick="recharge()">

<img src="/watermachine/static/pic/tip.png" style="height: 2rem;margin-top: 0.5rem" />

<div class="col-sm-12 col-xs-12" id="bottombar">
天风物业<br>
powered by Terabits
</div>

<!-- 订单生成功，跳转等待 -->
<div class="wait" id="wait" style="display: none;height:100%;
	width:100%;
	position:absolute;
	z-index:1;
	font-size:0.5rem;
	padding-top:30%;
	text-align: center;
	background-color: rgba(136 ,136, 136, 0.5);
	top: 0;">
<div class="waitbox" style="position: absolute;
	top: 20%;left: 50%;-webkit-transform: translate(-50%, -20%);-moz-transform: translate(-50%, -20%);-ms-transform: translate(-50%, -20%);-o-transform: translate(-50%, -20%);transform: translate(-50%, -20%);
	height: 8rem;
	width: 8rem;
	background-color: white;
	font-size: 0.4rem;
	padding-top: 0.5rem;
	text-align: center;">
 <p>花费/¥</p>
 <p style="font-size: 1rem; color: rgb(233, 125, 123);" id="waitmoney">10</p>
 <p>取水量/L</p>
 <p style="font-size: 1rem; color: rgb(37, 109, 243);" id="waitwater">10</p>
 <img style="width: 2.5rem"  src="/watermachine/static/pic/loading.gif"/>
 <p style="font-size: 0.3rem; color: rgb(136,136,136);">正在为您准备，请稍候</p>
</div>
</div>

<!-- 订单生成功，跳转等待 -->
<div class="wait" id="enwait" style="display: none;height:100%;
	width:100%;
	position:absolute;
	z-index:1;
	font-size:0.5rem;
	padding-top:30%;
	text-align: center;
	background-color: rgba(136 ,136, 136, 0.5);
	top: 0;">
<div class="waitbox" style="position: absolute;
	top: 20%;left: 50%;-webkit-transform: translate(-50%, -20%);-moz-transform: translate(-50%, -20%);-ms-transform: translate(-50%, -20%);-o-transform: translate(-50%, -20%);transform: translate(-50%, -20%);
	height: 8rem;
	width: 8rem;
	background-color: white;
	font-size: 0.4rem;
	padding-top: 0.5rem;
	text-align: center;">
 <p>COST/¥</p>
 <p style="font-size: 1rem; color: rgb(233, 125, 123);" id="waitmoney">10</p>
 <p>WATER/L</p>
 <p style="font-size: 1rem; color: rgb(37, 109, 243);" id="waitwater">10</p>
 <img style="width: 2.5rem"  src="/watermachine/static/pic/loading.gif"/>
 <p style="font-size: 0.3rem; color: rgb(136,136,136);">We are preparing for you.
Please wait a moment.
</p>
</div>
</div>

</body>
</html>