$(document).ready(function(e) {
	//下拉弹出层
	var appObj = $("#appLayer");
	if(appObj.length>0){
		
		appObj.removeClass("slideUpApp").addClass("slideDownApp");
		setTimeout(function(){window.scroll(0,1);},1000);
		
		/*if(appObj.hasClass("slideDownApp")){
			setTimeout(function(){$(".alContent").hide();},1000);
		}*/
		
		$("#hideAppLayer,.startleave").click(function(){
			appObj.removeClass("slideDownApp").addClass("slideUpApp");
			return false;
		});
	}
	
	//跑马灯开始
	var AutoRoll = function(){
		$(".lottery_list").animate({
			marginTop:"-24px"
		},500,function(){
			$(this).css({marginTop:"0px"}).find("li:first").appendTo(this);
		});
	};
	
	var startRoll = setInterval(AutoRoll,3000);
	
	
	/*优惠劵弹层*/
	var $overlay = $('#overlay');
	function modalHidden($ele) {
		$('body').removeClass('onBody');
		$ele.removeClass('modal-in');
		$ele.one('webkitTransitionEnd',function(){
		  $ele.css({"display": "none"});
		  $overlay.removeClass('active');
		});
	}
	
	$('.coupon-btn,#hideAppLayer').click(function(e){
		e.preventDefault();
		var $that = $(this);
		var ctx = $("#ctx").val();
		var userId = $("#userId").val();
		//判断用户是否应经领取过优惠券
		$.ajax({
			url : ctx + "/goddes/receive?timestmp=" + new Date().getTime(),
			type : "get",
			async:false,
			dataType:'json',
			success:function(data){
				if(data.code == "1"){
					alert(data.msg);
					return;
				}else{
					//判断是否为尚品老用户，如果是老用户则不用弹层
					//alert("userId:" + userId);
					if(userId != null && userId != ""){
						$.ajax({
							url : ctx + "/goddes/direct/receive?timestmp=" + new Date().getTime(),
							type : "get",
							async:false,
							dataType:'json',
							success:function(data){
								alert(data.msg);
								return;
							}
						});
					}else{
						$('body').addClass('onBody');
						$overlay.addClass('active');
						$('.modal').css({"display": "block"});
						$('.modal').animate(100,function(){			  
						  $('.modal').addClass('modal-in');
						});
					}
				}
			}
		});
	});
	
	$('.close').click(function(e){
	  modalHidden($('.modal'));
	  e.stopPropagation();
	});	
	
	$overlay.click(function(e){
	  if(e.target.classList.contains('overlay')){
		modalHidden($('.modal'));
	  }
	});	
	
	//验证码倒计时
	var isclick = true;
	$("#passwordGetCode").on("click",function(){
		var mre = /^1\d{10}$/;
		if ($.trim($("#J_mobileNum").val()) == "" || !mre.test($("#J_mobileNum").val())){
			$(".prompt").html("请输入正确手机号");
    		return;
		}
		if(!isclick) return false;
		var that = $(this),timeId;
		var num = 90;
		var thiscon = $(this).attr("data-waiting");
		that.text(num+thiscon);
		var ctx = $("#ctx").val();
		var phoneNum = $("#J_mobileNum").val();
		$.post(ctx + "/goddes/sendPhoneCode",{"phoneNum" : phoneNum},function(data){
			if(data.code == "1"){
				$(".prompt").html("手机验证码下发失败");
			}
		},"json");
		isclick = false;
		timeId = setInterval(function(){
			num--;
			that.text(num+thiscon)
			if(num == 0){
				clearInterval(timeId);
				that.text("获取验证码");
				isclick = true;					
			}				
		},1000);
		
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
	
	//点击领取按钮时验证输入内容是否正确
	$(".sure-btn").click(function(){
		var ctx = $("#ctx").val();
		var mre = /^1\d{10}$/;
		//手机号码验证
    	if ($.trim($("#J_mobileNum").val()) == "" || !mre.test($("#J_mobileNum").val())){
			$(".prompt").html("请输入正确手机号");
    		return;
		}	
		if ($.trim($("#J_yzm").val()) == ""){
			$(".prompt").html("请输入正确验证码");
    		return;
		}
		//领取优惠券
		var verifycode = $("#J_yzm").val();
		var phoneNum = $("#J_mobileNum").val();
		$.post(ctx + "/goddes/check",{"phoneNum" : phoneNum, "verifycode": verifycode},function(data){
			//alert("coupon code result:" + data);
			if(data.code == "1"){
				//alert(data.msg);
				var couponShowHtml = '<h3>抱歉</h3><p>对不起，你已领取过了，请到个人中心查看</p><div class="step-btn"><a href="#" class="close_btn">确认</a></div>';
				//return;
			}else{
				//alert(data.msg);
				modalHidden($('.modal'));
				var couponShowHtml = '<h3>激活成功</h3><p>礼券包已放入<em class="user_number">' + phoneNum + '</em></p><p>账户,在App Store或安卓应用市场搜索“尚品网”下载APP，礼券包购物即享优惠！</p><div class="step-btn"><a href="#" class="close_btn">确认</a></div>';
				var $sw = $("#sucess_window");
			    function modalHidden2($ele) {	
			    	 $('body').removeClass('onBody');
					  $ele.removeClass('couponShow-in');
					  setTimeout(function(){
						  $ele.css({"display": "none"});
						$sw.removeClass('active');
					  },300)
				}
				
				setTimeout(function(){
					$('body').addClass('onBody');
					$sw.addClass('active');
					$('.couponShow').html(couponShowHtml);
					$('.couponShow').css({"display": "block"});
					$('.couponShow').animate(100,function(){			  
						$('.couponShow').addClass('couponShow-in');
					});
					$(".close_btn").click(function(e){
							modalHidden2($(".couponShow"));
							e.stopPropagation();
					});
					$sw.click(function(e){					
					  if(e.target.classList.contains('sucess_window')){
							modalHidden2($(".couponShow"));
					  }
					});
				},300)
				return;	
			}
		},"json");	
	});
});	