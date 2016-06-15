
$(function(){
	var path = getRootPath();
	//选择支付方式
	$(".paymet_block fieldset p span a").click(function(){
		
		var that = $(this),
			obj = $(".paymet_block fieldset p span a");
		
		obj.removeClass("cur");	
		that.addClass("cur");
		var cls = that.closest("span").attr("class");
		var orderType=$("#orderType").val();
		if(cls == "alipay"){
			if(orderType=='1'){
				$("#mainPay").val("20");
				$("#subPay").val("37");
			}else{
				$("#mainPay").val("30");
				$("#subPay").val("121");
			}
			$(".price span").text("支付宝");
		}else if(cls == "unionPay"){
			$("#mainPay").val("19");
			$("#subPay").val("49");
			$(".price span").text("银联");
			
		}else if(cls == "weixinPay"){
			var id=that.closest("span").attr("id")
			if(id='weixinWap'){
				$("#mainPay").val("27");
				$("#subPay").val("117");
			}else{
				$("#mainPay").val("27");
				$("#subPay").val("58");
			}
			$(".price span").text("微信");
		}else if(cls == "cashPay" || cls == "postPay"){
			$("#mainPay").val("2");
			$("#subPay").val("41");
			$(".price span").text("浦发");
		}else if(cls == "CMPay"){
			$("#mainPay").val("15");
			$("#subPay").val("118");
			$(".price span").text("招商银行");
		}
	});
	
	
	
});

function continuepay(){
	console.log(1);
	var path = getRootPath();
	var orderId = $("#orderId").val();
	var mainPay = $("#mainPay").val();
	var subPay = $("#subPay").val();
	console.log(orderId);
	console.log(mainPay);
	console.log(subPay);
	if(mainPay == "" || subPay == ""){
		alert("请选择支付方式!");
		return;
	}else if(mainPay == "20" && subPay == "37"){
		$(".payment_btn").text("正在跳转支付宝支付页面.......");
		window.location.href = path + "/pay/alipay/ALIWAP?orderId=" + orderId;
	}else if(mainPay == "19" && subPay == "49"){
		
		$(".payment_btn").text("正在跳转银联支付页面.......");
		window.location.href = path + "/pay/unpay/UNWAP?orderId=" + orderId;
		setTimeout(function(){
			window.location.href = path + "/order/install?orderId=" + orderId ;
		},2000);
	
	}else if(mainPay == "27"&& subPay == "58"){
		$(".payment_btn").text("正在跳转微信支付页面.......");
		window.location.href = path + "/pay/weixin/WEIXINPUB?orderId=" + orderId;
	}else if(mainPay == "15"&& subPay == "115"){
		$(".payment_btn").text("正在跳转浦发银行支付页面.......");
		window.location.href = path + "/order/spdb/pay?orderId=" + orderId;
		  
	}else if(mainPay == "30" && subPay == "121"){
		$(".payment_btn").text("正在跳转支付宝支付页面.......");
		window.location.href = path + "/pay/alipay/ALIWAPSEA?orderId=" + orderId;
	}else if(mainPay == "27" && subPay == "117"){
		$(".payment_btn").text("正在跳转微信支付页面.......");
		window.location.href = path + "/pay/weixin/WEIXINWAP?orderId=" + orderId;
	}
	
}