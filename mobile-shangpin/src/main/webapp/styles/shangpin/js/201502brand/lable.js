var num = 0;
var ready = 1;
var menuNavHeight=0;
var path = getRootPath();
$(function() {
	var $overlay = $('#overlay');

	$(".alContent").attr("style", "margin-top:0");
	var imgHeight = $('.tabSlider-bd .hallBox a img').eq(0).height(); // 获取焦点图img高度
	$('.tabSlider-bd .hallBox a').css('min-height', imgHeight);

	$(window).scroll(BottomLoading); // 下拉加载

	// 判断设备
	var UA = window.navigator.userAgent;
	var CLICK = 'click';
	if (/ipad|iphone|android/.test(UA)) {
		CLICK = 'tap';
	}
	if($('.menu-nav').length){
	    menuNavHeight = $('.menu-nav').offset().top;
	}

	selectOrder();

	/* 滑块列表 */
	app.TabSlider('#tabSlider1');
	app.TabSlider('#tabSlider2');

	// 特卖未开启弹出层
	$('body').delegate('.no_open', CLICK, function(e) {
		e.preventDefault();
		jShare('还没开始呢，20:00 再来吧！', "", "");
		return false;
	});


	// 筛选按钮事件
	$('body').delegate('.fillBtn', CLICK, function(e) {
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

/*	 筛选栏里选择选项 
	$('body').delegate('.category-box li,.color-box li', CLICK, function(e) {
		//$(this).addClass("cur").siblings().removeClass("cur");
	});*/

	$('body').delegate('.color-box a', CLICK, function(e) {
		$(this).parent().addClass("cur").siblings().removeClass("cur");
	});

	// 点击列表按钮切换并跳转
	$('body').delegate(
			'#list_menu li',
			CLICK,
			function(e) {
				e.preventDefault();
				if ($(this).hasClass('price-btn')) {
					if (!$(this).hasClass('curr')) {
						$(this).addClass('curr');
						$("#postArea").val("1");
						orderChange("2");
					} else if ($(this).hasClass('curr')
							&& $('.price-btn').hasClass('price-down')) {
						$(this).removeClass('price-down');
						$("#postArea").val("1");
						orderChange("2");
					} else {
						$(this).addClass('price-down');
						$("#postArea").val("1");
						orderChange("1");// 从高到低
					}
				}

				$(this).addClass('curr').siblings().removeClass(
						'curr price-down');

			})

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
	$('#filters').val("");
	$('#search_form').submit();
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
		$("#filter_layer").fadeOut();
		$("#start").val("1");
		$('#search_form').submit();
		
	}, 600);
	return false;
}

// 优惠劵弹层
function modalHidden($ele) {
	$ele.removeClass('modal-in');
	$ele.one('webkitTransitionEnd', function() {
		$ele.css({
			"display" : "none"
		});
		 $('#overlay').removeClass('active');
	});
}

$(window).scroll(function(){
	topFixed('.topFix');
	topFixed('.menu-nav');
});
function filterboxSelect(){
		var flag=0
		var dName="";
		var dValue="";
		$("#attributesItemFirst .category-box ul").each(function() {
			var name=$(this).attr("id");
			dname=name;
			$("#"+name+" li").each(function(n,v){
				if(n==0){
					dValue=$(this).attr("id");
				}
				if(getSelected(name,$(this).attr("id"),null)=='1'){
					$(this).addClass("cur")
					 flag=1
				}
			});
			
		});
		if(flag==0){
			$("#attributesItemFirst .category-box ul .first_yes").addClass("cur");
			getSearchConditions("1")
			$("#filters").val(dname+"_"+dValue);
		}
		$("#attributesItemNotf .category-box ul").each(function() {
			var name=$(this).attr("id");
			if(name!=null){
				$("#"+name+" li").each(function(n,v){
					if(getSelected(name,$(this).attr("id"),null)=='1'){
						$(this).addClass("cur")
					}
				});
			}
			
		});
	
		$("#filtratesAll ul").each(function() {
			var name=$(this).attr("id");
			$("#"+name+" li").each(function(n,v){
				if(getSelected(name,$(this).attr("id"),null)=='1'){
					$(this).addClass("cur")
				}
			});
			
		});
		
		if(flag==1){
			getSearchConditions("1")
		}
		
		
}
	

function selectOrder() {
	
	$("#filters").val($("#oldFilters").val());
	$("#type").val($("#oldType").val());	
	$("#order").val($("#oldOrder").val());
	$("#tagId").val($("#oldTagId").val());
	$("#start").val($("#oldStart").val());
	var order = $("#order").val();
	if (order == "1") {// 从高到低
		$("#orderThree").addClass("price-btn curr price-down");
	} else if (order == "2") {// 从低到高
		$("#orderThree").addClass("price-btn curr ");
	}else if(order=="5"){//销量
        $("#orderTwo").addClass("curr");
    }else if(order=="3"){//新品
        $("#orderOne").addClass("curr");
   /* }else if(order=="" && $("#postArea").val() == "2"){//海外购
        $("#abroadOrder").addClass("curr");*/
    }else if(order==""){//默认
        $("#defaultOrder").addClass("curr");
    }
}

//下拉加载
function BottomLoading(){
		var path = getRootPath();
		var loading = {
			img : path + '/styles/shangpin/images/201502brand/loading.gif',
			msgText : '正在加载中...',
		};
		
		var htmlUrlLength = $("#hasPageNum").val();
	
		var addSelector = '.prod_list';
		var loadingMsg = '<div id="loading" style="display:block"><img src=' + loading.img + ' /><p>' +loading.msgText + '</p><div>'
		
		if(num==0&& parseInt($('#start').val())>1){
			 $('#start').val("1");
		}
		if(num < htmlUrlLength){
			
			if ($(window).scrollTop() + $(window).height() >= $(document).height()-50){
				if(!$('body').find('#loading').length){
					$('.list-box').append(loadingMsg);
				}
				if(ready==1){
					  $('#loading').show();
					  ready=0;
					  var path = getRootPath();
					  var checkAPP=$('#checkApp').val()
					  var searchConditions = $('#search_form').serialize();
					  var tagId = $("#tagId").val();
					  $.ajax({
						 
						  url : path+"/lable/product/list/more?tagId="+tagId ,
						  data:searchConditions,
						  dataType:"json",
						  success : function(data) {
							$('#loading').hide();
							//var len = data.searchResult.productList.length;
							if( data.searchResult.productList!=null&&data.searchResult.productList.length > 0){
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
									if(checkAPP){
										hrefV = "shangPinApp://phone.shangpin/actiongodetail?productid="+item.productId;
								  	}else{
								  		hrefV =path+ "/product/detail?productNo="+item.productId + "&picNo=" + item.picNo;
								  		var categoryName=$("#categoryName").val();
								  		var onclick="_smq.push(['custom',"+item.productId+","+categoryName+","+item.productName+"]);";
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
								
								$(addSelector).append($div);
								});
							
								$('#start').val(data.start);
									num++;
								ready=1;
							}
							if(num == htmlUrlLength){
								$('#loading').html('没有更多了');
								$('#loading').show();
								$('#loading img').hide();
							}
						  },
						  error : function(){
//							alert('数据获取失败，请刷新页面');
						  }
					  });
						
			   }else{
					$('#loading img').hide();
			   }
		  }else{
				$('#loading img').hide();
		  }
	   }else{
			$('#loading img').hide();
	   }
	}



/* ============== 搜索处理方法 start ============== */
function searchKey(key, value) {
	if (key == "color") {
		var arr = new String(value).split("-");
		$('#color').val(arr[0]);
		$('#colorName').val(arr[1]);

	} else if (key == "size") {
		$('#size').val(value);
	} else if (key == "price") {
		if (value == '10000') {
			$("#price").val("10000-");
		} else {
			$('#price').val(value);
		}
	}
}


function orderChange(order) {
	$('#start').val("1");
	if(order == "9"){
		$("#order").val("");
		$("#postArea").val("2");//海外
	}else{
		$('#order').val(order);
		$("#postArea").val("0");//全部
	}
	$('#search_form').submit();
//	var search=getSearchParameter();
//	window.location.href=path+"/subject/product/list?"+search;
	$('.topBack').removeClass('topbg');
}
function searchFilter(id,name,type){
	if(type=='0'){
		$("#filters").val("");
		$("#attributesItemNotf .category-box ul").each(function() {
			var name=$(this).attr("id");
			$("#"+name+" li").each(function(n,v){
				$(this).removeClass("cur")
			});
			
		});
		$("#filtratesAll .category-box").each(function() {
			$("li").each(function(n,v){
				$(this).removeClass("cur")
			});
			
		});
	}
/*	var filter=$("#filters").val();
	var flag=0;
	if(flag==0){
		filter=filter+name+"_"+id;
		flag=1
	}else{
		filter=filter+"|"+name+"_"+id;
	}
	
	$("#filters").val(filter);*/
	$("#"+id).addClass("cur").siblings().removeClass("cur");
	$("#filters").val(getFilter('1'));
	
	getSearchConditions(type);

}
function getSearchConditions(type){
	if(type!='2'){
		var filter=getFilter('2');
	  	var path = getRootPath();
			$.ajax({
				url : path+"/lable/search/conditions" ,
			  	data:{
				  "filters":filter,
				  "tagId":$("#tagId").val()
				},
		  	dataType:"json",
		  	success : function(data) {
		  		var len=0;
		  		var len2=0;
		  		var len3=0;
		  		var len4=0;
		  		if(data.searchResult.sizeList!=null){
		  			len = data.searchResult.sizeList.length;
		  		}
		  		if(data.searchResult.colorList!=null){
		  			len2 = data.searchResult.colorList.length;
		  		}
		  		if(data.searchResult.brandList!=null){
		  			 len3 = data.searchResult.brandList.length;
		  		}
		  		if(data.searchResult.postAreaList!=null){
		  			 len3 = data.searchResult.postAreaList.length;
		  		}
				var hasMore = data.hasMore;
				$ul = $("<ul></ul>").attr({"id":'size'});
				$ul2 = $("<ul></ul>").attr({"id":'color'});
				$ul3= $("<ul></ul>").attr({"id":'brand'});
				$ul4= $("<ul></ul>").attr({"id":'postArea'});
				if(len > 0){
					$('#sizeItem ul').remove();
					$('#sizeItem').show();
					$.each(data.searchResult.sizeList,function(index,item){
						$li = $("<li></li>");
						var onclick="searchFilter("+"'"+item.id+"'"+','+"'size',"+"'"+'2'+"'"+")";
						$li.attr({"id":item.id,"onclick":onclick});
						if(type!='0'){
							if(getSelected('size',item.id,null)=='1'){
								$li.addClass("cur")
							}
						}
						$li.text(item.name);
						$ul.append($li);
					});
				}else{
					$('#sizeItem').hide();
				
				}
				if(len2 > 0){
					$('#colorItem ul').remove();
					$('#colorItem').show();
					$.each(data.searchResult.colorList,function(index1,item2){
						$li2 = $("<li></li>");
						$i= $("<i></i>");
						$img = $("<img/>");
						$img.attr({"src":path+"/styles/shangpin/images/201502brand/color_cur.png"});
						$i.append($img);
						$a = $("<a></a>");
						var hrefV="javascript:searchFilter("+"'"+item2.id+"'"+","+'color'+"'"+")";
						$a.attr({"href":hrefV});
						if(item2.rgb == "#ffffff" || item2.rgb == "#FFFFFF"){
							$a.addClass("white-color");
							$a.attr({"style":"background: "+item2.rgb});     
						}else if(item2.rgb == null || item2.rgb == ''){
							$a.append("<img src='../../styles/shangpin/images/201502brand/other_color.jpg' />"); 
						}else{
							$a.attr({"style":"background: "+item2.rgb});      
						}
						
						$span=$("<span></span>")
						$span.text(item2.name);
						$li2.attr({"id":item2.id});
						if(type!='0'){
							if(getSelected('color',item2.id,null)=='1'){
								$li2.addClass("cur")
							}
						}
						$li2.append($i);
						$li2.append($a);
						$li2.append($span);
						$ul2.append($li2);
					});
				}else{
					$('#colorItem').hide();
				}
				if(len3 > 0){
					$('#brandItem ul').remove();
					$('#brandItem').show();
					$.each(data.searchResult.brandList,function(index3,item3){
						$li = $("<li></li>");
						var onclick="searchFilter("+"'"+item3.id+"'"+','+"'brand',"+"'"+'1'+"'"+")";
						$li.attr({"id":item3.id,"onclick":onclick});
						if(type!='0'){
							if(getSelected('brand',item3.id,null)=='1'){
								$li.addClass("cur")
							}
						}
						$li.text(item3.nameEN);
						$ul3.append($li);
					});
				}else{
					$('#brandItem').hide();
				
				}
				if(len4 > 0){
					$('#postAreaItem ul').remove();
					$('#postAreaItem').show();
					$.each(data.searchResult.postAreaList,function(index4,item4){
						$li = $("<li></li>");
						var onclick="searchFilter("+"'"+item4.id+"'"+','+"'brand',"+"'"+'1'+"'"+")";
						$li.attr({"id":item4.id,"onclick":onclick});
						if(type!='0'){
							if(getSelected('postArea',item4.id,null)=='1'){
								$li.addClass("cur")
							}
						}
						if(item4.id=='1'){
							$li.text("国内");
						}else{
							$li.text("海外");
						}
						
						$ul4.append($li);
					});
				}else{
					$('#postAreaItem').hide();
				
				}
				$('#sizeItem').append($ul);
				$('#colorItem').append($ul2);
				$('#brandItem').append($ul3);
				$('#postAreaItem').append($ul4);
		  	}
		});
	}
}
/*function getSearchConditions(type){
		if(type!='2'){
			var filter=getFilter('2');
			var path = getRootPath();
			$.ajax({
				url : path+"/lable/search/conditions" ,
			  	data:{
				  "filters":filter,
				  "tagId":$("#tagId").val()
				},
			  	dataType:"json",
			  	success : function(data) {
			  		console.log(data);
			  		var len=0;
			  		var len2=0;
			  		if(data.searchResult!=null&&data.searchResult.attributes!=null){
			  			len = data.searchResult.attributes.length;
			  		}
			  		if(data.searchResult!=null&&data.searchResult.filtrates!=null){
			  			len2 = data.searchResult.filtrates.length;
			  		}
					$ul = $("<ul></ul>");
					if(len > 0){
						$('#attributesItemNotf ul').remove();
						$('#attributesItemNotf').show();
						$.each(data.searchResult.attributes,function(index,item){
							if(item.name!=$("#attributesItemFirst ul").attr("id")){
								$.each(item.value,function(n,v){
									$ul.attr({"id":item.name})
									$li = $("<li></li>");
									var onclick="searchFilter("+"'"+v.id+"'"+','+"'"+item.name+"',"+"'"+'1'+"'"+")";
									$li.attr({"id":v.id,"onclick":onclick});
									if(type!='0'){
										if(getSelected(item.name,v.id,null)=='1'){
											$li.addClass("cur")
										}
									}
									
									$li.text(v.name);
									$ul.append($li);
								});
							}
							
						});
					}else{
						$('#attributesItemNotf').hide();
					}
					if(len2 > 0){
						$('#filtratesAll').children().remove();
						$.each(data.searchResult.filtrates,function(index1,item1){
							if(item1.value!=null&&item1.value.length>0){
								$div=$("<div></div>").addClass("category-box");
								$h3=$("<h3></h3>").text(item1.desc);
								$div.append($h3);
								$ul2 = $("<ul></ul>");
								$ul2.attr({"id":item1.name})
								$.each(item1.value,function(n1,v1){
									$li = $("<li></li>");
									var onclick="searchFilter("+"'"+v1.id+"'"+','+"'"+item1.name+"',"+"'"+'2'+"'"+")";
									$li.attr({"id":v1.id,"onclick":onclick});
									if(type!='0'){
										if(getSelected(item1.name,v1.id,null)=='1'){
											$li.addClass("cur")
										}
									}
									$li.text(v1.name);
									$ul2.append($li);
									$div.append($ul2);
								});
								$('#filtratesAll').append($div);
							}
							
						});
					}else{
						$('#filterItem').hide();
					}
					$('#attributesItemNotf .category-box').append($ul);
					
			  	}
		});
		}
		
} */
 function getSearchParameter(){
	 var searchConditions="";
	 var iFlag=0;
	 var spe="&";
	 var spq="=";
	 $("#hiddenValue input").each(function(i){
	 	var nName=$("#hiddenValue input").eq(i).attr("id");
	 	var nValue=$("#hiddenValue input").eq(i).val();
	 	
	 	if(nValue!=null&&nValue!=""){
	 		if(iFlag==0){
	 			searchConditions=searchConditions+nName+spq+nValue;
	 			iFlag=1;
	 		}else{
	 			searchConditions=searchConditions+spe+nName+spq+nValue;
	 		}
	 	}
	 	
	 })
	 return searchConditions;
 }
 function getFilter(ntype){
		var filter="";
		var flag=0;
		$("#attributesItemFirst .category-box ul").each(function() {
			var name=$(this).attr("id");
			$("#"+name+" li").each(function(n,v){
				if($(this).hasClass('cur')){
					$("#")
					if(flag==0){
						filter=filter+name+"_";
						flag=1
					}else{
						filter=filter+"|"+name+"_";
					}
					filter=filter+$(this).attr("id");
					
				}
			});
			
		});
		$("#attributesItemNotf .category-box ul").each(function() {
			var name=$(this).attr("id");
			$("#"+name+" li").each(function(n,v){
				if($(this).hasClass('cur')){
					if(flag==0){
						filter=filter+name+"_";
						flag=1
					}else{
						filter=filter+"|"+name+"_";
					}
					filter=filter+$(this).attr("id");
				}
			});
			
		});
		if(ntype=='1'){
			$("#filtratesAll ul").each(function() {
				var name=$(this).attr("id");
				$("#"+name+" li").each(function(n,v){
					if($(this).hasClass('cur')){
						if(flag==0){
							filter=filter+name+"_";
							flag=1
						}else{
							filter=filter+"|"+name+"_";
						}
						filter=filter+$(this).attr("id");
					}
				});
				
			});
			
		}
		return filter;
		
 }
 function getSelected(name,id,searchParam){
	 if(searchParam==null){
		 var filter=$("#filters").val();
		 var filterArray=filter.split("|");
		 for (var i = 0; i < filterArray.length; i++) {  
			   if(filterArray[i].indexOf(name)>-1){
				  if( filterArray[i].split("_")[1]==id ){
					  return "1"
				  }
			   }
		 }  
	 }else{
		 if(name.indexOf("brand")>-1){
			 if(searchParam.brandId==id){
				 return "1"
			 }
		}
		if(name.indexOf("category")>-1){
			 if(searchParam.categoryId==id){
				 return "1"
			 }
		}
		if(name.indexOf("size")>-1){
			 if(searchParam.sizeId==id){
				 return "1"
			 }
		}
		if(name.indexOf("color")>-1){
			 if(searchParam.colorId==id){
				 return "1"
			 }
		}
		if(name.indexOf("price")>-1){
			 if(searchParam.priceId==id){
				 return "1"
			 }
		}
		if(name.indexOf("attribute")>-1){
			 if(searchParam.attributeId==id){
				 return "1"
			 }
		}
		if(name.indexOf("postArea")>-1){
			 if(searchParam.postArea==id){
				 return "1"
			 }
		}
	 }
	
	return "0"
	 
 }