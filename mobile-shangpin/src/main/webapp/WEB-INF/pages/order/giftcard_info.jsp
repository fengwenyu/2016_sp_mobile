<%@ page language="java" contentType="text/html; charset=UTF-8" trimDirectiveWhitespaces="true"
    pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/pages/common/include.inc.jsp"%>

<rapid:override name="custum">
	<link href="${cdn:css(pageContext.request) }/styles/shangpin/css/page/giftcard/giftcard_pay.css${ver}" rel="stylesheet" />
	<script type="text/javascript" charset="utf-8">
		loader = SP.core
		.install("${cdn:js(pageContext.request)}/styles/shangpin/js/jquery.min.js${ver}")
		.using("${cdn:js(pageContext.request)}/styles/shangpin/js/j.dialogs.js${ver}")
		.excute()
		.using("${cdn:js(pageContext.request)}/styles/shangpin/js/giftcard/giftcard_pay.js${ver}")
		.excute();
	</script>
</rapid:override>
<rapid:override name="header">
	<c:if test="${!checkWX&&!checkAPP}">
		<div class="topFix">
	        <section>
	            <div class="topBack" >
	            	订单信息
	            <ul class="alUser_icon clr">
	                <li><a href="${ctx}/index"><img src="${cdn:pic(pageContext.request)}/styles/shangpin/images/home_icon.png" width="25" height="25" alt="首页"></a></li>
	            </ul>
	            </div>
	        </section>
	    </div>
	</c:if>
</rapid:override>
<rapid:override name="content">
	<div class="payment_block_o">
		<c:choose>
			<c:when test="${status == '0'}">
				<i><img src="${cdn:pic(pageContext.request)}/styles/shangpin/images/giftcard/payment_cancel_icon.png" /></i>
        		<h2>该订单已失效，请重新选购</h2>
			</c:when>
			<c:otherwise>
				<i><img src="${cdn:pic(pageContext.request)}/styles/shangpin/images/giftcard/payment_success_icon.png" /></i>
        		<h2>该订单已支付成功，请登录尚品网查看</h2>
			</c:otherwise>
		</c:choose>
        <a href="${ctx}/login" class="submit_btn" id="payment_s_btn">登录</a>
    </div>
</rapid:override>


<rapid:override name="footer">
	
</rapid:override>
<%@ include file="/WEB-INF/pages/common/submit_order_base.jsp" %> 