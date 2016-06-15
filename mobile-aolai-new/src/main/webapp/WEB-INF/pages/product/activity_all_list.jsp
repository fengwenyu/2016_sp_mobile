<%@ page language="java" contentType="text/html; charset=UTF-8" trimDirectiveWhitespaces="true"
    pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/pages/common/include.inc.jsp"%>
<rapid:override name="custum">
	<link href="${cdn:css(pageContext.request)}/styles/shangpin/css/page/index.css${ver}" rel="stylesheet" />
	<link href="${cdn:css(pageContext.request)}/styles/shangpin/css/add2home.css${ver}" rel="stylesheet" />
	
	<script type="text/javascript" charset="utf-8">
			loader = SP.core
				.install("${cdn:js(pageContext.request)}/styles/shangpin/js/zepto.min.js${ver}")
				.using("${cdn:js(pageContext.request)}/styles/shangpin/js/lazyload.js${ver}")
				.excute()
				.using("${cdn:js(pageContext.request)}/styles/shangpin/js/config.sim.js${ver}")
				.using("${cdn:js(pageContext.request)}/styles/shangpin/js/comm.js${ver}")
				.excute(function(){
					//isHome(true);
					$('#J_m-slider').slider({wrap:".slider-outer",wrapUl:".slider-wrap",wrapStatus:'.slider-status',isLoop:true,isPlay:true});
				});
	</script>
</rapid:override>

<rapid:override name="content">
<c:import url="/nav?navId=5"></c:import>
	<div class="alContent">
		<section class="alIndex_list">
			<header style="background:none">
				<span style="color: #B51111;font-weight: 700;">每天上午10点最新特卖</span>
	  		</header>
	  		<ul class="todayList">
	  			<!-- 当活动为空时 -->
	  			<c:if test="${allNewHotProductList == null}"><li>暂无活动</li></c:if>
	  			
	  			<!-- 当活动不为空时 -->
	  			<c:if test="${allNewHotProductList != null}">
		  			<c:forEach items="${allNewHotProductList}" var="productList">
			  			<li>
			  				<a title="${productList.activityname}" href="<c:url value='/activity/lv2?activityId=${productList.activityid}&typeFlag=1&pageType=1'/>">
								<img width="145" height="108" alt="${productList.activityname}" src="${ctx}/styles/shangpin/images/e.gif" lazy="${productList.pic}">
								<div class="alIndex_list_info">
									<p>
									${productList.brandenname}<br />
									${productList.activityname}
									</p>
									<span><em>${productList.t1}</em>${productList.t2}</span>
								</div>
							 </a>
			  			</li>
		  			</c:forEach>
	  			</c:if>
	  		</ul>
		</section>
	</div>
</rapid:override>
<%@ include file="/WEB-INF/pages/common/base.jsp" %> 