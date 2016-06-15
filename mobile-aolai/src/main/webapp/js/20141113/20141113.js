$(function(){
	
	if($(".nav_menu_pop").length > 0){
		$(".nav_menu_pop").height(document.body.scrollHeight).hide();
	}

	//关闭弹出层
	$(".pop_close, .nav_menu_pop").bind("click touchend", function(){
		$(".nav_menu_pop").hide();
		$(".nav_menu").removeClass("slideIn").addClass("slideOut");

	});

	//点击右侧浮动框
	$(".nav_menu li").click(function(){

        var $this = $(this);
        $this.addClass("curr").siblings().removeClass("curr");
        $(".nav_menu_pop").hide();
        $(".nav_menu").removeClass("slideIn").addClass("slideOut");
    });
     
    //鼠标滚动事件
    $(window).scroll(function(){
        //页面滚动时，显示置顶的小图标
    	if($(this).scrollTop()>=window.screen.availHeight){
        	$(".nav_top").css("display","block");
        }else{
        	$(".nav_top").css("display","none");
        }
    });
	
	//点击置顶，页面滚动到顶部
    $(".nav_top").click(function(){
    	window.scrollTo(0,0);
    });

    //点击详情显示滚动条
    $(".nav_list").click(function(){
    	$(".nav_menu_pop").show();
    	$(".nav_menu").removeClass("slideOut").addClass("slideIn");
    });
	
});