<%@ page language="java" contentType="text/html; charset=UTF-8" trimDirectiveWhitespaces="true"
    pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/pages/common/include.inc.jsp"%>
  <div class="tabSlider-box curr">
     	<div class="hallBox clr">
		     <c:forEach var="hotSale" items="${hotSales}">
		     	<c:choose>
					<c:when test="${hotSale.type=='1' }">
					  	<h4 class="open">${hotSale.title}<em></em></h4>
						 <c:forEach var="content" items="${hotSale.list}" varStatus="status">
				           <a href="<c:url value='/subject/product/list?topicId=${content.refContent}'/>" class="tag"><img alt="" src="${fn:substring(content.pic,0,fn:indexOf(content.pic,'-'))}-616-260.jpg">
				        	   <div class="pic-des">
                                	<p>${content.desc }</p>
                                </div>
				        	   </a> 
			     		 </c:forEach>
			           </c:when>
			        <c:otherwise>
			          <h4 class="open">${hotSale.title}</em></h4>
			        	 <c:forEach var="content" items="${hotSale.list}">
			        	 <c:choose>
				        	 <c:when test="${content.startTime>nowTime}">
				        	    <a href="<c:url value='/subject/product/list?topicId=${content.refContent}'/>" class="on-sale no_open" startTime="${content.startTime}" startTag="${content.startTag}"><img alt="" src="${cdn:pic(pageContext.request)}/styles/shangpin/images/e.gif" lazy="${fn:substring(content.pic,0,fn:indexOf(content.pic,'-'))}-616-260.jpg"><span><em class="open-time">${content.startTag }</em>开启</span>	
				        	    <div class="pic-des"><p>${content.desc }</p></div>
				        	    </a>
				        	 </c:when>
				        	 <c:otherwise>
				        	    <a href="<c:url value='/subject/product/list?topicId=${content.refContent}'/>" ><img alt="" src="${cdn:pic(pageContext.request)}/styles/shangpin/images/e.gif" lazy="${fn:substring(content.pic,0,fn:indexOf(content.pic,'-'))}-616-260.jpg" >
				        	     <div class="pic-des">
                                	<p>${content.desc }</p>
                                </div>
				        	    </a>
				        	 
				        	 </c:otherwise>
			          	</c:choose>
			           </c:forEach>
			        </c:otherwise>
		        </c:choose>
		     </c:forEach>  
		      	<c:if test="${fn:length(hotSales) == 0}">
				<p class="brand_noresults">暂无活动</p>
		</c:if>
       </div>
      
 </div>