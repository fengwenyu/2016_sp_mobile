<%@ page language="java" contentType="text/html; charset=UTF-8" trimDirectiveWhitespaces="true"
    pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/pages/common/include.inc.jsp"%>
<rapid:override name="custum">
	<link href="${cdn:css(pageContext.request)}/styles/shangpin/css/page/account.css" rel="stylesheet" />
	<link href="${cdn:css(pageContext.request)}/styles/shangpin/css/page/order.css" rel="stylesheet" />
	<script type="text/javascript" charset="utf-8">
			loader = SP.core
				.install("${cdn:js(pageContext.request)}/styles/shangpin/js/zepto.min.js${ver}")
				.using("${cdn:js(pageContext.request)}/styles/shangpin/js/ajaxSettings.js${ver}")
				.using("${cdn:js(pageContext.request)}/styles/shangpin/js/lazyload.js${ver}")
				.using("${cdn:js(pageContext.request)}/styles/shangpin/js/config.sim.js${ver}")
				.using("${cdn:js(pageContext.request)}/styles/shangpin/js/comm.js${ver}")
				.using("${cdn:js(pageContext.request)}/styles/shangpin/js/coupon/coupon_list.js${ver}")
				.excute();
	</script>
</rapid:override >
<rapid:override name="content">
	<c:import url="/nav?navId=13"></c:import>
<div class="alContent">
    <nav class="alOrder_listMenu">
      <ul>
        <li id="unused"<c:if test="${couponType eq '0'}"> class="cur"</c:if>><a href="javascript:void(0);">未使用</a></li>
        <li id="used"<c:if test="${couponType eq '1'}"> class="cur"</c:if>><a href="javascript:void(0);">已使用</a></li>
        <li id="expired"<c:if test="${couponType eq '3'}"> class="cur"</c:if>><a href="javascript:void(0);">已过期</a></li>
        <li id="all"<c:if test="${couponType eq '-1'}"> class="cur"</c:if>><a href="javascript:void(0);">全部</a></li>
      </ul>
    </nav>
    <div id="content">
    <%@ include file="list_cell.jsp" %> 
   	</div>
   	 <div id="loadOrder" class="alList_moreBtn loading"  style="display:none"><img alt="" src="${cdn:pic(pageContext.request)}/styles/shangpin/images/l1.gif"></div>
</div>
</rapid:override>

<%@ include file="/WEB-INF/pages/common/base.jsp" %> 