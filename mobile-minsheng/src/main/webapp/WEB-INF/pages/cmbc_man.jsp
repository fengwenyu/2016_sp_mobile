<%@ page language="java" import="java.util.*,com.shangpin.mobileShangpin.common.alipay.util.Base64" pageEncoding="utf-8"%>
<%@ page contentType="text/html;charset=UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="s" uri="/struts-tags"%>

<%String path = request.getContextPath();
String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + path + "/";
%>
<!doctype html>
<html lang="zh-CN">
<head>
<meta content-type="text/html" charset="utf-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0, user-scalable=no, minimum-scale=1.0, maximum-scale=1.0">

<!-- 开启对web app程序的支持 -->
<meta name="apple-mobile-web-app-capable" content="yes">
<!-- 全屏模式浏览 -->
<meta name="apple-touch-fullscreen" content="yes">
<!-- 改变Safari状态栏的外观 -->
<meta name="apple-mobile-web-app-status-bar-style" content="black">

<!-- 改变Safari状态栏的外观 -->
<meta name="apple-itunes-app" content="app-id=432489082"/>

<!-- 禁止自动识别5位以上数字为电话 -->
<meta name="format-detection" content="telephone=no">

<title>尚品网触屏版_领先的高端时尚和奢侈品购物网站</title>
<link href="<%=path%>/css/base.css?1" rel="stylesheet" />
<link href="<%=path%>/css/page/index_new.css?1" rel="stylesheet" />
<link href="<%=path%>/css/page/index.css?1" rel="stylesheet" />
<link href="<%=path%>/css/add2home.css?1" rel="stylesheet" />

<!-- icon -->
<link rel="apple-touch-icon" href="<%=path%><%=path%>/images/touch-icon-iphone.png">
<link rel="apple-touch-icon" sizes="72x72" href="<%=path%><%=path%>/images/touch-icon-ipad.png">
<link rel="apple-touch-icon" sizes="114x114" href="<%=path%><%=path%>/images/touch-icon-iphone4.png">
<link rel="apple-touch-icon" sizes="144x144" href="<%=path%><%=path%>/images/touch-icon-newipad.png">
<!-- 启动自定义图片(png, 320X640) -->
<link rel="apple-touch-startup-image" sizes="72x72" href="<%=path%><%=path%>/images/logo/loading.png">
<script src="<%=path%>/js/client.js" type="text/javascript" charset="utf-8"></script>
<script src="<%=path%>/js/core.js" type="text/javascript" charset="utf-8"></script>
<script type="text/javascript" charset="utf-8">
	var ver = "?20121023001";
		loader = SP.core
			.install("<%=path%>/js/zepto.min.js" + ver)
			.using("<%=path%>/js/lazyload.js" + ver)
			.excute()
			.using("<%=path%>/js/config.sim.js" + ver)
			.using("<%=path%>/js/add2home.js" + ver)
			.using("<%=path%>/js/remainTime.js" + ver)
			.using("<%=path%>/js/css3.js" + ver)
			.using("<%=path%>/js/slideLayer.js" + ver)
			.excute()
			.using("<%=path%>/js/comm.js" + ver)
			.excute(function(){
				isHome(true);
				$('#J_m-slider').slider({wrap:".slider-outer",wrapUl:".slider-wrap",wrapStatus:'.slider-status',isLoop:true,isPlay:true});
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

<jsp:include page="common/header.jsp"></jsp:include>
<nav class="alMenu">
  <ul>
    <li ><a href="<%=path%>/spindex!index?gender=0&ch=${ch}">女士</a></li>
   <li class="cur"><a href="#">男士</a></li>
  </ul>
</nav>

<div class="alContent">

<!-- 焦点图 Start -->
<div class="content" id="J_m-slider">
	<div class="slider-outer">
    	<ul class="slider-wrap">
            <s:iterator value="activityList" var="product" >
		    	<s:if test="#product.type=='ACTIVITY'">
				    	<s:if test="#product.imgUrl!=null&&#product.imgUrl!=''">
					    	 <li>
			             		 <a href="<%=path %>/merchandiseaction!splist?topicid=${product.activityID}&istop=0&gender=${gender}"><img alt="${product.title}" height="292" src="<%=path %>/images/e.gif" lazy="${fn:substring(product.imgUrl,0,fn:indexOf(product.imgUrl,'-'))}-${product.imgWidth}-${product.imgHeight}.jpg"  width="320"  ></a>
			           		 </li>
					    </s:if>
					    <s:else>
						    <li>
			              		<a href="<%=path %>/merchandiseaction!splist?topicid=${product.activityID}&istop=0&gender=${gender}"><img alt="${product.title}" height="292" src="<%=path %>/images/e.gif" lazy="${fn:substring(product.iphonePic,0,fn:indexOf(product.iphonePic,'-'))}-640-584.jpg"  width="320"  ></a>
			            	</li>
					    </s:else>
		    	</s:if>
		   		<s:else>
			    	<s:if test="#product.imgUrl!=null&&#product.imgUrl!=''">
				    	<li>
			              <a href="<%=path %>/activeaction!meetindex?z=0&id=26"><img alt="${product.title}" height="292" src="<%=path %>/images/e.gif" lazy="${fn:substring(product.imgUrl,0,fn:indexOf(product.imgUrl,'-'))}-${product.imgWidth}-${product.imgHeight}.jpg"  width="320" ></a>
			            </li>
			    	</s:if>
			    	<s:else>
				    	<li>
			              <a href="<%=path %>/activeaction!meetindex?z=0&id=26"><img alt="${product.title}" height="292" src="${product.getUrl}" width="320" ></a>
			            </li>
			    	</s:else>
		    	</s:else>
	    	</s:iterator>
		</ul>
	</div>
    <div class="slider-status" ></div>
</div>
<!-- 焦点图 End -->


<nav class="tagMenu">
  <ul>
   <li><a href="<%=path%>/brandaction!getSPBrands?gender=0&ch=${ch}">品牌</a></li>
   <li><a href="<%=path%>/merchandiseaction!getNewlist?categoryid=A01&gender=0&ch=${ch}">新品</a></li>
  </ul>
</nav>


<!-- 分类列表 start -->
<section class="category_box">
    <ul class="clr">
    	<li><a href="<%=path%>/categoryproductsaction!getCategoryProducts?categoryid=A02B02&categoryname=服饰&childcategoryid=A02B02C39&childCategoryname=衬衫/T恤&gender=1&ch=${ch}"><img src="<%=path%>/images/e.gif" lazy="<%=path%>/images/index/cmbc/manCloth01.jpg" width="94" height="94"></a></li>
        <li class="big frt"><a href="<%=path%>/categoryproductsaction!getCategoryProducts?categoryid=A02B02&categoryname=服饰&gender=1&ch=${ch}"><img src="<%=path%>/images/e.gif" lazy="<%=path%>/images/index/cmbc/manCloth02.jpg" width="194" height="194"></a></li>
        <li><a href="<%=path%>/categoryproductsaction!getCategoryProducts?categoryid=A02B02&categoryname=服饰&childcategoryid=A02B02C34&childCategoryname=外套/上装&gender=1&ch=${ch}"><img src="<%=path%>/images/e.gif" lazy="<%=path%>/images/index/cmbc/manCloth03.jpg" width="94" height="94"></a></li>
        <li><a href="<%=path%>/categoryproductsaction!getCategoryProducts?categoryid=A02B02&categoryname=服饰&childcategoryid=A02B02C35&childCategoryname=户外运动&gender=1&ch=${ch}"><img src="<%=path%>/images/e.gif" lazy="<%=path%>/images/index/cmbc/manCloth04.jpg" width="94" height="94"></a></li>
        <li class="middle2"><a href="<%=path%>/categoryproductsaction!getCategoryProducts?categoryid=A02B02&categoryname=服饰&childcategoryid=A02B02C05&childCategoryname=内衣&gender=1&ch=${ch}"><img src="<%=path%>/images/e.gif" lazy="<%=path%>/images/index/cmbc/manCloth05.jpg" width="94" height="94"></a></li>
        <li class="frt"><a href="<%=path%>/brandaction!getSPProductsBrand?brandid=B0334&brandName=MONCLER&categoryid=A02B02&gender=1&ch=${ch}"><img src="<%=path%>/images/e.gif" lazy="<%=path%>/images/index/cmbc/manCloth06.jpg" width="94" height="94"></a></li>
    </ul>
    <div class="btmBg"></div>
    
	<ul class="clr">
    	<li class="big"><a href="<%=path%>/categoryproductsaction!getCategoryProducts?categoryid=A02B01&categoryname=箱包&childcategoryid=A02B01C01&childCategoryname=手提包&gender=1&ch=${ch}"><img src="<%=path%>/images/e.gif" lazy="<%=path%>/images/index/cmbc/manBag01.jpg" width="194" height="194"></a></li>
        <li class="frt"><a href="<%=path%>/categoryproductsaction!getCategoryProducts?categoryid=A02B01&categoryname=箱包&gender=1&ch=${ch}"><img src="<%=path%>/images/e.gif" lazy="<%=path%>/images/index/cmbc/manBag02.jpg" width="94" height="94"></a></li>
        <li class="frt"><a href="<%=path%>/categoryproductsaction!getCategoryProducts?categoryid=A02B01&categoryname=箱包&childcategoryid=A02B01C04&childCategoryname=钱夹&gender=1&ch=${ch}"><img src="<%=path%>/images/e.gif" lazy="<%=path%>/images/index/cmbc/manBag03.jpg" width="94" height="94"></a></li>
        <li><a href="<%=path%>/categoryproductsaction!getCategoryProducts?categoryid=A02B01&categoryname=箱包&childcategoryid=A02B01C06&childCategoryname=旅行箱包&gender=1&ch=${ch}"><img src="<%=path%>/images/e.gif" lazy="..<%=path%>/images/index/cmbc/manBag04.jpg" width="94" height="94"></a></li>
        <li class="middle"><a href="<%=path%>/categoryproductsaction!getCategoryProducts?categoryid=A02B01&categoryname=箱包&childcategoryid=A02B01C11&childCategoryname=小型配件&&gender=1&ch=${ch}"><img src="<%=path%>/images/e.gif" lazy="<%=path%>/images/index/cmbc/manBag05.jpg" width="94" height="94"></a></li>
        <li class="frt"><a href="<%=path%>/brandaction!getSPProductsBrand?brandid=B0109&brandName=BALLY&categoryid=A02B01&gender=1&ch=${ch}"><img src="<%=path%>/images/e.gif" lazy="<%=path%>/images/index/cmbc/manBag06.jpg" width="94" height="94"></a></li>
    </ul>
    <div class="btmBg"></div>
    
</section>
<!-- 分类列表 end -->


</div>

<jsp:include page="common/footer.jsp"></jsp:include>
</body>
</html>
