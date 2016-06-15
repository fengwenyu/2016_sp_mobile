$(function(){
var $overlay = $('#overlay');

//判断设备
var UA = window.navigator.userAgent;
var CLICK = 'click';
if(/ipad|iphone|android/.test(UA)){
	CLICK = 'tap';
}

/*$('.container').delegate('.coupon-list li,.receive-btn',CLICK,function(e){
	e.stopImmediatePropagation();
	var $that = $(this);
	var code=$(this).attr("id");
	$overlay.addClass('active');
	$('.modal').css("display","block");
	$('.modal').animate({"display":"block"},100,function(){
	  $('.modal').addClass('modal-in');
	});		
}); */

/**
 * 领取优惠券逻辑
 */
$('.coupon-list').delegate('li',CLICK,function(e){
	var $that = $(this);
	var code = $(this).attr("id");
	var path = getRootPath();
	$.ajax({
				url : path + "/coupon/ajax/getCoupon",
				data : {
					"couponCode" : code
				},
				dataType : "json",
				success : function(data) {
					if ("0" == data.code) {
						$(".modal-hd").html("成功领取优惠券")
						$(".modal-bd")
								.html(
										"<p>领取成功<br/>您可以在<strong>“我的”</strong>的页面<strong>“优惠券”</strong>中查看</p>")
						$overlay.addClass('active');
						$('.modal').css("display","block");
						$('.modal').animate({"display":"block"},100,function() {$('.modal').addClass('modal-in');});
					} else if ("2" == data.code) {
						var isApp=$("#_isapp").val();
	                	if(isApp){
	                		window.location.href=path+"/coupon/app/getCoupon?back=/subject/product/list_"+$("#topicId").val();
	                	}else{
	                		window.location.href=path+"/login?back=/subject/product/list_"+$("#topicId").val();
	                		 
	                	}
						return;
					} else {
						$(".modal-hd").html("领取失败")
						$(".modal-bd").html(data.msg);
						$overlay.addClass('active');
						$('.modal').css("display","block");
						$('.modal').animate({"display" : "block"},100,function() {
							$('.modal').addClass('modal-in');
						});
					}

				}
			});
});

$('.container').delegate('.btn-modal',CLICK,function(e){
  modalHidden($('.modal'));
  e.stopPropagation();
});
$('.container').delegate($overlay,CLICK,function(e){
 if(e.target.classList.contains('overlay')){
	modalHidden($('.modal'));
  }
});

function modalHidden($ele) {
	$ele.removeClass('modal-in');
	$ele.one('webkitTransitionEnd',function(){
		$ele.css({"display": "none"});
		$overlay.removeClass('active');
	});
}


		var navType = 4, //超过四个左右滑动
			tieleBannerLen=0,navHtml="",swiperList="",navList="";
			tieleBannerLen = $(".title_banner").length;;
		$(".title_banner").each(function(i,t){ //添加锚点位置
			$(t).prepend("<a hrer='javascript:;' name='anchor' class='anchor' id='anchor" + i + "'></a>");	
		});
		if($(".topFix").length > 0){
			$(".title_banner a[class='anchor'][id]").css({
				"top": -88	
			});	
		}else{
			$(".title_banner a[class='anchor'][id]").css({
				"top": -44	
			});	
		}
		
		if(tieleBannerLen > navType){
			$(".title_banner").each(function(i,t){
				var text = $(t).attr("data-t");
				swiperList += '<div class="swiper-slide"><a href="#anchor'+ i + '">'+ text +'</a></div>';
				navList += '<li><a href="#anchor3'+ i + '">'+ text +'</a></li>';
			});
			navHtml = '<div class="swiper-nav-box" id="swiper-nav-box"><div class="swiper-container" id="swiper-container"><div class="swiper-wrapper">';
			navHtml += swiperList;
			navHtml += '</div></div><div class="mask-line js-fold-btn"></div><div class="fold-nav-title">切换楼层</div><ul class="fold-nav-box clr">';
			navHtml += navList;
			navHtml += '</ul></div><div class="mask"></div>'	
		}else{
			$(".title_banner").each(function(i,t){
				var text = $(t).attr("data-t");
				navList += '<li>'+ text +'</li>';
			});	
			navHtml += '<ul class="page_nav clr" id="page_nav">';
			navHtml += navList;
			navHtml += '</ul>';			
		}
		$(".menu-nav").html(navHtml);
		
		var swiperFn, isSwiper = true, curr = 0, tCurr = 0,flag=0;	

		//判断左右滑动
		function swiperFn(){
			swiper = new Swiper('#swiper-container', {
				slidesPerView: 'auto'
			});
			/*主会场底部滚动条*/			
		
			//判断左右滑动
			$("#swiper-container .swiper-slide").click(function(){
				  //锚点点击滑动页面事件
				  var that = $(this).find("a"),
					  val = that.attr("href").substr(1);
					  pageTop = $("#"+val).offset().top;
				  $("html,body").animate({"scrollTop":pageTop},300);					
				  return false;
			});
		}
		
	  if(tieleBannerLen > navType){
		  swiperFn();
		  var fixedTop = $(".swiper-nav-box").offset().top;
		  if($(".topFix").length > 0){
			  fixedTop -= 44;
		  }
		  $('.js-fold-btn').click(function(){
			  var that = $(this);
			  if(scrollsTop < fixedTop){				 
				  $(window).scrollTop(fixedTop);	
			  }		
			  setTimeout(function(){
				  if(that.hasClass('fold')){
					  that.removeClass('fold');
					  $('.fold-nav-title').hide();
					  $('.fold-nav-box').hide();
					  $('.mask').hide();
				  }else{
					  that.addClass('fold');
					  $('.fold-nav-title').show();
					  $('.fold-nav-box').show();
					  $('.mask').show();
				  }	
			  },50);			
		  });
		  
		  $(".fold-nav-box li").click(function(){
				  $('.js-fold-btn').removeClass('fold');
				  $('.fold-nav-title').hide();
				  $('.fold-nav-box').hide();
				  $('.mask').hide();
				  var mIndex =$(this).index();
				  $("#swiper-container .swiper-slide").eq(mIndex).trigger("click");
				  return false;		
		  });
		  
		  /*底部导航随滚动位置改变class*/
		  var n = null,scrollsTop =0;
		  var positionNav = function(){
			  
			  var nav_arr = [] ;
			  scrollsTop = document.body.scrollTop;
			  $(".list-box").find("a[class='anchor'][name]").each(function(index,ele){
				  nav_arr.push($(this).offset().top)
			  });	
			  var nav_len = nav_arr.length,myIndex;
			  for(var i=0 ; i<nav_len; i++){
				  if(scrollsTop+1 > nav_arr[i]){
					   myIndex = i;				
				  }
			  }
			  if(myIndex != n){
				  if($("#swiper-container").length > 0){
					  goNav(myIndex);
				  }else{
					  $(".sp_nav").find("li").eq(myIndex).addClass("cur").siblings().removeClass("cur");
				  }				
			  }
			  if(scrollsTop < fixedTop){
				  $('.js-fold-btn').removeClass('fold');
				  $('.fold-nav-title').hide();
				  $('.fold-nav-box').hide();
				  $('.mask').hide();	
			  }
		  }
		  
		  var goNav = function(m){			  
			  if(!m && m != 0){				
				  $("#swiper-container .swiper-slide").find("a").removeClass('cur');
				  n = null;
				  return;
			  }			
			  $("#swiper-container .swiper-slide").eq(m).find("a").addClass('cur').parent().siblings().find("a").removeClass('cur');
			  $(".fold-nav-box li").eq(m).find('a').addClass('cur').parent().siblings().find("a").removeClass('cur');
			  tCurr = $("#swiper-container .swiper-slide-active").index();
			  if ($(".swiper-nav-box").length>0) {
				  if(m>tCurr ){
					  swiper.slideNext();
				  }else{
					  swiper.slidePrev();
				  }
				  n = m;		
			  };
		  };
	 }else{
		/***************************/
		var anchorArr = [];
		$(".list-box").find("a[class='anchor'][name]").each(function(){
			anchorArr.push($(this).offset().top);
		});
		$(".page_nav").on("click","li",function(){
			var index = $(this).index();
			$("html,body").animate({"scrollTop":anchorArr[index]},300);
			return false;	
		});
		
		var scrollStyle = function(){
			var scrollTop = document.body.scrollTop;
				
			if(scrollTop < anchorArr[0]){
				$(".page_nav li").removeClass("cur");
				return false;	
			}
			for(var i=0; i<anchorArr.length; i++){
				if(scrollTop+1 >= anchorArr[i]){
					$(".page_nav li").eq(i).addClass("cur").siblings().removeClass("cur");	
				}
			}	
		}
		/***************************/	 
	 }
	/*底部导航随滚动位置改变class--end*/	

	$(window).scroll(function(){
		topFixed('.topFix');
		topFixed('.menu-nav');
		if(tieleBannerLen > navType){
			positionNav();
		}else{
			scrollStyle();
		}
	})
	
})

















