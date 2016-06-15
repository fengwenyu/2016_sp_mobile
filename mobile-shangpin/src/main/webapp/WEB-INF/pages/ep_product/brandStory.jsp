<%@ page language="java" contentType="text/html; charset=UTF-8"
	trimDirectiveWhitespaces="true" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/pages/common/include.inc.jsp"%>
<!-- 品牌店入口 start -->
<div class="brand-shop-enter">
	<h3 class="brand-shop-name clr">
		<span>${productDetail.basic.brand.nameEN } 品牌店</span><a
			class="story-btn"
			href="<c:url value='/brand/product/list?brandNo=${productDetail.basic.brand.id }&brandName=${productDetail.basic.brand.nameEN }'/>">进店逛逛</a>
		<a class="enter-btn" href="<c:url value='/brandStory/index?id=${productDetail.basic.brand.id }&type=0'/>">品牌故事</a>
	</h3>
	<c:choose>
     <c:when test="${(productDetail.basic.brand.allNum  ne -1) && (productDetail.basic.brand.newestNum ne -1) && (productDetail.basic.brand.collections ne -1)}">
		<ul class="brand-shop-list">
			<li><em>${productDetail.basic.brand.allNum }</em> <span>全部商品</span>
			</li>
			<li><em>${productDetail.basic.brand.newestNum}</em> <span>上新</span>
			</li>
			<li><em>${productDetail.basic.brand.collections}</em> <span>喜欢人数</span>
			</li>
		</ul>
	</c:when>
	</c:choose>
</div>
<!-- 品牌店入口 end -->
