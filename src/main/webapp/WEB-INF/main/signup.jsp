<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>

<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<script language="javascript" src="/watermachine/static/js/jquery-3.2.1.min.js"></script>
<script language="javascript" src="/watermachine/static/js/flexible.js"></script>
<script language="javascript" src="/watermachine/static/js/signup.js"></script>
<link rel="stylesheet" href="/watermachine/static/css/bootstrap.css">
<link rel="stylesheet" href="/watermachine/static/css/signup.css">
<title>注册</title>
<script>
	var openid = "${openId}";
	var language = "${language}";
	var avatar = "${headimgurl}";
	var nickname = "${nickname}";
</script>
</head>

<body onload="load()" style="font-family: 'Microsoft YaHei' 'Cambria Math';background-color: rgb(240, 240, 240);display: flex;flex-direction: column;align-items: center;">


<!-- 广告图片 -->
<img src="/watermachine/static/pic/signuppic.png" style="width: 101.5%;" id="signuppic"/>

<!-- 输入部分 -->
<div id="inputdiv1" style="text-align: center;width: 100%;">
<input type="tel" class="inputbox" id="tel" maxlength="11" value=" " onfocus="this.value = '', positionhide()" onblur="positionshow()" >
<button class="inputbutton" onclick="signup()" id="signup"> </button>
<button class="shadow">&nbsp;</button>

<input type="tel" class="inputbox" id="icode" maxlength="6" value=" " onfocus="this.value = '', positionhide()" onblur="positionshow()" >
<button class="inputbutton" onclick="icode()" id="vcode"> </button>
<button class="shadow">&nbsp;</button>
</div>

<div id="inputdiv2" style="font-size: 0.4rem;padding-left: 6%;width: 100%;margin-top: 0.5rem">
<p>完成注册后立享<span style="color: rgb(230, 67,64);">5</span>元体验额，免费取水<span style="color: rgb(230, 67,64);">10</span>升！</p>
<a href = "/watermachine/rules">详细规则</a>
</div>

<div id="eninputdiv2" style="font-size: 0.4rem;padding-left: 6%;width: 100%;margin-top: 0.5rem;display:none;">
<p>Get<span style="color: rgb(230, 67,64);"> ¥5</span> for <span style="color: rgb(230, 67,64);">20L </span>free water!</p>
<a href = "/watermachine/rules">Detailed Rules</a>
</div>

<!-- 注册成功div -->
<div id="sucdiv" style="font-size: 0.45rem;padding-left: 6%;width: 100%;margin-top: 0.5rem; display: none;">
注册成功，<br>
<span style="color: rgb(230, 67,64);">5</span>元体验额已发放至您的钱包！<br>
<a style="margin-top: 1rem;text-align: center;">将在3秒后跳转至首页，立享健康水</a>
</div>

<div id="ensucdiv" style="font-size: 0.45rem;padding-left: 6%;width: 100%;margin-top: 0.5rem; display: none;">
Registration succeeds!<br>
You already have <span style="color: rgb(230, 67,64);">¥5</span> in your wallet.<br>
<a style="margin-top: 1rem;text-align: center;">Going to the home page in 3seconds. Enjoy the water NOW!</a>
</div>

<div class="col-sm-12 col-xs-12" id="bottombar">
天风物业<br>
Powered by Terabits
</div>

</body>
</html>