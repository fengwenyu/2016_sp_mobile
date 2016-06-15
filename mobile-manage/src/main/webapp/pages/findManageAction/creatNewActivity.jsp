<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<html>
<head>
<title>新建活动</title>
<%@ include file="/pages/public/common.jspf"%>

<script type="text/javascript">
	$(function() {
		grid = $('#findManage').datagrid({
					id : 'getPager',
					name : 'getPager',
					nowrap: false,
					striped: true,
					collapsible:true,
					idField : 'id',
					url : '${pageContext.request.contextPath }/findManageAction_datagridList.action',
					singleSelect : true,
					frozenColumns : [ [ {
						field : 'select',
						checkbox : true
					} ] ],
					columns : [ [ {
						field : 'name',
						title : '活动名称',
						width : 100,
						align : 'center',
						editor : 'text'
					}, {
						field : 'subtitle',
						title : '专题副标题',
						width : 100,
						align : 'center',
						editor : 'text'
					} , {
						field : 'timequantum',
						title : '活动开启时间段',
						width : 100,
						align : 'center',
						editor : 'text'
					} , {
						field : 'starttime',
						title : '移动端开始时间',
						width : 150,
						
						align : 'center',
						editor : 'text'
					}, {
						field : 'endtime',
						title : '移动端结束时间',
						
						width : 150,
						align : 'center',
						editor : 'text'
					}, {
						field : 'pretime',
						title : '移动端活动预热时间',
						width : 150,
						align : 'center',
						editor : 'text'
					}, {
						field : 'shareurl',
						title : '分享URL链接(网站页面链接)',
						width : 200,
						align : 'center',
						hidden:'true',
						editor : 'text'
					}, {
						field : 'mobilepic',
						title : 'M站用图链接',
						width : 200,
						align : 'center',
						hidden:'true',
						editor : 'text'
					}, {
						field : 'iphonepic',
						title : '客户端用图',
						width : 200,
						align : 'center',
						hidden:'true',
						editor : 'text'
					}, {
						field : 'status',
						title : '状态',
						width : 50,
						align : 'center',
						formatter : function(value) {
							if (value == '1') {
								return '开启';
							} else if (value == '0') {
								return '关闭';
							}
						}
					}, {
						field : 'desc',
						title : '活动描述',
						width : 100,
						align : 'center',
						editor : 'text'
					},{
						field : 'activityID',
						title : '活动编号',
						width : 100,
						align : 'center',
						editor : 'text'
					}] ],

					title : '列表',
					width : '100%',
					height : 340,

					allowPage : false,
					pagination : false,
					toolbar : [ {

						text : '配置活动',
						iconCls : 'icon-add',
						handler : setActivity

					} ]

				});

	});

	
	function reloadgrid() {

		$('#findManage').datagrid('options').queryParams = {
			startDateStr : $("#startDate").val(),
			endDateStr : $("#endDate").val(),
			keyWord : $("#keyword").val()
		}
		$("#findManage").datagrid('reload');
		$("#findManage").datagrid('resize');
	}


	

	function closeWindows() {

		set_window.window('close');
	}
	
	function setActivity() {
		var row = grid.datagrid('getSelected');
		if (row) {
			if (grid.datagrid('getSelections').length > 1) {
				$.messager.alert('warning', '只能选择一条记录!', 'warning');
				return;
			}
			$.messager.confirm('提示', '确认配置此活动吗?', function(id) {	
					if(id){
						$.ajax({
							type : "post",
							url : 'findManageAction_save.action',
							data : {
								title : row.name,
								activityID : row.activityID,
								startDate : row.starttime,
								endDate : row.endtime,
								status : row.status,
								preTime : row.pretime,
								mobilePic : row.mobilepic,
								iphonePic : row.iphonepic,
								shareUrl : row.shareurl,
								description : row.desc,
								subTitle : row.subtitle,
								typeStr : 'activity',
								
								showStartDate : row.starttime,
								showEndDate : row.endtime,
								status : row.status,
								mobilePreTime : row.pretime,
								x : Math.random()
							},
			
							success : function callback(data) {
								data = eval("(" + data + ")");
								if ("success" == data.returnCode) {
									$("#isu").val("1");
									$.messager.alert('操作提示', '操作成功!');
								
								} else {
			
									$.messager.alert('操作提示', data.returnInfo);
			
								}
			
							}
						});
					}});
			}else{
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
					src="${pageContext.request.contextPath}/style/images/title_arrow.gif" />&nbsp;新建活动
			</div>
			<div id="Title_End"></div>
		</div>
	</div>
	<!--显示表单内容-->
	<div id="QueryArea">
		<div>
			<table border=0 cellspacing=3 cellpadding=5>
				<tr>
					<td>开始时间:</td>
					<td><input id="startDate" name="startDate" value="起始时间"
								readonly="readonly"
								onFocus="var endDate=$dp.$('endDate');WdatePicker({onpicked:function(){endDate.focus();},maxDate:'#F{$dp.$D(\'endDate\')}'})"
								style="width: 70px;">
					</td>
				</tr>
				<tr>
					<td>结束时间</td>
					<td><input id="endDate"  value="结束时间"
												name="endDate"
									onFocus="WdatePicker({minDate:'#F{$dp.$D(\'startDate\')}'})"
									readonly="readonly" style="width: 70px;">
					</td>
				</tr>
				<tr>
						<td>活动编号或者活动名称</td>
						<td><input type="text" name="keyword" value=""
								id="keyword" style="width: 90px;"  />
						</td>
				
				   
				   <td><input type="image" src="${pageContext.request.contextPath}/style/blue/images/button/query.PNG" onclick="reloadgrid();"/></td>
				  <td><input type="image" src="${pageContext.request.contextPath}/style/images/goBack.png" onclick="javascript:history.go(-1);"/></td>
				</tr>
				
			</table>
		</div>
	
	</div>


	<div id=MainArea>
		<div class="Description" style="margin-left: 10px;">

			<div id="findManageDiv">
				<table id="findManage"></table>
			</div>
		</div>
	</div>

		

		

</body>
</html>
