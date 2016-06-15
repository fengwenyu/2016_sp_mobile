<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + path + "/";
%>
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
<title>购物车</title>
<link href="<%=path%>/css/base.css" rel="stylesheet" />
<link href="<%=path%>/css/page/weixin/order.new.css" rel="stylesheet" />
<!-- icon -->
<link rel="apple-touch-icon" href="<%=path%>/images/touch-icon-iphone.png">
<link rel="apple-touch-icon" sizes="72x72" href="<%=path%>/images/touch-icon-ipad.png">
<link rel="apple-touch-icon" sizes="114x114" href="<%=path%>/images/touch-icon-iphone4.png">
<link rel="apple-touch-icon" sizes="144x144" href="<%=path%>/images/touch-icon-newipad.png">
<!-- 启动自定义图片(png, 320X640) -->
<link rel="apple-touch-startup-image" sizes="72x72" href="<%=path%>/images/logo/loading.png">
<script src="<%=path%>/js/core.js" type="text/javascript" charset="utf-8"></script>
<script type="text/javascript" charset="utf-8">
	var ver = "?20121023001";
		loader = SP.core
			.install("<%=path%>/js/jquery.min.js?" + ver)
      .using("<%=path%>/js/lazyload.js?" + ver)
      .excute()
      .using("<%=path%>/js/config.sim.js?" + ver)
      .using("<%=path%>/js/j.floatCalculate.js" + ver) //计算价格
      .using("<%=path%>/js/j.appDialogs.js" + ver) //弹窗
			.excute()
			.using("<%=path%>/js/weixin/order.js" + ver);
</script>
</head>
<body>

  <!--头部 start-->
 <div class="topFix">
     <jsp:include page="../common/header.jsp"></jsp:include>
  </div>  
  <!--头部 end-->

  <!--内容区域 start-->
  <section class="detail_block">
    <div class="no_result">
      <img src="<%=path%>/images/e.gif" lazy="<%=path%>/images/order/pic_order.png" width="128" height="93">
      <span>您的购物车什么都没有哦！</span>
      <a href="<%=path%>/spindex!index">再去逛逛</a>
    </div>
  </section>
  <!--内容区域 end-->

</body>
</html>