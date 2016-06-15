$(function(){
	
	/*计算导航宽度*/
	var filterWidth = 0;
	
	var thisLeft = 0;
	var thisWid = 0;

	$('.header_nav li').each(function(index, element) {
        filterWidth += $(this).width()+28;
        
        if($(this).hasClass("hover")){
        	//thisLeft = $(this).offset().left;
        	thisLeft = (index*88)-120;
        	thisWid = $(this).width()+28;
			thisIndex = $(this).index();
        }
    });
	
	$('.header_nav').css('width',filterWidth);

	$("#filter_box").animate({"scrollLeft":thisLeft},"fast");
	
	/*if(thisIndex <= 1){
		$("#filter_box").animate({"scrollLeft":0},"fast");
	}else if(thisIndex >= 5){
		$("#filter_box").animate({"scrollLeft":filterWidth},"fast");
	}else{
		$("#filter_box").animate({"scrollLeft":thisLeft/2},"fast");
	}*/
	
	$(".header_nav li").click(function(){
		$(this).addClass("hover").siblings().removeClass("hover");
	});
});