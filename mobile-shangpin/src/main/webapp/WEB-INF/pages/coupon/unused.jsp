<%@ page language="java" contentType="text/html; charset=UTF-8" trimDirectiveWhitespaces="true"
    pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/pages/common/include.inc.jsp"%>
<div class="tabs_Cell">
	<!--  <form class="coupons_active">
		<fieldset>
			<legend>优惠劵激活</legend>
			<p>
				<input type="text" id="coupons_code" name="coupons_code" placeholder="输入优惠劵编码" required />
				<a href="javascript:sendActivation();" class="coupons_submit">确认</a>
			</p>
		</fieldset>
	</form>
	-->
	
	
	<ul class="coupon_list select_coupon unused_list">
		<c:forEach var="coupon" items="${coupons}">
			<c:if test="${coupon.type == '1' }">
           			<li class="cash">
		                <h4><img src="${ctx}/styles/shangpin/images/order/cash_coupon_angle.png" width="69" height="145" /></h4>
		                <input type="hidden" name="couponId" id="couponId" value="${coupon.couponno}"/>
		                <div class="cash">
		                	<i id="coupon_amount">&yen;${coupon.amount }</i>
		                    <em>${coupon.name}</em>
		                    <span>
		                      <strong style='overflow:hidden;height:20px;'>${coupon.expirydate}</strong>
		                   	  <span style="height:57px;overflow:hidden;font-size: 10px;">${coupon.rule}</span> 
		                    </span>
		                </div>
		                <p><img src="${ctx}/styles/shangpin/images/order/cash_coupon.png" width="69" height="145" /></p>
		            </li>
           		</c:if>
           		<c:if test="${coupon.type == '0' }">
           			<li class="sale">
		                   <h4><img src="${ctx}/styles/shangpin/images/order/coupon_angle.png" width="69" height="145" /></h4>
		                   <input type="hidden" name="couponId" id="couponId" value="${coupon.couponno}"/>
		                <div class="cash">
		                	<i id="coupon_amount">&yen;${coupon.amount }</i>
		                    <em>${coupon.name}</em>
		                    <span>
		                      <strong style='overflow:hidden;height:20px;'>${coupon.expirydate}</strong>
		                     <span style="height:57px;overflow:hidden;font-size: 10px;">${coupon.rule}</span> 
		                    </span>
		                </div>
		                <p><img src="${ctx}/styles/shangpin/images/order/coupon.png" width="69" height="145" /></p>
		            </li>
           		</c:if>
		</c:forEach>
	</ul>
	
	<c:if test="${hasMore}">
		<a id="haveMore" class="payment_btn moreButton" href="javascript:getMoreList('0','unused_list');">点击查看更多</a>
	</c:if>
	<input type="hidden" id="unused_list" name="unused" value="${start}"/>
</div>