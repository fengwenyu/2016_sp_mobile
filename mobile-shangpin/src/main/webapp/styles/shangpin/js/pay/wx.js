$(document).ready(function() {

	var appId = $("#appId").val();
	var timestamp = $("#timeStamp").val();
	var packageStr = $("#package").val();
	packageStr = packageStr.replace(/\+/g, "%20");
	packageStr = packageStr.replace("&amp;", "&");
	var paySign = $("#paySign").val();
	var signType = $("#signType").val();
	var noncestr = $("#nonceStr").val();
	weixinSendPay(appId, timestamp, packageStr, paySign, signType, noncestr);
});


function weixinSendPay(appId, timestamp, packageStr, paySign, signType,
		noncestr) {
	// 公众号支付

	document.addEventListener('WeixinJSBridgeReady', function onBridgeReady() {
		WeixinJSBridge.invoke('getBrandWCPayRequest', {
			"appId" : appId, // 公众号名称，由商户传入
			"timeStamp" : timestamp, // 时间戳
			"nonceStr" : noncestr, // 随机串
			"package" : packageStr,// 扩展包
			"signType" : signType, // 微信签名方式:1
			"paySign" : paySign
		// 微信签名
		}, function(res) {
			var path = getRootPath();
			if (res.err_msg == "get_brand_wcpay_request:ok") {

				location.href = path + "/pay/callback/WEIXINPUB?orderId="
						+ $("#orderId").val()+"&status=0";
			} else {
				location.href = path + "/pay/callback/WEIXINPUB?orderId="
						+ $("#orderId").val()+"&status=1";
			}
		});

	});
	/*wx.chooseWXPay({
	    timestamp: timestamp, // 支付签名时间戳，注意微信jssdk中的所有使用timestamp字段均为小写。但最新版的支付后台生成签名使用的timeStamp字段名需大写其中的S字符
	    nonceStr: noncestr, // 支付签名随机串，不长于 32 位
	    package: '', // 统一支付接口返回的prepay_id参数值，提交格式如：prepay_id=***）
	    signType: '', // 签名方式，默认为'SHA1'，使用新版支付需传入'MD5'
	    paySign: '', // 支付签名
	    success: function (res) {
	        // 支付成功后的回调函数
	    }
	});*/
/*	if (typeof WeixinJSBridge == "undefined"){
		   if( document.addEventListener ){
		       document.addEventListener('WeixinJSBridgeReady', onBridgeReady, false);
		   }else if (document.attachEvent){
		       document.attachEvent('WeixinJSBridgeReady', onBridgeReady); 
		       document.attachEvent('onWeixinJSBridgeReady', onBridgeReady);
		   }
		}else{
		   onBridgeReady();
		}*/
}