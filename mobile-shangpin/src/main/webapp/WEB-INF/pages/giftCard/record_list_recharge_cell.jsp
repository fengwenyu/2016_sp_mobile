<%@ page language="java" contentType="text/html; charset=UTF-8"
	trimDirectiveWhitespaces="true" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/pages/common/include.inc.jsp"%>
<c:choose>
	<c:when test="${giftCardRecord!=null && fn:length(giftCardRecord.list)>0 }">
	<input type="hidden" id="isHaveMore${recordType }${pageNo }" value="${isHaveMore }"/>
	          	<c:forEach var="record" items="${giftCardRecord.list }">
			     <li class="clr"> 
			        <span>${record.typeDesc }</span>
			        <span>￥${record.faceValue }</span>
			        <span>${record.fmtDate }</span>
			        <span>${record.statusDesc }</span> 
			     </li>
				</c:forEach>
	</c:when>
</c:choose>