<%@ page language="java" contentType="text/html; charset=UTF-8" trimDirectiveWhitespaces="true"
    pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/pages/common/include.inc.jsp"%>

<rapid:override name="custum">
	<link href="${cdn:css(pageContext.request)}/styles/shangpin/css/page/discover.css${ver}" rel="stylesheet" />
	<script type="text/javascript" charset="utf-8">
			loader = SP.core
			.install("${cdn:js(pageContext.request)}/styles/shangpin/js/jquery.min.js${ver}")
			.using("${cdn:js(pageContext.request)}/styles/shangpin/js/lazyload.js${ver}")
			.excute()
			.using("${cdn:js(pageContext.request)}/styles/shangpin/js/config.sim.js${ver}")
			.excute(function(){
				isHome(false);
			});
			
	</script>
</rapid:override >

<%-- 浏览器标题 --%>
<rapid:override name="title">
	潮流资讯
</rapid:override>

<%-- 页标题 --%>
<rapid:override name="page_title">
	潮流资讯
</rapid:override>
<rapid:override name="content">
<div>
	<section>
		<c:forEach var="fashion" items="${fashionList}" >
			    <div class="discover_box">
			    	<a href="<c:url value='/fashion/detail?id=${fashion.id}"'/>>
			    		<li>
			        		<h2>${fashion.title}</h2>
			        		<h4>${fn:substring(fashion.releaseDate,0,10)}</h4>
			        		<c:if test="${ fashion.coverImg!=null}">
			        			<img src="${cdn:pic(pageContext.request)}/styles/shangpin/images/e.gif" lazy="${fn:substring(fashion.coverImg,0,fn:indexOf(fashion.coverImg,'-'))}-600-320.jpg" height="160" width="300">
			        		</c:if>
			        		<c:if test="${fashion.smallCoverImg!=null}">
			        			<img src="${cdn:pic(pageContext.request)}/styles/shangpin//images/e.gif" lazy="${fn:substring(fashion.smallCoverImg,0,fn:indexOf(fashion.smallCoverImg,'-'))}-200-200.jpg" height="200" width="200">
			        		</c:if>
			          		<p>
			            		<em>${fashion.digest}</em>
			          		</p>
			    		</li>
			    	</a>
			    </div>
	    </c:forEach>
	</section>
</div>
</rapid:override> 
<%@ include file="/WEB-INF/pages/common/common.jsp" %> 



