<%@ page language="java" contentType="text/html; charset=UTF-8" trimDirectiveWhitespaces="true"
    pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/pages/common/include.inc.jsp"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>

<rapid:override name="custum">
	<link href="${cdn:css(pageContext.request) }/styles/shangpin/css/page/2015TOPSHOP01.css${ver}" rel="stylesheet" />
	<script type="text/javascript" charset="utf-8">
		loader = SP.core
		.install("${cdn:js(pageContext.request)}/styles/shangpin/js/jquery.min.js${ver}")
		.using("${cdn:js(pageContext.request)}/styles/shangpin/js/lazyload.js${ver}")
		.excute()
		.using("${cdn:js(pageContext.request)}/styles/shangpin/js/config.sim.js${ver}")
		.excute();
	</script>
</rapid:override>
<rapid:override name="header">
	<c:if test="${!checkWX&&!checkAPP}">
		<div class="topFix">
			<section>
				<div class="topBack">
					<a href="javascript:history.go(-1);" class="backBtn">&nbsp;</a> <span>
						${headInfo.name}
					</span>

					<ul class="alUser_icon clr"></ul>
				</div>
			</section>
		</div>
	</c:if>
</rapid:override>

<rapid:override name="content">
		<c:choose>
			<c:when test="${!checkWX&&!checkAPP}">
				<div class="alContent">
			</c:when>
			<c:otherwise>
				<div class="alContent" style="margin-top: 0;">
			</c:otherwise>
		</c:choose>
		<div class="main_box">
	      <p class="top_img"><a href="#"><img src='${fn:replace(headInfo.logo, "-10-10", "-640-450")}' /></a></p>
	      <h3>品牌介绍</h3>
	      <p class="t_text">${headInfo.about}</p>
	    </div>
	</div>
</rapid:override>
<rapid:override name="footer">
  	
 </rapid:override>
<%@ include file="/WEB-INF/pages/common/another_base.jsp" %> 