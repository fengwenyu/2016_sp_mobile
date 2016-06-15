<%@ page language="java" contentType="text/html; charset=UTF-8" trimDirectiveWhitespaces="true"
    pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/pages/common/include.inc.jsp"%>

<rapid:override name="custum">
<link href="${cdn:css(pageContext.request) }/styles/shangpin/css/20150909topManStory.css${ver}" rel="stylesheet" />
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
    <div class="conbox">
   		<!--头图-->
    	<p class="top_img"> <img src="${cdn:pic(pageContext.request)}/styles/shangpin/images/e.gif" lazy="${cdn:pic(pageContext.request)}/styles/shangpin/images/topManStory/img_top.jpg" /></p>
        <p><img src="${cdn:pic(pageContext.request)}/styles/shangpin/images/e.gif" lazy="${cdn:pic(pageContext.request)}/styles/shangpin/images/topManStory/img01.jpg" /></p>
        <article>
        	<p>TOPMAN 创立于1978年，与 TOPSHOP 同属于英国 ARCADIA 服装集团，同时也是世界级权威男装快时尚品牌之一，主要为热爱时尚的年轻男士提供平价又有型的个性男装。</p>
            <p>TOPMAN 的顾客不受年龄段限制，而更多的是在精神层面上的认可与喜爱，这些简约、赋有时尚头脑、同时又是时尚狂热份子的时尚型男们，职业一般也多种多样。有的是学生、有的是时尚编辑、还有音乐家、演员或是爱好时尚的任何人，只要你把时尚作为自己的生活方式，那么选择 TOPMAN 一定超乎所愿。</p>
        </article>
        <p><img src="${cdn:pic(pageContext.request)}/styles/shangpin/images/e.gif" lazy="${cdn:pic(pageContext.request)}/styles/shangpin/images/topManStory/img02.jpg" /></p>
        <article>
        	<p>TOPMAN 开创之初即大胆启用着装品味时尚度超高的足球明星作代言，以此打开了男士服饰零售新局面。</p>
            <p>伦敦牛津街214号的 TOPMAN 品牌旗舰店是英国大都市最主要的8家店铺之一，同时另一个该楼正在建设的扩展，并会提供免费的个人购物服务。这里每周有75，000人次光临15，000平方英尺店铺，而且这个数字在持续上涨。</p>
        </article>
        <p><img src="${cdn:pic(pageContext.request)}/styles/shangpin/images/e.gif" lazy="${cdn:pic(pageContext.request)}/styles/shangpin/images/topManStory/img03.jpg" /></p>
        <article>
        	<p>如今 TOPMAN 连锁店也走上了国际化发展路线，足迹遍布全球30多个国家，在日本、美国、澳大利亚、新加坡、菲律宾、马来西亚、印度尼西亚等国家都有 TOPMAN 品牌店，店铺平均面积2600平米。纽约曼哈顿中心百老汇的 TOPMAN/TOPSHOP 店铺一共有4层楼，面积约2300多平米，从国际超模 Kate Moss 到时尚女魔头 Anna Wintour 等时尚界名流大咖都曾光顾于此。</p>
        </article>
        <p><img src="${cdn:pic(pageContext.request)}/styles/shangpin/images/e.gif" lazy="${cdn:pic(pageContext.request)}/styles/shangpin/images/topManStory/img04.jpg" /></p>
    </div>
    <!--内容区域 END-->
</rapid:override>

<rapid:override name="footer">
  	
 </rapid:override>
<%@ include file="/WEB-INF/pages/common/bottom_common_mall_no_banner.jsp" %> 