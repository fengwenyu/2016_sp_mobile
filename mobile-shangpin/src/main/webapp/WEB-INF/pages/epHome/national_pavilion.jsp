 <%@ page language="java" contentType="text/html; charset=UTF-8"
	trimDirectiveWhitespaces="true" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/pages/common/include.inc.jsp"%>
<rapid:override name="custum">
	<link rel="stylesheet" href="${cdn:css(pageContext.request)}/styles/shangpin/css/page/font-css/font-awesome.min.css${ver}">
	<link href="${cdn:css(pageContext.request)}/styles/shangpin/css/swiper.min.css${ver}" rel="stylesheet" />	
	<link href="${cdn:css(pageContext.request)}/styles/shangpin/css/page/ep_home.css${ver}" rel="stylesheet" />
	<script type="text/javascript" charset="utf-8">
		loader = SP.core
			.install("${cdn:js(pageContext.request)}/styles/shangpin/js/zepto.1.1.3.js${ver}")
			.using("${cdn:js(pageContext.request)}/styles/shangpin/js/lazyload.js${ver}")
			.excute()
			.using("${cdn:js(pageContext.request)}/styles/shangpin/js/config.sim.js${ver}")
			.using("${cdn:js(pageContext.request)}/styles/shangpin/js/ephome/swiper.min.js${ver}")
			.excute()
			.using("${cdn:js(pageContext.request)}/styles/shangpin/js/comm.js${ver}")
			.using("${cdn:js(pageContext.request)}/styles/shangpin/js/ephome/ep_home.js${ver}")
			.excute(function(){
			
			})
	</script>
</rapid:override>
<rapid:override name="title">
	${countryname }
</rapid:override>
<rapid:override name="downloadAppShowBottom">
</rapid:override>
<rapid:override name="appLayer">
</rapid:override>
<rapid:override name="page_title">
	${countryname }
</rapid:override>
<rapid:override name="content">
	 <!-- 焦点图 Start -->
     <%-- <c:import url="/ephome/countrypic"></c:import> --%>
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
     <!-- 焦点图 End -->
	 <c:import url="/ephome/mall?postAreaNO=${postAreaNO }"></c:import>
      <c:if test="${checkAPP}">  
	  	  <div class="shang_share"> 
	         	<a href="shangpinapp://phone.shangpin/actiongoshare?title=尚品全球购-${countryname }&url=${basePath }/ephome/pavilion?country=${country }8uuuuu8postAreaNO=${postAreaNO }&desc=精选时尚单品，让时尚没有时差，100%海外直采，100%海外正品，100%海外直邮"><img src="${cdn:pic(pageContext.request)}/styles/shangpin/images/b_share.png" width="40"></a>
	   	  </div>
	 </c:if>  
</rapid:override>
<%@ include file="/WEB-INF/pages/common/bottom_common_mall_banner.jsp" %> 