<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<html>
<head>
<title>微信潮流资讯</title>
<%@ include file="/pages/public/common.jspf"%>
<script type="text/javascript" src="${pageContext.request.contextPath}/script/ckeditor/ckeditor.js"></script>
<script type="text/javascript">
	$(function() {
		
		grid = $('#wx')
		.datagrid(
				{
					nowrap: false,
					striped: true,
					collapsible:true,
					url : '${pageContext.request.contextPath }/wxFashionInformationAction_query.action',
					remoteSort: true,
					showFooter:true,
					singleSelect :true,
					
					columns : [ [ {
						field : 'coverImg',
						title : '封面图',
						width : 200,
						align : 'center',
						formatter:function(value){
							if(value!=null){
								return "<img src='"+value+"' width='160' height='110'/>"
							}
							
						}
					},{
						field : 'title',
						title : '标题',
						width : 160,
						align : 'center',
						editor : 'text'
					},{
						field : 'releaseDate',
						title : '发布时间',
						width : 150,
						align : 'center',
						editor : 'text'
					},{
						field : 'sort',
						title : '位置顺序',
						width : 100,
						align : 'center',
						formatter:function(value,rec){
							
							return'<a style="color:#4574a0; font-weight:bold;"  href=javascript:sort("' + rec.id + '");>' + value + '</a>'
							
							
						}
					},{
						field:'id',title:'操&nbsp;&nbsp;作',width:200,align:'center',
						formatter:function(value,rec){
							
							return '<a style="color:#4574a0; font-weight:bold;" href="wxFashionInformationAction_info.action?id='+ value + '">' + "编&nbsp;&nbsp;辑 " + '</a>&nbsp;&nbsp;<a style="color:#4574a0; font-weight:bold;"  href=javascript:remove("' + value + '","' + rec.sort + '");>' + "删&nbsp;&nbsp;除" + '</a>';
						}
					}

					] ],
				 
					width : 850,
					height : 500,

				
					pagination : true,
					rownumbers:true
					

				});

	
		sort_window = $('#sort-window').window({
			closed : true,
			
			modal : false,
			minimizable : false,
			maximizable : false,
			resizable : false
		});
		form = add_window.find('form');
		    
		    
		    
		     
	});

	
	function reloadgrid(){
		//执行查询操作
		var pager = $('#wx').datagrid('getPager');//得到DataGrid页面
			pager.pagination({
				pageNumber:1
			});

		$('#wx').datagrid('options').queryParams = {
			title : $("#title").val(),
			releaseDate : $("#releaseDateQuery").val()
		};
		$("#wx").datagrid('reload');
		$("#wx").datagrid('resize'); 
	}

	function remove(id,sort){
		$.messager.confirm('提示', '确认删除么?', function(r){
					if (r){
						$.ajax(
						{
							type : "post",
							url : "wxFashionInformationAction_delete.action",
							data : {
								id : id,
								sort:sort,
								x : Math.random()
							},
							success : function callback()
							{
								$.messager.alert('操作提示', '操作成功!');

								reloadgrid();
							}
						});

					}
				});
	}
	function sort(v){
		
		sort_window.window('open');
		
		$("#vid").val(v);
		

	}
	function setSort(){
		
		$.ajax({
				type : "post",
				url : "wxFashionInformationAction_setSort.action",
				data : {
						id:$("#vid").val(),
						sort:$("#vsort").val(),
						x : Math.random()
				},
				success : function callback(data)
			{
					data = eval("(" + data + ")");
					if ("success" == data.returnCode) {
						$.messager.alert('操作提示', '操作成功!');
						sort_window.window('close');
						reloadgrid();
					} else {
						$.messager.alert('操作提示', data.returnInfo);
					}
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
			<div id="Title">
				<!--页面标题-->
				<img border="0" width="13" height="13"
					src="${pageContext.request.contextPath}/style/images/title_arrow.gif" />&nbsp;微信潮流资讯
			</div>
			<div id="Title_End"></div>
		</div>
	</div>
	<!--显示表单内容-->

	<div id="QueryArea">
	<div>
			<table border=0 cellspacing=3 cellpadding=5>
				<tr>
					<td>按标题：<s:textfield  cssClass="InputStyle" name="title"  id="title"/></td>
				
				</tr>
				<tr>
				    <td>发布时间：<s:textfield cssClass="InputStyle" onclick="WdatePicker({isShowClear:true,readOnly:true});" id="releaseDateQuery" name="releaseDateQuery"/></td>
				   
				   <td><input type="image" src="${pageContext.request.contextPath}/style/blue/images/button/query.PNG" onclick="reloadgrid();"/></td>
				 <td><s:a action="wxFashionInformationAction_addUI"><img src="${pageContext.request.contextPath}/style/images/createNew.png" /></s:a></td>
				</tr>
				
			</table>
		</div>
	
	</div>


	<div id=MainArea>
		<div class="Description" style="margin-left: 50px;">

			<div id="wxDiv">
				<table id="wx"></table>
			</div>
			
		</div>

		<div id="sort-window" title="修改位置"
		style="text-align: center; padding: 5px;">

		<div style="padding: 20px 20px 20px 20px;">

			<s:form action="">
				<table>
					<tr>
						<td>	
							<input id="vsort" name="vsort" type="text" size="20" />
						</td>
					</tr>
				</table>
			</s:form>
		</div>
		<div style="text-align: center; padding: 5px;">
			<a href="javascript:void(0)" onclick="setSort()" id="btn-save"
				icon="icon-search" class='easyui-linkbutton'>保存</a> 
		</div>
	</div>

	</div>
	<input id="vid" name="vid" type="hidden" value=""/>
</body>
</html>
