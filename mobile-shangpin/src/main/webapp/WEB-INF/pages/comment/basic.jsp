<%@ page language="java" contentType="text/html; charset=UTF-8"
	trimDirectiveWhitespaces="true" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/pages/common/include.inc.jsp"%>
<div class="product_all">
 <c:set var="firstP" value="${productDetail.basic.defaultIndex.firstPropIndex}"/>
 <c:set var="secondP" value="${productDetail.basic.defaultIndex.secondPropIndex}"/>
	<c:choose>
		<c:when
			test="${productDetail.basic.firstProps[firstP].secondProps[secondP].isPromotion=='0'}">
			<c:choose>
				<c:when
					test="${productDetail.basic.firstProps[firstP].secondProps[secondP].isSupportDiscount==1 }">
					<c:choose>
						<c:when test="${userLv == '0002'}">
							<input type="hidden" id="lowestInfo" name="lowestInfo"
								sku="${productDetail.basic.firstProps[firstP].secondProps[secondP].sku}"
								isPromotion="${productDetail.basic.firstProps[firstP].secondProps[secondP].isPromotion}"
								lowestPrice="${productDetail.basic.firstProps[firstP].secondProps[secondP].goldPrice}"
								isExchange="${productDetail.basic.firstProps[firstP].secondProps[secondP].isExchange }"
								marketPrice="${productDetail.basic.firstProps[firstP].secondProps[secondP].marketPrice }" />
						</c:when>
						<c:when test="${userLv == '0003'}">
							<input type="hidden" id="lowestInfo" name="lowestInfo"
								sku="${productDetail.basic.firstProps[firstP].secondProps[secondP].sku}"
								isPromotion="${productDetail.basic.firstProps[firstP].secondProps[secondP].isPromotion}"
								lowestPrice="${productDetail.basic.firstProps[firstP].secondProps[secondP].platinumPrice}"
								isExchange="${productDetail.basic.firstProps[firstP].secondProps[secondP].isExchange }"
								marketPrice="${productDetail.basic.firstProps[firstP].secondProps[secondP].marketPrice }" />
						</c:when>
						<c:when test="${userLv == '0004'}">
							<input type="hidden" id="lowestInfo" name="lowestInfo"
								sku="${productDetail.basic.firstProps[firstP].secondProps[secondP].sku}"
								isPromotion="${productDetail.basic.firstProps[firstP].secondProps[secondP].isPromotion}"
								lowestPrice="${productDetail.basic.firstProps[firstP].secondProps[secondP].diamondPrice}"
								isExchange="${productDetail.basic.firstProps[firstP].secondProps[secondP].isExchange }"
								marketPrice="${productDetail.basic.firstProps[firstP].secondProps[secondP].marketPrice }" />
						</c:when>
						<c:otherwise>
							<input type="hidden" id="lowestInfo" name="lowestInfo"
								sku="${productDetail.basic.firstProps[firstP].secondProps[secondP].sku}"
								isPromotion="${productDetail.basic.firstProps[firstP].secondProps[secondP].isPromotion}"
								lowestPrice="${productDetail.basic.firstProps[firstP].secondProps[secondP].limitedPrice}"
								isExchange="${productDetail.basic.firstProps[firstP].secondProps[secondP].isExchange }"
								marketPrice="${productDetail.basic.firstProps[firstP].secondProps[secondP].marketPrice }" />
						</c:otherwise>
					</c:choose>
				</c:when>
				<c:otherwise>
					<input type="hidden" id="lowestInfo" name="lowestInfo"
						sku="${productDetail.basic.firstProps[firstP].secondProps[secondP].sku}"
						isPromotion="${productDetail.basic.firstProps[firstP].secondProps[secondP].isPromotion}"
						lowestPrice="${productDetail.basic.firstProps[firstP].secondProps[secondP].limitedPrice}"
						isExchange="${productDetail.basic.firstProps[firstP].secondProps[secondP].isExchange }"
						marketPrice="${productDetail.basic.firstProps[firstP].secondProps[secondP].marketPrice }" />
				</c:otherwise>
			</c:choose>
		</c:when>
		<c:otherwise>
			<input type="hidden" id="lowestInfo" name="lowestInfo"
				sku="${productDetail.basic.firstProps[firstP].secondProps[secondP].sku}"
				isPromotion="${productDetail.basic.firstProps[firstP].secondProps[secondP].isPromotion}"
				lowestPrice="${productDetail.basic.firstProps[firstP].secondProps[secondP].promotionPrice}"
				isExchange="${productDetail.basic.firstProps[firstP].secondProps[secondP].isExchange }"
				marketPrice="${productDetail.basic.firstProps[firstP].secondProps[secondP].marketPrice }" />
		</c:otherwise>
	</c:choose>
	<div class="detailed_information">
		<input type="hidden" id="pageIndex" name="pageIndex" value="1"/>
	    <input type="hidden" id="hasMore" name="hasMore" value="1"/>
	    <input type="hidden" id="seleTagId" name="seleTagId" value="${seleTagId}"/>
		<!--评论消息-->
		<ul class="comment_tab comment_page_tab" id="comment_tab">
			<c:forEach var="tag" items="${productComment.tag }">
			<c:choose>
			<c:when test="${(tag.id) eq seleTagId}"><li class="cur"></c:when>
			<c:otherwise><li ></c:otherwise>
			</c:choose>
			  ${tag.name }( ${tag.count } )
		      <input type="hidden" value="${tag.id}" id="tagId" />
			</c:forEach>
		</ul>
		<div class="produce_comment">
			<!--评论start-->
			<c:choose>
				<c:when test="${productComment.count <=0 }">

					<!--无商品评论start-->
					<div class="no_comment">
						<p>暂无评价</p>
					</div>
					<!--无商品评论end-->

				</c:when>
				<c:otherwise>
					<!--有商品评论start-->
					<div class="comment_height">
						<c:forEach var="infoUser" items="${productComment.list}">
							<div class="content_comment">
								<div class="comment_user">
									<div class="user_cont">
									    <c:if test="${infoUser.userIcon eq ''}">
										<c:set var="picU" value="${cdn:pic(pageContext.request)}/styles/shangpin/images/detail/user.jpg"/>
										</c:if>
										<c:if test="${infoUser.userIcon ne ''}">
										<c:set var="picU" value="${infoUser.userIcon}"/>
									    </c:if>
										<a href="#"> <img
											src="${cdn:pic(pageContext.request)}/styles/shangpin/images/e.gif" lazy="${picU}" width="35px" height="35px" />
										</a> 
										<span class="user_name">${infoUser.userName}</span>
									</div>
									<div class="user_class">
										<span class="comment_time">
										  ${infoUser.strDate}   
										</span>
									</div>
								</div>
								<div class="comment_cont">
									<p>${infoUser.desc}</p>
								</div>
								<div class="comment_img">
									<ul>
										<c:forEach var="pics" items="${infoUser.pics}">
											<li><img
												src="${cdn:pic(pageContext.request)}/styles/shangpin/images/e.gif"
												lazy="${pics }" width="50px"></li>
										</c:forEach>
									</ul>
									<p>
										<c:forEach var="tags" items="${infoUser.tags}">
											<span>${tags}</span>
										</c:forEach>
										<span>${infoUser.productInfo}</span> <span>${infoUser.userInfo}</span>
									</p>
								</div>
								<div class="comment_reply">
									<c:forEach var="reply" items="${infoUser.reply}">
										<!--<i></i>-->
										<h6>${reply.from}</h6>
										<p>${reply.desc}</p>
									</c:forEach>
								</div>
							</div>
						</c:forEach> 
					</div>
				</c:otherwise>
			</c:choose>

		</div>
	</div>

</div>
