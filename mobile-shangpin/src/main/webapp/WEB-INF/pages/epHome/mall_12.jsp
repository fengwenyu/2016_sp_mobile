<%@ page language="java" contentType="text/html; charset=UTF-8" trimDirectiveWhitespaces="true"
    pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/pages/common/include.inc.jsp"%>
   <div class="swiper-nav-box">
          <div class="swiper-container" id="swiperNav">
              <c:if test="${firstCategory!=null }">
              	<div class="swiper-wrapper">
              		<c:forEach var="first" items="${firstCategory }" varStatus="status">
              			<c:choose>
              				<c:when test="${status.index*1==0 }">
              					<div class="swiper-slide"><a class="cur" href="javascript:;">${first.shopCategoryName }</a></div>
              				</c:when>
              				<c:otherwise>
              					<div class="swiper-slide"><a href="javascript:;">${first.shopCategoryName }</a></div>
              				</c:otherwise>
              			</c:choose>
              		</c:forEach>
              	</div>
              </c:if>
          </div>
    </div>
        <div class="swiper-container" id="swiperList">
            <c:forEach var="first" items="${firstCategory }" varStatus="status">
            	<div class="swiper-wrapper">
               	 <div class="swiper-slide">
                    <div class="list-box">
                    	<c:choose>
                    		<c:when test="${status.index*1==0 }">
                    		 	<ul class="shoes-list clr">
                    			<c:forEach var="second" items="${secondCategory }">
                    				<li><a href="${ctx }/category/product/list?categoryNo=${second.shopCategoryNo}&postArea=1"><i class="icon-caret-right"></i>${second.shopCategoryName }</a></li> 
                    			</c:forEach>
                    			</ul>
                    			<c:if test="${productList!=null }">
                    				<c:forEach var="product" items="${productList }">
                    					<div class="content-list">
                    						<c:choose>
                    							<c:when test="${checkAPP }">
                    								<a href="shangPinApp://phone.shangpin/actiongodetail?productid=${product.productNo}">
                    							</c:when>
                    							<c:otherwise>
                    								<a href="${ctx}/product/detail?productNo=${product.productNo}" class="clr">
                    							</c:otherwise>
                    						</c:choose>
                    						
                    							<div class="list-img"><img alt="" src="${product.productPicUrl }"></div>
                    							<div class="list-text">
                    								<h3>${product.brandEnName }</h3>
                    								<c:choose>
	                    								<c:when test="${fn:length(product.promotionNotice)!=0}">
	                    									<p>${product.productName }&nbsp;${product.promotionNotice }</p>
	                    								</c:when>
	                    								<c:otherwise>
	                    									<p>${product.productName }</p>
	                    								</c:otherwise>
                    								</c:choose>
                    								<div class="country-logo"><i><img src="${product.postAreaPic }"></i>
	                    									<%-- <c:choose>
	                    										<c:when test="${product.isNewSeasonal=='1' }">
	                    											<em class="hot">新品</em>
	                    										</c:when>
	                    										<c:when test="${product.hotValue>20 }">
	                    											<em class="hot">热卖</em>
	                    										</c:when>
	                    									</c:choose> --%>
                    								</div>
                    								<div class="price-style">
				                                        <c:choose>
				                                        <c:when test="${product.isSupportDiscount==1 }">
					                                        <c:choose>
					                                        	<c:when test="${userLV=='0002' }">
					                                        		<b>&yen;${product.sellPrice }</b>
<%-- 					                                        		<c:choose> --%>
<%-- 							                                        	<c:when test="${fn:length(product.marketPrice)!=0&&(product.marketPrice-product.sellPrice)>0 }"> --%>
<%-- 							                                        		<em>比国内便宜&yen;${product.marketPrice-product.sellPrice }</em> --%>
<%-- 							                                        	</c:when> --%>
<%-- 				                                        			</c:choose> --%>
					                                        	</c:when>
					                                        	<c:when test="${userLV=='0003' }">
					                                        		<b>&yen;${product.platinumPrice }</b>
<%-- 					                                        		<c:choose> --%>
<%-- 							                                        	<c:when test="${fn:length(product.marketPrice)!=0&&(product.marketPrice-product.platinumPrice)>0 }"> --%>
<%-- 							                                        		<em>比国内便宜&yen;${product.marketPrice-product.platinumPrice }</em> --%>
<%-- 							                                        	</c:when> --%>
<%-- 				                                        			</c:choose> --%>
					                                        	</c:when>
					                                        	<c:when test="${userLV=='0004' }">
					                                        		<b>&yen;${product.diamondPrice }</b>
<%-- 					                                        		<c:choose> --%>
<%-- 							                                        	<c:when test="${fn:length(product.marketPrice)!=0&&(product.marketPrice-product.diamondPrice)>0 }"> --%>
<%-- 							                                        		<em>比国内便宜&yen;${product.marketPrice-product.diamondPrice }</em> --%>
<%-- 							                                        	</c:when> --%>
<%-- 				                                        			</c:choose> --%>
					                                        	</c:when>
					                                        	<c:otherwise>
					                                        		<b>&yen;${product.limitedPrice }</b>
<%-- 					                                        		<c:choose> --%>
<%-- 							                                        	<c:when test="${fn:length(product.marketPrice)!=0&&(product.marketPrice-product.limitedPrice)>0 }"> --%>
<%-- 							                                        		<em>比国内便宜&yen;${product.marketPrice-product.limitedPrice }</em> --%>
<%-- 							                                        	</c:when> --%>
<%-- 				                                        			</c:choose> --%>
					                                        	</c:otherwise>
					                                        </c:choose>
					                                        </c:when>
					                                        <c:otherwise>
					                                        	 <b>&yen;${product.limitedPrice }</b>
<%-- 						                                        <c:choose> --%>
<%-- 						                                        	<c:when test="${fn:length(product.marketPrice)!=0&&(product.marketPrice-product.limitedPrice)>0 }"> --%>
<%-- 						                                        		<em>比国内便宜&yen;${product.marketPrice-product.limitedPrice }</em> --%>
<%-- 						                                        	</c:when> --%>
<%-- 						                                        </c:choose> --%>
					                                        </c:otherwise>
				                                        </c:choose>
				                                       
				                                        
                                   					</div>
                    							</div>
                    						</a>
                    				
               						 <div class="swiper-slide"></div>
                    				</c:forEach>
                    				
                    			</c:if>
                    			 </div>
                    			 	</div>
                    					
               						 </div>
                    		</c:when>
                    	</c:choose> 
                
                
            </div>
			</c:forEach>
        </div>
<input type="hidden" id="firstCategoryNO" name="firstCategoryNO" value="${firstCategory[0].shopCategoryNo }"/>
<input type="hidden" id="userLv" name="userLv" value="${userLv }"/>
<input type="hidden" id="tabIndex" name="tabIndex" value="0"/>
        



