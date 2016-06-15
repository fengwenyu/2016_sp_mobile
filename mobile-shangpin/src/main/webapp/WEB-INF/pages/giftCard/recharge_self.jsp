 <%@ page language="java" contentType="text/html; charset=UTF-8"
	trimDirectiveWhitespaces="true" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/pages/common/include.inc.jsp"%>

<rapid:override name="custum">

	<link href="${cdn:css(pageContext.request)}/styles/shangpin/css/page/giftcard/giftCard_send.css${ver}" rel="stylesheet" />
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
      <p class="eCard_show">
        <img src="${fn:replace(pic,'-10-10','-420-465') }">
        <span></span>
        <a href="${ctx }/giftCard/electronicRecharge?giftCardId=${giftCardId}">立即充值</a> 
      </p>
	 
</rapid:override>

<%@ include file="/WEB-INF/pages/common/another_common_mall_banner.jsp" %> 
