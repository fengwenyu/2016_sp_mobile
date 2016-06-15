<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<html>
<head>
	<title>渠道信息</title>
   <%@ include file="/pages/public/common.jspf"%>
    <script type="text/javascript">
		//jQuery的ajax验证渠道名是否存在
		function checkChannelName(){
			$.ajax({
		        type: "POST",
		        url: "${pageContext.request.contextPath }/channelAction_checkChannelName.action",
		        data:"channelName="+$("#channelName").val()+"&timeStamp="+new Date().getTime(),
		        success: function(data){
					data = eval("("+data+")");
					$("#checkChannelName").text("");
					$("#checkChannelName").text(data.returnInfo);
		        }
		    });	
		}
		function checkChannelNum(){
			$.ajax({
		        type: "POST",
		        url: "${pageContext.request.contextPath }/channelAction_checkChannelNum.action",
		        data:"channelnum="+$("#channelNum").val()+"&timeStamp="+new Date().getTime(),
		        success: function(data){
					data = eval("("+data+")");
					$("#checkChannelNum").text("");
					$("#checkChannelNum").text(data.returnInfo);
		        }
		    });	
		}
		/*
		//表单验证
		$().ready(function(){
			$("#channelInfo").validate({
				rules:{
					channelNum:{
						"required":true,
						"isNum":$("#channelNum").val()
					},
					channelName:{
						"required":true
					}
				},
				messages:{
					channelNum:{
						"required":"",
						"isNum":"请输入数字"
					},
					channelName:{
						"required":""
					}
				}
			});
		})
		*/
	</script>
</head>
<body>
<!-- 标题显示 -->
<div id="Title_bar">
    <div id="Title_bar_Head">
        <div id="Title_Head"></div>
        <!--页面标题-->
        <div id="Title">
            <img border="0" width="13" height="13" src="${pageContext.request.contextPath}/style/images/title_arrow.gif"/> 渠道信息
        </div>
        <div id="Title_End"></div>
    </div>
</div>

<!--显示表单内容-->
<div id=MainArea>
    <s:form id="channelInfo" action="channelAction_%{id == null ? 'add' : 'edit'}">
    	<s:hidden name="id"></s:hidden>
        <div class="ItemBlock_Title1"><!-- 信息说明 --><div class="ItemBlock_Title1">
        	<img border="0" width="4" height="7" src="${pageContext.request.contextPath}/style/blue/images/item_point.gif" /> 渠道信息 </div> 
        </div>
        <!-- 表单内容显示 -->
        <div class="ItemBlockBorder">
            <div class="ItemBlock">
                <table cellpadding="0" cellspacing="0" class="mainForm">
                    <tr>
                    	<td>渠道号</td>
                        <td>
                        <c:choose>
                        	<c:when test="${id == null }">
	                        	<s:textfield name="channelnum" id="channelNum" cssClass="InputStyle" onblur="checkChannelNum();"/>
                        	</c:when>
							<c:otherwise>
	                        	<s:textfield name="channelnum" cssClass="InputStyle" id="channelNum"/>
                     		</c:otherwise>
						</c:choose>
                        <label><font color="red"><span id ="checkChannelNum">${checkChannelNum}</span>*</font></label>
						</td>
                    </tr>
                    <tr>
                    	<td>渠道名</td>
                        <td>
                        <c:choose>
                        	<c:when test="${id == null }">
	                        	<s:textfield name="channelName" id="channelName" cssClass="InputStyle" onblur="checkChannelName();"/>
                        	</c:when>
							<c:otherwise>
	                        	<s:textfield name="channelName" cssClass="InputStyle" id="channelName"/>
                     		</c:otherwise>
						</c:choose>
                        <label><font color="red"><span id ="checkChannelName">${checkChannelName}</span>*</font></label>
						</td>
                    </tr>
                    <tr>
                    	<td>邀请码</td>
                        <td>
                        <c:choose>
                        	<c:when test="${id == null }">
	                        	<s:textfield name="invitationCode" id="invitationCode" cssClass="InputStyle"/>
                        	</c:when>
							<c:otherwise>
	                        	<s:textfield name="invitationCode" cssClass="InputStyle" id="invitationCode"/>
                     		</c:otherwise>
						</c:choose>
						</td>
                    </tr>
                </table>
            </div>
        </div>
        <!-- 表单操作 -->
        <div id="InputDetailBar">
            <input type="image" src="${pageContext.request.contextPath}/style/images/save.png"/>
            <a href="javascript:history.go(-1);"><img src="${pageContext.request.contextPath}/style/images/goBack.png"/></a>
        </div>
    </s:form>
</div>
</body>
</html>
