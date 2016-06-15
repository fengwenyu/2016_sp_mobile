<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8" trimDirectiveWhitespaces="true"%>
<%@ include file="/WEB-INF/views/include.inc.jsp"%>
<div class="pageContent">
<form method="post" action="${contextPath }/management/manager/product/create" class="required-validate pageForm" onsubmit="return validateCallback(this, dialogReloadNavTab);">
	<div class="pageFormContent" layoutH="58">
	<dl>
		<dt>产品号：</dt>
		<dd>
			<input type="text" name="productNum" class="validate[required,maxSize[32]"  readonly="readonly"  size="30" maxlength="32" value="${productNum}"/>
		</dd>
	</dl>
	<dl>
		<dt>产品名：</dt>
		<dd>
			<input type="text" name="productName" class="validate[maxSize[255]" size="30" maxlength="255" alt="请输入产品名"/>
		</dd>
	</dl>	
	<dl>
	  <dt>手机平台：</dt>
	  <dd>
	     <select name="platForm" id="platform">
	        <option value="ios">ios</option>
	        <option value="android">android</option>
	     </select>
	  </dd>
	</dl>
	</div>
	
	<div class="formBar">
		<ul>
			<li><div class="button"><div class="buttonContent"><button type="submit">确定</button></div></div></li>
			<li><div class="button"><div class="buttonContent"><button class="close" type="button">关闭</button></div></div></li>
		</ul>
	</div>
</form>
</div>