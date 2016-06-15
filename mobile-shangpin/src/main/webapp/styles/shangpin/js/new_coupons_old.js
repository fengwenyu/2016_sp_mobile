// JavaScript Document

$(function(){
	//tab 切换
	var tabs = function(nav,content){
		$(nav).find("li").bind("click",function(){
			var index = $(this).index();
			$(this).addClass("cur").siblings().removeClass("cur");
			$(content).eq(index).show().siblings(content).hide();
		});
	}
	
	tabs(".tabs_menu", ".tabs_Cell");
	
	//选择优惠券
	$(".select_coupon").delegate('li','click',function(){
		var path = getRootPath();
		$(this).siblings().find('b').removeClass("coupons_selected");
		$(this).find('b').toggleClass("coupons_selected");
		$(".code_result").hide();
		$("#sale_code").val("")
		var giftCard = parseInt($("#giftCard").text());//礼品卡金额
		var product_total_price = parseInt($.trim($("#product_total_price").text()).substring(1));//商品总金额
		var carriage = parseInt($.trim($("#carriage").text()).substring(1));//运费
		if($("#nocarriage").length > 0){
			var nocarriage = parseInt($.trim($("#nocarriage").text()).substring(2));//免运费金额
		}else{
			var nocarriage = 0;//免运费金额
		}
		var order_total = product_total_price + carriage - nocarriage;
		var fact_pay = parseInt($("#real_pay b").text());
		//如果选择使用优惠券或者折扣码
		if($(this).find('b').hasClass("coupons_selected")){
			$("#span_pay_coupon").show();
			var couponId = $(".coupons_selected").siblings("input").val();
			$("#couponflag").val("1");
			$("#coupon").val(couponId);
			var buyGids = "";
			$(".pord_info").each(function(){
				buyGids += $(this).attr("id") + "|";
			});
			buyGids=buyGids.substr(0,buyGids.length-1);
			var addressId = $("#addrid").val();
			var orderSource = $("#orderSource").val();
			var coupon_amount;
			//发送优惠券码，返回优惠信息
			$.post(path + "/coupon/info",{
				"couponFlag" : 1,
				"coupon" : couponId,
				"buyGids" : buyGids,
				"addressId" : addressId,
				"orderSource" : orderSource
			},function(data){
				if(data.msgcode == 0){
					$("#online_pay").val(data.couponVO.payamount);
					$("#coupon_payamount").val(data.couponVO.payamount);
					var count = data.couponVO.discountamount;
					coupon_amount = parseInt(count);
					$("#pay_coupon b").text(coupon_amount);
					$("#pro_amount").text(coupon_amount);
					$("#span_pay_coupon").show();
					$("#use_conpus").text("已优惠" + coupon_amount);
					//判断是否有运费
					if(data.couponVO.carriage != '' && data.couponVO.carriage != '0'){
						$("#carriage").text("￥" + data.couponVO.carriage);
						$("#span_carriage").show();
						$("#nocarriage").text("-￥" + 0);
						$("#span_nocarriage").hide();
					}
					var coupon_payamount = parseInt($("#coupon_payamount").val());//在线支付金额
					//判断在线支付金额是否为0，如果为0则不需要其他支付方式
					if(coupon_payamount == 0){
						$("#mainPay").val("99");
						$("#subPay").val("99");
						if($(".giftCard a").hasClass("cur")){
							$(".giftCard a i").remove(".card_pay");
							$("#pay_card b").text("0");
							$("#span_pay_card").hide();
							$(".giftCard a").removeClass("cur");
							$("#pay_coupon b").text(coupon_amount);
							$("#span_pay_coupon").show();
							$("#real_pay b").text("0");
							$(".price em i b").text("0");
							$(".total_amount em b").text("0");
							$("#surplus").text($("#real_pay b").text());
						}else{
							$("#pay_coupon b").text(coupon_amount);
							$("#span_pay_coupon").show();
							$("#real_pay b").text("0");
							$(".price em i b").text("0");
							$(".total_amount em b").text("0");
							$("#surplus").text($("#real_pay b").text());
						}
					}else{
						var sperade_price = parseInt(data.couponVO.payamount);
						//如果使用礼品卡
						if($(".giftCard a").hasClass("cur")){
							$(".giftCard a i").remove(".card_pay");
							$("#span_pay_card").show();
							if(giftCard >= sperade_price){
								$("#pay_card b").text(sperade_price);
								$("#real_pay b").text("0");
								$(".price em i b").text("0");
								$(".total_amount em b").text("0");
								$(".giftCard a").append("<i class='card_pay'>使用礼品卡为本次支付 &yen;" + sperade_price + "</i>");
								$("#surplus").text($("#real_pay b").text());
							}else{
								$("#pay_card b").text(giftCard);
								var pay = sperade_price - giftCard;
								$("#real_pay b").text(pay);
								$(".price em i b").text(pay);
								$(".total_amount em b").text(pay);
								$(".giftCard a").append("<i class='card_pay'>使用礼品卡为本次支付 &yen;" + giftCard + "</i>");
								$("#surplus").text($("#real_pay b").text());
							}
						}else{
							$("#pay_coupon b").text(coupon_amount);
							$("#span_pay_coupon").show();
							$("#real_pay b").text(sperade_price);
							$(".price em i b").text(sperade_price);
							$(".total_amount em b").text(sperade_price);
							$("#surplus").text($("#real_pay b").text());
						}
					}
				}else{
					alert("对不起，你不能使用该优惠券！");
					return;
				}
			},"json");
		}else{
			//未使用优惠
			$("#couponflag").val("");
			$("#coupon").val("0");
			$("#pay_coupon b").text("0");
			$("#pro_amount").text("0");
			$("#span_pay_coupon").hide();
			var totalCoupon = $("#totalCoupon").val();
			$("#use_conpus").html("您有 <em>" + totalCoupon + "</em> 张优惠券可用");
			var ori_carriage = parseInt($("#ori_carriage").val());
			var ori_nocarriage = parseInt($("#ori_nocarriage").val());
			if(ori_carriage != '' && ori_carriage != 0){
				$("#carriage").html("&nbsp;&nbsp;&nbsp;&yen;&nbsp;" + ori_carriage);
				$("#span_carriage").show();
				$("#nocarriage").text("-￥" + 0);
				$("#span_nocarriage").hide();
			}
			if(ori_nocarriage != '' && ori_nocarriage != 0){
				$("#carriage").html("&nbsp;&nbsp;&nbsp;&yen;&nbsp;" + ori_carriage);
				$("#span_carriage").show();
				$("#nocarriage").html("&nbsp;&nbsp;-&yen;&nbsp;" + ori_nocarriage);
				$("#span_nocarriage").show();
			}
			var pay_real = product_total_price + ori_carriage - ori_nocarriage;
			//还原默认支付方式
			$("#mainPay").val("20");
			$("#subPay").val("37");
			$(".paymet_block fieldset p span a").removeClass("cur");
			$(".alipay a").addClass("cur");
			if($(".giftCard a").hasClass("cur")){
				$("#span_pay_card").show();
				$(".giftCard a i").remove(".card_pay");
				if(giftCard >= pay_real){
					$("#pay_card b").text(pay_real);
					//虽然礼品卡的金额大于订单的总金额，但是还需要支付运费，因为运费的标准是根据用户最后实际需要支付的金额定的。
					$("#real_pay b").text("0");
					$(".price em i b").text("0");
					$(".total_amount em b").text("0");
					$(".giftCard a").append("<i class='card_pay'>使用礼品卡为本次支付 &yen;" + pay_real + "</i>")
					$("#surplus").text($("#real_pay b").text());
				}else{
					$("#pay_card b").text(giftCard);
					var pay = pay_real - giftCard;
					$("#real_pay b").text(pay);
					$(".price em i b").text(pay);
					$(".total_amount em b").text(pay);
					$(".giftCard a").append("<i class='card_pay'>使用礼品卡为本次支付 &yen;" + giftCard + "</i>")
					$("#surplus").text($("#real_pay b").text());
				}
			}else{
				$("#span_pay_card").hide();
				$("#pay_card b").text("0");
				$("#real_pay b").text(pay_real);
				$(".price em i b").text(pay_real);
				$(".total_amount em b").text(pay_real);
				$("#surplus").text($("#real_pay b").text());
			}
		}
		showPage('order_detail','order_header');
		return false;
	});
	
	//获取焦点按钮变高亮
	$(".coupons_active input").focus(function(){
		$(this).next().addClass('coupons_submited');
	});
	//失去焦点按钮变高亮
	$(".coupons_active input").blur(function(){
		if(!$(this).val()>0){
			$(this).parent().find('.coupons_submited').removeClass('coupons_submited');
		}
		
	});
	
});

//获取折扣码信息
function promoCodeInfo(){
	var path = getRootPath();
	var sale_code = $("#sale_code").val();
	if ($.trim($("#sale_code").val()) == "" ){
		return jShare('请输入折扣码!',"",""),
		$("#sale_code").addClass("error"),
		!1;
	}else{
		$("#sale_code").removeClass("error");
	}
	var buyGids = "";
	var orderSource = $("#orderSource").val();
	var addressId = $("#addrid").val();
	if(orderSource=='2'){
		$(".pord_info").each(function(){
			buyGids = $(this).attr("id");
		});
	}else{
		$(".pord_info").each(function(){
			buyGids += $(this).attr("id") + "|";
		});
	}
	$.post(path + "/coupon/info",{
		"couponFlag" : 2,
		"coupon" : sale_code,
		"buyGids" : buyGids,
		"addressId" : addressId,
		"orderSource" : orderSource
	},function(data){
		if(data.msgcode == 0){
			$(".code_result").show();
			var count = data.couponVO.discountamount;//打折金额
			$("#prom_code").text(sale_code);
			$("#pro_amount").text(count);
			$("#coupon_carriage").val(data.couponVO.carriage);//运费
			$("#coupon_payamount").val(data.couponVO.payamount);//在线支付金额
			//判断是否有运费
			if(data.couponVO.carriage != '' && data.couponVO.carriage != '0'){
				$("#carriage").html("&nbsp;&nbsp;&nbsp;&yen;&nbsp;" + data.couponVO.carriage);
				$("#span_carriage").show();
				$("#nocarriage").text("-￥" + 0);
				$("#span_nocarriage").hide();
			}
			//使用折扣码
			usePromoCode();
		}else{
			//alert("该折扣码不适用购物车中的商品！");
			alert(data.msg);
			return;
		}
	},"json");
}

//确认使用折扣码
function usePromoCode(){
	var sale_code = $("#sale_code").val();
	$("#coupon").val(sale_code);
	$("#couponflag").val("2");
	var giftCard = parseInt($("#giftCard").text());//礼品卡金额
	var product_total_price = parseInt($.trim($("#product_total_price").text()).substring(1));//商品总金额
	var carriage = parseInt($.trim($("#carriage").text()).substring(2));//运费
	var nocarriage = parseInt($.trim($("#nocarriage").text()).substring(2));//免运费金额
	var order_total = product_total_price + carriage - nocarriage;
	//优惠金额
	var coupon_amount = parseInt($("#pro_amount").text());
	$(".select_coupon li").siblings().find('b').removeClass("coupons_selected");
	var coupon_payamount = parseInt($("#coupon_payamount").val());//在线支付金额
	$("#use_conpus").text("已优惠" + $("#pro_amount").text());
	//判断在线支付金额是否为0，如果为0表示全额支付已经不需要其他支付方式了
	if(coupon_payamount == 0){
		$("#mainPay").val("99");
		$("#subPay").val("99");
		if($(".giftCard a").hasClass("cur")){//判断之前是否选择了礼品卡支付
			$(".giftCard a i").remove(".card_pay");
			$("#pay_card b").text("0");
			$("#span_pay_card").hide();
			$(".giftCard a").removeClass("cur");
			$("#pay_coupon b").text(coupon_amount);
			$("#span_pay_coupon").show();
			$("#real_pay b").text("0");
			$(".price em i b").text("0");
			$(".total_amount em b").text("0");
			$("#surplus").text($("#real_pay b").text());
		}else{
			$("#pay_coupon b").text(coupon_amount);
			$("#span_pay_coupon").show();
			$("#real_pay b").text("0");
			$(".price em i b").text("0");
			$(".total_amount em b").text("0");
			$("#surplus").text($("#real_pay b").text());
		}
	}else{//还需要在线支付
		//判断是否使用了礼品卡
		if($(".giftCard a").hasClass("cur")){//使用礼品卡
			var sperade_price = coupon_payamount;
			if(giftCard >= sperade_price){
				$("#pay_coupon b").text(coupon_amount);
				$("#span_pay_coupon").show();
				$("#pay_card b").text(sperade_price);
				$("#span_pay_card").show();
				$("#real_pay b").text("0");
				$(".price em i b").text("0");
				$(".total_amount em b").text("0");
				$(".giftCard a i").remove(".card_pay");
				$(".giftCard a").append("<i class='card_pay'>使用礼品卡为本次支付 &yen;" + sperade_price + "</i>");
				$("#surplus").text($("#real_pay b").text());
			}else{
				$("#pay_coupon b").text(coupon_amount);
				$("#span_pay_coupon").show();
				$("#pay_card b").text(giftCard);
				$("#span_pay_card").show();
				var pay = sperade_price - giftCard;
				$("#real_pay b").text(pay);
				$(".price em i b").text(pay);
				$(".total_amount em b").text(pay);
				$(".giftCard a i").remove(".card_pay");
				$(".giftCard a").append("<i class='card_pay'>使用礼品卡为本次支付 &yen;" + giftCard + "</i>");
				$("#surplus").text($("#real_pay b").text());
			}
		}else{
			$("#pay_coupon b").text(coupon_amount);
			$("#span_pay_coupon").show();
			$("#real_pay b").text(coupon_payamount);
			$(".price em i b").text(coupon_payamount);
			$(".total_amount em b").text(coupon_payamount);
			$("#surplus").text($("#real_pay b").text());
		}
	}
	$("#surplus").text($("#real_pay b").text());
	showPage("order_detail", "order_header");
}

//取消使用折扣码
function cannelPromoCode(){
	//清空折扣信息
	$("#sale_code").val("");
	$("#coupon_payamount").val("");
	$("#coupon_carriage").val("");
	$("#pro_amount").text("");
	$("#prom_code").text("");
	$(".code_result").hide();
	//取消使用折扣码
	$("#coupon").val("");
	$("#couponflag").val("0");
	//隐藏使用优惠金额，并将值设置为0
	$("#pay_coupon b").text("0");
	$("#span_pay_coupon").hide();
	//还原默认支付方式
	$("#mainPay").val("20");
	$("#subPay").val("37");
	$(".paymet_block fieldset p span a").removeClass("cur");
	$(".alipay a").addClass("cur");
	//从新显示正确的商品价格、运费、礼品卡支付金额
	var giftCard = parseInt($("#giftCard").text());//礼品卡金额
	var product_total_price = parseInt($.trim($("#product_total_price").text()).substring(1));//商品总金额
	var ori_carriage = parseInt($("#ori_carriage").val());
	var ori_nocarriage = parseInt($("#ori_nocarriage").val());
	var totalCoupon = $("#totalCoupon").val();
	$("#use_conpus").html("您有 <em>" + totalCoupon + "</em> 张优惠券可用");
	if(ori_carriage != '' && ori_carriage != 0){
		$("#carriage").html("&nbsp;&nbsp;&nbsp;&yen;&nbsp;" + ori_carriage);
		$("#span_carriage").show();
		$("#nocarriage").text("-￥" + 0);
		$("#span_nocarriage").hide();
	}
	if(ori_nocarriage != '' && ori_nocarriage != 0){
		$("#carriage").html("&nbsp;&nbsp;&nbsp;&yen;&nbsp;" + ori_carriage);
		$("#span_carriage").show();
		$("#nocarriage").text("-￥" + ori_nocarriage);
		$("#span_nocarriage").show();
	}
	var pay_real = product_total_price + ori_carriage - ori_nocarriage;
	//判断是否使用了礼品卡
	if($(".giftCard a").hasClass("cur")){//使用了礼品卡
		if(giftCard >= pay_real){
			$("#pay_card b").text(pay_real);
			$("span_pay_card").show();
			$(".giftCard a i").remove(".card_pay");
			$(".giftCard a").append("<i class='card_pay'>使用礼品卡为本次支付 &yen;" + pay_real + "</i>");
			$("#real_pay b").text("0")
			$(".price em i b").text("0");
			$(".total_amount em b").text("0");
			$("#surplus").text($("#real_pay b").text())
		}else{
			$("#pay_card b").text(giftCard);
			$("span_pay_card").show();
			$(".giftCard a i").remove(".card_pay");
			$(".giftCard a").append("<i class='card_pay'>使用礼品卡为本次支付 &yen;" + giftCard + "</i>");
			$("#real_pay b").text(pay_real - giftCard);
			$(".price em i b").text(pay_real - giftCard);
			$(".total_amount em b").text(pay_real - giftCard);
			$("#surplus").text($("#real_pay b").text());
		}
	}else{
		$("#isUseGiftCardPay").val("0");
		$("#pay_card b").text("0");
		$("#span_pay_card").hide();
		$(".giftCard a i").remove(".card_pay");
		$(".tips").text("");
		$("#real_pay b").text(pay_real);
		$(".price em i b").text(pay_real);
		$(".total_amount em b").text(pay_real);
		$("#surplus").text($("#real_pay b").text());
	}
}

