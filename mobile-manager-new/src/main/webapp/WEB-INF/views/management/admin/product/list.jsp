<%@ page language="java" contentType="text/html; charset=UTF-8" trimDirectiveWhitespaces="true"
    pageEncoding="UTF-8"%>
<%@page import="java.util.Date"%>
<%@ include file="/WEB-INF/views/include.inc.jsp"%>

<c:set var="contextPath" value="${pageContext.request.contextPath}"/>

<dwz:paginationForm action="${contextPath }/management/manager/product/list" page="${page }">
	<input type="hidden" name="search_LIKE_productName" value="${param.search_LIKE_productName}"/>
	<input type="hidden" name="search_EQ_productNum" value="${param.search_EQ_productNum}"/>
</dwz:paginationForm>

<form method="post" action="${contextPath }/management/manager/product/list" onsubmit="return navTabSearch(this)">
	<div class="pageHeader">
		<div class="searchBar">
			<ul class="searchContent">
				<li>
					<label>产品名称：</label>
					<input type="text" name="search_LIKE_productName" value="${param.search_LIKE_productName}"/>			
				</li>
				<li>
					<label>产品编号：</label>
					<input type="text" name="search_EQ_productNum" value="${param.search_EQ_productNum}"/>			
				</li>
				   <li><div class="button"><div class="buttonContent"><button type="submit">搜索</button></div></div></li>
			</ul>
		</div>
	</div>
</form>

<div class="pageContent">

	<div class="panelBar">
		<ul class="toolBar">
			<shiro:hasPermission name="product:save">
				<li><a iconClass="database_add" target="dialog" mask="true" width="530" height="280" href="${contextPath }/management/manager/product/create"><span>添加产品</span></a></li>
			</shiro:hasPermission>
			<shiro:hasPermission name="product:edit">
				<li><a iconClass="database_edit" target="dialog" mask="true" width="530" height="280" href="${contextPath }/management/manager/product/update/{slt_uid}"><span>编辑产品</span></a></li>
			</shiro:hasPermission>
			<shiro:hasPermission name="product:delete">
				<li><a iconClass="database_delete" target="selectedTodo" rel="ids" href="${contextPath }/management/manager/product/delete" title="确认要删除选定的产品吗?"><span>删除产品</span></a></li>
			</shiro:hasPermission>
		</ul>
	</div>
	
	<table class="table" layoutH="112" width="100%">
		<thead>
			<tr>
				<th width="22"><input type="checkbox" group="ids" class="checkboxCtrl"></th>
				<th width="200" orderField="productNum" class="${page.orderField eq 'productNum' ? page.orderDirection : ''}">产品编号</th>
				<th width="200">产品名称</th>
				<th width="200">平台</th>
				<th width="400" orderField="createTime" class="${page.orderField eq 'createTime' ? page.orderDirection : ''}">创建时间</th>
			</tr>
		</thead>
		<tbody>
			<c:forEach var="item" items="${products}">
			<tr target="slt_uid" rel="${item.id}">
				<td><input name="ids" value="${item.id}" type="checkbox"></td>
				<td>${item.productNum}</td>
				<td>${item.productName}</td>
				<td>${item.platForm}</td>
				<td><fmt:formatDate value="${item.createTime}" pattern="yyyy-MM-dd HH:mm:ss"/></td>
			</tr>
			</c:forEach>
		</tbody>
	</table>
	<!-- 分页 -->
	<dwz:pagination page="${page }"/>
</div>