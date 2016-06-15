<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<html>
<head>
    <title>用户列表</title>
    <%@ include file="/pages/public/common.jspf"%>
</head>
<body>

<div id="Title_bar">
    <div id="Title_bar_Head"> 
        <div id="Title_Head"></div>
        <!--页面标题-->
        <div id="Title">
            <img border="0" width="13" height="13" src="${pageContext.request.contextPath}/style/images/title_arrow.gif"/> 账号管理
        </div>
        <div id="Title_End"></div>
    </div>
</div>

<div id="MainArea">
    <table cellspacing="0" cellpadding="0" class="TableStyle">
       
        <!-- 表头-->
        <thead>
            <tr align=center valign=middle id=TableTitle>
                <td width="100">登录名</td>
                <td width="100">姓名</td>
                <td width="100">级别</td>
                <td width="200">创建时间</td>
                <td>相关操作</td>
            </tr>
        </thead>
        <!--显示数据列表-->
        <tbody id="TableData" class="dataContainer">
        <s:iterator value="recordList">
            <tr class="TableDetail1 template">
                <td>${loginName}&nbsp;</td>
                <td>${nickname}&nbsp;</td>
                <td>${rank}&nbsp;</td>
                <td>${my:getDate(createTime )}&nbsp;</td>
                <td>
                	<s:a action="userAction_delete?id=%{id}" onclick="return delConfirm()">删除</s:a>
                    <s:a action="userAction_editUI?id=%{id}">修改</s:a>
					<s:a action="userAction_initPassword?id=%{id}" onclick="return window.confirm('您确定要初始化密码为1234吗？')">初始化密码</s:a>
					<s:a action="userAction_setPrivilegeUI?userId=%{id}">设置权限</s:a>
                </td>
            </tr>
        </s:iterator> 
            
        </tbody>
    </table>
    
    <!-- 其他功能超链接 -->
    <div id="TableTail">
        <div id="TableTail_inside">
            <s:a action="userAction_addUI"><img src="${pageContext.request.contextPath}/style/images/createNew.png" /></s:a>
        </div>
    </div>
</div>
	<%-- 分页信息 --%>
	<s:form action="userAction_list" id="pageSubForm"></s:form>
	<%@ include file="/pages/public/pageView.jspf" %>
</body>
</html>
