// JavaScript Document

$(document).ready(function(){
/*	$('.friends-box').hide();*/
    //if($(this).find('.arrow-down')){
		$('.raise-tip').toggle(function(){
			$('.friends-box').slideDown();
			$(this).find('.arrow-down').addClass('arrow-up').removeClass('.arrow-down');
			},
			function(){
				$('.friends-box').slideUp();
			$(this).find('.arrow-down').addClass('arrow-down').removeClass('.arrow-up');
			}
	  );
	/*}else if($(this).find('.arrow-up')){
		$('.raise-tip').click(function(){
			$('.friends-box').slideUp();
			$(this).find('.arrow-down').addClass('arrow-down').removeClass('.arrow-up');
		});
	}*/
	
	//tab切换事件
	var tabs = function(nav,content){
		$(nav).find("li").bind("click",function(){
			var index = $(this).index();
			$(this).addClass("cur").siblings().removeClass("cur");
			$(content).eq(index).show().siblings(content).hide();
		});
	}
	
	tabs(".tabs_menu", ".tabs_Cell");
	
	//选择优惠券
	
	
	$(".order_fill_coupon_list").delegate("li","click",function(){
		$(this).siblings("li").find('b').removeClass("selected");
		$(this).find('b').toggleClass("selected");
		//使用优惠券
		var couponId = $(this).find("input").val();
		var product_total_price = $.trim($("#totalAmount").val());
		//console.log("product_total_price:" + product_total_price);
		//console.log("couponId:" + couponId);
		if($(this).find('b').hasClass("selected")){
			var ticketAmount = $.trim($(this).find("i").text()).substring(1);
		}else{
			var ticketAmount = 0;
		}
		//console.log("ticketAmount:" + ticketAmount);
		var buyId = $("#buyId").val();
		var path = getRootPath();
		var orderSource = $("#orderSource").val();
		$.post(path + "/coupon/user/coupon",{
			type : "1",
			totalAmount : product_total_price,
			promoAmount : "0",
			ticketAmount : ticketAmount,
			discountCode : "0",
			giftCardAmount : "0",
			carriageAmount : "0",
			postArea : "2",
			buyIds : buyId,
			orderSource : orderSource
		},function(data){
			//console.log("priceShow:" + data);
			$(".total").html(data);
			var amount = $("#real_pay b").text();
			$html = "您还需要支付剩余 &yen;" + amount;
			if($("#span_pay_coupon").length > 0){
				var coupon_amount = $.trim($("#pay_coupon b").text());
				$("#use_conpus").text("已优惠" + coupon_amount);
				$("#couponflag").val("1");
				$("#coupon").val(couponId);
			}else{
				var totalCoupon = $("#totalCoupon").val();
				$("#use_conpus").html("您有 <em>" + totalCoupon + "</em> 张优惠券可用");
				$("#couponflag").val("");
				$("#coupon").val("0");
			}
			$("#last_pay").html($html);
		},"html");
		couponBack();
		return false;
	});
	
	//优惠码事件
	var codeActive = $(".code_active"),
		saleCode = $("#sale_code"),
		codeResult = $(".code_result"),
		codeActiveBtn = $("#saleCode_btn"),
		codeCancelBtn = $("#saleCacel_btn");
		
	//激活
	codeActiveBtn.click(function(){
		if(saleCode.val() != ""){
			codeActive.hide();
			codeResult.show();
		}
		return false;
	});
	//更改
	codeCancelBtn.click(function(){
		saleCode.val("");
		codeActive.show();
		codeResult.hide();
		return false;
	});
	


});
function showCouponPage(){
	$(".order_fill_coupon_list").siblings().hide();
	$(".order_fill_coupon_list").show();
	$("#coupons_head").show();
}
function couponBack(){
	$("#order_container").show().siblings().hide();
	$("#order_head").show();
}