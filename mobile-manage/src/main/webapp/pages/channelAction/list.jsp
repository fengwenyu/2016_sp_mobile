<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<html>
<head>
    <title>渠道列表</title>
    <%@ include file="/pages/public/common.jspf"%>
    <script type="text/javascript">
    	//添加数字验证
	    $.validator.addMethod("isNum",function(value,element,params){
			var pattern = /^[0-9]*$/;
			if(value == "" || pattern.test(value)){
				return true;
			}
			return false;
		})
		//表单验证
		$().ready(function(){
			$("#channelInfo").validate({
				rules:{
					id:{
						"isNum":$("#channelNum").val()
					}
				},
				messages:{
					id:{
						"isNum":"请输入数字"
					}
				}
			});
		})
	</script>
</head>
<body>
<div id="Title_bar">
    <div id="Title_bar_Head"> 
        <div id="Title_Head"></div>
        <!--页面标题-->
        <div id="Title">
            <img border="0" width="13" height="13" src="${pageContext.request.contextPath}/style/images/title_arrow.gif"/> 渠道管理
        </div>
        <div id="Title_End"></div>
    </div>
</div>
<div id="QueryArea">
	<div style="height: 30px">
		<s:form action="channelAction_list">
			<table border=0 cellspacing=3 cellpadding=5>
				<tr>
					<td>渠道名称：<s:textfield name="channelName" cssClass="InputStyle" id="channelName"/></td>
					<td><input type="IMAGE" src="${pageContext.request.contextPath}/style/blue/images/button/query.PNG"/></td>
				</tr>
			</table>
		</s:form>
	</div>
	<div style="height: 30px">
		<s:form action="channelAction_list" id="channelInfo">
			<table border=0 cellspacing=3 cellpadding=5>
				<tr>
					<td>渠道编号：<s:textfield name="channelNum" cssClass="InputStyle" id="channelNum"/></td>
					<td><input type="IMAGE" src="${pageContext.request.contextPath}/style/blue/images/button/query.PNG"/></td>
				</tr>
			</table>
		</s:form>
	</div>
</div>
<div id="MainArea">
    <table cellspacing="0" cellpadding="0" class="TableStyle">
        <!-- 表头-->
        <thead>
            <tr align=center valign=middle id=TableTitle>
                <td width="100">渠道编号</td>
                <td width="100">渠道名称</td>
                <td width="100">邀请码</td>
                <td width="200">创建时间</td>
                <td>相关操作</td>
            </tr>
        </thead>
        <!--显示数据列表-->
        <tbody id="TableData" class="dataContainer">
        <s:iterator value="recordList">
            <tr class="TableDetail1 template">
                <td>${channelNum}&nbsp;</td>
                <td>${channelName}&nbsp;</td>
                <td>${invitationCode}&nbsp;</td>
                <td>${my:getDate(createTime )}&nbsp;</td>
                <td>
                	<s:a action="channelAction_delete?id=%{id}" onclick="return delConfirm()">删除</s:a>
                    <s:a action="channelAction_editUI?id=%{id}">修改</s:a>
                </td>
            </tr>
        </s:iterator> 
        </tbody>
    </table>
    <!-- 其他功能超链接 -->
    <div id="TableTail">
        <div id="TableTail_inside">
            <s:a action="channelAction_addUI"><img src="${pageContext.request.contextPath}/style/images/createNew.png" /></s:a>
        </div>
    </div>
</div>
	<%-- 分页信息 --%>
	<s:form action="channelAction_list" id="pageSubForm"></s:form>
	<%@ include file="/pages/public/pageView.jspf" %>
</body>
</html>
