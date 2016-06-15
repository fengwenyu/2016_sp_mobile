function weixinSendPay(appId, timestamp, packageStr, paySign, signType,
		noncestr) {
	// 公众号支付

	document.addEventListener('WeixinJSBridgeReady', function onBridgeReady() {
		WeixinJSBridge.invoke('getBrandWCPayRequest', {
			"appId" : appId, // 公众号名称，由商户传入
			"timeStamp" : timestamp, // 时间戳
			"nonceStr" : noncestr, // 随机串
			"package" : packageStr,// 扩展包
			"signType" : signType, // 微信签名方式:1.sha1
			"paySign" : paySign
		// 微信签名
		}, function(res) {
			var path = getRootPath();
			if (res.err_msg == "get_brand_wcpay_request:ok") {

				location.href = path + "/fashionrun/wxpay/success?orderId="
						+ $("#orderId").val()+"&type=0";
			} else {
				location.href = path + "/fashionrun/wxpay/success?orderId="
				+ $("#orderId").val()+"&type=1";
			}
		});

	});
}
$(document).ready(function() {

	var appId = $("#appId").val();
	var timestamp = $("#timeStamp").val();
	var packageStr = $("#packageStr").val();
	packageStr = packageStr.replace(/\+/g, "%20");
	packageStr = packageStr.replace("&amp;", "&");
	var paySign = $("#paySign").val();
	var signType = $("#signType").val();
	var noncestr = $("#nonceStr").val();
	weixinSendPay(appId, timestamp, packageStr, paySign, signType, noncestr);
});
