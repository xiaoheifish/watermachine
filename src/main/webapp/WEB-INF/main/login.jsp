<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>

    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <script language="javascript" src="/watermachine/static/js/jquery-3.2.1.min.js"></script>
    <script language="javascript" src="/watermachine/static/js/flexible.js"></script>
    <script language="javascript" src="/watermachine/static/js/login.js"></script>
    <script language="javascript" src="/watermachine/static/js/islider.js"></script>
    <script src="http://res.wx.qq.com/open/js/jweixin-1.0.0.js"></script>
    <link rel="stylesheet" href="/watermachine/static/css/bootstrap.css">
    <link rel="stylesheet" href="/watermachine/static/css/login.css">

    <title>欢迎使用智能饮水机</title>
    <script>
        var openid = "${openId}";
        var language = "${language}";
        var avatar = "${headimgurl}";
        var nickname = "${nickname}";
    </script>
</head>

<body onload="load();"
      style="font-family: 'Microsoft YaHei' 'Cambria Math';background-color: rgb(240, 240, 240);display: flex;flex-direction: column;align-items: center;">


<!-- 广告图片 -->
<div id="toppicture" class="iSlider-effect"></div>

<script>

    /* 读取cookie */
    function getCookie(cname) {
        var name = cname + "=";
        var ca = parent.document.cookie.split(';');
        for (var i = 0; i < ca.length; i++) {
            var c = ca[i].trim();
            if (c.indexOf(name) == 0) return c.substring(name.length, c.length);
        }
        return null;
    }

    if (language == "zh_CN") {
        var picList = [
            {
                content: "/watermachine/static/pic/toppicture.png",
            },
            {
                content: "/watermachine/static/pic/toppicture1.png",
            },
            {
                content: "/watermachine/static/pic/toppicture2.png",
            },
            {
                content: "/watermachine/static/pic/guidlines.png",
            },
        ];
    }
    else {
        var picList = [
            {
                content: "/watermachine/static/pic/entoppicture.png",
            },
            {
                content: "/watermachine/static/pic/entoppicture1.png",
            },
            {
                content: "/watermachine/static/pic/entoppicture2.png",
            },
            {
                content: "/watermachine/static/pic/enguidlines.png",
            },
        ];
    }


    //all animation effect
    var islider1 = new iSlider({
        data: picList,
        dom: document.getElementById("toppicture"),
        duration: 2000,
        animateType: 'default',
        isAutoplay: true,
        isLooping: true,
        // isVertical: true, 是否垂直滚动
    });
    islider1.bindMouse();
</script>


<!-- 二维码扫描按钮 -->

<button id="RQ" onclick="RQ();" class="RQ">扫码取水</button>

<!-- 手动输入编号提示部分 -->
<p id="numbertext" style="margin-top: 0.7rem; font-size: 0.35rem; color: rgb(136, 136, 136);">输入编号取水:</p>
<button id="opennumber" onclick="opennumber()"></button>

<div class="col-sm-12 col-xs-12" id="bottombar">
    天风物业<br>
    powered by Terabits
</div>

<!-- 输入编号弹出部分 -->
<div id="numberbox" style="display: none;">
    <div id="number">
        <p id="idinputtext">请输入设备编号</p>
        <input type="tel" maxlength="6" id="id"><br>

        <input type="submit" value="取消" class="idbutton" id="cancle" onclick="closenumber()"
               style="left: 0;border-right: 0.5px solid rgb(136, 136, 136);color: rgb(136, 136, 136);z-index: -1;">
        <input type="submit" value="确认" class="idbutton" id="login" onclick="login()"
               style="border-left: 0.5px solid rgb(136, 136, 136);">
    </div>
</div>

<div id="closemenudiv" onclick="closemenu()"></div>
<!-- 我的账户按钮 -->
<input type="button" id="menubutton" value="" onclick="openmenu();">

<!-- 菜单-我的账户 -->
<div id="menu">
    <div style="margin-bottom: 1rem;width: 100%; text-align: right;margin-top: -0.5rem;">
        <button onclick="closemenu();" id="closemenu" class="closemenu"><img style="width: 0.4rem"
                                                                             src="/watermachine/static/pic/back.png"></button>
    </div>

    <img src="/watermachine/static/pic/avatar.png" style="width: 1.4rem;" id="avatar"/><br>
    &nbsp;<span style="font-size: 0.6rem; color: black;" id="nickname"></span>

    <button id="record" onclick="openrecordbox();" class="menu_1"><img style="width: 0.7rem"
                                                                       src="/watermachine/static/pic/record.png"/>&nbsp;&nbsp;取水记录
    <img style="width: 0.35rem;"></button>

    <button id="wallet" onclick="openwalletbox();" class="menu_1"><img style="width: 0.7rem"
                                                                       src="/watermachine/static/pic/wallet.png"/>&nbsp;&nbsp;我的钱包
    <img style="width: 0.35rem;"></button>

<!--    <button id="invite" onclick="openinvitationbox();" class="menu_1"><img style="width: 0.7rem"
                                                                           src="/watermachine/static/pic/invite.png"/>&nbsp;&nbsp;邀请好友
    <img style="width: 0.35rem;"></button>-->

    <button id="service" onclick="openservicebox();" class="menu_1"><img style="width: 0.7rem"
                                                                         src="/watermachine/static/pic/service.png"/>&nbsp;&nbsp;意见反馈
    <img src="/static/pic/award.png" style="width: 0.35rem;vertical-align: top;"></button>

<a href="/watermachine/rules" id="enrule" style="margin-top: 0.5rem;font-size: 0.3rem;">详细活动规则</a>

</div>

</body>
</html>