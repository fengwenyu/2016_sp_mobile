<%@ page contentType="text/html;charset=UTF-8"%>
<%String path = request.getContextPath();%>
<head>
<meta charset="utf-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0, user-scalable=no, minimum-scale=1.0, maximum-scale=1.0">

<!-- 开启对web app程序的支持 -->
<meta name="apple-mobile-web-app-capable" content="yes">
<!-- 全屏模式浏览 -->
<meta name="apple-touch-fullscreen" content="yes">
<!-- 改变Safari状态栏的外观 -->
<meta name="apple-mobile-web-app-status-bar-style" content="black">

<title>尚品</title>
<link href="<%=path%>/css/base.css" rel="stylesheet" />
<link href="<%=path%>/css/page/2014618/index.css" rel="stylesheet" />

<script src="<%=path%>/js/core.js" type="text/javascript" charset="utf-8"></script>
<script type="text/javascript" charset="utf-8">
	var ver = "?20121023001";
		loader = SP.core
			.install("<%=path%>/js/jquery.min.js" + ver)
			.using("<%=path%>/js/j.lazyload.js" + ver)
			.excute()
			.using("<%=path%>/js/config.sim.js" + ver)
			.excute()
			.using("<%=path%>/js/2014618/index.js" + ver)
			.excute(function(){
				isHome(false);
			});
</script>
</head>