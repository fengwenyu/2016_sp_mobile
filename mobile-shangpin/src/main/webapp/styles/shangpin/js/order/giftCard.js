$(document).ready(function(){
	// 应付金额
	var amount = $("#payAmountValue").val();
	// 礼品卡总金额
	var giftCardAmount = $("#giftCardAmount").val();
	//判断是否需要其他支付方式
	var balance =giftCardAmount-amount;
	//得到礼品卡支付的金额
	if(balance > 0){
		$("#giftCardPay").html(amount);
		//全部由礼品卡支付   1为部分支付 2为完全支付
		$("#giftCardAllPay").val("2");
		//隐藏其他方式支付
		
		$(".price").hide();
		$(".nouse").hide();
	}else{
		$("#giftCardAllPay").val("1");
		$("#giftCardPay").html(giftCardAmount);
		balance=amount - giftCardAmount;
		//其他方式支付显示
		$(".price em").html(balance);
		$(".price").show();
		$(".nouse").show();
	}
	//是否支持货到付款
	isPayOnDelivery();
	//提交失败
	if($("#msgReturn").val()!=""){
		if($("#msgReturn").val()=="9350e446"){
			alert("卡账户冻结，请24小时后重试");
		}else{
			alert($("#msgReturn").val());
		}
		
		$("#msgReturn").val("");
	}
});
//礼品卡支付页面 --选择礼品卡支付
function _calculateGiftCard() {
	var useGiftCard = $("p.giftCard a").attr("useGiftCard");
	$("#isUseGiftCard").val(useGiftCard);
	_toggleGiftCard();
	$("p.giftCard a").toggleClass("cur");
}

// 礼品卡支付页面 --切换礼品卡显示
function _toggleGiftCard() {
	// 应付金额
	var amount = $("#payAmountValue").val();
	// 礼品卡总金额
	var giftCardAmount = $("#giftCardAmount").val();
	// 礼品卡减去应付值
	var balance =giftCardAmount-amount;
	// 是否已经使用礼品卡
	var isUseGiftCard = $("#isUseGiftCard").val();
	if (isUseGiftCard == "0") {
		// 已使用礼品卡
		if (balance>0) {
			//全部由礼品卡支付
			$("#giftCardAllPay").val("2");
			//隐藏其他方式支付
			$(".price").hide();
			$(".nouse").hide();
		} else {
			$("#giftCardAllPay").val("1");
			balance=amount - giftCardAmount;
			//其他方式支付显示
			$(".price em").html(balance);
			$(".price").show();
			$(".nouse").show();
		}
		// 设置为可使用
		$("p.giftCard a").attr("useGiftCard", "1");
		$("#giftPsd").show();
		$(".r_link").show();
		$("#isUseGiftCard").val("1");
		
	} else {
		$(".price em").html(amount);
		$(".price").show();
		$(".nouse").show();
		$("#giftPsd").hide();
		$(".r_link").hide();
		// 设置为取消
		$("p.giftCard a").attr("useGiftCard", "0");
		$("#isUseGiftCard").val("0");
	}
}

//礼品卡支付
function giftCardPayment(){
	$("#sb").attr("disabled","disabled");
	//得到是否是礼品卡支付
	var isUseGiftCard = $("#isUseGiftCard").val();
	if(isUseGiftCard =="1"){
		//礼品卡支付 //如果是礼品卡之支付 判断密码是否符合
		var p = /(?!^\d+$)(?!^[a-zA-Z]+$)^[\x21-\x7E]{6,20}$/;
		if($.trim($("#giftPsd").val()) == "" || !p.test($("#giftPsd").val())){
			$("#sb").removeAttr("disabled");
			return jShare('请输入正确的密码 !', "", ""),-1;
		}
	}
	//提交表单
	document.getElementById("giftCardForm").submit();
}

function showForget(){
	if($("#phoneHide").val()==""){
		jAlert('您的礼品卡未绑定手机号，请联系客服<a style="color: #fff;text-decoration:underline;" href="tel:4006-900-900"> 4006-900-900 </a>（08:00-24:00）！','','');
		return;
		}
	 $("#giftCardCon").hide();
	 $("#giftCardTop").hide();
	 $("#forgetCon").show();
	 $("#forgetTop").show();
}

var _block=false;
//忘记密码提交
function forgetSubmit(){
	if ($.trim($("#J_MobiCode").val()) == "" || $("#J_MobiCode").val().length != 6){
		return $(".mobiMsg").html("请输入正确验证码！"),
		$("#J_MobiCode").addClass("error"),
		!1;
	}else{
		$(".mobiMsg").html("");
		$("#J_MobiCode").removeClass("error");
	}
	var p = /(?!^\d+$)(?!^[a-zA-Z]+$)^[\x21-\x7E]{6,20}$/;
	if ($.trim($("#J_MobiPwd").val()) == "" || !p.test($("#J_MobiPwd").val())){
		return $(".mobiMsg").html("新支付密码(6-20位数字+字母结合)！"),
		$("#J_MobiPwd").addClass("error"),
		!1;
	}else{
		$(".mobiMsg").html("");
		$("#J_MobiPwd").removeClass("error");
	}
	
	if ($.trim($("#J_resetPwd").val()) == ""){
		return $(".mobiMsg").html("请输入确认密码！"),
		$("#J_resetPwd").addClass("error"),
		!1;
	}else if($.trim($("#J_MobiPwd").val()) != $.trim($("#J_resetPwd").val())){
		return $(".mobiMsg").html("两次密码输入不一致！"),
		$("#J_resetPwd").addClass("error"),
		!1;
	}else{
		$(".mobiMsg").html("");
		$("#J_resetPwd").removeClass("error");
	}
	var verifyCode=$("#J_MobiCode").val();
	//如果锁定返回
	if(_block){
		return;
	}
	_block=true;
	//验证手机验证码是否正确
		  $.post("accountaction!verifyGiftCardCode",
					{
					 "verifyCode":verifyCode
					},
					function(data){
						if(data.code!='0'){	
							_block=false;
							return $(".mobiMsg").html("请输入正确验证码！"),
							$("#J_MobiCode").addClass("error"),
							!1;
						}else{
							$(".mobiMsg").html("");
							$("#J_MobiCode").removeClass("error");
							//重置礼品卡密码
							$.post("accountaction!restGiftCardPwd",
									{
									 "verifyCode":verifyCode,
									 "password":$("#J_resetPwd").val()
									},
									function(data){
										 _block=false;
										if(data.code=='0'){	
											alert("礼品卡新密码设置成功！");
											$("#J_MobiCode").val("");
											$("#J_MobiPwd").val("");
											$("#J_resetPwd").val("");
											//手机验证码切换
										 	$("#reSendCode").hide();
											$("#sendCode").html("获取验证码");
											$("#sendCode").show();
											clearInterval(process);
											forgetBack();
										}else{
											alert(data.msg);
											return ;
										}
									},
									"json");
						}
					},
					"json");
      }   
var process;
function getCode(){
	$.post("accountaction!sendGiftCardPhone",
			{},
			function(data){
				if(data.code=='0'){
					//发送成功，60秒限制
					var t=60;
					process=window.setInterval(function(){
						t--;
						$("#reSendCode").html(t+"秒后重新发送");
						$("#reSendCode").show();
						$("#sendCode").hide();
						if(t==0){
							$("#reSendCode").hide();
							$("#sendCode").html("重新获取验证码");
							$("#sendCode").show();
							(function(){
								clearInterval(process);
							})();
						}
					},1000);
					alert("验证码已发送，查看手机短信");
				}else{
					alert(data.msg);
				}
			},
			"json");
}

//返回
function forgetBack(){
	 $("#forgetCon").hide();
	 $("#forgetTop").hide();
	 $("#giftCardCon").show();
	 $("#giftCardTop").show();
	 
}