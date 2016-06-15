<%@ page language="java" contentType="text/html; charset=UTF-8"  pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/pages/common/include.inc.jsp"%>

<rapid:override name="custum">
	<script src="/js/core.js" type="text/javascript" charset="utf-8"></script>
	<script type="text/javascript" charset="utf-8">
	  var ver = Math.random();
	    loader = SP.core
	      .install("${cdn:js(pageContext.request)}/styles/shangpin/js/jquery.min.js?" + ver)  //jquery库文件
	        .using("${cdn:js(pageContext.request)}/styles/shangpin/js/lazyload.js?" + ver)    //图片懒加载
	        .excute()
	        .using("${cdn:js(pageContext.request)}/styles/shangpin/js/config.sim.js?" + ver)  //图片懒加载
	        .using("${cdn:js(pageContext.request)}/styles/shangpin/js/j.dialogs.js?" + ver)  //图片懒加载
	        .excute()
	        .using("${cdn:js(pageContext.request)}/styles/shangpin/js/swiper.min.js?" + ver)
	    	.excute()
	        .using("${cdn:js(pageContext.request)}/styles/shangpin/js/giftcard/giftCard_send.js?46" + ver)    //页面专用JS
	        .using("${cdn:js(pageContext.request)}/styles/shangpin/js/downloadAppShow.js?" + ver)   //页面专用JS
	        .excute()
	      
	</script>
	<link href="${cdn:css(pageContext.request)}/styles/shangpin/css/base.css${ver}" rel="stylesheet" />
	<link href="${cdn:css(pageContext.request)}/styles/shangpin/css/public.css${ver}" rel="stylesheet" />
	<link href="${cdn:css(pageContext.request)}/styles/shangpin/css/swiper.min.css${ver}" rel="stylesheet" />
	<link href="${cdn:css(pageContext.request)}/styles/shangpin/css/page/giftcard/giftCard_send2.css?39${ver}" rel="stylesheet" /><!--页面专用CSS-->
</rapid:override>

<%-- 页标题 --%>
<rapid:override name="page_title">
	预览
</rapid:override>
<rapid:override name="downloadAppShowHead">

</rapid:override>

<rapid:override name="downloadAppShowHead">

</rapid:override>

<rapid:override name="content">
  <div class="container">
    <!-- background:url(../../../../../images/giftCard/gift_card_img01.jpg) no-repeat center center; -->
    <!-- 页面内容start -->
    <div class="giftCard_preview_box">
    	<div class="swiper-container" id="swiper-container1">
            <div class="swiper-wrapper">
                <div id="wishPic" class="swiper-slide page_box1" style="background:url(${wishPic}) no-repeat center center;     background-size: cover; ">
                    <div class="wishes_box">
                       <%--  <img src="${cdn:css(pageContext.request)}/images/giftCard/text_bg.png">  --%>
                        <div>${giftCard.wishMsg}</div>
                    </div>
                    <img class="page_next" src="${cdn:pic(pageContext.request)}/images/giftCard/next_page_icon.png">
                </div>
    
                <div class="swiper-slide swiper-no-swiping page_box2" id="page_box2"><!--swiper-no-swiping-->
                    <p class="img_box">
                        <img src="${cdn:pic(pageContext.request)}/images/giftCard/giftCard${giftCard.faceValue}.jpg">
                        <a href="${pageContext.request.contextPath }/giftCard/skipToCardLogin">立即充值</a> 
                    </p>
                    <span class="title_h6">尚品网礼品卡介绍：</span>
                    <p class="text_con">
                        1.尚品礼品卡是尚品网的定向储值卡，支持在尚品网www.shangpin.com全网站使用。<br />
                        2.本卡需要充值于尚品网会员账户中方可使用，充值方式有2种。<br />
                        第一种：通过尚品网网站、app或wap站，使用礼品卡秘钥并按照提示充值。<br />
                        第二种：点击“立即充值”，按照提示步骤完成充值。<br />
                        3.您登录的账户即为您充值的账户。<br />
                        4.如有问题，请拨打客服电话：4006-900-900。
                    </p>
                    <br /><br />
                </div>
            
            </div>
        </div>
    </div>
    <!-- 页面内容end -->
  </div>
  
  </rapid:override>
<%@ include file="/WEB-INF/pages/common/another_common_mall_banner.jsp" %>
