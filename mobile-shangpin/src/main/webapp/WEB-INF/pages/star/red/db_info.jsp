<%@ page language="java" contentType="text/html; charset=UTF-8"
	trimDirectiveWhitespaces="true" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/pages/common/include.inc.jsp"%>
 
<div class="wapper">
 
  <!--内容区域 start-->
  <section class="main">
  	<!--<h2 style=" text-indent:-9999px">圣诞现金红包</h2>-->
    <div class="red-title">
    	<img src="${cdn:pic(pageContext.request)}/styles/shangpin/images/star-packet/red_48/bg2.png" />
    </div>
    <div id="js_show_box">
    <div class="form-box">
    	<h3 class="title_h3">立即抢红包</h3>
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
    </div>
    <img src="${cdn:pic(pageContext.request)}/styles/shangpin/images/star-packet/red_48/tip_text.png" >
  </section>
  <!--内容区域 start-->
  <img src="${cdn:pic(pageContext.request)}/styles/shangpin/images/star-packet/red_48/img03.png" style="position:absolute; bottom:0;" >
  
  <div id="popup_overlay"></div>
  <div id="popup_container">
      <div id="popup_message">您已经领过了</div>
  </div>

  
</div>




