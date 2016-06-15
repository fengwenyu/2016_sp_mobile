<%@ page language="java" contentType="text/html; charset=UTF-8"
	trimDirectiveWhitespaces="true" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/pages/common/include.inc.jsp"%>

</head>
<rapid:override name="custum">
	<link
		href="${cdn:css(pageContext.request)}/styles/shangpin/css/page/weixin/account.css${ver}"
		rel="stylesheet" />
</rapid:override>
<%-- 浏览器标题 --%>
<rapid:override name="title">
	帐户绑定
</rapid:override>

<%-- 页标题 --%>
<rapid:override name="page_title">
	帐户绑定
</rapid:override>
<rapid:override name="content">
	 <div class="wx_bind">
  	<h2>解绑成功</h2>
    <p>您的尚品账号　<i><s:property value="#session['user'].email"/></i><br />与微信帐号已成功解除绑定</p>
    <a href="${ctx }/weixin/bind/info?way=modify" class="alList_submitBtn">去绑定</a>
  </div>
</rapid:override>
<%@ include file="/WEB-INF/pages/common/common.jsp"%>

