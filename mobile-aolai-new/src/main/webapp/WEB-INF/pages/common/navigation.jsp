<%@ page language="java" contentType="text/html; charset=UTF-8" trimDirectiveWhitespaces="true"
    pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/pages/common/include.inc.jsp"%>

	 <nav class="alNav">
	   <ul>
	<c:forEach items="${navList}" var="navigation" varStatus="status">
	   <c:if test="${status.last }">
			<li>${navigation.text }</li>
		</c:if>
	   <c:if test="${!status.last }">
	   <c:set var="urlnav" value="${navigation.link }"/>
			<li><a href="<c:url value='${urlnav }'/>">${navigation.text }</a></li>
		</c:if>
	</c:forEach>	
 	</ul>
 </nav>
