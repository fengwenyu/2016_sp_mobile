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
<!-- 禁止自动识别5位以上数字为电话 -->
<meta name="format-detection" content="telephone=no">

<title>${title }</title>
<link href="${cdn:css(pageContext.request)}/css/base.css" rel="stylesheet" />
<link href="${cdn:css(pageContext.request)}/css/page/20141111.css?20141128" rel="stylesheet" />
<link href="${cdn:css(pageContext.request)}/css/page/2014Double12.css" rel="stylesheet" />
<%@ include file="/WEB-INF/pages/common/message.jsp" %>
<script src="${cdn:js(pageContext.request)}/js/core.js" type="text/javascript" charset="utf-8"></script>

<script type="text/javascript" charset="utf-8">
  var ver = Math.random();
    loader = SP.core
      .install("${cdn:js(pageContext.request)}/js/jquery.min.js?" + ver)
      .using("${cdn:js(pageContext.request)}/js/j.lazyload.js?" + ver)
      .excute()
      .using("${cdn:js(pageContext.request)}/js/config.sim.js?" + ver)
       .using("${cdn:js(pageContext.request)}/js/2014Thanksgiving.js?" + ver);
</script>
</head>
<body>

 <jsp:include page="../common/header.jsp"></jsp:include>
 <%if(ClientUtil.CheckMircro(ua)||ClientUtil.CheckApp(ua)){%>
   <div class="alContent" style="margin-top:0;">
   <% }else{%>
   <div class="alContent">
 <% }%>
   ${html }
  
  </div>
<jsp:include page="../common/footer.jsp"></jsp:include>
</body>
</html>