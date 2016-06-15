<%@ page contentType="text/html;charset=UTF-8"%>
<%@taglib prefix="s" uri="/struts-tags" %>
<%@ taglib prefix="cdn" uri="http://m.shangpin.com/cdn" %>

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

<title>奥莱</title>
<link href="${cdn:css(pageContext.request)}/css/base.css" rel="stylesheet" />
<style type="text/css">
.sp520_list{width:100%; background-color:#47d2e5;}
.sp520_list p.top_img{width:320px; height:220px; margin: 0 auto; overflow:hidden;}

.sp520_box{width:100%; overflow:hidden;}
.sp520_list ul{width:305px;	margin:0 auto;}
.sp520_list li{width:150px; height:220px; margin:0 5px 5px 0; float:left;}
.sp520_list li:nth-child(2n){margin-right:0;}
.sp520_list li a, .sp520_list li img{display: block;}

/*国际大牌样式*/
.sp520_list ul.big_list{width:300px;}
.big_list li{width:300px; height:147px; margin:0; padding-bottom:10px;}

</style>

<script src="<%=path%>/js/core.js" type="text/javascript" charset="utf-8"></script>
<script type="text/javascript" charset="utf-8">
	var ver = "?20121023001";
		loader = SP.core
			.install("<%=path%>/js/jquery.min.js" + ver)
			.using("<%=path%>/js/j.lazyload.js" + ver)
			.excute()
			.using("<%=path%>/js/config.sim.js" + ver)
			.excute()
			.excute(function(){
				isHome(false);
			});
</script>
</head>

<body>
  
  ${html }
  
</body>
</html>
