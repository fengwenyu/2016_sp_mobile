<%@ page language="java" contentType="text/html; charset=UTF-8" trimDirectiveWhitespaces="true"
    pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/pages/common/include.inc.jsp"%>

<!-- 券列表 start -->
    <c:if test="${fn:length(dailyCouponsList.list) > 0}">
    		<c:forEach var="dcList" items="${dailyCouponsList.list}">
    			<li>
		            <h3 class="classes_title">${dcList.desc}</h3>
		            	<c:choose>
		            		<c:when test="${dcList.statusCode == '0'}">
		            		 <div class="list_box yellow">
		            		 <div class="type type3">
		            			
		            		</c:when>
		            		<c:when test="${dcList.statusCode == '1'}">
		            		 <div class="list_box purple">
		            			<div class="type type1">
		            		</c:when>
		            		<c:when test="${dcList.statusCode == '2'}">
		            		 <div class="list_box red">
		            			<div class="type type2">
		            		</c:when>
		            		<c:otherwise>
		            			
		            		</c:otherwise>
		            	</c:choose>
		            	<a href="${dcList.url}">
		                    <img src="${fn:substring(dcList.pic,0,fn:indexOf(dcList.pic,'-'))}-116-116.jpg">
		                    </a>
		                    <div class="info">
		                      <p><strong class="num">${dcList.amount}</strong>
		                      <span class="type-text">
		                      	<c:choose>
		                      		<c:when test="${dcList.type == '1'}">
				            			<strong>满减券</strong>${dcList.condition}
				            		</c:when>
				            		<c:when test="${dcList.type == '0'}">
				            			<strong>现金券</strong>${dcList.condition}
				            		</c:when>
				            		<c:when test="${dcList.type == '2'}">
				            			<strong>礼券包</strong>&nbsp;
				            		</c:when>
				            		<c:otherwise>
				            			
				            		</c:otherwise>
		                      	</c:choose>
		                      </span></p> 
		                      <time>有效期<br> ${dcList.expireDate}</time>     
		                    </div>
		                    <i class="icon_type" id="${dcList.activeCode}"></i>
		                </div>
		            </div>
		        </li>
    		</c:forEach>
    </c:if>
    <!-- 券列表 end -->