<%@ page language="java" contentType="text/html; charset=UTF-8" trimDirectiveWhitespaces="true"
    pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/pages/common/include.inc.jsp"%>
<c:if test="${categoryItem!=null }">
	<c:if test="${fn:length(categoryItem.categoryList) > 0}">
		<div class="navs clr">
		<c:forEach var="list" items="${categoryItem.categoryList}">
	        <a href="${ctx }/category/operation?id=${list.id}"><i><img alt="${list.name}" src="${cdn:pic(pageContext.request)}/styles/shangpin/images/e.gif" lazy="${fn:replace(list.pic,'-10-10','-64-64') }"></i>${list.name}</a>
	   	</c:forEach>
	    </div>
    </c:if>
</c:if>