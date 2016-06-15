<%@ page import="java.util.*,com.shangpin.mobileAolai.common.util.ClientUtil" pageEncoding="utf-8"%>
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
<title>奥莱分会场</title>
<link href="${cdn:css(pageContext.request)}/css/base.css" rel="stylesheet" />
<link href="${cdn:css(pageContext.request)}/css/page/20150520/20150520preheat.css" rel="stylesheet" />
<!-- icon -->
<link rel="apple-touch-icon" href="${cdn:css(pageContext.request)}/images/touch-icon-iphone.png">
<link rel="apple-touch-icon" sizes="72x72" href="${cdn:css(pageContext.request)}/images/touch-icon-ipad.png">
<link rel="apple-touch-icon" sizes="114x114" href="${cdn:css(pageContext.request)}/images/touch-icon-iphone4.png">
<link rel="apple-touch-icon" sizes="144x144" href="${cdn:css(pageContext.request)}/images/touch-icon-newipad.png">
<!-- 启动自定义图片(png, 320X640) -->
<link rel="apple-touch-startup-image" sizes="72x72" href="${cdn:css(pageContext.request)}/images/logo/loading.png">
<script src="${cdn:css(pageContext.request)}/js/core.js" type="text/javascript" charset="utf-8"></script>

<script type="text/javascript" charset="utf-8">
  var ver = "?20121023001";
    loader = SP.core
      .install("${cdn:css(pageContext.request)}/js/jquery.min.js?" + ver)
      .using("${cdn:css(pageContext.request)}/js/j.lazyload.js?" + ver)
	  .using("${cdn:css(pageContext.request)}/js/j.MXTimer.js?" + ver)
      .excute()
      .using("${cdn:css(pageContext.request)}/js/config.sim.js?" + ver)
      .excute()
      .using("${cdn:css(pageContext.request)}/js/20150520/20150520preheat.js" + ver);
</script>
</head>
<body>
<input type="hidden" id="nowSys" value="${nowSys}"/>
 ${html }
</body>
</html>