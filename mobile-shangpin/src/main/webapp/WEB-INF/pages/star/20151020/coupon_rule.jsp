<%@ page language="java" contentType="text/html; charset=UTF-8" trimDirectiveWhitespaces="true"
    pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/pages/common/include.inc.jsp"%>

<rapid:override name="custum">
<style>
.step-box{margin-top:0;}
.step-box p{margin-top:5px;}
</style>
	<link href="${cdn:css(pageContext.request)}/styles/shangpin/css/star-packet/20151020/base.css${ver}" rel="stylesheet" />
	<link href="${cdn:css(pageContext.request)}/styles/shangpin/css/public.css${ver}" rel="stylesheet" />
	<link href="${cdn:css(pageContext.request)}/styles/shangpin/css/page/help_center.css${ver}" rel="stylesheet" />
	<script type="text/javascript">window.bd && bd._qdc && bd._qdc.init({app_id: '75feae525068fb2bec34e48e'});</script>
	<script type="text/javascript" charset="utf-8">
			loader = SP.core
				.install("${cdn:js(pageContext.request)}/styles/shangpin/js/jquery.min.js${ver}")
				.using("${cdn:js(pageContext.request)}/styles/shangpin/js/lazyload.js${ver}")
				.excute()
				.using("${cdn:js(pageContext.request)}/styles/shangpin/js/config.sim.js${ver}")
				.using("${cdn:js(pageContext.request)}/styles/shangpin/js/j.dialogs.js${ver}")
				.excute()
				.using("${cdn:js(pageContext.request)}/styles/shangpin/js/comm.js${ver}")
				 .using("${cdn:js(pageContext.request)}/styles/weixin/jweixin-1.0.0.js${ver}")
				.using("${cdn:js(pageContext.request)}/styles/shangpin/js/weixin/WXShareM.js${ver}")
				.using("${cdn:js(pageContext.request)}/styles/shangpin/js/downloadAppShow.js${ver}")
				
	</script>
</rapid:override >
 
<rapid:override name="title">
	优惠券说明
</rapid:override>
<rapid:override name="page_title">
     优惠券说明
</rapid:override>

<rapid:override name="content">
<div class="container">
    <!-- 页面内容start -->
    <div class="alContent">
        <div class="login-explain-box">
          <p>一.优惠券使用规则</p>
          <p>1. 每笔订单只能使用一张优惠券，每张优惠券只能使用一次，且不能用于购买或兑换充值礼品卡。<br />
			2. 某些特殊商品不能使用优惠券。<br />
			3. 优惠券需在有效期内使用，过期则不能再使用。<br />
			4. 优惠券不予兑现，如遇退单情况，退款仅按照实际支付金额结算。
          </p>
          <p>二.优惠券在哪里查看</p>
          <div class="step-box">
            <h3>STEP.1 </h3>
            <p>登录尚品APP,点击“我的－优惠券”。</p>
          </div>
          <p><img src="${cdn:pic(pageContext.request)}/styles/shangpin/images/star-packet/20151020/coupon001.jpg" /></p>
          <div class="step-box">
            <h3>STEP.2 </h3>
            <p>你领取的优惠券在这里</p>
          </div>
          <p><img src="${cdn:pic(pageContext.request)}/styles/shangpin/images/star-packet/20151020/coupon002.jpg" /></p>
          <p>三.优惠券如何使用</p>
          <div class="step-box">
            <h3>STEP.1 </h3>
            <p>选择商品加入购物袋，点击“去结算”，进入“填写核对订单信息”页面。</p>
          </div>
          <p><img src="${cdn:pic(pageContext.request)}/styles/shangpin/images/star-packet/20151020/coupon003.jpg" /></p>
          <div class="step-box">
            <h3>STEP.2 </h3>
            <p>在“提交订单”页面商品信息下方选择您要使用的优惠券。</p>
          </div>
          <p><img src="${cdn:pic(pageContext.request)}/styles/shangpin/images/star-packet/20151020/coupon004.jpg" /></p>
          <div class="step-box">
            <h3>STEP.3 </h3>
            <p>优惠券使用成功，点击“提交订单”去支付。</p>
          </div>
          <p><img src="${cdn:pic(pageContext.request)}/styles/shangpin/images/star-packet/20151020/coupon005.jpg" /></p>

        </div>
    </div>
    <!-- 页面内容end -->
    
  
  </div>
</rapid:override>
<rapid:override name="footer">

</rapid:override>
<%@ include file="/WEB-INF/pages/common/common_mall_home_banner.jsp"%>