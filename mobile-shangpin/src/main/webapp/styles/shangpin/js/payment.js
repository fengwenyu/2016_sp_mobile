(function() {
    window.Login = function() {},
    $.extend(window.Login.prototype, {
        init: function() {},
        loginForm: function() {
            $("#J_Login").on("submit",
            function(e) {
				var re = /^[\u4e00-\u9fa5]$/,
					mre = /^1[34578]\d{9}$/,
					post = /^[1-9][0-9]{5}$/;
				
				//收货人姓名
				if ($.trim($("#J_userName").val()) == ""){
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
				if ($.trim($("#J_addr").val()) == ""){
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
				
            });
        }
    });
    var onlineamount = parseFloat($("#onlineamount").val());//订单需要支付的金额，已经优惠后需要支付的金额
	var giftcardbalance = parseFloat($("#giftcardbalance").text());//礼品卡余额
	if(giftcardbalance >= onlineamount){
		$("#realpay").text(0);
	}else{
		var sperade_parice = onlineamount - giftcardbalance;
		$("#realpay").text(sperade_parice);
	}
})(jQuery);

//登录表单验证
if($("#J_Login").length > 0){
	var Login = new Login();
	Login.loginForm();
}

$(function(){
	var path = getRootPath();
	//选择支付方式
	$(".paymet_block fieldset p span a").click(function(){
		
		var that = $(this),
			obj = $(".paymet_block fieldset p span a");
		
		obj.removeClass("cur");	
		that.addClass("cur");
		var cls = that.closest("span").attr("class");
		/*if(cls == "alipay"){
			$("#payTypeId").val("20");
			$("#payTypeChildId").val("37");
		}else if(cls == "unionPay"){
			$("#payTypeId").val("19");
			$("#payTypeChildId").val("49");
		}else if(cls == "weixinPay"){
			var id=that.closest("span").attr("id")
			if(id=='weixinWap'){
				$("#payTypeId").val("27");
				$("#payTypeChildId").val("117");
			}else{
				$("#payTypeId").val("27");
				$("#payTypeChildId").val("58");
			}
		}else if(cls == "cashPay" || cls == "postPay"){
			$("#payTypeId").val("2");
			$("#payTypeChildId").val("41");
		}else if(cls == "CMPay"){
			$("#payTypeId").val("15");
			$("#payTypeChildId").val("118");
		}*/
		if(cls == "alipay"){
			$("#payTypeId").val("30");
			$("#payTypeChildId").val("121");
		}else if(cls == "alipayIn"){
			$("#payTypeId").val("20");
			$("#payTypeChildId").val("37");
		}else if(cls == "weixinPay"){
			$("#payTypeId").val("27");
			$("#payTypeChildId").val("117");
		}else if(cls == "weixinPayOut"){
			$("#payTypeId").val("32");
			$("#payTypeChildId").val("111");
		}else if(cls == "weixinPayPub"){
			$("#payTypeId").val("27");
			$("#payTypeChildId").val("58");
		}else if(cls == "unionPay"){
			$("#payTypeId").val("19");
			$("#payTypeChildId").val("49");
		}else if(cls == "unionPayIn"){
			$("#payTypeId").val("19");
			$("#payTypeChildId").val("49");
		}else if(cls == "weixinPay"){
			var id=that.closest("span").attr("id")
			if(id=='weixinWap'){
				$("#payTypeId").val("27");
				$("#payTypeChildId").val("117");
			}else{
				$("#payTypeId").val("27");
				$("#payTypeChildId").val("58");
			}
		}else if(cls == "cashPay" || cls == "postPay"){
			$("#payTypeId").val("2");
			$("#payTypeChildId").val("41");
		}else if(cls == "CMPay"){
			$("#payTypeId").val("15");
			$("#payTypeChildId").val("118");
		}
	});
	
	//选择礼品卡支付
	$("#giftCardpay").click(function(){
		$(this).toggleClass("cur");
		var onlineamount = parseFloat($("#onlineamount").val());//订单需要支付的金额，已经优惠后需要支付的金额
		var giftcardbalance = parseFloat($("#giftcardbalance").text());//礼品卡余额
		//如果选择礼品卡支付
		if($(this).hasClass("cur")){
			$(".giftCard a i").remove();
			//$("p").remove(".price");
			//如果礼品卡的余额大于需要支付的金额,则礼品卡全额支付
			if(giftcardbalance >= onlineamount){
				// <i>使用礼品卡为本次支付 &yen;<b id="paycard">${paycard}</b></i>
				var $html1 = "<i>使用礼品卡为本次支付 &yen;<b id='paycard'>" + onlineamount + "</b></i>"
				$("#giftcardbalance").closest("a").append($html1);
				$("#realpay").text(0);
				$(".zhifu").hide();
			}else{
				var $html2 = "<i>使用礼品卡为本次支付 &yen;<b id='paycard'>" + giftcardbalance + "</b></i>"
				$("#giftcardbalance").closest("a").append($html2);
				var sperade_parice = onlineamount - giftcardbalance;
				//var $html3 = "<p class='price'>您需要支付 &yen;<b id='realpay'>" + sperade_parice + "</b></p>";
				//$(".giftCard").after($html3);
				$("#realpay").text(sperade_parice);
				$(".zhifu").show();
			}
		}else{
			$(".giftCard a i").remove();
			//$("p").remove(".price");
			//var $html4 = "<p class='price'>您需要支付 &yen;<b id='realpay'>" + onlineamount + "</b></p>";
			//$(".giftCard").after($html4);
			$("#realpay").text(onlineamount);
			$(".zhifu").show();
		}
		return false;
	});
	
	//选择收货地址
	var addrTxt;
	$("#select_area").click(function(){
		$("#area_overlay").height($(document).height());
		$("#area_overlay, #area_layer").show();
		addrTxt = "";
		return false;
	});
	
	$("#area_layer").delegate("a","click",function(){
		var that = $(this),
		obj = $("#area_layer dd  a"),
		
		content = $("#area_layer dl"),
		thisCon = that.closest("dl"),
		title = $("#area_layer h3");
	
	obj.removeClass("cur");	
	that.addClass("cur");
	var dl_id = thisCon.attr("id");
	//console.log(dl_id);
	if(dl_id == "area_province"){
		$("#province").val(that.attr("id"));
		$("#provinceName").val(that.text());
	}else if(dl_id == "area_city"){
		$("#city").val(that.attr("id"));
		$("#cityName").val(that.text());
	}else if(dl_id == "area_county"){
		$("#area").val(that.attr("id"));
		$("#areaName").val(that.text());
	}else if(dl_id == "area_street"){
		$("#town").val(that.attr("id"));
		$("#townName").val(that.text());
	}	
	//选择结果
	addrTxt += " "+that.text();
	
	setTimeout(function(){
		
		if(thisCon.next("dl").length > 0){
		
			content.hide();
			thisCon.next("dl").show();
			title.html(thisCon.next("dl").attr("title"));
			if(thisCon.next("dl").attr("id") == "area_city"){
				$("#area_city").empty();
				$.post(path + "/address/city",{proviceId : that.attr("id")},function(data){
					//console.log(data);
					$.each(data,function(index,item){
						$("#area_city").append("<dd><a href='#' id=" + item.id + ">" + item.name + "</a></dd>");
					});
				},"json");
			}else if(thisCon.next("dl").attr("id") == "area_county"){
				$("#area_county").empty();
				$.post(path + "/address/area",{cityId : that.attr("id")},function(data){
					//console.log(data);
					$.each(data,function(index,item){
						$("#area_county").append("<dd><a href='#' id=" + item.id + ">" + item.name + "</a></dd>");
					});
				},"json");
			}else if(thisCon.next("dl").attr("id") == "area_street"){
				$("#area_street").empty();
				$.post(path + "/address/town",{areaId : that.attr("id")},function(data){
					//console.log(data);
					$.each(data,function(index,item){
						$("#area_street").append("<dd><a href='#' id=" + item.id + ">" + item.name + "</a></dd>");
					});
				},"json");
			}
		}else{
			//返回初始状态
			content.hide();
			$("#area_overlay, #area_layer").hide();
			$("#area_layer dl:first").show();
			title.html($("#area_layer dl:first").attr("title"));
			
			$("#select_area").text(addrTxt);
			
			//obj.removeClass("cur");	
			//$("#area_layer dl dd:first a").addClass("cur");
		}
		
	}, 300);
	return false;
	
	});
		
	
	//礼品支付页密码输入框修改浏览器兼容问题
	if($(".paymet_success").length>0 && $("#giftPsd").length>0){
		$("#giftPsd").focus(function(){
			$(window).scrollTop($(".giftCard").offset().top);
		});
	}
	
	
});

//提交订单
function quickSubmitOrder(){
	var re = /^[\u4e00-\u9fa5]$/,
	mre = /^1[34578]\d{9}$/,
	post = /^[1-9][0-9]{5}$/;
	
	//收货人姓名
	if ($.trim($("#J_userName").val()) == ""){
		return jShare('请填写正确中文名称!',"",""),
		$("#J_userName").addClass("error"),
		!1;
		return;
	}else{
		$("#J_userName").removeClass("error");
	}
	
	//收货人电话
	if ($.trim($("#J_mobileNum").val()) == "" || !mre.test($("#J_mobileNum").val())){
		return jShare('请输入正确手机号码!',"",""),
		$("#J_mobileNum").addClass("error"),
		!1;
		return;
	}else{
		$("#J_mobileNum").removeClass("error");
	}
	
	//收货人地址
	if ($.trim($("#J_addr").val()) == ""){
		return jShare('请输入详细地址!',"",""),
		$("#J_addr").addClass("error"),
		!1;
		return;
	}else{
		$("#J_addr").removeClass("error");
	}
	
	//收货人邮编
	if ($.trim($("#J_code").val()) == "" || !post.test($("#J_code").val())){
		return jShare('请输入正确邮编!',"",""),
		$("#J_code").addClass("error"),
		!1;
		return;
	}else{
		$("#J_code").removeClass("error");
	}
	
	if($("#province").val() == ''){
		alert("请选择省市区!");
		return;
	}
	var path = getRootPath();
	var amount = $.trim($(".total em i").text());
	var totalFee = $.trim(amount.substring(1));
	var orderInfo = $("#J_Login").serialize();
	var mainPay = $("#payTypeId").val();
	var subPay = $("#payTypeChildId").val();
	$(".payment_btn").text("正在提交订单.......");
	$.post(path + "/order/submit",orderInfo,function(data){
		var now = new Date();
		var time = now.format("yyyy/MM/dd hh:mm:ss");
		if(data.code == 0){
			/*if(mainPay == "20" && subPay == "37"){
				$(".payment_btn").text("正在跳转支付宝支付页面.......");
				$(".payment_btn").text("提交订单");
				window.location.href = path + "/pay/alipay/ALIWAP?orderId=" + data.orderId+"&quickSubmitFlag=0";
			}else if(mainPay == "19" && subPay == "49"){
				$(".payment_btn").text("正在跳转银联支付页面.......");
				window.location.href = path + "/pay/unpay/UNWAP?orderId=" + data.orderId +"&quickSubmitFlag=0";
				setTimeout(function(){
					window.location.href = path + "/order/install?orderId=" + data.orderId +"&quickSubmitFlag=0";
				},2000);
			}else if(mainPay == "27 "&& subPay == "58"){
				$(".payment_btn").text("正在跳转微信支付页面.......");
				window.location.href = path + "/pay/weixin/WEIXINPUB?orderId=" +  data.orderId+"&quickSubmitFlag=0";;
			  
			}else if(mainPay == "27" && subPay == "117"){
				$(".payment_btn").text("正在跳转微信支付页面.......");
				window.location.href = path + "/pay/weixin/WEIXINWAP?orderId=" + orderId;
			}*/
			if(mainPay == "30" && subPay == "121"){
				$(".payment_btn").text("正在跳转支付宝支付页面.......");
				window.location.href = path + "/pay/alipay/ALIWAPSEA?orderId=" + orderId;
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
				  
			}else if(mainPay == "27" && subPay == "117"){
				$(".payment_btn").text("正在跳转微信支付页面.......");
				window.location.href = path + "/pay/weixin/WEIXINWAP?orderId=" + orderId;
			}
			
		}else{
			alert(data.msg);
		}
		return;
	},"json");
}

//礼品卡支付
function giftcardpay(){
	var path = getRootPath();
	var orderId = $("#orderId").val();
	var mainPay = $("#payTypeId").val();
	var subPay = $("#payTypeChildId").val();
	var giftPsd = $("#giftPsd").val();
	var realPay = parseFloat($("#realpay").text());
	var date = $("#date").val();
	var payType;
	$(".zhifu").find("a").each(function(){
		if($(this).hasClass("cur")){
			var $payType = $(this).parent();
			if($payType.hasClass("alipay")){
				mainPay = "20";
				subPay = "37";
			}else if($payType.hasClass("unionPay")){
				mainPay = "19";
				subPay = "49";
			}else if($payType.hasClass("cashPay") || $payType.hasClass("postPay")){
				mainPay = "2";
				subPay = "41";
			}else if($payType.hasClass("weixinPay")){
				var id=$payType.attr("id")
				console.log(id);
				console.log(id=='weixinWap');
				if(id=='weixinWap'){
					mainPay = "27";
					subPay = "117";
				}else{
					mainPay = "27";
					subPay = "58";
				}
				mainPay = "27";
				subPay = "58";
			}else if($payType.hasClass("pufaPay")){
				mainPay = "15";
				subPay = "115";
			}else if($payType.hasClass("cmbcPay")){
				mainPay = "15";
				subPay = "118";
			}
		}
	});
	
	if(mainPay == "20" && subPay == "37"){
		payType = "20_20_37";
	}else if(mainPay == "19" && subPay == "49"){
		payType = "19_19_49";
	}else if(mainPay == "2" && subPay == "41"){
		payType = "4_2_41";
	}else if(mainPay == "27"&& subPay == "58"){
		payType = "21_27_58";
	}else if(mainPay == "15"&& subPay == "115"){
		payType = "21_15_115";
	}else if(mainPay == "27"&& subPay == "117"){
		payType = "21_27_117";
	}else if(mainPay == "15"&& subPay == "118"){
		payType = "21_15_118";
	}else{//表示订单来自其他站点，如果M站没有该支付方式，默认选择支付宝进行支付
		mainPay = "20";
		subPay = "37";
		payType = "20_20_37";
	}
	
 	if($("#giftCardpay").hasClass("cur")){
		if(giftPsd == ""){
			alert("请输入礼品卡密码!");
			return;
		}
		$(".payment_btn").text("正在支付，请稍等.......");
		$.post(path + "/order/card/pay",{
			"orderId" : orderId,
			"password" : giftPsd
		},function(data){
			if(data.code == "0"){
				//判断礼品卡是不是全额支付
				if(data.returnInfo.payresult == "2"){
					//全额支付，订单状态自动更新，跳转到支付成功页
					window.location.href = path  + "/order/card/success?orderId=" + orderId;
				}else{
					alert("礼品卡支付成功！");
					$.get(path + "/order/update/type?mainOrderNo=" + orderId + "&payType= " + payType, function(data){
						if(data.code != '0'){
							alert(data.msg);
							return;
						}else{
							//跳转到第三方支付页面
							if(mainPay == "20" && subPay == "37"){
								$(".payment_btn").text("正在跳转支付宝支付页面.......");
//								$.get(path + "/order/alipay?orderId=" + orderId + "&totalFee=" + data.returnInfo.onlineamount + "&date=" + date,function(data1){
//									window.location.href = data1;
//								});
								window.location.href = path + "/pay/alipay/ALIWAP?orderId=" + orderId;
							}else if(mainPay == "19" && subPay == "49"){
								$(".payment_btn").text("正在跳转银联支付页面.......");
//								$.get(path + "/order/yinlianpay?orderId=" + orderId + "&totalFee=" + data.returnInfo.onlineamount + "&date=" + date,function(data1){
//									window.location.href = data1;
//								});
								window.location.href = path + "/pay/unpay/UNWAP?orderId=" + orderId ;
								setTimeout(function(){
									window.location.href = path + "/order/install?orderId=" + orderId ;
								},2000);
							}else if(mainPay == "2" && subPay == "41"){
								window.location.href = path + "/pay/hdpay/success?orderId=" + orderId ;
							}else if(mainPay == "27"&& subPay == "58"){
								$(".payment_btn").text("正在跳转微信支付页面.......");

								window.location.href = path + "/pay/weixin/WEIXINPUB?orderId=" + orderId;
							  
							}else if(mainPay == "15"&& subPay == "115"){
									$(".payment_btn").text("正在跳转浦发银行支付页面.......");
									window.location.href = path + "/order/spdb/pay?orderId=" + orderId;
							}else if(mainPay == "27" && subPay == "117"){
									$(".payment_btn").text("正在跳转微信支付页面.......");
									window.location.href = path + "/pay/weixin/WEIXINWAP?orderId=" + orderId;
							}
						}
					});
				}
			}else{
				alert("礼品卡支付失败!");
				$(".payment_btn").text("立即支付");
				return;
			}
		},"json")
	}else{
		$.get(path + "/order/update/type?mainOrderNo=" + orderId + "&payType= " + payType, function(data){
			//alert(data.code);
			if(data.code != '0'){
				alert(data.msg);
				return;
			}else{
				//没有礼品卡支付，跳转第三方支付
				if(mainPay == "20" && subPay == "37"){
					$(".payment_btn").text("正在跳转支付宝支付页面.......");
//					$.get(path + "/order/alipay?orderId=" + orderId + "&totalFee=" + realPay + "&date=" + date,function(data1){
//						$("#payment_btn").text("立即支付");
//						window.location.href = data1;
//					});
					window.location.href = path + "/pay/alipay/ALIWAP?orderId=" + orderId;
				}else if(mainPay == "19" && subPay == "49"){
					$(".payment_btn").text("正在跳转银联支付页面.......");
//					$.get(path + "/order/yinlianpay?orderId=" + orderId + "&totalFee=" + realPay + "&date=" + date,function(data1){
//						$("#payment_btn").text("立即支付");
//						window.location.href = data1;
//					});
					window.location.href = path + "/pay/unpay/UNWAP?orderId=" + orderId;
					setTimeout(function(){
						window.location.href = path + "/order/install?orderId=" + orderId ;
					},2000);
				}else if(mainPay == "2" && subPay == "41"){
					window.location.href = path + "/pay/hdpay/success?orderId=" + orderId;
				}else if(mainPay == "27"&& subPay == "58"){
					$(".payment_btn").text("正在跳转微信支付页面.......");

					window.location.href = path + "/pay/weixin/WEIXINPUB?orderId=" + orderId;
				  
				}else if(mainPay == "15"&& subPay == "115"){
						$(".payment_btn").text("正在跳转浦发银行支付页面.......");
						window.location.href = path + "/order/spdb/pay?orderId=" + orderId;
					  
				}else if(mainPay == "27" && subPay == "117"){
					$(".payment_btn").text("正在跳转微信支付页面.......");
					window.location.href = path + "/pay/weixin/WEIXINWAP?orderId=" + orderId;
				}
			}
		});
	}
}

//进入失败页面，继续支付
function continuepay(){
	var path = getRootPath();
	var orderId = $("#orderId").val();
	var mainPay = $("#payTypeId").val();
	var subPay = $("#payTypeChildId").val();
	
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
		  
	}else if(mainPay == "27" && subPay == "117"){
		$(".payment_btn").text("正在跳转微信支付页面.......");
		window.location.href = path + "/pay/weixin/WEIXINWAP?orderId=" + orderId;
	}
	
}