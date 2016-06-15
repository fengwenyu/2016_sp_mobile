<%@ page language="java" contentType="text/html; charset=UTF-8"
	trimDirectiveWhitespaces="true" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/pages/common/include.inc.jsp"%>

<rapid:override name="custum">
	<link href="${cdn:css(pageContext.request)}/styles/shangpin/css/public.css${ver}"rel="stylesheet" />
	<link href="${cdn:css(pageContext.request)}/styles/shangpin/css/page/swiper.min.css${ver}"rel="stylesheet" />
	<link href="${cdn:css(pageContext.request)}/styles/shangpin/css/page/bargain_buy.css${ver}" rel="stylesheet" />
	<script type="text/javascript" charset="utf-8">
		loader = SP.core
				.install("${cdn:js(pageContext.request)}/styles/shangpin/js/jquery.min.js${ver}")
				.using("${cdn:js(pageContext.request)}/styles/shangpin/js/lazyload.js${ver}")
				.excute()
				.using("${cdn:js(pageContext.request)}/styles/shangpin/js/config.sim.js${ver}")
				.using("${cdn:js(pageContext.request)}/styles/shangpin/js/page/swiper.min.js${ver}")
				.using("${cdn:js(pageContext.request)}/styles/shangpin/js/worth.js${ver}")
				.excute()
	</script>
</rapid:override>
<rapid:override name="page_title">
	每日超值购
</rapid:override>
<rapid:override name="content">
<div class="alContent">
<c:if test="${worthTitle!= null}">
 <c:if test="${fn:length(worthTitle.list) != 0}">
 <c:forEach  var="product" items="${worthTitle.list }">
  <div class="list-box">   
            <div class="content-list">
            	<c:choose>
					<c:when test="${checkAPP}">
						<a href="shangPinApp://phone.shangpin/actiongodetail?productid=${product.productNo}"  class="clr">
					</c:when>
					<c:otherwise>
						<a href="<c:url value='/product/detail?productNo=${product.productNo}&picNo='/>" onclick="_smq.push(['custom',${product.productNo},,]);"  class="clr">
					</c:otherwise>
				</c:choose>
                  <div class="list-img">
                  
                  <img src="${fn:substring(product.pic,0,fn:indexOf(product.pic,'-'))}-138-186.jpg">
                  
                  </div>
                  <div class="list-text">
                      <h3>${product.brandNameEN }</h3>
                      <p>${product.productName }</p>
                      <!--<div class="country-logo"><i><img src="/images/ep/country_logo.jpg"></i><em class="hot">热卖</em></div>-->
                      <div class="price-style">
                        
                          <c:choose>
									<c:when test="${product.isSupportDiscount==1 }">
									<c:choose>
										<c:when test="${userLv == '0002'}">
											<b>&yen;<strong>${fn:substring(product.sellPrice,0,fn:indexOf(product.sellPrice,'.'))}</strong></b>
												<em><c:if test="${product.sellPrice*1<product.marketPrice}">
																	&yen;${fn:substring(product.marketPrice,0,fn:indexOf(product.marketPrice,'.'))}
												</c:if>
												</em>
										</c:when>
										<c:when test="${userLv == '0003'}">
											<b>&yen;<strong>${fn:substring(product.platinumPrice,0,fn:indexOf(product.platinumPrice,'.'))}</strong></b>
											<em>
												<c:if test="${product.platinumPrice*1<product.marketPrice}">
														&yen;${fn:substring(product.marketPrice,0,fn:indexOf(product.marketPrice,'.'))}
												</c:if>
											</em>
										</c:when>
										<c:when test="${userLv == '0004'}">
											<b>&yen;<strong>${fn:substring(product.diamondPrice,0,fn:indexOf(product.diamondPrice,'.'))}</strong></b>
											<em>
												<c:if test="${product.diamondPrice*1<product.marketPrice}">
														&yen;${fn:substring(product.marketPrice,0,fn:indexOf(product.marketPrice,'.'))}
												</c:if>
											</em>
										</c:when>
										<c:otherwise>
											<b>&yen;<strong>${fn:substring(product.limitedPrice,0,fn:indexOf(product.limitedPrice,'.'))}</strong></b>
											<em>
												<c:if test="${product.limitedPrice*1<product.marketPrice}">
														&yen;${fn:substring(product.marketPrice,0,fn:indexOf(product.marketPrice,'.'))}
												</c:if>
											</em>
										</c:otherwise>
									</c:choose>
									</c:when>
									<c:otherwise>
											<b>&yen;<strong>${fn:substring(product.limitedPrice,0,fn:indexOf(product.limitedPrice,'.'))}</strong></b>
											<em>
												<c:if test="${product.limitedPrice*1<product.marketPrice}">
														&yen;${fn:substring(product.marketPrice,0,fn:indexOf(product.marketPrice,'.'))}
												</c:if>
											</em>
										</c:otherwise>
								</c:choose>
                      </div>
                  </div>
              </a>
            </div>
  	 </div>
   </c:forEach>
  </c:if>
</c:if>
    <input type="hidden" id="start" name="start" value="1"/>
	<input type="hidden" id="hasMore" name="hasMore" value="${hasMore }"/> 
	<input type="hidden" id="checkApp" name="checkApp" value="${checkAPP}"/> 
</div>
</rapid:override>


<%@ include file="/WEB-INF/pages/common/common_no_content_banner.jsp"%>
