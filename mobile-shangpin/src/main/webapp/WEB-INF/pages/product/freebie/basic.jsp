<%@ page language="java" contentType="text/html; charset=UTF-8"
	trimDirectiveWhitespaces="true" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/pages/common/include.inc.jsp"%>
<!--该商品已下架start-->
    <div class="goods-sold-out">
          <p>该商品已下架</p>
    </div>
<!--该商品已下架end-->
<div class="product_all">
 <div class="detailed_information">
 	<div class="product_show">
        <div class="show_case">
           <i class="item-activity">
                 <img src="${cdn:pic(pageContext.request)}/styles/shangpin/images/e.gif" lazy="${productDetail.promoLogo}" />
           </i>
            <a href="javascript:;" class="photo_details">
            <!--<em class="new_tag"><img src="/images/e.gif" lazy="/images/new_tag.png" /></em>-->
            <img src="${cdn:pic(pageContext.request)}/styles/shangpin/images/e.gif" lazy="${fn:replace(productDetail.basic.allPics[0],'-10-10','-600-758')}" />
            </a>
              <a href="#" class="case_img"><em></em><b>${fn:length(productDetail.basic.allPics)}</b></a>
        </div>
        <section>
          <header>
            <span> ${productDetail.basic.brand.nameEN }</span>${productDetail.basic.prefix }${productDetail.basic.productName } ${productDetail.basic.suffix }
              <%-- <c:choose>
                <c:when test="${productDetail.notice.salesPromotionNew.type  ne null}">
                 <c:set var="model" value="${productDetail.notice.salesPromotionNew}"/>
                  <%@ include file="/WEB-INF/pages/product/model_rule.jsp"%>
                  <p> ${model.name }</p>
	               </a>
                </c:when>
                <c:otherwise>
                  <p> ${productDetail.basic.advertWord }</p>
                </c:otherwise>
              </c:choose> --%>
          </header>
          <article>
       <div class="logo_box">
     	<c:forEach var="tag" items="${productDetail.tag}">
        	   <i><img src="${cdn:pic(pageContext.request)}/styles/shangpin/images/e.gif" lazy="${tag.pic}" /></i>
        </c:forEach>
       </div>
      <div>
     <p class="not_promotion" id="forSalsPrice" >
     <c:set var="firstP" value="${productDetail.basic.defaultIndex.firstPropIndex}"/>
     <c:set var="secondP" value="${productDetail.basic.defaultIndex.secondPropIndex}"/>
     <c:set var="color" value="${productDetail.basic.firstProps[firstP].secondProps[secondP].priceTag[0].color eq ''? '#000' : productDetail.basic.firstProps[firstP].secondProps[secondP].priceTag[0].color}"/>
     <span style='color:${color}' >${productDetail.basic.firstProps[firstP].secondProps[secondP].priceTag[0].priceTitle}</span>
	 <c:choose>
		<c:when test="${productDetail.basic.firstProps[firstP].secondProps[secondP].isPromotion=='0'}">
		    <em style='color:${color}'>
				<c:choose>
					<c:when test="${productDetail.basic.firstProps[firstP].secondProps[secondP].isSupportDiscount==1 }">
						<c:choose>
							<c:when test="${userLv == '0002'}">
								&yen;${productDetail.basic.firstProps[firstP].secondProps[secondP].goldPrice }
							<input type="hidden" id ="lowestInfo" name="lowestInfo" sku="${productDetail.basic.firstProps[firstP].secondProps[secondP].sku}" isPromotion="${productDetail.basic.firstProps[firstP].secondProps[secondP].isPromotion}"  lowestPrice="${productDetail.basic.firstProps[firstP].secondProps[secondP].goldPrice}" isExchange="${productDetail.basic.firstProps[firstP].secondProps[secondP].isExchange }" marketPrice="${productDetail.basic.firstProps[firstP].secondProps[secondP].marketPrice }"/>		
							</c:when>
							<c:when test="${userLv == '0003'}">
								&yen;${productDetail.basic.firstProps[firstP].secondProps[secondP].platinumPrice }
							<input type="hidden" id ="lowestInfo" name="lowestInfo" sku="${productDetail.basic.firstProps[firstP].secondProps[secondP].sku}" isPromotion="${productDetail.basic.firstProps[firstP].secondProps[secondP].isPromotion}"  lowestPrice="${productDetail.basic.firstProps[firstP].secondProps[secondP].platinumPrice}" isExchange="${productDetail.basic.firstProps[firstP].secondProps[secondP].isExchange }" marketPrice="${productDetail.basic.firstProps[firstP].secondProps[secondP].marketPrice }"/>		
							</c:when>
							<c:when test="${userLv == '0004'}">
								&yen;${productDetail.basic.firstProps[firstP].secondProps[secondP].diamondPrice }
							<input type="hidden" id ="lowestInfo" name="lowestInfo" sku="${productDetail.basic.firstProps[firstP].secondProps[secondP].sku}" isPromotion="${productDetail.basic.firstProps[firstP].secondProps[secondP].isPromotion}"  lowestPrice="${productDetail.basic.firstProps[firstP].secondProps[secondP].diamondPrice}" isExchange="${productDetail.basic.firstProps[firstP].secondProps[secondP].isExchange }" marketPrice="${productDetail.basic.firstProps[firstP].secondProps[secondP].marketPrice }"/>		
							</c:when>
							<c:otherwise>
								&yen;${productDetail.basic.firstProps[firstP].secondProps[secondP].limitedPrice }
							<input type="hidden" id ="lowestInfo" name="lowestInfo" sku="${productDetail.basic.firstProps[firstP].secondProps[secondP].sku}" isPromotion="${productDetail.basic.firstProps[firstP].secondProps[secondP].isPromotion}"  lowestPrice="${productDetail.basic.firstProps[firstP].secondProps[secondP].limitedPrice}" isExchange="${productDetail.basic.firstProps[firstP].secondProps[secondP].isExchange }" marketPrice="${productDetail.basic.firstProps[firstP].secondProps[secondP].marketPrice }"/>			
							</c:otherwise>
						</c:choose>
					</c:when>
					<c:otherwise>
						&yen;${productDetail.basic.firstProps[firstP].secondProps[secondP].limitedPrice }
						<input  type="hidden" id ="lowestInfo" name="lowestInfo" sku="${productDetail.basic.firstProps[firstP].secondProps[secondP].sku}" isPromotion="${productDetail.basic.firstProps[firstP].secondProps[secondP].isPromotion}"  lowestPrice="${productDetail.basic.firstProps[firstP].secondProps[secondP].limitedPrice}" isExchange="${productDetail.basic.firstProps[firstP].secondProps[secondP].isExchange }" marketPrice="${productDetail.basic.firstProps[firstP].secondProps[secondP].marketPrice }"/>		
					</c:otherwise>
				</c:choose>
			</em>
		</c:when>
		<c:otherwise>
		 <em style='color:${color}'>
			 &yen;${productDetail.basic.firstProps[firstP].secondProps[secondP].promotionPrice }
		 </em>
		 <input type="hidden" id ="lowestInfo" name="lowestInfo" sku="${productDetail.basic.firstProps[firstP].secondProps[secondP].sku}" isPromotion="${productDetail.basic.firstProps[firstP].secondProps[secondP].isPromotion}"  lowestPrice="${productDetail.basic.firstProps[firstP].secondProps[secondP].promotionPrice}" isExchange="${productDetail.basic.firstProps[firstP].secondProps[secondP].isExchange }" marketPrice="${productDetail.basic.firstProps[firstP].secondProps[secondP].marketPrice }"/>
		</c:otherwise>
	</c:choose>
	<c:if test="${productDetail.basic.firstProps[firstP].secondProps[secondP].priceTag[1].isShow ==1}">
	  <em style='color:${productDetail.basic.firstProps[firstP].secondProps[secondP].priceTag[1].color eq ""?"#000": productDetail.basic.firstProps[firstP].secondProps[secondP].priceTag[1].color }'>
	   ${productDetail.basic.firstProps[firstP].secondProps[secondP].priceTag[1].priceTitle} 
	   ${productDetail.basic.firstProps[firstP].secondProps[secondP].priceTag[1].priceDesc}
	  </em>
	 </c:if>
   	</p>
    </div>
    </article> 
   </section>
       <c:choose>
        <c:when test="${(productDetail.basic.sales ne -1) && (productDetail.basic.commentCount ne -1) && (productDetail.basic.collections ne -1)}">
	        <ul class="record_num top_line">
		          <li class="sales"><i></i>销量 <b> ${productDetail.basic.sales} 件</b></li>
		          <li class="comments"><i></i>评论 <b>${productDetail.basic.commentCount}条</b></li>
		          <li class="like"><i></i>喜欢 <b>${productDetail.basic.collections} 人</b></li>  
		    </ul>
        </c:when>
       </c:choose>
   </div>

   <!--促销信息-->
<%--     <c:if test="${fn:length(productDetail.promotionNew)>0 }">
       <c:forEach var="model" items="${productDetail.promotionNew }"> 
		<div class="product_promotional">
            <%@ include file="/WEB-INF/pages/product/model_rule.jsp"%>
	        <i><img src="${cdn:pic(pageContext.request)}/styles/shangpin/images/e.gif" lazy="${cdn:pic(pageContext.request)}/styles/shangpin/images/product/promotional_icon.png" /></i>
	        ${model.name }
	        </a>
		</div>
	  </c:forEach>
    </c:if> --%>
  <%--      <!--配送、支付、分期、承诺-->
       <div class="product_introduce">
       	<div class="row_box">
           	<em>配送：</em>${productDetail.notice.dispatching }。
           </div>
           <div class="row_box">
           	<em>支付：</em>
               <ul class="payment_method">
                   <li class="alipay"></li>
                   <li class="wechatpay"></li>
                   <li class="unionpay"></li>
                   <c:if test="${productDetail.notice.codFlag eq '1' }" >
                     <li class="topay"></li>
                   </c:if>
           	</ul>
           </div>
           <c:if test="${productDetail.notice.acceptance ne ''}">
           <div class="row_box">
           	<em>承诺：</em>${productDetail.notice.acceptance }
           </div>
           </c:if>
       </div> --%>
       
    <!--促销信息-->
	<div class="product_promotional product_sizes">
		<c:choose>
      		<c:when test="${productDetail.basic.isSoldOut=='1' }">
      		 <a href="javascript:;" class="select_bar" selectSoldOut="1"> 
      		</c:when>
      		<c:otherwise>
      		  <a href="javascript:;" class="select_bar" selectSoldOut="0">  
      		</c:otherwise>
	   </c:choose>
	   <c:if test="${productDetail.basic.firstPropName != ''}">
	   	 <span>颜 色：</span>
		 <c:forEach var="first" items="${productDetail.basic.firstProps}" varStatus="status" >
	              ${first.firstProp }
	              <c:if test="${ (status.index+1)<fn:length(productDetail.basic.firstProps)}">、</c:if>
	     </c:forEach> <br /> 
	    </c:if>
	    <c:if test="${productDetail.basic.firstProps[0].isSecondProp=='1'}">
          <span> ${productDetail.basic.secondPropName }：</span>
          <input type="hidden" id="secondPropName" name="secondPropName" value="${productDetail.basic.secondPropName }">
		<%--是否有第二属性 --%>
		   <c:forEach var="second" items="${productDetail.basic.firstProps[0].secondProps}" varStatus="status">
		      ${second.secondProp }
		      <c:if test="${ (status.index+1)<fn:length(productDetail.basic.firstProps[0].secondProps)}">、</c:if>
           </c:forEach>
		</c:if>
		</a>
	</div>
	</div>