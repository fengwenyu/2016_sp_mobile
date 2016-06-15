<%@ page language="java" contentType="text/html; charset=UTF-8" trimDirectiveWhitespaces="true"
    pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/pages/common/include.inc.jsp"%>

<rapid:override name="custum">
	<link href="${cdn:css(pageContext.request)}/styles/shangpin/css/page/overseas/order_form_detail.css${ver}" rel="stylesheet" />
	<script type="text/javascript" charset="utf-8">
			loader = SP.core
				.install("${cdn:js(pageContext.request)}/styles/shangpin/js/jquery.min.js${ver}")
				.using("${cdn:js(pageContext.request)}/styles/shangpin/js/lazyload.js${ver}")
				.excute()
				.using("${cdn:js(pageContext.request)}/styles/shangpin/js/config.sim.js${ver}")
				.using("${cdn:js(pageContext.request)}/styles/shangpin/js/comm.js${ver}")
				.using("${cdn:js(pageContext.request)}/styles/shangpin/js/order/finishOrder.js${ver}")
				.using("${cdn:js(pageContext.request)}/styles/shangpin/js/order/cancelOrder.js${ver}")
				.excute();
	</script>
</rapid:override > 


			
<%-- 浏览器标题 --%>
<rapid:override name="title">
	订单详情
</rapid:override>

<%-- 页标题 --%>
<rapid:override name="page_title">
	订单详情
</rapid:override>


<rapid:override name="content">
<input type="hidden" id="statusType" name="statusType" value="${statusType}">
 <c:set value="false" var="isConfirmGoods"  scope="page"/>
	<div class="paymet_block">
            <fieldset>
                <legend>订单详情</legend>
                <p class="selected"><a href="javascript:;">订单编号：${orderItem.mainorderno}  <br>下单时间：${orderItem.date } </a></p>
                <p class="selected"><a href="javascript:;"><em>${order.name }</em><i class="phone">${order.tel }</i><br>${cardID }<br>${order.province }${order.city }${order.area }${order.town }${order.addr }</a></p>
                <p class="selected"><a href="javascript:;" id="select_time">配送方式<span style="float:right;">${order.express}</span></a></p>
                
                <legend>您在尚品香港购买的商品</legend>
                <c:forEach items="${order.orderdetail}" var="detail" varStatus="st">
	                <p class="pord_info">
	                	<a href="javascript:;" class="clr">
	                    <img src="${cdn:pic(pageContext.request)}/styles/shangpin/images/e.gif" lazy="${detail.pic }" width="56" height="67">
	                    <ins>
	                        <i>${detail.productname}</i>
	                        <c:if test="${detail.firstpropname} != '' "><em>${detail.firstpropname} : ${detail.firstpropvalue}</em></c:if>
      					<c:if test="${detail.secondpropname} != '' "><em>${detail.secondpropname} : ${detail.secondpropvalue}</em></c:if>
      					<br>
      					
      					   <c:if test="${detail.type=='0'}" ><em>${detail.firstpropname} : ${detail.firstpropvalue}</em></c:if>
      						<c:if test="${detail.type=='0'}"><em>&nbsp;${detail.secondpropname} : ${detail.secondpropvalue}</em></c:if>
      						<br>
      				   		 <em>价格：&yen;<fmt:formatNumber type="number" value="${detail.amount}" maxFractionDigits="0"/></em>
	                        <em>数量：${detail.count }</em>
	                    </ins>
	                    </a>
	                </p>
				</c:forEach>
              <p class="total">
              <c:set value="${fn:substringBefore(orderItem.onlineamount,'.') }" var="abs"  scope="page"/>
	              <c:if test="${abs!=orderItem.totalamount }">
	              	  价格：<i>&nbsp;&nbsp;&yen;<fmt:formatNumber type="number" value="${orderItem.totalamount}" maxFractionDigits="0"/></i>
	            	<br />
	             </c:if>
                         <c:if test="${orderItem.freight != '' && orderItem.freight =='0'  }">
        				 运费：<i>- &yen;<fmt:formatNumber type="number" value="${orderItem.freight}" maxFractionDigits="0"/></i> 
		       		 	</c:if>
                    <em>应付金额：<i>&nbsp;&nbsp;&yen;<fmt:formatNumber type="number" value="${orderItem.onlineamount}" maxFractionDigits="0"/></i></em>
                </p>
                <c:if test="${orderItem.canpay == true }">
	                <div class="payment_submit">
	                  <div class="btn-icon clr">
	                   <a href="javascript:cancelOrder('${orderItem.mainorderno}');" class="cancel-btn" id="${orderItem.mainorderno }">取消订单</a>
                        <a href="${ctx}/overseas/pay/continue?orderId=${orderItem.mainorderno}" class="payment-btn">立即支付</a>
                    </div>
	               </div>
                </c:if>
             
		   		<c:if test="${orderItem.status==16 && orderItem.paytypeid != 2 }">
		   		  <c:set value="true" var="isConfirmGoods"  scope="page"/>
			   		<div class="payment_submit">
			         <a href="javascript:finishOrder('${orderItem.mainorderno}');" class="payment_btn">确认收货</a>
	       		 </div>
       			 </c:if>
       			 <c:if test="${! isConfirmGoods}">
					<c:if test="${orderItem.status==12 ||orderItem.status==13 ||orderItem.status==14||orderItem.status==15||orderItem.status==16||orderItem.status==18||orderItem.status==98 || orderItem.status==99 }">
						<div class="payment_submit">
        				<a href="${ctx}/logistice/list?orderId=${orderItem.mainorderno }&postArea=1" class="payment_btn">订单跟踪</a>
         				</div>
   					</c:if>
   				</c:if>
            </fieldset>
    </div>
</rapid:override> 
<%@ include file="/WEB-INF/pages/common/common.jsp" %> 