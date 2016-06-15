<%@ page language="java" contentType="text/html; charset=UTF-8" trimDirectiveWhitespaces="true"
    pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/pages/common/include.inc.jsp"%>

<rapid:override name="title">
	<c:choose>
	<c:when test="${star eq '2' }">
	    信和红包
	</c:when>
	<c:otherwise>
	  e保养红包
	</c:otherwise>
	</c:choose>
	</rapid:override>
<rapid:override name="custum">
<link href="${cdn:css(pageContext.request)}/styles/shangpin/css/star-packet/red_32/base.css${ver}" rel="stylesheet" />
<link href="${cdn:css(pageContext.request)}/styles/shangpin/css/star-packet/red_32/swiper.min.css${ver}" rel="stylesheet" />
	<c:choose>
	<c:when test="${star eq '2' }">
	    <link href="${cdn:css(pageContext.request)}/styles/shangpin/css/star-packet/red_510/packet.css${ver}" rel="stylesheet" />
	</c:when>
	<c:otherwise>
	  <link href="${cdn:css(pageContext.request)}/styles/shangpin/css/star-packet/red_48/packet.css${ver}" rel="stylesheet" />
	</c:otherwise>
	</c:choose>
</rapid:override>
<rapid:override name="content">
 
 <c:choose>
	<c:when test="${star eq '2' }">
	    <input type="hidden" name="_shareUrl"  id="_shareUrl"  value="http://m.shangpin.com/red/index?star=2"/>
		<input type="hidden" name="_currentUrl" id="_currentUrl"  value=""/>
	    <input type="hidden" name="star"  id="star"  value="${star}"/>
	    <input type="hidden" name="_mainTitle"  id="_mainTitle"  value="信和大金融联合尚品网送您红包"/>
	    <input type="hidden" name="_mainDesc"  id="_mainDesc"  value="信和大金融联合轻奢电商尚品网，现金红包拿到手软"/>
	    <input type="hidden" name="_mainImgUrl" id="_mainImgUrl"  value="${cdn:pic(pageContext.request)}/styles/shangpin/images/star-packet/red_510/300-300.jpg"/>
	    <%@ include file="/WEB-INF/pages/star/red/db_510info.jsp"%>
	</c:when>
	<c:otherwise>
	  	<input type="hidden" name="_shareUrl"  id="_shareUrl"  value="http://m.shangpin.com/red/index?star=1"/>
		<input type="hidden" name="_currentUrl" id="_currentUrl"  value=""/>
	    <input type="hidden" name="star"  id="star"  value="${star}"/>
	    <input type="hidden" name="_mainTitle"  id="_mainTitle"  value="【福利】时髦的你快来抢90元！"/>
	    <input type="hidden" name="_mainDesc"  id="_mainDesc"  value="快来拿下最时髦的美好单品"/>
	    <input type="hidden" name="_mainImgUrl" id="_mainImgUrl"  value="${cdn:pic(pageContext.request)}/styles/shangpin/images/star-packet/red_48/300-300.jpg"/>
	    <%@ include file="/WEB-INF/pages/star/red/db_info.jsp"%>
	</c:otherwise>
	</c:choose>
</rapid:override>
<rapid:override name="static_file">
    <script src="${cdn:js(pageContext.request)}/styles/weixin/jweixin-1.0.0.js${ver}" type="text/javascript" charset="utf-8"></script>
	<script src="${cdn:js(pageContext.request)}/styles/shangpin/js/star-packet/20151020/jquery.min.js${ver}" type="text/javascript" charset="utf-8"></script>
	<script src="${cdn:js(pageContext.request)}/styles/shangpin/js/star-packet/20151020/weixin_ready.js${ver}" type="text/javascript" charset="utf-8"></script>
	<script src="${cdn:js(pageContext.request)}/styles/shangpin/js/star-packet/red/packet.js${ver}" type="text/javascript" charset="utf-8"></script>
</rapid:override>
<%@ include file="/WEB-INF/pages/star/red/red_packet_base.jsp" %>