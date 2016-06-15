<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
<title>用户行为统计</title>
<%@ include file="/pages/public/common.jspf"%>

<script type="text/javascript">
$(function(){
	grid =$('#behavioralStatistics').datagrid({
		id : 'getPager',
		name : 'getPager',
		url:'${pageContext.request.contextPath }/userBehavioralStatisticsAction_query.action',
		singleSelect :true,
		columns:[[
			{field:'behaviorName',title:'操作',width:150,align:'left',editor:'text'} ,
			{field:'times',title:'次数',width:100,align:'left',editor:'text'},
			{field:'imeiNum',title:'用户数',width:100,align:'left',editor:'text'}
		
		]],	
		title : '列表',
		width: '100%',
		height :340,
		pageList :
		[ 10, 15, 20, 25, 30, 40, 50 ],
		pageSize : 10,
		pagination:true
	});
});

//查询
function reloadgrid(){
	//执行查询操作
	var pager = $('#behavioralStatistics').datagrid('getPager');//得到DataGrid页面
		pager.pagination({
			pageNumber:1
		});
	var productNum=$("#productNum").val();
		if(Trim(productNum) == ''||Trim(productNum) == '-1'){
			$.messager.alert("操作提示", "请选择产品！");
			return;
		}
	$('#behavioralStatistics').datagrid('options').queryParams = {
		productNum:$("#productNum").val(),
		startDate:$("#startDate").val(),
		endDate:$("#endDate").val()
		};
	$("#behavioralStatistics").datagrid('reload');
	$("#behavioralStatistics").datagrid('resize'); 
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
					src="${pageContext.request.contextPath}/style/images/title_arrow.gif" />&nbsp;用户行为统计
			</div>
			<div id="Title_End"></div>
		</div>
	</div>
	<!--显示表单内容-->

	<div id="QueryArea">
		<div style="height: 60px">

			<table border=0 cellspacing=3 cellpadding=5>
			<tr>
				<td>时间:</td>
				<td><input id="startDate" name="startDate" readonly="readonly"
					onFocus="var endDate=$dp.$('endDate');WdatePicker({onpicked:function(){endDate.focus();},maxDate:'#F{$dp.$D(\'endDate\')}'})"
					style="width: 70px;">--<input id="endDate" name="endDate"
					onFocus="WdatePicker({minDate:'#F{$dp.$D(\'startDate\')}'})" readonly="readonly"
					style="width: 70px;"></td>
			</tr>
			<tr>
				<td>产品</td>
				<td><select id="productNum" name="productNum"
					style="width: 150px;">
					<option value="-1">---请选择---</option>
					<option value="2">尚品iPhone客户端</option>
					<option value="1">奥莱iPhone客户端</option>
					<option value="102">尚品安卓客户端</option>
					<option value="101">奥莱安卓客户端</option>
					</select>
				</td>
					<td><input type="IMAGE"
						src="${pageContext.request.contextPath}/style/blue/images/button/query.PNG"
						/ " onclick="reloadgrid();">
					</td>
				</tr>
			</table>

		</div>

	</div>


	<div id=MainArea>
		<div class="Description" style="margin-left: 10px;">

			<div id="behavioralStatisticsDiv">
				<table id="behavioralStatistics"></table>
			</div>
		</div>

		</div>

	</div>
</body>
</html>
