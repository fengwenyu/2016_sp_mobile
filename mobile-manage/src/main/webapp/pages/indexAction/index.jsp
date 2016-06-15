<%@ page language="java" pageEncoding="UTF-8"%>
<html>
	<head>
		<title>尚品无线管理后台</title>
		<%@ include file="/pages/public/common.jspf"%>	
	</head>
	<frameset rows="100,*,25" framespacing=0 border=0 frameborder="0">
		<frame noresize name="TopMenu" scrolling="no" src="${pageContext.request.contextPath}/indexAction_top.action">
		<frameset cols="175,*" id="resize">
			<frame noresize name="menu" scrolling="no" src="${pageContext.request.contextPath}/indexAction_left.action">
			<frame noresize name="right" scrolling="yes" src="${pageContext.request.contextPath}/indexAction_right.action">
		</frameset>
		<frame noresize name="status_bar" scrolling="no" src="${pageContext.request.contextPath}/indexAction_bottom.action">
	</frameset>
	<noframes><body>
</body>
</noframes></html>



