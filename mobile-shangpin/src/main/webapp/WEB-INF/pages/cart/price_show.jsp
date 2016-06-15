<%@ page language="java" contentType="text/html; charset=UTF-8" trimDirectiveWhitespaces="true"
    pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/pages/common/include.inc.jsp"%>
	<c:if test="${code == '0'}">
		<c:forEach var="priceShow" items="${prices}">
		   	<c:choose>
		   		<c:when test="${priceShow.type == '1' }">
		   			${priceShow.title}：<i id="product_total_price">&nbsp;&nbsp;&nbsp;&yen;&nbsp;${priceShow.amount}</i><br />
		   		</c:when>
		   		<c:when test="${priceShow.type == '2' && priceShow.amount != '0' }">
		   			${priceShow.title}：<i id="promoto_price">&nbsp;&nbsp;-&yen;&nbsp;${priceShow.amount}</i><br />
		   		</c:when>
		   		<c:when test="${priceShow.type == '3' && priceShow.amount != '0'}">
		   			 <span id="span_pay_coupon">${priceShow.title}：<i id="pay_coupon">&nbsp;&nbsp;-&yen;&nbsp;<b style="font-weight: normal;">${priceShow.amount}</b></i><br /></span>
		   		</c:when>
		   		<c:when test="${priceShow.type == '4' && priceShow.amount != '0'}">
		   			<span id="span_pay_card">${priceShow.title}：<i id="pay_card">&nbsp;&nbsp;-&yen;&nbsp;<b style="font-weight: normal;">${priceShow.amount}</b></i><br /></span>
		   		</c:when>
		   		<c:when test="${priceShow.type == '5' }">
		   			<span id="span_carriage">${priceShow.title}：<i id="carriage">&nbsp;&nbsp;&nbsp;&yen;&nbsp;${priceShow.amount}</i><br /></span>
		   		</c:when>
		   		<c:when test="${priceShow.type == '6' && priceShow.amount != '0'}">
		   			<span id="span_nocarriage">${priceShow.title}：<i id="nocarriage">&nbsp;&nbsp;-&yen;&nbsp;${priceShow.amount}</i></span>
		   		</c:when>
		   		<c:when test="${priceShow.type == '7' }">
		   			<em>${priceShow.title}：<i id="real_pay">&nbsp;&nbsp;&nbsp;&yen;&nbsp;<b>${priceShow.amount}</b></i></em>
		   		</c:when>
		   	</c:choose>
		   </c:forEach>
	</c:if>
