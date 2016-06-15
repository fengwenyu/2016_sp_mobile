<%@ page language="java" contentType="text/html; charset=UTF-8" trimDirectiveWhitespaces="true"
    pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/pages/common/include.inc.jsp"%>
  <!-- 页面内容start -->
<c:if test="${hotList != null}">
 
	<h3>${hotList.moduleName }</h3>
	<c:if test="${fn:length(hotList.list) != 0}">
    <ul class="imglist_three">
 		<c:forEach var="list" items="${hotList.list}">
 		<li>
	 		<c:choose>
	 			<c:when test="${list.type == '1'}">
	 			<c:choose>
	 				<c:when test="${checkAPP }">
	 					<a href="shangpinapp://phone.shangpin/actiongoactivitylist?title=${list.name}&activityid=${list.refContent}">
	 				</c:when>
	 				<c:otherwise>
	 					<a href="${ctx}/subject/product/list?topicId=${list.refContent}&postArea=0">
	 				</c:otherwise>
	 			</c:choose>
	 			</c:when>
	 			<c:when test="${list.type == '3'}">
		 			<c:choose>
		 				<c:when test="${checkAPP }">
		 					<a href="shangpinapp://phone.shangpin/actiongocatelist?title=${list.name}&filters=category_${list.refContent}&categoryid=${list.refContent}">
		 				</c:when>
		 				<c:otherwise>
		 					<a href="${ctx}/category/product/list?categoryNo=${list.refContent}&postArea=0">
		 				</c:otherwise>
		 			</c:choose>
	 				
	 			</c:when>
	 			<c:when test="${list.type == '4'}">
	 				 <c:choose>
		 				<c:when test="${checkAPP }">
		 					<a href="shangpinapp://phone.shangpin/actiongobrandlist?title=${list.name}&filters=brand_${list.refContent}&brandid=${list.refContent}">
		 				</c:when>
		 				<c:otherwise>
		 					 <a href="${ctx}/brand/product/list?brandNo=${list.refContent}&postArea=0">
		 				</c:otherwise>
		 			</c:choose>
	 				 
	 			</c:when>
	 			<c:when test="${list.type == '5'}">
	 			 <c:choose>
		 				<c:when test="${checkAPP }">
		 					<a href="shangpinapp://phone.shangpin/actiongodetail?title=${list.name}&productid=${list.refContent}">
		 				</c:when>
		 				<c:otherwise>
		 				
		 					 <a href="${ctx}/product/detail?productNo=${list.refContent}">
		 				</c:otherwise>
		 			</c:choose>
	 				
	 			</c:when>
	 			<c:when test="${list.type == '0'}">
	 			
	 			 <c:choose>
		 				<c:when test="${checkAPP }">
		 					<a href="ShangPinApp://phone.shangpin/actiongowebview?title=${list.name}&url=${fn:replace(list.refContent, '&', '8uuuuu8')}">
		 				</c:when>
		 				<c:otherwise>
		 						<a href="${list.refContent}">
		 				</c:otherwise>
		 			</c:choose>
	 				
	 			
	 			
	 			</c:when>
	 			<c:otherwise>
	 				<a href="#">
	 			</c:otherwise>
	 		</c:choose>
	 		<img alt="${list.name}" src="${fn:substring(list.pic,0,fn:indexOf(list.pic,'-'))}-105-114.jpg"/></a></li>	
 		</c:forEach>
	</ul>
	</c:if>
</c:if>