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
<link rel="shortcut icon" href="http://pic11.shangpin.com/shangpin/images/logo/favicon.ico" />

<!-- 启动自定义图片(png, 320X640) -->
<link rel="apple-touch-startup-image" sizes="72x72" href="<%=path %>/images/logo/loading.png">
<script src="<%=path%>/js/client.js" type="text/javascript" charset="utf-8"></script>
<script src="<%=path %>/js/core.js" type="text/javascript" charset="utf-8"></script>
<script src="<%=path%>/jquery/jQuery-1.8.0.js" type="text/javascript" charset="utf-8"></script>
<script type="text/javascript" charset="utf-8">
	var ver = "?20121023001";
		loader = SP.core
			.install("<%=path %>/js/zepto.min.js" + ver)
			.using("<%=path%>/js/lazyload.js" + ver)
		    .excute()
		    .using("<%=path%>/js/config.sim.js" + ver)
			.using("<%=path %>/js/comm.js" + ver)
			.using("<%=path %>/js/login.js" + ver)
			.excute(function(){
				isHome(false);
				if($("#sexVal").val()==""){
					$("#sexVal").attr("value",0);
				}
				var gender=$("#sexVal").val();
				if(gender==1){
					$("a[name='0']").removeClass("cur");
					$("a[name='1']").addClass("cur");
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
		//调用客户端提示框-end

	//设置性别
	function setGender(gender){
		$("#gender").val(gender);
	}
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
     <li><a href="<%=path%>/spindex!index?gender=0&ch=${ch}">首页</a></li>
    <li>注册</li>
  </ul>
</nav>

<div class="alContent">
  <form name="sigin" id="J_RegForm" action="accountaction!register?ch=${ch }" method="post">
  <input type="hidden" name="loginFrom" value="${loginFrom }"/>
  <fieldset>
    <br>
    <sprptag:registPrompt type="regist"></sprptag:registPrompt> 
    <p class="c-form-search">
      <label for="email">账户</label><input type="email" id="J_UserName" name="loginName" value="${loginName}" placeholder="输入邮箱地址" required /><button type="button"></button>
      <span class="datalist"></span>
    </p>
    <p class="c-form-search">
      <label for="password">密码</label><input type="password" id="J_Pwd" name="password" value="${password}" placeholder="输入6-20位密码" required maxlength="20" /><button type="button"></button>
    </p>
    <p class="rePwd c-form-search">
      <label for="Repassword">确认密码</label><input type="password" id="J_rePwd" name="Repassword" value="${password}" placeholder="再次输入密码" required maxlength="20" /><button type="button"></button>
    </p>
    <hgroup style="width:100%; margin:0;">
      <p class="c-form-search" style="width:55%; float:left;">
        <label for="captcha">验证码</label>
   	    <input type="text" name="captcha" id="J_Captcha" placeholder="输入验证码" size="6" maxlength="6" style="width:50%;" required />
        <button type="button"></button>
      </p>
      <img alt="" src="accountaction!captcha;" style="margin:0 0 0 10px;" onclick="changeImg(this);">
    </hgroup>
    <div class="fillGender" style="margin:10px 0 0 0px;">性别：<a href="#" class="cur" name="0"><em>女士<i><img src="<%=path %>/images/cur_icon.png"  width="12" height="12"></i></em></a><a href="#" name="1"><em>男士<i><img src="<%=path %>/images/cur_icon.png"  width="12" height="12"></i></em></a><input type="hidden" name="gender" value="${gender}" id="sexVal"  /></div>
   <!-- 
    <a href="<%=path %>/accountaction!agreement?ch=${ch}" class="agreement"><img src="<%=path %>/images/e.gif" lazy="<%=path %>/images/radio_icon.png" width="20" height="20">同意《注册条款》</a>
    <div style="text-align: center;font-size:15px;color:red;margin-bottom: 10px">立即注册，可获赠￥500购物礼券！</div>
    -->
    <p class="login_errorMsg">${msg }</p>
    <input type="submit" class="login_btn" value="注 册" />
    <!--a href="javascript:;" class="login_btn">注 册</a-->
    <div align="right" style="margin:0 0 10px 0;"><a href="<%=path %>/accountaction!loginui?ch=${ch}" >已有账号，请登录&gt;</a></div>
  </fieldset>
  </form>
</div>

<jsp:include page="../common/footer.jsp"></jsp:include>
</body>
</html>
