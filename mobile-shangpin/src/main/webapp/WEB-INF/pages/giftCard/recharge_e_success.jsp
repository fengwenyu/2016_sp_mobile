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
	礼品卡充值
</rapid:override>
<rapid:override name="content">
    <div class="pay-success-box">
    	<i><img src="${cdn:js(pageContext.request)}/styles/shangpin/images/giftcard/detail/recharge_success_icon.png" /></i>
        <h2>充值成功</h2>
    	<p>您的面值￥${rechargeMoney }元的礼品卡已经充值成功,您可在“我的尚品-礼品卡”查看账户余额。</p>
        <div class="btn-icon clr">
            <a href="${ctx }/giftCard/recordList" class="recharge-btn">查看账户余额</a>
            <a href="${ctx }/index" class="share-btn">立即购物</a>
        </div>
    </div>	 
</rapid:override>

<%@ include file="/WEB-INF/pages/common/another_common_mall_banner.jsp" %> 

