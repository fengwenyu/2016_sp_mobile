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

<dwz:paginationForm action="${contextPath }/management/maintain/staticActivity/list" page="${page }">
	<input type="hidden" name="keywords" value="${keywords}"/>
</dwz:paginationForm>

<form method="post" action="${contextPath }/management/maintain/staticActivity/list" onsubmit="return navTabSearch(this)">
	<div class="pageHeader">
		<div class="searchBar">
			<ul class="searchContent">
				<li>
					<label> 产品名称：</label>
					<select name="search_EQ_app">
						<option value="">请选择</option>
						<option value="AOLAI">奥莱</option>
						<option value="SHANGPIN">尚品</option>
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
			<shiro:hasPermission name="StaticActivity:save">
				<li><a iconClass="user_add" target="dialog" rel="lookup2organization_add" mask="true" width="530" height="680" href="${contextPath }/management/maintain/staticActivity/create"><span>添加</span></a></li>
			</shiro:hasPermission>
			<shiro:hasPermission name="StaticActivity:edit">
				<li><a iconClass="user_edit" target="dialog" rel="lookup2organization_edit" mask="true" width="530" height="680" href="${contextPath }/management/maintain/staticActivity/update/{slt_id}"><span>编辑</span></a></li>
			</shiro:hasPermission>
			<shiro:hasPermission name="StaticActivity:delete">
				<li><a iconClass="user_delete" target="selectedTodo" rel="ids" href="${contextPath }/management/maintain/staticActivity/delete" title="确认要删除?"><span>删除</span></a></li>
			</shiro:hasPermission>
		</ul>
	</div>
	
	<table class="table" layoutH="137" width="100%">
		<thead>
			<tr>
				<th width="22"><input type="checkbox" group="ids" class="checkboxCtrl"></th>			
				<th width="200" align="center">静态链接</th>
				<th width="150" align="center">开始时间</th>
				<th width="150" align="center">结束时间</th>
			
				<th width="60" align="center">是否显示</th>
				<th width="160"align="center">图片</th>
				<th width="200"align="center">描述</th>
				<th width="100"align="center">标题</th>
				<th width="60"align="center">所属产品</th>
				<th orderField="createTime" class="${page.orderField eq 'createTime' ? page.orderDirection : ''}"align="center">创建时间</th>
			</tr>
		</thead>
		<tbody>
			<c:forEach var="item" items="${staticActivitys}">
			<tr target="slt_id" rel="${item.id}">
				<td><input name="ids" value="${item.id}" type="checkbox"></td>
				<td>${item.getUrl}</td>
				<td>${item.startTime}</td>
				<td>${item.endTime}</td>
				<c:choose>
					<c:when test="${item.display.value == 0}"><td>不显示</td></c:when>
					<c:when test="${item.display.value == 1}"><td>显示</td></c:when>
					<c:otherwise><td></td></c:otherwise>
				</c:choose>
				<td class="imgDiv22"><img src="${item.imgUrl}"></td>
				<td>${item.description}</td>
				<td>${item.title}</td>
				<c:choose>
					<c:when test="${item.app.value == '0'}"><td>奥莱</td></c:when>
					<c:when test="${item.app.value == '1'}"><td>尚品</td></c:when>
					<c:otherwise><td></td></c:otherwise>
				</c:choose>
				<td><fmt:formatDate value="${item.createTime}" pattern="yyyy-MM-dd HH:mm:ss"/></td>
			</tr>			
			</c:forEach>
		</tbody>
	</table>
	<!-- 分页 -->
	<dwz:pagination page="${page}"/>
</div>