<%@ page language="java" contentType="text/html; charset=UTF-8" trimDirectiveWhitespaces="true"
    pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/pages/common/include.inc.jsp"%>

<rapid:override name="title">
	尚品网CEO红包,快抢！
</rapid:override>
<rapid:override name="custum">
	<link href="${cdn:css(pageContext.request)}/styles/shangpin/css/star-packet/black_five/base.css${ver}" rel="stylesheet" />
	<link href="${cdn:css(pageContext.request)}/styles/shangpin/css/star-packet/black_five/packet.css${ver}" rel="stylesheet" />
</rapid:override>

<rapid:override name="content">
	<input type="hidden" name="_shareUrl"  id="_shareUrl"  value="http://m.shangpin.com/star/index?star=${star}"/>
	<input type="hidden" name="_mainTitle"  id="_mainTitle"  value="尚品网CEO，1亿红包任性送，人人有份，快来抢！"/>
	<input type="hidden" name="_mainDesc"  id="_mainDesc"  value="11月26日-11月30日尚品网黑色星期五，全球尖货5折封顶"/>
	<input type="hidden" name="_mainImgUrl" id="_mainImgUrl"  value="${cdn:pic(pageContext.request)}/styles/shangpin/images/star-packet/black_five/300-300ceo.jpg"/>
	<input type="hidden" name="_currentUrl" id="_currentUrl"  value=""/>
    <input type="hidden" name="star"  id="star"  value="${star}"/>
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
          <img class="code-img" title="看不清，单击图片换一组" src="${ctx}/common/captcha" onclick="changeImage(this);" >
          <span class="refresh-btn"><img onclick="changeImage();" src="${cdn:pic(pageContext.request)}/styles/shangpin/images/star-packet/refresh_btn.png" /></span>
        </div> 
         <p class="prompt"></p> 
        <a class="start-btn js-start-btn" href="javascript:;">抢红包</a>
    	
    </div>
    <img src="${cdn:pic(pageContext.request)}/styles/shangpin/images/star-packet/black_five/tip_text.png" >
  </section>
  <!--内容区域 start-->
  
  <c:if test="${star eq '6'}">
  <div class="float-text text1"></div>
  <div class="float-text text2"></div>
  <div class="float-text text3"></div>
  </c:if>
  <div id="popup_overlay"></div>
  <div id="popup_container">
      <div id="popup_message">您已经领过了</div>
  </div>
  
  
</div>
</rapid:override>

<rapid:override name="static_file">
    <script src="${cdn:js(pageContext.request)}/styles/weixin/jweixin-1.0.0.js${ver}" type="text/javascript" charset="utf-8"></script>
	<script src="${cdn:js(pageContext.request)}/styles/shangpin/js/star-packet/20151020/jquery.min.js${ver}" type="text/javascript" charset="utf-8"></script>
	<script src="${cdn:js(pageContext.request)}/styles/shangpin/js/star-packet/20151020/weixin_ready.js${ver}" type="text/javascript" charset="utf-8"></script>
	<script src="${cdn:js(pageContext.request)}/styles/shangpin/js/star-packet/black_five/packet.js${ver}" type="text/javascript" charset="utf-8"></script>
</rapid:override>
<%@ include file="/WEB-INF/pages/common/star_red_packet_base.jsp" %> 