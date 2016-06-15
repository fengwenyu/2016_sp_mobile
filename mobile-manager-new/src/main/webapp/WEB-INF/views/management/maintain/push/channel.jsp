<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8" trimDirectiveWhitespaces="true"%>
<%@page import="java.util.Date"%>
<%@ include file="/WEB-INF/views/include.inc.jsp"%>

<dwz:paginationForm action="${contextPath }/management/maintain/push/lookup2channel" page="${page }">
	<input type="hidden" name="search_LIKE_channelName" value="${param.search_LIKE_channelName }"/>
	<input type="hidden" name="search_EQ_channelNum" value="${param.search_EQ_channelNum }"/>
</dwz:paginationForm>

<form method="post" action="${contextPath}/management/maintain/push/lookup2channel" onsubmit="return dwzSearch(this, 'dialog');">
	<div class="pageHeader">
		<div class="searchBar">
			<ul class="searchContent">
				<li>
					<label style="width: 100px;">渠道名称：</label>
					<input type="text" name="search_LIKE_channelName" value="${param.search_LIKE_channelName }"/>
				</li>	
				<li>
					<label style="width: 100px;">渠道编号：</label>
					<input type="text" name="search_EQ_channelNum" value="${param.search_EQ_channelNum }"/>
				</li>
				<li>
					<div class="button"><div class="buttonContent"><button type="submit">搜索</button></div></div>
				</li>
			</ul>
		</div>
	</div>
</form>

<div class="pageContent">
	<table class="table" layoutH="162" width="100%">
		<thead>
			<tr>
				<th width="30" align="right">全/反 选<input type="checkbox" group="ids" class="checkboxCtrl"></th>
				<th width="100">渠道编号</th>
				<th width="100">渠道名称</th>
				<th width="100">邀请码</th>
				<th width="100">查找带回</th>
			</tr>
		</thead>
		<tbody>
			<c:forEach var="item" items="${channels}">
			<tr target="slt_uid" rel="${item.id}">
				<td align="right"><input name="ids" value="${item.id}" type="checkbox"></td>
				<td>${item.channelNum}</td>
				<td>${item.channelName}</td>
				<td>${item.invitationCode}</td>
				<td><a class="btnSelect" href="javascript:$.bringBack({channelCode:'${item.channelNum}'})" title="查找带回">选择</a></td>
			</tr>			
			</c:forEach>
		</tbody>
	</table>
	<!-- 分页 -->
	<dwz:pagination page="${page }"/>
</div>