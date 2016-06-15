<%@ page language="java" contentType="text/html; charset=UTF-8" trimDirectiveWhitespaces="true"
    pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/pages/common/include.inc.jsp"%>

<rapid:override name="title">
	女神新装一元购
</rapid:override>
<rapid:override name="custum">
	<link href="${cdn:css(pageContext.request)}/styles/shangpin/css/page/one/base.css${ver}" rel="stylesheet" />
	<link href="${cdn:css(pageContext.request)}/styles/shangpin/css/page/one/20151016GoddessClothes.css${ver}" rel="stylesheet" />
	<script type="text/javascript" charset="utf-8">
		loader = SP.core
			.install("${cdn:js(pageContext.request)}/styles/shangpin/js/jquery.min.js${ver}")
			.using("${cdn:js(pageContext.request)}/styles/shangpin/js/lazyload.js${ver}")
			.excute()
			.using("${cdn:js(pageContext.request)}/styles/weixin/jweixin-1.0.0.js${ver}")
			.using("${cdn:js(pageContext.request)}/styles/shangpin/js/config.sim.js${ver}")
			.using("${cdn:js(pageContext.request)}/styles/shangpin/js/one/one.js${ver}")
			.using("${cdn:js(pageContext.request)}/styles/shangpin/js/one/weixin_ready.js${ver}")
			.excute(function(){
			
			})
	</script>
</rapid:override>

<rapid:override name="page_title">
	女神新装一元购
</rapid:override>

<rapid:override name="content">
	<input type="hidden" name="_shareUrl"  id="_shareUrl"  value="http://m.shangpin.com/one/index"/>
	<input type="hidden" name="_mainTitle"  id="_mainTitle"  value="没看错！1元女神新装！尚品网CEO豪送2000套女神新装！"/>
	<input type="hidden" name="_mainDesc"  id="_mainDesc"  value="尚品网CEO豪送2000套女神新装，回馈辛苦的购物达人！"/>
	<input type="hidden" name="_mainImgUrl" id="_mainImgUrl"  value="${cdn:pic(pageContext.request)}/styles/shangpin/images/goddes/goddes-10.jpg"/>
	<input type="hidden" name="_currentUrl" id="_currentUrl"  value=""/>
	<!--内容区域 start-->
    <div class="conbox">
    	<p><img src="${cdn:pic(pageContext.request)}/styles/shangpin/images/one/images/img01.jpg" /></p>
        
        <p class="clr">
          <a href="#">
            <img src="${cdn:pic(pageContext.request)}/styles/shangpin/images/e.gif" lazy="${cdn:pic(pageContext.request)}/styles/shangpin/images/one/images/img005.jpg" >
          </a>
        </p>
        
        <!--两列-->
        <ul class="sp_list list-nom clr">
        	<c:choose>
        		<c:when test="${productCount.totalCount > 0}">
		        	<li><a href="${ctx}/product/detail?productNo=30176752&topicId=51016522"><img src="${cdn:pic(pageContext.request)}/styles/shangpin/images/e.gif" lazy="${cdn:pic(pageContext.request)}/styles/shangpin/images/one/images/img006.jpg" ></a></li>	
		            <li><img src="${cdn:pic(pageContext.request)}/styles/shangpin/images/e.gif" lazy="${cdn:pic(pageContext.request)}/styles/shangpin/images/one/images/img007.jpg" ></li>
        		</c:when>
        		<c:otherwise>
		            <li><a href="#"><img src="${cdn:pic(pageContext.request)}/styles/shangpin/images/e.gif" lazy="${cdn:pic(pageContext.request)}/styles/shangpin/images/one/images/img036.jpg" ></a></li>	
		            <li><img src="${cdn:pic(pageContext.request)}/styles/shangpin/images/e.gif" lazy="${cdn:pic(pageContext.request)}/styles/shangpin/images/one/images/img037.jpg" ></li>
        		</c:otherwise>
        	</c:choose>
        </ul>
        
        <p class="clr">
            <img src="${cdn:pic(pageContext.request)}/styles/shangpin/images/one/images/img02.jpg" >
        </p>
        
        <!--两列-->
        <ul class="sp_list list-nom clr">
<%--         	<li><img src="${cdn:pic(pageContext.request)}/styles/shangpin/images/one/images/img03.jpg" ></li>	 --%>
<%--             <li><a href="${ctx}/product/detail?productNo=30195277&topicId=51016522"><img src="${cdn:pic(pageContext.request)}/styles/shangpin/images/one/images/img04.jpg" ></a></li> --%>
        	<li><img src="${cdn:pic(pageContext.request)}/styles/shangpin/images/one/images/img33.jpg" ></li>	
            <li><a href="#"><img src="${cdn:pic(pageContext.request)}/styles/shangpin/images/one/images/img34.jpg" ></a></li>
        </ul>
        
        <p class="clr">
          <a href="#">
            <img src="${cdn:pic(pageContext.request)}/styles/shangpin/images/e.gif" lazy="${cdn:pic(pageContext.request)}/styles/shangpin/images/one/images/img11.jpg" >
          </a>
        </p>
        <p class="clr">
          <a href="#">
            <img src="${cdn:pic(pageContext.request)}/styles/shangpin/images/e.gif" lazy="${cdn:pic(pageContext.request)}/styles/shangpin/images/one/images/img12.jpg" >
          </a>
        </p>
        
        <!--两列-->
        <ul class="sp_list list-nom clr">
        	<li><a href="${ctx}/product/detail?productNo=30176642"><img src="${cdn:pic(pageContext.request)}/styles/shangpin/images/e.gif" lazy="${cdn:pic(pageContext.request)}/styles/shangpin/images/one/images/img13.jpg" ></a></li>	
            <li><a href="${ctx}/product/detail?productNo=30176657"><img src="${cdn:pic(pageContext.request)}/styles/shangpin/images/e.gif" lazy="${cdn:pic(pageContext.request)}/styles/shangpin/images/one/images/img14.jpg" ></a></li>
        </ul>
        
        <p class="clr">
          <a href="#">
            <img src="${cdn:pic(pageContext.request)}/styles/shangpin/images/e.gif" lazy="${cdn:pic(pageContext.request)}/styles/shangpin/images/one/images/img15.jpg" >
          </a>
        </p>
        
        <p class="clr">
          <a href="#">
            <img src="${cdn:pic(pageContext.request)}/styles/shangpin/images/e.gif" lazy="${cdn:pic(pageContext.request)}/styles/shangpin/images/one/images/img16.jpg" >
          </a>
        </p>
        
        <!--两列-->
        <ul class="sp_list list-nom clr">
        	<li><a href="${ctx}/product/detail?productNo=30176243"><img src="${cdn:pic(pageContext.request)}/styles/shangpin/images/e.gif" lazy="${cdn:pic(pageContext.request)}/styles/shangpin/images/one/images/img17.jpg" ></a></li>	
            <li><a href="${ctx}/product/detail?productNo=30176410"><img src="${cdn:pic(pageContext.request)}/styles/shangpin/images/e.gif" lazy="${cdn:pic(pageContext.request)}/styles/shangpin/images/one/images/img18.jpg" ></a></li>
        </ul>

		<!--三列 214 * 524-->
        <ul class="venue_list clr">
          <li>
              <a href=""><img src="${cdn:pic(pageContext.request)}/styles/shangpin/images/e.gif" lazy="${cdn:pic(pageContext.request)}/styles/shangpin/images/one/images/img19.jpg" /></a>
          </li>
          <li>
              <a href=""><img src="${cdn:pic(pageContext.request)}/styles/shangpin/images/e.gif" lazy="${cdn:pic(pageContext.request)}/styles/shangpin/images/one/images/img20.jpg" /></a>
          </li>
           <li>
              <a href=""><img src="${cdn:pic(pageContext.request)}/styles/shangpin/images/e.gif" lazy="${cdn:pic(pageContext.request)}/styles/shangpin/images/one/images/img21.jpg" /></a>
          </li>
        </ul>
        
    </div>
</rapid:override>
<rapid:override name="footer">

</rapid:override>
<rapid:override name="static_file">
	
</rapid:override>

<%@ include file="/WEB-INF/pages/common/common_no_content_banner.jsp" %> 
