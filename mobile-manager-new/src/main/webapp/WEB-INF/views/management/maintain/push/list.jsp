<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8" trimDirectiveWhitespaces="true"%>
<%@page import="java.util.Date"%>
<%@ include file="/WEB-INF/views/include.inc.jsp"%>
<script type="text/javascript">

</script>
<dwz:paginationForm action="${contextPath }/management/maintain/push/list" page="${page }">
	<input type="hidden" name="keywords" value="${keywords}"/>
</dwz:paginationForm>

<form method="post" action="${contextPath }/management/maintain/push/list" onsubmit="return navTabSearch(this)">
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
			<shiro:hasPermission name="Push:save">
				<li><a iconClass="user_add" target="dialog" rel="lookup2organization_add" mask="true" width="530" height="680" href="${contextPath }/management/maintain/push/create"><span>添加消息</span></a></li>
			</shiro:hasPermission>
			<shiro:hasPermission name="Push:edit">
				<li><a iconClass="user_edit" target="dialog" rel="lookup2organization_edit" mask="true" width="530" height="680" href="${contextPath }/management/maintain/push/update/{slt_id}"><span>编辑消息</span></a></li>
			</shiro:hasPermission>
			<shiro:hasPermission name="Push:delete">
				<li><a iconClass="user_delete" target="selectedTodo" rel="ids" href="${contextPath }/management/maintain/push/delete" title="确认要删除?"><span>删除消息</span></a></li>
			</shiro:hasPermission>
		</ul>
	</div>
	
	<table class="table" layoutH="137" width="100%">
		<thead>
			<tr>
				<th width="22"><input type="checkbox" group="ids" class="checkboxCtrl"></th>			
				<th width="100">平台名称</th>
				<th width="100">产品编号</th>
				<th width="100">渠道编号</th>
				<th width="100" hidden="true">事件类型</th>
				<th width="100" hidden="true">事件参数</th>
				<th width="100" hidden="true">详细参数</th>
				<th width="100">用户名</th>
				<th width="200">消息标题</th>
				<th width="200">公告内容</th>
				<th width="100">发送状态</th>
				<th orderField="createTime" class="${page.orderField eq 'createTime' ? page.orderDirection : ''}">创建时间</th>
			</tr>
		</thead>
		<tbody>
			<c:forEach var="item" items="${pushs}">
			<tr target="slt_id" rel="${item.id}">
				<td><input name="ids" value="${item.id}" type="checkbox"></td>
				<c:choose>
					<c:when test="${item.platformType == 0}"><td>android</td></c:when>
					<c:when test="${item.platformType == 1}"><td>ios</td></c:when>
					<c:otherwise><td>其他平台</td></c:otherwise>
				</c:choose>
				<td>${item.productCode}</td>
				<td>${item.channelCode}</td>
				<td hidden="true">${item.actionType}</td>
				<td hidden="true">${item.actionParam}</td>
				<td hidden="true">${item.actionDetailParam}</td>
				<td>${item.username}</td>
				<td>${item.title}</td>
				<td>${item.notice}</td>
				<td>${item.status == 0 ? "未发送":"已发送"}</td>
				<td><fmt:formatDate value="${item.createTime}" pattern="yyyy-MM-dd HH:mm:ss"/></td>
			</tr>			
			</c:forEach>
		</tbody>
	</table>
	<!-- 分页 -->
	<dwz:pagination page="${page}"/>
</div>