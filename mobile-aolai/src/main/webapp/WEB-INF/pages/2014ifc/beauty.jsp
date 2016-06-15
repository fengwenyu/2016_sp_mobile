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
<title>2014IFC 会场</title>
<link href="${cdn:css(pageContext.request)}/css/base.css" rel="stylesheet" />
<link href="${cdn:css(pageContext.request)}/css/page/2014ifc/index.css?ver=2014110317" rel="stylesheet" />
<!-- icon -->
<link rel="apple-touch-icon" href="${cdn:pic(pageContext.request)}/images/touch-icon-iphone.png">
<link rel="apple-touch-icon" sizes="72x72" href="${cdn:pic(pageContext.request)}/images/touch-icon-ipad.png">
<link rel="apple-touch-icon" sizes="114x114" href="${cdn:pic(pageContext.request)}/images/touch-icon-iphone4.png">
<link rel="apple-touch-icon" sizes="144x144" href="${cdn:pic(pageContext.request)}/images/touch-icon-newipad.png">
<!-- 启动自定义图片(png, 320X640) -->
<link rel="apple-touch-startup-image" sizes="72x72" href="${cdn:pic(pageContext.request)}/images/logo/loading.png">
<%@ include file="/WEB-INF/pages/common/message.jsp" %>
<script src="${cdn:js(pageContext.request)}/js/core.js" type="text/javascript" charset="utf-8"></script>

<script type="text/javascript" charset="utf-8">
  var ver = "?20121023001";
    loader = SP.core
      .install("${cdn:js(pageContext.request)}/js/jquery.min.js" + ver)
      .using("${cdn:js(pageContext.request)}/js/j.lazyload.js" + ver)
      .excute()
      .using("${cdn:js(pageContext.request)}/js/config.sim.js" + ver)

      .using("${cdn:js(pageContext.request)}/js/j.MXTimer.js?" + ver)
      .using("${cdn:js(pageContext.request)}/js/iscroll.js?" + ver)
      .using("${cdn:js(pageContext.request)}/js/slider.js?" + ver)
      .using("${cdn:js(pageContext.request)}/js/j.dialogs.js?" + ver)
      
      .excute()
      .using("${cdn:js(pageContext.request)}/js/2014ifc/index.js" + ver);
</script>
<script>
var mainTitle="尚品网.TOPSHOP",
	mainDesc="尚品网.TOPSHOP",
	mainURL=location.href,
	mainImgUrl= "";
</script>
</head>
<body>
<jsp:include page="../common/header.jsp"></jsp:include>
  <!--内容区域 start-->
    <%if(ClientUtil.CheckMircro(ua)||ClientUtil.CheckApp(ua)){%>
   <div class="alContent" style="margin-top:0;">
   <% }else{%>
   <div class="alContent">
 <% }%>
  ${html }
  <div class="return_ifc"> 
    <a href="<%=path %>/ifc/index"><b>&lt;</b>&nbsp;返回主会场</a> 
   </div> 
  </div>
  <!--弹层 end-->
<input type="hidden" id="type" value="${param.type }">
<jsp:include page="../common/footer.jsp"></jsp:include> 
</body>
</html>