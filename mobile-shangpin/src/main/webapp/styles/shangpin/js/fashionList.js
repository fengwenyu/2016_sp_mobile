$(window).scroll(function(){
	topFixed('.topFix');
	topFixed('.list-menu');
	
});
/*//滑动显示导航浮层
var menuNavHeight = 0,menuTopHeight=0;
if($('.menu-nav').length){
  menuNavHeight = $('.menu-nav').offset().top;
}
$(window).scroll(function() {
	var scrolls = $(window).scrollTop();
	var topSize=45;
	var isWx=$("#_iswx").val();
	var isApp=$("#_isapp").val();
	if(isWx||isApp){
		topSize=0;
	}
	if (scrolls >= menuNavHeight-45) {
		$('.menu-nav').css({
			position: "fixed",
			top: topSize,
			zIndex: 99
		});
		$('.content_info').css('margin-top',47);    
	
	}else {
		$('.menu-nav').css({
			position: "static",
		});
		$('.content_info').css('margin-top',13);    
	}
});
*/


//显示菜单浮层方法
	/*$(window).scroll(topFixed);
    if($('.topFix').length){
		menuTopHeight = $('.topFix').offset().top;
	}
	function topFixed(){	
		var scrolls = document.body.scrollTop;
		if (scrolls > menuTopHeight) {
			$('.topFix').css({
				position: "fixed"
			});
			$('body .alContent').css('margin-top',55);    
		
		}else {
			$('body .alContent').css('margin-top',0); 
			$('.topFix').css({
				position: "static"
			});
			   
		}
	};*/

 
 


//$(function(){
//   //tab切换
//	$(".tab_info").find("li").click(function(e){
//		e.preventDefault();
//		var $this = $(this);
//		var $thisIndex = $this.index();
//		$(this).addClass("curr").siblings().removeClass("curr");
//
//		$(".content_info").find(".content_list").eq($thisIndex).show().siblings().hide();
//	});
//   
//});

 
 


