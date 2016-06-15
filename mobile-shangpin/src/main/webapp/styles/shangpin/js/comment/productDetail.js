var menuTopHeight=0;
var secondPropName=$("#secondPropName").val();
var firstPropName=$("#firstPropName").val();
var postArea=$('#postArea').val();
$(function(){
	var lowestPrice=$("#lowestInfo").attr("lowestPrice");
	var marketPrice=$("#lowestInfo").attr("marketPrice");
	var isPromotion=$("#lowestInfo").attr("isPromotion");
	showPrice(lowestPrice,marketPrice,isPromotion,"0");
	
	var path = getRootPath();
	//显示导航浮层方法
	$(window).scroll(BottomLoading);
	var isWx=$("#_iswx").val();
	if(isWx){
		menuTopHeight = 0;
	}else{
		if($('.topFix').length){
			menuTopHeight = $('.topFix').offset().top;
		}
	}

	function topFixed(){	
		var scrolls = document.body.scrollTop;
		if (scrolls > menuTopHeight) {
			$('.topFix').css({
				position: "fixed"
			});
			var isApp=$("#_isapp").val();
			var height=45
			if(isApp){
				height=0;
			}
			$('body .alContent').css('margin-top',height);    
		
		}else {
			$('body .alContent').css('margin-top',0); 
			$('.topFix').css({
				position: "static"
			});
			   
		}
	};

        //点击收藏
	$(".collection_btn").click(function(){
		if(!$(this).hasClass("cur")){
			$(this).addClass("cur");
			collect();
		}else{
			$(this).removeClass("cur");
			cancleCollect();
		}
				
	})

	var isShoppingCart =false; //公共变量
	var commodityNumber = 1;   //公共变量
	//tab切换
	var tabFlag=1;
	var sizeFlag=1
	$(".tab_info").find("li").click(function(){
		var $this = $(this);
		var $thisIndex = $this.index();
		var id=$this.attr("id");
		$(this).addClass("curr").siblings().removeClass("curr");
		$(".product_introduction").show();
		$(".other_service_ep").show();
		$(".list-box").show();
		if(id=="product_tab_1"){
			$(".content_info").find(".content_list").eq(0).show().siblings().hide();
		}
		if(id=="product_tab_2"){
			
			$(".content_info").find(".content_list").eq(1).show().siblings().hide();
		} 
		if(id=="product_tab_3"){
			getSize();
			$(".content_info").find(".content_list").eq(2).show().siblings().hide();
		}
		if(id=="product_tab_4"){
//			getTemplate();
			$(".content_info").find(".content_list").eq(3).show().siblings().hide();
		}
		if(id=="product_tab_5"){
			getIntroduction();
			$(".product_introduction").show();
			$(".other_service_ep").show();
			$(".list-box").show();
			$(".content_info").find(".content_list").eq(4).show().siblings().hide();
		}
	});

	var flag=1;
	//评论--查看更多评论
	$(".moreComment").click(function(){
		var $this = $(this);
		$this.parents(".content_list").find(".content_comment").show();
		
		$this.addClass("hidden");
		$(".putAwayComment").removeClass("hidden");
		if(flag==1){
			getComment(1);
			flag=0;
		}
	
		
	});

	//评论--收起评论
	$(".putAwayComment").click(function(){
		var $this = $(this);
		$this.parents(".content_list").find(".comment_height").find(".content_comment").each(function(){
			var thisIndex = $(this).index();
			if(thisIndex<3){
				$(this).show();
			}else{
				$(this).removeAttr("style"); 
				$(this).hide();
			}
		});
		$this.addClass("hidden");
	 
		$(".moreComment").removeClass("hidden");
		
	})

	//点击大图片显示图片的列表
	$(".photo_details").click(function(){
		$(".simple_information").show();
		$(".detailed_information").hide();
	});

	//点击图片列表中的图片返回大图片显示
	$(".simple_information").find("img").click(function(){
		$(".detailed_information").show();
		$(".simple_information").hide();
		$("html, body").animate({ scrollTop: 0 }, 120);
	})

	var touch = 1; //公共变量控制是否滑动屏幕
	var submitHtml; //公共变量控制是否滑动屏幕
	//点击“加入购物车”,“立即购买”按钮
	$(".add_btn, .buy_btn, .select_bar").click(function(){
		if($(".footerBtmFixed div a.add_btn").attr("cartSoldOut")!='1'){
			touch = 0;
			$(".alProd").height(document.documentElement.clientHeight).show();
			$("body").attr("style","overflow:hidden");
			$("body").bind("touchmove",function(e){
				if(touch==0){
					e.preventDefault();
				}
			})
	
			$(".alProdInfo").show();
		}
	});
	
	//点击“加入购物车”按钮
	$(".add_btn").click(function(){
		if($(".footerBtmFixed div a.add_btn").attr("cartSoldOut")!='1'){
			isShoppingCart = true;
	
		}else{
			return jShare("商品已售罄","","");
		}
	});

	//点击“立即购买”按钮
	$(".buy_btn").click(function(){
		if($(".footerBtmFixed div a.buy_btn").attr("buySoldOut")!='1'){
			isShoppingCart = false;
		}else{
			return jShare("商品已售罄","","");
		}
	});
	
	//选择商品后点击“确定”购买
	$(".submit").on("click",".submit_btn",function(){
		gosunmit();
		return false;
	});
	$(".submit").on("click",".addShopCart",function(){
		isShoppingCart = true;
		gosunmit();
		return false;
	});
	$(".submit").on("click",".goBuy",function(){
		isShoppingCart = false;
		gosunmit();
		return false;
	});

	function gosunmit(){
		var color_info = $(".color_info").find("li.curr").length;
		var maxNum = $(".amount_val").attr("maxValue");
		var choiceNum = parseInt($(".amount_val").val());
		var size_info = $(".size_info").find("li.curr").length;
		var id=$(".color_info").find("li.curr").attr("id");
		var issecondprop=$(".color_info").find("li.curr").attr("issecondprop");
		if($(".color_info").length > 0 && color_info==0){			
			$(".color_info").find("h3").eq(0).html(""+firstPropName+"："+"<span class='red'>请选择"+firstPropName+"</span>")
		}else if(color_info==0 && $(".size_info").length > 0 && size_info==0){			
			$(".size_info").find("h3").eq(0).html(""+secondPropName+"："+"<span class='red'>请选择"+secondPropName+"</span>")
		}else if(choiceNum>maxNum){ //选择的件数大于库存的件数时，提示"库存不足"
			return jShare('库存不足',"","");
		}else{
			var id=$(".color_info").find("li.curr").attr("id");
			var issecondprop=$(".color_info").find("li.curr").attr("issecondprop");
			if(issecondprop=='1'){
				var size_info = $("#second_"+id).find("li.curr").length;
				if(size_info==0){
					$(".size_info").find("h3").html(secondPropName+"："+"<span class='red'>请选择尺码</span>")
					return;
				}
				
			}
			$(".alProd, .alProdInfo").hide();
			$("body").removeAttr("style");
			touch = 1;
			if(isShoppingCart==true){
				addCart();
				var $thisShopping = $(".footerBtmFixed").find("span");
				var $thisLeft = $thisShopping.offset().left+5;
				var $thisBottem = 56;
				
				$thisShopping.find("em").html(commodityNumber).show();
				var settlement_accounts= '<div style="left:'+$thisLeft+'px; bottom:'+$thisBottem+'px" class="settlement_accounts"><i></i>商品已加入购物车<a href="#">去结算</a></div>';
				if($("body").find(".settlement_accounts").length==0){
					$("body").append(settlement_accounts);
					setTimeout(function(){
						$(".settlement_accounts").remove();
						
					},3000);

				}
			}else{
				var skuId=$("#buySku").val();
				var productId=$("#productNo").val();
				var amount=$("#buyCount").val();
				var activityId=$("#topicId").val();
				if(skuId==null||skuId==''||amount==null||amount*1<0){
					return jShare('商品信息有误',"","");
				}
				
				if(postArea==2){
					window.location.href=path+"/overseas/order/fill?productId="+productId+"&skuId="+skuId+"&amount="+amount+"&activityId="+activityId;
				}else{
					window.location.href=path+"/buy/now?productId="+productId+"&skuId="+skuId+"&amount="+amount+"&region=1&activityId="+activityId;
				}
			}
		}	
	}


	//选择颜色点击
	$(".color_info ul li").click(function(){
		var $this = $(this);
		if(!$(this).hasClass("soldOut")){
			$this.addClass("curr").siblings().removeClass("curr");
			$("#buyCount").val("1");
			$(".amount_val").val("1");
			$this.parents("div").children("h3").html("颜色："+$this.html());
			//$(".choice_product").find("span").eq(0).removeClass("red").html("已选择"+firstPropName+$this.find("p").html())
			var id="second_"+$this.attr("id");
			if($this.attr("issecondprop")=='1'){
				$(".size_info").each(function(){
					var second_id=$(this).attr("id");
					if(id==second_id){
						$("#"+second_id).removeAttr("style"); 
						var select=0;
						$("#"+second_id+" li").each(function(){
							if($(this).hasClass('curr')){
								$("#buySku").val($(this).children("#sku").val());
								select=1;
								$(".choice_product").find("span").eq(1).removeClass("red").html("已选择"+secondPropName+$(this).html())
								var salePrice=$(this).children("#salePrice").val();
								var marketPrice=$(this).children("#marketPrice").val();
								var isPromotion=$(this).children("#isPromotion");
							
								showPrice(salePrice,marketPrice,isPromotion,"1");
							}
						});
						if(select==0){
							$("#buySku").val("");
							$(".choice_product").find("span").eq(1).removeClass("red").html("");
						}
					}else{
						$(this).attr("style","display:none");
					}
				});
			}else{
				$(".size_info").hide();
				var salePrice=$("#"+id).children("#salePrice").val();
				var marketPrice=$("#"+id).children("#marketPrice").val();
				var sku=$("#"+id).children("#sku").val();
				var isPromotion=$("#"+id).children("#isPromotion");
				showPrice(salePrice,marketPrice,isPromotion,"1");
				$(".choice_product").find("span").eq(1).removeClass("red").html("");
				$("#buySku").val($("#"+id).children("#sku").val());
				
			}
		}
	});

	//选择尺码点击
	$(".size_info ul li").click(function(){
		$("#buyCount").val("1");
		$(".amount_val").val("1");
		var $this = $(this);
		if(!$(this).hasClass("soldOut")){
			$this.addClass("curr").siblings().removeClass("curr");
			$this.parents("div").children("h3").html(secondPropName+"："+$this.html());
			//$(".choice_product").find("span").eq(1).removeClass("red").html("已选择"+secondPropName+$this.html())
			var salePrice=$this.children("#salePrice").val();
			var marketPrice=$this.children("#marketPrice").val();
			var sku=$this.children("#sku").val();
			var isPromotion=$this.children("#isPromotion").val();
			showPrice(salePrice,marketPrice,isPromotion,"1");
			$("#buySku").val(sku);
		}
	
	});

	var commodityNumber = 1;   //公共变量
	// 添加商品数量点击
	$(".amount_add").click(function() {
		/*//return jShare('抱歉，海外商品一次最多购买1件，如需购买1件以上请分开购买！',"","");
		$(".amount_val").val("1");
		commodityNumber = $(".amount_val").val();
		$("#buyCount").val(commodityNumber);*/
		var colorName = $("#color_name").val();
		var sizeName = $("#size_name").val();
		var color_info = $(".color_info").find("li.curr").length;
		var size_info = $(".size_info").find("li.curr").length;
		if(color_info==0 && colorName!=""){
			$(".color_info").find("h3").eq(0).html(""+firstPropName+"："+"<span class='red'>请选择"+firstPropName+"</span>")
		}else if(colorName=="" && $(".size_info").length > 0 && size_info==0){
			$(".size_info").find("h3").eq(0).html(""+sizeName+"："+"<span class='red'>请选择"+sizeName+"</span>")
		}else{
			var id=$(".color_info").find("li.curr").attr("id");
			var issecondprop=$(".color_info").find("li.curr").attr("issecondprop");
			var skuCount=0;
			if(issecondprop=='1'){
				var size_info = $("#second_"+id).find("li.curr").length;
				skuCount=$("#second_"+id).find("li.curr").children("#count").val();
				if(size_info==0){
					$(".size_info").find("h3").html(secondPropName+"："+"<span class='red'>请选择尺码</span>");
					return;
				}
				
			}else if($(".size_info").length > 0 && colorName==""){
				skuCount=$(".size_info").find("li.curr").children("#count").val();
			}else if(color_info== 0 && size_info==0){
				skuCount=$("#second_first_0").children("#count").val();
			}else{
				skuCount=$("#second_"+id).children("#count").val();
			}
			var amount_val = $(".amount_val").val();
			if(skuCount>amount_val*1){
				if(amount_val*1<10){
					$(".amount_val").val(++amount_val);
				}else{
					
					$(".amount_val").val(10);
					return jShare("抱歉！您最多只能购买10件!","","");
				}
				
				
				commodityNumber=$(".amount_val").val();
				$("#buyCount").val(commodityNumber);
			}else{
				return jShare("抱歉！您最多只能购买"+skuCount+"件!","","");
			}
			
		}
	});

	// 删除商品数量点击
	$(".amount_cut").click(function() {

		/*$(".amount_val").val("1");
		commodityNumber = $(".amount_val").val();
		$("#buyCount").val(commodityNumber);*/
		var colorName = $("#color_name").val();
		var sizeName = $("#size_name").val();
		var color_info = $(".color_info").find("li.curr").length;
		var size_info = $(".size_info").find("li.curr").length;
		if(color_info==0 && colorName!=""){
			$(".color_info").find("h3").eq(0).html(""+firstPropName+"："+"<span class='red'>请选择"+firstPropName+"</span>")
		}else if(colorName=="" && $(".size_info").length > 0 && size_info==0){
			$(".size_info").find("h3").eq(0).html(""+sizeName+"："+"<span class='red'>请选择"+sizeName+"</span>")
		}else{
			var id=$(".color_info").find("li.curr").attr("id");
			var issecondprop=$(".color_info").find("li.curr").attr("issecondprop");
			if(issecondprop=='1'){
				var size_info = $("#second_"+id).find("li.curr").length;
				if(size_info==0){
					$(".size_info").find("h3").html(secondPropName+"："+"<span class='red'>请选择尺码</span>");
					return;
				}
				
			}else if( $(".size_info").length > 0 && colorName==""){
				skuCount=$(".size_info").find("li.curr").children("#count").val();
			}else if(color_info== 0 && size_info==0){
				skuCount=$("#second_first_0").children("#count").val();
			}else{
				skuCount=$("#second_"+id).children("#count").val();
			}
			var amount_val = $(".amount_val").val();
			if(amount_val>1){
				$(".amount_val").val(--amount_val);
				commodityNumber=$(".amount_val").val();
				$("#buyCount").val(commodityNumber);
			}
		}
		

	});
	// 输入框中直接修改商品数量
	$(".amount_val").keyup(function() {
	/*	$(".amount_val").val("1");
		commodityNumber = $(".amount_val").val();
		$("#buyCount").val(commodityNumber);*/
		var color_info = $(".color_info").find("li.curr").length;
		var size_info = $(".size_info").find("li.curr").length;
		var colorName = $("#color_name").val();
		var sizeName = $("#size_name").val();
		if(color_info==0 && colorName!=""){
			$(".amount_val").val(1);
			$(".color_info").find("h3").eq(0).html(""+firstPropName+"："+"<span class='red'>请选择"+firstPropName+"</span>")
		}else if(colorName=="" && $(".size_info").length > 0 && size_info==0){
			$(".size_info").find("h3").eq(0).html(""+sizeName+"："+"<span class='red'>请选择"+sizeName+"</span>")
		}else{
			var id=$(".color_info").find("li.curr").attr("id");
			var issecondprop=$(".color_info").find("li.curr").attr("issecondprop");
			var skuCount=0;
			if(issecondprop=='1'){
				var size_info = $("#second_"+id).find("li.curr").length;
				skuCount=$("#second_"+id).find("li.curr").children("#count").val();
				if(size_info==0){
					$(".amount_val").val(1);
					$(".size_info").find("h3").html(secondPropName+"："+"<span class='red'>请选择尺码</span>");
					return;
				}
				
			}else if( $(".size_info").length > 0  && colorName==""){
				skuCount=$(".size_info").find("li.curr").children("#count").val();
			}else if(color_info== 0 && size_info==0){
				skuCount=$("#second_first_0").children("#count").val();
			}else{
				skuCount=$("#second_"+id).children("#count").val();
			}
			var amount_val = $(".amount_val").val();
			if(amount_val<1){
				$(".amount_val").val(1);
				$("#buyCount").val($(".amount_val").val(1));
			}else if(skuCount>10){
				 if(amount_val*1>skuCount){//库存小于选择
					$(".amount_val").val(10);
					return jShare("抱歉！您最多只能购买10件!","","");
				}else if(amount_val>10){
					$(".amount_val").val(10);
					return jShare("抱歉！您最多只能购买10件!","","");
				}else{
					
					$(".amount_val").val(amount_val);
				
				}
			}else{
				if(amount_val*1>skuCount){//库存小于选择
					$(".amount_val").val(skuCount);
					return jShare("抱歉！您最多只能购买"+skuCount+"件!","","");
				}else if(amount_val>10){
					$(".amount_val").val(skuCount);
					return jShare("抱歉！您最多只能购买"+skuCount+"件!","","");
				}else{
					$(".amount_val").val(amount_val);
					
				}
			}
			
			commodityNumber=$(".amount_val").val();
			$("#buyCount").val(commodityNumber);
		}

	})

	//关闭商品弹出层
	$(".close_btn").click(function(){
		$(".alProd, .alProdInfo").hide();
		$("body").removeAttr("style");
		touch = 1;
	})
	
	//购物车商品数量
	$.get(path + "/cart/count",function(data){
		if(data==0 || data==null){
			$("#forCartCount a").html(null);
		}else if(data == -1){
			$("#forCartCount").html(null);
		}else{
			$("#forCartCount").attr("style","display:block");
			$("#forCartCount").html(data);
		}
	});
	
});


function showPrice(salePrice,marketPrice,isPromotion,isLoad){
	if(isLoad!='0'){
		if(isPromotion=='0'){
			$("#forSalsPrice").removeClass("sales");
			$("#forSalsPrice").addClass("not_promotion");
			$(".alProdInfo p b").addClass("not_promotion");
		}else{
			$("#forSalsPrice").addClass("sales");
			$("#forSalsPrice").removeClass("not_promotion");
		}
		$("#forSalsPrice").html("&yen;"+salePrice);
	}else{
		if(isPromotion=='0'){
			$(".alProdInfo p b").addClass("not_promotion");
		}
	}
	if(salePrice*1<marketPrice){
		$(".product_show section article b.market").html("&yen;"+marketPrice);
		$("#forSaleDesc").addClass("red");
		var saleDesc=div(salePrice,marketPrice);
		$("#forSaleDesc").html(saleDesc+"折");
	}else{
		$(".product_show section article b.market").html("");
		$(".product_show section article ul li.red").removeClass("red");
	}
	var color_info = $(".color_info").find("li.buySold").length;//
	var size_info = $(".size_info").find("li.buySold").length;
	$(".alProdInfo p b").html("&yen;"+salePrice);
	
	if(isLoad=='0'){
		if(color_info<=1&&size_info<=1){//只有一个sku
			var count=0;
			if(size_info==0){
				count=$("#second_first_0").children("#count").val();
			}else{
				count=$(".size_info").find("li.buySold").children("#count").val();
			}
			if(count*1>0){
				$(".color_info").find("li.buySold").addClass("curr")
				$(".size_info").find("li.buySold").addClass("curr")
				$(".choice_product").find("span").eq(0).removeClass("red").html("已选择"+firstPropName+$(".color_info").find("li.buySold").find("p").html())
				if(size_info>0){
					$(".choice_product").find("span").eq(1).removeClass("red").html("已选择"+secondPropName+$(".size_info").find("li.buySold").html())
				}
				if(size_info==0){
					$("#buySku").val($("#second_first_0").children("#sku").val());
				}else{
					$("#buySku").val($(".size_info").find("li.buySold").children("#sku").val());
				}
				
			}else{
				$(".footerBtmFixed div a.add_btn").attr("cartSoldOut","1")
				$(".footerBtmFixed div a.buy_btn").attr("buySoldOut","1")
				$("#buyCount").val("0");
			}
		}else if(color_info<=1){
			$(".color_info").find("li").addClass("curr");
			$(".choice_product").find("span").eq(0).removeClass("red").html("已选择"+firstPropName+$(".color_info").find("li").find("p").html())
		}
	}
	
}
//加入购物车
function addCart(){
	var path = getRootPath();
	var productNo = $("#productNo").val();
	var topicNo = $("#topicId").val();
	var sku = $("#buySku").val();
	var count = parseInt($('#buyCount').val());
	if($("#sku").val()==""){
		return jShare('请选择尺码',"","");
	}
	if(count <= 0){
		return jShare('库存不足',"","");
	}
	$.post(path + "/cart/add",{
		"sku" : sku,
		"productNo" : productNo,
		"topicNo" : topicNo,
		"quantity":count
		},
		function(data,textStatus, XMLHttpRequest){
			var sessionstatus=XMLHttpRequest.getResponseHeader("sessionstatus");
			if(sessionstatus=="timeout"){
				var locationURL = XMLHttpRequest.getResponseHeader("locationURLAll"); // 通过XMLHttpRequest取得响应头，locationURL，
				// 如果超时就处理 ，指定要跳转的页面
				window.location.href = locationURL;
			}
			if(data.code == 2){
				window.location = path + "/login?back=cart/list";
			}else if(data.code == 0){
				var $thisShopping = $(".add_btn").parents(".footerBtmFixed").find("span");
				var $thisLeft = $thisShopping.offset().left+5;
				var $thisBottem = 56;
				
				var settlement_accounts= '<div style="left:'+$thisLeft+'px; bottom:'+$thisBottem+'px" class="settlement_accounts"><i></i>商品已加入购物车<a href="'+path+'/cart/list">去结算</a></div>';
				if($("body").find(".settlement_accounts").length==0){
					$("body").append(settlement_accounts);
					setTimeout(function(){
						$(".settlement_accounts").remove();
					},3000);
	
				}
				$.get(path + "/cart/count",function(data1){
					if(data1==0 || data1==null){
						$("#forCartCount").html(null);
					}else if(data1 == -1){
						$("#forCartCount").html(null);
					}else{
						$thisShopping.find("em").html(data1).show();
					}
				});
				var brandName=$("#brandName").val();
				_smq.push(['custom','加入购物车',brandName,productNo,count]);
			}else if(data.code == 1){
				return jShare(data.msg,"","");
			}
	});
}
function div(exp1, exp2)
{
    var n1 = Math.round(exp1); //四舍五入
    var n2 = Math.round(exp2); //四舍五入
    var rslt = n1 / n2; //除
    var value = Math.round(parseFloat(rslt) * 100) / 100;
    return (value*10).toFixed(1);
}


var ready=1;
var num = 0;
//下拉加载
function BottomLoading(){
	var tagId = $("#seleTagId").val();
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
	if(htmlUrlLength !=0){
		if ($(window).scrollTop() + $(window).height() >= $(document).height()-50){
			if(!$('body').find('#loading').length){
				$('.comment_height').append(loadingMsg);
			}
			if(ready==1){
				  $('#loading').show();
				  ready=0;
				  var path = getRootPath();
					$.ajax({
						url : path + "/product/getComment?"+Math.random(),
						data : {
							"productNo" : $('#productNo').val(),
							"pageIndex" : parseInt($('#pageIndex').val())+1,
							"pageSize" : 20,
							"tagId" : tagId
							},
						dataType : "json",
						success : function(data) {
							var len = 0;
							if (data.productComment.list != null) {
								len = data.productComment.list.length;
							}
							if (len > 0) {
								$("#hasMore").val(data.hasMore);
								$("#pageIndex").val(data.pageIndex);
								$.each(data.productComment.list, function(index, item) {
									$div_concom = $("<div></div>");
									$div_concom.addClass("content_comment");
									$div_concom_h = $("<div></div>");
									$div_concom_h.addClass("content_comment hidden");
				
									$div = $("<div></div>");
									$div.addClass("comment_user");
									$div_uc = $("<div></div>");
									$div_uc.addClass("user_cont");
									$div_ucl = $("<div></div>").addClass("user_class");
									$a = $("<a></a>");
									$a.attr({
										"href" : ""
									});
									$img = $("<img></img>");
									var userIcon;
									if(item.userIcon!=null&&item.userIcon!=''){
										userIcon=item.userIcon
									}else{
										userIcon=path+"/styles/shangpin/images/detail/user.jpg";
									}
									$img.attr({
										"src" : userIcon,
										"width":"35px",
										"height":"35px"
									});
									$a.append($img);
									$span = $("<span></span>").addClass("user_name").text(
											item.userName);
									/*$span_st = $("<span></span>").addClass(
											"starlevel_" + item.stars);*/
									$span_ct = $("<span></span>").addClass("comment_time")
											.text(item.strDate);
				
									$div_uc.append($a);
									$div_uc.append($span);
									/*$.each(item.tags, function(n, v) {
										$span_us = $("<span></span>").addClass("user_size");
										$span_us.text(v);
										$div_uc.append($span_us);
									});*/
									/*$div_ucl.append($span_st);*/
									$div_ucl.append($span_ct);
									$div.append($div_uc);
									$div.append($div_ucl);
				
									$div_cmt = $("<div></div>");
									$div_cmt.addClass("comment_cont");
									$p = $("<p></p>").text(item.desc);
									$div_cmt.append($p);
				
									$div_cImg = $("<div></div>");
									$div_cImg.addClass("comment_img");
									$ul = $("<ul></ul>")
									$.each(item.pics, function(n1, v1) {
										$li = $("<li></li>");
										$img = $("<img></img>");
										$img.attr({
											"src" : v1
										});
										$li.append($img);
										$ul.append($li);
									});
									$div_cImg.append($ul);
				
									$p1 = $("<p></p>");
									$span0 = $("<span></span>");
									$span1 = $("<span></span>");
									$span2 = $("<span></span>");
									
									$.each(item.tags, function(n, v) {
										$span0.text(v);
									});
									if(item.productInfo!=null&&item.productInfo!=''){
										$span1.text(item.productInfo);
									}
									if(item.userInfo!=null&&item.userInfo!=''){
										$span2.text(item.userInfo);
									}
									$p1.append($span0);
									$p1.append($span1);
									$p1.append($span2);
									$div_cImg.append($p1);
									
									$div_reply = $("<div></div>");
									$.each(item.reply, function(n2, v2) {
										
										if(v2.from!=null||v2.desc!=null){
											$div_reply.addClass("comment_reply");
					
											$i = $("<i></i>");
											$h6 = $("<h6></h6>").text(v2.from);
											$p = $("<p></p>").text(v2.desc);
											$div_reply.append($i);
											$div_reply.append($h6);
											$div_reply.append($p);
										}
									});
									$div_concom.append($div);
									$div_concom.append($div_cmt);
									$div_concom.append($div_cImg);
									$div_concom.append($div_reply);
									$(".comment_height").append($div_concom);
				
								});
								
								$("#pageIndex").val(start*1+1);
								
							}else{
								if(start==1){
									$(".comment_height").append("<div class='no_comment'> <p>暂无评论</p> </div>");
								}
						            
							}
							num++;
							ready=1;
							$('#pageIndex').val(data.pageIndex);
						  },
						  complete:function(){
								$('#loading').remove();
						  },
						  error : function(){
							  $('#loading').remove();
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