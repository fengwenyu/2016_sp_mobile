<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8" trimDirectiveWhitespaces="true"%>
<%@ include file="/WEB-INF/views/include.inc.jsp"%>
<a id="refreshJbsxBox2dictionaryTree" rel="jbsxBox2dictionaryTree" target="ajax" href="${contextPath}/management/security/dictionary/tree" style="display:none;"></a>
<dwz:paginationForm action="${contextPath}/management/security/dictionary/list/${parentDictionaryId}" page="${page }" onsubmit="return divSearch(this, 'jbsxBox2dictionaryList');">
	<input type="hidden" name="keywords" value="${keywords}"/>
</dwz:paginationForm>

<form method="post" action="${contextPath }/management/security/dictionary/list/${parentDictionaryId}" onsubmit="return divSearch(this, 'jbsxBox2dictionaryList');">
	<div class="pageHeader">
		<div class="searchBar">
			<ul class="searchContent">
				<li>
					<label>字典名称：</label>
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
		<shiro:hasPermission name="Dictionary:save">
			<li><a iconClass="book_add" target="dialog" width="540" height="500" mask="true" href="${contextPath }/management/security/dictionary/create/${parentDictionaryId}" title="添加字典"><span>添加字典</span></a></li>
		</shiro:hasPermission>
		<shiro:hasPermission name="Dictionary:edit">
			<li><a iconClass="book_edit" target="dialog" rel="lookupParent2dictionary_edit" width="540" height="530" mask="true" href="${contextPath }/management/security/dictionary/update/{slt_uid}" title="编辑字典"><span>编辑字典</span></a></li>
		</shiro:hasPermission>
		<shiro:hasPermission name="Dictionary:delete">
			<li><a iconClass="book_delete" target="ajaxTodo" callback="dialogReloadRel2Dictionary" href="${contextPath }/management/security/dictionary/delete/{slt_uid}" title="确认要删除该字典?"><span>删除字典</span></a></li>
		</shiro:hasPermission>
		</ul>
	</div>
	<table class="table" layoutH="142" width="100%" rel="jbsxBox2dictionaryList" >
		<thead>
			<tr>
				<th width="150">字典名称</th>
				<th width="150">字典代码</th>
				<th width="150">字典描述</th>
				<th width="60" orderField="priority" class="${page.orderField eq 'priority' ? page.orderDirection : ''}">优先级</th>
				<th width="150">父字典</th>
			</tr>
		</thead>
		<tbody>
			<c:forEach var="item" items="${dictionaries}">
			<tr target="slt_uid" rel="${item.id}">
				<td><a href="${contextPath}/management/security/dictionary/list/${item.id}" target="ajax" rel="jbsxBox2dictionaryList">${item.name}</a></td>
				<td>${item.code}</td>
				<td>${item.description}</td>
				<td>${item.priority}</td>
				<td>${item.parent.name}</td>
			</tr>
			</c:forEach>
		</tbody>
	</table>

	<!-- 分页 -->
	<dwz:pagination page="${page }" rel="jbsxBox2dictionaryList" onchange="navTabPageBreak({numPerPage:this.value}, 'jbsxBox2dictionaryList')"/>
</div>