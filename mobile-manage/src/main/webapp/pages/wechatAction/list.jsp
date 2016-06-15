<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<html>
<head>
    <title>产品列表</title>
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
			$("#productInfo").validate({
				rules:{
					id:{
						"isNum":$("#productNum").val()
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
            <img border="0" width="13" height="13" src="${pageContext.request.contextPath}/style/images/title_arrow.gif"/> 卡券列表
        </div>
        <div id="Title_End"></div>
    </div>
</div>
<div id="QueryArea">
	<div style="height: 30px">
		<s:form action="wechatAction_list">
			<table border=0  cellspacing=3 cellpadding=3>
				<tr>
					<td>卡券状态：</td>
					<td><input type="checkbox"  name="satusList" value="CARD_STATUS_NOT_VERIFY"/> 待审核</td>
					<td><input type="checkbox"  name="satusList" value="CARD_STATUS_VERIFY_FAIL"/> 审核失败</td>
					<td><input type="checkbox"  name="satusList" value="CARD_STATUS_VERIFY_OK"/> 通过审核</td>
					<td><input type="checkbox"  name="satusList" value="CARD_STATUS_USER_DELETE"/> 卡券被商户删除</td>
					<td><input type="checkbox"  name="satusList" value="CARD_STATUS_USER_DISPATCH"/> 在公众平台投放过的卡券</td>
					<td><input type="IMAGE" src="${pageContext.request.contextPath}/style/blue/images/button/query.PNG"/></td>
					<td><font color="#666">微信接口提供了按状态查询,并没有起作用</font></td>
				</tr>			
				<tr>
					<td></td>
					<td></td>
				</tr>
				<tr>
					<td></td>
					<td></td>
					<td></td>
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
                <td width="10">序号</td>
                <td width="100">卡券id</td>
                <td width="100">操作</td>
            </tr>
        </thead>
        <!--显示数据列表-->
        <tbody id="TableData" class="dataContainer">       
      
	        <s:iterator value="recordList" var="v" status="st">
	            <tr class="TableDetail1 template">
	                <td> ${st.index } </td>
	                <td> ${v } </td>
	                <td> <a href="wechatAction_detail.action?cardId=${v }">查看详情</a></td>
	            </tr>
	        </s:iterator> 
        </tbody>
    </table>
</div>
	<%-- 分页信息 --%>
	<s:form action="wechatAction_list" id="pageSubForm"></s:form>
	<%@ include file="/pages/public/pageView.jspf" %>
</body>
</html>
