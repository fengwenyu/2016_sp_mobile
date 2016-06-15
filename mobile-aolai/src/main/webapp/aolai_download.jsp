<%@ page contentType="text/html;charset=UTF-8" import="java.util.*"%>
<%String path = request.getContextPath();%>
<!doctype html>
<html lang="zh-CN">
<head>
<meta content-type="text/html" charset="utf-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0, user-scalable=no, minimum-scale=1.0, maximum-scale=1.0">

<!-- 开启对web app程序的支持 -->
<meta name="apple-mobile-web-app-capable" content="yes">
<!-- 全屏模式浏览 -->
<meta name="apple-touch-fullscreen" content="yes">
<!-- 改变Safari状态栏的外观 -->
<meta name="apple-mobile-web-app-status-bar-style" content="black">
<!-- 禁止自动识别5位以上数字为电话 -->
<meta name="format-detection" content="telephone=no">
<%@ include file="/WEB-INF/pages/common/message.jsp" %>
<title>奥莱下载页</title>
<link href="<%=path%>/css/base.css" rel="stylesheet" />	

<script src="<%=path%>/js/core.js" type="text/javascript" charset="utf-8"></script>
<script type="text/javascript" charset="utf-8">
	var ver = Math.random();
		loader = SP.core
			.install("<%=path%>/js/zepto.1.1.3.js?" + ver)
			.using("<%=path%>/js/lazyload.js?" + ver)
			.excute()
			.using("<%=path%>/js/config.sim.js?" + ver)
			.excute()
			.using("<%=path%>/js/comm.js?" + ver)
			.using("<%=path%>/js/download.js?" + ver)
</script>
</head>

<body>
<jsp:include page="/WEB-INF/pages/common/header.jsp"></jsp:include>
<style>
.container{ min-width:320px; max-width:640px; margin:0 auto;}
body .alContent{ margin:0;}
.top_banner{ position:relative;}
.top_banner img{ width:100%;}
.down_load_con{ width:100%; max-width:640px; position:fixed; padding:18px 0 0 0;background:rgba(255,255,255,.9); bottom:0; z-index:10;}
.down_load_nav{ padding:0 15px;}
.down_load_nav a{ text-align:center; float:left;  width:48%; display:block; }
.down_load_nav a:last-child{ float:right;}
.down_load_nav .android_bg{ text-align:right;}
.down_load_nav .apple_bg{ text-align:left;}
.phone_line{ font-size:12px; color:#de4747; width:100%; text-align:center; margin:26px 0 26px 0;}
</style>

<div class="container">        
    <!--内容部分-->
    <div class="alContent">      
      <!-- 头图 --> 
      <p class="top_banner"><img src="<%=path%>/images/2015download/index_con_bg.jpg" /></p>
      <p style="height:160px;">&nbsp;</p>
      <!-- download --> 
      <div class="down_load_con">
          <p class="down_load_nav clr">
            <a href="<%=res.getString("shangpinurl")%>download.action?p=101&ch=3&fileName=aolai_3.apk" class="android_bg"><img src="<%=path%>/images/2015download/android_img_btn.png" /></a>
            <a href="https://itunes.apple.com/cn/app/id432489082?mt=8" class="apple_bg"><img src="<%=path%>/images/2015download/ios_img_btn.png" /></a>
          </p>
          <p class="phone_line">服务热线：4006-900-900</p>
      </div>
 	<!--内容部分end-->
    </div>  
</div>
</body>
</html>
