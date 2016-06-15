<%@ page language="java"   contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib prefix="s" uri="/struts-tags" %>
<%@ taglib uri="/tlds/payData" prefix="p"%> 
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%String path = request.getContextPath();%>
<!doctype html>
<html lang="zh-CN">
<head>

<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<meta name="viewport" content="width=device-width, initial-scale=1.0, user-scalable=no, minimum-scale=1.0, maximum-scale=1.0">

<!-- 开启对web app程序的支持 -->
<meta name="apple-mobile-web-app-capable" content="yes">
<!-- 全屏模式浏览 -->
<meta name="apple-touch-fullscreen" content="yes">
<!-- 改变Safari状态栏的外观 -->
<meta name="apple-mobile-web-app-status-bar-style" content="black">

<title>尚品奥莱-触屏版</title>
<link href="<%=path%>/css/base.css" rel="stylesheet" />
<link href="<%=path%>/css/page/order.css" rel="stylesheet" />

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
			.install("<%=path%>/js/zepto.min.js" + ver)
			.using("<%=path%>/js/comm.js" + ver)
			.excute(function(){
				isHome(false);
				<s:if test="platform=='android'||ch=='100001'">
					JSCallback.onPageLoaded();
				</s:if>
			});
</script>

</head>

<body>

<jsp:include page="../common/header.jsp"></jsp:include>

<nav class="alNav">
  <ul>
    <li><a href="<%=path%>/aolaiindex!index">首页</a></li>
    <li><a href="<%=path%>/allcartaction!listCart">购物袋</a></li>
    <li>订单成功</li>
  </ul>
</nav>

<div class="alContent">
  
  <div class="alOrder_success">
      <h2><img src="<%=path%>/images/success.png" width="36" height="36">您的订单已提交成功</h2>
  <%--   <s:set name="paytypeid" value="#parameters.paytypeid[0]"/>
     <s:if test="#paytypeid == \"20\"">
      <a href="<%=path%>/alipay/trade?ch=${ch}&orderId=${param.orderid}" class="alOrder_buyBtn">立即支付</a>
  
    </s:if>
    <s:else>--%>  
    
      <a href="<%=path%>/orderaction!payment?orderid=${param.orderid}&amount=${param.amount}" class="alOrder_buyBtn">立即支付</a>
      <a href="<%=path%>/aolaiindex!index" class="alOrder_buyBtn">继续购物</a>
  <%--   </s:else>--%> 
  </div>
  
  <ul class="alOrder_done">
    <li>订 单 号：${param.orderid}</li>
   <!--   <li>商品金额：<em>&yen;${param.amount}</em></li>-->
    <li>配送方式：快递发送</li>
  </ul>
</div>
<img src="http://mvp.mediav.com/t?jzqt=tran&type=3&db=none&jzqs=m-24361-0&jzqv=3.2.7.12&jzqo=${param.orderid}&jzqot=${param.amount}"></img><%--mediav移动端监测代码 --%>
<jsp:include page="../common/footer.jsp"></jsp:include>

</body>
</html>
