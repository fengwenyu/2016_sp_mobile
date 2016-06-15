<%@ page language="java" contentType="text/html; charset=UTF-8"
	trimDirectiveWhitespaces="true" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/pages/common/include.inc.jsp"%>

<rapid:override name="custum">
<style>
.main_box img{ width:100%}
</style>
<script type="text/javascript" charset="utf-8">
	loader = SP.core
	.install("${cdn:js(pageContext.request)}/styles/shangpin/js/jquery.min.js${ver}")
	.using("${cdn:js(pageContext.request)}/styles/shangpin/js/lazyload.js${ver}")
	.using("${cdn:js(pageContext.request)}/styles/shangpin/js/jquery.easing.min.js${ver}")
	.using("${cdn:js(pageContext.request)}/styles/shangpin/js/jQueryRotate.2.2.js${ver}")
	.excute()
	.using("${cdn:js(pageContext.request)}/styles/shangpin/js/config.sim.js${ver}")
	.excute()
	.using("${cdn:js(pageContext.request)}/styles/shangpin/js/xmas.dialogs.js${ver}")
	.using("${cdn:js(pageContext.request)}/styles/shangpin/js/downloadAppShowMeet.js${ver}")
    .using("${cdn:js(pageContext.request)}/styles/shangpin/js/meet/single/goodluck.js${ver}")
    .excute(function(){
		isHome(false);
	});
	</script>
<link href="${cdn:css(pageContext.request)}/styles/shangpin/css/page/meet/single/20150909goodLuck.css${ver}" rel="stylesheet" />
</rapid:override>
<rapid:override name="header">
</rapid:override>
<rapid:override name="content">
<input id="isLogin"  type="hidden"  name="isLogin"  value="${sessionScope.isLogin}"/>
<div class="alContent">
	<header class="title-top">
        <img src="${cdn:pic(pageContext.request)}/styles/shangpin/images/20150909GoodLuck/img01.jpg">
    </header>
	<div class="turntable">
        <div class="turntable-box">
        	<img id="turntable_img" src="${cdn:pic(pageContext.request)}/styles/shangpin/images/20150909GoodLuck/turntable_img_03.png" style="-webkit-transform: rotate(115deg);">
            <div id="turntable_btn"></div>
        </div>
    </div>
	<div class="turntable-text">
        <div class="con">
        	<h3>每个用户每天有3次抽奖机会</h3>
            <ul class="con-text">                
                <li>
                  <strong>一等奖：</strong><em>1000元</em>衣橱基金（现金券）<br>
                  <strong>二等奖：</strong><em>超美单品</em>直接送（每周五统一发放）<br>
                  <strong>三等奖：</strong><em>20元现金券</em>
                </li>
                <li><strong>活动时间：</strong>2015.09.23-2015.09.27</li>
                <li>活动最终解释权归尚品网所有</li>
            </ul>
            <h3>快看大家都中了哪些萌物</h3>
        </div>
        
        <div class="sroll-text">
            <div>
            <ul class="lottery_list" id="window_roll" style="margin-top: 0px;">

            </ul>
            </div>
        </div>
    </div>
</div>
</rapid:override>

 
<rapid:override name="footer">
</rapid:override>
<%@ include file="/WEB-INF/pages/common/base_meet.jsp" %> 
