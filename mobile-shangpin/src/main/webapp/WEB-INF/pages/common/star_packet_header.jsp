<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/pages/common/include.inc.jsp"%>

<%--头部 start--%>
<c:if test="${!checkWX&&!checkAPP&&!checkWeibo}">
	<div class="topFix">
	    <section>
	        <div class="topBack" >
	        <c:choose>
	        	<c:when test="${index}">
	        		 <a href="#">&nbsp;</a>
	        	</c:when>
	        	<c:otherwise>
	        		 <a href="javascript:history.go(-1);" class="backBtn">&nbsp;</a>
	        	</c:otherwise>
	        </c:choose>
	       		<rapid:block name="page_title">
	       			
	       		</rapid:block>
	        </div>
	    </section>
	 </div>
</c:if>
<%--头部 end--%>