// JavaScript Document
//清除的时候,筛选默认选中全部 其他的都去掉
function clearSlect() {
	$(".layer_tabsMenu").find("li").each(function() {
		var leftIndex = $(this).index();
		if (leftIndex == 0) {
			$(this).addClass("cur");
		} else {
			$(this).removeAttr("class");
		}
	});

	$(".layer_tabsCell").each(function() {
		var leftIndex = $(this).index();
		if (leftIndex == 0) {
			$(this).attr("style", "display: block;");
		} else {
			$(this).attr("style", "display: none;");
		}
	});
}
function clearData(flag) {
	
	$("#size").val("");
	$("#price").val("");
	$("#color").val("");
	$('#colorName').val("");
	if (flag == 1) {
		$("#gender").val("");
	} else {

	}
}	
$(function(){
	
	/* ============== 筛选弹出层事件 ============== */
	var TopImg = $(".menu").attr("hasTopImg");
	var hasTitle = $(".menu").attr("hasTitle");
	var isWeixin= $("#isWeixin").val();
	if(TopImg == "no"){
		if(isWeixin=='1'){//如果不是微信的话
			if(hasTitle == "yes"){
				$(".nav_menu").css({"top":"190px"});
			}else{
				$(".nav_menu").css({"top":"90px"});
			}
		}else{
			if(hasTitle == "yes"){
				$(".nav_menu").css({"top":"90px"});
			}else{
				$(".nav_menu").css({"top":"45px"});
			}
		}
		
	}
	
	$(".fillBtn").on("click", function(){
		$("#filter_layer").show();
		$("#filter_box").attr("class", "slideIn");
		$(".alContent").attr("class", "alContent slideLeft").height($(window).height()).css({"overflow":"hidden"});
		//layerScroll.refresh();
		//关闭前面筛选层
		$(".menu span a i").hide();
		$(".nav_menu").slideUp(300);
		$(".brand_menu").slideUp(300);
		//默认选择项
		filterboxSelect();
		layerScroll.refresh();
		return false;
	});
	$("#finishBtn").on("click", function(){
		$("#filter_box").attr("class", "slideOut");
		$("#filter_layer").delay(600).fadeOut();
		$(".alContent").attr("class", "alContent slideRight").height("auto").css({"overflow":"auto"});
		//如果改变了则提交
		setTimeout(function(){
			$('#search_form').submit();
		},600);	
//		if(changeFlag()){
//			setTimeout(function(){
//				$('#search_form').submit();
//			},600);		
//		}else{
//			//$.post("${ctx}/brand/product/list",$('#search_form').serialize());
//			//var comCount = $("#commitCount").val()*1 + 1;
//			//$("#commitCount").val(comCount);
//			//$(".backBtn").attr("href","javascript:history.go(-"+comCount+");");
//		}
		return false;
	});
	//判断页面是否需要重新提交
	var changeFlag = function(){
		var primaryColorId = $("#color").val();
		var price = $("#price").val();
		var productSize = $("#size").val();
		var oldPrice = $("#oldPrice").val();
		var oldPrimaryColorId = $("#oldPrimaryColorId").val();
		var oldProductSize = $("#oldProductSize").val();
		
		
		var colorsItemClass = $("#colorsItem").attr("class");
		var sizeItemClass = $("#sizeItem").attr("class");
		var priceItemClass = $("#priceItem").attr("class");
		//子项选择取消的时候 如果没有选择则  将开始的值 置为空
		if(valueValid(colorsItemClass)){
			if(colorsItemClass.indexOf("curr")<0){
				$("#primaryColorId").val("");
				$("#primaryColorName").val("");
				primaryColorId = $("#primaryColorId").val();
			}
		}
		//子项选择取消的时候 如果没有选择则  将开始的值 置为空
		if(valueValid(sizeItemClass)){
			if(sizeItemClass.indexOf("curr")<0){
				$("#productSize").val("");
				productSize = $("#productSize").val();
			}
		}
		//子项选择取消的时候 如果没有选择则  将开始的值 置为空
		if(valueValid(priceItemClass)){
			if(priceItemClass.indexOf("curr")<0){
				$("#price").val("");
				price = $("#price").val();
			}
		}
		
		var changeFlag = false;		
		if(valueValid(oldPrice)&& valueValid(price) && oldPrice != price){
			changeFlag = true;
		}else if(valueValid(oldPrimaryColorId)&& valueValid(primaryColorId) && oldPrimaryColorId != primaryColorId){
			changeFlag = true;
		}else if(valueValid(oldProductSize)&& valueValid(productSize) && oldProductSize != productSize){
			changeFlag = true;
		}
		return changeFlag;
	};
	
	//值是否有效
	var valueValid = function(str){
		return !((typeof str == undefined) || (typeof str == "undefined")) ;
	};
	
	
	
	//关闭按钮事件
	$("#filter_close").on("click", function(){
		$("#filter_box").attr("class", "slideOut");
		$("#filter_layer").delay(600).fadeOut();
		$(".alContent").attr("class", "alContent slideRight").height("auto").css({"overflow":"auto"});
		return false;
	});
	
	//tab切换事件
	var tabs = function(nav,content){
		$(nav).find("li").bind("click",function(){
			var index = $(this).index();
			$(this).addClass("cur").siblings().removeClass("cur");
			$(content).eq(index).show().siblings(content).hide();
			
			//刷新滑动事件
			layerScroll.refresh();
			layerScroll.scrollTo(0, 0, 200);
			
		});
	};
	
	tabs(".layer_tabsMenu", ".layer_tabsCell");
	
	//滑动事件
	var layerScroll,
		leftMenuW = $(".filter_select li.first").width(),
		pageW = $(window).width() - (parseInt(leftMenuW)+60),
		pageH = $(window).height() - 50;
		
	$("#layerContent").css({"height":pageH, "width":pageW});
	
	function loaded(){
		
		layerScroll = new iScroll('layerContent',
		{
			hScrollbar:false,
			vScrollbar:true,
			hideScrollbar:true
		});
	};
	if($("#layerContent").length > 0){
		loaded();
	}
	function filterboxSelect(){
		//默认选中选项
		var brandNO = $("#brandNo").val();
		var categoryNO = $("#categoryNo").val();
		var productSize = $("#size").val();
		var price = $("#price").val();
		var primaryColorId = $("#color").val();
		var primaryColorName = $("#colorName").val();
		//var parentCategory = $("#parentCategory").val();
		$(".pageBox dd").each(function(){
			var selectFlag ="";
			var selValue = $(this).attr("id");
			if(selValue != ""){
				//判断头部的选中状态
				if(selValue == productSize){  
					selectFlag = "1";
					$("#sizeItem").addClass("curr");
				}else if(selValue == price){ 
					selectFlag = "1";
					$("#priceItem").addClass("curr");
				}else if(selValue == primaryColorId){
					selectFlag = "1";
					$("#colorItem").addClass("curr");
				}else{
					selectFlag ="";
				}
				//具体子项选中状态
				if(selectFlag == "1"){
					alert($(this));
					$(this).addClass("curr");
//					$("#clearBtn").addClass("curr");
					//$("#newclearBtn").addClass("curr");
				}
			}
		});			
		
		//changeItemSelect("brandNO", brandNO);
		changeItemSelect("price", price);
		//changeItemSelect("categoryNO", categoryNO);
		changeItemSelect("color", primaryColorId);
		changeItemSelect("size", productSize);
		changeleftAllSelect();
	}
	
	function changeleftAllSelect(){
		var select = false;
		$(".layer_tabsMenu").find("li").each(function(){
			//如果有curr样式  则将第一个选中为默认项
			//否则将全部置为默认项
			if(select == false){
				var tClass = $(this).attr("class");
				if(valueValid(tClass) && tClass.indexOf("curr") >= 0){
					select = true;
					var index = $(this).index();
					$(this).addClass("cur");
					//$("#newshaixuanAll").removeAttr("class");
					//$("#shaixuanAll").removeAttr("class");
					//右侧内容也要选中  display: block;
					$(".pageBox").find("dl").each(function(){
						if($(this).index() == index){
							$(this).attr("style","display: block;");
						}else{
							$(this).attr("style","display: none;");
						}
					});
				}
			}
		});
		
		if(select == false){
			
			if(valueValid($("#priceItem"))){
				$("#priceItem").addClass("cur");
			}else if(valueValid($("#sizeItem"))){
				$("#sizeItem").addClass("cur");
			}else if(valueValid($("#colorItem"))){
				$("#colorItem").addClass("cur");
			}
			//$("#newshaixuanAll").addClass("cur");
			//$("#shaixuanAll").addClass("cur");
		}
	}
	
	function changeItemSelect(item, value){
		if(valueValid(value) && value ==""){
			$("#"+item+"All").addClass("curr");
		}
	}
	
	//清除所有选择
	$("#clearBtn").click(function(){
		clearData(1);
		$("#filter_box dl dd.curr, #filter_box ul li.curr, #clearBtn").removeClass("curr");
		clearSlect();
		return false;
	});
	
	//新品清除所有选择
	$("#newclearBtn").click(function(){
		clearData(0);
		$("#filter_box dl dd.curr, #filter_box ul li.curr, #newclearBtn").removeClass("curr");
		
		clearSlect();
		return false;
	});
	
	
	//新品筛选点击全部
	//$("#newshaixuanAll").click(function(){
	//	clearData(0);
	//	$("#filter_box dl dd.curr, #filter_box ul li.curr, #newclearBtn").removeClass("curr");
	//	return false;
	//});
	
	//筛选点击全部
	//$("#shaixuanAll").click(function(){
	//	clearData(1);
	//	$("#filter_box dl dd.curr, #filter_box ul li.curr, #clearBtn").removeClass("curr");
	//	return false;
	//});
	
	
	
	
	//监听横竖屏切换事件
	window.addEventListener("orientationchange", function() {
		
		var pageW = $(window).width() - (parseInt(leftMenuW)+60),
			pageH = $(window).height() - 50;
		
		$("#layerContent").css({"height":pageH, "width":pageW});
		
		layerScroll.refresh();
		
	}, false);
	
	//筛选点击事件
	$("#filter_box dl").each(function() {
        var obj = $(this);
		
		obj.find("dd").bind("click", function(){
			if(navigator.userAgent.indexOf("Linux") > -1 || navigator.userAgent.indexOf("Android") > -1){
				$(this).addClass("curr").siblings("dd").removeClass("curr");
				
			}else{
				$(this).toggleClass("curr").siblings("dd").removeClass("curr");
			}
			
			var cLen = obj.find("dd.curr").length,
				cIndex = obj.index();
			    fIndex = $(this).index();
			if(cLen > 0){
				$(".layer_tabsMenu li:eq("+ cIndex +")").addClass("curr");
			}else{
				$(".layer_tabsMenu li:eq("+ cIndex +")").removeClass("curr");
			}
			if(fIndex == 0){
				$(".layer_tabsMenu li:eq("+ cIndex +")").attr("class","cur");
			}
			
			//清除按钮状态和事件
			var allLen = $("#filter_box dl dd.curr").length;
			if(allLen > 0){
				$("#clearBtn").addClass("curr");
				$("#newclearBtn").addClass("curr");
			}else{
				$("#clearBtn").removeClass("curr");
				$("#newclearBtn").removeClass("curr");
				
			}
			
			//return false;
		});
		
    });
	
	
	/* ============== 筛选弹出层事件 end ============== */
	
/* ============== 下拉弹出层事件 ============== */
	
	//品牌列表页头部筛选框
	if($(".menu span a").length > 0){
		
		$(".menu span a:not('.category_nav')").click(function(){
			$(".menu span a i").hide();
			$(this).find("i").show();
			
			//关闭品牌弹出层
			$(".brand_menu").slideUp(300);
			
			var data = $("#"+$(this).attr("data-list")).html();
			$("#nav_list").html(data);
			if(TopImg == "no"){
				$(".nav_menu").height($(window).height()-45).slideDown(300);
			}else{
				$(".nav_menu").height($(window).height()-140).slideDown(300);
			}
			navScroll.refresh();
			
			$(".alContent").height($(window).height()).css({"overflow":"hidden"});
			return false;
		});
		
//		$("#nav_list").delegate("dd", "click", function(){
//			$(".menu span a i").hide();
//			$(".nav_menu").slideUp(300);
//			$(".alContent").height("auto").css({"overflow":"auto"});
//			setTimeout(function(){
//				$(this).attr("onclick");
//			}, 100);
//			return false;
//		});
		
		$("#nav_list").delegate("dd", "click", function(){
			//return false;
		});
		$("#nav_list").click(function(){
			$(".menu span a i").hide();
			$(".nav_menu").delay(300).slideUp(300);
			$(".filter_txt").show();
			$(".alContent").height("auto").css({"overflow":"auto"});
			setTimeout(function(){
				$(this).attr("onclick");
			}, 100);
			return false;
		});
		
		
		//弹出层灰色层事件
		$("body").delegate(".nav_bg", "click", function(){
			$(".menu span a i").hide();
			$(".nav_menu").delay(300).slideUp(300);
			$(".filter_txt").show();
			$(".alContent").height("auto").css({"overflow":"auto"});
			return false;
		});
		
	}
	

	
	//滑动事件
	var navScroll,
	navW = $(window).width(),
	navH = 270;
		
	$("#navContent").css({"height":navH, "width":navW});
	
	function loaded2(){
		
		navScroll = new iScroll('navContent',
		{
			hScrollbar:false,
			vScrollbar:true,
			hideScrollbar:true,
			bounce:false
		});
	};
	if($("#layerContent").length > 0){
		loaded2();
	}
	/* ============== 下拉弹出层事件 end ============== */
	
	
/* ============== 品类弹出层事件 ============== */
	
	//品类列表页头部筛选框
	if($(".menu span a.category_nav").length > 0){
		
		$(".menu span a.category_nav").click(function(){
			$(".menu span a i").hide();
			$(this).find("i").show();
			//关闭别的层
			$(".nav_menu").slideUp(300);
			
			if(isWeixin=='1'){//如果不是微信的话
				if(TopImg == "yes"){
					$(".brand_menu").height($(window).height()-45).slideDown(300);
					$(".brand_menu").css({"top":"190px"});
				}else{
					$(".brand_menu").height($(window).height()-140).slideDown(300);
					$(".brand_menu").css({"top":"90px"});
				}
			}else{
				
				if(TopImg == "yes"){
					$(".brand_menu").height($(window).height()-45).slideDown(300);
					$(".brand_menu").css({"top":"145px"});
				}else{
					$(".brand_menu").height($(window).height()-140).slideDown(300);
					$(".brand_menu").css({"top":"45px"});
				}
			}
		
			
			brandScroll.refresh();
			
			$(".alContent").height($(window).height()).css({"overflow":"hidden"});
			return false;
		});
		
		//tab切换事件
		var brandTabs = function(nav,content){
			$(nav).find("li").bind("click", function(){
				var index = $(this).index();
				$(this).addClass("cur").siblings().removeClass("cur");
				$(content).eq(index).show().siblings(content).hide();
				
				//刷新滑动事件
				brandScroll.refresh();
				brandScroll.scrollTo(0, 0, 200);
				
			});
		};
		
		brandTabs(".brand_tabsMenu", ".brand_tabsCell");
		
		$(".brand_tabsCell").delegate("dd", "click", function(){
			$(".menu span a i").hide();
			$(".brand_menu").delay(300).slideUp(300);
			$(".filter_txt").show();
			
			$(".alContent").height("auto").css({"overflow":"auto"});
			return false;
		});
		
	}
	
	
	//滑动事件
	var brandScroll,
		brandW = $(window).width(),
		brandH;
		
	if(TopImg == "no"){
		brandH = $(window).height()-45;
	}else{
		brandH = $(window).height()-140;
	}
		
	$("#barndlayer").css({"height":brandH});
	
	function loaded3(){
		
		brandScroll = new iScroll('barndlayer',
		{
			hScrollbar:false,
			vScrollbar:true,
			hideScrollbar:true,
			bounce:false
		});
	};
	if($("#barndlayer").length > 0){
		loaded3();
	}
	/* ============== 品类弹出层事件 end ============== */
});
/* ============== 搜索处理方法 start	 ============== */
function searchKey(key,value){
	if(key == "color"){
		var arr = new String(value).split("-");
		$('#color').val(arr[0]);
		$('#colorName').val(arr[1]);
	}else if(key == "size"){
		$('#size').val(value);
	}else if(key == "price"){
		$('#price').val(value);
	}
}

function searchCategory(gender,code,name){
	
	if(gender != null){
		$('#gender').val(gender);
	}else{
		$('#gender').val("");
	}
	$("#order").val("");
	$('#categoryNo').val(code);
	$('#categoryName').val(name);
	clearData(0);
	clearSlect();
	
	$('#search_form').submit();
}
function searchBrand(code,name){

	$("#brandNo").val(code);
	$("#brandName").val(name);
	$('#search_form').submit();
}


function orderChange(order){
	$('#order').val(order);
	$('#search_form').submit();
}
//加载更多
function getMore(checkAPP){
	
	var path = getRootPath();
	var start = parseInt($('#start').val()) + 1;
	var pageNo = parseInt($("#pageNo").val()) + 1;
	$('#start').val(start);
	
	var searchConditions = $('#search_form').serialize();
	$.post(path+"/brand/product/list/more",searchConditions
			,function(data){
		var len = data.searchResult.productList.length;
		var hasMore = data.hasMore;
		
		if(len > 0){
			
			$("#pageNo").val(pageNo);
			$("a").remove(".list_moreLink");
			$.each(data.searchResult.productList,function(index,item){
				var pic = item.productPicUrl.substring(0,item.productPicUrl.indexOf('-')) + "-318-422.jpg";
				$li = $("<li></li>");
				$a = $("<a></a>");
				$img = $("<img/>");
				if(item.availableStock < 1){
					$a.append($("<i class='saleOut'>售罄</i>"));
				}
				$div = $("<div></div>");
				$p = $("<p></p>");
				$eme = $("<em></em>")
				$emc = $("<em></em>")
				$span = $("<span></span>");
				//需要判断根据用户级别显示不同的价格
				if(data.userLv == "0002"){
					$i = $("<em></em>").text('￥' + item.goldPrice);
				}else if(data.userLv == "0003"){
					$i = $("<em></em>").text('￥' + item.platinumPrice);
				}else if(data.userLv == "0004"){
					$i = $("<em></em>").text('￥' + item.diamondPrice);
				}else{
					$i = $("<em></em>").text('￥' + item.limitedPrice);
				}

				
				$p.append($eme);
				$p.append($emc);
				$div.append($p);
				$span.append($i);
				$div.append($span);
				$a.append($img);
				$a.append($div);
				$li.append($a);
				var hrefV='';
				if(checkAPP){
					hrefV = "shangPinApp://phone.shangpin/actiongodetail?productid="+item.productId;
			  	}else{
			  		hrefV =path+ "/product/detail?productNo="+item.productId;
			  	}
				$a.attr({"title":item.brandNameCN + item.brandNameEN,"href":hrefV});
				$img.attr({"width":"159","height":"212","alt":item.brandNameCN + item.brandNameEN,"src":pic});
				$div.addClass("brandList_info");
				$eme.text(item.brandNameEN);
				$emc.text(item.productName);
				$('#brandProductList').append($li);
			});
			if(hasMore == 1){
				$('.brandBlock').append("<a class='list_moreLink' href='javascript:getMore("+checkAPP+");'>点击查看更多</a>");
			}else{
				$("a").remove(".list_moreLink");
			}
		}
	},"json");
	
}





//加载更多
function getFlagshopMore(checkAPP){
	
	var path = getRootPath();
	var start = parseInt($('#start').val()) + 1;
	var pageNo = parseInt($("#pageNo").val()) + 1;
	$('#start').val(start);
	
	$("#pageNo").val(pageNo);
	var searchConditions = $('#search_form').serialize();
	$.post(path+"/brand/flagshopProduct/list/more",searchConditions
	,function(data){
		var len = data.searchResult.productList.length;
		var hasMore = data.hasMore;
		if(len > 0){
			$("a").remove(".list_moreLink");
			$.each(data.searchResult.productList,function(index,item){
				var pic = item.productPicUrl.substring(0,item.productPicUrl.indexOf('-')) + "-318-422.jpg";
				$li = $("<li></li>");
				$a = $("<a></a>");
				$img = $("<img/>");
				if(item.hasStock < 1){
					$a.append($("<i class='saleOut'>售罄</i>"));
				}
				$div = $("<div></div>");
				$p = $("<p></p>");
				$eme = $("<em></em>")
				$emc = $("<em></em>")
				$span = $("<span></span>");
				$dev= $("");
				//需要判断根据用户级别显示不同的价格
				if(data.userLv == "0002"){
					$i = $("<em></em>").text('￥' + item.goldPrice);
					if(item.marketPrice*1>item.goldPrice){
						$dev=$("<del></del>").text('￥' + item.marketPrice);
					}
				}else if(data.userLv == "0003"){
					$i = $("<em></em>").text('￥' + item.platinumPrice);
					if(item.marketPrice*1>item.platinumPrice){
						$dev=$("<del></del>").text('￥' + item.marketPrice);
					}
				}else if(data.userLv == "0004"){
					$i = $("<em></em>").text('￥' + item.diamondPrice);
					if(item.marketPrice*1>item.diamondPrice){
						$dev=$("<del></del>").text('￥' + item.marketPrice);
					}
				}else{
					
					$i = $("<em></em>").text('￥' + item.limitedPrice);
					if(item.marketPrice*1>item.limitedPrice){
						$dev=$("<del></del>").text('￥' + item.marketPrice);
					}
				}

				
				$p.append($eme);
				$p.append($emc);
				$div.append($p);
				$span.append($i);
				$span.append($dev);
				$div.append($span);
				$a.append($img);
				$a.append($div);
				$li.append($a);
				var hrefV='';
				if(checkAPP){
					hrefV = "shangPinApp://phone.shangpin/actiongodetail?productid="+item.productId;
			  	}else{
			  		hrefV =path+ "/product/detail?productNo="+item.productId;
			  	}
				$a.attr({"title":item.brandNameCN + item.brandNameEN,"href":hrefV});
				$img.attr({"width":"159","height":"212","alt":item.brandNameCN + item.brandNameEN,"src":pic});
				$div.addClass("flagList_info");
				$eme.text(item.brandNameEN);
				$emc.text(item.productName);
				$('#brandProductList').append($li);
			});
			if(hasMore == 1){
				$('.flagBlock').append("<a class='list_moreLink' href='javascript:getFlagshopMore("+checkAPP+");'>点击查看更多</a>");
			}else{
				$("a").remove(".list_moreLink");
			}
		}
	},"json");
}

//加载更多
function geNewProductMore(checkAPP){
	
	var path = getRootPath();
	var start = parseInt($('#start').val()) + 1;
	var pageNo = parseInt($("#pageNo").val()) + 1;
	$('#start').val(start);
	var searchConditions = $('#search_form').serialize();
	$.post(path+"/product/new/list/more",searchConditions
	,function(data){
		var len = data.searchResult.productList.length;
		var hasMore = data.hasMore;
		if(len > 0){
			$("#pageNo").val(pageNo);
			$("a").remove(".list_moreLink");
			$.each(data.searchResult.productList,function(index,item){
				var pic = item.productPicUrl.substring(0,item.productPicUrl.indexOf('-')) + "-318-422.jpg";
				$li = $("<li></li>");
				$a = $("<a></a>");
				$img = $("<img/>");
				if(item.hasStock < 1){
					$a.append($("<i class='saleOut'>售罄</i>"));
				}
				$div = $("<div></div>");
				$p = $("<p></p>");
				$eme = $("<em></em>")
				$emc = $("<em></em>")
				$span = $("<span></span>");
			
				//需要判断根据用户级别显示不同的价格
				if(data.userLv == "0002"){
					$i = $("<em></em>").text('￥' + item.goldPrice);
				}else if(data.userLv == "0003"){
					$i = $("<em></em>").text('￥' + item.platinumPrice);
				}else if(data.userLv == "0004"){
					$i = $("<em></em>").text('￥' + item.diamondPrice);
				}else{
					$i = $("<em></em>").text('￥' + item.limitedPrice);
				}

				
				$p.append($eme);
				$p.append($emc);
				$div.append($p);
				$span.append($i);
				$div.append($span);
				$a.append($img);
				$a.append($div);
				$li.append($a);
				var hrefV='';
				if(checkAPP){
					hrefV = "shangPinApp://phone.shangpin/actiongodetail?productid="+item.productId;
			  	}else{
			  		hrefV =path+ "/product/detail?productNo="+item.productId;
			  	}
				$a.attr({"title":item.brandNameCN + item.brandNameCN,"href":hrefV});
				$img.attr({"width":"159","height":"212","alt":item.brandNameCN,"src":pic});
				$div.addClass("brandList_info");
				$eme.text(item.brandNameCN);
				$emc.text(item.productName);
				$('#brandProductList').append($li);
			});
			if(hasMore == 1){
				$('.brandBlock').append("<a class='list_moreLink' href='javascript:geNewProductMore("+checkAPP+");'>点击查看更多</a>");
			}else{
				$("a").remove(".list_moreLink");
			}
		}
	},"json");
}

