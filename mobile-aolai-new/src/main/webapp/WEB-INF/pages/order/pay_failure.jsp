<%@ page language="java" contentType="text/html; charset=UTF-8" trimDirectiveWhitespaces="true"
    pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/pages/common/include.inc.jsp"%>
<rapid:override name="custum">
	<link href="${cdn:css(pageContext.request)}/styles/shangpin/css/page/order.css" rel="stylesheet" />
	<script type="text/javascript" charset="utf-8">
			loader = SP.core
				.install("${cdn:js(pageContext.request)}/styles/shangpin/js/zepto.min.js${ver}")
				.using("${cdn:js(pageContext.request)}/styles/shangpin/js/comm.js${ver}")
				.excute();
	</script>
</rapid:override >
<rapid:override name="content">
	<c:import url="/nav?navId=17"></c:import>
	<%-- <nav class="alNav">
	  <ul>
	    <li><a href="${ctx}">首页</a></li>
	    <li><a href="">我的账户</a></li>
	    <li><a href="">订单管理</a></li>
	    <li>支付</li>
	  </ul>
   </nav> --%>
<div class="alContent">
	<div class="alOrder_success">
		<h2><img src="${ctx}/styles/shangpin/images/fail.png" width="36" height="36">您的订单支付未成功</h2>
		<a href="${ctx}/cart/submit/pay?orderId=${orderId}" class="alOrder_buyBtn">继续支付</a>
        <a href="${ctx}/user/order/detail?orderId=${orderId}" class="alOrder_buyBtn">查看订单</a>
	</div>
	
	<ul class="alOrder_done">
	    <li>订 单 号：${orderId}</li>
	    <li>配送方式：快递发送</li>
    </ul>
	
</div>
</rapid:override>

<%@ include file="/WEB-INF/pages/common/base.jsp" %> 