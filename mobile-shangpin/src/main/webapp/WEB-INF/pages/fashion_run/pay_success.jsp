<%@ page language="java" contentType="text/html; charset=UTF-8" trimDirectiveWhitespaces="true"
    pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/pages/common/include.inc.jsp"%>

<link href="${cdn:css(pageContext.request)}/styles/shangpin/css/base.css${ver}" rel="stylesheet" />
<rapid:override name="custum">
	<script type="text/javascript" charset="utf-8">
    loader = SP.core
	.install("${cdn:css(pageContext.request)}/styles/shangpin/js/zepto.1.1.3.js${ver}")  //jquery库文件
  	.using("${cdn:css(pageContext.request)}/styles/shangpin/js/lazyload.js${ver}")
	.excute()
	.using("${cdn:css(pageContext.request)}/styles/shangpin/js/config.sim.js${ver}")
	.excute()
	.using("${cdn:css(pageContext.request)}/styles/shangpin/js/comm.js${ver}")
      
      
</script>
<style>
body{background-color:#000;}
.container{ min-width:320px; max-width:640px; margin:0 auto; color:#fafafa;}
.topFix{position:fixed; top:0; z-index:10;min-width:320px; max-width:640px; height:45px;}
.topFix section { position:static;}
body .alContent {line-height:20px;padding-bottom:10px; }
.receive_box{width:100%; }
.receive_box p{ padding:0 30px; font-size:14px;}
.receive_con{ text-align:center; }
.receive_con span{ font-size:26px;}
.receive_con i{ width:100px; height:105px; margin: 10% auto 10px; display:block;}
.receive_con i img{ max-width:100%;}
.receive_text{ padding:0 30px; margin:42px 0 31px 0; font-size:12px; text-align:center; line-height: 24px }
.receive_btn{ padding:0 30px;}
.receive_btn a{width:100%;font-size:16px;}
.abtn{ text-align:center; height:40px; line-height:40px; display:block;background:#fb0c60; border-radius:5px; color:#fff;}
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
	  <!--内容部分-->
    <img src="${ctx }/styles/shangpin/images/FashionRun/form_banner.png" alt="">
    <div class="receive_box">     
		<p class="receive_con" style="padding-top:10px; line-height:120px; font-size:30px; font-weight:700;">
        	<%-- <i><img src="${ctx }/styles/shangpin/images/FashionRun/success_enroll_img.png" /></i> --%>
        	<span>恭喜报名成功！</span>     
        	       
        </p>
		<p class="receive_text">时尚装备领取,请下载"尚品网"APP<br></p>
		<c:choose>
		<c:when test="${checkAPP }">
		  <p class="receive_btn"><a href="${ctx }/fashionrun/gift" class="abtn">领取时尚装备</a></p>
		</c:when>
		<c:otherwise>
		  <p class="receive_btn"><a href="${ctx }/fashionrun/download" class="abtn">领取时尚装备</a></p>
		</c:otherwise>
		</c:choose>
      
    </div>
 	<!--内容部分end-->
</rapid:override>
<rapid:override name="footer">
  
 </rapid:override>
<%@ include file="/WEB-INF/pages/common/common_mall_home_banner.jsp" %> 
