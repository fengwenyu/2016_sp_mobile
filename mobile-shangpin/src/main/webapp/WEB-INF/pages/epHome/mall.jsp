<%@ page language="java" contentType="text/html; charset=UTF-8" trimDirectiveWhitespaces="true"
    pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/pages/common/include.inc.jsp"%>
   <c:if test="${firstCategory!=null }">
<div class="swiper-nav-box">
          <div class="swiper-container swiper-container-horizontal" id="swiperNav">
              <div class="swiper-wrapper">
                  <c:forEach var="first" items="${firstCategory }" varStatus="status">
              			<c:choose>
              				<c:when test="${status.index*1==0 }">
              					<div class="swiper-slide" id="${first.shopCategoryNo }"><a class="cur" href="javascript:;">${first.shopCategoryName }</a></div>
              				</c:when>
              				<c:otherwise>
              					<div class="swiper-slide" id="${first.shopCategoryNo }"><a href="javascript:;">${first.shopCategoryName }</a></div>
              				</c:otherwise>
              			</c:choose>
              		</c:forEach>
              </div>
          </div>
        </div>
  </c:if>
  
 <div class="swiper-container swiper-container-horizontal" id="swiperList">
            <div class="swiper-wrapper">
              <c:forEach var="first" items="${firstCategory }" varStatus="status">
                <input type="hidden" id="${status.index }_start" name="${status.index }_start" value="1"/>
				<input type="hidden" id="${status.index }_hasMore" name="${status.index }_hasMore" value=${hasMore }/>
				<input type="hidden" id="${status.index }_firstCategoryNO" name="${status.index }_firstCategoryNO" value="${first.shopCategoryNo }"/> 
              	<c:choose>
            		<c:when test="${status.index*1==0 }">
				                	<div class="swiper-slide">
				                    <div class="list-box">
				                        <c:choose>
				                    		<c:when test="${status.index*1==0 }">
				                    		 	<ul class="shoes-list clr">
				                    			<c:forEach var="second" items="${secondCategory }">
			              							<li><a href="${ctx }/category/product/list?categoryNo=${second.shopCategoryNo}&postArea=2"><i class="icon-caret-right"></i>${second.shopCategoryName }</a></li> 
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
				                    								<div class="list-img"><img alt="" src="${fn:substring(product.pic,0,fn:indexOf(product.pic,'-'))}-83-111.jpg"></div>
				                    								<div class="list-text">
					                    								<h3>${product.brandEnName }</h3>
						                    							<p>${product.productName }&nbsp;${product.adverTitle }</p>
					                    								<div class="country-logo">
						                    								<!--<c:choose>
						                    									<c:when test="${fn:length(product.postAreaPic)!=0}">
						                    										<i><img src="${product.countryPic}"></i>
						                    									</c:when>
						                    									<c:when test="${fn:length(product.postAreaId)==0}">
					                    										<i><img src="http://pic13.shangpin.com/shangpin/images/public/areaflag/xianggang.png"></i>
					                    									</c:when>
						                    								</c:choose>
						                    								-->
					                    									<c:choose>
					                    										<c:when test="${product.isNewSeasonal=='1' }">
					                    											<em class="hot">新品</em>
					                    										</c:when>
					                    										<c:when test="${product.hotValue*1>20 }">
					                    											<em class="hot">热卖</em>
					                    										</c:when>
					                    									</c:choose>
					                    								</div>
				                    									<div class="price-style">
				                    										 <c:choose>
										                                        <c:when test="${product.isSupportDiscount=='1' }">
												                                        <c:choose>
												                                        	<c:when test="${userLV=='0002' }">
												                                        		<b>&yen;${fn:substring(product.sellPrice,0,fn:indexOf(product.sellPrice,'.'))}</b>
<%-- 												                                        		<c:choose> --%>
<%-- 														                                        	<c:when test="${fn:length(product.marketPrice)!=0&&(product.marketPrice-product.sellPrice)>0 }"> --%>
<%-- 														                                        		<em>比国内便宜&yen;${fn:substring(product.marketPrice-product.sellPrice,0,fn:indexOf(product.marketPrice-product.sellPrice,'.'))}</em> --%>
<%-- 														                                        	</c:when> --%>
<%-- 											                                        			</c:choose> --%>
												                                        	</c:when>
												                                        	<c:when test="${userLV=='0003' }">
												                                        		<b>&yen;${fn:substring(product.platinumPrice,0,fn:indexOf(product.platinumPrice,'.'))}</b>
<%-- 												                                        		<c:choose> --%>
<%-- 														                                        	<c:when test="${fn:length(product.marketPrice)!=0&&(product.marketPrice-product.platinumPrice)>0 }"> --%>
<%-- 														                                        		<em>比国内便宜&yen;${fn:substring(product.marketPrice-product.platinumPrice,0,fn:indexOf(product.marketPrice-product.platinumPrice,'.'))}</em> --%>
<%-- 														                                        	</c:when> --%>
<%-- 											                                        			</c:choose> --%>
												                                        	</c:when>
												                                        	<c:when test="${userLV=='0004' }">
												                                        		<b>&yen;${fn:substring(product.diamondPrice,0,fn:indexOf(product.diamondPrice,'.'))}</b>
<%-- 												                                        		<c:choose> --%>
<%-- 														                                        	<c:when test="${fn:length(product.marketPrice)!=0&&(product.marketPrice-product.diamondPrice)>0 }"> --%>
<%-- 														                                        		<em>比国内便宜&yen;${fn:substring(product.marketPrice-product.diamondPrice,0,fn:indexOf(product.marketPrice-product.diamondPrice,'.'))}</em> --%>
<%-- 														                                        	</c:when> --%>
<%-- 											                                        			</c:choose> --%>
												                                        	</c:when>
												                                        	<c:otherwise>
												                                        		<b>&yen;${fn:substring(product.limitedPrice,0,fn:indexOf(product.limitedPrice,'.'))}</b>
<%-- 														                                        	<c:choose> --%>
<%-- 															                                        	<c:when test="${fn:length(product.marketPrice)!=0&&(product.marketPrice-product.limitedPrice)>0 }"> --%>
<%-- 															                                        		<em>比国内便宜&yen;${fn:substring(product.marketPrice-product.limitedPrice,0,fn:indexOf(product.marketPrice-product.limitedPrice,'.'))}</em> --%>
<%-- 															                                        	</c:when> --%>
<%-- 														                                        	</c:choose> --%>
												                                        	</c:otherwise>
												                                        </c:choose>
											                                        </c:when>
									                                      			<c:otherwise>
									                                      					 <b>&yen;${fn:substring(product.limitedPrice,0,fn:indexOf(product.limitedPrice,'.'))}</b>
<%-- 														                                        <c:choose> --%>
<%-- 														                                        	<c:when test="${fn:length(product.marketPrice)!=0&&(product.marketPrice-product.limitedPrice)>0 }"> --%>
<%-- 														                                        		<em>比国内便宜&yen;${fn:substring(product.marketPrice-product.limitedPrice,0,fn:indexOf(product.marketPrice-product.limitedPrice,'.'))}</em> --%>
<%-- 														                                        	</c:when> --%>
<%-- 														                                        </c:choose> --%>
									                                      			</c:otherwise>
									                                      		</c:choose>
				                    									</div>
				                    								</div>
				                    							</a>
				                    					</div>
								                    </c:forEach>
				                        		</c:if>
				                        	</c:when>
				                        	<c:otherwise>
				                        		<div class="content_list" style=" display:block;"></div>
				                        	</c:otherwise>
				                        </c:choose>
				                    </div>
				                </div>
            				</c:when>
            				<c:otherwise>
            					<div class="swiper-slide"></div>
            				</c:otherwise>
              		</c:choose>
              </c:forEach>
            </div>

        </div>
        
       <input type="hidden" id="tabIndex" name="tabIndex" value="0"/>
       <input type="hidden" id="firstCategoryNO" name="firstCategoryNO" value="${firstCategory[0].shopCategoryNo }"/>
       <input type="hidden" id="postAreaNO" name="postAreaNO" value="${postAreaNO }"/>
       <input type="hidden" id="userLv" name="userLv" value="${userLv }"/>
       <input type="hidden" id="checkApp" name="checkApp" value="${checkAPP }"/>