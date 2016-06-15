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
<title>尚品网CEO红包,快抢！</title>
<link href="${cdn:css(pageContext.request)}/styles/shangpin/css/star-packet/base.css" rel="stylesheet" />
<link href="${cdn:css(pageContext.request)}/styles/shangpin/css/star-packet/20151020/ceo_packet.css${ver}" rel="stylesheet" />
</head>
<body>
<div class="wapper1">
 
  <!--内容区域 start-->
  <section class="main">
  	<div class="error-con">
    	<a href="${ctx }/star/index?star=ceo" style="display:block;"><i><img src="${cdn:pic(pageContext.request)}/styles/shangpin/images/star-packet/20151020/error_img.png" ></i>
        <span style="display:block; text-align:center; color:#333; line-height:30px; padding-bottom:10px;">点我再试一次</span>
        <p style="color:#333; text-align:center;">抢红包的人太多啦，让俺休息一会再发行不</p>
        </a>
    </div>
  </section>
  <!--内容区域 start-->
    
</div>
<script src="${cdn:js(pageContext.request)}/styles/shangpin/js/jquery.min.js" type="text/javascript" charset="utf-8"></script>
<script src="${cdn:js(pageContext.request)}/styles/shangpin/js/star-packet/red_packet.js?1"></script>
</body>
</html>