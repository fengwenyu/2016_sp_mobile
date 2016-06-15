<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + path + "/";
%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<!doctype html>
<html lang="zh-CN">
<head>
<script type="text/javascript">
	function submit(){
		document.getElementById("myform").submit();
	}
</script>
</head>
<body onload="submit();">
  <form id="myform" action="https://wappaygw.alipay.com/service/rest.htm" method="post">
  	<input type="hidden" value="${sign }" name="sign"/>
  	<input type="hidden" value="${sec_id }" name="sec_id"/>
  	<input type="hidden" value="${v }" name="v"/>
  	<input type="hidden" value="${call_back_url }" name="call_back_url"/>
  	<input type="hidden" value="${req_data }" name="req_data"/>
  	<input type="hidden" value="${service }" name="service"/>
  	<input type="hidden" value="${partner }" name="partner"/>
  	<input type="hidden" value="${format }" name="format"/>
  </form>
</body>
</html>
