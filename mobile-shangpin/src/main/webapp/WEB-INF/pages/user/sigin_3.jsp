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
			.excute()
			.using("${cdn:js(pageContext.request)}/styles/shangpin/js/config.sim.js${ver}")
			.using("${cdn:js(pageContext.request)}/styles/shangpin/js/login.js${ver}");
	</script>
</rapid:override > 

<%-- 页标题 --%>
<rapid:override name="page_title">
	完成注册
</rapid:override>


<rapid:override name="content">
	<form name="login" id="J_mobiForm3" action="${ctx}/register" method="post">
        
        <fieldset>
        	<input type="hidden" name="mobi" value="${mobi}"/>
	        <input type="hidden" name="type" value="1"/>
	        <input type="hidden" name="mobiCode" value="${mobiCode}">
            <p class="c-form-search">
                <label for="MobiPwd">设置密码</label>
                <input type="password" id="J_MobiPwd" name="password" placeholder="6-20位英文字母或数字组成" required maxlength="20" />
                <button type="button"></button>
            </p>
            <p class="c-form-search fillGender" id="mobiGender">
            	<label for="Gender">选择性别</label>
                <a href="#" class="cur" name="0">女</a>
                <a href="#" name="1">男</a>
            	<input type="hidden" name="gender" id="sexVal" value="0" />
            </p>
            <p class="login_errorMsg mobiMsg">&nbsp;${msg}</p>
            <input type="submit" class="login_btn" value="完 成" />
              <a href="${ctx}/login" class="login_link">登录</a>
        </fieldset>
    </form>
</rapid:override>
  <rapid:override name="footer">
  
 </rapid:override>
<%@ include file="/WEB-INF/pages/common/common.jsp" %> 