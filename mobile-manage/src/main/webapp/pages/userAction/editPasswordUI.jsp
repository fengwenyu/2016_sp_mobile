<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<html>
<head>
    <title>修改密码</title>
    <%@ include file="/pages/public/common.jspf"%>
    <script type="text/javascript">
    $.validator.addMethod("pwdformat",function(value,element,params){
		var pattern = /[A-Za-z].*[0-9]|[0-9].*[A-Za-z]/;
			if(!pattern.test(value)){
				return false;
			}
		return true;
	})
	$().ready(function(){
		$("#passwordForm").validate({
			rules:{
				oldPassword:{
					"required":true
				},
				pwd:{
					"required":true,
					"rangelength":[8,20],
					"pwdformat":true
				},
				passwordConfirm:{
					"required":true,
					"equalTo":"#pwd"
				}
			},
			messages:{
				oldPassword:"原始密码不能为空",
				pwd:{
					"required":"密码不能为空",
					"rangelength":"密码的长度应该在8-20之间",
					"pwdformat":"密码必须同时包含数字和字母"
				},
				passwordConfirm:{
					"required":"确认密码不能为空",
					"equalTo":"两次输入的密码不一样，请您重新输入"
				}
			}
		})
	})
	</script>
</head>
<body>

<!-- 标题显示 -->
<div id="Title_bar">
    <div id="Title_bar_Head">
        <div id="Title_Head"></div>
        <div id="Title">
            <img border="0" width="13" height="13" src="${pageContext.request.contextPath}/style/images/title_arrow.gif"/> 修改密码
        </div>
        <div id="Title_End"></div>
    </div>
</div>

<!--显示表单内容-->
<div id=MainArea>
    <s:form action="userAction_editPassword" id="passwordForm" method="post">
        <div class="ItemBlock_Title1">
        </div>
        <!-- 表单内容显示 -->
        <div class="ItemBlockBorder">
            <div class="ItemBlock">
                <table cellpadding="0" cellspacing="0" class="mainForm">
					<tr height="25">
						<td width="150">
							<img border="0" width="4" height="7" src="${pageContext.request.contextPath}/style/blue/images/item_point.gif" />
							请输入原密码
						</td>
						<td><input type="password" name="oldPassword" class="InputStyle" /><label><font color="red"> ${editPassword}*</font></label></td>
					</tr>
					<tr height="25">
						<td width="150">
							<img border="0" width="4" height="7" src="${pageContext.request.contextPath}/style/blue/images/item_point.gif" />
							请填写新密码
						</td>
						<td><input type="password" name="pwd" class="InputStyle" id="pwd"/><font color="red"> *</font></td>
					</tr>
					<tr height="25">
						<td width="150">
							<img border="0" width="4" height="7" src="${pageContext.request.contextPath}/style/blue/images/item_point.gif" />
							再次输入新密码
						</td>
						<td><input type="password" name="passwordConfirm" class="InputStyle" /><font color="red"> *</font></td>
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
<div class="Description" style="margin-left: 50px;">
	验证规则：<br />
	1，旧密码不能为空。<br />
	2，新密码不能为空。<br />
	3，再次输入的密码要和新密码一致。<br />
</div>
</body>
</html>
