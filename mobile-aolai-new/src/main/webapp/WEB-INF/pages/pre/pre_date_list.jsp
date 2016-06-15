<%@ page language="java" contentType="text/html; charset=UTF-8" trimDirectiveWhitespaces="true"
    pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/pages/common/include.inc.jsp"%>
<div class="alDate_menu">
	<c:forEach items="${preSellDateList}" var="dateList">
		<!-- 选中状态 -->
		<c:if test="${dateList.cssFlag eq '1'}">
			<a href="<c:url value='/presell?id=${dateList.id}&startTime=${dateList.startTime}&endTime=${dateList.endTime}'/>" class="cur">${dateList.date}<span>${dateList.weekDay}</span></a>
		</c:if>
		<!-- 未选中状态 -->
		<c:if test="${dateList.cssFlag eq '0'}">
			<a href="<c:url value='/presell?id=${dateList.id}&startTime=${dateList.startTime}&endTime=${dateList.endTime}'/>">${dateList.date}<span>${dateList.weekDay}</span></a>
		</c:if>
	</c:forEach>
</div>