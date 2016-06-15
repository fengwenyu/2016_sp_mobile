<%@ page language="java" contentType="text/html; charset=UTF-8" trimDirectiveWhitespaces="true"
    pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/pages/common/include.inc.jsp"%>

<rapid:override name="custum">
	<link rel="stylesheet" href="${ctx}/styles/shangpin/css/page/font-css/font-awesome.min.css"/>	
	<link href="${cdn:css(pageContext.request)}/styles/shangpin/css/page/newCategory.css" rel="stylesheet" />
	<script type="text/javascript" charset="utf-8">
			loader = SP.core
				.install("${cdn:js(pageContext.request)}/styles/shangpin/js/jquery.min.js${ver}")
				.using("${cdn:js(pageContext.request)}/styles/shangpin/js/lazyload.js${ver}")
				.excute()
				.using("${cdn:js(pageContext.request)}/styles/shangpin/js/config.sim.js${ver}")
				.using("${cdn:js(pageContext.request)}/styles/shangpin/js/jquery.cookie.js${ver}")
				.excute()
				.using("${cdn:js(pageContext.request)}/styles/shangpin/js/comm.js${ver}")
				
				
	</script>
</rapid:override >
<rapid:override name="appLayer">
</rapid:override>
<rapid:override name="content">
	<div class="cate-box">
          <div class="category-search"> 
			<c:import url="/category/navigation"></c:import>
			<c:import url="/category/brand"></c:import> 
		</div>
	</div>
</rapid:override>
<rapid:override name="down_page">
	
</rapid:override>
<%@ include file="/WEB-INF/pages/common/common_mall_banner.jsp" %>