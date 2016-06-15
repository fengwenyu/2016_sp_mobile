<%@ page language="java" contentType="text/html; charset=UTF-8"
	trimDirectiveWhitespaces="true" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/pages/common/include.inc.jsp"%>
<rapid:override name="header">
	<c:if test="${!checkWX&&!checkAPP}">
		<div class="topFix">
	        <section>
	            <div class="topBack" >
	            <a href="javascript:history.go(-1);" class="backBtn">&nbsp;</a>
	            	<rapid:block name="page_title">
	            		
	            	</rapid:block>
	            <ul class="alUser_icon clr">
	                <li><a href="${ctx}/index"><img src="${cdn:pic(pageContext.request)}/styles/shangpin/images/home_icon.png" width="25" height="25" alt="首页"></a></li>
	            </ul>
	            </div>
	        </section>
	    </div>
	</c:if>
</rapid:override>
<%@ include file="/WEB-INF/pages/common/submit_order_base.jsp"%>