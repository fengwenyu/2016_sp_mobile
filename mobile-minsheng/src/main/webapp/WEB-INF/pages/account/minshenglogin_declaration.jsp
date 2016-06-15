<%@ page contentType="text/html;charset=UTF-8"%>
<%@taglib prefix="s" uri="/struts-tags" %>
<%@ taglib uri="/tlds/activityName" prefix="spantag"%> 
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
<title>尚品奥莱-触摸屏版</title>
<link href="<%=path%>/css/base.css" rel="stylesheet" />
<link href="<%=path%>/css/page/login.css" rel="stylesheet" />
<!-- icon -->
<link rel="apple-touch-icon" href="/images/touch-icon-iphone.png">
<link rel="apple-touch-icon" sizes="72x72" href="<%=path%>/images/touch-icon-ipad.png">
<link rel="apple-touch-icon" sizes="114x114" href="<%=path%>/images/touch-icon-iphone4.png">
<link rel="apple-touch-icon" sizes="144x144" href="<%=path%>/images/touch-icon-newipad.png">
<!-- 启动自定义图片(png, 320X640) -->
<link rel="apple-touch-startup-image" sizes="72x72" href="<%=path%>/images/logo/loading.png">
<script src="<%=path%>/js/client.js" type="text/javascript" charset="utf-8"></script>
<script src="<%=path%>/js/clickos.js" type="text/javascript" charset="utf-8"></script>
<script src="<%=path%>/js/core.js" type="text/javascript" charset="utf-8"></script>
<script src="<%=path%>/jquery/jQuery-1.8.0.js" type="text/javascript" charset="utf-8"></script>
<script type="text/javascript" charset="utf-8">
	var ver = "?20121023001";
		loader = SP.core
			.install("<%=path%>/js/zepto.min.js" + ver)
			.using("<%=path%>/js/comm.js" + ver)
			.using("<%=path%>/js/login.js" + ver)
			.excute();
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
		//调用客户端提示框-end
		function goindex(){
			if(browser.versions.android==true){					
				window.SysClientJs.goBack();
			}else if(browser.versions.iPhone==true||browser.versions.iPad==true||browser.versions.ios==true){
				setWebitEvent("goback","SPA01");
			}			
		}
</script>
</head>

<body>
<div id="minshengtitle" style="height:45px">
<div style="background:url(images/title_bg1.png) no-repeat;position:fixed;background-size:100%;z-index:10;position:fixed;height:45px;margin:0 auto;width:100%;text-align:center;color:#fff;font-size:25px;line-height:45px;top:0px;"><a href="javascript:goindex();" style="background:url(images/go_back_btn_n_1.png) no-repeat;background-size:100%;width:60px;height:45px;position:absolute;top: 7px;left: 10px;">&nbsp;</a>尚品网</div>
</div>
<br />
<div class="alContent">
        <fieldset>
            <legend style="font-size:20px;">您的民生账号已经登录，是否授权进入尚品网,进行自动登录！</legend>           
        </fieldset>
        <a href="<%=path%>/spindex!index?gender=0&ch=${ch}" class="alOrder_buyBtn">进入尚品网</a>
</div>
</body>
</html>
