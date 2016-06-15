$(document).ready(
		function() {
			// 设置商品集合ID
			var buyIds = document.getElementsByName("buyId");
			var buysIds = [];
			$.each(buyIds, function(i, n) {
				buysIds.push($(n).val());
			});
			$("#buysIds").val(buysIds.join("|"));

			// 计算应付金额
			var carriageValue = $("#carriageValue").val();
			var amount = $("#amount").val();
			// 判断是否显示免运费
			if (carriageValue * 1 == 0) {
				$("#carriage i").html("- &yen;20");
				$("#carriage").show();
			}
			// 计算
			amount = amount * 1 + carriageValue * 1;
			$("#payAmountValue").val(amount);
			$("#payAmount i").html("&nbsp;&nbsp;&yen;" + amount);
			// 如果存在礼品卡
			if ($("#isGiftCard").val() == "true") {
				// 礼品卡总金额
				var giftCardAmount = $("#giftCardAmount").val();
				// 礼品卡减去应付值
				var balance = giftCardAmount - amount;
				if (balance * 1 > 0) {
					// 全付款
					$("#giftCardPay").val(amount);
				} else {
					// 部分付款
					balance = amount - giftCardAmount;
					$("#giftCardPay").val(giftCardAmount);
				}
				// 改变内容
				$(".giftCard i").html(
						"点击使用礼品卡为本次支付 &yen;" + $("#giftCardPay").val());
			}

			$("#balancePay").html("您需要支付 &yen;" + amount);
			
			var cod=$("#_cod").val();
			if(cod!=""){
				$("#isPayDelivery").val(cod);
			}
			
			isPayOnDelivery();
			//订单提交失败
			if($("#msgReturn").val()!=""){
				alert($("#msgReturn").val());
				$("#msgReturn").val("");
			}
		});

// order页面中要切换显示或隐藏的DIV id集合
var alldiv = [ "#orderCon","#invoiceCon", "#couponsUse", "#addAddr", "#listAddr","#addInvoiceAddr",
               "#orderConTop",  "#invoiceConTop",  "#couponsUseTop","#addAddrTop", "#listAddrTop", "#addInvoiceAddrTop"];
// 过滤div
function filterDiv(excludeDiv) {
	var constans = [];
	constans = $.grep(alldiv, function(val, key) {
		if ($.inArray(val, excludeDiv) != -1) {
			return true;
		}
	}, true);
	return constans;
}
// 页面要显示和隐藏div切换
function switchSH(hideList, showList, order) {
	if (order) {
		if (order == 'show') {
			$("body div").filter(showList).show();
			$("body div").filter(hideList).hide();
			return;
		}
	}
	$("body div").filter(hideList).hide();
	$("body div").filter(showList).show();
}
// 给定显示的div id数组 ,隐藏所有body中其他的div
function swithcSHDef(showDivArr) {
	switchSH(filterDiv(showDivArr).toString(), showDivArr.toString());
}

// 绑定beforeunload事件
$(window).bind('beforeunload', function() {
	return '您的订单内容尚未提交，确定离开此页面吗？';
});
// 返回按钮事件
function back() {
	var d = [ "#orderCon", "#orderConTop" ];
	swithcSHDef(d);
}

//提交订单
function submitOrder(){
	//防止重复提交
	$("#so").attr("disabled","disabled");
	if(isSubmitOrder()){
		$("#so").val('正在提交...');
		$("#soa").html('正在提交...');
		document.getElementById("orderForm").submit();
	}else{
		$("#so").removeAttr("disabled");
	}
}
//表单验证
function isSubmitOrder(){
	var addressId=$("#addressId").val();
	if(addressId==""){
		alert("请填写收货信息！");
		return false;
	}
	var deliveryId=$("#deliveryId").val();
	if(deliveryId==""){
		alert("请选择配送方式！");
		return false;
	}
	
	var isUseGiftCard=$("#isUseGiftCard").val()=="false"?"0":"1";
	$("#isUseGiftCard").val(isUseGiftCard);
	var payType=$("#payType").val();
	var payAmountValue=$("#payAmountValue").val();
	var balance=$("#giftCardPay").val()-$("#payAmountValue").val();
	//支付金额不为0时
	if((payAmountValue*1)!=0){
		if(payType=="" && (isUseGiftCard=="0"||(isUseGiftCard=="1" && balance<0))){
			alert("请选择支付方式！");
			return false;
		}
	}
	if(payType=='2' || payType=="-2"){
		
		//不支持货到付款
		var _isPayDelivery=$("#_isPayDelivery").val();
		var isPayDelivery=$("#isPayDelivery").val();
		if(_isPayDelivery==0 || _isPayDelivery=="false"){
			//不支持
			alert("商品不支持货到付款,请选择其他支付方式！");
			return false;
		}else{
			//判断收货地址是否支持货到付款
			if(isPayDelivery==0 || isPayDelivery=="false"){
				//不支持
				alert("收货地址不支持货到付款,请选择其他支付方式或收货地址！");
				return false;
			}
		}
	}
	
	//设置默认的发票地址id和收货地址id一样
	if($("#_invoiceaddrid").val()=="" || $("#_invoiceaddrid").val()==0){
		$("#_invoiceaddrid").val($("#addressId").val());
	}
	//提交表单
	 $(window).unbind('beforeunload');
	 return true;
}

