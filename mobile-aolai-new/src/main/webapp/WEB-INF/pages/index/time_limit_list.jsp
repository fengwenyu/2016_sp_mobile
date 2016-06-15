<%@ page language="java" contentType="text/html; charset=UTF-8" trimDirectiveWhitespaces="true"
    pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/pages/common/include.inc.jsp"%>

<%--特卖start--%>
<section class="alIndex_list">
	<header>
		<h3>限时特卖</h3>
		<c:if test="${flag }">
		<span class="lxftime2"  nowTime="${nowTime }" endTime="${endTime}"><em></em></span></c:if>
		<c:if test="${!flag }"><span>活动已经结束</span></c:if>
	</header>
	<ul class="todayList timeList">
		<c:forEach items="${limitedProductList}" var="productList">
			<li>
				<a title="${productList.activityname}" href="<c:url value='/activity/lv2?activityId=${productList.activityid}&typeFlag=1&pageType=2'/>">
				<img width="145" height="108" alt="${productList.activityname}" src="${ctx}/styles/shangpin/images/e.gif" lazy="${productList.pic}">
				<div class="alIndex_list_info">
					<p>
					${productList.brandenname}<br/>
					${productList.activityname}
					</p>
					<span><em>${productList.t1}</em>${productList.t2}</span>
				</div>
				</a>
			</li>
		</c:forEach>
	</ul>
</section>
<%--特卖end--%>