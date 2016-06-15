<%@ page language="java" contentType="text/html; charset=UTF-8" trimDirectiveWhitespaces="true"
    pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/pages/common/include.inc.jsp"%>

<div class="tabs_Cell">
	<ul class="coupon_list past_list">
		<c:forEach var="coupon" items="${coupons}">
			<c:if test="${coupon.type == '0' }">
				<li>
					<span class="sale"><i>&yen;</i>${coupon.amount}<em>${coupon.rule}</em></span>
					<p>过期时间：<br/>${fn:split(fn:split(coupon.expirydate,"至")[1]," ")[0]}<br/>${fn:split(fn:split(coupon.expirydate,"至")[1]," ")[1]}</p>
				</li>
			</c:if>
			<c:if test="${coupon.type == '1' }">
				<li>
					<span class="cash"><i>&yen;</i>${coupon.amount}<em>${coupon.rule}</em></span>
					<p>过期时间：<br/>${fn:split(fn:split(coupon.expirydate,"至")[1]," ")[0]}<br/>${fn:split(fn:split(coupon.expirydate,"至")[1]," ")[1]}</p>
				</li>
			</c:if>
		</c:forEach>
	</ul>
	<c:if test="${hasMore}">
		<a id="haveMore" class="payment_btn moreButton" href="javascript:getMoreList();">点击查看更多</a>
	</c:if>
	<input type="hidden" id="past_list" name="past" value="${start}"/>
</div>