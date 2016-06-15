<%@ page contentType="text/html;charset=UTF-8"%>
<%@taglib prefix="s" uri="/struts-tags" %>
<%String path = request.getContextPath();%>
<%String ua =request.getHeader("user-agent").toLowerCase();%>
<%String micromessenger = "micromessenger";%>
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

<title>尚品</title>
<link href="<%=path%>/css/base.css" rel="stylesheet" />
<link href="<%=path%>/css/page/anniversary_2014520.css" rel="stylesheet" />

<!-- icon -->
<link rel="apple-touch-icon" href="<%=path%>/images/touch-icon-iphone.png">
<link rel="apple-touch-icon" sizes="72x72" href="<%=path%>/images/touch-icon-ipad.png">
<link rel="apple-touch-icon" sizes="114x114" href="<%=path%>/images/touch-icon-iphone4.png">
<link rel="apple-touch-icon" sizes="144x144" href="<%=path%>/images/touch-icon-newipad.png">
<!-- 启动自定义图片(png, 320X640) -->
<link rel="apple-touch-startup-image" sizes="72x72" href="/images/logo/loading.png">
<script src="<%=path%>/js/client.js" type="text/javascript" charset="utf-8"></script>
<script src="<%=path%>/js/core.js" type="text/javascript" charset="utf-8"></script>
<script type="text/javascript" charset="utf-8">
	var ver = "?20121023001";
		loader = SP.core
			.install("<%=path%>/js/jquery.min.js" + ver)
			.using("<%=path%>/js/j.lazyload.js" + ver)
			.excute()
			.using("<%=path%>/js/config.sim.js" + ver)
			.excute()
      		.using("<%=path%>/js/2014520/list.js" + ver)
			.excute(function(){
				isHome(false);
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
		function goback(){
			var islogin=${islogin};
			if(browser.versions.android==true){					
				
				if(islogin==1){
					window.SysClientJs.goBack();
					<%
					request.getSession().setAttribute("islogin","0");
					%>
				}else{
					window.SysClientJs.toLastPage();
				}
					//
			}else if(browser.versions.iPhone==true||browser.versions.iPad==true||browser.versions.ios==true){
				if(islogin==1){
					setWebitEvent("goback","SPA01");
					<%
						request.getSession().setAttribute("islogin","0");
					%>
				}else{
					window.history.go(-1);
				}
				
			}			
		}
	//调用客户端提示框-end
	
</script>
</head>

<body>
<div id="minshengtitle" style="height:45px">
<div style="background:url(images/title_bg1.png) no-repeat;position:fixed;background-size:100%;z-index:10;height:45px;margin:0 auto;width:100%;text-align:center;color:#fff;font-size:25px;line-height:45px;top:0px;"><a href="javascript:goback();" style="background:url(images/go_back_btn_n_1.png) no-repeat;background-size:100%;width:60px;height:45px;position:absolute;top: 7px;left: -4px;">&nbsp;</a>尚品网</div>
</div>
<nav id="filter_box">
  <ul class="header_nav">
      <li><a href="<%=path%>/activeaction!meetpage?type=big&p=0&id=${id}">国际大牌</a></li>
      <li><a href="<%=path%>/activeaction!meetpage?type=women&p=1&id=${id}">女装秀</a></li>
      <li><a href="<%=path%>/activeaction!meetpage?type=men&p=2&id=${id}">型男志</a></li>
      <li class="hover"><a href="<%=path%>/activeaction!meetpage?type=shoe&p=3&id=${id}">鞋靴控</a></li>
      <li><a href="<%=path%>/activeaction!meetpage?type=bags&p=4&id=${id}">爱箱包</a></li>
      <li><a href="<%=path%>/activeaction!meetpage?type=sport&p=5&id=${id}">运动狂</a></li>
      <li><a href="<%=path%>/activeaction!meetpage?type=ornament&p=6&id=${id}">点睛配</a></li>
      <li><a href="<%=path%>/activeaction!meetpage?type=life&p=7&id=${id}">享生活</a></li>
  </ul>
</nav>
   ${html }
 


 <jsp:include page="../common/footer.jsp"></jsp:include>
</body>
</html>
