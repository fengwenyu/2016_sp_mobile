// JavaScript Document
$(function(){
	
	//tab切换事件
//	var tabs = function(nav,content){
//		$(nav).find("li").bind("click",function(){
//			var index = $(this).index();
//			$(this).addClass("cur").siblings().removeClass("cur");
//			$(content).eq(index).show().siblings(content).hide();
			
			//刷新滑动事件
//			brandScroll.refresh();
			//brandScroll.scrollTo(0, 0, 200);
//			if($("#pageTop").length > 0){
//				brandScroll.scrollToElement("#pageTop", 0);
//			}
			//显示右边字母导航事件
//			if(index == 1){
				$(".letter_box").show();
//			}else{
//				$(".letter_box").hide();
//			}
			
//		});
//	};
//	
//	tabs(".tabs_menu", ".tabs_Cell");
	
	var topApp=0,menuHeight=0,topHeight=0;
	
	if($('.headApp').length){
	  topApp = $('.headApp').height();
	}
	if($('.topFix').length){
		menuHeight = $('.topFix').height();
		topHeight = topApp+menuHeight;
	}
	//滑动事件
	var brandScroll,
		pageW = $(window).width(),
		pageH = $(window).height();
		
	$("#pageContent").css({"height":pageH-topHeight, "width":pageW});
	
	function loaded(){
		
		brandScroll = new iScroll('pageContent',
		{
			hScrollbar:false,
			vScrollbar:true,
			hideScrollbar:true
		});
	};
	if($("#pageContent").length > 0){
		loaded();
	}
	
	if($("#pageTop").length > 0){
		brandScroll.scrollToElement("#pageTop", 0);
	}
	//监听横竖屏切换事件
	window.addEventListener("orientationchange", function() {
		
		var pageW = $(window).width(),
			pageH = $(window).height();
		
		$("#pageContent").css({"height":pageH, "width":pageW});
		
		brandScroll.refresh();
		
	}, false);
	
	
	//点击右边字母滑动事件
	$(".letter_box a").on("click", function(){
		var topElem = 'a'+$(this).attr('href');
		brandScroll.scrollToElement(topElem, 200);
		return false;
	});
	
});
