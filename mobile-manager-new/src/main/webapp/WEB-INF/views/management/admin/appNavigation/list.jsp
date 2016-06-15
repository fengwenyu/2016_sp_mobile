<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8" trimDirectiveWhitespaces="true"%>
<%@page import="java.util.Date"%>
<%@ include file="/WEB-INF/views/include.inc.jsp"%>

<dwz:paginationForm action="${contextPath }/management/appNavigation/list" page="${page }">
	<input type="hidden" name="keywords" value="${keywords}"/>
</dwz:paginationForm>

<form method="post" action="${contextPath }/management/appNavigation/list" onsubmit="return navTabSearch(this)">
	<div class="pageHeader">
		<div class="searchBar">
			<ul class="searchContent">
				<li>
					<label>导航名称：</label>
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
			<shiro:hasPermission name="AppNavigation:save">
				<li><a iconClass="book_add" target="dialog" rel="lookup2organization_add" mask="true" width="500" height="350" href="${contextPath }/management/appNavigation/create"><span>添加</span></a></li>
			</shiro:hasPermission>
			<shiro:hasPermission name="AppNavigation:edit">
				<li><a iconClass="book_edit" target="dialog" rel="lookup2organization_edit" mask="true" width="500" height="350" href="${contextPath }/management/appNavigation/update/{slt_uid}"><span>编辑</span></a></li>
			</shiro:hasPermission>
			<shiro:hasPermission name="AppNavigation:delete">
				<li><a iconClass="book_delete" target="selectedTodo" rel="ids" href="${contextPath }/management/appNavigation/delete" title="确认要删除?"><span>删除</span></a></li>
			</shiro:hasPermission>
		</ul>
	</div>
	
	<table class="table" layoutH="137" width="1150">
		<thead>
			<tr>
				<th width="22"><input type="checkbox" group="ids" class="checkboxCtrl"></th>			
				<th width="100">导航ID</th>
				<th width="150">导航名称</th>
				<th width="600">链接</th>
				<th width="100">显示类型</th>
				<th width="200">开始时间</th>
				<th width="200">结束时间</th>
				<th width="100" orderField="sort" class="${page.orderField eq 'sort' ? page.orderDirection : ''}">位置顺序</th>
				<th width="200" orderField=createTime class="${page.orderField eq 'createTime' ? page.orderDirection : ''}">创建时间</th>
			</tr>
		</thead>
		<tbody>
			<c:forEach var="item" items="${appNavigations}">
			<tr target="slt_uid" rel="${item.id}">
				<td><input name="ids" value="${item.id}" type="checkbox"></td>
				<td>${item.tabId}</td>
				<td>${item.navName}</td>				
				<td>${item.link}</td>
				<td>
				<c:choose>
				  <c:when test="${item.showType==1}">单列显示</c:when>
				  <c:when test="${item.showType==2}">双列显示</c:when>
				  <c:when test="${item.showType==3}">HTML页面</c:when>
				</c:choose>
				</td>
				<td><fmt:formatDate value="${item.startTime}" pattern="yyyy-MM-dd HH:mm:ss"/></td>
				<td><fmt:formatDate value="${item.endTime}" pattern="yyyy-MM-dd HH:mm:ss"/></td>
				<td>${item.sort}</td>
				<td><fmt:formatDate value="${item.createTime}" pattern="yyyy-MM-dd HH:mm:ss"/></td>
			</tr>			
			</c:forEach>
		</tbody>
	</table>
	<!-- 分页 -->
	<dwz:pagination page="${page }"/>
</div>