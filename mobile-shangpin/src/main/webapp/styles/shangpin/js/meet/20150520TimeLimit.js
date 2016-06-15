$(function(){	
	

	
	var htmlStr = $('.content_detail').children("div").children("div").html().trim();
	if(htmlStr==undefined||htmlStr==null||htmlStr==""){
		$('#tabs_txt0').show();
		$('.tab_info li').addClass('curr');
	}else{
		
		//tab切换
		$(".tab_info").find("li").click(function(e){
			e.preventDefault();
			var $this = $(this);
			var $thisIndex = $this.index();
			$(this).addClass("curr").siblings().removeClass("curr");

			$(".content_info").find(".content_list").eq($thisIndex).show().siblings().hide();
		});
		
	}
	
	var meetId = getQueryString("meetId");
	if(meetId=="584"){
		$("#imageTitle").attr("src", "http://pic3.shangpin.com/group1/M00/E0/F3/rBQKaFahonWAagSjAAAzzLVauks336.jpg"); 
	}
	function getQueryString(name) { 
		var reg = new RegExp("(^|&)" + name + "=([^&]*)(&|$)", "i"); 
		var r = window.location.search.substr(1).match(reg); 
		if (r != null) return unescape(r[2]); return null; 
	} 
	
});