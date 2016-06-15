//表单验证
(function() {
    window.Account = function() {},
    $.extend(window.Account.prototype, {
        init: function() {},
        mobileForm: function() {
            $("#J_MobileForm").on("submit",
            function() {
				var re = /^1\d{10}$/;
				if ($.trim($("#J_Mobile").val()) == "" || !re.test($("#J_Mobile").val())){
					return $(".mobile_errorMsg").html("请输入正确手机号码！"),
					$("#J_Mobile").addClass("error"),
                	!1;
				}else{
					$(".mobile_errorMsg").html("");
					$("#J_Mobile").removeClass("error");
				}
            });
        },
		codeForm: function() {
            $("#J_CodeForm").on("submit",
            function() {
				var codeLen = $("#J_Code").val().length;
				if ($.trim($("#J_Code").val()) == "" || codeLen < 6){
					return $(".mobile_errorMsg").html("请输入正确验证码！"),
					$("#J_Code").addClass("error"),
                	!1;
				}else{
					$(".mobile_errorMsg").html("");
					$("#J_Code").removeClass("error");
				}
            });
        },
		addrForm: function() {
            $("#J_AddrForm").on("submit",
            function() {
            	return Account.validate();
            });
        },
        validate: function(){
        	var nameLen = $("#J_AddrName").val().length;
			if ($.trim($("#J_AddrName").val()) == "" || nameLen > 20){
				return $(".addr_errorMsg").html("请输入收货人姓名！").show(),
				$("#J_AddrName").addClass("error"),
            	!1;
			}else{
				$(".mobile_errorMsg").hide().html("");
				$("#J_AddrName").removeClass("error");
			}
			
			var pVal = $("#J_AddrProvince").val();
			if (pVal <= 0){
				return $(".addr_errorMsg").html("请选择省份！").show(),
				$("#J_AddrProvince").addClass("error"),
            	!1;
			}else{
				$(".addr_errorMsg").hide().html("");
				$("#J_AddrProvince").removeClass("error");
			}
			
			var cVal = $("#J_AddrCity").val();
			if (cVal <= 0){
				return $(".addr_errorMsg").html("请选择城市！").show(),
				$("#J_AddrCity").addClass("error"),
            	!1;
			}else{
				$(".addr_errorMsg").hide().html("");
				$("#J_AddrCity").removeClass("error");
			}
			
			var aVal = $("#J_AddrArea").val();
			if (aVal <= 0){
				return $(".addr_errorMsg").html("请选择地区！").show(),
				$("#J_AddrArea").addClass("error"),
            	!1;
			}else{
				$(".addr_errorMsg").hide().html("");
				$("#J_AddrArea").removeClass("error");
			}
			
			var mobileVal = /^1\d{10}$/;
			if ($.trim($("#J_AddrMobile").val()) == "" || !mobileVal.test($("#J_AddrMobile").val())){
				return $(".addr_errorMsg").html("请输入正确手机号码！").show(),
				$("#J_AddrMobile").addClass("error"),
            	!1;
			}else{
				$(".addr_errorMsg").hide().html("");
				$("#J_AddrMobile").removeClass("error");
			}
			
			var codeVal = /^\d{6}$/;
			if ($.trim($("#J_AddrCode").val()) == "" || !codeVal.test($("#J_AddrCode").val())){
				return $(".addr_errorMsg").html("请输入正确邮政编码！").show(),
				$("#J_AddrCode").addClass("error"),
            	!1;
			}else{
				$(".addr_errorMsg").hide().html("");
				$("#J_AddrCode").removeClass("error");
			}
			
			var addrLen = $("#J_AddrText").val().length;
			if ($.trim($("#J_AddrText").val()) == "" || addrLen < 5 || addrLen > 100){
				return $(".addr_errorMsg").html("请输入正确详细地址！").show(),
				$("#J_AddrText").addClass("error"),
            	!1;
			}else{
				$(".mobile_errorMsg").hide().html("");
				$("#J_AddrText").removeClass("error");
			}
        },couponCodeValidate: function(){
			if ($.trim($("#couponcode").val()) == "" ){
				return $(".addr_errorMsg").html("请输入优惠码！").show(),
				$("#couponcode").addClass("error"),
            	!1;
			}else{
				$(".mobile_errorMsg").hide().html("");
				$("#couponcode").removeClass("error");
			}
			
		
		
        }, 
        ajaxValidate: function(){
        	var nameLen = $("#INV_AddrName").val().length;
			if ($.trim($("#INV_AddrName").val()) == "" || nameLen > 20){
				return $(".inv_addr_errorMsg").html("请输入收货人姓名！").show(),
				$("#INV_AddrName").addClass("error"),
            	!1;
			}else{
				$(".inv_addr_errorMsg").hide().html("");
				$("#INV_AddrName").removeClass("error");
			}
			
			var pVal = $("#INV_AddrProvince").val();
			if (pVal == -1){
				return $(".inv_addr_errorMsg").html("请选择省份！").show(),
				$("#INV_AddrProvince").addClass("error"),
            	!1;
			}else{
				$(".inv_addr_errorMsg").hide().html("");
				$("#INV_AddrProvince").removeClass("error");
			}
			
			var cVal = $("#INV_AddrCity").val();
			if (cVal == -1){
				return $(".inv_addr_errorMsg").html("请选择城市！").show(),
				$("#INV_AddrCity").addClass("error"),
            	!1;
			}else{
				$(".inv_addr_errorMsg").hide().html("");
				$("#INV_AddrCity").removeClass("error");
			}
			
			var aVal = $("#INV_AddrArea").val();
			if (aVal == -1){
				return $(".inv_addr_errorMsg").html("请选择地区！").show(),
				$("#INV_AddrArea").addClass("error"),
            	!1;
			}else{
				$(".inv_addr_errorMsg").hide().html("");
				$("#INV_AddrArea").removeClass("error");
			}
			
			var mobileVal = /^1\d{10}$/;
			if ($.trim($("#INV_AddrMobile").val()) == "" || !mobileVal.test($("#INV_AddrMobile").val())){
				return $(".inv_addr_errorMsg").html("请输入正确手机号码！").show(),
				$("#INV_AddrMobile").addClass("error"),
            	!1;
			}else{
				$(".inv_addr_errorMsg").hide().html("");
				$("#INV_AddrMobile").removeClass("error");
			}
			
			var codeVal = /^\d{6}$/;
			if ($.trim($("#INV_AddrCode").val()) == "" || !codeVal.test($("#INV_AddrCode").val())){
				return $(".inv_addr_errorMsg").html("请输入正确邮政编码！").show(),
				$("#INV_AddrCode").addClass("error"),
            	!1;
			}else{
				$(".inv_addr_errorMsg").hide().html("");
				$("#INV_AddrCode").removeClass("error");
			}
			
			var addrLen = $("#INV_AddrText").val().length;
			if ($.trim($("#INV_AddrText").val()) == "" || addrLen < 5 || addrLen > 100){
				return $(".inv_addr_errorMsg").html("请输入正确详细地址！").show(),
				$("#INV_AddrText").addClass("error"),
            	!1;
			}else{
				$(".mobile_errorMsg").hide().html("");
				$("#INV_AddrText").removeClass("error");
			}
        }
    });
})(Zepto);

$(function(){
	
	//收货地址删除
	/*$(".address_infohd").delegate(".address_delbtn","click",function(){

		jConfirm('确认要删除该地址吗？','',function(result){
			if(result==true){
				
			}else{
				
			}
		});
		
		return false;
	});*/

	//设置默认收货地址
	/*$(".address_list").delegate("li","click",function(){
		$(this).addClass("curr").siblings("li").removeClass("curr");
		$(".default_address").empty();
		$(this).find(".default_address").html("默认地址");
		return false;
	});*/
	//设置默认收货地址
	
	//取消订单
	/*
	$(".alOrder_info").delegate(".alOrder_cancelBtn","click",function(){
		
		jConfirm('确认取消该订单吗？','',function(result){
			if(result==true){
			
			}
		});
		return false;
	});
	*/
	//取消订单
	
});

//手机绑定号码验证
if($("#J_MobileForm").length > 0){
	var Account = new Account();
	Account.mobileForm();
}

//手机绑定验证码验证
if($("#J_CodeForm").length > 0){
	var Account = new Account();
	Account.codeForm();
}

//收货地址表单验证
if($("#J_AddrForm").length > 0){
	var Account = new Account();
	Account.addrForm();
}

