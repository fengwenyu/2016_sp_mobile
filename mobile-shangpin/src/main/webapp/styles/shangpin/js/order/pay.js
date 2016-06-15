

$(function(){
	//选择支付方式
	$(".paymet_block fieldset p span a").click(function(){
	
		var that = $(this),
			obj = $(".paymet_block fieldset p span a");
		//如果设置了无效支付方式
		if(that.attr("disabled")=="disabled"){
			return;
		}	
		obj.removeClass("cur");	
		that.addClass("cur");
		//设置主支付方式 及子支付方式
		var payId=that.attr("payId");
		$("#payType").val(payId);
		if(payId==20){
			//支付宝
			$("#payType").val("20");
			$("#payTypeChildId").val("37");
		}
		if(payId==19){
			//银联
			$("#payType").val("19");
			$("#payTypeChildId").val("49");
		}
		if(payId==2){
			//货到付款现金支付
			$("#payType").val("2");
			$("#payTypeChildId").val("70");
		}
		if(payId==-2){
			//货到付款POS支付
			$("#payType").val("2");
			$("#payTypeChildId").val("69");
		}
		if(payId==27){
			//微信支付
			$("#payType").val("27");
			$("#payTypeChildId").val("58");
		}
	});
});

//判断是否支持货到付款
function isPayOnDelivery(){
	var isPayDelivery=$("#isPayDelivery").val();
	var pay=$(".paymet_block fieldset p span a");
	$.each(pay,function(i,n){
		//货到付款
		if($(n).attr("payId")=="2" || $(n).attr("payId")=="-2"){
			//如果商品列表本来就不支持货到付款直接返回
			if($("#_isPayDelivery").val()==0 || $("#_isPayDelivery").val()=="false"){
				//不支持
				$(n).attr("disabled","disabled");
				if($(n).attr("payId")=="2" ){
					$(n).html(' 货到付款现金支付<font color="red" style="padding-left:10%;">不支持货到付款</font>');
				}else if($(n).attr("payId")=="-2" ){
					$(n).html('货到付款POS机刷卡<font color="red" style="padding-left:5%;">不支持货到付款</font>');
				}
				return;
			}else{
				//判断收货地址是否支持货到付款
				if(isPayDelivery==0 || isPayDelivery=="false"){
					//不支持
					$(n).attr("disabled","disabled");
					if($(n).attr("payId")=="2" ){
						$(n).html('货到付款现金支付<font color="red" style="padding-left:10%;">不支持货到付款</font>');
					}else if($(n).attr("payId")=="-2" ){
						$(n).html('货到付款POS机刷卡<font color="red" style="padding-left:5%;">不支持货到付款</font>');
					}
				}else{
					//支持
					$(n).removeAttr("disabled");	
					if($(n).attr("payId")=="2" ){
						$(n).html('货到付款现金支付');
					}else if($(n).attr("payId")=="-2" ){
						$(n).html('货到付款POS机刷卡');
					}
				}
				return;
			}
		}
	});
}
//选择礼品卡支付
function calculateGiftCard() {
	var useGiftCard = $("p.giftCard a").attr("useGiftCard");
	$("#isUseGiftCard").val(useGiftCard);
	toggleGiftCard();
	$("p.giftCard a").toggleClass("cur");
}

// 切换礼品卡显示
function toggleGiftCard() {
	if ($("#isGiftCard").val() == "true") {
		// 应付金额
		var amount = $("#payAmountValue").val();
		// 礼品卡总金额
		var giftCardAmount = $("#giftCardAmount").val();
		// 礼品卡减去应付值
		var balance = giftCardAmount - amount;
		// 是否已经使用礼品卡
		var isUseGiftCard = $("#isUseGiftCard").val();
		if (isUseGiftCard == "true") {
			// 已使用礼品卡
			if (balance > 0 || amount == 0) {
				// 礼品卡使用金额==应付金额
				$("#giftCardPay").val(amount);
				balance = 0;
			} else {
				// 礼品卡使用金额==礼品卡全部金额
				balance = amount - giftCardAmount;
				$("#giftCardPay").val(giftCardAmount);
			}
			var giftCardPay = $("#giftCardPay").val();
			// 改变内容
			$(".giftCard i").html("使用礼品卡为本次支付 &yen;" + giftCardPay);
			$("#balancePay").html("您还需要支付剩余 &yen;" + balance);
			$("#payAmount i").html("&nbsp;&nbsp;&yen;" + balance);

			// 显示礼品卡信息
			$("#giftAmount i").html("- &yen;" + giftCardPay);
			$("#giftAmount").show();
			// 设置为可使用
			$("p.giftCard a").attr("useGiftCard", "false");

		} else {
			// 改变样式内容
			if (balance > 0 || amount == 0) {
				$("#giftCardPay").val(amount);
			} else {
				$("#giftCardPay").val(giftCardAmount);
			}
			var giftCardPay = $("#giftCardPay").val();
			$(".giftCard i").html("点击使用礼品卡为本次支付 &yen;" + giftCardPay);
			// 不适用礼品卡时显示正常的应付金额
			$("#balancePay").html("您还需要支付剩余 &yen;" + amount);
			$("#payAmount i").html("&nbsp;&nbsp;&yen;" + amount);

			// 礼品卡消费隐藏
			$("#giftAmount i").html("");
			$("#giftAmount").hide();
			// 设置为取消
			$("p.giftCard a").attr("useGiftCard", "true");
		}
	}
}
