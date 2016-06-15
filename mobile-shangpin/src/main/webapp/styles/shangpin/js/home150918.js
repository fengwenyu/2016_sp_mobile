$(function(){
	var rootPath=getRootPath();

var UA = window.navigator.userAgent;
var CLICK = 'click';
if(/ipad|iphone|android/.test(UA)){
	CLICK = 'tap';
}
//导航图轮播
var mySwiper = new Swiper('#swiper-container1',{
	loop:true,       //循环切换
	autoplay: 2000,  //自动播放
	autoplayDisableOnInteraction : false, //swiper之后自动切换不会停止
	pagination:'.swiper-pagination', //分页
	paginationClickable: true,
});
var swiper1 = new Swiper('#swiper-container2', {
	slidesPerView: 'auto',
	spaceBetween: 2,
});
var swiper2 = new Swiper('#swiper-container3', {
	slidesPerView: 'auto',
	spaceBetween: 2,
});
//分类导航滑动
var swiperNav = new Swiper('#swiperNav', {
	slidesPerView: 'auto',
});
var $container = $('#masonry');
$container.imagesLoaded(function() {
	$container.masonry({
			itemSelector: '.waterfull-list',
			columnWidth: '.waterfull-list',
			percentPosition: true
		});
});
$container.infinitescroll({
	state: {
	  currPage: 0 // The number of the first comment page loaded, you can generate this number dynamically to save changing it manually
	},        
	pathParse: function(path,nextPage){
	     path = [rootPath+'/index/exclusive/recommend/more?pageIndex=',''];
	     return path;
	},
	navSelector: "#navigation", //导航的选择器，会被隐藏
	nextSelector: "#navigation a", //包含下一页链接的选择器
	itemSelector: ".waterfull-list", //你将要取回的选项(内容块)
	debug: false, //启用调试信息
	animate: false, //当有新数据加载进来的时候，页面是否有动画效果，默认没有
	extraScrollPx: 150, //滚动条距离底部多少像素的时候开始加载，默认150
	bufferPx: 40, //载入信息的显示时间，时间越大，载入信息显示时间越短
	errorCallback: function() {
		alert('error');
	}, //当出错的时候，比如404页面的时候执行的函数
	localMode: true, //是否允许载入具有相同函数的页面，默认为false
	dataType: 'json',//可以是json
	appendCallback: false,
	template: function(data) {
		return data;
	},
	loading: {
		finishedMsg: '没有更多内容了',
	}
}, function(json,newElems) {
	//程序执行完的回调函数
	var $newElems =  parseData(json);
	$newElems.imagesLoaded(function () {
	/*	setTimeout(function(){*/
			$container.append($newElems).masonry("appended", $newElems, true);
		/*},500)*/
    });
	
});
$(window).scroll(function(){
	topFixed('.topFix');
	topFixed('.swiper-nav-box');
});
//头部app下载收起
$('.headApp').css('height',$('.headApp img').height());
setTimeout(function(){
	$('.headApp').css('height',0);
},3000);
});
var ready=1;
function parseData(data){
	if(ready==1){
		ready=0;
		var path = getRootPath();
		$html=$("<div></div>");
		var len=0
		if(data.recProductItem.list.length>0){
			len=data.recProductItem.list.length;
		}
		$("#hasMore").val(data.hasMore);
		$("#pageIndex").val(data.pageIndex);
		var checkAPP=$('#checkApp').val()
		$.each(data.recProductItem.list,function(index,item){
			$div= $("<div></div>");
			$div.addClass("waterfull-list");
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
			$a.append($img);
			$p = $("<p></p>").text(item.productName);
			$h5 = $("<h5></h5>").text(item.brandNameEN);
			$div1 = $("<div></div>");
			$div1.addClass("li_text");
			$div1.append($h5).append($p);
			$span=$("<span></span>").addClass("item-detail");
			$strong=$("<strong></strong>").addClass("refer-price");
			if(item.status == '0001'|| item.status == '000100'){
				$strong.text('￥' + item.promotionPrice);
			}else{
				if(item.isSupportDiscount==1){
					if(data.userLv=='0002'){
						$strong.text('￥' + item.goldPrice);
					}else if(data.userLv=='0003'){
						$strong.text('￥' + item.platinumPrice);
					}else if(data.userLv=='0004'){
						$strong.text('￥' + item.diamondPrice);
					}else{
						$strong.text('￥' + item.limitedPrice);
					} 
				}else{
					$strong.text('￥' + item.limitedPrice);
				}
			}
			$span.append($strong);
			
			$div2 = $("<div></div>");
			$div2.addClass("item-fame");
			$span1=$("<span></span>").addClass("fame-views");
			if(item.collections*1>0){
				$i1=$("<i></i>").addClass("fame-views-icon");
				$img1=$("<img/>");
				var pic1=path+"/styles/shangpin/images/home/icon13.png"
				$img1.attr({"src":pic1});
				$em1=$("<em></em>").addClass("fame-views-num").text(item.collections);
				$i1.append($img1);
				$i1.append($em1);
				$span1.append($i1);
				$div2.append($span1)
			}
			$span2=$("<span></span>").addClass("fame-comments");
			if(item.comments*1>0){
				$i2=$("<i></i>").addClass("fame-comments-icon");
				$img2=$("<img/>");
				var pic2=path+"/styles/shangpin/images/home/icon12.png"
				$img2.attr({"src":pic2});
				$i2.append($img2)
				$em2=$("<em></em>").addClass("fame-comments-num").text(item.comments);
				$i2.append($img2);
				$i2.append($em2);
				$span2.append($i2);
				$div2.append($span2)
			}
			$span.append($div2);
			$div1.append($span);
			$a.append($div1);
			$div.append($a);
			$html.append($div);
		})
		ready=1;
		return $html;
	}
}