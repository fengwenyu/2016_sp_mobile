<%@ page language="java" contentType="text/html; charset=UTF-8" trimDirectiveWhitespaces="true"
    pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/pages/common/include.inc.jsp"%>

<rapid:override name="title">
	杨幂生日发红包,快抢！
</rapid:override>
<rapid:override name="custum">
	<link href="${cdn:css(pageContext.request)}/styles/shangpin/css/star-packet/base.css${ver}" rel="stylesheet" />
	<link href="${cdn:css(pageContext.request)}/styles/shangpin/css/star-packet/red_packet.css${ver}" rel="stylesheet" />
</rapid:override>

<rapid:override name="page_title">
	杨幂生日发红包,快抢！
</rapid:override>

<rapid:override name="content">
	<input type="hidden" id="batchNo" name="batchNo" value="${batchNo}"/>
	<input type="hidden" id="source" name="source" value="${source}"/>
	<input type="hidden" name="_shareUrl"  id="_shareUrl"  value="http://m.shangpin.com/star/packet?batchNo=${batchNo}"/>
	<input type="hidden" name="_mainTitle"  id="_mainTitle"  value="杨幂发价值5000万红包，抢疯了！"/>
	<input type="hidden" name="_mainDesc"  id="_mainDesc"  value="杨幂生日发红包，见者有份！"/>
	<input type="hidden" name="_mainImgUrl" id="_mainImgUrl"  value="${cdn:pic(pageContext.request)}/styles/shangpin/images/star-packet/weixin_share.jpg"/>
	<input type="hidden" name="_currentUrl" id="_currentUrl"  value=""/`>
	<section class="main">
		<h2 style=" text-indent:-9999px">尚品网三千万红包</h2>
	    <div class="form-box">
	    	<i class="red-icon"><img src="${cdn:pic(pageContext.request)}/styles/shangpin/images/star-packet/red_icon.png" /></i>
	        <input class="tip-input" id="J_mobileNum" type="tel" value="" placeholder="请输入手机号" maxlength="11" required />
	        <div class="yzm-box">
	          <div class="login_list"><input id="J_yzm" type="tel" value="" placeholder="请输入验证码" maxlength="6" required></div>
	          <em id="passwordGetCode" data-waiting="秒">获取验证码</em>
	        </div> 
	       
	        <div class="graphics-code">
	          <div class="graphics-code-box"><input type="tel" id="J_graphicsCode"  value="" placeholder="请输入图形码" maxlength="4" required></div>
	          <img class="code-img" title="看不清，单击图片换一组" src="${ctx}/common/captcha"/>
	          <span class="refresh-btn"><img onclick="changeImage();" src="${cdn:pic(pageContext.request)}/styles/shangpin/images/star-packet/refresh_btn.png" /></span>
	        </div> 
	        <p class="prompt"></p> 
	        <a class="start-btn js-start-btn" href="javascript:;">抢红包</a>
	        <p class="tip-text">温馨提示：<br> 1. 你输入的手机号将注册成为尚品网用户，红包会放入该手机号账户中.<br>2. 每个用户限领取一个红包.</p>
	    </div>
	    <div class="sroll-text">
	      <ul class="lottery_list" id="window_roll">
	          <li>138****3254  获得尚品网TOPSHOP红包<span><strong>50</strong>元</span></li>
	          <li>135****8496  获得尚品网TOPSHOP红包<span><strong class="bold">1000</strong>元</span></li>
	          <li>188****8678  获得尚品网TOPSHOP红包<span><strong>500</strong>元</span></li>
	          <li>158****2356  获得尚品网TOPSHOP红包<span><strong>50</strong>元</span></li>
	          <li>136****4587  获得尚品网TOPSHOP红包<span><strong class="bold">1000</strong>元</span></li>
	          <li>175****5512  获得尚品网TOPSHOP红包<span><strong>200</strong>元</span></li>
	          <li>137****6688  获得尚品网TOPSHOP红包<span><strong>200</strong>元</span></li>
	          <li>186****5533  获得尚品网TOPSHOP红包<span><strong>100</strong>元</span></li>
	          <li>135****5512  获得尚品网TOPSHOP红包<span><strong>200</strong>元</span></li>
	          <li>186****5533  获得尚品网TOPSHOP红包<span><strong>100</strong>元</span></li>
	          <li>175****5512  获得尚品网TOPSHOP红包<span><strong>200</strong>元</span></li>
	          <li>138****6788  获得尚品网TOPSHOP红包<span><strong>200</strong>元</span></li>
	          <li>137****5543  获得尚品网TOPSHOP红包<span><strong>100</strong>元</span></li>
	          <li>177****5289  获得尚品网TOPSHOP红包<span><strong>200</strong>元</span></li>
	          <li>186****5593  获得尚品网TOPSHOP红包<span><strong>100</strong>元</span></li>
	      </ul>
	    </div>
	    <footer class="phone-txt"><a href="tel:4006-900-900">客服电话：4006-900-900</a></footer>
	    <img class="red-img red-img1" src="${cdn:pic(pageContext.request)}/styles/shangpin/images/star-packet/red01.png" width="56">
	    <img class="red-img red-img2" src="${cdn:pic(pageContext.request)}/styles/shangpin/images/star-packet/red02.png" width="60">
	    <img class="red-img red-img3" src="${cdn:pic(pageContext.request)}/styles/shangpin/images/star-packet/red03.png" width="63">
	    <img class="red-img red-img4" src="${cdn:pic(pageContext.request)}/styles/shangpin/images/star-packet/red04.png" width="82">
	    <img class="red-img coin-img1" src="${cdn:pic(pageContext.request)}/styles/shangpin/images/star-packet/coin01.png" width="39">
	    <img class="red-img coin-img2" src="${cdn:pic(pageContext.request)}/styles/shangpin/images/star-packet/coin02.png" width="39">
	    <img class="red-img coin-img3" src="${cdn:pic(pageContext.request)}/styles/shangpin/images/star-packet/coin03.png" width="39">
	    <img class="red-img coin-img4" src="${cdn:pic(pageContext.request)}/styles/shangpin/images/star-packet/coin04.png" width="39">
  	</section>
  	
  	  <!--红包领完了弹层-->
	  <div class="without-box">
	  	<div class="without-info">
	      <a class="close-btn" href="javascript:;"></a>
	      <h3>红包发完了<br>你可以关注尚品网官方微信<br>去领取尚品网千元红包 </h3>
	      <p><img src="${cdn:pic(pageContext.request)}/styles/shangpin/images/star-packet/ewm.jpg" ></p>
	    </div>
	  </div>
</rapid:override>

<rapid:override name="static_file">
	<script src="${cdn:js(pageContext.request)}/styles/weixin/jweixin-1.0.0.js${ver}" type="text/javascript" charset="utf-8"></script>
	<script src="${cdn:js(pageContext.request)}/styles/shangpin/js/star-packet/weixin_ready.js${ver}" type="text/javascript" charset="utf-8"></script>
	<script src="${cdn:js(pageContext.request)}/styles/shangpin/js/star-packet/red_packet.js${ver}" type="text/javascript" charset="utf-8"></script>
</rapid:override>

<%@ include file="/WEB-INF/pages/common/star_packet_base.jsp" %> 