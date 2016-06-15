<%@ page language="java" contentType="text/html; charset=UTF-8"
	trimDirectiveWhitespaces="true" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/pages/common/include.inc.jsp"%>
<!--评论消息-->
<div class="produce_comment">
	<!--有商品评论start-->
	<c:choose>
		<c:when test="${productComment.count >0 }">
			<!--评论start-->
			<strong class="comment_num">商品评价(${productComment.count})</strong>
			<ul class="comment_tab" id="comment_tab">
				<c:forEach var="tag" items="${productComment.tag }">
					<li>${tag.name }( ${tag.count } ) 
					<input type="hidden" id="tagId" value="${tag.id}" />
					</li>

				</c:forEach>
			</ul>
			<div class="comment_height" id="commentList">
				<c:forEach var="infoUser" items="${productComment.list}">
				<c:if test="${infoUser.userIcon eq ''}">
				<c:set var="picU" value="${cdn:pic(pageContext.request)}/styles/shangpin/images/detail/user.jpg"/>
				</c:if>
				<c:if test="${infoUser.userIcon ne ''}">
				<c:set var="picU" value="${item.userIcon}"/>
				</c:if>
					<div class="content_comment">
						<div class="comment_user">
							<div class="user_cont">
								<a href="#"> 
								<img src="${cdn:pic(pageContext.request)}/styles/shangpin/images/e.gif"	lazy="${picU}" width="35px" height="35px" />
								</a> <span class="user_name">${infoUser.userName}</span>
							</div>
						</div>
						<div class="comment_cont">
							<p>${infoUser.desc}</p>
						</div>
						<div class="comment_img">
						    <ul>
		                      <c:forEach var="pics" items="${infoUser.pics}">
								<li>
									<img src="${cdn:pic(pageContext.request)}/styles/shangpin/images/e.gif"	lazy="${pics }" width="50px">
								</li>
							 </c:forEach>
		                   </ul>
							<p>
								<span> 
								    <c:forEach var="tags" items="${infoUser.tags}">
										<span>${tags}</span>
									</c:forEach>
								</span> <span>${infoUser.productInfo}</span> <span>${infoUser.userInfo}</span>
							</p>
						</div>
					</div>
				</c:forEach>
				<a href="<c:url value='/product/prCommentList?productNo=${productDetail.basic.productId }'/>" class="comment_more">查看更多评价</a>
			</div>
		</c:when>
		<c:otherwise>
			<!--无商品评论start-->
			<div class="no_comment">
				<p>暂无评价</p>
			</div>
			<!--无商品评论end-->
		</c:otherwise>
	</c:choose>
</div>