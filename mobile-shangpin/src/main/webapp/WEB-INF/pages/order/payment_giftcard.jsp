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
		<div class="paymet_block">
	        <div class="paymet_success">
	        	<h3>订单已提交${phoneNum}</h3>
	            <p class="tips">1小时订单将被取消，请您尽快支付</p>
	        </div>
	        <fieldset>
	        	<p class="giftCard">
	                <a href="#" class="cur">
	                    礼品卡余额 &yen;${giftcardbalance}<br />
	                    <i>使用礼品卡为本次支付 &yen;<b id="paycard">${paycard}</b></i>
	                 </a>
	                 <input type="password" id="giftPsd" name="password" placeholder="请输入密码（6-20位数字+字母结合）" required class="giftCard" /><br />
	                 <c:if test="${phoneNum != ''&& phoneNum != null }">
	                 	<a href="${ctx}/order/card/password?mobile=${phoneNum}" class="r_link">忘记密码</a>
	                 </c:if>
	                 <c:if test="${phoneNum == '' || phoneNum == null }">
	                 	<a href="#" class="r_link">你的礼品卡未绑定手机号，请联系客服4006-900-900找回密码</a>
	                 </c:if>
	            </p>
	            <p class="price">您还需要支付剩余 &yen;<b id="realpay">${realpay}</b></p>
	            
	            
	             <c:choose>
		        	<c:when test="${checkWX }">
	                <c:choose>
				        	<c:when test="${cookie['ch'].value eq '102'}">
				        		<p class="zhifu">
				                	<span class="pufaPay" id="weixinPay">
				                    	<a href="javascript:;" class="cur">
				                            <em>浦发银行</em>
				                        </a>
				                    </span>
				                </p>
				        	</c:when>
				        	<c:otherwise>
				        		<p>
				                	<span class="weixinPay">
				                    	<a href="javascript:;" class="cur">
				                            <em>微信支付</em>
				                        </a>
				                    </span>
	                			</p>
				        	</c:otherwise>
				        </c:choose>
		        	</c:when>
		        	<c:otherwise> 
					        
			            <p>
			                <span class="alipay">
			                    <a href="javascript:;">
			                        <em>支付宝支付</em><br />
			                        储蓄卡支付需开通网银
			                    </a>
			                </span>
			            </p>
			            <p>
			                <span class="unionPay">
			                    <a href="javascript:;">
			                        <em>银联支付</em><br />
			                        储蓄卡支付需开通网银
			                    </a>
			                </span>
			            </p>
			            <c:if test="${cod == '1' }">
			            	<p>
				              <span class="cashPay">
				                    <a href="javascript:;">
				                        货到付款现金支付
				                    </a>
				                </span>
				            </p>
				            <p>
				              <span class="postPay">
				                    <a href="javascript:;">
				                        货到付款POS机刷卡
				                    </a>
				                </span>
				            </p>
			            </c:if>
		        	</c:otherwise>
		        </c:choose>
	            
	            
	            
	         
	            <div class="payment_submit">
	            	<input type="hidden" id="payTypeId" name="payTypeId" value="20"/>
	            	<input type="hidden" id="payTypeChildId" name="payTypeChildId" value="37"/>
	            	<input type="hidden" id="orderId" name="orderId" value="${orderId}"/>
	            	<input type="hidden" id="totalPrice" name="totalPrice" value="${amount}"/>
	                <a id="pay" href="javascript:giftcardpay();" class="payment_btn">立即支付</a>
	            </div>
	        </fieldset>
	    </div>
</rapid:override>
<rapid:override name="footer">

</rapid:override>
<%@ include file="/WEB-INF/pages/common/common.jsp" %> 