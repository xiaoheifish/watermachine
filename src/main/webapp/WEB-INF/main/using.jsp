<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
 <script>!function(e){function t(a){if(i[a])return i[a].exports;var n=i[a]={exports:{},id:a,loaded:!1};return e[a].call(n.exports,n,n.exports,t),n.loaded=!0,n.exports}var i={};return t.m=e,t.c=i,t.p="",t(0)}([function(e,t){"use strict";Object.defineProperty(t,"__esModule",{value:!0});var i=window;t["default"]=i.flex=function(e,t){var a=e||100,n=t||1,r=i.document,o=navigator.userAgent,d=o.match(/Android[\S\s]+AppleWebkit\/(\d{3})/i),l=o.match(/U3\/((\d+|\.){5,})/i),c=l&&parseInt(l[1].split(".").join(""),10)>=80,p=navigator.appVersion.match(/(iphone|ipad|ipod)/gi),s=i.devicePixelRatio||1;p||d&&d[1]>534||c||(s=1);var u=1/s,m=r.querySelector('meta[name="viewport"]');m||(m=r.createElement("meta"),m.setAttribute("name","viewport"),r.head.appendChild(m)),m.setAttribute("content","width=device-width,user-scalable=no,initial-scale="+u+",maximum-scale="+u+",minimum-scale="+u),r.documentElement.style.fontSize=a/2*s*n+"px"},e.exports=t["default"]}]);  flex(100, 1);</script>
 <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
 <script language="javascript" src="/watermachine/static/js/jquery-3.2.1.min.js"></script>
 <script language="javascript" src="/watermachine/static/js/using.js"></script>
 <meta name="viewport" content="width=device-width,height=device-height, initial-scale=1.0">
 <link rel="stylesheet" href="/watermachine/static/css/bootstrap.css">
 <link rel="stylesheet" href="/watermachine/static/css/information.css">
 <style type="text/css">
  html,body { height : 100%; width : 100%;}
 </style>
 <title>使用信息</title>
</head>

<body style="background-image: url(/watermachine/static/pic/BG1.jpg);background-size: 100% 100%;font-family: 'Microsoft YaHei' 'Cambria Math';" onload="settime()">
<script>
    var lefttime = "${lefttime}";
    var usingtime = "${usingtime}";
</script>
<!-- 信息显示部分 -->
<div class="col-sm-12 col-xs-12" id="informationbox2" style="text-align:center;padding:0;">

 <div class = "infor">
  &nbsp;&nbsp;<img height="30px" width="26px" src="/watermachine/static/pic/location.png">&nbsp;&nbsp;&nbsp;
  <span id="location">${location}</span>
 </div>

 <div class="infor">
  &nbsp;&nbsp;<img height="30px" width="26px" src="/watermachine/static/pic/id.png">&nbsp;&nbsp;&nbsp;
  <span id="id">${id}</span>
 </div>

 <div class="infor">
  &nbsp;&nbsp;<img height="30px" width="26px" src="/watermachine/static/pic/status.png">&nbsp;&nbsp;&nbsp;
  <span id="status">${status}</span></div>

 <div class="infor">
  &nbsp;&nbsp;<img height="30px" width="26px" src="/watermachine/static/pic/usingwater.png">&nbsp;&nbsp;&nbsp;
  <span id="water">${water}L</span></div>

 <div class="infor">
  &nbsp;&nbsp;<img height="26px" width="26px" src="/watermachine/static/pic/usingtime.png">&nbsp;&nbsp;&nbsp;
  <span id="lefttime">${lefttime}</span></div>
</div>

<div class="col-sm-12 col-xs-12" style="padding:0;font-size:0.9em;color: rgb(230,230,230);text-align: center;margin-top: 5%;g">
 <img height="30px" src="/watermachine/static/pic/lefttime.png"><br>取水阀门将在<span style="color: rgb(246, 153, 153);">取水结束</span>或<span style="color: rgb(246, 153, 153);">倒计时结束</span>后关闭，<br>请您尽快取水，使用过程中请注意节约用水！
</div>



<div class="col-sm-12 col-xs-12" style="text-align:center;position:absolute;bottom:15px;color: rgb(195,195,195);">
 <img src="/watermachine/static/pic/LOGO.png" width="12%"/><br>
 浙江天风物业<br>
 powered by Terabits
</div>

</body>
</html>