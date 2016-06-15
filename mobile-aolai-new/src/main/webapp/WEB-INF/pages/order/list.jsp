<%@ page language="java" contentType="text/html; charset=UTF-8" trimDirectiveWhitespaces="true"
    pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/pages/common/include.inc.jsp"%>
<rapid:override name="custum">
	<link href="${cdn:css(pageContext.request)}/styles/shangpin/css/page/account.css" rel="stylesheet" />
	<script type="text/javascript" charset="utf-8">
			loader = SP.core
				.install("${cdn:js(pageContext.request)}/styles/shangpin/js/zepto.min.js${ver}")
				.using("${cdn:js(pageContext.request)}/styles/shangpin/js/ajaxSettings.js${ver}")
				.using("${cdn:js(pageContext.request)}/styles/shangpin/js/lazyload.js${ver}")
				.using("${cdn:js(pageContext.request)}/styles/shangpin/js/config.sim.js${ver}")
				.using("${cdn:js(pageContext.request)}/styles/shangpin/js/comm.js${ver}")
				.using("${cdn:js(pageContext.request)}/styles/shangpin/js/order/order_list.js${ver}")
				.excute();
	</script>
</rapid:override >
<rapid:override name="content">
	<c:import url="/nav?navId=7"></c:import>
<div class="alContent">
	<section class="alOrder_list">
    <nav class="alOrder_listMenu">
      <ul>
        <li id="waitpay"<c:if test="${statusType eq '2'}"> class="cur"</c:if>><a href="javascript:void(0);">待支付</a></li>
        <li id="waitconfirm"<c:if test="${statusType eq '4'}"> class="cur"</c:if>><a href="javascript:void(0);">待收货</a></li>
        <li id="all"<c:if test="${statusType eq '1'}"> class="cur"</c:if>><a href="javascript:void(0);">全部</a></li>
      </ul>
    </nav>
    <div id="content">
    <%@ include file="list_cell.jsp" %> 
   	</div>
   	 <div id="loadOrder" class="alList_moreBtn loading"  style="display:none"><img alt="" src="${cdn:pic(pageContext.request)}/styles/shangpin/images/l1.gif"/></div>
  </section>
</div>
</rapid:override>

<%@ include file="/WEB-INF/pages/common/base.jsp" %> 