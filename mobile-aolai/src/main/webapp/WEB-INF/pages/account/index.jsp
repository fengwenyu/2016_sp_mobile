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

<title>尚品奥莱-触屏版</title>
<link href="${cdn:css(pageContext.request)}/css/base.css" rel="stylesheet" />
<link href="${cdn:css(pageContext.request)}/css/page/account.css" rel="stylesheet" />

<!-- icon -->
<link rel="apple-touch-icon" href="<%=path%>/images/touch-icon-iphone.png">
<link rel="apple-touch-icon" sizes="72x72" href="<%=path%>/images/touch-icon-ipad.png">
<link rel="apple-touch-icon" sizes="114x114" href="<%=path%>/images/touch-icon-iphone4.png">
<link rel="apple-touch-icon" sizes="144x144" href="<%=path%>/images/touch-icon-newipad.png">
<!-- 启动自定义图片(png, 320X640) -->
<link rel="apple-touch-startup-image" sizes="72x72" href="<%=path%>/images/logo/loading.png">

<script src="${cdn:js(pageContext.request)}/js/core.js" type="text/javascript" charset="utf-8"></script>

<script type="text/javascript" charset="utf-8">
	var ver = "?20121023001";
		loader = SP.core
			.install("${cdn:js(pageContext.request)}/js/zepto.min.js" + ver)
			.using("${cdn:js(pageContext.request)}/js/lazyload.js" + ver)
		    .excute()
		    .using("${cdn:js(pageContext.request)}/js/config.sim.js" + ver)
			.using("${cdn:js(pageContext.request)}/js/comm.js" + ver)
			.excute(function(){
				isHome(false);
				showBuyInfo(${entityJson!=null}?${entityJson}:false);
				<s:if test="platform=='android'||ch=='100001'">
					JSCallback.onPageLoaded();
				</s:if>
			});

	function showBuyInfo(entityJson){
		if(entityJson && "0"==entityJson.code){
			$("#waitpay").html(entityJson.content.waitpaycount);
			$("#prepare").html(entityJson.content.preparegoodscount);
			$("#deliver").html(entityJson.content.delivergoodscount);
			$("#cart").html(entityJson.content.cartgoodscount);
		}
	}
</script>

</head>

<body>

<jsp:include page="../common/header.jsp"></jsp:include>

<nav class="alNav">
  <ul>
    <li><a href="<%=path%>/">首页</a></li>
    <li>我的账户</li>
  </ul>
</nav>

<div class="alContent">
  <div class="account_info">

    <dl>
      <dt class="account_img"><img src="<%=path %>/images/e.gif" lazy="<s:if test="#session['user'].gender == 1"><%=path%>/images/man.png</s:if><s:else><%=path%>/images/woman.png</s:else>" width="80" height="80"></dt>
      <dd class="account_txt"><s:property value="#session['user'].name"/><br />会员级别：<s:property value="#session['user'].level"/><br />
      <!-- 手机号码：<s:property value="#session['user'].mobileNumber"/> -->
        <!--
        <s:if test="#session['user'].mobileNumber == null">
                       未填写<a href="<%=path%>/accountaction!bindphone">[填写]</a>
        </s:if>
        <s:else>
  	      <s:property value="#session['user'].mobileNumber"/>
        </s:else>
         -->
      </dd>
      <dd class="account_btn">
        <a href="<%=path%>/orderaction!orderlist" class="alList_moreBtn">订单管理</a>
        <a href="<%=path%>/accountaction!addresslist" class="alList_moreBtn">管理收货地址</a>
        <a href="<%=path%>/couponaction!couponlist" class="alList_moreBtn">我的优惠券</a>
      </dd>
    </dl>

    <menu>
      <li><a href="<%=path%>/orderaction!orderlist?statusType=2">待支付订单<span id="waitpay"></span></a></li>
      <li><a href="<%=path%>/orderaction!orderlist?statusType=3">待收货订单<span id="prepare"></span></a></li>
    </menu>
  </div>

<!-- 
  <div class="account_cart">
    <a href="<%=path%>/cartaction!showcart"><img src="<%=path%>/images/cart_icon.png" width="31" height="31" alt="购物袋">我的购物袋&nbsp;&nbsp;&nbsp;&nbsp;<span id="cart"></span></a>
  </div>
 -->
</div>

<jsp:include page="../common/footer.jsp"></jsp:include>

</body>
</html>
