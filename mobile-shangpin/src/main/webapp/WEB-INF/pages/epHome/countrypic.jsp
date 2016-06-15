 <%@ page language="java" contentType="text/html; charset=UTF-8"
	trimDirectiveWhitespaces="true" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/pages/common/include.inc.jsp"%>
 <div class="nation-banner">
        	<c:choose>
        		<c:when test="${countryname=='美国馆' }">
        			<a href=""><img alt="" src="${cdn:pic(pageContext.request)}/styles/shangpin/images/ep/nation_banner01.jpg" width="100%"></a>
        		</c:when>
        		<c:when test="${countryname=='意大利馆' }">
        			<a href=""><img alt="" src="${cdn:pic(pageContext.request)}/styles/shangpin/images/ep/nation_banner01.jpg" width="100%"></a>
        		</c:when>
        		<c:when test="${countryname=='香港馆' }">
        			<a href=""><img alt="" src="${cdn:pic(pageContext.request)}/styles/shangpin/images/ep/nation_banner01.jpg" width="100%"></a>
        		</c:when>
        		<c:when test="${countryname=='英国馆' }">
        			<a href=""><img alt="" src="${cdn:pic(pageContext.request)}/styles/shangpin/images/ep/nation_banner01.jpg" width="100%"></a>
        		</c:when>
        	</c:choose>
        </div>