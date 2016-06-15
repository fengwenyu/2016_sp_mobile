<%@ page language="java" contentType="text/html; charset=UTF-8" trimDirectiveWhitespaces="true"
    pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/pages/common/include.inc.jsp"%>

<rapid:override name="custum">
<link href="${cdn:css(pageContext.request) }/styles/shangpin/css/raiders/20150908topMan.css${ver}" rel="stylesheet" />
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
            牛仔攻略
</rapid:override>
<rapid:override name="content">
    <!--内容区域 start-->
    <div class="conbox">
   		<!--头图-->
    	<p class="top_img"> 
    	 <c:choose>
           	<c:when test="${checkAPP }">
           	  <a href="shangpinapp://phone.shangpin/actiongoactivitylist?title=尚品&activityid=50909662">
           	</c:when>
           	<c:otherwise>
           	   <a href="http://m.shangpin.com/subject/product/list_50909662">
           	</c:otherwise>
          </c:choose>
    		<img src="${cdn:pic(pageContext.request)}/styles/shangpin/images/e.gif" lazy="${cdn:pic(pageContext.request)}/styles/shangpin/images/raiders/img_top.jpg" />
    	</a>
    	</p>
        <p><img src="${cdn:pic(pageContext.request)}/styles/shangpin/images/e.gif" lazy="${cdn:pic(pageContext.request)}/styles/shangpin/images/raiders/img01.jpg" /></p>
        <!--三列-->
    	<ul class="venue_list clr">
            <li>
            <c:choose>
            	<c:when test="${checkAPP }">
            	  <a href="shangpinapp://phone.shangpin/actiongoactivitylist?title=尚品&activityid=50908529"><img src="${cdn:pic(pageContext.request)}/styles/shangpin/images/e.gif" lazy="${cdn:pic(pageContext.request)}/styles/shangpin/images/raiders/img02.jpg" /></a>
            	</c:when>
            	<c:otherwise>
            	  <a href="http://m.shangpin.com/subject/product/list_50908529"><img src="${cdn:pic(pageContext.request)}/styles/shangpin/images/e.gif" lazy="${cdn:pic(pageContext.request)}/styles/shangpin/images/raiders/img02.jpg" /></a>
            	</c:otherwise>
            </c:choose>
              
            </li>
            <li>
                <img src="${cdn:pic(pageContext.request)}/styles/shangpin/images/e.gif" lazy="${cdn:pic(pageContext.request)}/styles/shangpin/images/raiders/img03.jpg" />
            </li>
             <li>
                <c:choose>
	            	<c:when test="${checkAPP }">
	            	  <a href="shangpinapp://phone.shangpin/actiongoactivitylist?title=尚品&activityid=50908534"><img src="${cdn:pic(pageContext.request)}/styles/shangpin/images/e.gif" lazy="${cdn:pic(pageContext.request)}/styles/shangpin/images/raiders/img04.jpg" /></a>
	            	</c:when>
	            	<c:otherwise>
	            	   <a href="http://m.shangpin.com/subject/product/list_50908534"><img src="${cdn:pic(pageContext.request)}/styles/shangpin/images/e.gif" lazy="${cdn:pic(pageContext.request)}/styles/shangpin/images/raiders/img04.jpg" /></a>
	            	</c:otherwise>
            	</c:choose>
               
            </li>
    	</ul>
        <ul class="venue_list clr">
            <li>
            	 <c:choose>
	            	<c:when test="${checkAPP }">
	            	  <a href="shangpinapp://phone.shangpin/actiongoactivitylist?title=尚品&activityid=50908536"><img src="${cdn:pic(pageContext.request)}/styles/shangpin/images/e.gif" lazy="${cdn:pic(pageContext.request)}/styles/shangpin/images/raiders/img05.jpg" /></a>
	            	</c:when>
	            	<c:otherwise>
	            	    <a href="http://m.shangpin.com/subject/product/list_50908536"><img src="${cdn:pic(pageContext.request)}/styles/shangpin/images/e.gif" lazy="${cdn:pic(pageContext.request)}/styles/shangpin/images/raiders/img05.jpg" /></a>
	            	</c:otherwise>
            	</c:choose>
               
            </li>
            <li>
                <a href="#advice"><img src="${cdn:pic(pageContext.request)}/styles/shangpin/images/e.gif" lazy="${cdn:pic(pageContext.request)}/styles/shangpin/images/raiders/img06.jpg" /></a>
            </li>
             <li>
              <c:choose>
	            	<c:when test="${checkAPP }">
	            	  	<a href="shangpinapp://phone.shangpin/actiongoactivitylist?title=尚品&activityid=50908538"><img src="${cdn:pic(pageContext.request)}/styles/shangpin/images/e.gif" lazy="${cdn:pic(pageContext.request)}/styles/shangpin/images/raiders/img07.jpg" /></a>
	            	</c:when>
	            	<c:otherwise>
	            	     <a href="http://m.shangpin.com/subject/product/list_50908538"><img src="${cdn:pic(pageContext.request)}/styles/shangpin/images/e.gif" lazy="${cdn:pic(pageContext.request)}/styles/shangpin/images/raiders/img07.jpg" /></a>
	            	</c:otherwise>
            	</c:choose>
               
            </li>
    	</ul>
        <p class="imgList padding-top">
        	
        	    <c:choose>
	            	<c:when test="${checkAPP }">
	            	  	<a href="shangpinapp://phone.shangpin/actiongoactivitylist?title=尚品&activityid=50908529">
	            	</c:when>
	            	<c:otherwise>
	            	     <a href="http://m.shangpin.com/subject/product/list_50908529">
	            	</c:otherwise>
            	</c:choose>
            	<img src="${cdn:pic(pageContext.request)}/styles/shangpin/images/e.gif" lazy="${cdn:pic(pageContext.request)}/styles/shangpin/images/raiders/img08.jpg" />
                <img src="${cdn:pic(pageContext.request)}/styles/shangpin/images/e.gif" lazy="${cdn:pic(pageContext.request)}/styles/shangpin/images/raiders/img09.jpg" />
            </a>
        </p>
        <p class="imgList padding-top">
            <img src="${cdn:pic(pageContext.request)}/styles/shangpin/images/e.gif" lazy="${cdn:pic(pageContext.request)}/styles/shangpin/images/raiders/img10.jpg" />
            <img src="${cdn:pic(pageContext.request)}/styles/shangpin/images/e.gif" lazy="${cdn:pic(pageContext.request)}/styles/shangpin/images/raiders/img11.jpg" />
        </p>
        <p class="imgList padding-top">
           <c:choose>
	            	<c:when test="${checkAPP }">
	            	  	<a href="shangpinapp://phone.shangpin/actiongoactivitylist?title=尚品&activityid=50908534">
	            	</c:when>
	            	<c:otherwise>
	            	     <a href="http://m.shangpin.com/subject/product/list_50908534">
	            	</c:otherwise>
            	</c:choose>
        	
            	<img src="${cdn:pic(pageContext.request)}/styles/shangpin/images/e.gif" lazy="${cdn:pic(pageContext.request)}/styles/shangpin/images/raiders/img12.jpg" />
                <img src="${cdn:pic(pageContext.request)}/styles/shangpin/images/e.gif" lazy="${cdn:pic(pageContext.request)}/styles/shangpin/images/raiders/img13.jpg" />
            </a>
        </p>
        <p class="imgList padding-top">
        	 <c:choose>
	            	<c:when test="${checkAPP }">
	            	  	<a href="shangpinapp://phone.shangpin/actiongoactivitylist?title=尚品&activityid=50908536">
	            	</c:when>
	            	<c:otherwise>
	            	     <a href="http://m.shangpin.com/subject/product/list_50908536">
	            	</c:otherwise>
            	</c:choose>
            	<img src="${cdn:pic(pageContext.request)}/styles/shangpin/images/e.gif" lazy="${cdn:pic(pageContext.request)}/styles/shangpin/images/raiders/img14.jpg" />
                <img src="${cdn:pic(pageContext.request)}/styles/shangpin/images/e.gif" lazy="${cdn:pic(pageContext.request)}/styles/shangpin/images/raiders/img15.jpg" />
            </a>
        </p>
        <p class="imgList padding-top">
         <c:choose>
	            	<c:when test="${checkAPP }">
	            	  	<a href="shangpinapp://phone.shangpin/actiongoactivitylist?title=尚品&activityid=50908538">
	            	</c:when>
	            	<c:otherwise>
	            	     <a href="http://m.shangpin.com/subject/product/list_50908538">
	            	</c:otherwise>
            	</c:choose>
        	
            	<img src="${cdn:pic(pageContext.request)}/styles/shangpin/images/e.gif" lazy="${cdn:pic(pageContext.request)}/styles/shangpin/images/raiders/img16.jpg" />
                <img src="${cdn:pic(pageContext.request)}/styles/shangpin/images/e.gif" lazy="${cdn:pic(pageContext.request)}/styles/shangpin/images/raiders/img17.jpg" />
            </a>
        </p>
        <!--尺码选购建议-->
        <h3 class="imgList" style=" position:relative;">
        <a href="" id="advice"></a>
        <img src="${cdn:pic(pageContext.request)}/styles/shangpin/images/e.gif" lazy="${cdn:pic(pageContext.request)}/styles/shangpin/images/raiders/img18.jpg" title="尺码选购建议" /></h3>
        <p>
        	<img src="${cdn:pic(pageContext.request)}/styles/shangpin/images/e.gif" lazy="${cdn:pic(pageContext.request)}/styles/shangpin/images/raiders/img19.jpg" />
        	<img src="${cdn:pic(pageContext.request)}/styles/shangpin/images/e.gif" lazy="${cdn:pic(pageContext.request)}/styles/shangpin/images/raiders/img20.jpg" />
        </p>
        
        <!--尺码解析-->
        <h3><img src="${cdn:pic(pageContext.request)}/styles/shangpin/images/e.gif" lazy="${cdn:pic(pageContext.request)}/styles/shangpin/images/raiders/img21.jpg" title="尺码解析" /></h3>
        <p>
        	<img src="${cdn:pic(pageContext.request)}/styles/shangpin/images/e.gif" lazy="${cdn:pic(pageContext.request)}/styles/shangpin/images/raiders/img22.jpg" />
        	<img src="${cdn:pic(pageContext.request)}/styles/shangpin/images/e.gif" lazy="${cdn:pic(pageContext.request)}/styles/shangpin/images/raiders/img23.jpg" />
        </p>
        
        <!--JEANS牛仔裤-->
        <h3><img src="${cdn:pic(pageContext.request)}/styles/shangpin/images/e.gif" lazy="${cdn:pic(pageContext.request)}/styles/shangpin/images/raiders/img24.jpg" title="JEANS牛仔裤" /></h3>
        <p>
        	<img src="${cdn:pic(pageContext.request)}/styles/shangpin/images/e.gif" lazy="${cdn:pic(pageContext.request)}/styles/shangpin/images/raiders/img25.jpg" />
        	<img src="${cdn:pic(pageContext.request)}/styles/shangpin/images/e.gif" lazy="${cdn:pic(pageContext.request)}/styles/shangpin/images/raiders/img26.jpg" />
        </p>
    </div>
    <!--内容区域 start-->
</rapid:override>

<rapid:override name="footer">
  	
 </rapid:override>
<%@ include file="/WEB-INF/pages/common/bottom_common_mall_no_banner.jsp" %> 