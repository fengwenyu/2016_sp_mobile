<%@ page language="java" contentType="text/html; charset=UTF-8" trimDirectiveWhitespaces="true"
    pageEncoding="UTF-8"%>
    	<c:set var="ua" value="${header['User-Agent']}" />
	<c:set var="micromessenger" value="micromessenger" />
	<c:set var="aolaiIOSApp" value="AolaiIOSApp" />
	<c:set var="shangpinAndroidApp" value="ShangpinAndroidApp" />
	<c:set var="aolaiAndroidApp" value="AolaiAndroidApp" />
	<c:set var="shangpinIOSApp" value="ShangpinIOSApp" />
	<c:choose>
		<c:when test="${!fn:containsIgnoreCase(ua, micromessenger)&&!fn:containsIgnoreCase(ua, shangpinIOSApp)&&!fn:containsIgnoreCase(ua, aolaiAndroidApp)&&!fn:containsIgnoreCase(ua, aolaiIOSApp)&&!fn:containsIgnoreCase(ua, shangpinAndroidApp)}">    
			<div class="topFix" id="order_coupons_header" style="display: none">
			   <section>
			       <div class="topBack" >
			       <a href="javascript:showPage('order_detail','order_header');" class="backBtn">&nbsp;</a>
			       选择优惠
			       <ul class="alUser_icon clr">
			           <li><a href="<c:url value='/index'/>"><img src="${cdn:pic(pageContext.request)}/styles/shangpin/images/home_icon.png" width="25" height="25" alt="首页"></a></li>
			       </ul>
			       </div>
			   </section>
			</div>
			<div class="alContent order_coupons" style="display: none;min-height: 100%;">
		</c:when>
		<c:otherwise>
			<div class="alContent order_coupons" style="display: none;margin-top: 0;min-height: 100%;">
		</c:otherwise>
	</c:choose>
	<ul class="tabs_menu coupon_menu_order">
       <li class="cur">使用优惠劵</li>
       <li>使用折扣码</li>
   </ul>
   
   <div class="coupon_block_order">
   	<div class="tabs_Cell">	
           <form class="coupons_active_order">
               <!--  <h3>优惠券激活</h3>-->
           	<fieldset>
                   <p>
                       <input type="text" id="coupons_code" name="coupons_code" placeholder="输入优惠劵编码" required />
                       <a href="javascript:activeCoupon();" class="coupons_submit">确认</a>
                   </p>
               </fieldset>
           </form>
           
           <ul class="coupon_list_order select_coupon">
           	<c:forEach var="coupon" items="${elements.coupon}">
           		<c:if test="${coupon.type == '1' }">
           			<li class="cash">
		                <h4><img src="${ctx}/styles/shangpin/images/order/cash_coupon_angle.png" width="69" height="145" /></h4>
		                <input type="hidden" name="couponId" id="couponId" value="${coupon.couponno}"/>
		                <div class="cash">
		                	<i id="coupon_amount">&yen;${coupon.amount }</i>
		                    <em>${coupon.name}</em>
		                    <span>${coupon.expiredate}</span>
		                   	<span>${coupon.desc}</span>
		                </div>
		                <p><img src="${ctx}/styles/shangpin/images/order/cash_coupon.png" width="69" height="145" /></p>
		                <b id="coupon_selected" class="coupons_select"></b>
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
		                      <strong>${coupon.expiredate}</strong>
		                     	${coupon.desc}
		                    </span>
		                </div>
		                <p><img src="${ctx}/styles/shangpin/images/order/coupon.png" width="69" height="145" /></p>
		                <b id="coupon_selected" class="coupons_select"></b>
		            </li>
           		</c:if>
           	</c:forEach>
        </ul>
        <input type="hidden" id="online_pay" name="online_pay" value=""/>
    </div>
	<div class="tabs_Cell">
    	
        <form class="coupons_active_order">
        	<fieldset>
                <p class="code_active">
                    <input type="text" id="sale_code" name="sale_code" placeholder="输入折扣码" required />
                    <a href="javascript:promoCodeInfo();" id="saleCode_btn" class="coupons_submit">确认</a>
                </p>
                
                <p class="code_result" style="display:none;">
                    <span><i id="prom_code"></i> &nbsp;&nbsp; 已优惠 ¥<i id="pro_amount"></i></span>
                    <input type="hidden" id="coupon_carriage" name="coupon_carriage" value=""/>
                    <input type="hidden" id="coupon_payamount" name="coupon_payamount" value=""/>
                    <a href="javascript:cannelPromoCode();" id="saleCacel_btn" class="coupons_submit">更改</a>
                </p>
            </fieldset>
        </form>
        
        <p class="coupons_tips_order">温馨提示：当你使用现金券、优惠券、折扣码购买的商品发生退货时，现金券/优惠券抵扣的金额将不会退还。</p>
        
    </div>
</div>
</div>
