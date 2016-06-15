<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<html>
<head>
	<title>配置权限</title>
    <%@ include file="/pages/public/common.jspf"%>
	<script language="javascript" src="${pageContext.request.contextPath}/script/jquery_treeview/jquery.treeview.js"></script>
	<link type="text/css" rel="stylesheet" href="${pageContext.request.contextPath}/style/blue/file.css" />
	<link type="text/css" rel="stylesheet" href="${pageContext.request.contextPath}/script/jquery_treeview/jquery.treeview.css" />
	<script type="text/javascript">
		$(function(){
			//	$("#tree").treeview();
		});
    </script>
</head>
<body>
<!-- 标题显示 -->
<div id="Title_bar">
    <div id="Title_bar_Head">
        <div id="Title_Head"></div>
        <div id="Title"><!--页面标题-->
            <img border="0" width="13" height="13" src="${pageContext.request.contextPath}/style/images/title_arrow.gif"/> 配置权限
        </div>
        <div id="Title_End"></div>
    </div>
</div>
<!--显示表单内容-->
<div id=MainArea>
    <s:form action="userAction_setPrivilege">
    	<s:hidden name="userId"></s:hidden>
        <div class="ItemBlock_Title1"><!-- 信息说明 --><div class="ItemBlock_Title1">
        	<img border="0" width="4" height="7" src="${pageContext.request.contextPath}/style/blue/images/item_point.gif" /> 正在为【${user.nickname}】配置权限 </div> 
        </div>
        <!-- 表单内容显示 -->
        <div class="ItemBlockBorder">
            <div class="ItemBlock">
                <table cellpadding="0" cellspacing="0" class="mainForm">
					<!--表头-->
					<thead>
						<tr align="LEFT" valign="MIDDLE" id="TableTitle">
							<td width="300px" style="padding-left: 7px;">
								<!-- 如果把全选元素的id指定为selectAll，并且有函数selectAll()，就会有错。因为有一种用法：可以直接用id引用元素 -->
								<input type="checkbox" id="cbSelectAll" onClick="$('[name=privilegeIds]').attr('checked', this.checked)"/>
								<label for="cbSelectAll">全选</label>
							</td>
						</tr>
					</thead>
			   		<!--显示数据列表-->
					<tbody id="TableData">
						<tr class="TableDetail1">
							<!-- 显示权限树 -->
							<td>
								<ul id="tree">
								<s:iterator value="#topPrivilegeList">
									<li>
										<input type="checkbox" name="privilegeIds" value="${id}" id="privilegeIds_${id}" <s:property value="%{id in privilegeIds ? 'checked' : ''}"/> >
										<label for="privilegeIds_${id}"><span class="folder">${modulename}</span></label>
										<ul>
										<s:iterator value="children">
											<li>
												<input type="checkbox" name="privilegeIds" value="${id}" id="privilegeIds_${id}" <s:property value="%{id in privilegeIds ? 'checked' : ''}"/> >
												<label for="privilegeIds_${id}"><span class="folder">${modulename}</span></label>
												<ul>
												<s:iterator value="children">
													<li>
														<input type="checkbox" name="privilegeIds" value="${id}" id="privilegeIds_${id}" <s:property value="%{id in privilegeIds ? 'checked' : ''}"/> >
														<label for="privilegeIds_${id}"><span class="folder">${modulename}</span></label>
													</li>
												</s:iterator>
												</ul>
											</li>
										</s:iterator>
										</ul>
									</li>
								</s:iterator>
								</ul>
							</td>
						</tr>
					</tbody>
                </table>
            </div>
        </div>
        <script type="text/javascript">
			$("#tree").treeview();
			$("[name=privilegeIds]").click(function(){
				
				// 也同时选中或取消所有的下级权限
				$(this).siblings("ul").find("input").attr("checked", this.checked);
				
				// 当选中时，也要选中所有直系上级
				if(this.checked){
					$(this).parents("li").children("input").attr("checked", true);	
				}
				// 当取消时，如果同级中其他的权限都未选中，则也取消上级，再向也这样做
				else{
					if( $(this).parent().siblings("li").children("input:checked").size() == 0 ){
						$(this).parent().parent().siblings("input").attr("checked", false);
						
						var startPoint = $(this).parent().parent();
						if( startPoint.parent().siblings("li").children("input:checked").size() == 0 ){
							startPoint.parent().parent().siblings("input").attr("checked", false);
						}
					}
				}
			});
	    </script>
	
        <!-- 表单操作 -->
        <div id="InputDetailBar">
            <input type="image" src="${pageContext.request.contextPath}/style/images/save.png"/>
            <a href="javascript:history.go(-1);"><img src="${pageContext.request.contextPath}/style/images/goBack.png"/></a>
        </div>
    </s:form>
</div>
<div class="Description" style="margin-left: 50px;">
	说明：<br />
	1，选中一个权限时：<br />
	&nbsp;&nbsp;&nbsp;&nbsp; a，应该选中他的所有直系上级。<br />
	&nbsp;&nbsp;&nbsp;&nbsp; b，应该选中他的所有直系下级。<br />
	2，取消选择一个权限时：<br />
	&nbsp;&nbsp;&nbsp;&nbsp; a，应该取消选择他的所有直系下级。<br />
	&nbsp;&nbsp;&nbsp;&nbsp; b，如果同级的权限都是未选择状态，就应该取消选中他的直接上级，并向上做这个操作。<br />

	3，全选/取消全选。<br />
	4，默认选中当前岗位已有的权限。<br />
</div>
</body>
</html>
