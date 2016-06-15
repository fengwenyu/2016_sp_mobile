<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
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

<title>尚品奥莱-触屏版</title>
<link href="<%=path %>/css/base.css" rel="stylesheet" />
<link href="<%=path %>/css/page/login.css" rel="stylesheet" />

<!-- icon -->
<link rel="apple-touch-icon" href="<%=path %>/images/touch-icon-iphone.png">
<link rel="apple-touch-icon" sizes="72x72" href="<%=path %>/images/touch-icon-ipad.png">
<link rel="apple-touch-icon" sizes="114x114" href="<%=path %>/images/touch-icon-iphone4.png">
<link rel="apple-touch-icon" sizes="144x144" href="<%=path %>/images/touch-icon-newipad.png">
<!-- 启动自定义图片(png, 320X640) -->
<link rel="apple-touch-startup-image" sizes="72x72" href="<%=path %>/images/logo/loading.png">

<script src="<%=path %>/js/core.js" type="text/javascript" charset="utf-8"></script>
<script type="text/javascript" charset="utf-8">
	var ver = "?20121023001";
		loader = SP.core
			.install("<%=path %>/js/zepto.min.js" + ver)
			.using("<%=path %>/js/comm.js" + ver)
			.using("<%=path %>/js/login.js" + ver)
			.excute(function(){
				isHome(false);
				<s:if test="platform=='android'||ch=='100001'">
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

<jsp:include page="../common/header.jsp"></jsp:include>

<nav class="alNav">
  <ul>
    <li><a href="<%=path %>/">首页</a></li>
    <li>登录</li>
  </ul>
</nav>

<div class="alContent">
  <form name="login" id="J_Login" method="post" action="accountaction!login">
  <input type="hidden" name="loginFrom" value="${param.loginFrom }"/>
   <input type="hidden" name="couponcode" value="${couponcode }"/>
    <input type="hidden" name="isyr" value="${isyr}"/>
  <fieldset>
    <legend>如您在电脑上已注册可以直接登录，尚品网账号可直接登录。</legend>
    <p class="c-form-search">
      <label for="email">账户</label><input type="text" id="J_UserNameTxt" name="loginName" value="${loginName }" placeholder="输入邮箱地址或手机号码" required /><button type="button"></button>
      <span class="datalist"></span>
    </p>
    <p class="c-form-search">
      <label for="password">密码</label><input type="password" id="J_PassWordTxt" name="password" placeholder="输入密码" required maxlength="20" /><button type="button"></button>
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
    <input type="submit" class="login_btn" value="登 录" />
    <!--a href="javascript:;" class="login_btn">登 录</a -->
     <s:if test="{couponcode!=null||couponcode!=''}">
    <div align="right" style="margin:0 0 10px 0;"><a href="<%=path %>/accountaction!registerui?loginFrom=${loginFrom }&couponcode=${couponcode }">免费注册</a></div>
    </s:if>
    <s:else>
    <div align="right" style="margin:0 0 10px 0;"><a href="<%=path %>/accountaction!registerui?loginFrom=${loginFrom }">免费注册</a></div>
    </s:else>
  </fieldset>
  </form>
</div>

<jsp:include page="../common/footer.jsp"></jsp:include>

</body>
</html>

