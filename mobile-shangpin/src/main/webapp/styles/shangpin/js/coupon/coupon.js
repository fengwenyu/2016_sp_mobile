//发送优惠券激活码
function activeCoupon(){
	if ($.trim($("#coupons_code").val()) == "" ){
		return jShare('请输入优惠券激活码!',"",""),
		$("#coupons_code").addClass("error"),
		!1;
	}else{
		$("#coupons_code").removeClass("error");
	}
	var path = getRootPath();
	var coupon_code = $("#coupons_code").val();
	$.post(path + "/coupon/activation",{"code": "coupon:" + coupon_code},function(data){
		if(data.code == "0"){
			jShare("亲，优惠券充值成功","","");
			$("#coupons_code").val("");
			setTimeout(function(){
				window.location.href=path+"/coupon/list";
				}, 1000 );
		}else{
			jShare(data.msg,"","");
		}
	},"json");
}