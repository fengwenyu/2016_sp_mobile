<%@ page language="java" contentType="text/html; charset=UTF-8"
	trimDirectiveWhitespaces="true" pageEncoding="UTF-8"%>
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
		<script src="${cdn:js(pageContext.request)}/styles/shangpin/js/core.js${ver}" type="text/javascript" charset="utf-8"></script>
		<script src="${cdn:js(pageContext.request)}/styles/shangpin/js/fixed_public.js${ver}" type="text/javascript" charset="utf-8"></script>
		<script type="text/javascript" charset="utf-8">
			var _smq = _smq || [];
			_smq.push(['_setAccount', '1d17bef', new Date()]);
			_smq.push(['_setCustomVar', 1, "${sessionScope.mshangpin_user.userid}", 1]);
			_smq.push(['pageview']);
		
		
			(function() {
			var sm = document.createElement('script'); sm.type =
			'text/javascript'; sm.async = true;
			sm.src ='${cdn:js(pageContext.request)}/styles/shangpin/js/sitemaster/collect_shangpin.js${ver}';
			var s = document.getElementsByTagName('script')[0];
			s.parentNode.insertBefore(sm, s);
			})();
		</script>
		<link href="${cdn:css(pageContext.request)}/styles/shangpin/css/base.css${ver}" rel="stylesheet" />
		
		<rapid:block name="custum">
		
		</rapid:block>
	</head>
	<body>
		<div class="container">
			<input type="hidden" id="ctx" name="ctx" value="${ctx}"/>
			<input type="hidden" id="_iswx" name="_iswx" value="${checkWX }"/>
			<input type="hidden" id="_isapp" name="_isapp" value="${checkAPP }"/>
			<%--头部 --%>
			<rapid:block name="header">
				
			</rapid:block>
			<c:choose>
				<c:when test="${!checkWX&&!checkAPP}">
					<div class="alContent">
				</c:when>
				<c:otherwise>
					<div class="alContent" style="margin-top: 0;">
				</c:otherwise>
			</c:choose>
			<%-- 页面的内容 --%>
			<rapid:block name="content">
	
			</rapid:block>
			</div>
			<%--弹层信息（地址） --%>
			<rapid:block name="overlay">
				
			</rapid:block>
		</div>
	</body>
</html>