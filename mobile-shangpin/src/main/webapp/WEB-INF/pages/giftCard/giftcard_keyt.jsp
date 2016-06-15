<%@ page language="java" contentType="text/html; charset=UTF-8" trimDirectiveWhitespaces="true"
    pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/pages/common/include.inc.jsp"%>
<rapid:override name="custum">
		<link href="${cdn:css(pageContext.request)}/styles/shangpin/css/page/giftcard/giftcardDetail.css${ver}" rel="stylesheet" />
		
</rapid:override>

<%-- 浏览器标题 --%>
<rapid:override name="title">
查询充值秘钥
</rapid:override>

<%-- 页标题 --%>
<rapid:override name="page_title">
查询充值秘钥
</rapid:override>
<rapid:override name="downloadAppShowHead">

</rapid:override>

<rapid:override name="content">
	
	<div class="pay-success-box" style="min-height: 400px;">
    	<i><img src="${cdn:pic(pageContext.request)}/styles/shangpin/images/giftcard/detail/query_key.png"></i>
        <p class="text_key">
        <c:if test="${giftCardKeyt !=null&&fn:length(giftCardKeyt.list) >0}">
	        	<c:forEach var="list" items="${giftCardKeyt.list }">
	        		${list.desc }　秘钥：<em>${list.keyt }</em><br>
	        	</c:forEach>
	     </c:if>  
        </p>
    </div>
	
	
</rapid:override>

 
<%-- 页面的尾部 --%>      
<rapid:override name="down_page">

</rapid:override>

<%@ include file="/WEB-INF/pages/common/another_common_mall_banner.jsp" %> 
