// JavaScript Document
$(function(){
var path=getRootPath();	
var scaleW=window.innerWidth/320;
var scaleH=window.innerHeight/480;
var resizes = document.querySelectorAll('.resize');
          for (var j=0; j<resizes.length; j++) {
           resizes[j].style.width=parseInt(resizes[j].style.width)*scaleW+'px';
		   resizes[j].style.height=parseInt(resizes[j].style.height)*scaleH+'px';
		   resizes[j].style.top=parseInt(resizes[j].style.top)*scaleH+'px';
		   resizes[j].style.left=parseInt(resizes[j].style.left)*scaleW+'px'; 
	
          }
var scales = document.querySelectorAll('.txt');
for (var i=0; i<scales.length; i++) {
	ss=scales[i].style;
	ss.webkitTransform = ss.MsTransform = ss.msTransform = ss.MozTransform = ss.OTransform =ss.transform='translateX('+scales[i].offsetWidth*(scaleW-1)/2+'px) translateY('+scales[i].offsetHeight*(scaleH-1)/2+'px)scaleX('+scaleW+') scaleY('+scaleH+') ';
}/**/

		  
  var mySwiper = new Swiper ('.swiper-container-v', {
   direction : 'vertical',
   mousewheelControl : true,
    preloadImages: false,
   onInit: function(swiper){
	   //setTimeout(function(){
		 swiperAnimateCache(swiper);
		 swiperAnimate(swiper);
	   //},1000);
   },
   onSlideChangeEnd: function(swiper){
	  swiperAnimate(swiper);
   },
   onTransitionEnd: function(swiper){
      swiperAnimate(swiper);
    }	  
  }) ;
  
  var swiperV = new Swiper('.swiper-container-h', {
	loop:true,
	nextButton: '.h-swiper-button-next',
    prevButton: '.h-swiper-button-prev',
  });
	
	
	
	var audio = (function(options){
		var _audio = new Audio(),
			_playing = true;
	
		for(var key in options){
			if(options.hasOwnProperty(key) && (key in _audio)){
				_audio[key] = options[key];
			}
		}
	
		return {
			play:function(){
				_playing = true;
				_audio.play();
			},
			pause:function(){
				_playing = false;
				_audio.pause();
			},
			toggle:function(){
				_playing ? this.pause() : this.play();
				return _playing;
			}
		}
	})({
		loop: true,
		preload: "auto",
		src: path+"/styles/shangpin/audio/FashionRun/music.mp3?20150708"
	});
	$(document).one("touchstart", function(e){
		if($(e.target).attr("id")=="music"){
			$("#music").removeClass("on");
		}else{
			audio.play();
		}
		event.preventDefault();
	});
	$("#music").addClass("on").on("click",function(){
		if($(this).hasClass("on")){
			audio.pause();
		}else{
			audio.play();
		}
		$(this).toggleClass("on");
	});

});