<%@ page language="java" contentType="text/html; charset=UTF-8" trimDirectiveWhitespaces="true"
    pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/pages/common/include.inc.jsp"%>

<rapid:override name="custum">
	<script type="text/javascript" charset="utf-8">
    loader = SP.core
	.install("${cdn:css(pageContext.request)}/styles/shangpin/js/zepto.1.1.3.js${ver}")  //jquery库文件
  	.using("${cdn:css(pageContext.request)}/styles/shangpin/js/lazyload.js${ver}")
	.excute()
	.using("${cdn:css(pageContext.request)}/styles/shangpin/js/config.sim.js${ver}")
	.excute()
	.using("${cdn:css(pageContext.request)}/styles/shangpin/js/comm.js${ver}")
     .using("${cdn:css(pageContext.request)}/styles/shangpin/js/fashion_run.js${ver}") 
      
</script>
<style>
body{background-color:#000; 0 0;background-size: 150px;}


.container{ min-width:320px; max-width:640px; margin:0 auto; color:#fafafa;}
.topFix{position:fixed; top:0; z-index:10;min-width:320px; max-width:640px; height:45px;}
.topFix section { position:static;}
body .alContent {line-height:20px; padding-bottom:10px; }
.receive_box{width:100%; }
.receive_box p{ padding:0 30px; font-size:12px;}
.receive_con{ text-align:center; }
.receive_con span{ font-size:22px;}
.receive_con i{ width:100px; height:105px; margin: 10% auto 10px; display:block;}
.receive_con i img{ max-width:100%;}
.pay-box{margin: 0px 0 0 20px;vertical-align: top;line-height: 37px;}
.zhifu_way{  font-weight:normal; font-size:14px;display: inline-block; line-height: 37px;vertical-align: top;}
.payment_method{ display: inline-block;}
.payment_method li{ width:80px; height:30px; border:1px solid #d8d8d8; display: inline-block;margin-left:8px; position:relative;}
.payment_method li a{ position:absolute; left:0; width:75px; height:30px; text-align:right; padding-right:5px; color:#fff; font-size:14px; line-height:30px; }
.payment_method li.alipay{ 
	background:url(../../styles/shangpin/images/FashionRun/zhifu_logo.png) no-repeat 8px 4px; 
	background-size:25px 50px;
}
.payment_method li.wechatpay{ 
	background:url(../../styles/shangpin/images/FashionRun/zhifu_logo.png) no-repeat 16px -23px; 
	background-size:25px 50px;
}
.row-box {  margin:30px 20px 0;}
.row-box em{display: inline-block;font-size: 14px; }
.row-box > span{ display: inline-block; margin-left: 10px; color: #fff; height: 35px;}
.receive_btn{ padding:10px 20px 0;}
.receive_btn a{width:100%;font-size:16px;}
.abtn{ text-align:center; height:40px; line-height:40px; display:block;background:#fb0c60; border-radius:5px; color:#fff;}
</style>

</style>
</rapid:override>
<%-- 浏览器标题 --%>
<rapid:override name="title">
	FashionRun 时尚美女跑者招募
</rapid:override>

<%-- 页标题 --%>
 <rapid:override name="page_title">
	FashionRun 时尚美女跑者招募
</rapid:override> 
 <rapid:override name="appLayer">
</rapid:override> 
<rapid:override name="downloadAppShowHead">
		
</rapid:override>
<rapid:override name="content">
	<!--内容部分-->
     <img src="${ctx }/styles/shangpin/images/FashionRun/form_banner.png" alt="">
    <div class="receive_box">    		   
       <p class="receive_con" style="padding:10px 20px 0; line-height:140px; font-size:28px; font-weight:700;">
        	<%-- <i><img src="${ctx }/styles/shangpin/images/FashionRun/pay_fail_img.png" /></i> --%>
        	<span>未支付成功，请重新支付！</span>            
        </p>
        <div class="row-box" style="margin:30px 5px 0;">
            <em>报名费用：</em>
            <span><fmt:formatNumber type="number" value="${fashionOrder.payAmount }" pattern="0" maxFractionDigits="0"/>元／人（含时尚装备及保险）</span>  
        </div>
        <div class="pay-box">
        <em class="zhifu_way">支付方式：</em>
        <ul class="payment_method">
              <c:choose>
		        <c:when test="${checkWX }">
		           <li class="wechatpay"><a href="javascript:pay(2);">微信</a></li>
		        </c:when>
		        <c:otherwise>
		        	<li class="alipay"><a href="javascript:pay(1);">支付宝</a></li>
		        </c:otherwise>
		     </c:choose>
        </ul>
        
    </div>
    </div>
    <p class="receive_btn">
    	<c:choose>
		        <c:when test="${checkWX }">
		           <a href="javascript:pay(2);" class="abtn">继续支付</a>
		        </c:when>
		        <c:otherwise>
		        	<a href="javascript:pay(1);" class="abtn">继续支付</a>
		        </c:otherwise>
		     </c:choose>
   </P>
 	<!--内容部分end-->
 	 <input type="hidden" id="orderId" name="orderId" value="${orderId }"/>
</rapid:override>
<rapid:override name="footer">
  
 </rapid:override>
<%@ include file="/WEB-INF/pages/common/common_mall_home_banner.jsp" %> 