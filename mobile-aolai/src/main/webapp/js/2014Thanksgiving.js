$(function(){
	
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
	
});