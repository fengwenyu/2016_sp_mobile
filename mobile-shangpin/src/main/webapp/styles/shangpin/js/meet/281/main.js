$(document).ready(function(e) {
	
	$(window).scroll(topFixed);//滑动头部导航浮层
	
	//显示导航浮层方法
	function topFixed(){
		var menuTopHeight;
		if($('.topFix').length>0){
			menuTopHeight = $('.topFix').offset().top;
		}	
		var scrolls = document.body.scrollTop;
		if (scrolls > menuTopHeight) {
			$('.topFix section').css({
				position: "fixed",
				top:"0",
			});
			$('#swiper-container').css({
				position: "fixed",
				top:"44px"
			});
		}else {
			$('.topFix section').css({
				position: "relative",
				top:"0",
				overflow:'hidden'
			});
			$('#swiper-container').css({
				position: "relative",
				top:"0"
			});
			   
		}
	};
	
	var navIndex=$('.swiper-slide a').index($('.cur')); ;
    
	console.log(navIndex);
	//判断左右滑动
	var curr = 0, tCurr = 0;
	$("#swiper-container .swiper-slide").click(function(){
		$(this).find("a").addClass('cur').parent().siblings().find("a").removeClass('cur');
		$(this).find("img").attr("src",getRootPath()+"/styles/shangpin/images/20150723manDay/icon_arrow_cur.png").parent().parent().parent().siblings().find("img").attr("src",getRootPath()+"/styles/shangpin/images/20150723manDay/icon_arrow.png")
		
		curr = $(this).index();
		navIndex = curr-1;
		tCurr = $("#swiper-container .swiper-slide-active").index();
		
		if(curr>tCurr ){
			swiper.slideNext();
		}else{
			swiper.slidePrev();
		}
	});

	var swiper = new Swiper('#swiper-container', {
		
		initialSlide :navIndex,
		slidesPerView: 'auto',
		//spaceBetween: 5,
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