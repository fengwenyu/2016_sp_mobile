<%@ page language="java" contentType="text/html; charset=UTF-8"
	trimDirectiveWhitespaces="true" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/pages/common/include.inc.jsp"%>

<rapid:override name="custum">
	<link href="${cdn:css(pageContext.request)}/styles/shangpin/css/page/meet/${meet.id }/main.css${ver}" rel="stylesheet" />
	
	<script src="${cdn:js(pageContext.request)}/styles/shangpin/js/jquery.min.js${ver}"  type="text/javascript" charset="utf-8"></script>
	<script src="${cdn:js(pageContext.request)}/styles/shangpin/js/lazyload.js${ver}"  type="text/javascript" charset="utf-8"></script>
	<script src="${cdn:js(pageContext.request)}/styles/shangpin/js/detail.dialogs.js${ver}"  type="text/javascript" charset="utf-8"></script>
	<script src="${cdn:js(pageContext.request)}/styles/shangpin/js/config.sim.js${ver}"  type="text/javascript" charset="utf-8"></script>
	<script src="${cdn:js(pageContext.request)}/styles/shangpin/js/comm.js${ver}"  type="text/javascript" charset="utf-8"></script>
	<script src="${cdn:js(pageContext.request)}/styles/shangpin/js/meet/${meet.id }/main.js${ver}"  type="text/javascript" charset="utf-8"></script>

</rapid:override>
<%-- 浏览器标题 --%>
<rapid:override name="title">
	${meet.title }
</rapid:override>
<rapid:override name="downloadAppShowHead">
</rapid:override>
<rapid:override name="downloadAppShowBottom">
</rapid:override>

<%-- 页标题 --%>
<rapid:override name="page_title">
	${meet.title }
</rapid:override>
<rapid:override name="header">
	<c:if test="${!checkWX&&!checkAPP}">
		<%@ include file="/WEB-INF/pages/common/header.jsp"%>
	</c:if>
</rapid:override>
<rapid:override name="content">
		${meet.html }
</rapid:override>
<rapid:override name="footer">
<input type="hidden" id="_isapp" name="_isapp" value="${checkAPP }" />
</rapid:override>
<%@ include file="/WEB-INF/pages/common/base_banner.jsp" %> 
