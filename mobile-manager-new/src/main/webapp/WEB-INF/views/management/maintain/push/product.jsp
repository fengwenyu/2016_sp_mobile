<%@ page language="java" contentType="text/html; charset=UTF-8" trimDirectiveWhitespaces="true"
    pageEncoding="UTF-8"%>
<%@page import="java.util.Date"%>
<%@ include file="/WEB-INF/views/include.inc.jsp"%>

<c:set var="contextPath" value="${pageContext.request.contextPath}"/>

<dwz:paginationForm action="${contextPath }/management/maintain/push/lookup2product" page="${page }">
	<input type="hidden" name="search_LIKE_productName" value="${param.search_LIKE_productName}"/>
	<input type="hidden" name="search_EQ_productNum" value="${param.search_EQ_productNum}"/>
</dwz:paginationForm>

<form method="post" action="${contextPath }/management/maintain/push/lookup2product" onsubmit="return dwzSearch(this, 'dialog');">
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
	<table class="table" layoutH="137" width="100%">
		<thead>
			<tr>
				<th width="22"><input type="checkbox" group="ids" class="checkboxCtrl"></th>
				<th width="200" orderField="productNum" class="${page.orderField eq 'productNum' ? page.orderDirection : ''}">产品编号</th>
				<th width="200">产品名称</th>
				<th width="200">平台</th>
				<th width="100">查找带回</th>
			</tr>
		</thead>
		<tbody>
			<c:forEach var="item" items="${products}">
			<tr target="slt_uid" rel="${item.id}">
				<td><input name="ids" value="${item.id}" type="checkbox"></td>
				<td>${item.productNum}</td>
				<td>${item.productName}</td>
				<td>${item.platForm}</td>
				<td><a class="btnSelect" href="javascript:$.bringBack({productCode:'${item.productNum}',platformName:'${item.platForm}'})" title="查找带回">选择</a></td>
			</tr>
			</c:forEach>
		</tbody>
	</table>
	<!-- 分页 -->
	<dwz:pagination page="${page }"/>
</div>