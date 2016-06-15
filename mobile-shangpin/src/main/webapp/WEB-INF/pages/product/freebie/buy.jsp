<%@ page language="java" contentType="text/html; charset=UTF-8"
	trimDirectiveWhitespaces="true" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/pages/common/include.inc.jsp"%>
   <div class="simple_information">
       <div class="image_list product_info">
    	<c:forEach var="pic" items="${productDetail.basic.allPics}">
		          <img src="${fn:replace(pic,'-10-10','-600-758')}">
        </c:forEach>
       </div>
    </div>
   <!--弹层显示购买信息start-->
    <div class="alProd"></div>
    <div class="all_info_box">
      	  <div class="info_box alProdInfo">
              <!--<a href="javascript:;" class="close_btn">关闭</a>-->
              <div class="commodity_info clr">
                <span class="img_photo">
                   <img src="${fn:replace(productDetail.basic.allPics[0],'-10-10','-600-758') }" />
                </span>
                <div class="right_cont">
		          <h3>${productDetail.basic.brand.nameEN }  ${productDetail.basic.productName }</h3>
		          <p>
		            <b>&yen;299</b>
		          <p>
		      <!--  <div class="choice_product"><span></span><span></span></div> -->
		        </div>
              </div>
          <input type="hidden" value="${productDetail.basic.firstPropName}" id="firstPropName" >
          <input type="hidden"  id="isSize"  value="${productDetail.basic.isShortSize}"/>
          <input type="hidden"  id="isOut"  value="${productDetail.basic.isSoldOut}"/>
		  <c:if test="${productDetail.basic.firstPropName != ''}">
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
			        	 <li id="first_${status.index }" isSecondProp="${first.isSecondProp }" class="buySold" flag="${status.index }"> 
			        	</c:otherwise>
			        	</c:choose>
			        </c:when>
			        <c:otherwise>
			            <li id="first_${status.index }" isSecondProp="${first.isSecondProp }" class="buySold" flag="${status.index }">
			        </c:otherwise>
		        </c:choose>
			            ${first.firstProp }
			         </li>
		          </c:forEach>
		        </ul>
		      </div>
		     </c:if>
      
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
			        <input type="hidden" value="${productDetail.basic.secondPropName }" id="size_name" >   
			        <ul>
				            <c:forEach var="second" items="${first.secondProps}" varStatus="secondStatus">
				            <c:choose>
				            	<c:when test="${second.count*1>0 }">
				            	 <li class="buySold" count="${fn:length(second.sizeAbout)}"
				            	 <c:forEach var="val" items="${second.sizeAbout}" varStatus="valStatus">
				            	 		data-key${valStatus.index + 1}="${val.name}"  data-val${valStatus.index + 1}="${val.value}"  
				            	 	</c:forEach>
				            	 >
				            	</c:when>
				            	<c:otherwise>
				            	 <li class="soldOut" count="${fn:length(second.sizeAbout)}"
				            	 	<c:forEach var="val" items="${second.sizeAbout}" varStatus="valStatus">
				            	 		data-key${valStatus.index + 1}="${val.name}"  data-val${valStatus.index + 1}="${val.value}"
				            	 	</c:forEach>
				            	 >
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
			        <div class="size-detail-info">
			          	<a href="javascript:;" class="size-close">X</a>
			            <div class="table-box">
				          	<table>
				            </table>
			            </div>
						<span class="size-detail-arrow"></span>
		          	</div>
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
      
            <input type="hidden" id ="buySku" name="buySku" value=""/>
			<input type="hidden" id ="buyCount" name="buyCount" value="1"/>
			<input type="hidden" id ="productNo" name="productNo" value="${productNo}"/>
			<input type="hidden" id ="collectId" name="collectId" value="${productDetail.basic.collect.id }"/>
			<input type="hidden" id ="topicId" name="topicId" value="${topicId}"/>
              <!--输入手机号验证码-->
              <div class="user_info_mb">
                  <input class="tip-input" id="J_mobileNum" type="tel" value="" placeholder="请输入手机号" maxlength="11" required />
                  <div class="yzm-box">
                      <div class="login_list">                  
                          <input type="tel" id="J_yzm" value="" placeholder="请输入验证码" maxlength="6" required>                             
                      </div>
                      <em id="passwordGetCode" class="btn_gradient_gray" data-waiting="秒">获取验证码</em>
                  </div> 
              </div>  
              
              <div class="submit">          
                <a href="javascript:;" class="btn submit_btn">确定</a>
              </div>
      
            </div>
            <!--弹层显示购买信息end-->		
            
            <!--老用户选择收货地址-->
            <div class="info_box old_user hide">
            	<h3>收货信息</h3>
                <div class="addr_content">
                    <ul class="addr_list">
                    </ul>
                </div>
                <div class="submit">          
                	<a href="javascript:;" class="btn getGift_btn">免费领取同款撞衫</a>
                </div>
            </div>
            
            
            <!--新用户填写收货地址-->
            <div class="info_box new_user hide">
            	<h3>收货信息</h3>
				<div class="addr_box">
				   <form name="login" id="order_address_form" method="post">
			    	<input type="hidden" id="province" name="province" value=""/>
			    	<input type="hidden" id="provincename" name="provname" value=""/>
			    	<input type="hidden" id="city" name="city" value=""/>
			    	<input type="hidden" id="cityname" name="cityname" value=""/>
			    	<input type="hidden" id="area" name="area" value=""/>
			    	<input type="hidden" id="areaname" name="areaname" value=""/>
			    	<input type="hidden" id="town" name="town" value=""/>
			    	<input type="hidden" id="townname" name="townname" value=""/>
                	<p>
                        <label for="user_name">姓名：</label>
                        <input type="text" id="J_name" name="name" placeholder="请输入您的姓名" required />
                    </p>
                    <p id="select_addr">
                        <label for="province">省市区：</label>
                        <input type="text" id="J_province" name="province_es" placeholder="请选择省市区" required />
                    </p>
                    <p>
                        <label for="addr">详细地址：</label>
                        <input type="text" id="J_addr" name="addr" placeholder="请输入详细街道地址" required />
                    </p>
                    <p>
                        <label for="mail">邮箱：</label>
                        <input type="text" id="J_mail" name="mail" placeholder="请输入您的邮箱" required />
                    </p>
                    </form>
                </div>
                <div class="submit">          
                	<a href="javascript:;" class="btn getGift_btn">免费领取同款撞衫</a>
                </div>
            </div>
      </div>
      
      
       <!--页面底部start-->
      <div class="footerBtm">
        <div class="footerBtmFixed">
          <div id="footerHrefBox">
			<!--<a href="javascript:;" class="get_gift">免费领取撞衫同款</a>
            <a href="javascript:;" class="replace_gift">换领其他款式</a>-->
          </div>
        </div>
      </div>
      <!--页面底部end-->
      
      
      
