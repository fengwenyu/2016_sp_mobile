<%@ page language="java" contentType="text/html; charset=UTF-8"
	trimDirectiveWhitespaces="true" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/pages/common/include.inc.jsp"%>
<rapid:override name="custum">
	<link
		href="${cdn:css(pageContext.request)}/styles/shangpin/css/page/category/list.css${ver}"
		rel="stylesheet" />
	<script type="text/javascript" charset="utf-8">
	var ver = Math.random();
		loader = SP.core
		    .install("${cdn:js(pageContext.request)}/styles/shangpin/js/zepto.1.1.3.js${ver}")
			.using("${cdn:js(pageContext.request)}/styles/shangpin/js/lazyload.js${ver}")
		    .using("${cdn:js(pageContext.request)}/styles/shangpin/js/201502brand/iscroll.js${ver}")
			.excute()
			.using("${cdn:js(pageContext.request)}/styles/shangpin/js/config.sim.js${ver}")
			.using("${cdn:js(pageContext.request)}/styles/shangpin/js/tabslider.js${ver}")
			.excute()
			.using("${cdn:js(pageContext.request)}/styles/shangpin/js/comm.js${ver}")
			.using("${cdn:js(pageContext.request)}/styles/shangpin/js/downloadAppShow.js${ver}")
			.using("${cdn:js(pageContext.request)}/styles/shangpin/js/category/categorylist.js${ver}");
	</script>
</rapid:override>
<%-- 浏览器标题 --%>
<rapid:override name="title">
	品类列表
</rapid:override>

<%-- 页标题 --%>
<rapid:override name="page_title">
	品类列表
</rapid:override>
<rapid:override name="content">
	<div class="alContent">
		<div class="cate-box">
			<div class="category-left" id="categoryLeft">
				<ul class="cate-list">
					<c:forEach var="navList" items="${navigation.categoryList}"
						varStatus="status">
						<%-- 排除品牌分类 --%>
						<c:if test="${navList!= null && fn:length(navList.id) > 0 && navList.id ne '0'}">
							<li <c:if test="${navList.id == categoryId}">class="cur"</c:if>>
							  <input type="hidden" id="nav_id" value="${navList.id}" />
							  <a href=""><strong>${navList.name}</strong></a>
							</li>
						</c:if>
					</c:forEach>
				</ul>
			</div>
			<div class="category-right" id="categoryMid">
				<div>
					<div class="tabs_cell">
						<c:choose>
						<c:when test="${fn:length(categoryOperationItem.operation) > 0}">
							<c:forEach var="items" items="${categoryOperationItem.operation}" varStatus="status">
								<c:choose>
									<c:when test="${fn:length(items.type) > 0}">
										<c:if test="${items.name ne ''}">
											<div class="cate-brand-title">
												<a href="javascript:;">${items.name}</a>
												<!--<span>Fashion Trends</span>-->
												<em></em>
											</div>
										</c:if>
										<c:choose>
											<c:when test="${items.type eq '1'}">
												<div class="cate-banner">
													<%@ include file="/WEB-INF/pages/category/brandBox.jsp"%>
												</div>
											</c:when>
											<c:when test="${items.type eq '4' ||items.type eq '5'}">
												<div class="brandBox clr">
													<%@ include file="/WEB-INF/pages/category/brandBox.jsp"%>
												</div>
											</c:when>
										</c:choose>
									</c:when>
								</c:choose>
							</c:forEach>
						</c:when>
						<c:otherwise>
						       暂无商品
						</c:otherwise>
						</c:choose>
					</div>
				</div>
			</div>
		</div>
	</div>
</rapid:override>
<rapid:override name="statistics">
</rapid:override>
<rapid:override name="footer">
</rapid:override>
<%@ include file="/WEB-INF/pages/common/base_meet.jsp"%>

