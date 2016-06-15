<%@ page contentType="text/html;charset=UTF-8" import="java.util.*"%>
<%
	String path = request.getContextPath();
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
<!-- 禁止自动识别5位以上数字为电话 -->
<meta name="format-detection" content="telephone=no">
<%@ include file="/WEB-INF/pages/common/message.jsp" %>
<title>尚品奥莱客户端下载</title>

<style type="text/css">
  body,div,dl,dt,dd,ul,ol,li,h1,h2,h3,h4,h5,h6,pre,code,form,fieldset,legend,input,button,textarea,p,blockquote,th,td,figure {padding:0;margin:0;}
fieldset,img{border:0;}
:focus {outline:0;}
address,caption,cite,code,dfn,em,th,var,optgroup {font-style:normal;font-weight:normal;}
h1,h2,h3,h4,h5,h6 {font-size:100%;}
abbr,acronym {border:0;font-variant:normal;}
input,button,textarea,select,optgroup,option {font-family:inherit;font-size:inherit;font-style:inherit;font-weight:inherit;}
code,kbd,samp,tt {font-size:100%;}
body {line-height:1.5;}
ol,ul,li {list-style:none;}
table {border-collapse:collapse;border-spacing:0;}
caption,th {text-align:left;}
sup,sub {font-size:100%;vertical-align:baseline;}
blockquote,q {quotes:none;}
blockquote:before,blockquote:after,q:before,q:after {content:'';content:none;}

section,div,img,a{
  -webkit-tap-highlight-color:rgba(0,0,0,0);
  -webkit-tap-highlight-color:transparent;/* For some Androids */
}

html,body,.wrapper{position:relative;width:100%;height:100%;background:#e6e3e1;}
.slideMain{position:absolute;top:0;left:0;overflow:hidden;width:100%;height:100%;margin:auto;}

.layer{position:absolute;left:0;top:0;width:100%;height:100%;background-repeat:no-repeat;background-size:100%;}

#page .layer0{background:url(images/download/aolai_bg.jpg?201408261460) no-repeat center top;background-size:100%;}
#page .layer1{background-image:url(images/download/aolai_logo.png?201408261460); height:20%;}
#page .layer2{background-image:url(images/download/aolai_mobi.png?201408261460); top:20%;}
#page .layer3{background-image:url(images/download/aolai_btn.png?201408261460); height:10%; top:auto; bottom:2%;}
#page .layer3 a{display:block; width:50%; height:100%;}

.shopLink{position:absolute;z-index:999;width:100%;height:100%;top:0;left:0;pointer-events:none;}
.shopLink a{position:absolute;pointer-events:auto;}
</style>


</head>

<body>
<div class="wrapper">
	<section class="slidePage" id="page">
	  <div class="slideMain">
          <div class="layer layer0"></div>
          <div class="layer layer1"></div>
          <div class="layer layer2"></div>
          <div class="layer layer3 shopLink">
          	<a href="<%=res.getString("shangpinurl")%>download.action?p=101&ch=36&fileName=aolai_36.apk"></a>
            <a href="https://itunes.apple.com/cn/app/id432489082?mt=8" style="right:0;left:auto;"></a>
          </div>
	  </div>
    </section>
</div>

</body>
</html>
