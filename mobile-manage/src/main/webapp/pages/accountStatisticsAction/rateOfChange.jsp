<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<html>
<head>
    <title>用户统计分析列表</title>
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
			var date = $("#date").attr("value");
			if(date==""){
				$.messager.alert('警告','时间不能为空！','warning');
				return;
			}
			$("#rateOfChange").submit();
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
	            <img border="0" width="13" height="13" src="${pageContext.request.contextPath}/style/images/title_arrow.gif"/> 用户变化率查询
	        </div>
	        <div id="Title_End"></div>
	    </div>
	</div>
	<div id="QueryArea">
		<s:form action="accountStatisticsAction_rateOfChange" id="rateOfChange" method="post">
			<input type="hidden" name="queryType" value="1" />
			<div style="">
				<table border=0>
					<tr>
                    	<td>产品号： <s:select name="productNum" id="productNum" cssClass="SelectStyle" 
                           	list="#productList" listKey="productNum" listValue="productName"> 
                           </s:select></td>
                        <td>&nbsp;</td>
                    </tr>
                    <tr>
					    <td>渠道编号：
	   				      	   <s:select name="channelNum" id="channelNumSelect" 
	                           	list="#channelList" listKey="channelNum" listValue="channelName"
	                           	headerKey="" headerValue="请选择渠道名称" onchange="setChannelNumInput();"> 
	                           </s:select>
					    </td>
					    <td>&nbsp;</td>
					</tr>
					<tr>
					    <td>日期：<s:textfield cssClass="InputStyle" onclick="WdatePicker({isShowClear:true,readOnly:true,dateFmt:'yyyy-MM-dd'});" id="date" name="date"/></td>
						<td><a href="#" onclick="check();">查询</a></td>
					</tr>
				</table>
			</div>
		</s:form>
		<div id="MainArea">
		    <table cellspacing="0" cellpadding="0" class="TableStyle">
		        <!-- 表头-->
		        <thead>
		            <tr align=center valign=middle id=TableTitle>
		                <td>日期</td>
		                <td>变化率</td>
		            </tr>
		        </thead>
		        <!--显示数据列表-->
		        <tbody id="TableData" class="dataContainer">
		        <tr align=center valign=middle id=TableTitle>
	                <td>与昨天（${yesterday}）相比</td>
	                <td>${yesterdayRate}&nbsp;</td>
		        </tr>
		        <tr align=center valign="middle" id=TableTitle>
	                <td>与上周同期（${lastWeek}）相比</td>
	                <td>${lastWeekRate}&nbsp;</td>
		        </tr>
		        <tr align=center valign=middle id=TableTitle>
	                <td>与上月同期（${lastMonth}）相比</td>
	                <td>${lastMonthRate}&nbsp;</td>
		        </tr>
		        </tbody>
		    </table>
		</div>
	</div>
</body>
</html>