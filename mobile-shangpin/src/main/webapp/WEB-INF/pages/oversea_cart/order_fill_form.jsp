<%@ page language="java" contentType="text/html; charset=UTF-8" trimDirectiveWhitespaces="true"
    pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/pages/common/include.inc.jsp"%>

<form id="orderForm" action="${ctx }/overseas/order/submit"  method="post" >
	<input  type="hidden"  id="skuId"  name="skuId"  value="${elements.list.spList[0].cartItemList[0].skuNo }"/>
	<input  type="hidden"  id="productId"  name="productId"  value="${elements.list.spList[0].cartItemList[0].productNo }"/>
	<input  type="hidden"  id="activityId"  name="activityId"  value=""/>
	<input  type="hidden"  id="amount"  name="amount"  value="${elements.amount}"/>
	<input  type="hidden"  id="addressId"  name="addressId" value='<c:if test="${haveAddress}">${address.id }</c:if>'/>
	<input type="hidden"  id="invoiceAddressId"  name="invoiceAddressId" />
	<input type="hidden" id="invoiceFlag"  name="invoiceFlag"  value="0"/>
	<input type="hidden" id="invoiceType"  name="invoiceType" value="0"/>
	<input type="hidden"  id="invoiceTitle" name="invoiceTitle"  value="个人"/>
	<input type="hidden"  id="invoiceContent" name="invoiceContent" value="商品明细"/>
	<input type="hidden"  id="express" name="express" value="1"/>
	<input type="hidden"  id="buysIds" name="buysIds"  value="${elements.list.spList[0].cartItemList[0].shoppingCartDetailId}" />
	<input type="hidden"  id="couponflag" name="couponflag"  value="" />
	<input type="hidden"  id="coupon" name="coupon"  value="" />
	<input type="hidden"  id="orderSource" name="orderSource"  value="1" />
	
	<c:choose>
		<c:when test="${isPayOut=='1' }">
			<c:if test="${!checkWX }">
				<input type="hidden"  id="payId" name="payId" value="30"/>
				<input type="hidden"  id="payChildId" name="payChildId"  value="121" />
			</c:if>
			<c:if test="${checkWX }">
				<input type="hidden"  id="payId" name="payId" value="32"/>
				<input type="hidden"  id="payChildId" name="payChildId"  value="111" />
			</c:if>
		</c:when>
		<c:otherwise>
			<c:if test="${!checkWX }">
				<input type="hidden"  id="payId" name="payId" value="20"/>
				<input type="hidden"  id="payChildId" name="payChildId"  value="37" />
			</c:if>
			<c:if test="${checkWX }">
				<input type="hidden"  id="payId" name="payId" value="27"/>
				<input type="hidden"  id="payChildId" name="payChildId"  value="58" />
			</c:if>
		</c:otherwise>
	</c:choose>
</form>
<%---隐藏表单域--%>
<!-- <input type="hidden"  id="msg" name="msg"  value="${msg }"/> -->

<div id="address_area_overlay" class="_area_overlay"></div>
    <div id="address_area_layer" class="_area_layer">
    	<a href="javascript:;" class="prev_btn" onclick="prevCTA();">返回</a>
        <a href="javascript:;" class="close_btn">关闭</a>
        <h3>省份</h3>
        <dl id="area_province" title="省份">
        <c:forEach var="province"  items="${provinces }">
       		 <dd><a href="javascript:;" id="${province.id }"  onclick="choicePCAT(this)">${province.name }</a></dd>
        </c:forEach>
        <input type="hidden" id="addrFlag"/>
        
        </dl>
        <dl id="area_city" title="城市">
            
        </dl>
        <dl id="area_county" title="地区">
          
        </dl>
        <dl id="area_street" title="街道">
            
        </dl>
    </div>