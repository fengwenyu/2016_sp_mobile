<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>

<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + path + "/";
%>
<!doctype html>
<html lang="zh-CN">
<head>
<meta charset="utf-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0, user-scalable=no, minimum-scale=1.0, maximum-scale=1.0">
<link rel="shortcut icon" href="http://pic11.shangpin.com/shangpin/images/logo/favicon.ico" />

<!-- 开启对web app程序的支持 -->
<meta name="apple-mobile-web-app-capable" content="yes">
<!-- 全屏模式浏览 -->
<meta name="apple-touch-fullscreen" content="yes">
<!-- 改变Safari状态栏的外观 -->
<meta name="apple-mobile-web-app-status-bar-style" content="black">
<title>尚品网触屏版_领先的高端时尚和奢侈品购物网站</title>
<link href="<%=path %>/css/base.css" rel="stylesheet" />
<link href="<%=path %>/css/page/list.css" rel="stylesheet" />
<!-- icon -->
<link rel="apple-touch-icon" href="<%=path %>/images/touch-icon-iphone.png">
<link rel="apple-touch-icon" sizes="72x72" href="<%=path %>/images/touch-icon-ipad.png">
<link rel="apple-touch-icon" sizes="114x114" href="<%=path %>/images/touch-icon-iphone4.png">
<link rel="apple-touch-icon" sizes="144x144" href="<%=path %>/images/touch-icon-newipad.png">
<!-- 启动自定义图片(png, 320X640) -->
<link rel="apple-touch-startup-image" sizes="72x72" href="<%=path %>/images/logo/loading.png">
<script src="<%=path%>/js/client.js" type="text/javascript" charset="utf-8"></script>
<script src="<%=path %>/js/core.js" type="text/javascript" charset="utf-8"></script>
<script src="<%=path%>/jquery/jQuery-1.8.0.js" type="text/javascript" charset="utf-8"></script>
<script type="text/javascript" charset="utf-8">
	var ver = "?20121023001";
		loader = SP.core
			.install("<%=path %>/js/zepto.min.js" + ver)
			.using("<%=path%>/js/lazyload.js" + ver)
		    .excute()
		    .using("<%=path%>/js/config.sim.js" + ver)
			.using("<%=basePath%>js/remainTime.js" + ver)
			.excute()
			.using("<%=path %>/js/comm.js" + ver)
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
					
					window.MsgJs.setAlertInfo('{"title":"","msg":"'+res+'","ok_btn":"false","cancle_text":"确定","cancle_func":null}');
				}else if(browser.versions.iPhone==true||browser.versions.iPad==true||browser.versions.ios==true){
					setWebitEvent("iphonealert()","05");
				}
				
			}
			//iphone提示框
			function iphonealert(){
	
				var s='{"title":"","msg":"'+res+'","ok_btn":"false","cancle_text":"确定","cancle_func":null}';
			return s;
			}
		//调用客户端提示框-end
 	function getMore(activityId){
 		var pageIndex = $("#pageIndex").val();
 		$("#haveMore").text("正在加载......");
 		$("#haveMore").removeAttr("href");
 		$.post("<%=basePath%>merchandiseaction!getmore",{
			"pager.offset" : parseInt(pageIndex)+1,
			"activityId" : '${activityId }',
			"activityName" : "${activityName }",
			"typeFlag" : '${typeFlag}',
			"pageType" : '${pageType}',
			"activityId" : '${activityId}'
		}, function(data) {
			if(1 == parseInt(data.code)){
				$("#pageIndex").val(data.pageIndex);
				if(parseInt(data.haveMore) == 0){
					$("#haveMore").remove();
				}else{
					$("#haveMore").text("加载更多");
					$("#haveMore").attr("href", "javascript:getMore('${activityId }','${typeFlag }','${startTime }')");
				}
				for(var i = 0; i < 20; i++){
					var $ul = $(".alBrand_list > ul");
		 			var $li = $("<li/>");
		 	 		var $a = $("<a/>");
		 	 		$a.attr("title", data.merchandiseList[i].brand+data.merchandiseList[i].productname);
		 	 		if(4>parseInt(data.merchandiseList[i].count)&&parseInt(data.merchandiseList[i].count)>0&&1==parseInt(data.openFlag)){
		 	 			var $i = $("<i/>");
		 	 			$i.addClass("saletip salelastTip");
		 	 			var $em = $("<em/>");
		 	 			var $b = $("<b/>");
		 	 			$b.text(data.merchandiseList[i].count+"件");
		 	 			var $br = $("<br/>");
		 	 			$em.append("剩余");
		 	 			$em.append($br);
		 	 			$em.append($b);
		 	 			$i.append($em);
		 	 			$a.append($i);
		 	 		}else if(parseInt(data.merchandiseList[i].count)==0&&1==parseInt(data.openFlag)){
		 	 			var $i = $("<i/>");
		 	 			$i.addClass("saletip saleoutTip");
		 	 			$i.text("售罄");
		 	 			$a.append($i);
		 	 		}
		 	 		var $img = $("<img/>");
		 	 		$img.attr("width", "159");
		 	 		$img.attr("height", "211");
		 	 		$img.attr("src", data.merchandiseList[i].picurl);
		 	 		$a.append($img);
		 	 		if(0==parseInt(data.openFlag)){
		 	 			var $p = $("<p/>");
			 	 		$p.addClass("soonBegin");
			 	 		//$("p").css({"fontSize":"30px" ,"backgroundColor":"#ccc"})；
			 	 		$p.css("display","block");
			 	 		$p.text("即将开始");
			 	 		$a.append($p);
		 	 		} else if(1==parseInt(data.openFlag)){
		 	 			$a.attr("href", "<%=path %>/merchandiseaction!detail?ch=${ch}&categoryno="+data.merchandiseList[i].categoryno+"&goodsid="+data.merchandiseList[i].goodsid+"&typeFlag="+data.typeFlag+"&pageType="+data.pageType+"&activityId="+data.activityId);
		 	 		}
		 	 		var $div = $("<div/>");
		 	 		$div.addClass("alBrand_list_info");
		 	 		var $pInfo = $("<p/>");
		 	 		var $em1 = $("<em/>");
		 	 		$em1.text(data.merchandiseList[i].brand);
		 	 		$pInfo.append($em1);
		 	 		var $em2 = $("<em/>");
		 	 		$em2.text(data.merchandiseList[i].productname);
		 	 		$pInfo.append($em2);
		 	 		$div.append($pInfo);
		 	 		var $span = $("<span/>");
		 	 		if(1==parseInt(data.openFlag)){
		 	 			var $i = $("<i/>");
			 	 		$i.text("¥");
			 	 		$span.append($i);
		 	 		}
		 	 		var $em = $("<em/>");
		 	 		if(1==parseInt(data.openFlag)){
		 	 			$em.text(data.merchandiseList[i].now);
		 	 		}else{
		 	 			$em.text("敬请期待");
		 	 		}
		 	 		$span.append($em);
		 	 		var $del = $("<del/>");
		 	 		$del.text("¥"+data.merchandiseList[i].past);
		 	 		$span.append($del);
		 	 		$div.append($span);
		 	 		$a.append($div);
		 	 		$li.append($a);
		 	 		$ul.append($li);
				}
			} else {
				//alert(data);
			}
		}, "json");
 	}
</script>
</head>
<body>
<jsp:include page="../common/header.jsp"></jsp:include>
<nav class="alNav">
  <ul>
    <li> 
    	<s:if test="pageType=='0'||pageType=='01'||pageType=='02'||pageType=='03'">
	    	<a href="<%=path %>/spindex!index?gender=0&ch=${ch}">首页</a>
	    </s:if>
   	    <s:elseif test="pageType==2">
 	  	   <a href="<%=path %>/presaleaction!presalelist?ch=${ch}">首页</a>
	    </s:elseif>
   	    <s:elseif test="pageType=='1'||pageType=='11'||pageType=='12'||pageType=='13'">
 	  	   <a href="<%=path %>/spindex!index?gender=1&ch=${ch}">首页</a>
	    </s:elseif>
	</li>
    <li>${typeContent }</li>
  </ul>
</nav>
<div class="alContent">
<section class="alBrand_list">
  <header>
  	<h3>${activityName }</h3>
  	<s:if test="%{pageType!=03&&pageType!=13}">
  		<s:if test="%{openFlag==0}">
		    <span class="lxftime3" startTime="${startTime}" nowTime="${systemTime}"><em>&nbsp;</em></span>
		</s:if>
		<s:elseif test="%{openFlag==1}">
		    <span class="lxftime3" endTime="${endTime}" nowTime="${systemTime}"><em>&nbsp;</em></span>
		</s:elseif>
	</s:if>
  </header>
  <ul>
    <s:iterator value="merchandiseList">
     <li>
  	    <s:if test="%{openFlag==0}">
	    	<a title="${brand }${productname}">
		        <img width="159" height="211" alt="" src="<%=path %>/images/e.gif" lazy="${picurl}"><%-- ${picurl}--%>
				  <p class="soonBegin" style="display:block;">即将开始</p>
		        <div class="alBrand_list_info">
		          <p>
		            <em>${brand }</em>
		            <em>${productname}</em>
		          </p>
		          <span><i></i><em>敬请期待</em><del>&yen;${past }</del></span>
		        </div>
	         </a>
	    </s:if>
   	    <s:elseif test="openFlag==1">
 	  	    <a title="${brand }${productname}" href="<%=path %>/merchandiseaction!detail?ch=${ch}&categoryno=${categoryno}&goodsid=${goodsid}&pageType=${pageType}&typeFlag=${typeFlag}&activityId=${activityId}">
		        <s:if test="%{4>count&&count>0}">
				  <i class="saletip salelastTip"><em>剩余<br><b>${count }件</b></em></i>
			    </s:if>
			    <s:elseif test="%{count==0}">
			   	  <i class="saletip saleoutTip">售罄</i>
			    </s:elseif>
		        <img width="159" height="211" alt="" src="<%=path %>/images/e.gif" lazy="${picurl}"><%-- ${picurl}--%>
		        <div class="alBrand_list_info">
		          <p>
		            <em>${brand }</em>
		            <em>${productname}</em>
		          </p>
		          <span><i>&yen;</i><em>${now }</em><del>&yen;${past }</del></span>
		        </div>
	         </a>
	    </s:elseif>
     </li>
    </s:iterator>
  </ul>
  <input type="hidden" id="pageIndex" value="1">
  <input type="hidden" id="activityId" value="${activityId }">
  <s:if test="%{haveMore==1}">
  	<a id="haveMore" class="alList_moreBtn" href="javascript:getMore()">加载更多</a>
  </s:if>
  <s:else>
  	<a class="alList_moreBtn" style="visibility: hidden;"></a>
  </s:else>
</section>
</div>
<jsp:include page="../common/footer.jsp"></jsp:include>
</body>
</html>
