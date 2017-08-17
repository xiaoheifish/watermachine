<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
 <script>!function(e){function t(a){if(i[a])return i[a].exports;var n=i[a]={exports:{},id:a,loaded:!1};return e[a].call(n.exports,n,n.exports,t),n.loaded=!0,n.exports}var i={};return t.m=e,t.c=i,t.p="",t(0)}([function(e,t){"use strict";Object.defineProperty(t,"__esModule",{value:!0});var i=window;t["default"]=i.flex=function(e,t){var a=e||100,n=t||1,r=i.document,o=navigator.userAgent,d=o.match(/Android[\S\s]+AppleWebkit\/(\d{3})/i),l=o.match(/U3\/((\d+|\.){5,})/i),c=l&&parseInt(l[1].split(".").join(""),10)>=80,p=navigator.appVersion.match(/(iphone|ipad|ipod)/gi),s=i.devicePixelRatio||1;p||d&&d[1]>534||c||(s=1);var u=1/s,m=r.querySelector('meta[name="viewport"]');m||(m=r.createElement("meta"),m.setAttribute("name","viewport"),r.head.appendChild(m)),m.setAttribute("content","width=device-width,user-scalable=no,initial-scale="+u+",maximum-scale="+u+",minimum-scale="+u),r.documentElement.style.fontSize=a/2*s*n+"px"},e.exports=t["default"]}]);  flex(100, 1);</script>
 <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
 <script language="javascript" src="/watermachine/static/js/jquery-3.2.1.min.js"></script>
 <script language="javascript" src="/watermachine/static/js/confirm.js"></script>
 <meta name="viewport" content="width=device-width,height=device-height, initial-scale=1.0">
 <link rel="stylesheet" href="/watermachine/static/css/bootstrap.css">
 <link rel="stylesheet" href="/watermachine/static/css/information.css">
 <style type="text/css">
  html,body { height : 100%; width : 100%;}
 </style>
 <title>充值确认</title>
</head>

<body style="background-image: url(/watermachine/static/pic/BG1.jpg);background-size: 100% 100%;font-family: 'Microsoft YaHei' 'Cambria Math'; " onload="load();">
<script>
    var money = "${money}";
    var id = "${id}";
</script>


<!-- 支付成功等候5秒 -->
<div id="wait" style="display: none;height:100%;width:100%;position:absolute;z-index:1;font-size:1.3em;background-image: url(/watermachine/static/pic/BG1.jpg);background-size: 100% 100%;padding-top:30%;text-align: center;color: white;">
 <img src="/watermachine/static/pic/water.png" width="60%"/>
 <br>
 支付成功，<span id="waittime">10</span>秒后将自动跳转，<br>请稍候...

 <div class="col-sm-12 col-xs-12" style="text-align:center;position:absolute;bottom:15px;color: rgb(195,195,195);font-size: 0.77em">
  <img src="/watermachine/static/pic/LOGO.png" width="12%"/><br>
  浙江天风物业<br>
  powered by Terabits
 </div>
</div>

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

 <br>
 <p style="font-size:0.9em;">您本次的充值金额为</p>
 <div style="color:#e9ca33;margin-top: 3%;margin-bottom: 3%;">¥ <div style="font-size:2.5em;display:inline;">${money}</div></div>
 <div style="font-size:0.9em;text-align:center;">可取水&nbsp;<div id="time" style="display:inline;color:#e9ca33;font-size:1.3em;"></div>&nbsp;升<br></div>
</div>


<div class="col-sm-12 col-xs-12" style="text-align:center;margin-top:0.1rem;">
 <input type="submit" value="确认" class="button" id="confirm" onclick="confirm()">
</div>
</div>

<div class="col-sm-12 col-xs-12" style="text-align:center;position:absolute;bottom:15px;color: rgb(195,195,195);">
 <img src="/watermachine/static/pic/LOGO.png" width="12%"/><br>
 浙江天风物业<br>
 powered by Terabits
</div>
</body>
</html>