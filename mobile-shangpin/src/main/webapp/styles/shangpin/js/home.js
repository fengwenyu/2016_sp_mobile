$(function(){
	
var UA = window.navigator.userAgent;
var CLICK = 'click';
if(/ipad|iphone|android/.test(UA)){
	CLICK = 'tap';
}
//导航图轮播
$('#J_m-slider').slider({wrap:".slider-outer",wrapUl:".slider-wrap",wrapStatus:'.slider-status',isLoop:true,isPlay:true});

$(window).scroll(topFixed);//滑动头部导航浮层

var menuTopHeight = $('.topFix').offset().top;
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

$('body').delegate('.close-app',CLICK,function(e){
	$('.daoliuBanner').hide();
});



//值得买锚点跳转
$('body').delegate('.worth-buy-btn',CLICK,function(e){
	e.preventDefault();
	var worthBuyHeight = $('#worthBuy').offset().top;
	document.body.scrollTop = worthBuyHeight-50;
});

//特卖未开启弹出层
$('body').delegate('.no_open',CLICK,function(e){
	e.preventDefault();

	var timestamp=new Date().getTime();
	if ($(this).attr("startTime")>timestamp){
		var html="还没开始呢，"+$(this).attr("startTag")+" 再来吧！";
		$('#popup_message').html(html);
		e.preventDefault();
		$('#popup_overlay').show();
		$('#popup_container').show();
		setTimeout(function(){
			$('#popup_overlay').hide();
			$('#popup_container').hide();
		},2000);
	}
});

//判断值得买按钮是否存在
if($('.list-title a').length>1){
	$('.list-title a').css("width","50%");
}else{
	$('.list-title a').css("width","100%");
}


});