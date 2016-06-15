<%@ page language="java" contentType="text/html; charset=UTF-8" trimDirectiveWhitespaces="true"
    pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/pages/common/include.inc.jsp"%>
<rapid:override name="custum">
	<script type="text/javascript" charset="utf-8">
    loader = SP.core
	.install("${cdn:css(pageContext.request)}/styles/shangpin/js/zepto.1.1.3.js${ver}")  //jquery库文件
  	.using("${cdn:css(pageContext.request)}/styles/shangpin/js/lazyload.js${ver}")
	.excute()
	.using("${cdn:css(pageContext.request)}/styles/shangpin/js/config.sim.js${ver}")
	.excute()
	.using("${cdn:css(pageContext.request)}/styles/shangpin/js/comm.js${ver}")
	.using("${cdn:js(pageContext.request)}/styles/weixin/jweixin-1.0.0.js${ver}")
	.using("${cdn:js(pageContext.request)}/styles/shangpin/js/weixin/WXShareM.js${ver}")
    .using("${cdn:css(pageContext.request)}/styles/shangpin/js/FashionRun/download.js${ver}") 
      
</script>
<style type="text/css">
body{background-color:#000;}
.container{ min-width:320px; max-width:640px; margin:0 auto; color:#fafafa;}
.topFix{position:fixed; top:0; z-index:10;min-width:320px; max-width:640px; height:45px;}
.topFix section { position:static;}
body .alContent {line-height:20px;padding-bottom:10px; }
.receive_box{width:100%; }
.receive_box a{display:block;}
.receive_box p{ padding:0 30px; font-size:12px;}
.receive_box .big_title{ font-size:16px; margin:40px 0; text-align: center;}
.receive_box .code{margin: 10px 50px 20px;}
.shang_share{position: fixed;right:15px;bottom:68px;z-index:999;}
</style>
</rapid:override>
<%-- 浏览器标题 --%>
<rapid:override name="title">
	FashionRun 时尚美女跑者招募
</rapid:override>

<%-- 页标题 --%>
 <rapid:override name="page_title">
	FashionRun 时尚美女跑者招募
</rapid:override> 
 <rapid:override name="appLayer">
</rapid:override> 
<rapid:override name="downloadAppShowHead">
		
</rapid:override>
<rapid:override name="content">
	<input type="hidden" name="_shareUrl"  id="_shareUrl"  value="${basePath }/fashionrun/finish"/>
	<input type="hidden" name="_mainTitle"  id="_mainTitle"  value="FashionRun北京站美女招募,8月1日奥森公园不美不跑."/>
	<input type="hidden" name="_mainDesc"  id="_mainDesc"  value="FashionRun北京站美女招募,8月1日奥森公园不美不跑."/>
	<input type="hidden" name="_mainImgUrl" id="_mainImgUrl"  value="${basePath }/styles/shangpin/images/FashionRun/fashion-run.jpg"/>
   <!--内容部分-->
        <img src="${ctx }/styles/shangpin/images/FashionRun/form_banner.png" alt="">

    <div class="receive_box">    		   
		<p class="big_title" style="padding-top:10px; line-height:140px; font-size:30px; font-weight:700;">报名已结束！</p>
       <c:if test="${checkWX }">
       	<p class="big_title" style="font-size:12px;margin:0 0 10px 0; ">长按二维码关注 FashionRun 官方微信,<br>了解更多FashionRun活动</p>
        <p class="code"><a href=""><img src="${ctx }/styles/shangpin/images/FashionRun/registration_code.jpg" /></a></p>
      </c:if>
    </div>
 	<!--内容部分end-->
 <%-- <c:if test="${checkAPP}">
  <div class="shang_share"> 
         <a href="shangpinapp://phone.shangpin/actiongoshare?title=FashionRun北京站美女招募,8月1日奥森公园不美不跑.&url=${basePath }/fashionrun/finish&desc=FashionRun北京站美女招募,8月1日奥森公园不美不跑."><img src="${cdn:pic(pageContext.request)}/styles/shangpin/images/b_share.png" width="40"></a>
   </div>
</c:if> --%>
</rapid:override>
<rapid:override name="footer">
  
 </rapid:override>
<%@ include file="/WEB-INF/pages/common/common_mall_home_banner.jsp" %> 