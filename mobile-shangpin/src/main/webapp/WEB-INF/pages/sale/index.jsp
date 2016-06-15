<%@ page language="java" contentType="text/html; charset=UTF-8" trimDirectiveWhitespaces="true"
    pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/pages/common/include.inc.jsp"%>

<rapid:override name="custum">
	<link href="${cdn:css(pageContext.request)}/styles/shangpin/css/page/sale.css${ver}" rel="stylesheet" />
	<script type="text/javascript" charset="utf-8">
			loader = SP.core
				.install("${cdn:js(pageContext.request)}/styles/shangpin/js/jquery.min.js${ver}")
				.using("${cdn:js(pageContext.request)}/styles/shangpin/js/zepto.1.1.3.js${ver}")
				.using("${cdn:js(pageContext.request)}/styles/shangpin/js/lazyload.js${ver}")
				.excute()
				.using("${cdn:js(pageContext.request)}/styles/shangpin/js/config.sim.js${ver}")
				.using("${cdn:js(pageContext.request)}/styles/shangpin/js/j.dialogs.js${ver}")
				.using("${cdn:js(pageContext.request)}/styles/shangpin/js/tabslider.js${ver}" )
				.excute()
				.using("${cdn:js(pageContext.request)}/styles/shangpin/js/comm.js${ver}")
				.using("${cdn:js(pageContext.request)}/styles/shangpin/js/downloadAppShow.js${ver}")
				.using("${cdn:js(pageContext.request)}/styles/shangpin/js/201502brand/hotSaleNew.js${ver}")
				.excute(function(){
						//特卖未开启弹出层
						$('body').delegate('.no_open','click',function(e){
							var timestamp=new Date().getTime();
							if ($(this).attr("startTime")>timestamp){
								var html="还没开始呢，"+$(this).attr("startTag")+" 再来吧！";
								$('#popup_message').html(html);
								e.preventDefault();
								$('#popup_overlay').show();
								$('#popup_container').show();
								setTimeout(function(){
									$('#popup_overlay').hide();
									$('#popup_container').hide();
								},2000);
							}
						});
					});
	</script>
</rapid:override >
<rapid:override name="page_title">
	全部特卖
</rapid:override>
<rapid:override name="content">
      <!-- 滑块列表 --> 
      <div id="tabSlider1" class="tabSlider" style="visibility:hidden"> 
            <div class="menu-nav">
                  <ul class="tabSlider-hd">
                      <li class="curr"><a>今日新开</a></li>
                      <li><a>昨日上新</a></li>
                      <li><a>最后疯抢</a></li>
                  </ul> 
               </div>
            <div class="tabSlider-bd">      
                <div class="tabSlider-wrap">
               <c:import url="/sale/hot/today"></c:import>
               <c:import url="/sale/hot/yesterday"></c:import>
               <c:import url="/sale/hot/last"></c:import>
                </div>  
            </div>
         </div>
</rapid:override>

<%@ include file="/WEB-INF/pages/common/another_common_mall_banner.jsp" %> 