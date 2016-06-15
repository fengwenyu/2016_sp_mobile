<%@ page language="java" contentType="text/html; charset=UTF-8" trimDirectiveWhitespaces="true"
    pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/pages/common/include.inc.jsp"%>

<rapid:override name="custum">
	<link href="${cdn:css(pageContext.request)}/styles/shangpin/css/page/downLoad.css${ver}" rel="stylesheet" />
	<script type="text/javascript" charset="utf-8">
			loader = SP.core
				.install("${cdn:js(pageContext.request)}/styles/shangpin/js/jquery.min.js${ver}")
				.using("${cdn:js(pageContext.request)}/styles/shangpin/js/lazyload.js${ver}")
				.excute()
				.using("${cdn:js(pageContext.request)}/styles/shangpin/js/config.sim.js${ver}")
				.using("${cdn:js(pageContext.request)}/styles/shangpin/js/download.js${ver}")
				.excute(function(){
		
			});
			
	</script>
</rapid:override > 
<%-- 浏览器标题 --%>
<rapid:override name="title">
	尚品网
</rapid:override>
<rapid:override name="header">
</rapid:override>
<rapid:override name="content">
<div class="wrapper">
	<section class="slidePage" id="page">
	  <div class="slideMain">
          <div class="layer layer0"></div>
          <!--  <div class="layer layer1"></div>
          <div class="layer layer2"></div>-->
          
          <div class="down_page">
        	<img src="${cdn:pic(pageContext.request)}/styles/shangpin/images/download/app_download/download_img01.png" width="100%" />
      		<div class="download_box">
        	<img src="${cdn:pic(pageContext.request)}/styles/shangpin/images/download/app_download/download_img02.png" width="100%" />
            <a href="http://mp.weixin.qq.com/mp/redirect?url=https%3a%2f%2fitunes.apple.com%2fcn%2fapp%2fid598127498%3fmt%3d8%23rd" class="phone_btn"></a>
            	<c:choose>
	     			<c:when test="${checkWX }">
	     				<a href="javascript:;" id="aolaiDownload" class="android_btn"></a>
	     			</c:when>
	     			<c:otherwise>
	     				<a href="${ctx }/download.action?p=102&ch=4&fileName=shangpin_4.apk" class="android_btn"></a>
	     			</c:otherwise>
     			</c:choose>
         	</div>
    	</div>
          
	  </div>
    </section>
</div>
</rapid:override>
<!-- 弹出层内容 -->
<div id="popLayer"><img src="${cdn:css(pageContext.request)}/styles/shangpin/images/weixin_tip.png" width="320" height="225"></div>
<%@ include file="/WEB-INF/pages/common/another_common.jsp" %> 
