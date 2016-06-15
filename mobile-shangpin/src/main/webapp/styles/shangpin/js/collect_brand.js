var num = 0;
var ready=1;
var $overlay = $('#overlay');
$(".alContent").attr("style", "margin-top:0");
var imgHeight = $('.tabSlider-bd .hallBox a img').eq(0).height(); // 获取焦点图img高度
$('.tabSlider-bd .hallBox a').css('min-height', imgHeight);

//判断设备
  var UA = window.navigator.userAgent;
  var CLICK = 'click';
  if(/ipad|iphone|android/.test(UA)){
	  CLICK = 'tap';
  }
  
$(window).scroll(BottomLoading);  //下拉加载
$(window).scroll(menuFixed);//滑动显示导航浮层

$('.container').delegate('.coupon-list li,.receive-btn',CLICK,function(e){
	e.stopImmediatePropagation();
	var $that = $(this);
	var code=$(this).attr("id");
	$overlay.addClass('active');
	$('.modal').animate({"display":"block"},100,function(){
	  $('.modal').addClass('modal-in');
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


//筛选按钮事件
$('body').delegate('.fillBtn',CLICK,function(e){
	e.preventDefault();
	fillBtn();
});

//确定按钮事件
$('body').delegate('#finishBtn',CLICK,function(e){
	e.preventDefault();
	filterFinish();
});

//关闭按钮事件
$('body').delegate('#filter_close',CLICK,function(e){
	e.preventDefault();
	filterClose();
});

/*筛选栏里选择选项*/
$('body').delegate('.category-box li,.color-box li',CLICK,function(e){
	$(this).addClass("cur").siblings().removeClass("cur");
});

$('body').delegate('.color-box a',CLICK,function(e){
	$(this).parent().addClass("cur").siblings().removeClass("cur");
});
$('body').delegate('#sizeItem li',CLICK,function(e){
	$(this).addClass("cur").siblings().removeClass("cur");
});



//点击列表按钮切换并跳转
$('#list_menu li')[CLICK](function(e){
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


$(window).scroll(topFixed);//滑动头部导航浮层

if($('.topFix').length){
	var menuTopHeight = $('.topFix').offset().top;
}
//显示导航浮层方法
function topFixed(){	
	var scrolls = document.body.scrollTop;
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
};

if($('.menu-nav').length){
	var menuNavHeight = $('.menu-nav').offset().top;
}

//显示导航浮层方法
function menuFixed(){
	
	var scrolls = $(window).scrollTop();
	if (scrolls >= menuNavHeight-45) {
		var topSize=45;
		var isWx=$("#_iswx").val();
		if(isWx){
			topSize=0;
		}
		$('.menu-nav').css({
			position: "fixed",
			top: topSize,
			zIndex: 99
		});
		$('.list-box').css('margin-top',56);    
	
	}else {
		$('.menu-nav').css({
			position: "static",
		});
		$('.list-box').css('margin-top',15);    
	}
}


function modalHidden($ele) {
  $ele.removeClass('modal-in');
  $ele.one('webkitTransitionEnd',function(){
	$ele.css({"display": "none"});
	$overlay.removeClass('active');
  });
}



//筛选按钮
function fillBtn(){
	$("#filter_layer").show();
	$('body').css({'overflow':'hidden'});
	setTimeout(function(){
		$('.alContent').css({'visibility':'hidden'});
		$('.app_bg').css({'visibility':'hidden'});
	},1000)
	$("#filter_box").attr("class", "slideIn");
	//filterboxSelect();
}

//关闭筛选按钮
function filterClose(){
	$("body").unbind("touchmove");
	$("#filter_box").attr("class", "slideOut");
	$('.alContent').css({'visibility':'visible'});
	$('.app_bg').css({'visibility':'visible'});
	$('body').css({'overflow':'auto'});
	setTimeout(function(){
		$("#filter_layer").fadeOut();
	},600);
	$('#categoryNo').val("");
	$('#categoryName').val("");
	$('#size').val("");
	$('#color').val("");
	$('#colorName').val("");
	$('#price').val("");

	//$('#search_form').submit();
}

//筛选确定按钮
function filterFinish(){
	$("body").unbind("touchmove");//解除禁止滑动事件
	$("#filter_box").attr("class", "slideOut");
	$('body').css({'overflow':'auto'});
	$('.alContent').css({'visibility':'visible'});
	$('.app_bg').css({'visibility':'visible'});
	setTimeout(function(){
		$("#filter_layer").fadeOut();
		$("#start").val("1");
		//$('#search_form').submit();
	},600);
}




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






/*下拉加载*/
function BottomLoading(){
	var path = getRootPath();
	var loading = {
		img : path + '/styles/shangpin/images/201502brand/loading.gif',
		msgText : '正在加载中...',
	};
	var htmlUrl = [
		'/data/list/list1.html',
		'/data/list/list2.html',
		'/data/list/list3.html',

	];
	var htmlUrlLength = htmlUrl.length;
	var addSelector = '.prod_list';
	var loadingMsg = '<div id="loading" style="display:block"><img src=' + loading.img + ' /><p>' +loading.msgText + '</p><div>'
	var hasMore = $("#hasMore").val();
	if(hasMore == "1"){
		
		if ($(window).scrollTop() + $(window).height() >= $(document).height()-50){
			if(!$('body').find('#loading').length){
				$('.list-box').append(loadingMsg);
			}
			
			if(ready==1){
				  $('#loading').show();
				  var pageIndex = parseInt($('#pageIndex').val()) + 1;
					$('#pageIndex').val(pageIndex);
					  $('#loading').show();
					  ready=0;
					  $.ajax({
						  type : "POST",
						  url : path + "/collect/brand/more",
						  data : {pageIndex:pageIndex,pageSize:20},
						  dataType:"json",
						  success : function(data, textStatus, XMLHttpRequest) {
							  var sessionstatus=XMLHttpRequest.getResponseHeader("sessionstatus");
								if(sessionstatus=="timeout"){
									//var locationURL = XMLHttpRequest.getResponseHeader("locationURLAll"); // 通过XMLHttpRequest取得响应头，locationURL，
									// 如果超时就处理 ，指定要跳转的页面
									window.location.href = path + "/login?back=/collect/brand/list?pageIndex=1&pageSize=20";
								}
							  $("#hasMore").val(data.hasMore);
							  $('#loading').hide();
							  var hasMore = data.hasMore;
							  if(data.brands != null && data.brands.length > 0){
								  $.each(data.brands,function(index,item){
									  var pic = item.pic.substring(0,item.pic.indexOf('-')) + "-128-80.jpg";
									  $li = $("<li><a href='" + path + "/brand/product/list?brandNo=" + item.id + "' class='clr'><img src='" + pic + "'/><strong>" + item.nameEN + "</strong><span>品牌店</span></a></li>");
									  $(".brand-collect-list").append($li);
								  })
							  }
							num++;
							ready=1;
							if(hasMore == 0){
								$('#loading').html('没有更多了');
								$('#loading').show();
								$('#loading img').hide();
							}else{
								ready=1;
							}
						  },
						  error : function(){
							alert('数据获取失败，请刷新页面');
						  }
					  });
		   }
	  }
   }
}
