<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<html>
<head>
<title>首页活动管理</title>
<%@ include file="/pages/public/common.jspf"%>
<style>
.upload1{position:relative;}
.ipt_text{padding:2px;border:1px solid #aaa;}
.btn{border:1px solid #ccc;background:#fff}
.file{position:absolute;left:0;top:0;opacity:0;filter:alpha(opacity:0);}
</style>
<script type="text/javascript">
	$(function() {
		grid=$('#findManage').datagrid(
				{
					nowrap: false,
					striped: true,
					collapsible:true,
					url : '${pageContext.request.contextPath }/findManageAction_query.action?typeStr=activity',
					remoteSort: true,
					showFooter:true,
					singleSelect :true,
					frozenColumns : [ [ {
						field : 'select',
						checkbox : true
					} ] ],

					columns : [ [ {
						field : 'type',
						title : '类型',
						width : 50,
						align : 'center'
					},{
						field : 'name',
						title : '专题标题',
						width : 160,
						align : 'center',
						editor : 'text'
					},{
						field : 'activityID',
						title : '活动ID',
						width : 100,
						align : 'center',
						editor : 'text'
					},{
						field : 'showDateDesc',
						title : '移动端开始时间',
						width : 150,
						align : 'center',
						editor : 'text'
					},{
						field : 'mobilePreTime',
						title : '移动端预热时间',
						width : 150,
						align : 'center',
						editor : 'text'
					},{
						field : 'imgUrl',
						title : '图片',
						width : 150,
						formatter:function(value){
							if(value!=null){
								return "<img src='"+value+"' width='160' height='110'/>"
							}
							
						}
					},{
						field : 'isSlider',
						title : '是否为App轮播图',
						width : 100,
						align : 'center',
						formatter:function(value){
							if(value!=null){
								if(value!="否"){
									return "<img src='"+value+"' width='160' height='110'/>"
								}else{
									return value;
								}
								
							}
							
						}
					},{
						field : 'sort',
						title : '位置顺序',
						width : 50,
						align : 'center',
						formatter:function(value,rec){
							
							return'<a style="color:#4574a0; font-weight:bold;"  href=javascript:sort("' + rec.id + '");>' + value + '</a>'
							
							
						}
					},{
						field : 'desc',
						title : '描述',
						width : 150,
						align : 'center',
						editor : 'text'
					},{
						field : 'status',
						title : '状态',
						width : 50,
						align : 'center',
						editor : 'text'
					},{
						field : 'subtitle',
						title : '专题副标题',
						width : 150,
						align : 'center',
						editor : 'text'
					}

					] ],

					title : '最新活动列表',
					width : '100%',
					height : 500,

				
					pagination : true,
					rownumbers:true,
					toolbar : [ {

						id : 'btnsave',
						text : '修改',
						iconCls : 'icon-edit',
						handler : update
					},{id:'btnDelete',
						text:'删除',					
						iconCls:'icon-remove',
						handler : remove
					},{
						text:'设为轮播位',					
						iconCls:'icon-edit',
						handler : setAppSliderImg
						},{
						text:'取消轮播设置',					
						iconCls:'icon-edit',
						handler : cancelAppSliderImg
					}]

				});
		
		staticgrid=$('#staticatc').datagrid({
					nowrap: false,
					striped: true,
					collapsible:true,
					url : '${pageContext.request.contextPath }/findManageAction_query.action?typeStr=static',
					remoteSort: true,
					showFooter:true,
					singleSelect :true,
					frozenColumns : [ [ {
						field : 'select',
						checkbox : true
					} ] ],

					columns : [ [ {
						
						field : 'showDateDesc',
						title : '移动端开始时间',
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
						editor : 'text'
					},{
						field : 'imgUrl',
						title : '图片',
						width : 150,
						align : 'center',
						formatter:function(value){
							if(value!=null){
								return "<img src='"+value+"' width='160' height='110'/>"
							}
							
						}
					},{
						field : 'desc',
						title : '描述',
						width : 150,
						align : 'center',
						editor : 'text'
					},{
						field : 'title',
						title : '标题',
						width : 150,
						align : 'center',
						editor : 'text'
					}

					] ],

					title : '静态活动列表',
					width : '100%',
					pagination : true,
					rownumbers:true,
					toolbar : [ {

						id : 'btnsave',
						text : '修改',
						iconCls : 'icon-edit',
						handler : supdate
					},{id:'btnDelete',
						text:'删除',					
						iconCls:'icon-remove',
						handler : sremove
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
		
		activity_window = $('#activity-search-window').window({
			closed : true,
			closable : true,
			modal : false,
			minimizable : false,
			maximizable : false,
			resizable : false
		});
		activityform = activity_window.find('form');
		update_activity = $('#updateActivity-window').window({
			closed : true,
			closable : true,
			modal : false,
			minimizable : false,
			maximizable : false,
			resizable : false,
			draggable:true,
			top:200
		});
		upActivityForm= update_activity.find('form');
		imgText_window = $('#imgText-window').window({
			closed : true,
			closable : true,
			modal : false,
			minimizable : false,
			maximizable : false,
			resizable : false,
			draggable:true,
			top:200
		});
		imgTextForm = imgText_window.find('form');
		
		slider_window = $('#slider-window').window({
			closed : true,
			closable : true,
			modal : false,
			minimizable : false,
			maximizable : false,
			resizable : false,
			draggable:true,
			top:300
		});
		sliderForm = slider_window.find('form');
		static_window = $('#static-window').window({
			closed : true,
			closable : true,
			modal : false,
			minimizable : false,
			maximizable : false,
			resizable : false
			
		});
		staticForm = static_window.find('form');
		sort_window = $('#sort-window').window({
			closed : true,
			
			modal : false,
			minimizable : false,
			maximizable : false,
			resizable : false
		});
		form = add_window.find('form');
	});

	var add_window;
	function reloadgrid(){
		//执行查询操作
		var pager = $('#findManage').datagrid('getPager');//得到DataGrid页面
			pager.pagination({
				pageNumber:1
			});

		$('#findManage').datagrid('options').queryParams = {};
		$("#findManage").datagrid('reload');
		$("#findManage").datagrid('resize'); 
	}
	function reloadstaticgrid(){
		//执行查询操作
		var pager = $('#staticatc').datagrid('getPager');//得到DataGrid页面
			pager.pagination({
				pageNumber:1
			});

		$('#staticatc').datagrid('options').queryParams = {};
		$("#staticatc").datagrid('reload');
		$("#staticatc").datagrid('resize'); 
	}

	
	function closeWindows() {
		update_activity.window('close');
	}
	function update(id,type) {
		var row = grid.datagrid('getSelected');
		if (row) {
			if (grid.datagrid('getSelections').length > 1) {
				$.messager.alert('warning', '只能选择一条记录!', 'warning');
				return;
			}
			if(row.type=='活动'){
				update_activity.window('open');
				upActivityForm.form('clear');
				$.ajax({
					type : "post",
					url : 'findManageAction_info.action',
					data : {
						id : row.id,
						x : Math.random()
					},
					success : function callback(data) {
						var obj = eval('(' + data + ')');
						upActivityForm.form('load', obj);
						
					}
				});
			}else{
				imgText_window.window('open');
				imgTextForm.form('clear');
				$.ajax({
					type : "post",
					url : 'findManageAction_info.action',
					data : {
						id : row.id,
						x : Math.random()
					},
					success : function callback(data) {
						var obj = eval('(' + data + ')');
						imgTextForm.form('load', obj);
						
					}
				});
				
			}

		} else {
			$.messager.alert('警告', '请选择一条数据!', 'warning');
		}
	}	
	
	function supdate() {
		var row = staticgrid.datagrid('getSelected');
		if (row) {
			if (staticgrid.datagrid('getSelections').length > 1) {
				$.messager.alert('warning', '只能选择一条记录!', 'warning');
				return;
			}
			static_window.window('open');
			staticForm.form('clear');
			$.ajax({
				type : "post",
				url : 'findManageAction_info.action',
				data : {
					id : row.id,
					x : Math.random()
				},
				success : function callback(data) {
					var obj = eval('(' + data + ')');
					staticForm.form('load', obj);
					
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
			$.messager.confirm('提示', '确认删除么?', function(id){
				if (id){
					$.ajax({
						type : "post",
						url : "findManageAction_delete.action",
						data : {
							id :row.id,
							sort :row.sort,
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
	function sremove() {
		var row = staticgrid.datagrid('getSelected');
		if (row) {
			if (staticgrid.datagrid('getSelections').length > 1) {
				$.messager.alert('warning', '只能选择一条记录!', 'warning');
				return;
			}
			
			$.messager.confirm('提示', '确认删除么?', function(id){
				if (id){
					$.ajax({
						type : "post",
						url : "findManageAction_delete.action",
						data : {
							id :row.id,
							
							x : Math.random()
						},
						success : function callback()
						{
							$.messager.alert('操作提示', '操作成功!');
							reloadstaticgrid();
						
						}
					});

				}
			});

		} else {
			$.messager.alert('警告', '请选择一条数据!', 'warning');
		}

	}
	
	function setAppSliderImg() {
		var row = grid.datagrid('getSelected');
		if (row) {
			if (grid.datagrid('getSelections').length > 1) {
				$.messager.alert('warning', '只能选择一条记录!', 'warning');
				return;
			}
			$.messager.confirm('提示', '确认设置为app轮播图么?', function(id){
				slider_window.window('open');
				sliderForm.form('clear');
				$('#idslider').val(row.id);
						
			});

		} else {
			$.messager.alert('警告', '请选择一条数据!', 'warning');
		}

	}
	
	function setDisplay() {
		var row = staticgrid.datagrid('getSelected');
		if (row) {
			if (staticgrid.datagrid('getSelections').length > 1) {
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
					url : "findManageAction_setDisplay.action",
					data : {
						id :row.id,
						x : Math.random()
					},
					success : function callback(data)
					{
						data = eval("(" + data + ")");
						if ("success" == data.returnCode) {
							$.messager.alert('操作提示', '操作成功!');
							reloadstaticgrid();
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
		var row = staticgrid.datagrid('getSelected');
		if (row) {
			if (staticgrid.datagrid('getSelections').length > 1) {
				$.messager.alert('warning', '只能选择一条记录!', 'warning');
				return;
			}
			$.messager.confirm('提示', '确认关闭显示么?', function(id){
				if (id){
					$.ajax({
							type : "post",
							url : "findManageAction_setNotDisplay.action",
							data : {
								id :row.id,
								x : Math.random()
							},
							success : function callback(data)
								{
									data = eval("(" + data + ")");
									if ("success" == data.returnCode) {
										$.messager.alert('操作提示', '操作成功!');
										reloadstaticgrid();
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
	function confirmAppSliderImg() {
		sliderForm.form('submit', {
			url : 'findManageAction_setAppSliderImg.action',
			cache : false,

			success : function callback(data) {
					data = eval("(" + data + ")");
					if ("success" == data.returnCode) {
						$.messager.alert('操作提示', '操作成功!');
						slider_window.window('close');
						reloadgrid();
					} else {
						$.messager.alert('操作提示', data.returnInfo);
						

					}
			}
		});

	}
	function cancelAppSliderImg() {
		var row = grid.datagrid('getSelected');
		if (row) {
			if (grid.datagrid('getSelections').length > 1) {
				$.messager.alert('warning', '只能选择一条记录!', 'warning');
				return;
			}
			if(row.isSlider=='否'){
				$.messager.alert('warning', '并非APP轮播图。无需取消!', 'warning');
				return;
			}
			$.messager.confirm('提示', '确认取消么?', function(id){
				if (id){
					$.ajax({
						type : "post",
						url : "findManageAction_cancelAppSliderImg.action",
						data : {
							id :row.id,
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
	
	
	function closeImgText() {
		imgText_window.window('close');
	}
	
	function closeSliderText() {
		slider_window.window('close');
	}
	function closeStaticText() {
		static_window.window('close');
	}
	function confirmStatic() {
		staticForm.form('submit', {
			url : 'findManageAction_editeStaticAtc.action',
			cache : false,
			success : function callback(data) {
				data = eval("(" + data + ")");
				if ("success" == data.returnCode) {
					$.messager.alert('操作提示', '操作成功!');
					static_window.window('close');
					reloadstaticgrid();
				} else {
					$.messager.alert('操作提示', data.returnInfo);
					static_window.window('close');

				}
			}
		});
	}
	
	function saveImgText() {
			var imgUrl= $('#imgUrl').val();
			var imgWidth= $('#imgWidth').val();
			var imgHeight= $('#imgHeight').val();
			var description=$('#description').val();
			var showStartDate=$('#showStartDate').val();
			var showEndDate=$('#showEndDate').val();
			var title=$('#title').val();
			var getUrl=$('#getUrl').val();
			if (Trim(imgUrl) == '') {
				
				$.messager.alert('操作提示', '图片不能为空！');
				$('#imgUrl').focus();
				return;
			}
			
				if(Trim(imgWidth) == ''){
					$.messager.alert('操作提示', '图片宽度不能为空！');
					$('#imgWidth').focus();
					return;
				}
				if(Trim(imgHeight) == ''){
					$.messager.alert('操作提示', '图片高度不能为空！');
					$('#imgHeight').focus();
					return;
				}
			
			if (Trim(showStartDate) == '') {
				$.messager.alert('操作提示', '开始时间不能为空！');
				$('#showStartDate').focus();
				return;
			}
			if (Trim(showEndDate) == '') {
				$.messager.alert('操作提示','结束时间不能为空！');
				$('#showEndDate').focus();
				return;
			}
			if (Trim(title) == '') {
				$.messager.alert('操作提示','标题不能为空！');
				$('#title').focus();
				return;
			}
			if (Trim(getUrl) == '') {
				$.messager.alert('操作提示','跳转链接不能为空！');
				$('#getUrl').focus();
				return;
			}
		
			imgTextForm.form('submit', {
				url : 'findManageAction_editeImgText.action',
				cache : false,

				success : function callback(data) {
					data = eval("(" + data + ")");
					if ("success" == data.returnCode) {
						$("#isu").val("1");
						$.messager.alert('操作提示', '操作成功!');
						imgText_window.window('close');
						reloadgrid();
					} else {

						$.messager.alert('操作提示', data.returnInfo);

						imgText_window.window('close');

					}
				}
			});
		}
		
	function updateActivity(){
		var activityID=$("#activityIdUA").val();
		var title=$("#nameUA").val();
		var imgUrl=$("#imgUrlUA").val();
		var imgWidth=$("#imgWidthUA").val();
		var imgHeight=$("#imgHeightUA").val();
		var description=$("#descUA").val();
		
		if (Trim(activityID) == '') {
			$.messager.alert('操作提示', '活动编号不能为空！');
			$('#activityIdUA').focus();
			return;
		}
		if (Trim(title) == '') {
			$.messager.alert('操作提示', '专题标题不能为空！');
			$('#nameUA').focus();
			return;
		}
		if(Trim(imgUrl) !='' ){
			if (Trim(imgWidth) == '') {
				$.messager.alert('操作提示','图片宽度不能为空！');
				$('#imgWidthUA').focus();
				return;
			}
			if (Trim(imgHeight) == '') {
				$.messager.alert('操作提示','图片高度不能为空！');
				$('#imgHeightUA').focus();
				return;
			}
		}
		if (Trim(description) == '') {
			$.messager.alert('操作提示','描述不能为空！');
			$('#descUA').focus();
			return;
		}
		
		
		$.messager.confirm('提示', '确认修改吗?', function(id) {	
			if(id){
				$.ajax({
					type : "post",
					url : 'findManageAction_updateActivity.action',
					data : {
				
						id:$("#idUA").val(),
					
						activityID:$("#activityIdUA").val(),
						title:$("#nameUA").val(),
						subTitle:$("#subtitleUA").val(),
						imgUrl:$("#imgUrlUA").val(),
						imgWidth:$("#imgWidthUA").val(),
						imgHeight:$("#imgHeightUA").val(),
						description:$("#descUA").val(),
					
						x : Math.random()
					},
	
					success : function callback(data) {
						data = eval("(" + data + ")");
						if ("success" == data.returnCode) {
							$("#isu").val("1");
						
							update_activity.window('close');
							$.messager.alert('操作提示', '操作成功!');
							reloadgrid();
							//location.href = "findManageAction_input.action";
						} else {
	
							$.messager.alert('操作提示', data.returnInfo);
	
							update_imgText.window('close');
	
						}
	
					}
				});
			}
			
		});
	}
	
	function setTop(){
		var row = grid.datagrid('getSelected');
		if (row) {
			if (staticgrid.datagrid('getSelections').length > 1) {
				$.messager.alert('warning', '只能选择一条记录!', 'warning');
				return;
			}
			$.messager.confirm('提示', '确认置顶么?', function(id){
				if (id){
					$.ajax({
							type : "post",
							url : "findManageAction_setTop.action",
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
	
	function cancelTop(){
		var row = grid.datagrid('getSelected');
		if (row) {
			if (grid.datagrid('getSelections').length > 1) {
				$.messager.alert('warning', '只能选择一条记录!', 'warning');
				return;
			}
			$.messager.confirm('提示', '确认取消置顶么?', function(id){
				if (id){
					$.ajax({
							type : "post",
							url : "findManageAction_cancelTop.action",
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
	
	function sort(v){
		sort_window.window('open');
		$("#vid").val(v);
	}
	function setSort(){
		var vsort = $('#vsort').val();
		if(Trim(vsort)== ''){
			$.messager.alert('操作提示', '请填写排序位置!');
			return;
		}
		if(isNaN(vsort)){
			$.messager.alert('操作提示', '请输入正确的数字!');
			return;
		}
		if(vsort<1){
			$.messager.alert('操作提示', '排序位置不能小于1!');
			return;
		}
		$.ajax({
				type : "post",
				url : "findManageAction_setSort.action",
				data : {
					id:$("#vid").val(),
					sort:$("#vsort").val(),
					x : Math.random()
				},
				success : function callback(data)
				{
					data = eval("(" + data + ")");
					if ("success" == data.returnCode) {
						$.messager.alert('操作提示', '操作成功!');
						sort_window.window('close');
						reloadgrid();
					} else {
						$.messager.alert('操作提示', data.returnInfo);
					}
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
					src="${pageContext.request.contextPath}/style/images/title_arrow.gif" />&nbsp;首页活动管理
			</div>
			<div id="Title_End"></div>
		</div>
	</div>
	<!--显示表单内容-->

	<div id="QueryArea">
	<div>
			<table border=0 cellspacing=3 cellpadding=5>
				
				<tr><!-- dateFmt:'yyyy-MM-dd HH:mm:ss', -->
				   
				
				 <td><s:a action="findManageAction_creatNewActivity"><b><font
								color="red"> 新建活动</font></b></s:a></td>
				 <td><s:a action="findManageAction_creatNewImgText"><b><font
								color="red"> 新建图文</font></b></s:a></td>
				 <td><s:a action="findManageAction_creatNewStaticAtc"><b><font
								color="red"> 新建活动页</font></b></s:a></td>
								
				</tr>
				
			</table>
		</div>
	
	</div>


	<div id=MainArea>
		<div class="Description" style="margin-left: 10px;">
			
			<div id="staticDiv" padding: 5px;>
				<table id="staticatc"></table>
			</div>
			</br>
			<div id="findManageDiv">
				<table id="findManage"></table>
			</div>
		</div>

		
		<div id="activity-search-window" title="配置活动窗口"
		style="width: 700px; height: 600px;" draggable="false">
		<div id="activity-search-entity-window"
			style="width: 650px; height: 250px; padding: 20px 20px 20px 20px;"
			class="search_content">
			<s:form action="">
				<div style="padding: 10px 10px 10px 10px;">
					<table border=0 cellspacing=2 cellpadding=5>
						<tr>
							<td>时间:</td>
							<td><input id="startDate" name="startDate" value="起始时间"
								readonly="readonly"
								onFocus="var endDate=$dp.$('endDate');WdatePicker({onpicked:function(){endDate.focus();},maxDate:'#F{$dp.$D(\'endDate\')}'})"
								style="width: 70px;">--<input id="endDate"  value="结束时间"
									name="endDate"
									onFocus="WdatePicker({minDate:'#F{$dp.$D(\'startDate\')}'})"
									readonly="readonly" style="width: 70px;">
							</td>
						
							<td>活动编号或者活动名称</td>
							<td><input type="text" name="keyword" value=""
								id="keyword" style="width: 90px;" onkeyup="isLegal(this)" />
							</td>

						</tr>
					</table>
			</s:form>
		</div>
		<table id="activity-search-entity-table"></table>
		<div style="text-align: center; padding: 5px;">
			<a href="javascript:void(0)" onclick="reloadgrid()" id="btn-save"
				icon="icon-search" class='easyui-linkbutton'>查询</a>
				 <a
				href="javascript:void(0)" onclick="closeWindows()" id="btn-cancel"
				icon="icon-cancel" class='easyui-linkbutton'>取消</a>
		</div>
		
	<div id="updateActivity-window" title="修改窗口"
		style="text-align: left; padding: 5px;width: 600px; height: 500px;">

		<div style="padding: 20px 20px 20px 20px;">

			<s:form action="">
				<table>
					<input id="idUA" name="id" type="hidden" />
					
				<tr>
						<td width="90px">
						活动ID
						</td>
							<td><input id="activityIdUA" name="activityID" type="text" /><font
							color="red">*</font> </td>
					</tr>
					<tr>
						<td>
						专题标题
						</td>
							<td><input id="nameUA" name="name" type="text" /> <font
							color="red">*</font></td>
					</tr>
					<tr>
						<td>
						专题副标题
						</td>
							<td><input id="subtitleUA" name="subtitle" type="text" /> </td>
					</tr>
					</table>
					<table>
					<tr>
						
						<td><font
							color="red">如图片链接为空，则使用原设置图片。</font></td>
					</tr>
					</table>
					<table>
					<tr>
						<td width="90px">
						图片链接
						</td>
							<td><input id="imgUrlUA" name="imgUrl" type="text" size="70"/> </td>
					</tr>
					</table>
					<table>
					<tr><td>图片尺寸:<td>宽度</td>
							<td><input id="imgWidthUA" name="imgWidth" type="text" size="10"/>
							</td>

							<td>高度</td>
							<td><input id="imgHeightUA" name="imgHeight" type="text" size="10"/>
								
							</td></tr>
							</table>
							<table>
						<tr>
						<td width="90px">
						描述
						</td>
							<td><textarea rows="5" cols="30" id="descUA" name="desc" type="text" ></textarea><font
							color="red">*</font> </td>
					</tr>
				</table>
			</s:form>
		</div>
		<div style="text-align: center; padding: 5px;">
			<a href="javascript:void(0)" onclick="updateActivity()" id="btn-save"
				icon="icon-search" class='easyui-linkbutton'>保存</a> <a
				href="javascript:void(0)" onclick="closeWindows()" id="btn-cancel"
				icon="icon-cancel" class='easyui-linkbutton'>取消</a>
		</div>
	</div>

	<div id="imgText-window" title="修改窗口"
		style=" width: 600px; height: 500px;"">

		<div style="padding: 10px 10px 10px 10px;">

			<s:form action=""  method="POST" enctype="multipart/form-data"> 
				<table>
					<input id="id" name="id" type="hidden" />
					<tr>
						<td>图片</td>
						<td>
							<input type="text" name="imgUrl" id="imgUrl" >  
							<input type="button" value="浏览" onclick="imgUrlFile.click()" style="border:1px solid #ccc;background:#fff">  
							<input type="file" id="imgUrlFile" name="imgUrlFile" style="display:none" onchange="imgUrl.value=this.value">
							<font
							color="red">*</font>
						 </td>
					</tr>
				</table>
				<table>
					<tr>
						<td>图片宽度</td>
						<td><input id="imgWidth" name="imgWidth" type="text" /> </td>
					</tr>
					<tr>
						<td>图片高度</td>
						<td><input id="imgHeight" name="imgHeight" type="text" /><font
							color="red">*</font> </td>
					<tr>
				<table>
					<tr>
						<td>跳转链接</td>
						<td><input id="getUrl" name="getUrl" type="text" size="70"/><font
							color="red">*</font> </td>
					</tr>
					</table>
				<table>
					<tr>
						<td>移动端开始时间</td>
						<td><input id="showStartDate" name="showStartDate"
							readonly="readonly"
							onFocus="var endDate=$dp.$('showEndDate');WdatePicker({skin:'whyGreen',dateFmt:'yyyy-MM-dd HH:mm:ss',onpicked:function(){showEndDate.focus();},maxDate:'#F{$dp.$D(\'showEndDate\')}'})"
							style="width: 170px;"> <font
							color="red"> *</font>
						</td>
					</tr>	
					<tr>
						<td>移动端结束时间</td><td><input id="showEndDate"
								name="showEndDate"
								onFocus="WdatePicker({skin:'whyGreen',dateFmt:'yyyy-MM-dd HH:mm:ss',minDate:'#F{$dp.$D(\'showStartDate\')}'})"
								readonly="readonly" style="width: 170px;"> <font
							color="red"> *</font>
						</td>
					</tr>
					<tr>
						<td>移动端预热时间</td>
						<td>	
							<input id="mobilePreTime" name="mobilePreTime"
							readonly="readonly"
							 onclick="WdatePicker({isShowClear:true,readOnly:true,dateFmt:'yyyy-MM-dd HH:mm:ss'});"
							style="width: 170px;">
						</td>
					</tr>
					<tr>
				
						<td>文字描述</td>
						<td><textarea rows="5" cols="30" id="description"
										name="description"></textarea>
						</td>
					</tr>
					<tr>
						<td>标题</td>
						<td><input id="title" name="title" type="text" /> <font
							color="red">*</font></td>
					</tr>
				</table>
			</s:form>
		</div>
		<div style="text-align: center; padding: 5px;">
			<a href="javascript:void(0)" onclick="saveImgText()" id="btn-save"
				icon="icon-search" class='easyui-linkbutton'>保存</a> <a
				href="javascript:void(0)" onclick="closeImgText()" id="btn-cancel"
				icon="icon-cancel" class='easyui-linkbutton'>取消</a>
		</div>
	</div>
	
	<div id="slider-window" title="修改窗口"
		style=" width: 300px; height: 200px;"">

		<div style="padding: 10px 10px 10px 10px;">

			<s:form action=""  method="POST" enctype="multipart/form-data"> 
				<table>
					<input id="idslider" name="id" type="hidden" />
					<tr>
						<td>APP轮播图片</td>
						<td>
						
						
							
							<div class="upload1">
								<input type="text" name="sliderImgUrl" class="ipt_text"  id="sliderImgUrl" >  
								<input type="button" value="浏览" onclick="sliderImgUrlFile.click()" >  
								<input type="file" id="sliderImgUrlFile" name="sliderImgUrlFile"  class="file"  onchange="document.getElementById('sliderImgUrl').value=this.value">
							</div>
						 </td>
						 
					</tr>
				</table>
				
			</s:form>
		</div>
		<div style="text-align: center; padding: 5px;">
			<a href="javascript:void(0)" onclick="confirmAppSliderImg()" id="btn-save"
				icon="icon-search" class='easyui-linkbutton'>保存</a> <a
				href="javascript:void(0)" onclick="closeSliderText()" id="btn-cancel"
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
		<div id="static-window" title="修改窗口"
		style=" width: 500px; height: 400px;"">

		<div style="padding: 10px 10px 10px 10px;">

			<s:form action=""  method="POST"  enctype="multipart/form-data"> 
			
				<table>
					<tr>
					<input id="getUrl" name="getUrl" type="hidden" />
					<input id="display" name="display" type="hidden" />
					<input id="id" name="id" type="hidden" />
					<input id="isSlider" name="isSlider" type="hidden" />
					<input id="sort" name="sort" type="hidden" />
					<td>开始时间</td>
						<td><input id="showStartDate" name="showStartDate"
							readonly="readonly"
							onFocus="var endDate=$dp.$('showEndDate');WdatePicker({skin:'whyGreen',dateFmt:'yyyy-MM-dd HH:mm:ss',onpicked:function(){showEndDate.focus();},maxDate:'#F{$dp.$D(\'showEndDate\')}'})"
							style="width: 170px;"> <font
							color="red"> *</font>
							</td>
						
							<tr>	<td>结束时间</td><td><input id="showEndDate"
								name="showEndDate"
								onFocus="WdatePicker({skin:'whyGreen',dateFmt:'yyyy-MM-dd HH:mm:ss',minDate:'#F{$dp.$D(\'showStartDate\')}'})"
								readonly="readonly" style="width: 170px;"> <font
							color="red"> *</font>
						</td>
					</tr>
				</table>
					<table >
						<tr>
						<td><font
								color="red">为兼容老版本APP，请填写以下项</font></td></tr>
					
						</table>
					
            	<table>
				
					<tr>
						<td width="80px">图片</td>
						<td>
							<div class="upload1">
								<input type="text" class="ipt_text"  name="imgUrl"  id="staticImgUrl"/>
								<input type="button" class="btn" value="浏 览" />
								<input type="file"  id="imgUrlFile" name="imgUrlFile" class="file"  onchange="document.getElementById('staticImgUrl').value=this.value" />
							<font
							color="red"> *</font>
							<label><font color="red"><span>${checkImgUrl }</span></font></label>
							</div>
						 </td>
					</tr>
				</table>
				<table>
					<tr>
						<td width="80px">图片宽度</td>
						<td><input id="imgWidth" name="imgWidth" type="text" /> <font
							color="red"> *</font>
							<label><font color="red"><span>${checkImgWidth}</span></font></label></td>
					</tr>
					<tr>
						<td>图片高度</td>
						<td><input id="imgHeight" name="imgHeight" type="text" /> <font
							color="red"> *</font>
							<label><font color="red"><span>${checkImgHeight}</span></font></label> </td>
					<tr>
				
						<td>文字描述</td>
						<td><textarea rows="5" cols="30" id="description"
										name="description"></textarea>
						</td>
					</tr>
					<tr>
						<td>标题</td>
						<td><input id="title" name="title" type="text" /><font
							color="red"> *</font>
							<label><font color="red"><span>${checkTitle}</span></font></label> </td>
					</tr>
					
				</table>
				
				
			</s:form>
		</div>
		<div style="text-align: center; padding: 5px;">
			<a href="javascript:void(0)" onclick="confirmStatic()" id="btn-save"
				icon="icon-search" class='easyui-linkbutton'>保存</a> <a
				href="javascript:void(0)" onclick="closeStaticText()" id="btn-cancel"
				icon="icon-cancel" class='easyui-linkbutton'>取消</a>
		</div>
	</div>
	<input id="vid" name="vid" type="hidden" value=""/>
</body>
</html>
