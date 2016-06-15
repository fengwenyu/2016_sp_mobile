$(document).ready(function() {
$('#pay_form').submit();
setTimeout(function(){
	var url=window.location.href;
	console.log(url);
	if(url.indexOf("weixin/WEIXINWAP") > 0 ){
		var path = getRootPath();
		window.location.href = path + "/pay/callback/WEIXINWAP?orderId=" + $("#orderNo").val();
	}
	
},5000)
});