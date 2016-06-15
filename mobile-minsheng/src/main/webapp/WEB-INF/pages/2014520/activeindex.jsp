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
<!-- 禁止自动识别5位以上数字为电话 -->
<meta name="format-detection" content="telephone=no">
<title>尚品微信页</title>

<style type="text/css">
  body{
    margin:0;
    padding:0;
  }
  .download_box_new{ 
    width:320px; 
    margin:0 auto 3px; 
    position:relative;
  }
  .download_box_new a{ 
    display:block;  
    width: 115px;
    height: 40px; 
    text-indent: -3000px; 
    overflow:hidden;}
  
  .download_box_new .download_ios{
    line-height: 30px;
    position: absolute;
    top: 405px;
    left: 170px;
  }
  .download_box_new .download_android{
    line-height: 30px;
    position: absolute;
    top: 405px;
    left: 35px;

  }

</style>

</head>

<body>
<div class="download_box_new">
	<img src="<%=path%>/images/2014520.jpg" width="320" height="480" />
    <a class="download_android" href="<%=path %>/activeaction!meetpage?type=big&p=0&id=27&ch=${ch}">参与活动</a>
    <a class="download_ios" href="<%=path%>/spindex!index?gender=0&ch=${ch}">遗憾的错过</a>
   
</div>
<!--以下为测试弹出层所需页面内容 可忽略-->


</body>
</html>
