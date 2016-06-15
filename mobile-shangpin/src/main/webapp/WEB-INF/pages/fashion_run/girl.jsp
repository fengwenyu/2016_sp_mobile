<%@ page language="java" contentType="text/html; charset=UTF-8" trimDirectiveWhitespaces="true"
    pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/pages/common/include.inc.jsp"%>
<rapid:override name="custum">
	<link href="${cdn:css(pageContext.request)}/styles/shangpin/css/FashionRun/swiper.min.css${ver}" rel="stylesheet" />
	<link href="${cdn:css(pageContext.request)}/styles/shangpin/css/FashionRun/animate.min.css${ver}" rel="stylesheet" />
	<link href="${cdn:css(pageContext.request)}/styles/shangpin/css/FashionRun/style.css${ver}" rel="stylesheet" />
	
	<script type="text/javascript" charset="utf-8">
    loader = SP.core
      .install("${cdn:css(pageContext.request)}/styles/shangpin/js/jquery.min.js${ver}")  //jquery库文件
        .excute()
        .using("${cdn:js(pageContext.request)}/styles/weixin/jweixin-1.0.0.js${ver}")
		.using("${cdn:js(pageContext.request)}/styles/shangpin/js/weixin/WXShareM.js${ver}")
        .excute()
        .using("${cdn:js(pageContext.request)}/styles/shangpin/js/FashionRun/swiper.min.js${ver}")
	 	.using("${cdn:js(pageContext.request)}/styles/shangpin/js/FashionRun/swiper.animate.min.js${ver}")
	 	.using("${cdn:js(pageContext.request)}/styles/shangpin/js/FashionRun/music.js${ver}")
     	.excute();
      
	</script>
</rapid:override >
			
<%-- 浏览器标题 --%>
<rapid:override name="title">
	FashionRun
</rapid:override>

<rapid:override name="content">
	<input type="hidden" name="_shareUrl"  id="_shareUrl"  value="${basePath}/fashionrun/girl"/>
	<input type="hidden" name="_mainTitle"  id="_mainTitle"  value="FashionRun北京站美女招募,8月1日奥森公园不美不跑."/>
	<input type="hidden" name="_mainDesc"  id="_mainDesc"  value="FashionRun北京站美女招募,8月1日奥森公园不美不跑."/>
	<input type="hidden" name="_mainImgUrl" id="_mainImgUrl"  value="http://m.shangpin.com/styles/shangpin/images/FashionRun/fashion-run.jpg"/>
 	
 <div class="swiper-container swiper-container-v">
    <div class="swiper-wrapper">
    	<!-------------page1----------------->
        <section class="swiper-slide page1">
           <div class="ani resize" style="width:300px;height:60px;left:15px;top:250px;z-index:3;"swiper-animate-effect="bounceIn" swiper-animate-duration="2s" swiper-animate-delay="0s">
            	<img src="${ctx}/styles/shangpin/images/FashionRun/page1_2.png" />
            </div> 
        	<div class="ani resize" style="width:114px;height:29px;left:15px;top:315px;z-index:3;" swiper-animate-effect="fadeIn" swiper-animate-duration="2s" swiper-animate-delay="0.4s">
              <img src="${ctx}/styles/shangpin/images/FashionRun/page1_3.png"/>
            </div>
            <div class="ani resize" style="width:114px;height:34px;left:15px;top:345px;z-index:3;" swiper-animate-effect="fadeIn" swiper-animate-duration="2s" swiper-animate-delay="0.6s">
              <img src="${ctx}/styles/shangpin/images/FashionRun/page1_4.png"/> 
            </div>
            <div class="ani resize" style="width:114px;height:29px;left:15px;top:380px;z-index:3;" swiper-animate-effect="fadeIn" swiper-animate-duration="2s" swiper-animate-delay="0.8s">
              <img src="${ctx}/styles/shangpin/images/FashionRun/page1_5.png"/> 
            </div>
            <div id="array">
                <img src="${ctx}/styles/shangpin/images/FashionRun/arrow_up.png" width="25">
            </div>
        </section>
   		<!--------------page2-------------->   
        <section class="swiper-slide page2" >
            <div class="ani resize" style="width:198px;height:64px;left:60px;top:20px;z-index:3;"swiper-animate-effect="bounceIn" swiper-animate-duration="2s" swiper-animate-delay="0s">
            	<img src="${ctx}/styles/shangpin/images/FashionRun/page2_2.png" />
            </div> 
            <div class="ani resize" style="width:280px;height:20x;left:20px;top:90px;z-index:3;" swiper-animate-effect="fadeIn" swiper-animate-duration="2s" swiper-animate-delay="0.3s">
              <img src="${ctx}/styles/shangpin/images/FashionRun/page2_8.png"/> 
            </div>
        	<div class="ani resize" style="width:144px;height:90px;left:10px;top:150px;z-index:3;" swiper-animate-effect="fadeIn" swiper-animate-duration="2s" swiper-animate-delay="0.5s">
              <img src="${ctx}/styles/shangpin/images/FashionRun/page2_3.png"/>
            </div>
            <div class="ani resize" style="width:144px;height:138px;right:10px;top:125px;z-index:3;" swiper-animate-effect="fadeIn" swiper-animate-duration="2s" swiper-animate-delay="1s">
              <img src="${ctx}/styles/shangpin/images/FashionRun/page2_4.png"/> 
            </div>
            
            <div class="ani resize" style="width:125px;height:88px;left:10px;top:300px;z-index:3;" swiper-animate-effect="fadeIn" swiper-animate-duration="2s" swiper-animate-delay="2s">
              <img src="${ctx}/styles/shangpin/images/FashionRun/page2_5.png"/> 
            </div>
            <div class="ani resize" style="width:162px;height:84px;right:10px;top:260px;z-index:3;" swiper-animate-effect="fadeIn" swiper-animate-duration="2s" swiper-animate-delay="1.5s">
              <img src="${ctx}/styles/shangpin/images/FashionRun/page2_6.png"/> 
            </div>
            <div class="ani resize" style="width:260px;height:133px;right:0;top:360px;z-index:3;" swiper-animate-effect="fadeIn" swiper-animate-duration="2s" swiper-animate-delay="2.5s">
              <img src="${ctx}/styles/shangpin/images/FashionRun/page2_7.png"/> 
            </div>
            <div id="array">
                <img src="${ctx}/styles/shangpin/images/FashionRun/arrow_up.png" width="25">
            </div>
        </section>
   		<!-------------page3-------------->
        <section class="swiper-slide page3">
        	<h2 class="page3-title"><img src="${ctx}/styles/shangpin/images/FashionRun/page3_2.png" /></h2>
            <div class="swiper-container swiper-container-h">
                <div class="swiper-wrapper">
                   <%--  <div class="swiper-slide">
                    	<div class="run-people-box">
                        	<img src="${ctx}/styles/shangpin/images/FashionRun/page3_img04.jpg"/>
                            <h4>王潇</h4>
                        	<p>时尚COSMO主编，女性励志品牌“趁早”发起人</p>
                        </div>
                    </div> --%>
                    <div class="swiper-slide">
                    	<div class="run-people-box">
                        	<img src="${ctx}/styles/shangpin/images/FashionRun/page3_img05.jpg"/>
                            <h4>张驰</h4>
                        	<p>中国著名先锋时装设计师</p>
                        </div>
                    </div>
                    <div class="swiper-slide">
                    	<div class="run-people-box">
                        	<img src="${ctx}/styles/shangpin/images/FashionRun/page3_img01.jpg"/>
                            <h4>王鑫</h4>
                        	<p>奥运会女子双人十米跳台冠军</p>
                        </div> 
                    </div>
                    <div class="swiper-slide">
                    	<div class="run-people-box">
                        	<img src="${ctx}/styles/shangpin/images/FashionRun/page3_img02.jpg"/>
                            <h4>邢傲伟</h4>
                        	<p>奥运会体操冠军</p>
                        </div>
                    </div>
                    <div class="swiper-slide">
                    	<div class="run-people-box">
                        	<img src="${ctx}/styles/shangpin/images/FashionRun/page3_img03.jpg"/>
                            <h4>王丽萍</h4>
                        	<p>奥运会女子20公里竞走冠军</p>
                        </div>
                    </div>
                </div>
                
                <div class="swiper-button-prev h-swiper-button-prev"></div>
                <div class="swiper-button-next h-swiper-button-next"></div>
            </div>
            <div id="array">
                <img src="${ctx}/styles/shangpin/images/FashionRun/arrow_up.png" width="25">
            </div>
        </section>
  	    <!-------------page4----------------->
        <section class="swiper-slide page4" >
			<div class="ani resize" style="width:235px;height:55px;right:5px;top:210px;z-index:3;"swiper-animate-effect="wobble" swiper-animate-duration="2s" swiper-animate-delay="0s">
            	<img src="${ctx}/styles/shangpin/images/FashionRun/page4_2.png" />
            </div>
            <div id="array">
                <img src="${ctx}/styles/shangpin/images/FashionRun/arrow_up.png" width="25">
            </div>
        </section>
        <!-------------page5----------------->
        <section class="swiper-slide page5" >
       		 <div class="ani resize" style="width:108px;height:49px;right:30px;top:90px;z-index:3;"swiper-animate-effect="swing" swiper-animate-duration="2s" swiper-animate-delay="0s">
            	<img src="${ctx}/styles/shangpin/images/FashionRun/page5_2.png" />
            </div>
            <div class="ani resize" style="width:99px;height:61px;right:90px;top:275px;z-index:3;"swiper-animate-effect="swing" swiper-animate-duration="2s" swiper-animate-delay="0s">
            	<img src="${ctx}/styles/shangpin/images/FashionRun/page5_3.png" />
            </div>
            <div class="ani resize" style="width:93px;height:45px;left:20px;top:340px;z-index:3;"swiper-animate-effect="swing" swiper-animate-duration="2s" swiper-animate-delay="0s">
            	<img src="${ctx}/styles/shangpin/images/FashionRun/page5_4.png" />
            </div>
            <div id="array">
                <img src="${ctx}/styles/shangpin/images/FashionRun/arrow_up.png" width="25">
            </div>
        </section>
        <!-------------page6----------------->
        <section class="swiper-slide page6" >
            <div id="array">
                <img src="${ctx}/styles/shangpin/images/FashionRun/arrow_up.png" width="25">
            </div>
        </section>
        <!-------------page7----------------->
         <section class="swiper-slide page3">
        	<h2 class="page3-title"><img src="${ctx}/styles/shangpin/images/FashionRun/page7_2.png" /></h2>
            <div class="swiper-container swiper-container-h">
                <div class="swiper-wrapper">
                    <div class="swiper-slide">
                    	<div class="run-people-box">
                        	<img src="${ctx}/styles/shangpin/images/FashionRun/page7_img03.jpg"/>
                        	<p class="page7-text">帅哥猛男做“兔子”</p>
                        </div>
                    </div>
                    <div class="swiper-slide">
                    	<div class="run-people-box">
                        	<img src="${ctx}/styles/shangpin/images/FashionRun/page7_img04.jpg"/>
                        	<p class="page7-text">专属造型师现场服务<br>造型时尚“妆”着跑！</p>
                        </div>
                    </div>
                    <div class="swiper-slide">
                    	<div class="run-people-box">
                        	<img src="${ctx}/styles/shangpin/images/FashionRun/page7_img01.jpg"/>
                        	<p class="page7-text">湿身大趴嗨翻全场</p>
                        </div> 
                    </div>
                    <div class="swiper-slide">
                    	<div class="run-people-box">
                        	<img src="${ctx}/styles/shangpin/images/FashionRun/page7_img02.jpg"/>
                        	<p class="page7-text">吉它小鲜肉给你唱情歌！</p>
                        </div>
                    </div>
                     <div class="swiper-slide">
                    	<div class="run-people-box">
                        	<img src="${ctx}/styles/shangpin/images/FashionRun/page7_img05.jpg"/>
                        	<p class="page7-text">赛后抽奖</p>
                        </div>
                    </div>
                </div>
                
                <div class="swiper-button-prev h-swiper-button-prev"></div>
                <div class="swiper-button-next h-swiper-button-next"></div>
            </div>
            <div id="array">
                <img src="${ctx}/styles/shangpin/images/FashionRun/arrow_up.png" width="25">
            </div>
        </section>
        <!-------------page8----------------->
        <section class="swiper-slide page3" >
       		 <div class="ani resize" style="width:198px;height:64px;left:60px;top:30px;z-index:3;"swiper-animate-effect="bounceIn" swiper-animate-duration="2s" swiper-animate-delay="0s">
            	<img src="${ctx}/styles/shangpin/images/FashionRun/page2_2.png" />
            </div>
            <div class="ani resize" style="width:268px;height:310px;left:30px;top:120px;z-index:3;"swiper-animate-effect="fadeIn" swiper-animate-duration="2s" swiper-animate-delay="0.5s">
            	<img src="${ctx}/styles/shangpin/images/FashionRun/page8_2.png" />
            </div>
            <div class="ani resize" style="width:128px;height:40px;left:30px;top:410px;z-index:3;"swiper-animate-effect="fadeIn" swiper-animate-duration="2s" swiper-animate-delay="1s">
            	<a href="http://m.shangpin.com/fashionrun/sign"><img src="${ctx}/styles/shangpin/images/FashionRun/page8_3.png" /></a>
            </div>
            <div class="ani resize" style="width:120px;height:40px;right:30px;top:410px;z-index:3;"swiper-animate-effect="fadeIn" swiper-animate-duration="2s" swiper-animate-delay="1.5s">
            	<a href="http://m.shangpin.com/fashionrun/gift"><img src="${ctx}/styles/shangpin/images/FashionRun/page8_4.png" /></a>
            </div>
            <div id="array">
                <img src="${ctx}/styles/shangpin/images/FashionRun/arrow_up.png" width="25">
            </div>
        </section>
        <!-------------page9----------------->
        <section class="swiper-slide page3" >
       		<div class="ani resize" style="width:235px;height:88px;left:40px;top:30px;z-index:3;"swiper-animate-effect="bounceIn" swiper-animate-duration="2s" swiper-animate-delay="0s">
            	<img src="${ctx}/styles/shangpin/images/FashionRun/page9_2.png" />
            </div>
            <div class="ani resize" style="width:280px;height:112px;left:30px;top:200px;z-index:3;"swiper-animate-effect="fadeIn" swiper-animate-duration="2s" swiper-animate-delay="0.5s">
            	<img src="${ctx}/styles/shangpin/images/FashionRun/page9_4.png" />
            </div>
           <%--  <div class="ani resize" style="width:280px;height:115px;left:30px;top:300px;z-index:3;"swiper-animate-effect="fadeIn" swiper-animate-duration="2s" swiper-animate-delay="1s">
            	<img src="${ctx}/styles/shangpin/images/FashionRun/page9_3.png" />
            </div> --%>
        </section>
          
    </div>
    
    
   
</div>

<aside id="aside">
  <div id="music" class="on"></div>
</aside>

 <%-- <c:if test="${checkAPP}">
  <div class="shang_share"> 
         <a href="shangpinapp://phone.shangpin/actiongoshare?title=FashionRun北京站美女招募,8月1日奥森公园不美不跑.&url=${basePath }/fashionrun/girl&desc=FashionRun北京站美女招募,8月1日奥森公园不美不跑."><img src="${cdn:pic(pageContext.request)}/styles/shangpin/images/b_share.png" width="40"></a>
   </div>
</c:if> --%>
</rapid:override>
<%@ include file="/WEB-INF/pages/common/base_no_container_banner.jsp" %> 
