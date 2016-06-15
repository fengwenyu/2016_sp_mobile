<%@ page language="java" contentType="text/html; charset=UTF-8" trimDirectiveWhitespaces="true"
    pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/pages/common/include.inc.jsp"%>
<rapid:override name="custum">
	<link href="${cdn:css(pageContext.request)}/styles/shangpin/css/page/coupon.css${ver}" rel="stylesheet" />
	<link href="${cdn:css(pageContext.request)}/styles/shangpin/css/page/getCoupon.css${ver}" rel="stylesheet" />
	<script type="text/javascript" charset="utf-8">
	loader = SP.core
	.install("${cdn:js(pageContext.request)}/styles/shangpin/js/jquery.min.js${ver}")
	.using("${cdn:js(pageContext.request)}/styles/shangpin/js/lazyload.js${ver}")
	.excute()
	.using("${cdn:js(pageContext.request)}/styles/shangpin/js/config.sim.js${ver}")
	.using("${cdn:js(pageContext.request)}/styles/shangpin/js/j.j.floatCalculate.js${ver}")
	.using("${cdn:js(pageContext.request)}/styles/shangpin/js/j.j.appDialogs.js${ver}")
	.excute();
	</script>
</rapid:override >

<rapid:override name="content">


    <div class="coupon coupon_success" style="min-height:350px;">                   
            <p style=" text-align: center;line-height: 180%; margin-top: 50px;">
            <c:choose>
              <c:when test="${code eq '1' }">您已经领取了优惠券</c:when>
              <c:otherwise>您已成功领取优惠券</c:otherwise>
            </c:choose>
             <br>
				<a href="${ctx }/coupon/list">查看我的优惠券</a>
			</p>
    </div>
</rapid:override> 

<%@ include file="/WEB-INF/pages/common/common.jsp" %> 

