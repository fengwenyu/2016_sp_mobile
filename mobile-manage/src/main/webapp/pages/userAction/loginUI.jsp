<%@ page language="java" pageEncoding="UTF-8"%>
<html>
<head>

<style>
.msgWindow {
    border-radius: 5px;
    overflow: hidden;
    box-shadow: 0 0 100px #000;
	display: block;
	opacity: 1;
	position: absolute;
	left: 416.5px;
	top: 206.5px;
	z-index: 1370589976;
}

.msgWindow .msgWindow_title {
    background: #dddddb;
    color: #000;
    font: 18px/45px '微软雅黑', '\5FAE\8F6F\96C5\9ED1', Microsoft YaHei;
    text-shadow: 1px 1px 3px #fff;
    text-indent: 34px;
    position: relative;
	*position:inherit;
}

.msgwindow_body {
    width: 540px;
	line-height:25px;
    overflow: hidden;
    background-color: #fff;
    color: #666;
    padding:15px 25px;
	text-align:center;
}

.msgwindow_body a:link, .msgwindow_body a:visited {
    color: #069;
}

.msgwindow_body a:hover, .msgwindow_body a:active {
    text-decoration: underline;
}

a.msgwindow_closebtn:link, a.msgwindow_closebtn:visited {
    position: absolute;
    display: block;
    width: 22px;
    height: 22px;
	line-height:22px;
	text-decoration:none;
	text-align:center;
	text-indent:0;
	text-shadow:none;
	font-size:16px;
	color:#999;
	font-family:"宋体";
    overflow: hidden;
    top: 12px;
    right: 12px;
}

a.msgwindow_closebtn:hover, a.msgwindow_closebtn:active {
    color:#fff;
	background-color:#000;
}
.msgwindow_body a.mxbtn:link, .msgwindow_body a.mxbtn:visited{
	color:#fff;
	text-decoration: none;
}
.msgwindow_body a.mxbtn:hover, .msgwindow_body a.mxbtn:active {
    text-decoration: none;
}
.coupon_box{
	padding:10px 0;
}
.coupon_box P{
	width:100%;
	font-size:14px;
	line-height:25px;
	text-align:left;
	text-indent:2em;
}
.msgBtn{
	padding:15px 0;
	text-align:right;
}
.msgBtn a.mxbtn{
	display: inline-block;
    *display: inline;
    *zoom: 1;
	width:115px;
    height: 32px;
    vertical-align: middle;
    color: #fff;
    font-size: 14px;
    line-height: 32px;
    text-align: center;
	-webkit-border-radius:.2em;
	-moz-border-radius:.2em;
	border-radius:.2em;
	background: -webkit-linear-gradient(top, rgba(81,81,81,1) 0%, rgba(35,35,35,1) 100%);
	background: -moz-linear-gradient(top, rgba(81,81,81,1) 0%, rgba(35,35,35,1) 100%);
	background: -o-linear-gradient(top, rgba(81,81,81,1) 0%, rgba(35,35,35,1) 100%);
	background: linear-gradient(to bottom, rgba(81,81,81,1) 0%, rgba(35,35,35,1) 100%);
}
.msgBtn a.mxbtn:hover {
	background: -webkit-linear-gradient(top, rgba(61,61,61,1) 0%, rgba(23,23,23,1) 100%);
	background: -moz-linear-gradient(top, rgba(61,61,61,1) 0%, rgba(23,23,23,1) 100%);
	background: -o-linear-gradient(top, rgba(61,61,61,1) 0%, rgba(23,23,23,1) 100%);
	background: linear-gradient(to bottom, rgba(61,61,61,1) 0%, rgba(23,23,23,1) 100%);
}

#overlay{
	position: absolute;
	background-color: rgb(51, 51, 51);
	opacity: 0.8; z-index: 1370589975;
	top: 0px;
	left: 0px;
	width: 100%;
	height: 100%;
}
#sp_framefix{
	position: absolute;e
	z-index: 999;
	left: 0px;
	top: 0px;
	height: 100%;
	width: 100%;
	opacity: 0;
}
</style>
	<title>尚品无线管理后台</title>
    <%@ include file="/pages/public/common.jspf"%>
	<link href="${pageContext.request.contextPath}/style/blue/login.css" type=text/css rel=stylesheet>
	<script type="text/javascript">
		$(function(){
			hiddenMob();
			//document.forms[0].loginName.focus();
		});
		
		if((window.parent != window)){
			window.parent.location.reload(true);
		}
		function changeCheckNum(){
			var checkNumImage_=document.getElementById("checkNumImage");
			checkNumImage_.src="${pageContext.request.contextPath}/pages/userAction/image.jsp?timeStamp="+new Date().getTime();
		}

		//手机号码验证信息 
        function isMobile() {
		//	var mobileNum=$("#mobileNum").val();
          //  var patrn = /(^1[3|4|5|6|7|8|9][0-9]{9}$)/; 
          //  if (!patrn.test(mobileNum)) {
          // 	$("#mobileNum").focus();
          //  	$("#errormsg").html("手机号码输入有误！");
          //      return false; 
         //  }
            return true;
        }
		
		// 校验用户登录信息
		function ajaxCheckUserInfo(again) {
			$.ajax({
		        type: "POST",
		        dataType:"json",
		        url: "${pageContext.request.contextPath }/userAction_ajaxCheckUserInfo.action",
		        data:{
                    "model.loginName":$("#loginNameInput").val(),
                   "model.phoneNum":$("#mobileNum").val(),
                    "model.pwd":$("#aa").val(),
                    "checkNum":$("#checkNum").val(),
                    "timeStamp":new Date().getTime()
                },
		        success: function(data){
                	if(!again){
					    if(data.code == 0){
					    	$("#sysphonenum").val(data.mobilenum),
	                        $("#sysloginname").val(data.loginname),
	                        checkMobileNum();
					    
					    }else{
					    	$("#errormsg").html(data.msg);
					    }
				    }else if(again){
				    	if(data.code == 0){
					    	alert("已重新发送！");
					    }else{
					    	alert(data.msg);
					    }
				    }
		        }
		    });
		}
		
		function checkUserInfo() {
			//if(isMobile()){
				ajaxCheckUserInfo(false);
			//}
		}
		
		// 校验手机验证码
		function checkMobileNum() {
		//	$.ajax({
		  //      type: "POST",
		  //      dataType:"json",
		  //      url: "${pageContext.request.contextPath }/userAction_ajaxCheckMobileNum.action",
		  //      data:{
          //          "verifycode":$("#verifycode").val(),
          //          "phonenum":$("#sysphonenum").val(),
          //          "loginname":$("#sysloginname").val(),
          //          "timeStamp":new Date().getTime()
          //      },
		  //      success: function(data){
		//		    if(data.code == 0){
				    	document.location.href = "${pageContext.request.contextPath }/userAction_loginSys.action?loginname="+$("#sysloginname").val();
			//	    }else{
		//		    	$("#moberrormsg").html(data.msg);
			//	    	$("#verifycode").focus();
			//	    }
		  //      }
		 //   });
		}
        
		// 隐藏手机验证码
        function hiddenMob(){ 
            $("#magbox_over").hide();
            $("#overlay").hide();
            $("#sp_framefix").hide();
            $("#moberrormsg").html("");
        }
		
	    // 显示手机验证码
        function show(){ 
           $("#magbox_over").show();
            $("#overlay").show();
            $("#sp_framefix").show();
        }
		
	</script>
</head>
<body leftmargin=0 topmargin=0 marginwidth=0 marginheight=0 class=PageBody >
<!-- 显示表单 -->

    <div id="CenterAreaBg"> 
        <div id="CenterArea">
            <div id="LogoImg"></div>
            <div id="LoginInfo">
                <table BORDER=0 CELLSPACING=0 CELLPADDING=0 width=100%>
                	<tr>
                		<td colspan="3" style="padding-top: 20px;padding-left: 20px;">
							<font color="red" id="errormsg">${login}</font>
                		</td>
                	</tr>
                    <tr>
                        <td width=45 class="Subject"><img border="0" src="${pageContext.request.contextPath}/style/blue/images/login/userId.gif" /></td>
                        <td>
                        	<s:textfield name="loginName" size="20" tabindex="1" cssClass="TextField required" id="loginNameInput" />
                        </td>
                        <td rowspan="2" style="padding-left:10px;">
                        	<input type="image" tabindex="4" src="${pageContext.request.contextPath}/style/blue/images/login/userLogin_button.gif" onclick="checkUserInfo();"/>
                        </td>
                    </tr>
                    <tr>
                        <td class="Subject"><img border="0" src="${pageContext.request.contextPath}/style/blue/images/login/password.gif" /></td>
                        <td>
                        <s:password name="pwd" id="aa" size="20" tabindex="2" showPassword="false" cssClass="TextField required"/>
  	           		   </td>
                    </tr>
<!-- 
                    <tr>
                        <td class="Subject"><font size="2">手机号码</font></td>
                        <td>
                            <s:textfield name="mobileNum" size="20" id="mobileNum" cssClass="TextField required" maxlength="15"/>
  	           		    </td>
                    </tr>
 -->
                    <tr background="red;">
                        <td class="Subject"><font size="2">验证码</font></td>
                        <td>
                        <s:textfield name="checkNum" size="10" id="checkNum" tabindex="3"/>
                        <img id="checkNumImage" src="${pageContext.request.contextPath}/pages/userAction/image.jsp" onClick="changeCheckNum()" title="点击换一张" style="cursor:hand">
  	           		    </td>
                    </tr>
                </table>
            </div>
            <div id="CopyRight"></div>
        </div>
    </div>
    
    
    
    <!-- 消息层 start -->
    <div id="magbox_over" class="msgWindow">
	    <div class="msgWindow_title">手机验证码<a href="#" class="msgwindow_closebtn" onclick="hiddenMob();">×</a></div>
        <div class="msgwindow_body clr">
    	<div class=""><p><font color="red" id="moberrormsg"></font></p></div>
		<div class=""><p><input type="text" id="verifycode"/> </p></div>
        <div class="msgBtn rlt"><a href="#" class="mxbtn" onclick="checkMobileNum();">确认</a>&nbsp;&nbsp;<a href="#" class="mxbtn" onclick="ajaxCheckUserInfo(true);">重新获取验证码</a></div>
        <input type="hidden" id="sysphonenum"><input type="hidden" id="sysloginname">
        </div>
    </div>

    <div id="overlay" style=""></div>
    <iframe scrolling="no" frameborder="0" id="sp_framefix" name="sp_framefix" marginwidth="0" marginheight="0"></iframe>
    <!-- 消息层 end -->
</body>

</html>

