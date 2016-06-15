<%@ page language="java" contentType="text/html; charset=UTF-8" trimDirectiveWhitespaces="true"
    pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/pages/common/include.inc.jsp"%>

<rapid:override name="custum">
	<link href="${cdn:css(pageContext.request)}/styles/shangpin/css/page/login.css" rel="stylesheet" />
	<link href="${cdn:css(pageContext.request)}/styles/shangpin/css/page/payment.css" rel="stylesheet" />
	<script src="${cdn:css(pageContext.request)}/styles/shangpin/js/install_unionpay.js" type="text/javascript" charset="utf-8"></script>
		<script type="text/javascript" charset="utf-8">
			loader = SP.core
			.install("${cdn:js(pageContext.request)}/styles/shangpin/js/jquery.min.js${ver}")
			.using("${cdn:js(pageContext.request)}/styles/shangpin/js/lazyload.js${ver}")
			.using("${cdn:js(pageContext.request)}/styles/shangpin/js/j.dialogs.js${ver}")
			.using("${cdn:js(pageContext.request)}/styles/shangpin/js/style.js${ver}")
			.excute()
			.using("${cdn:js(pageContext.request)}/styles/shangpin/js/config.sim.js${ver}")
			
			.excute(function(){
				var accept="${accept}";
				setTimeout(function(){
					unionPay(accept);		
				},300);
				
			});
			
	</script>
</rapid:override>

<rapid:override name="content">
  
<div class="alContent">
        <fieldset>
            <p class="c-form-search" id="redirectYL">
	 		 <span>正在跳转到银联...</span>
            </p>
            <p class="c-form-search" id="notpromptandriod" style="display: none;">
	 		 <span>如果未安装银联安全支付控件<a href="http://mobile.unionpay.com/getclient?platform=android&type=securepayplugin" style="text-decoration: underline;"> 请 点 这 里</a>。</span>
            </p>
            <p class="c-form-search"  id="notpromptios" style="display: none;">
	 		<span>如果未安装银联安全支付控件<a href="http://mobile.unionpay.com/getclient?platform=ios&type=securepayplugin" style="text-decoration: underline;"> 请 点 这 里 </a>。</span>
            </p>
            <p class="c-form-search"  id="notphone" style="display: none;">
	 		 <span>不支持非手机用户访问</span>
            </p>
        </fieldset>
        <div class="paymet_block">
        <div class="payment_submit" id="goOn" style="display: none;margin: 20px 25px 25px;">
            <dl>
            <c:choose>
            	<c:when  test="${quickSubmitFlag=='0' }">
            		<dd><a href='${ctx}/pay/unpay/UNWAP?orderId=${orderId}&quickSubmitFlag=0' class="contiu_btn">继续银联支付</a></dd>
            	</c:when>
            	<c:otherwise>
            	<dd><a href='${ctx}/pay/unpay/UNWAP?orderId=${orderId}' class="contiu_btn">继续银联支付</a></dd>
            	</c:otherwise>
            </c:choose>
         
            	
<!--                 <dt>&nbsp;</dt> -->
<%--                 <dd><a href="${ctx}/pay/alipay?orderId=${orderId}" class="payment_btn">支付宝支付</a></dd> --%>
            </dl>
        </div>
        </div>
</div>
</rapid:override>
<%-- 页面的尾部 --%>
<rapid:override name="statistics">

</rapid:override>
<%-- 页面的尾部 --%>      
<rapid:override name="down_page">

</rapid:override>
<%@ include file="/WEB-INF/pages/common/base.jsp" %> 