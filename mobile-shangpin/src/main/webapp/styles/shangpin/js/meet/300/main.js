$(document).ready(function(e) {
	
				$("#overlay1, #overlay2").find("img").each(function(){
					
				$(this).attr("src",$(this).attr("alt"));
				})
				/*优惠劵弹层*/
				var $overlay = $('#overlay1');
				/**/function modalHidden($ele) {
					$("#overlay1, #overlay2").find("img").each(function(){						
						$(this).css({"opacity":1})
					})
					$ele.removeClass('modal-in');
					$ele.one('webkitTransitionEnd',function(){
					  $ele.css({"display": "none"});
					  
					  $overlay.removeClass('active');
					  
					});
				}
				$('.coupon-btn2').click(function(e){
					$("#overlay1, #overlay2").find("img").each(function(){						
						$(this).css({"opacity":1})
					})
					e.preventDefault();
					var $that = $(this);
					$overlay.addClass('active');
//					$(".modal-bd1>p>img").each(function(i,item){
//						$(item).attr("src",$(item).attr("alt"));
//					});
					$('.modal1').css({"display": "block"});
					$('.modal1').animate(100,function(){
					  
					  $('.modal1').addClass('modal-in');
					});
				});
				$('.btn-modal1').click(function(e){
				  modalHidden($('.modal1'));
				  e.stopPropagation();
				});
					
				$overlay.click(function(e){
					modalHidden($('.modal1'));
				});
				
				/*优惠劵弹层*/
				var $overlay2 = $('#overlay2');
				/**/function modalHidden2($ele) {
					$("#overlay1, #overlay2").find("img").each(function(){						
						$(this).css({"opacity":1})
					})
				}
				$('.coupon-btn').click(function(e){
					$("#overlay1, #overlay2").find("img").each(function(){						
						$(this).css({"opacity":1})
					})
					e.preventDefault();
					var $that = $(this);
					$overlay2.addClass('active');
//					$(".modal-bd2>p>img").each(function(i,item){
//						$(item).attr("src",$(item).attr("alt"));
//					});
					$('.modal2').css({"display": "block"});
					$('.modal2').animate(100,function(){
					  
					  $('.modal2').addClass('modal-in');
					});
				});
				$('.btn-modal2').click(function(e){
				  modalHidden2($('.modal2'));
				  e.stopPropagation();
				});
					
				$overlay2.click(function(e){
					modalHidden2($('.modal2'));
				});
});