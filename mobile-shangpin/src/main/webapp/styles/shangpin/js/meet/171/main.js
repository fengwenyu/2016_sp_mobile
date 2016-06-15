$(function(){
	
	if($(".nav_menu_pop").length > 0){
		$(".nav_menu_pop").height(16110).hide();
	}

	//关闭弹出层
	$(".poo_close, .nav_menu_pop").bind("click touchend", function(){
		$(".footer_nav").removeClass("slideDownXmas").addClass("slideUpXmas");
		$(".nav_menu_pop").hide();
		$(".left_menu").removeClass("slideIn").addClass("slideOut");
		return false;
	});

	//点击右侧浮动框
	$(".nav_menu li").bind("click touchend", function(){
		var $this = $(this);
        $this.addClass("curr").siblings().removeClass("curr");
		$(".footer_nav").removeClass("slideDownXmas").addClass("slideUpXmas");
        $(".nav_menu_pop").hide();
        $(".left_menu").removeClass("slideIn").addClass("slideOut");
    });

    //点击菜单显示导航
    $(".nav_list").bind("click touchend", function(){
		$(".footer_nav").removeClass("slideUpXmas").addClass("slideDownXmas");
    	setTimeout(function(){$(".nav_menu_pop").show();}, 300);
    	$(".left_menu").removeClass("slideOut").addClass("slideIn");
		return false;
    });
	
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