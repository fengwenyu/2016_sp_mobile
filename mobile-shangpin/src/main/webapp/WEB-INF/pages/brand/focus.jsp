<%@ page language="java" contentType="text/html; charset=UTF-8" trimDirectiveWhitespaces="true"
    pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/pages/common/include.inc.jsp"%>
<%-- 焦点图 Start --%>
<%-- <c:if test="${gallerys!= null && fn:length(gallerys) > 0}"> --%>
<c:if test="${brandShop.operat.gallery != null }">
    <div class="swiper-container" id="swiper-container1">
          <div class="swiper-wrapper">
	    	<c:forEach var="gallery" items="${brandShop.operat.gallery.list}">
	    		<c:choose>
	    			<c:when test="${gallery.type == '1'}">
	    				<div class="swiper-slide"><a href="${ctx}/subject/product/list?topicId=${gallery.refContent}&postArea=0"><img alt="${gallery.name}" src="${fn:substring(gallery.pic,0,fn:indexOf(gallery.pic,'-'))}-620-354.jpg" width="100%"></a></div>
	    			</c:when>
	    			<c:when test="${gallery.type == '2'}">
	    				<div class="swiper-slide"><a href="${ctx}/category/product/list?categoryNo=${gallery.refContent}&postArea=0"><img alt="${gallery.name}" src="${fn:substring(gallery.pic,0,fn:indexOf(gallery.pic,'-'))}-620-354.jpg" width="100%"></a></div>
	    			</c:when>
	    			<c:when test="${gallery.type == '3'}">
	    				<div class="swiper-slide"><a href="${ctx}/brand/product/list?brandNo=${gallery.refContent}&postArea=0"><img alt="${gallery.name}" src="${fn:substring(gallery.pic,0,fn:indexOf(gallery.pic,'-'))}-620-354.jpg" width="100%"></a></div>
	    			</c:when>
	    			<c:when test="${gallery.type == '4'}">
	    				<div class="swiper-slide"><a href="${ctx}/product/detail?productNo=${gallery.refContent}"><img alt="${gallery.name}" src="${fn:substring(gallery.pic,0,fn:indexOf(gallery.pic,'-'))}-620-354.jpg" width="100%"></a></div>
	    			</c:when>
	    			<c:when test="${gallery.type == '5'}">
	    				<div class="swiper-slide"><a href="${gallery.refContent}"><img alt="${gallery.name}" src="${fn:substring(gallery.pic,0,fn:indexOf(gallery.pic,'-'))}-620-354.jpg" width="100%"></a></div>
	    			</c:when>
	    			<c:otherwise>
	    				<div class="swiper-slide"><a href="#"><img alt="${gallery.name}" src="${fn:substring(gallery.pic,0,fn:indexOf(gallery.pic,'-'))}-620-354.jpg" width="100%"></a></div>
	    			</c:otherwise>
	    		</c:choose>
	    	</c:forEach>
		</div>
    	<div class="swiper-pagination"></div>
	</div>
</c:if>
