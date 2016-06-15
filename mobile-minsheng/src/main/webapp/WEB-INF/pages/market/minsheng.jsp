<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + path + "/";
%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<!doctype html>
<html lang="zh-CN">
<head>
<script src="<%=path%>/js/client.js" type="text/javascript" charset="utf-8"></script>
<script type="text/javascript">
	function submit(){
		if(browser.versions.android==true){
			window.SysClientJs.submitOrderShangPin("${orderinfo}");
		}else if(browser.versions.iPhone==true||browser.versions.iPad==true||browser.versions.ios==true){
			setWebitEvent("${orderinfo}", "SPA03");
		}
			document.getElementById("myform").submit();
	
	}
</script>
</head>
<body onload="submit();">
  <form id="myform" action="https://ebank.cmbc.com.cn/weblogic/servlets/EService/CSM/B2C/servlet/ReceiveMerchantCMBCTxReqServlet" method="post">
  	<input name="orderinfo" type="hidden" value="${orderinfo} ">
  </form>
</body>
</html>
