<%@ page language="java" contentType="text/html; charset=UTF-8" trimDirectiveWhitespaces="true"
    pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/pages/common/include.inc.jsp"%>

<rapid:override name="custum">
	<link href="${cdn:css(pageContext.request)}/styles/shangpin/css/base.css${ver}" rel="stylesheet" />
	<link href="${cdn:css(pageContext.request)}/styles/shangpin/css/page/FashionRun/topay.css${ver}" rel="stylesheet" />
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
</rapid:override>
<%-- 浏览器标题 --%>
<rapid:override name="title">
	填写报名表
</rapid:override>

<%-- 页标题 --%>
 <rapid:override name="page_title">
	填写报名表
</rapid:override> 
 <rapid:override name="appLayer">
</rapid:override> 
<rapid:override name="downloadAppShowHead">
		
</rapid:override>
<rapid:override name="content">
	   <div class="receive_box">    		   
        <dl class="user_info user_phone clr">
        	<dt>您的报名手机号：</dt>
            <dd><em>${fashionOrder.phone }</em></dd>

            <dd style=" width:100%;"><p>该手机号用于领取报名礼包，请确保手机号正确［<a href="${ctx }/fashionrun/modify?orderId=${fashionOrder.orderId }">修改</a>］</p></dd>
        </dl>
        <dl class="user_info user_name clr">
        	<dt>姓&#12288;名：</dt>
            <dd>${fashionOrder.name }</dd>
        </dl>
        <dl class="user_info user_cost clr">
        	<dt>报名费：</dt>
            <dd><i>${fashionOrder.payAmount }</i>含以下装备</dd>
			<dd style=" margin-left:52px;color:#555555;">Fashion run装备包、lulemon运动bra、白色、T恤、头带、贴纸、防水手机袋、号码牌</dd>
        </dl>
        <h3 class="zhifu_way">请选择支付方式：</h3>
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
     <input type="hidden" id="phone" name="phone" value="${fashionOrder.phone }"/>
     <input type="hidden" id="orderId" name="orderId" value="${fashionOrder.orderId }"/>
      <input type="hidden" id="packId" name="packId" value="${fashionOrder.packId }"/>
</rapid:override>
<rapid:override name="footer">
  
 </rapid:override>
<%@ include file="/WEB-INF/pages/common/another_common_mall_banner.jsp" %> 