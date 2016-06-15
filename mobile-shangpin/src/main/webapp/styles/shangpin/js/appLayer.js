$(function() {
	/* var v= browser.versions.android; */
	//下拉弹出层
	var appObj = $("#appLayer");
	if(appObj.length>0){
		var referrerUrl=document.referrer;
		var localUrl = window.location.href.toString();
		var localHostname=window.location.hostname;
		var localPort = window.location.port;
		var localProtocol=window.location.protocol;
		var domain= localProtocol+"//"+localHostname;
		if(localPort!="" && localPort!="80"){
			domain=domain+":"+localPort;
		}
		if(referrerUrl !='' && referrerUrl.indexOf(domain)==0 ){
			//来自于本网站url
		}else{
			//来自其他网站url
			appObj.removeClass("slideUpApp").addClass("slideDownApp");
		}
		
		if(appObj.hasClass("slideDownApp")){
			setTimeout(function(){$(".alContent").hide();},1000);
		}
		
		$("#hideAppLayer").click(function(){
			appObj.removeClass("slideDownApp").addClass("slideUpApp");
			$(".alContent").show();
			return false;
		});
	}
	
	//判断移动设备版本
	var browser={
		versions:function(){ 
			   var u = navigator.userAgent, app = navigator.appVersion; 
			   return {//移动终端浏览器版本信息 
					trident: u.indexOf('Trident') > -1, //IE内核
					presto: u.indexOf('Presto') > -1, //opera内核
					webKit: u.indexOf('AppleWebKit') > -1, //苹果、谷歌内核
					gecko: u.indexOf('Gecko') > -1 && u.indexOf('KHTML') == -1, //火狐内核
					mobile: !!u.match(/AppleWebKit.*Mobile.*/)||!!u.match(/AppleWebKit/), //是否为移动终端
					ios: !!u.match(/\(i[^;]+;( U;)? CPU.+Mac OS X/), //ios终端
					android: u.indexOf('Android') > -1 || u.indexOf('Linux') > -1, //android终端或者uc浏览器
					iPhone: u.indexOf('iPhone') > -1 || u.indexOf('Mac') > -1, //是否为iPhone或者QQHD浏览器
					iPad: u.indexOf('iPad') > -1, //是否iPad
					webApp: u.indexOf('Safari') == -1, //是否web应该程序，没有头部与底部
					isIOS7: u.indexOf('iPhone OS 7') > -1  //最新的IOS7版本
				};
			 }(),
			 language:(navigator.browserLanguage || navigator.language).toLowerCase()
	};
	if(browser.versions.android==true){
		$("#vers").click(function(){
			window.location.href=getRootPath()+"/download.action?p=102&ch=4&fileName=shangpin_4.apk";
			return false;
		});
												
	}else if(browser.versions.iPhone==true||browser.versions.iPad==true||browser.versions.ios==true){
		$("#vers").click(function(){
			window.location.href="https://itunes.apple.com/cn/app/id598127498?mt=8";
			return false;
		});
		
	}
	
});