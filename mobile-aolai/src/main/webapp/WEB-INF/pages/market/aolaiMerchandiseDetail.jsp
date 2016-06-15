<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%> 
<%@ taglib prefix="cdn" uri="http://m.shangpin.com/cdn" %>


<%
	String path = request.getContextPath();
%>
<!doctype html>
<html lang="zh-CN">
<head>
<meta charset="utf-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0, user-scalable=yes, minimum-scale=1.0, maximum-scale=4.0">
<link rel="shortcut icon" href="http://pic11.shangpin.com/shangpin/images/logo/favicon.ico" />
<meta name="format-detection" content="telephone=no">
<!-- 开启对web app程序的支持 -->
<meta name="apple-mobile-web-app-capable" content="yes">
<!-- 全屏模式浏览 -->
<meta name="apple-touch-fullscreen" content="yes">
<!-- 改变Safari状态栏的外观 -->
<meta name="apple-mobile-web-app-status-bar-style" content="black">

<title>${merchandise.brand}${merchandise.productname }_尚品网触屏版</title>
<meta name="keywords" content="${merchandise.brand}${merchandise.productname },正品,价格,图片,折扣,评论" /> 
<meta name="description" content="尚品网手机触屏版为您提供正品${merchandise.brand}${merchandise.productname }购买、价格、图片、折扣、评论等信息。了解更多${merchandise.brand}${merchandise.productname }商品细节。" />
<link href="${cdn:css(pageContext.request)}/app/css/base.css" rel="stylesheet" />
<link href="${cdn:css(pageContext.request)}/app/css/page/detail.new.css" rel="stylesheet" />

<script src="${cdn:js(pageContext.request)}/js/core.js" type="text/javascript" charset="utf-8"></script>
<script type="text/javascript" charset="utf-8">
	var ver = "?20121023001";
		loader = SP.core
			.install("${cdn:js(pageContext.request)}/js/zepto.min.js" + ver)
			.using("${cdn:js(pageContext.request)}/js/lazyload.js" + ver)
			.excute()
			.using("${cdn:js(pageContext.request)}/js/config.sim.js" + ver);
</script>

<style>
/*编辑推荐*/
.sp_detail_editbox_wp .editbox_wp {
	width: 340px;
	margin: 0 auto 10px;
	position: relative;
}

.sp_detail_editbox_wp .editbox_wp img{
	width:100%;
	height:auto;
}
</style>
</head>
<body>
<div class="alContent">
  <style>
  .alProd_intro img{width:100%;}
  </style>
  <section class="detail_block">
    <section class="alProd_intro">
        <s:if test="%{merchandise.recommend!=null&&merchandise.recommend!=''&&merchandise.recommend!='null'}">
        <!-- <h1>编辑推荐</h1> -->	
			${merchandise.recommend }
	  	</s:if>
	  	<s:else>
	  		<div class="alprod_none" style="display:block;">无更多详情内容</div>
	  	</s:else>
    </section> 
    <input type="hidden" value="<%=path%>" id="path">
  </section>

</div>
 <!--  <img src='http://mvp.mediav.com/t?type=3&db=none&jzqs=m-24361-0&jzqv=3.2.7.12'></img><img src='http://ckmap.mediav.com/m?tid=24361&tck=no_tck'></img>-->	
</body>
</html>
  
