<%@page import="java.util.Date"%>
<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8" trimDirectiveWhitespaces="true"%>
<%@ include file="/WEB-INF/views/include.inc.jsp"%>
<style>
.grid .gridTbody .imgtd div{
	display: block;
	overflow: hidden;
	height: 120px;
	white-space: nowrap;
	line-height: 21px;
}
</style>
<dwz:paginationForm action="${contextPath }/management/admin/appPic/list" page="${page }">
<input type="hidden" name="search_EQ_shopType" value="${param.search_EQ_shopType}"/>
	<input type="hidden" name="search_EQ_osType" value="${param.search_EQ_osType}"/>
	<input type="hidden" name="search_EQ_uses" value="${param.search_EQ_uses}"/>
	<input type="hidden" name="search_GTE_createTime" value="${param.search_GTE_createTime}"/>
	<input type="hidden" name="search_LTE_createTime" value="${param.search_LTE_createTime}"/>
</dwz:paginationForm>
<form method="post" action="${contextPath }/management/admin/appPic/list" onsubmit="return navTabSearch(this)">
	<div class="pageHeader">
		<div class="searchBar">
			<ul class="searchContent">
				<li>
					<label style="width: 100px;">客户端：</label>
					<select name="search_EQ_shopType">
					<option value="">请选择</option>
					<option value="1" ${param.search_EQ_shopType == "1" ? 'selected="selected"':'' }>尚品 &nbsp;&nbsp;&nbsp;</option>
					<option value="2" ${param.search_EQ_shopType == "2" ? 'selected="selected"':'' }>奥莱</option></select>
				</li>
				<li>
					<label style="width: 100px;">OS类型：</label>
					<select name="search_EQ_osType">
					<option value="">请选择</option>
					<option value="iphone4" ${param.search_EQ_osType == "iphone4" ? 'selected="selected"':'' }>iphone4</option>
					<option value="iphone5" ${param.search_EQ_osType == "iphone5" ? 'selected="selected"':'' }>iphone5</option>
					<option value="andriod" ${param.search_EQ_osType == "andriod" ? 'selected="selected"':'' }>andriod</option>
					<option value="ipad" ${param.search_EQ_osType == "ipad" ? 'selected="selected"':'' }>ipad</option></select>
				</li>
				<li>
					<label style="width: 100px;">用途：</label>
					<select name="search_EQ_uses">
					<option value="">请选择</option>
					<option value="start" ${param.search_EQ_uses == "start" ? 'selected="selected"':'' }>启动图</option>
					<option value="share" ${param.search_EQ_uses == "share" ? 'selected="selected"':'' }>分享图</option>
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
			<shiro:hasPermission name="AppPic:save">
				<li><a iconClass="application_add" rel="AppPic_navTab" target="navTab"  href="${contextPath }/management/admin/appPic/create"><span>添加App上传图</span></a></li>
			</shiro:hasPermission>
			<shiro:hasPermission name="AppPic:edit">
				<li><a iconClass="application_edit" rel="AppPic_navTab" target="navTab" href="${contextPath }/management/admin/appPic/update/{slt_uid}"><span>编辑App上传图</span></a></li>
			</shiro:hasPermission>
			<shiro:hasPermission name="AppPic:delete">
				<li><a iconClass="application_delete" target="selectedTodo" rel="ids" href="${contextPath }/management/admin/appPic/delete" title="确认要删除该信息吗?"><span>删除</span></a></li>
			</shiro:hasPermission>
		</ul>
	</div>
	
	<table class="table" layoutH="162" width="100%">
		<thead>
			<tr>	
				<th width="22"><input type="checkbox" group="ids" class="checkboxCtrl"></th>
				<th width='180' >图片</th>
				<th>宽度</th>
				<th>高度</th>
				<th orderField="osType" class="${page.orderField eq 'osType' ? page.orderDirection : ''}">OS类型</th>
				<th orderField="uses" class="${page.orderField eq 'uses' ? page.orderDirection : ''}">用途</th>
				<th orderField="shopType" class="${page.orderField eq 'shopType' ? page.orderDirection : ''}">客户端</th>
				<th orderField="createTime" class="${page.orderField eq 'createTime' ? page.orderDirection : ''}">操作时间</th>
			</tr>
		</thead>
		<tbody>
			<c:forEach var="item" items="${appPics}">
			<tr target="slt_uid" rel="${item.id}">
				<td><input name="ids" value="${item.id}" type="checkbox"></td>
			    <td class="imgtd"><img src="${fn:replace(fn:replace(item.imageUrl,'{w}',item.imageWidth),'{h}',item.imageHeight )}" width='180' height='110'/></td>
			    <td>${item.imageWidth }</td>
			    <td>${item.imageHeight }</td>
			    <td>${item.osType }</td>
			    <td><c:if test="${item.uses eq 'start' }">启动图</c:if>
			    <c:if test="${item.uses eq 'share' }">分享图</c:if>
			    </td>
			    <td>${item.shopType eq 2? "奥莱":"尚品" }</td>
			    <td><fmt:formatDate value="${item.createTime}" pattern="yyyy-MM-dd HH:mm:ss"/></td>
			</tr>
			</c:forEach>
		</tbody>
	</table>
	<!-- 分页 -->
	<dwz:pagination page="${page }"/>
</div>