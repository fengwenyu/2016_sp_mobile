<%@ page language="java" contentType="text/html; charset=UTF-8" trimDirectiveWhitespaces="true"
    pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/pages/common/include.inc.jsp"%>

<rapid:override name="title">
topshop转盘抽奖
</rapid:override>
<rapid:override name="custum">
    <link href="${cdn:css(pageContext.request)}/styles/shangpin/css/activity/base.css${ver}" rel="stylesheet" />
	<link href="${cdn:css(pageContext.request)}/styles/shangpin/css/activity/index.css${ver}" rel="stylesheet" />
    <!-- icon -->
	<link rel="apple-touch-icon" href="/images/touch-icon-iphone.png">
	<link rel="apple-touch-icon" sizes="72x72" href="/images/touch-icon-ipad.png">
	<link rel="apple-touch-icon" sizes="114x114" href="/images/touch-icon-iphone4.png">
	<link rel="apple-touch-icon" sizes="144x144" href="/images/touch-icon-newipad.png">
	<!-- 启动自定义图片(png, 320X640) -->
	<link rel="apple-touch-startup-image" sizes="72x72" href="/images/logo/loading.png">
</rapid:override>
<rapid:override name="content">
    <input type="hidden" value="${loginStatus}" id="isLogin"/>
    <div class="alContent">
	    <img src="${cdn:pic(pageContext.request)}/styles/shangpin/images/activity/img01.png">
		<div class="turntable">
	        <div class="turntable-box">
	        	<img id="turntable_img" src="${cdn:pic(pageContext.request)}/styles/shangpin/images/activity/turntable_img_03.png">
	            <div id="turntable_btn"></div>
	        </div>
	    </div>
	    <div class="con">
	        <ul class="con-text">
	            <li>每个用户只有1次抽奖机会</li>
	            <li><strong>活动时间：</strong>2016.04.27-2016.04.28</li>
	            <li><strong>奖项设置：</strong><br>
	              <strong>奖项一：</strong>草莓音乐节随机日票X2(限北京地区)<br>
	              <strong>奖项二：</strong>100元TOPSHOP现金券<br>
	              <strong>奖项三：</strong>30元TOPSHOP现金券<br>
	              <strong>奖项四：</strong>499-50 TOPSHOP优惠券<br>
	            </li>
	            <li>活动最终解释权归尚品网所有</li>
	        </ul>
	    </div>
	    
	</div>
</rapid:override>
<rapid:override name="static_file">
    <script src="${cdn:js(pageContext.request)}/styles/shangpin/js/activity/jquery.min.js${ver}" type="text/javascript" charset="utf-8"></script>
	<script src="${cdn:js(pageContext.request)}/styles/shangpin/js/activity/jquery.easing.min.js${ver}" type="text/javascript" charset="utf-8"></script>
	<script src="${cdn:js(pageContext.request)}/styles/shangpin/js/activity/jQueryRotate.2.2.js${ver}" type="text/javascript" charset="utf-8"></script>
	<script src="${cdn:js(pageContext.request)}/styles/shangpin/js/activity/xmas.dialogs.js${ver}" type="text/javascript" charset="utf-8"></script>
    <script src="${cdn:js(pageContext.request)}/styles/shangpin/js/activity/goodluck.js${ver}" type="text/javascript" charset="utf-8"></script>
</rapid:override>
<%@ include file="/WEB-INF/pages/common/star_red_packet_base.jsp" %> 