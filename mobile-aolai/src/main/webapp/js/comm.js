// JavaScript Document

//隐藏地址栏
function isHome(el){
	var $ipad = navigator.userAgent.match(/(iPad).*OS\s([\d_]+)/) ? true: false,
	$iphone = !$ipad && navigator.userAgent.match(/(iPhone\sOS)\s([\d_]+)/) ? true: false;
	
	if($ipad || $iphone){
		if(el){
			document.documentElement.style.height = "5000px";
		}
		window.scrollTo(0, 0);
		document.documentElement.style.height = window.innerHeight - 0 + "px";
	}else{
		setTimeout(scrollTo, 0, 0, 1);
	}
	//setTimeout(scrollTo, 0, 0, 1);
}
//隐藏地址栏

$(function(){

//判断不同浏览器
function get_scrollTop_of_body(){
	var scrollTop;
	if(typeof window.pageYOffset != 'undefined'){
		scrollTop = window.pageYOffset;
	}else if(typeof document.compatMode != 'undefined' && document.compatMode != 'BackCompat'){
		scrollTop = document.documentElement.scrollTop;
	}else if(typeof document.body != 'undefined'){
		scrollTop = document.body.scrollTop;
	}
	return scrollTop;
}
//判断不同浏览器
	
	//返回顶部
	$(".alScrollTop").click(function(){
		var scrollTop = $(window)[0].scrollTo(0,0);
		$("html, body").animate({ scrollTop: 0 }, 120);
		return false;
	});
	//返回顶部
	
	//tab切换事件
	var tabs = function(nav,content){
		$(nav).find("li").bind("click",function(){
			var index = $(this).index();
			$(this).addClass("cur").siblings().removeClass("cur");
			$(content).eq(index).show().siblings(content).hide();
		});
	}
	
	tabs(".alProd_introMenu ul",".alProd_introCell");
	tabs(".alOrder_listMenu ul",".alOrder_listCell");
	//tab切换事件
	
	//预售日历TAB切换
	$(".alDate_menu").delegate("a","click",function(){
		var tabIndex = $(this).index();
		$(".alDate_menu a").removeClass("cur");
		$(this).addClass("cur");
		$(this).parent().siblings(".alDate_menu").find("a").eq(tabIndex).addClass("cur");
		$(".alDate_list header em").html($(this).text());
	});
	//预售日历TAB切换
	
	//底部日历返回头部
	window.onscroll = function(){
		
		if (get_scrollTop_of_body() > 0){
			$("#aldate_btm a").click(function(){
				var scrollTop = $(window)[0].scrollTo(0,0);
				$("html, body").animate({ scrollTop: 0 }, 120);
				return false;
			});
		}
		
	};
	//底部日历返回头部
	
	//60秒倒计时重新发送手机验证码
	$("#secondCode").click(function(){
		$(this).hide();
		setTimeout(function(){$("#secondCode").show();},60000);
		return false;
	});
	setTimeout(function(){$("#secondCode").show();},60000);
	//60秒倒计时重新发送手机验证码
	if($("#tabContent").length > 0){
		loaded();
	}
	
	//
	if($(".alProd_introCell img").length > 0 ){
		var imgW = $(window).width() - 20;
		$(".alProd_introCell img").css({"max-width":imgW,"width":"auto","height":"auto"});
	}
	
});

var boxScroll;
function loaded(){
	
	boxScroll = new iScroll('tabContent',
    {
		hScrollbar:false,
		vScrollbar:true
    });
}
//document.addEventListener('DOMContentLoaded', loaded, false);

