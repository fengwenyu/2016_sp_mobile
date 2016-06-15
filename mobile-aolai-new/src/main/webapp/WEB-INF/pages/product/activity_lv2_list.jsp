<%@ page language="java" contentType="text/html; charset=UTF-8" trimDirectiveWhitespaces="true"
    pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/pages/common/include.inc.jsp"%>
<rapid:override name="custum">
	<link href="${cdn:css(pageContext.request)}/styles/shangpin/css/page/list.css${ver}" rel="stylesheet" />
	
	<script type="text/javascript" charset="utf-8">
			loader = SP.core
				.install("${cdn:js(pageContext.request)}/styles/shangpin/js/jquery.min.js${ver}")
				.using("${cdn:js(pageContext.request)}/styles/shangpin/js/j.lazyload.js${ver}")
				.excute()
				.using("${cdn:js(pageContext.request)}/styles/shangpin/js/config.sim.js${ver}")
				.using("${cdn:js(pageContext.request)}/styles/shangpin/js/j.remainTime.js${ver}")
				.excute()
				.using("${cdn:js(pageContext.request)}/styles/shangpin/js/comm.js${ver}")
				.using("${cdn:js(pageContext.request)}/styles/shangpin/js/product/product_lv2_list.js")
				.excute(function(){
					isHome(false);
				});
	</script>
	<script type="text/javascript" charset="utf-8" src="${cdn:js(pageContext.request)}/styles/shangpin/js/j.remainTime.js${ver}"></script>
</rapid:override>

<rapid:override name="content">
	<%--预售商品 --%>
	<c:if test="${pageType == 0 }">
		<c:import url="/nav?navId=19"></c:import>
	</c:if>
	<%--最新特卖 --%>
	<c:if test="${pageType == 1 }">
		<c:import url="/nav?navId=4"></c:import>
	</c:if>
	<%--限时特卖 --%>
	<c:if test="${pageType == 2 }">
		<c:import url="/nav?navId=18"></c:import>
	</c:if>
	<div class="alContent">
		<section class="alBrand_list">
			<header>
				<h3>${activityName}</h3>
				<c:if test="${openFlag eq '0'}">
					<span class="lxftime3" startTime="${startTime}" nowTime="${sysTime}"><em>&nbsp;</em></span>
				</c:if>
				<c:if test="${openFlag eq '1'}">
					<span class="lxftime3" endTime="${endTime}" nowTime="${sysTime}"><em>&nbsp;</em></span>
				</c:if>
	  		</header>
	  		<ul id="modelLv2">
	  			<c:forEach items="${merchandiseList}" var="productList">
		  			<li>
		  				<%--最新热卖、限时特卖二级活动列表 --%>
		  				<c:if test="${openFlag eq '1'}">
		  					<c:choose>
		  						<c:when test="${checkAPP}">
		  							<a title="${productList.brand}${productList.productname}" href="shangpinapp://phone.aolai/actiongodetail?title=商品详情&productno=${productList.goodsid}&categoryno=${productList.categoryno}">
		  						</c:when>
		  						<c:otherwise>
		  							<a title="${productList.brand}${productList.productname}" href="<c:url value='/activity/detail?categoryNo=${productList.categoryno}&goodsId=${productList.goodsid}&pageType=${pageType}&typeFlag=${typeFlag}&activityId=${activityId}'/>">
		  						</c:otherwise>
		  					</c:choose>
			  				
								<%--判断小于4件及售罄提醒图标 --%>
								<c:if test="${productList.count == 0}">
									<i class="saletip saleoutTip">售罄</i>
								</c:if>
								<c:if test="${0<productList.count&&productList.count<4}">
									<i class="saletip salelastTip"><em>剩余<br><b>${productList.count}件</b></em></i>
								</c:if>
								<img width="159" height="211" alt="" src="${ctx}/styles/shangpin/images/e.gif" lazy="${productList.picurl}">
								<div class="alBrand_list_info">
									<p>
									<em>${productList.brand}</em>
									<em>${productList.productname}</em>
									</p>
									<span><i>&yen;</i><em>${productList.now}</em><del>&yen;${productList.past}</del></span>
								</div>
							 </a>
						 </c:if>
						<%--预售二级活动列表 --%>
		  				<c:if test="${openFlag eq '0'}">
		  					<a title="${productList.brand}${productList.productname}">
								<img width="159" height="211" alt="" src="${ctx}/styles/shangpin/images/e.gif" lazy="${productList.picurl}">
								<p class="soonBegin" style="display:block;">即将开始</p>
								<div class="alBrand_list_info">
									<p>
									<em>${productList.brand}</em>
									<em>${productList.productname}</em>
									</p>
									<span><i></i><em>敬请期待</em><del>&yen;${productList.past}</del></span>
								</div>
							 </a>
		  				</c:if>
		  			</li>
	  			</c:forEach>
	  		</ul>
	  		<c:if test="${haveMore==1}">
	  			<a id="haveMore" class="alList_moreBtn" href="javascript:getMore()">加载更多</a>
	  		</c:if>
		</section>
		<form action="">
			<input type="hidden" id="pageIndex" value="1">
			<input type="hidden" id="ctx" value="${ctx}">
			<input type="hidden" id="pageType" value="${pageType}">
			<input type="hidden" id="typeFlag" value="${typeFlag}">
	  		<input type="hidden" id="activityNum" value="${activityId}">
	  		<input type="hidden" id="isMore" value="">
	  		<input type="hidden" id="nowPage" value="">
		</form>
	</div>
</rapid:override>
<%@ include file="/WEB-INF/pages/common/base.jsp" %> 