<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8" trimDirectiveWhitespaces="true"%>
<%@ include file="/WEB-INF/views/include.inc.jsp"%>
<div class="pageContent">
<form method="post" action="${contextPath }/management/index/updatePwd" class="required-validate pageForm" onsubmit="return validateCallback(this, dialogAjaxDone);">
	<div class="pageFormContent" layouth="58">
		<p>
			<label><spring:message code="user.old.password"/>：</label>
			<input type="password" name="plainPassword" class="validate[required, maxSize[32]] required" maxlength="32"/>
		</p>	
		<p>
			<label><spring:message code="user.new.password"/>：</label>
			<input type="password" name="newPassword" id="newPassword" class="validate[required, maxSize[32]] required" maxlength="32"/>
		</p>
		<p>
			<label><spring:message code="user.confirm.passrod"/>：</label>
			<input type="password" name="rPassword" class="validate[required,equals[newPassword], maxSize[32]] required" maxlength="32"/>
		</p>
	</div>
	<div class="formBar">
		<ul>
			<li><div class="button"><div class="buttonContent"><button type="submit"><spring:message code="common.edit"/></button></div></div></li>
			<li><div class="button"><div class="buttonContent"><button type="button" class="close" ><spring:message code="common.close"/></button></div></div></li>
		</ul>
	</div>
</form>
</div>