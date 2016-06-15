<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8" trimDirectiveWhitespaces="true"%>
<%@ include file="/WEB-INF/views/include.inc.jsp"%>
<div class="pageContent">
<form method="post" action="${contextPath}/management/admin/app/update" class="required-validate pageForm" onsubmit="return validateCallback(this, dialogReloadNavTab);">
	<input type="hidden" name="id" value="${app.id}"/>
	<div class="pageFormContent" layoutH="58">
		<p>
			<label>名称：</label>
			<input type="text" name="name" class="validate[required,maxSize[20]] required" size="20" maxlength="20" value="${app.name }"/>
		</p>
		<p>
			<label>缩写(英文/拼音)：</label>
			<input type="text" name="code" class="validate[required,maxSize[20]] required" size="20" maxlength="20" value="${app.code }"/>
		</p>		
		<p>
			<label>编号：</label>
			<input type="text" name="num" class="validate[required,maxSize[20]]" size="20" maxlength="20" readonly="readonly" value="${app.num }"/>
		</p>
		<p>
			<label>平台：</label>
			<select name="platform">
				<option value="IOS" ${app.platform == "IOS" ? 'selected="selected"' : ''}>IOS</option>
				<option value="ANDROID" ${app.platform == "ANDROID" ? 'selected="selected"' : ''}>ANDROID</option>
			</select>
		</p>
		<p class="nowrap">
			<label>描述：</label>
			<textarea name="description" class="required" cols="30" rows="5">${app.description}</textarea>
		</p>
	</div>
			
	<div class="formBar">
		<ul>
			<li><div class="button"><div class="buttonContent"><button type="submit">确定</button></div></div></li>
			<li><div class="button"><div class="buttonContent"><button type="button" class="close">关闭</button></div></div></li>
		</ul>
	</div>
</form>
</div>