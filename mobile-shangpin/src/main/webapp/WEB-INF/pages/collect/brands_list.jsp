<%@ page language="java" contentType="text/html; charset=UTF-8" trimDirectiveWhitespaces="true"
    pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/pages/common/include.inc.jsp"%>

<rapid:override name="custum">
	<link href="${cdn:css(pageContext.request)}/styles/shangpin/css/page/font-css/font-awesome.min.css${ver}" rel="stylesheet" />
	<link href="${cdn:css(pageContext.request)}/styles/shangpin/css/page/collect_brand.css${ver}" rel="stylesheet" />
	<script type="text/javascript" charset="utf-8">
			loader = SP.core
				.install("${cdn:js(pageContext.request)}/styles/shangpin/js/zepto.1.1.3.js?${ver}")
				.using("${cdn:js(pageContext.request)}/styles/shangpin/js/lazyload.js${ver}")
				.excute()
				.using("${cdn:js(pageContext.request)}/styles/shangpin/js/config.sim.js${ver}")
				.excute()
				.using("${cdn:js(pageContext.request)}/styles/shangpin/js/comm.js${ver}")
				.using("${cdn:js(pageContext.request)}/styles/shangpin/js/collect_brand.js${ver}")
	</script>
</rapid:override >

<rapid:override name="page_title">
	我喜欢的品牌
</rapid:override>
<rapid:override name="search_form">
</rapid:override>
<rapid:override name="content">
	<!-- 收藏列表 -->  
	<input type="hidden" id="pageIndex" name="pageIndex" value="${pageIndex}"/>
	<input type="hidden" id="hasMore" name="hasMore" value="${hasMore}"/>
	<div class="brand-collect-box">
         <ul class="brand-collect-list">
         	<c:forEach var="brand" items="${brands}">
         		<li><a href="${ctx}/brand/product/list?brandNo=${brand.id}" class="clr"><img src="${fn:substring(brand.pic,0,fn:indexOf(brand.pic,'-'))}-128-80.jpg" /><strong>${brand.nameEN}</strong><span>品牌店</span></a></li>
         	</c:forEach>
         </ul>
      </div>
       <c:if test="${fn:length(brands) == 0}">
					<p class="brand_noresults">你还没有我喜欢的品牌呢</p>
				</c:if>

      <rapid:override name="downloadAppShowBottom">
	  </rapid:override>
</rapid:override>

<%@ include file="/WEB-INF/pages/common/another_common_mall_banner.jsp" %> 