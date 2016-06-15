<%@page import="java.util.Date"%>
<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8" trimDirectiveWhitespaces="true"%>
<%@ include file="/WEB-INF/views/include.inc.jsp"%>

<dwz:paginationForm action="${contextPath }/management/admin/feedback/list" page="${page }">
	<input type="hidden" name="search_LIKE_loginName" value="${param.search_LIKE_loginName }"/>
	<input type="hidden" name="search_EQ_channelId" value="${param.search_EQ_channelId}"/>
	<input type="hidden" name="search_EQ_productId" value="${param.search_EQ_productId}"/>
	<input type="hidden" name="search_EQ_productVersion" value="${param.search_EQ_productVersion}"/>
	<input type="hidden" name="search_EQ_platform" value="${param.search_EQ_platform}"/>
	<input type="hidden" name="search_GTE_createTime" value="${param.search_GTE_createTime}"/>
	<input type="hidden" name="search_LTE_createTime" value="${param.search_LTE_createTime}"/>
</dwz:paginationForm>

<form method="post" action="${contextPath }/management/admin/feedback/list" onsubmit="return navTabSearch(this)">
	<div class="pageHeader">
		<div class="searchBar">
			<ul class="searchContent">
				<li>
					<label style="width: 100px;">登录名称：</label>
					<input type="text" name="search_LIKE_loginName" value="${param.search_LIKE_loginName}"/>
				</li>
				<li>
					<label style="width: 100px;">渠道号：</label>
					<input type="text" name="search_EQ_channelId" value="${param.search_EQ_channelId}"/>
				</li>
			</ul>
			<ul class="searchContent">
				<li>
					<label style="width: 100px;">产品号：</label>
					<input type="text" name="search_EQ_productId" value="${param.search_EQ_productId}"/>
				</li>
				<li>
					<label style="width: 100px;">产品版本：</label>
					<input type="text" name="search_EQ_productVersion" value="${param.search_EQ_productVersion}"/>
				</li>
				<li>
					<label style="width: 100px;">平台：</label>
					<select name="search_EQ_platform">
						<option value="">请选择平台</option>
						<option value="ios" ${param.search_EQ_platform == "ios" ? 'selected="selected"':'' }>IOS</option>
						<option value="android" ${param.search_EQ_platform == "android" ? 'selected="selected"':'' }>Android</option>
					</select>
				</li>
			</ul>
			<ul class="searchContent">	
				<li>
					<label style="width: 100px;">开始时间：</label>
					<input type="text" name="search_GTE_createTime" class="date" readonly="readonly" style="float:left;" value="${param.search_GTE_createTime}"/>
					<a class="inputDateButton" href="javascript:;" style="float:left;">选择</a>
				</li>			
				<li>
					<label style="width: 100px;">结束时间：</label>
					<input type="text" name="search_LTE_createTime" class="date" readonly="readonly" style="float:left;" value="${param.search_LTE_createTime}"/>
					<a class="inputDateButton" href="javascript:;" style="float:left;">选择</a>
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
			<shiro:hasPermission name="Feedback:edit">
				<li><a iconClass="application_edit" target="dialog" width="550" height="520" href="${contextPath }/management/admin/feedback/update/{slt_uid}"><span>编辑反馈</span></a></li>
			</shiro:hasPermission>
			<shiro:hasPermission name="Feedback:delete">
				<li><a iconClass="application_delete" target="selectedTodo" rel="ids" href="${contextPath }/management/admin/feedback/delete" title="确认要删除该反馈信息吗?"><span>删除反馈</span></a></li>
			</shiro:hasPermission>
		</ul>
	</div>
	
	<table class="table" layoutH="186" width="100%">
		<thead>
			<tr>
				<th width="22"><input type="checkbox" group="ids" class="checkboxCtrl"></th>
				<th orderField="loginName" class="${page.orderField eq 'loginName' ? page.orderDirection : ''}">登录名</th>
				<th orderField="platform" class="${page.orderField eq 'platform' ? page.orderDirection : ''}">平台</th>
				<th orderField="channelId" class="${page.orderField eq 'channelId' ? page.orderDirection : ''}">渠道号</th>
				<th orderField="productId" class="${page.orderField eq 'productId' ? page.orderDirection : ''}">产品号</th>
				<th orderField="productVersion" class="${page.orderField eq 'productVersion' ? page.orderDirection : ''}">版本号</th>
				<th orderField="createTime" class="${page.orderField eq 'createTime' ? page.orderDirection : ''}">操作时间</th>
				<th>反馈内容</th>
			</tr>
		</thead>
		<tbody>
			<c:forEach var="item" items="${feedbacks}">
			<tr target="slt_uid" rel="${item.id}">
				<td><input name="ids" value="${item.id}" type="checkbox"></td>
			    <td>${item.loginName }</td>
			    <td>${item.platform }</td>
			    <td>${item.channelId }</td>
			    <td>${item.productId }</td>
			    <td>${item.productVersion }</td>
			    <td><fmt:formatDate value="${item.createTime}" pattern="yyyy-MM-dd HH:mm:ss"/></td>
			     <td>${item.msg }</td>
			</tr>
			</c:forEach>
		</tbody>
	</table>
	<!-- 分页 -->
	<dwz:pagination page="${page }"/>
</div>