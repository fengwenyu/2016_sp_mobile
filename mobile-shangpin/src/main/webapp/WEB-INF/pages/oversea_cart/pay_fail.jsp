<%@ page language="java" contentType="text/html; charset=UTF-8" trimDirectiveWhitespaces="true"
    pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/pages/common/include.inc.jsp"%>

<rapid:override name="custum">
	<link href="${cdn:css(pageContext.request) }/styles/shangpin/css/page/payment.css${ver}" rel="stylesheet" />
	<script type="text/javascript" charset="utf-8">
		loader = SP.core
		.install("${cdn:js(pageContext.request)}/styles/shangpin/js/jquery.min.js${ver}")
		.using("${cdn:js(pageContext.request)}/styles/shangpin/js/comm.js${ver}")
		.excute();
	</script>
</rapid:override>

<rapid:override name="page_title">
	支付失败
</rapid:override>

<rapid:override name="content">
	<div class="alContent">
		<div class="paymet_block">
	        <div class="paymet_success">
	        	<h3>订单已提交</h3>
	            <p class="tips">1小时订单将被取消，请您尽快支付</p>
	        </div>
	        <form action="${ctx }/overseas/pay/payment" method="post">
	        <fieldset>
	          <p class="price">您需要支付 &yen;<b id="realpay">${orderItem.onlineamount }</b></p>
	        	<c:choose>
		        	<c:when test="${checkWX }">
		        	<input type="hidden" id="payId" name="payId" value="27"/>
	            	<input type="hidden" id=payChildId name="payChildId" value="58"/>
		        	<p>
	                	<span class="weixinPay" id="weixinPay">
	                    	<a href="javascript:;" class="cur">
	                            <em>微信支付</em>
	                        </a>
	                    </span>
	                </p>
		        	</c:when>
		        	<c:otherwise>
		        	<input type="hidden" id="payId" name="payId" value="30"/>
	            	<input type="hidden" id=payChildId name="payChildId" value="108"/>
		        		<p>
	                	<span class="alipay">
	                    	<a href="javascript:;" class="cur">
	                            <em>支付宝支付</em><br />
	                            储蓄卡支付需开通网银
	                        </a>
	                    </span>
	                	</p>
		        	</c:otherwise>
	        	</c:choose>
	           
	            <div class="payment_submit">
	            	<input type="hidden" id="orderId" name="orderId" value="${orderItem.mainorderno}"/>
	                <a id="pay" href="javascript:;" class="payment_btn">继续支付</a>
	                <input type="submit" class="payment_btn" value="继续支付" />
	            </div>
	        </fieldset>
	        </form>
	    </div>
	</div>
</rapid:override>
<rapid:override name="footer">

</rapid:override>
<%@ include file="/WEB-INF/pages/common/common.jsp" %> 