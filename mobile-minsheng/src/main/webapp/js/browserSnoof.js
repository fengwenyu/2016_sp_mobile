


function chagePayMode(payVal,paytypechildid){
	$.ajax({
		  url:'orderaction!chagePayMode',
		  data:{
			 "paytypeid":payVal,
			 "orderid":$("#orderid").val(),
			 "paytypechildid":paytypechildid
		  }, success:function(data){
			 if("success" == data.msgcode&&payVal==19&&paytypechildid==49){
				location.href = ""+ $("#path").val()+"/minshengpay/mspay?ch="+$("#ch").val()+"&orderId="+$("#orderid").val()+"&payVal="+payVal;
				return;
			  }
			 if("success" ==data.msgcode&&payVal==25&&paytypechildid==53){
				  location.href = ""+ $("#path").val()+"/minshengpay/mspay?ch="+$("#ch").val()+"&orderId="+$("#orderid").val()+"&payVal="+payVal;
				  return;
			  }
			 if("success" ==data.msgcode&&payVal==2&&paytypechildid==41){
				  location.href = ""+ $("#path").val()+"/unionpayaction!result?amount="+$("#amount").val()+"&ch="+$("#ch").val()+"&orderid="+$("#orderid").val();
				  return;
			  }
			 alert(data.msg);
			
		  }
 });
}  