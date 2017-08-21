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

<title>我的钱包</title>
</head>

<body onload="loadwallet()" style="font-family: 'Microsoft YaHei' 'Cambria Math';background-color: rgb(240, 240, 240);display: flex;flex-direction: column;align-items: center;">
<script>
    var balance = "${balance}";
</script>

<div id="walletbox">
<!-- 二级菜单-我的钱包 -->
<div id="balance">
<p>${balance}</p>
<p style="font-size: 0.3rem;" id="balancetext">余额(元)</p>
</div>


<button class="RQ" onclick="jumprecharge()" id="jumprecharge">充值</button><br>

<div class="col-sm-12 col-xs-12" id="bottombar">
天风物业<br>
powered by Terabits
</div>
</div>

</body>
</html>