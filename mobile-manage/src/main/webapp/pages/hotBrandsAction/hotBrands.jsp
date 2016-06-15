<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<html>
<head>
<title>热门品牌推荐</title>
<%@ include file="/pages/public/common.jspf"%>

<script type="text/javascript">
	$(function() {
		grid = $('#hotBrands')
		.datagrid(
				{
					nowrap: false,
					striped: true,
					collapsible:true,
					url : '${pageContext.request.contextPath }/hotBrandsAction_query.action',
					remoteSort: true,
					showFooter:true,
					singleSelect :true,
					frozenColumns : [ [ {
						field : 'select',
						checkbox : true
					} ] ],
					columns : [ [

					{
						field : 'id',
						title : 'id',
						width : 30,
						hidden : 'true',
						editor : 'text'
					}, {
						field : 'brandName',
						title : '品牌名称',
						width : 120,
						align : 'center',
						editor : 'text'
					},{
						field : 'brandId',
						title : '品牌ID',
						width : 60,
						align : 'center',
						editor : 'text'
					},{
						field : 'sort',
						title : '顺序',
						width : 30,
						align : 'center',
						editor : 'text'
					},{
						field : 'imgUrl',
						title : '图片url',
						width : 300,
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
						field : 'topImgUrl',
						title : '头图链接',
						width : 300,
						align : 'center',
						editor : 'text'
					},{
						field : 'topImgWidth',
						title : '头图宽度',
						width : 60,
						align : 'center',
						editor : 'text'
					},{
						field : 'topImgHeight',
						title : '头图高度',
						width : 60,
						align : 'center',
						editor : 'text'
					},{
						field : 'createDate',
						title : '创建时间',
						width : 100,
						align : 'center',
						editor : 'text'
					}

					] ],
					title : '列表',
					width : '100%',
					height : 500,

				
					pagination : true,
					rownumbers:true,
					toolbar : [ {

						text : '添加',
						iconCls : 'icon-add',
						handler : add

					}, {

						id : 'btnsave',
						text : '修改',
						iconCls : 'icon-edit',
						handler : update
					},{id:'btnDelete',
					text:'删除',					
					iconCls:'icon-remove',
					handler : remove
					}]

				});

		add_window = $('#add-window').window({
			closed : true,
			modal : true,
			minimizable : false,
			maximizable : false,
			resizable : false
		});

		form = add_window.find('form');

	});

	var add_window;
	//查询

	function add() {
		form.form('clear');
		add_window.window('open');

		form.url = 'hotBrandsAction_add.action';

	}
	function reloadgrid(){
		//执行查询操作
		var pager = $('#hotBrands').datagrid('getPager');//得到DataGrid页面
			pager.pagination({
				pageNumber:1
			});

		$('#hotBrands').datagrid('options').queryParams = {};
		$("#hotBrands").datagrid('reload');
		$("#hotBrands").datagrid('resize'); 
	}
	function saveEntity() {

		var brandId = $('#brandId').val();
		var brandName = $('#brandName').val();
		var imgUrl= $('#imgUrl').val();
		var imgWidth= $('#imgWidth').val();
		var imgHeight= $('#imgHeight').val();
		var sort=$("#sort").val();
		
		var topImgUrl= $('#topImgUrl').val();
		var topImgWidth= $('#topImgWidth').val();
		var topImgHeight= $('#topImgHeight').val(); 
		if (Trim(brandId) == '') {
			alert('品牌ID不能为空！');
			$('#brandId').focus();
			return;
		}
		if (isNaN(sort) ) {
			alert('位置必须为数字！');
			$('#sort').focus();
			return;
		}
		if (Trim(brandName) == '') {
			alert('品牌名称不能为空！');
			$('#brandName').focus();
			return;
		}
		if (Trim(imgUrl) == '') {
			alert('图片链接不能为空！');
			$('#imgUrl').focus();
			return;
		}
		if (Trim(imgWidth) == '') {
			alert('图片宽度不能为空！');
			$('#imgWidth').focus();
			return;
		}
		if (Trim(imgHeight) == '') {
			alert('图片高度不能为空！');
			$('#imgHeight').focus();
			return;
		}
		if (Trim(topImgUrl) == '') {
			alert('头图链接不能为空！');
			$('#topImgUrl').focus();
			return;
		}
		if (Trim(topImgWidth) == '') {
			alert('头图宽度不能为空！');
			$('#topImgWidth').focus();
			return;
		}
		if (Trim(topImgWidth) == '') {
			alert('头图高度不能为空！');
			$('#topImgWidth').focus();
			return;
		}
		
		form.form('submit', {
			url : form.url,
			cache : false,

			success : function callback() {
				add_window.window('close');
				$.messager.alert('操作提示', '操作成功!');

				reloadgrid();
			}
		});

	}
	function closeWindows() {
		add_window.window('close');
	}

	function update() {
		var row = grid.datagrid('getSelected');
		if (row) {
			if (grid.datagrid('getSelections').length > 1) {
				$.messager.alert('warning', '只能选择一条记录!', 'warning');
				return;
			}
			add_window.window('open');
			form.form('clear');
			$.ajax({
				type : "post",
				url : 'hotBrandsAction_info.action',
				data : {
					id : row.id,
					x : Math.random()
				},
				success : function callback(data) {
					var obj = eval('(' + data + ')');
					form.form('load', obj);
					form.url = 'hotBrandsAction_update.action';
				}
			});

		} else {
			$.messager.alert('警告', '请选择一条数据!', 'warning');
		}

	}
	
	function remove() {
		var row = grid.datagrid('getSelected');
		if (row) {
			if (grid.datagrid('getSelections').length > 1) {
				$.messager.alert('warning', '只能选择一条记录!', 'warning');
				return;
			}
			
			$.messager.confirm('提示', '确认删除么?', function(id)
					{
						if (id)
						{
							
							$.ajax(
							{
								type : "post",
								url : "hotBrandsAction_delete.action",
								data : {
									id : row.id,
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

		} else {
			$.messager.alert('警告', '请选择一条数据!', 'warning');
		}

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
					src="${pageContext.request.contextPath}/style/images/title_arrow.gif" />&nbsp;热门品牌
			</div>
			<div id="Title_End"></div>
		</div>
	</div>
	<!--显示表单内容-->

	<div id="QueryArea"></div>


	<div id=MainArea>
		<div class="Description" style="margin-left: 10px;">

			<div id="hotBrandsDiv">
				<table id="hotBrands"></table>
			</div>
		</div>

		<div id="add-window" title="推荐位添加窗口"
			style="width: 600px; height: 350px;">

			<div style="padding: 20px 20px 20px 20px;">

				<s:form action="">

					<table>
						<tr>
							<td width="50px">品牌ID</td>
							<td><input id="brandId" name="brandId" type="text" /> <font
								color="red"> *</font>
							</td>



							<td>品牌名称</td>
							<td><input id="brandName" name="brandName" type="text" /> <font
								color="red"> *</font>
							</td>
						</tr>
					</table>
					<table>
					<tr>
						<td>图片链接</td>
						<td><input id="imgUrl" name="imgUrl" type="text" size="70"/> <font
							color="red"> *</font>
						</td>
					</tr>
					</table>
					<table>
					<tr>
						<td>位置顺序</td>
						<td><input id="sort" name="sort" type="text" /> <font
							
						</td>
						</tr>
						</table>
						<table>	
						<tr><td>图片尺寸:<td>宽度</td>
							<td><input id="imgWidth" name="imgWidth" type="text" size="10"/> <font
								color="red"> *</font>
							</td>

							<td>高度</td>
							<td><input id="imgHeight" name="imgHeight" type="text" size="10"/>
								<font color="red"> *</font>
							</td></tr>
						
						</table>
						<table>	
						<tr>
							<td>头图链接</td>
							<td><input id="topImgUrl" name="topImgUrl" type="text" size="70"/> <font
								color="red"> *</font>
							</td>

						
						</tr>
						</table>
						<table>	
						<tr><td>头图尺寸: 宽度</td>
							<td><input id="topImgWidth" name="topImgWidth" type="text" size="10"/>
								<font color="red"> *</font>
							</td>
							<td>高度</td>
							<td><input id="topImgHeight" name="topImgHeight" type="text" size="10"/>
								<font color="red"> *</font>
							</td></tr>
						<tr>
							
						</tr>	
								<input id="id" name="id" type="hidden" />
					</table>
				</s:form>
			</div>
			<div style="text-align: center; padding: 5px;">
				<a href="javascript:void(0)" onclick="saveEntity()" id="btn-save"
					icon="icon-search" class='easyui-linkbutton'>保存</a> <a
					href="javascript:void(0)" onclick="closeWindows()" id="btn-cancel"
					icon="icon-cancel" class='easyui-linkbutton'>取消</a>
			</div>
		</div>

	</div>
</body>
</html>
