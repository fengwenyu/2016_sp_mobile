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
	充值失败
</rapid:override>
<rapid:override name="content">
     <div class="pay-success-box">
    	<i><img src="${cdn:js(pageContext.request)}/styles/shangpin/images/giftcard/detail/recharge_fail_icon.png" /></i>
        <h2>充值失败</h2>
    	<p>系统出错了,您的礼品卡没有立即充值到您的账户中,请耐心等待5分钟。</p>
        <p>如5分钟后礼品卡账户余额未增加,请重新操作充值,或联系客户4006-900-900</p>
        <div class="btn-icon clr">
            <a href="${ctx }/giftCard/recordList" class="recharge-btn">查看账户余额</a>
            <a href="${ctx }/giftCard/electronicRecharge?orderId=${orderId}&passwd=passwd" class="share-btn">重新充值</a>
        </div>
    </div>

</rapid:override>

<%@ include file="/WEB-INF/pages/common/another_common_mall_banner.jsp" %> 

