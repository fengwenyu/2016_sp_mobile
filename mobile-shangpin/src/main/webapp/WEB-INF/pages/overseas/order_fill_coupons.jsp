<%@ page language="java" contentType="text/html; charset=UTF-8" trimDirectiveWhitespaces="true"
    pageEncoding="UTF-8"%>
<div class="topFix" id="coupons_head" style="display: none;">
    <section>
        <div class="topBack">
            <a href="javascript:couponBack();" class="backBtn">&nbsp;</a>
            <span class="top-title">选择优惠券</span>
        </div>
    </section>
</div>
<ul class="order_fill_coupon_list select_coupon" style="display: none; padding-top: 54px;">
          	<c:forEach var="coupon" items="${fillData.coupon}">
          		<c:if test="${coupon.type == '1' }">
          			<li class="cash">
	                <h4><img src="${ctx}/styles/shangpin/images/order/cash_coupon_angle.png" width="69" height="121" /></h4>
	                <input type="hidden" name="couponId" id="couponId" value="${coupon.couponno}"/>
	                <div class="cash">
	                	<i id="coupon_amount">&yen;${coupon.amount }</i>
	                    <em>${coupon.name}</em>
	                    <span>
	                      <strong>${coupon.expiredate}</strong>
	                   	  ${coupon.desc}
	                    </span>
	                </div>
	                <p><img src="${ctx}/styles/shangpin/images/order/cash_coupon.png" width="69" height="121" /></p>
	                <b id="coupon_selected" class="select"></b>
	            </li>
          		</c:if>
          		<c:if test="${coupon.type == '0' }">
          			<li class="sale">
	                   <h4><img src="${ctx}/styles/shangpin/images/order/coupon_angle.png" width="69" height="121" /></h4>
	                   <input type="hidden" name="couponId" id="couponId" value="${coupon.couponno}"/>
	                <div class="cash">
	                	<i id="coupon_amount">&yen;${coupon.amount }</i>
	                    <em>${coupon.name}</em>
	                    <span>
	                      <strong>${coupon.expiredate}</strong>
	                     	${coupon.desc}
	                    </span>
	                </div>
	                <p><img src="${ctx}/styles/shangpin/images/order/coupon.png" width="69" height="121" /></p>
	                <b id="coupon_selected" class="select"></b>
	            </li>
          		</c:if>
          	</c:forEach>
       </ul>
		
