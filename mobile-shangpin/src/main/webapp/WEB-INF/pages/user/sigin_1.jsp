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
			.using("${cdn:js(pageContext.request)}/styles/shangpin/js/register1.js${ver}")
			.excute();
</script>
</rapid:override > 


<%-- 页标题 --%>
<rapid:override name="page_title">
	注册
</rapid:override>

<rapid:override name="content">
<form name="login" id="J_mobiForm1" action="${ctx}/sendRegistPhoneCode" method="post" onsubmit="return checkCaptchaCode();">
        <fieldset>
            <p class="c-form-search">
                <label for="mobi">手机号</label>
                <input type="text" id="J_MobiName" name="mobi" placeholder="11位手机号码" value= "${mobi}" required />
                <button type="button" onclick="clear();"></button>
                <span class="datalist"></span>
            </p>
	          
	        <p class="c-form-search verification"> 
                 <label for="verCode">验证码</label> 
                 <input type="text" name="captcha" id="J_verCode" placeholder="请输入验证码" size="6" maxlength="6" style="width:50%; margin-left: 77px; margin-top: -70px;" required/>
                 <button type="button"></button>
                 <span> 
                		<img src="${ctx}/captcha" width="60" height="25"><a href="#" onclick="changeImage();">刷新</a> 
                 </span>
                
             </p> 
	           
             <p class="login_errorMsg mobiMsg">&nbsp;${msg}</p>
            <input type="submit" class="login_btn" value="注 册" onclick="_smq.push(['custom', '注册', '${sessionScope.mshangpin_user.userid}']);"/>
            <a href="${ctx}/login" class="login_link">登录</a>
        </fieldset>
 </form>

</rapid:override > 
<rapid:override name="footer">
  
 </rapid:override>        
<%@ include file="/WEB-INF/pages/common/common.jsp" %> 