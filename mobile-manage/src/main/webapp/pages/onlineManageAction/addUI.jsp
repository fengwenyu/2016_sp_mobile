<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<html>
<head>
	<title>产品上传页面</title>
	<%@ include file="/pages/public/common.jspf"%>
	<script type="text/javascript">
		$(function(){
			// 填充渠道列表
			$('#channelList').datagrid({
				nowrap: false,
				striped: true,
				collapsible:true,
				url:'${pageContext.request.contextPath }/channelAction_datagridList.action',
				remoteSort: true,
				showFooter:true,
				width: 880,
				loadMsg:'加载数据...',
				singleSelect :true,
				onSelect : function(rowIndex, rowData){				
					$("#channelNumTemp").val(rowData.channelNum);
					$("#channelNum").val(rowData.channelNum);
				},
				frozenColumns:[[
					{field:'select',checkbox:true}
				]],	
				columns:[[
						{field:'channelNum',title:'渠&nbsp;道&nbsp;编&nbsp;号',width:200,align:'center'},
						{field:'channelName',title:'渠&nbsp;道&nbsp;名&nbsp;称',width:240,align:'center'},
						{field:'createTime',title:'创&nbsp;建&nbsp;时&nbsp;间',width:380,align:'center'}	
				]],				
				pagination:true,
				rownumbers:true
			});
		});
		//渠道查询
		function reloadgrid(){
			//执行查询操作
			var pager = $('#channelList').datagrid('getPager');//得到DataGrid页面
				pager.pagination({
					pageNumber:1
				});
			$('#channelList').datagrid('options').queryParams = {channelName:$("#channelName").val()};
			$("#channelList").datagrid('reload');
			$("#channelList").datagrid('resize'); 
    	}
		//表单验证
		function checkForm(){
			var versionPattern = /^([0-9]+)([.]{1})([0-9]+)([.]{1})([0-9]+)$/;	   			
			var numPattern = /^[0-9]*$/;
			//var suffixPattern = /^.*?\.(apk|ipa)$/;
			var suffixPattern = /^([a-zA-Z]+)([_]{1})([a-zA-Z]+)([_]{1})v([0-9]+)([.]{1})([0-9]+)([.]{1})([0-9]+)([.]{1})(apk|ipa)$/;
			if($("#versionLatest").val()==""){
				$.messager.alert('错误','产品版本号不能为空!','error',function(r){
					$("#versionLatest").focus();
				});	
				return false;
			}
			if(!versionPattern.test($("#versionLatest").val())){
				$.messager.alert('错误','产品版本号格式不正确!','error',function(r){
					$("#versionLatest").focus();
				});	
				return false;
			}
			if($("#versionForceMax").val()==""){
				$.messager.alert('错误','强制升级最大版本号不能为空!','error',function(r){
					$("#versionForceMax").focus();
				});	
				return false;
			}
	    	if(!versionPattern.test($("#versionForceMax").val())){
				$.messager.alert('错误','强制升级最大版本号格式不正确!','error',function(r){
					$("#versionForceMax").focus();
				});	
				return false;
			}
	    	var versionLatest= new Array(); 
	    	var versionForceMax= new Array(); 
			versionLatest = $('#versionLatest').val().split('.'); 
			versionForceMax = $('#versionForceMax').val().split('.'); 
			for (i = 0; i < 3; i++){    
				if(parseInt(versionLatest[i])>parseInt(versionForceMax[i])){
					break;
				}else if(parseInt(versionLatest[i])<parseInt(versionForceMax[i])){
					$.messager.alert('错误','版本大小顺序不正确!','error');	
					return false;
				}
   			} 	
			<%-- 
	    	if($("#upload").val()==""){
				$.messager.alert('错误','附件不能为空!','error');	
				return false;
			}
	    	if(!(suffixPattern.test($("#upload").val()))) {
				$.messager.alert('错误','附件后缀不正确!','error');	
			--%>
				return false;
			}
	    	if($("#prompt").val()==""){
				$.messager.alert('错误','提示信息不能为空!','error',function(r){
					$("#prompt").focus();
				});	
				return false;
			}
	    	if($("#channelNum").val()==""){
				$.messager.alert('错误','渠道号不能为空!','error',function(r){
					$("#channelNum").focus();
				});	
				return false;
			}
			if(!numPattern.test($("#channelNum").val())){
				$.messager.alert('错误','必须是数字!','error',function(r){
					$("#channelNum").focus();
				});	
				return false;
			}
		
	</script>
</head>
<body>
<!-- 标题显示 -->
<div id="Title_bar">
    <div id="Title_bar_Head">
        <div id="Title_Head"></div>
        <div id="Title"><!--页面标题-->
            <img border="0" width="13" height="13" src="${pageContext.request.contextPath}/style/images/title_arrow.gif"/>&nbsp;产品上传
        </div>
        <div id="Title_End"></div>
    </div>
</div>
<!--显示表单内容-->
<div id=MainArea>
    <form action="<%=basePath%>onlineManageAction_add.do" enctype="multipart/form-data" id="onlineManageForm" method="post" onsubmit="return checkForm();">
    	<input type="hidden" name="userLoginName" value="${user.loginName}"/>
    	<input type="hidden" name="channelNum" id="channelNum"/>
        <div class="ItemBlock_Title1">
        </div>
        <!-- 表单内容显示 -->
        <div class="ItemBlockBorder">
            <div class="ItemBlock">
                <table cellpadding="0" cellspacing="0" class="mainForm">
                    <tr>
                    	<td>产品号</td>
                        <td>
                           <s:select name="productNum" id="productNum" cssClass="SelectStyle" 
                           	list="#productList" listKey="productNum" listValue="productName"> 
                           </s:select>
	      				   <font color="red"> *</font>
                        </td>
                    </tr>
                    <tr>
                    	<td>产品版本号</td>
                        <td>
                        	<s:textfield name="versionLatest" cssClass="InputStyle" id="versionLatest"/><font color="red"> *&nbsp;（格式：1.1.1）</font>
                        </td>
                    </tr>
                    <tr>
                    	<td>强制升级最大版本号</td>
                        <td><s:textfield name="versionForceMax" cssClass="InputStyle" id="versionForceMax"/><font color="red"> *&nbsp;（格式：1.1.1）</font></td>
                    </tr>
                    <%--
                    <tr>
						<td>请选择产品文件</td>
                        <td><input type="file" name="upload" class="InputStyle" style="width:315px;" id="upload"/></td>
                    </tr>
                     --%>
                    <tr>
						<td>&nbsp;</td>
                        <td>（文件名称格式：<font color="red">字母_字母_v数字.数字.数字.apk </font>或者 <font color="red">字母_字母_v数字.数字.数字.ipa</font>）</td>
                    </tr>
                    <tr>
						<td>提示信息</td>
                        <td><s:textarea name="prompt" cssClass="InputStyle" id="prompt" cssStyle="height:70px;"/><font color="red"> *</font></td>
                    </tr>
                    <tr>
						<td>渠道编号</td>
                        <td>
                       	 	<s:textfield cssClass="InputStyle" id="channelNumTemp" disabled="true"/><font color="red"> * 请从下面的渠道列表中勾选</font>
		      			</td>
                    </tr>
                    <tr>
						<td><font color="red">说明:</font></td>
                        <td><font color="red">产品版本号 &gt; 强制升级最大版本号</font></td>
                    </tr>
                </table>
            </div>
        </div>
        <!-- 表单操作 -->
        <div id="InputDetailBar">
            <input type="image" src="${pageContext.request.contextPath}/style/images/save.png"/>
        </div>
    </form>
</div>
<div class="Description" style="margin-left: 50px;">
	<div style="height: 30px;margin: 0 0 10px 0">
		<table border=0 cellspacing=3 cellpadding=5>
			<tr>
				<td>渠道名称：<s:textfield name="channelName" cssClass="InputStyle" id="channelName"/></td>
				<td><input type="image" src="${pageContext.request.contextPath}/style/blue/images/button/query.PNG" onclick="reloadgrid();"/></td>
			</tr>
		</table>
	</div>
	<div id="channelListDiv">
		<table id="channelList"></table>
	</div>
</div>
</body>
</html>
	