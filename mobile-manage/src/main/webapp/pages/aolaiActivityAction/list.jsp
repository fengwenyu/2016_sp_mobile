<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<html>
<head>
<title>奥莱客户端导航编辑</title>
<%@ include file="/pages/public/common.jspf"%>

<script type="text/javascript">
	$(function() {
		grid = $('#aolaiActivity')
		.datagrid(
				{
					nowrap: false,
					striped: true,
					collapsible:true,
					url : '${pageContext.request.contextPath }/aolaiActivityAction_datagridList.action',
					remoteSort: true,
					showFooter:true,
					singleSelect :true,
					frozenColumns : [ [ {
						field : 'select',
						checkbox : true
					} ] ],
				columns : [ [ {
						
						field : 'startTime',
						title : '开始时间',
						width : 150,
						align : 'center',
						editor : 'text'
					},{
						
						field : 'endTime',
						title : '结束时间',
						width : 150,
						align : 'center',
						editor : 'text'
					},{
						field : 'getUrl',
						title : '静态页链接',
						width : 450,
						align : 'center',
						editor : 'text'
					},{
						field : 'display',
						title : '是否显示',
						width : 150,
						align : 'center',
						formatter:function(value){
							if(value=="1"){
								return "显示"
							}else if(value="0"){
								return "不显示"
							}
						}
					}

					] ],

					title : '列表',
					width : 950,
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
					},{
						text:'关闭显示',					
						iconCls:'icon-edit',
						handler : setNotDisplay
						},{
						text:'开启显示',					
						iconCls:'icon-edit',
						handler : setDisplay
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
		document.getElementById('display').value='YES';

	}
	function reloadgrid(){
		//执行查询操作
		var pager = $('#aolaiActivity').datagrid('getPager');//得到DataGrid页面
			pager.pagination({
				pageNumber:1
			});

		$('#aolaiActivity').datagrid('options').queryParams = {};
		$("#aolaiActivity").datagrid('reload');
		$("#aolaiActivity").datagrid('resize'); 
	}
	function saveEntity() {

		var name = $('#getUrl').val();
		var startTime = $('#startTime').val();
		var endTime= $('#endTime').val();
		if (Trim(getUrl) == '') {
			$.messager.alert('操作提示', '活动链接不能为空！');
			$('#getUrl').focus();
			return;
		}
		if (Trim(startTime) == ''||Trim(startTime) == -1) {
			$.messager.alert('操作提示', '请选择开始时间！');
			$('#startTime').focus();
			return;
		}
		if (Trim(endTime) == '') {
			$.messager.alert('操作提示', '请选择结束时间！');
			$('#endTime').focus();
			return;
		}
		form.form('submit', {
			url : 'aolaiActivityAction_edit.action',
			cache : false,

			success : function callback(data) {
					data = eval("(" + data + ")");
					if ("success" == data.returnCode) {
						$.messager.alert('操作提示', '操作成功!');
						add_window.window('close');
						reloadgrid();
					} else {
						$.messager.alert('操作提示', data.returnInfo);
						

					}
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
				url : 'aolaiActivityAction_info.action',
				data : {
					id : row.id,
					x : Math.random()
				},
				success : function callback(data) {
					var obj = eval('(' + data + ')');
					form.form('load', obj);
					form.url = 'aolaiActivityAction_edit.action';
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
								url : "aolaiActivityAction_delete.action",
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
	function setDisplay() {
		var row = grid.datagrid('getSelected');
		if (row) {
			if (grid.datagrid('getSelections').length > 1) {
				$.messager.alert('warning', '只能选择一条记录!', 'warning');
				return;
			}
			if(row.display=='是'){
				$.messager.alert('warning', '已经是显示状态，无需重复操作!', 'warning');
				return;
			}
			$.messager.confirm('提示', '确认显示吗?', function(id){
				$.ajax({
					type : "post",
					url : "aolaiActivityAction_setDisplay.action",
					data : {
						id :row.id,
						x : Math.random()
					},
					success : function callback(data)
					{
						data = eval("(" + data + ")");
						if ("success" == data.returnCode) {
							$.messager.alert('操作提示', '操作成功!');
							reloadgrid();
						} else {
							$.messager.alert('操作提示', data.returnInfo);
						}
					}
				});
					
			});

		} else {
			$.messager.alert('警告', '请选择一条数据!', 'warning');
		}

	}
	
	function setNotDisplay() {
		var row = grid.datagrid('getSelected');
		if (row) {
			if (grid.datagrid('getSelections').length > 1) {
				$.messager.alert('warning', '只能选择一条记录!', 'warning');
				return;
			}
			$.messager.confirm('提示', '确认关闭显示么?', function(id){
				if (id){
					$.ajax({
							type : "post",
							url : "aolaiActivityAction_setNotDisplay.action",
							data : {
								id :row.id,
								x : Math.random()
							},
							success : function callback(data)
								{
									data = eval("(" + data + ")");
									if ("success" == data.returnCode) {
										$.messager.alert('操作提示', '操作成功!');
										reloadgrid();
									} else {
										$.messager.alert('操作提示', data.returnInfo);
									}
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
					src="${pageContext.request.contextPath}/style/images/title_arrow.gif" />&nbsp;奥莱客户端导航编辑
			</div>
			<div id="Title_End"></div>
		</div>
	</div>
	<!--显示表单内容-->

	<div id="QueryArea"></div>


	<div id=MainArea>
		<div class="Description" style="margin-top: 50px;margin-left: 10px;">

			<div id="aolaiActivityDiv">
				<table id="aolaiActivity"></table>
			</div>
		</div>

		<div id="add-window" title="窗口"
			style="width: 600px; height: 350px;">

			<div style="padding: 20px 20px 20px 20px;">
				<s:form action="">
					<table>
					<tr>
						<td width="85px">活动静态页链接</td>
						<td><input id="getUrl" name="getUrl" type="text" size="70"/><font
							color="red"> *</font>
							<label><font color="red"></font></label>
							
							 </td>
					</tr>
				</table>
				<table>
					<tr>
						<td width="85px">开始时间</td>
						<td><input id="startTime" name="startTime"
							readonly="readonly"
							onFocus="var endDate=$dp.$('endTime');WdatePicker({skin:'whyGreen',dateFmt:'yyyy-MM-dd HH:mm:ss',onpicked:function(){endTime.focus();},maxDate:'#F{$dp.$D(\'endTime\')}'})"
							style="width: 170px;"> <font
							color="red"> *</font>
							<label><font color="red"></font></label>
						</td>
					</tr>
					<tr>
						<td>结束时间</td>
						<td><input id="endTime"
								name="endTime"
								onFocus="WdatePicker({skin:'whyGreen',dateFmt:'yyyy-MM-dd HH:mm:ss',minDate:'#F{$dp.$D(\'startTime\')}'})"
								readonly="readonly" style="width: 170px;"> <font
							color="red"> *</font>
							<label><font color="red"></font></label>
						</td>
					</tr>
					<tr>
						<td>是否显示</td>
						<td><select id="display" name="display">
							<option value="YES" selected="selected">是</option>
							<option value="NO">否</option>
						
						</select>
						</td>
					</tr>
			</table>
					<input id="id" name="id" type="hidden" />
				</s:form>
			</div>
			<div style="text-align: center; padding: 5px;">
				<a href="javascript:void(0)" onclick="saveEntity()" id="btn-save"
					icon="icon-search" class='easyui-linkbutton'>保存</a> <a
					href="javascript:void(0)" onclick="closeWindows()" id="btn-cancel"
					icon="icon-cancel" class='easyui-linkbutton'>取消</a>
			</div>
		</div>
	
</body>
</html>
