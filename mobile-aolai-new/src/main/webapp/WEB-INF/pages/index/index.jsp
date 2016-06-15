<%@ page language="java" contentType="text/html; charset=UTF-8" trimDirectiveWhitespaces="true"
    pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/pages/common/include.inc.jsp"%>

<rapid:override name="custum">
	<link href="${cdn:css(pageContext.request)}/styles/shangpin/css/page/index.css${ver}" rel="stylesheet" />
	<link href="${cdn:css(pageContext.request)}/styles/shangpin/css/add2home.css${ver}" rel="stylesheet" />
	<script type="text/javascript" charset="utf-8">
			loader = SP.core
				.install("${cdn:js(pageContext.request)}/styles/shangpin/js/zepto.min.js${ver}")
				.using("${cdn:js(pageContext.request)}/styles/shangpin/js/lazyload.js${ver}")
				.using("${cdn:js(pageContext.request)}/styles/shangpin/js/biz/navigation.js${ver}")
				.excute()
				.using("${cdn:js(pageContext.request)}/styles/shangpin/js/config.sim.js${ver}")
				.using("${cdn:js(pageContext.request)}/styles/shangpin/js/css3.js${ver}")
				.using("${cdn:js(pageContext.request)}/styles/shangpin/js/downloadAppShow.js${ver}")
				.using("${cdn:js(pageContext.request)}/styles/shangpin/js/slideLayer.js${ver}")
				.excute()
				.using("${cdn:js(pageContext.request)}/styles/shangpin/js/remainTime.js${ver}")
				.excute()
				.using("${cdn:js(pageContext.request)}/styles/shangpin/js/comm.js${ver}")
				.excute(function(){
					//isHome(true);
					$('#J_m-slider').slider({wrap:".slider-outer",wrapUl:".slider-wrap",wrapStatus:'.slider-status',isLoop:true,isPlay:true});
				});
	</script>
</rapid:override >

<rapid:override name="header">
	<!-- 顶部下载APP -->
    <div class="headApp" red_url=""><img src="${ctx }/styles/shangpin/images/download_top.png"></div>
	<c:if test="${!checkWX&&!checkAPP}">
	   <%@ include file="/WEB-INF/pages/common/header.jsp"%>
     </c:if>
</rapid:override>
<rapid:override name="content">
	<%--导航  --%>
	<%@ include file="/WEB-INF/pages/index/navigation.jsp" %>
	<div class="alContent">
		<rapid:block name="subContent">
	        <%--最新热卖 --%>
			<c:import url="/newhot?gender=${gender}" />
			
			<%--限时特卖 --%>
			<c:import url="/timelimit?gender=${gender}"/>
			<input type="hidden" id="gender" value="${gender}">
	   	</rapid:block>
	</div>
</rapid:override>

<%@ include file="/WEB-INF/pages/common/base.jsp" %> 