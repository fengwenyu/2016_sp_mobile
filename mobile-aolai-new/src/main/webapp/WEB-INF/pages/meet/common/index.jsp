<%@ page language="java" contentType="text/html; charset=UTF-8"
	trimDirectiveWhitespaces="true" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/pages/common/include.inc.jsp"%>

<rapid:override name="custum">
<script src="${cdn:js(pageContext.request)}/styles/shangpin/js/jquery.min.js${ver}"  type="text/javascript" charset="utf-8"></script>
<script src="${cdn:js(pageContext.request)}/styles/shangpin/js/lazyload.js${ver}"  type="text/javascript" charset="utf-8"></script>
<script src="${cdn:js(pageContext.request)}/styles/shangpin/js/config.sim.js${ver}"  type="text/javascript" charset="utf-8"></script>
 ${meet.css }
${meet.js }

</rapid:override>
<%-- 浏览器标题 --%>
<rapid:override name="title">
	${meet.title }
</rapid:override>

<rapid:override name="content">
	${meet.html }
</rapid:override>
<%@ include file="/WEB-INF/pages/common/meet_base.jsp" %>  
