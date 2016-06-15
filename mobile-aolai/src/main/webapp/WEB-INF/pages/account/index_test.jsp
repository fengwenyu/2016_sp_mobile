<%@ page contentType="text/html;charset=UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%@ page isELIgnored="false" %> 
<%@ taglib prefix="s"  uri="/struts-tags"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ taglib prefix="pg" uri="http://jsptags.com/tags/navigation/pager" %>

<!-- <!doctype html> -->
<html lang="zh-CN">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>Demo</title>

<script language="javascript" src="javaSript/jquery-1.5.1.min.js"></script>
<script language="javascript" src="javaSript/jquery.form.js"></script>

<script type="text/javascript">
var ts = '<%=System.currentTimeMillis()%>';
</script>
</head>
<body>
<div>hello , <c:out value="${userName}"/></div>

	<c:if test="${empty page.items}">
	 	<p align="center">抱歉</p>
	</c:if>

	<table border="1">
		<tr><td>ID</td><td>FirstName</td></tr>
        <c:forEach items="${page.items}" var="account">
		<tr>
		    <td><fmt:formatNumber value="${account.id}" pattern="0.00"/></td>
		    <td>${account.firstName}</td>
		</tr>
	    </c:forEach>
	</table>

</body>
</html>