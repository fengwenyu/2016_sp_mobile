<%@ page language="java" contentType="text/html; charset=UTF-8"
	trimDirectiveWhitespaces="true" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/pages/common/include.inc.jsp"%>

<rapid:override name="custum">
<style>
.main_box img{ width:100%}
</style>
<script type="text/javascript" charset="utf-8">
	loader = SP.core
	.install("${cdn:js(pageContext.request)}/styles/shangpin/js/jquery.min.js${ver}")
	.using("${cdn:js(pageContext.request)}/styles/shangpin/js/lazyload.js${ver}")
	.using("${cdn:js(pageContext.request)}/styles/shangpin/js/jquery.easing.min.js${ver}")
	.using("${cdn:js(pageContext.request)}/styles/shangpin/js/jQueryRotate.2.2.js${ver}")
	.excute()
	.using("${cdn:js(pageContext.request)}/styles/shangpin/js/config.sim.js${ver}")
	.excute()
	.using("${cdn:js(pageContext.request)}/styles/shangpin/js/xmas.dialogs.js${ver}")
	.using("${cdn:js(pageContext.request)}/styles/shangpin/js/downloadAppShowMeet.js${ver}")
    .using("${cdn:js(pageContext.request)}/styles/shangpin/js/meet/single/rules.js${ver}")
    .excute(function(){
		isHome(false);
	});
	</script>
<link href="${cdn:css(pageContext.request)}/styles/shangpin/css/page/20150715Bags.css${ver}" rel="stylesheet" />
</rapid:override>
<rapid:override name="header">
</rapid:override>
<rapid:override name="content">
<h1>品牌链接：	
		<c:choose>
				<c:when test="${checkAPP}">
					<a href="shangpinapp://phone.shangpin/actiongobrandlist?title=尚品&filters=brand_B1061&&brandid=B1061">
				</c:when>
			<c:otherwise>
				<a href="${ctx }/brand/product/list?brandNo=B1061&postArea=0" onclick="_smq.push(['custom',${product.productId},${searchConditions.categoryName},${product.productName}]);">
			</c:otherwise>
		</c:choose> 
		点我点我</a>	
</h1>
<br/>
<h1>品类链接：<c:choose>
				<c:when test="${checkAPP}">
					<a href="shangpinapp://phone.shangpin/actiongocatelist?title=尚品&filters=category_A01B02&categoryid=A01B02">
				</c:when>
			<c:otherwise>
				<a href="${ctx }/category/product/list?categoryNo=A01B02&postArea=0" onclick="_smq.push(['custom',${product.productId},${searchConditions.categoryName},${product.productName}]);">
			</c:otherwise>
		</c:choose> 
		点我点我</a>	
</h1>
<br/>
<h1>活动链接：	
		<c:choose>
				<c:when test="${checkAPP}">
					<a href="shangpinapp://phone.shangpin/actiongoactivitylist?title=尚品&activityid=40812532">
				</c:when>
			<c:otherwise>
				<a href="${ctx }/subject/product/list?topicId=40812532&postArea=0" onclick="_smq.push(['custom',${product.productId},${searchConditions.categoryName},${product.productName}]);">
			</c:otherwise>
		</c:choose> 
		点我点我</a>	
</h1>
<br/>
<h1>商品详情链接：	
		<c:choose>
				<c:when test="${checkAPP}">
					<a href="shangpinapp://phone.shangpin/actiongodetail?title=商品详情&productid=30003628">
				</c:when>
			<c:otherwise>
				<a href="${ctx }/product/detail?productNo=30003628&picNo=20150907170833222432" onclick="_smq.push(['custom',${product.productId},${searchConditions.categoryName},${product.productName}]);">
			</c:otherwise>
		</c:choose> 
		点我点我</a>	
</h1>
</rapid:override>

 
<rapid:override name="footer">
</rapid:override>
<%@ include file="/WEB-INF/pages/common/base_meet.jsp" %> 
