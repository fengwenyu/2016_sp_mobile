<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/pages/common/include.inc.jsp"%>

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
<title>尚品网_高端时尚购物平台_官网授权|海量新品|全场2折起</title>
<style type="text/css">
  body{
    margin:0;
    padding:0;
	background-color:#fff;
  }
  .error_box_new{ 
    min-width:320px;
	max-width:640px;
	margin:0 auto;
    position:relative;
  }
  .error_box_new img{
	  display:block;
	  width:100%;
  }
  

</style>

</head>
<body>
<div class="error_box_new">
	<img src="${cdn:pic(pageContext.request)}/styles/shangpin/images/404error.jpg" />
</div>
</body>
</html>