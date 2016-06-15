<%@ page language="java" contentType="text/html; charset=UTF-8"
	trimDirectiveWhitespaces="true" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/pages/common/include.inc.jsp"%>

<rapid:override name="custum">
	<link
		href="${cdn:css(pageContext.request) }/styles/shangpin/css/page/member.css${ver}"
		rel="stylesheet" />
	<script type="text/javascript" charset="utf-8">
		loader = SP.core
				.install(
						"${cdn:js(pageContext.request)}/styles/shangpin/js/jquery.min.js${ver}")
				.using(
						"${cdn:js(pageContext.request)}/styles/shangpin/js/lazyload.js${ver}")
				.excute()
				.using(
						"${cdn:js(pageContext.request)}/styles/shangpin/js/config.sim.js${ver}")
				.using(
						"${cdn:js(pageContext.request)}/styles/shangpin/js/j.floatCalculate.js${ver}")
				.using(
						"${cdn:js(pageContext.request)}/styles/shangpin/js/j.appDialogs.js${ver}")
				.excute()
				.using(
						"${cdn:js(pageContext.request)}/styles/shangpin/js/order.js${ver}");
	</script>
</rapid:override>

<rapid:override name="page_title">
	收藏
</rapid:override>

<rapid:override name="content">
	<div class="collect_block">
		<c:if test="${collects ==null}">
			<div class="collect_block activity_null">
				<h2>暂无收藏的活动</h2>
			</div>

		</c:if>
		<c:forEach var="collect" items="${collects}">
			<a
				href="<c:url value='/subject/product/list?topicId=${collect.id}'/>">
				<dl>
					<dt>${collect.nameEN}</dt>
					<dd>${collect.nameCN}<span> <c:choose>
						<c:when test="${fn:containsIgnoreCase (collect.desc,'折')}">
							<em> ${fn:substringBefore(collect.desc,"折")}</em>${fn:substringAfter(collect.desc,'折')}</span>
						</c:when>
						<c:otherwise>
							<em> ${collect.desc}</em>
						</span>
					</dd>
					</c:otherwise>
					</c:choose>
				</dl>
			</a>
		</c:forEach>
	</div>
</rapid:override>
<rapid:override name="down_page">

</rapid:override>
<%@ include file="/WEB-INF/pages/common/common.jsp"%>
