<%@ page language="java" contentType="text/html; charset=UTF-8" trimDirectiveWhitespaces="true"
    pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/pages/common/include.inc.jsp"%>
<rapid:override name="header">
<c:if test="${!checkWX&&!checkAPP}">
		<!--头部 start-->
		<div class="topFix">
			<section>
				<div class="topBack">
					<a href="javascript:history.go(-1);" class="backBtn">&nbsp;</a> <span>
						<rapid:block name="page_title">
	        		尚品网触屏版
	        		</rapid:block>
					</span>

					<ul class="alUser_icon clr">
						<li><a href="<c:url value='/index'/>"><img
								src="${cdn:pic(pageContext.request)}/styles/shangpin/images/home_icon.png"
								width="25" height="25" alt="首页"></a></li>
						<%--
	            <li><a href="#"><img src="${cdn:pic(pageContext.request)}/styles/shangpin/images/order_icon.png" width="25" height="25" alt="购物车"></a></li>
	            <li><a href="${ctx}/user/info"><img src="${cdn:pic(pageContext.request)}/styles/shangpin/images/user_icon.png" width="25" height="25" alt="账户"></a></li>
	            --%>
					</ul>
				</div>
			</section>
		</div>
		<!--头部 end-->
	</c:if>

</rapid:override>

<%@ include file="/WEB-INF/pages/common/weixin_base.jsp" %> 