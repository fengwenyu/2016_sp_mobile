<%@ page language="java" contentType="text/html; charset=UTF-8" trimDirectiveWhitespaces="true"
    pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/pages/common/include.inc.jsp"%>
<rapid:override name="custum">
	<link href="${cdn:css(pageContext.request)}/styles/shangpin/css/page/account.css" rel="stylesheet" />
	<link href="${cdn:css(pageContext.request)}/styles/shangpin/css/page/order.css" rel="stylesheet" />
	<script type="text/javascript" charset="utf-8">
			loader = SP.core
				.install("${cdn:js(pageContext.request)}/styles/shangpin/js/zepto.min.js${ver}")
				.using("${cdn:js(pageContext.request)}/styles/shangpin/js/lazyload.js${ver}")
				.using("${cdn:js(pageContext.request)}/styles/shangpin/js/config.sim.js${ver}")
				.using("${cdn:js(pageContext.request)}/styles/shangpin/js/comm.js${ver}")
				.using("${cdn:js(pageContext.request)}/styles/shangpin/js/coupon/coupon_list.js${ver}")
				.excute();
	</script>
</rapid:override >
<rapid:override name="content">
	<c:import url="/nav?navId=3"></c:import>

<div class="alContent">
  
  <div class="alOrder_coupon">
  	<p>${couponDesc }</p>
  
 <a href="${ctx }/index" class="alOrder_buyBtn">去购物</a>
    <div class="rule">
    	<h2>优惠券使用规则</h2>
        <ol>
			<li>当您将商品加入购物车，去结算时，可以使用符合条件的优惠券。</li>
			<li>每笔订单只能使用一张优惠券，每张优惠券只能使用一次，且不能用于购买或兑换充值礼品卡。</li>
			<li>某些特殊商品不能使用优惠券。</li>
			<li>优惠券需在有效期内使用，过期则不能再使用。</li>
			<li>优惠券不予兑现，如遇退单情况，退款仅按照实际支付金额结算。</li>
        </ol>
    </div>
  </div>
  
</div>

</rapid:override>

<%@ include file="/WEB-INF/pages/common/base.jsp" %> 