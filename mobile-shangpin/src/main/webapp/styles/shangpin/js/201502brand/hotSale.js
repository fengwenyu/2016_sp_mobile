
//滑动显示导航浮层
var menuNavHeight = $('.menu-nav').offset().top;
$(window).scroll(function() {
	var scrolls = document.body.scrollTop;
	if (scrolls >= menuNavHeight-39) {
		var topSize=44;
		//如果是微信页面浮层为0
		var isWx=$("#_iswx").val();
		var isApp=$("#_isapp").val();
		if(isWx || isApp){
			topSize=0;
		}
		$('.menu-nav').css({
			position: "fixed",
			top: topSize,
			zIndex: 99
		});
		$('.list-box').css('margin-top',39);    
	
	}else {
		$('.menu-nav').css({
			position: "static",
		});
		$('.list-box').css('margin-top',0);    
	}
});

//关注按钮
$('.follow_btn').click(function(){
	if($(this).hasClass('followed_btn')){
		$(this).removeClass('followed_btn');
		$(this).html('+关注');
	}else{
		$(this).addClass('followed_btn');
		$(this).html('已关注');
	}
})


/*滑块列表*/
//
//app.TabSlider('#tabSlider1');
//app.TabSlider('#tabSlider2');


/*优惠劵弹层*/
$(function(){
	var imgHeight = $('.tabSlider-bd .hallBox a img').eq(0).height(); //获取焦点图img高度
	
	$('.tabSlider-bd .hallBox a').css('min-height',imgHeight);
	if(imgHeight>0) {
		 $("#tabSlider1").css({"visibility": "visible"});
	} 
	  /*滑块列表*/
	app.TabSlider('#tabSlider1');
	app.TabSlider('#tabSlider2');
  var $overlay = $('#overlay');

  function modalHidden($ele) {
    $ele.removeClass('modal-in');
    $ele.one('transitionend',function(){
      $ele.css({"display": "none"});
      $overlay.removeClass('active');
    });
  }

  $('.coupon-list li').click(function(){
    var $that = $(this);
    $overlay.addClass('active');
    $('.modal').animate({"display":"block"},100,function(){
      $('.modal').addClass('modal-in');
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

   
});



//筛选按钮事件
$(".fillBtn").click(function(){
	$(".alContent").attr("class", "alContent")
	$("#filter_layer").show();
	$("#filter_box").attr("class", "slideIn");
	$(".alContent").attr("class", "alContent slideLeft").height("auto").css({"overflow":"auto"});
	filterboxSelect();
});


//确定按钮事件
$("#finishBtn").click(function(){
	$("#filter_box").attr("class", "slideOut");
	setTimeout(function(){
		$("#filter_layer").fadeOut();
		$('#search_form').submit();
	},600);
	$(".alContent").attr("class", "alContent slideRight").height("auto").css({"overflow":"auto"});
	return false;
});


//上次的选中状态
function filterboxSelect(){
	//默认选中选项
	var categoryName = $("#categoryName").val();
	var brandName = $("#brandName").val();
	var productSize = $("#size").val();
	var primaryColorId = $("#color").val();
	var primaryColorName = $("#colorName").val();
	var price = $("#price").val();

	$(".category-box ul li").each(function(){
		var selectFlag ="";
		var selValue = $(this).html();
		if(selValue != ""){
			//判断列表的选中状态
			switch(selValue){
				case categoryName : 
					selectFlag = "1"; 
					$(this).addClass("cur");
					break;
				case brandName : 
					selectFlag = "1"; 
					$(this).addClass("cur");
					break;
				case productSize : 
					selectFlag = "1"; 
					$(this).addClass("cur");
					break;
				case price : 
					selectFlag = "1"; 
					$(this).addClass("cur");
					break;
				default : 
					selectFlag ="";
					break;
			}
		}
		
	});	
	
	$(".color-box ul li").each(function(){
		var selectFlag ="";
		var selValue = $(this).find('span').html();
		if(selValue != ""){
			//判断颜色的选中状态
			switch(selValue){
				case primaryColorName : 
					selectFlag = "1"; 
					$(this).addClass("cur");
					$(this).find('a').css('background',primaryColorId);
					break;
				default : 
					selectFlag ="";
					break;
			}
		}
		
	});			

}



//关闭按钮事件
$("#filter_close").click(function(){
	$("#filter_box").attr("class", "slideOut");
	setTimeout(function(){
		$("#filter_layer").fadeOut();
	},600);
	$(".alContent").attr("class", "alContent slideRight").height("auto").css({"overflow":"auto"});
	return false;
});

/*筛选栏里选择选项*/
$('.category-box li,.color-box li').click(function(){
	$(this).addClass("cur").siblings().removeClass("cur");
});



//特卖未开启弹出层
$('body').delegate('.no_open','click',function(e){
	e.preventDefault();
	jShare('还没开始呢，20:00 再来吧！',"","");
	return false;
});


//点击列表按钮切换并跳转
$('#list_menu li').click(function(e){
	e.preventDefault();
	var url = $(this).children('a').attr('href');
	if(($(this).hasClass('price-btn')) && ($(this).hasClass('curr'))){
		if($('.price-btn').hasClass('price-down')){
			$(this).removeClass('price-down');
		}else{
			$(this).addClass('price-down');
		}
	}

	
	
	$(this).addClass('curr').siblings().removeClass('curr price-down');
	window.location.href=url;

})





//倒计时
function restTimefun(){
	var nowDate = new Date();
	var nowTime = nowDate.getTime();
	$('#last_crazy a').each(function(index, element) {
		var end_time = $(this).find('.rest-time').attr('end-time');
		var restHours = Math.floor((end_time - nowTime)/(365*24*60*60)+1);
		var restTime ="剩余"+restHours +"小时";
		$(this).find('.rest-time').html(restTime);

		setTimeout(restTimefun,3600000);
	});
	
}
restTimefun();









