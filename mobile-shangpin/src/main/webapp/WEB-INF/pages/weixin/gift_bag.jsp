<%@ page language="java" contentType="text/html; charset=UTF-8" trimDirectiveWhitespaces="true"
    pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/pages/common/include.inc.jsp"%>

<rapid:override name="title">
	TOPSHOP千元大礼包，人人有份！
</rapid:override>
<rapid:override name="custum">
	<link href="${cdn:css(pageContext.request)}/styles/shangpin/css/star-packet/thousand_base.css${ver}" rel="stylesheet" />
	<link href="${cdn:css(pageContext.request)}/styles/shangpin/css/star-packet/thousand_red_packet.css${ver}" rel="stylesheet" />
</rapid:override>

<rapid:override name="header">
	
</rapid:override>

<rapid:override name="content">
	<input type="hidden" id="openId" name="openId" value="${openId}"/>
	<section class="main">
		<h2 style=" text-indent:-9999px">尚品网三千万红包</h2>
	    <div class="form-box">
	    	<i class="red-icon"><img src="${cdn:pic(pageContext.request)}/styles/shangpin/images/star-packet/red_icon.png" /></i>
	        <input class="tip-input" id="J_mobileNum" type="tel" value="" placeholder="请输入手机号" maxlength="11" required />
	        <div class="yzm-box">
	          <div class="login_list"><input id="J_yzm" type="tel" value="" placeholder="请输入验证码" maxlength="6" required></div>
	          <em id="passwordGetCode" data-waiting="秒">获取验证码</em>
	        </div> 
	         <p class="prompt"></p> 
	        <a class="start-btn js-start-btn" href="javascript:;">领取</a>
	    	<p class="tip-text">温馨提示：<br>
	1.如果您已是尚品会员并绑定了手机，请输入该手机号，否则，将按照您
	    新输入的手机为您注册会员;<br>
	2.每位客户限领取一份优惠券礼包。</p>
	    </div>
  	</section>
  	
  	 <!--内容区域 start-->
  <footer class="footer"><img src="${cdn:pic(pageContext.request)}/styles/shangpin/images/star-packet/footer_bg.jpg" /></footer>
  <!--红包弹层-->
  <div class="without-box">
  	<div class="without-info">
      <a class="close-btn" href="javascript:;"></a>
      <h3>红包发完了</h3>
      <p></p>
      <a class="click-look"></a>
    </div>
  </div>
</rapid:override>

<rapid:override name="static_file">
	<script src="${cdn:js(pageContext.request)}/styles/shangpin/js/star-packet/thousand_red_packet.js${ver}" type="text/javascript" charset="utf-8"></script>
</rapid:override>

<%@ include file="/WEB-INF/pages/common/star_packet_base.jsp" %> 