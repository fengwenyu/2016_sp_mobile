$(function(){
	
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
	
	$('.without-box .close-btn').click(function(){
		$('.without-box').fadeOut();
	});
	

	function MobileErrorTip() {
		$(".prompt").css({height:'20px',marginTop:'10px'});
		$(".prompt").html("请输入正确手机号");
		$("#J_yzm").removeClass('error');
		$("#J_mobileNum").addClass('error');
	};
	
	function yzmErrorTip() {
	  $(".prompt").html("请输入正确验证码");
	 $(".prompt").css({height:'20px',marginTop:'10px'});
	  $("#J_mobileNum").removeClass('error');
	  $("#J_yzm").addClass('error');
	};
	
	function removeErrorTip(){
	  $(".prompt").html("");
	  $(".prompt").css({height:'0',marginTop:'0'});
	  $("#J_mobileNum").removeClass('error');
	  $("#J_yzm").removeClass('error');
	  
	}
	//点击领取按钮时验证输入内容是否正确
	var isMobile=/^1[3578]\d{9}$/; //手机号码验证规则
	$(".js-start-btn").click(function(){
		
		if ($.trim($("#J_mobileNum").val()) == "" || !isMobile.test($("#J_mobileNum").val())){
			MobileErrorTip();
			return;
		};	
		if ($.trim($("#J_yzm").val()) == ""){
			yzmErrorTip();
			return;
		};
		
		removeErrorTip();
		var path = getRootPath();
		var phoneNum = $("#J_mobileNum").val();
		var verifycode = $("#J_yzm").val();
		$.post(path + "/weixin/check",{phoneNum:phoneNum,verifycode:verifycode},function(data){
			if(data.code == "1"){
				alert(data.msg);
				return;
			}else if(data.code == "2"){
				//领光
				$('.without-box h3').html("你来晚了");
				$('.without-box p').html("礼包发完了");
				$('.without-box .click-look').html("确定");
				$('.without-box .click-look').addClass('end');
				$('.without-box').fadeIn();
				$('.end').click(function(){
				$('.without-box').fadeOut();
				});
				return;
			}else if(data.code == "0"){
				//领取成功
				$('.without-box h3').html("领取成功");
				$('.without-box p').html("礼券包已放入尚品网官微-我的优惠券中");
				$('.without-box .click-look').html("点击查看");
				$('.without-box').fadeOut();
				window.location.href = "http://m.shangpin.com/coupon/list";
				return;
			}else{
				alert(data.msg);
				return;
			}
		},"json");
				
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
	
		
	//获取验证码
	var flag = 0;
	$("#passwordGetCode").click(function() {
		if ($.trim($("#J_mobileNum").val()) == "" || !isMobile.test($("#J_mobileNum").val())){
			MobileErrorTip();
			return;
		}else{
			
			if($("#J_yzm").hasClass('error')){
				//$('.graphics-code').show();
			}else{
				removeErrorTip();
			}	
		}
		
		flag = 1;
		var path = getRootPath();
		var phoneNum = $.trim($("#J_mobileNum").val());
		var delay = 90, revert = $("#passwordGetCode").text();
		//obtainCode();
		$.get(path + "/common/send/phone/code?timstamp="+ new Date().getTime() + "&phoneNum=" + phoneNum,function(data){
			if(data.code != "0"){
				alert(data.msg);
				return;
			}else{
				$("#passwordGetCode").addClass('btn_gradient_gray');
				$("#passwordGetCode").attr({'disabled': true }).text(delay + $("#passwordGetCode").attr("data-waiting")).parents("label").addClass("waiting");
				var counter = setInterval(function() {
					$("#passwordGetCode").text($("#passwordGetCode").text().replace(delay, --delay));
					if(delay == 0) {
						flag = 0;
						window.clearInterval(counter);
						$("#passwordGetCode").text(revert).removeAttr("disabled").parents("label").removeClass("waiting");
						$("#passwordGetCode").removeClass('btn_gradient_gray');
						$("#passwordGetCode").html("重新获取验证码");
						$("#passwordGetCode").addClass('recode');
					}
				}, 1000);
			}
		});
	});
	
});