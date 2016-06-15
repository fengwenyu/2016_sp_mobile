<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8" trimDirectiveWhitespaces="true"%>
<%@page import="java.util.Date"%>
<%@ include file="/WEB-INF/views/include.inc.jsp"%>
<style>
.grid .gridTbody .imgDiv22 div {
    display: block;
    height: 61px;
    line-height: 21px;
    overflow: hidden;
    white-space: nowrap;
}

</style>
<dwz:paginationForm action="${contextPath }/management/admin/fashionInfo/list" page="${page }">
	<input type="hidden" name="search_LIKE_title" value="${param.search_LIKE_title }"/>
	<input type="hidden" name="search_EQ_releaseTime" value="${param.search_EQ_releaseTime }"/>
</dwz:paginationForm>

<form method="post" action="${contextPath}/management/admin/fashionInfo/list" onsubmit="return navTabSearch(this)">
	<div class="pageHeader">
		<div class="searchBar">
			<ul class="searchContent">
				<li>
					<label style="width: 100px;">标题：</label>
					<input type="text" name="search_LIKE_title" value="${param.search_LIKE_title }"/>
				</li>	
				<li>
					<label style="width: 100px;">发布时间：</label>
					<input type="text" name="search_EQ_releaseTime" value="${param.search_EQ_releaseTime }" class="date" readonly="readonly" style="float:left;"/>
					<a class="inputDateButton" href="javascript:;" style="float:left;">选择</a>
				</li>
				<li>
					<div class="button"><div class="buttonContent"><button type="submit">搜索</button></div></div>
				</li>
			</ul>
		</div>
	</div>
</form>

<div class="pageContent">

	<div class="panelBar">
		<ul class="toolBar">
			<shiro:hasPermission name="FashionInfo:save">
				<li><a href="${contextPath}/management/admin/fashionInfo/create" iconClass="application_form_add" target="dialog" mask="true" width="600" height="550"><span>添加资讯信息</span></a></li>
				<li class="line">line</li>
			</shiro:hasPermission>
			<shiro:hasPermission name="FashionInfo:edit">
				<li><a href="${contextPath}/management/admin/fashionInfo/update/{slt_uid}" iconClass="application_form_edit" target="dialog" mask="true" width="600" height="550"><span>编辑资讯信息</span></a></li> 
				<li class="line">line</li>
			</shiro:hasPermission>
			<shiro:hasPermission name="FashionInfo:delete">
				<li><a target="selectedTodo" rel="ids" href="${contextPath}/management/admin/fashionInfo/delete" title="确认要删除吗?" iconClass="application_form_delete" mask="true"><span>删除资讯信息</span></a></li>
			</shiro:hasPermission>
		</ul>
	</div>
	
	<table class="table" layoutH="110" width="">
		<thead>
			<tr>
				<th width="80" align="right">全/反 选<input type="checkbox" group="ids" class="checkboxCtrl"></th>
				<th width="100" align="center">封面图</th>
				<th width="420" align="center">标题</th>
				<th width="60" align="center">位置顺序</th>
				<th width="420" orderField="createTime" class="${page.orderField eq 'createTime' ? page.orderDirection : ''}" align="center">发布时间</th>
			</tr>
		</thead>
		<tbody>
			<c:forEach var="item" items="${apiFashionInfo}">
			<tr target="slt_uid" rel="${item.id}" >
				<td align="right"><input name="ids" value="${item.id}" type="checkbox"></td>
				<td class="imgDiv22"><img src="${item.imgUrl}"></td>
				<td>${item.title}</td>
				<td>${item.sort}</td>
				<td><fmt:formatDate value="${item.releaseTime}" pattern="yyyy-MM-dd HH:mm:ss"/></td>
			</tr>			
			</c:forEach>
		</tbody>
	</table>
	<!-- 分页 -->
	<dwz:pagination page="${page }"/>
</div>