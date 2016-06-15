<%@ page contentType="text/html;charset=UTF-8"%>
<%String path = request.getContextPath();%>

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
<link href="<%=path%>/css/page/2014520/index.css" rel="stylesheet" />

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
<div class="topFix">
	<nav id="filter_box">
	  <ul class="header_nav">
	 	 <li><a href="<%=path%>/activeaction!meetindex?z=0&id=26">尚品周年庆</a></li>
		  <li><a href="<%=path%>/activeaction!meetpage?z=0&type=shoe&p=0&id=28">鞋靴会场</a></li>
		  <li><a href="<%=path%>/activeaction!meetpage?z=0&type=bags&p=1&id=28">箱包会场</a></li>
	      <li><a href="<%=path%>/activeaction!meetpage?z=0&type=women&p=2&id=28">女装会场</a></li>
	      <li><a href="<%=path%>/activeaction!meetpage?z=0&type=men&p=3&id=28">男装会场</a></li>
	      <li class="hover"><a href="<%=path%>/activeaction!meetpage?z=0&type=sport&p=4&id=28">运动会场</a></li>
	      <li><a href="<%=path%>/activeaction!meetpage?z=0&type=ornament&p=5&id=28">配饰会场</a></li>
	      <li ><a href="<%=path%>/activeaction!meetpage?z=0&type=life&p=6&id=28">美妆家居会场</a></li>
	  </ul>
	</nav>
</div>
   ${html }

<jsp:include page="../../common/footer.jsp"></jsp:include>
</body>
</html>
