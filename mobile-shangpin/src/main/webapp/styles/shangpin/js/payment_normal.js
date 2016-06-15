$(function(){
	$('.other-payment').hide();
	$('.other-payment-box').show();
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
	//页面刚加载时默认选中的支付方式
	$(".paymet_block fieldset .payment-method").each(function(){
		var attr_cur=$(this).find("span a").attr("class");
		if(attr_cur=="cur"){
			var attr=$(this).find("span").attr("class");
			if(attr == "alipay"){
				$("#mainPay").val("20");
				$("#subPay").val("37");
				$("#payDesc").html("支付宝")
			}else if(attr == "unionPay"){
				$("#mainPay").val("19");
				$("#subPay").val("49");
				$("#payDesc").html("银联")
			}else if(attr == "weixinPay"){
				var id=$(this).find("span").attr("id")
				if(id='weixinWap'){
					$("#mainPay").val("27");
					$("#subPay").val("117");
				}else{
					$("#mainPay").val("27");
					$("#subPay").val("58");
				}
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
		}
		
	})
	$("#select-area-overlay, .close_btn").click(function(){
		$("#select-area-overlay, .select-layer").hide();
	});
	
	//选择四级地址
	$("#select_street_layer").delegate("a","click",function(){
		$(this).addClass("cur");
		var obj = $("#select_street_layer dd  a");
		var timeTxt = $(this).text();
		console.log(timeTxt);
		obj.removeClass("cur");	
		var townName = $(this).text();
		var townId = $(this).attr("id");
		var addrId = $("#addrid").val();
		$("#fourth_id").val(townId);
		setTimeout(function(){
			$("#select-area-overlay, .select-layer").hide();
			//$(".delivery_mode").text(timeTxt);
			//更新收货地址
			var path = getRootPath();
			$.get(path + "/address/ajax/update?addressId=" + addrId,function(data){
				console.log("update address:" + data);
				$.post(path + "/address/ajax/edit",{
					id : data.id,
					province : data.province,
					city : data.city,
					area : data.area,
					town : townId,
					name : data.name,
					addr : data.addr,
					postcode : data.postcode,
					tel : data.tel
				},function(data){
					if(data.code == "0"){
						$.each(data.content,function(index, item){
							if(addrId == item.id){
								var showAddress = item.provname + item.cityname + item.areaname + item.townname + item.addr;
								$("#pats a").text(showAddress);
								//判断货到付款
								var codeincart = $("#zhifu_codincart").val();
								var code = item.cod;
								if(code == "1" && codeincart == '1'){
									$(".hd_zhifu").show();
								}else{
									$(".hd_zhifu").hide();
								}
								return;
							}
						});
					}else{
						alert("四级地址更新失败");
						return;
					}
				},"json");
			},"json");
		}, 300);
		return false;
	});

	
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
			var id=that.closest("span").attr("id")
			if(id =='weixinWap'){
				$("#mainPay").val("27");
				$("#subPay").val("117");
			}else{
				$("#mainPay").val("27");
				$("#subPay").val("58");
			}
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
		var path = getRootPath();
		$(this).toggleClass("cur");
		if($("#giftCard").length > 0){
			var giftCard = parseInt($("#giftCard").text());//礼品卡金额
		}else{
			var giftCard = "";
		}
		var product_total_price = parseInt($.trim($("#product_total_price").text()).substring(1));//商品总金额
		//满减金额
		if($("#promoto_price").length > 0){
			var promoAmount = $.trim($.trim($("#promoto_price").text()).substring(2));
		}else{
			var promoAmount = "0";
		}
		//优惠券金额
		if($("#span_pay_coupon").length > 0){
			var ticketAmount = $.trim($("#pay_coupon b").text());
		}else{
			var ticketAmount = "0";
		}
		//折扣码
		if($.trim($("#dis_count_code").val()) == '1'){
			var discountCode = $.trim($("#sale_code").val());
		}else{
			var discountCode = "";
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
		if($(this).hasClass("cur")){//表示使用礼品卡
			$("#isUseGiftCardPay").val("1");
			if($("#span_pay_coupon").length > 0 && $.trim($("#dis_count_code").val()) == '1'){//表示使用优惠信息(用的是折扣码)
				type = "2|3";
			}else if($("#span_pay_coupon").length > 0 && $.trim($("#dis_count_code").val()) == '0'){//表示使用优惠信息(用的是优惠券)
				type = "1|3";
			}else{
				type = "3";
			}
		}else{
			$("#isUseGiftCardPay").val("0");
			if($("#span_pay_coupon").length > 0 && $.trim($("#dis_count_code").val()) == '1'){//表示使用优惠信息(用的是折扣码)
				type = "2";
			}else if($("#span_pay_coupon").length > 0 && $.trim($("#dis_count_code").val()) == '0'){//表示使用优惠信息(用的是优惠券)
				type = "1";
			}else{
				type = "";
			}
		}
		$.post(path + "/coupon/user/coupon",{
			type : type,
			totalAmount : product_total_price,
			promoAmount : promoAmount,
			ticketAmount : ticketAmount,
			discountCode : discountCode,
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
			if($("#span_pay_card").length > 0){
				var giftCard = $.trim($("#span_pay_card i b").text());
				$(".giftCard a").append("<i class='card_pay'>使用礼品卡为本次支付 &yen;" + giftCard + "</i>");
			}else{
				$(".giftCard a i").remove(".card_pay");
				if(amount == "0"){
					$("#mainPay").val("99");
					$("#subPay").val("99");
				}
			}
		},"html");
	});
	
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
		var third_id =$(this).attr("area");
		var fourth_id = $(this).attr("town");
		var codeincart = $("#zhifu_codincart").val();
		if(code == "1" && codeincart == '1'){
			//var $html = "<p id='zhifu' class='cashPay'><a href='javascript:;'>货到付款现金支付</a></p><p id='zhifu' class='cashPay'><a href='javascript:;'>货到付款POS机刷卡</a></p>"
			//$(".total").before($html);
			$(".hd_zhifu").show();
		}else{
			$(".hd_zhifu").hide();
		}
		$("#addrid").val(addressId);
		$("#third_id").val(third_id);
		$("#fourth_id").val(fourth_id);
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
		return jShare('请输入正确中文名称!',"",""),
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
	
	var path = getRootPath();
	//ajax 提交保存收货地址
	var address = $("#order_address_form").serialize();
	$.post(path + "/address/order/add",address,function(data){
		if(data.code == 0){
			//更改地址类表数据
			$(".add_block_list").remove("");
			$.each(data.addresses,function(index, item){
				var $html = "<p class='addr_block add_block_list' id='" + item.id + "' title='"+ item.cod +"' area='"+ item.area +"' town='"+ item.town +"'><span><i>" + item.name + "&nbsp;&nbsp;" + item.tel + "</i><em>" + item.provname + item.cityname + item.areaname + item.townname + item.addr +"</em></span></p>"
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
			$("#third_id").val(obj.area);
			$("#fourth_id").val(obj.town);
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

		if($("input[name='invoiceTel']").val()==""){
			alert("请输入发票手机号码!");
			return;
		}
		if(invoice_addr == ""){
			//变为电子发票没有地址注释掉
			/*if(type==1){
				alert("请填写发票地址");
				return;
			}*/
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
	//判断收货地址中是否有四级地址
	var fourth_id = $("#fourth_id").val();
	if(fourth_id == '' || fourth_id == '0'){
		$("#select-area-overlay, #select_street_layer").show();
		var area = $("#third_id").val();
		$.post(path + "/address/town",{areaId : area},function(data){
			$("#orgin_area_street").empty();
			$.each(data,function(index,item){
				$("#orgin_area_street").append("<dd><a href='#' id=" + item.id + ">" + item.name + "</a></dd>");
			});
		},"json");
		return;
	}
	
	var buyGids = "";
	$(".pord_info").each(function(){
		buyGids += $(this).attr("id") + "|";
	});
	buyGids = buyGids.substring(0, buyGids.length - 1);
	$("#buysIds").val(buyGids);
	var order = $(".order_detail form").serialize();
	$("#submit_order").text("正在提交订单.......");
	if(type=="1"||type=="2"){//礼品卡提交订单
		var url = path + "/giftCard/submitV2";
	}else{
		var url = path + "/order/common/submit";
	}
	$.post(url, order,function(data){
		console.log("submit:" + data);
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
					window.location.href = path + "/pay/hdpay/success?orderId=" + data.orderInfo.orderid;
				}else if(mainPay == "27" && subPay == "58"){
					$("#submit_order").text("正在跳转微信支付页面.......");
					window.location.href = path + "/pay/weixin/WEIXINPUB?orderId=" + data.orderInfo.orderid;
				}else if(mainPay == "15" && subPay == "115"){
					$("#submit_order").text("正在浦发银行支付页面.......");
					window.location.href = path + "/pay/wap/SPDBWAP?orderId=" + data.orderInfo.orderid;
				}else if(mainPay == "27" && subPay == "117"){
					$(".payment_btn").text("正在跳转微信支付页面.......");
					window.location.href = path + "/pay/weixin/WEIXINWAP?orderId=" + data.orderInfo.orderid;
				}
			
			}
		}else if(data.code == "3005"){//库存不足
			alert("您的订单中有库存不足的商品");
			$("#submit_order").text("提交订单");
			return;
		}else if(data.code == "3003"){//地址不全
			$("#select-area-overlay, #select_street_layer").show();
			var area = $("#third_id").val();
			$.post(path + "/address/town",{areaId : area},function(data){
				$("#orgin_area_street").empty();
				$.each(data,function(index,item){
					$("#orgin_area_street").append("<dd><a href='#' id=" + item.id + ">" + item.name + "</a></dd>");
				});
			},"json");
			$("#submit_order").text("提交订单");
			return;
		}else{
			if(data.msg == "库存不足"){
				alert("您的订单中有库存不足的商品");
			}else{
				alert(data.msg);
			}
			$("#submit_order").text("提交订单");
			return;
		}
	},"json");
	return;
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


