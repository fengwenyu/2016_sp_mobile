<%@ page language="java" contentType="text/html; charset=UTF-8"
	trimDirectiveWhitespaces="true" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/pages/common/include.inc.jsp"%>

<rapid:override name="custum">
<style>
.main_box img{ width:100%}
</style>
<script type="text/javascript" charset="utf-8">
	loader = SP.core
	.install("${cdn:js(pageContext.request)}/styles/shangpin/js/jquery.min.js${ver}")
	.using("${cdn:js(pageContext.request)}/styles/shangpin/js/lazyload.js${ver}")
	.using("${cdn:js(pageContext.request)}/styles/shangpin/js/xmas.dialogs.js${ver}")
	.excute()
	.using("${cdn:js(pageContext.request)}/styles/shangpin/js/config.sim.js${ver}")
	.excute()
    .using("${cdn:js(pageContext.request)}/styles/shangpin/js/meet/giftPackage.js${ver}")
    .excute(function(){
		isHome(false);
	});
	</script>
<link href="${cdn:css(pageContext.request)}/styles/shangpin/css/page/20150520GiftPackage.css${ver}" rel="stylesheet" />
</rapid:override>

<rapid:override name="header">
	<c:if test="${!checkWX&&!checkAPP}">
		<%@ include file="/WEB-INF/pages/common/header.jsp"%>
	</c:if>
</rapid:override>
<rapid:override name="content">
	<body id="bodyId">

<div class="alContent">
	<h1 class="title-top">
    	<i class="try-tag"><img src="${cdn:pic(pageContext.request)}/styles/shangpin/images/giftPackage/try_tag.png" /></i>
    	<img src="${cdn:pic(pageContext.request)}/styles/shangpin/images/giftPackage/top_title.png">
    </h1>
    <div class="turntable-top"><img src="${cdn:pic(pageContext.request)}/styles/shangpin/images/giftPackage/turntable_bg_top.png" /></div>
	<div class="turntable">
    	<p>
        	<strong>1</strong>即日起至5月25日，免费领取￥520礼券包； 
		</p>
        <p>
        	<strong>2</strong>获得的礼券包将自动充入您的个人中心，您可点击“我的优惠券”查看；
        </p>
        <p>
        	<strong>3</strong>礼券包中含五张优惠券，其中包括壹张200元，贰张100元及贰张60元优惠券；
        </p>
        <p>
          <strong>4</strong>礼券包限<span class="red">5月20日至5月25日</span>使用，<span class="red">单笔订单满520元减60元，满1000元减100元，满2000元减200元，</span>每张订单限用一张优惠券，本券全场通用（海外购等特殊商品除外）；
        </p>
        <p>
          <strong>5</strong>优惠券和优惠码限选<br>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;其一，优惠券的使用<br>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;规则详见<span class="red">“帮助中<br>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;心-优惠券使<br>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;用”</span>。
        </p>
        
        <a href="javascript:;" class="receive-btn"><img src="${cdn:pic(pageContext.request)}/styles/shangpin/images/giftPackage/receive_btn.png" /></a>
        <em class="gift-bg"><img src="${cdn:pic(pageContext.request)}/styles/shangpin/images/giftPackage/gift_bg.png" /></em>
    </div>
    <div class="con">
        <a href="${ctx }/meet/212" class="back_main"><i><img src="${cdn:pic(pageContext.request)}/styles/shangpin/images/giftPackage/back_icon.png" /></i>返回主会场</a>
    </div>
</div>

</body>
	
</rapid:override>

<rapid:override name="footer">
</rapid:override>
<%@ include file="/WEB-INF/pages/common/base_mall_banner.jsp" %> 
