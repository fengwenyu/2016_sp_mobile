
<%@ page language="java" contentType="text/html; charset=UTF-8" trimDirectiveWhitespaces="true"
    pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/pages/common/include.inc.jsp"%>
<!doctype html>
<html lang="zh-CN">
<head>
<meta content-type="text/html" charset="utf-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0, user-scalable=no, minimum-scale=1.0, maximum-scale=1.0">

<!-- 开启对web app程序的支持 -->
<meta name="apple-mobile-web-app-capable" content="yes">
<!-- 全屏模式浏览 -->
<meta name="apple-touch-fullscreen" content="yes">
<!-- 改变Safari状态栏的外观 -->
<meta name="apple-mobile-web-app-status-bar-style" content="black">
<!-- 禁止自动识别5位以上数字为电话 -->
<meta name="format-detection" content="telephone=no">
<title>支付</title>
<body>
 
<form id="pay_form" action="${gatewayUrl}" method="post">
	<c:forEach var="pa" items="${requestParams}">
		<input type="hidden" id="${pa.key }" name="${pa.key }" value="${pa.value }"/>
	</c:forEach>
	<script>document.forms['pay_form'].submit();setTimeout(function(){
		var url=window.location.href;

		if(url.indexOf("weixin/WEIXINWAP") > 0 ){
			window.location.href ="${pageContext.request.contextPath}/pay/callback/WEIXINWAP?orderId=" + document.getElementById("orderNo").value;
		}
		
	},10000)</script>
	</form>

</body>
 