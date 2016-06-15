<%@ page language="java" contentType="text/html; charset=UTF-8" trimDirectiveWhitespaces="true"
    pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/pages/common/include.inc.jsp"%>

<rapid:override name="custum">
	<link href="${cdn:css(pageContext.request)}/styles/shangpin/css/page/font-css/font-awesome.min.css${ver}" rel="stylesheet" />
	<link href="${cdn:css(pageContext.request)}/styles/shangpin/css/page/new_arrival.css${ver}" rel="stylesheet" />
	<script type="text/javascript" charset="utf-8">
			loader = SP.core
				.install("${cdn:js(pageContext.request)}/styles/shangpin/js/zepto.1.1.3.js${ver}")
				.using("${cdn:js(pageContext.request)}/styles/shangpin/js/lazyload.js${ver}")
				.excute()
				.using("${cdn:js(pageContext.request)}/styles/shangpin/js/config.sim.js${ver}")
				.excute()
				.using("${cdn:js(pageContext.request)}/styles/shangpin/js/comm.js${ver}")
				.using("${cdn:js(pageContext.request)}/styles/shangpin/js/downloadAppShow.js${ver}")
				.using("${cdn:js(pageContext.request)}/styles/shangpin/js/newArrive.js${ver}")
				.excute()
	</script>
</rapid:override ><rapid:override name="title">
    	新品到货
</rapid:override>
<rapid:override name="downloadAppShowBottom">
</rapid:override>
<rapid:override name="page_title">
	 新品到货
</rapid:override>
<rapid:override name="appLayer">
</rapid:override>
<rapid:override name="content">


	<c:if test="${newGoodsList != null&&fn:length(newGoodsList)>0}">
	<c:forEach var="newGoods" items="${newGoodsList }">
		<c:forEach var="brandList" items="${newGoods.brandList }">
			  <div class="new-arrival-box clr">
		        <h2 class="title"><span class="new-times" new-times="${newGoods.time }" ><times></times></span>／${brandList.nameEN } ${brandList.nameCN } 
		             <c:choose>
				        <c:when test="${checkAPP}">
							<a href="shangpinapp://phone.shangpin/actiongobrandlist?title=尚品&filters=brand_${brandList.refContent}&brandid=${brandList.refContent}"   class="all-new">
						</c:when>
						<c:otherwise>
							<a href="${ctx }/brand/product/list?brandNo=${brandList.refContent}&postArea=0"  class="all-new">
						</c:otherwise>
					</c:choose>全部新品<i class="icon-angle-right"></i></a></h2>
		        <h3 class="subtitle"><em>${brandList.count }件上新</em> 经典系列款上新！</h3>
		        <ul class="new-arrival-list">
		        	<c:forEach var="product" items="${brandList.productList }">
		        	 <li>
		        		<c:choose>
					        <c:when test="${checkAPP}">
								<a href="shangpinapp://phone.shangpin/actiongobrandlist?title=尚品&filters=brand_${brandList.refContent}&brandid=${brandList.refContent}"   class="all-new">
							</c:when>
							<c:otherwise>
								<a href="${ctx }/brand/product/list?brandNo=${brandList.refContent}&postArea=0"  class="all-new">
							</c:otherwise>
						</c:choose>
							<c:if test="${product.postArea=='2'}">
							  <i><img alt="" src="${cdn:pic(pageContext.request)}/styles/shangpin/images/icon_plane.png">
							</c:if>
		        </i>
		        <img alt="" src="${fn:replace(product.pic,'-10-10','-206-274') }" width="100%">
		        
			     <c:choose>
						<c:when test="${product.status == '0001' || product.status == '000100' }">
							&yen;	${product.promotionPrice }
						</c:when>
						<c:otherwise>
							<c:choose>
								<c:when test="${product.isSupportDiscount==1 }">
									<c:choose>
										<c:when test="${userLv == '0002'}">
												<span>&yen;${product.goldPrice}  </span>
										</c:when>
										<c:when test="${userLv == '0003'}">
												<span>&yen;${product.platinumPrice}</span>
										</c:when>
										<c:when test="${userLv == '0004'}">
												<span>&yen;${product.diamondPrice}</span>
										</c:when>
										<c:otherwise>
												<span>&yen;${product.limitedPrice}</span>
										</c:otherwise>
									</c:choose>
								</c:when>
								<c:otherwise>
										<span>&yen; ${product.limitedPrice  }</span>
								</c:otherwise>
						</c:choose>
					</c:otherwise>
				</c:choose>
		        </a>
		  </li>
		</c:forEach>
			            
		</ul>
		</div>
			
	<div class="line"></div>
	</c:forEach>
	</c:forEach>
</c:if>
</rapid:override>


<%@ include file="/WEB-INF/pages/common/bottom_common_mall_banner.jsp" %> 