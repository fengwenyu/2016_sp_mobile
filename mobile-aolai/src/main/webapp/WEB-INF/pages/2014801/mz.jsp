<%@ page contentType="text/html;charset=UTF-8"%>
<%@taglib prefix="s" uri="/struts-tags" %>
<%String path = request.getContextPath(); %>
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
<link href="<%=path%>/css/page/2014801/index.css" rel="stylesheet" />

<script src="<%=path%>/js/core.js" type="text/javascript" charset="utf-8"></script>
<script type="text/javascript" charset="utf-8">
	var ver = "?20121023001";
		loader = SP.core
			.install("<%=path%>/js/jquery.min.js" + ver)
			.using("<%=path%>/js/j.lazyload.js" + ver)
			.excute()
			.using("<%=path%>/js/config.sim.js" + ver)
			.excute()
      		.using("<%=path%>/js/2014801/list.js" + ver)
			.excute(function(){
				isHome(false);
			});
</script>
</head>

<body>
  
<div class="topFix">
    <section>
      <nav id="filter_box">
        <ul class="header_nav">
           <li><a href="<%=path%>/activeaction!meetindex?id=53">首页</a></li>
          <li><a href="<%=path%>/activeaction!meetindex?id=54&type=man">男装</a></li>
          <li><a href="<%=path%>/activeaction!meetindex?id=55&type=women">女装</a></li>
          <li><a href="<%=path%>/activeaction!meetindex?id=56&type=shoe">鞋靴</a></li>
          <li><a href="<%=path%>/activeaction!meetindex?id=57&type=bag">箱包</a></li>
          <li><a href="<%=path%>/activeaction!meetindex?id=58&type=acc">配饰</a></li>
          <li><a href="<%=path%>/activeaction!meetindex?id=59&type=watch">腕表</a></li>
          <li><a href="<%=path%>/activeaction!meetindex?id=60&type=yj">眼镜</a></li>
       <li  class="hover"><a href="<%=path%>/activeaction!meetindex?id=61&type=mz">美妆</a></li>
          <li><a href="<%=path%>/activeaction!meetindex?id=63&type=home">家居</a></li>
        </ul>
      </nav>
  </section>
  </div>

  ${html }
    
    <div class="sp_more"><img src="/images/e.gif" lazy="/images/2014801/bottomImg.jpg" width="320" height="47"></div>
    
  </div>
  
</body>
</html>
