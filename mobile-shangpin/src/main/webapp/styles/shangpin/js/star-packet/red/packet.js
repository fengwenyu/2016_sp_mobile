$(function(){
	/*$('#popup_overlay').fade();
	setTimeout(function(){
		$('#popup_overlay').hide();
	},16000);*/
	if($('#swiper_item').length){
	  var swiper = new Swiper('#swiper_item', {
		slidesPerView: 'auto',
		spaceBetween: 4,
	  });
	}
	
	function MobileErrorTip() {
		$(".prompt").css({height:'20px',marginTop:'10px'});
		$(".prompt").html("请输入正确手机号");
		$("#J_yzm").removeClass('error');
		$("#J_graphicsCode").removeClass('error');
		$("#J_mobileNum").addClass('error');
	};
	
	function yzmErrorTip() {
	  $(".prompt").html("验证码输入不正确");
	 $(".prompt").css({height:'20px',marginTop:'10px'});
	  $("#J_mobileNum").removeClass('error');
	  $("#J_graphicsCode").removeClass('error');
	  $("#J_yzm").addClass('error');
	 
	};
	
	function graphicsCodeErrorTip() {
	  $(".prompt").html("请输入右侧的数字");
	  $(".prompt").css({height:'20px',marginTop:'10px'});
	  $("#J_mobileNum").removeClass('error');
	  $("#J_yzm").removeClass('error');
	  $("#J_graphicsCode").addClass('error');
	};
	
	function removeErrorTip(){
	  $(".prompt").html("");
	  $(".prompt").css({height:'0',marginTop:'0'});
	  $("#J_mobileNum").removeClass('error');
	  $("#J_yzm").removeClass('error');
	  $("#J_graphicsCode").removeClass('error');
	}
	//点击领取按钮时验证输入内容是否正确
	var isMobile=/^1[34578]\d{9}$/; //手机号码验证规则
	$(".js-start-btn").click(function(){
		var phoneNum = $.trim($("#J_mobileNum").val());
		var verifyCode = $.trim($("#J_yzm").val());
		var star = $.trim($("#star").val());
		if ($.trim($("#J_mobileNum").val()) == "" || !isMobile.test($("#J_mobileNum").val())){
			MobileErrorTip();
			return;
		};	
		if ($.trim($("#J_yzm").val()) == ""){
			yzmErrorTip();
			return;
		};
		if($('.graphics-code').height()==40){
		  if ($.trim($("#J_graphicsCode").val()) == ""){
			  graphicsCodeErrorTip();
			  return;
		  };
		};
		removeErrorTip();
		//后台服务验证,首先判断是否输入图形验证码
		if($('.graphics-code').height()==40){//表示有图形验证码，验证图形验证码是否正确
			var captcha = $.trim($("#J_graphicsCode").val());
			var path = getRootPath();
			$.get(path + "/common/check/captcha?timstamp="+ new Date().getTime() + "&captcha=" + captcha,function(data){
				if(data.code == "1"){
					graphicsCodeErrorTip();
					return;
				}else{
					//判断手机号领取的情况
							$.get(path + "/red/receive/Coupons?imstamp="+ new Date().getTime() + "&phoneNum=" + phoneNum + "&verifyCode=" + verifyCode + "&star=" + star,function(data){
								if(data.code == "4"){//短信验证码输入错误
									yzmErrorTip();
									return;
								}else if(data.code == "0"){//领取成功
									var star =data.star;
									window.location.href = path + "/red/black_result?phoneNum=" +  phoneNum + "&star=" + star;
									return;
								}else if(data.code == "1"){//短信验证码输入错误
									alert(data.msg);
									return;
								}else{
									alert("抢红包人太多了，你没抢到，赶快再去抢！");
									return;
								}
							});
						}
			 });
		}else{
			var path = getRootPath();
			$.get(path + "/red/receive/Coupons?imstamp="+ new Date().getTime() + "&phoneNum=" + phoneNum + "&verifyCode=" + verifyCode + "&star=" + star,function(data){
				if(data.code == "4"){//短信验证码输入错误
					yzmErrorTip();
					return;
				}else if(data.code == "0"){
					var star = data.star;
					window.location.href = path + "/red/black_result?phoneNum=" +  phoneNum + "&star=" + star;
					return;
				}else if(data.code == "1"){//短信验证码输入错误
					alert(data.msg);
					return;
				}else{
					alert("抢红包人太多了，你没抢到，赶快再去抢！");
					return;
				}
			 });
     }
	return;		
});
	//判断手机输入框内容长度
	$("#J_mobileNum").on("keyup", function(){
		var len = $(this).val().length;
		if(len == 11){
			$(this).blur();
		}
	});
	$("#J_yzm").on("keyup", function(){
		var len = $(this).val().length;
		if(len == 6){
			$(this).blur();
		}
	});
	$("#J_graphicsCode").on("keyup", function(){
		var len = $(this).val().length;
		if(len == 4){
			$(this).blur();
		}
	});
	

			
	// 获取验证码
	var flag = 0;
	// var isS = 0;//true; //是否领过
	$("#passwordGetCode").click(function() {
				if ($.trim($("#J_mobileNum").val()) == "" || !isMobile.test($("#J_mobileNum").val())) {
					MobileErrorTip();
					return;
				} else {

					if ($("#passwordGetCode").hasClass('recode')) {
						$('.graphics-code').css({
							height : '40px',
							marginTop : '10px'
						});
					}
					if ($("#J_yzm").hasClass('error')) {
						// $('.graphics-code').show();
					} else {
						removeErrorTip();
					}
				}

				if (flag != 0) {
					return false;
				}

				flag = 1;
				var delay = 90, trigger = this, revert = $(trigger).text();
				var path = getRootPath();
				var phoneNum = $.trim($("#J_mobileNum").val());
				var star = $.trim($("#star").val());
				$.get(path + "/red/isSendInfo?timstamp=" + new Date().getTime()	+ "&phoneNum=" + phoneNum+ "&star=" + star, function(data) {
					if (data.code == "1") {
						alert(data.msg);
						return;
					} else if (data.code == "3") {
						popBox(data.star,data.phone);
						return;
					} 
					// obtainCode();
					$("#passwordGetCode").addClass('btn_gradient_gray');
					$(trigger).attr({'disabled' : true }).text(delay + $(trigger).attr("data-waiting")).parents("label").addClass("waiting");
					var counter = setInterval(function() {
						$(trigger).text(
								$(trigger).text().replace(delay, --delay));
						if (delay == 0) {
							flag = 0;
							window.clearInterval(counter);
							$(trigger).text(revert).removeAttr("disabled")
									.parents("label")
									.removeClass("waiting");
							$("#passwordGetCode").removeClass(
									'btn_gradient_gray');
							$("#passwordGetCode").html("重新获取");
							$("#passwordGetCode").addClass('recode');
						}
					}, 1000);
					
				});
	  });
	
	//领过弹出层
	function popBox(star,phone){
		$('#popup_message').html("您已经领过了");
		//$('#popup_overlay').show();
		$('#popup_container').show();
		setTimeout(function(){
			//$('#popup_overlay').hide();
			$('#popup_container').hide();
			window.location=getRootPath()+"/red/black_result?phoneNum="+phone+"&star="+star;
		},2000);
	}


	$('.js-share-btn').click(function(){
		$('.js-share-pop').show();	
	});
	$('.js-share-pop').click(function(){
		$('.js-share-pop').hide();	
	});	

	//新增点击分享图层
	$("#share_btn").click(function(){
		$('#popup_overlay').show();
		return false;
	});
	$("#popup_overlay").click(function(){
		$('#popup_overlay').hide();
		return false;
	});
	
});
function changeImage(){
	var path = getRootPath();
	var img = path + "/common/captcha?timstamp="+ new Date().getTime() ;
	$(".code-img").attr("src",img);
}

