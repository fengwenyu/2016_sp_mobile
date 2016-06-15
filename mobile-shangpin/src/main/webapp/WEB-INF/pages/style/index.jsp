<%@ page language="java" contentType="text/html; charset=UTF-8"
	trimDirectiveWhitespaces="true" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/pages/common/include.inc.jsp"%>

<rapid:override name="custum">
	<link href="${cdn:css(pageContext.request)}/styles/shangpin/css/page/style_list.css${ver}" rel="stylesheet" />
	<link href="${cdn:css(pageContext.request)}/styles/shangpin/css/page/style.css${ver}" rel="stylesheet" />
	<script type="text/javascript" charset="utf-8">
		
			loader = SP.core
			.install("${cdn:js(pageContext.request)}/styles/shangpin/js/jquery.min.js${ver}")
			.using("${cdn:js(pageContext.request)}/styles/shangpin/js/lazyload.js${ver}")
			.using("${cdn:js(pageContext.request)}/styles/shangpin/js/j.dialogs.js${ver}")
			.using("${cdn:js(pageContext.request)}/styles/shangpin/js/style.js${ver}")
			.excute()
			.using("${cdn:js(pageContext.request)}/styles/shangpin/js/config.sim.js${ver}")
			
			.excute(function(){
				isHome(false);
			});
			
	</script>
</rapid:override>
<%-- 浏览器标题 --%>
<rapid:override name="title">
	STYLE
</rapid:override>

<%-- 页标题 --%>
<rapid:override name="page_title">
	STYLE
</rapid:override>
<rapid:override name="content">
	<div class="alContent" style="padding: 0">
		<div class="boxSizing styleBigIMG">
			<c:forEach var="lo" items="${look}">
				<dl>
					<dt>
						<a href="<c:url value='/style/list?id=${lo.id}'/>"><img
							src="${cdn:pic(pageContext.request)}/styles/shangpin/images/e.gif"
							lazy="${fn:replace(lo.pic,'-10-10','-580-670')}" width="100%">
						</a>
					</dt>
					<dd>
						<c:forEach var="style" items="${lo.style}">
							<span> ${ style} </span>
						</c:forEach>
					</dd>
				</dl>
			</c:forEach>
		</div>
		<input type="hidden" id="pageIndex" name="pageIndex"
			value='${pageIndex}' />
		<c:if test="${haveMore==1 }">
			<a id="haveMore" class="list_moreLink" href="javascript:getMore();">点击查看更多</a>
		</c:if>
		<c:if test="${haveMore==0 }">
			<a class="alList_moreBtn" style="visibility: hidden;"></a>
		</c:if>

	</div>
</rapid:override>
<rapid:override name="down_page">
	
</rapid:override>
<%@ include file="/WEB-INF/pages/common/another_common.jsp"%>
