// JavaScript Document

// 点击进入使用优惠券页
function showUseCoupons() {
	var d = [ "#couponsUse", "#couponsUseTop" ];
	swithcSHDef(d);
}

// 点击返回
function couponsBack() {
	// 显示订单
	back();
}

// 优惠券编码生成优惠券
function activateCoupon() {
	var coupons_code = $("#coupons_code").val();
	if (coupons_code == '') {
		return jShare('请输入优惠券编码!', "", ""), !1;
	}
	$
			.ajax({
				url : 'couponaction!orderActivateCoupons',
				data : {
					"couponsNo" : coupons_code
				},
				dataType : 'json',
				timeout : 30000,
				async : false,
				error : function(xmlHttpRequest, error) {
					if (error == "parsererror") {
						alert("用户帐号异常，请重新登录");
						var enurl = escape(shangpinurl+"accountaction!applogin");
						window.location.href = "<%=path%>/mobileaolai/accountaction!loginui?callback="
								+ enurl;
					} else {
						alert("您的网络异常");
					}
					return;
				},
				success : function(data) {
					if ("0" == data.code) {
						var con = data.content;
						// 统计新的优惠券
						var count = 0;
						$
								.each(
										con,
										function(i, n) {
											// 取得已经存在的优惠券couponsno元素集合值
											var liList = $("#selectCouponUl li");
											var couponNos = [];

											$.each(liList, function(j, m) {
												couponNos[j] = $(m).attr(
														"couponNo");
											});
											// 如果不包含在数组中，则说明是新的
											if ($
													.inArray(n.couponno,
															couponNos) == -1) {
												var typeCss = "";
												if (n.type == "0") {
													typeCss = 'class="sale"';
												} else if (n.type == "1") {
													typeCss = 'class="cash"';
												}
												var ap = '<li onclick="selectCouponLi(this)"  couponNo="'
														+ n.couponno
														+ '" couponRule="'
														+ n.desc
														+ '" couponAmount="'
														+ n.amount
														+ '" id ="usecode"  isUse="0">'
														+ ' <span '
														+ typeCss
														+ '><i>&yen;</i>'
														+ n.amount
														+ '<em>'
														+ n.desc
														+ '</em></span>'
														+ '<p>过期时间：<br />'
														+ n.expiredate.substring(0,(n.expiredate.length-8))+'<br/>'
														+ n.expiredate.substring(n.expiredate.length-8)
														+ '</p></li>';
												// 追加页面
												$("#selectCouponUl").append(ap);
												// 未使用优惠券则改变显示内容个数
												if ($("#couponFlag").val() != 1) {
													$("#couponsTxt")
															.html(
																	"您有<em>"
																			+ $(
																					"#selectCouponUl li")
																					.size()
																			+ "</em>张优惠券可用");
												}
												count++;
											}
										})
						if(count==0){
							alert("您已成功激活优惠券，但优惠券不能用于该订单");
						}else{
							alert("您已成功激活优惠券");
						}				
						
						// 清空文本内容
						$("#coupons_code").val("");
						return;
					} else {
						alert(data.msg);
					}
				}
			});

}
// 选择优惠券
function selectCouponLi(o) {
	// 如果选中的是同一个则取消使用优惠券
	if ($(o).attr("isUse") == 1) {
		cancalUseCouponShow();
		$(o).removeClass("cur");
		// 设置为未使用
		$(o).attr("isUse", 0);
		back();
		return jShare('你取消了使用此优惠券!', "", ""), !1;
	}
	// 使用优惠券
	if ($(o).attr("isUse") == 0) {
		if (($("#couponFlag").val() == 2 && confirm("已使用优惠码,要改为使用优惠券吗？"))
				|| $("#couponFlag").val() != 2) {
			// 把选中的优惠信息加入订单form中
			var coupon = $(o).attr("couponNo");
			// 设置优惠券ID
			$("#coupon").val(coupon);
			
			// ajax请求
			$
					.ajax({
						url : 'orderaction!updateConfirmOrderInfo',
						data : {
							"orderVO.coupon" : $("#coupon").val(),
							"orderVO.buysids" : $("#buysIds").val(),
							"orderVO.couponflag" : "1",
							"orderVO.addrid" : $("#addressId").val()
						},
						dataType : 'json',
						timeout : 30000,
						async : false,
						error : function(xmlHttpRequest, error) {
							if (error == "parsererror") {
								alert("用户帐号异常，请重新登录");
								var enurl = escape(shangpinurl+"accountaction!applogin");
								window.location.href = "<%=path%>/mobileaolai/accountaction!loginui?callback="
										+ enurl;
							} else {
								alert("您的网络异常");
							}
							return;
						},
						success : function(data) {
							if ("success" == data.msgcode) {
								// 请求成功
								useCouponShow(data);
								// 样式
								$(o).siblings("li").removeClass("cur");
								$(o).toggleClass("cur");
								// 取消被选中
								$(o).siblings("li").attr("isUse", 0);
								// 选中
								$(o).attr("isUse", 1);
								// 修改优惠码样式
								$("#sale_code").val("");
								$(".code_active").show();
								$(".code_result").hide();
								// 返回订单列表
								back();
								// 设置使用类型为1优惠券
								$("#couponFlag").val(1);
								return;
							} else {
								alert(data.msg);
							}
						}
					});
		}
	}
	return false;
}

// 使用优惠码
function useCouponCode() {
	var couponCode = $("#sale_code").val();
	// 验证不为空
	if (couponCode == '') {
		return jShare('请输入优惠码!', "", ""), !1;
	}
	if (($("#couponFlag").val() == 1 && confirm("已使用优惠券,要改为使用优惠码吗？"))
			|| $("#couponFlag").val() != 1) {
		// 设置优惠券ID
		$("#coupon").val(couponCode);
		
		$
				.ajax({
					url : 'orderaction!updateConfirmOrderInfo',
					data : {
						"orderVO.coupon" : $("#coupon").val(),
						"orderVO.buysids" : $("#buysIds").val(),
						"orderVO.couponflag" : "2",
						"orderVO.addrid" : $("#addressId").val()
					},
					dataType : 'json',
					timeout : 30000,
					async : false,
					error : function(xmlHttpRequest, error) {
						if (error == "parsererror") {
							alert("用户帐号异常，请重新登录");
							var enurl = escape(shangpinurl+"accountaction!applogin");
							window.location.href = "<%=path%>/mobileaolai/accountaction!loginui?callback="
									+ enurl;
						} else {
							alert("您的网络异常");
						}
						return;
					},
					success : function(data) {
						if ("success" == data.msgcode) {
							// 显示使用后的效果
							useCouponShow(data);
							$(".code_result span").html(
									couponCode + " &nbsp;&nbsp; 已优惠  &yen;"
											+ data.discountamount);
							$(".code_active").hide();
							$(".code_result").show();

							// 取消优惠券的样式
							$("#selectCouponUl li").removeClass("cur");
							$("#selectCouponUl li").attr("isUse", 0);
							// 返回订单列表
							back();
							// 设置使用类型为2优惠码
							$("#couponFlag").val(2);
							return;
						} else {
							alert(data.msg);
						}
					}
				});
	}

	return false;
}

// 取消使用优惠码
function cancelCouponCode() {
	cancalUseCouponShow();
	$("#sale_code").val("");
	$(".code_active").show();
	$(".code_result").hide();
	return;
}

// 取消使用优惠显示
function cancalUseCouponShow() {
	// 优惠金额隐藏
	$("#discountAmount").hide();
	// 设置订单form元素
	$("#coupon").val("");
	$("#couponFlag").val("");
	// 重新计算应付金额
	
	var payAmountValue =  $("#payAmountValue").val() * 1;
	
	var payAmount = $("#payAmountValue").val() * 1 + $("#couponAmount").val()
			* 1;
	//如果用户(白金或砖石)就可以免运费 直接免运费  //或者用户应付金额大于等于499直接免运费
	if($("#free").val()=="true" ||payAmountValue>=499){
		
	}else{
		//否则判断是否小于499
		if(payAmountValue<499){
			//如果应付的金额 + 优惠的金额大于等于499 免运费
			if(payAmount>=499){
				payAmount-=20;
				$("#carriage").show();
			}else{
				
			}
		}
	}
	
	
	$("#payAmountValue").val(payAmount);
	// 显示应付金额值
	$("#payAmount i").html("&nbsp;&nbsp;&yen;" + payAmount);
	$("#balancePay").html("您还需要支付剩余 &yen;" + payAmount);
	// 改变返回优惠信息
	$("#couponsTxt").html(
			"您有<em>" + $("#selectCouponUl li").size() + "</em>张优惠券可用");
	toggleGiftCard();
	// 返回
}

// 使用优惠显示
function useCouponShow(data) {
	var discountAmount = data.discountamount * 1;
	var carriage = data.carriage;
	var payAmount = data.payamount;
	// 优惠值
	$("#discountAmount i").html("- &yen;" + discountAmount);
	// 显示优惠金额
	$("#discountAmount").show();
	// 是否运费减免
	if (carriage * 1 > 0) {
		$("#carriage").hide();
	} else {
		$("#carriage").show();
	}
	// 显示应付金额值
	$("#payAmount i").html("&nbsp;&nbsp;&yen;" + payAmount);
	$("#balancePay").html("您还需要支付剩余 &yen;" + payAmount);
	// 设置订单form中的payAmountValue
	$("#payAmountValue").val(payAmount);
	$("#couponAmount").val(discountAmount);
	$("#carriageValue").val(carriage);
	// 改变返回优惠信息
	$("#couponsTxt").html("已优惠:<em>&yen;" + discountAmount + "</em>");
	toggleGiftCard();
}
// 优惠码和优惠券切换
$(function() {

	// tab切换事件
	var tabs = function(nav, content) {
		$(nav).find("li").bind("click", function() {
			var index = $(this).index();
			$(this).addClass("cur").siblings().removeClass("cur");
			$(content).eq(index).show().siblings(content).hide();
		});
	}

	tabs(".tabs_menu", ".tabs_Cell");

	// 取消订单
	$(".alOrder_info").delegate(".alOrder_cancelBtn", "click", function() {

		jConfirm('确认取消该订单吗？', '', function(result) {
			if (result == true) {

			} else {

			}
		});

		return false;
	});
	// 取消订单
});
