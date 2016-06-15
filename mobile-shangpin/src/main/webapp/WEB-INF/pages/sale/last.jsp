<%@ page language="java" contentType="text/html; charset=UTF-8" trimDirectiveWhitespaces="true"
    pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/pages/common/include.inc.jsp"%>
  <div class="tabSlider-box">
	  	<div class="hallBox clr" id="last_crazy">
        <c:forEach var="hotSale" items="${hotSales}">
			
		          <h4 class="open">${hotSale.title}</em></h4>
		        	 <c:forEach var="content" items="${hotSale.list}">
			             <a href="<c:url value='/subject/product/list?topicId=${content.refContent}'/>" class="on-sale"><img alt="" src="${cdn:pic(pageContext.request)}/styles/shangpin/images/e.gif" lazy="${fn:substring(content.pic,0,fn:indexOf(content.pic,'-'))}-616-260.jpg">
			              <div class="pic-des">
                                	<p style="overflow:hidden;text-overflow: ellipsis;padding-right:80px;">${content.desc }</p>
                                    <strong class="flag" end-time="${content.endTime }">${content.startTag }</strong>
                        </div>
			             </a>
			             
		           </c:forEach>
		         <c:if test="${fn:length(hotSale.list) == 0}">
					<p class="brand_noresults">暂无活动</p>
				</c:if>
		     </c:forEach>      
	      </div>
	      
   </div>