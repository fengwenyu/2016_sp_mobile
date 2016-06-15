var num = 0;
var ready = 1;
var menuNavHeight=0;
var menuTopHeight=0;
if($('.topFix').length){
	menuTopHeight = $('.topFix').offset().top;
}
if($('.menu-nav').length){
	menuNavHeight = $('.menu-nav').offset().top;
}
//判断设备
var UA = window.navigator.userAgent;
var CLICK = 'click';
if (/ipad|iphone|android/.test(UA)) {
	CLICK = 'tap';
}

$(function() {
	$(".alContent").attr("style", "margin-top:0");
	//var imgHeight = $('.tabSlider-bd .hallBox a img').eq(0).height(); // 获取焦点图img高度
	//$('.tabSlider-bd .hallBox a').css('min-height', imgHeight);
	$(window).scroll(BottomLoading); // 下拉加载
	
	
	selectOrder();
	
	// 筛选按钮事件
	/*$('body').delegate('.fillBtn', CLICK, function(e) {
		e.preventDefault();
		fillBtn();
	});*/
	
	$('body').on('touchstart click','.fillBtn', function(e) {
		e.preventDefault();
		fillBtn();
	});

	// 确定按钮事件
	$('body').delegate('#finishBtn', CLICK, function(e) {
		e.preventDefault();
		filterFinish();
	});

	// 关闭按钮事件
	$('body').delegate('#filter_close', CLICK, function(e) {
		e.preventDefault();
		filterClose();
	});

	/* 筛选栏里选择选项 */
	$('body').delegate('.category-box li,.color-box li', CLICK, function(e) {
		$(this).addClass("cur").siblings().removeClass("cur");
	});

	$('body').delegate('.color-box a', CLICK, function(e) {
		$(this).parent().addClass("cur").siblings().removeClass("cur");
	});
	$('body').delegate('#sizeItem li', CLICK, function(e) {
		$(this).addClass("cur").siblings().removeClass("cur");
	});

	// 点击列表按钮切换并跳转
	$('body').delegate('#list_menu li',CLICK,function(e) {
			e.preventDefault();
			var value = $(this).attr("id");
			$(this).addClass('curr').siblings().removeClass('curr price-down');
			if(value == "treeOrder"){
				if(($(this).hasClass('price-btn')) && ($(this).hasClass('curr'))){//如果选中的是价格选项
					if($('.price-btn').hasClass('price-down')){
						$(this).removeClass('price-down');
						//$("#postArea").val("0");
						changeOrder("2");//升序
					}else{
						$(this).addClass('price-down');
						//$("#postArea").val("0");
						changeOrder("1");//降序
					}
				}
			}else if(value =="normalOrder"){//新品
				//$("#postArea").val("0");
				changeOrder("3");
			}else if(value == "hotOrder"){//销量
				//$("#postArea").val("0");
				changeOrder("5");
			}else if(value == "defaultOrder"){//默认
				//$("#postArea").val("0");
				changeOrder("");
			}/*else if(value == "abroadOrder"){//海外购
				$("#postArea").val("2");//2表示海外购，1表示国内
				changeOrder("");
			}*/
	});		    

});

// 筛选按钮
function fillBtn() {
	$("#filter_layer").show();
	$('body').css({
		'overflow' : 'hidden'
	});
	$('.alContent').css({
		'visibility' : 'hidden'
	});
	$('.app_bg').css({
		'visibility' : 'hidden'
	});
	$(window).unbind('scroll');
	$("#filter_box").attr("class", "slideIn");
	var categoryName = $("#categoryName").val();
	if(categoryName == null || categoryName == ''){
		ajaxSelect($("#defaultCategoryNo").val());
	}else{
		ajaxSelect($("#categoryNo").val());
	}
	filterboxSelect();
}

// 关闭筛选按钮
function filterClose() {
	$("body").unbind("touchmove");
	$("#filter_box").attr("class", "slideOut");
	$('.alContent').css({
		'visibility' : 'visible'
	});
	$('.app_bg').css({
		'visibility' : 'visible'
	});
	setTimeout(function() {
		$("#filter_layer").fadeOut();
	}, 600);
	
	return false;
}

// 筛选确定按钮
function filterFinish() {
	$("body").unbind("touchmove");// 解除禁止滑动事件
	$("#filter_box").attr("class", "slideOut");
	$('.alContent').css({
		'visibility' : 'visible'
	});
	$('.app_bg').css({
		'visibility' : 'visible'
	});
	setTimeout(function() {
			$('#start').val("1");
			var categoryNo = $("#categoryNo").val();
			var categoryName = $("#categoryName").val();
			if(categoryName == null || categoryName == ''){
				var defaultCategoryNo = $("#defaultCategoryNo").val();
				var defaultCategoryName = $("#defaultCategoryName").val();
				$("#categoryNo").val(defaultCategoryNo);
				$("#categoryName").val(defaultCategoryName);
			}
			$("#search_form").submit();
	}, 600);
	return false;
}


$(window).scroll(function(){
	topFixed('.menu-nav');
});


//上次的选中状态
function filterboxSelect(){
	var colorSelectFlag = "";
	//默认选中选项
	var categoryNo = $("#categoryNo").val();
	var categoryName = $("#categoryName").val();
	var brandNo = $("#brandNo").val();
	var brandName = $("#brandName").val();
	var productSize = $("#size").val();
	if(productSize.indexOf(".") > 1){
		productSize = productSize.replace(".", "_");
	}
	var primaryColorId = $("#color").val();
	var primaryColorName = $("#colorName").val();
	var price = $("#price").val();
	$(".category-box ul li").each(function(){
		var selectFlag ="";
		//var selValue = $(this).html();
		var selValue = $(this).attr("id");
		if(selValue != ""){
			//判断列表的选中状态
			switch(selValue){
				case categoryNo : 
					selectFlag = "1"; 
					$(this).addClass("cur").siblings().removeClass("cur");
					break;
				case brandNo : 
					selectFlag = "1"; 
					$(this).addClass("cur");
					break;
				case productSize : 
					selectFlag = "1"; 
					$(this).addClass("cur");
					break;
				case price : 
					selectFlag = "1"; 
					$(this).addClass("cur");
					break;
				default : 
					selectFlag ="";
					break;
			}
		}
		
	});	
	
	$(".color-box ul li").each(function(){
		//var selectFlag ="";
		var selValue = $(this).attr("id");
		if(selValue != "" && colorSelectFlag == ""){
			//判断颜色的选中状态
			switch(selValue){
				case primaryColorId : 
					colorSelectFlag = "1"; 
					$(this).addClass("cur");
					$(this).find('a').css('background',primaryColorId);
					break;
				default : 
					colorSelectFlag ="";
					break;
			}
		}
		
	});			

}
//选择条件给搜索表单中的属性赋值
//选择品类
function selectCategory(no,name,obj){
	$("#categoryNo").val(no);
	$("#categoryName").val(name);
	$("#brandNo").val("");
	$("#brandName").val("");
	$("#color").val("");
	$("#colorName").val("");
	$("#size").val("");
	$('#price').val("");
	$("#priceItem li").each(function(){
		$(this).removeClass("cur");
	});	
	$(obj).addClass("cur").siblings().removeClass("cur");
	ajaxSelect(no);
}

//ajax请求获取该品类下的品牌及尺码
function ajaxSelect(no){
	var path = getRootPath();
	$.ajax({
		url : path + "/category/select/",
		type : "post",
		data : {
			"categoryNo" : no,
			"postArea":$("#postArea").val()
		},
		dataType : "json",
		async : false,
		success : function(data){
			var brands = data.brandList;
			var sizes = data.sizeList;
			var colors = data.colorList;
			//品牌
			if(brands != null && brands.length > 0){
				$(".brandList").remove();
				$ul = $("<ul></ul>");
				$.each(brands,function(index,item){
					$li_html = $("<li id='" + item.id + "'>" + item.nameEN + "</li>");
					var va = "selectBrand('"+ item.id + "')";
					$li_html.attr("onclick", va);
					$ul.append($li_html); 
				});
				$div = $("<div class='category-box brandList'></div>");
				$h3 = $("<h3>品牌</h3>");
				$div.append($h3).append($ul);
				$("#brandList").after($div);
			}else{
				$(".brandList").remove();
			}
			//尺码
			if(sizes != null && sizes.length > 0){
				$(".sizeList").remove();
				$ul = $("<ul></ul>");
				$.each(sizes,function(index,item){
					var sizeId = item.id;
					if(sizeId.indexOf(".") > 1){
						sizeId = sizeId.replace(".", "_");
					}
					$li_html = $("<li id='" + sizeId + "' >" + item.id + "</li>");
					var va = "selectSize('"+ sizeId  +"')";
					$li_html.attr("onclick", va);
					$ul.append($li_html); 
				});
				$div = $("<div class='category-box sizeList'></div>");
				$h3 = $("<h3>尺码</h3>");
				$div.append($h3).append($ul);
				$("#sizeList").after($div);
			}else{
				$(".sizeList").remove();
			}
			
			//颜色
			if(colors != null && colors.length > 0){
				var path = getRootPath();
				$(".colorList").remove();
				$ul = $("<ul></ul>");
				$.each(colors,function(index,item){
					$li_html = $("<li id='" + item.id + "'></li>");
					var va = "selectColor('"+ item.id  +"' ,'" + item.name + "')";
					$li_html.attr("onclick", va);
					$i_html = $("<i><img src='" + path + "/styles/shangpin/images/201502brand/color_cur.png'/></i>");
					if(item.rgb == "#ffffff" || item.rgb == "#FFFFFF"){
						$a_html = $("<a href='javascript:;' class='white-color' style='background:"+ item.rgb +"'></a>");
					}else if(item.rgb == null || item.rgb == ''){
						$a_html = $("<a href='javascript:;' class='all-color'><img src='../../styles/shangpin/images/201502brand/other_color.jpg' /></a>");
					}else{
						$a_html = $("<a href='javascript:;' class='black-color' style='background:"+ item.rgb +"'></a>");
					}
					$span_html = $("<span id='"+ item.id  +"'>" + item.name + "</span>");
					$li_html.append($i_html).append($a_html).append($span_html);
					$ul.append($li_html);
				});
				$div = $("<div class='color-box colorList'></div>");
				$h3 = $("<h3>颜色</h3>");
				$div.append($h3).append($ul);
				$("#colorList").after($div);
			}else{
				$(".colorList").remove();
			}
		}
	});
}

//选择品牌
function selectBrand(no){
	$("#" + no).addClass("cur").siblings().removeClass("cur");
	$("#brandNo").val(no);
	//$("#brandName").val(name);
}

//选择尺码
function selectSize(no){
	$("#" + no).addClass("cur").siblings().removeClass("cur");
	if(no.indexOf("_") > 1){
		no = no.replace("_",".");
	}
	$("#size").val(no);
}

//选择颜色
function selectColor(no,name){
	$("#" + no).addClass("cur").siblings().removeClass("cur");
	if(no == "all"){
		$("#color").val("");
		$("#colorName").val("");
	}else{
		$("#color").val(no);
		$("#colorName").val(name);
	}
}

//选择价格
function selectPrice(no){
	$("#price").val(no);
}


function selectOrder(){
	$("#price").val($("#oldPrice").val());
	$("#size").val($("#oldSize").val());	
	$("#color").val($("#oldColor").val());
	$("#colorName").val($("#oldColorName").val());
	$("#categoryNo").val($("#oldCategoryNo").val());
	$("#categoryName").val($("#oldCategoryName").val());
	$("#brandNo").val($("#oldBrandNo").val());
	$("#brandName").val($("#oldBrandName").val());
	$("#gender").val($("#oldGender").val());
	$("#order").val($("#oldOrder").val());
	$("#postArea").val($("#oldPostArea").val());
	$("#start").val($("#oldStart").val());
	$("#pageNo").val($("#oldPageNo").val());
	var order = $("#order").val();
	if(order=="1"){// 从高到低
		$("#treeOrder").addClass("price-btn curr price-down").siblings().removeClass("curr");
	}else if(order=="2"){// 从低到高
		$("#treeOrder").addClass("price-btn curr ").siblings().removeClass("curr");
	
	}else if(order=="5"){//销量
		$("#hotOrder").addClass("curr").siblings().removeClass("curr");
	}else if(order=="3"){//新品
		$("#normalOrder").addClass("curr").siblings().removeClass("curr");
	}else if(order=="" && $("#postArea").val() == "2"){//海外购
		$("#abroadOrder").addClass("curr").siblings().removeClass("curr");
	}else if(order=="" && $("#postArea").val() == "0"){//默认
		$("#defaultOrder").addClass("curr").siblings().removeClass("curr");
	}
}

//排序
function changeOrder(order){
	$('#start').val("1");
	$('#order').val(order);
	$('#search_form').submit();
}
function BottomLoading(){
	var path = getRootPath();
	var loading = {
		img : path + '/styles/shangpin/images/201502brand/loading.gif',
		msgText : '正在加载中...',
	};
	var htmlUrl = [
		'/data/2015brand/list1.html',
		'/data/2015brand/list2.html',
		'/data/2015brand/list3.html',

	];
	var htmlUrlLength = htmlUrl.length;
	var addSelector = '.prod_list';
	var loadingMsg = '<div id="loading" style="display:block"><img src=' + loading.img + ' /><p>' +loading.msgText + '</p><div>'
	var totalPage = $("#totalPage").val();
	if(num < totalPage){
		if ($(window).scrollTop() + $(window).height() >= $(document).height()-50){
			if(!$('body').find('#loading').length){
				$('.list-box').append(loadingMsg);
			}
			if(ready==1){
				var start = parseInt($('#start').val()) + 1;
				var pageNo = parseInt($("#pageNo").val()) + 1;
				$('#start').val(start);
				$("#pageNo").val(pageNo);
				  $('#loading').show();
				  ready=0;
				  var searchConditions = $("#search_form").serialize();
				  $.ajax({
					  type : "POST",
					 // url : htmlUrl[num], 
					  url : path + "/category/product/list/more",
					  data : searchConditions,
					  dataType:"json",
					  success : function(data) {
						  $('#loading').hide();
						  var hasMore = data.hasMore;
						  if(data.searchResult.productList != null && data.searchResult.productList.length > 0){
							  $.each(data.searchResult.productList,function(index,item){
								  var pic = item.pic.substring(0,item.pic.indexOf('-')) + "-320-426.jpg";
									$div = $("<div></div>");
									$div.addClass("list_box");
									$a = $("<a></a>");
									$i=$("<i></i>");
									$i1=$("<i></i>");
									
									if(item.postArea=="2" && item.countryPic!=''){
										$img = $("<img/>");
										$img.attr({"src":item.countryPic});
										$i1.addClass("item-country");
										$i1.append($img);
										$a.append($i1);
									}
									
									if(item.productStatus=="1"){
										$i.addClass("item-status").text("售罄");
										$a.append($i);
									}else if(item.productStatus=="2"){
										$i.addClass("item-status new-item").text("新品");
										$a.append($i);
									}
									if(item.promoLogo!=null && item.promoLogo!=''){
										var $ati=$("<i></i>");
										$ati.addClass("item-activity");
										var $atimg= $("<img/>");
										$atimg.attr({"src":item.promoLogo});
										$ati.append($atimg);
										$a.append($ati);
									}
									if(item.expressLogo!=null && item.expressLogo!=''){
										var $eti=$("<i></i>");
										$eti.addClass("overseas-symbol");
										var $etimg= $("<img/>");
										$etimg.attr({"src":item.expressLogo});
										$eti.append($etimg);
										$a.append($eti);
									}
									if($("#_isapp").val()){
										hrefV = "shangPinApp://phone.shangpin/actiongodetail?productid="+item.productId;
								  	}else{
								  		hrefV =path+ "/product/detail?productNo="+item.productId + "&picNo=" + item.picNo;
								  		var categoryName=$("#categoryName").val();
								  		var onclick="_smq.push(['custom',"+ item.productId+","+categoryName+","+item.productName+"]);";
										$a.attr({"onclick":onclick});
								  	}
									
									$a.attr({"href":hrefV});
									$img = $("<img/>");
									$img.attr({"src":pic});
									$a.append($img);
									$div.append($a);
									
									$div1 = $("<div></div>");
									$div1.addClass("li_text");
									$h5 = $("<h5></h5>").text(item.brandNameEN);
									$p = $("<p></p>").text(item.prefix+item.productName+item.suffix);
									$span=$("<span></span>")
									$span.addClass("item-detail");
									
									$strong=$("<strong></strong>").attr({"style":"color:"+item.priceColor});
									$strong.addClass("refer-price");
									$b=$("<b></b>").text(item.priceTitle);
									$strong.append($b);
									if(item.status == '0001'|| item.status == '000100'){
										$strong.text('￥' + item.promotionPrice);
										$span.append($strong);
									}else{
										if(item.isSupportDiscount==1){
											if(data.userLv=='0002'){
												$strong.text('￥' + item.goldPrice);
												$span.append($strong);
											}else if(data.userLv=='0003'){
												$strong.text('￥' + item.platinumPrice);
												$span.append($strong);
											}else if(data.userLv=='0004'){
												$strong.text('￥' + item.diamondPrice);
												$span.append($strong);
											}else{
											
												$strong.text('￥' + item.limitedPrice);
												$span.append($strong);
											} 
										}else{
											$strong.text('￥' + item.limitedPrice);
											$span.append($strong);
										}
										
									}
									
									$em=$("<em></em>").addClass("promotion-price").attr({"style":"color:"+item.descColor}).text(item.priceDesc);
									$span.append($em);
									if((item.collections!=null && item.collections!="-1") || (item.comments!='' && item.comments!='-1')){
									$div2 = $("<div></div>");
									$div2.addClass("item-fame");
									if(item.collections!=null || item.collections!="-1"){
										$cspan=$("<span></span>");
										$cspan.addClass("fame-views");
										$ci=$("<i></i>")
										$ci.addClass("fame-views-icon");
										$cimg = $("<img/>");
										$cimg.attr({"src":path+"/styles/shangpin/images/home/icon13.png"});
										$cem=$("<em></em>");
										$cem.text(item.collections);
										$cem.addClass("fame-views-num");
										$ci.append($cimg);
										$cspan.append($ci);
										$cspan.append($cem);
										$div2.append($cspan);
									}
									if(item.comments!=null || item.comments!="-1"){
										$mspan=$("<span></span>");
										$mspan.addClass("fame-comments");
										$mi=$("<i></i>")
										$mi.addClass("fame-comments-icon");
										$mimg = $("<img/>");
										$mimg.attr({"src":path+"/styles/shangpin/images/home/icon12.png"});
										$mem=$("<em></em>");
										$mem.text(item.comments);
										$mem.addClass("fame-comments-num");
										$mi.append($mimg);
										$mspan.append($mi);
										$mspan.append($mem);
										$div2.append($mspan);
									}
									
									$span.append($div2);
								}
								$div1.append($h5);
								$div1.append($p);
								$div1.append($span);
								$div.append($div1);
								$("#prodList").append($div);
							  })
						  }
						num++;
						ready=1;
						if(hasMore == 0){
							$('#loading').html('没有更多了');
							$('#loading').show();
							$('#loading img').hide();
						}else{
							ready=1;
						}
					  },
					  error : function(){
						alert('数据获取失败，请刷新页面');
					  }
				  });
					
		   }
	  }
   }
}



