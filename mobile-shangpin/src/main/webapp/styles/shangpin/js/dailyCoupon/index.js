$(function(){
	//导航图轮播
	var mySwiper = new Swiper('#swiper-container1',{
		loop:true,       //循环切换
		autoplay: 2000,  //自动播放
		autoplayDisableOnInteraction : false, //swiper之后自动切换不会停止
		pagination:'.swiper-pagination', //分页
		paginationClickable: true,
		/*effect : 'coverflow',
		coverflow: {
		  rotate: 20,
		  stretch: 40,
		  depth: 300,
		  modifier: 2,
		  slideShadows : true
		}*/
	});
	
	/*领券*/
	var isSucess = true; //领取成功或失败
	$("body").on("click",".type1",function(){
		if(isSucess){
			show_window_tip();
			$(this).addClass("type3").removeClass("type1");
			return false;
		}else{
			tipLayer();
			return false;
		}
	});
	
	//领取成功弹出层公共方法
	function show_window_tip(){
		touch = 0;
		$("body").attr("style","overflow:hidden");
		$("body").bind("touchmove",function(e){
			if(touch==0){ e.preventDefault();}
		})
		$(".select-overlay").addClass("active");
		//继续领券
		$("body").on("touchend click",".continue_btn",function(){
			show_window_down();
			return false;
		});
		//立即使用
		$("body").on("touchend click",".use_btn",function(){
			show_window_down();
			return false;
		});
	}
	function show_window_down(){
		$("body").removeAttr("style");
		touch = 1;	
		$(".select-overlay").removeClass("active");
	}
	
	//领取失败提示层
	function tipLayer(){
		$('.tip-overlay').show();
		$('.tip-container').show();
		setTimeout(function(){
			$('.tip-overlay').hide();
			$('.tip-container').hide();
		},2000);		
	}
	
	//M站排序选项
	var scrollTop = 0,thisTop =0,touch =0,
		$selectFixedTop = $(".menu_nav").offset().top;
		thisTop = $selectFixedTop;/*$(".topFix").length ? $selectFixedTop -= $(".topFix").height() :*/ 
	
	$(".filte_btn").on("click",function(e){
		e.stopPropagation();
		var that = this;
		/*if(scrollTop < thisTop){
			$("body").scrollTop(thisTop);	
		}*/		
		show_box_window(".filte_btn","cur","#filte_list");						
		return false;	
	});
	function show_box_window(ele,ele_class,ele_list){
		if(!$(ele).hasClass(ele_class)){
			$(ele).addClass(ele_class);
			$(ele_list).stop(true,true).slideDown(300,function(){
				$(".Mask").fadeIn();
			});						
			touch = 0;
			$("body").bind("touchmove",function(e){
				if(touch==0){ e.preventDefault();}
			})
		}else{
			$(ele).removeClass(ele_class);
			$(ele_list).stop(true,true).slideUp(300,function(){
				$(".Mask").hide();
			});	
			touch = 1;
		}
	}
	
	$(".filte_menu").on("click","li",function(){
		$(this).addClass("cur").siblings().removeClass("cur");
		isHasclass(".filte_btn","click","cur");
		return false;
	})
	
	//显示筛选项
	$(".price_btn").click(function(e){
		e.stopPropagation();
		$(this).hasClass("cur") ? $(this).removeClass("cur") : $(this).addClass("cur");
		//关闭排序层
		isHasclass(".filte_btn","click","cur");		
	});
	$(".Mask").click(function(){	
		isHasclass(".filte_btn","click","cur");
		return false;
	});	

	function isHasclass(ele,t,c){
		if($(ele).hasClass(c)){
			$(ele).trigger(t);
		}
	}
		
	$(window).scroll(function(){
		scrollTop = $(this).scrollTop();
		if(scrollTop > thisTop){
			$(".fixed_box").addClass("fixed_active");
		}else{
			$(".fixed_box").removeClass("fixed_active");	
		}	
	});
	
});