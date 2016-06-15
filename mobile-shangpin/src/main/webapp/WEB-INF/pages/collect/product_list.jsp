<%@ page language="java" contentType="text/html; charset=UTF-8" trimDirectiveWhitespaces="true"
    pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/pages/common/include.inc.jsp"%>

<rapid:override name="custum">
	<link href="${cdn:css(pageContext.request)}/styles/shangpin/css/page/201502brand/list.css${ver}" rel="stylesheet" />
	<script type="text/javascript" charset="utf-8">
			loader = SP.core
				.install("${cdn:js(pageContext.request)}/styles/shangpin/js/jquery.min.js?${ver}")
				.using("${cdn:js(pageContext.request)}/styles/shangpin/js/lazyload.js${ver}")
				.excute()
				.using("${cdn:js(pageContext.request)}/styles/shangpin/js/config.sim.js${ver}")
				.using("${cdn:js(pageContext.request)}/styles/shangpin/js//j.dialogs.js${ver}")
				.excute()
				.using("${cdn:js(pageContext.request)}/styles/shangpin/js/comm.js${ver}")
				.using("${cdn:js(pageContext.request)}/styles/shangpin/js/downloadAppShow.js${ver}")
				.using("${cdn:js(pageContext.request)}/styles/shangpin/js/201502brand/collect.js${ver}");
	</script>
</rapid:override >

<rapid:override name="page_title">
	我的愿望清单
</rapid:override>
<rapid:override name="search_form">
</rapid:override>
<rapid:override name="content">
	<!-- 收藏列表 -->  
	<input type="hidden" id="pageIndex" name="pageIndex" value="${pageIndex}"/>
	<input type="hidden" id="hasMore" name="hasMore" value="${hasMore}"/>
	<c:if test="${fn:length(products) == 0}">
		 <p class="brand_noresults">你没有收藏呢</p>
	</c:if>
	<div class="list-box">
		<div class="prod_list clr">
			<c:forEach var="product" items="${products}">
				<div class="list_box">
				
				 <a href="javascript:goDetail('${product.type }','${product.isShelve}','${product.productId}');"> 
				<%-- 	 <c:if test="${product.type=='0' }">
						<a href="${ctx}/product/detail?productNo=${product.productId}">
					</c:if>
					<c:if test="${product.type=='1' }">
						<a href="${ctx}/giftCard/cardDetail?productNo=${product.productId}&type=1">
					</c:if>
					<c:if test="${product.type=='2' }">
						<a href="${ctx}/giftCard/cardDetail?productNo=${product.productId}&type=2">
					</c:if>  --%>
					
						<img src="${fn:substring(product.pic,0,fn:indexOf(product.pic,'-'))}-304-404.jpg" />
						<div class="li_text">
							<h5 style="white-space: nowrap;overflow: hidden;text-overflow: ellipsis;">
							<c:if test="${product.type=='0' }">
								${product.brandNameEN}
							</c:if>
							<c:if test="${product.type=='1' }">
								电子卡
							</c:if>
							<c:if test="${product.type=='2' }">
								实物卡
							</c:if>
							</h5>
							<p>${product.name}</p>
							<span>
								<c:choose>
									<c:when test="${product.status == '000100' }">
										<strong style="color: #c62026">¥${product.strongPrice}</strong>
									</c:when>
									<c:otherwise>
										<strong style="color: #2B2828">¥${product.strongPrice}</strong>
									</c:otherwise>
								</c:choose>
								<c:if test="${product.strongPrice < product.delPrice}">
									<em>¥${product.delPrice}</em>
								</c:if>
							</span>
						</div>
					</a>
				 </div>
			</c:forEach>
		</div>
	</div>
</rapid:override>

<%@ include file="/WEB-INF/pages/common/another_common_mall_banner.jsp" %> 