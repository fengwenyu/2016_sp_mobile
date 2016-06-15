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
			url : ctx + "/goddes2/receive?timestmp=" + new Date().getTime(),
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
							url : ctx + "/goddes2/direct/receive?timestmp=" + new Date().getTime(),
							type : "get",
							async:false,
							dataType:'json',
							success:function(data){
								alert(data.msg);
								return;
							}
						});
					}else{
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
	
	//点击获取验证码
	var flag = 0;
	$(".yzm-box em").click(function(){
		var mre = /^1\d{10}$/;
		if ($.trim($("#J_mobileNum").val()) == "" || !mre.test($("#J_mobileNum").val())){
			$(".prompt").html("请输入正确手机号");
    		return;
		}
		if(flag!=0){
    		return false;
    	}
    	flag = 1;
        var delay = 59, trigger = this, revert = $(trigger).text();
		var ctx = $("#ctx").val();
		var phoneNum = $("#J_mobileNum").val();
		$.post(ctx + "/goddes2/sendPhoneCode",{"phoneNum" : phoneNum},function(data){
			if(data.code == "1"){
				$(".prompt").html("手机验证码下发失败");
			}
		},"json");
		
		$('.btn_gradient_gray').css('background','#cecccc');
        $(trigger).attr({'disabled': true }).text(delay + $(trigger).attr("data-waiting")).parents("label").addClass("waiting");
        var counter = setInterval(function() {
            $(trigger).text($(trigger).text().replace(delay, --delay));
            if(delay == 0) {
            	flag = 0;
                window.clearInterval(counter);
                $(trigger).text(revert).removeAttr("disabled").parents("label").removeClass("waiting");
                $('.btn_gradient_gray').css('background','#c62026')
            }
        }, 1000);
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
		$.post(ctx + "/goddes2/check",{"phoneNum" : phoneNum, "verifycode": verifycode},function(data){
			//alert("coupon code result:" + data);
			if(data.code == "1"){
				alert(data.msg);
				return;
			}else{
				alert(data.msg);
				modalHidden($('.modal'));
				return;	
			}
		},"json");	
	});
});	