$(function(){
	//选择收货时间
	$("#alOrder_date").delegate("li","click",function(){
		$(this).addClass("cur").siblings("li").removeClass("cur");
		$("#dateVal").val($(this).attr("data-title"));
		return false;
	});
	//选择支付方式
	$("#alOrder_pay").delegate("li","click",function(){
		$(this).addClass("cur").siblings("li").removeClass("cur");
		$("#payVal").val($(this).attr("data-title"));
		if($(this).attr("data-title")==2){
			$("#alnote").html("提交订单后请务必保持手机畅通，我们客服会与您再次确认订单");
		}else{
			$("#alnote").html("&nbsp;&nbsp;&nbsp;&nbsp;");
		}
		return false;
	});
	
	/* ==== 收货地址 ==== */
	
	//是否要发票
	$("#invoice_option").delegate("a","click",function(){
		$(this).addClass("cur").siblings("a").removeClass("cur");
		$("#optionVal").val($(this).attr("data-title"));
		
		if($("#optionVal").val() == 1){
			$(".invoice_content").show();
		}else{
			$(".invoice_content").hide();
		}
		return false;
	});
	
	//发票抬头
	$("#invoice_title").delegate("a","click",function(){
		$(this).addClass("cur").siblings("a").removeClass("cur");
		$("#titleVal").val($(this).attr("data-title"));

		if($("#titleVal").val() == 0){
			$("#invoice_person").hide();
			$("#invoice_org").css("display","block");
		}else{
			$("#invoice_person").css("display","block");
			$("#invoice_org").hide();
		}
		return false;
	});
	
	//发票内容
	$("#invoice_class").delegate("a","click",function(){
		$(this).addClass("cur").siblings("a").removeClass("cur");
		$("#classVal").val($(this).attr("data-title"));
		return false;
	});
	
	//发票地址
	$("#invoice_addr").delegate("a","click",function(){
		$(this).addClass("cur").siblings("a").removeClass("cur");
		$("#addrVal").val($(this).attr("data-title"));
		
		if($("#addrVal").val() == 1){
			$(".invoice_addrForm").show();
		}else{
			$(".invoice_addrForm").hide();
		}
		return false;
	});
	
	/* ==== 收货地址 ==== */
});