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
				
				$(function(){
					if(browser.versions.android==true){
						window.SysClientJs.toLoginShangPin("<%=basePath%>/minshengbindaction!minshengLogin?ch=${ch}&loginFrom=${loginFrom}&islogin=1");
						//window.SysClientJs.goBack();
					}else if(browser.versions.iPhone==true||browser.versions.iPad==true||browser.versions.ios==true){
						//setWebitEvent("SPA01");
						//setWebitEvent({"title":"提示","msg":"接收电子影票的手机号码不能为空！","ok_btn":"false","cancle_text":"确定","cancle_func":null},"05");
						setWebitEvent("<%=basePath%>/minshengbindaction!minshengLogin?ch=${ch}&loginFrom=${loginFrom}&islogin=1","SPA02");
					}
					$('#J_m-slider').slider({wrap:".slider-outer",wrapUl:".slider-wrap",wrapStatus:'.slider-status',isLoop:true,isPlay:true});
				});
				
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

<div id="minshengtitle" style="height:45px">
<div style="background:url(images/title_bg1.png) no-repeat;position:fixed;background-size:100%;z-index:10;height:45px;margin:0 auto;width:100%;text-align:center;color:#fff;font-size:25px;line-height:45px;top:0px;"><a href="javascript:goindex();" style="background:url(images/go_back_btn_n_1.png) no-repeat;background-size:100%;width:60px;height:45px;position:absolute;top: 7px;left: -4px;">&nbsp;</a>尚品网</div>
</div>
<header>
  <!-- 禁止自动识别5位以上数字为电话 -->
  <h1 id="alLogo">
    <a href="<%=path%>/spindex!index?gender=0&ch=${ch}">尚品</a>
  </h1>
  <nav class="alUser_icon">
   <ul>
    <li><a href="<%=path%>/cartaction!showcart?ch=${ch}"><img src="<%=path%>/images/cart_icon.png" width="31" height="31" alt="购物袋"></a></li>
    <li><a href="<%=path%>/orderaction!orderlist?ch=${ch}"><img src="<%=path%>/images/order_icon.png" width="31" height="31" alt="订单"></a></li>
    <li><a href="<%=path%>/accountaction!info?ch=${ch}"><img src="<%=path%>/images/user_icon.png" width="31" height="31" alt="账户"></a></li>
   </ul>
  </nav>
</header>
<nav class="alMenu">
  <ul>
   <li class="cur"><a href="#">女士</a></li>
   <li><a href="<%=path%>/spindex!manIndex?gender=1&ch=${ch}">男士</a></li>
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
			              <a href="${product.getUrl}"><img alt="${product.title}" height="292" src="<%=path %>/images/e.gif" lazy="${fn:substring(product.imgUrl,0,fn:indexOf(product.imgUrl,'-'))}-${product.imgWidth}-${product.imgHeight}.jpg"  width="320" ></a>
			            </li>
			    	</s:if>
			    	<s:else>
				    	<li>
			              <a href="${product.getUrl}"><img alt="${product.title}" height="292" src="${product.getUrl}" width="320" ></a>
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
    	<li class="big"><a href="<%=path %>/categoryproductsaction!getCategoryProducts?categoryid=A01B01&categoryname=箱包&gender=0"><img src="<%=path%>/images/e.gif" lazy="<%=path%>/images/index/cmbc/bag01.jpg" width="194" height="194"></a></li>
        <li class="frt"><a href="<%=path%>/categoryproductsaction!getCategoryProducts?categoryid=A01B01&categoryname=箱包&childcategoryid=A01B01C01&childCategoryname=手提包&gender=0"><img src="<%=path%>/images/e.gif" lazy="<%=path%>/images/index/cmbc/bag02.jpg" width="94" height="94"></a></li>
        <li class="frt"><a href="<%=path%>/categoryproductsaction!getCategoryProducts?categoryid=A01B01&categoryname=箱包&childcategoryid=A01B01C02&childCategoryname=肩包&gender=0"><img src="<%=path%>/images/e.gif" lazy="<%=path%>/images/index/cmbc/bag03.jpg" width="94" height="94"></a></li>
        <li><a href="<%=path%>/categoryproductsaction!getCategoryProducts?categoryid=A01B01&categoryname=箱包&childcategoryid=A01B01C05&childCategoryname=钱夹&gender=0"><img src="<%=path%>/images/e.gif" lazy="<%=path%>/images/index/cmbc/bag04.jpg" width="94" height="94"></a></li>
        <li class="middle"><a href="<%=path%>/categoryproductsaction!getCategoryProducts?categoryid=A01B01&categoryname=箱包&childcategoryid=A01B01C04&childCategoryname=斜挎包&gender=0"><img src="<%=path%>/images/e.gif" lazy="<%=path%>/images/index/cmbc/bag05.jpg" width="94" height="94"></a></li>
        <li class="frt"><a href="<%=path%>/brandaction!getSPProductsBrand?brandid=B0073&brandName=COACH&categoryid=A01B01&gender=0"><img src="<%=path%>/images/e.gif" lazy="<%=path%>/images/index/cmbc/bag06.jpg" width="94" height="94"></a></li>
    </ul>
    <div class="btmBg"></div>
    
    <ul class="clr">
    	<li><a href="<%=path%>/categoryproductsaction!getCategoryProducts?categoryid=A01B02&categoryname=服饰&childcategoryid=A01B02C55&childCategoryname=羽绒服&&gender=0"><img src="<%=path%>/images/e.gif" lazy="<%=path%>/images/index/cmbc/cloth01.jpg" width="94" height="94"></a></li>
        <li class="big frt"><a href="<%=path%>/categoryproductsaction!getCategoryProducts?categoryid=A01B02&categoryname=服饰&gender=0"><img src="<%=path%>/images/e.gif" lazy="<%=path%>/images/index/cmbc/cloth02.jpg" width="194" height="194"></a></li>
        <li><a href="<%=path%>/categoryproductsaction!getCategoryProducts?categoryid=A01B02&categoryname=服饰&childcategoryid=A01B02C43&childCategoryname=针织衫/毛衣&&gender=0"><img src="<%=path%>/images/e.gif" lazy="<%=path%>/images/index/cmbc/cloth03.jpg" width="94" height="94"></a></li>
        <li><a href="<%=path%>/categoryproductsaction!getCategoryProducts?categoryid=A01B02&categoryname=服饰&childcategoryid=A01B02C44&childCategoryname=牛仔裤&&gender=0"><img src="<%=path%>/images/e.gif" lazy="<%=path%>/images/index/cmbc/cloth04.jpg" width="94" height="94"></a></li>
        <li class="middle2"><a href="<%=path%>/categoryproductsaction!getCategoryProducts?categoryid=A01B02&categoryname=服饰&childcategoryid=A01B02C35&childCategoryname=丝巾/围巾&&gender=0"><img src="<%=path%>/images/e.gif" lazy="<%=path%>/images/index/cmbc/cloth05.jpg" width="94" height="94"></a></li>
        <li class="frt"><a href="<%=path%>/brandaction!getSPProductsBrand?brandid=B0005&brandName=BURBERRY&categoryid=A01B02&gender=0"><img src="<%=path%>/images/e.gif" lazy="<%=path%>/images/index/cmbc/cloth06.jpg" width="94" height="94"></a></li>
    </ul>
    <div class="btmBg"></div>
    
    <ul class="clr">
    	<li class="big"><a href="<%=path%>/categoryproductsaction!getCategoryProducts?categoryid=A01B03&categoryname=鞋靴&gender=0"><img src="<%=path%>/images/e.gif" lazy="<%=path%>/images/index/cmbc/shoes01.jpg" width="194" height="194"></a></li>
        <li class="frt"><a href="<%=path%>/categoryproductsaction!getCategoryProducts?categoryid=A01B03&categoryname=鞋靴&childcategoryid=A01B03C25&childCategoryname=雪地靴&&gender=0"><img src="<%=path%>/images/e.gif" lazy="<%=path%>/images/index/cmbc/shoes02.jpg" width="94" height="94"></a></li>
        <li class="frt"><a href="<%=path%>/categoryproductsaction!getCategoryProducts?categoryid=A01B03&categoryname=鞋靴&childcategoryid=A01B03C23&childCategoryname=及膝靴&&gender=0"><img src="<%=path%>/images/e.gif" lazy="<%=path%>/images/index/cmbc/shoes03.jpg" width="94" height="94"></a></li>
        <li><a href="<%=path%>/categoryproductsaction!getCategoryProducts?categoryid=A01B03&categoryname=鞋靴&childcategoryid=A01B03C22&childCategoryname=及踝靴&&gender=0"><img src="<%=path%>/images/e.gif" lazy="..<%=path%>/images/index/cmbc/shoes04.jpg" width="94" height="94"></a></li>
        <li class="middle"><a href="<%=path%>/categoryproductsaction!getCategoryProducts?categoryid=A01B03&categoryname=鞋靴&childcategoryid=A01B03C06&childCategoryname=运动休闲鞋&&gender=0"><img src="<%=path%>/images/e.gif" lazy="<%=path%>/images/index/cmbc/shoes05.jpg" width="94" height="94"></a></li>
        <li class="frt"><a href="<%=path%>/brandaction!getSPProductsBrand?brandid=B0581&brandName=ASH&categoryid=A01B03&gender=0"><img src="<%=path%>/images/e.gif" lazy="<%=path%>/images/index/cmbc/shoes06.jpg" width="94" height="94"></a></li>
    </ul>
    <div class="btmBg"></div>
    
    <ul class="clr">
    	<li><a href="<%=path%>/categoryproductsaction!getCategoryProducts?categoryid=A01B04&categoryname=配饰&childcategoryid=A01B04C15&childCategoryname=项链&&gender=0"><img src="<%=path%>/images/e.gif" lazy="<%=path%>/images/index/cmbc/acce01.jpg" width="94" height="94"></a></li>
        <li class="big frt"><a href="<%=path%>/categoryproductsaction!getCategoryProducts?categoryid=A01B04&categoryname=配饰&gender=0"><img src="<%=path%>/images/e.gif" lazy="<%=path%>/images/index/cmbc/acce02.jpg" width="194" height="194"></a></li>
        <li><a href="<%=path%>/categoryproductsaction!getCategoryProducts?categoryid=A01B04&categoryname=配饰&childcategoryid=A01B04C20&childCategoryname=耳饰&&gender=0"><img src="<%=path%>/images/e.gif" lazy="<%=path%>/images/index/cmbc/acce03.jpg" width="94" height="94"></a></li>
        <li><a href="<%=path%>/categoryproductsaction!getCategoryProducts?categoryid=A01B04&categoryname=配饰&childcategoryid=A01B04C17&childCategoryname=手链/手镯&&gender=0"><img src="<%=path%>/images/e.gif" lazy="<%=path%>/images/index/cmbc/acce04.jpg" width="94" height="94"></a></li>
        <li class="middle2"><a href="<%=path%>/categoryproductsaction!getCategoryProducts?categoryid=A01B04&categoryname=配饰&childcategoryid=A01B04C16&childCategoryname=戒指&&gender=0"><img src="<%=path%>/images/e.gif" lazy="<%=path%>/images/index/cmbc/acce05.jpg" width="94" height="94"></a></li>
        <li class="frt"><a href="<%=path%>/brandaction!getSPProductsBrand?brandid=B0497&brandName=TIFFANY%20&%20CO.&categoryid=A01B04&gender=0"><img src="<%=path%>/images/e.gif" lazy="<%=path%>/images/index/cmbc/acce06.jpg" width="94" height="94"></a></li>
    </ul>
    <div class="btmBg"></div>
    
    <ul class="clr">
    	<li class="big"><a href="<%=path%>/categoryproductsaction!getCategoryProducts?categoryid=A01B07&categoryname=美妆&gender=0"><img src="<%=path%>/images/e.gif" lazy="..<%=path%>/images/index/cmbc/beauty01.jpg" width="194" height="194"></a></li>
        <li class="frt"><a href="<%=path%>/categoryproductsaction!getCategoryProducts?categoryid=A01B07&categoryname=美妆&childcategoryid=A01B07C06&childCategoryname=护肤&&gender=0"><img src="<%=path%>/images/e.gif" lazy="..<%=path%>/images/index/cmbc/beauty02.jpg" width="94" height="94"></a></li>
        <li class="frt"><a href="<%=path%>/categoryproductsaction!getCategoryProducts?categoryid=A01B07&categoryname=美妆&childcategoryid=A01B07C04&childCategoryname=香氛&&gender=0"><img src="<%=path%>/images/e.gif" lazy="..<%=path%>/images/index/cmbc/beauty03.jpg" width="94" height="94"></a></li>
        <li><a href="<%=path%>/categoryproductsaction!getCategoryProducts?categoryid=A01B07&categoryname=美妆&childcategoryid=A01B07C08&childCategoryname=彩妆&&gender=0"><img src="<%=path%>/images/e.gif" lazy="..<%=path%>/images/index/cmbc/beauty04.jpg" width="94" height="94"></a></li>
        <li class="middle"><a href="<%=path%>/categoryproductsaction!getCategoryProducts?categoryid=A01B07&categoryname=美妆&childcategoryid=A01B07C07&childCategoryname=洗护&&gender=0"><img src="<%=path%>/images/e.gif" lazy="<%=path%>/images/index/cmbc/beauty05.jpg" width="94" height="94"></a></li>
        <li class="frt"><a href="<%=path%>/brandaction!getSPProductsBrand?brandid=B0043&brandName=CHANEL&categoryid=A01B07&gender=0"><img src="<%=path%>/images/e.gif" lazy="<%=path%>/images/index/cmbc/beauty06.jpg" width="94" height="94"></a></li>
    </ul>
    <div class="btmBg"></div>
    
</section>
<!-- 分类列表 end -->


</div>

<jsp:include page="common/footer.jsp"></jsp:include>
</body>
</html>


