<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ include file="/WEB-INF/pages/common/include.inc.jsp"%>

<rapid:override name="title">
	购物车
</rapid:override>
<rapid:override name="custum">
	<link href="${cdn:css(pageContext.request)}/styles/shangpin/css/page/order.new.css?20141028" rel="stylesheet" />
	<script type="text/javascript" charset="utf-8">
			loader = SP.core
				.install("${cdn:js(pageContext.request)}/styles/shangpin/js/jquery.min.js${ver}")
				.using("${cdn:js(pageContext.request)}/styles/shangpin/js/j.lazyload.js${ver}")
				.excute()
				.using("${cdn:js(pageContext.request)}/styles/shangpin/js/config.sim.js${ver}")
				.using("${cdn:js(pageContext.request)}/styles/shangpin/js/j.floatCalculate.js${ver}")
				.using("${cdn:js(pageContext.request)}/styles/shangpin/js/j.appDialogs.js${ver}")
				.excute()
				.using("${cdn:js(pageContext.request)}/styles/shangpin/js/cart/cart_list.js${ver}")
	</script>
</rapid:override >
<rapid:override name="header">
</rapid:override>
<rapid:override name="content">
	 <section class="detail_block">
    <div class="no_result">
      <img src="${ctx}/styles/shangpin/images/e.gif" lazy="${ctx}/styles/shangpin/images/order/pic_order.png" width="128" height="93">
      <span>您的购物车什么都没有哦！</span>
	  <a href="${ctx}/index">去逛逛</a>
    </div>
  </section>
</rapid:override>
 <rapid:override name="footer">
</rapid:override> 
<%@ include file="/WEB-INF/pages/common/base.jsp" %> 