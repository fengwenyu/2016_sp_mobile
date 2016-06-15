<%@ page language="java" contentType="text/html; charset=UTF-8" trimDirectiveWhitespaces="true"
    pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/pages/common/include.inc.jsp"%>

<!DOCTYPE HTML>
<html lang="zh-CN">
	<head>
		<%--奥莱title --%>
		<title>
			<rapid:block name="title">
	        	尚品奥莱-触屏版
	   		</rapid:block>
	   	</title>
	   	<%--通用的meta设置 --%>
	   	<%--通用文件 --%>
	   	<rapid:block name="conmm">
			<link href="${cdn:css(pageContext.request)}/styles/shangpin/css/meet_base.css${ver}" rel="stylesheet" />
			<script src="${cdn:js(pageContext.request)}/styles/shangpin/js/core.js${ver}" type="text/javascript" charset="utf-8"></script>
		</rapid:block>
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
	   	
	   	<%--自定义引入css,jss等 --%>
	   	<rapid:block name="custum">
        	
   		</rapid:block>
	</head>
	<body>
	<div class="container">
	  <%-- 页面的头部 --%>
	  <rapid:block name="header">
	  	<c:if test="${!checkWX&&!checkAPP}">
	      <%@ include file="/WEB-INF/pages/common/meet_header.jsp"%>
	    </c:if>
	  </rapid:block>
	  
	   <%-- 页面的内容 --%>
	   <rapid:block name="content">
	      
	   </rapid:block>
	   </div>
	  <input name="ctx" id="ctx" value="${pageContext.request.contextPath}" type="hidden" />
	</body>  
</html>