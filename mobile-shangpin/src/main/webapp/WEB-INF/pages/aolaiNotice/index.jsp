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


<%@ page language="java" contentType="text/html; charset=UTF-8" trimDirectiveWhitespaces="true"
    pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/pages/common/include.inc.jsp"%>

<title>奥莱告知页</title>

<link href="${cdn:css(pageContext.request)}/styles/shangpin/css/aolai.css${ver}" rel="stylesheet" />	
</head>

<body>

<div class="down_page">
    <h1>致“尚品奥莱”全体顾客</h1> 
    <h2>尊敬的“尚品奥莱”客户：</h2>
    <p>由于公司业务调整，尚品奥莱将于即日起停止运营。</p>
    <p>尽管尚品奥莱停止运营，但是您之前的订单不会丢失，仍然可以通过www.shangpin.com在“个人中心”查看或者登录尚品网客户端－“我的帐户”进行查看。</p>
    <p>给您带来的不便深表歉意，衷心感谢大家长期以来对尚品奥莱的信赖与支持！ 敬请各位继续关注尚品网及尚品网APP， 我们将一如既往地为您提供优质的商品与周到的服务！</p>
    <footer>
        <div class="down_btn">
            <a href="https://itunes.apple.com/cn/app/id598127498?mt=8"><img src="${cdn:pic(pageContext.request)}/styles/shangpin/images/ios_down.jpg" width="100%" /></a>
            <a href="http://m.shangpin.com/download.action?p=102&ch=4&fileName=shangpin_4.apk"><img src="${cdn:pic(pageContext.request)}/styles/shangpin/images/android_down.jpg" width="100%" /></a>
        </div>
        <p class="tel_text">服务热线：4006-900-900</p>
    </footer>
</div>
</body>
</html>