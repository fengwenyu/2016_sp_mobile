<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<html>
<head>
	<title>推送消息配置页面</title>
	<%@ include file="/pages/public/common.jspf"%>
	<script type="text/javascript">
		$(function(){
			// 填充渠道列表
			$('#pushManageIos').datagrid({
				nowrap: false,
				striped: true,
				collapsible:true,
				url:'${pageContext.request.contextPath }/pushManageAction_datagridList.action?platform=ios',
				remoteSort: true,
				showFooter:true,
				width: 1000,
				loadMsg:'加载数据...',
				singleSelect :true,
				frozenColumns:[[
					{field:'select',checkbox:true}
				]],	
				columns:[[
						{field:'id',title:'删除',width:50,align:'center',
							formatter:function(value,rec){
								var platform = "ios";
								return '<a href=javascript:void(0) style="color:#4574a0; font-weight:bold;" onclick=remove("' + value+ '","' + platform+ '");>' + "删&nbsp;除" + '</a>';
							}
						},
						{field:'platform',title:'平台',width:40,align:'center'},
						{field:'productNum',title:'产品号',width:50,align:'center'},
						{field:'channelNum',title:'渠道号',width:50,align:'center'},
						{field:'title',title:'标题',width:50,align:'center'},
						{field:'notice',title:'显示文字',width:310,align:'center'},
						{field:'pushType',title:'发送状态',width:60,align:'center',
							formatter:function(value,rec){
								return value == 0 ? '未发送' :'已发送';
							}
						},
						{field:'showTime',title:'显&nbsp;示&nbsp;时&nbsp;间',width:90,align:'center'},
						{field:'createTime',title:'创&nbsp;建&nbsp;时&nbsp;间',width:90,align:'center'},
						{field:'username',title:'用户名',width:150,align:'center'},
						{field:'action',title:'事件',width:40,align:'center'},
						{field:'actionarg',title:'参数',width:140,align:'center'},
						{field:'actionobj',title:'详细参数',width:340,align:'center'}
				]],				
				pagination:true,
				rownumbers:true
			});
			// 填充渠道列表
			$('#pushManageAndroid').datagrid({
				nowrap: false,
				striped: true,
				collapsible:true,
				url:'${pageContext.request.contextPath }/pushManageAction_datagridList.action?platform=android',
				remoteSort: true,
				showFooter:true,
				width: 1000,
				loadMsg:'加载数据...',
				singleSelect :true,
				frozenColumns:[[
					{field:'select',checkbox:true}
				]],	
				columns:[[
						{field:'id',title:'删除',width:50,align:'center',
							formatter:function(value,rec){
								var platform = "android";
								return '<a href=javascript:void(0) style="color:#4574a0; font-weight:bold;" onclick=remove("' + value+ '","' + platform+ '");>' + "删&nbsp;除" + '</a>';
							}
						},
						{field:'platform',title:'平台',width:50,align:'center'},
						{field:'productNum',title:'产品号',width:50,align:'center'},
						{field:'channelNum',title:'渠道号',width:50,align:'center'},
						{field:'notice',title:'显示文字',width:300,align:'center'},
						{field:'pushType',title:'发送状态',width:60,align:'center',
							formatter:function(value,rec){
								return value == 0 ? '未发送' :'已发送';
							}
						},
						{field:'showTime',title:'显&nbsp;示&nbsp;时&nbsp;间',width:90,align:'center'},
						{field:'endTime',title:'结&nbsp;束&nbsp;时&nbsp;间',width:90,align:'center'},
						{field:'createTime',title:'创&nbsp;建&nbsp;时&nbsp;间',width:90,align:'center'},
						{field:'username',title:'用户名',width:150,align:'center'},
						{field:'action',title:'事件',width:40,align:'center'},
						{field:'actionarg',title:'参数',width:140,align:'center'},
						{field:'actionobj',title:'详细参数',width:340,align:'center'},
				]],			
				pagination:true,
				rownumbers:true
			});
		});
		function remove(id,platform){
			$.messager.confirm('确认操作', '确认要删除选中的信息吗?', function(r){
				if (r){
					$.ajax({
				        type: "POST",
				        url: "${pageContext.request.contextPath }/pushManageAction_delete.do",
				        data:"id="+id+"&platform="+platform+"&timeStamp="+new Date().getTime(),
				        success: function(data){
							data = eval("("+data+")");
			                if(data.returnCode == '1' ){ 
		                    	 $.messager.alert('提示','删除成功！','info');
	                    	     if(data.platform == 'ios' ){
	                    	     	$("#pushManageIos").datagrid('reload');
	                    	     }else{
	                    	    	 $("#pushManageAndroid").datagrid('reload');
	                    	     }
		                    }else{ 
		                        $.messager.alert('提示','删除失败!<br>原因：' + data.returnInfo,'error');
		                    } 
				        }
				    });	
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
        <div id="Title"><!--页面标题-->
            <img border="0" width="13" height="13" src="${pageContext.request.contextPath}/style/images/title_arrow.gif"/>&nbsp;推送消息
        </div>
        <div id="Title_End"></div>
    </div>
</div>
<!--显示表单内容-->
<div class="Description" style="margin-left: 50px;">
	<div id="pushManageIosDiv">
		<table id="pushManageIos"></table>
	</div>
	<br/>
	<br/>
	<br/>
	<br/>
	<div id="pushManageAndroidDiv">
		<table id="pushManageAndroid"></table>
	</div>
</div>
</body>
</html>
	