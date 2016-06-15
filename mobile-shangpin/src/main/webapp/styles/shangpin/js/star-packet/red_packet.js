$(function(){
	//分享
	$('.share-btn').click(function(){
		var isWeixin = $("#_iswx").val();
		if(isWeixin){
			$('.share-box').fadeIn();
		}else{
			//havaOther();192.168.20.77/mshangpin  m.shangpin.com localhost:8080/mobile-shangpin
			var batchNo = $("#batchNo").val();
			var phoneNum = $("#phoneNum").val();
			var url = encodeURIComponent("http://m.shangpin.com" + "/star/title?batchNo=" + batchNo + "&phoneNum=" + phoneNum + "&share=weibo"); 
			var title = "杨幂发价值5000万红包，抢疯了！";
			var pic = "http://m.shangpin.com/styles/shangpin/images/star-packet/weixin_share.jpg";
			window.location.href = "http://v.t.sina.com.cn/share/share.php?url=" + url + "&title=" + title + "&language=zh_cn&pic=" + pic;
		}
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
	//$('.receive-btn').click(function(){
		//$('.without-box').fadeIn();
	//});
	
	$('.without-box .close-btn').click(function(){
		$('.without-box').fadeOut();
	});
	
	
	
	//跑马灯开始
	var AutoRoll = function(){
		$(".lottery_list").animate({
			marginTop:"-24px"
		},500,function(){
			$(this).css({marginTop:"0px"}).find("li:first").appendTo(this);
		});
	};
	var startRoll = setInterval(AutoRoll,3000);
	
	function MobileErrorTip() {
		$(".prompt").css({height:'20px',marginTop:'10px'});
		$(".prompt").html("请输入正确手机号");
		$("#J_yzm").removeClass('error');
		$("#J_graphicsCode").removeClass('error');
		$("#J_mobileNum").addClass('error');
	};
	
	function yzmErrorTip() {
	  $(".prompt").html("请输入正确验证码");
	 $(".prompt").css({height:'20px',marginTop:'10px'});
	  $("#J_mobileNum").removeClass('error');
	  $("#J_graphicsCode").removeClass('error');
	  $("#J_yzm").addClass('error');
	 
	};
	
	function graphicsCodeErrorTip() {
	  $(".prompt").html("请输入正确图形码");
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
	var isMobile=/^1[3578]\d{9}$/; //手机号码验证规则
	$(".js-start-btn").click(function(){
		var path = getRootPath();
		var batchNo = $("#batchNo").val();
		var phoneNum = $.trim($("#J_mobileNum").val());
		var source = $("#source").val();
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
					$.get(path + "/star/check/coupon/phone?timstamp="+ new Date().getTime() + "&phoneNum=" + phoneNum + "&batchNo=" + batchNo + "&source=" + source + "&verifyCode=" + verifyCode,function(data){
						if(data.code == "4"){
							alert(data.msg);
						}else if(data.code == "1"){//表示该手机号已经不能再领取了
							var amount = data.couponInfo[0].amount;
							window.location.href = path + "/star/out?batchNo=" + batchNo + "&phoneNum=" +  phoneNum + "&amount=" + amount;
							return;
						}else if(data.code == "2"){//表示该手机号已经领取过一次，还能领取一次，跳转到去分享页面
							var amount = data.couponInfo[0].amount;
							window.location.href = path + "/star/share?batchNo=" + batchNo + "&phoneNum=" +  phoneNum + "&amount=" + amount;
							return;
						}else if(data.code == "3"){//表示券被领光
							$('.without-box').fadeIn();
							return;
						}else{
							$.get(path + "/star/check/have?imstamp="+ new Date().getTime() + "&phoneNum=" + phoneNum + "&batchNo=" + batchNo + "&verifyCode=" + verifyCode,function(data){
								if(data.code == "1"){//短信验证码输入错误
									yzmErrorTip();
									return;
								}else if(data.code == "2"){//优惠券已经领完
									$('.without-box').fadeIn();
									return;
								}else if(data.code == "0"){//领取成功
									var amount = data.couponInfo.amount;
//									var temple = "尊敬的会员，您已经成功领取了杨幂的红包，" + amount + "元现金券已经充值到"+ phoneNum +"的账户中，有效期至2015年12月31日，点击t.cn/RvweXV1";
//									$.get(path + "/star/send/phone/code?timstamp=" + new Date().getTime() + "&phoneNum=" + phoneNum + "&temple=" + temple,function(data){
//									});
									window.location.href = path + "/star/share?batchNo=" + batchNo + "&phoneNum=" +  phoneNum + "&amount=" + amount;
									return;
								}else{
									alert("抢红包人太多了，你没抢到，赶快再去抢！");
									return;
								}
							});
						}
					});
				}
			});
		}else{
			//判断手机号领取的情况
			$.get(path + "/star/check/coupon/phone?timstamp="+ new Date().getTime() + "&phoneNum=" + phoneNum + "&batchNo=" + batchNo + "&source=" + source + "&verifyCode=" + verifyCode,function(data){
				if(data.code == "4"){
					alert(data.msg);
					return;
				}else if(data.code == "1"){//表示该手机号已经不能再领取了
					var amount = data.couponInfo[0].amount;
					window.location.href = path + "/star/out?batchNo=" + batchNo + "&phoneNum=" +  phoneNum + "&amount=" + amount;
					return;
				}else if(data.code == "2"){//表示该手机号已经领取过一次，还能领取一次，跳转到去分享页面
					var amount = data.couponInfo[0].amount;
					window.location.href = path + "/star/share?batchNo=" + batchNo + "&phoneNum=" +  phoneNum + "&amount=" + amount;
					return;
				}else if(data.code == "3"){//表示券被领光
					$('.without-box').fadeIn();
					return;
				}else{
					$.get(path + "/star/check/have?imstamp="+ new Date().getTime() + "&phoneNum=" + phoneNum + "&batchNo=" + batchNo + "&verifyCode=" + verifyCode,function(data){
						if(data.code == "1"){//短信验证码输入错误
							yzmErrorTip();
							return;
						}else if(data.code == "2"){//优惠券已经领完
							$('.without-box').fadeIn();
							return;
						}else if(data.code == "0"){
							var amount = data.couponInfo.amount;
//							var temple = "尊敬的会员，您已经成功领取了杨幂的红包，" + amount + "元现金券已经充值到"+ phoneNum +"的账户中，有效期至2015年12月31日，点击t.cn/RvweXV1";
//							$.get(path + "/star/send/phone/code?timstamp=" + new Date().getTime() + "&phoneNum=" + phoneNum + "&msgTemplate=" + temple,function(data){
//							});
							window.location.href = path + "/star/share?batchNo=" + batchNo + "&phoneNum=" +  phoneNum + "&amount=" + amount;
							return;
						}else{
							alert("抢红包人太多了，你没抢到，赶快再去抢！");
							return;
						}
					});
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
			var path = getRootPath();
			var phoneNum = $.trim($("#J_mobileNum").val());
			var batchNo = $("#batchNo").val();
			//判断输入手机号领取券的情况
			if($("#passwordGetCode").hasClass('recode')){
				$('.graphics-code').css({height:'40px',marginTop:'10px'});
			}
			if($("#J_yzm").hasClass('error')){
				//$('.graphics-code').show();
			}else{
				removeErrorTip();
			}
			
			flag = 1;
			var delay = 90, revert = $("#passwordGetCode").text();
			//下发短信验证码
			var msgTemplate = "验证码:{$verifyCode$}，你正在领取红包，需要验证。【请勿把验证码告知他人】";
			$.get(path + "/star/send/phone/code?timstamp="+ new Date().getTime() + "&phoneNum=" + phoneNum + "&msgTemplate=" + msgTemplate,function(data){
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
							$("#passwordGetCode").html("重新获取");
							$("#passwordGetCode").addClass('recode');
						}
					}, 1000);
				}
			});
		}
		
	});
	
});

//分享完成后用户再领取一个红包
function havaOther(){
	var path = getRootPath();
	var batchNo = $("#batchNo").val();
	var phoneNum = $("#phoneNum").val();
	$.post(path + "/star/hava/other",{"batchNo":batchNo, "phoneNum":phoneNum},function(data){
		if(data.code == "3"){//表示券已经领取光
			$('.without-box').fadeIn();
			return;
		}else if(data.code == "2"){//表示该正好已经领取完2个红包
			var amount = data.couponInfo[0].amount;
			window.location.href = path + "/star/out?batchNo=" + batchNo + "&phoneNum=" +  phoneNum + "&amount=" + amount;
			return;
		}else if(data.code == "4"){//表示session 用户时效跳转到首页输入手机号
			window.location.href = path + "/star/index?batchNo=" + batchNo + "&source=share";
			return;
		}else if(data.code == "0"){//表示领取成功，跳转到已领取的页面
			var amount = data.couponInfo.amount;
//			var temple = "尊敬的会员，您已经成功领取了杨幂的红包，" + amount + "元现金券已经充值到"+ phoneNum +"的账户中，有效期至2015年12月31日，点击t.cn/RvweXV1";
//			$.get(path + "/star/send/phone/code?timstamp=" + new Date().getTime() + "&phoneNum=" + phoneNum + "&msgTemplate=" + temple,function(data){
//			});
			window.location.href = path + "/star/out?batchNo=" + batchNo + "&phoneNum=" +  phoneNum + "&amount=" + amount + "&source=1";
			return;
		}else{
			alert("抢红包人太多了，你没抢到，赶快再去抢！");
			return;
		}
	},"json");
}

function changeImage(){
	var path = getRootPath();
	var img = path + "/common/captcha?timstamp="+ new Date().getTime() ;
	$(".code-img").attr("src",img);
}

function share(){
	
	window.location.href = path + "/star/had?batchNo=" + batchNo + "&phoneNum=" + phoneNum;
}