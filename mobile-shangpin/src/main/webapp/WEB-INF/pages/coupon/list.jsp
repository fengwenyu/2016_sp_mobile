<%@ page language="java" contentType="text/html; charset=UTF-8" trimDirectiveWhitespaces="true"
    pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/pages/common/include.inc.jsp"%>

<rapid:override name="custum">
	<link href="${cdn:css(pageContext.request) }/styles/shangpin/css/page/user_order_result.css" rel="stylesheet" />
	<style>
		.alContent{min-height:480px;}
		.coupons_active_order{padding:0 10px;}
		.coupons_active_order fieldset { width: 100%; padding: 10px 0 20px; }
		.coupons_active_order fieldset legend { color: #2d2d2d; font-size: 16px; height: 31px; line-height: 31px; display: block; width: 100%; box-sizing: border-box; }
		.coupons_active_order fieldset p { background-color: #fff; height: 31px; line-height: 31px; color: #999; position: relative; }
		.coupons_active_order fieldset p input[type=text] { border: none; background: rgba(0,0,0,0); height: 29px; z-index: 0; width: 62%; font-size: 14px; padding: 0 5px; border-bottom: 1px solid #d8d8d8; }
		.coupons_active_order fieldset p span { display: inline-block; width: 78%; color: #555; }
		.coupons_active_order fieldset p a.coupons_submit { position: absolute; right: 0; display: inline-block; width: 30%; height: 29px; line-height: 29px; text-align: center; font-size: 14px; background-color: #f2f2f2; color: #555; border: 1px solid #aaa; margin-left: 2%; }
		.coupons_active_order fieldset p a.coupons_submited,.coupons_active_order fieldset p a.coupons_submit:hover, .coupons_active_order fieldset p a.coupons_submit:active { background-color: #aa0006; color:#fff; border: 1px solid #fff; }
				
	</style>
	<script type="text/javascript" charset="utf-8">
		var ver = Math.random();
			loader = SP.core
				.install("${cdn:js(pageContext.request)}/styles/shangpin/js/jquery.min.js?" + ver)
				.using("${cdn:js(pageContext.request)}/styles/shangpin/js/comm.js?" + ver)
				.excute()
				.using("${cdn:js(pageContext.request)}/styles/shangpin/js/user_new_coupons.js?" + ver)
				.using("${cdn:js(pageContext.request)}/styles/shangpin/js/j.dialogs.js?" + ver)
				.using("${cdn:js(pageContext.request)}/styles/shangpin/js/coupon/coupon.js?" + ver)
				.excute();
	</script>
</rapid:override>



<rapid:override name="page_title">
	优惠券
</rapid:override>

<rapid:override name="content">
	<ul class="tabs_menu coupon_menu">
        <li class="cur">未使用</li>
        <li>已使用</li>
    </ul>
    <form class="coupons_active_order">
	    <fieldset>
		   <p>
			   <input type="text" id="coupons_code" name="coupons_code" placeholder="输入优惠劵编码" required="">
			   <a href="javascript:activeCoupon();" class="coupons_submit">确认</a>
		   </p>
		</fieldset>
    </form>
    <div class="coupon_block">
    	<%--未使用 --%>
		<c:import url="/coupon/unsed?type=0"/>
		<%--已使用 --%>
		<c:import url="/coupon/used?type=1"/>
    </div>
</rapid:override>
<rapid:override name="down_page">
	
</rapid:override>
<%@ include file="/WEB-INF/pages/common/common.jsp" %> 