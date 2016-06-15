$(function(){
	
	function MobileErrorTip() {
		$(".prompt").css({height:'20px',marginTop:'10px'});
		$(".prompt").html("请输入正确手机号");
		$("#J_name").removeClass('error');
		$("#J_mobileNum").addClass('error');
	};
	
	function yzmErrorTip() {
	  $(".prompt").html("请输入正确姓名");
	  $(".prompt").css({height:'20px', marginTop:'10px'});
	  $("#J_mobileNum").removeClass('error');
	  $("#J_name").addClass('error');
	 
	};
	function removeErrorTip(){
	  $(".prompt").html("");
	  $(".prompt").css({height:'0',marginTop:'0'});
	  $("#J_mobileNum").removeClass('error');
	  $("#J_name").removeClass('error');
	}
	//点击领取按钮时验证输入内容是否正确
	//var isMobile=/^1[358]\d{9}$/; //手机号码验证规则
	$(".js-start-btn").click(function(){
		var path = getRootPath();
		if ($.trim($("#J_name").val()) == ""){
			yzmErrorTip();
			return;
		};
		if ($.trim($("#J_mobileNum").val()) == ""){
			MobileErrorTip();
			return;
		};
		var name=$("#J_name").val();
		var phone=$("#J_mobileNum").val();
		var sex = $('.login_list label.cur').text();
		if(sex=="男"){
			sex="1";
		}else{
			sex="0";
		}
		var size = $('.login_list a.cur').text();
		$.post(path+"/activity/saveInfo", {
			"name" : name,
			"Phone" : phone,
			"sex" :sex,
			"size":size
		}, function(data) {
			if (data.code == "0") {
				window.location.href = path + "/activity/result?code="+data.date;
				removeErrorTip();
				return;
			}else if (data.code == "2"){
				window.location= getRootPath() + "/login?back=/activity/edit";
				removeErrorTip();
				return;
			}else if (data.code == "4"){
				popBox(data.date);
				return;
			}else{
				alert(data.msg)
				return;
			}
		}, "json"); 
		return;		
	});
	
	//选择性别
	$(".login_list label").click(function(){
		$(this).find("input").prop('checked',true);
		$(this).addClass("cur").siblings("label").removeClass("cur");
	});
	
	//选择尺码
	$(".login_list a").click(function(){
		$(this).addClass("cur").siblings("a").removeClass("cur");
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
		if(len == 5){
			$(this).blur();
		}
	});
	
	
	//领过弹出层
	function popBox(code){
		$('#popup_message').html("您已经领过了");
		$('#popup_container').show();
		setTimeout(function(){
			 window.location=getRootPath()+ "/activity/result?code="+code;
			$('#popup_container').hide();
		},2000);
	}

	
	
});