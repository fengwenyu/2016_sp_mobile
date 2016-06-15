$(window).scroll(topFixed);//滑动头部导航浮层

var menuTopHeight = $('.topFix').offset().top;
//显示导航浮层方法
function topFixed(){	
	var scrolls = document.body.scrollTop;
	if (scrolls > menuTopHeight) {
		$('.topFix').css({
			position: "fixed"
		});
		$('body .alContent').css('margin-top',45);    
	
	}else {
		$('body .alContent').css('margin-top',0); 
		$('.topFix').css({
			position: "static"
		});
		   
	}
};