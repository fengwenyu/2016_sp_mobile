$(function(){
	$(window).bind('beforeunload', function() {
		return "确定要离开订单提交页吗";
	});
//	if (window.history && window.history.pushState) {
//		$(window).on('popstate', function () {
//			var hashLocation = location.hash;
//			console.log(hashLocation);
//			var hashSplit = hashLocation.split("");
//			console.log(hashSplit);
//			var hashName = hashSplit[1];
//			console.log(hashName);
//			if (hashName != '') {
//				var hash = window.location.hash;
//				//console.log(hash);
//				if (hash == '') {
//					if(confirm('确定要离开订单提交页吗？')){
//						//history.go(-1);
//						var path = getRootPath();
//						window.location.href = path + "/cart/list";
//					}
//				}
//			}
//		});
//		window.history.pushState("", null, "");
//	}
	$('.other-payment').click(function(){
		$('.other-payment').hide();
		$('.other-payment-box').show();	
	});
	//选择支付方式
	$(".paymet_block fieldset p span a").click(function(){
		var that = $(this),
			obj = $(".paymet_block fieldset p span a");
		
		obj.removeClass("cur");	
		that.addClass("cur");
		var attr = that.closest("span").attr("class")
		if(attr == "alipay"){
			$("#mainPay").val("20");
			$("#subPay").val("37");
			$("#payDesc").html("支付宝")
		}else if(attr == "unionPay"){
			$("#mainPay").val("19");
			$("#subPay").val("49");
			$("#payDesc").html("银联")
		}else if(attr == "weixinPay"){
			$("#mainPay").val("27");
			$("#subPay").val("58");
			$("#payDesc").html("微信")
		}else if(attr == "cashPay" || attr == "postPay"){
			$("#mainPay").val("2");
			$("#subPay").val("41");
			$("#payDesc").html("货到付款")
		}else if(attr == "pufaPay"){
			$("#mainPay").val("15");
			$("#subPay").val("115");
			$("#payDesc").html("浦发")
		}else if(attr == "CMPay"){
			$("#mainPay").val("15");
			$("#subPay").val("118");
			$("#payDesc").html("招商银行")
		}else{
			$("#mainPay").val("0");
			$("#subPay").val("0");
		}
		
	});
	
	//选择礼品卡支付
	$("p.giftCard a").click(function(){
		$(this).toggleClass("cur");
		var giftCard = parseInt($("#giftCard").text());//礼品卡金额
		var product_total_price = parseInt($.trim($("#product_total_price").text()).substring(1));//商品总金额
		var promoPrice = parseInt($.trim($("#promoPrice").val()));//商品促销价
		var carriage = parseInt($.trim($("#carriage").text()).substring(1));//运费
		if($("#nocarriage").length > 0){
			var nocarriage = parseInt($.trim($("#nocarriage").text()).substring(2));//免运费金额
		}else{
			var nocarriage = 0;//免运费金额
		}
		var coupon_text = $("#pro_amount").text();
		if(coupon_text == null || coupon_text == ''){
			var pay_coupon = 0;
		}else{
			var pay_coupon = parseInt(coupon_text);//优惠金额
		}
		var order_total = product_total_price + carriage - nocarriage - promoPrice;
		var fact_pay = parseInt($("#real_pay b").text());
		//如果选择礼品卡，则显示礼品卡支付的金额$(".tips").text("提示：提交订单时需输入支付密码," + "另外你还需要额外支付&yen:" + price_spread)
		if($(this).hasClass("cur")){//使用礼品卡
			$("#isUseGiftCardPay").val("1");
			$("#span_pay_card").show();
			$(".tips").text("提示：提交订单时需输入支付密码");
			//判断是否使用优惠券或者折扣码
			if(pay_coupon != '' && pay_coupon != 0){
				$("#span_pay_coupon").show();
				$("pay_coupon b").text(pay_coupon);
				var real_pay = parseInt($("#real_pay b").text());
				var sperade_price = real_pay;
				//判断礼品卡+优惠金额是否大于订单总金额
				if(giftCard >= sperade_price){
					$("#pay_card b").text(sperade_price);
					$("#real_pay b").text("0");
					$(".price em i b").text("0");
					$(".total_amount em b").text("0");
					$(".giftCard a").append("<i class='card_pay'>使用礼品卡为本次支付 &yen;" + sperade_price + "</i>")
				}else{
					$("#pay_card b").text(giftCard);
					var pay = sperade_price - giftCard;
					$("#real_pay b").text(pay);
					$(".price em i b").text(pay);
					$(".total_amount em b").text(pay);
					$(".giftCard a").append("<i class='card_pay'>使用礼品卡为本次支付 &yen;" + giftCard + "</i>")
				}
			}else{
				$("pay_coupon b").text("0");
				$("#span_pay_coupon").hide();
				if(giftCard >= fact_pay){
					//var sperade = order_total - giftCard;
					$("#real_pay b").text("0");
					$(".price em i b").text("0");
					$(".total_amount em b").text("0");
					$("#pay_card b").text(fact_pay);
					$(".giftCard a").append("<i class='card_pay'>使用礼品卡为本次支付 &yen;" + fact_pay + "</i>");
				}else{
					$("#pay_card b").text(giftCard);
					var pay = fact_pay - giftCard;
					$("#real_pay b").text(pay);
					$(".price em i b").text(pay);
					$(".total_amount em b").text(pay);
					$(".giftCard a").append("<i class='card_pay'>使用礼品卡为本次支付 &yen;" + giftCard + "</i>");
				}
			}
		}else{
			//没有使用礼品卡
			$("#isUseGiftCardPay").val("0");
			$("#pay_card b").text("0");
			$("#span_pay_card").hide();
			$(".giftCard a i").remove(".card_pay");
			$(".tips").text("");
			//判断是否使用优惠券
			if(pay_coupon != '' && pay_coupon != 0){
				$("#span_pay_coupon").show();
				$("#pay_coupon b").text(pay_coupon);
//				var sperade_price = order_total - parseInt(pay_coupon);
				var onlinePay = $("#coupon_payamount").val();
				$("#real_pay b").text(onlinePay);
				$(".price em i b").text(onlinePay);
				$(".total_amount em b").text(onlinePay);
			}else{
				$("#pay_coupon b").text("0");
				$("#span_pay_coupon").hide();
				var sperade_price = order_total;
				$("#real_pay b").text(sperade_price);
				$(".price em i b").text(sperade_price);
				$(".total_amount em b").text(sperade_price);
			}
		}
		$("#surplus").text($("#real_pay b").text());
		return false;
	});
	
	//选择配送方式
//	$("#select_time").click(function(){
//		$("#area_overlay").height($(document).height());
//		$("#area_overlay, #area_layer").show();
//		addrTxt = "";
//		return false;
//	});
//	
//	$("#area_layer dd  a").click(function(){
//		
//		var that = $(this),
//			obj = $("#area_layer dd  a"),
//			timeTxt = that.text();
//		
//		obj.removeClass("cur");	
//		that.addClass("cur");
//		$("#delivery").val(that.attr("id"));
//		setTimeout(function(){
//			
//			$("#area_overlay, #area_layer").hide();
//			$("#select_time").text(timeTxt);
//			
//		}, 300);
//		return false;
//		
//	});
	var type = $('#type').val();
	if(type==1){
		$('#patsCard').find('a').css('background','url()');
	}
});

//显示指定页面
function showPage(content, header){
	scroll(0,0);
	$("." + content).show().siblings().hide();
	$("#" + header).show();
	
}

//显示收货地址列表
function showAddress(id, header){
	scroll(0,0);
	showPage(id, header);
	//选择收货地址，回到提交订单页，显示所选择的收货地址
	$(".order_address_list").delegate("p","click",function(){
		var addressId = $(this).attr("id");
		var receive_name = $(this).find("i").text();
		var receive_addr = $(this).find("em").text();
		//如果地址中支持货到付款，则显示货到付款支付方式
		var code = $(this).attr("title");
		var codeincart = $("#zhifu_codincart").val();
		if(code == "1" && codeincart == '1'){
			//var $html = "<p id='zhifu' class='cashPay'><a href='javascript:;'>货到付款现金支付</a></p><p id='zhifu' class='cashPay'><a href='javascript:;'>货到付款POS机刷卡</a></p>"
			//$(".total").before($html);
			$(".hd_zhifu").show();
		}else{
			$(".hd_zhifu").hide();
		}
		$("#addrid").val(addressId);
		$(".selected a").html(receive_name + "<br/>" + receive_addr);
		$(".order_detail").show().siblings().hide();
		$("#order_header").show();
		
	});
}

//显示提交订单页
function showAddressList(id, header){
	var re = /^[\u4e00-\u9fa5]$/,
	mre = /^1[34578]\d{9}$/,
	post = /^[1-9][0-9]{5}$/;

	//收货人姓名
	if ($.trim($("#J_userName").val()) == "" ){
		return jShare('请填写正确中文名称!',"",""),
		$("#J_userName").addClass("error"),
		!1;
	}else{
		$("#J_userName").removeClass("error");
	}
	
	//收货人电话
	if ($.trim($("#J_mobileNum").val()) == "" || !mre.test($("#J_mobileNum").val())){
		return jShare('请输入正确手机号码!',"",""),
		$("#J_mobileNum").addClass("error"),
		!1;
	}else{
		$("#J_mobileNum").removeClass("error");
	}
	
	//收货人地址
	if ($.trim($("#J_addr").val()) == "" || $.trim($("#province").val()) == "" || $.trim($("#city").val()) == "" || $.trim($("#area").val()) == "" || $.trim($("#town").val()) == ""){
		return jShare('请输入详细地址!',"",""),
		$("#J_addr").addClass("error"),
		!1;
	}else{
		$("#J_addr").removeClass("error");
	}
	
	//收货人邮编
	if ($.trim($("#J_code").val()) == "" || !post.test($("#J_code").val())){
		return jShare('请输入正确邮编!',"",""),
		$("#J_code").addClass("error"),
		!1;
	}else{
		$("#J_code").removeClass("error");
	}
	
//	var province = $("#province").val();
//	if(province == ''){
//		alert("请选择省市区!");
//		return;
//	}
	var path = getRootPath();
	//ajax 提交保存收货地址
	var address = $("#order_address_form").serialize();
	$.post(path + "/address/order/add",address,function(data){
		if(data.code == 0){
			//更改地址类表数据
			$(".add_block_list").remove("");
			$.each(data.addresses,function(index, item){
				var $html = "<p class='addr_block add_block_list' id='" + item.id + "'><span><i>" + item.name + "&nbsp;&nbsp;" + item.tel + "</i><em>" + item.provname + item.cityname + item.areaname + item.townname + item.addr +"</em></span></p>"
				$("#append_address").prepend($html);
			});
			var obj = data.addresses[0];
			var receive_name = obj.name + "," + obj.tel;
			var receive_addr = obj.provname + obj.cityname + obj.areaname + obj.townname + obj.addr;
			var cod = obj.cod;
			var codeincart = $("#zhifu_codincart").val();
			if(cod == "1" && codeincart == '1'){
				$(".hd_zhifu").show();
			}else{
				$(".hd_zhifu").hide();
			}
			if(!$("#addrid").length > 0){
				var $html = "<input type='hidden' id='addrid' name='addrid' value=''/>";
				$("#pats").before($html);
			}
			$("#addrid").val(obj.id);
			$("#pats").attr("class","select selected");
			$("#pats a").html(receive_name + "<br>" + receive_addr);
			$(".order_detail").show().siblings().hide();
			$("#order_header").show();
			$("#J_userName").val("");
			$("#J_mobileNum").val("");
			$("#J_addr").val("");
			$("#J_code").val("");
			$("#select_area").text("省市区");
			$("#J_isd").attr("checked",false);
			//showPage(id, header);
		}else{
			alert("收货地址添加失败！");
			return;
		}
	},"json");
}

//发票内容
function invoiceContent(){
	var select_value = $("#J_invoiceContent").children('option:selected').text();
	$("#invoice_content").text(select_value);
	$("#invoicecontent").val(select_value);
}

//发票类型
function invoiceType(){
	var select_value = $("#J_invoiceType").children('option:selected').val();
	$("#invoicetype").val(select_value);
	if(select_value == '0'){
		$("#invoice_type_text").text("个人");
	}else{
		$("#invoice_type_text").text("公司");
	}
}


//显示优惠券页面
function showCouponPage(content,header){
	scroll(0,0);
	var coupon_id = $("#detail_coupon_id").val();
	var coupons = $(".coupon_list_order input").val();
	if(coupon_id != ''){
		$.each(coupons,function(index,item){
			if(coupon_id == item){
				$(".select_coupon li").find("b").addClass("coupons_selected");
			}
		});
	}
	showPage(content,header);
}

//发送优惠券激活码
function activeCoupon(){
	if ($.trim($("#coupons_code").val()) == "" ){
		return jShare('请输入优惠券激活码!',"",""),
		$("#coupons_code").addClass("error"),
		!1;
	}else{
		$("#coupons_code").removeClass("error");
	}
	var path = getRootPath();
	var coupon_code = $("#coupons_code").val();
	var coupons = $(".coupon_list_order input").val();
	var orderSource = $("#orderSource").val();
	if(orderSource == "2"){
		var buyId = $(".pord_info").attr("id");
	}else{
		var buyId = "";
	}
	$.post(path + "/coupon/order/active",{"code": "coupon:" + coupon_code, "coupons": coupons},function(data){
		if(data.code == "0"){
			jShare("亲，优惠券充值成功","","");
			$("#coupons_code").val("");
			$.get(path + "/coupon/order/coupon/list",{orderSource:orderSource, buyId:buyId},function(data1){
				$(".coupon_list_order").html(data1);
			});
		}else{
			jShare(data.msg,"","");
		}
	},"json");
}

//提交订单
function submit(){
	var path = getRootPath();
	var product_total_price = parseInt($.trim($("#product_total_price").text()).substring(1));//商品总金额
	var mainPay = $("#mainPay").val();
	var subPay = $("#subPay").val();
	var addrId = $("#addrid").val();
	var invoice_addr = $("#invoice_addr").val();
	var totalFee = $("#real_pay b").text();
	var pat_giftcard = $("#isUseGiftCardPay").val();
	var pay_card = $("#pay_card b").text();
	var type = $('#type').val();
	if(type==1){
		$('#patsCard').find('a').css('background','url()');
	}else{
		if(!$("#addrid").length > 0 || addrId == ""){
			alert("请填写收货地址！");
			return;
		}
	}

	if(type==1){
		$("#delivery").val('1');
	}else if($("#delivery").val() == '0' || $("#delivery").val() == ''){
		alert("请选择配送方式！");
		return;
	}
	var invoiceFlag = $("#invoiceflag").val();
	if(invoiceFlag == "1"){//表示开发票
		
		if(invoice_addr == ""){
			if(type==1){
				alert("请填写发票地址");
				return;
			}
			invoice_addr=addrId;
			 $("#invoice_addr").val(invoice_addr);
		}
	}else{
		$("#invoice_addr").val("");
		$("#invoiceflag").val("0");
		$("#invoicetype").val("");
		$("#invoicetitle").val("");
		$("#invoicecontent").val("");
	}
	
	var buyGids = "";
	$(".pord_info").each(function(){
		buyGids += $(this).attr("id") + "|";
	});
	buyGids = buyGids.substring(0, buyGids.length - 1);
	$("#buysIds").val(buyGids);
	var order = $(".order_detail form").serialize();
	$("#submit_order").text("正在提交订单.......");
	var url = '';
	if(type==1||type==2){//礼品卡提交订单
		url = path + "/giftCard/submit";
	}else{
		url = path + "/order/common/submit";
	}
	$.post(url, order,function(data){
		
		if(data.code == "0"){
			//alert("订单提交成功" + data.orderInfo.orderid);
			//有礼品卡支付
			$("#orderNum").val(data.orderInfo.orderid);
			
			if(pat_giftcard == "1"){
				window.location.href = path + "/order/gifgcard?orderId=" + data.orderInfo.orderid;
															 
			}else if(mainPay == "99" && subPay == "99"){//优惠券、折扣码全额支付
				window.location.href = path + "/order/coupon/success?orderId=" + data.orderInfo.orderid;
			}else{
				//获取跳转到第三方支付页面url
				if(mainPay == "20" && subPay == "37"){
					$("#submit_order").text("正在跳转支付宝支付页面.......");
					window.location.href = path + "/pay/alipay/ALIWAP?orderId=" + data.orderInfo.orderid;
				}else if(mainPay == "19" && subPay == "49"){
					$("#submit_order").text("正在跳转银联支付页面.......");
					window.location.href = path + "/pay/unpay/UNWAP?orderId=" + data.orderInfo.orderid;
					setTimeout(function(){
						window.location.href = path + "/order/install?orderId=" + data.orderInfo.orderid;
					},2000);
				}else if(mainPay == "2" && subPay == "41"){//货到付款
					window.location.href = path + "/order/hdpay/success?orderId=" + data.orderInfo.orderid;
				}else if(mainPay == "27" && subPay == "58"){
					$("#submit_order").text("正在跳转微信支付页面.......");
					window.location.href = path + "/pay/weixin/WEIXINPUB?orderId=" + data.orderInfo.orderid;
				}else if(mainPay == "15" && subPay == "115"){
					$("#submit_order").text("正在浦发银行支付页面.......");
					window.location.href = path + "/order/spdb/pay?orderId=" + data.orderInfo.orderid;
				}else if(mainPay == "27" && subPay == "117"){
					$(".payment_btn").text("正在跳转微信支付页面.......");
					window.location.href = path + "/pay/weixin/WEIXINWAP?orderId=" + orderId;
				}
			}
		}else{
			alert("订单提交失败!");
			$("#submit_order").text("提交订单");
			return;
		}
	},"json");
}

function leave(){
	var path = getRootPath();
	if(confirm('确定要离开订单提交页吗？')){
		history.go(-1);
		//window.location.href = path + "/cart/list";
	}
}
//选择配送方式
$("#select_time").click(function(){
	$("#select_area_overlay").height($(document).height());
	$("#select_area_overlay, #select_area_layer").show();
	addrTxt = "";
	return false;
});

$("#select_area_layer dd  a").click(function(){
	
	var that = $(this),
		obj = $("#select_area_layer dd  a"),
		timeTxt = that.text();
	//console.log("id:" + that.attr("id"));
	obj.removeClass("cur");	
	that.addClass("cur");
	$("#delivery").val(that.attr("id"));
	setTimeout(function(){
		
		$("#select_area_overlay, #select_area_layer").hide();
		$(".delivery_mode").text(timeTxt);
//		$("#select_time").text(timeTxt);
		
	}, 300);
	return false;
	
});

//收货地址的弹层关闭
$(".close_btn, #select_area_overlay").click(function(){
	$("#select_area_layer, #select_area_overlay").hide();
});

$('#yes').click(function(){
	$('.invoice').show();
	$('#no').removeClass("cur");	
	$(this).addClass("cur");
	$("#invoiceflag").val("1");
	var type = $('#type').val();
	if(type==2){
		var addrId = $("#addrid").val();
		$("#invoice_addr").val(addrId);
		var invoice_title = $('#invoice_title').text();
		$('#invoicetitle').val(invoice_title);
	}
});

$('#no').click(function(){
	$('.invoice').hide();
	$('#yes').removeClass("cur");	
	$(this).addClass("cur");
	$("#invoiceflag").val("0");
});
