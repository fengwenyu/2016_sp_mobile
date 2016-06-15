<%@ page language="java" contentType="text/html; charset=UTF-8"
	trimDirectiveWhitespaces="true" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/pages/common/include.inc.jsp"%>

<!--分类导航-->
<c:if test="${revealLabel != null&&fn:length(revealLabel.list)>0}">
<c:set var="flag" value="0" />
			      <c:forEach var="list" items="${revealLabel.list}">
		          <c:choose>
		    			<c:when test="${list.type == '1'}">
		    				<c:if test="${flag=='0'}">
		    					<div class="swiper-nav-box">
		 						<div class="swiper-container" id="swiperNav">
			 					<div class="swiper-wrapper">
			 					<c:set var="flag" value="1" />
			 					<div class="swiper-slide"><a class="cur" href="#1">推荐</a></div>
			 				</c:if>
		    				<div class="swiper-slide"><a href="${ctx}/subject/product/list?topicId=${list.refContent}&postArea=0">${list.name }</a></div>
		    			</c:when>
		    			<c:when test="${list.type == '2'}">
		    				<c:if test="${flag=='0'}">
		    					<div class="swiper-nav-box">
		 						<div class="swiper-container" id="swiperNav">
			 					<div class="swiper-wrapper">
			 					<c:set var="flag" value="1" />
			 					<div class="swiper-slide"><a class="cur" href="#1">推荐</a></div>
			 				</c:if>
		    				<div class="swiper-slide"><a href="${ctx}/category/product/list?categoryNo=${list.refContent}&postArea=0">${list.name }</a></div>
		    			</c:when>
		    			<c:when test="${list.type == '3'}">
		    				<c:if test="${flag=='0'}">
		    					<div class="swiper-nav-box">
		 						<div class="swiper-container" id="swiperNav">
			 					<div class="swiper-wrapper">
			 					<c:set var="flag" value="1" />
			 					<div class="swiper-slide"><a class="cur" href="#1">推荐</a></div>
			 				</c:if>
		    				<div class="swiper-slide"><a href="${ctx}/brand/product/list?brandNo=${list.refContent}&postArea=0">${list.name }</a></div>
		    			</c:when>
		    			<c:when test="${list.type == '4'}">
		    				<c:if test="${flag=='0'}">
		    					<div class="swiper-nav-box">
		 						<div class="swiper-container" id="swiperNav">
			 					<div class="swiper-wrapper">
			 					<c:set var="flag" value="1" />
			 					<div class="swiper-slide"><a class="cur" href="#1">推荐</a></div>
			 				</c:if>
		    				<div class="swiper-slide"><a href="${ctx}/product/detail?productNo=${list.refContent}">${list.name }</a></div>
		    			</c:when>
		    			<c:when test="${list.type == '5'}">
		    				<c:if test="${flag=='0'}">
		    					<div class="swiper-nav-box">
		 						<div class="swiper-container" id="swiperNav">
			 					<div class="swiper-wrapper">
			 					<c:set var="flag" value="1" />
			 					<div class="swiper-slide"><a class="cur" href="#1">推荐</a></div>
			 				</c:if>
		    				<div class="swiper-slide"><a href="${list.refContent}">${list.name }</a></div>
		    			</c:when>
		    			<c:when test="${list.type == '9'}">
		    				<c:if test="${flag=='0'}">
		    					<div class="swiper-nav-box">
		 						<div class="swiper-container" id="swiperNav">
			 					<div class="swiper-wrapper">
			 					<c:set var="flag" value="1" />
			 					<div class="swiper-slide"><a class="cur" href="#1">推荐</a></div>
			 				</c:if>
		    				<div class="swiper-slide"><a href="${ctx}/lable/product/list?tagId=${list.refContent}">${list.name }</a></div>
		    			</c:when>
		    				<c:when test="${list.type=='6'}">
						 <c:choose>
								<c:when test="${list.refContent=='1'}">
									<c:if test="${flag=='0'}">
				    					<div class="swiper-nav-box">
				 						<div class="swiper-container" id="swiperNav">
					 					<div class="swiper-wrapper">
					 					<c:set var="flag" value="1" />
					 					<div class="swiper-slide"><a class="cur" href="#1">推荐</a></div>
			 						</c:if>
									<div class="swiper-slide"><a href="${ctx}/coupon/list">${list.name }</a></div>
								</c:when>
								<c:when test="${list.refContent=='3'}">
									<c:if test="${flag=='0'}">
				    					<div class="swiper-nav-box">
				 						<div class="swiper-container" id="swiperNav">
					 					<div class="swiper-wrapper">
					 					<c:set var="flag" value="1" />
					 					<div class="swiper-slide"><a class="cur" href="#1">推荐</a></div>
			 						</c:if>
									<div class="swiper-slide"><a href="${ctx}/order/list-1">${list.name }</a></div>
								</c:when>
								<c:when test="${list.refContent=='4'}">
									<c:if test="${flag=='0'}">
				    					<div class="swiper-nav-box">
				 						<div class="swiper-container" id="swiperNav">
					 					<div class="swiper-wrapper">
					 					<c:set var="flag" value="1" />
					 					<div class="swiper-slide"><a class="cur" href="#1">推荐</a></div>
			 						</c:if>
									<div class="swiper-slide"><a href="${ctx}/collect/product/list?pageIndex=1&pageSize=20&shopType=1">${list.name }</a></div>
								</c:when>
								<c:when test="${list.refContent=='2'}">
									<c:if test="${flag=='0'}">
				    					<div class="swiper-nav-box">
				 						<div class="swiper-container" id="swiperNav">
					 					<div class="swiper-wrapper">
					 					<c:set var="flag" value="1" />
					 					<div class="swiper-slide"><a class="cur" href="#1">推荐</a></div>
			 						</c:if>
									<div class="swiper-slide"><a href="${ctx}/giftCard/productList">${list.name }</a></div>
								</c:when>
								<c:when test="${list.refContent=='7'}">
									<c:if test="${flag=='0'}">
				    					<div class="swiper-nav-box">
				 						<div class="swiper-container" id="swiperNav">
					 					<div class="swiper-wrapper">
					 					<c:set var="flag" value="1" />
					 					<div class="swiper-slide"><a class="cur" href="#1">推荐</a></div>
			 						</c:if>
									<div class="swiper-slide"><a href="http://m.aolai.com">${list.name }</a></div>
								</c:when>
								<c:otherwise>
								
								</c:otherwise>
							</c:choose>
					</c:when>
		    			<c:otherwise>
		    				
		    			</c:otherwise>
		    		</c:choose>
		          
		       </c:forEach>
		       <c:if test="${flag=='1'}">
				 </div>
				</div>
				</div>
			 </c:if>
</c:if>
