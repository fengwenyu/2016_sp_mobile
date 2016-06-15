<%@ page language="java" contentType="text/html; charset=UTF-8" trimDirectiveWhitespaces="true"
    pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/pages/common/include.inc.jsp"%>
<section class="alIndex_list">
	<ul class="todayList">
		<c:forEach items="${newHotProductList}" var="productList">
		<li>
			<a title="${productList.activityname}" href="<c:url value='/activity/lv2?activityId=${productList.activityid}&typeFlag=1&pageType=1'/>">
				<img width="145" height="108" alt="${productList.activityname}" src="${ctx}/styles/shangpin/images/e.gif" lazy="${productList.pic}">
				<div class="alIndex_list_info">
					<p>
					${productList.brandenname}<br />
					${productList.activityname}
					</p>
					<span><em>${productList.t1} </em>${productList.t2}</span>
				</div>
			</a>
		</li>
	   </c:forEach>
	</ul>
	<a class="alList_moreBtn" href="<c:url value='/allnew?pageType=1&gender=${gender}'/>">查看全部</a>
</section>