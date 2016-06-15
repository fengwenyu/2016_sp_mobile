<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8" trimDirectiveWhitespaces="true"%>
<%@ include file="/WEB-INF/views/include.inc.jsp"%>
<script type="text/javascript">



</script>
<div class="pageContent">
<form method="post" action="${contextPath }/management/maintain/find/setSort" class="required-validate pageForm" onsubmit="return validateCallback(this, dialogReloadNavTab);">
	<div class="pageFormContent" layoutH="58">  
			<input id="id" name="id" type="hidden" value="${id }"/>
			<p>
				<label style="width:80px">位置</label>
				<input  id="sortBy" name="sortBy"  type="text" class="validate[required,minSize[1],maxSize[100]] required"/>
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