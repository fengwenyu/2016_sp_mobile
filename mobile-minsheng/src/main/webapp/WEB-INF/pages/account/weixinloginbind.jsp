<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ taglib uri="/tlds/registPrompt" prefix="sprptag"%> 
<%@ taglib uri="/tlds/activityName" prefix="spantag"%> 
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + path + "/";
%>
<%@ taglib prefix="s" uri="/struts-tags"%>
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
<link href="<%=path %>/css/base.css" rel="stylesheet" />
<link href="<%=path %>/css/page/login.css" rel="stylesheet" />

<!-- icon -->
<link rel="apple-touch-icon" href="<%=path %>/images/touch-icon-iphone.png">
<link rel="apple-touch-icon" sizes="72x72" href="<%=path %>/images/touch-icon-ipad.png">
<link rel="apple-touch-icon" sizes="114x114" href="<%=path %>/images/touch-icon-iphone4.png">
<link rel="apple-touch-icon" sizes="144x144" href="<%=path %>/images/touch-icon-newipad.png">
<!-- 启动自定义图片(png, 320X640) -->
<link rel="apple-touch-startup-image" sizes="72x72" href="<%=path %>/images/logo/loading.png">
<link rel="shortcut icon" href="http://pic11.shangpin.com/shangpin/images/logo/favicon.ico" />

<script src="<%=path %>/js/core.js" type="text/javascript" charset="utf-8"></script>
<script type="text/javascript" charset="utf-8">
	var ver = "?20121023001";
		loader = SP.core
			.install("<%=path %>/js/zepto.min.js" + ver)
			.using("<%=path %>/js/comm.js" + ver)
			.using("<%=path %>/js/login.js" + ver)
			.excute(function(){
				isHome(false);
				<s:if test="#session['ch'] == '100001'">
					JSCallback.onPageLoaded();
				</s:if>
			});

    function changeImg(v){
    	var img = "accountaction!captcha?t="+ new Date().getTime() ;
    	$(v).attr("src",img);
    }
</script>

</head>

<body>

<jsp:include page="../common/header_weixin.jsp"></jsp:include>

<nav class="alNav">
  <ul>
    <li><a href="<%=path%>/spindex!index?gender=0&ch=${ch}">首页</a></li>
    <li>绑定登录</li>
  </ul>
</nav>

<div class="alContent">
  <form name="login" id="J_Login" method="post" action="weixinaction!loginbind?ch=${ch }">
 
  <fieldset>
    <br>
    <legend>绑定后，无需登录即可享受订单物流跟踪、优惠券管理等尚品会员专属服务。</legend>
    <p class="c-form-search">
      <label for="email">账户</label><input type="text" id="J_UserNameTxt" name="loginName" value="${email }" placeholder="输入邮箱地址或手机号码" required /><button type="button"></button>
      <span class="datalist"></span>
    </p>
    <p class="c-form-search">
      <label for="password">密码</label><input type="password" id="J_PassWordTxt" name="password"  value="${password}" placeholder="输入密码" required maxlength="20" />
    </p>
    <hgroup style="width:100%; margin:0;">
    <p class="c-form-search" style="width:55%; float:left;">
      <label for="captcha">验证码</label>
   	  <input type="text" name="captcha" id="J_Captcha" placeholder="输入验证码" size="6" maxlength="6" style="width:50%;" required />
      <button type="button"></button>
    </p>
    <img alt="" src="accountaction!captcha;" style="margin:0 0 0 10px;" onclick="changeImg(this);">
    </hgroup>
    <p class="login_errorMsg" style="margin:20px 0 0 0px;">${msg }</p>
    <input type="submit" class="login_btn" value="登录，并与微信绑定" />
    <!--a href="javascript:;" class="login_btn">登 录</a -->
    <br>
    <div align="right" style="margin:0 0 10px 0;">
      <a href="<%=path %>/weixinaction!weixinregisterui?&ch=${ch}" style="font-size:15px;">
	    <sprptag:registPrompt type="login"></sprptag:registPrompt>&gt; 
      </a>
    </div>
  </fieldset>
  </form>
</div>

<jsp:include page="../common/footer_weixin.jsp"></jsp:include>

</body>
</html>

