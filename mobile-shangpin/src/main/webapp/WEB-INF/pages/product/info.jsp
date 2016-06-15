 <%@ page language="java" contentType="text/html; charset=UTF-8"
	trimDirectiveWhitespaces="true" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/pages/common/include.inc.jsp"%>
 
 <div class="product_info">
        <div>
          <ul class="tab_info">
            <li class="curr" id="product_tab_1"><span>商品详情</span></li>
            <li id="product_tab_2"><span>图片详情</span></li>
            <c:if test="${productDetail.basic.isSize=='1'}">
           		<li id="product_tab_3"><span>尺码及试穿</span></li>
            </c:if>
            <li id="product_tab_5"><span>保养售后</span></li>
          </ul>
        </div>
        <div class="content_info">
          <div class="content_detail content_list">
            <ul class="info_base">
             <c:forEach var="info" items="${productDetail.basic.info }">
               <c:choose>
               		 <c:when test="${fn:indexOf(info,':')>-1 }">
			             <li><span><strong>${fn:substring(info,0,fn:indexOf(info,":")) }</strong>：</span>${fn:substring(info,fn:indexOf(info,":")+1,fn:length(info)) } &nbsp;</li>
			         </c:when>
			          <c:when test="${fn:indexOf(info,'：')>-1 }">
			              <li><span><strong>${fn:substring(info,0,fn:indexOf(info,"：")) }</strong>：</span>${fn:substring(info,fn:indexOf(info,"：")+1,fn:length(info)) }&nbsp;</li>
			            </c:when>
			          	<c:otherwise>
			          	 <li><span><strong>${fn:substring(info,0,fn:indexOf(info,":")) }</strong>：</span>${fn:substring(info,fn:indexOf(info,":")+1,fn:length(info)) }&nbsp;</li>
			          	</c:otherwise>
		        </c:choose>
           	 </c:forEach>
            </ul>
    		</div>
          <div class="content_size content_list" id="tabs_txt1" style="display:none">
             <section>
                <header>${productDetail.basic.recommend }</header>
                <article>
                 <c:forEach var="pic" items="${productDetail.basic.allPics}">
				     <img src="${fn:replace(pic,'-10-10','-600-758')}">
		        </c:forEach>
                </article>
              </section>
          </div>
          <div class="content_size content_list nobottom border_bottom" id="tabs_txt0" style="display:none">
          </div>
          <div class="content_rand content_list nobottom border_bottom" id="tabs_txt3" style="display:none">
         	${productTemplate.html }
          </div>
          <!--保养及售后start-->
          <div class="content_rand content_list"  id="tabs_txt4" style="display:none">
           ${productTemplate.html }
          </div>
          
         </div>
      </div> 
        <!--保养及售后end-->       
        
        <!-- 品牌店入口 start -->
			<div class="brand-shop-enter">
				<h3 class="brand-shop-name clr">
					<span>${productDetail.basic.brand.nameEN } 品牌店</span><a
						class="story-btn"
						href="<c:url value='/brand/product/list?brandNo=${productDetail.basic.brand.id }&brandName=${productDetail.basic.brand.nameEN }'/>">进店逛逛</a>
					<a class="enter-btn" href="<c:url value='/brandStory/index?id=${productDetail.basic.brand.id }&type=0'/>">品牌故事</a>
				</h3>
				<c:choose>
                <c:when test="${(productDetail.basic.brand.allNum  ne -1) && (productDetail.basic.brand.newestNum ne -1) && (productDetail.basic.brand.collections ne -1)}">
					<ul class="brand-shop-list">
						<li><em>${productDetail.basic.brand.allNum }</em> <span>全部商品</span>
						</li>
						<li><em>${productDetail.basic.brand.newestNum}</em> <span>上新</span>
						</li>
						<li><em>${productDetail.basic.brand.collections}</em> <span>喜欢人数</span>
						</li>
					</ul>
				</c:when>
				</c:choose>
			</div>
		<!-- 品牌店入口 end -->
        
        
        
        
        <%-- <!--其他服务start-->
       <c:choose>
		 <c:when test="${fn:length(guessLikeList) > 0}">
		   <ul class="other_service">
		 </c:when>
		 <c:otherwise>
		   <ul class="other_service nobottom border_bottom">
		 </c:otherwise>
 		</c:choose>
          <li>
            <img src="${cdn:pic(pageContext.request)}/styles/shangpin/images/e.gif" lazy="${cdn:pic(pageContext.request)}/styles/shangpin/images/detail/pic_service01.png" />
          </li>
          <li>
            <img src="${cdn:pic(pageContext.request)}/styles/shangpin/images/e.gif" lazy="${cdn:pic(pageContext.request)}/styles/shangpin/images/detail/pic_service02.png" />
          </li>
          <li>
            <img src="${cdn:pic(pageContext.request)}/styles/shangpin/images/e.gif" lazy="${cdn:pic(pageContext.request)}/styles/shangpin/images/detail/pic_service03.png" />
          </li>
        </ul>
        <!--其他服务end--> --%>
		<c:if test="${fn:length(guessLikeList) > 0}">
		 <div class="list-box border_bott list-bottom-bg">
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
		                              <span  class="item-detail" >
				                           <c:choose>
												<c:when test="${product.status == '0001' || product.status == '000100' }">
													<strong class="refer-price" >&yen;	${product.promotionPrice }</strong>
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
																	<strong class="refer-price">&yen;${product.limitedPrice}</strong>
																</c:otherwise>
														</c:choose>
													</c:when>
													<c:otherwise>
														<strong class="refer-price" >&yen; ${product.limitedPrice  }</strong>
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
          <img src="${cdn:pic(pageContext.request)}/styles/shangpin/images/e.gif" lazy="${cdn:pic(pageContext.request)}/styles/shangpin/images/product/pic_service00.jpg" class="service_pic"/>
          
    </div>
