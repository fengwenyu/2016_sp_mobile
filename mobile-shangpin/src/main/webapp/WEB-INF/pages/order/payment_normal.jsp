<%@ page language="java" contentType="text/html; charset=UTF-8" trimDirectiveWhitespaces="true"
    pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/pages/common/include.inc.jsp"%>

<rapid:override name="custum">
	<link href="${cdn:css(pageContext.request) }/styles/shangpin/css/page/payment.css${ver}" rel="stylesheet" />
	<script type="text/javascript" charset="utf-8">
		loader = SP.core
		.install("${cdn:js(pageContext.request)}/styles/shangpin/js/jquery.min.js${ver}")
		.using("${cdn:js(pageContext.request)}/styles/shangpin/js/comm.js${ver}")
		.using("${cdn:css(pageContext.request)}/styles/shangpin/js/payment.js${ver}")
		.excute();
	</script>
</rapid:override>

<rapid:override name="page_title">
	提交成功
</rapid:override>

<rapid:override name="content">
	<div class="alContent">
		<div class="paymet_block">
	        <div class="paymet_success">
	        	<h3>订单已提交</h3>
	            <p class="tips">30分钟后订单将被取消，请您尽快支付</p>
	        </div>
	        <fieldset>
	        	<c:if test="${giftcardbalance!='0.00'&&isusegiftcard=='1'}">
	        		<p class="giftCard">
		                <a id="giftCardpay" href="#" class="cur"> 礼品卡余额 &yen;<b id="giftcardbalance">${fn:substring(giftcardbalance,0,fn:indexOf(giftcardbalance,'.'))}</b><br />
<%-- 		                    <i>使用礼品卡为本次支付 &yen;<b id="paycard">${paycard}</b></i> --%>
		                 </a>
		                 <input type="password" id="giftPsd" name="password" placeholder="请输入密码（6-20位数字+字母结合）" required class="giftCard" /><br />
		                 <c:if test="${phoneNum != ''&& phoneNum != null }">
		                 	<a href="${ctx}/order/card/password?mobile=${phoneNum}" class="r_link">忘记密码</a>
		                 </c:if>
		                 <c:if test="${phoneNum == '' || phoneNum == null }">
		                 	<a href="#" class="r_link">你的礼品卡未绑定手机号，请联系客服4006-900-900找回密码</a>
		                 </c:if>
		            </p>
		            <p class="price">您需要支付 &yen;<b id="realpay"></b></p>
	        	</c:if>
	        	<c:choose>
		        	<c:when test="${checkWX }">
			        	<c:choose>
				        	<c:when test="${cookie['ch'].value eq '102'}">
				        		<p class="payment-method">
				                	<span class="pufaPay" id="pufaPay">
				                    	<a href="javascript:;" class="cur">
				                            <em>浦发银行</em>
				                        </a>
				                    </span>
				                </p>
				        	</c:when>
				        	<c:otherwise>
				        		<p class="payment-method">
				                	<span class="weixinPay" id="weixinPay">
				                    	<a href="javascript:;" class="cur">
				                            <em>微信支付</em>
				                        </a>
				                    </span>
				                </p>
				        	</c:otherwise>
				        </c:choose>
		        	</c:when>
		        	<c:otherwise>
		        	
		        	 <c:choose>
		            	<c:when test="${paytypeid == '20' && paytypechildid == '37' }">
		            		<p class="payment-method">
				                <span class="alipay">
				                    <a href="javascript:;" class="cur">
				                        <em>支付宝支付</em><br />储蓄卡支付需开通网银
				                    </a>
				                </span>
				            </p>
				              <p class="payment-method">
			                	<span class="weixinPay" id="weixinWap">
			                    	<a href="javascript:;">
			                            <em>微信支付</em>
			                        </a>
			                    </span>
	                		</p>
				            <p class="payment-method">
				                <span class="unionPay">
				                    <a href="javascript:;">
				                        <em>银联支付</em><br />储蓄卡支付需开通网银
				                    </a>
				                </span>
				            </p>
		            	</c:when>
		            	<c:when test="${paytypeid == '19' && paytypechildid == '49' }">
		            		<p class="payment-method">
				                <span class="alipay">
				                    <a href="javascript:;">
				                        <em>支付宝支付</em><br />储蓄卡支付需开通网银
				                    </a>
				                </span>
				            </p>
		            		<p class="payment-method">
			                	<span class="weixinPay" id="weixinWap">
			                    	<a href="javascript:;">
			                            <em>微信支付</em>
			                        </a>
			                    </span>
	                		</p>
				            <p class="payment-method">
				                <span class="unionPay">
				                    <a href="javascript:;" class="cur">
				                        <em>银联支付</em><br />储蓄卡支付需开通网银
				                    </a>
				                </span>
				            </p>
		            	</c:when>
		            	<c:when test="${paytypeid == '2' && paytypechildid == '41' }">
		            		<p class="payment-method">
			                	<span class="weixinPay" id="weixinWap">
			                    	<a href="javascript:;">
			                            <em>微信支付</em>
			                        </a>
			                    </span>
	                		</p>
		            		<p class="payment-method">
				                <span class="alipay">
				                    <a href="javascript:;">
				                        <em>支付宝支付</em><br />储蓄卡支付需开通网银
				                    </a>
				                </span>
				            </p>
				            <p class="payment-method">
				                <span class="unionPay">
				                    <a href="javascript:;">
				                        <em>银联支付</em><br />储蓄卡支付需开通网银
				                    </a>
				                </span>
				            </p>
				         
		            	</c:when>
		            	<c:otherwise>
		            		<p class="payment-method">
			                	<span class="weixinPay" id="weixinWap">
			                    	<a href="javascript:;" class="cur">
			                            <em>微信支付</em>
			                        </a>
			                    </span>
	                		</p>
		            		 <p class="payment-method">
				                <span class="alipay">
				                    <a href="javascript:;">
				                        <em>支付宝支付</em><br />储蓄卡支付需开通网银
				                    </a>
				                </span>
				            </p>
				            <p class="payment-method">
				                <span class="unionPay">
				                    <a href="javascript:;">
				                        <em>银联支付</em><br />储蓄卡支付需开通网银
				                    </a>
				                </span>
				            </p>
				        
		            	</c:otherwise>
		            </c:choose>
		        	</c:otherwise>
	        	</c:choose>
	           <c:if test="${cod}">
            	 <p class="payment-method">
		              <span class="cashPay">
		                    <a href="javascript:;" >
		                        <em>货到付款现金支付</em>
		                    </a>
		                </span>
		            </p>
		            <p class="payment-method">
		              <span class="postPay">
		                    <a href="javascript:;">
		                        <em>货到付款POS机刷卡</em>
		                    </a>
		                </span>
		            </p>
	            </c:if>
	            <div class="payment_submit">
					<input type="hidden" id="cod" name="cod" value="${cod}"/>
	            	<input type="hidden" id="payTypeId" name="payTypeId" value="${paytypeid}"/>
	            	<input type="hidden" id="payTypeChildId" name="payTypeChildId" value="${paytypechildid}"/>
	            	<input type="hidden" id="orderId" name="orderId" value="${orderId}"/>
	            	<input type="hidden" id="totalamount" name="totalamount" value="${totalamount}"/>
	            	<input type="hidden" id="payamount" name="payamount" value="${payamount}"/>
	            	<input type="hidden" id="onlineamount" name="onlineamount" value="${onlineamount}"/>
	            	<input type="hidden" id="paycard" name="paycard" value="${paycard}"/>
	            	<input type="hidden" id="date" name="date" value="${date}"/>
	            	<input type="hidden" id="phoneNum" name="phoneNum" value="${phoneNum}"/>
	                <a id="pay" href="javascript:giftcardpay();" class="payment_btn">立即支付</a>
	            </div>
	        </fieldset>
	    </div>
	</div>
</rapid:override>
<rapid:override name="footer">

</rapid:override>
<%@ include file="/WEB-INF/pages/common/common.jsp" %> 