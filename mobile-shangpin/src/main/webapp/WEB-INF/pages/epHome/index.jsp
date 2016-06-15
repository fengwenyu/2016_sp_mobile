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
			.using("${cdn:js(pageContext.request)}/styles/shangpin/js/app_share_img.js${ver}")
			.excute(function(){
			
			})
	</script>
</rapid:override>
<rapid:override name="title">
	全球购首页
</rapid:override>

<rapid:override name="downloadAppShowBottom">
</rapid:override>
<rapid:override name="appLayer">
</rapid:override>
<rapid:override name="page_title">
	全球购
</rapid:override>
<rapid:override name="content">
	<!-- 轮播图 -->
	<c:import url="/ephome/focus"></c:import>
	<!-- 国家馆 -->
	<%-- <c:import url="/ephome/entrance"></c:import> --%>
	<!-- 广告位 -->
	<c:import url="/ephome/ad"></c:import>
	<!-- 列表 -->
	<c:import url="/ephome/indexmall"></c:import>
	  <c:if test="${checkAPP}">  
  		<div class="shang_share"> 
         	<a href="shangpinapp://phone.shangpin/actiongoshare?title=尚品全球购 &url=${basePath }/ephome/index&desc=%E5%B0%9A%E5%93%81%E5%85%A8%E7%90%83%E8%B4%AD%EF%BC%8C%E7%B2%BE%E9%80%89%E5%85%A8%E7%90%83%E6%97%B6%E5%B0%9A%E5%8D%95%E5%93%81%EF%BC%8C%E8%AE%A9%E6%97%B6%E5%B0%9A%E6%B2%A1%E6%9C%89%E6%97%B6%E5%B7%AE%EF%BC%8C100%25%E6%B5%B7%E5%A4%96%E7%9B%B4%E9%87%87%EF%BC%8C100%25%E6%B5%B7%E5%A4%96%E6%AD%A3%E5%93%81%EF%BC%8C100%25%E6%B5%B7%E5%A4%96%E7%9B%B4%E9%82%AE"><img src="${cdn:pic(pageContext.request)}/styles/shangpin/images/b_share.png" width="40"></a>
   	   </div>
	 </c:if>  
</rapid:override>


<%@ include file="/WEB-INF/pages/common/bottom_common_mall_banner.jsp" %> 