// JavaScript Document
var boxScroll;
function loaded(){
	var winW = $(window).width() - 20;
	$(".tab_content").width(winW);
	
	boxScroll = new iScroll('tabContent',
    {
		hScrollbar:true,
		vScrollbar:false
    });
}
//document.addEventListener('DOMContentLoaded', loaded, false);