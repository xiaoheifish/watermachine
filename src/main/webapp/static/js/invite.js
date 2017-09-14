var openid,language;
//邀请好友
function loadinvite(){
    openid = getCookie("openid");
    language = getCookie("language");
	if(language != "zh_CN"){
		$("#ch").remove();
		$("title").html("Invitation");
	}
	else{
		$("#en").remove();
	}

	//配置分享接口
    $.ajax({
            url : "/watermachine/wxconfig",
            type : 'post',
            dataType : 'json',
            contentType : "application/x-www-form-urlencoded; charset=utf-8",
            data : {
                'url' : location.href.split('#')[0]
            },
            error: function(XMLHttpRequest, textStatus, errorThrown){
                alert(XMLHttpRequest.status);
            },
            success:function(data){
                //分享相关函数
                wx.config({
                    debug: false,
                    appId: data["appId"],
                    timestamp: data["timestamp"],
                    nonceStr: data["nonceStr"],
                    signature: data["signature"],
                    jsApiList : [ 'checkJsApi','onMenuShareTimeline', 'onMenuShareAppMessage']
                });
                wx.ready(function (){
                        var shareData = {
                            title: '邀您共享高品质饮用水',
                            desc: '接受邀请，好友与您各得5元饮水券！点击立即体验便捷、高质、卫生饮水！',
                            link: 'http://www.terabits-wx.cn/watermachine/invitation/'+ phone,
                            imgUrl: 'http://www.terabits-wx.cn/watermachine/static/pic/sharepic.jpg'
                        };
                        wx.onMenuShareAppMessage(shareData);
                        wx.onMenuShareTimeline(shareData);
                });
                wx.error(function (res) {
                    alert(res.errMsg);
                });
            }
    });
}


/*//发送给好友
function friends(){
	wx.onMenuShareAppMessage();
}

//分享到朋友圈
function moments(){
	wx.onMenuShareTimeline();
}*/
/*
//分享到朋友圈内容
wx.onMenuShareTimeline({
    title: '邀您共享高品质饮用水', // 分享标题
    link: 'http://www.terabits-wx.cn/mainpage', // 分享链接，该链接域名或路径必须与当前页面对应的公众号JS安全域名一致
    imgUrl: 'http://www.terabits-wx.cn/watermachine/static/pic/sharepic.jpg', // 分享图标
    success: function () { 
        alert("分享成功！");
        // 用户确认分享后执行的回调函数
    },
    cancel: function () { 
        // 用户取消分享后执行的回调函数
        alert("分享已取消！");
    }
});

//分享给朋友内容
wx.onMenuShareAppMessage({
    title: '邀您共享高品质饮用水', // 分享标题
    desc: '接受邀请，好友与您各得5元饮水券！点击立即体验便捷、高质、卫生饮水！', // 分享描述
    link: 'http://www.terabits-wx.cn/mainpage', // 分享链接，该链接域名或路径必须与当前页面对应的公众号JS安全域名一致
    imgUrl: 'http://www.terabits-wx.cn/watermachine/static/pic/sharepic.jpg', // 分享图标
    type: 'link', // 分享类型,music、video或link，不填默认为link
    dataUrl: '', // 如果type是music或video，则要提供数据链接，默认为空
    success: function () { 
        // 用户确认分享后执行的回调函数
        alert("分享成功！");
    },
    cancel: function () { 
        // 用户取消分享后执行的回调函数
        alert("分享已取消！");
    }
});*/

/* 读取cookie */
function getCookie(cname)
{
    var name = cname + "=";
    var ca = parent.document.cookie.split(';');
    for(var i=0; i<ca.length; i++) {
        var c = ca[i].trim();
        if (c.indexOf(name)==0) return c.substring(name.length,c.length);
    }
    return null;
}