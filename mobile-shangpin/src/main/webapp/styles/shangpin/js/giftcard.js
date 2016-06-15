var wait = 60;
$(function(){
	$("#J_MobiCode").val("");
	$("#J_MobiPwd").val("");
	changeShow();
});
//倒计时显示
function changeShow(){
	if(wait == 0){
		$('span a').css('display','block');
		$('.reSend_btn').css('display','none');
		wait = 60;
	}else{
		$('span a').css('display','none');
		$('.reSend_btn').css('display','block');
		$('.reSend_btn').text(wait + '秒后重新发送');
		wait--;
		setTimeout(function(){
			changeShow();
		}, 1000);
	}
}
//发送手机验证码
function sendphonecode(){
	var path = getRootPath();
	var phoneNum = $('#J_phonenum').val();
	$.post(path + "/order/send/code",{mobi:phoneNum},function(data){
		if(data.code==1){
			alert(data.msg);
			return;
		}else{
			changeShow();
		}
	},"json");
}

/**
 * 验证发送到手机上的验证码
 */
function checkPhoneCode(){
	var path = getRootPath();
	var flag = true;
	var mobi = $('#J_phonenum').val();
	var verifycode = $('#J_MobiCode').val();
	$.ajax({
		type:"get",
		url:path + "/order/code/verify?mobi=" + mobi + "&verifycode=" + verifycode,
		async:false,
		dataType:"json",
		success:function(data){
			var code = data.code;
			if(code == "1"){
				flag = false;
				//alert(data.msg);
			}
		}
	});
	return flag;
}
//修改礼品卡密码
function setPassword(){
	var path = getRootPath();
	if ($.trim($("#J_MobiCode").val()) == "" || $("#J_MobiCode").val().length != 6){
		return $(".mobiMsg").html("请输入正确验证码！"),
		$("#J_MobiCode").addClass("error"),
		!1;
		return;
	}else{
		$(".mobiMsg").html("");
		$("#J_MobiCode").removeClass("error");
	}
	
	if ($.trim($("#J_MobiPwd").val()) == "" || $("#J_MobiPwd").val().length < 6){
		return $(".mobiMsg").html("请输入6-20位密码！"),
		$("#J_MobiPwd").addClass("error"),
		!1;
		return;
	}else{
		$(".mobiMsg").html("");
		$("#J_MobiPwd").removeClass("error");
	}
	
	if ($.trim($("#J_resetPwd").val()) == ""){
		return $(".mobiMsg").html("请输入确认密码！"),
		$("#J_resetPwd").addClass("error"),
		!1;
		return;
	}else if($.trim($("#J_MobiPwd").val()) != $.trim($("#J_resetPwd").val())){
		return $(".mobiMsg").html("两次密码输入不一致！"),
		$("#J_resetPwd").addClass("error"),
		!1;
		return;
	}else{
		$(".mobiMsg").html("");
		$("#J_resetPwd").removeClass("error");
	}
	var phoneNum = $('#J_phonenum').val();
	var password = $("#J_MobiPwd").val();
	var flag = checkPhoneCode();
	if(flag){
		$.post(path + "/order/set/password",{
			password : password
		},function(data){
			if(data.code == 0){
				alert("密码修改成功！");
				window.history.go(-1);
			}else{
				alert("密码修改失败，请从新设置!");
				return;
			}
			
		},"json");
	}else{
		alert("验证码输入错误！");
	}
	
	
}