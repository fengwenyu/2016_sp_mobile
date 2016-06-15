/**
 * @author liling
 */
$(function() {
	//选择支付方式
	$(".paymet_block fieldset p span a").click(function(){
		var that = $(this),
			obj = $(".paymet_block fieldset p span a");
		
		obj.removeClass("cur");	
		that.addClass("cur");
		var attr = that.closest("span").attr("class")
		if(attr == "alipay"){
			$("#payId").val("30");
			$("#payChildId").val("121");
		}else if(attr == "unionPay"){
			$("#payId").val("19");
			$("#payChildId").val("49");
		}else if(attr == "alipayIn"){
			$("#payId").val("20");
			$("#payChildId").val("37");
		}else if(attr == "weixinPayPub"){
			$("#payId").val("27");
			$("#payChildId").val("58");
		}else if(attr == "weixinPaySea"){
			$("#payId").val("32");
			$("#payChildId").val("111");
		}else if(attr == "weixinPay"){
			var id=that.closest("span").attr("id")
			if(id='weixinWap'){
				$("#payId").val("27");
				$("#payChildId").val("117");
			}else{
				$("#payId").val("32");
				$("#payChildId").val("111");
			}
		}else if(attr == "cashPay" || attr == "postPay"){
			$("#payId").val("2");
			$("#payChildId").val("41");
		}else if(attr == "pufaPay"){
			$("#payId").val("15");
			$("#payChildId").val("115");
		}else{
			$("#payId").val("0");
			$("#payChildId").val("0");
		}
		
	});
});

function submit(){
	var path=getRootPath();
	$.ajax({
		type : "POST",
		url : path+"/overseas/orderLockSku", 
		data:{
			orderId:$("#orderId").val()
         },
         
		dataType:"json",
		success : function(data) {
			console.log(data);
			if(data.code!="0"){
				jShare(data.msg,"","");
			/*	setTimeout(function(){
					window.location.href = path + "/order/list?statusType=1" ;
				},3000)*/
				return;
			}else{
				$('#pay_form').submit();
			}
		}
	});
    
}