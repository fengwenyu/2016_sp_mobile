<%@ page contentType="text/html;charset=UTF-8"%>
<%@taglib prefix="s" uri="/struts-tags" %>
<%String path = request.getContextPath();%>
<%String ua =request.getHeader("user-agent").toLowerCase();%>
<%String micromessenger = "micromessenger";%>
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

<title>尚品-V码活动专区</title>
<link href="<%=path%>/css/base.css" rel="stylesheet" />
<link href="<%=path%>/css/page/2014520/vcode.css" rel="stylesheet" />

<!-- icon -->
<link rel="apple-touch-icon" href="<%=path%>/images/touch-icon-iphone.png">
<link rel="apple-touch-icon" sizes="72x72" href="<%=path%>/images/touch-icon-ipad.png">
<link rel="apple-touch-icon" sizes="114x114" href="<%=path%>/images/touch-icon-iphone4.png">
<link rel="apple-touch-icon" sizes="144x144" href="<%=path%>/images/touch-icon-newipad.png">
<!-- 启动自定义图片(png, 320X640) -->
<link rel="apple-touch-startup-image" sizes="72x72" href="/images/logo/loading.png">

<script src="<%=path%>/js/core.js" type="text/javascript" charset="utf-8"></script>
<script type="text/javascript" charset="utf-8">
	var ver = "?20121023001";
		loader = SP.core
			.install("<%=path%>/js/jquery.min.js" + ver)
			.using("<%=path%>/js/lazyload.js" + ver)
			.using("<%=path%>/js/j.dialogs.js" + ver)
			.excute()
			.using("<%=path%>/js/config.sim.js" + ver)
			.excute()
			.using("<%=path%>/js/2014520/vcode.js" + ver)
			.excute(function(){
				isHome(false);
			});
</script>
</head>

<body>
<jsp:include page="../common/header.jsp"></jsp:include>
<div class="alContent" style="background-color:#f4f4f4;">
	
    <ul class="coupons_form">
        <li id="verify_box">
        	<span>您现在访问的是尚品V码活动专区，本专区仅对持有V码的用户或尚品高级会员（黄金、白金或钻石会员）开放。</span>
            <input id="code_text" type="text" name="" placeholder="请输入您的V码" /><a id="code_btn" href="javascript:;">开启V码购物</a>
        </li>
        <li>
        	<h2>如何获取V码</h2>
            
            <h3>方式一:微博抢V码</h3>
            <p>关注尚品网或尚品奥莱的官方微博，分享信息包括#尚品520有爱还不购#主题，并@3位好友，官微会将V码以私信的方式发送。（每天限量发放300个）</p>
            
            <h3>方式二:微信抢V码</h3>
            <p>关注尚品网微信公众账号，直接输入“520有爱还不购”索取V码即可。</p>
            
            <h3>方式三:快速会员升级</h3>
            <p>黄金及以上会员自动具备V大牌购买资格哦！</p>
            <p>或现在购买礼品卡即刻升级会员级别吧！</p>
        </li>
	</ul>
</div>
  <div style="width:80%; vertical-align:middle; align:center;margin: 0 auto;"><a href="<%=path%>/520" class="alOrder_buyBtn alOrder_submitBtn" >返回主会场</a></div>
</body>
</html>
