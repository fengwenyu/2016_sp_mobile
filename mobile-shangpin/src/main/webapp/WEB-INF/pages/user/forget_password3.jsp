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
			.using("${cdn:js(pageContext.request)}/styles/shangpin/js/register2.js${ver}")
			.excute();
</script>
</rapid:override > 

<rapid:override name="title">
	重置密码
</rapid:override>

<%-- 页标题 --%>
<rapid:override name="page_title">
	重置密码
</rapid:override>

<rapid:override name="content">  

	<form name="login" id="J_mobiForm3" action="${ctx}/changepwd" method="post" onsubmit="return checkPassword();">
        <input type="hidden" id="J_phonenum" name="mobi" value="${mobi}"/>
        <input type="hidden" name="type" value="1"/>
        <fieldset>
        	<p class="mobiTxt">请输入${mobi}收到的短信校验码</p>
            <p class="c-form-search verification">
                <input type="text" id="J_MobiCode" name="mobiCode" placeholder="短信校验码" class="verCode" required maxlength="6" style="margin-top: 7px"/>
                <button type="button"></button>
                <span>
                	<!-- 下面按钮二选一 -->
                    <i class="reSend_btn"></i>
                    <a href="javascript:;" class="login_btn" style="display:none;" onclick="sendFindpwdcode();">重新发送</a>
                </span>
            </p>
            <p class="c-form-search">
                <label for="MobiPwd">设置密码</label>
                <input type="text" name="password" onfocus="this.type='password'" id="J_MobiPwd" autocomplete="off" name="password" placeholder="6-20位英文字母或数字组成" required maxlength="20" />
                <button type="button"></button>
            </p>
            <p class="login_errorMsg mobiMsg">&nbsp;${msg}</p>
            <input type="submit" class="login_btn" value="完 成" />
        </fieldset>
    </form>

</rapid:override > 

  <rapid:override name="footer">
  
 </rapid:override>
<%@ include file="/WEB-INF/pages/common/common.jsp" %> 
