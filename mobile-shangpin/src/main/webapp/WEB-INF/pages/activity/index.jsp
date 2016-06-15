<%@ page language="java" contentType="text/html; charset=UTF-8" trimDirectiveWhitespaces="true"
    pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/pages/common/include.inc.jsp"%>

<rapid:override name="title">
  adidas NMD跑鞋，共六款配色0元赠！
</rapid:override>
<rapid:override name="custum">
	<link href="${cdn:css(pageContext.request)}/styles/shangpin/css/star-packet/base.css${ver}" rel="stylesheet" />
    <link href="${cdn:css(pageContext.request)}/styles/shangpin/css/nmb_activie/packet.css${ver}"	rel="stylesheet" />
</rapid:override>
<rapid:override name="content">
 <input type="hidden" name="_shareUrl"  id="_shareUrl"  value="http://m.shangpin.com/activity/index"/>
 <input type="hidden" name="_currentUrl" id="_currentUrl"  value=""/>
 <input type="hidden" name="_mainTitle"  id="_mainTitle"  value="adidas NMD跑鞋，共六款配色0元赠！"/>
 <input type="hidden" name="_mainDesc"  id="_mainDesc"  value="排队都买不到的全宇宙最夯NMD，尚品网任性0元送！另有50元现金红包海量发放~"/>
 <input type="hidden" name="_mainImgUrl" id="_mainImgUrl"  value="${cdn:pic(pageContext.request)}/styles/shangpin/images/nmb_activie/nbd.jpg"/>

<div class="wapper">
 
  <!--内容区域 start-->
  <section class="main">
  	<img src="${cdn:pic(pageContext.request)}/styles/shangpin/images/nmb_activie/index_bg.png" />
    <div class="link_box">
    <a href="${ctx}/activity/details"></a><a href="${ctx}/activity/edit"></a></div>
  </section>
  <!--内容区域 start-->
  
</div>
</rapid:override>
<rapid:override name="static_file">
    <script src="${cdn:js(pageContext.request)}/styles/weixin/jweixin-1.0.0.js${ver}" type="text/javascript" charset="utf-8"></script>
	<script src="${cdn:js(pageContext.request)}/styles/shangpin/js/star-packet/20151020/jquery.min.js${ver}" type="text/javascript" charset="utf-8"></script>
	<script src="${cdn:js(pageContext.request)}/styles/shangpin/js/star-packet/20151020/weixin_ready.js${ver}" type="text/javascript" charset="utf-8"></script>
	<script src="${cdn:js(pageContext.request)}/styles/shangpin/js/nmb_activie/packet.js${ver}" type="text/javascript" charset="utf-8"></script>
</rapid:override>
<%@ include file="/WEB-INF/pages/common/star_red_packet_base.jsp" %> 