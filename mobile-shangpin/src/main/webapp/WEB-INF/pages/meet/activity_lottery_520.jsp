<%@ page language="java" contentType="text/html; charset=UTF-8"
	trimDirectiveWhitespaces="true" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/pages/common/include.inc.jsp"%>
<rapid:override name="custum">
	<link href="${cdn:css(pageContext.request)}/styles/shangpin/css/page/meet/goodLuck520/20150520GoodLuck.css${ver}" rel="stylesheet" />
	<script src="${cdn:js(pageContext.request)}/styles/shangpin/js/jquery.min.js${ver}"  type="text/javascript" charset="utf-8"></script>
	<script src="${cdn:js(pageContext.request)}/styles/shangpin/js/lazyload.js${ver}"  type="text/javascript" charset="utf-8"></script>
	<script src="${cdn:js(pageContext.request)}/styles/shangpin/js/jquery.easing.min.js${ver}"  type="text/javascript" charset="utf-8"></script>
	<script src="${cdn:js(pageContext.request)}/styles/shangpin/js/jQueryRotate.2.2.js${ver}"  type="text/javascript" charset="utf-8"></script>
	<script src="${cdn:js(pageContext.request)}/styles/shangpin/js/xmas.dialogs.js${ver}"  type="text/javascript" charset="utf-8"></script>
	<script src="${cdn:js(pageContext.request)}/styles/shangpin/js/config.sim.js${ver}"  type="text/javascript" charset="utf-8"></script>
	<script src="${cdn:js(pageContext.request)}/styles/weixin/jweixin-1.0.0.js${ver}"  type="text/javascript" charset="utf-8"></script>
	<script src="${cdn:js(pageContext.request)}/styles/shangpin/js/weixin/WXShareM.js${ver}"  type="text/javascript" charset="utf-8"></script>
	<script src="${cdn:js(pageContext.request)}/styles/shangpin/js/meet/goodLuck520/goodluck.js${ver}"  type="text/javascript" charset="utf-8"></script>

</rapid:override>
<%-- 浏览器标题 --%>
<rapid:override name="title">
	${meet.title }
</rapid:override>

<%-- 页标题 --%>
<rapid:override name="page_title">
	${meet.title }
</rapid:override>
<rapid:override name="header">
	<c:if test="${!checkWX&&!checkAPP}">
		<%@ include file="/WEB-INF/pages/common/header.jsp"%>
	</c:if>
</rapid:override>
<rapid:override name="content">
		<h1 class="title-top">
    	<i class="try-tag"><img src="${cdn:pic(pageContext.request)}/styles/shangpin/images/20150520GoodLuck/try_tag.png" /></i>
    	<img src="${cdn:pic(pageContext.request)}/styles/shangpin/images/20150520GoodLuck/top_title.png">
    </h1>
    <div class="turntable-top"><img src="${cdn:pic(pageContext.request)}/styles/shangpin/images/20150520GoodLuck/turntable_bg_top.png" /></div>
	<div class="turntable">
		<input type="hidden" name="_shareUrl"  id="_shareUrl"  value="http://m.shangpin.com/meet/212"/>
		<input type="hidden" name="_mainTitle"  id="_mainTitle"  value="尚品520,幸运大转盘,现金天天赚。"/>
		<input type="hidden" name="_mainDesc"  id="_mainDesc"  value="尚品网5周年店庆，幸运大抽奖，百万现金天天赚"/>
		<input type="hidden" name="_mainImgUrl" id="_mainImgUrl"  value="${ctx }/styles/shangpin/images/520share.gif"/>
    	
    	<c:if test="${checkAPP||checkWX }">
    		<a href='<c:if test="${checkWX }">javascript:;</c:if><c:if test="${checkAPP }">shangpinapp://phone.shangpin/actiongoshare?title=尚品520,幸运大转盘,现金天天赚。&desc=尚品网5周年店庆，幸运大抽奖，百万现金天天赚&url=http://m.shangpin.com/meet/212</c:if>' class="share-icon">
    			<img src="${cdn:pic(pageContext.request)}/styles/shangpin/images/20150520GoodLuck/share_btn.png" />
    		</a>
    	</c:if>
        <div class="turntable-box">
        	<img id="turntable_img" src="${cdn:pic(pageContext.request)}/styles/shangpin/images/20150520GoodLuck/turntable_img_03.png">
            <div id="turntable_btn"></div>
        </div>
    	
        
    </div>
    <div class="con">
        <h2><img src="${cdn:pic(pageContext.request)}/styles/shangpin/images/20150520GoodLuck/rule_title.png"></h2>
        <img src="${cdn:pic(pageContext.request)}/styles/shangpin/images/e.gif" lazy="${cdn:pic(pageContext.request)}/styles/shangpin/images/20150520GoodLuck/rule_pic01.png">
        <img src="${cdn:pic(pageContext.request)}/styles/shangpin/images/e.gif" lazy="${cdn:pic(pageContext.request)}/styles/shangpin/images/20150520GoodLuck/rule_pic02.png">
        <img src="${cdn:pic(pageContext.request)}/styles/shangpin/images/e.gif" lazy="${cdn:pic(pageContext.request)}/styles/shangpin/images/20150520GoodLuck/rule_pic03.png">
        <img src="${cdn:pic(pageContext.request)}/styles/shangpin/images/e.gif" lazy="${cdn:pic(pageContext.request)}/styles/shangpin/images/20150520GoodLuck/rule_pic04.png">
     	 <a href="${ctx }/coupon/list"><img src="${cdn:pic(pageContext.request)}/styles/shangpin/images/e.gif" lazy="${cdn:pic(pageContext.request)}/styles/shangpin/images/20150520GoodLuck/rule_pic05.png"></a>  
        <img src="${cdn:pic(pageContext.request)}/styles/shangpin/images/e.gif" lazy="${cdn:pic(pageContext.request)}/styles/shangpin/images/20150520GoodLuck/rule_pic06.png">
        <a href="${ctx }/meet/212" class="back_main"><i><img src="${cdn:pic(pageContext.request)}/styles/shangpin/images/20150520GoodLuck/back_icon.png" /></i>返回主会场</a>
        
    </div>
     <div class="share-tip"><img src="${cdn:pic(pageContext.request)}/styles/shangpin/images/happyShare/share_tip.png" /></div>
    
</rapid:override>
<rapid:override name="footer">
<input type="hidden" id="_isapp" name="_isapp" value="${checkAPP }" />
</rapid:override>
<%@ include file="/WEB-INF/pages/common/base_mall_banner.jsp" %> 
