<%@ page language="java" contentType="text/html; charset=UTF-8" trimDirectiveWhitespaces="true"
    pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/pages/common/include.inc.jsp"%>

<rapid:override name="custum">
	<link href="${cdn:css(pageContext.request)}/styles/shangpin/css/page/sale.css${ver}" rel="stylesheet" />
	<script type="text/javascript" charset="utf-8">
			loader = SP.core
				.install("${cdn:js(pageContext.request)}/styles/shangpin/js/zepto.1.1.3.js?${ver}")
				.using("${cdn:js(pageContext.request)}/styles/shangpin/js/lazyload.js${ver}")
				.excute()
				.using("${cdn:js(pageContext.request)}/styles/shangpin/js/config.sim.js${ver}")
				.excute()
				.using("${cdn:js(pageContext.request)}/styles/shangpin/js/downloadAppShow.js${ver}")
				.using("${cdn:js(pageContext.request)}/styles/shangpin/js/comm.js${ver}");
	</script>
</rapid:override >
<rapid:override name="appLayer">
	
</rapid:override>
<rapid:override name="page_title">
	全部上新	
</rapid:override>
<rapid:override name="content">
	<div class="hallBox clr" style="margin-top:4px">
		<c:forEach var="activity" items="${activities}">
			<a href="${ctx}/subject/product/list?topicId=${activity.refContent}&postArea=0" class="on-sale">
	            <img alt="" src="${cdn:pic(pageContext.request)}/styles/shangpin/images/e.gif" lazy="${fn:substring(activity.picNew,0,fn:indexOf(activity.picNew,'-'))}-${activity.width}-${activity.height}.jpg">
	            <div class="pic-des">
	              <p>${activity.desc}</p>
<!-- 	              <strong>¥<b>576</b>起</strong> -->
	            </div>
	          </a>
		</c:forEach>
      </div>
</rapid:override>

<%@ include file="/WEB-INF/pages/common/another_common_mall_banner.jsp" %> 