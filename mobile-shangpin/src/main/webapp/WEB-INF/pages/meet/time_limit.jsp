<%@ page language="java" contentType="text/html; charset=UTF-8"
	trimDirectiveWhitespaces="true" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/pages/common/include.inc.jsp"%>

<rapid:override name="custum">
<style>
.main_box img{ width:100%}
</style>
<script type="text/javascript" charset="utf-8">
	loader = SP.core
	.install("${cdn:js(pageContext.request)}/styles/shangpin/js/jquery.min.js${ver}")
	.using("${cdn:js(pageContext.request)}/styles/shangpin/js/lazyload.js${ver}")
	.excute()
	.using("${cdn:js(pageContext.request)}/styles/shangpin/js/config.sim.js${ver}")
	.excute()
    .using("${cdn:js(pageContext.request)}/styles/shangpin/js/meet/20150520TimeLimit.js${ver}")
    .excute();
	</script>
<link href="${cdn:css(pageContext.request)}/styles/shangpin/css/page/20150520TimeLimit.css${ver}" rel="stylesheet" />
</rapid:override>
<rapid:override name="title">
	每日秒杀
</rapid:override>

<%-- 页标题 --%>
<rapid:override name="page_title">
	<c:choose>
      	<c:when test="${meetId == '368'}">
      		MO&Co.品牌特惠日
      	</c:when>
      	<c:otherwise>
      		每日秒杀
      	</c:otherwise>
      </c:choose>
</rapid:override>
<rapid:override name="content">
	<div class="container">
      <div class="card_balance">
      <c:choose>
      	<c:when test="${meetId == '368'}">
      		<img src="http://pic4.shangpin.com/group1/M00/D9/E9/rBQKaFaPcmuAYAFhAADqGBRtRrY778.jpg" /></div>
      	</c:when>
      	<c:otherwise>
      		<img src="http://pic3.shangpin.com/group1/M00/E0/F3/rBQKaFahonWAagSjAAAzzLVauks336.jpg"/></div>
      	</c:otherwise>
      </c:choose>
      <div class="menu-nav">
        <ul class="tab_info">
        <c:forEach items="${timeLimit.sale}" var="sale" varStatus="st">
        	<c:if test="${sale.phase==1}">
        		 <li class="curr"><a href="#">${sale.phaseTitle}</a></li>
        	</c:if>
        	<c:if test="${sale.phase==2}">
        		 <li><a href="">${sale.phaseTitle}</a></li>
        	</c:if>
        </c:forEach>        
          
        </ul>
      </div>
     
      <div class="content_info">
        <div class="content_detail content_list">
            <div class="list-box">
                <div class="prod_list clr">
                <c:forEach items="${timeLimit.sale}" var="sale" varStatus="st">
		        	<c:if test="${sale.phase==1}">
		        		  <c:forEach items="${sale.list}" var="timeLimitBuy">
		        		  	<div class="list_box">
		        		  		<c:if test="${timeLimitBuy.isClick==1 }">
		        		  			<c:choose>
									<c:when test="${checkAPP}">
										<a href="shangPinApp://phone.shangpin/actiongodetail?productid=${timeLimitBuy.productId}">
									</c:when>
									<c:otherwise>
										<a href="<c:url value='/product/detail?productNo=${timeLimitBuy.productId}'/>">
									</c:otherwise>
									</c:choose>
		        		  		</c:if>
		                        
								<c:if test="${timeLimitBuy.count<=0 }">
									<i class="saleOut">售罄</i>
								</c:if> 
		                          <img src="${fn:substring(timeLimitBuy.pic,0,fn:indexOf(timeLimitBuy.pic,'-'))}-306-407.jpg" />
		                          <div class="li_text">
		                            <h5>${timeLimitBuy.brandNameEN}</h5>
		                            <p>${timeLimitBuy.productName}</p>
		                            <span>
									<c:choose>
											<c:when test="${timeLimitBuy.status == '0001' || timeLimitBuy.status == '000100' }">

													<strong>¥${timeLimitBuy.promotionPrice }</strong>
													<em>
													<c:if test="${timeLimitBuy.promotionPrice*1<timeLimitBuy.marketPric }">
														¥${timeLimitBuy.marketPric}
													</c:if>
													</em>
											</c:when>
											<c:otherwise>
												<c:choose>
													<c:when test="${timeLimitBuy.isSupportDiscount==1 }">
														<c:choose>
															<c:when
																test="${sessionScope.mshangpin_user.level=='钻石会员'}">
																<strong>¥${timeLimitBuy.diamondPrice }</strong><em>
																<c:if test="${timeLimitBuy.diamondPrice*1<timeLimitBuy.marketPric }">
																	¥${timeLimitBuy.marketPric}
																</c:if>
																</em>
															</c:when>
															<c:when
																test="${sessionScope.mshangpin_user.level=='白金会员'}">
																<strong>¥${timeLimitBuy.platinumPrice }</strong><em>
																<c:if test="${timeLimitBuy.platinumPrice*1<timeLimitBuy.marketPric}">
																	¥${timeLimitBuy.marketPric}
																</c:if>
																</em>
															</c:when>
															<c:when
																test="${sessionScope.mshangpin_user.level=='黄金会员'}">
																<strong>¥${timeLimitBuy.goldPrice }</strong><em>
																<c:if test="${timeLimitBuy.goldPrice*1<timeLimitBuy.marketPric}">
																	¥${timeLimitBuy.marketPric}
																</c:if>
																</em>
															</c:when>
															<c:otherwise>
																<strong>&yen;${timeLimitBuy.limitedPrice }</strong><em>
																<c:if test="${timeLimitBuy.limitedPrice*1<timeLimitBuy.marketPric}">
																	¥${timeLimitBuy.marketPric}
																</c:if>
																</em>
															</c:otherwise>
														</c:choose>
													</c:when>
													<c:otherwise>
														<strong>¥${timeLimitBuy.limitedPrice  }</strong><em>
														<c:if test="${timeLimitBuy.limitedPrice*1<timeLimitBuy.marketPric}">
																	¥${timeLimitBuy.marketPric}
																</c:if>
																</em>
													</c:otherwise>
												</c:choose>
											</c:otherwise>
										</c:choose> 
		                            </span>
		                          </div>
		                        </a>
                    		</div>
		        		  </c:forEach>
		        	</c:if>
        		</c:forEach>
                </div>
             </div>
        </div>
        <div class="content_size content_list" id="tabs_txt0" style="display:none">
            <div class="list-box">
                <div class="prod_list clr">
                     <c:forEach items="${timeLimit.sale}" var="sale" varStatus="st">
		        	<c:if test="${sale.phase==2}">
		        		  <c:forEach items="${sale.list}" var="timeLimitBuy">
		        		  	<div class="list_box">
		                        <c:if test="${timeLimitBuy.isClick==1 }">
		        		  			<c:choose>
									<c:when test="${checkAPP}">
										<a href="shangPinApp://phone.shangpin/actiongodetail?productid=${timeLimitBuy.productId}">
									</c:when>
									<c:otherwise>
										<a href="<c:url value='/product/detail?productNo=${timeLimitBuy.productId}'/>">
									</c:otherwise>
									</c:choose>
		        		  		</c:if>
								<c:if test="${timeLimitBuy.count<=0 }">
<!-- 									<i class="saleOut">售罄</i> -->
								</c:if>
		                          <img src="${fn:substring(timeLimitBuy.pic,0,fn:indexOf(timeLimitBuy.pic,'-'))}-306-407.jpg" />
		                          <div class="li_text">
		                            <h5>${timeLimitBuy.brandNameEN}</h5>
		                            <p>${timeLimitBuy.productName}</p>

		                            <span>
<%-- 		                                <strong>¥${timeLimitBuy.limitedPrice  }</strong> --%>
<%-- 		                                <em>¥${timeLimitBuy.marketPric} --%>
<!-- 										</em> -->


									<strong>¥${timeLimitBuy.promotionPrice }</strong>
		                            <em>¥${timeLimitBuy.marketPric}</em>
		                            </span>
		                          </div>
		                        </a>
                    		</div>
		        		  </c:forEach>
		        	</c:if>
        		</c:forEach>
                </div>
             </div>
        </div>

      </div>

</div>
	
</rapid:override>

 
<rapid:override name="footer">
</rapid:override>
<%@ include file="/WEB-INF/pages/common/base_meet.jsp" %> 