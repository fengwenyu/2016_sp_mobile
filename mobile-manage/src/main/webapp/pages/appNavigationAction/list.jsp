<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<html>
<head>
<title>奥莱客户端导航编辑</title>
<%@ include file="/pages/public/common.jspf"%>

<script type="text/javascript">
	$(function() {
		grid = $('#appNavigation')
		.datagrid(
				{
					nowrap: false,
					striped: true,
					collapsible:true,
					url : '${pageContext.request.contextPath }/appNavigationAction_datagridList.action',
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
						field : 'name',
						title : '导航名称',
						width : 120,
						align : 'center',
						editor : 'text'
					},{
						field : 'tabId',
						title : '导航id',
						width : 50,
						align : 'center',
						editor : 'text'
					},{
						field : 'link',
						title : '链接',
						width : 150,
						align : 'center',
						editor : 'text'
					},{
						field : 'showType',
						title : '数据展示类型',
						width : 100,
						align : 'center',
						formatter:function(value){
							
							if(value=="0"){
								return "单列显示"
							}else if(value=="1"){
								return "双列显示"
							}else if(value=="2"){
								return "HTML页面"
							}
						}
					},{
						field : 'createDate',
						title : '创建时间',
						width : 100,
						align : 'center',
						editor : 'text'
					},{
						field : 'startDate',
						title : '开始时间',
						width : 100,
						align : 'center',
						editor : 'text'
					},{
						field : 'endDate',
						title : '开始时间',
						width : 100,
						align : 'center',
						editor : 'text'
					},{
						field : 'sort',
						title : '位置顺序',
						width : 100,
						align : 'center',
						formatter:function(value,rec){
							return'<a style="color:#4574a0; font-weight:bold;"  href=javascript:sort("' + rec.id + '");>' + value + '</a>'
						}
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
					},{
						id:'btnDelete',
						text:'删除',					
						iconCls:'icon-remove',
						handler : remove
					}]

				});

		add_window = $('#add-window').window({
			closed : true,
			modal : false,
			minimizable : false,
			maximizable : false,
			resizable : false
		});

		form = add_window.find('form');
		sort_window = $('#sort-window').window({
			closed : true,
			modal : false,
			minimizable : false,
			maximizable : false,
			resizable : false
		});
	});


	function add() {
		form.form('clear');
		add_window.window('open');
		document.getElementById('typeStr').value='-1';
		$('#tabidname').hide();
		$('#linkname').hide();
	}
	function reloadgrid(){
		//执行查询操作
		var pager = $('#appNavigation').datagrid('getPager');//得到DataGrid页面
			pager.pagination({
				pageNumber:1
			});

		$('#appNavigation').datagrid('options').queryParams = {};
		$("#appNavigation").datagrid('reload');
		$("#appNavigation").datagrid('resize'); 
	}
	function saveEntity() {

		var name = $('#name').val();
		var type = $('#typeStr').val();
		var link= $('#link').val();
		var tabid= $('#tabId').val();
		var startDate=$("#startDate").val();
		var endDate=$("#endDate").val();
		if (Trim(name) == '') {
			$.messager.alert('操作提示', '导航名称不能为空！');
			$('#name').focus();
			return;
		}
		if (Trim(type) == ''||Trim(type) == -1) {
			$.messager.alert('操作提示', '请选择展示类型！');
			$('#type').focus();
			return;
		}
		if(Trim(type) == 'HTML'){
			if (Trim(link) == ''||Trim(link) ==null) {
				$.messager.alert('操作提示', '链接不能为空！');
				$('#link').focus();
				return;
			}
			if (Trim(startDate) == ''||Trim(startDate) ==null) {
				$.messager.alert('操作提示', '开始时间不能为空！');
				$('#startDate').focus();
				return;
			}
			if (Trim(endDate) == ''||Trim(endDate) ==null) {
				$.messager.alert('操作提示', '结束时间不能为空！');
				$('#endDate').focus();
				return;
			}
		}
		if(Trim(type) != 'HTML'){
			if (Trim(tabid) == ''||tabid ==null) {
				$.messager.alert('操作提示','请选择tabid！');
				$('#tabid').focus();
				return;
			}
			
		}
		
		$.ajax(
				{
					type : "post",
					url : "appNavigationAction_edit.action",
					data : {
						id:$("#id").val(),
						typeStr:$("#typeStr").val(),
						name:$("#name").val(),
						link:$("#link").val(),
						tabId:$("#tabId").val(),
						sort:$("#sort").val(),
						startDate:$("#startDate").val(),
						endDate:$("#endDate").val(),
						x : Math.random()
					},
					success : function callback(data)
					{data = eval("(" + data + ")");
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
				url : 'appNavigationAction_info.action',
				data : {
					id : row.id,
					x : Math.random()
				},
				success : function callback(data) {
					var obj = eval('(' + data + ')');
					form.form('load', obj);
					form.url = 'appNavigationAction_edit.action';
					var type = $('#typeStr').val();
					if (Trim(type) == 'NATIVE'||Trim(type) == 'READONLY') {
						$('#linkname').hide();
						$('#tabidname').show();
						$('#startdatename').hide();
						$('#enddatename').hide();
						
					}
					if (Trim(type) == 'HTML') {
						$('.tabidname').hide();
						$('#startdatename').show();
						$('#enddatename').show();
						$('#linkname').show();
					}
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
								url : "appNavigationAction_delete.action",
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
	function sort(v){
			
			sort_window.window('open');
			
			$("#vid").val(v);
			
	
		}
	function setSort(){
		var sort = $('#vsort').val();
		if (Trim(sort) == '') {
			$.messager.alert('操作提示', '位置不能为空！');
			$('#vsort').focus();
			return;
		}
		
		if(isNaN(sort)){
			$.messager.alert('操作提示', '请输入正确的数字!');
			return;
		}
		if(sort<0){
			$.messager.alert('操作提示', '排序位置不能小于0!');
			return;
		}
			$.ajax({
					type : "post",
					url : "appNavigationAction_setSort.action",
					data : {
							id:$("#vid").val(),
							sort:$("#vsort").val(),
							x : Math.random()
					},
					success : function callback()
				{
							$.messager.alert('操作提示', '操作成功!');
							sort_window.window('close');
							reloadgrid();
						}
			});
		}
	
	function select(){
		var type = $('#typeStr').val();
		if (Trim(type) == 'NATIVE'||Trim(type) == 'READONLY') {
			$('#linkname').hide();
			$('#tabidname').show();
			$('#startdatename').hide();
			$('#enddatename').hide();
			
		}
		if (Trim(type) == 'HTML') {
			$('.tabidname').hide();
			$('#startdatename').show();
			$('#enddatename').show();
			$('#linkname').show();
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

			<div id="appNavigationDiv">
				<table id="appNavigation"></table>
			</div>
		</div>

		<div id="add-window" title="窗口"
			style="width: 700px; height: 250px;">

			<div style="padding: 20px 20px 20px 20px;">
				<s:form action="">
					<table>
						<tr>
							<td width="50px">导航名称</td>
							<td><input id="name" name="name" type="text" /> <font
								color="red"> *</font>
							</td>
						</tr>
						<tr>
							<td>展示类型</td>
							<td><select id="typeStr" name="showType" onchange="select();"
								style="width: 100px;">
									<option value="-1">---请选择---</option>
									<option value="NATIVE">单列显示</option>
									<option value="READONLY">双列显示</option>
									<option value="HTML">HTML页面</option>
							</select> <font color="red"> *</font>
							</td>
						</tr>
						<tr id="linkname"  style="display:none">
							<td>链接</td>
							<td><input id="link" name="link" type="text" size="30"/> <font
								color="red"> *</font>
							</td>
						</tr>
						<tr id="tabidname"  style="display:none">
							<td>tabid</td>
							<td><select id="tabId" name="tabId"
								style="width: 100px;">
									<option value="1">1</option>
									<option value="2">2</option>
									<option value="3">3</option>
									<option value="4">4</option>
							</select> <font color="red"> *</font><font
								color="red"> </font>
							</td>
						</tr>
						<tr id="startdatename"  style="display:none">
						<td>开始时间</td>
						<td><input id="startDate" name="startDate"
							readonly="readonly"
							onFocus="var endDate=$dp.$('endDate');WdatePicker({skin:'whyGreen',dateFmt:'yyyy-MM-dd HH:mm:ss',onpicked:function(){endDate.focus();},maxDate:'#F{$dp.$D(\'endDate\')}'})"
							style="width: 170px;"> <font
							color="red"> *</font>
							<label><font color="red"></font></label>
						</td>
					</tr>
					<tr  id="enddatename" style="display:none">
						<td>结束时间</td>
						<td><input id="endDate"
								name="endDate"
								onFocus="WdatePicker({skin:'whyGreen',dateFmt:'yyyy-MM-dd HH:mm:ss',minDate:'#F{$dp.$D(\'startDate\')}'})"
								readonly="readonly" style="width: 170px;"> <font
							color="red"> *</font>
							<label><font color="red"></font></label>
						</td>
					</tr>
					</table>
					<input id="id" name="id" type="hidden" />
					<input id="sort" name="sort" type="hidden" />
				</s:form>
			</div>
			<div style="text-align: center; padding: 5px;">
				<a href="javascript:void(0)" onclick="saveEntity()" id="btn-save"
					icon="icon-search" class='easyui-linkbutton'>保存</a> <a
					href="javascript:void(0)" onclick="closeWindows()" id="btn-cancel"
					icon="icon-cancel" class='easyui-linkbutton'>取消</a>
			</div>
		</div>
	<div id="sort-window" title="修改位置"
		style="text-align: center; padding: 5px;">

		<div style="padding: 20px 20px 20px 20px;">

			<s:form action="">
				<table>
					<tr>
						<td>	
							<input id="vsort" name="vsort" type="text" size="20" />
						</td>
					</tr>
				</table>
			</s:form>
		</div>
		<div style="text-align: center; padding: 5px;">
			<a href="javascript:void(0)" onclick="setSort()" id="btn-save"
				icon="icon-search" class='easyui-linkbutton'>保存</a> 
		</div>
	</div>

	</div>
	<input id="vid" name="vid" type="hidden" value=""/>
	</div>
</body>
</html>
