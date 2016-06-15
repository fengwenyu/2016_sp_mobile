<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8"
	trimDirectiveWhitespaces="true"%>
<%@ include file="/WEB-INF/views/include.inc.jsp"%>
<div class="pageContent">
	<form action="${contextPath }/management/admin/feedback/create"
		method="post" class="required-validate pageForm"
		onsubmit="return validateCallback(this,dialogReloadNavTab)">
		<div class="pageFormContent" layoutH="85">
			<p>
				<label>用户ID：</label> <input type="text" name="userId" class="validate[maxSize[50]]" size="20" maxlength="50" />
			</p>
			<p><label>登录名：</label><input type="text" name="loginName" class="validate[maxSize[50]]" size="20" maxlength="50"/></p>
			<p><label>渠道号：</label><input type="text" name="channelId" class="validate[maxSize[20],custom[integer]]" size="20" maxlength="20"/></p>
			<p><label>产品号：</label><input type="text" name="productId" class="validate[maxSize[20]],custom[integer]" size="20" maxlength="20"/></p>
			<p><label>产品版本号：</label><input type="text" name="productVersion" class="validate[maxSize[10]]" size="20" maxlength="20"/></p>
			<p><label>手机型号：</label><input type="text" name="phoneModel" class="validate[maxSize[40]]" size="20" maxlength="40"/></p>
			<p><label>手机平台：</label><input type="text" name="platform" class="validate[maxSize[10]]" size="20" maxlength="20"/></p>
			<p><label>手机信息：</label><input type="text" name="phone" class="validate[maxSize[20]]" size="20" maxlength="20"></p>
			<p><label>手机信号类型：</label><input type="text" name="phoneType" class="validate[maxSize[20]]" size="20" maxlength="20"/></p>
			<p><label>反馈信息：</label><textarea name="msg" rows="5" cols="70"></textarea></p>
		</div>
		<div class="formBar">
			<ul>
				<li><div class="button">
						<div class="buttonContent">
							<button type="submit">确定</button>
						</div>
					</div></li>
				<li><div class="button">
						<div class="buttonContent">
							<button type="button" class="close">关闭</button>
						</div>
					</div></li>
			</ul>
		</div>
	</form>
</div>
