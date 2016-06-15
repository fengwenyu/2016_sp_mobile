<%@ page language="java"  import="java.net.URLEncoder" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page import="java.net.URLEncoder"%>
<%@taglib prefix="s" uri="/struts-tags" %>
<%@ taglib uri="/tlds/payData" prefix="p"%> 
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
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
<link href="<%=path%>/css/base.css" rel="stylesheet" />
<link href="<%=path%>/css/page/order.css" rel="stylesheet" />

<!-- icon -->
<link rel="apple-touch-icon" href="<%=path%>/images/touch-icon-iphone.png">
<link rel="apple-touch-icon" sizes="72x72" href="<%=path%>/images/touch-icon-ipad.png">
<link rel="apple-touch-icon" sizes="114x114" href="<%=path%>/images/touch-icon-iphone4.png">
<link rel="apple-touch-icon" sizes="144x144" href="<%=path%>/images/touch-icon-newipad.png">
<!-- 启动自定义图片(png, 320X640) -->
<link rel="apple-touch-startup-image" sizes="72x72" href="<%=path%>/images/logo/loading.png">
<script src="<%=path%>/js/client.js" type="text/javascript" charset="utf-8"></script>
<script src="<%=path%>/js/core.js" type="text/javascript" charset="utf-8"></script>
<script src="<%=path%>/jquery/jQuery-1.8.0.js" type="text/javascript" charset="utf-8"></script>
<script type="text/javascript" charset="utf-8">
	var ver = "?20121023001";
		loader = SP.core
			.install("/js/zepto.min.js" + ver)
			.using("/js/comm.js" + ver)
			.excute();
		
			//调用客户端提示框-start
			var res;
			window.alert=function(msg){
				res=msg;
				if(browser.versions.android==true){
					
					window.MsgJs.setAlertInfo('{"title":"提示","msg":"'+res+'","ok_btn":"false","cancle_text":"确定","cancle_func":null}');
				}else if(browser.versions.iPhone==true||browser.versions.iPad==true||browser.versions.ios==true){
					setWebitEvent("iphonealert()","05");
				}
				
			}
			//iphone提示框
			function iphonealert(){
	
				var s='{"title":"提示","msg":"'+res+'","ok_btn":"false","cancle_text":"确定","cancle_func":null}';
			return s;
			}
		//调用客户端提示框-end
</script>

</head>

<body>
<jsp:include page="../common/header.jsp"></jsp:include>

<nav class="alNav">
  <ul>
    <li><a href="<%=path%>/spindex!index?gender=0&ch=${ch}">首页</a></li>
    <li><a href="<%=path%>/accountaction!info?ch=${ch}">我的账户</a></li>
    <li><a href="<%=path%>/orderaction!orderlist?ch=${ch}">订单管理</a></li>
    <li>支付</li>
  </ul>
</nav>

<div class="alContent">
  
  <div class="alOrder_success">
  <s:if test="statusType== \"all\"">
    <h2><img src="<%=path%>/images/success.png" width="36" height="36">您的订单已提交成功，等待配送</h2>
    </s:if>
    <s:else>
    <h2><img src="<%=path%>/images/success.png" width="36" height="36">您的订单支付成功</h2>
    </s:else>
     <a href="<%=path%>/orderaction!orderlist?ch=${ch}&statusType=all"  class="alOrder_buyBtn">查看订单</a>
     <a href="<%=path%>/spindex!index?gender=0&ch=${ch}"  class="alOrder_buyBtn">返回首页</a>
  </div>
  
  <ul class="alOrder_done">
    <li>订 单 号：${orderid}</li>
    <!--  
    <s:if test="amount!=null&&amount!=''">
    <li>商品金额：<em>&yen;${amount}</em></li>
    </s:if>
    -->
    <li>配送方式：快递发送（免运费）</li>
  </ul>
  
</div>

<jsp:include page="../common/footer.jsp"></jsp:include>

</body>
</html>
