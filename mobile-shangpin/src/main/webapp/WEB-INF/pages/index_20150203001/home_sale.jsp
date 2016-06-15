<%@ page language="java" contentType="text/html; charset=UTF-8"
	trimDirectiveWhitespaces="true" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/pages/common/include.inc.jsp"%>

<!--今日特卖-->
<c:if test="${fn:length(homeSales) > 0}">
	<c:forEach var="homeSale" items="${homeSales}">
		<c:if test="${homeSale.type=='1' }">
			<%--今日特卖<em class="text-color-red">DAILY EVENTS</em> --%>	
			<c:if test="${fn:length(homeSale.list) > 0}">
			<c:set var="hasday" value="1" scope="page"></c:set>
			<h2 class="title">
				<c:set var="first" value="true" />
				${fn:substring(homeSale.title,0,fn:indexOf(homeSale.title," ")) }
				<em class="text-color-red"></em>
			</h2>
				<div class="hallBox clr">
					<c:forEach var="homeSale" items="${homeSale.list}" varStatus="status">
						<c:choose>
							<c:when
								test="${status.index==0||status.index==1||status.index==2 }">
								<a href="${ctx }/subject/product/list?topicId=${homeSale.refContent}&postArea=0"
									class="tag"><img alt="${homeSale.name}"
									src="${cdn:pic(pageContext.request)}/styles/shangpin/images/e.gif"
									lazy="${fn:substring(homeSale.picNew,0,fn:indexOf(homeSale.picNew,'-'))}-${homeSale.width}-${homeSale.height}.jpg"
									width="100%"><span><img alt=""
										src="${cdn:pic(pageContext.request)}/styles/shangpin/images/e.gif"
										lazy="${cdn:pic(pageContext.request)}/styles/shangpin/images/new-mall/tag0${status.index +1}.png"
										width="100%"></span>
										
								 <div class="pic-des"><p>${homeSale.desc }  </p></div>		
							</a>
							</c:when>
							<c:otherwise>
								<a
									href="<c:url value='/subject/product/list?topicId=${homeSale.refContent}&postArea=0'/>"><img
									alt="${homeSale.name}"
									src="${cdn:pic(pageContext.request)}/styles/shangpin/images/e.gif"
									lazy="${fn:substring(homeSale.picNew,0,fn:indexOf(homeSale.picNew,'-'))}-${homeSale.width}-${homeSale.height}.jpg	"
									width="100%">
								 <div class="pic-des"><p>${homeSale.desc }  </p></div>			
							</a>
							</c:otherwise>
						</c:choose>
					</c:forEach>
					</div>
					<a href="<c:url value='/sale/index'/>" class="more-btn btn-color-red">全部特卖</a>
				</c:if>
		</c:if>
	</c:forEach>

		<c:forEach var="homeSale" items="${homeSales}">
		 <c:if test="${homeSale.type=='2' }">
			<c:if test="${fn:length(homeSale.list) > 0}">

			<c:if test="${hasday!=1 }">
				<h2 class="title">
					今日特卖
						<em class="text-color-red">
							TODAY'S EVENTS
						</em>
					</h2>
			</c:if>
		<div class="hallBox clr">
			<c:set var="second" value="true" />
				<c:forEach var="homeSale" items="${homeSale.list}">
					<input type="hidden" id="startTime" name="startTime" value="${homeSale.startTime}"/>
					<input type="hidden" id="startTag" name="startTag" value="${homeSale.startTag}"/>
					<c:choose>
					<c:when test="${homeSale.startTime>nowTime}">
						<a href="<c:url value='/subject/product/list?topicId=${homeSale.refContent}&postArea=0'/>" startTime="${homeSale.startTime}" startTag="${homeSale.startTag}" class="on-sale no_open">
					</c:when>
					<c:otherwise>
						<a href="<c:url value='/subject/product/list?topicId=${homeSale.refContent}&postArea=0'/>" class="on-sale">
					</c:otherwise>
					</c:choose>
					<img alt="${homeSale.name}"src="${cdn:pic(pageContext.request)}/styles/shangpin/images/e.gif" lazy="${fn:substring(homeSale.pic,0,fn:indexOf(homeSale.pic,'-'))}-616-260.jpg" width="100%">
					<span><em  class="open-time">${homeSale.startTag }</em>开启</span>
					 <div class="pic-des"><p>${homeSale.desc}</p></div>
					</a>
				</c:forEach>
			</div>
			</c:if>
		</c:if>
	</c:forEach>

	<c:if test="${first||second }">

	<div class="line"></div>
	</c:if>
</c:if>


