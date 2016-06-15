<%@ page language="java"   contentType="text/html; charset=UTF-8" %>
<%@ taglib prefix="s" uri="/struts-tags" %>
<%@ taglib uri="/tlds/payData" prefix="pd"%> 
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="cdn" uri="http://m.shangpin.com/cdn" %>

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
<link href="${cdn:css(pageContext.request)}/css/base.css" rel="stylesheet" />
<link href="${cdn:css(pageContext.request)}/css/page/order.css" rel="stylesheet" />

<!-- icon -->
<link rel="apple-touch-icon" href="${cdn:pic(pageContext.request)}/images/touch-icon-iphone.png">
<link rel="apple-touch-icon" sizes="72x72" href="${cdn:pic(pageContext.request)}/images/touch-icon-ipad.png">
<link rel="apple-touch-icon" sizes="114x114" href="${cdn:pic(pageContext.request)}/images/touch-icon-iphone4.png">
<link rel="apple-touch-icon" sizes="144x144" href="${cdn:pic(pageContext.request)}/images/touch-icon-newipad.png">
<!-- 启动自定义图片(png, 320X640) -->
<link rel="apple-touch-startup-image" sizes="72x72" href="${cdn:pic(pageContext.request)}/images/logo/loading.png">
<script src="${cdn:js(pageContext.request)}/js/browserSnoof.js" type="text/javascript" charset="utf-8"></script>
<script src="${cdn:js(pageContext.request)}/js/core.js" type="text/javascript" charset="utf-8"></script>

<script src="${cdn:js(pageContext.request)}/jquery/jQuery-1.8.0.js" type="text/javascript" charset="utf-8"></script>

<script type="text/javascript" charset="utf-8">
	var ver = "?20121023001";
		loader = SP.core
			.install("${cdn:js(pageContext.request)}/js/zepto.min.js" + ver)
			.using("${cdn:js(pageContext.request)}/js/comm.js" + ver)
			.using("${cdn:js(pageContext.request)}/js/lazyload.js" + ver)
		    .excute()
		    .using("${cdn:js(pageContext.request)}/js/config.sim.js" + ver)
			.using("${cdn:js(pageContext.request)}/js/browserSnoof.js" + ver)
			.excute(function(){
				isHome(false);
			});
	    $(document).ready(function(){
	    	var accept="${accept}";
			unionpay(accept);
	    });
</script>

</head>

<body>
<jsp:include page="../common/header.jsp"></jsp:include>


<nav class="alNav">
  <ul>
    <li><a href="<%=path%>/">首页</a></li>
    <li><a href="<%=path%>/accountaction!info">我的账户</a></li>
    <li><a href="<%=path%>/orderaction!orderlist">订单管理</a></li>
    <li>支付</li>
  </ul>
</nav>

<input type="hidden" value="${param.orderid}" id="orderid">
<input type="hidden" value="${ch}" id="ch">
<input type="hidden" value="${param.amount}" id="amount">
<input type="hidden" value="<%=path%>" id="path">
 <div class="alContent">
		<div class="pay_btn_box">
		 	<s:iterator value="payList" id="payvo">
			  <s:if test="#payvo.id=='19'">
				<div id="atag" class="pay_btn_yinlian" style="display: none;">
					<a href="javascript:chagePayMode(19,49)"></a>
					
				</div>
				<div id="notpromptandriod" class="pay_btn_yinlian" style="display: none;">
					<a href="javascript:chagePayMode(19,49)"></a>
					 <span>如果未安装银联安全支付控件<a href="http://mobile.unionpay.com/getclient?platform=android&type=securepayplugin">请点这里</a>。</span>
				</div>
				
				<div id="notpromptios" class="pay_btn_yinlian" style="display: none;">
					<a href="javascript:chagePayMode(19,49)"></a>
					 <span>如果未安装银联安全支付控件<a href="http://mobile.unionpay.com/getclient?platform=ios&type=securepayplugin">请点这里</a>。</span>
				</div> 
				
				<div id="embedtag" class="pay_btn_yinlian" style="display: none;">
					<embed id="embed" type="application/x-unionpayplugin"
						uc_plugin_id="unionpay" height="50" width="165"
						paydata="<pd:payData></pd:payData>"></embed>
				</div>
			</s:if>
			<s:elseif test="#payvo.id=='20'">
			<div class="pay_btn_alipay">
				<a href="javascript:chagePayMode(20,37);"></a>
			</div>
			</s:elseif>
			<s:else>
			<div class="pay_btn_hdfk">
				<a href="javascript:chagePayMode(2,41)"></a>
			</div>
			</s:else>
			</s:iterator>
</div></div>
		<jsp:include page="../common/footer.jsp"></jsp:include>
</body>
</html>
