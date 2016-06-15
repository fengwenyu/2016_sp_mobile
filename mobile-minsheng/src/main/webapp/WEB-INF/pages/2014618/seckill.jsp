<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ taglib uri="/tlds/registPrompt" prefix="sprptag"%>
<%@ taglib uri="/tlds/activityName" prefix="spantag"%> 
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + path + "/";
%>
<%@ taglib prefix="s" uri="/struts-tags"%>
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
<link href="<%=path %>/css/base.css" rel="stylesheet" />
<link href="<%=path %>/css/page/anniversary_2014520.css" rel="stylesheet" />

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
			.install("<%=path %>/js/jquery.min.js" + ver)
			.using("<%=path %>/js/j.lazyload.js" + ver)
      .using("<%=path %>/js/xmas.dialogs.js" + ver)
			.excute()
			.using("<%=path %>/js/config.sim.js" + ver)
			.excute()
      .using("<%=path %>/js/2014520/seckill.js" + ver)
			.excute(function(){
				isHome(false);
			});
</script>
</head>

<body>
  <div class="sp_seckill_list">
    <div class="banner">
      <a href="javascript:;" class="pop_btn">
        <img width="59" height="12" alt="" src="<%=path %>/images/2014520/seckill_btn01.gif">
      </a>
      <img src="<%=path %>/images/2014520/seckill_bg01.jpg" width="320" height="194">
    </div>
    <h3>
      <img src="<%=path %>/images/2014520/seckill_152.jpg" width="320" height="31">
    </h3>
    <ul class="brandList clr">
        <li>
          <a title="SALVATORE FERRAGAMO魅惑女士淡香水" href="/html/detail.html">
            <i class="saleOut">售罄</i>
            <img width="159" height="212" alt="魅惑女士淡香水" src="/images/e.gif" lazy="/images/2014520/img004.png">
            <div class="brandList_info">
              <p>
                <em>SALVATORE FERRAGAMO SALVATORE FERRAGAMO</em>
                <em>魅惑女士淡香水</em>
              </p>
              <span><em>&yen;1490</em><del>&yen;2490</del></span>
            </div>
          </a>
        </li>
        <li>
          <a title="SALVATORE FERRAGAMO魅惑女士淡香水" href="/html/detail.html">
            <i class="saleOut">售罄</i>
            <img width="159" height="212" alt="魅惑女士淡香水" src="/images/e.gif" lazy="/images/2014520/img004.png">
            <div class="brandList_info">
              <p>
                <em>SALVATORE FERRAGAMO SALVATORE FERRAGAMO</em>
                <em>魅惑女士淡香水</em>
              </p>
              <span><em>&yen;1490</em><del>&yen;2490</del></span>
            </div>
          </a>
        </li>
        
        <li>
          <a title="SALVATORE FERRAGAMO魅惑女士淡香水" href="/html/detail.html">
            <img width="159" height="212" alt="魅惑女士淡香水" src="/images/e.gif" lazy="/images/2014520/img004.png">
            <div class="brandList_info">
              <p>
                <em>SALVATORE FERRAGAMO SALVATORE FERRAGAMO</em>
                <em>魅惑女士淡香水</em>
              </p>
              <span><em>&yen;1490</em><del>&yen;2490</del></span>
            </div>
          </a>
        </li>
        <li>
          <img src="/images/e.gif" lazy="/images/2014520/noProduct.png" width="159" style="height:auto;" alt="暂无商品">
        </li>
    </ul>
    <h3>
      <img src="/images/2014520/seckill_520.jpg" width="320" height="31">
    </h3>
    <ul class="brandList clr">
        <li>
          <a title="SALVATORE FERRAGAMO魅惑女士淡香水" href="/html/detail.html">
            <i class="saleOut">售罄</i>
            <img width="159" height="212" alt="魅惑女士淡香水" src="/images/e.gif" lazy="/images/2014520/img004.png">
            <div class="brandList_info">
              <p>
                <em>SALVATORE FERRAGAMO SALVATORE FERRAGAMO</em>
                <em>魅惑女士淡香水</em>
              </p>
              <span><em>&yen;1490</em><del>&yen;2490</del></span>
            </div>
          </a>
        </li>
        <li>
          <a title="SALVATORE FERRAGAMO魅惑女士淡香水" href="/html/detail.html">
            <i class="saleOut">售罄</i>
            <img width="159" height="212" alt="魅惑女士淡香水" src="/images/e.gif" lazy="/images/2014520/img004.png">
            <div class="brandList_info">
              <p>
                <em>SALVATORE FERRAGAMO SALVATORE FERRAGAMO</em>
                <em>魅惑女士淡香水</em>
              </p>
              <span><em>&yen;1490</em><del>&yen;2490</del></span>
            </div>
          </a>
        </li>
        
        
    </ul>
    <h3>
      <img src="/images/2014520/seckill_1520.jpg" width="320" height="31">
    </h3>
    <h3>
      <img src="/images/2014520/seckill_3520.jpg" width="320" height="31">
    </h3>
   
  </div> 
   <jsp:include page="../common/footer.jsp"></jsp:include>
</body>
</html>
