var path = getRootPath();
$(function(){
	//判断当是微信的时候不显示头
	var iswx =$("#_iswx").attr("value");
	var isapp =$("#_isapp").attr("value");
	if(!iswx&&!isapp){
		$('.cate-box').css({top:45});
	}else{
		$('.cate-box').css({top:0});
	}
	var scrollLeft,
		scrollMid,
		scrollRight;
	if($("#categoryLeft").length > 0){
		scrollLeft();
	}
	if($("#categoryMid").length > 0){
		scrollMid();
	}
	if($("#categoryRight").length > 0){
		scrollRight();
	}
	//tab切换事件
	var tabs = function(nav,content){
		$(nav).find("li").bind("click",function(e){
			e.preventDefault();
			var nav_id=$(this).find('input')[0].value;
			//var index = $(this).index();
			$(this).addClass("cur").siblings().removeClass("cur");
			$("#categoryMid div .tabs_cell").text("");
			//$(content).eq(index).show().siblings(content).hide();
			scrollMid.refresh();
			getCategory(nav_id);
			scrollMid.refresh();
			scrollMid.scrollTo(0, 0, 200);
		});
	}
	tabs(".cate-list", ".tabs_cell");
	
	
	var scrollLeft,
		scrollMid,
		scrollRight;
	function scrollLeft(){
	  scrollLeft = new iScroll('categoryLeft',{
				  hScrollbar:false,
				  vScrollbar:false,
				  hideScrollbar:true
			  });
	}
	function scrollMid(){
	  scrollMid = new iScroll('categoryMid',{
				  hScrollbar:false,
				  vScrollbar:false,
				  hideScrollbar:true
			  });
	}
	function scrollRight(){
	  scrollRight = new iScroll('categoryRight',{
				  hScrollbar:false,
				  vScrollbar:false,
				  hideScrollbar:true
			  });
	}
	
	
	//监听横竖屏切换事件
	window.addEventListener("orientationchange", function() {

		scrollMid.refresh();
		
	}, false);
	


//请求分类信息
function getCategory(id) {
	 if(id ==null){
		 $("#categoryMid div .tabs_cell").append("暂无商品");	
	 }
		$.ajax({
			url : path + "/category/operations?"+Math.random(),
			data : {
				"id" : id
				},
			dataType : "json",
			success : function(data) {
				var html = "";
				var len = 0;
				if (data.categoryItem.operation != null) {
					len = data.categoryItem.operation.length;
				}
				if (len > 0) {
					$.each(data.categoryItem.operation, function(index, item) {
					    if(item.name !=""){
					    	html +="<div class='cate-brand-title'><a href='javascript:;'>";
					    	html +=item.name;
					    	html +="</a><em></em></div>";
					    }
						if(item.type!=null && item.type>0){
							switch(parseInt(item.type)) 
							{ 
							case 1: 
								html +="<div class='cate-banner'>";
							    html = htmlInfo(item,html);
								html +="</div>";
								break; 
							case 4: 
							case 5: 
								html +="<div class='brandBox clr'>";
							    html = htmlInfo(item,html);
								html +="</div>";
								break;
							default:; 
							}
						}
					});
					$("#categoryMid div .tabs_cell").append(html);
					setTimeout(function(){
						scrollMid.refresh();
					},500)
						
					
				}else{
					$("#categoryMid div .tabs_cell").append("暂无商品");	
				}
			}
		});
		//scrollMid.refresh();
	 //
}
//拼接分类信息
function htmlInfo(item,htmls) {
	$.each(item.list, function(n2, v2) {
		switch(parseInt(v2.type)) 
		{ 
		 case 1: 
			 htmls +="<a href='"+path+"/subject/product/list?topicId="+v2.refContent+"&postArea=0'>";
			break; 
		 case 2: 
			 htmls +="<a href='"+path+"/category/product/list?categoryNo="+v2.refContent+"&postArea=0'>";
		   break; 
		 case 3: 
			 htmls +="<a href='"+path+"/brand/product/list?brandNo="+v2.refContent+"&postArea=0&WWWWWWWWW'>";
			break;
		 case 4: 
			 htmls +="<a href='"+path+"/product/detail?productNo="+v2.refContent+"'>";
			break;
		 case 5: 
			 htmls +="<a href='"+v2.refContent+"'>";
			break;
		 case 6: 
			 htmls =htmlLink(html);
			break;
		 case 9: 
			 htmls +="<a href='"+path+"/lable/product/list?tagId="+v2.refContent+"'>";
			break;
		 default:
			 htmls +="<a href='#'>"; 
		   break;
		}
		var pic = v2.pic.substring(0,v2.pic.indexOf('-')) + "-"+item.width+"-"+item.height+".jpg";
		htmls +="<img alt='' src='"+pic+"'>";
		if(item.type=='5'){
            htmls +="<span>"+v2.name+"</span>";
		}
		htmls +="</a>";
	});
	return htmls;
}
//拼接分类信息
function htmlLink(html){
	switch(parseInt(v2.type)) 
	{ 
	 case 1: 
		 html +="<a href='"+path+"/coupon/list'>";
		break; 
	 case 2: 
		 html +="<a href='"+path+"/order/list-1'>";
	   break; 
	 case 3: 
		 html +="<a href='"+path+"/collect/product/list?pageIndex=1&pageSize=20&shopType=1'>";
		break;
	 case 4: 
		 html +="<a href='"+path+"/giftCard/productList'>";
		break;
	 case 7: 
		 html +="<a href='http://m.aolai.com'>";
		break;
	 default:
		 html +="<a href='#'>"; 
	   break;
	}
	return html;
}


});