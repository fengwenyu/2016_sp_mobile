<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8" trimDirectiveWhitespaces="true"%>
<%@page import="java.util.Date"%>
<%@ include file="/WEB-INF/views/include.inc.jsp"%>
<style>
.grid .gridTbody .imgDiv22 div {
    display: block;
    height: 100px;
    line-height: 21px;
    overflow: hidden;
    white-space: nowrap;
}

</style>
<script type="text/javascript">
(function(){
	$("selct").change(function(){
		var value = $('select option').val();
		console.log("selct:" + value);
		if("${param.search_EQ_platformType == " + value + "}"){
			$('select option[value = ' + value + ']').attr("selected",true);
		}
	});
	
})(jQuery);
</script>
<dwz:paginationForm action="${contextPath }/management/maintain/find/list" page="${page }">
	<input type="hidden" name="keywords" value="${keywords}"/>
</dwz:paginationForm>

<form method="post" action="${contextPath }/management/maintain/find/list" onsubmit="return navTabSearch(this)">
	<div class="pageHeader">
		<div class="searchBar">
			<ul class="searchContent">
				<li>
					<label>平台名称：</label>
					<select name="search_EQ_platformType">
						<option value="">请选择</option>
						<option value="0">android</option>
						<option value="1">ios</option>
					</select>
				</li>
				<li>
					<label>发送状态：</label>
					<select name="search_EQ_status">
						<option value="">请选择</option>
						<option value="0">未发送</option>
						<option value="1">已发送</option>
					</select>
				</li>
			</ul>
			<div class="subBar">
				<ul>						
					<li><div class="button"><div class="buttonContent"><button type="submit">搜索</button></div></div></li>
				</ul>
			</div>
		</div>
	</div>
</form>

<div class="pageContent">

	<div class="panelBar">
		<ul class="toolBar">
			<shiro:hasPermission name="Find:save">
				<li><a iconClass="user_add" target="dialog" rel="lookup2organization_add" mask="true" width="900" height="500" href="${contextPath }/management/maintain/find/activityList" ><span>添加活动</span></a></li>
			</shiro:hasPermission>
			<shiro:hasPermission name="Find:save">
				<li><a iconClass="user_add" target="dialog" rel="lookup2organization_addImageText" mask="true" width="530" height="680" href="${contextPath }/management/maintain/find/preCreate" ><span>添加图文活动</span></a></li>
			</shiro:hasPermission>
			<shiro:hasPermission name="Find:edit">
				<li><a iconClass="user_edit" target="dialog" rel="lookup2organization_edit" mask="true" width="530" height="680" href="${contextPath }/management/maintain/find/update/{slt_uid}"><span>编辑活动</span></a></li>
			</shiro:hasPermission>
			<shiro:hasPermission name="Find:delete">
				<li><a iconClass="user_delete" target="selectedTodo" rel="ids" href="${contextPath }/management/maintain/find/delete" title="确认要删除?"><span>删除活动</span></a></li>
			</shiro:hasPermission>
				<shiro:hasPermission name="Find:edit">
				<li><a iconClass="user_edit" target="dialog" rel="lookup2organization_editqq" mask="true" width="430" height="180"  href="${contextPath }/management/maintain/find/preSetSort/{slt_uid}"><span>编辑活动排序</span></a></li>
			</shiro:hasPermission>
		</ul>
	</div>
	
	<table class="table" layoutH="137" width="100%">
		<thead>
			<tr>
				<th width="22"><input type="checkbox" group="ids" class="checkboxCtrl"></th>			
				<th width="50">类型</th>
				<th width="100">专题标题</th>
				<th width="100">活动ID</th>
				<th width="150">移动端开始时间</th>
				<th width="150">移动端结束时间</th>
				<th width="150">移动端预热时间</th>
				<th width="150">图片</th>
				<th width="100">位置</th>
				<th width="200">描述</th>
				<th width="100">状态</th>
				<th width="200">专题副标题</th>
			</tr>
		</thead>
		<tbody>
			<c:forEach var="item" items="${finds}">
			<tr target="slt_uid" rel="${item.id}">
				<td><input name="ids" value="${item.id}" type="checkbox"></td>
				<c:choose>
					<c:when test="${item.type == 'ACTIVITY'}"><td>活动</td></c:when>
					<c:when test="${item.type == 'IMAGETEXT'}"><td>图文</td></c:when>
					<c:otherwise><td>其他平台</td></c:otherwise>
				</c:choose>
				<td>${item.title}</td>
				<td>${item.activityId}</td>
				<td><fmt:formatDate value="${item.showStartTime}" pattern="yyyy-MM-dd HH:mm:ss"/></td>
				<td><fmt:formatDate value="${item.showEndTime}" pattern="yyyy-MM-dd HH:mm:ss"/></td>
				<td><fmt:formatDate value="${item.mobilePreTime}" pattern="yyyy-MM-dd HH:mm:ss"/></td>
				<td class="imgDiv22"><img src="${item.imgUrl}"></td>
				<td>${item.sortBy}</td>
				<td>${item.description}</td>
				<c:choose>
					<c:when test="${item.status == '1'}"><td>开启</td></c:when>
					<c:when test="${item.type == 'IMAGETEXT'}"><td></td></c:when>
					<c:otherwise><td>未开启</td></c:otherwise>
				</c:choose>
				<td>${item.subTitle}</td>
			</tr>			
			</c:forEach>
		</tbody>
	</table>
	<!-- 分页 -->
	<dwz:pagination page="${page}"/>
</div>