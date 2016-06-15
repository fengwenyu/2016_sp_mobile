<%@ page language="java" contentType="text/html; charset=UTF-8"  pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/pages/common/include.inc.jsp"%>
<rapid:override name="custum">
<style>
.main_box img{ width:100%}
</style>
<script type="text/javascript" charset="utf-8">
  var ver = Math.random();
    loader = SP.core
      .install("${cdn:js(pageContext.request)}/styles/shangpin/js/jquery.min.js?" + ver)  //jquery库文件
        .using("${cdn:js(pageContext.request)}/styles/shangpin/js/lazyload.js?" + ver)    //图片懒加载
        .excute()
        .using("${cdn:js(pageContext.request)}/styles/shangpin/js/config.sim.js?" + ver)  //图片懒加载
        .excute()
        .using("${cdn:js(pageContext.request)}/styles/shangpin/js/giftcard/giftCard_send.js?41" + ver)    //页面专用JS
        .using("${cdn:js(pageContext.request)}/styles/shangpin/js/downloadAppShow.js?" + ver)   //页面专用JS
        .excute()
      
</script>
<link href="${cdn:css(pageContext.request)}/styles/shangpin/css/base.css${ver}" rel="stylesheet" />
<link href="${cdn:css(pageContext.request)}/styles/shangpin/css/public.css${ver}" rel="stylesheet" />
<link href="${cdn:css(pageContext.request)}/styles/shangpin/css/page/giftcard/giftCard_send2.css?34${ver}" rel="stylesheet" /><!--页面专用CSS-->
</rapid:override>
<%-- 页标题 --%>
<rapid:override name="page_title">
	验证口令
</rapid:override>


<rapid:override name="downloadAppShowHead">

</rapid:override>
<!-- icon -->
<link rel="apple-touch-icon" href="/images/touch-icon-iphone.png">
<link rel="apple-touch-icon" sizes="72x72" href="/images/touch-icon-ipad.png">
<link rel="apple-touch-icon" sizes="114x114" href="/images/touch-icon-iphone4.png">
<link rel="apple-touch-icon" sizes="144x144" href="/images/touch-icon-newipad.png">
<!-- 启动自定义图片(png, 320X640) -->
<link rel="apple-touch-startup-image" sizes="72x72" href="/images/logo/loading.png">

<rapid:override name="content">
	<div class="container">
	    
	    <div class="giftCard_verify">
	    	<p class="text_box f_red">${giftCard.buyerName}给${sendPhoneNum }送了一张价值为￥${giftCard.faceValue }的礼品卡，请输入验证码确认您的身份？</p>
	        <div class="yzm-box">
	            <div class="login_list"> 
	            <input type="hidden" id="giftCard" value="${giftCard }">                 
	            	<input type="tel" id="J_yzm" maxlength="6" value="" placeholder="请输入短信验证码" required>                             
	            </div>
	            <em id ="get_code" class="get_code" data-waiting="秒" >发送验证码</em>
	        </div>
	        <p class="text_tip">您输入的验证码有误</p>
	        <p class="text_box" id="code_tip">短信验证码已发至您的手机${sendPhoneNum }</p>
	        <a class="chose_btn" id="verify_btn">确定</a>
	    </div>
	    
	</div>
</rapid:override>
<%@ include file="/WEB-INF/pages/common/another_common_mall_banner.jsp" %>
