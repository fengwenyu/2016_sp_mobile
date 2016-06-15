<%@ page language="java" contentType="text/html; charset=UTF-8" trimDirectiveWhitespaces="true"
    pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/pages/common/include.inc.jsp"%>

<rapid:override name="title">
  adidas NMD跑鞋，共六款配色0元赠！
</rapid:override>
<rapid:override name="custum">
	<link href="${cdn:css(pageContext.request)}/styles/shangpin/css/star-packet/base.css${ver}" rel="stylesheet" />
    <link href="${cdn:css(pageContext.request)}/styles/shangpin/css/nmb_activie/packet.css${ver}"	rel="stylesheet" />
	<style>
	/*中奖名单*/
	.prize_content{ position:absolute; top:0; left:0; bottom:0; right:0; z-index:1; padding-top:40px; box-sizing:content-box; background:url(../styles/shangpin/images/nmb_activie/prize_img_bg2.png) 0 bottom no-repeat; background-size:contain; text-align:center;}
	.prize_content h2{ font-size:14px; font-weight:bold; color:#313131; /*margin-top:25;*/}
	.prize_content h2,.prize_content h3,.prize_content h4{ text-align:center;}
	.prize_content h3{ font-size:14px; margin-top:12px; font-weight:bold; color:#313131;}
	.prize_content h4{ font-size:14px; padding-top:5px; font-weight:normal;}
	.prize_content p{ font-size:12px; color:#000; line-height:20px; text-align:center;}
	.prize_content p b{ font-weight:bold;}
	.prize_content .href_box{ width:100%; text-align:center;padding-top:15px;}
	.prize_content .href_btn{ display:inline-block; border-radius:5px; height:35px; line-height:35px; font-size:12px; background:#ff7358; padding:0 25px; text-align:center; color:#fff;}
	.prize_content .list{ display:inline-block; margin:5px auto;}
	.list li{ border:1px solid #666; font-size:13px; color:#000; padding:2px 25px; text-align:center;}
	.list li:first-child{ border-bottom:none;}
	@media screen and (min-width: 375px) {
	/*.prize_content h3{ margin-top:20%;}*/
	}
	</style>
</rapid:override>
<rapid:override name="content">
 <input type="hidden" name="_shareUrl"  id="_shareUrl"  value="http://m.shangpin.com/activity/index"/>
 <input type="hidden" name="_currentUrl" id="_currentUrl"  value=""/>
 <input type="hidden" name="_mainTitle"  id="_mainTitle"  value="adidas NMD跑鞋，共六款配色0元赠！"/>
 <input type="hidden" name="_mainDesc"  id="_mainDesc"  value="排队都买不到的全宇宙最夯NMD，尚品网任性0元送！另有50元现金红包海量发放~"/>
 <input type="hidden" name="_mainImgUrl" id="_mainImgUrl"  value="${cdn:pic(pageContext.request)}/styles/shangpin/images/nmb_activie/nbd.jpg"/>

<div class="wapper">
 
  <!--内容区域 start-->
  <div class="prize_content">
  	  <h2>本次中奖号码</h2>
  	  <c:choose>
  	  	<c:when test="${isEnd == '1'}">
  	  		 <ul class="list">
		      	<li>614952  曲先生</li>
		        <li>131613  李先生</li>
		      </ul>
		      <p>奖品将于两周内寄出，颜色随机发放<br /></p>
  	  	</c:when>
  	  	<c:otherwise>
  	  		<ul class="list">
		      	<li>800101  何女士</li>
		        <li>988856 魏先生</li>
		    </ul>
		    <p>奖品将于两周内寄出，颜色随机发放<br />
				下次中奖号码公布时间<b>4月02日10:00</b></p>
  	  	</c:otherwise>
  	  </c:choose>
      <h3>没中奖？</h3>
      <h4>你还可以去微博抽奖碰碰运气！</h4>
      <div class="href_box"><a href="http://weibo.com/u/1901336503" class="href_btn" >查看微博</a></div>
  </div>
  <!--内容区域 start-->

  
</div>

</rapid:override>
<rapid:override name="static_file">
    <script src="${cdn:js(pageContext.request)}/styles/weixin/jweixin-1.0.0.js${ver}" type="text/javascript" charset="utf-8"></script>
	<script src="${cdn:js(pageContext.request)}/styles/shangpin/js/star-packet/20151020/jquery.min.js${ver}" type="text/javascript" charset="utf-8"></script>
	<script src="${cdn:js(pageContext.request)}/styles/shangpin/js/star-packet/20151020/weixin_ready.js${ver}" type="text/javascript" charset="utf-8"></script>
</rapid:override>
<%@ include file="/WEB-INF/pages/activity/datail_base.jsp" %> 

