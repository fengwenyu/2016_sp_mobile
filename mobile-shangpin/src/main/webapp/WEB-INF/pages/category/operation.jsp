<%@ page language="java" contentType="text/html; charset=UTF-8" trimDirectiveWhitespaces="true"
    pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/pages/common/include.inc.jsp"%>

<rapid:override name="custum">
	<link rel="stylesheet" href="${ctx}/styles/shangpin/css/page/font-css/font-awesome.min.css"/>	
	<link href="${cdn:css(pageContext.request)}/styles/shangpin/css/page/newCategory.css" rel="stylesheet" />
	<script type="text/javascript" charset="utf-8">
			loader = SP.core
				.install("${cdn:js(pageContext.request)}/styles/shangpin/js/zepto.1.1.3.js${ver}")
				.using("${cdn:js(pageContext.request)}/styles/shangpin/js/lazyload.js${ver}")
				.excute()
				.using("${cdn:js(pageContext.request)}/styles/shangpin/js/config.sim.js${ver}")
				.excute()
				.using("${cdn:js(pageContext.request)}/styles/shangpin/js/comm.js${ver}")
				
	</script>
</rapid:override >
<rapid:override name="appLayer">
</rapid:override>
<rapid:override name="content">

<c:if test="${categoryOperationItem!=null }">
	<c:if test="${fn:length(categoryOperationItem.operation) > 0}">
		<c:forEach var="operation" items="${categoryOperationItem.operation}">
			<div class="cate-box">
		          <div class="category-search"> 
		             
		              	<c:if test="${operation.type=='1'}">
		              		<div class="cate-banner">
		              			<c:forEach var="list" items="${operation.list}">
				              		<c:choose>
										<c:when test="${list.type=='1' }">
											<a href="${ctx }/subject/product/list?topicId=${list.refContent}&postArea=0">
										</c:when>
										<c:when test="${list.type=='2'}">
											<a href="${ctx }/category/product/list?categoryNo=${list.refContent}&postArea=0">
											
										</c:when>
										<c:when test="${list.type=='3'}">
											<a href="${ctx }/brand/product/list?brandNo=${list.refContent}&postArea=0">
											
										</c:when>
						
										<c:when test="${list.type=='4'}">
											<a href="<c:url value='/product/detail?productNo=${list.refContent}'/>">
										</c:when>
										<c:when test="${list.type=='5'}">
											<a href="${list.refContent}">
										</c:when>
									
										<c:otherwise>
											<a href="">
										</c:otherwise>
									</c:choose>
			              			<i><img alt="${list.name }"
														src="${cdn:pic(pageContext.request)}/styles/shangpin/images/e.gif"
														lazy="${fn:replace(list.pic,'-0-0','-616-178') }"></i></a>
								</c:forEach>
		              			</div>
		              	</c:if>
		              
		              	<c:if test="${operation.type!='1'}">
			              	<div class="cate-brand-title">
			                    <a href="javascript:;">${operation.name }</a>
			                   <%--  <span>${operation.nameEN }</span> --%>
			                    <em></em>
			                </div>
			                <div class="brandBox clr">
			                	<c:forEach var="list" items="${operation.list}">
				                 	<c:choose>
										<c:when test="${list.type=='1' }">
											<a href="${ctx }/subject/product/list?topicId=${list.refContent}&postArea=0">
										</c:when>
										<c:when test="${list.type=='2'}">
											<a href="${ctx }/category/product/list?categoryNo=${list.refContent}&postArea=0">
										</c:when>
										<c:when test="${list.type=='3'}">
											<a href="${ctx }/brand/product/list?brandNo=${list.refContent}&postArea=0">
										</c:when>
										<c:when test="${list.type=='4'}">
											<a href="<c:url value='/product/detail?productNo=${list.refContent}'/>">
										</c:when>
										<c:when test="${list.type=='5'}">
											<a href="${list.refContent}">
										</c:when>
										<c:otherwise>
											<a href="">
										</c:otherwise>
									</c:choose>
									<c:choose>
										<c:when test="${operation.type=='2'}">
											<img alt="${list.name }"
															src="${cdn:pic(pageContext.request)}/styles/shangpin/images/e.gif"
															lazy="${fn:replace(list.pic,'-0-0','-152-152') }">
										</c:when>
										<c:when test="${operation.type=='3'}">
											<img alt="${list.name }"
															src="${cdn:pic(pageContext.request)}/styles/shangpin/images/e.gif"
															lazy="${fn:replace(list.pic,'-0-0','-152-202') }">
										</c:when>
										<c:when test="${operation.type=='3'}">
											<img alt="${list.name }"
															src="${cdn:pic(pageContext.request)}/styles/shangpin/images/e.gif"
															lazy="${fn:replace(list.pic,'-0-0','-152-202') }">
										</c:when>
											<c:when test="${operation.type=='4'}">
											<img alt="${list.name }"
															src="${cdn:pic(pageContext.request)}/styles/shangpin/images/e.gif"
															lazy="${fn:replace(list.pic,'-0-0','-152-72') }">
										</c:when>
										<c:otherwise></c:otherwise>
									</c:choose>
										<c:if test="${operation.type=='3'}">
			              			<span>${list.name }</span></c:if>
			              			
			              			</a>
			                    </c:forEach>
			                 </div>
		                 </c:if>
		                
		             
		                
		                
		                
		          </div>
	          </c:forEach>
           </c:if>
      </c:if>
</rapid:override>


<%@ include file="/WEB-INF/pages/common/common.jsp" %>