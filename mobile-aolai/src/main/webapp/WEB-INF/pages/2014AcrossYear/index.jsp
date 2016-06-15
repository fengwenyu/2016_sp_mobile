<%@page import="com.shangpin.mobileAolai.common.util.ClientUtil"%>
<%@ page language="java" import="java.util.*,com.shangpin.mobileAolai.common.alipay.util.Base64" pageEncoding="utf-8"%>
<%@taglib prefix="s" uri="/struts-tags" %>
<%@ taglib prefix="cdn" uri="http://m.shangpin.com/cdn" %>
<%String path = request.getContextPath();%>
<%String ua =request.getHeader("user-agent").toLowerCase();%>

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
<!-- 禁止自动识别5位以上数字为电话 -->
<meta name="format-detection" content="telephone=no">

<title>${title }</title>
<link href="<%=path%>/css/base.css?20141224" rel="stylesheet" />
<link href="<%=path%>/css/page/2014AcrossYear.css?20141224" rel="stylesheet" />

<script src="<%=path%>/js/core.js?20141119" type="text/javascript" charset="utf-8"></script>

<script type="text/javascript" charset="utf-8">

    loader = SP.core
    	.install("<%=path%>/js/jquery.min.js?20141119")
		.using("<%=path%>/js/j.lazyload.js?20141211")
		.excute()
		.using("<%=path%>/js/config.sim.js?20141119")
  		.excute()
  		.using("<%=path%>/js/2014AcrossYear/2014AcrossYear.js?20141221");
</script>
</head>
<body>

  <jsp:include page="../common/header.jsp"></jsp:include>
  ${html }
</body>
</html>