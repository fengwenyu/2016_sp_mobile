<%@ page language="java" contentType="text/html; charset=UTF-8"  pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/pages/common/include.inc.jsp"%>

<rapid:override name="custum">
<script src="${cdn:js(pageContext.request)}/styles/shangpin/js/core.js" type="text/javascript" charset="utf-8"></script>
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

<!-- icon -->
<link rel="apple-touch-icon" href="/images/touch-icon-iphone.png">
<link rel="apple-touch-icon" sizes="72x72" href="/images/touch-icon-ipad.png">
<link rel="apple-touch-icon" sizes="114x114" href="/images/touch-icon-iphone4.png">
<link rel="apple-touch-icon" sizes="144x144" href="/images/touch-icon-newipad.png">
<!-- 启动自定义图片(png, 320X640) -->
<link rel="apple-touch-startup-image" sizes="72x72" href="/images/logo/loading.png">


<%-- 页标题 --%>
<rapid:override name="page_title">
	礼品卡登录
</rapid:override>

<rapid:override name="downloadAppShowHead">

</rapid:override>


<rapid:override name="content">
<div class="container">
    <div class="results-box giftCard-login-box">
    	<i><img src="${pageContext.request.contextPath}/images/giftCard/detail/giftCard_login.png" /></i>
        <h2>请登录或注册尚品网！</h2>
    	<p class="m_l_r">礼品卡充值是将礼品卡的资金充值到尚品网会员的礼品卡账户中，请登录或注册一个尚品网会员。</p>
        <div class="btn-icon clr">
        	<a id="normal_login">账号登录</a>
            <a  class="bg_btn" id="quick_login">一键注册</a>            
        </div>  
        <span class="title_tip">注册登录说明：</span> 
        <p>1.尚品网的礼品卡必须充值于尚品网会员的账户中方可使用；<br />
        2.如您要将礼品卡充值于您的手机号码账户中，请使用“一键注册”，如该手机号码不是尚品网的会员，系统会自动为您创建一个会员账户；<br />
        3.如您有尚品网的其他账户，请使用“账号登录”。
        </p>    
    </div>
    
    
    <!--提示注册弹出层-->
    <div class="select-overlay">
    	<div class="show_window_con">
        	<h3 class="title">注册提醒</h3>
            <div class="text_con">手机号码${giftCard.sendPhoneNum }不是尚品网会员，使用该手机号码登录，系统会直接注册为会员。</div>
            <p class="close_window" id="oneKeySure">确定</p>
        </div>
    </div>
</div>
 </rapid:override>
<%@ include file="/WEB-INF/pages/common/another_common_mall_banner.jsp" %>
