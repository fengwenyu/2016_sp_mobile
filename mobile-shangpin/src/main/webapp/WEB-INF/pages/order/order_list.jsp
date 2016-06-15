<%@ page language="java" contentType="text/html; charset=UTF-8" trimDirectiveWhitespaces="true"
    pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/pages/common/include.inc.jsp"%>
<rapid:override name="custum">
		<link href="${cdn:css(pageContext.request)}/styles/shangpin/css/page/order_form150422.css${ver}" rel="stylesheet" />
		<link href="${cdn:css(pageContext.request)}/styles/shangpin/css/page/new_order_member.css${ver}" rel="stylesheet" />
		
		<script type="text/javascript" charset="utf-8">
			var ver = Math.random();
				loader = SP.core
					.install("${cdn:js(pageContext.request)}/styles/shangpin/js/jquery.min.js${ver}")
					.using("${cdn:js(pageContext.request)}/styles/shangpin/js/comm.js${ver}")
					.using("${cdn:js(pageContext.request)}/styles/shangpin/js/lazyload.js${ver}")
					.using("${cdn:js(pageContext.request)}/styles/shangpin/js/order/orderList.js${ver}")
					.using("${cdn:js(pageContext.request)}/styles/shangpin/js/order/cancelOrder.js${ver}")
					.using("${cdn:js(pageContext.request)}/styles/shangpin/js/order/finishOrder.js${ver}")
					.using("${cdn:js(pageContext.request)}/styles/shangpin/js/order/leftTime.js${ver}")
					.excute();
		</script>
</rapid:override>

<%-- 浏览器标题 --%>
<rapid:override name="title">
	<c:if test="${statusType=='1'}">全部订单</c:if>
	<c:if test="${statusType=='2'}">待支付</c:if>
	<c:if test="${statusType=='3'}">待收货</c:if>
	<c:if test="${statusType=='4'}">待发货</c:if>
</rapid:override>

<%-- 页标题 --%>
<rapid:override name="page_title">
	<c:if test="${statusType=='1'}">全部订单</c:if>
	<c:if test="${statusType=='2'}">待支付</c:if>
	<c:if test="${statusType=='3'}">待收货</c:if>
	<c:if test="${statusType=='4'}">待发货</c:if>
</rapid:override>
<rapid:override name="downloadAppShowHead">

</rapid:override>

<rapid:override name="content">
	<input type="hidden" id="statusType" name="statusType" value="${statusType}">
	<input type="hidden" id="currentTimes" name="currentTimes" value="${currentTimes}">
 	 <c:choose>
	   <c:when test="${result.list != null }">
	   	<c:set value="false" var="isEcard"  scope="page"/>
		<div class="paymet_block">
      	  <form>
      	  <div class="order-prompt"><i><img src="${cdn:js(pageContext.request)}/styles/shangpin/images/order/prompt_img.png" width="26" height="26"></i><span>提示：${result.noticeInfo }</span></div>            
            <c:forEach items="${result.list}" var="mainOrder"  varStatus="i" >
	            <fieldset class="order-box">	                
	                <p class="selected order-type"><a href="#">${mainOrder.orderTypeDesc }<time id="setTime">${mainOrder.date }</time></a></p>
	                <c:forEach items="${mainOrder.orderList}" var="order" varStatus="status" >
	                <p class="selected"><a href="#"><strong style="color:#888">
	                <c:choose>
	                	<c:when test="${order.isSplitOrder!='1'}">
	                		 主订单：
	                	</c:when>
	                	<c:otherwise>
	                		<c:choose>
		                		<c:when test="${fn:length(mainOrder.orderList) > 1}">
		                		子订单${status.index+1 }：
		                		</c:when>
		                		<c:otherwise>子订单：</c:otherwise>
	                		</c:choose>
	               	 		
	                	</c:otherwise>
	                </c:choose>
	               		</strong>${order.orderId }  <time style="color:#c62026" >${order.statusName }</time></a></p>
	                <!--<h2>您在尚品香港购买的商品</h2>-->	                
		                <c:forEach items="${order.detail}" var="varDetail">
		                	<p class="pord_info">
		                	<a href="<c:url value='/order/detail?isSplitOrder=${order.isSplitOrder}&orderId=${order.orderId}&mainOrderId=${mainOrder.mainOrderId}'/>"  class="clr" >
		               	 	
		                    <img src="${varDetail.pic }" width="56" height="67">
		                    <ins>
		                        <i>
		                        <c:if test="${mainOrder.orderType eq '2'&& varDetail.countryPic !='' }">
			                        <span><img src="${varDetail.countryPic }" width="20" height="13"></span>
		                        </c:if>
		                           <c:choose>
		                        	<c:when test="${varDetail.giftType eq '0'}">
		                        	  ${varDetail.brandNameEN }<br />${varDetail.name }</i>
		                        	</c:when>
		                        	<c:otherwise>
		                        	  ${varDetail.name }</i>
		                        	</c:otherwise>
		                        </c:choose>
		                      
		                        <c:if test="${varDetail.giftType=='0'}">
			                        <c:forEach items="${varDetail.attribute }" var="attr">
			                        	<c:if test="${attr.value !=null&&attr.value !='' }">
				                        	 <em>${attr.name }：${attr.value }</em>			
			                        	</c:if>
				                                             	                        
			                        </c:forEach>
		                        </c:if>
		                        <br />
		                        <em>${varDetail.priceTitle}：&yen;${varDetail.price }</em>
                        		<em>${varDetail.quantityTitle}：${varDetail.quantity }</em>
		                    </ins>
		                    </a>
		                </p>		                
		              </c:forEach>
	                 
	                <p class="pay-model-p"><a>支付方式：<strong>${mainOrder.payment.desc }</strong></a><span>支付金额：<strong>￥${order.orderAmount }</strong></span></p>
					<c:set var="btnflag" value="0" scope="page"/>
	                    
              		<c:if test="${'1' eq order.canLogistics  }">
	              		<c:if test="${btnflag eq '0'}">
	              			<div class="btn-icon clr">	 
	              			<c:set var="btnflag" value="1" scope="page"/>
	              		</c:if>   
              			<a href="${ctx}/logistice/list?orderId=${order.orderId }&postArea=${order.detail[0].postArea}" class="cur">订单跟踪</a>
              		</c:if>
	               	<c:set var="toPayUrl" value="${ctx}/order/pay/normal?orderId=${mainOrder.mainOrderId }" scope="page"/>
	                <c:if test="${mainOrder.orderType=='2' && mainOrder.payment.mainPayCode!='20' && mainOrder.payment.mainPayCode!='19' && mainOrder.payment.mainPayCode!='27'}">
	               	 <c:set var="toPayUrl" value="${ctx}/overseas/pay/continue?orderId=${mainOrder.mainOrderId }" scope="page"/>
	                </c:if>
	                 
              	
              		<c:if test="${'1' eq mainOrder.canPay}">
              			<c:if test="${btnflag eq '0'}">
              			<div class="btn-icon clr">	 
              			<c:set var="btnflag" value="1" scope="page"/>
              			</c:if>   
                		<a href="${toPayUrl }" class="red">立即支付 
                		<c:if test="${mainOrder.status!='10'}">
                			<em id="leftTime" leftTime=""></em>
                		</c:if>
                		</a>
                    </c:if>
              		<c:if test="${'1' eq order.canConfirm}">
              			<c:if test="${btnflag eq '0'}">
              				<div class="btn-icon clr">	 
              				<c:set var="btnflag" value="1" scope="page"/>
              			</c:if>   
              		        <a href="javascript:finishOrder('${order.orderId }');" class="red">确认收货</a>
                    </c:if>
                     <c:if test="${'1' eq mainOrder.canCancel}">
                     	<c:if test="${btnflag eq '0'}">
              				<div class="btn-icon clr">	 
              				<c:set var="btnflag" value="1" scope="page"/>
              			</c:if>   
                    	<a href="javascript:cancelOrder('${mainOrder.mainOrderId }','${mainOrder.orderType }');" id="${order.orderId}" class="cancel-order-btn">取消订单</a>
                    </c:if>  
                    	<c:if test="${btnflag eq '1'}">
              			 </div>
              			</c:if>     
                
                  </c:forEach>
	            </fieldset>
	        
            </c:forEach>            
        </form>
    </div>
     <%--為1顯示查看更多按鈕 --%>
    		 <c:if test="${result.haveMore eq '1'}">
    		 	<a id="haveMore" class="payment_btn moreButton" href="javascript:getOrderList();" pageIndex="${pageIndex}" statusType="${statusType}">点击查看更多</a>
    		 </c:if>
	  		<div class="alList_moreBtn loading" style="display: none; margin-top: 100px;" align="center"><img alt="" src="${cdn:pic(pageContext.request)}/styles/shangpin/images/l1.gif"></div>
    
     </c:when>
     <c:otherwise>
 			<div class="alOrder_listCell order_null" >
			      <div class="alOrder_none">
				        <h2>暂无此类订单</h2>
				        <p><img src="${cdn:pic(pageContext.request)}/styles/shangpin/images/none_prod.png" width="130" height="130"></p>
				        <div class="payment_submit">
			                <a href="<c:url value='/index'/>" class="payment_btn">去 购 物</a>
			            </div>
			      </div>
			</div>
 		</c:otherwise>
   </c:choose>
 </rapid:override> 

 
<%-- 页面的尾部 --%>      
<rapid:override name="down_page">

</rapid:override>

<%@ include file="/WEB-INF/pages/common/another_common_mall_banner.jsp" %> 
