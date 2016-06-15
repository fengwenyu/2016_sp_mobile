<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<html>
<head>
	<title>产品信息</title>
   <%@ include file="/pages/public/common.jspf"%>
    <script type="text/javascript">
		//jQuery的ajax验证产品名是否存在
		function checkProductName(){
			$.ajax({
		        type: "POST",
		        url: "${pageContext.request.contextPath }/productAction_checkProductName.action",
		        data:"productName="+$("#productName").val()+"&timeStamp="+new Date().getTime(),
		        success: function(data){
					data = eval("("+data+")");
					$("#checkProductName").text("");
					$("#checkProductName").text(data.returnInfo);
		        }
		    });	
		}
		function checkProductNum(){
			$.ajax({
		        type: "POST",
		        url: "${pageContext.request.contextPath }/productAction_checkProductNum.action",
		        data:"productnum="+$("#productNum").val()+"&timeStamp="+new Date().getTime(),
		        success: function(data){
					data = eval("("+data+")");
					$("#checkProductNum").text("");
					$("#checkProductNum").text(data.returnInfo);
		        }
		    });	
		}
		/*
		//表单验证
		$().ready(function(){
			$("#productInfo").validate({
				rules:{
					productNum:{
						"required":true
					},
					productName:{
						"required":true
					}
				},
				messages:{
					productNum:{
						"required":""
					},
					productName:{
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
            <img border="0" width="13" height="13" src="${pageContext.request.contextPath}/style/images/title_arrow.gif"/> 产品信息
        </div>
        <div id="Title_End"></div>
    </div>
</div>

<!--显示表单内容-->
<div id=MainArea>
    <s:form id="productInfo" action="productAction_%{id == null ? 'add' : 'edit'}">
    	<s:hidden name="id"></s:hidden>
        <div class="ItemBlock_Title1"><!-- 信息说明 --><div class="ItemBlock_Title1">
        	<img border="0" width="4" height="7" src="${pageContext.request.contextPath}/style/blue/images/item_point.gif" /> 产品信息 </div> 
        </div>
        <!-- 表单内容显示 -->
        <div class="ItemBlockBorder">
            <div class="ItemBlock">
                <table cellpadding="0" cellspacing="0" class="mainForm">
                    <tr>
                    	<td>产品号</td>
                        <td>
                        <c:choose>
                        	<c:when test="${id == null }">
	                        	<s:textfield name="productnum" id="productNum" cssClass="InputStyle" onblur="checkProductNum();"/>
                        	</c:when>
							<c:otherwise>
	                        	<s:textfield name="productnum" cssClass="InputStyle" id="productNum"/>
                     		</c:otherwise>
						</c:choose>
                        <label><font color="red"><span id ="checkProductNum">${checkProductNum}</span>*</font></label>
						</td>
                    </tr>
                    <tr>
                    	<td>产品名</td>
                        <td>
                        <c:choose>
                        	<c:when test="${id == null }">
	                        	<s:textfield name="productName" id="productName" cssClass="InputStyle" onblur="checkProductName();"/>
                        	</c:when>
							<c:otherwise>
	                        	<s:textfield name="productName" cssClass="InputStyle" id="productName"/>
                     		</c:otherwise>
						</c:choose>
                        <label><font color="red"><span id ="checkProductName">${checkProductName}</span>*</font></label>
						</td>
                    </tr>
                    <tr>
                    	<td>手机平台：</td>
					    <td>
					    	<select name="platform" class="SelectStyle" id="platform">
	   							<option value="ios">IOS</option>
	   							<option value="android">Android</option>
	   						</select>
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
