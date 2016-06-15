<%@ page language="java" contentType="text/html; charset=UTF-8" trimDirectiveWhitespaces="true"
    pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/pages/common/include.inc.jsp"%>


<rapid:override name="custum">
	<link href="${cdn:css(pageContext.request)}/styles/shangpin/css/page/account_new.css${ver}" rel="stylesheet" />
	<link href="${cdn:css(pageContext.request)}/styles/shangpin/css/page/order.css${ver}" rel="stylesheet" />
	<link href="${cdn:css(pageContext.request)}/styles/shangpin/css/page/new_order_member.css${ver}" rel="stylesheet" />
	<script type="text/javascript" charset="utf-8">
		loader = SP.core
		 	.install("${cdn:js(pageContext.request)}/styles/shangpin/js/zepto.min.js${ver}")
			.using("${cdn:js(pageContext.request)}/styles/shangpin/js/lazyload.js${ver}")
		    .excute()
		    .using("${cdn:js(pageContext.request)}/styles/shangpin/js/config.sim.js${ver}")
			.using("${cdn:js(pageContext.request)}/styles/shangpin/js/comm.js${ver}")
			.using("${cdn:js(pageContext.request)}/styles/shangpin/js/order/orderList.js${ver}")
			.excute(function(){
				isHome(false);
			});
			
	</script>
</rapid:override > 


<%-- 浏览器标题 --%>
<rapid:override name="title">
	我的订单
</rapid:override>

<%-- 页标题 --%>
<rapid:override name="page_title">
	订单管理
</rapid:override>

<rapid:override name="content">

  <section class="alOrder_list">
    <nav class="alOrder_listMenu">
    <input type="hidden" id="statusType" name="statusType" value="${statusType}">
    <input type="hidden" id="pageIndex" name="pageIndex" value="${pageIndex}">
      <ul>
      	<c:if test="${statusType == 1 }">
       		<li id="waitpay" ><a href="<c:url value='/order/list?statusType=2'/>">待支付</a></li>
         	<li id="waitconfirm"><a href="<c:url value='/order/list?statusType=3'/>">待收货</a></li>
        	<li id="all" class="cur"><a href="<c:url value='/order/list?statusType=1'/>">全部</a></li>
      	</c:if>
      	<c:if test="${statusType == 2 }">
      		<li id="waitpay" class="cur"><a href="<c:url value='/order/list?statusType=2'/>">待支付</a></li>
         	<li id="waitconfirm" ><a href="<c:url value='/order/list?statusType=3'/>">待收货</a></li>
        	<li id="all"><a href="<c:url value='/order/list?statusType=1'/>">全部</a></li>
      	</c:if>
      	<c:if test="${statusType == 3 }">
      		<li id="waitpay" ><a href="<c:url value='/order/list?statusType=2'/>">待支付</a></li>
         	<li id="waitconfirm" class="cur"><a href="<c:url value='/order/list?statusType=3'/>">待收货</a></li>
        	<li id="all"><a href="<c:url value='/order/list?statusType=1'/>">全部</a></li>
      	</c:if>
      </ul>
    </nav>
    
	<c:choose>
	   <c:when test="${orderlist != null }">
	   		 <div class="alOrder_listCell order_list">
    	         <c:forEach items="${orderlist}" var="orderItem" >
    	         	<div class="alOrder_listBlock">
    	         	<c:forEach items="${orderItem.order}" var="order">
		   		 	 	<h3>订单编号：${orderItem.mainorderno}
    	         	</c:forEach>
		   		 	   <c:choose>
		   		 	   		<c:when test="${orderItem.canpay}">
		   		 	   			<a href="<c:url value='/order/detail?mainOrderNo=${orderItem.mainorderno}'/>" class="alOrder_buyBtn list_buyBtn">去付款</a>
		   		 	   			<span>${orderItem.statusname}</span>
		   		 	   		</c:when>
		   		 	   		<c:when test="${orderItem.status==13 || orderItem.status==99 }">
		   		 	   		   <a href="${ctx}/logistice/list?orderId=${orderItem.mainorderno }&postArea=${orderItem.postArea }" class="alOrder_buyBtn list_buyBtn">物流跟踪</a>
		   		 	   		   <span>${orderItem.statusname}</span>
		   		 	   		</c:when>
		   		 	   		<c:otherwise>
		   		 	   		   <span>${orderItem.statusname}</span>
		   		 	   		</c:otherwise>
		   		 	   </c:choose>
		   		 	 </h3>
		   		 	 
		   		 	 <c:forEach items="${orderItem.order[0].orderdetail}" var="detail">
		   		 	 	
		   		 	 		<a href="<c:url value='/order/detail?mainOrderNo=${orderItem.mainorderno}'/>">
		   		 	 		<img src="${detail.pic}" width="60" height="80">
		   		 	 		<dl><dt>${detail.productname}	</dt>
		   		 	 		<c:if test="${detail.type != '1' }">
			   		 	 		颜色:${detail.firstpropvalue}<br />
			   		 	 		尺码:${detail.secondpropvalue} <br />
		   		 	 		</c:if>
		   		 	 		<dd>
		   		 	 		<c:choose>
		   		 	 			<c:when test="${ orderItem.freight == '0'}">
		   		 	 				<dd><em>&yen;<fmt:formatNumber type="number" value="${detail.amount}" maxFractionDigits="0"/>*${detail.count}(免运费)</em>
		   		 	 			</c:when>
		   		 	 			<c:otherwise>
		   		 	 				<dd><em>&yen;<fmt:formatNumber type="number" value="${detail.amount}" maxFractionDigits="0"/>*${detail.count}</em>
		   		 	 			</c:otherwise>
		   		 	 		</c:choose>
		   		 	 		
		   		 	 		</dd></dl></a>
		   		 	 </c:forEach>
    	           </div>
    	         </c:forEach>
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
  </section>

</rapid:override> 

 <%-- 页面的尾部 --%>      
 <rapid:override name="down_page">
	
</rapid:override> 

<%@ include file="/WEB-INF/pages/common/common.jsp" %> 