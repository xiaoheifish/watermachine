﻿//邀请好友
function loadinvite{
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
                //扫一扫相关函数
                wx.config({
                    debug: false,
                    appId: data["appId"],
                    timestamp: data["timestamp"],
                    nonceStr: data["nonceStr"],
                    signature: data["signature"],
                    jsApiList : [ 'checkJsApi','onMenuShareTimeline', 'onMenuShareAppMessage']
                });
            }
    });
}


//发送给好友
function friends{
	wx.onMenuShareAppMessage();
}

//分享到朋友圈
function moments(){
	wx.onMenuShareTimeline();
}

//分享到朋友圈内容
wx.onMenuShareTimeline({
    title: '邀您共享高品质饮用水', // 分享标题
    link: '', // 分享链接，该链接域名或路径必须与当前页面对应的公众号JS安全域名一致
    imgUrl: '/watermachine/static/pic/sharepic.jpg', // 分享图标
    success: function () { 
        // 用户确认分享后执行的回调函数
    },
    cancel: function () { 
        // 用户取消分享后执行的回调函数
    }
});

//分享给朋友内容
wx.onMenuShareAppMessage({
    title: '邀您共享高品质饮用水', // 分享标题
    desc: '接受邀请，好友与您各得5元饮水券！点击立即体验便捷、高质、卫生饮水！', // 分享描述
    link: '', // 分享链接，该链接域名或路径必须与当前页面对应的公众号JS安全域名一致
    imgUrl: '/watermachine/static/pic/sharepic.jpg', // 分享图标
    type: 'link', // 分享类型,music、video或link，不填默认为link
    dataUrl: '', // 如果type是music或video，则要提供数据链接，默认为空
    success: function () { 
        // 用户确认分享后执行的回调函数
    },
    cancel: function () { 
        // 用户取消分享后执行的回调函数
    }
});

var openid,language;
//加载cookie，语言和用户id
function loadid(){
	openid = getCookie("openid");
	language = getCookie("language");
}