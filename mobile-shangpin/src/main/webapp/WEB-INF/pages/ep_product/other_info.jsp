
<%@ page language="java" contentType="text/html; charset=UTF-8"
	trimDirectiveWhitespaces="true" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/pages/common/include.inc.jsp"%>
<!--列表数据start-->
<c:if test="${fn:length(guessLikeList) > 0}">
	<div class="list-box border_bottom">
		<h3>猜您喜欢</h3>
		<div class="prod_list clr">
			<c:forEach var="product" items="${guessLikeList}" varStatus="status">
				<div class="list_box">
					<c:choose>
						<c:when test="${checkAPP}">
							<a 	href="shangPinApp://phone.shangpin/actiongodetail?productid=${product.productId}">
						</c:when>
						<c:otherwise>
							<a href="<c:url value='/product/detail?productNo=${product.productId}'/>">
						</c:otherwise>
					</c:choose>
					<i class="item-country"><img src="${cdn:pic(pageContext.request)}/styles/shangpin/images/e.gif" 	lazy="${product.countryPic }"></i> 
					<%-- <i class="c_tag"><img src="${cdn:pic(pageContext.request)}/styles/shangpin/images/e.gif" lazy="/images/c_tag0${status.index }.png" /></i> --%>
					<c:if test="${product.count eq '0'}">
						<i class="item-status">售罄</i>
					</c:if>
					<img src="${cdn:pic(pageContext.request)}/styles/shangpin/images/e.gif" lazy="${fn:substring(product.pic,0,fn:indexOf(product.pic,'-'))}-306-407.jpg" " />
					</a>
					<div class="li_text">
						<h5>${product.brandNameEN}</h5>
						<p>${product.productName}</p>
						<span class="item-detail"> <c:choose>
								<c:when test="${product.status == '0001' || product.status == '000100' }">
									<strong class="refer-price">&yen; 	${product.promotionPrice }</strong>
								</c:when>
								<c:otherwise>
									<c:choose>
										<c:when test="${product.isSupportDiscount==1 }">
											<c:choose>
												<c:when test="${userLv == '0002'}">
													<strong class="refer-price">&yen;${product.goldPrice}</strong>
												</c:when>
												<c:when test="${userLv == '0003'}">
													<strong class="refer-price">&yen;${product.platinumPrice}</strong>
												</c:when>
												<c:when test="${userLv == '0004'}">
													<strong class="refer-price">&yen;${product.diamondPrice}</strong>
												</c:when>
												<c:otherwise>
													<strong>&yen;${product.limitedPrice}</strong>
												</c:otherwise>
											</c:choose>
										</c:when>
										<c:otherwise>
											<strong class="refer-price">&yen;
												${product.limitedPrice }</strong>
										</c:otherwise>

									</c:choose>
								</c:otherwise>
							</c:choose>
							<div class="item-fame">
							   <c:if test="${product.collections ne null}">
                                    <span class="fame-views">
                                        <i class="fame-views-icon"><img src="${cdn:pic(pageContext.request)}/styles/shangpin/images/home/icon13.png"></i>
                                        <em class="fame-views-num">${product.collections}</em>
                                    </span>
                               </c:if>
                               <c:if test="${product.comments ne null}">
                                    <span class="fame-comments">
                                        <i class="fame-comments-icon"><img src="${cdn:pic(pageContext.request)}/styles/shangpin/images/home/icon12.png"></i>
                                        <em class="fame-comments-num">${product.comments}</em>
                                    </span>
                               </c:if>
                          </div>
						</span>
					</div>
				</div>

			</c:forEach>
		</div>
	</div>
	<!--列表数据end-->
</c:if>
<!--其他服务start-->
<img src="${cdn:pic(pageContext.request)}/styles/shangpin/images/e.gif"
	lazy="${cdn:pic(pageContext.request)}/styles/shangpin/images/product/pic_epservice00.jpg"  class="service_pic" />
<!--其他服务end-->
</div>
