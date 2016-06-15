$(document).ready(function(e) {
	function topFixed(){	
				var scrolls = document.body.scrollTop;
				var menuTopHeight;
				if($('.topFix').length>0){
					menuTopHeight = $('.topFix').offset().top;
				}
				if (scrolls > menuTopHeight) {
					$('.topFix section').css({
						position: "fixed",
						top:"0",
						zIndex:"999"
					});
				}else {
					$('.topFix section').css({
						position: "relative",
						top:"0",
						zIndex:"10"
					});
					   
				}
		};
		
		$(window).scroll(function(){
			topFixed();
		});  
		//页面自动滑动加载头部Lazyload图片
		setTimeout(function(){window.scroll(0,1);},1500);
		/*优惠劵弹层*/
		var $overlay = $('#overlay');
		function modalHidden($ele) {
			$("html,body").removeClass("onBody");			
			$ele.removeClass('modal-in');
			$ele.one('webkitTransitionEnd',function(){
			  $ele.css({"display": "none"});
			  $overlay.removeClass('active');
			});
		}
		$('.coupon,#hideAppLayer').click(function(e){
			$("html,body").addClass("onBody");
			e.preventDefault();			
			var $that = $(this);
			$overlay.addClass('active');			
			$('.modal').css({"display": "block"});
			$('.modal').animate(100,function(){			  
			  $('.modal').addClass('modal-in');
			});
		});		
		$('.close').click(function(e){
		  modalHidden($('.modal'));
		  e.stopPropagation();
		});			
		$overlay.click(function(e){
		  if(e.target.classList.contains('overlay')){
			modalHidden($('.modal'));
		  }
		});

});	