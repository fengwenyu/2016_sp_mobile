<%@ page language="java" contentType="text/html; charset=UTF-8"
	trimDirectiveWhitespaces="true" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/pages/common/include.inc.jsp"%>

<rapid:override name="custum">
	<link href="${cdn:css(pageContext.request)}/styles/shangpin/css/page/new.css${ver}" rel="stylesheet" />
	<script type="text/javascript" charset="utf-8">
		
			loader = SP.core
			.install("${cdn:js(pageContext.request)}/styles/shangpin/js/jquery.min.js${ver}")
			.using("${cdn:js(pageContext.request)}/styles/shangpin/js/lazyload.js${ver}")
			.excute()
			.using("${cdn:js(pageContext.request)}/styles/shangpin/js/config.sim.js${ver}")
			
			.excute(function(){
				var winH = $(window).height();
				$(".new_box").height(winH);
			});
	</script>
</rapid:override>
<%-- 浏览器标题 --%>
<rapid:override name="title">
	最新到货
</rapid:override>

<%-- 页标题 --%>
<rapid:override name="page_title">
	最新到货
</rapid:override>
<rapid:override name="content">
	  <div class="new_box">
    	<ul>
        	<li> 
            	<h2><img src="${cdn:pic(pageContext.request)}/styles/shangpin/images/new/title.png" width="142" height="50"></h2>
            	<!-- 
            	<br />
                    1月12日上新 -->
                <p class="clr"><a href="${ctx }/product/new/list?categoryNo=A01&gender=0">女士<em>WOMAN</em></a><a href="${ctx }/product/new/list?categoryNo=A02&gender=1">男士<em>MAN</em></a></p>
            </li>
        </ul>
    </div>
</rapid:override>
<rapid:override name="down_page">
	
</rapid:override>
<%@ include file="/WEB-INF/pages/common/weixin_common.jsp"%>


