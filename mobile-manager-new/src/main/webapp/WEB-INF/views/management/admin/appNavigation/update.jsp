<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8"
	trimDirectiveWhitespaces="true"%>
<%@ include file="/WEB-INF/views/include.inc.jsp"%>
<script type="text/javascript" src="${contextPath}/styles/manager/js/admin/appNavigation/update.js">
</script>
<script type="text/javascript">
$(function(){
	var showType=$("#showType_hidden").val();
	$("#showType").val(showType);
	if("${appNavigation.tabId }"==""){
		$("#tabP").hide();
	}
	if("${appNavigation.link }"==""){
		$("#linkP").hide();
	}
	if("${appNavigation.startTime }"==""){
		$("#startTimeP").hide();
	}
	if("${appNavigation.endTime }"==""){
		$("#endTimeP").hide();
	}	
})
</script>
<div class="pageContent">
	<form id="appNavigationAdd"
		action="${contextPath }/management/appNavigation/update" method="post"
		class="required-validate pageForm"
		onsubmit="return validateCallback(this, dialogReloadNavTab);">
		<input type="hidden" name="id" value="${appNavigation.id}"/>
		<div class="pageFormContent" layoutH="58">
			<p>
				<label>导航名称</label> <input id="navName" name="navName" value="${appNavigation.navName }"
					class="validate[required,maxSize[50]] required" size="20"
					maxlength="50">
			</p>
			<p>
				<label>展示类型</label> 
				<input type="hidden" id="showType_hidden" value="${appNavigation.showType }">
				<select id="showType" name="showType">
				<option value="">---请选择---</option>
				<option value=1>单列显示</option>
				<option value=2>双列显示</option>
				<option value=3>HTML页面</option>
				</select>
			</p>
			<p id="tabP">
			   <label>tabid</label>
			   <input id="tabId" name="tabId" class="validate[required,maxSize[50]] required" size="20"
					maxlength="50" value="${appNavigation.tabId }">
			</p>
			<p id="linkP">
			   <label>链接</label>
			   <input id="link" name="link" class="validate[required,maxSize[50]] required" size="20"
					maxlength="50" value="${appNavigation.link }">
			</p>
			<p id="startTimeP">
			   <label>开始时间</label>
			   <input id="startTime" name="startTime" class="date" dateFmt="yyyy-MM-dd HH:mm:ss" readonly="readonly" size="20"
					maxlength="50" value="<fmt:formatDate value='${appNavigation.startTime }' pattern='yyyy-MM-dd HH:mm:ss'/>">
			</p>
			<p id="endTimeP">
			   <label>结束时间</label>
			   <input id="endTime" name="endTime" class="date" dateFmt="yyyy-MM-dd HH:mm:ss" readonly="readonly" size="20"
					maxlength="50" value="<fmt:formatDate value='${appNavigation.endTime }' pattern='yyyy-MM-dd HH:mm:ss'/>">
			</p>
		</div>
		<div class="formBar">
			<ul>
				<li>
					<div class="button">
						<div class="buttonContent">
							<button type="submit">确定</button>
						</div>
					</div>
				</li>
				<li>
					<div class="button">
						<div class="buttonContent">
							<button type="button" class="close">关闭</button>
						</div>
					</div>
				</li>
		</div>
	</form>
</div>