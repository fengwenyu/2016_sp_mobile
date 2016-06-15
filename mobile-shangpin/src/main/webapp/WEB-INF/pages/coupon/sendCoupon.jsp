<%@ page language="java" contentType="text/html; charset=UTF-8" trimDirectiveWhitespaces="true"
    pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/pages/common/include.inc.jsp"%>
<rapid:override name="custum">
	<link href="${cdn:css(pageContext.request)}/styles/shangpin/css/page/sendCoupon.css"${ver}" rel="stylesheet" />
	<script type="text/javascript" charset="utf-8">
	loader = SP.core
	.install("${cdn:js(pageContext.request)}/styles/shangpin/js/jquery.min.js${ver}")
	.using("${cdn:js(pageContext.request)}/styles/shangpin/js/lazyload.js${ver}")
	.excute()
	.using("${cdn:js(pageContext.request)}/styles/shangpin/js/config.sim.js${ver}")
	.excute();
	</script>
</rapid:override >

<rapid:override name="content">


   <div class="alOrder_coupon">
  	<p>${sendcoupondesc }</p>
  
    <a href="${ctx }/index" class="payment_btn">去购物</a>
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
</rapid:override> 

<%@ include file="/WEB-INF/pages/common/common.jsp" %> 

