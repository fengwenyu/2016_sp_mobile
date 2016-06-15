<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8" trimDirectiveWhitespaces="true"%>
<%@ include file="/WEB-INF/views/include.inc.jsp"%>
<script type="text/javascript">
<!--
jQuery(document).ready(function(){
	$("#captcha").click(function(){
		$(this).attr("src", "${contextPath }/Captcha.jpg?time=" + new Date());
		return false;
	});
});
//-->
</script>
<div class="pageContent">
	<form method="post" action="${contextPath }/login" class="required-validate pageForm" onsubmit="return validateCallback(this, dialogAjaxDone)">
		<%-- 指定为ajax请求 --%>
		<input type="hidden" name="ajax" value="true"/>
		<div class="pageFormContent" layoutH="58">
			<p>
				<label><spring:message code="user.username"/>:</label>
				<input type="text" name="username" id="username" maxlength="32" class="validate[required] required"/>
			</p>
			<p>
				<label><spring:message code="user.password"/>:</label>
				<input type="password" name="password" id="password" maxlength="32" class="validate[required] required"/>
			</p>
			<p>
				<label><spring:message code="user.captcha.code"/>:</label>
				<input type="text" id="captcha_key" name="captcha_key" class="code validate[required,maxSize[6]]" size="6" />&nbsp;&nbsp;
				<span><img src="${contextPath }/Captcha.jpg" alt='<spring:message code="user.captcha.refresh"/>' width="75" height="24" id="captcha"/></span>
			</p>			
		</div>
		<div class="formBar">
			<ul>
				<li><div class="button"><div class="buttonContent"><button type="submit"><spring:message code="user.login"/></button></div></div></li>
				<li><div class="button"><div class="buttonContent"><button type="button" class="close"><spring:message code="common.close"/></button></div></div></li>
			</ul>
		</div>
	</form>
	
</div>