<%@ page language="java" contentType="text/html; charset=UTF-8" trimDirectiveWhitespaces="true"
    pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/pages/common/include.inc.jsp"%>

<rapid:override name="custum">
	<script type="text/javascript" charset="utf-8">
			loader = SP.core
				.install("${cdn:js(pageContext.request)}/styles/shangpin/js/zepto.min.js${ver}")
				.using("${cdn:js(pageContext.request)}/styles/shangpin/js/download.js${ver}")
				.excute();
	</script>
</rapid:override >

<rapid:override name="header">

</rapid:override>
<rapid:override name="content">

</rapid:override>
<rapid:override name="footer">

</rapid:override>
<%@ include file="/WEB-INF/pages/common/base.jsp" %> 