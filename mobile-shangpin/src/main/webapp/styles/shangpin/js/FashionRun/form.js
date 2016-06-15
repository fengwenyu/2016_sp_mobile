var path = getRootPath();
$(function(){
	if($("#J_mobileNum").length>0){
		$("#J_mobileNum").bigGlass(2);
	}
	if($("#J_identificationNum").length>0){
		$("#J_identificationNum").bigGlass(1);
	}
	$(".agreement em").click(function(){
		if($(this).hasClass('cur')){
			$(this).removeClass('cur');
			$(".js-sign-up").addClass('grey-btn');
		}else{
			$(this).addClass('cur');
			$(".js-sign-up").removeClass('grey-btn');
		}
	})

	//点击开始报名按钮时验证输入内容是否正确
	$(".js-sign-up").click(function(){
		var re = /^[\u4e00-\u9fa5]$/,
			mre = /^1\d{10}$/,
			identification = /^(\d{15}$|^\d{18}$|^\d{17}(\d|X|x))$/;

		//姓名验证
		//if ($.trim($("#J_userName").val()) == "" || !re.test($("#J_userName").val())){
		var name=$.trim($("#J_userName").val()) ;
		if (name== ""){
			return jShare('请填写姓名',"",""),
        	!1;
		}
		if (name.length >15){
			return jShare('姓名过长，请检查',"",""),
        	!1;
		}
		 var reg=/^[A-Za-z0-9]$/;
		/* if((!re.test(name)))
		 {
			return jShare('姓名格式不正确',"",""),
	        !1;
		 }*/
		//手机号码验证
        if ($.trim($("#J_mobileNum").val()) == "" || !mre.test($("#J_mobileNum").val())){
			return jShare('请输入正确手机号码',"",""),
        	!1;
		}
		//身份证号码验证
		if ($.trim($("#J_identificationNum").val()) =="" || !identification.test($("#J_identificationNum").val())){
			return jShare('请输入正确的身份证号码',"",""),
        	!1;
		}
		
		//身份证号码验证
		if ($(".js-sign-up").hasClass('grey-btn')){
        	return false;
		}
		
		
			var fashionRun = $('#fashion_form').serialize();
			console.log(fashionRun);
			$.ajax({
				url : path + "/fashionrun/add",
				data : fashionRun,
				dataType : "json",
				success : function(data) {
					if(data.code=="0"){
						location.href=path+"/fashionrun/topay?orderId="+data.orderId;
					}
					if(data.code=="1"){
						return jShare('该手机号用户已经报名成功',"",""),
			        	!1;
					}
					if(data.code=="-1"){
						//报名结束
						location.href=path+"/fashionrun/finish";
					}
					if(data.code=="2"){
						return jShare('该身份证用户已经报名成功',"",""),
			        	!1;
					}
					if(data.code=="4"){
						return jShare(data.msg,"",""),
			        	!1;
					}
					
					console.log(data);
				}
			});

	});
	
	//点击开始报名按钮时验证输入内容是否正确
	$(".js-modify-up").click(function(){
		var re = /^[\u4e00-\u9fa5]$/,
			mre = /^1\d{10}$/,
			identification = /^(\d{15}$|^\d{18}$|^\d{17}(\d|X|x))$/;

		//姓名验证
		//if ($.trim($("#J_userName").val()) == "" || !re.test($("#J_userName").val())){
		var name=$.trim($("#J_userName").val()) ;
		if (name== ""){
			return jShare('请填写姓名',"",""),
        	!1;
		}
		if (name.length >15){
			return jShare('姓名过长，请检查',"",""),
        	!1;
		}
	/*	 var reg=/^[A-Za-z0-9]$/;
		 if((!re.test(name))&&(!reg.test(name)))
		 {
			return jShare('姓名格式不正确',"",""),
	        !1;
		 }*/
		//手机号码验证
        if ($.trim($("#J_mobileNum").val()) == "" || !mre.test($("#J_mobileNum").val())){
			return jShare('请输入正确手机号码',"",""),
        	!1;
		}
		//身份证号码验证
		if ($.trim($("#J_identificationNum").val()) =="" || !identification.test($("#J_identificationNum").val())){
			return jShare('请输入正确的身份证号码',"",""),
        	!1;
		}
		
		//身份证号码验证
		if ($(".js-modify-up").hasClass('grey-btn')){
        	return false;
		}
		
		
			var fashionRun = $('#fashion_form').serialize();
			console.log(fashionRun);
			$.ajax({
				url : path + "/fashionrun/update",
				data : fashionRun,
				dataType : "json",
				type:"post",
				success : function(data) {
					if(data.code=="0"){
						location.href=path+"/fashionrun/topay?orderId="+data.orderId;
					}
					if(data.code=="1"){
						return jShare('该手机号用户已经报名成功',"",""),
			        	!1;
					}
					if(data.code=="-1"){
						//报名结束
						location.href=path+"/fashionrun/finish";
					}
					if(data.code=="2"){
						return jShare('该身份证用户已经报名成功',"",""),
			        	!1;
					}
					if(data.code=="3"){
						return jShare('修改成功',"",""),
			        	!1;
					}
					if(data.code=="4"){
						return jShare(data.msg,"",""),
			        	!1;
					}
					console.log(data);
				}
			});

	});
	
	//尺码选择
	$('.js-bar-size li').click(function(){
		if($(this).hasClass('cur')){
			$(this).removeClass('cur');
		}else{
			$("#size").val($(this).attr('id'))
			$(this).addClass('cur');
			$(this).siblings().removeClass('cur');
		}
	});
	
	/*//选择省市区
	var addrTxt;
	$("#J_area").click(function(){
		$("#area_overlay").height($(document).height());
		$("#area_overlay, #area_layer").show();
		addrTxt = "";
		return false;
	});
	
	$("#area_layer dd a").click(function(){
		var that = $(this),
			obj = $("#area_layer dd  a"),
			content = $("#area_layer dl"),
			thisCon = that.closest("dl"),
			title = $("#area_layer h3");
		
		obj.removeClass("cur");	
		that.addClass("cur");
			
		//选择结果
		addrTxt += " "+that.text();
		
		setTimeout(function(){
			if(thisCon.next("dl").length > 0){
				content.hide();
				thisCon.next("dl").show();
				title.html(thisCon.next("dl").attr("title"));
				
			}else{
				//返回初始状态
				content.hide();
				$("#area_overlay, #area_layer").hide();
				$("#area_layer dl:first").show();
				title.html($("#area_layer dl:first").attr("title"));
				$("#J_area").val(addrTxt);
			}
			
		}, 300);
		return false;
		
	});*/
	//选择收货地址
	var addrTxt;
	$("#J_area").click(function(){
		$("#address_area_overlay").height($(document).height());
		$("#address_area_overlay, #address_area_layer").show();
		addrTxt = "";
		return false;
	});
	
	$("#address_area_layer").delegate("a","click",function(){
		var that = $(this),
		obj = $("#address_area_layer dd  a"),
		content = $("#address_area_layer dl"),
		thisCon = that.closest("dl"),
		title = $("#address_area_layer h3");
		obj.removeClass("cur");	
		that.addClass("cur");
		var dl_id = thisCon.attr("id");
		if(dl_id == "area_province"){
			$("#province").val(that.text());
			$("#provincename").val(that.text());
		}else if(dl_id == "area_city"){
			$("#city").val(that.text());
		}else if(dl_id == "area_county"){
			$("#area").val(that.text());
		}else if(dl_id == "area_street"){
			$("#town").val(that.text());
		}
		//选择结果
		addrTxt += " "+that.text();
		setTimeout(function(){
			if(thisCon.next("dl").length > 0){
				content.hide();
				thisCon.next("dl").show();
				title.html(thisCon.next("dl").attr("title"));
				if(thisCon.next("dl").attr("id") == "area_city"){
					$("#area_city").empty();
					$.post(path+"/fashionrun/city",{proviceId : that.attr("id")},function(data){
						$.each(data,function(index,item){
							$("#area_city").append("<dd><a href='#' id=" + item.id + ">" + item.name + "</a></dd>");
						});
					},"json");
				}else if(thisCon.next("dl").attr("id") == "area_county"){
					$("#area_county").empty();
					$.post(path+"/fashionrun/area",{cityId : that.attr("id")},function(data){
						$.each(data,function(index,item){
							$("#area_county").append("<dd><a href='#' id=" + item.id + ">" + item.name + "</a></dd>");
						});
					},"json");
				}else if(thisCon.next("dl").attr("id") == "area_street"){
					$("#area_street").empty();
					$.post(path+"/fashionrun/town",{areaId : that.attr("id")},function(data){
						$.each(data,function(index,item){
							$("#area_street").append("<dd><a href='#' id=" + item.id + ">" + item.name + "</a></dd>");
						});
					},"json");
				}
				}else{
					//返回初始状态
					content.hide();
					$("#address_area_overlay, #address_area_layer").hide();
					$("#address_area_layer dl:first").show();
					title.html($("#address_area_layer dl:first").attr("title"));
					$("#J_area").html(addrTxt);
					
					//obj.removeClass("cur");	
					//$("#area_layer dl dd:first a").addClass("cur");
			}
			
		}, 10);
		return false;
	});


	//省市区的弹层关闭
	$(".close_btn, #area_overlay").click(function(){
		$("#area_layer, #area_overlay").hide();
		$("#area_layer h3").show().html("省份");
		$("#area_layer").find("dl").eq(0).show().siblings("dl").hide();
		$("#area_layer dd a").removeClass("cur");

	});
	
	
	//点击领取按钮时验证输入内容是否正确
	$(".js-get-btn").click(function(){
		console.log("sign");
		var mre = /^1\d{10}$/;
		//手机号码验证
        if ($.trim($("#J_mobileNum").val()) == "" || !mre.test($("#J_mobileNum").val())){
			return jShare('请输入正确手机号码',"",""),
        	!1;
		}
		//尺码验证
		if (!$(".js-bar-size li").hasClass('cur')){
			return jShare('请选择尺码',"",""),
        	!1;
		}
		console.log($("#J_area").text());
		//选择省市区验证
		if ($.trim($("#J_area").text()) == ""||$.trim($("#J_area").text()) == "省市区"){
			return jShare('选择省市区',"",""),
        	!1;
		}
		
		//详细地址验证
		if ($.trim($("#J_addr").val()) == ""){
			return jShare('请输入详细街道地址',"",""),
        	!1;
		}
		var fashionRun = $('#apply_form').serialize();
		$.ajax({
			url : path + "/fashionrun/apply",
			data : fashionRun,
			dataType : "json",
			type:"post",
			success : function(data) {
				
				if(data.code=="0"){
					location.href=path+"/fashionrun/gift/success?orderId="+data.orderId;
				}
				if(data.code=="-1"){
					return jShare('您尚未报名，请先报名！',"",""),
		        	!1;
				}
				if(data.code=="1"){
					return jShare('您已经领取过了!',"",""),
		        	!1;
				}
				if(data.code=="2"){
					return jShare('该尺码库存不足!',"",""),
		        	!1;
				}
				console.log(data);
			}
		});
		
	});

});

function pay(type,orderId) {
	if (type == "1") {
		$(".payment_btn").text("正在跳转支付宝支付页面.......");
		// $.get(path + "/order/alipay?orderId=" + orderId + "&totalFee=" +
		// data.returnInfo.onlineamount + "&date=" + date,function(data1){
		// window.location.href = data1;
		// });
		window.location.href = path + "/fashionrun/alipay?orderId=" + orderId;
		/*} else if (type == "2") {
		$(".payment_btn").text("正在跳转银联支付页面.......");
		// $.get(path + "/order/yinlianpay?orderId=" + orderId + "&totalFee=" +
		// data.returnInfo.onlineamount + "&date=" + date,function(data1){
		// window.location.href = data1;
		// });
		window.location.href = path + "/fashion/run/yinlianpay?orderId="
				+ orderId;*/

	} else if (type == "2") {
		$(".payment_btn").text("正在跳转微信支付页面.......");

		window.location.href = path + "/fashionrun/wxpay?orderId=" + orderId;

	}
	
}