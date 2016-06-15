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
<title>尚品网触屏版_领先的高端时尚和奢侈品购物网站</title>
<link href="<%=path%>/css/base.css" rel="stylesheet" />
<link href="<%=path%>/css/page/coupon.css" rel="stylesheet" />

<!-- icon -->
<link rel="apple-touch-icon" href="<%=path%>/images/touch-icon-iphone.png">
<link rel="apple-touch-icon" sizes="72x72" href="<%=path%>/images/touch-icon-ipad.png">
<link rel="apple-touch-icon" sizes="114x114" href="<%=path%>/images/touch-icon-iphone4.png">
<link rel="apple-touch-icon" sizes="144x144" href="<%=path%>/images/touch-icon-newipad.png">
<!-- 启动自定义图片(png, 320X640) -->
<link rel="apple-touch-startup-image" sizes="72x72" href="<%=path%>/images/logo/loading.png">
<script src="/js/core.js" type="text/javascript" charset="utf-8"></script>
<script type="text/javascript" charset="utf-8">
	var ver = "?20121023001";
		loader = SP.core
			.install("<%=path%>/js/zepto.min.js" + ver)
			.using("<%=path%>/js/comm.js" + ver)
			.excute();
</script>

</head>
<style>
.alContent h3{
	width:100%;
	background-color:#ee5658;
}
.alContent h3 a{display:block;}
.alContent h3 a span{
	color:#fff;
	font-size:16px;
	height:50px;
	line-height:50px;
	width:100px;
	margin:0 auto;
	display:block;
	padding-left:20px;
	background:url(../images/2014ifc/rule/rule_back.png) no-repeat 5px center;
	background-size:13px 13px;
}
</style>
<body>
<jsp:include page="../common/header.jsp"></jsp:include>
 <%if(ClientUtil.CheckMircro(ua)||ClientUtil.CheckApp(ua)){%>
   <div class="alContent" style="margin-top:0;">
   <% }else{%>
   <div class="alContent">
 <% }%>

    <div class="coupon coupon_success" style="min-height:350px;">                   
            <p style="
    text-align: center;
    line-height: 180%; margin-top: 50px;"> 您已成功领取优惠券！<br>
				    
			
    </div>
    <h3><a href="<%=path%>/meet!index?id=228"><span>返回会场</span></a></h3>
</div>
 <jsp:include page="../common/footer.jsp"></jsp:include>
</body>
</html>
