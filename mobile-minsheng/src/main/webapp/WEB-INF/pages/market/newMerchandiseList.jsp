<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ taglib uri="/tlds/activityPicture" prefix="spaptag"%>
<%@ taglib uri="/tlds/activityName" prefix="spantag"%> 
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + path + "/";
%>
<!doctype html>
<html lang="zh-CN">
<head>
<link rel="shortcut icon" href="http://pic11.shangpin.com/shangpin/images/logo/favicon.ico" />
<meta charset="utf-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0, user-scalable=no, minimum-scale=1.0, maximum-scale=1.0">
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
				var haveMore=${haveMore};
				if(haveMore=='0'){
					$(".alList_moreBtn").css("display", "none");
				}
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
 	function getMore(){
 		var pageIndex = $("#pageIndex").val();
 		$("#haveMore").text("正在加载......");
 		$("#haveMore").removeAttr("href");
 		$.post("<%=basePath%>merchandiseaction!getSpNewProductsMore",{
			"pager.offset" : parseInt(pageIndex)+1,
			"categoryid":'A01'
		}, function(data) {
			if(1 == parseInt(data.code)){
				$("#pageIndex").val(data.pageIndex);
				if(parseInt(data.haveMore) == 0){
					$("#haveMore").remove();
					$(".alList_moreBtn").css("display", "none");
				}else{
					$("#haveMore").text("加载更多");
					$("#haveMore").attr("href", "javascript:getMore()");
				}
				for(var i = 0; i < 20; i++){
					var $ul = $(".alBrand_list > ul");
		 			var $li = $("<li/>");
		 	 		var $a = $("<a/>");
		 	 		$a.attr("title", data.merchandiseList[i].brandname + data.merchandiseList[i].productname);
		 	 		if(data.merchandiseList[i].count==0){
		 	 			var $i = $("<i/>");
		 	 			$i.addClass("saletip saleoutTip");
		 	 			$i.text("售罄");
		 	 			$a.append($i);
		 	 		}

		 	 		var $img = $("<img/>");
		 	 		$img.attr("width", "159");
		 	 		$img.attr("height", "211");
		 	 		$img.attr("src", data.merchandiseList[i].pic);
		 	 		$a.append($img);
		 	 		$a.attr("href", "<%=path %>/merchandiseaction!spdetail?ch=${ch}&productid="+data.merchandiseList[i].productid);
		 	 		var $div = $("<div/>");
		 	 		$div.addClass("alBrand_list_info");
		 	 		var $pInfo = $("<p/>");
		 	 		var $em1 = $("<em/>");
		 	 		$em1.text(data.merchandiseList[i].brandname);
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
		 	 		
		 	 		$span.append($em);
		 	 		var price;
		 	 		if(data.merchandiseList[i].status=="000100"){
		 	 			price = data.merchandiseList[i].specialprice[0];
		 	 		}else{
		 	 			price = data.merchandiseList[i].prices[data.merchandiseList[i].priceindex];
		 	 		}
		 	 		$span.text("¥"+price);
		 	 		$div.append($span);
		 	 		$a.append($div);
		 	 		$li.append($a);
		 	 		$ul.append($li);
				}
			} else {
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
        <a href="<%=path%>/spindex!index?gender=0&ch=${ch}">首页</a>
	</li>
    <li>新品</li>
  </ul>
</nav> 
<div class="alContent">
<section class="alBrand_list">
   <jsp:include page="../common/productList.jsp"></jsp:include>
  <input type="hidden" id="pageIndex" value="1">
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
