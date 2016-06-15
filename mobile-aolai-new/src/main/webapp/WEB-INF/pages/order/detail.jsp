<%@ page language="java" contentType="text/html; charset=UTF-8" trimDirectiveWhitespaces="true"
    pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/pages/common/include.inc.jsp"%>
<rapid:override name="custum">
	<link href="${cdn:css(pageContext.request)}/styles/shangpin/css/page/account.css" rel="stylesheet" />
	<script type="text/javascript" charset="utf-8">
			loader = SP.core
				.install("${cdn:js(pageContext.request)}/styles/shangpin/js/zepto.min.js${ver}")
				.using("${cdn:js(pageContext.request)}/styles/shangpin/js/lazyload.js${ver}")
				.using("${cdn:js(pageContext.request)}/styles/shangpin/js/ajaxSettings.js${ver}")
				.using("${cdn:js(pageContext.request)}/styles/shangpin/js/config.sim.js${ver}")
				.using("${cdn:js(pageContext.request)}/styles/shangpin/js/comm.js${ver}")
				.using("${cdn:js(pageContext.request)}/styles/shangpin/js/order/order_list.js${ver}")
				.excute();
	</script>
</rapid:override >
<rapid:override name="content">
	<c:import url="/nav?navId=9"></c:import>
<div class="alContent">
	<section class="alOrder_info">
	    <h3>订单编号：${orderItem.mainorderno }
	    	<c:if test="${orderItem.cancancel}">
	    	<a href="javascript:cancelOrder();" class="alOrder_cancelBtn" id="${orderItem.mainorderno }">[取消订单]</a>
	    	</c:if>
	    </h3>
		<c:if test="${orderItem.order!=null }">
		    <c:forEach var="order" items="${orderItem.order }" varStatus="st">
		    	<c:if test="${order.orderdetail!=null }">
					 <c:forEach var="detail" items="${order.orderdetail }" varStatus="sts">
					     <div style="display:bolck; padding:8px 10px;">
					      <span><img src="${cdn:pic(pageContext.request)}/styles/shangpin/images/e.gif" lazy="${detail.pic }" width="70" height="70"></span>
					      <p><i>${detail.productname }</i>
					     	${detail.firstpropname }:${detail.firstpropvalue }<br />
			      		 	${detail.secondpropname }:${detail.secondpropvalue }<br />
			      		 	<em>&yen;<fmt:formatNumber value="${detail.amount }"/> x ${detail.count }</em>
					      </p>
					      </div>
		    		</c:forEach>
		    	</c:if>
		    </c:forEach>
		</c:if>
  </section>

  <c:if test="${orderItem.canpay}">
    <a href="<c:url value='/cart/submit/pay?orderId=${orderItem.mainorderno }'/>" class="alOrder_buyBtn orderInfo_buyBtn">确定并付款</a>
  </c:if>
  <c:if test="${orderItem.order!=null }">
		    <c:forEach var="order" items="${orderItem.order }" varStatus="st">
		    <c:if test="${st.index==0}">
 <section class="alOrder_prodInfo">
    <h2>收货信息</h2>
    <dl>
      <dt>收货人：</dt><dd>${order.name }</dd>
    </dl>
    <dl>
      <dt>地　址：</dt><dd>${order.province }${order.city }${order.area }${order.addr }</dd>
    </dl>
    <dl>
      <dt>手机号：</dt><dd>${order.tel }</dd>
    </dl>
  </section>					
 <section class="alOrder_prodInfo">
    <h2>订单信息</h2>
  		 <dl>
		      <dd>
				        订单状态：${orderItem.statusname }<br />
				        下单时间：${orderItem.date }<br />
				        收货日期：${order.express }<br />
				        发票信息：${order.title }<br />
				        发票内容：${order.invoice }<br />
				        支付方式：${orderItem.paytypename }<br />
				        <span>订单总金额：<em>&yen;<fmt:formatNumber value="${orderItem.totalamount}"/></em></span><br />
				        <span>优惠金额：<em>&yen;<fmt:formatNumber value="${orderItem.discountamount}"/></em></span><br />
				         <c:if test="${!(empty orderItem.freight==null)} ">
				        	<span>运费：<em>&yen;${orderItem.freight}</em></span><br />
				        </c:if>
				        <c:if test="orderItem.giftcardamount >0 ">
				        <span>礼品卡金额：<em>&yen;<fmt:formatNumber value="${orderItem.giftcardamount}"/></em></span><br />
				      </c:if>
				        <span>应付金额：<em>&yen;<fmt:formatNumber value="${orderItem.onlineamount}"/></em></span><br />
				        <c:if test="${orderItem.canpay}">
				          <a href="<c:url value='/cart/submit/pay?orderId=${orderItem.mainorderno }'/>" class="alOrder_buyBtn prodInfo_buyBtn">确定并付款</a>
				        </c:if>
		      </dd>
		</dl>
  </section>
  </c:if>
	 </c:forEach>
 </c:if>
  
</div>
</rapid:override>

<%@ include file="/WEB-INF/pages/common/base.jsp" %> 