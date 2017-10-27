<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>

<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link rel="stylesheet" href="/watermachine/static/css/bootstrap.css">
<link rel="stylesheet" href="/watermachine/static/css/signup.css">
<title>接受邀请</title>
<script>
	var phone = "${phone}";
    var openid = "${openid}";
</script>
</head>

<body style="font-family: 'Microsoft YaHei' 'Cambria Math';background-color: rgb(240, 240, 240);display: flex;flex-direction: column;align-items: center;font-size: 0.4rem;">
<!-- 广告图片 -->
<img src="/watermachine/static/pic/signuppic.png" style="width: 101.5%;" />

<!-- 输入部分 -->
<div id="inputdiv1" style="text-align: center;width: 100%;margin-top: 0.3rem;">
<p>接受邀请并注册，好友与你各得</p>
<p style="font-size: 0.6rem;"><span style="color: rgb(230, 67,64);">5元</span>饮水体验券</p>
<input type="tel" class="inputbox" id="tel" maxlength="11" value="请输入手机号" onfocus="this.value = '', positionhide()" onblur="positionshow()" style="color:#616161">
<button class="inputbutton" onclick="signup()" id="signup" style="color:white;">立即注册</button>
<button class="shadow">&nbsp;</button>

<input type="tel" class="inputbox" id="icode" maxlength="6" value="请输入验证码" onfocus="this.value = '', positionhide()" onblur="positionshow()" style="color:#616161">
<button class="inputbutton" onclick="icode()" style="color:white">确认</button>
<button class="shadow">&nbsp;</button>
</div>


<!-- 注册成功div -->
<div id="sucdiv" style="width: 100%; height: 100% ;position: absolute;top: 0;background-color: rgb(240, 240, 240);display: flex;flex-direction: column;align-items: center;font-size: 0.4rem;display: none;text-align:center;">
<p style="margin-top: 1rem">您已获得</p>
<p style="font-size: 0.6rem;"><span style="color: rgb(230, 67,64);">5元</span>饮水体验券</p>
<img style="width: 5rem; margin-top: 1rem;" src="/watermachine/static/pic/officialQR.jpg">
<p style="margin-top: 2rem;">搜索公众号“<span style="color: rgb(230, 67,64);">钛比科技</span>”或扫描上方二维码</p>
<p>立即开启高品质饮水之旅！</p>
</div>


<!-- 英文版 -->
<!-- 输入部分 
<div id="eninputdiv1" style="text-align: center;width: 100%;margin-top: 0.3rem;">
<p>Sign up! Both of you will receive a </p>
<p style="font-size: 0.6rem;"><span style="color: rgb(230, 67,64);">¥5</span> coupon </p>
<input type="tel" class="inputbox" id="tel" maxlength="11" value="input your phone number" onfocus="this.value = '', positionhide()" onblur="positionshow()" >
<button class="inputbutton" onclick="signup()" id="signup">Register</button>
<button class="shadow">&nbsp;</button>

<input type="tel" class="inputbox" id="icode" maxlength="6" value="input verification code" onfocus="this.value = '', positionhide()" onblur="positionshow()" >
<button class="inputbutton" onclick="icode()">Confirm</button>
<button class="shadow">&nbsp;</button>
</div>-->


<!-- 注册成功div 
<div id="ensucdiv" style="width: 100%; height: 100% ;position: absolute;top: 0;background-color: rgb(240, 240, 240);display: flex;flex-direction: column;align-items: center;font-size: 0.4rem;display: none;">
<p style="margin-top: 1rem">You received</p>
<p style="font-size: 0.6rem;"><span style="color: rgb(230, 67,64);">¥5</span> coupon </p>
<img style="width: 5rem; margin-top: 1rem;" src="/watermachine/static/pic/officialQR.jpg">
<p style="margin-top: 2rem;">Search official accounts “<span style="color: rgb(230, 67,64);">钛比科技</span>” or scan the QR code.</p>
<p>Drink high quality water Now!</p>
</div>
-->


<div class="col-sm-12 col-xs-12" id="bottombar">
天风物业<br>
powered by Terabits
</div>

<script language="javascript" src="/watermachine/static/js/jquery-3.2.1.min.js"></script>
<script language="javascript" src="/watermachine/static/js/flexible.js"></script>
<script language="javascript" src="/watermachine/static/js/invited_signup.js"></script>
</body>
</html>