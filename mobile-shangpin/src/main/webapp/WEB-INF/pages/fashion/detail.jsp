<%@ page language="java" contentType="text/html; charset=UTF-8" trimDirectiveWhitespaces="true"
    pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/pages/common/include.inc.jsp"%>

<rapid:override name="custum">
	<link href="${cdn:css(pageContext.request)}/styles/shangpin/css/page/news.css${ver}" rel="stylesheet" />
	<script type="text/javascript" charset="utf-8">
			loader = SP.core
			.install("${cdn:js(pageContext.request)}/styles/shangpin/js/jquery.min.js${ver}")
			.using("${cdn:js(pageContext.request)}/styles/shangpin/js/lazyload.js${ver}")
			.excute()
			.using("${cdn:js(pageContext.request)}/styles/shangpin/js/config.sim.js${ver}")
			.excute();
			
	</script>
</rapid:override >

<%-- 浏览器标题 --%>
<rapid:override name="title">
	${fashion.title}
</rapid:override>

<%-- 页标题 --%>
<rapid:override name="page_title">
	${fashion.title}
</rapid:override>
<rapid:override name="content">
<div>
<section>
	    <div class="news_box">
	      <h2>${fashion.title}</h2>
	      <h3>${fn:substring(fashion.releaseDate,0,10)}</h3>
	      <p>${fashion.content}</p>
	    </div>
	</section>
</div>
</rapid:override> 
<%@ include file="/WEB-INF/pages/common/common.jsp" %> 
