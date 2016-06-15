<%@ page language="java" contentType="text/html; charset=UTF-8" trimDirectiveWhitespaces="true"
    pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/pages/common/include.inc.jsp"%>

<rapid:override name="custum">
	<link href="${cdn:css(pageContext.request)}/styles/shangpin/css/page/index.css${ver}" rel="stylesheet" />
	<link href="${cdn:css(pageContext.request)}/styles/shangpin/css/page/login.css${ver}" rel="stylesheet" />
	<link href="${cdn:css(pageContext.request)}/styles/shangpin/css/page/payment_normal.css${ver}" rel="stylesheet" />
	<link href="${cdn:css(pageContext.request) }/styles/shangpin/css/page/member.css${ver}" rel="stylesheet" />
	<link href="${cdn:css(pageContext.request) }/styles/shangpin/css/page/order_address.css${ver}" rel="stylesheet" />
	<link href="${cdn:css(pageContext.request) }/styles/shangpin/css/page/order_invoice.css${ver}" rel="stylesheet" />
	<link href="${cdn:css(pageContext.request) }/styles/shangpin/css/page/order_result.css${ver}" rel="stylesheet" />
	<script type="text/javascript" charset="utf-8">
			loader = SP.core
				.install("${cdn:js(pageContext.request)}/styles/shangpin/js/zepto.1.1.3.js${ver}")
				.using("${cdn:js(pageContext.request)}/styles/shangpin/js/lazyload.js${ver}")
				.excute()
				.using("${cdn:js(pageContext.request)}/styles/shangpin/js/config.sim.js${ver}")
				.using("${cdn:js(pageContext.request)}/styles/shangpin/js/css3.js${ver}")
				.using("${cdn:js(pageContext.request)}/styles/shangpin/js/swiper.min.js${ver}" )
				.excute()
				.using("${cdn:js(pageContext.request)}/styles/shangpin/js/comm.js${ver}")
				.using("${cdn:js(pageContext.request)}/styles/shangpin/js/downloadAppShow.js${ver}")
				.using("${cdn:js(pageContext.request)}/styles/shangpin/js/fashion_run.js${ver}")
				
	</script>
</rapid:override >
<rapid:override name="appLayer">
</rapid:override>
<rapid:override name="content">
	<form id="fashion_form"  method="post">
		<input type="hidden" id="packId" name="packId" value="1"/>
		<input type="hidden" id="name" name="name" value="李灵"/>
		<input type="hidden" id="sex" name="sex" value="女"/>
		<input type="hidden" id="birthday" name="birthday" value="0810"/>
		<input type="hidden" id="phone" name="phone" value="18810865659"/>
		<input type="hidden" id="email" name="email" value="630935451@qq.com"/>
	<%--	<input type="hidden" id="brandNo" name="brandNo" value="${searchConditions.brandNo}"/>--%>
		<input type="hidden" id="province" name="province" value="河南"/>
		
		<input type="hidden" id="city" name="city" value="郑州"/>
		<input type="hidden" id="addr" name="addr" value=""/>
		<input type="hidden" id="pid" name="pid" value="123456123307456565"/>
		<input type="hidden" id="contacts" name="contacts" value="李秀"/>
		<input type="hidden" id="contactsPhone" name="contactsPhone" value="18710568569"/>
		<input type="hidden" id="size" name="size" value="l"/>
		<input type="hidden" id="zipCode" name="zipCode" value=""/>
		<input type="hidden" id="logisticsName" name="logisticsName" value=""/>
		<input type="hidden" id="logisticsNo" name="logisticsNo" value=""/>
	</form>
	 <fieldset>
	 <a href="javascript:add()" class="login_btn">报名</a>
	 </fieldset>
	  <fieldset>
	  <input type="hidden" id="orderId" name="orderId" value="20150702104730238005"/>
	   <input type="hidden" id="packId" name="packId" value="1"/>
	    <input type="hidden" id="payType" name="payType" value="3"/>
	 <a href="javascript:pay()" class="payment_btn">支付</a>
	 </fieldset>
	 
	 <form id="apply_form"  method="post">
		<input type="hidden" id="id" name="id" value="7"/>
	
	<%--	<input type="hidden" id="brandNo" name="brandNo" value="${searchConditions.brandNo}"/>--%>
		<input type="hidden" id="province" name="province" value="河南"/>
		
		<input type="hidden" id="city" name="city" value="郑州"/>
		<input type="hidden" id="addr" name="addr" value="周口市鹿邑县"/>
		<input type="hidden" id="pid" name="pid" value="123456123307456565"/>
	
		<input type="hidden" id="size" name="size" value="l"/>
		<input type="hidden" id="zipCode" name="zipCode" value="111111"/>
		<input type="hidden" id="logisticsName" name="logisticsName" value="顺丰物流"/>
		<input type="hidden" id="logisticsNo" name="logisticsNo" value=""/>
	</form>
	 <fieldset>
	 <a href="javascript:apply()" class="login_btn">申请领取</a>
	 </fieldset>
	 <%-- 头图 --%> 
</rapid:override>


<%@ include file="/WEB-INF/pages/common/base_index_banner.jsp" %> 