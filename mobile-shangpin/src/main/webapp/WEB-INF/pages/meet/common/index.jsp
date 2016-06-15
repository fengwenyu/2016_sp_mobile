<%@ page language="java" contentType="text/html; charset=UTF-8"
	trimDirectiveWhitespaces="true" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/pages/common/include.inc.jsp"%>

<rapid:override name="custum">
<script src="${cdn:js(pageContext.request)}/styles/shangpin/js/jquery.min.js${ver}"  type="text/javascript" charset="utf-8"></script>
<script src="${cdn:js(pageContext.request)}/styles/shangpin/js/lazyload.js${ver}"  type="text/javascript" charset="utf-8"></script>
<script src="${cdn:js(pageContext.request)}/styles/shangpin/js/config.sim.js${ver}"  type="text/javascript" charset="utf-8"></script>
<script src="${cdn:js(pageContext.request)}/styles/shangpin/js/downloadAppShow.js${ver}"  type="text/javascript" charset="utf-8"></script>
<script src="${cdn:js(pageContext.request)}/styles/weixin/jweixin-1.0.0.js${ver}"  type="text/javascript" charset="utf-8"></script>
<script src="${cdn:js(pageContext.request)}/styles/shangpin/js/weixin/weixin_ready.js${ver}"  type="text/javascript" charset="utf-8"></script>
<link href="${cdn:css(pageContext.request)}/styles/shangpin/css/page/meet/share.css${ver}" rel="stylesheet" />
<link href="${cdn:css(pageContext.request)}/styles/shangpin/css/swiper.min.css${ver}"  rel="stylesheet" />
<link href="${cdn:css(pageContext.request)}/styles/shangpin/css/page/font-css/font-awesome.min.css${ver}"  rel="stylesheet"/>
<script src="${cdn:js(pageContext.request)}/styles/shangpin/js/swiper.min.js${ver}"  type="text/javascript" charset="utf-8"></script>
<script src="${cdn:js(pageContext.request)}/styles/shangpin/js/j.MXTimer.js${ver}"  type="text/javascript" charset="utf-8"></script>
<script src="${cdn:js(pageContext.request)}/styles/shangpin/js/xmas.dialogs.js${ver}"  type="text/javascript" charset="utf-8"></script>
<script src="${cdn:js(pageContext.request)}/styles/shangpin/js/app_share_img.js${ver}"  type="text/javascript" charset="utf-8"></script>
<script src="${cdn:js(pageContext.request)}/styles/shangpin/js/jQueryRotate.2.2.js${ver}"  type="text/javascript" charset="utf-8"></script>
<script src="${cdn:js(pageContext.request)}/styles/shangpin/js/jquery.easing.min.js${ver}"  type="text/javascript" charset="utf-8"></script>
<c:if test="${meet.js!=null && meet.js!=''}">
	${meet.js}
</c:if>
<c:if test="${meet.css!=null && meet.css!=''}">
	${meet.css}
</c:if>
<c:if test="${meet.id!=null}">
<c:choose>
	<c:when test="${meet.id==100}">
		<%-- 特殊处理--%>
	</c:when>
	<c:otherwise>
		<%-- 默认处理 --%>
		<c:choose>
			<c:when test="${meet.status !=null }">
			<%-- 如果会场是预热的样式 --%>
				<c:if test="${meet.status==0 }">
					<c:if test="${internal==null || internal=='' || internal==0}">
						<link href="${cdn:css(pageContext.request)}/styles/shangpin/css/page/meet/${meet.id }/pre.css${ver}" rel="stylesheet" />
						<script src="${cdn:js(pageContext.request)}/styles/shangpin/js/meet/${meet.id }/pre.js${ver}"  type="text/javascript" charset="utf-8"></script>
					</c:if>
					<c:if test="${internal==1 }">
						<script src="${cdn:js(pageContext.request)}/styles/shangpin/js/meet/${meet.id }/pre.js${ver}"  type="text/javascript" charset="utf-8"></script>
					</c:if>
					<c:if test="${internal==3 }">
						<link href="${cdn:css(pageContext.request)}/styles/shangpin/css/page/meet/${meet.id }/pre.css${ver}" rel="stylesheet" />
					</c:if>
				</c:if>
				
				<c:if test="${meet.status==1 }">
					<%-- 如果会场是正式的样式 --%>
					<c:if test="${internal==null || internal=='' || internal==0}">
						<link href="${cdn:css(pageContext.request)}/styles/shangpin/css/page/meet/${meet.id }/main.css${ver}" rel="stylesheet" />
						<script src="${cdn:js(pageContext.request)}/styles/shangpin/js/meet/${meet.id }/main.js${ver}"  type="text/javascript" charset="utf-8"></script>
						
					</c:if>
					<c:if test="${internal==1 }">
						<script src="${cdn:js(pageContext.request)}/styles/shangpin/js/meet/${meet.id }/main.js${ver}"  type="text/javascript" charset="utf-8"></script>
					</c:if>
					<c:if test="${internal==3 }">
						<link href="${cdn:css(pageContext.request)}/styles/shangpin/css/page/meet/${meet.id }/main.css${ver}" rel="stylesheet" />
					</c:if>
				</c:if>
			</c:when>
		</c:choose>
	</c:otherwise>
</c:choose>
</c:if>
</rapid:override>
<%-- 浏览器标题 --%>
<rapid:override name="title">
	${meet.title }
</rapid:override>

<%-- 页标题 --%>
<rapid:override name="page_title">
	${meet.title }
</rapid:override>
<rapid:override name="content">
<%@ include file="/WEB-INF/pages/meet/common/share.jsp" %>
<input id="mmeetId"  type="hidden"  name="mmeetId"  value="${meet.id }"/>
<input id="mmeetStatus"  type="hidden"  name="mmeetStatus"  value="${meet.status }"/>
<input id="ctx"  type="hidden"  name="ctx"  value="${pageContext.request.contextPath}"/>
<input id="userId"  type="hidden"  name="userId"  value="${sessionScope.mshangpin_user.userid}"/>
<input id="isLogin"  type="hidden"  name="isLogin"  value="${sessionScope.isLogin}"/>
<input id="nowTime"  type="hidden"  name="nowTime"  value="${nowTime}"/>
<input id="activeCode"  type="hidden"  name="activeCode"  value="${meet.activeCode}"/>
<%-- 加此标签是为了获取会场第一张图片，app分享用的 ，不会-对样式造成影响--%>
<div class="share-img">
	${meet.html }
</div>
</rapid:override>
<rapid:override name="footer">
</rapid:override>
<%@ include file="/WEB-INF/pages/common/base_meet.jsp" %> 
