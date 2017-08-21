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

<title>意见反馈</title>
</head>

<body onload="loadservice()" style="font-family: 'Microsoft YaHei' 'Cambria Math';background-color: rgb(240, 240, 240);display: flex;flex-direction: column;align-items: center;font-size: 0.4rem;">

<!-- 二级菜单-我的客服 -->
<textarea id="suggestion" onfocus="this.value = ''">欢迎您提出对智能饮水机的感受和意见，期待您的声音。</textarea>
<div>
<p style="position: absolute;padding: 0.3rem;margin-top: 0.2rem;" id="emailtext">您的邮箱</p><input type="email" id="email" class="inputdiv"></input></div>
<button class="RQ" onclick="suggestion();" id="suggestionbutton">提交</button>

<p id="text" style="font-size: 0.3rem;margin-top: 0.3rem;text-align: center;">感谢您的宝贵意见，<span style="color: rgb(230, 67,64);">我们将选取有价值的意见及建议进行奖励</span>。<br>联系我们：sales@terabits.cn</p>
<p id="entext" style="display:none;font-size: 0.3rem;margin-top: 0.3rem;text-align: center;">Thanks for your advice.<span style="color: rgb(230, 67,64);">We will pick valuable comments and suggestions and reward.</span><br>contact us：sales@terabits.cn</p>

</body>
</html>