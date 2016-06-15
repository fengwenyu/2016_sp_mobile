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

<title>礼券包</title>
<link href="${cdn:css(pageContext.request)}/css/base.css" rel="stylesheet" />
<link href="${cdn:css(pageContext.request)}/css/page/20150520/20150520GiftPackage.css" rel="stylesheet" />

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
          
			.using("${cdn:css(pageContext.request)}/js/jquery.easing.min.js" + ver)
			.using("${cdn:css(pageContext.request)}/js/xmas.dialogs.js" + ver)
			.excute()
			.using("${cdn:css(pageContext.request)}/js/config.sim.js" + ver)
			.excute()
			//.using("${cdn:css(pageContext.request)}/js/20150520/giftPackage.js" + ver)
			.excute(function(){
				isHome(false);
			});
</script>
</head>

<body id="bodyId">

<jsp:include page="../common/header.jsp"></jsp:include>
 <%if(ClientUtil.CheckMircro(ua)||ClientUtil.CheckApp(ua)){%>
   <div class="alContent" style="margin-top:0;">
   <% }else{%>
   <div class="alContent">
 <% }%>
	<h1 class="title-top">
    	<i class="try-tag"><img style="max-width: 100%;" src="${cdn:css(pageContext.request)}/images/20150520preheat/20150520GiftPackage/try_tag.png" /></i>
    	<img style="max-width: 100%;" src="${cdn:css(pageContext.request)}/images/20150520preheat/20150520GiftPackage/top_title.png">
    </h1>
    <div class="turntable-top"><img style="max-width: 100%;" src="${cdn:css(pageContext.request)}/images/20150520preheat/20150520GiftPackage/turntable_bg_top.png" /></div>
	<div class="turntable">
    	<p>
        	<strong>1</strong>即日起至5月25日，免费领取￥520礼券包； 
		</p>
        <p>
        	<strong>2</strong>获得的礼券包将自动充入您的个人中心，您可点击“我的优惠券”查看；
        </p>
        <p>
        	<strong>3</strong>礼券包中含五张优惠券，其中包括壹张200元，贰张100元及贰张60元优惠券；
        </p>
        <p>
          <strong>4</strong>礼券包限<span class="red">5月20日至5月25日</span>使用，<span class="red">单笔订单满520元减60元，满1000元减100元，满2000元减200元，</span>每张订单限用一张优惠券，本券全场通用（海外购等特殊商品除外）；
        </p>
        <p>
          <strong>5</strong>优惠券和优惠码限选<br>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;其一，优惠券的使用<br>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;规则详见<span class="red">“帮助中<br>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;心-优惠券使<br>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;用”</span>。
        </p>
        
        <a href="<%=path%>/accountaction!getSpecialCoupon?couponcode=9634581364&loginFrom=8" class="receive-btn"><img style="max-width: 100%;" src="${cdn:css(pageContext.request)}/images/20150520preheat/20150520GiftPackage/receive_btn.png" /></a>
        <em class="gift-bg"><img style="max-width: 100%;" src="${cdn:css(pageContext.request)}/images/20150520preheat/20150520GiftPackage/gift_bg.png" /></em>
    </div>
    <div class="con">
        <a href="<%=path%>/meet!index?id=228" class="back_main"><i><img style="max-width: 100%;" src="${cdn:css(pageContext.request)}/images/20150520preheat/20150520GiftPackage/back_icon.png" style="height:13px;width:14px"/></i>返回主会场</a>
    </div>
</div>

</body>
</html>
