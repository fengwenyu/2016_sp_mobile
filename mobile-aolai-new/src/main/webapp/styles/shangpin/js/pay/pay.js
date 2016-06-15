
/**
 * 改变支付方式，跳转到第三方支付
 * 
 * @param mainPay 主支付方式
 * @param subPay 子支付方式
 */
function chagePayMode(mainPay,subPay){
	//订单ID
	var orderId = $("#orderNum").val();
	//订单金额
	var totalFee = $("#payMoney").val();
	$.ajax({
		url:getRootPath()+"/pay/change",
		data:{
			"orderId":orderId,
			"mainPay":mainPay,
			"subPay":subPay
		},
		dataType:'json',
	    timeout: 30000,
	    error: function (xmlHttpRequest, error) {			            	
            alert("您的网络异常");
            return;
        },
        success:function(data){
        	//支付宝
        	if("success" == data.msgcode && mainPay==20 && subPay==37){
        		//跳转支付宝链接
        		location.href = getRootPath()+"/pay/alipay/ALIWAP?orderId="+orderId;
        	}
        	//银联
        	if("success" == data.msgcode && mainPay==19 && subPay==49){
        		//跳转银联链接
        		location.href = getRootPath()+"/pay/unpay/UNWAP?orderId="+orderId;
        		//判断是否安装银联安全控件
        		setTimeout(function(){
        			location.href = getRootPath()+"/pay/install?orderId="+orderId;;
				},2000);
        	}
        	//货到付款
        	if("success" == data.msgcode && mainPay==2 && subPay==41){
        		//跳转支付成功页面
        		location.href = getRootPath()+"/pay/cod?orderId="+orderId;
        	}
        	if("fail" == data.msgcode){
        		alert("改变支付方式失败："+data.msg);
        	}
        }
	});
}
