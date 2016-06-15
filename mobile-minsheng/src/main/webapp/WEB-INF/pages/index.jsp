<%@ page language="java" import="java.util.*,com.shangpin.mobileShangpin.common.alipay.util.Base64" pageEncoding="utf-8"%>
<%@ page contentType="text/html;charset=UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
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
<link href="<%=path%>/css/base.css" rel="stylesheet" />
<link href="<%=path%>/css/page/index.css" rel="stylesheet" />
<link href="<%=path%>/css/add2home.css" rel="stylesheet" />
<link rel="shortcut icon" href="http://pic11.shangpin.com/shangpin/images/logo/favicon.ico" />

<!-- icon -->
<link rel="apple-touch-icon" href="/images/touch-icon-iphone.png">
<link rel="apple-touch-icon" sizes="72x72" href="<%=path%>/images/touch-icon-ipad.png">
<link rel="apple-touch-icon" sizes="114x114" href="<%=path%>/images/touch-icon-iphone4.png">
<link rel="apple-touch-icon" sizes="144x144" href="<%=path%>/images/touch-icon-newipad.png">
<!-- 启动自定义图片(png, 320X640) -->
<link rel="apple-touch-startup-image" sizes="72x72" href="<%=path%>/images/logo/loading.png">
<script src="<%=path%>/js/client.js" type="text/javascript" charset="utf-8"></script>
<script src="<%=path%>/js/core.js" type="text/javascript" charset="utf-8"></script>
<script src="<%=path%>/jquery/jQuery-1.8.0.js" type="text/javascript" charset="utf-8"></script>

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
<div id="minshengtitle" style="height:45px">
<div style="background:url(images/title_bg1.png) no-repeat;position:fixed;background-size:100%;z-index:10;height:45px;margin:0 auto;width:100%;text-align:center;color:#fff;font-size:25px;line-height:45px;top:0px;"><a href="javascript:goindex();" style="background:url(images/go_back_btn_n_1.png) no-repeat;background-size:100%;width:60px;height:45px;position:absolute;top: 7px;left: 10px;">&nbsp;</a>尚品网</div>
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
   <li class="cur"><a href="<%=path%>/spindex!index?gender=0&ch=${ch}">女士</a></li>
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

<section class="alIndex_list">
  <header><a href="<%=path%>/categoryproductsaction!getCategoryProducts?categoryid=A01B01&categoryname=箱包&gender=0&ch=${ch}" title="箱包">箱包</a></header>
  <ul class="todayList">
  <s:iterator value="categoryCasesProduct" id="SPMerchandiseVO" >
            <li>
              <a  title="<s:property value='#SPMerchandiseVO.productname'/>" href="<%=path %>/merchandiseaction!spdetail?categoryid=A01B01&categoryname=箱包&productid=${productid }&ch=${ch}">
              <img width="80" height="105" src="<%=path%>/images/e.gif"  lazy="<s:property value="#SPMerchandiseVO.pic" />" width="320"></a>
              <p>&yen;
              <s:if test="%{status==\"000100\"}">
			    ${SPMerchandiseVO.specialprice[0] }
		      </s:if>
		      <s:else>
		        ${SPMerchandiseVO.prices[priceindex] }
		      </s:else></p>
            </li>
    </s:iterator>
  </ul>
  <div class="tagList">
  	<s:iterator value="categoryCasesList" id="categoryVo" status="CC">
  		<s:if test="#CC.index<4">
  		<a href="<%=path%>/categoryproductsaction!getCategoryProducts?categoryid=A01B01&categoryname=箱包&childcategoryid=${categoryVo.id}&childCategoryname=${categoryVo.name}&gender=${gender}&ch=${ch}">
  		<s:if test="#categoryVo.name.length()>3"><s:property value="#categoryVo.name.substring(0,2)"/>...</s:if>
			<s:else>
				<s:property value="#categoryVo.name"/>
			</s:else>
  		</a>
  		</s:if>
  	</s:iterator>
  	</div>
  	 <div class="tagListbrand">
     <s:iterator value="brandCasesList" id="brandVo" status="BC"> 	
     <s:if test="#BC.index<3">	
  		<a  href="<%=path%>/brandaction!getSPProductsBrand?brandid=${brandVo.id}&brandName=${brandVo.name}&categoryid=A01B01&gender=0&ch=${ch}">
  		<s:if test="#brandVo.name.length()>7"><s:property value="#brandVo.name.substring(0,6)"/>...</s:if>
			<s:else>
				<s:property value="#brandVo.name"/>
			</s:else>
  		</a>
  		</s:if>
  	</s:iterator>
 </div>
</section>

<section class="alIndex_list">
  <header><a href="<%=path%>/categoryproductsaction!getCategoryProducts?categoryid=A01B02&categoryname=服饰&gender=0&ch=${ch}" title="服饰">服饰</a></header>
  <ul class="todayList">
     <s:iterator value="categoryDressProduct" id="SPMerchandiseVO">
            <li>
              <a  title="<s:property value='#SPMerchandiseVO.productname'/>" href="<%=path %>/merchandiseaction!spdetail?categoryid=A01B02&categoryname=服饰&productid=${productid }">
              <img width="80" height="105" src="<%=path%>/images/e.gif"  lazy="<s:property value="#SPMerchandiseVO.pic" />" width="320"></a>
              <p>&yen;
              <s:if test="%{status==\"000100\"}">
			    ${SPMerchandiseVO.specialprice[0] }
		      </s:if>
		      <s:else>
		        ${SPMerchandiseVO.prices[priceindex] }
		      </s:else></p>
            </li>
    </s:iterator>
  </ul>
  <div class="tagList">
     <s:iterator value="categoryDressList" id="categoryVo" status="CD">
  		<s:if test="#CD.index<4">
  		<a  href="<%=path%>/categoryproductsaction!getCategoryProducts?categoryid=A01B02&categoryname=服饰&childcategoryid=${categoryVo.id}&childCategoryname=${categoryVo.name}&gender=${gender}&ch=${ch}">
  		<s:if test="#categoryVo.name.length()>3"><s:property value="#categoryVo.name.substring(0,2)"/>...</s:if>
			<s:else>
				<s:property value="#categoryVo.name"/>
			</s:else>
  		</a>
  		</s:if>
  	</s:iterator>
   
 </div>
 <div class="tagListbrand">
 	 <s:iterator value="brandDressList" id="brandVo" status="BD"> 
    	<s:if test="#BD.index<3">		
  		<a href="<%=path%>/brandaction!getSPProductsBrand?brandid=${brandVo.id}&brandName=${brandVo.name}&categoryid=A01B02&gender=0&ch=${ch}">
  		<s:if test="#brandVo.name.length()>7"><s:property value="#brandVo.name.substring(0,6)"/>...</s:if>
			<s:else>
				<s:property value="#brandVo.name"/>
			</s:else></a>
  		</s:if>
  	</s:iterator>
 </div>
</section>

<section class="alIndex_list">
  <header><a href="<%=path%>/categoryproductsaction!getCategoryProducts?categoryid=A01B03&categoryname=鞋靴&gender=0&ch=${ch}" title="鞋靴">鞋靴</a></header>
  <ul class="todayList">
     <s:iterator value="categoryShoesProduct" id="SPMerchandiseVO">
            <li>
              <a  title="<s:property value='#SPMerchandiseVO.productname'/>" href="<%=path %>/merchandiseaction!spdetail?categoryid=A01B03&categoryname=鞋靴&productid=${productid }">
              <img width="80" height="105" src="<%=path%>/images/e.gif"  lazy="<s:property value="#SPMerchandiseVO.pic" />" width="320"></a>
              <p>&yen;
              <s:if test="%{status==\"000100\"}">
			    ${SPMerchandiseVO.specialprice[0] }
		      </s:if>
		      <s:else>
		        ${SPMerchandiseVO.prices[priceindex] }
		      </s:else></p>
            </li>
    </s:iterator>
  </ul>
  <div class="tagList">
    <s:iterator value="categoryShoesList" id="categoryVo" status="CS">
  		<s:if test="#CS.index<4">		
  		<a href="<%=path%>/categoryproductsaction!getCategoryProducts?categoryid=A01B03&categoryname=鞋靴&childcategoryid=${categoryVo.id}&childCategoryname=${categoryVo.name}&gender=${gender}&ch=${ch}">
  		<s:if test="#categoryVo.name.length()>3"><s:property value="#categoryVo.name.substring(0,2)"/>...</s:if>
			<s:else>
				<s:property value="#categoryVo.name"/>
			</s:else>
  		</a>
  		</s:if>
  	</s:iterator>
  	</div>
  	<div class="tagListbrand">
    <s:iterator value="brandShoesList" id="brandVo" status="BS"> 	
    	<s:if test="#BS.index<3">	
  		<a  href="<%=path%>/brandaction!getSPProductsBrand?brandid=${brandVo.id}&brandName=${brandVo.name}&categoryid=A01B03&gender=0&ch=${ch}">
  		<s:if test="#brandVo.name.length()>7"><s:property value="#brandVo.name.substring(0,6)"/>...</s:if>
			<s:else>
				<s:property value="#brandVo.name"/>
			</s:else>
  		</a>
  		</s:if>
  	</s:iterator>
 	</div>
</section>

<section class="alIndex_list">
  <header><a href="<%=path%>/categoryproductsaction!getCategoryProducts?categoryid=A01B04&categoryname=配饰&gender=0&ch=${ch}" title="配饰">配饰</a></header>
  <ul class="todayList">
     <s:iterator value="categoryOrnamentProduct" id="SPMerchandiseVO">
            <li>
              <a  title="<s:property value='#SPMerchandiseVO.productname'/>" href="<%=path %>/merchandiseaction!spdetail?categoryid=A01B04&categoryname=配饰&productid=${productid }">
              <img width="80" height="105" src="<%=path%>/images/e.gif"  lazy="<s:property value="#SPMerchandiseVO.pic" />" width="320"></a>
              <p>&yen;
              <s:if test="%{status==\"000100\"}">
			    ${SPMerchandiseVO.specialprice[0] }
		      </s:if>
		      <s:else>
		        ${SPMerchandiseVO.prices[priceindex] }
		      </s:else></p>
            </li>
    </s:iterator>
  </ul>
  <div class="tagList">
     <s:iterator value="categoryOrnamentList" id="categoryVo" status="CO">
  		<s:if test="#CO.index<4">	
  		<a  href="<%=path%>/categoryproductsaction!getCategoryProducts?categoryid=A01B04&categoryname=配饰&childcategoryid=${categoryVo.id}&childCategoryname=${categoryVo.name}&gender=${gender}&ch=${ch}">
  		<s:if test="#categoryVo.name.length()>3"><s:property value="#categoryVo.name.substring(0,2)"/>...</s:if>
			<s:else>
				<s:property value="#categoryVo.name"/>
			</s:else>
  		</a>
  		</s:if>
  	</s:iterator>
  	</div>
  	<div class="tagListbrand">
     <s:iterator value="brandOrnamentList" id="brandVo" status="BO"> 		
     	<s:if test="#BO.index<3">	
  		<a  href="<%=path%>/brandaction!getSPProductsBrand?brandid=${brandVo.id}&brandName=${brandVo.name}&categoryid=A01B04&gender=0&ch=${ch}">
  		<s:if test="#brandVo.name.length()>7"><s:property value="#brandVo.name.substring(0,6)"/>...</s:if>
			<s:else>
				<s:property value="#brandVo.name"/>
			</s:else></a>
  		</s:if>
  	</s:iterator>
 </div>
</section>

<section class="alIndex_list">
  <header><a href="<%=path%>/categoryproductsaction!getCategoryProducts?categoryid=A01B05&categoryname=家居&gender=0&ch=${ch}" title="家居">家居</a></header>
  <ul class="todayList">
     <s:iterator value="categoryHomeProduct" id="SPMerchandiseVO">
            <li>
              <a  title="<s:property value='#SPMerchandiseVO.productname'/>" href="<%=path %>/merchandiseaction!spdetail?categoryid=A01B05&categoryname=家居&productid=${productid }">
              <img width="80" height="105" src="<%=path%>/images/e.gif"  lazy="<s:property value="#SPMerchandiseVO.pic" />" width="320"></a>
              <p>&yen;
              <s:if test="%{status==\"000100\"}">
			    ${SPMerchandiseVO.specialprice[0] }
		      </s:if>
		      <s:else>
		        ${SPMerchandiseVO.prices[priceindex] }
		      </s:else></p>
            </li>
    </s:iterator>
  </ul>
  <div class="tagList">
    <s:iterator value="categoryHomeList" id="categoryVo" status="CH">
  		<s:if test="#CH.index<4">	
  		<a  href="<%=path%>/categoryproductsaction!getCategoryProducts?categoryid=A01B05&categoryname=家居&childcategoryid=${categoryVo.id}&childCategoryname=${categoryVo.name}&gender=${gender}&ch=${ch}">
  		<s:if test="#categoryVo.name.length()>3"><s:property value="#categoryVo.name.substring(0,2)"/>...</s:if>
			<s:else>
				<s:property value="#categoryVo.name"/>
			</s:else>
  		</a>
  		</s:if>
  	</s:iterator>
  	</div>
  	<div class="tagListbrand">
    <s:iterator value="brandHomeList" id="brandVo" status="BH"> 
    	<s:if test="#BH.index<3">	    	
  			<a  href="<%=path%>/brandaction!getSPProductsBrand?brandid=${brandVo.id}&brandName=${brandVo.name}&categoryid=A01B05&gender=0&ch=${ch}">
  			<s:if test="#brandVo.name.length()>7"><s:property value="#brandVo.name.substring(0,6)"/>...</s:if>
			<s:else>
				<s:property value="#brandVo.name"/>
			</s:else></a>
  		</s:if>
  	</s:iterator>
 	</div>
</section>

</div>
  <jsp:include page="common/footer.jsp"></jsp:include>
</body>
</html>
