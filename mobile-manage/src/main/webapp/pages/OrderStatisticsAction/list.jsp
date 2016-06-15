<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<html>
<head>
    <title>订单统计分析列表</title>
    <%@ include file="/pages/public/common.jspf"%>
    <script type="text/javascript">
		function check(){
			/*var startDate = $("#startDate").attr("value");   
			var endDate = $("#endDate").attr("value");
			var i = GetDateDiff(startDate,endDate);
			if(i>30){
				$.messager.alert('警告','时间跨度不能超过一个月！','warning');
				return;
			}else{
				$("#channelInfo").submit();
			}*/
			$("#channelInfo").submit();
		}
		function GetDateDiff(startDate,endDate){  
		    var startTime = new Date(Date.parse(startDate.replace(/-/g,   "/"))).getTime();     
		    var endTime = new Date(Date.parse(endDate.replace(/-/g,   "/"))).getTime();     
		    var dates = Math.abs((startTime - endTime))/(1000*60*60*24);     
		    return dates;    
		}
	</script>
</head>
<body>
	<div id="Title_bar">
	    <div id="Title_bar_Head"> 
	        <div id="Title_Head"></div>
	        <!--页面标题-->
	        <div id="Title">
	            <img border="0" width="13" height="13" src="${pageContext.request.contextPath}/style/images/title_arrow.gif"/> 订单统计查询
	        </div>
	        <div id="Title_End"></div>
	    </div>
	</div>
	<div id="QueryArea">
		<table cellspacing="0" cellpadding="0" class="TableStyle">
	        <!-- 表头-->
	        <thead>
	            <tr align=center valign=middle id=TableTitle>
	                <td width="">订单总量</td>
	                <td width="">支付成功</td>
	                <td width="">支付失败</td>
	                <td width="">未支付</td>
	            </tr>
	        </thead>
	        <!--显示数据列表-->
	        <tbody id="TableData" class="dataContainer">
	            <tr class="TableDetail1 template">
	                <td>${totalCount}&nbsp;</td>
	                <td>${successPaymentCount}&nbsp;</td>
	                <td>${failPaymentCount}&nbsp;</td>
	                <td>${nonPaymentCount}&nbsp;</td>
	            </tr>
	        </tbody>
	    </table>
	</div>
	<div id="QueryArea">
		<s:form action="orderStatisticsAction_query" id="channelInfo" method="post">
			<div style="">
				<table border=0>
					<tr><!-- dateFmt:'yyyy-MM-dd HH:mm:ss', -->
					    <td>起始日期：<s:textfield cssClass="InputStyle" onclick="WdatePicker({isShowClear:true,readOnly:true,dateFmt:'yyyy-MM-dd',maxDate:'#F{$dp.$D(\\'endDate\\')}'});" id="startDate" name="startDate"/></td>
					    <td>&nbsp;</td>
					</tr>
					<tr>
					    <td>结束日期：<s:textfield cssClass="InputStyle" onclick="WdatePicker({isShowClear:true,readOnly:true,dateFmt:'yyyy-MM-dd',minDate:'#F{$dp.$D(\\'startDate\\')}',maxDate:'%y-%M-%d'});" id="endDate" name="endDate" /></td>
					    <!-- 
						<td><input type="image" src="${pageContext.request.contextPath}/style/blue/images/button/query.PNG" onclick="check();"/></td>
					     -->
						<td><a href="#" onclick="check();">查询</a></td>
						
					</tr>
				</table>
			</div>
		</s:form>
		<img src="${pageContext.request.contextPath}/orderStatisticsAction_image.action?startDate=${startDate}&endDate=${endDate}&type=hour" alt="" />
		<s:if test="picFlag==1">
			<!-- 
			<img src="${pageContext.request.contextPath}/orderStatisticsAction_image.action?startDate=2012-10-19&endDate=2012-10-30&type=day" alt="" />
			<img src="${pageContext.request.contextPath}/orderStatisticsAction_image.action?startDate=2012-10-19&endDate=2012-10-30&type=hour" alt="" />
			 -->
			<img src="${pageContext.request.contextPath}/orderStatisticsAction_image.action?startDate=${startDate}&endDate=${endDate}&type=day" alt="" />
		</s:if>
		<s:else>
			<div id="MainArea">
			    <table cellspacing="0" cellpadding="0" class="TableStyle">
			        <!-- 表头-->
			        <thead>
			            <tr align=center valign=middle id=TableTitle>
			                <td>订单日期</td>
			                <td>订单数量</td>
			            </tr>
			        </thead>
			        <!--显示数据列表-->
			        <tbody id="TableData" class="dataContainer">
			        <c:if test="${orderMap==null }">
				        <tr align=center valign=middle id=TableTitle>
			                <td>暂无数据</td>
			                <td>暂无数据</td>
				        </tr>
			        </c:if>
			        <c:forEach var="entry" items="${orderMap }">
			    		<tr align=center valign=middle id=TableTitle>
				    		<td>${entry.key }&nbsp;</td>
				    		<td>${entry.value}&nbsp;</td>
			    		</tr>
			    	</c:forEach>
			        </tbody>
			    </table>
			</div>
		</s:else>
	</div>
</body>
</html>