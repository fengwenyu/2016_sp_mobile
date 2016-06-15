// JavaScript Document

$(function(){
	
	//tab切换事件
	var tabs = function(nav,content){
		$(nav).find("li").bind("click",function(){
			var index = $(this).index();
			
			$(this).addClass("cur").siblings().removeClass("cur");
			$(content).eq(index).show().siblings(content).hide();
			
		});
	};
	
	tabs(".tabs_menu", ".tabs_Cell");
	
	//下拉内容
	var $category_list_title = $('a.category_list_title');
	
	$category_list_title.bind("click",function(){
		var $this = $(this),
			$category_list_con = $this.siblings('.category_list_con');
			
		if($category_list_con.is(':hidden')){
			$('div.category_list_con').slideUp().find('i.icon_arrow').hide();
			$category_list_con.slideDown(function(){
				$(this).find('i.icon_arrow').fadeIn('fast');	
			});
		}else{
			$('div.category_list_con').slideUp().find('i.icon_arrow').hide();
		}
		
		
	});
});