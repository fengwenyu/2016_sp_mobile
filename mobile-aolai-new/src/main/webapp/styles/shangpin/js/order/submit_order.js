
/**
 * 加载页面时初始化操作
 */
$(function(){
	var shopids = "";
	$("input[name='shopid']").each(function(){
		shopids += $(this).val() + "|";
	});
	console.log(shopids);
	//购物车商品ID放入隐藏域
	$("#buysIds").val(shopids);
	//收获地址ID放入隐藏域
	$("#addrid").val($("#cId").val());
	//发票、优惠码、优惠劵信息置为空
	$("#invoiceflag").val("0");//默认不开发票：0
	$("#couponflag").val("");//默认优惠类型为空
	$("#coupon").val("");//默认优惠码为空
	$("#invFlag").val("0");//标记不开发票
	
	//判断单品是否显示运费，大于等于499不显示
	var fee = $("#carrFee").val();
	if(fee*1 > 0){
		var famount=$("#CartOrder").val();
		famount=famount*1+fee*1;
		$("#s_carriage").show();
		//应付金额
		$("#factPay").html("&yen;"+famount);
		//应付金额放入隐藏域
		$("#famount").val(famount);
		$("#carriage").html("&yen;"+fee);//运费
	}
});

/**
 * 提交订单
 */
function submitOrder(){
	//表单校验
	if($("#addrid").val()==''){
		alert("请填写收货人信息。");
		return;
	}
	//判断订单已经提交则跳转到个人中心/订单管理
	if($("#isSubmit").val()=="1"){
		alert("订单已提交,请勿重复提交");
		window.location.href=getRootPath()+"/user/order/list";
		return;
	}
	//改变按钮文案并禁用按钮
	var sub = $("#subOrder");
	sub.text("正在提交...");
	sub.attr("href","");
	//应付总金额
	var count = $("#factPay").html(); 
	$.ajax({
		url:getRootPath()+"/cart/submit/order",
		data:{//提交订单所需参数
			"addrid":$("#addrid").val(),          		 //收货地址id
			"invoiceaddrid":$("#invoiceaddrid").val(),   //发票地址id
			"invoiceflag":$("#invoiceflag").val(),       //是否开发票
			"invoicetype":$("#invoicetype").val(),       //发票类型
			"invoicetitle":$("#invoicetitle").val(),     //发票抬头
			"invoicecontent":$("#invoicecontent").val(), //发票内容
			"couponflag":$("#couponflag").val(),         //使用优惠券类型
			"coupon":$("#coupon").val(),                 //优惠券或折扣码编号
			"express":$("#express").val(),               //配送方式
			"buysids":$("#buysIds").val(),               //购物车商品id：多个用"|"分开
		},
		dataType:'json',
	    timeout: 30000,
	    error: function (xmlHttpRequest, error) {			            	
            alert("您的网络异常");
            sub.text("提交订单");
        	sub.attr("href","javascript:submitOrder()");
        	//标识订单提交失败
        	$("#isSubmit").val("0");
            return;
        },
        success:function(data){
        	console.log(data.order);
        	var order = data.order;
        	if(data.code == '0'){//提交订单成功
        		alert("提交订单成功。");
        		//标识订单提交成功
            	$("#isSubmit").val("1");
        		//跳转到提交订单成功页面
        		location.href = getRootPath()+"/cart/submit/success?orderId="+order.orderid+"&msgcode="+data.msgcode;
        	}else{
        		alert("提交订单失败！");
        		//标识订单提交失败
            	$("#isSubmit").val("0");
        	}
        	sub.text("提交订单");
        	sub.attr("href","javascript:submitOrder()");
        }
	});
	
}
