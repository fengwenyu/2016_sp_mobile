<%@ page language="java" contentType="text/html; charset=UTF-8" trimDirectiveWhitespaces="true"
    pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/pages/common/include.inc.jsp"%>
 <h2 class="title">买遍全球<em>世界那么大，咱们去看看</em></h2>
        <div id="navs" class="clr">
            <c:choose>
            	<c:when test="${checkAPP }">
            		<a href="ShangPinApp://phone.shangpin/actiongowebview?title=美国馆&url=${basePath }/ephome/pavilion?country=America8uuuuu8postAreaNO=51108">
            		<i><img alt="" src="${cdn:pic(pageContext.request)}/styles/shangpin/images/ep/country_icon01.png"></i>美国馆</a>
            		<a href="ShangPinApp://phone.shangpin/actiongowebview?title=意大利&url=${basePath }/ephome/pavilion?country=Italy8uuuuu8postAreaNO=51110">
            		<i><img alt="" src="${cdn:pic(pageContext.request)}/styles/shangpin/images/ep/country_icon02.png"></i>意大利</a>
            		<a href="ShangPinApp://phone.shangpin/actiongowebview?title=香港&url=${basePath }/ephome/pavilion?country=Hongkong8uuuuu8postAreaNO=33">
            		<i><img alt="" src="${cdn:pic(pageContext.request)}/styles/shangpin/images/ep/country_icon03.png"></i>香港</a>
            		<a href="ShangPinApp://phone.shangpin/actiongowebview?title=英国&url=${basePath }/ephome/pavilion?country=England8uuuuu8postAreaNO=51112">
            		<i><img alt="" src="${cdn:pic(pageContext.request)}/styles/shangpin/images/ep/country_icon04.png"></i>英国</a>
            	</c:when>
            	<c:otherwise>
            		<a href="${ctx }/ephome/pavilion?country=America&postAreaNO=51108"><i><img alt="" src="${cdn:pic(pageContext.request)}/styles/shangpin/images/ep/country_icon01.png"></i>美国馆</a>
            		<a href="${ctx }/ephome/pavilion?country=Italy&postAreaNO=51110"><i><img alt="" src="${cdn:pic(pageContext.request)}/styles/shangpin/images/ep/country_icon02.png"></i>意大利</a>
            		<a href="${ctx }/ephome/pavilion?country=Hongkong&postAreaNO=33"><i><img alt="" src="${cdn:pic(pageContext.request)}/styles/shangpin/images/ep/country_icon03.png"></i>香港</a>
            		<a href="${ctx }/ephome/pavilion?country=England&postAreaNO=51112"><i><img alt="" src="${cdn:pic(pageContext.request)}/styles/shangpin/images/ep/country_icon04.png"></i>英国</a>
            	</c:otherwise>
            </c:choose>
             
            
        </div>
        <div class="line"></div>