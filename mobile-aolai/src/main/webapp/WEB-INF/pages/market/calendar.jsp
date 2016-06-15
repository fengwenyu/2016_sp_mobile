<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ taglib prefix="my" uri="/WEB-INF/MyElFunction.tld" %>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + path + "/";
%>
<!doctype html>
<html lang="zh-CN">
<head>
<meta charset="utf-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0, user-scalable=no, minimum-scale=1.0, maximum-scale=1.0">
<!-- 开启对web app程序的支持 -->
<meta name="apple-mobile-web-app-capable" content="yes">
<!-- 全屏模式浏览 -->
<meta name="apple-touch-fullscreen" content="yes">
<!-- 改变Safari状态栏的外观 -->
<meta name="apple-mobile-web-app-status-bar-style" content="black">
<title>尚品奥莱-触屏版</title>
<link href="<%=path %>/css/base.css" rel="stylesheet" />
<link href="<%=path %>/css/page/index.css" rel="stylesheet" />
<!-- icon -->
<link rel="apple-touch-icon" href="<%=path %>/images/touch-icon-iphone.png">
<link rel="apple-touch-icon" sizes="72x72" href="<%=path %>/images/touch-icon-ipad.png">
<link rel="apple-touch-icon" sizes="114x114" href="<%=path %>/images/touch-icon-iphone4.png">
<link rel="apple-touch-icon" sizes="144x144" href="<%=path %>/images/touch-icon-newipad.png">
<!-- 启动自定义图片(png, 320X640) -->
<link rel="apple-touch-startup-image" sizes="72x72" href="<%=path %>/images/logo/loading.png">
<script src="<%=path %>/js/core.js" type="text/javascript" charset="utf-8"></script>
<script type="text/javascript" charset="utf-8">
	var ver = "?20121023001";
		loader = SP.core
			.install("<%=path %>/js/zepto.min.js" + ver)
			.using("<%=path%>/js/lazyload.js" + ver)
		    .excute()
		    .using("<%=path%>/js/config.sim.js" + ver)
			.using("<%=path %>/js/comm.js" + ver)
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
<nav class="alMenu">
  <ul>
   <li><a href="<%=path %>/aolaiindex!index?gender=0">女士</a></li>
   <li><a href="<%=path %>/aolaiindex!index?gender=1">男士</a></li>
   <li class="cur"><a href="<%=path %>/presaleaction!presalelist">预售日历</a></li>
  </ul>
</nav>
<div class="alContent">
<div class="alDate_menu">
<s:iterator value="presaleList">
  	<s:if test="%{cssFlag==1}"><a href="<%=path %>/presaleaction!presalelist?startTime=${startTime}&endTime=${endTime}&id=${id}" class="cur">${date }<span>${weekDay }</span></a></s:if>
	<s:else><a href="<%=path %>/presaleaction!presalelist?startTime=${startTime}&endTime=${endTime}&id=${id}">${date }<span>${weekDay }</span></a></s:else>
</s:iterator>
</div>
<section class="alIndex_list alDate_list">
  <s:if test="%{activityList.size!=0}">
  <header style="display:block;background:none;"><b>${showContent }</b></header>
  </s:if>
  <s:if test="%{activityList.size==0}">
  <header style="display:block;background:none; height: 158px;">暂无预售商品上架，敬请期待！</header>
  </s:if>
  <ul class="todayList timeList">
  	<s:iterator value="activityList">
    <li>
      <a title="${activityname }" href="<%=path %>/merchandiseaction!list?activityId=${activityid }&pageType=2&typeFlag=1">
        <img width="145" height="108" alt="" src="<%=path %>/images/e.gif" lazy="${pic }"><%--=basePath images/index/20120901182118408987W310H230.jpg --%>
        <div class="alIndex_list_info">
          <p>${activityname }</p>
          <span><em>${t1} </em>${t2} </span>
        </div>
      </a>
    </li>
  </s:iterator>
  </ul>
</section>
<s:if test="%{activityList.size!=0}">
<div class="alDate_menu">
<s:iterator value="presaleList">
  	<s:if test="%{cssFlag==1}"><a href="<%=path %>/presaleaction!presalelist?startTime=${startTime}&endTime=${endTime}&id=${id}" class="cur">${date }<span>${weekDay }</span></a></s:if>
	<s:else><a href="<%=path %>/presaleaction!presalelist?startTime=${startTime}&endTime=${endTime}&id=${id}">${date }<span>${weekDay }</span></a></s:else>
</s:iterator>
</div>
</s:if>
</div>
<jsp:include page="../common/footer.jsp"></jsp:include>
</body>
</html>
