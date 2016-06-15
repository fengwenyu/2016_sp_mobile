<%@ page language="java" contentType="text/html; charset=UTF-8"
	trimDirectiveWhitespaces="true" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/pages/common/include.inc.jsp"%>
 <div class="detailed_information">
 	<div class="product_show">
        <a href="javascript:;" class="photo_details">
		     <img src="${cdn:pic(pageContext.request)}/styles/shangpin/images/e.gif" lazy="${fn:replace(productDetail.basic.allPics[0],'-10-10','-600-758')}" />
        </a>
        <section>
          <header>
            ${productDetail.basic.productName }
          </header>
          <article>
          <c:choose>
	          <c:when test="${productDetail.basic.collect.isCollected=='1'}">
	          <span class="collection_commodity already_collection"></span>
	          </c:when>
          	<c:otherwise>
          	<span class="collection_commodity"></span>
          	</c:otherwise>
          </c:choose>
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
	<c:if test="${productDetail.basic.firstProps[0].secondProps[0].marketPrice}>${productDetail.basic.firstProps[0].secondProps[0].limitedPrice }">
	<b class="market">&yen;${productDetail.basic.firstProps[0].secondProps[0].marketPrice}</b>
	</c:if>
	<c:if test="${productDetail.basic.firstProps[0].secondProps[0].marketPrice}>${productDetail.basic.firstProps[0].secondProps[0].promotionPrice }">
	<b class="market">&yen;${productDetail.basic.firstProps[0].secondProps[0].marketPrice}</b>
	</c:if>
	<c:if test="${productDetail.basic.firstProps[0].secondProps[0].marketPrice}>${productDetail.basic.firstProps[0].secondProps[0].goldPrice }">
	<b class="market">&yen;${productDetail.basic.firstProps[0].secondProps[0].marketPrice}</b>
	</c:if>
	<c:if test="${productDetail.basic.firstProps[0].secondProps[0].marketPrice}>${productDetail.basic.firstProps[0].secondProps[0].platinumPrice }">
	<b class="market">&yen;${productDetail.basic.firstProps[0].secondProps[0].marketPrice}</b>
	</c:if>
	<c:if test="${productDetail.basic.firstProps[0].secondProps[0].marketPrice}>${productDetail.basic.firstProps[0].secondProps[0].diamondPrice }">
	<b class="market">&yen;${productDetail.basic.firstProps[0].secondProps[0].marketPrice}</b>
	</c:if>
   	
   		<ul>
      		<li id="forSaleDesc"></li>
	        <c:if test="${productDetail.basic.isPackage=='1'}">
	      	  <li class="blue">包邮</li>
	        </c:if>
       	</ul>
     </article> 
   </section>
</div>
    