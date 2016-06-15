<%@ page language="java" contentType="text/html; charset=UTF-8" trimDirectiveWhitespaces="true"
    pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/pages/common/include.inc.jsp"%>

<rapid:override name="custum">
	<link rel="stylesheet" href="${cdn:css(pageContext.request)}/styles/shangpin/css/page/font-css/font-awesome.min.css?${ver}">
	<link href="${cdn:css(pageContext.request)}/styles/shangpin/css/page/meet/share.css?${ver}" rel="stylesheet" />
	<link href="${cdn:css(pageContext.request)}/styles/shangpin/css/base.css?${ver}" rel="stylesheet" />
	<link href="${cdn:css(pageContext.request)}/styles/shangpin/css/page/banner.css?${ver}" rel="stylesheet" />
	<link href="${cdn:css(pageContext.request)}/styles/shangpin/css/swiper.min.css" rel="stylesheet" />
	<script type="text/javascript" charset="utf-8">
		loader = SP.core
		.install("${cdn:js(pageContext.request)}/styles/shangpin/js/jquery.min.js${ver}")
		.using("${cdn:js(pageContext.request)}/styles/shangpin/js/lazyload.js${ver}")
		.excute()
		.using("${cdn:js(pageContext.request)}/styles/shangpin/js/config.sim.js${ver}")
		.excute();
	</script>
</rapid:override>
<rapid:override name="header">
	<c:if test="${!checkWX&&!checkAPP}">
		<div class="topFix">
			<section>
				<div class="topBack">
					<a href="javascript:history.go(-1);" class="backBtn">&nbsp;</a> <span>
						TOPSHOP
					</span>

					<ul class="alUser_icon clr"></ul>
				</div>
			</section>
		</div>
	</c:if>
</rapid:override>

<rapid:override name="content">
		<c:choose>
			<c:when test="${!checkWX&&!checkAPP}">
				<div class="alContent">
			</c:when>
			<c:otherwise>
				<div class="alContent" style="margin-top: 0;">
			</c:otherwise>
		</c:choose>
		
		<div class="conbox"> 
		   <p> <img src="http://pic6.shangpin.com/group1/M00/F2/A2/rBQKaFbXsSqAfX-YAAD41T9EYn0782.jpg"> <img src="http://pic2.shangpin.com/group1/M00/F2/A2/rBQKaFbXsSqAEuQXAACqh2JlfTE277.jpg"> <img src="http://pic4.shangpin.com/group1/M00/F2/A3/rBQKaFbXsSyALjSdAAD-KeNr-1w949.jpg"> <img src="http://pic3.shangpin.com/group1/M00/F2/A3/rBQKaFbXsSyAeQ8JAACx01GrKVw739.jpg"> </p> 
		   <p class="fixed-high"><img src="http://pic3.shangpin.com/group1/M00/F2/A3/rBQKaFbXsS2AdrS9AAB-4Qj4ByQ189.jpg"></p> 
		   <p class="fixed-high"><img src="http://pic1.shangpin.com/group1/M00/F2/A3/rBQKaFbXsS6AZ6RIAADYcwZxKrY280.jpg"></p> 
		   <p><img src="http://pic4.shangpin.com/group1/M00/F2/A3/rBQKaFbXsS-AQ8p_AACc0wsufQM341.jpg"></p> 
		   <div class="video_nav"> 
		    <a href="" class="show_video" data-id="XMTQ1MzY2OTQ1Ng"></a> 
		    <img src="http://pic2.shangpin.com/group1/M00/F2/A3/rBQKaFbXsUGAUBRyAAA3HNi_Hy4200.jpg"> 
		   </div> 
		   <p><img src="http://pic6.shangpin.com/group1/M00/F2/A3/rBQKaFbXsTCAdYiVAAB0uxfyFEk433.jpg"></p> 
		   <p><img src="http://pic1.shangpin.com/group1/M00/F2/A3/rBQKaFbXsTGAH0Z6AAC9xSZk90I452.jpg"></p> 
		   <p class="fixed-high"><img src="http://pic2.shangpin.com/group1/M00/F2/A3/rBQKaFbXsTKAPq_iAAEK6A8YC4k560.jpg"></p> 
		   <p><img src="http://pic2.shangpin.com/group1/M00/F2/A3/rBQKaFbXsTOAGm5tAABvvslLk2A762.jpg"></p> 
		   <p class="fixed-high"><img src="http://pic1.shangpin.com/group1/M00/F2/A3/rBQKaFbXsTWAcRtMAAExJpUlY4k006.jpg"></p> 
		   <p class="fixed-high"><img src="http://pic3.shangpin.com/group1/M00/F2/A3/rBQKaFbXsTWAPTOjAACvm-O7Dbo118.jpg"></p> 
		   <p class="fixed-high"><img src="http://pic4.shangpin.com/group1/M00/F2/A3/rBQKaFbXsTaAaO-WAADhmsfM4uQ903.jpg"></p> 
		   <p class="fixed-high"><img src="http://pic3.shangpin.com/group1/M00/F2/A3/rBQKaFbXsTeAEtt4AADM94GINDc291.jpg"></p> 
		   <p class="fixed-high"><img src="http://pic4.shangpin.com/group1/M00/F2/A3/rBQKaFbXsTiAJhKvAADkF8K9Lik971.jpg"></p> 
		   <p class="fixed-high"><img src="http://pic4.shangpin.com/group1/M00/F2/A3/rBQKaFbXsTmAb58IAACqKFjRe64380.jpg"></p> 
		   <p class="fixed-high"><img src="http://pic2.shangpin.com/group1/M00/F2/A3/rBQKaFbXsTqAc4hfAADP8NmXVMA156.jpg"></p> 
		   <p class="fixed-high"><img src="http://pic6.shangpin.com/group1/M00/F2/A3/rBQKaFbXsTuAPIDKAADW8zxh7gc588.jpg"></p> 
		   <p class="fixed-high"><img src="http://pic6.shangpin.com/group1/M00/F2/A3/rBQKaFbXsTyAKLr4AADFzzOQToE735.jpg"></p> 
		   <p class="fixed-high"><img src="http://pic1.shangpin.com/group1/M00/F2/A3/rBQKaFbXsT2AadnBAADXBAkUSew664.jpg"></p> 
		   <p><img src="http://pic3.shangpin.com/group1/M00/F2/A3/rBQKaFbXsT-AIQFrAAFCgI2UMUs302.jpg"></p> 
		   <div class="href_box"> 
	     		<p><a href="http://m.shangpin.com/brand/product/list?brandNo=B1885&postArea=0&WWWWWWWWW"><img src="http://pic3.shangpin.com/group1/M00/F2/A3/rBQKaFbXsT-AU6YeAAAaXZWmjdk915.jpg"></a></p> 
	   	   </div> 
		 </div>
		
	</div>
</rapid:override>
<rapid:override name="footer">
  	
 </rapid:override>
<%@ include file="/WEB-INF/pages/common/another_base.jsp" %> 