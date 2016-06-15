var menuTopHeight=0;
$(function(){
var startSliderHeight = $("#swiperList .swiper-slide").find('.list-box').height();
$("#swiperList .swiper-wrapper").height(startSliderHeight);
$("#swiperList").height(startSliderHeight);

var curUrl = window.location.hash;
var urlArg=curUrl.substr(1); 
if(urlArg==1){
	$("#swiperNav .swiper-slide").eq(urlArg-1).find("a").addClass('cur')
}else if(urlArg==2){
	$("#swiperNav .swiper-slide").eq(urlArg-1).find("a").addClass('cur')
}else if(urlArg==3){
	$("#swiperNav .swiper-slide").eq(urlArg-1).find("a").addClass('cur')
}else if(urlArg==4){
	$("#swiperNav .swiper-slide").eq(urlArg-1).find("a").addClass('cur')
}else if(urlArg==5){
	$("#swiperNav .swiper-slide").eq(urlArg-1).find("a").addClass('cur')
}else if(urlArg==6){
	$("#swiperNav .swiper-slide").eq(urlArg-1).find("a").addClass('cur')
}else if(urlArg==7){
	$("#swiperNav .swiper-slide").eq(urlArg-1).find("a").addClass('cur')
};

//banner轮播
var mySwiper = new Swiper('#swiper-container1',{
	loop:true,       //循环切换
	autoplay: 2000,  //自动播放
	autoplayDisableOnInteraction : false, //swiper之后自动切换不会停止
	pagination:'.swiper-pagination', //分页
	paginationClickable: true,
	
});
$(window).scroll(BottomLoading); 


var navIndex=$('#swiperNav .swiper-slide a').index($('.cur')); ;
    
//点击分类导航左右滑动
var curr = 0, tCurr = 0;
$("#swiperNav .swiper-slide").click(function(){
	$(this).find("a").addClass('cur').parent().siblings().find("a").removeClass('cur');
	$("#firstCategoryNO").val($(this).attr("id"));
	curr = $(this).index();
	navIndex = curr-1;
	tCurr = $("#swiperNav .swiper-slide-active").index();
	
	if(curr>tCurr){
		swiperNav.slideNext();
	}else{
		swiperNav.slidePrev();
	};

	swiperList.slideTo(curr);

});

//分类导航滑动
var swiperNav = new Swiper('#swiperNav', {
	initialSlide :navIndex,
	slidesPerView: 'auto',
});

var timeajax;
var loadFlag = true;
var timeout = false;
var loadingBox = '<div class="loading-box"><div class="loading"></div><p>数据正努力加载中，请稍安勿躁~</p></div>';
//分类导航对应内容滑动
var swiperList = new Swiper('#swiperList', {
	onSlideChangeStart: function(swiper){
		var path = getRootPath();
		var checkAPP=$('#checkApp').val();
		var isNative=$('#isNative').val();
		clearTimeout(timeajax);
		var $this=$(this);
		$("#tabIndex").val(swiper.activeIndex);
		swiperNav.slideTo(swiper.activeIndex);
	    $("#swiperNav .swiper-slide").eq(swiper.activeIndex).find("a").addClass('cur').parent().siblings().find("a").removeClass('cur');
      	var $ele = $("#swiperList .swiper-slide").eq(swiper.activeIndex);
      	$("#firstCategoryNO").val($("#swiperNav .swiper-slide").eq(swiper.activeIndex).attr("id"));
      	var sliderHeight = $ele.find('.list-box').height();
      	$("#swiperList .swiper-wrapper").height(sliderHeight);
      	$("#swiperList").height(sliderHeight);

		if(loadFlag){
			
			//滑动ajax请求数据
			if($ele.html()==""){
				if(!$("#swiperList").find('.loading-box').length){
					$("#swiperList").append(loadingBox);
				}
				timeajax=setTimeout(function(){
					timeout=true;
					if(timeout){
						loadFlag = false;
						$.ajax({
							type : "POST",
							url : path+"/ephome/get/mall", 
							data:{
					              firstCategoryNO:$("#firstCategoryNO").val(),
					              postAreaNO:$("#postAreaNO").val()
					         },
							dataType:"json",
							success : function(data) {
								$('.loading-box').remove();
								loadFlag = true;
								$div3=$("<div></div>");
								$div3.addClass("list-box");
								var bannerHeight = $('#swiper-container1').height();
								// window.scroll(0,bannerHeight);
								
//								if(data.secondCategory != null && data.secondCategory.length > 0){
//									$ul=$("<ul></ul>");
//									$ul.addClass("shoes-list clr");
//									$.each(data.secondCategory,function(index,item_index){
//										$li=$("<li></li>");
//										$a=$("<a></a>");
////										if(checkAPP){
////											if(isNative=='1'){下个版本再放开进行判断
////												hrefV="ShangPinApp:// phone.shangpin/actiongocatelist?title="+item_index.shopCategoryName+"&cateid="+item_index.shopCategoryNo+"postarea=2";
////											}else{
////												hrefV="ShangPinApp://phone.shangpin/actiongowebview?title="+item_index.shopCategoryName+"&url="+$('#basePath').val()+"/category/product/list?categoryNo="+item_index.shopCategoryNo+"8uuuuu8postArea=2";
////											}
////											
////										}else{
////											hrefV=path+"/category/product/list?categoryNo="+item_index.shopCategoryNo+"&postArea=2&categoryName="+item_index.shopCategoryName;
////										}
//										hrefV=path+"/category/product/list?categoryNo="+item_index.shopCategoryNo+"&postArea=2&categoryName="+item_index.shopCategoryName;
//										$a.attr({"href":hrefV});
//										$i=$("<i></i>");
//										$i.addClass("icon-caret-right");
//										$a.append($i);
//										$a.append(item_index.shopCategoryName);
//										$li.append($a);
//										//$ul.append($li);
//					                    
//									});
//									
//									$div3.append($ul);	
//								}
								
								
								if(data.productList != null && data.productList.length > 0){
									 $.each(data.productList,function(index,item){
										 $divc=$("<div></div>");
										 $divc.addClass("content-list");
										 $a=$("<a></a>");
										 if(checkAPP){
								             hrefV = "shangPinApp://phone.shangpin/actiongodetail?productid="+item.productNo;
								           }else{
								        	   hrefV = path+"/product/detail?productNo="+item.productNo;
								           }
										 $a.addClass("clr");
										 $a.attr({"href":hrefV});
										 $divimg=$("<div></div>");
										 $divimg.addClass("list-img");
										 $img=$("<img/>");
										 var pic = item.pic.substring(0,item.pic.indexOf('-')) + "-83-111.jpg";
										 $img.attr({"src":pic});
										 $divimg.append($img);
										 $divtext=$("<div></div>");
										 $divtext.addClass("list-text");
										 $h3=$("<h3></h3>");
										 $h3.text(item.brandEnName);
										 $p=$("<p></p>");
										 text1=item.productName; 
										 text2=item.adverTitle;
										 if(text2==null){
											 $p.text(text1);
										 }else{
											 $p.text(text1+" "+text2);
										 }
										 $divlogo=$("<div></div>");
										 $divlogo.addClass("country-logo");
										 /*$i=$("<i></i>");
										 $iimg=$("<img/>");
										 var countrypic;
										 if(item.postAreaPic.length!=0){
											 countrypic=item.countryPic;
										 }
										 if(item.postAreaId==null||item.postAreaId.length==0){
											 countrypic="http://pic13.shangpin.com/shangpin/images/public/areaflag/xianggang.png";
										 }
										 $iimg.attr({"src":countrypic});
										 $i.append($iimg);
										 $divlogo.append($i)*/
										 $emtag=$("<em></em>");
									     $emtag.addClass("hot");;
										 if(item.isNewSeasonal=='1'){
											 $emtag.text("新品");
										 }
										 if(item.hotValue*1>20){
											 $emtag.text("热卖");
										 }
										 $divlogo.append($emtag);
										 $divprice=$("<div></div>");
										 $divprice.addClass("price-style");
										 $b=$("<b></b>").text("¥");
										 $strong=$("<strong></strong>");
										 //$emprice=$("<em></em>");
										 var userLv=$("#userLv").val();
										 if(item.isSupportDiscount==1){
								             if(userLv=='0002'){
								            	 $strong.text( item.sellPrice*1);
//								                 if(item.marketPrice!=null&&(item.marketPrice*1-item.sellPrice*1)>0){
//													 var price=item.marketPrice*1-item.sellPrice*1;
//													 $emprice.text('比国内便宜￥'+price);
//													 
//												 }
								                 
								             }else if(userLv=='0003'){
								            	 $strong.text( item.platinumPrice*1);
//								                 if(item.marketPrice!=null&&(item.marketPrice*1-item.platinumPrice*1)>0){
//													 var price=item.marketPrice*1-item.platinumPrice*1;
//													 $emprice.text('比国内便宜￥'+price);
//													 
//												 }
								             }else if(userLv=='0004'){
								            	 $strong.text(item.diamondPrice*1);
//								                 if(item.marketPrice!=null&&(item.marketPrice*1-item.diamondPrice*1)>0){
//													 var price=item.marketPrice*1-item.diamondPrice*1;
//													 $emprice.text('比国内便宜￥'+price);
//													 
//												 }
								             }else{
								            	 $strong.text( item.limitedPrice*1);
//								                 if(item.marketPrice!=null&&(item.marketPrice*1-item.limitedPrice*1)>0){
//													 var price=item.marketPrice*1-item.limitedPrice*1;
//													 $emprice.text('比国内便宜￥'+price);
//													 
//												 }
								             } 
								           
								         }else{
								        	 $strong.text( item.limitedPrice*1);
//								             if(item.marketPrice!=null&&(item.marketPrice*1-item.limitedPrice*1)>0){
//												 var price=item.marketPrice*1-item.limitedPrice*1;
//												 $emprice.text('比国内便宜￥'+price);
//												 
//											 }
								          
								             
								         }
										   $b.append($strong)
								             $divprice.append($b);
								             
										 //$divprice.append($emprice);
										 $divc.append($a);
										 $a.append($divimg);
										 $a.append($divtext);
										 $divtext.append($h3);
										 $divtext.append($p);
										 $divtext.append($divlogo);
										
										 $divtext.append($divprice);
										 
										 
										 $div3.append($divc);
										 
										
											
									 });
								}
								
								$ele.append($div3);
								/* $("#"+tabIndex+'_start').val(data.start);
		                          $("#"+tabIndex+'_hasMore').val(data.hasMore);	    */
								sliderHeight = $ele.find('.list-box').height();
								$("#swiperList .swiper-wrapper").height(sliderHeight);
								$("#swiperList").height(sliderHeight);
								
							},
							error : function(){
								/*alert('数据获取失败，请刷新页面');*/
								$('.loading-box').remove();
							}
						});
					}
				},2000);
			}else{
				$('.loading-box').remove();
				loadFlag = true;
			}
		}
    }
});







//头部app下载收起
$('.headApp').css('height',$('.headApp img').height());
setTimeout(function(){
	$('.headApp').css('height',0);
},3000);


//$(window).scroll(topFixed);//滑动头部导航浮层


//显示导航浮层方法
function topFixed(){	
	var scrolls = document.body.scrollTop;
	
	if($('.topFix').length){
		menuTopHeight = $('.topFix').offset().top;
	}
	if (scrolls > menuTopHeight) {
		$('.topFix section').css({
			position: "fixed",
			top:"0",
			zIndex:"999"
		});
	}else {
		$('.topFix section').css({
			position: "relative",
			top:"0",
			zIndex:"10"
		});
		   
	}
};

//显示swiper-nav-box浮层方法
function swiperNavFixed(){	
	var swiperNavHeight;
	var scrolls = document.body.scrollTop;
	var top=45;
	var checkAPP=$('#checkApp').val();
	var checkWX=$('#checkWX').val();
	if(checkAPP||checkWX){
		swiperNavHeight = $('.swiper-nav-box').offset().top;
		top=0;
	}else{
		swiperNavHeight = $('.swiper-nav-box').offset().top-45;
	}
	if (scrolls > swiperNavHeight) {
		$('#swiperNav').css({
			position: "fixed",
			top:top,
			zIndex:"998"
		});
	}else {
		$('#swiperNav').css({
			position: "relative",
			top:0,
			zIndex:"9"
		});
		   
	}
};

//滑动头部导航浮层

$(window).scroll(function(){
	topFixed();
	swiperNavFixed();
});
var documentWith=$(window).width();
/*var startx=0;
var starty=0;
var endx=0;
var endy=0;

document.addEventListener('touchstart',function(event){
	startx=event.touches[0].pageX;
	starty=event.touches[0].pageY;
})

function bodyScroll(e){
	e.preventDefault();
}

document.addEventListener('touchmove',bodyScroll, false);

document.addEventListener('touchend',function(event){
	endx=event.changedTouches[0].pageX;
	endy=event.changedTouches[0].pageY;
	
	var deltax=endx-startx;
	var deltay=endy-starty;
	
	if(Math.abs(deltax)<0.6*documentWith && Math.abs(deltay)<0.3*documentWith){
		return;
	}
	if(Math.abs(deltax)>=Math.abs(deltay)){
		swiperList.unlockSwipes();
	}else{
		swiperList.lockSwipes();
		document.removeEventListener('touchmove', bodyScroll, false);
	}
})*/


});


var ready=1;
var num = 0;
//下拉加载
function BottomLoading(){
  var loading = {
      img : '../styles/shangpin/images/201502brand/loading.gif',
      msgText : '正在加载中...',
  };
  var tabIndex= $("#tabIndex").val();
  var htmlUrlLength = $("#"+tabIndex+'_hasMore').val();
  var $eleBottom = $("#swiperList .swiper-slide");

  var loadingMsg = '<div id="loading" style="display:block"><img src=' + loading.img + ' /><p>' +loading.msgText + '</p><div>'
 
 /* if(num==0&& parseInt($("#"+tabIndex+'_start').val())>1){
       $("#"+tabIndex+'_start').val("1");
       $("#"+tabIndex+'_hasMore').val("1");
  }*/
  var start=$("#"+tabIndex+'_start').val();
  if(htmlUrlLength*1!=0){
	  if ($(window).scrollTop() + $(window).height() >= $(document).height()-50){
	         if($eleBottom.eq(tabIndex).find("#loading").length){
	        	 $eleBottom.eq(tabIndex).find("#loading").remove();
	          }
	         $eleBottom.eq(tabIndex).find(".list-box").append(loadingMsg);
	          if(ready==1){
	        	  $eleBottom.eq(tabIndex).find("#loading").show();
	                ready=0;
	                var path = getRootPath();
	                var checkAPP=$('#checkApp').val();
	                
	            	$.ajax({
						type : "POST",
						url : path+"/ephome/get/product", 
						data:{
				              firstCategoryNO:$("#firstCategoryNO").val(),
				              start:start,
				              postAreaNO:$("#postAreaNO").val()
				         },
				         
						dataType:"json",
						success : function(data) {
							$eleBottom.eq(tabIndex).find("#loading").hide();
							loadFlag = true;
							if(data.productList != null && data.productList.length > 0){
								 $.each(data.productList,function(index,item){
									 $divc=$("<div></div>");
									 $divc.addClass("content-list");
									 $a=$("<a></a>");
									 if(checkAPP){
							             hrefV = "shangPinApp://phone.shangpin/actiongodetail?productid="+item.productNo;
							           }else{
							        	   hrefV = path+"/product/detail?productNo="+item.productNo;
							           }
									 $a.addClass("clr");
									 $a.attr({"href":hrefV});
									 $divimg=$("<div></div>");
									 $divimg.addClass("list-img");
									 $img=$("<img/>");
									 var pic = item.pic.substring(0,item.pic.indexOf('-')) + "-83-111.jpg";
									 $img.attr({"src":pic});
									 $divimg.append($img);
									 $divtext=$("<div></div>");
									 $divtext.addClass("list-text");
									 $h3=$("<h3></h3>");
									 $h3.text(item.brandEnName);
									 $p=$("<p></p>");
									 text1=item.productName; 
									 text2=item.adverTitle;
									 if(text2==null){
										 $p.text(text1);
									 }else{
										 $p.text(text1+" "+text2);
									 }
									 $divlogo=$("<div></div>");
									 $divlogo.addClass("country-logo");
									 $i=$("<i></i>");
									 $iimg=$("<img/>");
									 if(item.postAreaPic.length!=0){
										 countrypic=item.countryPic;
									 }
									 if(item.postAreaId==null||item.postAreaId.length==0){
										 countrypic="http://pic13.shangpin.com/shangpin/images/public/areaflag/xianggang.png"
									 }	
									 $iimg.attr({"src":countrypic});
									 $i.append($iimg);
									 $divlogo.append($i);
									 $emtag=$("<em></em>");
									 $emtag.addClass("hot");
									 if(item.isNewSeasonal=='1'){
										 $emtag.text("新品");
									 }
									 if(item.hotValue*1>20){
										 $emtag.text("热卖");
									 }
									 $divlogo.append($emtag);
									 $divprice=$("<div></div>");
									 $divprice.addClass("price-style");
									 $b=$("<b></b>").text('¥');
									 $strong=$("<strong></strong>");
									 //$emprice=$("<em></em>");
									 var userLv=$("#userLv").val();
									 if(item.isSupportDiscount==1){
							             if(userLv=='0002'){
							            	 $strong.text( item.sellPrice*1);
//							                 if(item.marketPrice!=null&&(item.marketPrice*1-item.sellPrice*1)>0){
//												 var price=item.marketPrice*1-item.sellPrice*1;
//												 $emprice.text('比国内便宜￥'+price);
//												 
//											 }
							                 
							             }else if(userLv=='0003'){
							            	 $strong.text( item.platinumPrice*1);
//							                 if(item.marketPrice!=null&&(item.marketPrice*1-item.platinumPrice*1)>0){
//												 var price=item.marketPrice*1-item.platinumPrice*1;
//												 $emprice.text('比国内便宜￥'+price);
//												 
//											 }
							             }else if(userLv=='0004'){
							            	 $strong.text( item.diamondPrice*1);
//							                 if(item.marketPrice!=null&&(item.marketPrice*1-item.diamondPrice*1)>0){
//												 var price=item.marketPrice*1-item.diamondPrice*1;
//												 $emprice.text('比国内便宜￥'+price);
//												 
//											 }
							             }else{
							            	 $strong.text(item.limitedPrice*1);
//							                 if(item.marketPrice!=null&&(item.marketPrice*1-item.limitedPrice*1)>0){
//												 var price=item.marketPrice*1-item.limitedPrice*1;
//												 $emprice.text('比国内便宜￥'+price);
//												 
//											 }
							             } 
							          
							         }else{
							             $b.text('¥' + item.limitedPrice*1);
//							             if(item.marketPrice!=null&&(item.marketPrice*1-item.limitedPrice*1)>0){
//											 var price=item.marketPrice*1-item.limitedPrice*1;
//											 $emprice.text('比国内便宜￥'+price);
//											 
//										 }
							             
							         }
									 $b.append($strong);
							         $divprice.append($b);
									 //$divprice.append($emprice);
									 $divtext.append($divprice);
									 $divtext.append($h3);
									 $divtext.append($p);
									 $divtext.append($divlogo);
									 $a.append($divimg);
									 $a.append($divtext);
									 $divc.append($a);
									 $eleBottom.eq(tabIndex).find(".list-box").append($divc);
										
								 });
								 
							      num++; 
								  ready=1;
							}else{
								ready=1;
								 $eleBottom.eq(tabIndex).find("#loading").hide();
							}
							
							  $("#"+tabIndex+'_start').val(data.start);
	                          $("#"+tabIndex+'_hasMore').val(data.hasMore);	      
							var sliderHeight1 =  $eleBottom.eq(tabIndex).find('.list-box').height()+80;
							$("#swiperList .swiper-wrapper").height(sliderHeight1);
							$("#swiperList").height(sliderHeight1);
							
							
						},
						error : function(){
							 $eleBottom.eq(tabIndex).find("#loading").hide();
						}
					});
	                
	                
	           }else{
	        	   $eleBottom.eq(tabIndex).find("#loading").hide();
	           }
	                
	        }else{
	        	 $eleBottom.eq(tabIndex).find("#loading").hide();
	        }
  }else{
	  	if($eleBottom.eq(tabIndex).find('#loading').length){
	  		$eleBottom.eq(tabIndex).find("#loading").remove();
	    }
	  	$eleBottom.eq(tabIndex).find(".list-box").append(loadingMsg);
	  	$eleBottom.eq(tabIndex).find("#loading p").html('没有更多了');
	  	$eleBottom.eq(tabIndex).find("#loading").show();
	  	$eleBottom.eq(tabIndex).find("#loading img").hide();
     
  }
}





