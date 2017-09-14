<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>

<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<script language="javascript" src="/watermachine/static/js/jquery-3.2.1.min.js"></script>
	<script src="http://cdn.bootcss.com/blueimp-md5/1.1.0/js/md5.js"></script>
<script language="javascript" src="/watermachine/static/js/flexible.js"></script>
<script language="javascript" src="/watermachine/static/js/wallet.js"></script>
	<link rel="stylesheet" href="/watermachine/static/css/bootstrap.css">
<link rel="stylesheet" href="/watermachine/static/css/login.css">

<title>我的钱包</title>
	<script>
        var balance = "${balance}";
        var recharge = "${recharge}";
        var present = "${present}";
	</script>
</head>

<body onload="loadwallet()" style="font-family: 'Microsoft YaHei' 'Cambria Math';background-color: rgb(240, 240, 240);display: flex;flex-direction: column;align-items: center;text-align: center;font-size: 0.3rem;">


<!-- 二级菜单-我的钱包 -->
<div style="background-image: url(/watermachine/static/pic/balancepic.png);background-size: 100% 100%;width: 100%;">
<div id="balancebox">
<p style="font-size: 1.4rem">${balance}</p>
<p id="balancetext">余额(元)</p>
</div>

<div style="display: inline;margin-top: 2rem;color: rgb(113,113,113);">
	<div class="presenttext"><p style="display: inline-block;font-size: 0.4rem;color: black;">${recharge}</p><p id="rechargetext"></p></div>
	<div class="presenttext"><p style="display: inline-block;font-size: 0.4rem;color: black;">${present}</p><p id="presenttext"></p></div>
</div>
</div>


<button class="RQ" onclick="jumprecharge()" id="jumprecharge" style="color: rgb(37, 109, 243)">充值</button><br>
<button class="RQ" onclick="jumpreimburse()" id="jumpreimburse" style="color: rgb(37, 109, 243)">提现</button><br>

<a href="/watermachine/refundrec">提现记录</a>



<!-- 提现窗口 -->
<div id="refunddiv" style="position: absolute;height: 100%; width: 100%; top: 0;left: 0; background-color: rgb(240, 240, 240);text-align: center;padding-top: 2rem;display: none;">
<p style="font-size: 0.4rem">提现金额(元)</p>
<p style="font-size: 1.2rem">${recharge}</p><br><br>
<button class="RQ" onclick="refund();" id="confirm">确定</button><br>
<img style="height: 0.8rem;margin-top: 1rem;" src="/watermachine/static/pic/lefttime.png"><br><br>
1.点击“确定”即表示您同意下述规则。<br>
2.请至“提现记录”查询相关操作状态。<br>
3.提现成功后，您的账户余额将被清空，赠送余额被收回。<br>
4.提现金额将在提现申请提交后1-2个工作日转入您的微信钱包。<br>
</div>

<div id="enrefunddiv" style="position: absolute;height: 100%; width: 100%; top: 0;left: 0; background-color: rgb(240, 240, 240);text-align: center;padding-top: 2rem;display: none;">
<p style="font-size: 0.4rem">Refund Amount(¥)</p>
<p style="font-size: 1.2rem">${recharge}</p><br><br>
<button class="RQ" onclick="refund();" id="enconfirm">Confirm</button><br>
<img style="height: 0.8rem;margin-top: 1rem;" src="/watermachine/static/pic/lefttime.png"><br><br>
1.Click "Confirm" means you agree the following rules.<br>
2.You can check your refund process in "Refund record".<br>
3.After the refund succeed, your balance will be cleared.<br>
4.The refund amount will be transfered to you Wechat Wallet in 1-2 days.<br>
</div>

</body>
</html>