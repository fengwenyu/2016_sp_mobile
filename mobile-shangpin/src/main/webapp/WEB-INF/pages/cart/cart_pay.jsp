<%@ page language="java" contentType="text/html; charset=UTF-8" trimDirectiveWhitespaces="true"
    pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/pages/common/include.inc.jsp"%>

<rapid:override name="custum">
	<link href="${cdn:css(pageContext.request)}/styles/shangpin/css/page/payment_normal.css${ver}" rel="stylesheet" />
	<link href="${cdn:css(pageContext.request) }/styles/shangpin/css/page/member.css${ver}" rel="stylesheet" />
	<link href="${cdn:css(pageContext.request) }/styles/shangpin/css/page/order_address.css${ver}" rel="stylesheet" />
	<link href="${cdn:css(pageContext.request) }/styles/shangpin/css/page/order_invoice.css${ver}" rel="stylesheet" />
	<link href="${cdn:css(pageContext.request) }/styles/shangpin/css/page/order_result.css${ver}" rel="stylesheet" />
	<script type="text/javascript" charset="utf-8">
		loader = SP.core
		.install("${cdn:js(pageContext.request)}/styles/shangpin/js/jquery.min.js${ver}")
		.using("${cdn:js(pageContext.request)}/styles/shangpin/js/comm.js${ver}")
		.using("${cdn:js(pageContext.request)}/styles/shangpin/js/iscroll.js${ver}")
		.using("${cdn:js(pageContext.request)}/styles/shangpin/js/payment_normal.js${ver}")
		.using("${cdn:js(pageContext.request)}/styles/shangpin/js/j.dialogs.js${ver}")
		.using("${cdn:js(pageContext.request)}/styles/shangpin/js/order_address.js${ver}")
		.using("${cdn:js(pageContext.request)}/styles/shangpin/js/new_coupons.js${ver}")
		.using("${cdn:js(pageContext.request)}/styles/shangpin/js/invoice.js${ver}")
		.excute();
	</script>
</rapid:override>


<rapid:override name="page_title">
	
</rapid:override>
<rapid:override name="content">
	<%@include file="/WEB-INF/pages/cart/order_detail.jsp" %>
	<%@include file="/WEB-INF/pages/cart/address_list.jsp" %>
	<%@include file="/WEB-INF/pages/cart/address_add.jsp" %>
	<%@include file="/WEB-INF/pages/cart/coupons.jsp" %>
	<%@include file="/WEB-INF/pages/cart/invoice.jsp" %>
</rapid:override>

		
<rapid:override name="footer">

</rapid:override>
<%@ include file="/WEB-INF/pages/common/another_common.jsp" %> 