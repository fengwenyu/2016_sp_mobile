<%@ page language="java" contentType="text/html; charset=UTF-8" trimDirectiveWhitespaces="true"
    pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/pages/common/include.inc.jsp"%>

<rapid:override name="custum">
	<link href="${cdn:css(pageContext.request) }/styles/shangpin/css/page/payment.css${ver}" rel="stylesheet" />
	
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
<rapid:override name="content">

		<div class="paymet_block">
			<div class="paymet_success">
				<h3>订单支付成功</h3>
			    <p>
			    	订单编号：<span>${orderItem.mainorderno}</span><br />
			        下单时间：<span>${orderItem.date}</span><br />   
			        支付方式：<span>${orderItem.paytypename }</span><br />
			        支付金额：<span>&yen;${orderItem.onlineamount}</span>
			    </p>
			</div>
			<div class="payment_submit">
			    <dl>
			    	<dd><a href="<c:url value='/index'/>" class="contiu_btn">继续购物</a></dd>
			        <dt>&nbsp;</dt>
			        <dd><a href="${ctx}/logistice/list?orderId=${orderItem.mainorderno}" class="payment_btn">订单跟踪</a></dd>
			    </dl>
			</div>
		</div>

</rapid:override>
<rapid:override name="footer">

</rapid:override>
<%@ include file="/WEB-INF/pages/common/common.jsp" %> 