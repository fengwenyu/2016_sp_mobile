<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<html>
<head>
    <title>操作日志列表</title>
    <%@ include file="/pages/public/common.jspf"%>
    <script type="text/javascript">
	$(function(){
			// 填充操作日志列表
			$('#feedbackList').datagrid({
				nowrap: false,
				striped: true,
				collapsible:true,
				url:'<%=basePath%>feedbackAction_query.do',
				remoteSort: true,
				showFooter:true,
				singleSelect :true,
				width: 952,
				loadMsg:'加载数据...',	
				columns:[[
						{field:'msg',title:'反&nbsp;馈&nbsp;内&nbsp;容',width:350,align:'center'},	
						{field:'platform',title:'平&nbsp;&nbsp;台',width:58,align:'center'},	
						{field:'product',title:'产&nbsp;品&nbsp;号&nbsp;',width:50,align:'center'},	
						{field:'channel',title:'渠&nbsp;道&nbsp;号&nbsp;',width:50,align:'center'},	
						{field:'ver',title:'版&nbsp;本&nbsp;号&nbsp;',width:50,align:'center'},	
						{field:'createTime',title:'操&nbsp;作&nbsp;时&nbsp;间',width:163,align:'center',sortable:true},	
						{field:'loginName',title:'登&nbsp;录&nbsp;名&nbsp;',width:172,align:'center'}
				]],				
				pagination:true,
				rownumbers:true
			});
		});
		function reloadgrid(){
			var numPattern = /^[0-9]*$/;
			if($("#productNum").val()!="" && !numPattern.test($("#productNum").val())){
					$.messager.alert('错误','产品号必须是数字!','error',function(r){
						$("#productNum").focus();
					});	
					return false;
			}
			if($("#channelNum").val()!="" && !numPattern.test($("#channelNum").val())){
					$.messager.alert('错误','渠道号必须是数字!','error',function(r){
						$("#channelNum").focus();
					});	
					return false;
			}
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
			var pager = $('#feedbackList').datagrid('getPager');    //得到DataGrid页面
				pager.pagination({
					pageNumber:1
				});
			$('#feedbackList').datagrid('options').queryParams = {loginName:$("#loginName").val(),ver:$("#ver").val(),productNum:$("#productNum").val(),channelNum:$("#channelNum").val(),platform:$("#platform").val(),startDate:$("#startDate").val(),endDate:$("#endDate").val()};
			$("#feedbackList").datagrid('reload');
			$("#feedbackList").datagrid('resize'); 
    	}
	</script>
</head>
<body>
	<div id="Title_bar">
	    <div id="Title_bar_Head"> 
	        <div id="Title_Head"></div>
	        <!--页面标题-->
	        <div id="Title">
	            <img border="0" width="13" height="13" src="${pageContext.request.contextPath}/style/images/title_arrow.gif"/> 信息反馈查询
	        </div>
	        <div id="Title_End"></div>
	    </div>
	</div>
	<div id="QueryArea">
		<div>
			<table border=0 cellspacing=3 cellpadding=5>
				<tr>
					<td>登&nbsp;录&nbsp;名：<s:textfield name="loginName" cssClass="InputStyle" id="loginName"/></td>
					<td>&nbsp;</td>
				</tr>
				<tr>
					<td>产&nbsp;品&nbsp;号：<s:textfield name="productNum" cssClass="InputStyle" id="productNum"/></td>
					<td>&nbsp;</td>
				</tr>
				<tr>
					<td>渠&nbsp;道&nbsp;号：<s:textfield name="channelNum" cssClass="InputStyle" id="channelNum"/></td>
					<td>&nbsp;</td>
				</tr>
				<tr>
					<td>版&nbsp;本&nbsp;号：<s:textfield name="ver" cssClass="InputStyle" id="ver"/></td>
					<td>&nbsp;</td>
				</tr>
				<tr>
					<td>平&nbsp;&nbsp;&nbsp;&nbsp;台：
				    	<select name="platform" class="SelectStyle" id="platform">
   							<option value="">请选择平台</option>
   							<option value="ios">IOS</option>
   							<option value="android">Android</option>
   						</select></td>
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
	</div>
	<div id="feedbackListDiv">
		<table id="feedbackList"></table>
	</div>
</body>
</html>