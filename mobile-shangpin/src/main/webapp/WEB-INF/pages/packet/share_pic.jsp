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
		<title>${account.nickName}的二维码</title>
		<link href="${cdn:js(pageContext.request)}/styles/shangpin/css/base.css" rel="stylesheet" />
		
		<style>
			body .alContent{margin:0 auto; width:640px;}
			.canvasBox{position:relative; width:640px;}
			.wechatData{position:absolute; width:100%; top:0; left:0;}
			.wechatData dl{display:block; text-align:center; margin-top:28%;}
			.wechatData dl dd{width:130px; height:130px; display:inline-block; border:6px solid #fff; overflow:hidden;}
			.wechatData h3{height:50px; line-height:50px; text-align:center; color:#c62026; font-weight:normal; font-size:24px;}
			.wechatData ul{display:block; text-align:center; margin-top:43%;}
			.wechatData ul li{width:260px; height:260px; display:inline-block; border:8px solid #fff; overflow:hidden;}
			.wechatData p{width:80%; margin:10% auto 0; line-height:24px; font-size:24px;}
			.picBox{text-align:center; width:100%;}
		</style>
	</head>
	
	<body>
		<div class="alContent">
<!-- 			<div class="canvasBox" style="display: none;"> -->
<%-- 		    	<img src="${ctx}/styles/shangpin/images/wechatRed/sharebg.jpg" /> --%>
<!-- 		        <div class="wechatData"> -->
<%-- 		            <dl><dd><img id="logoImg" data-url="" src="${showHeadImageURL}" /></dd></dl> --%>
<%-- 		            <h3>${account.nickName }</h3> --%>
<%-- 		            <ul><li><img id="qrCodeImg" data-url="" src="${showQrcodeURL}" /></li></ul> --%>
<!-- 		            <p>长按以上二维码，点击识别图中二维码，关注微<br />信公众号就可以为自己/为好友赠送红包啦！</p> -->
<!-- 		        </div> -->
<!-- 		    </div> -->
		</div>
		
		<div class="picBox" style="display: block;">
			<input type="hidden" id="openId" name="openId" value="${account.openId}"/>
			<input type="hidden" id="ctx" name="ctx" value="${basePath}"/>
			<input id="_iswx" name="_iswx" type="hidden" value="${checkWX}"/>
			<input type="hidden" name="_shareUrl"  id="_shareUrl"  value="${basePath}/packet/share?openId=${account.openId}"/>
			<input type="hidden" name="_mainTitle"  id="_mainTitle"  value="${account.nickName}的红包二维码"/>
			<input type="hidden" name="_mainDesc"  id="_mainDesc"  value="红包在手扫码我有，尚品网520千万红包大赠送啦~"/>
			<input type="hidden" name="_mainImgUrl" id="_mainImgUrl"  value="${cdn:pic(pageContext.request)}/styles/shangpin/images/wechatRed/share_code.jpg"/>
			<img alt="" src="${showPic}">
		</div>
		
		<script type="text/javascript" src="${cdn:js(pageContext.request)}/styles/shangpin/js/jquery.min.js"></script>
		<script type="text/javascript" src="${cdn:js(pageContext.request)}/styles/shangpin/js/core.js"></script>
		<script type="text/javascript" src="${cdn:js(pageContext.request)}/styles/weixin/jweixin-1.0.0.js"></script>
		<script type="text/javascript" src="${cdn:js(pageContext.request)}/styles/shangpin/js/weixin/weixin_ready.js"></script>
<%-- 		<script type="text/javascript" src="${ctx}/styles/shangpin/js/html2canvas.js"></script> --%>
		<script type="text/javascript">
// 		$(function(){
// 			html2canvas(document.querySelectorAll('.canvasBox'), {
// 				allowTaint: true,
// 				taintTest: false,
// 				onrendered: function(canvas) {
// 					var openId = $("#openId").val();
// 					canvas.id = "mycanvas";
// 					//document.body.appendChild(canvas);
// 					//生成base64图片数据
// 					var dataUrl = canvas.toDataURL();
// 					var newImg = document.createElement("img");
// 					$.ajax({
// 						type:'post',
// 						url:'../packet/convert',
// 						data:{openId:openId, image: dataUrl},
// 						async:false,
// 						dataType:'json',
// 						success:function(data){
// 							newImg.src = data.resultImage;
// 							$("#_mainImgUrl").val(data.resultImage);
// 						}
// 					});
// 					//newImg.src =  dataUrl;
// 					//document.body.appendChild(newImg);
// 					$(".picBox").html(newImg);
// 					$('.alContent').hide();
// 					setTimeout(function(){
// 						$.get("../packet/delete?openId=" + openId,function(data){});
// 					}, 5000);
// 				}
// 			});
// 		});
		</script>
	</body>
</html>
