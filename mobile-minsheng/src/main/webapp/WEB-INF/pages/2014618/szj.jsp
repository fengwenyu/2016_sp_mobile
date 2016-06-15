<%@ page contentType="text/html;charset=UTF-8"%>
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
<body>
   <div class="topFix">
    <section>
      <nav id="filter_box">
        <ul class="header_nav">
           <li ><a href="<%=path%>/activeaction!meetpage?id=38&type=index&p=0">首页</a></li>
          <li class="hover"><a href="<%=path%>/activeaction!meetpage?id=38&type=szj&p=1">时装精</a></li>
          <li><a href="<%=path%>/activeaction!meetpage?id=38&type=xnz&&p=2">型男志</a></li>
          <li><a href="<%=path%>/activeaction!meetpage?id=38&type=sxh&&p=3">奢享汇</a></li>
          <li><a href="<%=path%>/activeaction!meetpage?id=38&type=cxg&p=4">潮鞋柜</a></li>
          <li><a href="<%=path%>/activeaction!meetpage?id=38&type=ydp&p=5">悦动派</a></li>
          <li><a href="<%=path%>/activeaction!meetpage?id=38&type=djp&p=6">点睛配</a></li>
          <li><a href="<%=path%>/activeaction!meetpage?id=38&type=xsh&p=7">享生活</a></li>
        </ul>
      </nav>
  </section>
  </div>
  <div class="sp520_list" style="margin-top:0;">
    ${html }
  </div>
 <jsp:include page="../common/footer.jsp"></jsp:include>
</body>
</html>
