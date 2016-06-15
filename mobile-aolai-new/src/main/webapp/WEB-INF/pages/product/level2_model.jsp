<%@ page language="java" contentType="text/html; charset=UTF-8" trimDirectiveWhitespaces="true"
    pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/pages/common/include.inc.jsp"%>
<script>
$(function(){
	$("#isMore").val(${haveMore});
	$("#nowPage").val(${pageIndex});
});
</script>
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
								<img width="159" height="211" alt="" src="${productList.picurl}">
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
								<img width="159" height="211" alt="" src="${productList.picurl}">
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
 
 