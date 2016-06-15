<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<html>
<head>
    <title>操作日志列表</title>
    <%@ include file="/pages/public/common.jspf"%>
    <script type="text/javascript">
	$(function(){
			// 填充操作日志列表
			$('#operateLogList').datagrid({
				nowrap: false,
				striped: true,
				collapsible:true,
				url:'${pageContext.request.contextPath }/operateLogAction_list.action',
				remoteSort: true,
				showFooter:true,
				width: 952,
				loadMsg:'加载数据...',
				frozenColumns:[[
					{field:'select',checkbox:true}
				]],	
				columns:[[
						{field:'id',title:'详&nbsp;细&nbsp;内&nbsp;容',width:70,align:'center',
							formatter:function(value,rec){
								return '<a href=javascript:void(0) style="color:#4574a0; font-weight:bold;" onclick=showLogDet("' + value + '");>' + "查&nbsp;看" + '</a>';
							}
						},	
						{field:'content',title:'日&nbsp;志&nbsp;内&nbsp;容',width:558,align:'center'},/*,
							formatter:function(value,rec){
								value = value.length > 30 ? value.substring(0,30)+"......" : value;
								return value;
							}*/
						{field:'operateTime',title:'操&nbsp;作&nbsp;时&nbsp;间',width:163,align:'center',sortable:true},	
						{field:'operateUserName',title:'操&nbsp;作&nbsp;人&nbsp;',width:100,align:'center'}
				]],				
				pagination:true,
				rownumbers:true,
				toolbar:[{
					id:'btnDelete',
					text:'删除',					
					iconCls:'icon-remove',
					handler:function(){
					  remove()
					}
				}]
			});
		});
	function remove(){
			var rows = $("#operateLogList").datagrid('getSelections');
			if( rows.length == 0 ){
				$.messager.alert('警告','请至少选择一行记录！','warning');
				return;
			}
			var ids="";
			for( var i = 0; i < rows.length; i ++ ){
				ids = ids + rows[i].id + "-";
			}
			$.messager.confirm('确认操作', '确认要删除选中的信息吗?', function(r){
				if (r){
					$.ajax({
				        type: "POST",
				        url: "${pageContext.request.contextPath }/operateLogAction_delete.action",
				        data:"ids="+ids+"&timeStamp="+new Date().getTime(),
				        success: function(data){
							data = eval("("+data+")");
			                if(data.returnCode == '1' ){ 
		                    	 $.messager.alert('提示','删除成功！','info');
		                    	 $("#operateLogList").datagrid('reload');
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
			var pager = $('#operateLogList').datagrid('getPager');    //得到DataGrid页面
				pager.pagination({
					pageNumber:1
				});
			$('#operateLogList').datagrid('options').queryParams = {operateUserName:$("#operateUserName").val(),startDate:$("#startDate").val(),endDate:$("#endDate").val()};
			$("#operateLogList").datagrid('reload');
			$("#operateLogList").datagrid('resize'); 
    	}
   	function showLogDet(id){
		  $.ajax({
	         type: "POST",
	         url: "${pageContext.request.contextPath }/operateLogAction_detail.action",
	         data:"id="+id+"&timeStamp="+new Date().getTime(),
	         success: function(data){
				data = eval("("+data+")");
				$("#operateUserNameDet").val(data.operateUserName);
				$("#content").val(data.content);
	         }
	      });	
          $("#QueryArea").hide();
	      $("#operateLogListDiv").hide();
	      $("#operateLogDet").show();
	
	}
	// 重置查询条件输入框
	function searchReset(){
			//$('#startDate').val( '' ); 	// 查询起始时间
			//$('#endDate').val( '' ); 	// 查询结束时间
			$('#operateUserName').val( '' ); 	
	}
	function goback(){
	      $("#operateLogDet").hide();
	      $("#QueryArea").show();
	      $("#operateLogListDiv").show();
	}
	</script>
</head>
<body>
	<div id="Title_bar">
	    <div id="Title_bar_Head"> 
	        <div id="Title_Head"></div>
	        <!--页面标题-->
	        <div id="Title">
	            <img border="0" width="13" height="13" src="${pageContext.request.contextPath}/style/images/title_arrow.gif"/> 操作日志查询
	        </div>
	        <div id="Title_End"></div>
	    </div>
	</div>
	<div id="QueryArea">
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
	</div>
	<div id="operateLogListDiv">
		<table id="operateLogList"></table>
	</div>
	<div id="operateLogDet" style="display: none;">
        <div class="ItemBlockBorder">
            <div class="ItemBlock">
                <table cellpadding="0" cellspacing="0" class="mainForm">
                    <tr style="padding:  0 0 20px 0">
                    	<td>操作人姓名</td>
                        <td><input id="operateUserNameDet" class="InputStyle"></input></td>
                    </tr>
                    <tr>
                    	<td>操作日志内容</td>
                        <td><textarea id="content" class="TextareaStyle" style="height: 80px;"></textarea></td>
                    </tr>
                    <tr>
                    	<td>&nbsp;</td>
	       				<td><img src="${pageContext.request.contextPath}/style/images/goBack.png" style="margin: 10px 0 0 0" onclick="goback();"/></td>
                    </tr>
                </table>
            </div>
        </div>
	</div>
</body>
</html>