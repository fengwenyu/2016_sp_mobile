<%@ page language="java" contentType="text/html; charset=UTF-8"
	trimDirectiveWhitespaces="true" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/pages/common/include.inc.jsp"%>
<c:if test="${advertTitle != null&&fn:length(advertTitle.list)>0}">
<ul class="three-list clr">
  <c:forEach var="list" items="${advertTitle.list}">
  <li>
  <c:choose>
  		<c:when test="${list.type == '1'}">
	    				<a href="${ctx}/subject/product/list?topicId=${list.refContent}&postArea=0">${list.name }</a>
	    			</c:when>
	    			<c:when test="${list.type == '2'}">
	    				<a href="${ctx}/category/product/list?categoryNo=${list.refContent}&postArea=0">${list.name }</a>
	    			</c:when>
	    			<c:when test="${list.type == '3'}">
	    				<a href="${ctx}/brand/product/list?brandNo=${list.refContent}&postArea=0">${list.name }</a>
	    			</c:when>
	    			<c:when test="${list.type == '4'}">
	    				<a href="${ctx}/product/detail?productNo=${list.refContent}">${list.name }</a>
	    			</c:when>
	    			<c:when test="${list.type == '5'}">
	    				<a href="${list.refContent}">${list.name }
	    			</c:when>
				
				
	    		<c:otherwise>
	    				<a href="#">${list.name }
	    		</c:otherwise>
	    </c:choose>

	<img alt="" src="${cdn:pic(pageContext.request)}/styles/shangpin/images/e.gif" lazy="${fn:replace(list.pic,'-10-10','-208-108') }" width="100%"></a>
</li>
</c:forEach>
</ul>
</c:if>