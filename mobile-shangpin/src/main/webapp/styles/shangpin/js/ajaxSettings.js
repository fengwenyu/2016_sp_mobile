//使用zepto框架 全局的ajax访问，处理ajax清求时sesion超时
$.ajaxSettings.timeout = 30000;// (默认： 0): 以毫秒为单位的请求超时时间, 0 表示不超时。
$.ajaxSettings.complete = function(XMLHttpRequest, textStatus) {
	var sessionstatus = XMLHttpRequest.getResponseHeader("sessionstatus"); // 通过XMLHttpRequest取得响应头，sessionstatus，
	if (sessionstatus == "timeout") {
		alert("用户帐号异常，请重新登录");
		var locationURL = XMLHttpRequest.getResponseHeader("locationURL"); // 通过XMLHttpRequest取得响应头，locationURL，
		// 如果超时就处理 ，指定要跳转的页面
		window.location.href = locationURL;
	}
}
$.ajaxSettings.error = function(xhr, errorType, error) {
	switch (xhr.status) {
	case (500):
		alert("服务器系统内部错误");
		break;
	case (401):
		alert("未登录");
		break;
	case (403):
		alert("无权限执行此操作");
		break;
	case (404):
		alert("所请求的页面不存在或链接错误");
	break;
	case (408):
		alert("请求超时");
		break;
	default:
		alert("访问超时或服务器异常！");
	}
}