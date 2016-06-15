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
  <jsp:include page="../common/header.jsp"/>  
  <div class="sp_sign_list">
    <a href="<%=path %>/accountaction!registerui?loginFrom=" target="blank">
      <img src="<%=path %>/images/2014520/sign_btn01.gif" width="115" height="40">
    </a>
    
    <img src="<%=path %>/images/2014520/sign_bg01.jpg" width="320" height="126" />
    <img src="<%=path %>/images/2014520/sign_bg02.jpg" width="320" height="56" />
    <img src="<%=path %>/images/2014520/sign_bg03.jpg" width="320" height="120" />
    <img src="<%=path %>/images/2014520/sign_bg04.jpg" width="320" height="117" />
    
  </div> 
  <div style="width:80%; vertical-align:middle; align:center;margin: 0 auto;"><a href="<%=path%>/520" class="alOrder_buyBtn alOrder_submitBtn" >返回主会场</a></div>
   <jsp:include page="../common/footer.jsp"></jsp:include>
</body>
</html>
