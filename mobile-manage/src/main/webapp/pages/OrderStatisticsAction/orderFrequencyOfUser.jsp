<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<html>
<head>
    <title>订单统计分析列表</title>
    <%@ include file="/pages/public/common.jspf"%>
    <script type="text/javascript">
    $(function(){
			// 填充错误日志列表
			$('#orderFrequencyOfUserList').datagrid({
				nowrap: false,
				striped: true,
				collapsible:true,
				url:'<%=basePath%>orderStatisticsAction_orderFrequencyOfUserQuery.action',
				remoteSort: true,
				showFooter:true,
				singleSelect :true,
				width: 545,
				loadMsg:'加载数据...',
				columns:[[
					{field:'num',title:'下单次数',width:60,align:'center'},	
					{field:'username',title:'用户名',width:200,align:'center'},
					{field:'userId',title:'用户id',width:250,align:'center'}
				]],				
				pagination:true,
				rownumbers:true
			});
		});
		function check(){
			var date = $("#date").attr("value");
			if(date==""){
				$.messager.alert('警告','时间不能为空！','warning');
				return;
			}
			var params = {
				date:$("#date").attr("value")
				};
			reloadgrid(params);
		}
	   function reloadgrid(params){
			//执行查询操作
			var pager = $('#orderFrequencyOfUserList').datagrid('getPager');//得到DataGrid页面
				pager.pagination({
					pageNumber:1
				});
			$('#orderFrequencyOfUserList').datagrid('options').queryParams = params;
			$("#orderFrequencyOfUserList").datagrid('reload');
			$("#orderFrequencyOfUserList").datagrid('resize'); 
    	}
	</script>
</head>
<body>
	<div id="Title_bar">
	    <div id="Title_bar_Head"> 
	        <div id="Title_Head"></div>
	        <!--页面标题-->
	        <div id="Title">
	            <img border="0" width="13" height="13" src="${pageContext.request.contextPath}/style/images/title_arrow.gif"/> 用户下单频率查询
	        </div>
	        <div id="Title_End"></div>
	    </div>
	</div>
	<div id="QueryArea">
		<s:form action="orderStatisticsAction_orderFrequencyOfUserQuery" id="orderFrequencyOfUser" method="post">
			<div style="">
				<table border=0>
					<tr>
					    <td>日期：<s:textfield cssClass="InputStyle" onclick="WdatePicker({isShowClear:true,readOnly:true,dateFmt:'yyyy-MM-dd'});" id="date" name="date"/></td>
						<td><a href="#" onclick="check();">查询</a></td>
					</tr>
					<!-- 
					<tr>
                    	<td>
                    		次数：
                    		<select name="num" class="SelectStyle" id="num">
	   							<option value="1">1</option>
	   							<option value="2">2</option>
	   							<option value="3">3</option>
	   							<option value="4">4</option>
	   							<option value="5">5</option>
	   						</select>
	   					</td>
					    <td><a href="#" onclick="check();">查询</a></td>
                    </tr>
					 -->
				</table>
			</div>
		</s:form>
	</div>
	<div id="orderFrequencyOfUserListDiv">
		<table id="orderFrequencyOfUserList"></table>
	</div>
</body>
</html>