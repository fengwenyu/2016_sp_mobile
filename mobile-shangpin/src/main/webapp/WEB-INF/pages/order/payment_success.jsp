<%@ page language="java" contentType="text/html; charset=UTF-8" trimDirectiveWhitespaces="true"
    pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/pages/common/include.inc.jsp"%>

<rapid:override name="custum">
	<link href="${cdn:css(pageContext.request) }/styles/shangpin/css/page/payResult.css${ver}" rel="stylesheet" />
	<link href="${cdn:css(pageContext.request) }/styles/shangpin/css/page/swiper.min.css${ver}" rel="stylesheet" />
	<link href="${cdn:css(pageContext.request)}/styles/shangpin/css/page/201502brand/brand.css${ver}" rel="stylesheet" />

		<script type="text/javascript" charset="utf-8">
		loader = SP.core
		.install("${cdn:js(pageContext.request)}/styles/shangpin/js/jquery.min.js${ver}")
		.using("${cdn:js(pageContext.request)}/styles/shangpin/js/lazyload.js${ver}")
		.excute()
		.using("${cdn:js(pageContext.request)}/styles/shangpin/js/config.sim.js${ver}")
		.excute()
		.using("${cdn:js(pageContext.request)}/styles/shangpin/js/comm.js${ver}")
		.using("${cdn:js(pageContext.request)}/styles/shangpin/js/swiper.min.js${ver}")
		.excute(function(){
			_smq.push(['custom', '支付成功', '','${orderId}', '${totalPrice}']);
				$(function(){
					var swiper = new Swiper('#swiper-container1', {
						slidesPerView: 'auto',
					});
				})
		});
	</script>
</rapid:override>
<rapid:override name="title">
    	支付成功
</rapid:override>
<rapid:override name="page_title">
		支付成功
</rapid:override>
<rapid:override name="content">
		<div class="paymet_block">
		        <div class="paymet_success">
		        	<h3>订单支付成功</h3>
		            <p class="notice">
						订单编号：<span>${orderId}</span><br>
						下单时间：<span>${orderDetail.date}</span><br>   
						支付金额：<span>¥${totalPrice}</span><br>
						支付方式：<span>
						<c:choose>
				        	<c:when test="${payType == '0'}">
				        		<span>支付宝支付</span><br />
				        	</c:when>
				        	<c:when test="${payType == '1'}">
				        		<span>银联支付</span><br />
				        	</c:when>
				        	<c:when test="${payType == '2'}">
				        		<span>礼品卡支付</span><br />
				        	</c:when>
				        	<c:when test="${payType == '3'}">
				        		 <span>优惠券、折扣码支付</span><br />
				        	</c:when>
				        	<c:when test="${payType == '4'}">
				        		 <span>货到付款</span><br />
				        	</c:when>
				        	<c:when test="${payType == '5'}">
				        		<span>微信支付</span><br />
				        	</c:when>
				        	<c:when test="${payType  == '6'}">
				        		 <span>浦发银行支付</span><br />
				        	</c:when>
				        	<c:otherwise>
				        		 <span>其他支付</span><br />
				        	</c:otherwise>
			        	</c:choose>  </span>
		             </p>
		             <p class="notice" style="color:red">
		             如果您参与了尚品网520撞衫日买一赠一活动，即刻<a href="http://m.shangpin.com/countDownload?favor=4" >下载</a>尚品APP,到“我的-全部订单”送出同款撞衫给好友吧！
		             </p>
					<div class="payment_submit clr">
					    <!--<dl>
					       <dd></dd>
					       <dt>&nbsp;</dt>
					       <dd><a href="javascript:;" class="payment_btn">订单跟踪</a></dd>
					   </dl> -->
					    <a href="${ctx }/logistice/list?orderId=${orderId}&postArea=${postArea}" class="payment_btn">查看订单</a>
					    <a href="${ctx }/index" class="contiu_btn">继续购物</a>
					</div>
		            <p class="notice">
		            	<i class="prompt_tip"><img src="${cdn:pic(pageContext.request)}/styles/shangpin/images/order/prompt_img.png" width="100%"></i>
		            	如订单中有多件商品，有可能拆分为多个子订单进行配送，由此产生的运费将由尚品网承担。
		            	由于奢侈品的特殊性质，极个别情况下，会出现缺码或缺货情况，一旦出现系统将为您自动退款，款项会原渠道返回。
					</p>
		            <p class="notice">温馨提示：尚品网不会以订单无效等原因主动要求您提供银行卡信息操作退款，谨防诈骗！</p>
		            <br>
		            <div class="coupon_tip">
		            	<img src="${cdn:pic(pageContext.request)}/styles/shangpin/images/order/pay_succeed_coupons.png" class="coupon_img">
		            	<c:set var="couponAmount" value="0" />
		            	<c:set var="totalPrice" value="${orderDetail.onlineamount*1 }" />
		            	<c:choose>
		            		<c:when test="${0.00*1<=totalPrice && totalPrice <500.00*1}">
		            		<c:set var="couponAmount" value="50" /></c:when>
			            	<c:when test="${500.00*1<=totalPrice&& totalPrice<1000.00*1}">
			            		<c:set var="couponAmount" value="100" />
			            	<</c:when>
			            	<c:when test="${1000.00<=totalPrice && totalPrice<2000.00*1}">
			            		
			            		<c:set var="couponAmount" value="200" />
			            	</c:when>
			            	<c:when test="${2000.00*1<=totalPrice}">
			            	
			            		<c:set var="couponAmount" value="400" />
			            	</c:when>
		            	</c:choose>
		                <p>感谢您对尚品网的支持，特赠送您<a href="" class="f_red">¥${couponAmount }优惠券</a>，将在5分钟内充值到您的账户，<a href="${ctx }/coupon/list" class="f_black">查看优惠券 &gt;</a></p>
		            </div>
		            </br>
		         <c:import url="/coupon/product/list?from=pay&payAmount=${orderDetail.onlineamount}"></c:import> 
	            </div>
		        </div>
	</div>
	<c:if test="${hasFreebie==0 }">
		<script type="text/javascript" charset="utf-8">
			loader = SP.core.using("${cdn:js(pageContext.request)}/styles/shangpin/js/downloadAppShow.js${ver}").excute();
		</script>

		<div class="overlay active" id="overlay">
			<section class="modal modal-test modal-in" style="display: block;" >
				<div class="modal-hd">下单成功</div>
				<div class="modal-bd">
					<p>
					您的订单可参与尚品520买一赠一活动哦><br/>
					下载尚品APP，点击<strong>“我的-全部订单”</strong><br/>
						分享同款撞衫给好友
					</p>
				</div>
				<div class="modal-ft">
					<span class="btn-modal download_bottom_app"  style="width: 48%;float: left;">点击下载APP</span>
					<span class="btn-modal " onclick="javascript:$('#overlay').hide();" style="width: 48%;float:right;">取消</span>
				</div>
			</section>
		</div>
	</c:if>
</rapid:override>
<rapid:override name="footer">

</rapid:override>
<%@ include file="/WEB-INF/pages/common/common.jsp" %> 