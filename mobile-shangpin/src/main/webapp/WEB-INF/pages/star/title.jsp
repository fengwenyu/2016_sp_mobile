<%@ page language="java" contentType="text/html; charset=UTF-8" trimDirectiveWhitespaces="true"
    pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/pages/common/include.inc.jsp"%>

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
		<!-- 禁止自动识别5位以上数字为电话 -->
		<meta name="format-detection" content="telephone=no">
		
		<meta http-equiv="Cache-Control" content="no-cache, no-store, must-revalidate" />
		<meta http-equiv="Pragma" content="no-cache" />
		<meta http-equiv="Expires" content="0" />
		
		<title>杨幂发红包</title>
		<link href="${cdn:css(pageContext.request)}/styles/shangpin/css/star-packet/title_base.css${ver}" rel="stylesheet" />
		<link href="${cdn:css(pageContext.request)}/styles/shangpin/css/star-packet/title_red_packet.css${ver}" rel="stylesheet" />
	</head>
	<body>
		<input name="ctx" id="ctx" value="${pageContext.request.contextPath}" type="hidden"/>
		<input id="_iswx" name="_iswx" type="hidden" value="${checkWX}"/>
		<input type="hidden" id="batchNo" name="batchNo" value="${batchNo}"/>
		<input type="hidden" id="source" name="source" value="${source}"/>
		<input type="hidden" name="_shareUrl"  id="_shareUrl"  value="http://m.shangpin.com/star/packet?batchNo=${batchNo}"/>
		<input type="hidden" name="_mainTitle"  id="_mainTitle"  value="杨幂发价值5000万红包，抢疯了！"/>
		<input type="hidden" name="_mainDesc"  id="_mainDesc"  value="杨幂生日发红包，见者有份！"/>
		<input type="hidden" name="_mainImgUrl" id="_mainImgUrl"  value="${cdn:pic(pageContext.request)}/styles/shangpin/images/star-packet/weixin_share.jpg"/>
		<input type="hidden" name="_currentUrl" id="_currentUrl"  value=""/>
		<div class="wapper">
		  <div class="loading-box">
		  	<div class="loading"></div>
		    <p>抢红包的人太多，稍等一下！</p>
		  </div>
		
		  <!--内容区域 start-->
		  <section class="main">
		  	<h2 class="dialogue dialogue1"><img src="${cdn:pic(pageContext.request)}/styles/shangpin/images/star-packet/t0.png"></h2>
		    <!----><ul>
		    	<li class="dialogue dialogue2"><!--<img class="avatar1" src="img/tx1.jpg" width="35" height="35" />--><img src="${cdn:pic(pageContext.request)}/styles/shangpin/images/star-packet/t1.png"></li>
		        <li class="dialogue dialogue3"><img src="${cdn:pic(pageContext.request)}/styles/shangpin/images/star-packet/t2.png"></li>
		        <li class="dialogue dialogue4"><img src="${cdn:pic(pageContext.request)}/styles/shangpin/images/star-packet/t3.png"></li>
		        <li class="dialogue dialogue5"><img src="${cdn:pic(pageContext.request)}/styles/shangpin/images/star-packet/t4.png"></li>
		        <li class="dialogue dialogue6"><img src="${cdn:pic(pageContext.request)}/styles/shangpin/images/star-packet/t5.png"></li>
		        <li class="dialogue dialogue7 red-btn"><img src="${cdn:pic(pageContext.request)}/styles/shangpin/images/star-packet/t6.png"><!--<a class="red-hand-btn"><img src="img/img08.png"></a>--></li>
		    </ul>
		
		    
		    <a class="input-box"><img src="${cdn:pic(pageContext.request)}/styles/shangpin/images/star-packet/img06.png"></a>
		      <div class="keyboard">
		      	  <img src="${cdn:pic(pageContext.request)}/styles/shangpin/images/star-packet/t7.jpg">
		          <div class="spt" id="t8"><img class="spt" id="p2_ipt1" src="${cdn:pic(pageContext.request)}/styles/shangpin/images/star-packet/t8.png"></div>
		          <div class="text" id="myInputText"></div>
		          <a class="hand-btn"><img src="${cdn:pic(pageContext.request)}/styles/shangpin/images/star-packet/img08.png"></a>
		      </div>
		    <div class="red-animate"></div>
		    <audio src="${cdn:pic(pageContext.request)}/styles/shangpin/images/star-packet/tip.wav" id="media" preload=""></audio>
		    
		    
		  </section>
		  <!--内容区域 start-->
		
		</div>
		<script src="${cdn:js(pageContext.request)}/styles/shangpin/js/jquery.min.js" type="text/javascript" charset="utf-8"></script>
		<script src="${cdn:js(pageContext.request)}/styles/shangpin/js/core.js${ver}" type="text/javascript" charset="utf-8"></script>
		<script src="${cdn:js(pageContext.request)}/styles/weixin/jweixin-1.0.0.js${ver}" type="text/javascript" charset="utf-8"></script>
		<script src="${cdn:js(pageContext.request)}/styles/shangpin/js/star-packet/weixin_ready.js${ver}" type="text/javascript" charset="utf-8"></script>
		<script src="${cdn:js(pageContext.request)}/styles/shangpin/js/star-packet/title_red_packet.js${ver}"></script>
	</body>
</html>