<%@ page language="java" contentType="text/html; charset=UTF-8"
	trimDirectiveWhitespaces="true" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/pages/common/include.inc.jsp"%>

</head>
<rapid:override name="custum">
	<link
		href="${cdn:css(pageContext.request)}/styles/shangpin/css/page/weixin/account.css${ver}"
		rel="stylesheet" />
	<script type="text/javascript" charset="utf-8">
		loader = SP.core
				.install("${cdn:js(pageContext.request)}/styles/shangpin/js/jquery.min.js${ver}")
				.using("${cdn:js(pageContext.request)}/styles/shangpin/js/lazyload.js${ver}")
				.using("${cdn:js(pageContext.request)}/styles/shangpin/js/j.dialogs.js${ver}")
				.excute()
				.using("${cdn:js(pageContext.request)}/styles/shangpin/js/config.sim.js${ver}")

				.excute(function() {
					isHome(false);
				});
	</script>
</rapid:override>
<%-- 浏览器标题 --%>
<rapid:override name="title">
	帐户绑定
</rapid:override>

<%-- 页标题 --%>
<rapid:override name="page_title">
	帐户绑定
</rapid:override>
<rapid:override name="content">
	<div class="wx_bind">
		<h2>绑定成功</h2>
		<p>
			您已成功将尚品账号 <i>${sessionScope.mshangpin_user.name }</i><br />与微信帐号绑定
		</p>
		<a href="${ctx }/weixin/bind/info"
			class="alList_submitBtn">查看我绑定的帐号</a>
	</div>
</rapid:override>
<%@ include file="/WEB-INF/pages/common/common.jsp"%>

