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