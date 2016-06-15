
$(function(){
	
	//滑动事件
	var brandScroll,
		pageW = $(window).width(),
		pageH = $(window).height();
		
	$("#pageContent").css({"height":pageH, "width":pageW});
	
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
	$(".letter_box1 a").on("click", function(){
		var topElem = 'a'+$(this).attr('href');
		brandScroll.scrollToElement(topElem, 200);
		return false;
	});
	
	
});