$(function(){
	/*优惠劵弹层*/
	
	//验证码倒计时
	var isclick = true;
	var isS = false;//true; //是否参与过抽奖
	
	$("#passwordGetCode").on("click",function(){
		if(!isclick) return false;
		
		//手机号码验证
    	if ($.trim($("#J_mobileNum").val()) == "" || !mre.test($("#J_mobileNum").val())){
			$(".prompt").html("请输入正确手机号");
			$(".prompt").css({height:'20px',marginTop:'5px'});
    		return;
		}
		$(".prompt").html("");
		$(".prompt").css({height:'0px',marginTop:'0px'});
		var path = getRootPath();
		var phoneNum = $.trim($("#J_mobileNum").val());
		//检查手机号是否参与过该活动
		$.get(path + "/one/is/check/phone/activity?phoneNum=" + phoneNum,function(data){
			if(data.code == "1"){
				  couponShowHtml = '<h3>您已经参与过活动啦</h3><p>每个手机号只能参与一次，不可以重复参与哦！</p><div class="step-btn1"><a href="#" class="close_btn">确认</a></div>';
				  popBox();
				  return;	
			}else{
				var that = $("#passwordGetCode"),timeId;
				var num = 90;
				var thiscon = $("#passwordGetCode").attr("data-waiting");
				that.text(num+thiscon)
				//下发短信验证码
    			$.get(path + "/common/send/phone/code?timstamp="+ new Date().getTime() + "&phoneNum=" + phoneNum,function(data){
    				if(data.code != "0"){
    					alert(data.msg);
    					return;
    				}else{
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
    				}
    			});
			}
		});
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
	
	var mre = /^1\d{10}$/;
	//点击领取按钮时验证输入内容是否正确
	$(".coupon-btn").click(function(e){			
	    e.preventDefault();
		
		if(isS){
			 couponShowHtml = '<h3>您已经参与过活动啦</h3><p>每个手机号只能参与一次，不可以重复参与哦！</p><div class="step-btn1"><a href="#" class="close_btn">确认</a></div>';
		}else{
			//手机号码验证
			if ($.trim($("#J_mobileNum").val()) == "" || !mre.test($("#J_mobileNum").val())){
				$(".prompt").html("请输入正确手机号");
				$(".prompt").css({height:'20px',marginTop:'5px'});
				return;
			}	
			if ($.trim($("#J_yzm").val()) == ""){
				$(".prompt").html("请输入正确验证码");
				$(".prompt").css({height:'20px',marginTop:'5px'});
				return;
			}
			$(".prompt").html("");
			$(".prompt").css({height:'0px',marginTop:'0px'});
			//参与活动
			var path = getRootPath();
			var phoneNum = $.trim($("#J_mobileNum").val());
			var verifyCode = $.trim($("#J_yzm").val());
			$.post(path + "/common/check/verify/code",{phoneNum:phoneNum, verifyCode:verifyCode},function(data){
    			if(data.code == "0"){
    				$.get(path + "/one/check/phone/activity?phoneNum=" + phoneNum,function(data){
    					if(data.code == "0"){
    						couponShowHtml = '<h3>您已成功参与活动</h3><p>11.2-11.5每天中午12点公布中奖名单，记得来看哦！</p><div class="step-btn1"><a href="#" class="close_btn">确认</a></div>';
        					isS = true;
        					popBox();
        					$.get(path + "/one/check/activity?phoneNum=" + phoneNum,function(data){});
    					}
    				});
    			}else{
    				alert(data.msg);
    				return;
    			}
    		},"json");
		}
			
	});	
	
	//弹层提示
	function popBox(){
		
		var $sw = $("#sucess_window");
	    function modalHidden2($ele) {
			  $("body").removeAttr("style");
			  touch = 1;		
			  $ele.removeClass('couponShow-in');
			  setTimeout(function(){
				  $ele.css({"display": "none"});
				$sw.removeClass('active');
			  },300)
		}
		
		setTimeout(function(){
			touch = 0;
			$("body").attr("style","overflow:hidden");
			$sw.addClass('active');
			$('.couponShow').html(couponShowHtml);
			$('.couponShow').css({"display": "block"});
			$('.couponShow').animate(100,function(){			  
				$('.couponShow').addClass('couponShow-in');
			});
			$(".close_btn").click(function(e){
					modalHidden2($(".couponShow"));
					return false;
			});
			$sw.click(function(e){					
			  if(e.target.classList.contains('sucess_window')){
					modalHidden2($(".couponShow"));
			  }
			});
		},300);
		
		return;		
	
	}
	
	//中奖名单
	$(".pack-up-btn").click(function(){
		$('.slide-btn').show();
		$('.lottery-box').slideUp();
		//$('html,body').css({height:'auto',overflow:'auto'});
		
	});
	$('.slide-btn').click(function(){
		$('.lottery-box').slideDown();
		//$('html,body').css({height:'100%',overflow:'hidden'});
		$(this).hide();

	});
});