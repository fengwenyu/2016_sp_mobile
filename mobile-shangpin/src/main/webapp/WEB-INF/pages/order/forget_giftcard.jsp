<%@ page language="java" contentType="text/html; charset=UTF-8" trimDirectiveWhitespaces="true"
    pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/pages/common/include.inc.jsp"%>

<rapid:override name="custum">
	<link href="${cdn:css(pageContext.request) }/styles/shangpin/css/page/login.css${ver}" rel="stylesheet" />
	<script type="text/javascript" charset="utf-8">
		loader = SP.core
		.install("${cdn:js(pageContext.request)}/styles/shangpin/js/jquery.min.js${ver}")
		.using("${cdn:js(pageContext.request)}/styles/shangpin/js/giftcard.js${ver}")
		.excute();
	</script>
</rapid:override>

<rapid:override name="page_title">
	忘记支付密码
</rapid:override>

<rapid:override name="content">
	
		<form name="login" id="J_Forget2" >
			<input type="hidden" name="mobile" id="J_phonenum" value="${mobile}"/>
	        <fieldset>
	            <p class="mobiTxt">您绑定的手机：${fn:substring(mobile,0,2)}******${fn:substring(mobile,9,11)}</p>
	            <p class="c-form-search verification">
	                
	                <input type="text" id="J_MobiCode" name="mobiCode" placeholder="输入6位验证码" class="verCode" required maxlength="6" />
	                <button type="button"></button>
	                <span>
	                	<!-- 下面按钮二选一 -->
	                    <i class="reSend_btn">55秒后重新发送</i>
	                    <a href="javascript:sendphonecode();" class="login_btn" style="display:none;">重新发送</a>
	                </span>
	            </p>
	            <p class="c-form-search">
	                <input type="password" id="J_MobiPwd" name="MobiPwd" placeholder="设置新支付密码（6-20位数字+字母结合）" required maxlength="20" />
	                <button type="button"></button>
	            </p>
	            <p class="c-form-search">
	                <input type="password" id="J_resetPwd" name="resetPwd" placeholder="确认支付密码" required maxlength="20" />
	                <button type="button"></button>
	            </p>
	            <p class="login_errorMsg mobiMsg">&nbsp;</p>
<!-- 	            <input type="submit" class="login_btn" value="确 定" onclick="setPassword();" /> -->
	             <a href="javascript:setPassword();" class="login_btn">确定</a>
	        </fieldset>
	    </form>
	
</rapid:override>
<rapid:override name="footer">

</rapid:override>
<%@ include file="/WEB-INF/pages/common/common.jsp" %> 