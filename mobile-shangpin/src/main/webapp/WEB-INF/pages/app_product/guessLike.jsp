<%@ page language="java" contentType="text/html; charset=UTF-8" trimDirectiveWhitespaces="true"
    pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/pages/common/include.inc.jsp"%>
 <c:if test="${productDetail.basic.isAfterSale=='1'}">
        <!--保养及售后start-->
        <div class="product_introduction">
          <a href="ShangPinApp://phone.shangpin/actiongowebview?title=保养及售后&url=${basePath }/product/template?type=38uuuuu8productNo=${productNo }" class="maintenance">
            <span>保养及售后</span>
          </a>
        </div>
          <!--保养及售后end-->
   </c:if>
<c:if test="${not empty productDetail.basic.brand}">
	<c:if test="${not empty productDetail.basic.brand.id}">
	 <div class="brand-shop-enter">
	            <h3 class="brand-shop-name clr"><span>${productDetail.basic.brand.nameEN}品牌店</span><a class="enter-btn" href="shangpinapp://phone.shangpin/actiongobrandlist?brandid=${productDetail.basic.brand.id}&title=${productDetail.basic.brand.title}">进店逛逛</a></h3>
	 </div>
	</c:if>
</c:if>
<c:if test="${fn:length(guessLikeList) > 0}">
	<div class="list-box list-bottom-bg">
          <h3>猜您喜欢</h3>
                  <div class="prod_list clr">
                  <c:forEach var="product" items="${guessLikeList}" varStatus="status">
                      <div class="list_box">
                         <c:choose>
									<c:when test="${checkAPP}">
										<a  href="shangPinApp://phone.shangpin/actiongodetail?productid=${product.productId}">
									</c:when>
									<c:otherwise>
										<a href="<c:url value='/product/detail?productNo=${product.productId}'/>">
									</c:otherwise>
						</c:choose>
                         
                            
                             <img src="${cdn:pic(pageContext.request)}/styles/shangpin/images/e.gif" lazy="${fn:substring(product.pic,0,fn:indexOf(product.pic,'-'))}-306-407.jpg"" />
                            <div class="li_text">
                              <h5>${product.brandNameEN}</h5>
                              <p>${product.productName}</p>
                              <span>
		                           <c:choose>
										<c:when test="${product.status == '0001' || product.status == '000100' }">
											<strong class="red">&yen;	${product.promotionPrice }</strong>
											<em>
												<c:if test="${product.promotionPrice*1<product.marketPrice }">
												&yen;${product.marketPrice}
												</c:if>
											</em>
										</c:when>
										<c:otherwise>
											<c:choose>
												<c:when test="${product.isSupportDiscount==1 }">
													<c:choose>
														<c:when test="${userLv == '0002'}">
															<strong>&yen;${product.goldPrice}</strong>
															<em>
																<c:if test="${product.goldPrice*1<product.marketPrice }">
																	&yen;${product.marketPrice}
																</c:if>
															</em>
														</c:when>
														<c:when test="${userLv == '0003'}">
															<strong>&yen;${product.platinumPrice}</strong>
															<em>
																<c:if test="${product.platinumPrice*1<product.marketPrice }">
																	&yen;${product.marketPrice}
																</c:if>
															</em>
														</c:when>
														<c:when test="${userLv == '0004'}">
															<strong>&yen;${product.diamondPrice}</strong>
															<em>
																<c:if test="${product.diamondPrice*1<product.marketPrice }">
																	&yen;${product.marketPrice}
																</c:if>
															</em>
														</c:when>
														<c:otherwise>
															<strong>&yen;${product.limitedPrice}</strong>
															<em>
																<c:if test="${product.limitedPrice*1<product.marketPrice }">
																	&yen;${product.marketPrice}
																</c:if>
															</em>
														</c:otherwise>
												</c:choose>
											</c:when>
											<c:otherwise>
												<strong>&yen; ${product.limitedPrice  }</strong>
												<em>
													<c:if test="${product.limitedPrice*1<product.marketPrice }">
														&yen;${product.marketPrice}
													</c:if>
												</em>
											</c:otherwise>
											
											</c:choose>
										</c:otherwise>
								</c:choose>
                              </span>
                            </div>
                          </a>
                      </div>
                   </c:forEach> 
                    </div>  
                  </div>
                  
                  <c:choose>
		<c:when test="${productDetail.postArea=='2' }">
        <!--其他服务start-->
         <c:choose>
			 <c:when test="${fn:length(guessLikeList) > 0}">

			 </c:when>
			 <c:otherwise>

			 </c:otherwise>
		 </c:choose>
       

            <img style="min-height: 76px;" src="${cdn:pic(pageContext.request)}/styles/shangpin/images/e.gif" lazy="${cdn:pic(pageContext.request)}/styles/shangpin/images/detail/pic_epservice001.jpg" />

        </c:when>
        <c:otherwise>
           <!--其他服务start-->
	          <c:choose>
				 <c:when test="${fn:length(guessLikeList) > 0}">
				 
				 </c:when>
				 <c:otherwise>

				 </c:otherwise>
		 		</c:choose>
       
	            <img style="min-height: 76px;" src="${cdn:pic(pageContext.request)}/styles/shangpin/images/e.gif" lazy="${cdn:pic(pageContext.request)}/styles/shangpin/images/detail/pic_service00.jpg" />

        <!--其他服务end-->
        </c:otherwise>
      </c:choose>
                  
                  
               </div>
            
        <!--列表数据end-->
      </div>
 </c:if> 
 	