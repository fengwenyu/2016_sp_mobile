<%@ page language="java"  import="java.net.URLEncoder" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page import="java.net.URLEncoder"%>
<%@taglib prefix="s" uri="/struts-tags" %>
<%@ taglib uri="/tlds/payData" prefix="p"%> 
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%String path = request.getContextPath();%>
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

<title>尚品奥莱-触摸屏版</title>
<link href="<%=path%>/css/base.css" rel="stylesheet" />
<link href="<%=path%>/css/page/order.css" rel="stylesheet" />

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
			.install("/js/zepto.min.js" + ver)
			.using("/js/comm.js" + ver)
			.excute();
</script>

</head>

<body>

<jsp:include page="../common/header.jsp"></jsp:include>

<nav class="alNav">
  <ul>
    <li><a href="<%=path%>/">首页</a></li>
    <li><a href="<%=path%>/accountaction!info">我的账户</a></li>
    <li><a href="<%=path%>/orderaction!orderlist">订单管理</a></li>
    <li>支付</li>
  </ul>
</nav>

<div class="alContent">
  
  <div class="alOrder_success">
    <h2><img src="<%=path%>/images/success.png" width="36" height="36">您的订单支付成功</h2>
     <a href="<%=path%>/orderaction!orderlist?statusType=1"  class="alOrder_buyBtn">查看订单</a>
     <a href="<%=path%>/aolaiindex!index"  class="alOrder_buyBtn">返回首页</a>
  </div>
  
  <ul class="alOrder_done">
    <li>订 单 号：${orderid}</li>
    <!-- 
    <s:if test="amount!=null&&amount!=''">
    <li>商品金额：<em>&yen;${amount}</em></li>
    </s:if>
     -->
    <li>配送方式：快递发送（免运费）</li>
  </ul>
  
</div>

<jsp:include page="../common/footer.jsp"></jsp:include>

</body>
</html>
