<%@ page language="java" contentType="text/html; charset=UTF-8" trimDirectiveWhitespaces="true"
    pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/pages/common/include.inc.jsp"%>

<rapid:override name="custum">
   	<link href="${cdn:css(pageContext.request)}/styles/shangpin/css/base.css${ver}" rel="stylesheet" />
	<link href="${cdn:css(pageContext.request)}/styles/shangpin/css/page/login.css${ver}" rel="stylesheet" />
	
	<script src="${cdn:js(pageContext.request)}/styles/shangpin/js/core.js${ver}" type="text/javascript" charset="utf-8"></script>
	<script type="text/javascript" charset="utf-8">
			loader = SP.core
				.install("${cdn:js(pageContext.request)}/styles/shangpin/js/zepto.min.js${ver}")
				.using("${cdn:js(pageContext.request)}/styles/shangpin/js/comm.js${ver}")
				.using("${cdn:js(pageContext.request)}/styles/shangpin/js/login.js?t="+ new Date().getTime()) 
				.excute();
	</script>
	<script type="text/javascript" charset="utf-8">
		function changeImg(v){
	    	var img = "${ctx}/captcha?t="+ new Date().getTime() ;
	    	$(v).attr("src",img);
	 	}
	</script>
</rapid:override > 

<%-- 页标题 --%>
<rapid:override name="page_title">
	
</rapid:override>

<rapid:override name="content">
	<c:import url="/nav?navId=2"></c:import>
	<div class="alContent">
	  <form name="login" id="J_Login" method="post" action="${ctx}/login">
		  <input type="hidden" name="back" value="${back}" />
		  <fieldset>
		        <legend>如您在电脑上已注册可以直接登录，尚品网账号可直接登录。</legend>
			    <p class="c-form-search">
			      <label for="email">账&nbsp;&nbsp;&nbsp;&nbsp;号</label><input type="text" id="J_UserNameTxt" name="userName" placeholder="手机／邮箱" required /><button type="button"></button>
			       <span class="datalist"></span>
			    </p>
			    <p class="c-form-search">
			      <label for="password">密&nbsp;&nbsp;&nbsp;&nbsp;码</label><input type="password" id="J_PassWordTxt" name="password" placeholder="请输入密码" required maxlength="20" /><button type="button"></button>
			    </p>
			    <hgroup style="width:100%; margin:0;">
				    <p class="c-form-search" style="width:55%; float:left;">
				      <label for="captcha">验证码</label>
				   	  <input type="text" name="captcha" id="J_Captcha" placeholder="输入验证码" size="6" maxlength="6" style="width:50%;" required />
				      <button type="button"></button>
				    </p>
				    <img title="看不清，单击图片换一组" alt="" src="${ctx}/captcha" style="margin:0 0 0 10px;" onclick="changeImg(this);">
			    </hgroup>
			    <p class="login_errorMsg mobiMsg">&nbsp;${msg}</p>
			    <input type="submit" class="login_btn" value="登 录" />
			    <div align="right" style="margin:0 0 10px 0;"><a href="${ctx }/register">免费注册</a></div>
		  </fieldset>
	  </form>
	</div>
</rapid:override> 

<%@ include file="/WEB-INF/pages/common/base.jsp" %> 