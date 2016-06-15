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

<title>分享</title>
<link href="${cdn:css(pageContext.request)}/css/base.css" rel="stylesheet" />
<link href="${cdn:css(pageContext.request)}/css/page/20150520/20150520HappyShare.css" rel="stylesheet" />

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
			.excute()
			.using("${cdn:css(pageContext.request)}/js/config.sim.js" + ver)
			.excute();
</script>
</head>

<body>
<jsp:include page="../common/header.jsp"></jsp:include>
 <%if(ClientUtil.CheckMircro(ua)||ClientUtil.CheckApp(ua)){%>
   <div class="alContent" style="margin-top:0;">
   <% }else{%>
   <div class="alContent">
 <% }%>
	<h1 class="title-top">
    	<img src="${cdn:css(pageContext.request)}/images/20150520preheat/20150520HappyShare/top_title.png">
    </h1>
    <div class="turntable-top"><img src="${cdn:css(pageContext.request)}/images/20150520preheat/20150520HappyShare/turntable_bg_top.png" /></div>
	<div class="turntable">
    	<div class="rule-box">
        	<h2>-&nbsp;活动规则&nbsp;-</h2>
            <div class="rule-text">
            	<p>分享520活动页面到新浪微博，并@ 1位好友</p>
            </div>
        </div>
        <div class="rule-box ">
        	<h2>-&nbsp;活动奖品&nbsp;-</h2>
            <div class="rule-text prize-box">
                <span class="prize-pic"><img src="${cdn:css(pageContext.request)}/images/20150520preheat/20150520HappyShare/img01.jpg" /></span>
            	<p class="prize-text">
                （1）EOS全美热销润唇膏，每天随机抽取5位粉丝送出，共发放60个！<br>
                （2）500元现金券，每日1个，共6500元！
                </p>
            </div>
        </div>
        <div class="rule-box">
        	<h2>-&nbsp;活动时间&nbsp;-</h2>
            <div class="rule-text">
            	<p style="text-align:center;">5月13日－5月25日</p>
            </div>
        </div>
        <div class="rule-box">
        	<h2 class="long">-&nbsp;获奖名单公布&nbsp;-</h2>
            <div class="rule-text">
            	<p>5月15日、18日、20日、23日、25日各公布一次。</p>
            </div>
        </div>
    </div>
    <div class="con">
    	<div class="con-text">
        	<h3>关注尚品网微信额外获得30元现金券和520元礼券包</h3>
            <p>
              <strong>活动规则：</strong><br>
              1.	扫描二维码，关注尚品网官方微信；<br>
              2.	发送“520”给尚品网微信；<br>
              3.	你将得到30元现金券和520元礼券包的激活码，按照提示激活即可！
            </p>
        </div>
        <a href="<%=path%>/meet!index?id=228" class="back_main"><i><img style="height:13px;width:14px" src="${cdn:css(pageContext.request)}/images/20150520preheat/20150520HappyShare/back_icon.png" /></i>返回主会场</a>
    </div>
</div>
</body>
</html>
