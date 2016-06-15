<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib prefix="s" uri="/struts-tags" %>
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
<link href="<%=path%>/css/page/account.css" rel="stylesheet" />

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
			.using("<%=path%>/js/account.js" + ver)
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
    <li><a href="<%=path%>/">首页</a></li>
    <li><a href="<%=path%>/accountaction!info">我的账户</a></li>
    <li>绑定手机号</li>
  </ul>
</nav>

<div class="alContent">
  <div class="mobile_block">
    <form name="mobile" id="J_CodeForm" action="<%=path%>/accountaction!verifyphonecode" method="post">
      <p>验证码已发至手机 <span>${phonenum }</span> 请查看手机短信。</p>
      <label>输入验证码：</label><input type="tel" id="J_Code" maxlength="6" class="code_txt" name="verifycode" />&nbsp;&nbsp;<a href="javascript:alert(123);" id="secondCode">重新发送验证码</a>
      <input type="hidden" name="phonenum" value="${phonenum }"/>
      <span class="mobile_errorMsg">&nbsp;</span>
      <p class="mobile_btn">
        <input type="submit" class="alList_submitBtn" value="确认" /> 
        <!-- a href="/htmlmobilecode.html" class="alList_submitBtn">确认</a-->
        <a href="<%=path%>/accountaction!info" class="alList_moreBtn">取消</a>
      </p>
    </form>
  </div>
</div>

<jsp:include page="../common/footer.jsp"></jsp:include>

</body>
</html>
