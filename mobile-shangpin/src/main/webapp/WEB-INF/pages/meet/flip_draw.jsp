<%@ page language="java" contentType="text/html; charset=UTF-8"
	trimDirectiveWhitespaces="true" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/pages/common/include.inc.jsp"%>

<rapid:override name="custum">
<script src="${cdn:js(pageContext.request)}/styles/shangpin/js/jquery.min.js${ver}"  type="text/javascript" charset="utf-8"></script>
<script src="${cdn:js(pageContext.request)}/styles/shangpin/js/meet/316/xmas.dialogs.js${ver}"  type="text/javascript" charset="utf-8"></script>
<script src="${cdn:js(pageContext.request)}/styles/shangpin/js/meet/316/index.js${ver}"  type="text/javascript" charset="utf-8"></script>
<link href="${cdn:css(pageContext.request)}/styles/shangpin/css/page/meet/264/flip.css${ver}" rel="stylesheet" />
</rapid:override>

<rapid:override name="page_title">
    	快来抢现金
</rapid:override>

<rapid:override name="content">
    <!--内容区域 start-->
    <div class="content-box">
        <input type="hidden" id="isLogin" value="${isLogin}" />
        <p class="rule-pop-btn"><img src="${cdn:pic(pageContext.request)}/styles/shangpin/images/flipDraw/purple_img01.jpg" /></p>
    	<h2><a href="${ctx}/flipDrawRule"><img src="${cdn:pic(pageContext.request)}/styles/shangpin/images/flipDraw/purple_img02.jpg" /></a></h2>
        <!--抽奖 start-->
        <div class="flip-box">
            <ul class="flip-list clr">
                <li><img src="${cdn:pic(pageContext.request)}/styles/shangpin/images/flipDraw/flip01.jpg" alt="${cdn:pic(pageContext.request)}/styles/shangpin/images/flipDraw/flip_bg1.jpg" /></li>
                <li><img src="${cdn:pic(pageContext.request)}/styles/shangpin/images/flipDraw/flip02.jpg" alt="${cdn:pic(pageContext.request)}/styles/shangpin/images/flipDraw/flip_bg2.jpg" /></li>
                <li><img src="${cdn:pic(pageContext.request)}/styles/shangpin/images/flipDraw/flip03.jpg" alt="${cdn:pic(pageContext.request)}/styles/shangpin/images/flipDraw/flip_bg3.jpg" /></li>
                <li><img src="${cdn:pic(pageContext.request)}/styles/shangpin/images/flipDraw/flip04.jpg" alt="${cdn:pic(pageContext.request)}/styles/shangpin/images/flipDraw/flip_bg4.jpg" /></li>
                <li><img src="${cdn:pic(pageContext.request)}/styles/shangpin/images/flipDraw/flip05.jpg" alt="${cdn:pic(pageContext.request)}/styles/shangpin/images/flipDraw/flip_bg5.jpg" /></li>
                <li><img src="${cdn:pic(pageContext.request)}/styles/shangpin/images/flipDraw/flip06.jpg" alt="${cdn:pic(pageContext.request)}/styles/shangpin/images/flipDraw/flip_bg6.jpg" /></li>
            </ul>
        </div>
        <!--抽奖 end-->

    </div>
    <!--内容区域 start-->
    <!-- 验证手机-->    
    <div class="login-box js-login-box"  id="login_box1">
        <section class="login-form">
            <input class="tip-input" id="J_mobileNum" type="tel" value="" placeholder="请输入手机号" maxlength="11" required />
            <div class="yzm-box">
                <div class="login_list"><input type="tel" id="J_yzm" value="" placeholder="请输入验证码" maxlength="6" required></div>
                <em id="passwordGetCode" data-waiting="秒">获取验证码</em>
            </div>
            <a class="close-btn" href="javascript:;">X</a>     
            <p class="prompt"></p>  
        	<a href="javascipt:;" class="sure-btn">立即领取</a>     
        </section>      
    </div>    
    <!-- 验证手机end-->
</rapid:override>

<rapid:override name="footer">

</rapid:override>
<%@ include file="/WEB-INF/pages/common/common_no_content_banner.jsp"%>

