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
<script src="<%=path%>/js/client.js" type="text/javascript" charset="utf-8"></script>
<script src="<%=path%>/jquery/jQuery-1.8.0.js" type="text/javascript" charset="utf-8"></script>
<script type="text/javascript" charset="utf-8">
	var ver = "?20121023001";
		loader = SP.core
			.install("<%=path %>/js/zepto.min.js" + ver)
			.using("<%=path %>/js/comm.js" + ver)
			.using("<%=path %>/js/login.js" + ver)
			.excute(function(){
				isHome(false);
				var code='${entityJson.code}';
				if(code=='0'){
					$("#mm").hide();
					$("#cg").show();
				}
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
</script>

</head>

<body>
<jsp:include page="../common/header.jsp"></jsp:include>

<nav class="alNav">
  <ul>
    <li><a href="<%=path%>/spindex!index?gender=0&ch=${ch}">首页</a></li>
    <li><a href="<%=path%>/accountaction!info?ch=${ch}">我的账户</a></li>
    <li>修改密码</li>
  </ul>
</nav>

<div class="alContent">
  <form name="login" id="J_EditPwdForm" method="post" action="accountaction!editPassword?ch=${ch}">
  <fieldset>
  	<div id="mm">
    <br>
    <p class="c-form-search">
      <label for="password">当前密码</label><input type="password" id="J_OldPwd" name="oldPassword" placeholder="6-20位" required maxlength="20" />
    </p>
    <p class="c-form-search">
      <label for="password">新密码</label><input type="password" id="J_NewPwd" name="newPassword" placeholder="6-20位" required maxlength="20" />
    </p>
    <p class="rePwd c-form-search">
      <label for="Repassword">再次输入新密码</label><input type="password" id="J_SedPwd" name="sedPassword" placeholder="6-20位"  required maxlength="20" />
    </p>
        <p class="login_errorMsg" style="margin:20px 0 0 0px;">${entityJson.msg}</p>
    <input type="submit" class="login_btn" value="确定" />
    </div>
    <div id="cg" style="display: none;">
  	<p class="login_errorMsg" style="margin:20px 0 0 0px;"><img alt="" src="<%=path%>/images/success.png">密码修改成功！</p>
    <a href="<%=path%>/spindex!index?gender=0" class="alOrder_buyBtn">继续购物</a>
    </div>
    <br>   
  </fieldset>
  </form>
</div>

<jsp:include page="../common/footer.jsp"></jsp:include>

</body>
</html>

