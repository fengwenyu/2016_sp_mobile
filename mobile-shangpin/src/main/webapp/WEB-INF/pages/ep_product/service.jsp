<%@ page language="java" contentType="text/html; charset=UTF-8" trimDirectiveWhitespaces="true"
    pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/pages/common/include.inc.jsp"%>

<rapid:override name="custum">
	<link href="${cdn:css(pageContext.request)}/styles/shangpin/css/public.css${ver}" rel="stylesheet" />
	<link href="${cdn:css(pageContext.request)}/styles/shangpin/css/page/product/detail.css${ver}" rel="stylesheet" />
	<script type="text/javascript" charset="utf-8">
			loader = SP.core
				.install("${cdn:js(pageContext.request)}/styles/shangpin/js/jquery.min.js${ver}")
			   	.excute(function(){
		          //显示导航浮层方法
		          $(window).scroll(topFixed);
		
		          var menuTopHeight = $('.topFix').offset().top;
		          function topFixed(){  
		            var scrolls = document.body.scrollTop;
		            if (scrolls > menuTopHeight) {
		              $('.topFix').css({
		                position: "fixed"
		              });
		              $('body .alContent').css('margin-top',45);    
		            
		            }else {
		              $('body .alContent').css('margin-top',0); 
		              $('.topFix').css({
		                position: "static"
		              });
		                 
		            }
		          };
		      })
        
	</script>
</rapid:override ><rapid:override name="title">
	保养及售后
</rapid:override>
<rapid:override name="downloadAppShowBottom">
	
</rapid:override>
<rapid:override name="page_title">
	保养及售后
</rapid:override>
<rapid:override name="appLayer">
</rapid:override>
<rapid:override name="content">
	${productTemplate.html }
</rapid:override>


<%@ include file="/WEB-INF/pages/common/another_common_mall_banner.jsp" %> 