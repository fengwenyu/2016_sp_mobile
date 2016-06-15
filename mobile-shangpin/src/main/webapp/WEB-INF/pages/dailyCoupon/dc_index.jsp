<%@ page language="java" contentType="text/html; charset=UTF-8"
	trimDirectiveWhitespaces="true" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/pages/common/include.inc.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<rapid:override name="title">
	天天抢券
</rapid:override>
<rapid:override name="custum">
	<link rel="stylesheet" href="${cdn:css(pageContext.request)}/styles/shangpin/css/page/dailyCoupon/base.css${ver}">
	<link rel="stylesheet" href="${cdn:css(pageContext.request)}/styles/shangpin/css/page/font-css/font-awesome.min.css${ver}">
	<link href="${cdn:css(pageContext.request)}/styles/shangpin/css/page/dailyCoupon/swiper.min.css${ver}" rel="stylesheet" />	
	<link href="${cdn:css(pageContext.request)}/styles/shangpin/css/page/dailyCoupon/index.css${ver}" rel="stylesheet" />
</rapid:override>

<rapid:override name="content">
	
	<rapid:override name="header">
		<c:if test="${!checkWX&&!checkAPP&&!checkWeibo}">
			<div class="topFix">
			    <section id="topMenu">
			        <div class="topBack" >
			        <a href="javascript:history.go(-1);" class="backBtn">&nbsp;</a>
			      	  <span class="top-title">
							 天天抢券
						 </span>
			        <ul class="alUser_icon clr">
			          <li><a href="<c:url value='/index'/>"><img src="${cdn:pic(pageContext.request)}/styles/shangpin/images/home_icon.png" width="25" height="25" alt="首页"></a></li>
			        </ul>
			        </div>
			    </section>
			</div>
		</c:if>
	</rapid:override>
	<!-- 轮播图 -->
	<c:import url="/dailyCoupon/gallery"/>
	<!-- 天天抢券列表  -->
	<c:import url="/dailyCoupon/list?pageIndex=0&pageSize=20"/>
	
	<rapid:override name="static_file">
		<script src="${cdn:js(pageContext.request)}/styles/shangpin/js/swiper.min.js${ver}" type="text/javascript" charset="utf-8"></script>
		<script src="${cdn:js(pageContext.request)}/styles/shangpin/js/fixed_public.js${ver}" type="text/javascript" charset="utf-8"></script>
		<script src="${cdn:js(pageContext.request)}/styles/shangpin/js/dailyCoupon/index_old.js${ver}" type="text/javascript" charset="utf-8"></script>
	</rapid:override>
</rapid:override>

<%@ include file="/WEB-INF/pages/common/star_packet_base.jsp" %>

