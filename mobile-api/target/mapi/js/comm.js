// JavaScript Document
$(function(){
	//tab切换事件
	var tabs = function(nav,content){
		$(nav).find("a").bind("click",function(){
			var index = $(this).index();
			$(this).addClass("cur").siblings().removeClass("cur");
			$(content).eq(index).show().siblings(content).hide();
			//$(content).css("left","0px");  //返回到初始位置
			boxScroll.scrollToPage(0, 0, 200);
			return false;
		});
	}
	
	tabs(".tab_nav",".tab_content table");
	//tab切换事件
});

var boxScroll;
function loaded(){
	var winW = $(window).width() - 36;
	$(".tab_content").width(winW);
	
	boxScroll = new iScroll('tabContent',
    {
		hScrollbar:true,
		vScrollbar:false
    });
}
document.addEventListener('DOMContentLoaded', loaded, false);