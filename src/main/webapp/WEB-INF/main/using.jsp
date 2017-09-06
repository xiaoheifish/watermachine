<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>

<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<script language="javascript" src="/watermachine/static/js/jquery-3.2.1.min.js"></script>
<script language="javascript" src="/watermachine/static/js/flexible.js"></script>
<script language="javascript" src="/watermachine/static/js/using.js"></script>
<link rel="stylesheet" href="/watermachine/static/css/bootstrap.css">
<link rel="stylesheet" href="/watermachine/static/css/information.css">
<title>使用信息</title>
<script>
    var lefttime = "${lefttime}";
	var water = "${water}";
	var status = "${status}";
</script>
</head>

<body onload="load()" style="font-family: 'Microsoft YaHei' 'Cambria Math';background-color: rgb(240, 240, 240);display: flex;flex-direction: column;align-items: center;padding-top: 0.15rem;">


<!-- 信息显示部分 -->
<div class = "infor">
<img style="height:0.6rem;width:0.522rem;" src="/watermachine/static/pic/location.png">&nbsp;&nbsp; 
<span id="location">${location}</span>
</div>

<div class="infor">
<img style="height:0.6rem;width:0.522rem;" src="/watermachine/static/pic/id.png">&nbsp;&nbsp;
<span id="id">${id}</span>
</div>

<div class="infor">
<img style="height:0.6rem;width:0.522rem;" src="/watermachine/static/pic/status.png">&nbsp;&nbsp;
<span id="status">${status}</span></div>

<div class="infor">
<img style="height:0.6rem;width:0.522rem;" src="/watermachine/static/pic/usingwater.png">&nbsp;&nbsp;
<span id="water">${water}</span><span id="L"> </span></div>

<div style="font-size:0.3rem;color: rgb(97, 97, 97);text-align: center;margin-top: 0.5rem;">
<img style="height: 0.8rem" src="/watermachine/static/pic/lefttime.png"><br><br>
<span id="chwarnningtext" style="display:none;">取水阀门将在<span style="color: rgb(230, 67, 64);">取水结束</span>或<span  style="color: rgb(230, 67, 64);">1分钟</span>后关闭，<br>请您尽快取水，使用过程中请注意节约用水！</span>
<span id="enwarnningtext" style="display:none;">The water-tap will be closed after<span style="color: rgb(230, 67, 64);"> you finishing taking water</span> or <span style="color: rgb(230, 67, 64);">60</span>s.<br>Please take water soon.</span>
</div>

<br>
<br>
<img style="width: 1rem" src="/watermachine/static/pic/reward.png">

<div id="savebox" style="margin-top: 0rem;font-size: 0.35rem;text-align: center;color: rgb(97, 97, 97); border: none; padding: 5%; width: 80%;display: none;font-style: italic;background-image: url(/watermachine/static/pic/lineback.png); background-size: 100% 100%;">
	向今日饮水目标更进一步！<br>
	此次饮水您又节省了 <span id="save" style="color: rgb(230, 67, 64);">1/4</span> 个饮料瓶！
</div>

<div id="ensavebox" style="margin-top: 0rem;font-size: 0.35rem;text-align: center;color: rgb(97, 97, 97); border: none; padding: 5%; width: 80%;display: none; font-style: italic;background-image: url(/watermachine/static/pic/lineback.png); background-size: 100% 100%;">
	Go further on your drinking goal.<br>
	You saved <span id="save" style="color: rgb(230, 67, 64);">1/4</span> plastic bottle(s) this time！
</div>

<div class="col-sm-12 col-xs-12" id="bottombar">
天风物业<br>
Powered by Terabits
</div>

</body>
</html>