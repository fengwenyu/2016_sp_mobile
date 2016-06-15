<%@ page language="java" contentType="text/html; charset=UTF-8"
	trimDirectiveWhitespaces="true" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/pages/common/include.inc.jsp"%>

<rapid:override name="custum">
	<link href="${cdn:css(pageContext.request)}/styles/shangpin/css/page/meet/196/main.css${ver}" rel="stylesheet" />
	 <link href="${cdn:css(pageContext.request)}/styles/shangpin/css/page/meet/share.css${ver}" rel="stylesheet" />
	<script src="${cdn:js(pageContext.request)}/styles/shangpin/js/zepto.1.1.3.js${ver}"  type="text/javascript" charset="utf-8"></script>
	<script src="${cdn:js(pageContext.request)}/styles/shangpin/js/lazyload.js${ver}"  type="text/javascript" charset="utf-8"></script>
	<script src="${cdn:js(pageContext.request)}/styles/shangpin/js/meet/196/detail.dialogs.js${ver}"  type="text/javascript" charset="utf-8"></script>
	<script src="${cdn:js(pageContext.request)}/styles/shangpin/js/config.sim.js${ver}"  type="text/javascript" charset="utf-8"></script>
	<script src="${cdn:js(pageContext.request)}/styles/shangpin/js/comm.js${ver}"  type="text/javascript" charset="utf-8"></script>
	<script src="${cdn:js(pageContext.request)}/styles/weixin/jweixin-1.0.0.js${ver}"  type="text/javascript" charset="utf-8"></script>
	<script src="${cdn:js(pageContext.request)}/styles/shangpin/js/weixin/WXShareM.js${ver}"  type="text/javascript" charset="utf-8"></script>
	<script src="${cdn:js(pageContext.request)}/styles/shangpin/js/meet/196/main.js${ver}"  type="text/javascript" charset="utf-8"></script>

</rapid:override>
<%-- 浏览器标题 --%>
<rapid:override name="title">
	${meet.title }
</rapid:override>

<%-- 页标题 --%>
<rapid:override name="page_title">
	${meet.title }
</rapid:override>
<rapid:override name="header">
	<c:if test="${!checkWX&&!checkAPP}">
		<%@ include file="/WEB-INF/pages/common/header.jsp"%>
	</c:if>
</rapid:override>
<rapid:override name="content">
<c:if test="${checkAPP}">
  <div class="shang_share" > 
         <a href="shangpinapp://phone.shangpin/actiongoshare?title=${meet.title }&desc=这个活动很给力！&url=http://m.shangpin.com/meet/${meet.id}" style="height:50px;"><img src="${cdn:pic(pageContext.request)}/styles/shangpin/images/b_share.png" width="40"></a>
   </div>
</c:if>
<input id="_iswx" name="_iswx" type="hidden" value="${checkWX }"/>
<input type="hidden" name="_shareUrl"  id="_shareUrl"  value="http://m.shangpin.com/meet/${meet.id }"/>
<input type="hidden" name="_mainTitle"  id="_mainTitle"  value="${meet.title}"/>
<input type="hidden" name="_mainDesc"  id="_mainDesc"  value="这个活动很给力！"/>
<input type="hidden" name="_mainImgUrl" id="_mainImgUrl"  value="${ctx }/styles/shangpin/images/share-logo.png"/>
	${meet.html }
</rapid:override>
<rapid:override name="footer">
<input type="hidden" id="_isapp" name="_isapp" value="${checkAPP }" />
</rapid:override>
<%@ include file="/WEB-INF/pages/common/base_banner.jsp" %> 
