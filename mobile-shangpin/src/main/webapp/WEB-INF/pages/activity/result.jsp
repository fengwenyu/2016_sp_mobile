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
	/*领取成功*/
	.sucess_content{ overflow:hidden;}
	.sucess_content h3,.sucess_content h4{ text-align:center;}
	.sucess_content h3{ font-size:24px; margin-top:20%;}
	.sucess_content h4{ font-size:26px; padding-top:5px;}
	.sucess_content p{ font-size:12px; line-height:22px; text-align:center; margin-top:25px;}
	.sucess_content .f_red{ color:#b63c39;}
	.sucess_content .href_box{ width:100%; text-align:center; padding-top:28px;}
	.sucess_content .href_btn{ display:inline-block; border-radius:5px; height:35px; line-height:35px; font-size:12px; background:#ff7358; padding:0 25px; text-align:center; color:#fff;}
	.sucess_content .icon_img{ margin-top:13.2%; display:block;}
	
	@media screen and (min-width: 375px) {
	.sucess_content .icon_img{ margin-top:20%;}
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
  <section class="main">
  	<div class="sucess_content">
    	<h3>您的抽奖号码为:</h3>
        <h4>${code }</h4>
        <c:choose>
        	<c:when test="${isEnd=='1' }">
        		<p>本次中奖号码已公布<br />
        		您可以返回到首页查看<br /></p>
        	</c:when>
        	<c:otherwise>
		        <p>本次中奖号码将于4月02日上午10:00公布<br />
					中奖后我们将与您核对信息，建议您截屏保存<br />
					<em class="f_red">50元现金红包已充值到您的尚品网账户</em>
		        </p>
        	</c:otherwise>
        </c:choose>
        <div class="href_box"><a href="http://m.shangpin.com" class="href_btn" >点击使用红包</a></div>
        <i class="icon_img"><img src="${cdn:pic(pageContext.request)}/styles/shangpin/images/nmb_activie/sccess_img01.jpg" /></i>
    </div>
  </section>
  <!--内容区域 start-->

  
</div>

</rapid:override>
<rapid:override name="static_file">
    <script src="${cdn:js(pageContext.request)}/styles/weixin/jweixin-1.0.0.js${ver}" type="text/javascript" charset="utf-8"></script>
	<script src="${cdn:js(pageContext.request)}/styles/shangpin/js/star-packet/20151020/jquery.min.js${ver}" type="text/javascript" charset="utf-8"></script>
	<script src="${cdn:js(pageContext.request)}/styles/shangpin/js/star-packet/20151020/weixin_ready.js${ver}" type="text/javascript" charset="utf-8"></script>
</rapid:override>
<%@ include file="/WEB-INF/pages/activity/datail_base.jsp" %> 

