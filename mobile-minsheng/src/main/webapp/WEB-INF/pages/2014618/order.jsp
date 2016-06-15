<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ taglib uri="/tlds/registPrompt" prefix="sprptag"%>
<%@ taglib uri="/tlds/activityName" prefix="spantag"%> 
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + path + "/";
%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<!doctype html>
<html lang="zh-CN">
<head>
<meta charset="utf-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0, user-scalable=no, minimum-scale=1.0, maximum-scale=1.0">

<!-- 开启对web app程序的支持 -->
<meta name="apple-mobile-web-app-capable" content="yes">
<!-- 全屏模式浏览 -->
<meta name="apple-touch-fullscreen" content="yes">
<!-- 改变Safari状态栏的外观 -->
<meta name="apple-mobile-web-app-status-bar-style" content="black">

<title>尚品</title>
<link href="<%=path %>/css/base.css" rel="stylesheet" />
<link href="<%=path %>/css/page/anniversary_2014520.css" rel="stylesheet" />

<!-- icon -->
<link rel="apple-touch-icon" href="<%=path %>/images/touch-icon-iphone.png">
<link rel="apple-touch-icon" sizes="72x72" href="<%=path %>/images/touch-icon-ipad.png">
<link rel="apple-touch-icon" sizes="114x114" href="<%=path %>/images/touch-icon-iphone4.png">
<link rel="apple-touch-icon" sizes="144x144" href="<%=path %>/images/touch-icon-newipad.png">
<!-- 启动自定义图片(png, 320X640) -->
<link rel="apple-touch-startup-image" sizes="72x72" href="<%=path %>/images/logo/loading.png">

<script src="<%=path %>/js/core.js" type="text/javascript" charset="utf-8"></script>
<script type="text/javascript" charset="utf-8">
	var ver = "?20121023001";
		loader = SP.core
			.install("<%=path %>/js/jquery.min.js" + ver)
			.using("<%=path %>/js/j.lazyload.js" + ver)
			.excute()
			.using("<%=path %>/js/config.sim.js" + ver)
			.excute()
      		.using("<%=path %>/js/2014520/list.js" + ver)
			.excute(function(){
				isHome(false);
			});
</script>
</head>

<body>
  <div class="sp_order_list">
    <a href="http://m.shangpin.com" target="blank" class="left">
      <img src="<%=path %>/images/2014520/order_btn01.gif" width="115" height="40">
    </a>
    <a href="http://m.aolai.com" target="blank" class="right">
      <img src="<%=path %>/images/2014520/order_btn02.gif" width="115" height="40">
    </a>
    <img src="<%=path %>/images/2014520/order_bg01.jpg" width="320" height="116" />
    <img src="<%=path %>/images/2014520/order_bg02.jpg" width="320" height="65" />
    <img src="<%=path %>/images/2014520/order_bg03.jpg" width="320" height="106" />
    <img src="<%=path %>/images/2014520/order_bg04.jpg" width="320" height="70" />
    <img src="<%=path %>/images/2014520/order_bg05.jpg" width="320" height="83" />
  </div> 
   <jsp:include page="../common/footer.jsp"></jsp:include>
</body>
</html>
