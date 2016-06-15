0<%@ page language="java" import="java.util.*,com.shangpin.mobileShangpin.common.alipay.util.Base64" pageEncoding="utf-8"%>
<%@ page contentType="text/html;charset=UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="s" uri="/struts-tags"%>

<%String path = request.getContextPath();%>
<!doctype html>
<html lang="zh-CN">
<head>
<meta charset="utf-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0, user-scalable=no, minimum-scale=1.0, maximum-scale=1.0">

<!-- 开启对web app程序的支持 -->
<meta name="apple-mobile-web-app-capable" content="yes">
<!-- 全屏模式浏览 --><meta name="apple-touch-fullscreen" content="yes">
<!-- 改变Safari状态栏的外观 -->
<meta name="apple-mobile-web-app-status-bar-style" content="black">

<title>尚品网触屏版_领先的高端时尚和奢侈品购物网站</title>
<link href="<%=path%>/css/base.css" rel="stylesheet" />
<link href="<%=path%>/css/page/list.css" rel="stylesheet" />
<link href="<%=path%>/css/page/subject.css" rel="stylesheet" />

<!-- icon -->
<link rel="apple-touch-icon" href="<%=path%>/images/touch-icon-iphone.png">
<link rel="apple-touch-icon" sizes="72x72" href="<%=path%>/images/touch-icon-ipad.png">
<link rel="apple-touch-icon" sizes="114x114" href="<%=path%>/images/touch-icon-iphone4.png">
<link rel="apple-touch-icon" sizes="144x144" href="<%=path%>/images/touch-icon-newipad.png">
<!-- 启动自定义图片(png, 320X640) -->
<link rel="apple-touch-startup-image" sizes="72x72" href="/images/logo/loading.png">
<script src="<%=path%>/jquery/jQuery-1.8.0.js" type="text/javascript" charset="utf-8"></script>
<script src="<%=path%>/js/core.js" type="text/javascript" charset="utf-8"></script>
<script type="text/javascript" charset="utf-8">
	var ver = "?20121023001";
		loader = SP.core
			.install("<%=path%>/js/zepto.min.js" + ver)
			.using("<%=path%>/js/dialogs.js" + ver)
			.using("<%=path%>/js/comm.js" + ver)
			.excute(function(){
				$(".fillPord select").each(function(){
                    var obj = $(this),
						objTxt = obj.siblings("a");
					
					obj.change(function(){
						objTxt.html(obj.val());
					});
                });
			});
</script>

</head>

<body>

<jsp:include page="../common/header.jsp"></jsp:include>
<!--  
<nav class="alNav">
  <ul>
    <li><a href="<%=path%>/spindex!index?gender=0">首页</a></li>
    <li>最新特卖</li>
  </ul>
</nav>
-->
<div class="alContent">

<section class="alBrand_list alSubject">
  <div class="banner">
  	<img src="<%=path%>/images/subject/banner.png" width="320" height="160">
    <a href="#" class="btn share" target="_blank">关注</a>
    <a href="#" class="btn" target="_blank">分享</a>
  </div>
  <ul class="fillPord">
  	<li>
    <a href="#">品类</a>
    <select>
    	<option>品类</option>
        <option>鞋靴</option>
    </select>
    </li>
    <li>
    <a href="#">品牌</a>
    <select>
    	<option>品牌</option>
        <option>波司登</option>
    </select>
    </li>
    <li>
    <a href="#">价格</a>
    <select>
    	<option>价格</option>
    </select>
    </li>
    <li>
    <a href="#">尺码</a>
    <select>
    	<option>尺码</option>
    </select>
    </li>
  </ul>
  <ul>
    <li>
      <a title="PRADA S.TENS 鞋履集合场" href="/html/detail.html">
        <img width="159" height="211" alt="" src="/images/list/prodimg01.png">
        <div class="alBrand_list_info">
          <p>
            <em>PRADA S.TENS PRADA S.TENS</em>
            <em>鞋履集合场</em>
          </p>
          <span><i>&yen;</i><em>3468</em><del>&yen;5780</del></span>
        </div>
      </a>
    </li>
    <li>
      <a title="PRADA S.TENS 鞋履集合场" href="javascript:;">
        <img width="159" height="211" alt="" src="/images/list/prodimg01.png">
        <div class="alBrand_list_info">
          <p>
            <em>PRADA S.TENS PRADA S.TENS</em>
            <em>鞋履集合场</em>
          </p>
          <span><i>&yen;</i><em>3468</em><del>&yen;5780</del></span>
        </div>
      </a>
    </li>
    <li>
      <a title="PRADA S.TENS 鞋履集合场" href="#">
        <img width="159" height="211" alt="" src="/images/list/prodimg01.png">
        <div class="alBrand_list_info">
          <p>
            <em>PRADA S.TENS PRADA S.TENS</em>
            <em>鞋履集合场</em>
          </p>
          <span><i>&yen;</i><em>3468</em><del>&yen;5780</del></span>
        </div>
      </a>
    </li>
    <li>
      <a title="PRADA S.TENS 鞋履集合场" href="#">
        <img width="159" height="211" alt="" src="/images/list/prodimg01.png">
        <div class="alBrand_list_info">
          <p>
            <em>PRADA S.TENS PRADA S.TENS</em>
            <em>鞋履集合场</em>
          </p>
          <span><i>&yen;</i><em>3468</em><del>&yen;5780</del></span>
        </div>
      </a>
    </li>
    <li>
      <a title="PRADA S.TENS 鞋履集合场" href="#">
        <img width="159" height="211" alt="" src="/images/list/prodimg01.png">
        <div class="alBrand_list_info">
          <p>
            <em>PRADA S.TENS PRADA S.TENS</em>
            <em>鞋履集合场</em>
          </p>
          <span><i>&yen;</i><em>3468</em><del>&yen;5780</del></span>
        </div>
      </a>
    </li>
    <li>
      <a title="PRADA S.TENS 鞋履集合场" href="#">
        <img width="159" height="211" alt="" src="/images/list/prodimg01.png">
        <div class="alBrand_list_info">
          <p>
            <em>PRADA S.TENS PRADA S.TENS</em>
            <em>鞋履集合场</em>
          </p>
          <span><i>&yen;</i><em>3468</em><del>&yen;5780</del></span>
        </div>
      </a>
    </li>
    <li>
      <a title="PRADA S.TENS 鞋履集合场" href="#">
        <img width="159" height="211" alt="" src="/images/list/prodimg01.png">
        <div class="alBrand_list_info">
          <p>
            <em>PRADA S.TENS PRADA S.TENS</em>
            <em>鞋履集合场</em>
          </p>
          <span><i>&yen;</i><em>3468</em><del>&yen;5780</del></span>
        </div>
      </a>
    </li>
    <li>
      <a title="PRADA S.TENS 鞋履集合场" href="#">
        <img width="159" height="211" alt="" src="/images/list/prodimg01.png">
        <div class="alBrand_list_info">
          <p>
            <em>PRADA S.TENS PRADA S.TENS</em>
            <em>鞋履集合场</em>
          </p>
          <span><i>&yen;</i><em>3468</em><del>&yen;5780</del></span>
        </div>
      </a>
    </li>
  </ul>
  <a class="alList_moreBtn" href="javascript:;">点击查看更多</a>
</section>

</div>

<jsp:include page="../common/footer.jsp"></jsp:include>

</body>
</html>
