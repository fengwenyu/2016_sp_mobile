//使用jquey全局的ajax访问，处理ajax清求时sesion超时
$.ajaxSetup({
	timeout : 30000,
	contentType : "application/x-www-form-urlencoded;charset=utf-8",
	complete : function(XMLHttpRequest, textStatus) {
		var sessionstatus = XMLHttpRequest.getResponseHeader("sessionstatus"); // 通过XMLHttpRequest取得响应头，sessionstatus，
		if (sessionstatus == "timeout") {
			alert("用户帐号异常，请重新登录");
			var locationURL = XMLHttpRequest.getResponseHeader("locationURL"); // 通过XMLHttpRequest取得响应头，转向登陆页面locationURL，
			// 如果超时就处理 ，指定要跳转的页面
			window.location.href = locationURL;
		}
	},
	error : function(jqXHR, textStatus, errorThrown) {
		switch (jqXHR.status) {
		case (500):
			alert("服务器系统内部错误");
			break;
		case (401):
			alert("未登录");
			break;
		case (403):
			alert("无权限执行此操作");
			break;
		case (408):
			alert("请求超时");
			break;
		default:
			alert("未知错误");
		}
	}
})