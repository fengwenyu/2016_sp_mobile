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
	.excute()
	.using("${cdn:js(pageContext.request)}/styles/shangpin/js/config.sim.js${ver}")
	.excute()
	.using("${cdn:js(pageContext.request)}/styles/shangpin/js/xmas.dialogs.js${ver}")
    .using("${cdn:js(pageContext.request)}/styles/shangpin/js/giftcard/giftcardDetail.js${ver}")
    .using("${cdn:js(pageContext.request)}/styles/shangpin/js/product/commonProductDetail.js${ver}")
    .excute();
	</script>
<link href="${cdn:css(pageContext.request)}/styles/shangpin/css/page/giftcard/detail.css${ver}" rel="stylesheet" />
<link href="${cdn:css(pageContext.request)}/styles/shangpin/css/page/giftcard/giftcardDetail.css${ver}" rel="stylesheet" />
</rapid:override>

<rapid:override name="title">
    	商品详情
</rapid:override>
<rapid:override name="downloadAppShowBottom">
</rapid:override>
<rapid:override name="page_title">
	 商品详情
</rapid:override>
<rapid:override name="appLayer">
</rapid:override>
<rapid:override name="content">
<input id="cardType" name="cardType" type="hidden" value="1"/>
<input id="_iswx" name="_iswx" type="hidden" value="${checkWX }"/>
<input type="hidden" name="_shareUrl"  id="_shareUrl"  value="${productDetail.share.url}"/>
<input type="hidden" name="_mainTitle"  id="_mainTitle"  value="${productDetail.share.title}${productDetail.share.desc}"/>
<input type="hidden" name="_mainDesc"  id="_mainDesc"  value="${productDetail.share.title}"/>
<input type="hidden" name="_mainImgUrl" id="_mainImgUrl"  value="${fn:replace(productDetail.share.pic,'-10-10','-160-218') }"/>
<c:import url="basic.jsp"></c:import>   
<c:import url="ele_info.jsp"></c:import>
<c:import url="buy.jsp"></c:import>

</rapid:override>
<%@ include file="/WEB-INF/pages/common/another_common_mall_banner.jsp" %>
