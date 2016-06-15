<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/pages/common/include.inc.jsp"%>

<%--头部 start--%>
<c:if test="${!checkWX&&!checkAPP}">
	<div class="topFix">
	    <section>
	        <div class="topBack">
		        <a href="javascript:;" class="backBtn js-order-back-btn">&nbsp;</a>
		        <span class="top-title">提交订单</span>
		        <ul class="alUser_icon clr">
<%-- 		            <li><a href="${ctx}/index"><img src="${cdn:pic(pageContext.request)}/styles/shangpin/images/home_icon.png" width="25" height="25" alt="首页"></a></li> --%>
		        </ul>
		        <a class="add-address-btn">新增地址</a>
	        </div>
	    </section>
	</div>
</c:if>
<%--头部 end--%>