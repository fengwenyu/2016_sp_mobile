$(function(){

		var arr = document.cookie.split("; ");
		for(var i=0,len=arr.length;i<len;i++){
			var item = arr[i].split("=");
		}
		
var UA = window.navigator.userAgent;
var CLICK = 'click';
if(/ipad|iphone|android/.test(UA)){
	CLICK = 'tap';
}
//导航图轮播
var mySwiper = new Swiper('#swiper-container1',{
	loop:true,       //循环切换
	autoplay: 2000,  //自动播放
	pagination:'.swiper-pagination', //分页
	paginationClickable: true,
	autoplayDisableOnInteraction : false,
	/*effect : 'coverflow',
	coverflow: {
	  rotate: 20,
	                          stretch: 40,
	  depth: 300,
	  modifier: 2,
	  slideShadows : true
	}*/
});
				
var swiper1 = new Swiper('#swiper-container2', {
	loop:true,
/*	autoplay: 2000,*/
	slidesPerView: 3,
	spaceBetween: 10,
	/*effect : 'coverflow',
	coverflow: {
	  rotate: 20,
	  stretch: 40,
	  depth: 10,
	  modifier: 2,     
	  slideShadows : true
	}*/
});

$(window).scroll(BottomLoading);  //下拉加载
$(window).scroll(topFixed);//滑动头部导航浮层

var menuTopHeight = $('.topFix').offset().top;
//显示导航浮层方法
function topFixed(){	
	var scrolls = document.body.scrollTop;
	if (scrolls > menuTopHeight) {
		$('.topFix').css({
			position: "fixed"
		});
		$('body .alContent').css('margin-top',45);    
	
	}else {
		$('body .alContent').css('margin-top',0); 
		$('.topFix').css({
			position: "static"
		});
		   
	}
};


//头部app下载收起
$('.headApp').css('height',$('.headApp img').height());
setTimeout(function(){
	$('.headApp').css('height',0);
},3000);


var ready=1;
var num = 0;
/*下拉加载*/
function BottomLoading(){
	var path=getRootPath();
	var loading = {
		img : path+'/styles/shangpin/images/201502brand/loading.gif',
		msgText : '正在加载中...',
	};
	
	var htmlUrlLength = $("#hasMore").val();
	var addSelector = '.recommend-list';
	var loadingMsg = '<div id="loading" style="display:block; float:left"><img src=' + loading.img + ' /><p>' +loading.msgText + '</p><div>'

	if(htmlUrlLength!=0){
		if ($(window).scrollTop() + $(window).height() >= $(document).height()-50){
			if(!$('body').find('#loading').length){
				$('.recommend-list').append(loadingMsg);
			}
			
			if(ready==1){
				ready=0;
				  $('#loading').show();
				
				  $.ajax({
					  type : "POST",
					  url : path+"/index/exclusive/recommend/more",
					  dataType:"json",
					  data:{
						  "pageIndex":	$('#pageIndex').val()
						},
					  success : function(data) {
						$('#loading').remove();
						var len=0
						if(data.recProductItem.list.length>0){
							len=data.recProductItem.list.length;
						}
						$("#hasMore").val(data.hasMore);
						$("#pageIndex").val(data.pageIndex);
						  var checkAPP=$('#checkApp').val()
						$.each(data.recProductItem.list,function(index,item){
							$li= $("<li></li>");
							$a = $("<a></a>");
							if(checkAPP){
								hrefV = "shangPinApp://phone.shangpin/actiongodetail?productid="+item.productId;
						  	}else{
						  		hrefV =path+ "/product/detail?productNo="+item.productId + "&picNo=" + item.picNo;
						  	}
							$a.attr({"href":hrefV});
							$img = $("<img/>");
							var pic = item.pic.substring(0,item.pic.indexOf('-')) + "-306-407.jpg";
							$img.attr({"src":pic});
							$p = $("<p></p>").text(item.productName);
							$div1 = $("<div></div>");
							$div1.addClass("li-btn");
							$span1=$("<span></span>");
							if(item.collections*1>0){
								$i1=$("<i></i>");
								$img1=$("<img/>");
								var pic1=path+"/styles/shangpin/images/home/icon13.png"
								$img1.attr({"src":pic1});
								$em1=$("<em></em>").text(item.collections);
								$i1.append($img1);
								$i1.append($em1);
								$span1.append($i1);
								$div1.append($span1)
							}
							$span2=$("<span></span>");
							if(item.comments*1>0){
								$i2=$("<i></i>");
								$img2=$("<img/>");
								var pic2=path+"/styles/shangpin/images/home/icon12.png"
								$img2.attr({"src":pic2});
								$i2.append($img2)
								$em2=$("<em></em>").text(item.comments);
								$i2.append($img2);
								$i2.append($em2);
								$span2.append($i2);
								$div2.append($span2)
							}
							$span3=$("<span></span>");
							$em3=$("<em></em>")
							if(item.status == '0001'|| item.status == '000100'){
								$em3.text('￥' + item.promotionPrice);
								$em3.addClass("red");
								$span3.append($em3);
							}else{
								if(item.isSupportDiscount==1){
									if(data.userLv=='0002'){
										$em3.text('￥' + item.goldPrice);
										$span3.append($em3);
									}else if(data.userLv=='0003'){
										$em3.text('￥' + item.platinumPrice);
										$span3.append($em3);
									}else if(data.userLv=='0004'){
										$em3.text('￥' + item.diamondPrice);
										$span3.append($em3);
									}else{
										$em3.text('￥' + item.limitedPrice);
										$span3.append($em3);
									} 
								}else{
									$em3.text('￥' + item.limitedPrice);
									$span3.append($em3);
								}
							}
							$div1.append($span3);
							$a.append($img);
							$a.append($p);
							$a.append($div1);
							$li.append($a);
							$('.recommend-list').append($li);
						})
						
						num++;
						ready=1;
						$('#pageIndex').val(data.pageIndex);	
						/*if(num == htmlUrlLength){
							$('#loading').html('没有更多了');
							$('#loading').show();
							$('#loading img').hide();
						}*/
					  },
					  complete:function(){
							$('#loading').remove();
					  },
					  error : function(){
						  $('#loading').remove();
					  }
				  });
					
		   }
	  }
   }
}



});