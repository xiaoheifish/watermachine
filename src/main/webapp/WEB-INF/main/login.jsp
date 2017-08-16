<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
 <script>!function(e){function t(a){if(i[a])return i[a].exports;var n=i[a]={exports:{},id:a,loaded:!1};return e[a].call(n.exports,n,n.exports,t),n.loaded=!0,n.exports}var i={};return t.m=e,t.c=i,t.p="",t(0)}([function(e,t){"use strict";Object.defineProperty(t,"__esModule",{value:!0});var i=window;t["default"]=i.flex=function(e,t){var a=e||100,n=t||1,r=i.document,o=navigator.userAgent,d=o.match(/Android[\S\s]+AppleWebkit\/(\d{3})/i),l=o.match(/U3\/((\d+|\.){5,})/i),c=l&&parseInt(l[1].split(".").join(""),10)>=80,p=navigator.appVersion.match(/(iphone|ipad|ipod)/gi),s=i.devicePixelRatio||1;p||d&&d[1]>534||c||(s=1);var u=1/s,m=r.querySelector('meta[name="viewport"]');m||(m=r.createElement("meta"),m.setAttribute("name","viewport"),r.head.appendChild(m)),m.setAttribute("content","width=device-width,user-scalable=no,initial-scale="+u+",maximum-scale="+u+",minimum-scale="+u),r.documentElement.style.fontSize=a/2*s*n+"px"},e.exports=t["default"]}]);  flex(100, 1);</script>
 <meta name="viewport" content="width=device-width,height=device-height, initial-scale=1.0">
<script language="javascript" src="/watermachine/static/js/jquery-3.2.1.min.js"></script>
<script language="javascript" src="/watermachine/static/js/login.js"></script>
<script src="http://res.wx.qq.com/open/js/jweixin-1.0.0.js"></script>
<link rel="stylesheet" href="/watermachine/static/css/bootstrap.css">
<link rel="stylesheet" href="/watermachine/static/css/login.css">
<style type="text/css">
            html,body { height : 100%; width : 100%;}
</style>
<title>欢迎使用智能饮水机！</title>
</head>

<body onload="load();" style="background-image: url(/watermachine/static/pic/BG1.jpg);background-size: 100% 100%;font-family: 'Microsoft YaHei' 'Cambria Math';">


<!-- 二维码扫描部分 -->
<div class="col-sm-5 col-xs-12" id="RQbox" style="padding:0;width:100%;padding-top: 0.05rem;">

<button id="RQ" onclick="RQ();"><img width="60%" src="/watermachine/static/pic/scan.png"/></button>
</div>

<!-- 节约用水提示 -->
<div style="position:absolute; top:0.2rem; right:0px;"><img height="50px" width="100px" src="/watermachine/static/pic/warning1.png"></div>

<!-- 我的账户按钮 -->
<input type="button" style="position:absolute; top:0.2rem; left:10px;outline: none;border: none;background-image: url(/watermachine/static/pic/account.png);background-size: 100% 100%;height:30px;width: 30px;" value="" onclick="openmenu();">

<!-- 菜单-我的账户 -->
<div style="height: 100%;width: 40%; background-color: rgb(250, 250, 250);position: absolute;left: -40%; z-index: 1;padding-top: 10%;color: rgb(123,123,123)" id="menu">
<button onclick="closemenu();" style="border: none; outline: none; background-color: transparent;position: absolute;right: 5%; top: 3%;"><img width="17px" src="/watermachine/static/pic/back.png">&nbsp;关闭</button>
<div style="margin-left: 25%;width: auto;">
<img src="/watermachine/static/pic/avatar.png" width="70px"/><br>
<p style="font-size: 1.4em; color: black;">&nbsp;Jhone</p>
</div>

<button id="record" onclick="record();" style="border: none; outline: none; height: 10%;padding: 0; width: 100%;padding-left: 10%;text-align: left;"><img width="30px" src="/watermachine/static/pic/record.png"/>&nbsp;&nbsp;&nbsp;&nbsp;取水记录</button>

<button id="wallet" onclick="wallet();" style="border: none; outline: none; height: 10%;padding: 0; width: 100%;padding-left: 10%;text-align: left;"><img width="30px" src="/watermachine/static/pic/wallet.png"/>&nbsp;&nbsp;&nbsp;&nbsp;我的钱包</button>

<button id="invite" onclick="invite();" style="border: none; outline: none; height: 10%;padding: 0; width: 100%;padding-left: 10%;text-align: left;"><img width="30px" src="/watermachine/static/pic/invite.png"/>&nbsp;&nbsp;&nbsp;&nbsp;邀请好友</button>

<button id="message" onclick="message();" style="border: none; outline: none; height: 10%;padding: 0; width: 100%;padding-left: 10%;text-align: left;"><img width="30px" src="/watermachine/static/pic/message.png"/>&nbsp;&nbsp;&nbsp;&nbsp;我的消息</button>

<button id="service" onclick="service();" style="border: none; outline: none; height: 10%;padding: 0; width: 100%;padding-left: 10%;text-align: left;"><img width="30px" src="/watermachine/static/pic/service.png"/>&nbsp;&nbsp;&nbsp;&nbsp;我的客服</button>
</div>

<!-- 菜单-取水记录 -->
<div style="height: 100%;width: 100%; background-color: rgb(246, 246, 246);position: absolute;left: -100%; z-index: 1;padding-top: 10%;color: rgb(123,123,123);" id="recordbox">
<button onclick="closerecordbox();" style="border: none; outline: none; background-color: transparent;position: absolute;right: 5%; top: 3%;"><img width="17px" src="/watermachine/static/pic/back.png">&nbsp;返回</button>

<div style="width: 94%; height: 11.5%; background-color: white;margin: 3%;padding: 4%;">
<img width="10px" src="/watermachine/static/pic/clock.png">&nbsp;2017.08.05 10:02:53<br>
取水消费&nbsp;<span style="color: #e9ca33;">¥<span style="font-size: 1.5em">0.03</span></span>&nbsp;&nbsp;&nbsp;饮水机编号:100001
</div>

<div style="width: 94%; height: 11.5%; background-color: white;margin: 3%;padding: 4%;">
<img width="10px" src="/watermachine/static/pic/clock.png">&nbsp;2017.08.03 15:04:43<br>
取水消费&nbsp;<span style="color: #e9ca33;">¥<span style="font-size: 1.5em">0.05</span></span>&nbsp;&nbsp;&nbsp;饮水机编号:100003
</div>

<div style="width: 94%; height: 11.5%; background-color: white;margin: 3%;padding: 4%;">
<img width="10px" src="/watermachine/static/pic/clock.png">&nbsp;2017.08.03 10:01:33<br>
取水消费&nbsp;<span style="color: #e9ca33;">¥<span style="font-size: 1.5em">0.05</span></span>&nbsp;&nbsp;&nbsp;饮水机编号:100012
</div>

<div style="width: 94%; height: 11.5%; background-color: white;margin: 3%;padding: 4%;">
<img width="10px" src="/watermachine/static/pic/clock.png">&nbsp;2017.08.02 09:15:23<br>
取水消费&nbsp;<span style="color: #e9ca33;">¥<span style="font-size: 1.5em">0.05</span></span>&nbsp;&nbsp;&nbsp;饮水机编号:100025
</div>

<div style="width: 94%; height: 11.5%; background-color: white;margin: 3%;padding: 4%;">
<img width="10px" src="/watermachine/static/pic/clock.png">&nbsp;2017.08.1 08:59:21<br>
取水消费&nbsp;<span style="color: #e9ca33;">¥<span style="font-size: 1.5em">0.03</span></span>&nbsp;&nbsp;&nbsp;饮水机编号:100033
</div>
</div>



<!-- 手动输入编号部分 -->
<div class="col-sm-12 col-xs-12" id="number" style="padding:0;width:100%;">
手动输入饮水机编号:<br>
<input type="text" class="form-control changeinput" maxlength="6" id="id" onkeyup="this.value=this.value.replace(/[^0-9-]+/,'');" style="height:auto;">
<br>
<input type="submit" value="开始使用" id="login" onclick="login()">
</div>

<div class="col-sm-12 col-xs-12" style="text-align:center;position:absolute;bottom:15px;color: rgb(195,195,195);">
<img src="/watermachine/static/pic/LOGO.png" width="12%"/><br>
浙江天风物业<br>
powered by Terabits
</div>

</body>
</html>