$(function() {
	
		  	/*优惠劵弹层*/
			var $overlay = $('#overlay');
			/**/function modalHidden($ele) {
				$ele.removeClass('modal-in');
				$ele.one('webkitTransitionEnd',function(){
				  $ele.css({"display": "none"});
				  $overlay.removeClass('active');
				});
			}
			$('.coupon-btn').click(function(e){
				e.preventDefault();
				var $that = $(this);
				var pop_pic = $(this).attr('data-url');
				$('.modal-bd>p>img').attr('src',pop_pic);
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