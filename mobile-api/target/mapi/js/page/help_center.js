$(function(){
   //tab切换
	$(".tab_info").find("a").click(function(e){
		e.preventDefault();
		var $this = $(this);
		var $thisIndex = $this.index();
		$(this).addClass("curr").siblings().removeClass("curr");
		$(".content_info").find(".content_list").eq($thisIndex).show().siblings().hide();
	});
   
});

 
 


