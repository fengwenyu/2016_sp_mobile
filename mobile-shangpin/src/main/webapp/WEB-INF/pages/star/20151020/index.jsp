<%@ page language="java" contentType="text/html; charset=UTF-8" trimDirectiveWhitespaces="true"
    pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/pages/common/include.inc.jsp"%>

<rapid:override name="title">
	尚品网CEO红包,快抢！
</rapid:override>
<rapid:override name="custum">
	<link href="${cdn:css(pageContext.request)}/styles/shangpin/css/star-packet/20151020/base.css${ver}" rel="stylesheet" />
	<link href="${cdn:css(pageContext.request)}/styles/shangpin/css/star-packet/20151020/ceo_packet.css${ver}" rel="stylesheet" />
</rapid:override>

<rapid:override name="content">
	<input type="hidden" name="_shareUrl"  id="_shareUrl"  value="http://m.shangpin.com/star/index?star=ceo"/>
	<input type="hidden" name="_mainTitle"  id="_mainTitle"  value="尚品网CEO，1亿红包任性送，人人有份，快来抢现金！"/>
	<input type="hidden" name="_mainDesc"  id="_mainDesc"  value="更有尚品网轻奢购物狂欢节，10月22日-28日 精彩不容错过。"/>
	<input type="hidden" name="_mainImgUrl" id="_mainImgUrl"  value="${cdn:pic(pageContext.request)}/styles/shangpin/images/star-packet/20151020/300X300.jpg"/>
	<input type="hidden" name="_currentUrl" id="_currentUrl"  value=""/>
<div class="wapper">
 
  <!--内容区域 start-->
  <section class="main">
  	<h2 style=" text-indent:-9999px">尚品网CEO一亿红包</h2>
    <div class="form-box">
    	<h3 style=" font-size:20px; line-height:20px;padding-bottom:15px; color:#fff; text-align:center;">立即抢红包</h3>
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
    	
    </div>
    <img src="${cdn:pic(pageContext.request)}/styles/shangpin/images/star-packet/20151020/text_bg.jpg" >
    <!-- <div class="tip-box">
    	<p class="tip-text">温馨提示：<br> 1. 您输入的手机号将注册成为尚品网用户，密码随手机短信下发。<br>2. 红包将放入该手机号账户中，可直接使用。<br>3. 每个用户限领取一个红包。</p>
    </div> -->
     
  </section>
  <!--内容区域 start-->
  
  <!--红包领完了弹层-->
  <div class="without-box">
  	<div class="without-info">
      <a class="close-btn" href="javascript:;"></a>
      <h3>红包发完了<br>你可以关注尚品网官方微信<br>去领取尚品网千元红包 </h3>
      <p><img src="${cdn:pic(pageContext.request)}/styles/shangpin/images/star-packet/20151020/ewm.jpg" ></p>
    </div>
  </div>
  
</div>
</rapid:override>

<rapid:override name="static_file">
    <script src="${cdn:js(pageContext.request)}/styles/weixin/jweixin-1.0.0.js${ver}" type="text/javascript" charset="utf-8"></script>
	<script src="${cdn:js(pageContext.request)}/styles/shangpin/js/star-packet/20151020/jquery.min.js${ver}" type="text/javascript" charset="utf-8"></script>
	<script src="${cdn:js(pageContext.request)}/styles/shangpin/js/star-packet/20151020/weixin_ready.js${ver}" type="text/javascript" charset="utf-8"></script>
	<script src="${cdn:js(pageContext.request)}/styles/shangpin/js/star-packet/20151020/ceo_packet.js${ver}" type="text/javascript" charset="utf-8"></script>
</rapid:override>
<%@ include file="/WEB-INF/pages/common/star_red_packet_base.jsp" %> 