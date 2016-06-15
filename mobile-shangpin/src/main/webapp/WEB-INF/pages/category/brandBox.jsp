<%@ page language="java" contentType="text/html; charset=UTF-8"
	trimDirectiveWhitespaces="true" pageEncoding="UTF-8"%>
	<%@ include file="/WEB-INF/pages/common/include.inc.jsp"%>
	
	<c:forEach var="lists" items="${items.list}" varStatus="status">
	  <c:choose>
	 			<c:when test="${lists.type == '1'}">
	 				<a href="${ctx}/subject/product/list?topicId=${lists.refContent}&postArea=0">
	 			</c:when>
	 			<c:when test="${lists.type == '2'}">
	 				<a href="${ctx}/category/product/list?categoryNo=${lists.refContent}&postArea=0">
	 			</c:when>
	 			<c:when test="${lists.type == '3'}">
	 				<a href="${ctx}/brand/product/list?brandNo=${lists.refContent}&postArea=0&WWWWWWWWW">
	 			</c:when>
	 			<c:when test="${lists.type == '4'}">
	 				<a href="${ctx}/product/detail?productNo=${lists.refContent}">
	 			</c:when>
	 			<c:when test="${lists.type == '5'}">
	 				<a href="${lists.refContent}">
	 			</c:when>
	 			<c:when test="${lists.type=='6'}">
				 	<c:choose>
						<c:when test="${lists.refContent=='1'}">
							<a href="${ctx}/coupon/list">
						</c:when>
						<c:when test="${lists.refContent=='3'}">
							<a href="${ctx}/order/list-1">
						</c:when>
						<c:when test="${lists.refContent=='4'}">
							<a href="${ctx}/collect/product/list?pageIndex=1&pageSize=20&shopType=1">
						</c:when>
						<c:when test="${lists.refContent=='2'}">
							<a href="${ctx}/giftCard/productList">
						</c:when>
						<c:when test="${lists.refContent=='7'}">
							<a href="http://m.aolai.com">
						</c:when>
						<c:otherwise>
						<a href="#">
						</c:otherwise>
					</c:choose>
		       </c:when>
		  <c:when test="${lists.type == '9'}">
				<a href="${ctx}/lable/product/list?tagId=${lists.refContent}" >
		  </c:when>
		 <c:otherwise>
			<a href="#">
		 </c:otherwise>
	  	</c:choose>
	          <img alt="" src="${fn:substring(lists.pic,0,fn:indexOf(lists.pic,'-'))}-${items.width}-${items.height}.jpg">
	          <c:if test="${items.type eq '5' }">
	             <span>${lists.name }</span>
	          </c:if>
	          </a>
	    </c:forEach>
