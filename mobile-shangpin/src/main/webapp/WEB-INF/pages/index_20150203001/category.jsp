<%@ page language="java" contentType="text/html; charset=UTF-8" trimDirectiveWhitespaces="true"
    pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/pages/common/include.inc.jsp"%>

<c:if test="${categoryItem.categoryList!= null}">
	<h2 class="title">分类</h2>
	<div id="navs" class="clr">
		<c:forEach var="category" items="${categoryItem.categoryList }">
		<c:if test="${category.id !='0' }">
				<a href="${ctx}/category/page?categoryId=${category.id}"><i><img alt="" src="${cdn:pic(pageContext.request)}/styles/shangpin/images/e.gif" lazy="${fn:substring(category.pic,0,fn:indexOf(category.pic,'-'))}-80-80.jpg"></i>${category.name}</a>
		</c:if>
		</c:forEach>
	</div>
</c:if>
