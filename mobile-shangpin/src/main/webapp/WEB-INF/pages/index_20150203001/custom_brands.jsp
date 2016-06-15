<%@ page language="java" contentType="text/html; charset=UTF-8" trimDirectiveWhitespaces="true"
    pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/pages/common/include.inc.jsp"%>
<!--推荐品牌-->
<c:if test="${fn:length(customBrandItem.favList) >0}">

<div class="brandBox clr">
   <c:forEach var="list" items="${customBrandItem.favList}">
	   	<c:choose>
				<c:when test="${list.type=='1' }">
					<a href="${ctx }/subject/product/list?topicId=${list.refContent}&postArea=0">
				</c:when>
				<c:when test="${list.type=='2'}">
					<a href="${ctx }/category/product/list?categoryNo=${list.refContent}&postArea=0">
					
				</c:when>
				<c:when test="${list.type=='3'}">
					<a href="${ctx }/brand/product/list?brandNo=${list.refContent}&postArea=0">
					
				</c:when>

				<c:when test="${list.type=='4'}">
					<a href="<c:url value='/product/detail?productNo=${list.refContent}'/>">
				</c:when>
				<c:when test="${list.type=='5'}">
					<a href="${list.refContent}">
				</c:when>
				<c:otherwise>
					<a href="">
				</c:otherwise>
			</c:choose>
			   <img alt="${list.name }" src="${cdn:pic(pageContext.request)}/styles/shangpin/images/e.gif"
					lazy="${fn:replace(list.pic,'-10-10','-152-72') }"></a>
	    </c:forEach>
</div>
<a href="${ctx }/brand/list" class="more-btn">全部品牌</a>
</c:if>