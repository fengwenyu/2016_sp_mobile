<%@ page language="java" contentType="text/html; charset=UTF-8" trimDirectiveWhitespaces="true"
    pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/pages/common/include.inc.jsp"%>
<rapid:override name="custum">
	<link href="${cdn:css(pageContext.request)}/styles/shangpin/css/app/css/base.css" rel="stylesheet" />
	<link href="${cdn:css(pageContext.request)}/styles/shangpin/css/app/css/page/detail.new.css" rel="stylesheet" />
	<script type="text/javascript" charset="utf-8">
			loader = SP.core
				.install("${cdn:js(pageContext.request)}/styles/shangpin/js/zepto.min.js${ver}")
				.using("${cdn:js(pageContext.request)}/styles/shangpin/js/lazyload.js${ver}")
				.using("${cdn:js(pageContext.request)}/styles/shangpin/js/config.sim.js${ver}")
				.using("${cdn:js(pageContext.request)}/styles/shangpin/js/comm.js${ver}")
				.excute();
	</script>
	
</rapid:override>
<rapid:override name="header">

</rapid:override>
<rapid:override name="content">
	 <style>
  .alProd_intro img{width:100%;}
  </style>
  <section class="detail_block">
    <section class="alProd_intro">
    	<c:choose>
    		<c:when test="${merchandise.recommend!=null&&merchandise.recommend!=''&&merchandise.recommend!='null' }">
    			${merchandise.recommend }
    		</c:when>
    		<c:otherwise>
    			<div class="alprod_none" style="display:block;">无更多详情内容</div>
    		</c:otherwise>
    	</c:choose>
    </section> 
  </section>
</rapid:override>
<rapid:override name="footer">

</rapid:override>
<%@ include file="/WEB-INF/pages/common/base.jsp" %> 