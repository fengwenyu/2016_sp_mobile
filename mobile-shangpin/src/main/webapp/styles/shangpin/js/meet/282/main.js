$(document).ready(function(e) {
	
	$(window).scroll(topFixed);//滑动头部导航浮层
	
	//显示导航浮层方法
	function topFixed(){
		var menuTopHeight;
		
		var isApp=$("#_isapp").val();
		var isWx=$("#_iswx").val();
		if(isApp||isWx){
			
			if($('.nav_fixed').length>0){
				menuTopHeight = $('.nav_fixed').offset().top;
			}	
			var scrolls = document.body.scrollTop;
			if (scrolls > menuTopHeight) {
				
				$('#nav_fixed').css({
					position: "fixed",
					top:"0"
				});
			}else {
				
				$('#nav_fixed').css({
					position: "relative",
					top:"0"
				});
				   
			}
			
		}else{
			if($('.topFix').length>0){
				menuTopHeight = $('.topFix').offset().top;
			}	
			var scrolls = document.body.scrollTop;
			if (scrolls > menuTopHeight) {
				$('.topFix section').css({
					position: "fixed",
					top:"0",
				});
				$('#nav_fixed').css({
					position: "fixed",
					top:"44px"
				});
			}else {
				$('.topFix section').css({
					position: "relative",
					top:"0",
					overflow:'hidden'
				});
				$('#nav_fixed').css({
					position: "relative",
					top:"0"
				});
				   
			}
		}
	};
	
	$("#nav_fixed li").click(function(){
		$(this).find("a").addClass('cur').parent().siblings().find("a").removeClass('cur');
	});

	/*优惠劵弹层*/
	var $overlay = $('#overlay');
	function modalHidden($ele) {
		$ele.removeClass('modal-in');
		$ele.one('webkitTransitionEnd',function(){
		  $ele.css({"display": "none"});
		  $overlay.removeClass('active');
		});
	}
	$('.coupon-btn li').click(function(e){
//		e.preventDefault();
		var $that = $(this);
		$overlay.addClass('active');
		$('.modal').css({"display": "block"});
		$('.modal').animate(100,function(){
		  
		  $('.modal').addClass('modal-in');
		});
	});
	$('.btn-modal').click(function(e){
	  modalHidden($('.modal'));
	  e.stopPropagation();
	});
		
	$overlay.click(function(e){
	  if(e.target.classList.contains('overlay')){
		modalHidden($('.modal'));
	  }
	});
	
	
});	