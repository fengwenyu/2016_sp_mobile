 <%@ page language="java" contentType="text/html; charset=UTF-8"
	trimDirectiveWhitespaces="true" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/pages/common/include.inc.jsp"%>

<rapid:override name="custum">

	<link href="${cdn:css(pageContext.request)}/styles/shangpin/css/page/giftcard/giftcardDetail.css${ver}" rel="stylesheet" />
	<script src="${cdn:js(pageContext.request)}/styles/shangpin/js/jquery.min.js${ver}"  type="text/javascript" charset="utf-8"></script>
	<script src="${cdn:js(pageContext.request)}/styles/shangpin/js/lazyload.js${ver}"  type="text/javascript" charset="utf-8"></script>
	<script src="${cdn:js(pageContext.request)}/styles/shangpin/js/config.sim.js${ver}"  type="text/javascript" charset="utf-8"></script>
	<script src="${cdn:js(pageContext.request)}/styles/shangpin/js/head_scroll.js${ver}"  type="text/javascript" charset="utf-8"></script>
</rapid:override>
<%-- 页标题 --%>
<rapid:override name="page_title">
	查询充值秘钥
</rapid:override>
<rapid:override name="content">
<div class="pay-success-box">
    	<i><img src="${cdn:js(pageContext.request)}/styles/shangpin/images/giftcard/detail/query_key.png" /></i>
        <h2>${cardPasswd }</h2>
    	<p>充值秘钥是礼品卡充值的唯一凭证，请妥善保管。您可以通过如下2个途径进行充值：</p>
        <p>1、点击”充值于本账户“，您购买的礼品卡将直接充值于您购买的账户中；</p>
        <p>2、将充值秘钥复制后到“个人中心-礼品卡”进行充值。</p>
        <div class="btn-icon clr">
            <a href="javascript:history.back(-1);" class="recharge-btn">返回</a>
            <a href="${ctx }/giftCard/electronicRecharge?giftCardId=${giftCardId}" class="share-btn">充值于本账户</a>
        </div>
</div>
</rapid:override>

<%@ include file="/WEB-INF/pages/common/another_common_mall_banner.jsp" %> 

