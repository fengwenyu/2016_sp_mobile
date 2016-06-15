<%@ page language="java" contentType="text/html; charset=UTF-8" trimDirectiveWhitespaces="true"
    pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/pages/common/include.inc.jsp"%>

<rapid:override name="title">
	杨幂生日发红包,快抢！
</rapid:override>
<rapid:override name="custum">
	<link href="${cdn:css(pageContext.request)}/styles/shangpin/css/star-packet/base.css${ver}" rel="stylesheet" />
	<link href="${cdn:css(pageContext.request)}/styles/shangpin/css/star-packet/red_packet.css${ver}" rel="stylesheet" />
	
</rapid:override>

<rapid:override name="page_title">
	杨幂生日发红包,快抢！
</rapid:override>

<rapid:override name="content">
	<input type="hidden" id="batchNo" name="batchNo" value="${batchNo}"/>
	<input type="hidden" id="phoneNum" name="phoneNum" value="${phoneNum}"/>
	<input type="hidden" id="amount" name="amount" value="${amount}"/>
	<input type="hidden" id="friend" name="friend" value="friend"/>
	<input type="hidden" name="_shareUrl"  id="_shareUrl"  value="http://m.shangpin.com/star/packet?batchNo=${batchNo}"/>
	<input type="hidden" name="_mainTitle"  id="_mainTitle"  value="杨幂发价值5000万红包，抢疯了！"/>
	<input type="hidden" name="_mainDesc"  id="_mainDesc"  value="杨幂生日发红包，见者有份！"/>
	<input type="hidden" name="_mainImgUrl" id="_mainImgUrl"  value="${cdn:pic(pageContext.request)}/styles/shangpin/images/star-packet/weixin_share.jpg"/>
	
	<section class="main">
	  	<div class="red-coupon">
	    	<h3>恭喜你获得</h3>
	        <img src="${cdn:pic(pageContext.request)}/styles/shangpin/images/star-packet/${amount}-1.png" >
	        <img src="${cdn:pic(pageContext.request)}/styles/shangpin/images/star-packet/banner02.png" >
	        <p>红包已充值到您的尚品网账户 <strong>${fn:substring(phoneNum,0,3)}****${fn:substring(phoneNum,7,11)}</strong></p>
	    </div>
	    <div class="surprise">
	    	<h3><strong>惊喜一：</strong>再领一个，转发就能有！</h3>
	        <div class="red-desc-box clr">
	        	<i><img src="${cdn:pic(pageContext.request)}/styles/shangpin/images/star-packet/${amount}.png" ></i>
	            <p>点击右侧分享好友，就能再领一个现金红包！</p>
	            <a class="share-btn">告诉好友抢红包</a>
	        </div>
	    </div>
	    <a class="download-btn" href="${ctx}/download">下载尚品网APP使用红包</a>
	    <a class="rule-btn" href="javascript:;">红包使用说明</a>
	    <div class="surprise">
	    	<h3><strong>惊喜二：</strong>关注尚品网官方微信领取千元大礼包</h3>
	        <img src="${cdn:pic(pageContext.request)}/styles/shangpin/images/star-packet/ewm.jpg" >
	    </div>
	    <div class="img-box">
	    	<a href="http://m.shangpin.com/brand/product/list?brandNo=B1885"><img src="${cdn:pic(pageContext.request)}/styles/shangpin/images/star-packet/img01.jpg" ></a>
	        <a href="http://m.shangpin.com/meet/319"><img src="${cdn:pic(pageContext.request)}/styles/shangpin/images/star-packet/img02.jpg" ></a>
	        <a href="http://m.shangpin.com/subject/product/list?topicId=50908630"><img src="${cdn:pic(pageContext.request)}/styles/shangpin/images/star-packet/img03.jpg" ></a>
	    </div>
	    <a class="dial_btn" href="tel:4006-900-900">客服电话：4006-900-900</a>
	  </section>
	  <div class="red-use-info-box">
	    <div class="red-use-info">
	      <a class="close-btn" href="javascript:;"></a>
	      <h3>活动规则及红包使用说明</h3>
	      <p> 
	          1.红包有效期至2015年09月30日24:00前。<br>
	          2.红包只能用于购买尚品网TOPSHOP品牌商品。<br>
	          3.红包面额50元（满150元使用）、100元（满300元使用）、200元（满500元使用）、500元（满1200元使用）、1000元（满2000元使用）。<br>
	          4.所得红包会充值到你的尚品网账号${fn:substring(phoneNum,0,3)}****${fn:substring(phoneNum,7,11)}中，登录尚品网或下载尚品网APP“个人中心-优惠券”查看所得红包。<br>
	          5.红包不能叠加使用，一个订单只能使用1个红包，单笔订单内商品数量不限。<br>
	          6.红包不可转让、不可提现、不可拆分使用、不可找零、不可冲抵运费金额。<br>
	          7.红包仅限在尚品网站与尚品网APP官方网站使用。<br>
	          8.如使用中有任何问题，在微信公众号中搜索“尚品网”（微信号:shangpin_aolai）并关注，进入公众号在“我的尚品-在线客服”资讯反馈，同时在“我的尚品-我的优惠券”中查看您的优惠券信息。<br>
	          9.消费者使用红包购物后，因各种原因造成退货，退款将扣除红包满减金额，满减金额将不作为退款。<br>
	          10.红包最终解释权在法律规定的范围内归尚品网所有。<br>
	       </p>
	    </div>
	  </div>
	  <!--分享弹层${ctx}/star/had?batchNo=${batchNo}&phoneNum=${phoneNum}-->
	  <div class="share-box">
	  	<img src="${cdn:pic(pageContext.request)}/styles/shangpin/images/star-packet/share.png" >
	    <a href="#" class="know-btn">知道了</a>
	  </div>
</rapid:override>

<rapid:override name="static_file">
	<script src="${cdn:js(pageContext.request)}/styles/weixin/jweixin-1.0.0.js${ver}" type="text/javascript" charset="utf-8"></script>
	<script src="${cdn:js(pageContext.request)}/styles/shangpin/js/star-packet/weixin_ready.js${ver}" type="text/javascript" charset="utf-8"></script>
	<script src="${cdn:js(pageContext.request)}/styles/shangpin/js/star-packet/red_packet.js${ver}" type="text/javascript" charset="utf-8"></script>
</rapid:override>

<%@ include file="/WEB-INF/pages/common/star_packet_base.jsp" %> 