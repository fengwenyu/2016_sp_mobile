<%@ page language="java" contentType="text/html; charset=UTF-8" trimDirectiveWhitespaces="true"
    pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/pages/common/include.inc.jsp"%> 

<rapid:override name="custum">
	<link href="${cdn:css(pageContext.request)}/styles/shangpin/css/page/index.css${ver}" rel="stylesheet" />
	<link href="${cdn:css(pageContext.request)}/styles/shangpin/css/page/login.css${ver}" rel="stylesheet" />
		
	<script type="text/javascript" charset="utf-8">
			loader = SP.core
			.install("${cdn:js(pageContext.request)}/styles/shangpin/js/jquery.min.js${ver}")
			.using("${cdn:js(pageContext.request)}/styles/shangpin/js/lazyload.js${ver}")
			.using("${cdn:js(pageContext.request)}/styles/shangpin/js/login.js${ver}") 
			.excute()
			.using("${cdn:js(pageContext.request)}/styles/shangpin/js/config.sim.js${ver}")
			.using("${cdn:js(pageContext.request)}/styles/shangpin/js/login1.js${ver}")
			.excute() ;
			
   </script>
</rapid:override > 

<%-- 页标题 --%>
<rapid:override name="page_title">
	找回密码
</rapid:override>

<rapid:override name="content">

	  <form name="login" id="J_Forget1" action="" method=""  onsubmit="return checkCaptchaCode();">
	  <fieldset>
		<p class="c-form-search">
	       <input type="text" id="J_UserNameTxt" name="mobi" placeholder="请输入手机号／邮箱" value="${mobi}" required onblur="sendAddress();"/>
	       <button type="button"></button>
	       <span class="datalist"></span>
    	</p>
    
    	<p class="c-form-search verification">
	        <input type="text" id="J_verCode" name="captcha" placeholder="右侧验证码" class="verCode" required maxlength="6" />
	        <button type="button"></button>
	        <span>
            <img src="${ctx}/captcha" width="60" height="25"><a href="#" onclick="changeImage();">刷新</a>
        	</span>
   		</p>
	    
	    <p class="login_errorMsg mobiMsg">&nbsp;${msg}</p>
	    <input type="submit"  class="login_btn" value="下一步" />
	  </fieldset>
	  </form>
</rapid:override > 
  <rapid:override name="footer">
  
 </rapid:override>
<%@ include file="/WEB-INF/pages/common/common.jsp" %>  