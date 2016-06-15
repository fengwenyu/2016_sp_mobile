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
    	<i><img src="${cdn:js(pageContext.request)}/styles/shangpin/images/giftcard/detail/recharge_fail_icon.png" /></i>
        <h2>系统出错了</h2>
    	<p>对不起，系统繁忙，暂时无法获取您的礼品卡秘钥，请耐心等待5分钟。5分钟后，您可在“我的尚品－礼品卡”查看礼品卡秘钥</p>
        <a href="javascript:history.back(-1);" class="one-btn">返回</a>
    </div>

</rapid:override>

<%@ include file="/WEB-INF/pages/common/another_common_mall_banner.jsp" %> 

