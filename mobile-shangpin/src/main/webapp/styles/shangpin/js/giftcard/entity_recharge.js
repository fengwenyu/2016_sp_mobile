$(function() {
	//读取错误msg
	var msg=$("#msg").val();
	if(msg!=""){
		alert(msg);
		$("#msg").val("");
	}
	
	 
	$('#rechargeEntity').click(function(){
		 
		$("#recharge_entityFrom").submit();
	});
});