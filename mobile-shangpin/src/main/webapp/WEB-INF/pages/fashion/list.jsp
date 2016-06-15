<%@ page language="java" contentType="text/html; charset=UTF-8" trimDirectiveWhitespaces="true"
    pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/pages/common/include.inc.jsp"%>

<rapid:override name="custum">
	<link href="${cdn:css(pageContext.request)}/styles/shangpin/css/public.css" rel="stylesheet" />
	<link href="${cdn:css(pageContext.request)}/styles/shangpin/css/page/fashionList.css" rel="stylesheet" />

	<script type="text/javascript" charset="utf-8">
			loader = SP.core
				.install("${cdn:js(pageContext.request)}/styles/shangpin/js/jquery.min.js${ver}")
				.using("${cdn:js(pageContext.request)}/styles/shangpin/js/lazyload.js${ver}")
				.excute()
				.using("${cdn:js(pageContext.request)}/styles/shangpin/js/config.sim.js${ver}")
				.excute()
				
				.using("${cdn:js(pageContext.request)}/styles/shangpin/js/downloadAppShow.js${ver}")
				.excute()
				.using("${cdn:js(pageContext.request)}/styles/shangpin/js/fashionList.js${ver}");  
				
	</script>
</rapid:override >
<rapid:override name="page_title">
	潮流趋势
</rapid:override>
<rapid:override name="content">
	<div class="list-menu">
  		<div class="menu-nav" id="menu">
            <ul class="tab_info" >
            <c:choose>
                      	  <c:when test="${type=='1' }">
                      	       <li class="curr"><a href="${ctx }/fashion/list?type=1">潮搭</a></li>
		                       <li><a href="${ctx }/fashion/list?type=2">流行公告</a></li>
		                       <li><a href="${ctx }/fashion/list?type=3">明星衣品</a></li>
		                       <li><a href="${ctx }/fashion/list?type=4">抢手货</a></li>
                      	  </c:when>
                      	   <c:when test="${type=='2' }">
                      	       <li><a href="${ctx }/fashion/list?type=1">潮搭</a></li>
		                       <li class="curr"><a href="${ctx }/fashion/list?type=2">流行公告</a></li>
		                       <li><a href="${ctx }/fashion/list?type=3">明星衣品</a></li>
		                       <li><a href="${ctx }/fashion/list?type=4">抢手货</a></li>
                      	  </c:when>
                      	  <c:when test="${type=='3' }">
                      	       <li><a href="${ctx }/fashion/list?type=1">潮搭</a></li>
		                       <li><a href="${ctx }/fashion/list?type=2">流行公告</a></li>
		                       <li class="curr"><a href="${ctx }/fashion/list?type=3">明星衣品</a></li>
		                       <li><a href="${ctx }/fashion/list?type=4">抢手货</a></li>
                      	  </c:when>
                      	  <c:otherwise>
                      	       <li><a href="${ctx }/fashion/list?type=1">潮搭</a></li>
		                       <li><a href="${ctx }/fashion/list?type=2">流行公告</a></li>
		                       <li><a href="${ctx }/fashion/list?type=3">明星衣品</a></li>
		                       <li class="curr"><a href="${ctx }/fashion/list?type=4">抢手货</a></li>
                      	  </c:otherwise>
                      </c:choose>
                    
            </ul>
          </div>
	</div>
		<div class="content_info">
            
           	 	<div class="content_detail content_list">
           	 	  <c:choose>
                        <c:when test="${fn:length(fashionList) > 0 }">
                           <c:forEach var="fashion" items="${fashionList }">
                               <div class="fashion_box clr" id="${fashion.refContent }">
                                   <c:choose>
										<c:when test="${fashion.type=='1' }">
											<a href="${ctx }/subject/product/list?topicId=${fashion.refContent}&postArea=0">
										</c:when>
										<c:when test="${fashion.type=='2'}">
											<a href="${ctx }/category/product/list?categoryNo=${fashion.refContent}&postArea=0">
											
										</c:when>
										<c:when test="${fashion.type=='3'}">
											<a href="${ctx }/brand/product/list?brandNo=${fashion.refContent}&postArea=0">
											
										</c:when>
						
										<c:when test="${fashion.type=='4'}">
											<a href="<c:url value='/product/detail?productNo=${fashion.refContent}'/>">
										</c:when>
										<c:when test="${fashion.type=='5'}">
											<a href="${fashion.refContent}">
										</c:when>
										<c:otherwise>
											   <a href="${ctx }/fashion/info?id=${fashion.refContent }">
										</c:otherwise>
									</c:choose>
                                    <h2>${fashion.name }</h2>
                                    <h4>${fashion.pubTime }</h4>
                                    <img alt="${fashion.name }" src="${fn:replace(fashion.pic,'-10-10','-600-320') }">
                                    <p>${fashion.summary }</p>
                                    </a>
                                </div>
                           </c:forEach>   
                         </c:when>
                         <c:otherwise>
                         	<p class="brand_noresults">抱歉！ 暂无相关的内容！</p>
                         
                         </c:otherwise>
                   </c:choose>
            	</div>
         </div>
</rapid:override>


<%@ include file="/WEB-INF/pages/common/common_normal_banner.jsp" %> 
