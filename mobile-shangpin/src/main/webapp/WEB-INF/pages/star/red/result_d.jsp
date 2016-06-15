<%@ page language="java" contentType="text/html; charset=UTF-8" trimDirectiveWhitespaces="true"
    pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/pages/common/include.inc.jsp"%>

<rapid:override name="title">
	尚品网祝你圣诞快乐！
</rapid:override>
<rapid:override name="custum">
	<link href="${cdn:css(pageContext.request)}/styles/shangpin/css/star-packet/black_five/base.css${ver}" rel="stylesheet" />
	<link href="${cdn:css(pageContext.request)}/styles/shangpin/css/star-packet/black_five/swiper.min.css${ver}" rel="stylesheet" />
	<link href="${cdn:css(pageContext.request)}/styles/shangpin/css/star-packet/black_five/packet.css${ver}" rel="stylesheet" />
</rapid:override>

<rapid:override name="page_title">
	尚品网祝你圣诞快乐！
</rapid:override>

<rapid:override name="content">
    <input type="hidden" name="_shareUrl"  id="_shareUrl"  value="http://m.shangpin.com/star/index?star=${star}"/>
	<input type="hidden" name="_mainTitle"  id="_mainTitle"  value="尚品网CEO，1亿红包任性送，人人有份，快来抢！"/>
	<input type="hidden" name="_mainDesc"  id="_mainDesc"  value="11月26日-11月30日尚品网黑色星期五，全球尖货5折封顶"/>
	<input type="hidden" name="_mainImgUrl" id="_mainImgUrl"  value="${cdn:pic(pageContext.request)}/styles/shangpin/images/star-packet/black_five/300-300ceo.jpg"/>
	<input type="hidden" name="_currentUrl" id="_currentUrl"  value=""/>
 <!--内容区域 start-->
  <section class="main">  
    <div class="red-val">
      <img class="get-coupon" src="${cdn:pic(pageContext.request)}/styles/shangpin/images/star-packet/red_1212/coupon.png">
      <h3>恭喜您！</h3>
      <h4>圣诞红包已放入${phoneNum }账户</h4>
      <div class="btn-box clr">
        <a href="${ctx }/coupon/list">查看红包</a>
        <a href="${ctx }/meet/426">立即使用</a>
      </div>
    </div>
    <img src="${cdn:pic(pageContext.request)}/styles/shangpin/images/star-packet/red_1212/title01.png" >
    <div class="swiper-container" id="swiper_item">
      <div class="swiper-wrapper">
        <div class="swiper-slide"><a href="#"><img alt="" src="${cdn:pic(pageContext.request)}/styles/shangpin/images/star-packet/red_1212/item_img01.png" width="100%"></a></div>
        <div class="swiper-slide"><a href="#"><img alt="" src="${cdn:pic(pageContext.request)}/styles/shangpin/images/star-packet/red_1212/item_img02.png" width="100%"></a></div>
        <div class="swiper-slide"><a href="#"><img alt="" src="${cdn:pic(pageContext.request)}/styles/shangpin/images/star-packet/red_1212/item_img03.png" width="100%"></a></div>
        <div class="swiper-slide"><a href="#"><img alt="" src="${cdn:pic(pageContext.request)}/styles/shangpin/images/star-packet/red_1212/item_img04.png" width="100%"></a></div>
        <div class="swiper-slide"><a href="#"><img alt="" src="${cdn:pic(pageContext.request)}/styles/shangpin/images/star-packet/red_1212/item_img05.png" width="100%"></a></div>
      </div>
    </div>
    <a href="http://m.shangpin.com/download"> 
    <img src="${cdn:pic(pageContext.request)}/styles/shangpin/images/star-packet/red_1212/title03.png" >
    </a>
    <img src="${cdn:pic(pageContext.request)}/styles/shangpin/images/star-packet/red_1212/title04.png" >
    
  </section>
  <!--内容区域 start-->
</rapid:override>

<rapid:override name="static_file">
    <script src="${cdn:js(pageContext.request)}/styles/weixin/jweixin-1.0.0.js${ver}" type="text/javascript" charset="utf-8"></script>
	<script src="${cdn:js(pageContext.request)}/styles/shangpin/js/star-packet/20151020/jquery.min.js${ver}" type="text/javascript" charset="utf-8"></script>
	<script src="${cdn:js(pageContext.request)}/styles/shangpin/js/star-packet/20151020/weixin_ready.js${ver}" type="text/javascript" charset="utf-8"></script>
    <script src="${cdn:js(pageContext.request)}/styles/shangpin/js/star-packet/red/swiper.min.js${ver}" type="text/javascript" charset="utf-8"></script>
	<script src="${cdn:js(pageContext.request)}/styles/shangpin/js/star-packet/red/packet.js${ver}" type="text/javascript" charset="utf-8"></script>
</rapid:override>

<%@ include file="/WEB-INF/pages/star/black_five/red_packet_base.jsp" %>