<%@ page language="java" contentType="text/html; charset=UTF-8" trimDirectiveWhitespaces="true"
    pageEncoding="UTF-8"%>
<%@ page import="java.util.Date"%>    
<%@ include file="/WEB-INF/views/include.inc.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title><spring:message code="common.platform"/></title>
<link href="${contextPath}/styles/dwz/themes/default/style.css" rel="stylesheet" type="text/css" media="screen"/>
<link href="${contextPath}/styles/dwz/themes/css/core.css" rel="stylesheet" type="text/css" media="screen"/>
<link href="${contextPath}/styles/dwz/themes/css/print.css" rel="stylesheet" type="text/css" media="print"/>
<link href="${contextPath}/styles/validationEngine/css/validationEngine.jquery.css" rel="stylesheet" type="text/css" media="screen"/>
<link href="${contextPath}/styles/ztree/css/zTreeStyle.css" rel="stylesheet" type="text/css" media="screen"/>
<link href="${contextPath}/styles/uploadify/css/uploadify.css" rel="stylesheet" type="text/css" media="screen"/>
<link href="${contextPath}/styles/treeTable/themes/default/treeTable.css" rel="stylesheet" type="text/css" />
<link href="${contextPath}/styles/manager/css/manager.css" rel="stylesheet" type="text/css" />
<!--[if IE]>
<link href="${contextPath}/styles/dwz/themes/css/ieHack.css" rel="stylesheet" type="text/css" media="screen"/>
<![endif]-->
<!--[if lte IE 9]>
<script src="${contextPath}/styles/dwz/js/speedup.js" type="text/javascript"></script>
<![endif]-->
<script src="${contextPath}/styles/jquery/jquery-1.7.2.min.js" type="text/javascript"></script>
<script src="${contextPath}/styles/dwz/js/jquery.bgiframe.js" type="text/javascript"></script>

<script src="${contextPath}/styles/treeTable/jquery.treeTable.min.js" type="text/javascript"></script>
<%-- form验证 --%>
<script src="${contextPath}/styles/validationEngine/js/languages/jquery.validationEngine-<spring:message code='common.language'/>.js" type="text/javascript" charset="utf-8"></script>
<script src="${contextPath}/styles/validationEngine/js/jquery.validationEngine-2.6.4.js" type="text/javascript" charset="utf-8"></script>

<script src="${contextPath}/styles/dwz/js/dwz.min.js" type="text/javascript"></script>

<script src="${contextPath}/styles/dwz/js/dwz.regional.zh.js" type="text/javascript"></script>
<%-- 自定义JS --%>
<script src="${contextPath}/styles/dwz/js/customer.js" type="text/javascript"></script>
<script src="${contextPath}/styles/My97DatePicker/WdatePicker.js" type="text/javascript"></script>
<script src="${contextPath}/styles/highcharts/highcharts.js" type="text/javascript"></script>
<script src="${contextPath}/styles/highcharts/modules/exporting.js" type="text/javascript"></script>
<script src="${contextPath}/styles/manager/js/manager.js" type="text/javascript"></script>
<%-- upload --%>
<script src="${contextPath}/styles/uploadify/scripts/jquery.uploadify.min.js" type="text/javascript"></script>
<%-- zTree --%>
<script src="${contextPath}/styles/ztree/js/jquery.ztree.all-3.5.min.js" type="text/javascript"></script>
<%--ckeditor --%>
<script src="${contextPath}/styles/ckeditor/ckeditor.js" type="text/javascript"></script>

<script type="text/javascript">
$(function(){	
	DWZ.init("${contextPath}/styles/dwz/dwz.frag.xml", {
		loginUrl:"${contextPath}/login/timeout", 
		loginTitle:"Login",	// 弹出登录对话框
		debug:false,	// 调试模式 【true|false】
		callback:function(){
			initEnv();
			$("#themeList").theme({themeBase:"${contextPath}/styles/dwz/themes"});
		}
	});
});
</script>
</head>
<body scroll="no">
<div id="layout">
	<div id="header">
		<div class="headerNav">
			<a class="logo" href="${contextPath}/management/index">Logo</a>
			<ul class="nav">
				<li><a href="${contextPath}/management/index"><spring:message code="common.home"/></a></li>
				<li><a href="${contextPath}/management/index/updateBase" target="dialog" mask="true" width="550" height="250"><spring:message code="user.edit.profile"/></a></li>
				<li><a href="${contextPath}/management/index/updatePwd" target="dialog" mask="true" width="500" height="200"><spring:message code="user.edit.password"/></a></li>
				<li><a href="${contextPath}/logout"><spring:message code="user.logout"/></a></li>
			</ul>
		</div>
	</div>
	<div id="leftside">
		<div id="sidebar_s">
			<div class="collapse">
				<div class="toggleCollapse"><div></div></div>
			</div>
		</div>
		<div id="sidebar">
			<div class="toggleCollapse"><h2><spring:message code="common.menu"/></h2><div>collapse</div></div>
			<div class="accordion" fillSpace="sideBar">
				<c:forEach var="level1" items="${menuModule.children }">
					<div class="accordionHeader">
						<h2><span>Folder</span>${level1.name }</h2>
					</div>
					<div class="accordionContent">
						<ul class="tree treeFolder expand">
						<c:forEach var="level2" items="${level1.children }">
							<li>
								<dwz:menuAccordion child="${level2 }" urlPrefix="${contextPath }"/>
							</li>
						</c:forEach>
						</ul>
					</div>												
				</c:forEach>				
			</div>
		</div>
	</div>
	<div id="container">
		<div id="navTab" class="tabsPage">
			<div class="tabsPageHeader">
				<div class="tabsPageHeaderContent"><!-- 显示左右控制时添加 class="tabsPageHeaderMargin" -->
					<ul class="navTab-tab">
						<li tabid="main" class="main"><a href="javascript:void(0)"><span><span class="home_icon">主页</span></span></a></li>
					</ul>
				</div>
				<div class="tabsLeft">left</div><!-- 禁用只需要添加一个样式 class="tabsLeft tabsLeftDisabled" -->
				<div class="tabsRight">right</div><!-- 禁用只需要添加一个样式 class="tabsRight tabsRightDisabled" -->
				<div class="tabsMore">more</div>
			</div>
			<ul class="tabsMoreList">
				<li><a href="javascript:void(0)">主页</a></li>
			</ul>
			<div class="navTab-panel tabsPageContent layoutBox">
				<div class="page unitBox">
					<div class="accountInfo">
						<div class="right">
							<p><fmt:formatDate value="<%=new Date() %>" pattern="yyyy-MM-dd EEEE"/></p>
						</div>
						<p><span><spring:message code="common.welcome"/>, ${login_user.realname } .</span></p>
					</div>
					<div class="pageFormContent" layouth="80">
					<fieldset>
						<legend><spring:message code="user.basic.info"/></legend>
						<dl>
							<dt><spring:message code="user.username"/>：</dt>
							<dd><span class="unit">${login_user.username }</span></dd>
						</dl>
						<dl>
							<dt><spring:message code="user.realname"/>：</dt>
							<dd><span class="unit">${login_user.realname }</span></dd>
						</dl>
						<dl>
							<dt><spring:message code="user.phone"/>：</dt>
							<dd><span class="unit">${login_user.phone }</span></dd>
						</dl>
						<dl>
							<dt><spring:message code="user.email"/>：</dt>
							<dd><span class="unit">${login_user.email }</span></dd>
						</dl>
						<dl>
							<dt><spring:message code="common.createtime"/>：</dt>
							<dd><span class="unit"><fmt:formatDate value="${login_user.createTime}" pattern="yyyy-MM-dd HH:mm:ss"/></span></dd>
						</dl>
						<dl>
							<dt><spring:message code="user.status"/>：</dt>
							<dd><span class="unit">${(login_user.status == "enabled")? "可用":"不可用"}</span></dd>
						</dl>
						<dl>
							<dt><spring:message code="user.organization"/>：</dt>
							<dd><span class="unit">${login_user.organization.name }</span></dd>
						</dl>
					</fieldset>
					</div>
				</div>
			</div>
		</div>
	</div>
</div>

<div id="footer">Copyright &copy; 2012-2013, feinno.com, All Rights Reserve.</div>
</body>
</html>