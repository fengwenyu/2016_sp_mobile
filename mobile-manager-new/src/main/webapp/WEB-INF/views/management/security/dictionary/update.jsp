<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8" trimDirectiveWhitespaces="true"%>
<%@ include file="/WEB-INF/views/include.inc.jsp"%>
<a id="refreshJbsxBox2dictionaryTree" rel="jbsxBox2dictionaryTree" target="ajax" href="${contextPath}/management/security/dictionary/tree" style="display:none;"></a>
<div class="pageContent">
<form id="permissionForm" method="post" action="${contextPath }/management/security/dictionary/update" class="required-validate pageForm" onsubmit="return validateCallback(this, dialogReloadRel2Dictionary);">
	<input type="hidden" name="id" value="${dictionary.id }"/>
	<input type="hidden" name="parent.id" value="${dictionary.parent.id }"/>
	<div id="permissionFormContent" class="pageFormContent" layoutH="58">
		<p>
			<label>字典名称：</label>
			<input type="text" name="name" class="validate[required,maxSize[32]] required" size="32" maxlength="32" value="${dictionary.name }" alt="请输入字典名称"/>
		</p>	
		<p>
			<label>字典代码：</label>
			<input type="text" name="code" class="validate[required,maxSize[255]] required" size="32" maxlength="255" alt="请输入字典代码" value="${dictionary.code }"/>
		</p>		
		<p>
			<label>字典描述：</label>
			<input type="text" name="description" class="validate[required,maxSize[32]] required" size="32" maxlength="32" alt="请输入字典描述信息" value="${dictionary.description }"/>
		</p>
		<p>
			<label>优先级：</label>
			<input type="text" name="priority" class="validate[required,custom[integer],min[1],max[99]] required" size="2" maxlength="2" value="${dictionary.priority }"/>
			<span class="info">&nbsp;&nbsp;默认:99</span>
		</p>	
		<p>
			<label>父字典：</label>
			<input name="parent.id" value="${dictionary.parent.id }" type="hidden"/>
			<input class="required" name="parent.name" type="text" readonly="readonly" value="${dictionary.parent.name }"/>
			<a class="btnLook" href="${contextPath}/management/security/dictionary/lookupParent/${dictionary.id}" lookupGroup="parent" mask="true" title="更改父字典" width="400">查找带回</a>			
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