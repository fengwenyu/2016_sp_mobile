<%@ page language="java" contentType="text/html; charset=UTF-8"  pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/pages/common/include.inc.jsp"%>

<rapid:override name="custum">
<style>
.main_box img{ width:100%}
</style>
<script src="${cdn:js(pageContext.request)}/styles/shangpin/js/core.js" type="text/javascript" charset="utf-8"></script>
<script src="" type="text/javascript" charset="utf-8"></script>
<script type="text/javascript" charset="utf-8">
  var ver = Math.random();
    loader = SP.core
      .install("${cdn:js(pageContext.request)}/styles/shangpin/js/jquery.min.js?" + ver)  //jquery库文件
        .using("${cdn:js(pageContext.request)}/styles/shangpin/js/lazyload.js?" + ver)    //图片懒加载
        .excute()
        .using("${cdn:js(pageContext.request)}/styles/shangpin/js/config.sim.js?" + ver)  //图片懒加载
        .using("${cdn:js(pageContext.request)}/styles/shangpin/js/j.dialogs.js?" + ver)  //图片懒加载
        .excute()
        .using("${cdn:js(pageContext.request)}/styles/shangpin/js/giftcard/giftCard_send.js?" + ver)   //页面专用JS
      	.using("${cdn:js(pageContext.request)}/styles/weixin/jweixin-1.0.0.js")
      	.using("${cdn:js(pageContext.request)}/styles/shangpin/js/downloadAppShow.js?" + ver)   //页面专用JS
      	.excute();
      	//.using("${cdn:js(pageContext.request)}/styles/shangpin/js/star-packet/weixin_ready.js")
</script>

<link href="${cdn:css(pageContext.request)}/styles/shangpin/css/base.css${ver}" rel="stylesheet" />
<link href="${cdn:css(pageContext.request)}/styles/shangpin/css/public.css${ver}" rel="stylesheet" />
<link href="${cdn:css(pageContext.request)}/styles/shangpin/css/page/giftcard/giftCard_send2.css?34${ver}" rel="stylesheet" /><!--页面专用CSS-->
</rapid:override>

<%-- 页标题 --%>
<rapid:override name="page_title">
	赠送给小伙伴
</rapid:override>

<rapid:override name="downloadAppShowHead">

</rapid:override>

<rapid:override name="content">
<!-- <body class="bg-color"> -->
		<!-- 分享微信的参数 -->
		<input type="hidden" name="_shareUrl"  id="_shareUrl"  />
		<input type="hidden" name="_mainTitle"  id="_mainTitle" />
		<input type="hidden" name="_mainDesc"  id="_mainDesc"   />
		<input type="hidden" name="_mainImgUrl" id="_mainImgUrl"  value="${cdn:pic(pageContext.request)}/styles/shangpin/images/star-packet/giftCard_Send.jpg"/>
  <div class="container">
    <!-- 页面内容start -->
    <div class="alContent">
      	<div class="js-share-pop" style="position:fixed; right:0; top:0; height:100%; width:100%; background:rgba(0,0,0,0.7);  z-index:999; display:none;"><img src="${cdn:pic(pageContext.request)}/styles/shangpin/images/giftcard/sendToFriend.jpg" /></div>
    	<div class="sendCard_form">
            <p><textarea maxlength="70" data-value="给您的小伙伴写点什么吧。">给您的小伙伴写点什么吧。</textarea></p>
        </div>
        <div class="select_img_box">
        	<p class="chose_img">选一张图片作为您祝福信息的背景吧</p>
            <ul class="chose_img_list clr">
            	<li><img src="${cdn:pic(pageContext.request)}/images/giftCard/gift_card_img01.jpg"></li>
                <li><img src="${cdn:pic(pageContext.request)}/images/giftCard/gift_card_img02.jpg"></li>
                <li><img src="${cdn:pic(pageContext.request)}/images/giftCard/gift_card_img03.jpg"></li>
                <li><img src="${cdn:pic(pageContext.request)}/images/giftCard/gift_card_img08.jpg"></li>
                <li><img src="${cdn:pic(pageContext.request)}/images/giftCard/gift_card_img09.jpg"></li>
                <li><img src="${cdn:pic(pageContext.request)}/images/giftCard/gift_card_img07.jpg"></li>
                <li><img src="${cdn:pic(pageContext.request)}/images/giftCard/gift_card_img06.jpg"></li>
                <li><img src="${cdn:pic(pageContext.request)}/images/giftCard/gift_card_img04.jpg"></li>
            </ul>
        </div>
        <div class="select_box">
            <p class="input_box">
                <i class="mobile_icon"></i>
                <input maxlength="11" class="mobile_number" id="mbNumber" data-value="您小伙伴的手机号码" type="tel" value="您小伙伴的手机号码" >
                <input type="hidden" id="faceValue" value="${giftCard.faceValue }">
                <input type="hidden" id="giftCardId" value="${giftCard.giftCardId }">
                <input type="hidden" id="giftOrder" value="${giftCard.giftOrder }">
                <input type="hidden" id="requestHead" value="${basePath }">
                <input type="hidden" id="userid" value="${userid}">
            </p>
            <p class="tip_text f_red">手机号码是获取该礼品卡的验证方式，请准确填写。</p>
        </div>
        <a  class="submit_btn" id="submit_send" >赠送给小伙伴</a>
        <%-- <c:if test="${checkAPP}">
		  <div class="shang_share" > 
		         <a href="shangpinapp://phone.shangpin/actiongoshare?title=${meet.title }&desc=${meet.title }&url=http://m.shangpin.com/meet/${meet.id}" style="height:50px;"><img src="${cdn:pic(pageContext.request)}/styles/shangpin/images/b_share.png" width="40"></a>
		   </div>
		</c:if> --%>
    </div>
    <!-- 页面内容end -->
  </div>
  </rapid:override>
  <rapid:override name="static_file">
	
</rapid:override>
<%@ include file="/WEB-INF/pages/common/another_common_mall_banner.jsp" %>
