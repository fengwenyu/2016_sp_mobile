<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib prefix="s" uri="/struts-tags" %>
<%@ taglib uri="/tlds/activityName" prefix="spantag"%> 
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

<title>尚品网触屏版_领先的高端时尚和奢侈品购物网站</title>
<link href="<%=path %>/css/base.css" rel="stylesheet" />
<link href="<%=path %>/css/page/order.css" rel="stylesheet" />

<!-- icon -->
<link rel="apple-touch-icon" href="<%=path %>/images/touch-icon-iphone.png">
<link rel="apple-touch-icon" sizes="72x72" href="<%=path %>/images/touch-icon-ipad.png">
<link rel="apple-touch-icon" sizes="114x114" href="<%=path %>/images/touch-icon-iphone4.png">
<link rel="apple-touch-icon" sizes="144x144" href="<%=path %>/images/touch-icon-newipad.png">
<!-- 启动自定义图片(png, 320X640) -->
<link rel="apple-touch-startup-image" sizes="72x72" href="<%=path %>/images/logo/loading.png">
<link rel="shortcut icon" href="http://pic11.shangpin.com/shangpin/images/logo/favicon.ico" />

<script src="<%=path %>/js/core.js" type="text/javascript" charset="utf-8"></script>
<script type="text/javascript" charset="utf-8">
	var ver = "?20121023001";
		loader = SP.core
			.install("<%=path %>/js/zepto.min.js" + ver)
			.using("<%=path %>/js/comm.js" + ver)
			.excute();
</script>

</head>

<body>

<jsp:include page="../common/header.jsp"></jsp:include>

<nav class="alNav">
  <ul>
    <li><a href="<%=path %>/merchandiseaction!splist?ch=${ch}&topicid=${topicid}"><spantag:activityName></spantag:activityName></a></li>
    <li>注册</li>
  </ul>
</nav>

<div class="alContent">
  
  <div class="alOrder_coupon">
  	<p>感谢您注册为尚品网会员，赠送您优惠券2张！<br />消费满￥2000可用200元券！<br />消费满￥3000可用300元券！<br />优惠券只限尚品使用</p>
    <dl>
    	<dd>200元</dd>
        <dd class="box_margin">&nbsp;</dd>
        <dd>300元</dd>
    </dl>
    <a href="<%=path %>/merchandiseaction!splist?ch=${ch}&topicid=${topicid}" class="alOrder_buyBtn">去购物</a>
    <div class="rule">
    	<h2>优惠券使用规则</h2>
        <ol>
        	<li>通过本次活动成功注册为尚品网会员的用户将获得200元和300元购物券各一张，您可在"我的账户"中查看。</li>
			<li>本次活动优惠券有效期：至2013年7月15日截至。</li>
			<li>当您将商品加入购物车，去结算时，可以使用符合条件的优惠券。</li>
			<li>每笔订单只能使用一张优惠券，每张优惠券只能使用一次，且不能用于购买或兑换充值礼品卡。</li>
			<li>某些特殊商品不能使用优惠券。</li>
			<li>优惠券需在有效期内使用，过期则不能再使用。</li>
			<li>优惠券不予兑现，如遇退单情况，退款仅按照实际支付金额结算。</li>
        </ol>
    </div>
  </div>
  
</div>

<jsp:include page="../common/footer.jsp"></jsp:include>

</body>
</html>
