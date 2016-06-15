<%@ page language="java" contentType="text/html; charset=UTF-8" trimDirectiveWhitespaces="true"
    pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/pages/common/include.inc.jsp"%>

<rapid:override name="custum">
	<link href="${cdn:css(pageContext.request) }/styles/shangpin/css/page/2015TOPSHOP0113.css${ver}" rel="stylesheet" />
	<script type="text/javascript" charset="utf-8">
		loader = SP.core
		.install("${cdn:js(pageContext.request)}/styles/shangpin/js/jquery.min.js${ver}")
		.using("${cdn:js(pageContext.request)}/styles/shangpin/js/lazyload.js${ver}")
		.excute()
		.using("${cdn:js(pageContext.request)}/styles/shangpin/js/config.sim.js${ver}")
		.excute();
	</script>
</rapid:override>
<rapid:override name="header">
	<c:if test="${!checkWX&&!checkAPP}">
		<div class="topFix">
			<section>
				<div class="topBack">
					<a href="javascript:history.go(-1);" class="backBtn">&nbsp;</a> <span>
						TOPSHOP
					</span>

					<ul class="alUser_icon clr"></ul>
				</div>
			</section>
		</div>
	</c:if>
</rapid:override>

<rapid:override name="content">
	<c:choose>
			<c:when test="${!checkWX&&!checkAPP}">
				<div class="alContent">
			</c:when>
			<c:otherwise>
				<div class="alContent" style="margin-top: 0;">
			</c:otherwise>
		</c:choose>
		<div class="main_box">
	      <p class="top_img"><a href="#"><img src="${cdn:pic(pageContext.request)}/styles/shangpin/images/2015TOPSHOP0113/topshop01.jpg" /></a></p>
	      
	      <!-- 引领全球时尚50年 -->
	      <h2 class="line2-h2"><a class="t_title" href="javascript:;">引领全球时尚50年</a><span>THOPSHOP</span><em></em></h2>
	      <p class="t_img"><a href="#"><img src="${cdn:pic(pageContext.request)}/styles/shangpin/images/2015TOPSHOP0113/topshop02.jpg" /></a></p>
	      <h3>品牌介绍</h3>
	      <p class="t_text">Topshop是一个快时尚品牌的商业奇迹，Topshop引导的高街时尚从英国红到欧美再到亚洲，中国也有相当多的Topshop粉丝。Topshop1964年成立于英国伦敦，当时还只在Sheffield百货公司中间有一个Peter Robinson的专柜，一年后Peter Robinson百货公司给了Topshop地下层很多的空间。</p>
	      <p class="t_text">1974年，Topshop从Sheffield百货公司退出来，成为独立零售商，当JaneShepherdson在1999年成为Topshop品牌总监时，她的愿望是把Topshop品牌建立成权威时尚品牌。</p>
	      <p class="t_text">从那时起Topshop就逐渐成为时尚界一个成功故事。目前，Topshop在全球拥有500家时尚分店，包括在世界上最大的时尚店，在伦敦Oxford Circus的一家Topshop百货商店，每个星期吸引着20万的消费者去光顾。  (视频为TOPSHOP在新加坡开店盛况）</p>
	
	      <h2 class="line2-h2"><a class="t_title t_title1" href="javascript:;">目前为止全球最大的TOPSHOP旗舰店</a><em></em></h2>
	      <p class="t_img"><a href="#"><img src="${cdn:pic(pageContext.request)}/styles/shangpin/images/e.gif" lazy="${cdn:pic(pageContext.request)}/styles/shangpin/images/2015TOPSHOP0113/topshop03.jpg" /></a></p>
	      <p class="t_text">位于英国伦敦市中心黄金地段的牛津街上，面积达9万平方英尺，共有5个楼层，设有超过200个更衣室，店内1000名员工每周为20万多购物者提供服务</p>
	      
	      <!-- 产品线 -->
	      <h2 class="line_h2"><a class="t_title" href="javascript:;">产品线</a></h2>
	      <p class="t_text">每周，Topshop都有万件新品摆上货架。从大衣到短装再到各种各样的配件、包、首饰、内衣、化妆品甚至玩具，现在也可以跟随线上的Topshop体验这一切！从基本款式到高端潮流，符合各种不同体型、价位，以及每季的时尚明星，设计师的限量款式等诸多选择，为时尚女性提供了TOTAL LOOK的造型需求。 Topshop的价格区间都是几百元到千元不等。</p>
	      <p class="t_img"><a href="#"><img src="${cdn:pic(pageContext.request)}/styles/shangpin/images/e.gif" lazy="${cdn:pic(pageContext.request)}/styles/shangpin/images/2015TOPSHOP0113/topshop04.jpg" /></a></p>
	      <p class="t_img" style="border-top:1px solid #000"><a href="#"><img src="${cdn:pic(pageContext.request)}/styles/shangpin/images/e.gif" lazy="${cdn:pic(pageContext.request)}/styles/shangpin/images/2015TOPSHOP0113/topshop05.jpg" /></a></p>
	      
	      
	      <!-- 明星代言 -->
	      <h2 class="line_h2"><a class="t_title" href="javascript:;">明星代言</a></h2>
	      <ul class="prod_list clr">
	          <li><a href="#"><img src="${cdn:pic(pageContext.request)}/styles/shangpin/images/e.gif" lazy="${cdn:pic(pageContext.request)}/styles/shangpin/images/2015TOPSHOP0113/topshop06.jpg" /><span class="li_text"><em>Kate Moss</em><b>至于这两位合作的故事，那就是一场好戏。据说当时是Kate Moss主动找TOPSHOP的老板，表示我们都来自克罗伊登，我们应该一起做生意。</b></span></a></li>
	          <li><a href="#"><img src="${cdn:pic(pageContext.request)}/styles/shangpin/images/e.gif" lazy="${cdn:pic(pageContext.request)}/styles/shangpin/images/2015TOPSHOP0113/topshop07.jpg" /><span class="li_text"><em>Cara Delevingne</em><b>2014年7月28日，英国伦敦， TOPSHOP宣布超模卡拉·迪瓦伊（Cara Delevingne）成全其全新代言人。</b></span></a></li>
	      </ul>
	      
	      <!-- 品牌大事记 -->
	      <h2 class="line_h2"><a class="t_title" href="javascript:;">品牌大事记</a></h2>
	      <div class="date_box">
	      	<h4 class="date_title">2014年9月</h4>
	        <div class="data_event">
	        	<em class="cirle_icon"><img src="${cdn:pic(pageContext.request)}/styles/shangpin/images/2015TOPSHOP0113/topshop_cirle.jpg" /></em>
	        	<h5>终于进军中国大陆，尚品网获官方授权！</h5>
	            <p>再也不受任何地域限制、时间限制，货品更新速度最快。</p>
	            <a href="#"><img src="${cdn:pic(pageContext.request)}/styles/shangpin/images/e.gif" lazy="${cdn:pic(pageContext.request)}/styles/shangpin/images/2015TOPSHOP0113/topshop08.jpg" /></a>
	        </div>
	        <h4 class="date_title">2014年7月</h4>
	        <div class="data_event">
	        	<em class="cirle_icon"><img src="${cdn:pic(pageContext.request)}/styles/shangpin/images/2015TOPSHOP0113/topshop_cirle.jpg" /></em>
	        	<h5>卡拉代言Topshop 2014秋冬广告大片</h5>
	            <p>卡拉·迪瓦伊(Cara Delevingne)呈现出一个真正的TOPSHOP Girl，每件单品都穿出了属于自己的风格。</p>
	            <a href="#"><img src="${cdn:pic(pageContext.request)}/styles/shangpin/images/e.gif" lazy="${cdn:pic(pageContext.request)}/styles/shangpin/images/2015TOPSHOP0113/topshop09.jpg" /></a>
	        </div>
	        <h4 class="date_title">2014年4月</h4>
	        <div class="data_event">
	        	<em class="cirle_icon"><img src="${cdn:pic(pageContext.request)}/styles/shangpin/images/2015TOPSHOP0113/topshop_cirle.jpg" /></em>
	        	<h5>TOPSHOP与Kate Moss</h5>
	            <p>联名系列上市再度引发哄抢，这是他们合作的第6次</p>
	            <a href="#"><img src="${cdn:pic(pageContext.request)}/styles/shangpin/images/e.gif" lazy="${cdn:pic(pageContext.request)}/styles/shangpin/images/2015TOPSHOP0113/topshop10.jpg" /></a>
	        </div>
	        <h4 class="date_title">2013年6月</h4>
	        <div class="data_event">
	        	<em class="cirle_icon"><img src="${cdn:pic(pageContext.request)}/styles/shangpin/images/2015TOPSHOP0113/topshop_cirle.jpg" /></em>
	        	<h5>进驻香港中环皇后大道旗舰店</h5>
	            <a href="#"><img src="${cdn:pic(pageContext.request)}/styles/shangpin/images/e.gif" lazy="${cdn:pic(pageContext.request)}/styles/shangpin/images/2015TOPSHOP0113/topshop11.jpg" /></a>
	        </div>
	        <h4 class="date_title">2012年6月</h4>
	        <div class="data_event">
	        	<em class="cirle_icon"><img src="${cdn:pic(pageContext.request)}/styles/shangpin/images/2015TOPSHOP0113/topshop_cirle.jpg" /></em>
	        	<h5>TOPSHOP邀请英国设计师理查·尼考尔 (Richard Nicoll) 操刀，推出首个婚纱礼服系列</h5>
	            <a href="#"><img src="${cdn:pic(pageContext.request)}/styles/shangpin/images/e.gif" lazy="${cdn:pic(pageContext.request)}/styles/shangpin/images/2015TOPSHOP0113/topshop12.jpg" /></a>
	        </div>
	        <h4 class="date_title">2009年4月</h4>
	        <div class="data_event">
	        	<em class="cirle_icon"><img src="${cdn:pic(pageContext.request)}/styles/shangpin/images/2015TOPSHOP0113/topshop_cirle.jpg" /></em>
	        	<h5>正式进驻美国纽约，Kate Moss亲自主持开幕礼</h5>
	            <a href="#"><img src="${cdn:pic(pageContext.request)}/styles/shangpin/images/e.gif" lazy="${cdn:pic(pageContext.request)}/styles/shangpin/images/2015TOPSHOP0113/topshop13.jpg" /></a>
	        </div>
	        <h4 class="date_title">2006年9月</h4>
	        <div class="data_event">
	        	<em class="cirle_icon"><img src="${cdn:pic(pageContext.request)}/styles/shangpin/images/2015TOPSHOP0113/topshop_cirle.jpg" /></em>
	        	<h5>Kate Moss 与Topshop合作的第一个系列并对外正式宣布次年5月开售</h5>
	            <a href="#"><img src="${cdn:pic(pageContext.request)}/styles/shangpin/images/e.gif" lazy="${cdn:pic(pageContext.request)}/styles/shangpin/images/2015TOPSHOP0113/topshop14.jpg" /></a>
	        </div>
	        <h4 class="date_title">2005年9月</h4>
	        <div class="data_event">
	        	<em class="cirle_icon"><img src="${cdn:pic(pageContext.request)}/styles/shangpin/images/2015TOPSHOP0113/topshop_cirle.jpg" /></em>
	        	<h5>TOPSHOP 高端产品线 Topshop Unique亮相国际时装周</h5>
	            <a href="#"><img src="${cdn:pic(pageContext.request)}/styles/shangpin/images/e.gif" lazy="${cdn:pic(pageContext.request)}/styles/shangpin/images/2015TOPSHOP0113/topshop15.jpg" /></a>
	        </div>
	        <h4 class="date_title">1974年</h4>
	        <div class="data_event">
	        	<em class="cirle_icon"><img src="${cdn:pic(pageContext.request)}/styles/shangpin/images/2015TOPSHOP0113/topshop_cirle.jpg" /></em>
	        	<h5>TOPSHOP从Sheffield百货公司退出来，成为独立零售商</h5>
	            <a href="#"><img src="${cdn:pic(pageContext.request)}/styles/shangpin/images/e.gif" lazy="${cdn:pic(pageContext.request)}/styles/shangpin/images/2015TOPSHOP0113/topshop16.jpg" /></a>
	        </div>
	        <h4 class="date_title">1964年</h4>
	        <div class="data_event">
	        	<em class="cirle_icon"><img src="${cdn:pic(pageContext.request)}/styles/shangpin/images/2015TOPSHOP0113/topshop_cirle.jpg" /></em>
	        	<h5>TOPSHOP英国伦敦诞生</h5>
	            <a href="#"><img src="${cdn:pic(pageContext.request)}/styles/shangpin/images/e.gif" lazy="${cdn:pic(pageContext.request)}/styles/shangpin/images/2015TOPSHOP0113/topshop17.jpg" /></a>
	        </div>
	      </div>
	    </div>
	</div>
</rapid:override>
<rapid:override name="footer">
  	
 </rapid:override>
<%@ include file="/WEB-INF/pages/common/another_base.jsp" %> 