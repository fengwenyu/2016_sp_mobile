<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8" trimDirectiveWhitespaces="true"%>
<%@page import="java.util.Date"%>
<%@ include file="/WEB-INF/views/include.inc.jsp"%>

<dwz:paginationForm action="${contextPath }/management/admin/app/list" page="${page }">
	<input type="hidden" name="keywords" value="${keywords}"/>
</dwz:paginationForm>

<form method="post" action="${contextPath }/management/admin/app/list" onsubmit="return navTabSearch(this)">
	<div class="pageHeader">
		<div class="searchBar">
			<ul class="searchContent">
				<li>
					<label>名称/缩写：</label>
					<input type="text" name="keywords" value="${keywords}"/>
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
			<shiro:hasPermission name="App:save">
				<li><a iconClass="book_add" target="dialog" mask="true" width="500" height="350" href="${contextPath }/management/admin/app/create"><span>添加app</span></a></li>
			</shiro:hasPermission>
			<shiro:hasPermission name="App:edit">
				<li><a iconClass="book_edit" target="dialog" mask="true" width="500" height="350" href="${contextPath }/management/admin/app/update/{slt_uid}"><span>编辑app</span></a></li>
			</shiro:hasPermission>
			<shiro:hasPermission name="App:delete">
				<li><a iconClass="book_delete" target="selectedTodo" rel="ids" href="${contextPath }/management/admin/app/delete" title="确认要删除?"><span>删除app</span></a></li>
			</shiro:hasPermission>
		</ul>
	</div>
	
	<table class="table" layoutH="137" width="1600">
		<thead>
			<tr>
				<th width="22"><input type="checkbox" group="ids" class="checkboxCtrl"></th>			
				<th width="100">名称</th>
				<th width="100">编号</th>
				<th width="100">缩写</th>
				<th width="100">平台</th>
				<th width="150" orderField=createTime class="${page.orderField eq 'createTime' ? page.orderDirection : ''}">创建时间</th>
			</tr>
		</thead>
		<tbody>
			<c:forEach var="item" items="${apps}">
			<tr target="slt_uid" rel="${item.id}">
				<td><input name="ids" value="${item.id}" type="checkbox"></td>
				<td>${item.name}</td>
				<td>${item.num}</td>				
				<td>${item.code}</td>
				<td>${item.platform}</td>
				<td><fmt:formatDate value="${item.createTime}" pattern="yyyy-MM-dd HH:mm:ss"/></td>
			</tr>			
			</c:forEach>
		</tbody>
	</table>
	<!-- 分页 -->
	<dwz:pagination page="${page }"/>
</div>