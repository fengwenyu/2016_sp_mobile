<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8" trimDirectiveWhitespaces="true"%>
<%@page import="java.util.Date"%>
<%@ include file="/WEB-INF/views/include.inc.jsp"%>

<dwz:paginationForm action="${contextPath }/management/hotBrand/list" page="${page }">
	<input type="hidden" name="keywords" value="${keywords}"/>
</dwz:paginationForm>

<form method="post" action="${contextPath }/management/hotBrand/list" onsubmit="return navTabSearch(this)">
	<div class="pageHeader">
		<div class="searchBar">
			<ul class="searchContent">
				<li>
					<label>品牌名称：</label>
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
			<shiro:hasPermission name="HotBrand:save">
				<li><a iconClass="book_add" target="dialog" rel="lookup2organization_add" mask="true" width="500" height="350" href="${contextPath }/management/hotBrand/create"><span>添加品牌</span></a></li>
			</shiro:hasPermission>
			<shiro:hasPermission name="HotBrand:edit">
				<li><a iconClass="book_edit" target="dialog" rel="lookup2organization_edit" mask="true" width="500" height="350" href="${contextPath }/management/hotBrand/update/{slt_uid}"><span>编辑品牌</span></a></li>
			</shiro:hasPermission>
			<shiro:hasPermission name="HotBrand:delete">
				<li><a iconClass="book_delete" target="selectedTodo" rel="ids" href="${contextPath }/management/hotBrand/delete" title="确认要删除?"><span>删除品牌</span></a></li>
			</shiro:hasPermission>
		</ul>
	</div>
	
	<table class="table" layoutH="137" width="1600">
		<thead>
			<tr>
				<th width="22"><input type="checkbox" group="ids" class="checkboxCtrl"></th>			
				<th width="100">品牌ID</th>
				<th width="100">品牌名称</th>
				<th width="50" orderField="sort" class="${page.orderField eq 'sort' ? page.orderDirection : ''}">顺序</th>
				<th width="480">图片URL</th>
				<th width="80">图片高度</th>
				<th width="80">图片宽度</th>
				<th width="480">头图链接</th>
				<th width="80">头图宽度</th>
				<th width="80">头图高度</th>
				<th width="150" orderField=createTime class="${page.orderField eq 'createTime' ? page.orderDirection : ''}">创建时间</th>
			</tr>
		</thead>
		<tbody>
			<c:forEach var="item" items="${hotBrands}">
			<tr target="slt_uid" rel="${item.id}">
				<td><input name="ids" value="${item.id}" type="checkbox"></td>
				<td>${item.brandId}</td>
				<td>${item.brandName}</td>				
				<td>${item.sort}</td>
				<td>${item.imgUrl}</td>
				<td>${item.imgHeight}</td>
				<td>${item.imgWidth}</td>
				<td>${item.topImgUrl}</td>
				<td>${item.topImgHeight}</td>
				<td>${item.topImgWidth}</td>
				<td><fmt:formatDate value="${item.createTime}" pattern="yyyy-MM-dd HH:mm:ss"/></td>
			</tr>			
			</c:forEach>
		</tbody>
	</table>
	<!-- 分页 -->
	<dwz:pagination page="${page }"/>
</div>