<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib prefix="s" uri="/struts-tags" %>
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

<title>尚品奥莱</title>
<link href="<%=path%>/css/base.css" rel="stylesheet" />
<link href="<%=path%>/css/page/subject/index.css" rel="stylesheet" />

<script src="<%=path%>/js/core.js" type="text/javascript" charset="utf-8"></script>
<script type="text/javascript" charset="utf-8">
	var ver = "?20121023001";
		loader = SP.core
			.install("<%=path%>/js/jquery.min.js" + ver)
			.using("<%=path%>/js/j.lazyload.js" + ver)
			.excute()
			.using("<%=path%>/js/config.sim.js" + ver)
			.excute(function(){
				isHome(false);
			});
</script>
</head>

<body>
  <jsp:include page="/WEB-INF/pages/common/header.jsp"></jsp:include>
  <div class="sp520_list">
    <p class="top_img"><img src="<%=path%>/images/e.gif" lazy="<%=path%>/images/subject/top_bg.jpg" width="320" height="160" /></p>
  </div>
  
  <div class="sp520_list" style="margin-top:0;">
  	
    <div class="sp520_box">
    	<p style="width:320px; margin:0 auto;"><a href="<%=path%>/merchandiseaction!list?activityId=40627756&pageType=02&typeFlag=1"><img src="<%=path%>/images/e.gif" lazy="<%=path%>/images/subject/seckill.jpg" width="320" height="100" /></a></p>
    </div>
    
    <div class="sp520_box">
      <header class="page_header">
    	<img src="<%=path%>/images/e.gif" lazy="<%=path%>/images/subject/title01.jpg" width="150" height="50" />
      </header>
      
      <ul class="prod_list clr">
    	<li><a href="<%=path%>/merchandiseaction!list?activityId=40625389&pageType=02&typeFlag=1"><img src="<%=path%>/images/e.gif" lazy="<%=path%>/images/subject/bigs_01.jpg" width="150" height="220" /></a></li>
        <li><a href="<%=path%>/merchandiseaction!list?activityId=40626570&pageType=02&typeFlag=1"><img src="<%=path%>/images/e.gif" lazy="<%=path%>/images/subject/bigs_02.jpg" width="150" height="220" /></a></li>
        <li><a href="<%=path%>/merchandiseaction!list?activityId=40626573&pageType=02&typeFlag=1"><img src="<%=path%>/images/e.gif" lazy="<%=path%>/images/subject/bigs_03.jpg" width="150" height="220" /></a></li>
        <li><a href="<%=path%>/merchandiseaction!list?activityId=40626575&pageType=02&typeFlag=1"><img src="<%=path%>/images/e.gif" lazy="<%=path%>/images/subject/bigs_04.jpg" width="150" height="220" /></a></li>
      	<li><a href="<%=path%>/merchandiseaction!list?activityId=40625391&pageType=02&typeFlag=1"><img src="<%=path%>/images/e.gif" lazy="<%=path%>/images/subject/bigs_05.jpg" width="150" height="220" /></a></li>
        <li><a href="<%=path%>/merchandiseaction!list?activityId=40630034&pageType=02&typeFlag=1"><img src="<%=path%>/images/e.gif" lazy="<%=path%>/images/subject/bigs_06.jpg" width="150" height="220" /></a></li>
        <li><a href="<%=path%>/merchandiseaction!list?activityId=40630036&pageType=02&typeFlag=1"><img src="<%=path%>/images/e.gif" lazy="<%=path%>/images/subject/bigs_07.jpg" width="150" height="220" /></a></li>
        <li><a href="<%=path%>/merchandiseaction!list?activityId=40630069&pageType=02&typeFlag=1"><img src="<%=path%>/images/e.gif" lazy="<%=path%>/images/subject/bigs_08.jpg" width="150" height="220" /></a></li>
        <li><a href="<%=path%>/merchandiseaction!list?activityId=40626576&pageType=02&typeFlag=1"><img src="<%=path%>/images/e.gif" lazy="<%=path%>/images/subject/bigs_09.jpg" width="150" height="220" /></a></li>
        <li><a href="<%=path%>/merchandiseaction!list?activityId=40626578&pageType=02&typeFlag=1"><img src="<%=path%>/images/e.gif" lazy="<%=path%>/images/subject/bigs_10.jpg" width="150" height="220" /></a></li>
        <li><a href="<%=path%>/merchandiseaction!list?activityId=40626579&pageType=02&typeFlag=1"><img src="<%=path%>/images/e.gif" lazy="<%=path%>/images/subject/bigs_11.jpg" width="150" height="220" /></a></li>
        <li><a href="<%=path%>/merchandiseaction!list?activityId=40626580&pageType=02&typeFlag=1"><img src="<%=path%>/images/e.gif" lazy="<%=path%>/images/subject/bigs_12.jpg" width="150" height="220" /></a></li>
        <li><a href="<%=path%>/merchandiseaction!list?activityId=40626581&pageType=02&typeFlag=1"><img src="<%=path%>/images/e.gif" lazy="<%=path%>/images/subject/bigs_13.jpg" width="150" height="220" /></a></li>
        <li><a href="<%=path%>/merchandiseaction!list?activityId=40630049&pageType=02&typeFlag=1"><img src="<%=path%>/images/e.gif" lazy="<%=path%>/images/subject/bigs_14.jpg" width="150" height="220" /></a></li>
        <li><a href="<%=path%>/merchandiseaction!list?activityId=40626582&pageType=02&typeFlag=1"><img src="<%=path%>/images/e.gif" lazy="<%=path%>/images/subject/bigs_15.jpg" width="150" height="220" /></a></li>
        <li><a href="<%=path%>/merchandiseaction!list?activityId=40626584&pageType=02&typeFlag=1"><img src="<%=path%>/images/e.gif" lazy="<%=path%>/images/subject/bigs_16.jpg" width="150" height="220" /></a></li>

      </ul>
      
    </div>
    
    <div class="sp520_box">
      <header class="page_header">
    	<img src="<%=path%>/images/e.gif" lazy="<%=path%>/images/subject/title02.jpg" width="150" height="50" />
      </header>
      
      <ul class="prod_list clr">
    	<li><a href="<%=path%>/merchandiseaction!list?activityId=40630039&pageType=02&typeFlag=1"><img src="<%=path%>/images/e.gif" lazy="<%=path%>/images/subject/brand_01.jpg" width="150" height="220" /></a></li>
        <li><a href="<%=path%>/merchandiseaction!list?activityId=40626586&pageType=02&typeFlag=1"><img src="<%=path%>/images/e.gif" lazy="<%=path%>/images/subject/brand_02.jpg" width="150" height="220" /></a></li>
        <li><a href="<%=path%>/merchandiseaction!list?activityId=40626588&pageType=02&typeFlag=1"><img src="<%=path%>/images/e.gif" lazy="<%=path%>/images/subject/brand_03.jpg" width="150" height="220" /></a></li>
        <li><a href="<%=path%>/merchandiseaction!list?activityId=40626591&pageType=02&typeFlag=1"><img src="<%=path%>/images/e.gif" lazy="<%=path%>/images/subject/brand_04.jpg" width="150" height="220" /></a></li>
    	<li><a href="<%=path%>/merchandiseaction!list?activityId=40630040&pageType=02&typeFlag=1"><img src="<%=path%>/images/e.gif" lazy="<%=path%>/images/subject/brand_05.jpg" width="150" height="220" /></a></li>
        <li><a href="<%=path%>/merchandiseaction!list?activityId=40626600&pageType=02&typeFlag=1"><img src="<%=path%>/images/e.gif" lazy="<%=path%>/images/subject/brand_06.jpg" width="150" height="220" /></a></li>
        <li><a href="<%=path%>/merchandiseaction!list?activityId=40630042&pageType=02&typeFlag=1"><img src="<%=path%>/images/e.gif" lazy="<%=path%>/images/subject/brand_07.jpg" width="150" height="220" /></a></li>
        <li><a href="<%=path%>/merchandiseaction!list?activityId=40630052&pageType=02&typeFlag=1"><img src="<%=path%>/images/e.gif" lazy="<%=path%>/images/subject/brand_08.jpg" width="150" height="220" /></a></li>
        <li><a href="<%=path%>/merchandiseaction!list?activityId=40630071&pageType=02&typeFlag=1"><img src="<%=path%>/images/e.gif" lazy="<%=path%>/images/subject/brand_09.jpg" width="150" height="220" /></a></li>
        <li><a href="<%=path%>/merchandiseaction!list?activityId=40630054&pageType=02&typeFlag=1"><img src="<%=path%>/images/e.gif" lazy="<%=path%>/images/subject/brand_10.jpg" width="150" height="220" /></a></li>
        <li><a href="<%=path%>/merchandiseaction!list?activityId=40630044&pageType=02&typeFlag=1"><img src="<%=path%>/images/e.gif" lazy="<%=path%>/images/subject/brand_11.jpg" width="150" height="220" /></a></li>
        <li><a href="<%=path%>/merchandiseaction!list?activityId=40630046&pageType=02&typeFlag=1"><img src="<%=path%>/images/e.gif" lazy="<%=path%>/images/subject/brand_12.jpg" width="150" height="220" /></a></li>
        <li><a href="<%=path%>/merchandiseaction!list?activityId=40626593&pageType=02&typeFlag=1"><img src="<%=path%>/images/e.gif" lazy="<%=path%>/images/subject/brand_13.jpg" width="150" height="220" /></a></li>
        <li><a href="<%=path%>/merchandiseaction!list?activityId=40626595&pageType=02&typeFlag=1"><img src="<%=path%>/images/e.gif" lazy="<%=path%>/images/subject/brand_14.jpg" width="150" height="220" /></a></li>
        <li><a href="<%=path%>/merchandiseaction!list?activityId=40630055&pageType=02&typeFlag=1"><img src="<%=path%>/images/e.gif" lazy="<%=path%>/images/subject/brand_15.jpg" width="150" height="220" /></a></li>
        <li><a href="<%=path%>/merchandiseaction!list?activityId=40630057&pageType=02&typeFlag=1"><img src="<%=path%>/images/e.gif" lazy="<%=path%>/images/subject/brand_16.jpg" width="150" height="220" /></a></li> 
      </ul>
      
    </div>
    
    <div class="sp520_box">
      <header class="page_header">
    	<img src="/images/e.gif" lazy="<%=path%>/images/subject/title03.jpg" width="150" height="50" />
      </header>
      
      <ul class="prod_list clr">
    	<li><a href="<%=path%>/merchandiseaction!list?activityId=40625428&pageType=02&typeFlag=1"><img src="<%=path%>/images/e.gif" lazy="<%=path%>/images/subject/active_01.jpg" width="150" height="220" /></a></li>
        <li><a href="<%=path%>/merchandiseaction!list?activityId=40625434&pageType=02&typeFlag=1"><img src="<%=path%>/images/e.gif" lazy="<%=path%>/images/subject/active_02.jpg" width="150" height="220" /></a></li>
        <li><a href="<%=path%>/merchandiseaction!list?activityId=40625435&pageType=02&typeFlag=1"><img src="<%=path%>/images/e.gif" lazy="<%=path%>/images/subject/active_03.jpg" width="150" height="220" /></a></li>
        <li><a href="<%=path%>/merchandiseaction!list?activityId=40625436&pageType=02&typeFlag=1"><img src="<%=path%>/images/e.gif" lazy="<%=path%>/images/subject/active_04.jpg" width="150" height="220" /></a></li>
        <li><a href="<%=path%>/merchandiseaction!list?activityId=40630059&pageType=02&typeFlag=1"><img src="<%=path%>/images/e.gif" lazy="<%=path%>/images/subject/active_05.jpg" width="150" height="220" /></a></li>
        <li><a href="<%=path%>/merchandiseaction!list?activityId=40630061&pageType=02&typeFlag=1"><img src="<%=path%>/images/e.gif" lazy="<%=path%>/images/subject/active_06.jpg" width="150" height="220" /></a></li>
        <li><a href="<%=path%>/merchandiseaction!list?activityId=40630062&pageType=02&typeFlag=1"><img src="<%=path%>/images/e.gif" lazy="<%=path%>/images/subject/active_07.jpg" width="150" height="220" /></a></li>
        <li><a href="<%=path%>/merchandiseaction!list?activityId=40630064&pageType=02&typeFlag=1"><img src="<%=path%>/images/e.gif" lazy="<%=path%>/images/subject/active_08.jpg" width="150" height="220" /></a></li>
        <li><a href="<%=path%>/merchandiseaction!list?activityId=40630066&pageType=02&typeFlag=1"><img src="<%=path%>/images/e.gif" lazy="<%=path%>/images/subject/active_09.jpg" width="150" height="220" /></a></li>
        <li><a href="<%=path%>/merchandiseaction!list?activityId=40630067&pageType=02&typeFlag=1"><img src="<%=path%>/images/e.gif" lazy="<%=path%>/images/subject/active_10.jpg" width="150" height="220" /></a></li>
        <li><a href="<%=path%>/merchandiseaction!list?activityId=40630058&pageType=02&typeFlag=1"><img src="<%=path%>/images/e.gif" lazy="<%=path%>/images/subject/active_11.jpg" width="150" height="220" /></a></li>
        <li><a href="<%=path%>/merchandiseaction!list?activityId=40626601&pageType=02&typeFlag=1"><img src="<%=path%>/images/e.gif" lazy="<%=path%>/images/subject/active_12.jpg" width="150" height="220" /></a></li>
        <li><a href="<%=path%>/merchandiseaction!list?activityId=40626603&pageType=02&typeFlag=1"><img src="<%=path%>/images/e.gif" lazy="<%=path%>/images/subject/active_13.jpg" width="150" height="220" /></a></li>
        <li><a href="<%=path%>/merchandiseaction!list?activityId=40626604&pageType=02&typeFlag=1"><img src="<%=path%>/images/e.gif" lazy="<%=path%>/images/subject/active_14.jpg" width="150" height="220" /></a></li>
        <li><a href="<%=path%>/merchandiseaction!list?activityId=40626606&pageType=02&typeFlag=1"><img src="<%=path%>/images/e.gif" lazy="<%=path%>/images/subject/active_15.jpg" width="150" height="220" /></a></li>
        <li><a href="<%=path%>/merchandiseaction!list?activityId=40626607&pageType=02&typeFlag=1"><img src="<%=path%>/images/e.gif" lazy="<%=path%>/images/subject/active_16.jpg" width="150" height="220" /></a></li>
      </ul>
      <dt><s:property value="#session['user'].name"/></dt>
            <dd><s:property value="#session['user'].level"/></dd>
    </div>
    
  </div>
  
   
</body>
</html>
