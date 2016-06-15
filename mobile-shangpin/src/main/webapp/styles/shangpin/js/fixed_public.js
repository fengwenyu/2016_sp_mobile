//在移动浏览器里
function topFixed(ele){
 if($(ele).length){//如果存在此元素时
	var scrolls = $(window).scrollTop();
	var menuHeight=0,top=0;
	var eleChild = $(ele).children().attr('id');
	
	if(ele==='.topFix'){//如果元素是头部导航
	  top=0;
	  menuHeight = $(ele).offset().top;
	}else{
		if($('.topFix').length){
		  //如果元素非头部导航
		  top= $('.topFix').height();
		  menuHeight = $(ele).offset().top-top;
		}else{
			menuHeight = $(ele).offset().top;
		}
	};
	if (scrolls > menuHeight) {
		$('#'+eleChild).css({position: "fixed",top:top,zIndex:"998"});
	}else {
		$('#'+eleChild).css({position: "relative",top:0,zIndex:"9"});
	};
 };
};