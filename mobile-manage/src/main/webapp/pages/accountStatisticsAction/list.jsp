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
	            <img border="0" width="13" height="13" src="${pageContext.request.contextPath}/style/images/title_arrow.gif"/> 用户统计查询
	        </div>
	        <div id="Title_End"></div>
	    </div>
	</div>
	<div id="QueryArea">
		<table cellspacing="0" cellpadding="0" class="TableStyle">
	        <!-- 表头-->
	        <thead>
	            <tr align=center valign=middle id=TableTitle>
	                <td width="">用户总数</td>
	                <td width="">查询时段内注册用户数</td>
	            </tr>
	        </thead>
	        <!--显示数据列表-->
	        <tbody id="TableData" class="dataContainer">
	            <tr class="TableDetail1 template" align=center >
	                <td>${totalCount}&nbsp;</td>
	                <td>${totalCountByDate}&nbsp;</td>
	            </tr>
	        </tbody>
	    </table>
	</div>
	<div id="QueryArea">
		<s:form action="accountStatisticsAction_query" id="channelInfo" method="post">
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
		<img src="${pageContext.request.contextPath}/accountStatisticsAction_image.action?queryType=1&startDate=${startDate}&endDate=${endDate}&type=hour&productNum=${productNum}&channelNum=${channelNum}" alt="" />
		<s:if test="picFlag==1">
			<img src="${pageContext.request.contextPath}/accountStatisticsAction_image.action?queryType=1&startDate=${startDate}&endDate=${endDate}&type=day&productNum=${productNum}&channelNum=${channelNum}" alt="" />
		</s:if>
		<s:else>
			<div id="MainArea">
			    <table cellspacing="0" cellpadding="0" class="TableStyle">
			        <!-- 表头-->
			        <thead>
			            <tr align=center valign=middle id=TableTitle>
			                <td>注册日期</td>
			                <td>新增用户数</td>
			            </tr>
			        </thead>
			        <!--显示数据列表-->
			        <tbody id="TableData" class="dataContainer">
			        <c:if test="${accountMap==null }">
				        <tr align=center valign=middle id=TableTitle>
			                <td>暂无数据</td>
			                <td>暂无数据</td>
				        </tr>
			        </c:if>
			        <c:forEach var="entry" items="${accountMap }">
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