$(function(){
	//如果不是微信页面直接返回
	var isWx=$("#_iswx").val();
	if(!isWx){
		return;
	}
	var localhref=location.href;
	/*//得到当前完整的url
	if($("#_shareUrl").val()!=undefined&& $("#_shareUrl").val()!=""){
		localhref=$("#_shareUrl").val();
	}else{
		localhref = location.href;
	}*/
	//把完整的url传送到后台进行签名
	var path = getRootPath();
	$.getJSON(path+"/weixin/shareUrl?shareUrl="+escape(localhref),function(data){
		if(data!=null){
			var configParam=new Object();
			configParam.debug=false;
			configParam.appId=data.appId;
			configParam.timestamp=data.timestamp;
			configParam.nonceStr=data.nonceStr;
			configParam.signature=data.signature;
			configParam.jsApiList=["onMenuShareTimeline","onMenuShareAppMessage"];
			wx.config(configParam);
			//微信验证成功后调用
			wx.ready(function(){
				var mainDesc=$("#_mainDesc").val();
				var mainTitle=$("#_mainTitle").val();
				var mainImgUrl=$("#_mainImgUrl").val();
				var mainURL=localhref;
				//处理本地的分享图片url链接为整个访问路径
				var localProtocol="http:";
				if(mainImgUrl.indexOf(localProtocol)==-1){
					var localHostname=location.hostname;
					var localPort = location.port;
					if(localPort!=""){
						localPort=":"+localPort;
					}
					var domain= localProtocol+"//"+localHostname+localPort;
					mainImgUrl=domain+mainImgUrl;
				}
				
				//设置分享到朋友圈信息
				var menuShareTimeline=new Object();
				menuShareTimeline.title=mainTitle;
				menuShareTimeline.link=mainURL;
				menuShareTimeline.imgUrl=mainImgUrl;
				//设置发送给朋友信息
				var menuShareAppMessage=new Object();
				menuShareAppMessage.title=mainTitle;
				menuShareAppMessage.desc=mainDesc;
				menuShareAppMessage.link=mainURL;
				menuShareAppMessage.imgUrl=mainImgUrl;
				menuShareAppMessage.type="link";
				//分享到朋友圈
				wx.onMenuShareTimeline(menuShareTimeline);
				//发送给朋友
				wx.onMenuShareAppMessage(menuShareAppMessage);
				});
		}
	});
});
$(function(){
	var isApp=$("#_isapp");
	if(isApp){
		$(".shang_share").show();
	}else{
		$(".shang_share").hide();
	}
});
