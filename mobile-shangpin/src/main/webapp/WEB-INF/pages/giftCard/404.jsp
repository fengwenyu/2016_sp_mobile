<%@ page language="java" contentType="text/html; charset=UTF-8"  pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/pages/common/include.inc.jsp"%>

<rapid:override name="custum">
<style>
.main_box img{ width:100%}
</style>
<script src="${cdn:js(pageContext.request)}/styles/shangpin/js/core.js" type="text/javascript" charset="utf-8"></script>
<script type="text/javascript" charset="utf-8">
  var ver = Math.random();
    loader = SP.core
      .install("${cdn:js(pageContext.request)}/styles/shangpin/js/jquery.min.js?" + ver)  //jquery库文件
        .using("${cdn:js(pageContext.request)}/styles/shangpin/js/lazyload.js?" + ver)    //图片懒加载
        .excute()
        .using("${cdn:js(pageContext.request)}/styles/shangpin/js/config.sim.js?" + ver)  //图片懒加载
        .using("${cdn:js(pageContext.request)}/styles/shangpin/js/j.dialogs.js?" + ver)  //图片懒加载
        .excute()
        .using("${cdn:js(pageContext.request)}/styles/shangpin/js/page/giftCard_send.js?41" + ver);    //页面专用JS
      
</script>
<link href="${cdn:css(pageContext.request)}/styles/shangpin/css/base.css${ver}" rel="stylesheet" />
<link href="${cdn:css(pageContext.request)}/styles/shangpin/css/public.css${ver}" rel="stylesheet" />
<link href="${cdn:css(pageContext.request)}/styles/shangpin/css/page/giftcard/giftCard_send2.css?34${ver}" rel="stylesheet" /><!--页面专用CSS-->
</rapid:override>
<rapid:override name="content">

	<div class="error_box_new">
		<br>
		<br>
		<br>
		<h5><font color="red">错误信息：${errorMsg }</font></h5>
		<br>
		<br>
		<br>
		<br>
		<br>
	</div>
 </rapid:override>
<%@ include file="/WEB-INF/pages/common/another_common_mall_banner.jsp" %>