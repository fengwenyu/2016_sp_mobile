(function() {
	window.Login = function() {},
		$.extend(window.Login.prototype, {
			init: function() {},
			loginForm: function() {
				$("#J_Login_invoice").on("click",
					function(e) {
						var re = /^[\u4e00-\u9fa5]+$/,
							mre = /^1\d{10}$/,
							mail = /^([a-zA-Z0-9_\.\-])+\@(([a-zA-Z0-9\-])+\.)+([a-zA-Z0-9]{2,4})+$/,
							post = /^[1-9][0-9]{5}$/;

						//发票抬头公司名称
						if($(".org_name").css('display')!="none"){

							if ($.trim($("#J_invoiceName").val()) == ""){
								return jShare('请输入单位名称!',"",""),
									//$("#J_invoiceName").addClass("error"),
									null;
									!1;
							}else{
								$("#J_invoiceName").removeClass("error");
							}
						}

						//发票内容
						if ($.trim($("#J_invoiceContent").val()) == ""){
							return jShare('请选择发票内容!',"",""),
								//$("#J_invoiceContent").addClass("error"),
								null,
								!1;
						}else{
							$("#J_invoiceContent").removeClass("error");

						}

						//收票人手机
						if ($.trim($("#J_invoicePhone").val()) == "" || !mre.test($("#J_invoicePhone").val())){
							return jShare('请输入11位手机号码!',"",""),
								//$("#J_invoicePhone").addClass("error"),
								null,
								!1;
						}else{
							$("#J_invoicePhone").removeClass("error");
						}

						//收票人邮箱
						if ($("#J_invoiceMail").val()!=""){
							if (!mail.test($("#J_invoiceMail").val())){
								return jShare('邮箱格式不正确，请重新输入!',"",""),
									//$("#J_invoiceMail").addClass("error"),
									null,
									!1;
							}else{
								$("#J_invoiceMail").removeClass("error");
							}
						}

						//是否开发票标识
						$("input[name='invoiceflag']").val("1");
						//发票类型
						if($(".invoice_info p em a.active_a").hasClass("personal")){
							$("input[name='invoicetype']").val("1");//个人/单位
							//发票名称
							$("input[name='invoicetitle']").val("个人");
							$("legend.invo").next("p").next("p").children("a").children("em").eq(1).text("个人");
						}else{
							$("input[name='invoicetype']").val("2");//个人/单位
							//发票名称
							$("input[name='invoicetitle']").val($("#J_invoiceName").val());
							$("legend.invo").next("p").next("p").children("a").children("em").eq(1).text($("#J_invoiceName").val());
						}

						//发票内容
						var invoiceCon = $("#J_invoiceContent").children('option:selected').text()
						$("input[name='invoicecontent']").val(invoiceCon);
						$("legend.invo").next("p").next("p").children("a").children("em").eq(2).text(invoiceCon);

						//手机 邮箱
						$("input[name='invoicetel']").val($("#J_invoicePhone").val());//发票手机号
						$("input[name='invoiceemail']").val($("#J_invoiceMail").val());//发票邮箱

						//跳转支付主页面
						scroll(0,0);
						$("#order_content").show().siblings().hide();
						$("#order_head").show();
					});
			}
		});
})(jQuery);

//登录表单验证
if($("#J_Login").length > 0){
	var Login = new Login();
	Login.loginForm();
}

/**back按钮*/
$(function () {
	$("#invoice_head .backBtn").click(function () {
		//跳转支付主页面
		scroll(0,0);
		$("#order_content").show().siblings().hide();
		$("#order_head").show();
	})
})

$(function(){

	//选择地址
	$(".select-address").click(function(){
		$(".select-address").toggleClass("cur");
	});
	$(".personal").click(function(){
		$(this).addClass("active_a").siblings().removeClass("active_a");
		$(".org_name").hide();
	});
	$(".company").click(function(){
		$(this).addClass("active_a").siblings().removeClass("active_a");
		$(".org_name").show();
		$("#J_invoiceName").val("单位");
	});

});