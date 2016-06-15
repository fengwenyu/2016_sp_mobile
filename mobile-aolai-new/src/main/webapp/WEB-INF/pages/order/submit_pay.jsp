<%@ page language="java" contentType="text/html; charset=UTF-8" trimDirectiveWhitespaces="true"
    pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/pages/common/include.inc.jsp"%>
<rapid:override name="custum">
	<link href="${cdn:css(pageContext.request)}/styles/shangpin/css/page/order.css" rel="stylesheet" />
	
	<script src="${cdn:js(pageContext.request)}/styles/shangpin/js/jquery.min.js" type="text/javascript" charset="utf-8"></script>
	<script src="${cdn:js(pageContext.request)}/styles/shangpin/js/pay/pay.js" type="text/javascript" charset="utf-8"></script>
	<script type="text/javascript" charset="utf-8">
			loader = SP.core
				.install("${cdn:js(pageContext.request)}/styles/shangpin/js/zepto.min.js${ver}")
				.using("${cdn:js(pageContext.request)}/styles/shangpin/js/comm.js${ver}")
				.using("${cdn:js(pageContext.request)}/styles/shangpin/js/lazyload.js${ver}")
				.excute();
			    .using("${cdn:js(pageContext.request)}/styles/shangpin/js/config.sim.js${ver}")
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
	<div class="pay_btn_box">
		<c:forEach items="${payList}" var="payVO">
			<%--银联 --%>
			<c:if test="${payVO.id == '19'}">
				<div id="atag" class="pay_btn_yinlian" >
					<a href="javascript:chagePayMode(19,49);"></a>
				</div>
			</c:if>
			<%--支付宝 --%>
			<c:if test="${payVO.id == '20'}">
				<div class="pay_btn_alipay">
					<a href="javascript:chagePayMode(20,37);"></a>
				</div>
			</c:if>
			<%--货到付款：判断是否支持货到付款 --%>
			<c:if test="${payVO.id == '2'}">
				<c:if test="${cod == 'true' || cod}">
				<div class="pay_btn_hdfk">
					<a href="javascript:chagePayMode(2,41);"></a>
				</div>
				</c:if>
			</c:if>
		</c:forEach>
	</div>
	<form action="">
		<input type="hidden" id="orderNum" value="${orderId}">
		<input type="hidden" id="payMoney" value="${amount}">
	</form>
</div>
</rapid:override>

<%@ include file="/WEB-INF/pages/common/base.jsp" %> 