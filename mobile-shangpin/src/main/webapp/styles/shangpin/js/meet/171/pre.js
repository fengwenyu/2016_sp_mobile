$(function(){
	
	
	//弹框
	$('.pop-btn').bind("click touchstart", function(e){
		e.stopPropagation();
		$('.overlay').css('z-index',999);
		$('.pop-box').css('opacity',1);
	});
	
	$('.close-btn').bind("click touchstart", function(e){
		e.stopPropagation();
		$('.overlay').css('z-index',-1);
		$('.pop-box').css('opacity',0);
	});
	
	
});