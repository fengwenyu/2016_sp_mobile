<%@ page language="java" contentType="text/html; charset=UTF-8" trimDirectiveWhitespaces="true"
    pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/pages/common/include.inc.jsp"%>
      <!-- 优惠券列表开始 -->
      <c:if test="${haveCoupon}">
       <input type="hidden" id="statusType${pageNo }" value="${couponType }"/>
 	    <input type="hidden" id="pageNo${pageNo }" value="${pageNo }"/>
 	    <input type="hidden" id="isHaveMore${pageNo }" value="${isHaveMore }"/>
	      <c:forEach var="item" items="${couponList }" >
	       	<dl>
				<dd>
					<h2>${item.rule }</h2>
				    <ul>
				   <li>优惠券面值<br />${item.amount }</li>
				   <li>有效期<br />${item.expirydate }</li>
				  <li >状态<br /><i style="color:red;">${item.status }</i></li>
				 </ul>
				</dd>
			</dl>
	      </c:forEach>
	   </c:if>
	