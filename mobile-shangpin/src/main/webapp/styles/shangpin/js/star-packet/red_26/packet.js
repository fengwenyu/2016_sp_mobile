var star ="";
$(function(){
	var valueText1 = "输口令抢红包";
		valueText2 = "重新输入";
	$(".passwordInput").focus(function(){
		if($(this).val() == valueText1 || $(this).val() == valueText2){
			$(this).val("").addClass("black_f");
		}
	});
	$(".passwordInput").blur(function(){
		if($(this).val() == ""){
			$(this).val(valueText1).removeClass("black_f");	
		}
	});
	
	// 口令提示弹层
	$(".sure_btn").on("click",function() {
		var path = getRootPath();
		var password = $("#password").val();
		star= $("#star").val();
		if (password == valueText1 || password == valueText2
				|| password == null) {
			popBox("口令输入有误");
			return;
		}
		$.ajax({
			type: "GET",
			url: getRootPath()+"/isLogin?"+new Date(),
			success: function(data){
				/*
				data = 0  未登录
				data = 1  已登录
				*/
				//未登录
				if(data==undefined || data=="" || data == "0" ){
					if(star==1){
						window.location= getRootPath() + "/login?back=/star/red";
					}else{
						window.location= getRootPath() + "/login?back=/star/red/index";
					}
					return false;
				}
				//已登录
				if(data == "1"){
					$.post(path+"/red/coupons", {
						"verifyPassword" : password
					}, function(data) {
						if (data.code == "0") {
							window.location.href = path + "/red/star/black_result?star="+star;
							return;
						} else if (data.code == "2") {
							if(star==1){
								window.location= getRootPath() + "/login?back=/star/red";
							}else{
								window.location= getRootPath() + "/login?back=/star/red/index";
							}
							return;
						} else if (data.code == "4") {
							popBox(data.msg); 
							return;
						} else {
							popBox(data.msg);
							return;
						}
					}, "json");
				}
				
			}
		}); 
	 });
	
	//分享按钮
	$('.share_btn').click(function(){
		$('.fixed_layer').show();	
	});
	$('.fixed_layer').click(function(){
		$(this).hide();	
	});;	
	
	
	//口令弹出层方法
	function popBox(msg){
		$('#popup_message').html(msg);
		$('#popup_overlay').show();
		$('#popup_container').show();
		setTimeout(function(){
			$('#popup_overlay').hide();
			$('#popup_container').hide();
			$(".passwordInput").val(valueText2).removeClass("black_f");
			if(msg=="红包已领过，不可重复领取哦"){
			 window.location=getRootPath()+ "/red/star/black_result?star="+star;
			}
		},2000);
	}

});