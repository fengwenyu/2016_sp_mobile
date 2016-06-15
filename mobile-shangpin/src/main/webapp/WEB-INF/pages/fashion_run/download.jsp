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
<style>
html,body{height:100%; overflow:hidden; }
body{background-color:#000;}
.container{ min-width:320px; max-width:640px; margin:0 auto; height:100%;}
.topFix{position:fixed; top:0; z-index:10;min-width:320px; max-width:640px;height:45px;}
.topFix section { position:static;}
body .alContent {line-height:20px; /*height:-webkit-calc(100% - 45px);*/ margin-top:0; height:100%;}
.top_banner{ position:relative; width:100%; height:100%; background:url(../styles/shangpin/images/FashionRun/index_download_color_bg.jpg) no-repeat left center; background-size:100%;}
.top_banner img{ width:100%; position:absolute; left:0; bottom:130px;}
.down_load_con{ width:100%; max-width:640px; position:fixed; bottom:0; z-index:10;background:rgba(0,0,0,.5);}
.down_load_nav{ padding:0 15px 35px; }
.down_load_nav a{ text-align:center; float:left;  width:48%; color:#c62026; display:block; }
.down_load_nav a:last-child{ float:right;}
.down_load_nav .android_bg{ text-align:right;}
.down_load_nav .apple_bg{ text-align:left;}
.p_text{ color:#f7ffff; font-size:14px; text-align:center; padding:15px 0 12px;}
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
 	<input type="hidden" name="_shareUrl"  id="_shareUrl"  value="${basePath }/fashionrun/download"/>
	<input type="hidden" name="_mainTitle"  id="_mainTitle"  value="FashionRun北京站美女招募,8月1日奥森公园不美不跑."/>
	<input type="hidden" name="_mainDesc"  id="_mainDesc"  value="FashionRun北京站美女招募,8月1日奥森公园不美不跑."/>
	<input type="hidden" name="_mainImgUrl" id="_mainImgUrl"  value="${basePath }/styles/shangpin/images/FashionRun/fashion-run.jpg"/>
      <!-- 头图 --> 
      <p class="top_banner"><img src="${ctx }/styles/shangpin/images/FashionRun/index_download_color.png" /></p>
      <!-- download --> 
      <div class="down_load_con">
      	  <p class="p_text">时尚装备领取,请下载"尚品网"APP</p>
          <p class="down_load_nav clr">
            <a href="javascript:download()" class="android_bg"><img src="${ctx }/styles/shangpin/images/FashionRun/android_imgcolor_btn.png" /></a>
            <a href="javascript:download()" class="apple_bg"><img src="${ctx }/styles/shangpin/images/FashionRun/ios_imgcolor_bg.png" /></a>
          </p>
      </div>
 	<!--内容部分end-->
<%--  <c:if test="${checkAPP}">
  <div class="shang_share"> 
         <a href="shangpinapp://phone.shangpin/actiongoshare?title=FashionRun北京站美女招募,8月1日奥森公园不美不跑.&url=${basePath }/fashionrun/download&desc=FashionRun北京站美女招募,8月1日奥森公园不美不跑."><img src="${cdn:pic(pageContext.request)}/styles/shangpin/images/b_share.png" width="40"></a>
   </div>
</c:if> --%>
</rapid:override>
 <rapid:override name="footer">
  
 </rapid:override> 
<%@ include file="/WEB-INF/pages/common/common_mall_home_banner.jsp" %> 
