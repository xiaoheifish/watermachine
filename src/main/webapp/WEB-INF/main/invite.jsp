<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>

<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<script language="javascript" src="/watermachine/static/js/jquery-3.2.1.min.js"></script>
<script language="javascript" src="/watermachine/static/js/flexible.js"></script>
<script language="javascript" src="/watermachine/static/js/invite.js"></script>
<script src="http://res.wx.qq.com/open/js/jweixin-1.0.0.js"></script>
<link rel="stylesheet" href="/watermachine/static/css/bootstrap.css">
<link rel="stylesheet" href="/watermachine/static/css/login.css">

<title>邀请好友</title>
</head>

<body onload="loadinvite();" style="font-family: 'Microsoft YaHei' 'Cambria Math';background-color: rgb(240, 240, 240);display: flex;flex-direction: column;align-items: center;font-size: 0.3rem;">

<div id="ch" style="text-align: center;">
<img style="width: 7rem; margin-top: 1rem;" src="/watermachine/static/pic/invitation.png">
<p>邀请好友，好友与你各得</p>
<p style="font-size: 0.7rem">5元饮水体验券</p>
<br><br><br>
<div style="display: inline;">
<div id="friends" onclick="friends();" style="text-align: center; display: inline-block;">
<img style="width: 1.3rem" src="/watermachine/static/pic/invite-friends.png"><br>发送给好友</div>
<div id="moments" onclick="moments();" style="text-align: center; display: inline-block;margin-left: 1rem;">
<img style="width: 1.3rem" src="/watermachine/static/pic/invite-moments.png"><br>分享至朋友圈</div>
</div>
<br><br><br>
<a href = "/watermachine/rules">详细活动规则</a>
</div>

<div id="en" style="text-align: center;">
<img style="width: 7rem; margin-top: 1rem;" src="/watermachine/static/pic/invitation.png">
<p>Invite your friends. Both of you will receive a</p>
<p style="font-size: 0.7rem">¥5 coupon</p>
<br><br><br>
<div>
<div id="friends" onclick="friends();" style="text-align: center; display: inline-block;">
<img style="width: 1.3rem" src="/watermachine/static/pic/invite-friends.png"><br>Send to chat</div>
<div id="moments" onclick="moments();" style="text-align: center; display: inline-block;margin-left: 1rem;">
<img style="width: 1.3rem" src="/watermachine/static/pic/invite-moments.png"><br>Share on moments
</div>
</div>
<br><br><br>
<a href = "/watermachine/rules">Detail Rules</a>
</div>

<div class="col-sm-12 col-xs-12" id="bottombar">
天风物业<br>
powered by Terabits
</div>


</body>
</html>