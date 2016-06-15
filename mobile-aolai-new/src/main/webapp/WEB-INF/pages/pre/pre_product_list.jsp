<%@ page language="java" contentType="text/html; charset=UTF-8" trimDirectiveWhitespaces="true"
    pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/pages/common/include.inc.jsp"%>
<section class="alIndex_list alDate_list">
	<!-- 当预售产品列表为空时 -->
	<c:if test="${preSellProductList == null}">
		<header style="display:block;background:none;margin-bottom:50%"><b>暂无预售商品上架，敬请期待！</b></header>
	</c:if>
	<!-- 当预售产品列表不为空时 -->
	<c:if test="${preSellProductList != null}">
		<header style="display:block;background:none;"><b>${showHead}</b></header>
		<ul class="todayList timeList">
		<c:forEach items="${preSellProductList}" var="productList">
			<li>
				<a title="${productList.activityname}" href="<c:url value='/activity/lv2?activityId=${productList.activityid}&typeFlag=1&pageType=0'/>">
					<img width="145" height="108" alt="${productList.activityname}" src="${ctx}/styles/shangpin/images/e.gif" lazy="${productList.pic}">
					<div class="alIndex_list_info">
						<p>
						${productList.activityname}
						</p>
						<span><em>${productList.t1}</em>${productList.t2}</span>
					</div>
				</a>
			</li>
		</c:forEach>
		</ul>
	</c:if>
</section>