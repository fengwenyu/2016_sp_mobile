$(document).ready(function(e) {
	function topFixed(){	
				var scrolls = document.body.scrollTop;
				var menuTopHeight = $('.topFix').offset().top;
				if (scrolls > menuTopHeight) {
					$('.topFix section').css({
						position: "fixed",
						top:"0",
						zIndex:"999"
					});
				}else {
					$('.topFix section').css({
						position: "relative",
						top:"0",
						zIndex:"10"
					});
					   
				}
		};
		
		$(window).scroll(function(){
			topFixed();
		});  
		//页面自动滑动加载头部Lazyload图片
		setTimeout(function(){window.scroll(0,1);},1500);
		
		//视频播放器
	var vId = "XNzk2OTMwMjY4",
					vWidth = "100%",
					vHeight = "100%",
					//src = 'http://js.tudouui.com/bin/lingtong/PortalPlayer.swf?tvcCode=-1&hd=2&vcode=' + vId,
					src = 'http://static.youku.com/v1.0.0220/v/swf/player.swf?VideoIDS=' + vId + '&winType=adshow&isAutoPlay=false',
					objStr = null,
					browser = {//判断浏览器
					versions: (function(){
					   var u = navigator.userAgent;
					   return {//移动终端浏览器版本信息
							ios: !!u.match(/\(i[^;]+;( U;)? CPU.+Mac OS X/), //ios终端
							iPhone: u.indexOf('iPhone') > -1 || u.indexOf('Mac') > -1, //是否为iPhone或者QQHD浏览器
							iPad: u.indexOf('iPad') > -1 //是否iPad
						};
					})()
				};
				
				if(browser.versions.ios || browser.versions.iPhone || browser.versions.iPad){
					objstr = "<video src=\"http://v.youku.com/player/getRealM3U8/vid/" + vId + "/type/video.m3u8\" width=\"" + vWidth + "\" height=\"" + vHeight + "\" preload=\"auto\" controls=\"controls\" autoplay=\"autoplay\"></video>";
					
				}else{
					objstr = '<object width="'+ vWidth +'" height="'+ vHeight +'" id="sp-video" name="spVideo" ><param name="src" value="'+ src +'" /><param name="quality" value="high" /><param name="wmode" value="transparent" /><embed src="http://pic12.shangpin.com/images/e.gif"  lazy="'+ src +'" type="application/x-shockwave-flash" width="'+ vWidth +'" height="'+ vHeight +'" wmode="transparent" quality="high" pluginspage="http://www.macromedia.com/go/getflashplayer"></embed></object>' + 
					"<div class='sp-video-mask' name='sp_videoMask' style='width:"+ vWidth +"px;height:"+ vHeight +"px;  position:absolute;'></div>";
				}
				
				$(".box_video").html(objstr);
				setTimeout(function(){$(".sp-video-mask").fadeOut();},1500);
				
	//视频播放器end

});	