<%@ page language="java" contentType="text/html; charset=UTF-8" trimDirectiveWhitespaces="true"
    pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/pages/common/include.inc.jsp"%>
<script type="text/javascript" charset="utf-8">
function share(){
	alert("请登录尚品网公众号或尚品网app进行赠送");
}
</script>

<rapid:override name="custum">
	<link href="${cdn:css(pageContext.request)}/styles/shangpin/css/page/giftcardPayResult.css${ver}" rel="stylesheet" />
	<link href="${cdn:css(pageContext.request)}/styles/shangpin/css/swiper.min.css${ver}" rel="stylesheet" />
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

<rapid:override name="header">
	<c:if test="${!checkWX&&!checkAPP}">
	    <!--头部 start-->
		<div class="topFix">
		    <section>
		        <div class="topBack" >
<!-- 		        <a href="javascript:history.go(-1);" class="backBtn">&nbsp;</a> -->
		        	<span>
		        		支付成功
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
<rapid:override name="downloadAppShowHead">

</rapid:override>
<rapid:override name="content">
    <div class="pay-success-box clr">
    	<i><img src="${cdn:pic(pageContext.request)}/styles/shangpin/images/giftcard/detail/pay_success_icon.png" /></i>
        <h2>您的礼品卡已经订购成功！</h2>
    	<p>礼品卡充值于您的账户中方可消费。为避免卡片丢失造成您的损失，请尽快将礼品卡充值于您的账户，或赠送给您的小伙伴</p>
        
        <div class="btn-icon clr">
        	  <a href="${ctx}/giftCard/toRecharge?orderId=${orderId}&pic=${pic}&from=0">充值于本账户</a>
        	  <c:choose>
        	  <c:when test="${checkAPP||checkWX }">
        	  <a href="${ctx}/giftCard/sendFriend?orderId=${orderId}" class="recharge-btn">赠送给小伙伴</a>  
        	  </c:when>
        	  <c:otherwise><a href="javascript:share();" class="recharge-btn">赠送给小伙伴</a>  </c:otherwise>
        	  </c:choose>
        	        
        </div>
		
		<a href="${ctx}/giftCard/queryGiftCardSecretKey?orderId=${orderId}&pic=${pic}" class="text_btn">查询秘钥 &gt;</a>
    </div>
    
    <div class="coupon_tip">
        <img src="${cdn:pic(pageContext.request)}/styles/shangpin/images/order/pay_succeed_coupons.png" class="coupon_img">
        <c:set var="totalPrice" value="${orderDetail.onlineamount*1 }" />
        <p>感谢您对尚品网的支持，特赠送您<a href="" class="f_red">￥<c:choose>
		            		<c:when test="${0<=totalPrice && totalPrice <500}">50</c:when>
			            	<c:when test="${500<=totalPrice&& totalPrice<1000}">100</c:when>
			            	<c:when test="${1000<=totalPrice && totalPrice<2000}">200</c:when>
			            	<c:when test="${2000<=totalPrice}">400</c:when>
		            	</c:choose>优惠券</a>，将在5分钟内充值到您的账户，<a href="${ctx }/coupon/list" class="f_black">查看优惠券 &gt;</a></p>
		            	
    </div> </br>
    <c:import url="/coupon/product/list?from=pay&payAmount=${orderDetail.onlineamount}"></c:import> 
</rapid:override>
<rapid:override name="footer">

</rapid:override>
<%@ include file="/WEB-INF/pages/common/common_no_content_banner.jsp" %> 
