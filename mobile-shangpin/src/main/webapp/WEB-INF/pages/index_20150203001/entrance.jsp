<%@ page language="java" contentType="text/html; charset=UTF-8"
	trimDirectiveWhitespaces="true" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/pages/common/include.inc.jsp"%>


	<c:if test="${fn:length(entrances)>0}">
		<div id="navs" class="clr">
			<c:forEach var="entrance" items="${entrances}">
			<c:choose>
				<c:when test="${entrance.type=='1' }">
					<a href="${ctx }/subject/product/list?topicId=${entrance.refContent}&postArea=0">
						<i><img alt="${entrance.name }"
									src="${cdn:pic(pageContext.request)}/styles/shangpin/images/e.gif"
									lazy="${fn:replace(entrance.pic,'-10-10','-80-80') }"></i>${entrance.name }</a>
				</c:when>
				<c:when test="${entrance.type=='2'}">
					<a href="${ctx }/category/product/list?categoryNo=${entrance.refContent}&postArea=0">
						<i><img alt="${entrance.name }"
									src="${cdn:pic(pageContext.request)}/styles/shangpin/images/e.gif"
									lazy="${fn:replace(entrance.pic,'-10-10','-80-80') }"></i>${entrance.name }</a>
				</c:when>
				<c:when test="${entrance.type=='3'}">
					<a href="${ctx }/brand/product/list?brandNo=${entrance.refContent}&postArea=0">
						<i><img alt="${entrance.name }"
									src="${cdn:pic(pageContext.request)}/styles/shangpin/images/e.gif"
									lazy="${fn:replace(entrance.pic,'-10-10','-80-80') }"></i>${entrance.name }</a>
				</c:when>

				<c:when test="${entrance.type=='4'}">
					<a href="<c:url value='/product/detail?productNo=${entrance.refContent}'/>">
						<i><img alt="${entrance.name }"
									src="${cdn:pic(pageContext.request)}/styles/shangpin/images/e.gif"
									lazy="${fn:replace(entrance.pic,'-10-10','-80-80') }"></i>${entrance.name }</a>
				</c:when>
				<c:when test="${entrance.type=='5'}">
					<a href="${entrance.refContent}">
						<i><img alt="${entrance.name }"
									src="${cdn:pic(pageContext.request)}/styles/shangpin/images/e.gif"
									lazy="${fn:replace(entrance.pic,'-10-10','-80-80') }"></i>${entrance.name }</a>
				</c:when>
				<c:when test="${entrance.type=='6'}">
					 <c:choose>
							<c:when test="${entrance.refContent=='1'}">
								<a href="${ctx}/coupon/list">
								<i><img alt="${entrance.name }"
									src="${cdn:pic(pageContext.request)}/styles/shangpin/images/e.gif"
									lazy="${fn:replace(entrance.pic,'-10-10','-80-80') }"></i>${entrance.name }</a>
							</c:when>
							<c:when test="${entrance.refContent=='3'}">
								<a href="${ctx}/order/list-1">
									<i><img alt="${entrance.name }"
									src="${cdn:pic(pageContext.request)}/styles/shangpin/images/e.gif"
									lazy="${fn:replace(entrance.pic,'-10-10','-80-80') }"></i>${entrance.name }</a>
							</c:when>
							<c:when test="${entrance.refContent=='4'}">
								<a href="${ctx}/collect/product/list?pageIndex=1&pageSize=20&shopType=1">
									<i><img alt="${entrance.name }"
									src="${cdn:pic(pageContext.request)}/styles/shangpin/images/e.gif"
									lazy="${fn:replace(entrance.pic,'-10-10','-80-80') }"></i>${entrance.name }</a>
							</c:when>
							<c:when test="${entrance.refContent=='2'}">
								<a href="${ctx}/giftCard/productList">
									<i><img alt="${entrance.name }"
									src="${cdn:pic(pageContext.request)}/styles/shangpin/images/e.gif"
									lazy="${fn:replace(entrance.pic,'-10-10','-80-80') }"></i>${entrance.name }</a>
							</c:when>
							<c:otherwise>
								<c:set value="${fn:split(entrance.refContent, '|')}" var="refs" />
								<c:if test="${refs[0]==6}"> 
									<a href="${ctx}/category/page?categoryId=${refs[1]}">
										<i><img alt="${entrance.name }"
										src="${cdn:pic(pageContext.request)}/styles/shangpin/images/e.gif"
										lazy="${fn:replace(entrance.pic,'-10-10','-80-80') }"></i>${entrance.name }
									</a>
								</c:if>
							</c:otherwise>
						</c:choose>
				</c:when>
				<c:otherwise>
				
				</c:otherwise>
			</c:choose>
			
		</c:forEach>
		</div>
	</c:if>
