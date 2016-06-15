<%@ page language="java" contentType="text/html; charset=UTF-8" trimDirectiveWhitespaces="true"
    pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/pages/common/include.inc.jsp"%>

<rapid:override name="subContent">
	<%--预售日期列表  --%>
	<c:import url="/predate" />
	
	<%--预售产品列表 --%>
	<c:import url="/preproduct" />
	<input type="hidden" id="gender" value="${gender}">
</rapid:override>

<%@ include file="/WEB-INF/pages/index/index.jsp" %> 