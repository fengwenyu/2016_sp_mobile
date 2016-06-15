<%@ page language="java" contentType="text/html; charset=UTF-8" trimDirectiveWhitespaces="true"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@ taglib uri="http://www.rapid-framework.org.cn/rapid" prefix="rapid" %>
<%@ taglib uri="http://m.shangpin.com/cdn" prefix="cdn" %>

<c:set var="ctx" value="${pageContext.request.contextPath}"/>
<c:set var="basePath" value="${pageContext.request.scheme}://${pageContext.request.serverName}:${pageContext.request.serverPort}${pageContext.request.contextPath}"/>
<c:set var="ver" value="?2016102414267689"/>
<c:set var="ua" value="${header['User-Agent']}" />
<c:set var="origin" value="${header['origin']}" />
<c:set var="micromessenger" value="micromessenger" />
<c:set var="aolaiIOSApp" value="AolaiIOSApp" />
<c:set var="shangpinAndroidApp" value="ShangpinAndroidApp" />
<c:set var="aolaiAndroidApp" value="AolaiAndroidApp" />
<c:set var="shangpinIOSApp" value="ShangpinIOSApp" />
<c:set var="appFlag" value="app" />
<c:set var="weibo" value="weibo" />
<c:set var="imei" value="${header['imei']}" />
<c:set var="channleNo" value="${sessionScope.mshangpin_thridtoken_channelNo}" />
<c:if test="${fn:containsIgnoreCase(ua, micromessenger)}">
	<c:set var="checkWX" value="true" />
</c:if>
<c:if test="${fn:containsIgnoreCase(origin, appFlag)||fn:containsIgnoreCase(ua, shangpinAndroidApp)||fn:containsIgnoreCase(ua, shangpinIOSApp)}">
	<c:set var="checkAPP" value="true" />
</c:if>
<c:if test="${fn:containsIgnoreCase(ua, weibo)}">
	<c:set var="checkWeibo" value="true" />
</c:if>
<c:if test="${channleNo!=null&&(channleNo=='TCL'||channleNo=='A001')}">
	<c:set var="checkTCL" value="true" />
</c:if>
<c:if test="${channleNo!=null && channleNo=='A001'}">
	<c:set var="checkWX" value="true" />
	<c:set var="shangpinKE" value="true" />
</c:if>