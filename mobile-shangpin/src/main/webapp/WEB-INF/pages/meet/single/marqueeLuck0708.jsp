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
    .using("${cdn:js(pageContext.request)}/styles/shangpin/js/meet/single/marqueeLuck0708.js${ver}")
    .excute(function(){
		isHome(false);
	});
	</script>
<link href="${cdn:css(pageContext.request)}/styles/shangpin/css/page/meet/single/marqueeLuck0708.css${ver}" rel="stylesheet" />
</rapid:override>
<rapid:override name="header">
</rapid:override>
<rapid:override name="content">
<div class="alContent">
<h1 class="title-top">
        <img src="${cdn:pic(pageContext.request)}/styles/shangpin/images/20150707TopShopGoodLuck/top_title.png">
    </h1>
	<div class="turntable">
        <div class="turntable-box">
        	<img id="turntable_img" src="${cdn:pic(pageContext.request)}/styles/shangpin/images/20150707TopShopGoodLuck/turntable_img_03.png">
            <div id="turntable_btn"></div>
        </div>
    </div>
    <div class="con">
        <ul class="con-text">
            <li>每个用户每天有3次抽奖机会，TOPSHOP任性让你百分百中奖</li>
            <li><strong>活动时间：</strong>2015.07.08-2015.07.12</li>
            <li><strong>奖项设置：</strong><br>
              <strong>一等奖：</strong>1000元衣橱基金（现金券）<br>
              <strong>二等奖：</strong>超美单品直接送（每周五统一发放）<br>
              <strong>三等奖：</strong>30元现金券<br>
            </li>
            <li>活动最终解释权归尚品网所有。</li>
        </ul>
    </div>
    
    <div class="sroll-text">
        <div>
        <ul class="lottery_list" id="window_roll">
        
        </ul>
        </div>
    </div>
    </div>
</rapid:override>

 
<rapid:override name="footer">
</rapid:override>
<%@ include file="/WEB-INF/pages/common/base_meet.jsp" %> 
