<%@ page language="java" contentType="text/html; charset=UTF-8" trimDirectiveWhitespaces="true"
    pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/pages/common/include.inc.jsp"%>
<rapid:override name="custum">
	<link href="${cdn:css(pageContext.request)}/styles/shangpin/css/page/account.css" rel="stylesheet" />
	<script type="text/javascript" charset="utf-8">
			loader = SP.core
				.install("${cdn:js(pageContext.request)}/styles/shangpin/js/zepto.min.js${ver}")
				.using("${cdn:js(pageContext.request)}/styles/shangpin/js/lazyload.js${ver}")
				.using("${cdn:js(pageContext.request)}/styles/shangpin/js/config.sim.js${ver}")
				.using("${cdn:js(pageContext.request)}/styles/shangpin/js/comm.js${ver}")
				.excute();
	</script>
</rapid:override >
<rapid:override name="content">
	<c:import url="/nav?navId=6"></c:import>
	<div class="alContent">
  <div class="account_info">

    <dl>
      <dt class="account_img">
      	<img src="${cdn:pic(pageContext.request)}/styles/shangpin/images/e.gif" lazy="<c:if test="${sessionScope.maolai_user.gender eq 1  }">${cdn:pic(pageContext.request)}/styles/shangpin/images/man.png</c:if><c:if test="${sessionScope.maolai_user.gender eq 0 }">${cdn:pic(pageContext.request)}/styles/shangpin/images/woman.png</c:if>" width="80" height="80">
      </dt>
      <dd class="account_txt">
      	${sessionScope.maolai_user.name}
      	<br />会员级别：${sessionScope.maolai_user.level}<br />
      </dd>
      <dd class="account_btn">
        <a href="${ctx }/user/order/list" class="alList_moreBtn">订单管理</a>
        <a href="${ctx }/user/address/list" class="alList_moreBtn">管理收货地址</a>
        <a href="${ctx }/user/coupon/list" class="alList_moreBtn">我的优惠券</a>
      </dd>
    </dl>

    <menu>
      <li><a href="<c:url value='/user/order/list?statusType=2'/>">待支付订单<span>${buyInfo.waitpaycount }</span></a></li>
      <li><a href="<c:url value='/user/order/list?statusType=4'/>">待收货订单<span>${buyInfo.preparegoodscount+buyInfo.delivergoodscount }</span></a></li>
    </menu>
  </div>

</div>
</rapid:override>

<%@ include file="/WEB-INF/pages/common/base.jsp" %> 