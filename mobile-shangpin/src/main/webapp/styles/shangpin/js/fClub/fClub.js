$(function(){
	//tab切换
	$(".tab_info").find("li").click(function(e){
		e.preventDefault();
		var $this = $(this);
		var $thisIndex = $this.index();
		$(this).addClass("curr").siblings().removeClass("curr");

		$(".content_info").find(".content_list").eq($thisIndex).show().siblings().hide();
	});

	$('.btn-modal').click(function(e){
	  modalHidden($('.modal'));
	  e.stopPropagation();
	});
	
	$('.overlay').click(function(e){
	 if(e.target.classList.contains('overlay')){
		modalHidden($('.modal'));
	  }
	});
	
	function modalHidden($ele) {
	  $ele.removeClass('modal-in');
	  $ele.one('webkitTransitionEnd',function(){
		$ele.css({"display": "none"});
		$('.overlay').removeClass('active');
	  });
	}
	
	$('.exclusive-list a').click(function(e){
		e.stopImmediatePropagation();
		e.preventDefault();
		var $that = $(this);
		$('.overlay').addClass('active');
		$('.modal').addClass('modal-in').show();

		
	});
	
	if($("#swiper-container2").length>0){
	var navIndex = $("#swiper-container2").find('.curr').index();
	var swiper1 = new Swiper('#swiper-container2', {
		noSwiping : true,
		initialSlide : navIndex-1,
		slidesPerView : '3',
		spaceBetween: 2,
	});
	$(".content_info").find(".content_list").eq(navIndex-1).show();
	
	$("#swiper-container2").find(".swiper-slide").click(function(e){
		e.preventDefault();
		var $this = $(this);
		var $thisIndex = $this.index();
		tCurr = $("#swiper-container2 .swiper-slide-active").index();
		
		if($thisIndex>tCurr){
			swiper1.slideNext();
		}else{
			swiper1.slidePrev();
		};
	
		$(this).addClass("curr").siblings().removeClass("curr");
		$(".content_info").find(".content_list").eq($thisIndex-1).show().siblings().hide();
	});
	}
	
	
	$(".fCoin-store").click(function(){
		show_window_tip("时尚币积分商城即将上线，敬请期待");	
		return false;
	});
	
	//弹出层公共方法
	function show_window_tip(t,h){
		touch = 0;
		$("body").attr("style","overflow:hidden");
		$("body").bind("touchmove",function(e){
			if(touch==0){ e.preventDefault();}
		})
		if(!$(".select-overlay").length){
			var showWindwHtml = '<div class="select-overlay"><div class="show_window_con">';
				showWindwHtml += '<a class="fCoin-close-btn" href="javascript:;">x</a>';
				showWindwHtml += '<h3 class="title"></h3>';
				showWindwHtml += '<div class="text_con"></div>';
				showWindwHtml += '<p class="close_window">知道了</p></div></div>';
		}
		$("body").append(showWindwHtml);
		if(!h){
			$(".select-overlay").find(".title").hide();	
		}else{
			$(".select-overlay").find(".title").show().html(h);
		}
		$(".select-overlay").find(".text_con").html(t);
		$(".select-overlay").addClass("active");
		$("body").on("touchend click",".close_window,.fCoin-close-btn",function(){
			show_window_down();
			return false;
		});
	}
	function show_window_down(){
		$("body").removeAttr("style");
		touch = 1;	
		$(".select-overlay").removeClass("active");
	}
	
});