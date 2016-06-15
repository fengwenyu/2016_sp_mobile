<%@ page language="java" contentType="text/html; charset=UTF-8" trimDirectiveWhitespaces="true"
         pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/pages/common/include.inc.jsp"%>
<rapid:override name="custum">

    <title>订单提交</title>
    <link href=${cdn:css(pageContext.request)}/styles/shangpin/css/base.css${ver}" rel="stylesheet" />
    <link href="${cdn:css(pageContext.request)}/styles/shangpin/css/settlement/order_form160425.css${ver}" rel="stylesheet" />
    <link href="${cdn:css(pageContext.request) }/styles/shangpin/css/page/order_address.css${ver}" rel="stylesheet" />
    <link href="${cdn:css(pageContext.request) }/styles/shangpin/css/member.css${ver}" rel="stylesheet" />
    <link href="${cdn:css(pageContext.request) }/styles/shangpin/css/page/order_result.css${ver}" rel="stylesheet" />
    <link href="${cdn:css(pageContext.request)}/styles/shangpin/css/page/payment_normal.css${ver}" rel="stylesheet" />
    <link href="${cdn:css(pageContext.request)}/styles/shangpin/css/page/addr.css${ver}" rel="stylesheet" />
   <%-- <link href="${cdn:css(pageContext.request) }/styles/shangpin/css/page/member.css${ver}" rel="stylesheet" />--%>
<%--    <link href="${cdn:css(pageContext.request) }/styles/shangpin/css/page/order_invoice.css${ver}" rel="stylesheet" />--%>
<%--    <link href="${cdn:css(pageContext.request) }/styles/shangpin/css/page/order_invoice.css${ver}" rel="stylesheet" />--%>
    <script type="text/javascript" charset="utf-8">
        loader = SP.core
                .install("${cdn:js(pageContext.request)}/styles/shangpin/js/jquery.min.js${ver}")
                .using("${cdn:css(pageContext.request)}/styles/shangpin/js/core.js${ver}")
                .using("${cdn:css(pageContext.request)}/styles/shangpin/js/comm.js?${ver}")
                .using("${cdn:css(pageContext.request)}/styles/shangpin/js/settlement/order160425.js${ver}")
                .using("${cdn:js(pageContext.request)}/styles/shangpin/js/j.dialogs.js${ver}")

                .using("${cdn:js(pageContext.request)}/styles/shangpin/js/iscroll.js${ver}")
                .using("${cdn:js(pageContext.request)}/styles/shangpin/js/overseas/validIDCard.js${ver}")
                .using("${cdn:js(pageContext.request)}/styles/shangpin/js/settlement/order_address.js${ver}")
                .using("${cdn:js(pageContext.request)}/styles/shangpin/js/settlement/pay_union.js${ver}")
                .using("${cdn:js(pageContext.request)}/styles/shangpin/js/settlement/invoice.js${ver}")
                .excute();
        /*.using("${cdn:js(pageContext.request)}/styles/shangpin/js/new_coupons.js${ver}")*/
    </script>

</rapid:override>

<rapid:override name="page_title">
</rapid:override>

<rapid:override name="content">
    <%@include file="/WEB-INF/pages/settlement/submit_order160425.jsp" %>
    <%@include file="/WEB-INF/pages/settlement/address_list.jsp" %>
    <%@include file="/WEB-INF/pages/settlement/address_add.jsp" %>
    <%@include file="/WEB-INF/pages/settlement/coupons.jsp" %>
    <%@include file="/WEB-INF/pages/settlement/invoice_info.jsp" %>

    <input type="hidden" name="buyId" value="${cartUnion.buyId}">
    <input type="hidden" name="orderSource" value="${orderSource}">
    <input type="hidden" name="innerUseCouponFlag" value="${not empty cartUnion.domesticProduct.coupon.data?"1":""}">
    <input type="hidden" name="innerUseCouponNo" value="${not empty cartUnion.domesticProduct.coupon.data?cartUnion.domesticProduct.coupon.data:"0"}">
    <input type="hidden" name="outerUseCouponNo" value="${not empty cartUnion.abroadProduct.coupon.data?cartUnion.abroadProduct.coupon.data:"0"}">
    <input type="hidden" name="innerUseCouponBuyId" value="${cartUnion.domesticProduct.buyId}">
    <input type="hidden" name="outerUseCouponBuyId" value="${cartUnion.abroadProduct.buyId}">
    <%--页面临时保存发票信息--%>
    <input type="hidden" name="invoiceflag" value="0">
    <input type="hidden" name="invoicetype" value="1">
    <input type="hidden" name="invoicetitle" value="个人">
    <input type="hidden" name="invoicecontent" value="商品全称">
    <input type="hidden" name="invoiceemail" value="">
    <input type="hidden" name="invoicetel" value="">
    <input type="hidden" name="wx" value="${checkWX}">
</rapid:override>

<rapid:override name="footer">
</rapid:override>

<%@ include file="/WEB-INF/pages/common/another_common.jsp"%>
