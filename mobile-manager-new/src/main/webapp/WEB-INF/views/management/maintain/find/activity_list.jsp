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
<script type="text/javascript">
(function(){
	$("selct").change(function(){
		var value = $('select option').val();
		console.log("selct:" + value);
		if("${param.search_EQ_platformType == " + value + "}"){
			$('select option[value = ' + value + ']').attr("selected",true);
		}
	});
	
})(jQuery);

</script>
<dwz:paginationForm action="${contextPath }/management/maintain/find/activityList" page="${page }">
	<input type="hidden" name="keywords" value="${keywords}"/>
</dwz:paginationForm>

<form method="post" action="${contextPath }/management/maintain/find/activityList" onsubmit="return dialogSearch(this)">
	<div class="pageHeader">
		<div class="searchBar">
			<ul class="searchContent">
				
				<li>
					<label>活动关键字(id或者名称)：</label>
					<input type="text" name="keyword" value="${keyword}"/>
				</li>
			</ul>
			<ul class="searchContent">
				<li>
					<label>开始时间：</label>
					<input type="text" name="startTime" class="date" dateFmt="yyyy-MM-dd HH:mm:ss" readonly="true"/>
					<a class="inputDateButton" href="javascript:;">选择</a>
				</li>
			</ul>
			<ul class="searchContent">
				<li>
					<label>结束时间：</label>
					<input type="text" name="endTime" class="date" dateFmt="yyyy-MM-dd HH:mm:ss" readonly="true"/>
					<a class="inputDateButton" href="javascript:;">选择</a>
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

	
	
	<table class="table" layoutH="180" width="850" nowrapTD="false">
		<thead>
			<tr>
				<th width="22"><input type="checkbox" group="ids" class="checkboxCtrl"></th>			
				<th style="width:80px;"align="center">操作</th>
				
				<th style="width:80px"align="center">专题标题</th>
				<th style="width:80px"align="center">活动ID</th>
				<th style="width:80px"align="center">移动端开始时间</th>
				<th style="width:80px"align="center">移动端结束时间</th>
				<th style="width:80px"align="center">移动端预热时间</th>
				<th style="width:50px"align="center">描述</th>
				<th style="width:50px"align="center">状态</th>
				<th style="width:80px"align="center">专题副标题</th>
				
			</tr>
		</thead>
		<tbody>
			<c:forEach var="item" items="${activityFinds}">
					<tr target="slt_uid" rel="${item.id}">
						<td><input name="ids" value="${item.id}" type="checkbox"></td>
						<td> <a href="#" onclick="javascript:setActivity('${contextPath }','${item.title}','${item.activityId}','<fmt:formatDate value="${item.startTime}" pattern="yyyy-MM-dd HH:mm:ss"/>','<fmt:formatDate value="${item.endTime}" pattern="yyyy-MM-dd HH:mm:ss"/>','<fmt:formatDate value="${item.preTime}" pattern="yyyy-MM-dd HH:mm:ss"/>','${item.iphonePic}','${item.mobilePic}','${item.shareUrl}','${item.description}','${item.status}','${item.subTitle}');" ><font color="red">配置活动</font></a></td>
						
						<td >${item.title}</td>
						<td >${item.activityId}</span></td>
						<td><fmt:formatDate value="${item.startTime}" pattern="yyyy-MM-dd HH:mm:ss"/></td>
						<td><fmt:formatDate value="${item.endTime}" pattern="yyyy-MM-dd HH:mm:ss"/></td>
						<td><fmt:formatDate value="${item.preTime}" pattern="yyyy-MM-dd HH:mm:ss"/></td>
						<td>${item.description}</td>
						<td>${item.status}</td>
						<td>${item.subTitle}</td>
					
					</tr>	
				
			</c:forEach>
		</tbody>
	</table>
	<!-- 分页 -->
	
</div>