<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<html>
<head>
    <title>上线管理</title>
    <%@ include file="/pages/public/common.jspf"%>
    <script type="text/javascript">
	$(function(){
			// 填充上线管理列表
			$('#onlineManageList').datagrid({
				nowrap: false,
				striped: true,
				collapsible:true,
				url:'<%=basePath%>onlineManageAction_query.do',
				remoteSort: true,
				showFooter:true,
				singleSelect :true,
				width: 1030,
				loadMsg:'加载数据...',
				columns:[[
						{field:'id',title:'删&nbsp;&nbsp;除',width:70,align:'center',
							formatter:function(value,rec){
								return '<a href=javascript:void(0) style="color:#4574a0; font-weight:bold;" onclick=remove("' + value + '");>' + "删&nbsp;&nbsp;除" + '</a>';
							}
						},	
						{field:'summary',title:'摘要',width:210,align:'center',sortable:false},	
						{field:'downloadPath',title:'下载地址',width:305,align:'center',sortable:false,
							/*formatter:function(value,rec){
								return value == null ? "" :'<a href=javascript:void(0) style="color:#4574a0; font-weight:bold;" onclick=downAttachment("' + value + '");>' + value + '</a>';
							}*/
							formatter:function(value,rec){
								return value == null ? '' :'<a href='+value+' style="color:#4574a0; font-weight:bold;">' + value + '</a>';
							}
						},	
						{field:'prompt',title:'提示信息',width:360,align:'center',
							formatter:function(val,rec){
								return '<textarea cols=42" rows="4" readonly="true" wrap="off" background="#ff">' + val + '</textarea>';
						}},
						{field:'createTime',title:'操作时间',width:100,align:'center',sortable:false}
				]],				
				pagination:true,
				rownumbers:true
				/*,toolbar:[{
					id:'btnDelete',
					text:'删除',					
					iconCls:'icon-remove',
					handler:function(){
					  remove()
					}
				}]*/
			});
	       //reloadgrid();
		});
	function remove(id){
			$.messager.confirm('确认操作', '确认要删除选中的信息吗?', function(r){
				if (r){
					$.ajax({
				        type: "POST",
				        url: "${pageContext.request.contextPath }/onlineManageAction_delete.do",
				        data:"id="+id+"&timeStamp="+new Date().getTime(),
				        success: function(data){
							data = eval("("+data+")");
			                if(data.returnCode == '1' ){ 
		                    	 $.messager.alert('提示','删除成功！','info');
		                    	 $("#onlineManageList").datagrid('reload');
		                    }else{ 
		                        $.messager.alert('提示','删除失败!<br>原因：' + data.returnInfo,'error');
		                    } 
				        }
				    });	
				}
			});
		}
		function reloadgrid(){
			//验证用户输入日期是否合法
			/*var startDate = $("#startDate").attr("value");   
			var endDate = $("#endDate").attr("value");
			var startDateArr = startDate.split("-");   
			var endDateArr = endDate.split("-");   
			startDate = new Date(startDateArr[0],parseInt(startDateArr[1])-1,startDateArr[2]);   
			endDate = new Date(endDateArr[0],parseInt(endDateArr[1])-1,endDateArr[2]);
			if(startDate > endDate){
				$.messager.alert('错误','开始日期必须小于或等于结束日期！！！','error');
				return;
			} */
			//执行查询操作
			var pager = $('#onlineManageList').datagrid('getPager');    //得到DataGrid页面
				pager.pagination({
					pageNumber:1
				});
			$('#onlineManageList').datagrid('options').queryParams = {operateUserName:$("#operateUserName").val()};
			$("#onlineManageList").datagrid('reload');
			$("#onlineManageList").datagrid('resize'); 
        
    	}
	// 重置查询条件输入框
	function searchReset(){
			//$('#startDate').val( '' ); 	// 查询起始时间
			//$('#endDate').val( '' ); 	// 查询结束时间
			$('#operateUserName').val( '' ); 	
	}
	//文件下载
	/*function downAttachment(downloadPath){
		$("#downloadForm input").remove();
		$("#downloadForm").append("<input type='hidden' name='downloadPath' value='" + downloadPath + "'/>");
		$("#downloadForm").submit();
	}*/
	</script>
</head>
<body>
	<div id="Title_bar">
	    <div id="Title_bar_Head"> 
	        <div id="Title_Head"></div>
	        <!--页面标题-->
	        <div id="Title">
	            <img border="0" width="13" height="13" src="${pageContext.request.contextPath}/style/images/title_arrow.gif"/>&nbsp;上线管理
	        </div>
	        <div id="Title_End"></div>
	    </div>
	</div>
					<%--
	<div id="QueryArea">
		<div style="height: 30px">
			<table border=0 cellspacing=3 cellpadding=5>
				<tr>
					<td>操作人：<s:textfield name="operateUserName" cssClass="InputStyle" id="operateUserName"/></td>
					<td><input type="image" src="${pageContext.request.contextPath}/style/blue/images/button/query.PNG" onclick="reloadgrid();"/></td>
				    <td align="left" class="label_style">起始日期：</td>
				    <td><input type="text" class="input_style" onClick="WdatePicker({isShowClear:true,readOnly:true,dateFmt:'yyyy-MM-dd',maxDate:'#F{$dp.$D(\'endDate\')}'})"   value="" id="startDate" name="startDate" /></td>
				    <td align="left" class="label_style">结束日期：</td>
				    <td><input type="text" class="input_style" onClick="WdatePicker({isShowClear:true,readOnly:true,dateFmt:'yyyy-MM-dd',minDate:'#F{$dp.$D(\'startDate\')}',maxDate:'%y-%M-%d'})"   value="" id="endDate" name="endDate" /></td> 
				</tr>
			</table>
		</div>
	</div>
				     --%>
	<div id="onlineManageListDiv">
		<table id="onlineManageList"></table>
	</div>
</body>
</html>