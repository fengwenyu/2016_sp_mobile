<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8"
	trimDirectiveWhitespaces="true"%>
<%@ include file="/WEB-INF/views/include.inc.jsp"%>
<div class="pageContent">
	<form action="${contextPath }/management/admin/feedback/update"
		method="post" class="required-validate pageForm"
		onsubmit="return validateCallback(this,dialogReloadNavTab)">
		<input type="hidden" name="id" value="${feedback.id }"/>
		<div class="pageFormContent" layoutH="58">
			<p>
				<label>用户ID：</label> <input type="text" name="userId" value="${feedback.userId }" class="validate[maxSize[50]]" size="20" maxlength="50" />
			</p>
			<p><label>登录名：</label><input type="text" name="loginName" value="${feedback.loginName }" class="validate[maxSize[50]]" size="20" maxlength="50"/></p>
			<p><label>渠道号：</label><input type="text" name="channelId" value="${feedback.channelId }" class="validate[maxSize[20],custom[integer]]" size="20" maxlength="20"/></p>
			<p><label>产品号：</label><input type="text" name="productId" value="${feedback.productId }" class="validate[maxSize[20]],custom[integer]" size="20" maxlength="20"/></p>
			<p><label>产品版本号：</label><input type="text" name="productVersion" value="${feedback.productVersion }" class="validate[maxSize[10]]" size="20" maxlength="20"/></p>
			<p><label>手机型号：</label><input type="text" name="phoneModel" value="${feedback.phoneModel }" class="validate[maxSize[40]]" size="20" maxlength="40"/></p>
			<p><label>手机平台：</label><input type="text" name="platform" value="${feedback.platform }" class="validate[maxSize[10]]" size="20" maxlength="20"/></p>
			<p><label>手机信息：</label><input type="text" name="phone" value="${feedback.phone }" class="validate[maxSize[20]]" size="20" maxlength="20"></p>
			<p><label>手机信号类型：</label><input type="text" name="phoneType" value="${feedback.phoneType }" class="validate[maxSize[20]]" size="20" maxlength="20"/></p>
			<p><label>反馈信息：</label><textarea name="msg" rows="5" cols="70">${feedback.msg }</textarea></p>
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
