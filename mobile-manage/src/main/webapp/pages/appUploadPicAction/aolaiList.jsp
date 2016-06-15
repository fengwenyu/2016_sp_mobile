<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<html>
<head>
<title>APP上传图</title>
<%@ include file="/pages/public/common.jspf"%>
<script type="text/javascript">
	$(function() {
		grid = $('#appPictures')
		.datagrid(
				{
					nowrap: false,
					striped: true,
					collapsible:true,
					url : '${pageContext.request.contextPath }/appUploadPicAction_aolaiQuery.action',
					remoteSort: true,
					showFooter:true,
					singleSelect :true,
				
					columns : [ [

					 {
							field : 'imgUrl',
							title : '图片',
							width : 180,
							formatter:function(value){
								if(value!=null){
									return "<img src='"+value+"' width='180' height='110'/>"
								}
								
							}
						},{
							field : 'osType',
							title : '图片类型',
							width : 100,
							align : 'center',
							editor : 'text'
						},{
							field : 'imgWidth',
							title : '图片宽度',
							width : 60,
							align : 'center',
							editor : 'text'
						},{
							field : 'imgHeight',
							title : '图片高度',
							width : 60,
							align : 'center',
							editor : 'text'
						},{
							field : 'createDate',
							title : '创建时间',
							width : 100,
							align : 'center',
							editor : 'text'
						},{
							field:'id',title:'操&nbsp;&nbsp;作',
							width:200,
							align:'center',
							formatter:function(value){
								return '<a style="color:#4574a0; font-weight:bold;" href="appUploadPicAction_info.action?shopType=0&id='+ value + '">' + "编&nbsp;&nbsp;辑 " + '</a>&nbsp;&nbsp;<a style="color:#4574a0; font-weight:bold;"  href=javascript:remove("' + value + '");>' + "删&nbsp;&nbsp;除" + '</a>';}
						}
					] ],
					title : '列表',
					width : 850,
					height : 500,

				
					pagination : true,
					rownumbers:true
				

				});

		
	});

	
	function reloadgrid(){
		//执行查询操作
		var pager = $('#appPictures').datagrid('getPager');//得到DataGrid页面
			pager.pagination({
				pageNumber:1
			});

		$('#appPictures').datagrid('options').queryParams = {osType:$("#typeQuery").val()};
		$("#appPictures").datagrid('reload');
		$("#appPictures").datagrid('resize'); 
	}
	function remove(id){
		$.messager.confirm('提示', '确认删除么?', function(r){
					if (r){
						$.ajax(
						{
							type : "post",
							url : "appUploadPicAction_delete.action",
							data : {
								id : id,
								x : Math.random()
							},
							success : function callback()
							{
								$.messager.alert('操作提示', '操作成功!');

								reloadgrid();
							}
						});

					}
				});
	}
</script>
</head>
<body>
	<!-- 标题显示 -->
	<div id="Title_bar">
		<div id="Title_bar_Head">
			<div id="Title_Head"></div>
			<div id="Title">
				<!--页面标题-->
				<img border="0" width="13" height="13"
					src="${pageContext.request.contextPath}/style/images/title_arrow.gif" />&nbsp;App上传图
			</div>
			<div id="Title_End"></div>
		</div>
	</div>
	<!--显示表单内容-->

	<div id="QueryArea">
	<table border=0 cellspacing=3 cellpadding=5>
				<tr>
					<td>类型</td>
					<td><select id="typeQuery" name="typeQuery"
						style="width: 150px;">
							<option value="-1">---请选择---</option>
							<option value="iphone4Start">iphone4启动图</option>
							<option value="iphone5Start">iphone5,6,6Plus启动图</option>
							<option value="androidStart">安卓启动图</option>
							<option value="ipadStart">ipad启动图</option>
							<!-- <option value="iphoneFindShare">iphone发现分享图</option>
							<option value="androidFindShare">安卓发现分享图</option> -->
					</select> </td>
					<td><input type="image"
						src="${pageContext.request.contextPath}/style/blue/images/button/query.PNG"
						/ " onclick="reloadgrid();">
					</td>
					 <td><s:a action="appUploadPicAction_aolaiEdit"><img src="${pageContext.request.contextPath}/style/images/createNew.png" /></s:a></td>
				</tr>
			</table>
	
	</div>


	<div id=MainArea>
		<div class="Description" style="margin-left: 10px;">

			<div id="appPicturesDiv">
				<table id="appPictures"></table>
			</div>
		</div>

		
		

	</div>
</body>
</html>
