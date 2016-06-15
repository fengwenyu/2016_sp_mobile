
<%@ page language="java" contentType="text/html; charset=UTF-8" trimDirectiveWhitespaces="true"
    pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/pages/common/include.inc.jsp"%>


<rapid:override name="custum">
	<script src="${cdn:js(pageContext.request)}/styles/shangpin/js/jquery.min.js" type="text/javascript" charset="utf-8"></script>
	<script src="${cdn:js(pageContext.request)}/styles/shangpin/js/weixin/fashion_pay.js" type="text/javascript" charset="utf-8"></script>
	
</rapid:override > 


<%-- 浏览器标题 --%>
<rapid:override name="title">
	我的订单
</rapid:override>

<%-- 页标题 --%>
<rapid:override name="page_title">
	订单管理
</rapid:override>

<rapid:override name="content">

<body>
 
	<c:if test="${checkWX }">
		<input type="hidden" id="appId" value="${wxPayData.appId }" >
	<input type="hidden" id="timeStamp" value="${wxPayData.timeStamp }" >
	<input type="hidden" id="packageStr" value="${wxPayData.packageStr }" >
	<input type="hidden" id="paySign" value="${wxPayData.paySign }" >
	<input type="hidden" id="signType" value="${wxPayData.signType }" >
	<input type="hidden" id="nonceStr" value="${wxPayData.nonceStr }" >
	<input type="hidden" id="orderId" value="${orderId }" >
	</c:if>

</body>
  
</rapid:override> 

 <%-- 页面的尾部 --%>      
 <rapid:override name="footer">
 
 </rapid:override> 

<%@ include file="/WEB-INF/pages/common/common.jsp" %> 
