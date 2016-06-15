<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8" trimDirectiveWhitespaces="true"%>
<%@ include file="/WEB-INF/views/include.inc.jsp"%>
<div class="pageContent">
<form method="post" action="${contextPath }/management/manager/product/update" class="required-validate pageForm" onsubmit="return validateCallback(this, dialogReloadNavTab);">
	<div class="pageFormContent" layoutH="58">
	<input type="hidden" name="id" value="${product.id}"> </input>
	<dl>
		<dt>产品号：</dt>
		<dd>
			<input type="text" name="productNum" class="validate[required,maxSize[32]" size="30" maxlength="32"  readonly="readonly" value="${product.productNum}"/>
		</dd>
	</dl>
	<dl>
		<dt>产品名：</dt>
		<dd>
			<input type="text" name="productName" class="validate[required,maxSize[255]" size="30" maxlength="255" value="${product.productName}"/>
		</dd>
	</dl>	
	<dl>
	  <dt>手机平台：</dt>
	  <dd>
	     <select name="platForm" id="platform" >
	        <option value="ios" <c:if test="${product.platForm eq 'ios'}">selected</c:if>>ios</option>
	        <option value="android" <c:if test="${product.platForm eq 'android'}">selected</c:if>>android</option>
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