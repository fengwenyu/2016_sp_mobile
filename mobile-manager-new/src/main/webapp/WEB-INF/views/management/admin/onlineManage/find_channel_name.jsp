<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8" trimDirectiveWhitespaces="true"%>
<%@ include file="/WEB-INF/views/include.inc.jsp"%>
<dwz:paginationForm action="${contextPath }/management/base/channel/list" page="${page }">
	<input type="hidden" name="search_LIKE_channelName" value="${param.search_LIKE_channelName }"/>
</dwz:paginationForm>
<form method="post" action="${contextPath}/management/base/channel/list" onsubmit="return navTabSearch(this)">
	<div class="pageHeader">
		<div class="searchBar">
			<ul class="searchContent">
				<li>
					<label>渠道名称：</label>
					<input type="text" name="search_LIKE_channelName" value="${param.search_LIKE_channelName }"/>
				</li>	
				<li>
					<div class="button"><div class="buttonContent"><button type="submit">搜索</button></div></div>
				</li>
			</ul>
		</div>
	</div>
</form>

	<table class="table" layoutH="110" width="100%">
		<thead>
			<tr>
				<th width="30" align="right">全/反 选<input type="checkbox" group="ids" class="checkboxCtrl"></th>
				<th width="100">渠道编号</th>
				<th width="100">渠道名称</th>
				<th width="100">邀请码</th>
				<th width="130" orderField="createTime" class="${page.orderField eq 'createTime' ? page.orderDirection : ''}">创建时间</th>
				<th width="80">查找带回</th>
			</tr>
		</thead>
		<tbody>
			<c:forEach var="item" items="${channel}">
			<tr target="slt_uid" rel="${item.id}">
				<td align="right"><input name="ids" value="${item.id}" type="checkbox"></td>
				<td>${item.channelNum}</td>
				<td>${item.channelName}</td>
				<td>${item.invitationCode}</td>
				<td><fmt:formatDate value="${item.createTime}" pattern="yyyy-MM-dd"/></td>
				<td>
					<a class="btnSelect" href="javascript:$.bringBack({id:${item.id}, channelNum:${item.channelNum}})" title="查找带回">选择</a>
				</td>
			</tr>			
			</c:forEach>
		</tbody>
	</table>
	<!-- 分页 -->
	<dwz:pagination page="${page }"/>
</div>