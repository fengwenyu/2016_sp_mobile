<%@ page language="java" contentType="text/html; charset=UTF-8"
	trimDirectiveWhitespaces="true" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/pages/common/include.inc.jsp"%>

<rapid:override name="custum">
	<link
		href="${cdn:css(pageContext.request) }/styles/shangpin/css/page/detail.css${ver}"
		rel="stylesheet" />
	<link
		href="${cdn:css(pageContext.request) }/styles/shangpin/css/page/detailEdit.css${ver}"
		rel="stylesheet" />
	<script type="text/javascript" charset="utf-8">
		loader = SP.core
				.install("${cdn:js(pageContext.request)}/styles/shangpin/js/zepto.min.js${ver}")
				.using("${cdn:js(pageContext.request)}/styles/shangpin/js/lazyload.js${ver}")
				.excute()
				.using("${cdn:js(pageContext.request)}/styles/shangpin/js/config.sim.js${ver}")
	</script>
</rapid:override>

<rapid:override name="page_title">
	 商品详情
</rapid:override>

<rapid:override name="content">
	<section class="detail_block">
		<section class="alProd_intro">
			<br />
			<h4>商品详情</h4>
			<p>
				<c:forEach items="${merchandise.info}" var="str">
        	${str }<br />
				</c:forEach>
				<c:if
					test="${merchandise.returnChangeRemind!=null&&merchandise.returnChangeRemind!=''}">
			温馨提示：
       		${fn:replace(merchandise.returnChangeRemind, '<br/>', '')}
      		</c:if>
			</p>
		</section>
		<c:if test="${merchandise.recommend!=null&&merchandise.recommend!=''}">
			<section>${merchandise.recommend }</section>
		</c:if>
		<section class="alProd_intro intro_bg">
			<h3>轻奢第一站，尚品全保障：</h3>
			<p class="server">
				<span>正品保障</span>100%全正品承诺<br /> <span>闪电发货</span>顶尖物流闪电直达<br /> <span>免邮政策</span>订单支付满499及白金以上会员特享<br />
				<span>客户服务</span>4006-900-900（8:00~24:00为你守候）
			</p>
		</section>

		<ul class="server_notice intro_bg">
			<li><img
				src="${cdn:pic(pageContext.request)}/styles/shangpin/images/detail/server01.png"
				width="100" height="65"></li>
			<li><img
				src="${cdn:pic(pageContext.request)}/styles/shangpin/images/detail/server02.png"
				width="100" height="65"></li>
			<li><img
				src="${cdn:pic(pageContext.request)}/styles/shangpin/images/detail/server03.png"
				width="100" height="65"></li>
		</ul>
	</section>
</rapid:override>
<%@ include file="/WEB-INF/pages/common/common.jsp"%>
