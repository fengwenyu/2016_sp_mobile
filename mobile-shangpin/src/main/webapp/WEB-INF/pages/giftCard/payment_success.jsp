<%@ page language="java" contentType="text/html; charset=UTF-8"  pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/pages/common/include.inc.jsp"%>

<%-- 页标题 --%>
<rapid:override name="page_title">
	支付成功
</rapid:override>
<rapid:override name="custum">

<script type="text/javascript" charset="utf-8">
  var ver = Math.random();
    loader = SP.core
      .install("${cdn:js(pageContext.request)}/styles/shangpin/js/jquery.min.js?" + ver)  //jquery库文件
        .using("${cdn:js(pageContext.request)}/styles/shangpin/js/lazyload.js?" + ver)    //图片懒加载
        .excute()
        .using("${cdn:js(pageContext.request)}/styles/shangpin/js/config.sim.js?" + ver)  //图片懒加载
        .using("${cdn:js(pageContext.request)}/styles/shangpin/js/j.dialogs.js?" + ver)  //图片懒加载
        .excute()
        .using("${cdn:js(pageContext.request)}/styles/shangpin/js/downloadAppShow.js?" + ver)   //页面专用JS
      	.excute();
</script>
	<link href="${cdn:css(pageContext.request)}/styles/shangpin/css/base.css${ver}" rel="stylesheet" />
	<!-- icon -->
	<link rel="apple-touch-icon" href="/images/touch-icon-iphone.png">
	<link rel="apple-touch-icon" sizes="72x72" href="/images/touch-icon-ipad.png">
	<link rel="apple-touch-icon" sizes="114x114" href="/images/touch-icon-iphone4.png">
	<link rel="apple-touch-icon" sizes="144x144" href="/images/touch-icon-newipad.png">
	<!-- 启动自定义图片(png, 320X640) -->
	<link rel="apple-touch-startup-image" sizes="72x72" href="/images/logo/loading.png">
</rapid:override>

<style>
.container{ margin:0 auto; max-width:640px; width:100%;}
.payment_block_o{ padding: 45px 15px 20px;}
.payment_block_o{ padding:45px 0 20px}
.payment_block_o i{ display:block; width:65px; margin:0 auto;}
.payment_block_o h2{ font-size:14px; color:#2d2d2d; margin:15px 0 52px; text-align:center;}
.payment_block_o .submit_btn{ color:#fff; font-size:18px; line-height:40px; height:40px; margin:10px 10px 30px; display:block; background:#c62026; text-align:center;}

</style>

<rapid:override name="content">
<div class="container">
    <div class="payment_block_o">
    	<i><img src="${cdn:css(pageContext.request)}/images/giftCard/detail/payment_success_icon.png" /></i>
        <h2>该礼品卡已经被领取，请登录尚品网账户查看。</h2>
        <a href="${pageContext.request.contextPath}/login?back=/user/home" class="submit_btn" id="payment_s_btn">登录</a>
    </div>
</div>
</rapid:override>
<%@ include file="/WEB-INF/pages/common/another_common_mall_banner.jsp" %>
