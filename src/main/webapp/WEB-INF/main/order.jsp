<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>

<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link rel="stylesheet" href="/watermachine/static/css/bootstrap.css">
<link rel="stylesheet" href="/watermachine/static/css/information.css">
<script language="javascript" src="/watermachine/static/js/jquery-3.2.1.min.js"></script>
<script language="javascript" src="/watermachine/static/js/flexible.js"></script>
<script language="javascript" src="/watermachine/static/js/information.js"></script>
<script>
	var id = "${id}";
	var status = "${status}";
</script>
<title>欢迎使用智能直饮水机</title>
</head>

<body onload="orderload()" style="font-family: 'Microsoft YaHei' 'Cambria Math';background-color: rgb(240, 240, 240);display: flex;flex-direction: column;align-items: center;padding-top: 0.15rem;">

<!-- 信息显示部分 -->
<div class = "infor">
<img style="height:0.6rem;width:0.522rem;" src="/watermachine/static/pic/location.png">&nbsp;&nbsp; 
<span id="location" style="text-overflow: ellipsis;">${location}</span>
</div>

<div class="infor">
<img style="height:0.6rem;width:0.522rem;" src="/watermachine/static/pic/id.png">&nbsp;&nbsp;
<span id="id">${id}</span>
</div>

<div class="infor">
<img style="height:0.6rem;width:0.522rem;" src="/watermachine/static/pic/status.png">&nbsp;&nbsp;
<span id="status">${status}</span>
</div>

<div class="col-sm-12 col-xs-12" id="bottombar">
天风物业<br>
powered by Terabits
</div>
</body>
</html>