<%@ page language="java" contentType="text/html; charset=UTF-8" trimDirectiveWhitespaces="true"
    pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/pages/common/include.inc.jsp"%>

<!--最新上架-->
<c:if test="${fn:length(activities) > 0}">
	<h2 class="title">今日上新<em class="text-color-green"></em></h2>
	<div class="hallBox clr">
		<c:forEach var="activity" items="${activities}">
			<a href="${ctx}/subject/product/list?topicId=${activity.refContent}&postArea=0" class="on-sale">
				<img alt="" src="${cdn:pic(pageContext.request)}/styles/shangpin/images/e.gif"
								lazy="${fn:substring(activity.picNew,0,fn:indexOf(activity.picNew,'-'))}-${activity.width}-${activity.height}.jpg" width="100%">
					
				<div class="pic-des"><p>${activity.desc}</p></div>
			</a> 
		</c:forEach>
	</div>
	<a class="more-btn btn-color-green" href="${ctx}/index/release/list?pageIndex=1&pageSize=20&origin=1" class="more-btn btn-color-green">全部上新</a>
	<div class="line"></div>
</c:if>
