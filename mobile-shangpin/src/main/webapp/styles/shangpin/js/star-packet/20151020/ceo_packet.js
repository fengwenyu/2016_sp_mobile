$(function(){
	var amount=$("#amount").val();
	if(amount!=null && amount!=0){
		$(".show-cash").show();
	}else{
		$(".show-cash").hide();
	}
	//分享
	$('.share-btn').click(function(){
		$('.share-box').fadeIn();
	});
	
	$('.know-btn').click(function(){
		$('.share-box').fadeOut();
	});
	//活动规则
	$('.rule-btn').click(function(){
		$('.red-use-info-box').fadeIn();
	});
	
	$('.red-use-info-box .close-btn').click(function(){
		$('.red-use-info-box').fadeOut();
	});
	//领光了
	$('.receive-btn').click(function(){
		$('.without-box').fadeIn();
	});
	
	$('.without-box .close-btn').click(function(){
		$('.without-box').fadeOut();
	});
	
	//跑马灯开始
	/*var AutoRoll = function(){
		$(".lottery_list").animate({
			marginTop:"-24px"
		},500,function(){
			$(this).css({marginTop:"0px"}).find("li:first").appendTo(this);
		});
	};
	var startRoll = setInterval(AutoRoll,3000);*/
	
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
		var path = getRootPath();
		var batchNo = $("#batchNo").val();
		var source = $("#source").val();
		var phoneNum = $.trim($("#J_mobileNum").val());
		var verifyCode = $.trim($("#J_yzm").val());
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
			$.get(path + "/common/check/captcha?timstamp="+ new Date().getTime() + "&captcha=" + captcha,function(data){
				if(data.code == "1"){
					graphicsCodeErrorTip();
					return;
				}else{
					//判断手机号领取的情况
							$.get(path + "/red/receive/Coupons?imstamp="+ new Date().getTime() + "&phoneNum=" + phoneNum + "&verifyCode=" + verifyCode,function(data){
								if(data.code == "4"){//短信验证码输入错误
									yzmErrorTip();
									return;
								}else if(data.code == "3"){//到老用户页面
									var amount =data.amount;
									window.location.href = path + "/red/resultOld?phoneNum=" +  phoneNum +  "&amount=" + amount;
									return;
								}else if(data.code == "2"){//优惠券已经领完
									$('.without-box').fadeIn();
									return;
								}else if(data.code == "0"){//领取成功
									var amount =data.amount;
									window.location.href = path + "/red/result?phoneNum=" +  phoneNum + "&amount=" + amount;
									return;
								}else{
									alert("抢红包人太多了，你没抢到，赶快再去抢！");
									return;
								}
							});
						}
			 });
		}else{
			$.get(path + "/red/receive/Coupons?imstamp="+ new Date().getTime() + "&phoneNum=" + phoneNum + "&verifyCode=" + verifyCode,function(data){
					if(data.code == "4"){//短信验证码输入错误
						yzmErrorTip();
						return;
					}else if(data.code == "3"){//到老用户页面
						var amount =data.amount;
						window.location.href = path + "/red/resultOld?phoneNum=" +  phoneNum + "&amount=" + amount;
						return;
					}else if(data.code == "2"){//优惠券已经领完
						$('.without-box').fadeIn();
						return;
					}else if(data.code == "0"){
						var amount = data.amount;
						window.location.href = path + "/red/result?phoneNum=" +  phoneNum + "&amount=" + amount;
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
	
		
	//获取验证码
	var flag = 0;
	$("#passwordGetCode").click(function() {
		if ($.trim($("#J_mobileNum").val()) == "" || !isMobile.test($("#J_mobileNum").val())){
			MobileErrorTip();
			return;
		}else{
			
			if($("#passwordGetCode").hasClass('recode')){
				$('.graphics-code').css({height:'40px',marginTop:'10px'});
			}
			if($("#J_yzm").hasClass('error')){
				//$('.graphics-code').show();
			}else{
				removeErrorTip();
			}	
		}
		
		if(flag!=0){
			return false;
		}
		
		flag = 1;
		var path = getRootPath();
		var phoneNum = $.trim($("#J_mobileNum").val());
		var delay = 90, trigger = this, revert = $(trigger).text();
		//obtainCode();
		$.get(path + "/common/send/phone/code?timstamp="+ new Date().getTime() + "&phoneNum=" + phoneNum,function(data){
			if(data.code != "0"){
				alert(data.msg);
				return;
			}else{
				//obtainCode();
				$("#passwordGetCode").addClass('btn_gradient_gray');
				$(trigger).attr({'disabled': true }).text(delay + $(trigger).attr("data-waiting")).parents("label").addClass("waiting");
				var counter = setInterval(function() {
					$(trigger).text($(trigger).text().replace(delay, --delay));
					if(delay == 0) {
						flag = 0;
						window.clearInterval(counter);
						$(trigger).text(revert).removeAttr("disabled").parents("label").removeClass("waiting");
						$("#passwordGetCode").removeClass('btn_gradient_gray');
						$("#passwordGetCode").html("重新获取");
						$("#passwordGetCode").addClass('recode');
					}
				}, 1000);
	       }
	   });
    });
});

function changeImage(){
	var path = getRootPath();
	var img = path + "/common/captcha?timstamp="+ new Date().getTime() ;
	$(".code-img").attr("src",img);
}