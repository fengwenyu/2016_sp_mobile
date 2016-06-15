<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<html>
<head>
    <title>操作日志列表</title>
    <%@ include file="/pages/public/common.jspf"%>
    <script type="text/javascript">
	$(function(){
			// 填充错误日志列表
			$('#errorLogList').datagrid({
				nowrap: false,
				striped: true,
				collapsible:true,
				url:'<%=basePath%>errorLogAction_query.do',
				remoteSort: true,
				showFooter:true,
				singleSelect :true,
				width: 1030,
				loadMsg:'加载数据...',
				columns:[[
						/*{field:'id',title:'点&nbsp;击&nbsp;查&nbsp;看',width:80,align:'center',
							formatter:function(value,rec){
								return '<a href=javascript:void(0) style="color:#4574a0; font-weight:bold;" onclick=showLogDet("' + value + '");>' + "查&nbsp;看" + '</a>';
							}
						},*/	
				{field:'id',title:'编号',width:50,align:'center'},	
				{field:'summary',title:'摘要',width:240,align:'center'},
				{field:'shortmsg',title:'简短信息',width:130,align:'center'},
				{field:'longmsg',title:'详细信息',width:468,align:'center',
					formatter:function(val,rec){
						return '<textarea cols="54" rows="12" readonly="true" wrap="off" background="#ff">' + val + '</textarea>';
					}},
				{field:'createTime',title:'创建时间',width:100,align:'center'}
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
		});
	function remove(){
			var rows = $("#errorLogList").datagrid('getSelections');
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
				        url: "${pageContext.request.contextPath }/errorLogAction_delete.do",
				        data:"ids="+ids+"&timeStamp="+new Date().getTime(),
				        success: function(data){
							data = eval("("+data+")");
			                if(data.returnCode == '1' ){ 
		                    	 $.messager.alert('提示','删除成功！','info');
		                    	 $("#errorLogList").datagrid('reload');
		                    }else{ 
		                        $.messager.alert('提示','删除失败!<br>原因：' + data.returnInfo,'error');
		                    } 
				        }
				    });	
				}
			});
						
		}
	function checkId(){
		var numPattern = /^[0-9]*$/;
		if($("#errorLogId").val()!="" && !numPattern.test($("#errorLogId").val())){
				$.messager.alert('错误','日志ID必须是数字!','error',function(r){
					$("#errorLogId").focus();
				});	
				return false;
		}
		var params = {
				id:$("#errorLogId").val()
				};
		reloadgrid(params);
	}
	function checkForm(){
		var versionPattern = /^([0-9]+)([.]{1})([0-9]+)([.]{1})([0-9]+)$/;	
		if($("#productVersion").val()!="" && !versionPattern.test($("#productVersion").val())){
				$.messager.alert('错误','产品版本号格式不正确!','error',function(r){
					$("#productVersion").focus();
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
		var params = {
				platform:$("#platform").val(),
				productNum:$("#productNum").val(),
				productVersion:$("#productVersion").val(),
				channelNum:$("#channelNumSelect").val(),
				startDate:$("#startDate").val(),
				endDate:$("#endDate").val()
				};
		reloadgrid(params);
	}
	function reloadgrid(params){
			//执行查询操作
			var pager = $('#errorLogList').datagrid('getPager');//得到DataGrid页面
				pager.pagination({
					pageNumber:1
				});
			$('#errorLogList').datagrid('options').queryParams = params;
			$("#errorLogList").datagrid('reload');
			$("#errorLogList").datagrid('resize'); 
    	}
   	function showLogDet(id){
		  $.ajax({
	         type: "POST",
	         url: "${pageContext.request.contextPath }/errorLogAction_detail.do",
	         data:"id="+id+"&timeStamp="+new Date().getTime(),
	         success: function(data){
				data = eval("("+data+")");
				$("#operateUserNameDet").val(data.operateUserName);
				$("#content").val(data.content);
	         }
	      });	
          $("#QueryArea").hide();
	      $("#errorLogListDiv").hide();
	      $("#errorLogDet").show();
	
	}
	// 重置查询条件输入框
	function searchReset(){
			//$('#startDate').val( '' ); 	// 查询起始时间
			//$('#endDate').val( '' ); 	// 查询结束时间
			$('#operateUserName').val( '' ); 	
	}
	function goback(){
	      $("#errorLogDet").hide();
	      $("#QueryArea").show();
	      $("#errorLogListDiv").show();
	}
	function setChannelNumInput(){
		$("#channelNumInput").val($("#channelNumSelect").val());
	}
	function setChannelNumSelect(){
		var numPattern = /^[0-9]*$/;
		if($("#channelNumInput").val()!="" && !numPattern.test($("#channelNumInput").val())){
				$.messager.alert('错误','渠道编号必须是数字!','error',function(r){
					$("#channelNumInput").focus();
				});	
				return false;
		}
		var flag = true;
		$("#channelNumSelect").children("option").each(function(){
            if($("#channelNumInput").val() == $(this).val()){
            	$(this).attr("selected",true);
            	flag= false;
            	return false;
             }
        });
		if(flag){
			 $('#channelNumSelect option:first').attr('selected','selected'); 
		}
	}
	</script>
</head>
<body>
	<div id="Title_bar">
	    <div id="Title_bar_Head"> 
	        <div id="Title_Head"></div>
	        <!--页面标题-->
	        <div id="Title">
	            <img border="0" width="13" height="13" src="${pageContext.request.contextPath}/style/images/title_arrow.gif"/> 错误日志查询
	        </div>
	        <div id="Title_End"></div>
	    </div>
	</div>
	<div id="QueryArea">
		<div style="">
			<table border=0>
				<tr>
				    <td>手机平台：
				    	<select name="platform" class="SelectStyle" id="platform">
   							<option value="">请选择平台</option>
   							<option value="ios">IOS</option>
   							<option value="android">Android</option>
   						</select>
				    </td>
				    <td>&nbsp;</td>
				</tr>
				<tr>
				     <td>产品编号：
				      	   <s:select name="productNum" id="productNum" cssClass="SelectStyle" 
                           	list="#productList" listKey="productNum" listValue="productName"
                           	headerKey="" headerValue="请选择产品名称"> 
                           </s:select>
                     </td>
				     <td>&nbsp;</td>
				</tr>
				<tr>
				    <td>渠道编号：
   				      	   <s:select name="channelNum" id="channelNumSelect" 
                           	list="#channelList" listKey="channelNum" listValue="channelName"
                           	headerKey="" headerValue="请选择渠道名称" onchange="setChannelNumInput();"> 
                           </s:select>
                           <s:textfield id="channelNumInput" onblur="setChannelNumSelect();"/>
				    </td>
				    <td>&nbsp;</td>
				</tr>
				<tr>
				    <td>版本编号：<s:textfield name="productVersion" cssClass="InputStyle" id="productVersion"/></td>
				    <td>&nbsp;</td>
				</tr>
				<tr><!-- dateFmt:'yyyy-MM-dd HH:mm:ss', -->
				    <td>起始日期：<s:textfield cssClass="InputStyle" onclick="WdatePicker({isShowClear:true,readOnly:true});" id="startDate" name="startDate"/></td>
				    <td>&nbsp;</td>
				</tr>
				<tr>
				    <td>结束日期：<s:textfield cssClass="InputStyle" onclick="WdatePicker({isShowClear:true,readOnly:true});" id="endDate" name="endDate" /></td>
					<td><input type="image" src="${pageContext.request.contextPath}/style/blue/images/button/query.PNG" onclick="checkForm();"/></td>
				</tr>
				<tr>
					<td>&nbsp;</td>
					<td>&nbsp;</td>
				</tr>
				<tr>
					<td colspan="2"><div style="border-bottom-width: 1px;border-bottom-style: solid;border-bottom-color: #dbdbdb;"></div></td>
				</tr>
				<tr>
					<td>&nbsp;</td>
					<td>&nbsp;</td>
				</tr>
				<tr>
					<td>日志&nbsp;ID&nbsp;：<s:textfield name="errorLogId" cssClass="InputStyle" id="errorLogId"/></td>
					<td><input type="image" src="${pageContext.request.contextPath}/style/blue/images/button/query.PNG" onclick="checkId();"/></td>
				</tr>
			</table>
		</div>
	</div>
	<div id="errorLogListDiv" style="margin: 100px 0 0 0;">
		<table id="errorLogList"></table>
	</div>
	<%--
	<div id="errorLogDet" style="display: none;">
        <div class="ItemBlockBorder">
            <div class="ItemBlock">
                <table cellpadding="0" cellspacing="0" class="mainForm">
                    <tr style="padding:  0 0 20px 0">
                    	<td>操作人姓名</td>
                        <td><input id="operateUserNameDet" class="InputStyle"></input></td>
                    </tr>
                    <tr>
                    	<td>错误日志内容</td>
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
	 --%>
</body>
</html>