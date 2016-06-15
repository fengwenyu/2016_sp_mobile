<%@ page contentType="text/html;charset=UTF-8"%>
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

<title>尚品</title>
<link href="<%=path%>/css/base.css" rel="stylesheet" />
<link href="<%=path%>/css/page/2014520/index.css" rel="stylesheet" />

<script src="<%=path%>/js/core.js" type="text/javascript" charset="utf-8"></script>
<script type="text/javascript" charset="utf-8">
	var ver = "?20121023001";
		loader = SP.core
			.install("<%=path%>/js/jquery.min.js" + ver)
			.using("<%=path%>/js/j.lazyload.js" + ver)
			.excute()
			.using("<%=path%>/js/config.sim.js" + ver)
			.excute()
      		.using("<%=path%>/js/2014520/list.js" + ver)
			.excute(function(){
				isHome(false);
			});
</script>
</head>

<body>
  
<div class="topFix">
	<nav id="filter_box">
	  <ul class="header_nav">
	    <li><a href="<%=path%>/activeaction!meetindex?z=0&id=26">尚品周年庆</a></li>
		  <li><a href="<%=path%>/activeaction!meetpage?z=0&type=shoe&p=0&id=28">鞋靴会场</a></li>
		  <li><a href="<%=path%>/activeaction!meetpage?z=0&type=bags&p=1&id=28">箱包会场</a></li>
	      <li><a href="<%=path%>/activeaction!meetpage?z=0&type=women&p=2&id=28">女装会场</a></li>
	      <li><a href="<%=path%>/activeaction!meetpage?z=0&type=men&p=3&id=28">男装会场</a></li>
	      <li><a href="<%=path%>/activeaction!meetpage?z=0&type=sport&p=4&id=28">运动会场</a></li>
	      <li><a href="<%=path%>/activeaction!meetpage?z=0&type=ornament&p=5&id=28">配饰会场</a></li>
	      <li><a href="<%=path%>/activeaction!meetpage?z=0&type=life&p=6&id=28">美妆家居会场</a></li>
	      <li class="hover"><a href="<%=path%>/activeaction!meetpage?z=0&type=aolai&p=7&id=28">尚品奥莱</a></li>
	  </ul>
	</nav>
</div>
${html }

 <jsp:include page="../../common/footer.jsp"></jsp:include>
</body>
</html>
