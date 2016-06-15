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
	
	/*//选择优惠券
	$(".select_coupon").delegate('li','click',function(){
		var path = getRootPath();
		$(this).siblings().find('b').removeClass("coupons_selected");
		$(this).find('b').toggleClass("coupons_selected");
		$(".code_result").hide();
		$("#sale_code").val("");
		var coupon_code = $(this).find("input").val();
		if($("#giftCard").length > 0){
			var giftCard = parseInt($("#giftCard").text());//礼品卡金额
		}else{
			var giftCard = "0";
		}
		var product_total_price = parseInt($.trim($("#product_total_price").text()).substring(1));//商品总金额
		//满减金额
		if($("#promoto_price").length > 0){
			var promoAmount = $.trim($.trim($("#promoto_price").text()).substring(2));
		}else{
			var promoAmount = "0";
		}
		//优惠券金额
		var ticketAmount = $.trim($.trim($(this).find("i").text()).substring(1));
		//购买商品的shodDetailId串
		var buyGids = "";
		var orderSource = $.trim($("#orderSource").val());
		if(orderSource=='2'){
			$(".pord_info").each(function(){
				buyGids = $(this).attr("id");
			});
		}else{
			$(".pord_info").each(function(){
				buyGids += $(this).attr("id") + "|";
			});
		}
		//运费
		var carriageAmount = $.trim($.trim($("#carriageAmount").val()));
		var type;
		if($(this).find("b").hasClass("coupons_selected")){//表示使用优惠券
			$("#dis_count_code").val("0");
			if($("#span_pay_card").length > 0){//表示使用礼品卡
				type = "1|3";
			}else{
				type = "1";
			}
		}else{
			if($("#span_pay_card").length > 0){//表示使用礼品卡
				type = "3";
			}else{
				type = "";
			}
		}
		
		$.post(path + "/coupon/user/coupon",{
			type : type,
			totalAmount : product_total_price,
			promoAmount : promoAmount,
			ticketAmount : ticketAmount,
			discountCode : "0",
			giftCardAmount : giftCard,
			carriageAmount : carriageAmount,
			postArea : "1",
			buyIds : buyGids,
			orderSource : orderSource
		},function(data){
			console.log("priceShow:" + data);
			$(".total").html(data);
			var amount = $("#real_pay b").text();
			$(".price em i b").text(amount);
			$("#surplus").text(amount);
			$(".total_amount em b").text(amount)
			if($("#span_pay_coupon").length > 0){
				var coupon_amount = $.trim($("#pay_coupon b").text());
				$("#use_conpus").text("已优惠" + coupon_amount);
				$("#couponflag").val("1");
				$("#coupon").val(coupon_code);
			}else{
				var totalCoupon = $("#totalCoupon").val();
				$("#use_conpus").html("您有 <em>" + totalCoupon + "</em> 张优惠券可用");
				$("#couponflag").val("");
				$("#coupon").val("0");
			}
			if($("#span_pay_card").length == 0 && amount == "0"){
				$("#mainPay").val("99");
				$("#subPay").val("99");
			}
		},"html");
		showPage('order_detail','order_header');
		return false;
	});*/
	
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
	var sale_code = $.trim($("#sale_code").val());
	if ($.trim($("#sale_code").val()) == "" ){
		return jShare('请输入折扣码!',"",""),
		$("#sale_code").addClass("error"),
		!1;
	}else{
		$("#sale_code").removeClass("error");
	}
	$("#dis_count_code").val("1");
	if($("#giftCard").length > 0){
		var giftCard = parseInt($("#giftCard").text());//礼品卡金额
	}else{
		var giftCard = "0";
	}
	var product_total_price = parseInt($.trim($("#product_total_price").text()).substring(1));//商品总金额
	//满减金额
	if($("#promoto_price").length > 0){
		var promoAmount = $.trim($.trim($("#promoto_price").text()).substring(2));
	}else{
		var promoAmount = "0";
	}
	
	//购买商品的shodDetailId串
	var buyGids = "";
	var orderSource = $.trim($("#orderSource").val());
	if(orderSource=='2'){
		$(".pord_info").each(function(){
			buyGids = $(this).attr("id");
		});
	}else{
		$(".pord_info").each(function(){
			buyGids += $(this).attr("id") + "|";
		});
	}
	//运费
	var carriageAmount = $.trim($.trim($("#carriageAmount").val()));
	var type;
	if($(".select_coupon li").find("b").hasClass("coupons_selected")){
		$(this).removeClass("coupons_selected");
	}
	if($("#span_pay_card").length > 0){//表示使用礼品卡
		type = "2|3";
	}else{
		type = "2";
	}
	
	$.post(path + "/coupon/user/coupon",{
		type : type,
		totalAmount : product_total_price,
		promoAmount : promoAmount,
		ticketAmount : "0",
		discountCode : sale_code,
		giftCardAmount : giftCard,
		carriageAmount : carriageAmount,
		postArea : "1",
		buyIds : buyGids,
		orderSource : orderSource
	},function(data,textStatus, XMLHttpRequest){
		var result = XMLHttpRequest.getResponseHeader("couon_return");
		if(result == "1"){
			alert("折扣码不支持该订单内的商品！");
			return;
		}
		console.log("priceShow:" + data);
		$(".total").html(data);
		var amount = $("#real_pay b").text();
		$(".price em i b").text(amount);
		$("#surplus").text(amount);
		$(".total_amount em b").text(amount)
		if($("#span_pay_coupon").length > 0){
			var coupon_amount = $.trim($("#pay_coupon b").text());
			$("#use_conpus").text("已优惠" + coupon_amount);
			$("#pro_amount").text(coupon_amount);
			$(".code_result").show();
			$("#couponflag").val("2");
			$("#coupon").val(sale_code);
		}else{
			var totalCoupon = $("#totalCoupon").val();
			$("#use_conpus").html("您有 <em>" + totalCoupon + "</em> 张优惠券可用");
		}
		if($("#span_pay_card").length == 0 && amount == "0"){
			$("#mainPay").val("99");
			$("#subPay").val("99");
		}
	},"html");
	showPage("order_detail", "order_header");
}

//取消使用折扣码
function cannelPromoCode(){
	var path = getRootPath();
	$("#sale_code").val("");
	$("#pro_amount").text("");
	$(".code_result").hide();
	$("#dis_count_code").val("0");
	if($("#giftCard").length > 0){
		var giftCard = parseInt($("#giftCard").text());//礼品卡金额
	}else{
		var giftCard = "0";
	}
	var product_total_price = parseInt($.trim($("#product_total_price").text()).substring(1));//商品总金额
	//满减金额
	if($("#promoto_price").length > 0){
		var promoAmount = $.trim($.trim($("#promoto_price").text()).substring(2));
	}else{
		var promoAmount = "0";
	}
	
	//购买商品的shodDetailId串
	var buyGids = "";
	var orderSource = $.trim($("#orderSource").val());
	if(orderSource=='2'){
		$(".pord_info").each(function(){
			buyGids = $(this).attr("id");
		});
	}else{
		$(".pord_info").each(function(){
			buyGids += $(this).attr("id") + "|";
		});
	}
	//运费
	var carriageAmount = $.trim($.trim($("#carriageAmount").val()));
	var type;
	if($(".select_coupon li").find("b").hasClass("coupons_selected")){
		$(this).removeClass("coupons_selected");
	}
	if($("#span_pay_card").length > 0){//表示使用礼品卡
		type = "3";
	}else{
		type = "";
	}
	
	$.post(path + "/coupon/user/coupon",{
		type : type,
		totalAmount : product_total_price,
		promoAmount : promoAmount,
		ticketAmount : "0",
		discountCode : "0",
		giftCardAmount : giftCard,
		carriageAmount : carriageAmount,
		postArea : "1",
		buyIds : buyGids,
		orderSource : orderSource
	},function(data){
		console.log("priceShow:" + data);
		$(".total").html(data);
		var amount = $("#real_pay b").text();
		$(".price em i b").text(amount);
		$("#surplus").text(amount);
		$(".total_amount em b").text(amount)
		var totalCoupon = $("#totalCoupon").val();
		$("#use_conpus").html("您有 <em>" + totalCoupon + "</em> 张优惠券可用");
		$("#couponflag").val("");
		$("#coupon").val("0");
		if($("#span_pay_card").length == 0 && amount == "0"){
			$("#mainPay").val("99");
			$("#subPay").val("99");
		}
	},"html");
}

