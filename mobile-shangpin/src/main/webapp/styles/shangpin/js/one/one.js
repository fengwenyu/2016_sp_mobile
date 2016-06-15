$(function(){
	//验证码倒计时
	var isclick = true;
	$("#passwordGetCode").on("click",function(){
		if(!isclick) return false;
		//手机号码验证
    	if ($.trim($("#J_mobileNum").val()) == "" || !mre.test($("#J_mobileNum").val())){
			$(".prompt").html("请输入正确手机号");
    		return;
		}
		$(".prompt").html("");	
		var that = $(this),timeId;
		var num = 90;
		var thiscon = $(this).attr("data-waiting");
		that.text(num+thiscon)
		var path = getRootPath();
		var phoneNum = $.trim($("#J_mobileNum").val());
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
	var path = getRootPath();
	var mre = /^1\d{10}$/;
	//点击领取按钮时验证输入内容是否正确
	$(".coupon-btn").click(function(e){			
	    e.preventDefault();
		//手机号码验证
    	if ($.trim($("#J_mobileNum").val()) == "" || !mre.test($("#J_mobileNum").val())){
			$(".prompt").html("请输入正确手机号");
    		return;
		}	
		if ($.trim($("#J_yzm").val()) == ""){
			$(".prompt").html("请输入正确验证码");
    		return;
		}
		var phoneNum =$.trim($("#J_mobileNum").val());
		var verifyCode = $.trim($("#J_yzm").val());
		$.post(path + "/common/check/verify/code",{phoneNum:phoneNum, verifyCode:verifyCode},function(data){
			if(data.code == "0"){
				$.get(path + "/one/check?phoneNum=" + phoneNum,function(data){
					if(data.code == "0"){
						alert("领取成功！");
						window.location.href = "http://m.shangpin.com/subject/product/list?topicId=51016533";
					}else{
						alert(data.msg);
						return;
					}
				});
			}else{
				alert(data.msg);
				return;
			}
		},"json");
	});	
	
});
