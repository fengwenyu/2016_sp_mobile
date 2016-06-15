<%@ page language="java" contentType="text/html; charset=UTF-8"
	trimDirectiveWhitespaces="true" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/pages/common/include.inc.jsp"%>

<rapid:override name="custum">
	<link href="${cdn:css(pageContext.request)}/styles/shangpin/css/public.css${ver}"rel="stylesheet" />
	<link href="${cdn:css(pageContext.request)}/styles/shangpin/css/page/swiper.min.css${ver}"rel="stylesheet" />
	<link href="${cdn:css(pageContext.request)}/styles/shangpin/css/page/run.css${ver}" rel="stylesheet" />
	<script type="text/javascript" charset="utf-8">
		loader = SP.core
				.install("${cdn:js(pageContext.request)}/styles/shangpin/js/jquery.min.js${ver}")
				.using("${cdn:js(pageContext.request)}/styles/shangpin/js/lazyload.js${ver}")
				.excute()
				.using("${cdn:js(pageContext.request)}/styles/shangpin/js/config.sim.js${ver}")
				.using("${cdn:js(pageContext.request)}/styles/shangpin/js/page/swiper.min.js${ver}")
				.using("${cdn:js(pageContext.request)}/styles/shangpin/js/topFix.js${ver}")
				.using("${cdn:js(pageContext.request)}/styles/shangpin/js/run.js${ver}")
				.excute()
	</script>
</rapid:override>
<rapid:override name="page_title">
	运动馆
</rapid:override>
<rapid:override name="content">
	<%--焦点图 --%>
	<c:import url="/run/focus"></c:import>
	<div class="alContent">
		<div class="run_box">
			<c:import url="/run/hot"></c:import>
				<c:import url="/run/channel"></c:import>
			<!--content_list  END-->
		</div>
		<!--content_info  END-->
	</div>
</rapid:override>


<%@ include file="/WEB-INF/pages/common/common_no_content_banner.jsp"%>
