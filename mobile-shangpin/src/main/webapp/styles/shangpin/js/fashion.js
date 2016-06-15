
var UA = window.navigator.userAgent;
var CLICK = 'click';
if(/ipad|iphone|android/.test(UA)){
	CLICK = 'tap';
}
$(window).scroll(function(){
	topFixed('.topFix');
});

$(function(){

  var imgHeight = $('.fashion_box img').eq(0).height(); //获取焦点图img高度
  $('.fashion_box img').css('min-height',imgHeight);
  /*滑块列表*/
 app.TabSlider('#tabSlider1');
		
   

   
});

 
 


