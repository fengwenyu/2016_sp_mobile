var menuTopHeight=0;
var secondPropName=$("#secondPropName").val();
var firstPropName=$("#firstPropName").val();
$(function(){
	var lowestPrice=$("#lowestInfo").attr("lowestPrice");
	var marketPrice=$("#lowestInfo").attr("marketPrice");
	var isPromotion=$("#lowestInfo").attr("isPromotion");
	showPrice(lowestPrice,marketPrice,isPromotion,"0");
	
	var path = getRootPath();

	//显示导航浮层方法
	$(window).scroll(topFixed);
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


	//点击收藏效果
	$(".collection_commodity").click(function(){
        if(!$(this).hasClass("already_collection")){
        	collect();
        }else{
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
		if(id=="product_tab_1"){
			$(".product_introduction").show();
			$(".other_service").show();
			$(".list-box").show();
			$(".content_info").find(".content_list").eq(0).show().siblings().hide();
		}
		if(id=="product_tab_2"){
			if(sizeFlag==1){
				getSize();
				sizeFlag==0;
			}
			$("#for_aftersale").hide();
			$(".other_service").hide();
			$(".list-box").hide();
			$(".content_info").find(".content_list").eq(1).show().siblings().hide();
		} 
		if(id=="product_tab_3"){
//			getTemplate();
			$("#for_aftersale").hide();
			$(".other_service").hide();
			$(".list-box").hide();
			$(".content_info").find(".content_list").eq(2).show().siblings().hide();
		}
		if(id=="product_tab_4"){
			$("#for_aftersale").hide();
			$(".other_service").hide();
			$(".list-box").hide();
			if(tabFlag==1){
				getComment(0);
				tabFlag=0;
			}
		
			$(".content_info").find(".content_list").eq(3).show().siblings().hide();
		}
		
		
		//滚动屏幕时，发请求获取更多的评论数据
		if($("#for_comments").is(":visible")){
			$(window).scroll(function(){
				var scrollTop = $(this).scrollTop(),
				scrollHeight = $(document).height(),
				windowHeight = $(this).height(),
				comment_list="";  //发请求时添加到页面的内容
				//页面滚动到最底部，并且“查看更多评论”的内容显示出来了
				if(scrollTop + windowHeight == scrollHeight && $(".moreComment").is(":hidden")){
					//发请求start
					getComment(1);
					//发请求end
				}
			})
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
	
	//点击“加入购物车”,“立即购买”按钮
	$(".add_btn, .buy_btn").click(function(){
		if($(".footerBtmFixed div a.add_btn").attr("cartSoldOut")!='1'){
			touch = 0;
			$(".alProd").height(document.documentElement.clientWidth).show();
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
	$(".submit_btn").click(function(){
		var color_info = $(".color_info").find("li.curr").length;
		if(color_info==0){
			$(".choice_product").find("span").eq(0).addClass("red").html("请选择"+firstPropName)
		}else{
			var id=$(".color_info").find("li.curr").attr("id");
			var issecondprop=$(".color_info").find("li.curr").attr("issecondprop");
			if(issecondprop=='1'){
				var size_info = $("#second_"+id).find("li.curr").length;
				if(size_info==0){
					$(".choice_product").find("span").eq(1).addClass("red").html("请选择"+secondPropName)
					return;
				}
				
			}
			$(".alProd, .alProdInfo").hide();
			$("body").removeAttr("style");
			touch = 1;
			if(isShoppingCart==true){
			
				addCart();
				
			}else{
					var skuId=$("#buySku").val();
					var productId=$("#productNo").val();
					var amount=$("#buyCount").val();
					var activityId=$("#topicId").val();
					if(skuId==null||skuId==''||amount==null||amount*1<0){
						return jShare('商品信息有误',"","");
					}
					
					window.location.href=path+"/buy/now?productId="+productId+"&skuId="+skuId+"&amount="+amount+"&region=1&activityId="+activityId;
			}
		}
	});

	//选择颜色点击
	$(".color_info ul li").click(function(){
		var $this = $(this);
		if(!$(this).hasClass("soldOut")){
			$this.addClass("curr").siblings().removeClass("curr");
			$("#buyCount").val("1");
			$(".amount_val").val("1");
			$(".choice_product").find("span").eq(0).removeClass("red").html("已选择"+firstPropName+$this.find("p").html())
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
		var $this = $(this);
		if(!$(this).hasClass("soldOut")){
			$this.addClass("curr").siblings().removeClass("curr");
			$(".choice_product").find("span").eq(1).removeClass("red").html("已选择"+secondPropName+$this.html())
			var salePrice=$this.children("#salePrice").val();
			var marketPrice=$this.children("#marketPrice").val();
			var sku=$this.children("#sku").val();
			var isPromotion=$this.children("#isPromotion").val();
			showPrice(salePrice,marketPrice,isPromotion,"1");
			$("#buySku").val(sku);
		}
	
	});

	//添加商品数量点击
	$(".amount_add").click(function(){
		var color_info = $(".color_info").find("li.curr").length;
		var size_info = $(".size_info").find("li.curr").length;
		if(color_info==0){
			$(".choice_product").find("span").eq(0).addClass("red").html("请选择"+firstPropName)
		}else{
			var id=$(".color_info").find("li.curr").attr("id");
			var issecondprop=$(".color_info").find("li.curr").attr("issecondprop");
			var skuCount=0;
			if(issecondprop=='1'){
				var size_info = $("#second_"+id).find("li.curr").length;
				skuCount=$("#second_"+id).find("li.curr").children("#count").val();
				if(size_info==0){
					$(".choice_product").find("span").eq(1).addClass("red").html("请选择"+secondPropName)
					return;
				}
				
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

	//删除商品数量点击
	$(".amount_cut").click(function(){
		var color_info = $(".color_info").find("li.curr").length;
		var size_info = $(".size_info").find("li.curr").length;
		if(color_info==0){
			$(".choice_product").find("span").eq(0).addClass("red").html("请选择"+firstPropName)
		}else{
			var id=$(".color_info").find("li.curr").attr("id");
			var issecondprop=$(".color_info").find("li.curr").attr("issecondprop");
			if(issecondprop=='1'){
				var size_info = $("#second_"+id).find("li.curr").length;
				if(size_info==0){
					$(".choice_product").find("span").eq(1).addClass("red").html("请选择"+secondPropName)
					return;
				}
				
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
	//输入框中直接修改商品数量
	$(".amount_val").keyup(function(){
		var color_info = $(".color_info").find("li.curr").length;
		var size_info = $(".size_info").find("li.curr").length;
		if(color_info==0){
			$(".amount_val").val(1);
			$(".choice_product").find("span").eq(0).addClass("red").html("请选择"+firstPropName)
		}else{
			var id=$(".color_info").find("li.curr").attr("id");
			var issecondprop=$(".color_info").find("li.curr").attr("issecondprop");
			var skuCount=0;
			if(issecondprop=='1'){
				var size_info = $("#second_"+id).find("li.curr").length;
				skuCount=$("#second_"+id).find("li.curr").children("#count").val();
				if(size_info==0){
					$(".amount_val").val(1);
					$(".choice_product").find("span").eq(1).addClass("red").html("请选择"+secondPropName)
					return;
				}
				
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