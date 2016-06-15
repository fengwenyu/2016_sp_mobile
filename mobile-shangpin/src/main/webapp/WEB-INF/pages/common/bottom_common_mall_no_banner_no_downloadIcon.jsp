<%@ page language="java" contentType="text/html; charset=UTF-8" trimDirectiveWhitespaces="true"
		 pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/pages/common/include.inc.jsp"%>

<%-- 非首页页面全部依赖这个页面 --%>
<rapid:override name="header">
	<c:if test="${!checkWX&&!checkAPP}">
		<!--头部 start-->
		<div class="topFix">
			<section id="topMenu">
				<div class="topBack">
					<a href="javascript:history.go(-1);" class="backBtn">&nbsp;</a>
		        		<span class="top-title">
			        		<rapid:block name="page_title">
								尚品网触屏版
							</rapid:block>
			        	</span>
					<ul class="alUser_icon clr">
						<li><a href="<c:url value='/index'/>"><img src="${cdn:pic(pageContext.request)}/styles/shangpin/images/home_icon.png" width="25" height="25" alt="首页"></a></li>
					</ul>
				</div>
			</section>
		</div>
		<!--头部 end-->
	</c:if>
</rapid:override>
<rapid:override name="footer">
	<c:if test="${!checkWX&&!checkAPP}">
		<c:if test="${!checkWX&&!checkAPP}">
			<%@ include file="/WEB-INF/pages/common/mall_footer.jsp"%>
		</c:if>
		<br/>
		<br/>
		<br/>
	</c:if>
</rapid:override>
<%@ include file="/WEB-INF/pages/common/base_no_content_banner_no_downloadIcon.jsp" %>