$(function() {
	//读取错误msg
	var msg=$("#msg").val();
	if(msg!=""){
		alert(msg);
		$("#msg").val("");
	}
	
	$('.select-option').click(function() {
		$(this).siblings('span').removeClass("cur");
		$("#choice_type").val($(this).attr("type"));
		$(this).addClass("cur");
	});
	//绑定手机事件
	$('#step_tel_btn').click(function() {
		var phone = $("#phone").val();
		var mre = /^1[358]\d{9}$/;
		if ($("#phone").val() == "") {
			alert("请输入手机号码！");
			return;
		}

		if (phone != "" && !mre.test(phone)) {
			alert("请输入正确的手机号码！");
			return;
		}

		if ($("#verify_code").val() == "") {
			alert("请输入验证码！");
			return;
		}
		//提交表单
		$("#bingTel").submit();
	});
	//获取验证码事件
	$('#get_verify_code').click(function(e) {
		var phone = $("#phone").val();
		var mre = /^1[358]\d{9}$/;
		if(phone==""){
			return ;
		}
		if (phone != "" && !mre.test(phone)) {
			alert("请输入正确的手机号码！");
			return;
		}

		//防止重复点击按钮执行下面事件
		if($(this).attr("disabled")){
			return;
		}
		//发送验证码
		sendCode(phone);
		//60秒事件
		time(this,e);
	});
	
	//发送验证码
	function sendCode(phone){
		$.ajax({
			type:"get",
			url:"sendVerifyCode?phone=" + phone,
			dataType:"json",
			success:function(data){
				var code = data.code;
				if(code == "1"){
					flag = false;
					alert(data.msg);
				}
			}
		});
	}
	// 开始60秒事件
	var wait = 60;
	function time(o,e) {
		if (wait == 0) {
			$(o).attr("disabled",false);
			$(o).text ( "获取验证码");
			wait = 60;
		} else {
			$(o).attr("disabled", true);
			$(o).text(wait + "秒重新获取");
			wait--;
			setTimeout(function() {
				time(o)
			}, 1000)
		}
	}
	
	//设置礼品卡支付密码
	$('#passwdBtn').click(function(){
		var passwd=$('#passwd').val();
		var nextpasswd=$('#nextpasswd').val();
		if(passwd==""){
			alert("请输入支付密码！");
			return ;
		}
		var p = /(?!^\d+$)(?!^[a-zA-Z]+$)^[\x21-\x7E]{6,20}$/;
		if(!p.test(passwd)){
			alert("请输入正确的支付密码(6-20位数字+字母结合)！");
			return;
		}
		if(nextpasswd==""){
			alert("请再次确认支付密码！");
			return ;
		}
		if(nextpasswd!=passwd){
			alert("两次输入的支付密码不一致！");
			return;
		}
		$("#passwdForm").submit();
	});
	
	$('#choice_next').click(function(){
		$(".select-option").each(function(){
			if($(this).hasClass("cur")){
				$("#choice_type").val($(this).attr("type"));
			}
    	});	
		
		var type=$("#choice_type").val();
		if(type==1){
			//电子卡充值页面
			location.href=getRootPath()+"/giftCard/rechargeE";
		}else if(type==2){
			//实物卡充值页面
			location.href=getRootPath()+"/giftCard/rechargeEntity";
		}
		
	});
	$('#rechargeE').click(function(){
		var passwd=$("#passwd").val();
		if(passwd==""){
			alert("请输入卡密");
			return ;
		}
		$("#recharge_eFrom").submit();
	});
	$('.recharge').click(function(){
		var passwd=$("#cardNo").val();
		if(passwd==""){
			alert("页面不正确");
			return ;
		}
		$("#coupon").submit();
	});
	$('#recharge').click(function(){
		var passwd=$("#cardNo").val();
		if(passwd==""){
			alert("页面不正确");
			return ;
		}
		$("#coupon").submit();
	});
	$('#rechargeEntity').click(function(){
		var cardno=$("#cardno").val();
		var passwd=$("#passwd").val();
		if(cardno==""){
			alert("请输入卡号");
			return;
		}
		if(passwd==""){
			alert("请输入卡密");
			return ;
		}
		$("#recharge_entityFrom").submit();
	});
});