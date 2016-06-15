<%@ page language="java" contentType="text/html; charset=UTF-8"
	trimDirectiveWhitespaces="true" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/pages/common/include.inc.jsp"%>

<rapid:override name="custum">
<style>
.main_box{ min-width:320px; max-width:640px; margin:0 auto}
.main_box img{ width:100%}
</style>
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
	
</rapid:override>
<rapid:override name="content">

 <!--内容区域 start-->
    <div class="main_box">
  	  <img src="${cdn:pic(pageContext.request)}/styles/shangpin/images/20150313aolai/img01.jpg" />
      <img src="${cdn:pic(pageContext.request)}/styles/shangpin/images/20150313aolai/img02.jpg" />
      <img src="${cdn:pic(pageContext.request)}/styles/shangpin/images/20150313aolai/img03.jpg" />
      <img src="${cdn:pic(pageContext.request)}/styles/shangpin/images/20150313aolai/img04.jpg" />
      <img src="${cdn:pic(pageContext.request)}/styles/shangpin/images/e.gif" lazy="${cdn:pic(pageContext.request)}/styles/shangpin/images/20150313aolai/img05.jpg" />
    </div>
    	
</rapid:override>
<rapid:override name="footer">
</rapid:override>
<%@ include file="/WEB-INF/pages/common/base.jsp" %> 
