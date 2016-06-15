<%@ page language="java" contentType="text/html; charset=UTF-8" trimDirectiveWhitespaces="true"
    pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/pages/common/include.inc.jsp"%>

<rapid:override name="custum">
	<link rel="stylesheet" href="${ctx}/styles/shangpin/css/page/font-css/font-awesome.min.css"/>	
	<link href="${cdn:css(pageContext.request)}/styles/shangpin/css/page/swiper.min.css" rel="stylesheet" />
	<link href="${cdn:css(pageContext.request)}/styles/shangpin/css/page/home150918.css" rel="stylesheet" />
	<script type="text/javascript" charset="utf-8">
			loader = SP.core
				.install("${cdn:js(pageContext.request)}/styles/shangpin/js/jquery.min.js${ver}")
				.using("${cdn:js(pageContext.request)}/styles/shangpin/js/lazyload.js${ver}")
				.excute()
				.using("${cdn:js(pageContext.request)}/styles/shangpin/js/config.sim.js${ver}")
				.using("${cdn:js(pageContext.request)}/styles/shangpin/js/swiper.min.js${ver}" )
				.excute()
				.using("${cdn:js(pageContext.request)}/styles/shangpin/js/comm.js${ver}")
				.using("${cdn:js(pageContext.request)}/styles/shangpin/js/masonry-docs.min.js${ver}")
				.using("${cdn:js(pageContext.request)}/styles/shangpin/js/jquery.infinitescroll.js${ver}")
				.using("${cdn:js(pageContext.request)}/styles/shangpin/js/downloadAppShow.js${ver}")
				.using("${cdn:js(pageContext.request)}/styles/shangpin/js/coupon_product_list.js${ver}")
				
	</script>
</rapid:override >

<%-- 浏览器标题 --%>
<rapid:override name="title">
	优惠券可用商品
</rapid:override>
<rapid:override name="page_title">
	优惠券可用商品
</rapid:override>

<%--内容体 --%>
<rapid:override name="content">
	<div id="masonry" class="container-fluid recommend-list">
	     	<c:forEach var="product" items="${recProductFor.list}">
			         <div class="waterfull-list">
			        	<c:choose>
							<c:when test="${checkAPP}">
								<a  href="shangPinApp://phone.shangpin/actiongodetail?productid=${product.productId}">
							</c:when>
							<c:otherwise>
								<a href="<c:url value='/product/detail?productNo=${product.productId}'/>">
							</c:otherwise>
						</c:choose>
			            <img src="${fn:substring(product.pic,0,fn:indexOf(product.pic,'-'))}-306-407.jpg" />
						<div class="li_text">
						<h5>${product.brandNameEN}</h5>
						<p>${product.productName}</p>
						<span class="item-detail">
		                    <strong class="refer-price">
		                     <c:choose>
									<c:when test="${product.isPromotion ==1}">
										<em class="red">&yen;	${product.promotionPrice }</em>
									</c:when>
									<c:otherwise>
										<c:choose>
											<c:when test="${product.isSupportDiscount==1 }">
												<c:choose>
													<c:when test="${userLv == '0002'}">
															&yen;${product.goldPrice}
													</c:when>
													<c:when test="${userLv == '0003'}">
															&yen;${product.platinumPrice}>
													</c:when>
													<c:when test="${userLv == '0004'}">
															&yen;${product.diamondPrice}
													</c:when>
													<c:otherwise>
															&yen;${product.limitedPrice}
													</c:otherwise>
												</c:choose>
											</c:when>
											<c:otherwise>
													&yen; ${product.limitedPrice  }
											</c:otherwise>
										</c:choose>
									</c:otherwise>
								</c:choose>
		                    </strong>
		                    <!--<em class="promotion-price">预热价：¥870</em>-->
		                    <div class="item-fame">
		                    <c:if test="${product.collections != '' && product.collections != null && product.collections*1>0}">
		                    	
			                        <span class="fame-views">
			                            <i class="fame-views-icon"><img src="${cdn:pic(pageContext.request)}/styles/shangpin/images/e.gif" lazy="${cdn:pic(pageContext.request)}/styles/shangpin/images/home/icon13.png"></i>
			                            <em class="fame-views-num">90</em>
			                        </span>
		                        </c:if>
		                        <c:if test="${product.comments != '' && product.comments != null && product.comments*1>0}">
		                        <span class="fame-comments">
		                            <i class="fame-comments-icon"><img src="${cdn:pic(pageContext.request)}/styles/shangpin/images/e.gif" lazy="${cdn:pic(pageContext.request)}/styles/shangpin/images/home/icon12.png"></i>
		                            <em class="fame-comments-num">87</em>
		                        </span>
		                        </c:if>
		                    </div>
		                </span>
		               </div>
			       	</a>
			     </div>
	        </c:forEach>
	    <input type="hidden" id="pageIndex" name="pageIndex" value="1"/>
		<input type="hidden" id="hasMore" name="hasMore" value="1"/>
		<input type="hidden" id="payAmount" name="payAmount" value="${payAmount }"/>
		</div>
		<div class="loading"><img src="${cdn:pic(pageContext.request)}/styles/shangpin/images/loading1.gif"><span>正在加载...</span></div><!---->
    <div id="navigation"><a href="#"></a></div> <!---->
</rapid:override>


<%@ include file="/WEB-INF/pages/common/bottom_common_mall_banner.jsp" %> 

