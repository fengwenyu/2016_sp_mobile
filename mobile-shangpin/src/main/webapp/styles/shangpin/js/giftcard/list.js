var menuTopHeight=0;
$(function(){
	//tab切换
	$(".tab_info").find("li").click(function(e){
		e.preventDefault();
		var $this = $(this);
		var $thisIndex = $this.index();
		$(this).addClass("curr").siblings().removeClass("curr");
		var st=$(document).scrollTop();
		$(document).scrollTop(st+1);
		$(document).scrollTop(st);
		$(".content_info").find(".content_list").eq($thisIndex).show().siblings().hide();
	});
/*	$(window).scroll(topFixed);//滑动头部导航浮层

	menuTopHeight = $('.topFix').offset().top;
	//显示导航浮层方法
*/
})
/*	function topFixed(){	
		var scrolls = document.body.scrollTop;
		console.log(scrolls);
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
	};*/
	
	
	$(window).scroll(function(){
		topFixed('.topFix');
		//topFixed('.swiper-nav-box');
		
	});
	var checkAPP=$('#checkApp').val();
	var checkWX=$('#checkWX').val();
	//显示导航浮层方法
	function topFixed(ele){	
		var scrolls = document.body.scrollTop;
		var menuHeight,top;
		var eleChild = $(ele).children().attr('id');
		if($(ele).length){
			
			  if(checkAPP||checkWX){
				  top=0;
				  menuHeight = $(ele).offset().top;
			  }else{
				  if(ele==='.topFix'){
				  	top=0;
					menuHeight = $(ele).offset().top;
				  }else{
				  	top= $('.topFix').height();
					menuHeight = $(ele).offset().top-top;
				  }
				  
			  }
			  if (scrolls > menuHeight) {
				  $('#'+eleChild).css({
					  position: "fixed",
					  top:top,
					  zIndex:"998"
				  });
			  }else {
				  $('#'+eleChild).css({
					  position: "relative",
					  top:0,
					  zIndex:"9"
				  });
		  	  }
		}
	};