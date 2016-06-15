<%@ page language="java" import="java.util.*,com.shangpin.mobileAolai.common.alipay.util.Base64" pageEncoding="utf-8"%>
<%@ page contentType="text/html;charset=UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="s" uri="/struts-tags"%>

<%String path = request.getContextPath();
String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + path + "/";
%>
<%@ include file="/WEB-INF/pages/common/hm.jsp" %>
<%@ include file="/WEB-INF/pages/common/message.jsp" %>
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

<title>奥莱-触摸屏版</title>
<link href="<%=path%>/css/base.css" rel="stylesheet" />
<link href="<%=path%>/css/page/201408lottery/index.css" rel="stylesheet" />

<!-- icon -->
<link rel="apple-touch-icon" href="<%=path%>/images/touch-icon-iphone.png">
<link rel="apple-touch-icon" sizes="72x72" href="<%=path%>/images/touch-icon-ipad.png">
<link rel="apple-touch-icon" sizes="114x114" href="<%=path%>/images/touch-icon-iphone4.png">
<link rel="apple-touch-icon" sizes="144x144" href="<%=path%>/images/touch-icon-newipad.png">
<!-- 启动自定义图片(png, 320X640) -->
<link rel="apple-touch-startup-image" sizes="72x72" href="<%=path%>/images/logo/loading.png">

<script src="<%=path%>/js/core.js" type="text/javascript" charset="utf-8"></script>
<script type="text/javascript" charset="utf-8">
	var ver = "?20121023001";
		loader = SP.core
			.install("<%=path%>/js/jquery.min.js" + ver)
			.using("<%=path%>/js/j.lazyload.js" + ver)
			.using("<%=path%>/js/jquery.easing.min.js" + ver)
			.using("<%=path%>/js/jQueryRotate.2.2.js" + ver)
			.using("<%=path%>/js/xmas.dialogs.js" + ver)
			.excute()
			.using("<%=path%>/js/config.sim.js" + ver)
			.excute()
			.using("<%=path%>/js/lottery/lottery.js" + ver)
			.excute(function(){
				isHome(false);
			});
</script>
</head>

<body id="bodyId">
<div class="alContent">
	<div class="turntable about">
    	<p class="about_txt"><span>8月11日</span>活动结束后我们的客服会与您联系确认奖品邮寄地址，请您保持手机畅通。<br /><br />
            如有疑问，请联系我们的客服4006-900-900<br />
            www.aolai.com</p>
        <img id="turntable_bg" src="<%=path%>/images/e.gif" lazy="<%=path%>/images/201408lottery/about_bg.jpg" width="320" >
        <a href="#" class="rule_link">&nbsp;</a>
    </div>
    <div class="con">
    	<img id="" src="<%=path%>/images/e.gif" lazy="<%=path%>/images/201408lottery/prize.jpg" width="270" height="175" class="prize">
        <img id="" src="<%=path%>/images/e.gif" lazy="<%=path%>/images/201408lottery/about_bg1.jpg" width="320" height="415">
        <a href="#" class="share_btn">&nbsp;</a>
    </div>
</div>

<div id="popLayer" style="top:-743px;">
    <ul>
    	<h2><img src="<%=path%>/images/201408lottery/title01.png" width="200" height="20"></h2>
        <li>每个账号每天抽奖4次；</li>
        <li>通过抽奖方式集齐4张奖品图片，即可免费获得该奖品；</li>
        <li>关注尚品奥莱官方微信：spaolai，将此活动成功分享至好友或朋友圈后，截图至官方微信即可获赠&yen;10元现金券；</li>
        <li>本活动最终解释权归尚品奥莱所有。</li>
        <a href="#" id="pop_btn">&nbsp;</a>
    </ul>
</div>

</body>
</html>
