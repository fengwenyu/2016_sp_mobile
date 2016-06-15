$(function() {

	// 判断移动设备版本
	var browser = {
		versions : function() {
			var u = navigator.userAgent, app = navigator.appVersion;
			return {// 移动终端浏览器版本信息
				trident : u.indexOf('Trident') > -1, // IE内核
				presto : u.indexOf('Presto') > -1, // opera内核
				webKit : u.indexOf('AppleWebKit') > -1, // 苹果、谷歌内核
				gecko : u.indexOf('Gecko') > -1 && u.indexOf('KHTML') == -1, // 火狐内核
				mobile : !!u.match(/AppleWebKit.*Mobile.*/)
						|| !!u.match(/AppleWebKit/), // 是否为移动终端
				ios : !!u.match(/\(i[^;]+;( U;)? CPU.+Mac OS X/), // ios终端
				android : u.indexOf('Android') > -1 || u.indexOf('Linux') > -1, // android终端或者uc浏览器
				iPhone : u.indexOf('iPhone') > -1 || u.indexOf('Mac') > -1, // 是否为iPhone或者QQHD浏览器
				iPad : u.indexOf('iPad') > -1, // 是否iPad
				webApp : u.indexOf('Safari') == -1, // 是否web应该程序，没有头部与底部
				isIOS7 : u.indexOf('iPhone OS 7') > -1
			// 最新的IOS7版本
			};
		}(),
		language : (navigator.browserLanguage || navigator.language)
				.toLowerCase()
	};
	if (browser.versions.android == true) {
		if (navigator.userAgent.toLowerCase().indexOf("micromessenger") > -1) {
			window.location.href = "http://app.qq.com/#id=detail&appid=100884746";
		} else {
			window.location.href = getRootPath()+ "/download.action?p=102&ch=4&fileName=shangpin_4.apk";
		}

	} else if (browser.versions.iPhone == true || browser.versions.iPad == true
			|| browser.versions.ios == true) {
		if (navigator.userAgent.toLowerCase().indexOf("micromessenger") > -1) {
			window.location.href = "http://a.app.qq.com/o/simple.jsp?pkgname=com.shangpin";
		} else {
			window.location.href = "https://itunes.apple.com/cn/app/id598127498?mt=8";

		}
		
	}else{
		window.location.href = getRootPath()+ "/download.action?p=102&ch=4&fileName=shangpin_4.apk";
	}
	$(function(){
		$("#aolaiDownload").click(function(){
		$("#popLayer").animate({
			"top":"0"
		},500);
		return false;
	});					
	$("#popLayer").click(function(){
		$("#popLayer").animate({
			"top":"-568px"
		},500);
		return false;
	});
});
});