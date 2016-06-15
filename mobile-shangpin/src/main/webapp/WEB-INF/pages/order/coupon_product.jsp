<%@ page language="java" contentType="text/html; charset=UTF-8" trimDirectiveWhitespaces="true"
    pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/pages/common/include.inc.jsp"%>
<c:if test="${recProductFor.list!=null}">
	<c:if test="${fn:length(recProductFor.list) >0}">
	    <h6 class="coupons_h6">该优惠券可购买<a href="${ctx }/coupon/product/list?payAmount=${payAmount}" class="right">查看更多</a></h6></br>
	          <div class="swiper-container swiper-container-horizontal" id="swiper-container1">
	             <div class="swiper-wrapper">
	             	<c:forEach var="product" items="${recProductFor.list}">                
	                 <div class="swiper-slide">                          
	                    <c:choose>
							<c:when test="${checkAPP}">
								<a  href="shangPinApp://phone.shangpin/actiongodetail?productid=${product.productId}">
							</c:when>
							<c:otherwise>
								<a href="<c:url value='/product/detail?productNo=${product.productId}'/>">
							</c:otherwise>
						</c:choose>
							<div class="div_img"><img src="${fn:substring(product.pic,0,fn:indexOf(product.pic,'-'))}-180-220.jpg"" width="100%"></div>
	                         <div class="text_con">
	                             <h3>${product.brandNameEN }</h3>
	                             <p class="title_name">${product.productName }</p>
	                            
	                              <c:choose>
									<c:when test="${product.isPromotion ==1}">
									 <p style="color:red">
										&yen;	${product.promotionPrice }
									</p>
									</c:when>
									<c:otherwise>
									 <p>
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
										</p>
									</c:otherwise>
									
									
								</c:choose>
								
	                          
	                         </div>
	                     </a>
	           		</div>
	           	</c:forEach>	
				</div>
			</div>
			
	</c:if>
</c:if>