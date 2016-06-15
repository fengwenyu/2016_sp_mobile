 <%@ page language="java" contentType="text/html; charset=UTF-8" trimDirectiveWhitespaces="true"
    pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/pages/common/include.inc.jsp"%>
  <ul class="tab_info">
              <c:if test="${fn:length(firstCategory) != 0}">
              	<c:forEach var="first" items="${firstCategory }" varStatus="status">
	              	<c:choose>
	              	<c:when test="${status.index*1==0 }">
	              	 <li id="${first.channelCategoryNo }"><a href="" class="ico_tab curr">${first.channelCategoryName }</a></li>
	              	</c:when>
	              	<c:otherwise>
	              	 <li id="${first.channelCategoryNo }"><a href="" >${first.channelCategoryName }</a></li>
	              	</c:otherwise>
	              	</c:choose>
              	</c:forEach>
              </c:if>
            </ul>           
           
            <div class="content_info">   
            <c:forEach var="first" items="${firstCategory }" varStatus="status"> 
               <input type="hidden" id="${status.index }_start" name="${status.index }_start" value="1"/>
				<input type="hidden" id="${status.index }_hasMore" name="${status.index }_hasMore" value="${hasMore }"/>   
				<input type="hidden" id="${status.index }_ready" name="${status.index }_ready" value="1"/>   
	            <c:choose>
	            	<c:when test="${ status.index*1==0}">
	            		  <div class="content_list" style=" display:block;">
		                  <ul class="content_shoes">
		                  	<c:if test="${fn:length(secondfirstCategory) != 0}">
			                  	<c:forEach var="second" items="${secondfirstCategory }">
							  	  <li>
							  	  <c:choose>
							  	  <c:when test="${isNative=='1'}">
							  	   <a href="ShangPinApp://phone.shangpin/actiongochannellist?title=${second.channelCategoryName }&channelid=${second.channelCategoryNo }" >
							  	  </c:when>
							  	  <c:otherwise>
							  	    <a href="${ctx }/channel/product/list?channelCategoryNo=${second.channelCategoryNo }&channelCategoryName=${second.channelCategoryName }" >
							  	  </c:otherwise>
							  	  </c:choose>
							  	
							  	  
							  	  <img src="${fn:substring(second.channelCategoryPic,0,fn:indexOf(second.channelCategoryPic,'-'))}-72-56.jpg" />
			                      <em>${second.channelCategoryName }</em>                     
			                      </a></li>
			                    
			                     </c:forEach>
		                     </c:if>
		                  </ul>
		                  <c:if test="${fn:length(productList) != 0}">
		                  <c:forEach var="product" items="${productList }">
								<c:choose>
									<c:when test="${checkAPP}">
										<a href="shangPinApp://phone.shangpin/actiongodetail?productid=${product.productNo}">
									</c:when>
									<c:otherwise>
										<a href="<c:url value='/product/detail?productNo=${product.productNo}&picNo='/>" onclick="_smq.push(['custom',${product.productNo},,]);">
									</c:otherwise>
								</c:choose>
				                  <ul class="content_shoes_list">
				                  		<li class="list_img"><img src="${fn:substring(product.pic,0,fn:indexOf(product.pic,'-'))}-78-105.jpg" /></li>
				                        <li class="list_text">
				                       	    <span>${product.brandEnName }</span>
				                       	 <!--    空判断 -->
				                        	<p>${product.productName }&nbsp;${product.adverTitle }</p>
				                            <div class="pricestyle">
											<c:choose>
												<c:when test="${product.isSupportDiscount==1 }">
												<c:choose>
													<c:when test="${userLv == '0002'}">
														<b>&yen;<strong>${fn:substring(product.sellPrice,0,fn:indexOf(product.sellPrice,'.'))}</strong></b>
													</c:when>
													<c:when test="${userLv == '0003'}">
														<b>&yen;<strong>${fn:substring(product.platinumPrice,0,fn:indexOf(product.platinumPrice,'.'))}</strong></b>
													</c:when>
													<c:when test="${userLv == '0004'}">
														<b>&yen;<strong>${fn:substring(product.diamondPrice,0,fn:indexOf(product.diamondPrice,'.'))}</strong></b>
													</c:when>
													<c:otherwise>
														<b>&yen;<strong>${fn:substring(product.limitedPrice,0,fn:indexOf(product.limitedPrice,'.'))}</strong></b>
													</c:otherwise>
												</c:choose>
												</c:when>
												<c:otherwise>
														<b>&yen;<strong>${fn:substring(product.limitedPrice,0,fn:indexOf(product.limitedPrice,'.'))}</strong></b>
													</c:otherwise>
											</c:choose>
				                               <!--  <em>（免税包邮）</em> -->
				                            </div>
				                        </li>
				                       <!--  <li class="country_logo"><img src="/images/run/pic_country_logo.jpg" /></li> -->
				                  </ul>
			                  </a>
		                  </c:forEach>
		                </c:if>
		              </div>
	            	</c:when>
	            	<c:otherwise>
	            	 <div class="content_list" style=" display:block;"></div>
	            	</c:otherwise>
	            </c:choose>     
            
             </c:forEach>
                 
        </div>
        <input type="hidden" id="isNative" name="isNative" value="${isNative }"/>
       
		<input type="hidden" id="channelCategoryNO" name="channelCategoryNO" value="${firstCategory[0].channelCategoryNo }"/>
		<input type="hidden" id="tabIndex" name="tabIndex" value="0"/>
		<input type="hidden" id="userLv" name="userLv" value="${userLv }"/>
		<input type="hidden" id="checkApp" name="checkApp" value="${checkAPP}"/>
			