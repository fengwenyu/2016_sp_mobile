// JavaScript Document
$(function(){
	
	/* ============== 筛选弹出层事件 ============== */
	
	//如果页面无头图
	var TopImg = $(".menu").attr("hasTopImg");
	var hasTitle = $(".menu").attr("hasTitle");
	if(TopImg == "no"){
		if(hasTitle == "yes"){
			$(".nav_menu").css({"top":"90px"});
		}else{
			$(".nav_menu").css({"top":"45px"});
		}
	}
	
	//筛选按钮事件
	$(".fillBtn").on("click", function(){
		$("#filter_layer").show();
		$("#filter_box").attr("class", "slideIn");
		$(".alContent").attr("class", "alContent slideLeft").height($(window).height()).css({"overflow":"hidden"});
		
		//关闭前面筛选层
		$(".menu span a i").hide();
		$(".nav_menu").slideUp(300);
		$(".brand_menu").slideUp(300);
		
		var color_value = $("#color").val();
		var size_value = $("#size").val();
		var price_value = $("#price").val();
		if(!isEmpty(color_value)){
			initClass("color",color_value);
		}
		if(!isEmpty(size_value)){
			initClass("size",size_value);
		}
		if(!isEmpty(price_value)){
			//initClass("price",price_value);
			$("#priceItem").addClass("curr");
			$("#prices dd").each(function(){
				var dd_value = $(this).text();
				if(price_value == dd_value){
					$(this).addClass("curr").siblings().removeClass("curr");
					$("#newclearBtn").addClass("curr");
				}
			});
		}
		
		//显示左边颜色、价格、尺码如果有curr则添加cur样式，默认显示第一个
		var index;
		$(".layer_tabsMenu li").each(function(){
			if($(this).hasClass("curr")){
				index = $(this).index();
				$(this).addClass("cur").siblings().removeClass("cur");
				return false;
			}
		});
		
		$(".pageBox dl").each(function(){
			if($(this).index() == index){
				$(this).show();
				$(this).siblings().hide();
				return false;
			}
		});
		layerScroll.refresh();
		return false;
	});
	
	function initClass(id, value){
		$("#" + id + "Item").addClass("curr");
		$("#newclearBtn").addClass("curr");
		$("#" + value).addClass("curr");
	}
	
	function isEmpty(str){
		if(str == "" || str == null){
			return true;
		}else{
			return false;
		}
	}
	
	//完成按钮事件
	$("#finishBtn").on("click", function(){
		$("#filter_box").attr("class", "slideOut");
		$("#filter_layer").delay(600).fadeOut();
		$(".alContent").attr("class", "alContent slideRight").height("auto").css({"overflow":"auto"});
		initPageNo();
		$("#search_form").submit();
		return false;
	});
	
	//关闭按钮事件
	$("#filter_close").on("click", function(){
		$("#filter_box").attr("class", "slideOut");
		$("#filter_layer").delay(600).fadeOut();
		$(".alContent").attr("class", "alContent slideRight").height("auto").css({"overflow":"auto"});
		return false;
	});
	
	
	//tab切换事件
	var tabs = function(nav,content){
		$(nav).find("li").bind("click", function(){
			var index = $(this).index();
			$(this).addClass("cur").siblings().removeClass("cur");
			$(content).eq(index).show().siblings(content).hide();
			
			//刷新滑动事件
			layerScroll.refresh();
			layerScroll.scrollTo(0, 0, 200);
			
		});
	}
	
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
				if($(this).parent().attr("id") == "prices"){
					$("#price").val($(this).attr("id"));
				}else if($(this).parent().attr("id") == "sizes"){
					$("#size").val($(this).attr("id"));
				}else if($(this).parent().attr("id") == "colors"){
					$("#color").val($(this).attr("id"));
					$("#colorName").val($(this).text());
				}
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
	
	//清除所有选择
	$("#clearBtn").click(function(){
		$("#filter_box dl dd.curr, #filter_box ul li.curr, #clearBtn").removeClass("curr");
		clearSlect();
		return false;
	});
	//新品清除所有选择
	$("#newclearBtn").click(function(){
		$("#filter_box dl dd.curr, #filter_box ul li.curr, #newclearBtn").removeClass("curr");
		clearSlect();
		$("#price").val("");
		$("#size").val("");
		$("#color").val("");
		$("#colorName").val("");
		initPageNo();
		return false;
	});
	//清除的时候,筛选默认选中全部 其他的都去掉
	function clearSlect(){
		$(".layer_tabsMenu").find("li").each(function(){
			var leftIndex = $(this).index();
			if(leftIndex == 0){
				$(this).addClass("cur");
			}else{
				$(this).removeAttr("class");
			}
		});
		
		$(".layer_tabsCell").each(function(){
			var leftIndex = $(this).index();
			if(leftIndex == 0){
				$(this).attr("style","display: block;");
			}else{
				$(this).attr("style","display: none;");
			}
		});
	}
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
		
		$("#nav_list").delegate("dd", "click", this, function(){
			var type = $(this).attr("type");
			if(type == "category"){
				$("#categoryNo").val($(this).attr("id"));
				initPageNo();
				if($(this).text().indexOf('全部')==-1){
					$("#categoryName").val($(this).text());
				}else{
					$("#categoryName").val($("#parentCategoryName").val());
				}
			}else if(type == "brand"){
				$("#brandNo").val($(this).attr("id"));
				$("#brandName").val($(this).text());
				initPageNo();
			}else if(type == "order"){
				$("#order").val($(this).attr("id"));
				initPageNo();
			}
			$("#search_form").submit();
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
			
			if(TopImg == "no"){
				$(".brand_menu").height($(window).height()-45).slideDown(300);
			}else{
				$(".brand_menu").height($(window).height()-140).slideDown(300);
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
		}
		
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
//配置图片延迟加载
function SP_plug_lazyload(){

	SP.plug.lazyload("img", null, {
		start : function(){
			this.css({opacity : 0});
		},
		end : function(){
			this.animate({opacity:1}, 200);
		}
	}).run();
}
//加载更多
function getMore(checkAPP){
	var path = getRootPath();
	var start = parseInt($('#start').val()) + 1;
	var pageNo = parseInt($("#pageNo").val()) + 1;
	$('#start').val(start);
	$("#pageNo").val(pageNo);
	var searchConditions = $('#search_form').serialize();
	$.post(path + "/category/product/list/more",searchConditions
	,function(data){
		var len = data.searchResult.productList.length;
		var hasMore = data.hasMore;
		if(len > 0){
			$("a").remove(".list_moreLink");
			$.each(data.searchResult.productList,function(index,item){
				var pic = item.pic.substring(0,item.pic.indexOf('-')) + "-318-422.jpg";
				$li = $("<li></li>");
				$a = $("<a></a>");
				$img = $("<img/>");
				
				$div = $("<div></div>");
				$p = $("<p></p>");
				$eme = $("<em></em>")
				$emc = $("<em></em>")
				$span = $("<span></span>");
				$emp = $("<em></em>");
				//需要判断根据用户级别显示不同的价格
				//0001 普通 0002 黄金，0003 白金，0004 钻石
				if(data.userLv == "0002"){
					$emp.text('￥' + item.goldPrice);//黄金会员价
				}else if(data.userLv == "0003"){
					$emp.text('￥' + item.platinumPrice);//白金会员价
				}else if(data.userLv == "0004"){
					$emp.text('￥' + item.diamondPrice);//钻石会员价
				}else{
					$emp.text('￥' + item.limitedPrice);
				}
				$span.append($emp);
				$p.append($eme);
				$p.append($emc);
				$div.append($p);
				$div.append($span);
				if(item.count < 1){
					$a.append($("<i class='saleOut'>售罄</i>"));
				}
				$a.append($img);
				$a.append($div);
				$li.append($a);
				var hrefV='';
				if(checkAPP){
					hrefV = "shangPinApp://phone.shangpin/actiongodetail?productid="+item.productId;
			  	}else{
			  		hrefV =path+ "/product/detail?productNo="+item.productId;
			  	}
				$a.attr({"title":item.brandNameEN + item.brandNameCN,"href":hrefV});
				$img.attr({"width":"159","height":"212","alt":item.brandNameCN,"src":pic});
				$div.addClass("brandList_info");
				$eme.text(item.brandNameEN);
				$emc.text(item.productName);
				
				$('#brandListClass').append($li);
			});
			if(hasMore == 1){
				$('.brandBlock').append("<a class='list_moreLink' href='javascript:getMore("+checkAPP+");'>点击查看更多</a>");
			}
		}
		SP_plug_lazyload();
	},"json");
}

function initPageNo(){
	$('#start').val("1");
	$("#pageNo").val("1");
}