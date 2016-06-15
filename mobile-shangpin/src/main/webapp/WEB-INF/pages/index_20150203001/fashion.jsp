<%@ page language="java" contentType="text/html; charset=UTF-8" trimDirectiveWhitespaces="true"
    pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/pages/common/include.inc.jsp"%>
<c:if test="${fn:length(fashionList) > 0 }">
<c:set var="fashion" value="true" />
	<h2 class="title">潮流趋势<a href="${ctx }/fashion/list?type=1" class="title-more">更多<i class="icon-caret-right"></i></a></h2>
 	<!-- Swiper -->
    <div class="swiper-container" id="swiper-container2">
       <div class="swiper-wrapper">
	        
	             <c:forEach var="fashion" items="${fashionList }">
	                  <div class="swiper-slide">
	                  
	                  <c:choose>
						<c:when test="${fashion.type=='1' }">
							<a href="${ctx }/subject/product/list?topicId=${fashion.refContent}&postArea=0">
						</c:when>
						<c:when test="${fashion.type=='2'}">
							<a href="${ctx }/category/product/list?categoryNo=${fashion.refContent}&postArea=0">
							
						</c:when>
						<c:when test="${fashion.type=='3'}">
							<a href="${ctx }/brand/product/list?brandNo=${fashion.refContent}&postArea=0">
							
						</c:when>
		
						<c:when test="${fashion.type=='4'}">
							<a href="<c:url value='/product/detail?productNo=${fashion.refContent}'/>">
						</c:when>
						<c:when test="${fashion.type=='5'}">
							<a href="${fashion.refContent}">
						</c:when>
						<c:otherwise>
							   <a href="${ctx }/fashion/info?id=${fashion.refContent }">
						</c:otherwise>
					</c:choose>
	                  
	                  
	                  
	                  
	                  <img alt="${fashion.name }" src="${cdn:pic(pageContext.request)}/styles/shangpin/images/e.gif" lazy="${fn:replace(fashion.pic,'-10-10','-113-163') }" width="100%"></a></div>
	             </c:forEach>   
	         
        </div>
    </div>
   
   </c:if>