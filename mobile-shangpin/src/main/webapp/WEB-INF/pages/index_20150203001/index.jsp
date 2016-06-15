<%@ page language="java" contentType="text/html; charset=UTF-8" trimDirectiveWhitespaces="true"
    pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/pages/common/include.inc.jsp"%>

<rapid:override name="custum">
	<link rel="stylesheet" href="${ctx}/styles/shangpin/css/page/font-css/font-awesome.min.css"/>	
	<link href="${cdn:css(pageContext.request)}/styles/shangpin/css/page/swiper.min.css" rel="stylesheet" />
	<link href="${cdn:css(pageContext.request)}/styles/shangpin/css/page/home150918.css" rel="stylesheet" />
	<script type="text/javascript">window.bd && bd._qdc && bd._qdc.init({app_id: '75feae525068fb2bec34e48e'});</script>
	<script type="text/javascript" charset="utf-8">
			loader = SP.core
				.install("${cdn:js(pageContext.request)}/styles/shangpin/js/jquery.min.js${ver}")
				.using("${cdn:js(pageContext.request)}/styles/shangpin/js/lazyload.js${ver}")
				.excute()
				.using("${cdn:js(pageContext.request)}/styles/shangpin/js/config.sim.js${ver}")
				.using("${cdn:js(pageContext.request)}/styles/shangpin/js/swiper.min.js${ver}" )
				.excute()
				.using("${cdn:js(pageContext.request)}/styles/shangpin/js/comm.js${ver}")
				.using("${cdn:js(pageContext.request)}/styles/shangpin/js/masonry-docs.min.js${ver}")
				.using("${cdn:js(pageContext.request)}/styles/shangpin/js/jquery.infinitescroll.js${ver}")
				.using("${cdn:js(pageContext.request)}/styles/shangpin/js/downloadAppShow.js${ver}")
				.using("${cdn:js(pageContext.request)}/styles/shangpin/js/home150918.js${ver}")
				
	</script>
</rapid:override >
<rapid:override name="appLayer">
</rapid:override>
<rapid:override name="content">
	<%--标签 --%>
	<c:import url="/index/revealLabel"></c:import>
	<%--焦点图 --%>
	<c:import url="/index/focus?type=3"></c:import>
	<c:import url="/index/advert/first"></c:import>
	<c:import url="/index/entrance"></c:import> 
	<%--最新上架--%>
	<c:import url="/index/advert"></c:import> 
 	<c:import url="/index/fashion"></c:import> 
 	<%--分类 --%>
  	 <c:import url="/index/addCategory"></c:import> 
	 <c:import url="/index/custom/brand"></c:import> 
	<c:import url="/index/exclusive/recommend"></c:import>
</rapid:override>
<rapid:override name="downloadAppShowBottom">

</rapid:override>
		

<%@ include file="/WEB-INF/pages/common/base_only_index.jsp" %> 