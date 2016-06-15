<%@ page language="java" contentType="text/html; charset=UTF-8" trimDirectiveWhitespaces="true"
    pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/pages/common/include.inc.jsp"%>

<rapid:override name="title">
	一元购
</rapid:override>
<rapid:override name="custum">
	<link href="${cdn:css(pageContext.request)}/styles/shangpin/css/page/one/base.css${ver}" rel="stylesheet" />
	<link href="${cdn:css(pageContext.request)}/styles/shangpin/css/page/one/one_buy.css${ver}" rel="stylesheet" />
</rapid:override>

<rapid:override name="page_title">
	
</rapid:override>

<rapid:override name="content">
	<input type="hidden" name="_shareUrl"  id="_shareUrl"  value="http://m.shangpin.com/one/index"/>
	<input type="hidden" name="_mainTitle"  id="_mainTitle"  value="没看错！1元女神新装！尚品网CEO豪送2000套女神新装！"/>
	<input type="hidden" name="_mainDesc"  id="_mainDesc"  value="尚品网CEO豪送2000套女神新装，回馈辛苦的购物达人！"/>
	<input type="hidden" name="_mainImgUrl" id="_mainImgUrl"  value="${cdn:pic(pageContext.request)}/styles/shangpin/images/goddes/goddes-10.jpg"/>
	<input type="hidden" name="_currentUrl" id="_currentUrl"  value=""/>
	<!--内容区域 start-->
  <section class="main">
      <p><img src="${cdn:pic(pageContext.request)}/styles/shangpin/images/one/img01.png" ></p>
      <p class="share-btn"><img src="${cdn:pic(pageContext.request)}/styles/shangpin/images/one/img02.png" ></p>
      <p><img src="${cdn:pic(pageContext.request)}/styles/shangpin/images/one/img03.png" ></p>
  </section>
  <!--内容区域 start-->
  
  <!--分享弹层-->
  <div class="share-box">
  	<img src="${cdn:pic(pageContext.request)}/styles/shangpin/images/one/share.png" >
    <!--<a href="javascript:;" class="know-btn">分享</a>-->
  </div>
</rapid:override>

<rapid:override name="static_file">
	<script src="${cdn:js(pageContext.request)}/styles/weixin/jweixin-1.0.0.js${ver}" type="text/javascript" charset="utf-8"></script>
	<script src="${cdn:js(pageContext.request)}/styles/shangpin/js/one/one_buy.js${ver}" type="text/javascript" charset="utf-8"></script>
	<script src="${cdn:js(pageContext.request)}/styles/shangpin/js/one/weixin_ready.js${ver}" type="text/javascript" charset="utf-8"></script>
</rapid:override>

<%@ include file="/WEB-INF/pages/common/star_packet_base.jsp" %> 