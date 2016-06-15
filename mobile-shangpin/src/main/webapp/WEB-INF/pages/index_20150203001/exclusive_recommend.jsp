<%@ page language="java" contentType="text/html; charset=UTF-8"
	trimDirectiveWhitespaces="true" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/pages/common/include.inc.jsp"%>
 <c:if test="${fn:length(recProductItem.list) >0}">
	
	<!-- 为您推荐 -->
	
	<div class="recommend-box">
	<h3 class="recommend-date clr"><time><em>${date}/${month*1+1 }</em><span>周<c:choose>
	    	<c:when test="${week*1==1 }">日</c:when>
	    	<c:when test="${week*1==2}">一</c:when>
	    	<c:when test="${week*1==3}">二</c:when>
	    	<c:when test="${week*1==4}">三</c:when>
	    	<c:when test="${week*1==5}">四</c:when>
	    	<c:when test="${week*1==6}">五</c:when>
	    	<c:when test="${week*1==7}">六</c:when>
	    	<c:otherwise></c:otherwise></c:choose></span></time><p>你的专属推荐<br><strong>${time }</strong>更新</p></h3>
	    	<div id="masonry" class="container-fluid recommend-list">
	     	<c:forEach var="product" items="${recProductItem.list}">
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
									<c:when test="${product.status == '0001' || product.status == '000100' }">
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
															&yen;${product.platinumPrice}
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
		</div>
		
	<div class="loading"><img src="${cdn:pic(pageContext.request)}/styles/shangpin/images/loading1.gif"><span>正在加载...</span></div><!---->
    <div id="navigation"><a href="${ctx }/index/exclusive/recommend/more?pageIndex=1"></a></div> <!---->
</div>
</c:if>

