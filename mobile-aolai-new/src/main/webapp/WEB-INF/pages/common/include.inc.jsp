<%@ page language="java" contentType="text/html; charset=UTF-8" trimDirectiveWhitespaces="true"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@ taglib uri="http://www.rapid-framework.org.cn/rapid" prefix="rapid" %>
<%@ taglib uri="http://m.shangpin.com/cdn" prefix="cdn" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<c:set var="ctx" value="${pageContext.request.contextPath}"/>
<c:set var="ver" value="?2015010141345"/>

<c:set var="ua" value="${header['User-Agent']}" />
<c:set var="micromessenger" value="micromessenger" />
<c:set var="aolaiIOSApp" value="AolaiIOSApp" />
<c:set var="shangpinAndroidApp" value="ShangpinAndroidApp" />
<c:set var="aolaiAndroidApp" value="AolaiAndroidApp" />
<c:set var="shangpinIOSApp" value="ShangpinIOSApp" />

<c:if test="${fn:containsIgnoreCase(ua, micromessenger)}">
	<c:set var="checkWX" value="true" />
</c:if>

<c:if test="${fn:containsIgnoreCase(ua, shangpinIOSApp)||fn:containsIgnoreCase(ua, aolaiAndroidApp)||fn:containsIgnoreCase(ua, aolaiIOSApp)||fn:containsIgnoreCase(ua, shangpinAndroidApp)}">
	<c:set var="checkAPP" value="true" />
</c:if>