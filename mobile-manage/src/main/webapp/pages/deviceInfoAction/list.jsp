<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<html>
<head>
    <title>操作日志列表</title>
    <%@ include file="/pages/public/common.jspf"%>
    <script type="text/javascript">
	$(function(){
			// 填充操作日志列表
			$('#deviceInfoList').datagrid({
				nowrap: false,
				striped: true,
				collapsible:true,
				url:'${pageContext.request.contextPath }/deviceInfoAction_query.action',
				remoteSort: true,
				showFooter:true,
				singleSelect :true,
				width: 1160,
				loadMsg:'加载数据...',
				frozenColumns:[[
					{field:'select',checkbox:true}
				]],	
				columns:[[
						{field:'platform',title:'平台',width:70,align:'center'},
						{field:'osv',title:'系统版本',width:70,align:'center',sortable:true},	
						{field:'apn',title:'网络类型',width:90,align:'center'},
						{field:'resolution',title:'分辨率',width:100,align:'center'},
						{field:'phoneModel',title:'手机型号',width:100,align:'center'},
						{field:'ip',title:'IP地址',width:100,align:'center'},
						{field:'operator',title:'运营商',width:80,align:'center'},
						{field:'phoneType',title:'手机类型',width:80,align:'center'},
						{field:'ua',title:'浏览器UA',width:100,align:'center'},
						{field:'browser',title:'浏览器',width:80,align:'center'},
						{field:'browserInfo',title:'浏览器详细信息',width:100,align:'center'},
						{field:'imei',title:'IMEI标识',width:220,align:'center'},
						{field:'createTime',title:'创建时间',width:100,align:'center'}
				]],				
				pagination:true,
				rownumbers:true
			});
		});
		function reloadgrid(){
			//验证用户输入日期是否合法
			var startDate = $("#startDate").attr("value");   
			var endDate = $("#endDate").attr("value");
			var startDateArr = startDate.split("-");   
			var endDateArr = endDate.split("-");   
			startDate = new Date(startDateArr[0],parseInt(startDateArr[1])-1,startDateArr[2]);   
			endDate = new Date(endDateArr[0],parseInt(endDateArr[1])-1,endDateArr[2]);
			if(startDate > endDate){
				$.messager.alert('错误','开始日期必须小于或等于结束日期！！！','error');
				return;
			} 
			//执行查询操作
			var pager = $('#deviceInfoList').datagrid('getPager');    //得到DataGrid页面
				pager.pagination({
					pageNumber:1
				});
			$('#deviceInfoList').datagrid('options').queryParams = {operateUserName:$("#operateUserName").val(),startDate:$("#startDate").val(),endDate:$("#endDate").val()};
			$("#deviceInfoList").datagrid('reload');
			$("#deviceInfoList").datagrid('resize'); 
    	}
	</script>
</head>
<body>
	<div id="Title_bar">
	    <div id="Title_bar_Head"> 
	        <div id="Title_Head"></div>
	        <!--页面标题-->
	        <div id="Title">
	            <img border="0" width="13" height="13" src="${pageContext.request.contextPath}/style/images/title_arrow.gif"/> 设备查询
	        </div>
	        <div id="Title_End"></div>
	    </div>
	</div>
	<div id="QueryArea">
	<%-- 
		<div>
			<table border=0 cellspacing=3 cellpadding=5>
				<tr>
					<td>操&nbsp;作&nbsp;人：<s:textfield name="operateUserName" cssClass="InputStyle" id="operateUserName"/></td>
					<td>&nbsp;</td>
				</tr>
				<tr><!-- dateFmt:'yyyy-MM-dd HH:mm:ss', -->
				    <td>起始日期：<s:textfield cssClass="InputStyle" onclick="WdatePicker({isShowClear:true,readOnly:true});" id="startDate" name="startDate"/></td>
				    <td>&nbsp;</td>
				</tr>
				<tr>
				    <td>结束日期：<s:textfield cssClass="InputStyle" onclick="WdatePicker({isShowClear:true,readOnly:true});" id="endDate" name="endDate" /></td>
					<td><input type="image" src="${pageContext.request.contextPath}/style/blue/images/button/query.PNG" onclick="reloadgrid();"/></td>
				</tr>
			</table>
		</div>
	 --%>
	</div>
	<div id="deviceInfoListDiv">
		<table id="deviceInfoList"></table>
	</div>
</body>
</html>