var path = getRootPath();
function add() {
	var fashionRun = $('#fashion_form').serialize();
	$.ajax({
		url : path + "/fashionrun/add",
		data : fashionRun,
		dataType : "json",
		success : function(data) {
			console.log(data);
		}
	});
}
function pay(type) {
	var orderId = $("#orderId").val();
	var packId = $("#packId").val();
	var payType = $("#payType").val();
	if (type == "1") {
		$(".payment_btn").text("正在跳转支付宝支付页面.......");
		// $.get(path + "/order/alipay?orderId=" + orderId + "&totalFee=" +
		// data.returnInfo.onlineamount + "&date=" + date,function(data1){
		// window.location.href = data1;
		// });
		window.location.href = path + "/fashionrun/alipay?orderId=" + orderId;
		/*} else if (type == "2") {
		$(".payment_btn").text("正在跳转银联支付页面.......");
		// $.get(path + "/order/yinlianpay?orderId=" + orderId + "&totalFee=" +
		// data.returnInfo.onlineamount + "&date=" + date,function(data1){
		// window.location.href = data1;
		// });
		window.location.href = path + "/fashion/run/yinlianpay?orderId="
				+ orderId;*/

	} else if (type == "2") {
		$(".payment_btn").text("正在跳转微信支付页面.......");

		window.location.href = path + "/fashionrun/wxpay?orderId=" + orderId;

	}
	
}
function apply(){
	var fashionRun = $('#apply_form').serialize();
	$.ajax({
		url : path + "/fashionrun/apply",
		data : fashionRun,
		dataType : "json",
		type:"post",
		success : function(data) {
			console.log(data);
		}
	});
}