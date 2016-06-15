<%@ page language="java" contentType="text/html; charset=UTF-8" trimDirectiveWhitespaces="true"
    pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/pages/common/include.inc.jsp"%>

<rapid:override name="title">
  adidas NMD跑鞋，共六款配色0元赠！
</rapid:override>
<rapid:override name="custum">
	<link href="${cdn:css(pageContext.request)}/styles/shangpin/css/star-packet/base.css${ver}" rel="stylesheet" />
    <link href="${cdn:css(pageContext.request)}/styles/shangpin/css/nmb_activie/packet.css${ver}"	rel="stylesheet" />
    <style>
		html, body{height:100%;}
		@media screen and (max-width: 360px) {
			html, body{height:auto;}
			.form-box{padding:15px 0; margin:0 0 60%;}
		}
   </style>
</rapid:override>
<rapid:override name="content">
 <input type="hidden" name="_shareUrl"  id="_shareUrl"  value="http://m.shangpin.com/activity/index"/>
 <input type="hidden" name="_currentUrl" id="_currentUrl"  value=""/>
 <input type="hidden" name="_mainTitle"  id="_mainTitle"  value="adidas NMD跑鞋，共六款配色0元赠！"/>
 <input type="hidden" name="_mainDesc"  id="_mainDesc"  value="排队都买不到的全宇宙最夯NMD，尚品网任性0元送！另有50元现金红包海量发放~"/>
 <input type="hidden" name="_mainImgUrl" id="_mainImgUrl"  value="${cdn:pic(pageContext.request)}/styles/shangpin/images/nmb_activie/nbd.jpg"/>

	<div class="wapper" >
	 
	  <!--内容区域 start-->
	  <section class="main">
	    <div id="js_show_box" style="background:#fff;">
	    <div class="form-box">
	    	<h3>填写信息领取抽奖号码：</h3>
	        <div class="yzm-box">
	          <em>姓名</em>
	          <div class="login_list"><input class="tip-input" id="J_name" type="text" value="" placeholder="请输入姓名" maxlength="5" required style="width:60%;" /></div>
	        </div>
	        <div class="yzm-box">
	          <em>性别</em>
	          <div class="login_list">
	          	<label for="male" class="cur">男<input type="radio" id="male" name="gender"></label>
	            <label for="female">女<input type="radio" id="female" name="gender"></label>
	          </div>
	        </div> 
	        <div class="yzm-box" style="margin-bottom:5px;">
	          <em>尺码</em>
	          <div class="login_list">
	          	<a href="javascript:;" class="cur">36</a>
	            <a href="javascript:;">36.5</a>
	            <a href="javascript:;">37</a>
	            <a href="javascript:;">38</a>
	            <a href="javascript:;">38.5</a>
	            <a href="javascript:;">39</a>
	            <a href="javascript:;">40</a>
	            <a href="javascript:;">40.5</a>
	            <a href="javascript:;">41</a>
	            <a href="javascript:;">42</a>
	            <a href="javascript:;">42.5</a>
	            <a href="javascript:;">43</a>
	            <a href="javascript:;">44</a>
	            <a href="javascript:;">44.5</a>
	            <a href="javascript:;">45</a>
	          </div>
	        </div>
	        <div class="yzm-box">
	          <em>手机号</em>
	          <div class="login_list"><input class="tip-input" id="J_mobileNum" type="tel" value="" placeholder="请输入手机号" maxlength="11" required style="width:80%;"/></div>
	        </div>
	         <p class="prompt"></p> 
	        <a class="start-btn js-start-btn" href="javascript:;">确认领取</a>
	    	
	    </div>
	    </div>
	    
	  </section>
	  <!--内容区域 start-->
	  
	  
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
	<script src="${cdn:js(pageContext.request)}/styles/shangpin/js/nmb_activie/packet.js${ver}" type="text/javascript" charset="utf-8"></script>
</rapid:override>
<%@ include file="/WEB-INF/pages/activity/edit_base.jsp" %> 