<%@ page language="java" contentType="text/html; charset=UTF-8" trimDirectiveWhitespaces="true"
    pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/pages/common/include.inc.jsp"%>

<rapid:override name="custum">
	<link href="${cdn:css(pageContext.request) }/styles/shangpin/css/page/payment.css${ver}" rel="stylesheet" />
	<script type="text/javascript" charset="utf-8">
		var ver = Math.random();
			loader = SP.core
				.install("${cdn:js(pageContext.request) }/styles/shangpin/js/jquery.min.js?" + ver)
				.using("${cdn:js(pageContext.request) }/styles/shangpin/js/comm.js?" + ver)
				.using("${cdn:js(pageContext.request) }/styles/shangpin/js/payment.js?" + ver)
				.excute();
	</script>
		
</rapid:override>

<rapid:override name="header">
	<c:if test="${!checkWX&&!checkAPP}">
	    <!--头部 start-->
		<div class="topFix">
		    <section>
		        <div class="topBack" >
<!-- 		        <a href="javascript:history.go(-1);" class="backBtn">&nbsp;</a> -->
		        	<span>
		        		支付失败
		        	</span>
		        	
			        <ul class="alUser_icon clr">
			            <li><a href="<c:url value='/index'/>"><img src="${cdn:pic(pageContext.request)}/styles/shangpin/images/home_icon.png" width="25" height="25" alt="首页"></a></li>
			        </ul>
		        </div>
		    </section>
		</div>
		<!--头部 end-->
	</c:if>
</rapid:override>

<rapid:override name="content">
	
		<div class="paymet_block">
	        <div class="paymet_fail">
	        	<h3>订单支付失败</h3>
	            <p>30分钟订单将被取消，请您尽快支付</p>
	        </div>
	        <fieldset>
	            <legend>支付方式</legend>
	            
	            
	            <c:choose>
	            	<c:when test="${isPayIn=='1' }">
	            		<c:choose>
				        	<c:when test="${checkWX }">
				        	  <c:choose>
						        	<c:when test="${cookie['ch'].value eq '102'}">
						        		<p class="payment-method">
						                	<span class="pufaPay" id="weixinPay">
						                    	<a href="javascript:;" class="cur">
						                            <em>浦发银行</em>
						                        </a>
						                    </span>
						                </p>
						        	</c:when>
						        	<c:otherwise>
						        		<p class="payment-method">
				                			<span class="weixinPayPub">
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
				        			<c:when test="${payType == '5'}">
				        				<p class="payment-method">
						                	<span class="weixinPay" id="weixinWap">
						                    	<a href="javascript:;" class="cur">
						                            <em>微信支付</em>
						                        </a>
						                    </span>
					               		 </p>
								         <p class="payment-method">
							                <span class="alipayIn">
							                    <a href="javascript:;">
							                        <em>支付宝支付</em><br />
							                    	    储蓄卡支付需开通网银
							                    </a>
							                </span>
							            </p>
							             <p class="payment-method">
							                <span class="unionPay">
							                    <a href="javascript:;" >
							                        <em>银联支付</em><br />
							                        	储蓄卡支付需开通网银
							                    </a>
							                </span>
							            </p>
				        			</c:when>
				        			<c:when test="${payType == '0'}">
				        				<p class="payment-method">
						                	<span class="weixinPay" id="weixinWap">
						                    	<a href="javascript:;">
						                            <em>微信支付</em>
						                        </a>
						                    </span>
					               		 </p>
								         <p class="payment-method">
							                <span class="alipayIn">
							                    <a href="javascript:;" class="cur">
							                        <em>支付宝支付</em><br />
							                    	    储蓄卡支付需开通网银
							                    </a>
							                </span>
							            </p>
							             <p class="payment-method">
							                <span class="unionPay">
							                    <a href="javascript:;" >
							                        <em>银联支付</em><br />
							                        储蓄卡支付需开通网银
							                    </a>
							                </span>
							            </p>
				        			</c:when>
				        			<c:when test="${payType == '1'}">
				        				<p class="payment-method">
						                	<span class="weixinPay" id="weixinWap">
						                    	<a href="javascript:;">
						                            <em>微信支付</em>
						                        </a>
						                    </span>
					               		 </p>
								         <p class="payment-method">
							                <span class="alipayIn">
							                    <a href="javascript:;" >
							                        <em>支付宝支付</em><br />
							                    	    储蓄卡支付需开通网银
							                    </a>
							                </span>
							            </p>
							             <p class="payment-method">
							                <span class="unionPay">
							                    <a href="javascript:;" class="cur">
							                        <em>银联支付</em><br />
							                        储蓄卡支付需开通网银
							                    </a>
							                </span>
							            </p>
				        			</c:when>
				        			<c:otherwise>
				        				
				        			</c:otherwise>
				        		</c:choose>
				        	</c:otherwise>
				        </c:choose>
	            	</c:when>
	            	<c:otherwise>
	            			<c:choose>
				        	<c:when test="${checkWX }">
				        	  <c:choose>
						        	<c:when test="${cookie['ch'].value eq '102'}">
						        		<p class="payment-method">
						                	<span class="pufaPay" id="weixinPay">
						                    	<a href="javascript:;" class="cur">
						                            <em>浦发银行</em>
						                        </a>
						                    </span>
						                </p>
						        	</c:when>
						        	<c:otherwise>
						        		<p class="payment-method">
				                			<span class="weixinPayOut">
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
				        			<c:when test="${payType == '5'}">
				        				<p class="payment-method">
						                	<span class="weixinPayOut" id="weixinWap">
						                    	<a href="javascript:;" class="cur">
						                            <em>微信支付</em>
						                        </a>
						                    </span>
					               		 </p>
								         <p class="payment-method">
							                <span class="alipay">
							                    <a href="javascript:;">
							                        <em>支付宝支付</em><br />
							                    	    储蓄卡支付需开通网银
							                    </a>
							                </span>
							            </p>
				        			</c:when>
				        			<c:when test="${payType == '0'}">
				        				<p class="payment-method">
						                	<span class="weixinPayOut" id="weixinWap">
						                    	<a href="javascript:;">
						                            <em>微信支付</em>
						                        </a>
						                    </span>
					               		 </p>
								         <p class="payment-method">
							                <span class="alipay">
							                    <a href="javascript:;" class="cur">
							                        <em>支付宝支付</em><br />
							                    	    储蓄卡支付需开通网银
							                    </a>
							                </span>
							            </p>
				        			</c:when>
				        			<c:otherwise>
				        				
				        			</c:otherwise>
				        		</c:choose>
				        	</c:otherwise>
				        </c:choose>
	            	</c:otherwise>
	            </c:choose>
	            
	            
	            <c:if test="${orderDetail.iscod}">
	            	 <p class="payment-method hd_zhifu">
                      <span id="zhifu" class="cashPay">
                          <a href="javascript:;">货到付款现金支付</a>
                      </span>
                     </p>
                    <p class="payment-method hd_zhifu">
                      <span id="zhifu" class="postPay">
                          <a href="javascript:;">货到付款POS机刷卡</a>
                      </span>
                    </p>
	            </c:if>
	            
	         
	            
	            <div class="payment_submit">
	                <a href="javascript:continuepay();" class="payment_btn">继续支付</a>
	                <input type="hidden" id="orderId" value="${orderId}"/>
	                 <input type="hidden" id="payTypeId" value="${orderDetail.paytypeid}"/>
	                <input type="hidden" id="payTypeChildId" value="${orderDetail.paytypechildid}"/>
	            </div>
	        </fieldset>
	    </div>

</rapid:override>
<rapid:override name="footer">

</rapid:override>
<%@ include file="/WEB-INF/pages/common/common.jsp" %> 