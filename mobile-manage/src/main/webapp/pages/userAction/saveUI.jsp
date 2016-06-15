<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<html>
<head>
	<title>账号信息</title>
   <%@ include file="/pages/public/common.jspf"%>
   <script type="text/javascript">
   		//添加数字验证
	    $.validator.addMethod("isNum",function(value,element,params){
			var pattern = /^[0-9]*$/;
			if(pattern.test(value)){
				return true;
			}
			return false;
		});
   		//手机号码验证信息 
   		$.validator.addMethod("isMobile",function(value,element,params){
            var patrn = /(^1[3|4|5|6|7|8|9][0-9]{9}$)/; 
            if (!patrn.test(value)) {
                return false; 
            }
            return true;
		});

		//jQuery的ajax验证用户名是否存在
		function checkLoginName(){
			if($("#loginName").val()==""){
				$.messager.alert('错误','用户名不能为空!','error');	
				return;
			}
			$.ajax({
		        type: "POST",
		        url: "${pageContext.request.contextPath }/userAction_checkLoginName.action",
		        data:"loginName="+$("#loginName").val()+"&timeStamp="+new Date().getTime(),
		        success: function(data){
					data = eval("("+data+")");
					$("#checkLoginName").text("");
					$("#checkLoginName").text(data.returnInfo);
		        }
		    });	
		}
		//表单验证
		$().ready(function(){
			$("#userInfo").validate({
				rules:{
					loginName:{
						"required":true
					},
					nickname:{
						"required":true
					},
					phoneNum:{
						"required":true,
						"isMobile":$("#phoneNum").val()
					}/*,
					rank:{
						"required":true,
						"isNum":$("#rank").val()
					}*/
				},
				messages:{
					loginName:{
						"required":""
					},
					nickname:{
						"required":""
					},
					phoneNum:{
						"required":"",
						"isMobile":"请输入正确的手机号码"
					}/*,
					rank:{
						"required":"",
						"isNum":"请输入数字"
					}*/
				}
			});
		})
	</script>
</head>
<body>

<!-- 标题显示 -->
<div id="Title_bar">
    <div id="Title_bar_Head">
        <div id="Title_Head"></div>
        <!--页面标题-->
        <div id="Title">
            <img border="0" width="13" height="13" src="${pageContext.request.contextPath}/style/images/title_arrow.gif"/> 账号信息
        </div>
        <div id="Title_End"></div>
    </div>
</div>

<!--显示表单内容-->
<div id=MainArea>
    <s:form id="userInfo" action="userAction_%{id == null ? 'add' : 'edit'}" method="post">
    	<s:hidden name="id"></s:hidden>
        <div class="ItemBlock_Title1"><!-- 信息说明 --><div class="ItemBlock_Title1">
        	<img border="0" width="4" height="7" src="${pageContext.request.contextPath}/style/blue/images/item_point.gif" /> 账号信息 </div> 
        </div>
        <!-- 表单内容显示 -->
        <div class="ItemBlockBorder">
            <div class="ItemBlock">
                <table cellpadding="0" cellspacing="0" class="mainForm">
                    <tr>
                    	<td>登录名</td>
                        <td>
                        	<c:choose>
                        	<c:when test="${id == null }">
	                        	<s:textfield name="loginName" cssClass="InputStyle" onblur="checkLoginName();" id="loginName"/>
                        	</c:when>
							<c:otherwise>
	                        	<s:textfield name="loginName" cssClass="InputStyle" id="loginName"/>
                     		</c:otherwise>
							</c:choose>
							<label><font color="red"><span id ="checkLoginName">${checkLoginName}</span>（登录名要唯一）*</font></label>
						</td>
                    </tr>
                    <tr>
                    	<td>姓名</td>
                        <td><s:textfield name="nickname" cssClass="InputStyle"/><font color="red"> *</font></td>
                    </tr>
                    <tr>
                    	<td>手机号码</td>
                        <td><s:textfield name="phoneNum" cssClass="InputStyle" id="phoneNum"/><font color="red"> *</font></td>
                    </tr>
<!--					<tr>
                    	<td>用户级别</td>
                        <td><s:textfield name="rank" cssClass="InputStyle" id="rank"/><font color="red"> *</font></td>
                    </tr>-->
                    <s:hidden name="rank" value="123"></s:hidden>
                </table>
            </div>
        </div>
        <!-- 表单操作 -->
        <div id="InputDetailBar">
            <input type="image" src="${pageContext.request.contextPath}/style/images/save.png"/>
            <a href="javascript:history.go(-1);"><img src="${pageContext.request.contextPath}/style/images/goBack.png"/></a>
        </div>
    </s:form>
    <div class="Description" style="margin-left: 50px;">
		说明：<br />
		1，用户的登录名要唯一，在填写时要同时检测是否可用。<br />
		2，新建用户后，密码被初始化为"1234"。<br />
		3，密码在数据库中存储的是MD5摘要（不是存储明文密码）。<br />
		4，用户登录系统后可以使用“帐号管理→修改密码”功能修改密码。<br />
		5，修改用户信息时，登录名不可修改。
	</div>
</div>
</body>
</html>
