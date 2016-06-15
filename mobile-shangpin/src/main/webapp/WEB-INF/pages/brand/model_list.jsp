<%@ page language="java" contentType="text/html; charset=UTF-8"
	trimDirectiveWhitespaces="true" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/pages/common/include.inc.jsp"%>
  <c:forEach var="model" items="${modelOne.model}" varStatus="status">
	<c:if test="${modelOne.type!='1'}">
		<li>
	</c:if>
	<c:if test="${modelOne.type=='100'}">
		<c:if test="${status.index ==0 }">
			<c:set value="class='left-floor'" var="one_stye" />
		</c:if>
		<c:if test="${status.index ==1 }">
			<c:set value="class='right-up-floor'" var="one_stye" />
		</c:if>
		<c:if test="${status.index ==2 }">
			<c:set value="class='right-down-floor'" var="one_stye" />
		</c:if>
	</c:if>
	<c:if test="${modelOne.type=='101'}">
		<c:if test="${status.index ==0 }">
			<c:set value="class='left-up-floor'" var="one_stye" />
		</c:if>
		<c:if test="${status.index ==1 }">
			<c:set value="class='right-floor'" var="one_stye" />
		</c:if>
		<c:if test="${status.index ==2 }">
			<c:set value="class='left-down-floor'" var="one_stye" />
		</c:if> 
	</c:if>
	<c:choose>
		<c:when test="${model.type == '1'}">
			<a href="${ctx}/subject/product/list?topicId=${model.refContent}&postArea=0" ${one_stye }>
		</c:when>
		<c:when test="${model.type == '2'}">
			<a href="${ctx}/category/product/list?categoryNo=${model.refContent}&postArea=0" ${one_stye }>
		</c:when>
		<c:when test="${model.type == '3'}">
			<a href="${ctx}/brand/product/list?brandNo=${model.refContent}&postArea=0&WWWWWWWWW" ${one_stye }>
		</c:when>
		<c:when test="${model.type == '4'}">
			<a href="${ctx}/product/detail?productNo=${model.refContent}" ${one_stye }>
		</c:when>
		<c:when test="${model.type == '5'}">
			<a href="${model.refContent}" ${one_stye }>
		</c:when>
		<c:when test="${model.type=='6'}">
	 	   <c:choose>
				<c:when test="${model.refContent=='1'}">
					<a href="${ctx}/coupon/list" ${one_stye }>
				</c:when>
				<c:when test="${model.refContent=='3'}">
					<a href="${ctx}/order/list-1" ${one_stye }>
				</c:when>
				<c:when test="${model.refContent=='4'}">
					<a href="${ctx}/collect/product/list?pageIndex=1&pageSize=20&shopType=1" ${one_stye }>
				</c:when>
				<c:when test="${model.refContent=='2'}">
					<a href="${ctx}/giftCard/productList" ${one_stye }>
				</c:when>
				<c:when test="${model.refContent=='7'}">
					<a href="http://m.aolai.com" ${one_stye }>
				</c:when>
				<c:otherwise>
				<a href="#" ${one_stye }>
				</c:otherwise>
		  </c:choose>
      </c:when>
	  <c:when test="${model.type == '9'}">
			<a href="${ctx}/lable/product/list?tagId=${model.refContent}" ${one_stye } >
	  </c:when>
	   <c:otherwise>
			<a href="#" ${one_stye } >
	   </c:otherwise>
	</c:choose>
	  
	<c:choose>
	    <c:when test="${modelOne.type=='1'}">
<%-- 			<img alt="" src="${cdn:pic(pageContext.request)}/styles/shangpin/images/e.gif" lazy="${fn:substring(model.pic,0,fn:indexOf(model.pic,'-'))}-${model.width}-${model.height}.jpg" width="100%">
 --%>			<img alt="" src="${fn:substring(model.pic,0,fn:indexOf(model.pic,'-'))}-${model.width}-${model.height}.jpg" width="100%">
				   </a>
		</c:when>
		<c:when test="${modelOne.type=='100' ||modelOne.type=='101'}">
			<img alt="" src="${fn:substring(model.pic,0,fn:indexOf(model.pic,'-'))}-${model.width}-${model.height}.jpg" /></a>
		</c:when>
		<c:otherwise>
			<img src="${fn:substring(model.pic,0,fn:indexOf(model.pic,'-'))}-${model.width}-${model.height}.jpg"></a></li>
		</c:otherwise>
    </c:choose>
</c:forEach>
