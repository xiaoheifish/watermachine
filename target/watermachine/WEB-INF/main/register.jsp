<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <script>!function(e){function t(a){if(i[a])return i[a].exports;var n=i[a]={exports:{},id:a,loaded:!1};return e[a].call(n.exports,n,n.exports,t),n.loaded=!0,n.exports}var i={};return t.m=e,t.c=i,t.p="",t(0)}([function(e,t){"use strict";Object.defineProperty(t,"__esModule",{value:!0});var i=window;t["default"]=i.flex=function(e,t){var a=e||100,n=t||1,r=i.document,o=navigator.userAgent,d=o.match(/Android[\S\s]+AppleWebkit\/(\d{3})/i),l=o.match(/U3\/((\d+|\.){5,})/i),c=l&&parseInt(l[1].split(".").join(""),10)>=80,p=navigator.appVersion.match(/(iphone|ipad|ipod)/gi),s=i.devicePixelRatio||1;p||d&&d[1]>534||c||(s=1);var u=1/s,m=r.querySelector('meta[name="viewport"]');m||(m=r.createElement("meta"),m.setAttribute("name","viewport"),r.head.appendChild(m)),m.setAttribute("content","width=device-width,user-scalable=no,initial-scale="+u+",maximum-scale="+u+",minimum-scale="+u),r.documentElement.style.fontSize=a/2*s*n+"px"},e.exports=t["default"]}]);  flex(100, 1);</script>
    <meta name="viewport" content="width=device-width,height=device-height, initial-scale=1.0">
    <script language="javascript" src="/watermachine/static/js/jquery-3.2.1.min.js"></script>
    <script language="javascript" src="/watermachine/static/js/register.js"></script>
    <script src="http://res.wx.qq.com/open/js/jweixin-1.0.0.js"></script>
    <link rel="stylesheet" href="/watermachine/static/css/bootstrap.css">
    <link rel="stylesheet" href="/watermachine/static/css/login.css">
    <style type="text/css">
        html,body { height : 100%; width : 100%;}
    </style>
    <title>欢迎使用智能饮水机！</title>
</head>

<body style="background-image: url(/watermachine/static/pic/BG1.jpg);background-size: 100% 100%;font-family: 'Microsoft YaHei' 'Cambria Math';">

<!-- 手动输入编号部分 -->
<div class="col-sm-12 col-xs-12" id="number" style="padding:0;width:100%;">
    电话号码:<br>
    <input type="text" class="form-control changeinput" maxlength="11" id="id" onkeyup="this.value=this.value.replace(/[^0-9-]+/,'');">
    <br>
    <input type="submit" value="获取验证码" id="login" onclick="sendMessage()">
</div>

<!-- 手动输入编号部分 -->
<div class="col-sm-12 col-xs-12" id="code" style="padding:0;width:100%;">
    验证码:<br>
    <input type="text" class="form-control changeinput" maxlength="6" id="watermachine" onkeyup="this.value=this.value.replace(/[^0-9-]+/,'');">
    <br>
    <input type="submit" value="注册" id="register" onclick="register()">
</div>


<div class="col-sm-12 col-xs-12" style="text-align:center;position:absolute;bottom:15px;color: rgb(195,195,195);">
    <img src="/watermachine/static/pic/LOGO.png" width="12%"/><br>
    浙江天风物业<br>
    powered by Terabits
</div>

</body>
</html>