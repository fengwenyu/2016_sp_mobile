<%@ page language="java" contentType="text/html; charset=UTF-8"
	trimDirectiveWhitespaces="true" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/pages/common/include.inc.jsp"%>

<rapid:override name="custum">
<script src="${cdn:js(pageContext.request)}/styles/shangpin/js/jquery.min.js${ver}"  type="text/javascript" charset="utf-8"></script>
<script src="${cdn:js(pageContext.request)}/styles/shangpin/js/lazyload.js${ver}"  type="text/javascript" charset="utf-8"></script>
<script src="${cdn:js(pageContext.request)}/styles/shangpin/js/xmas.dialogs.js${ver}"  type="text/javascript" charset="utf-8"></script>
<script src="${cdn:js(pageContext.request)}/styles/shangpin/js/config.sim.js${ver}"  type="text/javascript" charset="utf-8"></script>
<script src="${cdn:js(pageContext.request)}/styles/weixin/jweixin-1.0.0.js${ver}"  type="text/javascript" charset="utf-8"></script>
<script src="${cdn:js(pageContext.request)}/styles/shangpin/js/weixin/WXShareM.js${ver}"  type="text/javascript" charset="utf-8"></script>
<script src="${cdn:js(pageContext.request)}/styles/shangpin/js/meet/happyShare.js${ver}"  type="text/javascript" charset="utf-8"></script>
<link href="${cdn:css(pageContext.request)}/styles/shangpin/css/page/20150520HappyShare.css${ver}" rel="stylesheet" />
</rapid:override>

<rapid:override name="header">
	<c:if test="${!checkWX&&!checkAPP}">
		<%@ include file="/WEB-INF/pages/common/header.jsp"%>
	</c:if>
</rapid:override>
<rapid:override name="content">
	<div class="alContent">
	<h1 class="title-top">
    	<img src="${cdn:pic(pageContext.request)}/styles/shangpin/images/happyShare/top_title.png">
    </h1>
    <div class="turntable-top"><img src="${cdn:pic(pageContext.request)}/styles/shangpin/images/happyShare/turntable_bg_top.png" /></div>
	<div class="turntable">
    	<div class="rule-box">
        	<h2>-&nbsp;活动规则&nbsp;-</h2>
            <div class="rule-text">
            	<p>分享520活动页面到新浪微博，并@ 1位好友</p>
            </div>
        </div>
        <div class="rule-box ">
        	<h2>-&nbsp;活动奖品&nbsp;-</h2>
            <div class="rule-text prize-box">
                <span class="prize-pic"><img src="${cdn:pic(pageContext.request)}/styles/shangpin/images/happyShare/img01.jpg" /></span>
            	<p class="prize-text">
                （1）EOS全美热销润唇膏，每天随机抽取5位粉丝送出，共发放60个！<br>
                （2）500元现金券，每日1个，共6500元！
                </p>
            </div>
        </div>
        <div class="rule-box">
        	<h2>-&nbsp;活动时间&nbsp;-</h2>
            <div class="rule-text">
            	<p style="text-align:center;">5月13日－5月25日</p>
            </div>
        </div>
        <div class="rule-box">
        	<h2 class="long">-&nbsp;获奖名单公布&nbsp;-</h2>
            <div class="rule-text">
            	<p>5月15日、18日、20日、23日、25日各公布一次。</p>
            </div>
        </div>
    </div>
    <div class="con">
    	<div class="con-text">
        	<h3>关注尚品网微信额外获得30元现金券和520元礼券包</h3>
            <p>
              <strong>活动规则：</strong><br>
              1.	扫描二维码，关注尚品网官方微信；<br>
              2.	发送“520”给尚品网微信；<br>
              3.	你将得到30元现金券和520元礼券包的激活码，按照提示激活即可！
            </p>
        </div>
	     <input id="_iswx" name="_iswx" type="hidden" value="${checkWX }"/>
		<input type="hidden" name="_shareUrl"  id="_shareUrl"  value="http://m.shangpin.com/meet/212"/>
		<input type="hidden" name="_mainTitle"  id="_mainTitle"  value="尚品520,幸运大转盘,现金天天赚。"/>
		<input type="hidden" name="_mainDesc"  id="_mainDesc"  value="尚品网5周年店庆，百万潮流单品大促，低至1.52折！每日还有机会抽取惊喜大奖哦！"/>
		<input type="hidden" name="_mainImgUrl" id="_mainImgUrl"  value="${ctx }/styles/shangpin/images/520share2.jpg"/>
          <c:if test="${checkAPP||checkWX }">
       		 <a href='<c:if test="${checkWX }">javascript:;</c:if><c:if test="${checkAPP }">shangpinapp://phone.shangpin/actiongoshare?title=尚品520,幸运大转盘,现金天天赚。&desc=尚品网5周年店庆，百万潮流单品大促，低至1.52折！每日还有机会抽取惊喜大奖哦！&url=http://m.shangpin.com/meet/212</c:if>'>
         	<img src="${cdn:pic(pageContext.request)}/styles/shangpin/images/e.gif" lazy="${cdn:pic(pageContext.request)}/styles/shangpin/images/happyShare/ewm_img.jpg" />
         	<img class="share-icon"  src="${cdn:pic(pageContext.request)}/styles/shangpin/images/e.gif"  lazy="${cdn:pic(pageContext.request)}/styles/shangpin/images/happyShare/share_img.jpg">
        </a>
        </c:if>
        
        <a href="${ctx }/meet/212" class="back_main"><i><img src="${cdn:pic(pageContext.request)}/styles/shangpin/images/happyShare/back_icon.png" /></i>返回主会场</a>
    </div>
    <div class="share-tip"><img src="${cdn:pic(pageContext.request)}/styles/shangpin/images/happyShare/share_tip.png" /></div>
</div>
</rapid:override>

<rapid:override name="footer">
</rapid:override>
<%@ include file="/WEB-INF/pages/common/base_mall_banner.jsp" %> 
