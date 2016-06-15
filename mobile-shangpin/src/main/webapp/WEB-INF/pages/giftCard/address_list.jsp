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
			<div class="topFix" id="order_address_list_header" style="display: none">
			   <section>
			       <div class="topBack" >
			       <a href="javascript:showPage('order_detail','order_header');" class="backBtn">&nbsp;</a>
			       	收货地址
			       <ul class="alUser_icon clr">
			           <li><a href="<c:url value='/index'/>"><img src="${cdn:pic(pageContext.request)}/styles/shangpin/images/home_icon.png" width="25" height="25" alt="首页"></a></li>
			       </ul>
			       </div>
			   </section>
			</div>
			<div class="alContent order_address_list" style="display: none;min-height:550px;">
		</c:when>
		<c:otherwise>
			<div class="alContent order_address_list" style="display: none;margin-top: 0;">
		</c:otherwise>
	</c:choose>
	<div class="paymet_block" id="append_address">
		<c:forEach var="address" items="${buyNow.receive}">
			<p class="addr_block add_block_list" id="${address.id}" title="${address.cod}" area="${address.area}" town="${address.town}">
				<span>
					<i>${address.name} &nbsp;&nbsp;${address.tel}</i>
					<em>${address.provName}${address.cityName}${address.areaName}${address.townName}${address.addr}</em>
				</span>
			</p>
		</c:forEach>
		<c:if test="${fn:length(buyNow.receive) < 10 }">
			<div class="payment_submit">
				<a href="javascript:showPage('order_address_add','order_address_add_header');" class="payment_btn" >新增地址</a>
			</div>
		</c:if>
	</div>
</div>






