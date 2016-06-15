<%@ page language="java" contentType="text/html; charset=UTF-8" trimDirectiveWhitespaces="true"
    pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/pages/common/include.inc.jsp"%>
 
 <c:if test="${galleries!=null && fn:length(galleries) > 0}">
 	 <div class="swiper-container share-img" id="swiper-container1">
 	 	<div class="swiper-wrapper">
 	 		<c:forEach var="gallery" items="${galleries}">
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
	 					<c:when test="${gallery.type == '2'}">
		 					<c:choose>
				 				<c:when test="${checkAPP }">
				 					<a href="shangpinapp://phone.shangpin/actiongocatelist?title=${gallery.name}&filters=category_${gallery.refContent}&categoryid=${gallery.refContent}">
				 				</c:when>
				 				<c:otherwise>
				 					<a href="${ctx}/category/product/list?categoryNo=${gallery.refContent}&postArea=0">
				 				</c:otherwise>
		 					</c:choose>				
	 					</c:when>
	 					<c:when test="${gallery.type == '3'}">
	 				 		<c:choose>
				 				<c:when test="${checkAPP }">
				 					<a href="shangpinapp://phone.shangpin/actiongobrandlist?title=${gallery.name}&filters=brand_${gallery.refContent}&brandid=${gallery.refContent}">
				 				</c:when>
				 				<c:otherwise>
				 					 <a href="${ctx}/brand/product/list?brandNo=${gallery.refContent}&postArea=0">
				 				</c:otherwise>
		 					</c:choose>
	 				 
	 					</c:when>
	 					<c:when test="${gallery.type == '4'}">
	 			 			<c:choose>
				 				<c:when test="${checkAPP }">
				 					<a href="shangpinapp://phone.shangpin/actiongodetail?title=${gallery.name}&productid=${gallery.refContent}">
				 				</c:when>
				 				<c:otherwise>
				 					 <a href="${ctx}/product/detail?productNo=${gallery.refContent}">
				 				</c:otherwise>
		 					</c:choose>
	 				
	 					</c:when>
	 					
	 					<c:when test="${gallery.type == '5'}">
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
	 						<c:choose>
	 							<c:when test="${checkAPP&&isTag=='1'}">
	 								<a href="shangpinapp://phone.shangpin/actiongotaglist?title=${gallery.name}&tagid=${gallery.refContent}"/>
	 							</c:when>
	 							<c:otherwise>
	 								<a href="${ctx }/lable/product/list?tagId=${gallery.refContent}"/>
	 							</c:otherwise>
	 						</c:choose>
	 					</c:otherwise>
	 					
 	 				</c:choose> 
 	 					<img alt="${gallery.name}" src="${fn:substring(gallery.pic,0,fn:indexOf(gallery.pic,'-'))}-640-358.jpg" width="100%"></a> 
 	 			</div>
 	 		</c:forEach>
 	 	</div>
 	 	<div class="swiper-pagination"></div>
 	 </div>
 </c:if>
  