<%@ page language="java" contentType="text/html; charset=UTF-8" trimDirectiveWhitespaces="true"
    pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/pages/common/include.inc.jsp"%>


<rapid:override name="custum">
		<link href="${cdn:css(pageContext.request)}/styles/shangpin/css/page/order/base.css${ver}" rel="stylesheet" />
		<link href="${cdn:css(pageContext.request)}/styles/shangpin/css/page/order_form150422.css${ver}" rel="stylesheet" />
		<link href="${cdn:css(pageContext.request)}/styles/shangpin/css/page/new_order_member.css${ver}" rel="stylesheet" />
		<script type="text/javascript" charset="utf-8">
			var ver = Math.random();
				loader = SP.core
					.install("${cdn:js(pageContext.request)}/styles/shangpin/js/jquery.min.js${ver}")
					.using("${cdn:js(pageContext.request)}/styles/shangpin/js/comm.js${ver}")
					.using("${cdn:js(pageContext.request)}/styles/shangpin/js/lazyload.js${ver}")
					.using("${cdn:js(pageContext.request)}/styles/shangpin/js/order/orderList.js${ver}")
					.using("${cdn:js(pageContext.request)}/styles/shangpin/js/downloadAppShow.js${ver}")
					.using("${cdn:js(pageContext.request)}/styles/shangpin/js/order/cancelOrder.js${ver}")
					.using("${cdn:js(pageContext.request)}/styles/shangpin/js/order/finishOrder.js${ver}")
					.using("${cdn:js(pageContext.request)}/styles/shangpin/js/order/leftTime.js${ver}")
					.excute();
		</script>
</rapid:override>

<%-- 浏览器标题 --%>
<rapid:override name="title">
	<c:if test="${statusType=='1'}">
            全部订单  		     
     </c:if>
	<c:if test="${statusType=='2'}">
            待支付	     
     </c:if>
	<c:if test="${statusType=='3'}">
           待收货	     
     </c:if>
	<c:if test="${statusType=='4'}">
           待发货		     
     </c:if>
</rapid:override>

<%-- 页标题 --%>
<rapid:override name="page_title">
	<c:if test="${statusType=='1'}">
            全部订单  		     
     </c:if>
	<c:if test="${statusType=='2'}">
            待支付	     
     </c:if>
	<c:if test="${statusType=='3'}">
           待收货	     
     </c:if>
	<c:if test="${statusType=='4'}">
           待发货		     
     </c:if>
</rapid:override>
 <rapid:override name="downloadAppShowHead">
		
</rapid:override>  
<rapid:override name="content">
	<input type="hidden" id="statusType" name="statusType" value="${statusType}">
	<input type="hidden" id="currentTimes" name="currentTimes" value="${currentTimes}">
 	 <c:choose>
	   <c:when test="${orderlist != null }">
		<div class="paymet_block">
      	  <form>
      	    <c:forEach items="${orderlist}" var="orderItem"  varStatus="i" >
      	     <c:set value="false" var="isEcard"  scope="page"/>
          	   <fieldset class="order-box">
                <p class="selected"><a href="#"><strong style="color:#888">单号：</strong>${orderItem.mainorderno}<time id="setTime" >${orderItem.date}</time></a></p>
                	<c:forEach items="${orderItem.order}" var="order">
               		 <p class="selected"><a href="#"><strong style="color:#888">
				     <c:choose>
				     	<c:when test="${orderItem.postArea==2}">
				     	  香港订单:
				     	</c:when>
				     	<c:otherwise>
					     	 <c:if test="${order.ordertype==1}">
					                           尚品订单：
						      </c:if>          
						      <c:if test="${order.ordertype==2}">
						                 奥莱订单：
						      </c:if>  
				     	</c:otherwise>
				     </c:choose>
				     </strong>${order.orderno}   <time style=" color:#c62026">${order.statusname}</time></a></p>
		                <c:forEach items="${order.orderdetail}" var="detail">
		               	 <p class="pord_info">
		               	 	<a href="<c:url value='/order/detail?isConfirm=${orderItem.canconfirmgoods}&statusType=${statusType}&mainOrderNo=${orderItem.mainorderno}'/>"  class="clr" >
		               	 	<img src="${detail.pic}" width="60" height="67">
		                    <ins>
		                        <i>
		                        <c:if test="${detail.type=='0' }">
		                        
		                        </c:if>
		                        <c:if test="${detail.type=='1' }">
		                        	  <c:set value="true" var="isEcard"  scope="page"/>
		                        </c:if>
		                        <c:if test="${detail.type=='2' }">
		                        
		                        </c:if>
		                        <br />${detail.productname}</i>
		                        <c:if test="${detail.type=='0' }">
		                            <em>颜色：${detail.firstpropvalue}</em>
		                        	<em>尺码：${detail.secondpropvalue}</em>
		                        </c:if><br />
		                        <em>价格：&yen;${detail.amount}</em>
		                        <em>数量：${detail.count}</em>
		                    </ins>
		                    </a>
		                </p>
		               </c:forEach>
                 </c:forEach>
                <p class="pay-model-p"><a>支付方式：<strong>${orderItem.paytypename} </strong></a><span>支付金额：￥<strong>${orderItem.formatamount}</strong></span></p> <div class="btn-icon clr">
	                <c:if test="${!isEcard }">
	              		<c:if test="${  orderItem.order[0].status==12|| orderItem.order[0].status==13|| orderItem.order[0].status==14||orderItem.order[0].status==15 || orderItem.order[0].status==16|| orderItem.order[0].status==18||orderItem.order[0].status==98 || orderItem.order[0].status==99}">
	              			<a href="${ctx}/logistice/list?orderId=${orderItem.mainorderno }&postArea=${orderItem.postArea }" class="cur">订单跟踪</a>
	              		</c:if>
	                </c:if>
	               	<c:set var="toPayUrl" value="${ctx}/order/pay/normal?orderId=${orderItem.mainorderno}" scope="page"/>
	                <c:if test="${orderItem.postArea==2 }">
	               	 <c:set var="toPayUrl" value="${ctx}/overseas/pay/continue?orderId=${orderItem.mainorderno}" scope="page"/>
	                </c:if>
	                 
              		<c:if test="${ orderItem.order[0].status==11}">
                		<a href="${toPayUrl }" class="red">立即支付 <em id="leftTime" leftTime=""></em></a>
                    	<a href="javascript:cancelOrder('${orderItem.mainorderno}');" id="${orderItem.mainorderno}" class="">取消订单</a>
                    </c:if>
              		<c:if test="${orderItem.order[0].status==10 }">
                		<a href="${toPayUrl }" class="red">立即支付 </em></a>
                    	<a href="javascript:cancelOrder('${orderItem.mainorderno}');" id="${orderItem.mainorderno}" class="">取消订单</a>
                    </c:if>
              		<c:if test="${ orderItem.canconfirmgoods==1}">
              		        <a href="javascript:finishOrder('${orderItem.mainorderno}');" class="red">确认收货</a>
                    </c:if>
                 </div>
            </fieldset>
           </c:forEach>
        </form>
    </div>
     <%--為1顯示查看更多按鈕 --%>
    		 <c:if test="${haveMore eq '1'}">
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