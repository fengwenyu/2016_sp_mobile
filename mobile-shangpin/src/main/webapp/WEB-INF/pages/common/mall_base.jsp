<%@ page language="java" contentType="text/html; charset=UTF-8"
	trimDirectiveWhitespaces="true" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/pages/common/include.inc.jsp"%>
<!DOCTYPE HTML>
<html lang="zh-CN">
<head>
<%--尚品网title --%>
<title><rapid:block name="title">
	        	尚品网_高端时尚购物平台_官网授权|海量新品|全场2折起
	   		</rapid:block></title>
<%--通用的meta设置 --%>
<rapid:block name="meta">
	<meta content-type="text/html" charset="utf-8">
	<meta name="viewport"
		content="width=device-width, initial-scale=1.0, user-scalable=no, minimum-scale=1.0, maximum-scale=1.0">
	<%-- 开启对web app程序的支持 --%>
	<meta name="apple-mobile-web-app-capable" content="yes">
	<%-- 全屏模式浏览 --%>
	<meta name="apple-touch-fullscreen" content="yes">
	<%-- 改变Safari状态栏的外观 --%>
	<meta name="apple-mobile-web-app-status-bar-style" content="black">
	<%-- 禁止自动识别5位以上数字为电话 --%>
	<meta name="format-detection" content="telephone=no">
</rapid:block>
<%--自定义引入css,jss等 --%>
<script
	src="${cdn:js(pageContext.request)}/styles/shangpin/js/core.js${ver}"
	type="text/javascript" charset="utf-8"></script>
<link
	href="${cdn:css(pageContext.request)}/styles/shangpin/css/base.css${ver}"
	rel="stylesheet" />

<rapid:block name="custum">

</rapid:block>
</head>
<body>
	<div class="container">
		<%--下拉窗口 --%>
		<rapid:block name="appLayer">

		</rapid:block>
		<%--头部 --%>
		<rapid:block name="header">
			<%@ include file="/WEB-INF/pages/common/header.jsp"%>
		</rapid:block>

		<c:choose>
			<c:when test="${!checkWX&&!checkAPP}">
				<div class="alContent">
			</c:when>
			<c:otherwise>
				<div class="alContent" style="margin-top: 0;">
			</c:otherwise>
		</c:choose>
		<input name="ctx" id="ctx" value="${pageContext.request.contextPath}"
			type="hidden" />
		<%-- 页面的内容 --%>
		<rapid:block name="content">

		</rapid:block>
	</div>

	<%-- 页面的尾部 --%>
	<rapid:block name="footer">
		<c:if test="${!checkWX&&!checkAPP}">
			<%@ include file="/WEB-INF/pages/common/mall_footer.jsp"%>
		</c:if>
	</rapid:block>
	<%--针对品牌、品牌商品列表搜索弹出层 --%>
	<rapid:block name="filter_layer">
		
	</rapid:block>
	
	<%--首页特卖弹出层 --%>
	<rapid:block name="popup_layer">
		
	</rapid:block>
	
	</div>
	<rapid:block name="statistics">
			 <%@ include file="/WEB-INF/pages/common/statistics.jsp"%>
		</rapid:block>
</body>
</html>