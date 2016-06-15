<%@ page language="java" contentType="text/html; charset=UTF-8" trimDirectiveWhitespaces="true"
    pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/pages/common/include.inc.jsp"%>

<rapid:override name="custum">
	<link href="${cdn:css(pageContext.request)}/styles/shangpin/css/public.css${ver}" rel="stylesheet" />
	<link href="${cdn:css(pageContext.request)}/styles/shangpin/css/page/product/detail.css${ver}" rel="stylesheet" />
	<script type="text/javascript" charset="utf-8">
			loader = SP.core
				.install("${cdn:js(pageContext.request)}/styles/shangpin/js/jquery.min.js${ver}")
				.using("${cdn:js(pageContext.request)}/styles/shangpin/js/lazyload.js${ver}")
				.excute()
				.using("${cdn:js(pageContext.request)}/styles/shangpin/js/config.sim.js${ver}")
				.using("${cdn:js(pageContext.request)}/styles/shangpin/js//j.dialogs.js${ver}")
				.excute()
				.using("${cdn:js(pageContext.request)}/styles/shangpin/js/comm.js${ver}")
				.using("${cdn:js(pageContext.request)}/styles/shangpin/js/product/appProductDetail.js${ver}")
				
	</script>
</rapid:override ><rapid:override name="title">
    	商品详情
</rapid:override>
<rapid:override name="downloadAppShowBottom">
</rapid:override>
<rapid:override name="page_title">
	 商品详情
</rapid:override>
<rapid:override name="appLayer">
</rapid:override>
<rapid:override name="content">
<div class="product_all">
<div class="detailed_information">
 	<c:import url="navigation.jsp"></c:import> 
 	<c:if test="${type=='1'}" >
		<c:import url="/product/app/guessLike"></c:import>
	</c:if>
</div>
</div>
</rapid:override>


<%@ include file="/WEB-INF/pages/common/common_normal_banner.jsp" %> 