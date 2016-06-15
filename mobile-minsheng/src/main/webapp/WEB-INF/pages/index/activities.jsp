<%@ page contentType="text/html;charset=UTF-8"%>

<%@ taglib prefix="s" uri="/struts-tags"%>
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

<title>尚品网触屏版_领先的高端时尚和奢侈品购物网站</title>
<link href="<%=path%>/css/base.css" rel="stylesheet" />
<link href="<%=path%>/css/page/index.css" rel="stylesheet" />
<link href="<%=path%>/css/add2home.css" rel="stylesheet" />
<link rel="shortcut icon" href="http://pic11.shangpin.com/shangpin/images/logo/favicon.ico" />

<!-- icon -->
<link rel="apple-touch-icon" href="/images/touch-icon-iphone.png">
<link rel="apple-touch-icon" sizes="72x72" href="<%=path%>/images/touch-icon-ipad.png">
<link rel="apple-touch-icon" sizes="114x114" href="<%=path%>/images/touch-icon-iphone4.png">
<link rel="apple-touch-icon" sizes="144x144" href="<%=path%>/images/touch-icon-newipad.png">
<!-- 启动自定义图片(png, 320X640) -->
<link rel="apple-touch-startup-image" sizes="72x72" href="<%=path%>/images/logo/loading.png">

<script src="<%=path%>/js/core.js" type="text/javascript" charset="utf-8"></script>
<script src="<%=path%>/js/client.js" type="text/javascript" charset="utf-8"></script>
<script src="<%=path%>/jquery/jQuery-1.8.0.js" type="text/javascript" charset="utf-8"></script>
<script type="text/javascript" charset="utf-8">
	var ver = "?20121023001";
		loader = SP.core
			.install("<%=path%>/js/zepto.min.js" + ver)
			.using("<%=path%>/js/lazyload.js" + ver)
		    .excute()
		    .using("<%=path%>/js/config.sim.js" + ver)
			.using("<%=path%>/js/comm.js" + ver)
			.excute(function(){
				isHome(false);
				<s:if test="#session['ch'] == '100001'">
					JSCallback.onPageLoaded();
				</s:if>
			});
			//调用客户端提示框-start
			var res;
			window.alert=function(msg){
				res=msg;
				if(browser.versions.android==true){
					
					window.MsgJs.setAlertInfo('{"title":"提示","msg":"'+res+'","ok_btn":"false","cancle_text":"确定","cancle_func":null}');
				}else if(browser.versions.iPhone==true||browser.versions.iPad==true||browser.versions.ios==true){
					setWebitEvent("iphonealert()","05");
				}
				
			}
			//iphone提示框
			function iphonealert(){
	
				var s='{"title":"提示","msg":"'+res+'","ok_btn":"false","cancle_text":"确定","cancle_func":null}';
			return s;
			}
		//调用客户端提示框-end

</script>
</head>


<body>
<jsp:include page="../common/header.jsp"></jsp:include>

<nav class="alNav">
  <ul>
    <li>    	
    	<s:if test="pageType==0||pageType==01||pageType==02||pageType==03">
	    	<a href="<%=path %>/spindex!index?gender=0&ch=${ch}">首页</a>
	    </s:if>
   	    <s:elseif test="pageType==2">
 	  	   <a href="<%=path %>/presaleaction!presalelist?ch=${ch}">首页</a>
	    </s:elseif>
   	    <s:elseif test="pageType==1||pageType==11||pageType==12||pageType==13">
 	  	   <a href="<%=path %>/spindex!index?gender=1&ch=${ch}">首页</a>
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
      <a title="<s:property value="#newActivitiesVO.activityname" />" href="<%=path %>/merchandiseaction!list?ch=${ch}&activityId=${activityid }&pageType=${pageType }&typeFlag=1">
        <img width="145" height="108" alt="<s:property value="#newActivitiesVO.activityname" />" src="<%=path %>/images/e.gif" lazy="<s:property value="#newActivitiesVO.pic" />">
        <div class="alIndex_list_info">
          <p><s:property value="#newActivitiesVO.activityname" /></p>
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