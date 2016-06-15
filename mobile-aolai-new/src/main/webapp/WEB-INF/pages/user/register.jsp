<%@ page language="java" contentType="text/html; charset=UTF-8" trimDirectiveWhitespaces="true"
    pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/pages/common/include.inc.jsp"%>

<rapid:override name="custum">
   	<script src="${cdn:js(pageContext.request)}/styles/shangpin/js/core.js${ver}" type="text/javascript" charset="utf-8"></script>
   	<link href="${cdn:css(pageContext.request)}/styles/shangpin/css/base.css${ver}" rel="stylesheet" />
	<link href="${cdn:css(pageContext.request)}/styles/shangpin/css/page/index.css${ver}" rel="stylesheet" />
	<link href="${cdn:css(pageContext.request)}/styles/shangpin/css/page/login.css${ver}" rel="stylesheet" />
	
	<script type="text/javascript" charset="utf-8">
		loader = SP.core
			.install("${cdn:js(pageContext.request)}/styles/shangpin/js/zepto.min.js${ver}")
			.using("${cdn:js(pageContext.request)}/styles/shangpin/js/lazyload.js${ver}")
			.excute()
			.using("${cdn:js(pageContext.request)}/styles/shangpin/js/config.sim.js${ver}")
			.using("${cdn:js(pageContext.request)}/styles/shangpin/js/comm.js${ver}")
			.using("${cdn:js(pageContext.request)}/styles/shangpin/js/register.js${ver}")
			.using("${cdn:js(pageContext.request)}/styles/shangpin/js/login.js${ver}")
			.excute(function(){
				isHome(false);
			});
			
</script>
</rapid:override > 

<%-- 页标题 --%>
<rapid:override name="page_title">
</rapid:override>

<rapid:override name="content">
<c:import url="/nav?navId=3"></c:import>
<div class="alContent">
  <form name="sigin" id="J_RegForm" action="${ctx}/register" method="post">
  <fieldset>
    <br>
    <legend>如您在电脑上已注册可以直接登录。</legend>
    <p class="c-form-search">
    
      <label for="email">账户</label><input type="email" id="J_UserName" name="userName" value="${userName}" placeholder="输入邮箱地址" required /><button type="button"></button>
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
   	    <input type="text" name="captcha" id="J_Verification" placeholder="输入验证码" size="6" maxlength="6" style="width:50%;" required />
        <button type="button"></button>
      </p>
      <img title="看不清，单击图片换一组" src="${ctx}/captcha"  style="margin:0 0 0 10px;" onclick="changeImg(this);" width="95" height="40">
    </hgroup>
 
   <div class="fillGender" id="mobiGender" >
   		性别：
   		<a href="#" class="cur" name="0" onclick="changeGender(this);"><em>女士<i><img src="${cdn:pic(pageContext.request)}/styles/shangpin/images/cur_icon.png" width="12" height="12"></i></em></a>
   		<a href="#" name="1" onclick="changeGender(this);"><em>男士<i><img src="${cdn:pic(pageContext.request)}/styles/shangpin/images/cur_icon.png" width="12" height="12"></i></em></a>
   		<input type="hidden" name="gender" id="sexVal01" value="0" />
		
	</div>
	
    <a href="${ctx}/agreement" class="agreement"><img src="${cdn:pic(pageContext.request)}/styles/shangpin/images/radio_icon.png" width="20" height="20">同意《注册条款》</a>
     <!--   <div style="text-align: center;font-size:15px;color:red;margin-bottom: 10px">立即注册，可获赠￥500购物礼券！</div>
    -->
 <p class="login_errorMsg regMsg">${msg}</p>
    <input type="submit" class="login_btn" value="注 册"/>
    <div align="right" style="margin:0 0 10px 0;"><a href="${ctx}/login" >已有账号，请登录&gt;</a></div>
  </fieldset>
  </form>
</div>

</rapid:override > 

<%@ include file="/WEB-INF/pages/common/base.jsp" %> 