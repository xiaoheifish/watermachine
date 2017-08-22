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
</head>

<body onload="settime(),load()" style="font-family: 'Microsoft YaHei' 'Cambria Math';background-color: rgb(240, 240, 240);display: flex;flex-direction: column;align-items: center;padding-top: 0.15rem;">
<script>
    var lefttime = "${lefttime}";
</script>

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
<span id="water">${water}</span><span id="L">升取水量</span></div>

<div style="font-size:0.3rem;color: rgb(97, 97, 97);text-align: center;margin-top: 0.5rem;">
<img style="height: 0.8rem" src="/watermachine/static/pic/lefttime.png"><br><br>
<span id="chwarnningtext">取水阀门将在<span style="color: rgb(230, 67, 64);">取水结束</span>或<span  style="color: rgb(230, 67, 64);">40秒</span>后关闭，<br>请您尽快取水，使用过程中请注意节约用水！</span>
<span id="enwarnningtext" style="display:none;">The water-tap will be closed after<span style="color: rgb(230, 67, 64);"> you finishing taking water</span> or <span style="color: rgb(230, 67, 64);">40</span>s.<br>Please take water soon.</span>
</div>



<div class="col-sm-12 col-xs-12" id="bottombar">
天风物业<br>
powered by Terabits
</div>

</body>
</html>