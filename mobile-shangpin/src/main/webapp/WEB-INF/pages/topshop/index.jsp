<%@ page language="java" contentType="text/html; charset=UTF-8" trimDirectiveWhitespaces="true"
    pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/pages/common/include.inc.jsp"%>

<rapid:override name="custum">
	<link href="${cdn:css(pageContext.request)}/styles/shangpin/css/page/topshop.css${ver}" rel="stylesheet" />
	<script type="text/javascript" charset="utf-8">
			loader = SP.core
				.install("${cdn:js(pageContext.request)}/styles/shangpin/js/jquery.min.js${ver}")
				.using("${cdn:js(pageContext.request)}/styles/shangpin/js/lazyload.js${ver}")
				.excute()
				.using("${cdn:js(pageContext.request)}/styles/shangpin/js/config.sim.js${ver}")
				.using("${cdn:js(pageContext.request)}/styles/shangpin/js/topshop.js${ver}")
				.excute();
		
	</script>
</rapid:override > 
<%-- 浏览器标题 --%>
<rapid:override name="title">
	TOPSHOP 旗舰店
</rapid:override>

<%-- 页标题 --%>
<rapid:override name="page_title">
	TOPSHOP 旗舰店
</rapid:override>
  
<rapid:override name="content">
<div class="wrapper">
  <div id="slide">
    <!--page01-->
    <section id="page01">
	  <div class="layer layer0 on"></div>
      <div class="layer layer1 zIndex"></div>
      <div class="layer layer2"></div>
      <div class="layer layer4"></div>
      <div class="layer layer5">
        <span>230,876粉丝</span>
      </div>
      <div class="layer layer3">
        <div class="topshop_menu clr">
        	<c:choose>
        		<c:when test="${checkAPP}">
        		<a href="ShangPinApp://phone.shangpin/actiongoactivitylist?title=TOPSHOP本周新品&activityid=41117083"><img src="${cdn:pic(pageContext.request)}/styles/shangpin/images/e.gif" lazy="${cdn:pic(pageContext.request)}/styles/shangpin/images/topshop/topshop001-1.jpg?20141120" width="100%"></a>
        		</c:when>
        		<c:otherwise> 
        			 <a href="<c:url value='/subject/product/list?topicId=41117083'/>"><img src="${cdn:pic(pageContext.request)}/styles/shangpin/images/e.gif" lazy="${cdn:pic(pageContext.request)}/styles/shangpin/images/topshop/topshop001-1.jpg" width="100%"></a>
       				 </c:otherwise>
        	</c:choose>
        	<a href="<c:url value='/meet/index?id=182'/>"><img src="${cdn:pic(pageContext.request)}/styles/shangpin/images/e.gif" lazy="${cdn:pic(pageContext.request)}/styles/shangpin/images/topshop/topshop002-1.jpg" width="100%"></a>
        </div>
        <div class="layer_box">
            <ul class="clr">
            	<li class="big">
			    		<a href="<c:url value='/brand/product/list?brandNo=B1885&brandName=TOPSHOP&categoryNo=A01B02'/>"><img src="${cdn:pic(pageContext.request)}/styles/shangpin/images/e.gif" lazy="${cdn:pic(pageContext.request)}/styles/shangpin/images/topshop/img003.jpg" width="100%"></a>                 
                	  <div class="title">
                    	<h3>服装</h3>
                        <!-- <span>上新 55</span> -->
                    </div>
                </li>
                <li>
			    	<a href="<c:url value='/brand/product/list?brandNo=B1885&brandName=TOPSHOP&categoryNo=A01B03'/>"><img src="${cdn:pic(pageContext.request)}/styles/shangpin/images/e.gif" lazy="${cdn:pic(pageContext.request)}/styles/shangpin/images/topshop/img002.jpg" width="100%"></a>
                	<div class="title r_t">
                    	<h3>&nbsp;&nbsp;鞋靴</h3>
                       <!-- <span>30%OFF</span>-->
                    </div>
                </li>
                <li class="non_top">
			    <a href="<c:url value='/brand/product/list?brandNo=B1885&brandName=TOPSHOP&categoryNo=A01B04'/>"><img src="${cdn:pic(pageContext.request)}/styles/shangpin/images/e.gif" lazy="${cdn:pic(pageContext.request)}/styles/shangpin/images/topshop/img004.jpg" width="100%"></a>
                	  <div class="title r_t">
                    	<h3>配饰</h3>
                       <!-- <span>今日免邮</span>-->
                    </div>
                </li>
            </ul>
             <p class="big">           
            <a href="${ctx}/style/index"><img src="${cdn:pic(pageContext.request)}/styles/shangpin/images/e.gif" lazy="${cdn:pic(pageContext.request)}/styles/shangpin/images/topshop/img001.jpg" width="100%"></a></p>
            <div class="btmBox divWidthSizing clr">
            <div class="btmBox divWidthSizing clr">
          
			  <a href="<c:url value='/brand/product/list?brandNo=B1885&brandName=TOPSHOP'/>">
				
                	查看所有商品
                    <br />
                    <span>每周一上新</span>
                </a>
            </div>
            <!--<div class="tel400 divWidthSizing"><img src="/images/e.gif" lazy="/images/mall-v2/400tel.png" height="39"></div>-->
            
        </div>
      </div>
    </section>
  </div>
</div>
</rapid:override> 
<%@ include file="/WEB-INF/pages/common/common.jsp" %> 