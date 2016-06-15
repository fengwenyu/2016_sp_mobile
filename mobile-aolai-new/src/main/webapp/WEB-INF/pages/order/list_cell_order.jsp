<%@ page language="java" contentType="text/html; charset=UTF-8" trimDirectiveWhitespaces="true"
    pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/pages/common/include.inc.jsp"%>
      <!-- 订单列表开始 -->
 		 
	     <c:if test="${orderResult.items!=null }">
	     <input type="hidden" id="statusType${pageNo }" value="${statusType }"/>
 	    <input type="hidden" id="pageNo${pageNo }" value="${pageNo }"/>
 	    <input type="hidden" id="isHaveMore${pageNo }" value="${isHaveMore }"/>
	      <c:forEach var="item" items="${orderResult.items }" >
	       	<dl class="alOrder_listBlock">
		      	<dt>
		      		<i style="font-size:12px;color:#333">订单编号：${item.mainorderno}</i>
		      		<c:if test="${item.order!=null }">
		      			<c:forEach var="order" items="${item.order }">
		      				<c:if test="${order.orderdetail!=null }">
			      				<c:forEach var="detail" items="${order.orderdetail }">
			      					<a href="<c:url value='/user/order/detail?orderId=${item.mainorderno}'/>">
			      						<span><img src="${cdn:pic(pageContext.request)}/styles/shangpin/images/e.gif" lazy="${detail.pic }" width="60" height="80"/></span>
			      						<p>
			      						<i>${detail.productname }</i>
			      						${detail.firstpropname }:${detail.firstpropvalue }<br />
			      						${detail.secondpropname }:${detail.secondpropvalue }<br />
			      						<em>&yen;<fmt:formatNumber value="${detail.amount }"/> x ${detail.count }</em>
			      						</p>
			      					</a>
			      				</c:forEach>
		      				</c:if>
		      			</c:forEach>
		      		</c:if>
		      		<br />
		      	</dt>
		      	<dd>
					<span>
					<c:if test="${!(item.statusname eq 'null')}">
						${item.statusname}
					</c:if>
					</span>	
					<c:if test="${item.canpay }">
						<a href="<c:url value='/cart/submit/pay?orderId=${item.mainorderno }'/>" class="alOrder_buyBtn list_buyBtn">付款</a>
					</c:if>	      	
		      	</dd>
		      </dl>	
	      </c:forEach>
	   </c:if>
	   
    <c:if test="${orderResult2.items!=null }">
      <input type="hidden" id="statusType${pageNo }" value="${statusType }"/>
 	    <input type="hidden" id="pageNo${pageNo }" value="${pageNo }"/>
 	    <input type="hidden" id="isHaveMore${pageNo }" value="${isHaveMore }"/>
	      <c:forEach var="item" items="${orderResult2.items }" >
	       	<dl class="alOrder_listBlock">
		      	<dt>
		      		<i style="font-size:12px;color:#333">订单编号：${item.mainorderno}</i>
		      		<c:if test="${item.order!=null }">
		      			<c:forEach var="order" items="${item.order }">
		      				<c:if test="${order.orderdetail!=null }">
			      				<c:forEach var="detail" items="${order.orderdetail }">
			      					<a href="<c:url value='/user/order/detail?orderId=${item.mainorderno}'/>">
			      						<span><img src="${cdn:pic(pageContext.request)}/styles/shangpin/images/e.gif" lazy="${detail.pic }" width="60" height="80"/></span>
			      						<p>
			      						<i>${detail.productname }</i>
			      						${detail.firstpropname }:${detail.firstpropvalue }<br />
			      						${detail.secondpropname }:${detail.secondpropvalue }<br />
			      						<em>&yen;<fmt:formatNumber value="${detail.amount }"/> x ${detail.count }</em>
			      						</p>
			      					</a>
			      				</c:forEach>
		      				</c:if>
		      			</c:forEach>
		      		</c:if>
		      		<br />
		      	</dt>
		      	<dd>
					<span>
					<c:if test="${!(item.statusname eq 'null')}">
						${item.statusname}
					</c:if>
					</span>	
					<c:if test="${item.canpay }">
						<a href="<c:url value='/cart/submit/pay?orderId=${item.mainorderno }'/>" class="alOrder_buyBtn list_buyBtn">付款</a>
					</c:if>	      	
		      	</dd>
		      </dl>	
	      </c:forEach>
	   </c:if>