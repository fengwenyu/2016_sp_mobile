<%@ page language="java" contentType="text/html; charset=UTF-8" trimDirectiveWhitespaces="true"
    pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/pages/common/include.inc.jsp"%>
    <c:if test="${gallerys != null}">
	<div class="swiper-container" id="swiper-container1">
    	<div class="swiper-wrapper">
	    	<c:forEach var="gallery" items="${gallerys.gallery}">
	    		<div class="swiper-slide">
	    		<c:choose>
	 			<c:when test="${gallery.type == '1'}">
	 			<c:choose>
	 				<c:when test="${checkAPP }">
	 					<a href="shangpinapp://phone.shangpin/actiongoactivitylist?title=${gallery.name}&activityid=${gallery.refContent}">
	 				</c:when>
	 				<c:otherwise>
	 					<a href="${ctx}/subject/product/list?topicId=${gallery.refContent}&postArea=0">
	 				</c:otherwise>
	 			</c:choose>
	 			</c:when>
	 			<c:when test="${gallery.type == '3'}">
		 			<c:choose>
		 				<c:when test="${checkAPP }">
		 					<a href="shangpinapp://phone.shangpin/actiongocatelist?title=${gallery.name}&categoryid=${gallery.refContent}">
		 				</c:when>
		 				<c:otherwise>
		 					<a href="${ctx}/category/product/list?categoryNo=${gallery.refContent}&postArea=0">
		 				</c:otherwise>
		 			</c:choose>
	 			
	 				
	 			</c:when>
	 			<c:when test="${gallery.type == '4'}">
	 				 <c:choose>
		 				<c:when test="${checkAPP }">
		 					<a href="shangpinapp://phone.shangpin/actiongobrandlist?title=${gallery.name}&brandid=${gallery.refContent}">
		 				</c:when>
		 				<c:otherwise>
		 					 <a href="${ctx}/brand/product/list?brandNo=${gallery.refContent}&postArea=0">
		 				</c:otherwise>
		 			</c:choose>
	 				 
	 			</c:when>
	 			<c:when test="${gallery.type == '5'}">
	 			 <c:choose>
		 				<c:when test="${checkAPP }">
		 					<a href="shangpinapp://phone.shangpin/actiongodetail?title=${gallery.name}&productid=${gallery.refContent}">
		 				</c:when>
		 				<c:otherwise>
		 					 <a href="${ctx}/product/detail?productNo=${gallery.refContent}">
		 				</c:otherwise>
		 			</c:choose>
	 				
	 			</c:when>
	 			<c:when test="${gallery.type == '0'}">
	 				 <c:choose>
		 				<c:when test="${checkAPP }">
		 					<a href="ShangPinApp://phone.shangpin/actiongowebview?title=${gallery.name}&url=${fn:replace(gallery.refContent, '&', '8uuuuu8')}">
		 				</c:when>
		 				<c:otherwise>
		 					 <a href="${gallery.refContent}">
		 				</c:otherwise>
		 			</c:choose>
	 				
	 			</c:when>
	 			<c:otherwise>
	 				<a href="#">
	 			</c:otherwise>
	 		</c:choose>
	    		<img alt="${gallery.name}" src="${fn:substring(gallery.pic,0,fn:indexOf(gallery.pic,'-'))}-320-135.jpg" width="100%"></a></div>
	    	</c:forEach>
			</ul>
		</div>
    	<div class="swiper-pagination"></div>
	</div>
</c:if>

	