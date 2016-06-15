<%@ page language="java" contentType="text/html; charset=UTF-8" trimDirectiveWhitespaces="true"
    pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/views/include.inc.jsp"%>        
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title><spring:message code="common.platform"/></title>
<link href="${contextPath}/styles/dwz/themes/default/style.css" rel="stylesheet" type="text/css" />
<link href="${contextPath}/styles/dwz/themes/css/core.css" rel="stylesheet" type="text/css" />
<link href="${contextPath}/styles/dwz/themes/css/login.css" rel="stylesheet" type="text/css" />

<!-- form验证 -->
<link rel="stylesheet" href="${contextPath}/styles/validationEngine/css/validationEngine.jquery.css" type="text/css"/>
<script src="${contextPath}/styles/jquery/jquery-1.7.2.min.js" type="text/javascript"></script>
<script src="${contextPath}/styles/validationEngine/js/languages/jquery.validationEngine-<spring:message code='common.language'/>.js" type="text/javascript" charset="utf-8"></script>
<script src="${contextPath}/styles/validationEngine/js/jquery.validationEngine-2.6.4.js" type="text/javascript" charset="utf-8"></script>
<script>
    jQuery(document).ready(function(){
        jQuery("#formID").validationEngine();
    });
    jQuery(document).ready(function(){
    	$("#captcha").click(function(){
    		$(this).attr("src", "${contextPath}/Captcha.jpg?time=" + new Date());
    		return false;
    	});
    });

</script>
</head>

<body>
	<div id="login">
		<div id="login_header">
			<h1 class="login_logo">
				<img src="${contextPath}/styles/dwz/themes/default/images/logo.png" />
			</h1>

			<div class="login_headerContent">
				&nbsp;
			</div>
		</div>
		<div id="login_content">
			<div class="loginForm">
				<form method="post" action="${contextPath}/login" id="formID" >
					<c:if test="${msg!=null }">
						<p style="color: red; margin-left: 10px;">${msg }</p>
					</c:if>
					<p>
						<label><spring:message code="user.username"/>:</label>
						<input type="text" name="username" style="width: 150px;" class="validate[required] login_input" id="username" value="${username }"/>
					</p>
					<p>
						<label><spring:message code="user.password"/>:</label>
						<input type="password" name="password" style="width: 150px;" class="validate[required] login_input" id="password"/>
					</p>
					<p>
						<label><spring:message code="user.captcha.code"/>:</label>
						<input type="text" id="captcha_key" style="width: 70px;float:left;" name="captcha_key" class="login_input validate[required,maxSize[4]]" size="6" />
						<span><img src="${contextPath}/Captcha.jpg" alt='<spring:message code="user.captcha.refresh"/>' width="75" height="24" id="captcha"/></span>
					</p>
					<p>
						<%--
						<label>记住我:</label>
						<input type="checkbox" id="rememberMe" name="rememberMe"/>
						 --%>
						 &nbsp;
					</p>					
					<div class="login_bar" style="disply:block;float:left;">
						<input class="sub" type="submit" value=""/>
					</div>
				</form>
			</div>
			<div class="login_banner"><img src="${contextPath}/styles/dwz/themes/default/images/login_banner.jpg" /></div>
			<div class="login_main">
				&nbsp;
			</div>
		</div>
		<div id="login_footer">
			Copyright &copy; 2012-2013, feinno.com, All Rights Reserve.
		</div>
	</div>
</body>
</html>