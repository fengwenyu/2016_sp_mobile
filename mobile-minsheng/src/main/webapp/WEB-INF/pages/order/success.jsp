<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib prefix="s" uri="/struts-tags" %>
<%@ taglib uri="/tlds/activityName" prefix="spantag"%> 
<%String path = request.getContextPath();%>
<!doctype html>
<html lang="zh-CN">
<head>

<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<meta name="viewport" content="width=device-width, initial-scale=1.0, user-scalable=no, minimum-scale=1.0, maximum-scale=1.0">
<link rel="shortcut icon" href="http://pic11.shangpin.com/shangpin/images/logo/favicon.ico" />

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
			.install("<%=path%>/js/zepto.min.js" + ver)
			.using("<%=path%>/js/comm.js" + ver)
			.excute(function(){
				isHome(false);
				<s:if test="#session['ch'] == '100001'">
					JSCallback.onPageLoaded();
				</s:if>
			});
		
		
		
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
    <li><a href="<%=path%>/spindex!index?gender=0&ch=${ch}">首页</li>
    <li><a href="<%=path%>/cartaction!showcart?ch=${ch}">购物袋</a></li>
    <li>订单成功</li>
  </ul>
</nav>

<div class="alContent">
  
  <div class="alOrder_success">
      <h2><img src="<%=path%>/images/success.png" width="36" height="36">您的订单已提交成功</h2>
 <%--      <s:set name="paytypeid" value="#parameters.paytypeid[0]"/>
    <s:if test="#paytypeid == \"20\"">
      <a href="<%=path%>/alipay/trade?ch=${ch}&orderId=${param.orderid}" class="alOrder_buyBtn">立即支付</a>
    </s:if>
    <s:else>--%> 
    	 <a href="<%=path%>/orderaction!payment?orderid=${param.orderid}&amount=${param.amount}&ch=${ch}" class="alOrder_buyBtn">立即支付</a>
      <a href="<%=path%>/spindex!index?gender=0&ch=${ch}" class="alOrder_buyBtn">继续购物</a>
  <%--   </s:else>--%> 
  </div>
  
  <ul class="alOrder_done">
    <li>订 单 号：${param.orderid}</li>
    <!--  <li>商品金额：<em>&yen;${param.amount}</em></li>-->
    <li>配送方式：快递发送（免运费）</li>
  </ul>
  
</div>

<jsp:include page="../common/footer.jsp"></jsp:include>

</body>
</html>
