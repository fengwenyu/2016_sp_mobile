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
	.using("${cdn:js(pageContext.request)}/styles/weixin/jweixin-1.0.0.js${ver}")
	.using("${cdn:js(pageContext.request)}/styles/shangpin/js/weixin/WXShareM.js${ver}")
      
      
</script>
<style>
body{background-color:#000;}
.container{ min-width:320px; max-width:640px; margin:0 auto; color:#fafafa;}
.topFix{position:fixed; top:0; z-index:10;min-width:320px; max-width:640px; height:45px;}
body .alContent {line-height:20px;padding-bottom:10px; }
.topFix section { position:static;}
.receive_con{ width:100%; text-align:center;}
.receive_con span{ font-size:26px; }
.receive_con i{ width:100px; height:105px; margin: 10% auto 10px; display:block;}
.receive_con i img{ max-width:100%;}

.receive_box{width:100%; }
.receive_text{ padding:0 30px; margin:10px 0 23px 0; font-size:14px;}
.receive_text a{ color:#fff; margin:10px 0 23px 0; font-size:14px;}
.user_info{ padding:0 30px;font-size:14px;}
.user_info dt,.user_info dd{ float:left;line-height:24px;}
.user_info dd{}
.user_info p{ padding:0; }
.len_s{ position:relative;}
.phone{  
  width: 145px;
  margin: 70px auto 20px;
  font-size: 16px;
  font-weight: bold;
  color: #000;
  height: 30px;
  display:block;
}
.service-text{text-align:center; color:#fff;margin: 70px auto 20px;}
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
		<input type="hidden" name="_shareUrl"  id="_shareUrl"  value="${basePath }/fashionrun/index"/>
		<input type="hidden" name="_mainTitle"  id="_mainTitle"  value="FashionRun北京站美女招募,8月1日奥森公园不美不跑."/>
		<input type="hidden" name="_mainDesc"  id="_mainDesc"  value="FashionRun北京站美女招募,8月1日奥森公园不美不跑."/>
		<input type="hidden" name="_mainImgUrl" id="_mainImgUrl"  value="${basePath }/styles/shangpin/images/FashionRun/fashion-run.jpg"/>
	<!--内容部分-->
        <img src="${ctx }/styles/shangpin/images/FashionRun/form_banner.png" alt="">     
		<p class="receive_con" style="padding-top:10px; line-height:120px; font-size:30px; font-weight:700;">
        	<%-- <i><img src="${ctx }/styles/shangpin/images/FashionRun/success_enroll_img.png" /></i> --%>
        	<span>领取成功！</span>            
        </p>
        <div class="receive_box"> 
        <p class="receive_text">7月26日由首席赞助商尚品网发货<br />预计2-3天送到您手中<br />如有问题请联系<a href="mailto:info@fashionrun.org?subject=&body=" >info@fashionrun.org</a></p>  
            <dl class="user_info clr">
            	<dt>收货人：</dt>
                <dd>${fashionOrder.name }</dd>
            </dl>
            <dl class="user_info clr">
            	<dt>电&#12288;话：</dt>
                <dd>${fashionOrder.phone }</dd>
            </dl>
            <dl class="user_info len_s clr">
            	<dt style="position:absolute;">地&#12288;址：</dt>
                <dd style="margin-left:56px;">${fashionOrder.province }${fashionOrder.city }${fashionOrder.area }${fashionOrder.town }${fashionOrder.addr }&nbsp;</dd>
            </dl>
          
		</div>
 	<!--内容部分end-->
</rapid:override>
<rapid:override name="footer">
  
 </rapid:override>
<%@ include file="/WEB-INF/pages/common/common_mall_home_banner.jsp" %> 
