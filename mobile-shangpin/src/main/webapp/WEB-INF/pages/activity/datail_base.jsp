<%@ page language="java" contentType="text/html; charset=UTF-8" trimDirectiveWhitespaces="true"
    pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/pages/common/include.inc.jsp"%>
<!DOCTYPE HTML>
<html lang="zh-CN">
	<head>
		<%--尚品网title --%>
		<title>
			<rapid:block name="title">
				尚品网_高端时尚购物平台_官网授权|海量新品|全场2折起
	   		</rapid:block>
	   	</title>
	   	<%--通用的meta设置 --%>
		<rapid:block name="meta">
	        <meta content-type="text/html" charset="utf-8">
			<meta name="viewport" content="width=device-width, initial-scale=1.0, user-scalable=no, minimum-scale=1.0, maximum-scale=1.0">
			<%-- 开启对web app程序的支持 --%>
			<meta name="apple-mobile-web-app-capable" content="yes">
			<%-- 全屏模式浏览 --%>
			<meta name="apple-touch-fullscreen" content="yes">
			<%-- 改变Safari状态栏的外观 --%>
			<meta name="apple-mobile-web-app-status-bar-style" content="black">
			<%-- 禁止自动识别5位以上数字为电话 --%>
			<meta name="format-detection" content="telephone=no">
	   	</rapid:block>
   		
	   	<rapid:block name="custum">
        	
   		</rapid:block>
	</head>
	<body style="background:#fff;">
		<c:choose>
			<c:when test="${index}">
				<c:choose>
					<c:when test="${checkWX || checkAPP || checkWeibo }">
						<div class="wapper" style="margin: 0px">
					</c:when>
					<c:otherwise>
						<div class="wapper">
					</c:otherwise>
				</c:choose>
			</c:when>
			<c:otherwise>
				<c:choose>
					<c:when test="${checkWX || checkAPP || checkWeibo }">
						<div class="wapper1" style="margin: 0px">
					</c:when>
					<c:otherwise>
						<div class="wapper1">
					</c:otherwise>
				</c:choose>
			</c:otherwise>
		</c:choose>
			<input name="ctx" id="ctx" value="${pageContext.request.contextPath}" type="hidden"/>
			<input id="_iswx" name="_iswx" type="hidden" value="${checkWX}"/>
			<%-- 页面的头部 --%>
		    <rapid:block name="header">
		    	 
		    </rapid:block>
		    <%--内容 --%>
		    <rapid:block name="content">
		    	
		    </rapid:block>
		</div>
		<script src="${cdn:js(pageContext.request)}/styles/shangpin/js/jquery.min.js" type="text/javascript" charset="utf-8"></script>
		<script src="${cdn:js(pageContext.request)}/styles/shangpin/js/core.js${ver}" type="text/javascript" charset="utf-8"></script>
		<rapid:block name="static_file">
			
		</rapid:block>
	</body>  
</html>