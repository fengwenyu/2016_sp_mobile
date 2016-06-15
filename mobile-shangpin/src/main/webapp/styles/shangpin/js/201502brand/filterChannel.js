var num = 0;
var ready=1;
var menuNavHeight=0; 
$(function(){
	var $overlay = $('#overlay');
   
    $(".alContent").attr("style", "margin-top:0");
    var imgHeight = $('.tabSlider-bd .hallBox a img').eq(0).height(); // 获取焦点图img高度
    $('.tabSlider-bd .hallBox a').css('min-height', imgHeight);
    
    $(window).scroll(BottomLoading);  //下拉加载

    //判断设备
    var UA = window.navigator.userAgent;
    var CLICK = 'click';
    if(/ipad|iphone|android/.test(UA)){
        CLICK = 'tap';
    }
    
    if($('.menu-nav').length){
    	menuNavHeight = $('.menu-nav').offset().top;
    }
	
	selectOrder();
	
	/* 滑块列表 */
	app.TabSlider('#tabSlider1');
	app.TabSlider('#tabSlider2');
    
    //特卖未开启弹出层
    $('body').delegate('.no_open',CLICK,function(e){
        e.preventDefault();
        jShare('还没开始呢，20:00 再来吧！',"","");
        return false;
    });
    
    restTimefun();//倒计时
    
 
    $('.btn-modal')[CLICK](function(e){
      modalHidden($('.modal'));
      e.stopPropagation();
    });
    $overlay[CLICK](function(e){
    	 if(e.target.classList.contains('overlay')){
    	        modalHidden($('.modal'));
    	      }
      });
    /*
    $('body').delegate($overlay,CLICK,function(e){
     if(e.target.classList.contains('overlay')){
        modalHidden($('.modal'));
      }
    });*/
    

    //筛选按钮事件
    $('body').delegate('.fillBtn',CLICK,function(e){
        e.preventDefault();
        fillBtn();
    });
    
    //确定按钮事件
    $('body').delegate('#finishBtn',CLICK,function(e){
        e.preventDefault();
        filterFinish();
    });
    
    //关闭按钮事件
    $('body').delegate('#filter_close',CLICK,function(e){
        e.preventDefault();
        filterClose();
    });
    
    /*筛选栏里选择选项*/
    $('body').delegate('.category-box li,.color-box li',CLICK,function(e){
        $(this).addClass("cur").siblings().removeClass("cur");
    });
    
    $('body').delegate('.color-box a',CLICK,function(e){
        $(this).parent().addClass("cur").siblings().removeClass("cur");
    });
    $('body').delegate('#sizeItem li',CLICK,function(e){
        $(this).addClass("cur").siblings().removeClass("cur");
    });
    
    
    //点击列表按钮切换并跳转
    $('body').delegate('#list_menu li',CLICK,function(e){
        e.preventDefault();
        if($(this).hasClass('price-btn')){
            if(!$(this).hasClass('curr')){
                $(this).addClass('curr');
                $("#postArea").val("1");
                orderChange("2");
            }else if($(this).hasClass('curr')&&$('.price-btn').hasClass('price-down')){
                $(this).removeClass('price-down');
                $("#postArea").val("1");
                orderChange("2");
            }else{
                $(this).addClass('price-down');
                $("#postArea").val("1");
                orderChange("1");//从高到低
            }
        }
        
        $(this).addClass('curr').siblings().removeClass('curr price-down');
    
    })


});



//筛选按钮
function fillBtn(){
	$("#filter_layer").show();
	$('body').css({'overflow':'hidden'});
	$('.alContent').css({'visibility':'hidden'});
	$('.app_bg').css({'visibility':'hidden'});
	$(window).unbind('scroll');
	$("#filter_box").attr("class", "slideIn");
	filterboxSelect();
}

//关闭筛选按钮
function filterClose(){
	$("body").unbind("touchmove");
	$("#filter_box").attr("class", "slideOut");
	$('.alContent').css({'visibility':'visible'});
	$('.app_bg').css({'visibility':'visible'});
	setTimeout(function(){
		$("#filter_layer").fadeOut();
	},600);
	$('#categoryNo').val("");
	$('#categoryName').val("");
	$('#size').val("");
	$('#color').val("");
	$('#colorName').val("");
	$('#price').val("");

	$('#search_form').submit();
	return false;
}

//筛选确定按钮
function filterFinish(){
	$("body").unbind("touchmove");//解除禁止滑动事件
	$("#filter_box").attr("class", "slideOut");
	$('.alContent').css({'visibility':'visible'});
	$('.app_bg').css({'visibility':'visible'});
	setTimeout(function(){
		$("#filter_layer").fadeOut();
		$("#start").val("1");
		$('#search_form').submit();
	},600);
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
		// 默认选中选项
		var brandNO = $("#brandNo").val();
		var categoryNO = $("#categoryNo").val();
		var productSize = $("#size").val();
		var price = $("#price").val();
		var primaryColorId = $("#color").val();
		var primaryColorName = $("#colorName").val();
		var categoryFlag=""
		$("#categoryItem li").each(function(){
			var selectFlag ="";
			var selValue = $(this).attr("id");
			if(selValue != ""){
			 if(selValue == categoryNO){
					selectFlag = "1";
					categoryFlag="1";
					getSearchConditions();
				}else{
					selectFlag ="";
				}
				// 具体子项选中状态
				if(selectFlag == "1"){
					$(this).addClass("cur");
					return false; 
				}
			
			}
		});	
		if(categoryFlag!="1"){
			$("#categoryFirst").addClass("cur");
			$("#categoryNo").val($("#categoryFirst").attr("idFlag"));
			$('#categoryName').val($("#categoryFirst").attr("nameFlag"));
			getSearchConditions();
		}
		// var parentCategory = $("#parentCategory").val();
	if(productSize!=null&&productSize!=""){
		$("#sizeItem li").each(function(){
			var selectFlag ="";
			var selValue = $(this).attr("id");
			if(selValue != ""){
				// 判断头部的选中状态
				if(selValue == productSize){  
					selectFlag = "1";
				}else{
					selectFlag ="";
				}
				// 具体子项选中状态
				if(selectFlag == "1"){
					$(this).addClass("cur");
					return false; 
				}
			}
		});	
	}	
	if(primaryColorId!=null&&primaryColorId!=""){
		
		$("#colorItem li").each(function(){
			var selectFlag ="";
			var selValue = $(this).attr("id");
			if(selValue != ""){
				// 判断头部的选中状态
				if(selValue == primaryColorId){  
					selectFlag = "1";
				}else{
					selectFlag ="";
				}
				// 具体子项选中状态
				if(selectFlag == "1"){
					$(this).addClass("cur");
					return false; 
				}
			}
		});	
	}
	if(price!=null&&price!=""){
		$("#priceItem li").each(function(){
			var selectFlag ="";
			var selValue = $(this).attr("id");
			if(selValue != ""){
				// 判断头部的选中状态
				if(price=='10000-'&&selValue=='10000'){
					selectFlag = "1";
				}else if(selValue == price){  
					selectFlag = "1";
				}else{
					selectFlag ="";
				}
				// 具体子项选中状态
				if(selectFlag == "1"){
					$(this).addClass("cur");
					return false; 
				}
			}
		});	
	}
}

function selectOrder(){
	$("#price").val($("#oldPrice").val());
	$("#size").val($("#oldSize").val());	
	$("#color").val($("#oldColor").val());
	$("#categoryNo").val($("#oldCategoryNo").val());
	$("#brandNo").val($("#oldBrandNo").val());
	$("#gender").val($("#oldGender").val());
	$("#order").val($("#oldOrder").val());
	$("#postArea").val($("#oldPostArea").val());
	$("#start").val($("#oldStart").val());
	var order = $("#order").val();
    if(order=="1"){// 从高到低
        $("#orderThree").addClass("price-btn curr price-down");
    }else if(order=="2"){// 从低到高
        $("#orderThree").addClass("price-btn curr ");
    }else if(order=="5"){//销量
        $("#orderTwo").addClass("curr");
    }else if(order=="3"){//新品
        $("#orderOne").addClass("curr");
    }else if(order=="" && $("#postArea").val() == "2"){//海外购
        $("#abroadOrder").addClass("curr");
    }else if(order=="" && $("#postArea").val() == "0" ){//默认
        $("#defaultOrder").addClass("curr");
    }else if(order=="" && $("#postArea").val() == "" ){//默认
        $("#defaultOrder").addClass("curr");
    }
}

//下拉加载
function BottomLoading(){
	console.log(1);
	var loading = {
		img : '../../styles/shangpin/images/201502brand/loading.gif',
		msgText : '正在加载中...',
	};
	
	var htmlUrlLength = $("#hasMore").val();
	var addSelector = '.prod_list';
	var loadingMsg = '<div id="loading" style="display:block"><img src=' + loading.img + ' /><p>' +loading.msgText + '</p><div>'
	
	if(num==0&& parseInt($('#start').val())>1){
		 $('#start').val("1");
	}
	var start=$("#start").val();
	console.log(start);
	if(htmlUrlLength!="0"){
		
		if ($(window).scrollTop() + $(window).height() >= $(document).height()-50){
			if(!$('body').find('#loading').length){
				$('.list-box').append(loadingMsg);
			}
			if(ready==1){
				  $('#loading').show();
				  ready=0;
				  var path = getRootPath();
				  var checkAPP=$('#checkApp').val()
				  var channelConditions = $('#search_form').serialize();
				  $.ajax({
					  url : path+"/channel/product/list/more?channelCategoryNo="+$("#channelCategoryNo").val() ,
					  data:channelConditions,
					  dataType:"json",
					  success : function(data) {
						  console.log(data);
						$('#loading').hide();
						var hasMore = data.hasMore;
						if(data.channelResult.productList != null && data.channelResult.productList.length > 0){
							$.each(data.channelResult.productList,function(index,item){
								var pic = item.pic.substring(0,item.pic.indexOf('-')) + "-306-407.jpg";
								$div = $("<div></div>");
								$div.addClass("list_box");
								$a = $("<a></a>");
								$i=$("<i></i>");
								if(item.count<=0){
									$i.addClass("saleOut").text("售罄");
								}
								if(checkAPP){
									hrefV = "shangPinApp://phone.shangpin/actiongodetail?productid="+item.productId;
							  	}else{
							  		hrefV =path+ "/product/detail?productNo="+item.productId + "&picNo=" + item.picNo;
							  		var onclick="_smq.push(['custom',"+ item.productId+","+item.productName+"]);";
									$a.attr({"onclick":onclick});
							  	}
								if(item.count<=0){
									$a.append($i);
							  	}
								$a.attr({"href":hrefV});
								$img = $("<img/>");
								$img.attr({"src":pic});
								$a.append($img);
								$div.append($a);
								$div1 = $("<div></div>");
								$div1.addClass("li_text");
								$h5 = $("<h5></h5>").text(item.brandNameEN);
								$p = $("<p></p>").text(item.productName);
								$span=$("<span></span>").addClass("item-detail");
								$strong=$("<strong></strong>").addClass("refer-price");
								
								$emc=$("<em></em>");
								if(item.status == '0001'|| item.status == '000100'){
									$strong.text('￥' + item.promotionPrice);
									if(item.promotionPrice*1<item.marketPrice){
										$emc.text('￥' +item.marketPrice);
									}
									$span.append($strong);
								}else{
									if(item.isSupportDiscount==1){
										if(data.userLv=='0002'){
											$strong.text('￥' + item.goldPrice);
											if(item.goldPrice*1<item.marketPrice){
												$emc.text('￥' +item.marketPrice);
											}
											$span.append($strong);
										}else if(data.userLv=='0003'){
											$strong.text('￥' + item.platinumPrice);
											if(item.platinumPrice*1<item.marketPrice){
												$emc.text('￥' +item.marketPrice);
											}
											$span.append($strong);
										}else if(data.userLv=='0004'){
											$strong.text('￥' + item.diamondPrice);
											if(item.diamondPrice*1<item.marketPrice){
												$emc.text('￥' +item.marketPrice);
											}
											$span.append($strong);
										}else{
										
											$strong.text('￥' + item.limitedPrice);
											if(item.limitedPrice*1<item.marketPrice){
												$emc.text('￥' +item.marketPrice);
											}
											$span.append($strong);
										} 
									}else{
										$strong.text('￥' + item.limitedPrice);
										if(item.limitedPrice*1<item.marketPrice){
											$emc.text('￥' +item.marketPrice);
										}
										$span.append($strong);
									}
									
								}
								
								/*$span.append($emc);*/
								
								$div1.append($h5);
								$div1.append($p);
								$div1.append($span);
								$div.append($div1);
								
							//	$div.append($div1);
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
//					//	alert('数据获取失败，请刷新页面');
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




//倒计时
function restTimefun(){
	var nowDate = new Date();
	var nowTime = nowDate.getTime();
	$('#last_crazy a').each(function(index, element) {
		var end_time = $(this).find('.rest-time').attr('end-time');
		var restHours = Math.floor((end_time - nowTime)/(365*24*60*60)+1);
		var restTime ="剩余"+restHours +"小时";
		$(this).find('.rest-time').html(restTime);

		setTimeout(restTimefun,3600000);
	});
	
}


/* ============== 搜索处理方法 start ============== */
function searchKey(key,value){
	if(key == "color"){
		var arr = new String(value).split("-");
		$('#color').val(arr[0]);
		$('#colorName').val(arr[1]);
	
	}else if(key == "size"){
		$('#size').val(value);
	}else if(key == "price"){
		if(value=='10000'){
			$("#price").val("10000-");
		}else{
			$('#price').val(value);
		}
	}
}

function searchCategory(id,code,name){
    	$('#categoryNo').val(code);
    	$('#categoryName').val(name);
    	$('#size').val("");
    	$('#color').val("");
    	$('#colorName').val("");
    	$('#price').val("");
    	$("#priceItem li").each(function(){
			$(this).removeClass("cur");
    	});	
    	getSearchConditions();
}

function orderChange(order){
	$('#start').val("1");
	if(order == "9"){
		$("#order").val("");
		$("#postArea").val("2");//海外
	}else{
		$('#order').val(order);
		$("#postArea").val("0");//全部
	}
	$('#search_form').submit();
	$('.topBack').removeClass('topbg');
}

function getSearchConditions(){
  	var path = getRootPath();
		$.ajax({
			url : path+"/channel/search/conditions" ,
		  	data:{
			  "categoryNo":	$('#categoryNo').val(),
			  "brandNo":$('#brandNo').val(),
			  "channelCategoryNo":$('#channelCategoryNo').val()
			},
	  	dataType:"json",
	  	success : function(data) {
	  		var len=0;
	  		var len2=0;
	  		if(data.channelResult.sizeList!=null){
	  			len = data.channelResult.sizeList.length;
	  		}
	  		if(data.channelResult.colorList!=null){
	  			len2 = data.channelResult.colorList.length;
	  		}
			var hasMore = data.hasMore;
			$ul = $("<ul></ul>");
			$ul2 = $("<ul></ul>");
			if(len > 0){
				$('#sizeItem ul').remove();
				$('#sizeItem').show();
				$.each(data.channelResult.sizeList,function(index,item){
					$li = $("<li></li>");
					var onclick="searchKey('size',"+"'"+item.id+"'"+")";
					$li.attr({"id":item.id,"onclick":onclick});
					if(item.id==$('#size').val()){
						$li.addClass("cur")
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
				$.each(data.channelResult.colorList,function(index1,item2){
					$li2 = $("<li></li>");
					$i= $("<i></i>");
					$img = $("<img/>");
					$img.attr({"src":path+"/styles/shangpin/images/201502brand/color_cur.png"});
					$i.append($img);
					$a = $("<a></a>");
					var hrefV="javascript:searchKey('color',"+"'"+item2.id+"-"+item2.name+"'"+")";
					$a.attr({"href":hrefV});
//					$a.addClass("all-color");
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
					if(item2.id==$('#color').val()){
						$li2.addClass("cur")
					}
					$li2.append($i);
					$li2.append($a);
					$li2.append($span);
					$ul2.append($li2);
				});
				
			}else{
				$('#colorItem').hide();
			}
			
			$('#sizeItem').append($ul);
			$('#colorItem').append($ul2);
			
	  	}
	});
}
