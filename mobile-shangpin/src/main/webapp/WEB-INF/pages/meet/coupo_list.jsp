<%@ page language="java" contentType="text/html; charset=UTF-8"
	trimDirectiveWhitespaces="true" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/pages/common/include.inc.jsp"%>

<rapid:override name="custum">
<script src="${cdn:js(pageContext.request)}/styles/shangpin/js/jquery.min.js${ver}"  type="text/javascript" charset="utf-8"></script>
<script src="${cdn:js(pageContext.request)}/styles/shangpin/js/meet/316/index.js${ver}"  type="text/javascript" charset="utf-8"></script>
<link href="${cdn:css(pageContext.request)}/styles/shangpin/css/page/meet/264/flip.css${ver}" rel="stylesheet" />
</rapid:override>
<rapid:override name="title">
    	天天领福利
</rapid:override>
<rapid:override name="page_title">
    	天天领福利
</rapid:override>
<rapid:override name="content">
      <!--内容区域 start-->
    <div class="content-box">
        <input type="hidden" id="isLogin" value="${isLogin}" />
        <p><a href="${ctx}/couponRule"><img src="${cdn:pic(pageContext.request)}/styles/shangpin/images/flipDraw/red_img01.jpg" /></a></p>
         <ul class="coupon-list">
	        <li id="3359763552" data-name="OASIS" data-num="50"><img src="${cdn:pic(pageContext.request)}/styles/shangpin/images/flipDraw/coupon01.jpg" /></li>
	        <li id="3347613587" data-name="PAGEONE" data-num="50"><img src="${cdn:pic(pageContext.request)}/styles/shangpin/images/flipDraw/coupon02.jpg" /></li>
	        <li id="3334317787" data-name="内衣品类" data-num="180"><img src="${cdn:pic(pageContext.request)}/styles/shangpin/images/flipDraw/coupon03.jpg" /></li>
	        <li id="3312010494" data-name="CELE男鞋" data-num="50"><img src="${cdn:pic(pageContext.request)}/styles/shangpin/images/flipDraw/coupon04.jpg" /></li>
	        <li id="3492564496" data-name="LESS" data-num="20"><img src="${cdn:pic(pageContext.request)}/styles/shangpin/images/flipDraw/coupon05.jpg" /></li>
	        <li id="3505369929" data-name="LUCKY OZASEC" data-num="25"><img src="${cdn:pic(pageContext.request)}/styles/shangpin/images/flipDraw/coupon06.jpg" /></li>
	        <li id="3379964803" data-name="MISS SELFRIDGE" data-num="200"><img src="${cdn:pic(pageContext.request)}/styles/shangpin/images/flipDraw/coupon07.jpg" /></li>
	        <li id="3528576525" data-name="PAUL FRANK" data-num="50"><img src="${cdn:pic(pageContext.request)}/styles/shangpin/images/flipDraw/coupon08.jpg" /></li>
	        <li id="3549311123" data-name="PILGRIM" data-num="20"><img src="${cdn:pic(pageContext.request)}/styles/shangpin/images/flipDraw/coupon09.jpg" /></li>
	        <li id="3323804248" data-name="BURBERRY男装" data-num="200"><img src="${cdn:pic(pageContext.request)}/styles/shangpin/images/flipDraw/coupon10.jpg" /></li>
	        <li id="3438609360" data-name="配饰品类" data-num="50"><img src="${cdn:pic(pageContext.request)}/styles/shangpin/images/flipDraw/coupon11.jpg" /></li>
	        <li id="3362670449" data-name="眼睛腕表家居品类" data-num="100"><img src="${cdn:pic(pageContext.request)}/styles/shangpin/images/flipDraw/coupon12.jpg" /></li>
        </ul>
        <p><img src="${cdn:pic(pageContext.request)}/styles/shangpin/images/flipDraw/red_img04.jpg" /></p>

       
    </div>
    <!--内容区域 start-->
	<!--礼券包弹层-->
  	  <div class="overlay" id="overlay">
        <section class="modal modal-test">
          <div class="modal-hd coupon-name">您已领取<span>女装品类</span></div>
          <div class="modal-bd">
            <p>
            	￥<strong class="coupon-num">1000</strong>优惠券<br>
                已充入您的账户中
            </p>
            <a href="${ctx }/coupon/list" class="click-look">点击查看</a>
          </div>
          
        </section>
      </div>
      
    <!-- 验证手机-->    
    <div class="login-box js-login-box" id="login_box2">
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

