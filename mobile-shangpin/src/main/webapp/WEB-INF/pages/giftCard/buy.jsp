<%@ page language="java" contentType="text/html; charset=UTF-8"
	trimDirectiveWhitespaces="true" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/pages/common/include.inc.jsp"%>
   <!--弹层显示购买信息start-->
    <div class="alProd"></div>
    <div class="alProdInfo">
      <a href="javascript:;" class="close_btn">关闭</a>
      <div class="clr">
       <div class="commodity_info clr">
        <div class="right_cont">
          <h3>${productDetail.basic.productName }</h3>
          <p>
            	<c:choose>
		<c:when test="${productDetail.basic.firstProps[0].secondProps[0].isPromotion=='0'}">
		  <b class="sales" id="forSalsPrice" >
				<c:choose>
					<c:when test="${productDetail.basic.firstProps[0].secondProps[0].isSupportDiscount==1 }">
						<c:choose>
							<c:when test="${userLv == '0002'}">
								&yen;${productDetail.basic.firstProps[0].secondProps[0].goldPrice }
								<span type="hidden" id ="lowestInfo" name="lowestInfo" sku="${productDetail.basic.firstProps[0].secondProps[0].sku}" isPromotion="${productDetail.basic.firstProps[0].secondProps[0].isPromotion}"  lowestPrice="${productDetail.basic.firstProps[0].secondProps[0].goldPrice}" isExchange="${productDetail.basic.firstProps[0].secondProps[0].isExchange }" marketPrice="${productDetail.basic.firstProps[0].secondProps[0].marketPrice }"></span>		
							</c:when>
							<c:when test="${userLv == '0003'}">
								&yen;${productDetail.basic.firstProps[0].secondProps[0].platinumPrice }
							<span type="hidden" id ="lowestInfo" name="lowestInfo" sku="${productDetail.basic.firstProps[0].secondProps[0].sku}" isPromotion="${productDetail.basic.firstProps[0].secondProps[0].isPromotion}"  lowestPrice="${productDetail.basic.firstProps[0].secondProps[0].platinumPrice}" isExchange="${productDetail.basic.firstProps[0].secondProps[0].isExchange }" marketPrice="${productDetail.basic.firstProps[0].secondProps[0].marketPrice }"></span>			
							</c:when>
							<c:when test="${userLv == '0004'}">
								&yen;${productDetail.basic.firstProps[0].secondProps[0].diamondPrice }
							<span type="hidden" id ="lowestInfo" name="lowestInfo" sku="${productDetail.basic.firstProps[0].secondProps[0].sku}" isPromotion="${productDetail.basic.firstProps[0].secondProps[0].isPromotion}"  lowestPrice="${productDetail.basic.firstProps[0].secondProps[0].diamondPrice}" isExchange="${productDetail.basic.firstProps[0].secondProps[0].isExchange }" marketPrice="${productDetail.basic.firstProps[0].secondProps[0].marketPrice }"></span>			
							</c:when>
							<c:otherwise>
								&yen;${productDetail.basic.firstProps[0].secondProps[0].limitedPrice }
							<span type="hidden" id ="lowestInfo" name="lowestInfo" sku="${productDetail.basic.firstProps[0].secondProps[0].sku}" isPromotion="${productDetail.basic.firstProps[0].secondProps[0].isPromotion}"  lowestPrice="${productDetail.basic.firstProps[0].secondProps[0].limitedPrice}" isExchange="${productDetail.basic.firstProps[0].secondProps[0].isExchange }" marketPrice="${productDetail.basic.firstProps[0].secondProps[0].marketPrice }"></span>			
							</c:otherwise>
						</c:choose>
					</c:when>
					<c:otherwise>
						&yen;${productDetail.basic.firstProps[0].secondProps[0].limitedPrice }
						<span type="hidden" id ="lowestInfo" name="lowestInfo" sku="${productDetail.basic.firstProps[0].secondProps[0].sku}" isPromotion="${productDetail.basic.firstProps[0].secondProps[0].isPromotion}"  lowestPrice="${productDetail.basic.firstProps[0].secondProps[0].limitedPrice}" isExchange="${productDetail.basic.firstProps[0].secondProps[0].isExchange }" marketPrice="${productDetail.basic.firstProps[0].secondProps[0].marketPrice }"></span>			
					
					</c:otherwise>
				</c:choose>
		  </b>
		</c:when>
		<c:otherwise>
		 <b class="sales" id="forSalsPrice" >
			 &yen;${productDetail.basic.firstProps[0].secondProps[0].promotionPrice }
		 </b>
		</c:otherwise>
	</c:choose>
          <p>
          <div class="choice_product"><span></span><span></span></div>
        </div>
      </div>
	 </div>
      <div class="color_info">
        <h3>${productDetail.basic.firstPropName }</h3>
        <ul>
        <c:forEach var="first" items="${productDetail.basic.firstProps}" varStatus="status">
        <c:choose>
	        <c:when test="${first.isSecondProp =='0'}">
	        	<c:choose>
	        	<c:when test="${first.secondProps[0].count*1<1 }">
	        	 <li id="first_${status.index }" isSecondProp="${first.isSecondProp }" class="soldOut">
	        	</c:when>
	        	<c:otherwise>
	        	 <li id="first_${status.index }" isSecondProp="${first.isSecondProp }">
	        	</c:otherwise>
	        	</c:choose>
	        </c:when>
	        <c:otherwise>
	            <li id="first_${status.index }" isSecondProp="${first.isSecondProp }">
	        </c:otherwise>
        </c:choose>
	       		<img src="${fn:replace(first.icon,'-10-10','-98-132')}" width="49" height="66" data-lazy-uid="0_1427437131388">
	            <p>${first.firstProp }</p>
	            
	          </li>
          </c:forEach>
        </ul>
      </div>
    <c:forEach var="first" items="${productDetail.basic.firstProps}" varStatus="status">
    	<c:choose>
    			<c:when test="${first.isSecondProp=='1'}"><%--是否有第二属性 --%>
			    <c:choose>
			    	<c:when test="${status.index==0 }">
			    	  	<div class="size_info" id="second_first_${status.index }">
			    	</c:when>
			    	<c:otherwise>
			    		 <div class="size_info" id="second_first_${status.index }" style="display:none">
			    	</c:otherwise>
			    </c:choose>
		        <h3>${productDetail.basic.secondPropName }</h3>   
		        <ul>
			            <c:forEach var="second" items="${first.secondProps}" varStatus="secondStatus">
			            <c:choose>
			            	<c:when test="${second.count*1>0 }">
			            	 <li>
			            	</c:when>
			            	<c:otherwise>
			            	 <li class="soldOut">
			            	</c:otherwise>
			            </c:choose>
				             	${second.secondProp }
				             	<input type="hidden" id ="isExchange" name="isExchange" value="${second.isExchange }"/>
				             	<input type="hidden" id ="marketPrice" name="marketPrice" value="${second.marketPrice }"/>
				             	<input type="hidden" id ="isPromotion" name="isPromotion" value="${second.isPromotion }"/>
				             	<input type="hidden" id ="sku" name="sku" value="${second.sku }"/>
				             	<input type="hidden" id ="count" name="count" value="${second.count }"/>
				             	<c:choose>
										<c:when test="${second.isPromotion == '1'}">
												<input type="hidden" id ="salePrice" name="salePrice" value="${second.promotionPrice }"/>
										</c:when>
										<c:otherwise>
											<c:choose>
												<c:when test="${second.isSupportDiscount==1 }">
													<c:choose>
														<c:when test="${userLv == '0002'}">
																<input type="hidden" id ="salePrice" name="salePrice" value="${second.goldPrice }"/>
														</c:when>
														<c:when test="${userLv == '0003'}">
															<input type="hidden" id ="salePrice" name="salePrice" value="${second.platinumPrice }"/>
														</c:when>
														<c:when test="${userLv == '0004'}">
															<input type="hidden" id ="salePrice" name="salePrice" value="${second.diamondPrice }"/>
														</c:when>
														<c:otherwise>
															<input type="hidden" id ="salePrice" name="salePrice" value="${second.limitedPrice }"/>
														</c:otherwise>
													</c:choose>
												</c:when>
												<c:otherwise>
													<input type="hidden" id ="salePrice" name="salePrice" value="${second.limitedPrice }"/>
													
												</c:otherwise>
											</c:choose>
										</c:otherwise>
								</c:choose>
									
			            	 </li>
				      	</c:forEach>
		        </ul>
		      </div>
		    </c:when>
		    <c:otherwise>
			    	 <div id="second_first_${status.index }">
				        <input type="hidden" id ="isExchange" name="isExchange" value="${first.secondProps[0].isExchange }"/>
				       	<input type="hidden" id ="marketPrice" name="marketPrice" value="${first.secondProps[0].marketPrice }"/>
				        <input type="hidden" id ="isPromotion" name="isPromotion" value="${first.secondProps[0].isPromotion }"/>
				        <input type="hidden" id ="sku" name="sku" value="${first.secondProps[0].sku }"/>
		    	 		<input type="hidden" id ="count" name="count" value="${first.secondProps[0].count }"/>
				    	 <c:choose>
							<c:when test="${first.secondProps[0].isPromotion == '1'}">
									<input type="hidden" id ="salePrice" name="salePrice" value="${first.secondProps[0].promotionPrice }"/>
							</c:when>
							<c:otherwise>
									<c:choose>
										<c:when test="${first.secondProps[0].isSupportDiscount==1 }">
											<c:choose>
												<c:when test="${userLv == '0002'}">
														<input type="hidden" id ="salePrice" name="salePrice" value="${first.secondProps[0].goldPrice }"/>
												</c:when>
												<c:when test="${userLv == '0003'}">
													<input type="hidden" id ="salePrice" name="salePrice" value="${first.secondProps[0].platinumPrice }"/>
												</c:when>
												<c:when test="${userLv == '0004'}">
													<input type="hidden" id ="salePrice" name="salePrice" value="${first.secondProps[0].diamondPrice }"/>
													
												</c:when>
												<c:otherwise>
													<input type="hidden" id ="salePrice" name="salePrice" value="${first.secondProps[0].limitedPrice }"/>
												</c:otherwise>
											</c:choose>
										</c:when>
										<c:otherwise>
											<input type="hidden" id ="salePrice" name="salePrice" value="${first.secondProps[0].limitedPrice }"/>
										</c:otherwise>
											</c:choose>
										</c:otherwise>
							</c:choose>
					 </div>
			</c:otherwise>
		</c:choose>
	
	</c:forEach>
      <div class="num_info">
        <h3>数量</h3>
        <div class="amount_change">
          <a class="amount_cut" href="javascript:;">-</a>
          <input class="amount_val" name=""  value="1" type="text" >
          <a class="amount_add" href="javascript:;">+</a>
        </div>
      </div>

      <div class="submit">
        <a href="javascript:;" class="submit_btn">确定</a>
      </div>
	<input type="hidden" id ="buySku" name="buySku" value=""/>
	<input type="hidden" id ="buyCount" name="buyCount" value="1"/>
	<input type="hidden" id ="productNo" name="productNo" value="${productNo}"/>
	<input type="hidden" id ="collectId" name="collectId" value="${productDetail.basic.collect.id }"/>
	<input type="hidden" id ="topicId" name="topicId" value="${topicId}"/>
    </div>
    <!--弹层显示购买信息end-->

    <!--页面底部start-->
    <div class="footerBtm">
      <div class="footerBtmFixed">
        <div>
          <a href="#" class="buy_btn" buySoldOut="0">立即购买</a>
	      </div>
      </div>
    </div>
    <!--页面底部end-->