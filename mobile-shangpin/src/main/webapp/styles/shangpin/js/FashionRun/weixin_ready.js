$(function(){
	var appId;
	var timestamp;
	var nonceStr;
	var signature;
	var url = window.location.href;
	if(url.indexOf("#") > 0){
		url = window.location.href.split("#")[0];
	}
	var path = getRootPath();
	$.getJSON(path + "/weixin/shareUrl?shareUrl=" + url,function(data){
		appId = data.appId;
		timestamp = data.timestamp;
		nonceStr = data.nonceStr;
		signature = data.signature;
		wx.config({
		    debug: false, // 开启调试模式,调用的所有api的返回值会在客户端alert出来，若要查看传入的参数，可以在pc端打开，参数信息会通过log打出，仅在pc端时才会打印。
		    appId: appId, // 必填，公众号的唯一标识
		    timestamp: timestamp, // 必填，生成签名的时间戳
		    nonceStr: nonceStr, // 必填，生成签名的随机串
		    signature: signature,// 必填，签名，见附录1
		    jsApiList: ['onMenuShareTimeline','onMenuShareAppMessage'] // 必填，需要使用的JS接口列表，所有JS接口列表见附录2
		});
		wx.ready(function(){
			var title = $("#_mainTitle").val();
			var desc = $("#_mainDesc").val();
			var link = $("#_shareUrl").val();
			var imgUrl = $("#_mainImgUrl").val();
			//分享给朋友
			wx.onMenuShareAppMessage({
			    title: title, // 分享标题
			    desc: desc, // 分享描述
			    link: link, // 分享链接
			    imgUrl: imgUrl, // 分享图标
			    type: 'link', // 分享类型,music、video或link，不填默认为link
			    dataUrl: '', // 如果type是music或video，则要提供数据链接，默认为空
			    success: function () { 
			        // 用户确认分享后执行的回调函数
			    	//alert("分享成功");
			    },
			    cancel: function () { 
			        // 用户取消分享后执行的回调函数
			    },
			    complete: function(){
			    	//接口调用完成时执行的回调函数，无论成功或失败都会执行
			    },
			    fail: function(){
			    	//接口调用失败时执行的回调函数。
			    },
			    trigger: function(){
			    	//监听Menu中的按钮点击时触发的方法，该方法仅支持Menu中的相关接口。
			    }
			});
			
			//分享到朋友圈
			wx.onMenuShareTimeline({
			    title: title, // 分享标题
			    link: link, // 分享链接
			    imgUrl: imgUrl, // 分享图标
			    success: function () { 
			        // 用户确认分享后执行的回调函数
			    	//alert("分享成功");
			    },
			    cancel: function () { 
			        // 用户取消分享后执行的回调函数
			    },
			    complete: function(){
			    	//接口调用完成时执行的回调函数，无论成功或失败都会执行
			    },
			    fail: function(){
			    	//接口调用失败时执行的回调函数。
			    },
			    trigger: function(){
			    	//监听Menu中的按钮点击时触发的方法，该方法仅支持Menu中的相关接口。
			    }
			});
		});
	});
});