<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>success</title>
</head>
<body>
	<h1>springMVC字节流输入上传文件</h1>
	<form name="userForm1" action="behaviorUpload"
		enctype="multipart/form-data" method="post">
		<div id="newUpload1">
			<input type="file" name="file">
		</div>

		<input type="submit" value="上传">
	</form>
	<br>
	<br>
	<hr align="left" width="60%" color="#FF0000" size="3">
	<br>
	<br>
</body>
</html>