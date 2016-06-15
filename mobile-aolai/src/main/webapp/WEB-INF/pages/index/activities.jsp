<%@ page contentType="text/html;charset=UTF-8"%>
<%@ taglib prefix="my" uri="/WEB-INF/MyElFunction.tld" %>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ taglib prefix="cdn" uri="http://m.shangpin.com/cdn" %>
<%String path = request.getContextPath();%>
<!doctype html>
<html lang="zh-CN">

<head>
<meta content-type="text/html" charset="utf-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0, user-scalable=no, minimum-scale=1.0, maximum-scale=1.0">

<!-- 开启对web app程序的支持 -->
<meta name="apple-mobile-web-app-capable" content="yes">
<!-- 全屏模式浏览 -->
<meta name="apple-touch-fullscreen" content="yes">
<!-- 改变Safari状态栏的外观 -->
<meta name="apple-mobile-web-app-status-bar-style" content="black">

<title>尚品奥莱-触屏版</title>
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

<script type="text/javascript" charset="utf-8">
	var ver = "?20121023001";
		loader = SP.core
			.install("${cdn:js(pageContext.request)}/js/zepto.min.js" + ver)
			.using("${cdn:js(pageContext.request)}/js/lazyload.js" + ver)
		    .excute()
		    .using("${cdn:js(pageContext.request)}/js/config.sim.js" + ver)
			.using("${cdn:js(pageContext.request)}/js/comm.js" + ver)
			.excute(function(){
				isHome(false);
				<s:if test="platform=='android'||ch=='100001'">
					JSCallback.onPageLoaded();
				</s:if>
			});

</script>
</head>


<body>

<jsp:include page="../common/header.jsp"></jsp:include>

<nav class="alNav">
  <ul>
    <li>    	
    	<s:if test="pageType==0||pageType==01||pageType==02||pageType==03">
	    	<a href="<%=path %>/">首页</a>
	    </s:if>
   	    <s:elseif test="pageType==2">
 	  	   <a href="<%=path %>/presaleaction!presalelist">首页</a>
	    </s:elseif>
   	    <s:elseif test="pageType==1||pageType==11||pageType==12||pageType==13">
 	  	   <a href="<%=path %>/aolaiindex!index">首页</a>
	    </s:elseif>
	</li>
    <li>${typeContent }</li>
  </ul>
</nav>

<div class="alContent">

<section class="alIndex_list" >
  <header style="background:none"><span style="color: #B51111;font-weight: 700;">每天上午10点最新特卖
<!-- 活动将在<em>2小时15分钟</em>后开始 -->
  </span></header>
  <ul class="todayList">
    <s:if test="null == newActivitiesList || newActivitiesList.size < 1"><li>暂无活动</li></s:if>
    <s:else>
    <s:iterator value="newActivitiesList" id="newActivitiesVO">
    <li>
      <a title="<s:property value="#newActivitiesVO.activityname" />" href="<%=path %>/merchandiseaction!list?activityId=${activityid }&pageType=${pageType }&typeFlag=1">
        <img width="145" height="108" alt="<s:property value="#newActivitiesVO.activityname" />" src="<%=path %>/images/e.gif" lazy="<s:property value="#newActivitiesVO.pic" />">
        <div class="alIndex_list_info">
          <p><s:property value="#newActivitiesVO.brandenname"/><br/><s:property value="#newActivitiesVO.activityname" /></p>
          <%--<s:property value="#newActivitiesVO.t0" />--%>
          <span><em><s:property value="#newActivitiesVO.t1" /></em><s:property value="#newActivitiesVO.t2" /></span>
        </div>
      </a>
    </li>
    </s:iterator>
    </s:else>
  </ul>
</section>

</div>

<jsp:include page="../common/footer.jsp"></jsp:include>

</body>
</html>