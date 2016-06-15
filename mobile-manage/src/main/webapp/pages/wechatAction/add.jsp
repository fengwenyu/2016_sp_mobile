<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<html>
<head>
    <title>卡券添加结果</title>
    <%@ include file="/pages/public/common.jspf"%>
    	
</head>
<body>
<div id="Title_bar">
    <div id="Title_bar_Head"> 
        <div id="Title_Head"></div>
        <!--页面标题-->
        <div id="Title">
            <img border="0" width="13" height="13" src="${pageContext.request.contextPath}/style/images/title_arrow.gif"/> 卡券创建结果
        </div>
        <div id="Title_End"></div>
    </div>
</div>

<div id="MainArea">
    <table cellspacing="0" cellpadding="0" class="TableStyle">
		<thead>
            <tr align=center valign=middle id=TableTitle>
                <td width="20">添加结果</td>
                <td width="20">卡券ID</td>             
                <td width="20">操作</td>             
            </tr>
        </thead>
        <!--显示数据列表-->
        <tbody id="TableData" class="dataContainer">       
            <tr class="TableDetail1 template">
                <td>${cardId==null?"添加失败":"添加成功"}</td>
                <td>${cardId}</td>
                <td><a href="wechatAction_detail.action?cardId=${cardId}">查看详情</a></td>
            </tr>        
        </tbody>
    </table>
</div>
</body>
</html>
