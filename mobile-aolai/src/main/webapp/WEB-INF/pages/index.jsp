<%@ page language="java" import="java.util.*,com.shangpin.mobileAolai.common.alipay.util.Base64" pageEncoding="utf-8"%>
<%@ page contentType="text/html;charset=UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ taglib prefix="my" uri="/WEB-INF/MyElFunction.tld" %>
<%@ taglib prefix="cdn" uri="http://m.shangpin.com/cdn" %>

<%String path = request.getContextPath();%>
<!doctype html>
<html lang="zh-CN">

<head>
<%--
<script>window.location.href ="http://m.shangpin.com/";</script>
 --%>
<meta content-type="text/html" charset="utf-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0, user-scalable=no, minimum-scale=1.0, maximum-scale=1.0">

<!-- 开启对web app程序的支持 -->
<meta name="apple-mobile-web-app-capable" content="yes">
<!-- 全屏模式浏览 -->
<meta name="apple-touch-fullscreen" content="yes">
<!-- 改变Safari状态栏的外观 -->
<meta name="apple-mobile-web-app-status-bar-style" content="black">

<!-- 改变Safari状态栏的外观
<meta name="apple-itunes-app" content="app-id=432489082"/> -->

<!-- SEO优化 start  -->
<title>尚品奥莱-触屏版</title>
<meta name="keywords" content="尚品网,尚品网官网,奢侈品,奢侈品正品,设计师品牌,时尚购物网站,高端时尚,ShangPin" /> 
<meta name="description" content="尚品网奢侈品网站是国内领先的高端时尚和奢侈品购物网站，也是中国首家获得品牌授权、正价销售欧美高端时尚和奢侈品牌应季新品的购物网站，致力于将“全球最好的时尚带回中国”，尚品网为中国消费者提供与国际比肩的高端奢侈品时尚体验！" />
<!-- SEO优化 end  -->
<link href="${cdn:css(pageContext.request)}/css/base.css" rel="stylesheet" />
<link href="${cdn:css(pageContext.request)}/css/page/index.css" rel="stylesheet" />
<link href="${cdn:css(pageContext.request)}/css/add2home.css" rel="stylesheet" />

<!-- icon -->
<link rel="apple-touch-icon" href="${cdn:pic(pageContext.request)}/images/touch-icon-iphone.png">
<link rel="apple-touch-icon" sizes="72x72" href="${cdn:pic(pageContext.request)}/images/touch-icon-ipad.png">
<link rel="apple-touch-icon" sizes="114x114" href="${cdn:pic(pageContext.request)}/images/touch-icon-iphone4.png">
<link rel="apple-touch-icon" sizes="144x144" href="${cdn:pic(pageContext.request)}/images/touch-icon-newipad.png">
<!-- 启动自定义图片(png, 320X640) -->
<link rel="apple-touch-startup-image" sizes="72x72" href="${cdn:pic(pageContext.request)}/images/logo/loading.png">
<script src="${cdn:js(pageContext.request)}/js/core.js" type="text/javascript" charset="utf-8"></script>
<%-- <script type="text/javascript" name="baidu-tc-cerfication" src="http://apps.bdimg.com/cloudaapi/lightapp.js#98cc078912ece91b39f84d2aeeae81ed"></script>
<script type="text/javascript">window.bd && bd._qdc && bd._qdc.init({app_id: '96b15cce080cf040d0e00fd7'});</script>
 --%>
<script type="text/javascript" charset="utf-8">
	var ver = "?20121023001";
		loader = SP.core
		.install("${cdn:js(pageContext.request)}/js/zepto.min.js" + ver)
		.using("${cdn:js(pageContext.request)}/js/lazyload.js" + ver)
		.excute()
		.using("${cdn:js(pageContext.request)}/js/config.sim.js" + ver)
		.using("${cdn:js(pageContext.request)}/js/css3.js" + ver)
		//.using("${cdn:js(pageContext.request)}/js/slideLayer.js" + ver)
		.using("${cdn:js(pageContext.request)}/js/add2home.js" + ver)
		.using("${cdn:js(pageContext.request)}/js/remainTime.js" + ver)
		.excute()
		.using("${cdn:js(pageContext.request)}/js/comm.js" + ver)
		.using("${cdn:js(pageContext.request)}/js/comm.js" + ver)
		.using("${cdn:js(pageContext.request)}/js/downloadAppShow.js" + ver)
		.excute(function(){
			isHome(true);	
			$(function(){
				if(1 == ${gender}){
				  $("#li2").attr("class","cur");
				  $("#li1").attr("class","");
			    }else{
				  $("#li1").attr("class","cur");
				  $("#li2").attr("class","");
			    }
				//$('#J_m-slider').slider({wrap:".slider-outer",wrapUl:".slider-wrap",wrapStatus:'.slider-status',isLoop:true,isPlay:true});
			});
			
			<s:if test="platform=='android'||ch==''">
			JSCallback.onPageLoaded();
			</s:if>
			
		});
		
</script>
</head>


<body>
<!-- 顶部下载APP -->
    <div class="headApp" red_url=""><img src="<%=path%>/images/download_top.png"></div>
<s:if test="venueEntranceVO!=null ">
	<div style="text-align:center;">
		<a  title="${venueEntranceVO.title}" href="${venueEntranceVO.url}" style="height:100px;">
		              <img width="320" height="100"  src="${venueEntranceVO.imgUrl}" ></a>
	</div>
</s:if>


<jsp:include page="common/header.jsp"></jsp:include>

<nav class="alMenu">
  <ul>
   <li class="cur" id="li1"><a href="<%=path%>/aolaiindex!index?gender=0">女士</a></li>
   <li id="li2"><a href="<%=path%>/aolaiindex!index?gender=1">男士</a></li>
   <li id="li3"><a href="<%=path%>/presaleaction!presalelist">预售日历</a></li>
  </ul>
</nav>

<div class="alContent">

<!-- 焦点图 Start -->
<!--
<div class="content" id="J_m-slider">
	<div class="slider-outer">
    	<ul class="slider-wrap">
    	  <s:iterator value="topicCarouselList" id="carouselVO">
            <li>
              <a href="<%=path %>/merchandiseaction!list?activityId=${topicid }&pageType=${gender }&typeFlag=1"><img alt="<s:property value="#carouselVO.name" />" height="160" src="<s:property value="#carouselVO.pic" />" width="320"></a>
            </li>
          </s:iterator>
		</ul>
	</div>
    <div class="slider-status" ></div>
</div>
 -->
<!-- 焦点图 End -->

<section class="alIndex_list">
  
  <!-- 
  <header><h3>最新特卖</h3>
  <s:if test="map['flag'] == 'true'">
    <span class="lxftime1"  startTime="${map['startTime']}" nowTime="${map['nowTime']}"><em></em></span>
  </s:if>
  <s:else>
    <span style="font-weight: 700;color: #B51111;">每天上午10点最新特卖</span>
  </s:else>
  </header>
   -->
  
  <ul class="todayList">

    <s:iterator value="newActivitiesList" id="newActivitiesVO">
    <li>
      <a title="<s:property value="#newActivitiesVO.activityname" />" href="<%=path %>/merchandiseaction!list?activityId=${activityid }&pageType=${gender }1&typeFlag=1">
        <img width="145" height="108" alt="<s:property value="#newActivitiesVO.activityname" />" src="<%=path %>/images/e.gif" lazy="<s:property value="#newActivitiesVO.pic" />">
        <div class="alIndex_list_info">
          <p><s:property value="#newActivitiesVO.brandenname"/><br/><s:property value="#newActivitiesVO.activityname" /></p>
          <%--<s:property value="#newActivitiesVO.t0" /> --%>
          <span><em><s:property value="#newActivitiesVO.t1" /></em><s:property value="#newActivitiesVO.t2" /></span>
        </div>
      </a>
    </li>
    </s:iterator>

  </ul>
  <a class="alList_moreBtn" href="<%=path%>/aolaiindex!newactivities?gender=${gender}&pageType=${gender }1">查看全部</a>
</section>

<section class="alIndex_list">

  <header><h3>限时特卖</h3>
   <s:if test="map['_flag'] == 'true'">
    <span class="lxftime2"  nowTime="${map['nowTime'] }" endTime="${map['_endTime'] }"><em></em></span>
  </s:if>
  <s:else>
    <span >活动已经结束</span>
  </s:else>
  </header>

  <ul class="todayList timeList">

    <s:iterator value="limitActivitiesList" id="limitActivitiesVO">
    <li>
      <a title='<s:property value="#limitActivitiesVO.activityname" />' href="<%=path %>/merchandiseaction!list?activityId=${activityid }&pageType=${gender }2&typeFlag=1">
        <img width="145" height="108" alt='<s:property value="#limitActivitiesVO.activityname" />' src="<%=path %>/images/e.gif" lazy="<s:property value="#limitActivitiesVO.pic" />">
        <div class="alIndex_list_info">
          <p><s:property value="#limitActivitiesVO.brandenname"/><br/><s:property value="#limitActivitiesVO.activityname" /></p>
          <%--<s:property value="#limitActivitiesVO.t0" /> --%>
          <span><em><s:property value="#limitActivitiesVO.t1" /></em><s:property value="#limitActivitiesVO.t2" /></span>
        </div>
      </a>
    </li>
    </s:iterator>

  </ul>
<!-- 
  <a class="alList_moreBtn" href="<%=path%>/aolaiindex!limitActivities?gender=${gender}">查看全部</a>
-->
</section>

</div>

<jsp:include page="common/footer.jsp"></jsp:include>

</body>

</html>