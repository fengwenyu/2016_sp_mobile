<%@ page language="java" contentType="text/html; charset=UTF-8" trimDirectiveWhitespaces="true"
    pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/pages/common/include.inc.jsp"%>

 <!--筛选排序-->
    <div class="menu_nav">
    	<div class="fixed_box" id="fixed_box">
            <ul class="tabSlider clr">
                <li class="price_btn disabel">金额<span></span></li>
                <li class="filte_btn">筛选</li>
            </ul>
            <ul class="filte_menu" id="filte_list">
                <li id="0"><a href="#">优惠券</a></li>
                <li id="1"><a href="#">现金券</a></li>
                <li id="2"><a href="#">礼包券</a></li>
            </ul>
        </div>
        <div class="Mask"></div>
        <input type="hidden" id="pageIndex" name="pageIndex" value="${pageIndex}"/>
        <input type="hidden" id="pageSize" name="pageSize" value="${pageSize}"/>
        <input type="hidden" id="order" name="order" value="${order}"/>
        <input type="hidden" id="type" name="type" value="${type}"/>
        <input type="hidden" id="hasMore" name="hasMore" value="${hasMore}"/>
    </div>
    
    <!-- 券列表 start -->
    <c:if test="${fn:length(dailyCouponsList.list) > 0}">
    	<ul class="con">
    		<c:forEach var="dcList" items="${dailyCouponsList.list}">
    			<li>
		            <h3 class="classes_title">${dcList.desc}</h3>
		            	<c:choose>
		            		<c:when test="${dcList.type == '0'}">
		            			<div class="list_box yellow">
		            		</c:when>
		            		<c:when test="${dcList.type == '1'}">
		            			<div class="list_box red">
		            		</c:when>
		            		<c:when test="${dcList.type == '2'}">
		            			<div class="list_box purple">
		            		</c:when>
		            	</c:choose>
		            	<c:choose>
		            		<c:when test="${dcList.statusCode == '0'}">
		            		<div class="type type3">
		            		</c:when>
		            		<c:when test="${dcList.statusCode == '1'}">
		            			<div class="type type1">
		            		</c:when>
		            		<c:when test="${dcList.statusCode == '2'}">
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
		                      		<c:when test="${dcList.type == '0'}">
				            			<strong>满减券</strong>${dcList.condition}
				            		</c:when>
				            		<c:when test="${dcList.type == '1'}">
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
		</ul>   
    </c:if>
	<c:if test="${hasMore == '1'}">
		<p class="load_btn">上拉加载</p>
	</c:if>
    <!-- 券列表 end -->
    
    <!--领券弹出层-成功-->
	<div class="select-overlay">
    	<div class="show_window_con">
        	<h3 class="title">恭喜你，领取成功！</h3>
            <p class="close_window">
            	<a href="" class="continue_btn">继续抢券</a>
                <a href="" class="use_btn">立即使用</a>
            </p>
        </div>
    </div>
    
    <!--领券弹出层-失败--> 
    <div class="tip-overlay"></div>
    <div class="tip-container" >
        <p class="tip-message">领取失败，再试试~</p>
    </div>