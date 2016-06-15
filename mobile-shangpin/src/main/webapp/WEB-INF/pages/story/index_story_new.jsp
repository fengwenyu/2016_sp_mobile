<%@ page language="java" contentType="text/html; charset=UTF-8" trimDirectiveWhitespaces="true"
    pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/pages/common/include.inc.jsp"%>

<rapid:override name="custum">
	<link rel="stylesheet" href="${cdn:css(pageContext.request)}/styles/shangpin/css/page/font-css/font-awesome.min.css?${ver}">
	<link href="${cdn:css(pageContext.request)}/styles/shangpin/css/page/meet/share.css?${ver}" rel="stylesheet" />
	<link href="${cdn:css(pageContext.request)}/styles/shangpin/css/base.css?${ver}" rel="stylesheet" />
	<link href="${cdn:css(pageContext.request)}/styles/shangpin/css/page/banner.css?${ver}" rel="stylesheet" />
	<link href="${cdn:css(pageContext.request)}/styles/shangpin/css/swiper.min.css" rel="stylesheet" />
	<link href="${cdn:css(pageContext.request)}/styles/shangpin/css/page/201502brand/topman.css" rel="stylesheet" />
	
<script type="text/javascript" charset="utf-8">
  var ver = Math.random();
    loader = SP.core
	  .install("${cdn:js(pageContext.request)}/styles/shangpin/js/jquery.min.js${ver}")
      .using("${cdn:js(pageContext.request)}/styles/shangpin/js/lazyload.js${ver}")
	  .excute()
	  .using("${cdn:js(pageContext.request)}/styles/shangpin/js/downloadAppShow.js${ver}")
	  .using("${cdn:js(pageContext.request)}/styles/shangpin/js/config.sim.js${ver}")
	  .excute(function(){
		 //顶部悬浮层
		 function topFixed(){	
			 var scrolls = document.body.scrollTop;
			 var menuTopHeight = 0;
				if($('.topFix').length){
					menuTopHeight = $('.topFix').offset().top;
				}
				if (scrolls > menuTopHeight) {
					$('.topFix section').css({
						position: "fixed",
						top:"0",
						zIndex:"999"
					});
				}else {
					$('.topFix section').css({
						position: "relative",
						top:"0",
						zIndex:"10"
					});
					   
				}
		};
		
		$(window).scroll(function(){
			topFixed();
		});
		//页面自动滑动加载头部Lazyload图片
		setTimeout(function(){window.scroll(0,1);},2000);
		
	  });
</script>
</rapid:override>
<rapid:override name="page_title">
            品牌故事 
</rapid:override>
<rapid:override name="content">
    <!--内容区域 start-->
    <div class="conbox" >
	   <img src="http://pic2.shangpin.com/group1/M00/70/C3/rBQKaVbmZ0uAbfq5AADAm-bv9po457.jpg"> 
	   <img src="http://pic1.shangpin.com/group1/M00/70/C3/rBQKaVbmZ0yAYLxBAADnNuT-qIo442.jpg"> 
	   <img src="http://pic1.shangpin.com/group1/M00/70/C3/rBQKaVbmZ02AQDa-AAAkkm80Fxs229.jpg"> 
	   <img src="http://pic4.shangpin.com/group1/M00/70/C4/rBQKaVbmZ06AKmC5AADP7FiWO0A874.jpg"> 
	   <img src="http://pic5.shangpin.com/group1/M00/70/C4/rBQKaVbmZ1GAeKwSAACWclzbNl4774.jpg"> 
	   <img src="http://pic3.shangpin.com/group1/M00/70/C4/rBQKaVbmZ1KALFlfAACiYX6gbDs326.jpg"> 
	   <img src="http://pic3.shangpin.com/group1/M00/70/C4/rBQKaVbmZ0-AcB1GAADKnWnxqAo979.jpg"> 
	   <div class="href_box"> 
	    <a href="http://m.shangpin.com/brand/product/list?brandNo=B02850&amp;postArea=0&amp;WWWWWWWWW"><img src="http://pic1.shangpin.com/group1/M00/70/C4/rBQKaVbmZ1CAOMfgAAAKVTuw1wU740.jpg" width="100%"></a> 
	   </div> 
 	 </div>
    <!--内容区域 END-->
</rapid:override>

<rapid:override name="footer">
  	
 </rapid:override>
<%@ include file="/WEB-INF/pages/common/bottom_common_mall_no_banner.jsp" %> 