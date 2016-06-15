<%@ page language="java" contentType="text/html; charset=UTF-8" trimDirectiveWhitespaces="true"
    pageEncoding="UTF-8"%>
<%@page import="java.util.Date"%>
<%@ include file="/WEB-INF/views/include.inc.jsp"%>
<style>
.grid .gridTbody .myTd div {
    display: block;
    height: 31px;
    line-height: 21px;
    overflow: hidden;
    white-space: nowrap;
}
</style>
<c:set var="contextPath" value="${pageContext.request.contextPath}"/>

<dwz:paginationForm action="${contextPath }/management/manager/online/list" page="${page }">

</dwz:paginationForm>

<div class="pageContent">
	<div class="panelBar">
		<ul class="toolBar">
		    <shiro:hasPermission name="OnlineManage:save">
				<li><a iconClass="database_add" target="dialog" rel="uploadProduct" mask="true" width="530" height="480" href="${contextPath }/management/manager/online/create"><span>上传产品</span></a></li>
			</shiro:hasPermission>
			<shiro:hasPermission name="OnlineManage:delete">
				<li><a iconClass="database_delete" target="selectedTodo" rel="ids" href="${contextPath }/management/manager/online/delete" title="确认要删除选定的产品吗?"><span>删除产品</span></a></li>
			</shiro:hasPermission>
		</ul>
	</div>
	
	<table class="table" layoutH="75" width="100%">
		<thead>
			<tr>
				<th width="22"><input type="checkbox" group="ids" class="checkboxCtrl"></th>
				<th width="100">产品</th>
				<th width="100">渠道</th>
				<th width="100">强制升级最大版本号</th>
				<th width="100">产品版本号</th>
				<th width="100">文件名</th>
				<th width="100">下载地址</th>
				<th width="100">提示信息</th>
				<th width="200" orderField="createTime" class="${page.orderField eq 'createTime' ? page.orderDirection : ''}">操作时间</th>
			</tr>
		</thead>
		<tbody>
			<c:forEach var="item" items="${onlinManages}">
			<tr target="slt_uid" rel="${item.online.id}">
				<td><input name="ids" value="${item.online.id}" type="checkbox"></td>
				<td> ${item.productName}</td>
				<td> ${item.channelName}</td>
				<td> ${item.online.versionForceMax}</td>
				<td> ${item.online.versionLatest}</td>
			    <td> ${item.online.fileName}</td>
				<td><a href=${item.online.downloadPath} style="color:#4574a0; font-weight:bold;">${item.online.downloadPath}</a></td>
				<td class="myTd"><textarea cols=42" rows="2" readonly="true" wrap="off" background="#ff">${item.online.prompt}</textarea></td>
				<td><fmt:formatDate value="${item.online.createTime}" pattern="yyyy-MM-dd HH:mm:ss"/></td>
			</tr>
			</c:forEach>
		</tbody>
	</table>
	<!-- 分页 -->
	<dwz:pagination page="${page }"/>
</div>