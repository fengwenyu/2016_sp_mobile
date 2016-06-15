<%@ page language="java" import="java.util.*,com.shangpin.mobileShangpin.common.alipay.util.Base64" pageEncoding="utf-8"%>
<%@ page contentType="text/html;charset=UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%String path = request.getContextPath();%>
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
				$('#J_m-slider').slider({wrap:".slider-outer",wrapUl:".slider-wrap",wrapStatus:'.slider-status',isLoop:true,isPlay:true});
			});
			//调用客户端页头-end
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
</script>
</head>

<body>
<jsp:include page="common/header.jsp"></jsp:include>
<nav class="alMenu">
  <ul>
   <li><a href="<%=path%>/spindex!index?gender=0&ch=${ch}">女士</a></li>
   <li class="cur"><a href="<%=path%>/spindex!manIndex?gender=1&ch=${ch}">男士</a></li>
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
			              		<a href="<%=path %>/merchandiseaction!splist?topicid=${product.activityID}&istop=0&gender=${gender}"><img alt="${product.title}" height="292" src="<%=path %>/images/e.gif" lazy="${fn:substring(product.iphonePic,0,fn:indexOf(product.iphonePic,'-'))}-640-584.jpg"  width="320" ></a>
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

<section class="alIndex_list">
  <header><a>品牌</a></header>
  <ul class="brandList">
  	<s:iterator value="brandsList" id="brandVO" >  	
  	<li>
      <a title="${brandVO.name}" href="<%=path %>/brandaction!getSPProductsBrand?brandid=${brandVO.id}&brandName=${brandVO.name}&gender=1&ch=${ch}">
        <img width="60" height="42" alt="${brandVO.name}" src="${brandVO.imgurl}" >
      </a>
    </li>
   
    </s:iterator>
   <li> <a title="更多品牌" href="<%=path%>/brandaction!getSPBrands?gender=1&ch=${ch}" class="more">更多品牌</a></li>
  </ul>
</section>

<section class="alIndex_list">
  <header><a>活动</a></header>
  <ul class="activeList">
  	<s:iterator value="activitiesList" id="activitiesVO" >  	
    <li>
      <a title="<s:property value='#activitiesVO.activityName'/>" href="<%=path %>/merchandiseaction!splist?ch=${ch}&istop=1&activityName=${activitiesVO.activityname}&topicid=${activitiesVO.activityid}&gender=1">
        <img width="138" height="103" alt="<s:property value='#activitiesVO.activityName'/>" src="<s:property value='#activitiesVO.pic'/>" >
      </a>
    </li>
    </s:iterator>
  </ul>
</section>

<section class="alIndex_list">
  <header><a>分类</a></header>
  <ul class="tagNav" >
  	<li>
 	 <span>箱包<i>&nbsp;</i></span>
     <div class="categoryList">
     	<a href="<%=path%>/categoryproductsaction!getCategoryProducts?categoryid=A02B01&categoryname=箱包&gender=${gender}&ch=${ch}">全部</a>
     	 <s:iterator value="categoryCasesList" id="categoryVo"> 		
  			<a href="<%=path%>/categoryproductsaction!getCategoryProducts?categoryid=A02B01&categoryname=箱包&childcategoryid=${categoryVo.id}&childCategoryname=${categoryVo.name}&gender=${gender}&ch=${ch}">
  			<s:if test="#categoryVo.name.length()>6">
							<s:property value="#categoryVo.name.substring(0,6)"/>...
						</s:if>
						<s:else>
							<s:property value="#categoryVo.name"/>
						</s:else></a>
  		</s:iterator>
         
     </div>
    </li>
    <li>
 	 <span>服饰<i>&nbsp;</i></span>
     <div class="categoryList">
     <a href="<%=path%>/categoryproductsaction!getCategoryProducts?categoryid=A02B02&categoryname=服饰&gender=${gender}&ch=${ch}">全部</a>
         <s:iterator value="categoryDressList" id="categoryVo">
  		
  			<a href="<%=path%>/categoryproductsaction!getCategoryProducts?categoryid=A02B02&categoryname=服饰&childcategoryid=${categoryVo.id}&childCategoryname=${categoryVo.name}&gender=${gender}&ch=${ch}">
  			<s:if test="#categoryVo.name.length()>6">
							<s:property value="#categoryVo.name.substring(0,6)"/>...
						</s:if>
						<s:else>
							<s:property value="#categoryVo.name"/>
						</s:else>
  			</a>
  		</s:iterator>
     </div>
    </li>
    <li>
 	 <span>鞋靴<i>&nbsp;</i></span>
     <div class="categoryList">
     <a href="<%=path%>/categoryproductsaction!getCategoryProducts?categoryid=A02B03&categoryname=鞋靴&gender=${gender}&ch=${ch}">全部</a>
         <s:iterator value="categoryShoesList" id="categoryVo">
  		
  			<a href="<%=path%>/categoryproductsaction!getCategoryProducts?categoryid=A02B03&categoryname=鞋靴&childcategoryid=${categoryVo.id}&childCategoryname=${categoryVo.name}&gender=${gender}&ch=${ch}">
  			<s:if test="#categoryVo.name.length()>6">
							<s:property value="#categoryVo.name.substring(0,6)"/>...
						</s:if>
						<s:else>
							<s:property value="#categoryVo.name"/>
						</s:else>
  			</a>
  		</s:iterator>
     </div>
    </li>
    <li>
 	 <span>配饰<i>&nbsp;</i></span>
     <div class="categoryList">
     <a href="<%=path%>/categoryproductsaction!getCategoryProducts?categoryid=A02B04&categoryname=配饰&gender=${gender}&ch=${ch}">全部</a>
         <s:iterator value="categoryOrnamentList" id="categoryVo">
  		
  			<a href="<%=path%>/categoryproductsaction!getCategoryProducts?categoryid=A02B04&categoryname=配饰&childcategoryid=${categoryVo.id}&childCategoryname=${categoryVo.name}&gender=${gender}&ch=${ch}">
  			<s:if test="#categoryVo.name.length()>6">
							<s:property value="#categoryVo.name.substring(0,6)"/>...
						</s:if>
						<s:else>
							<s:property value="#categoryVo.name"/>
						</s:else></a>
  		</s:iterator>
     </div>
    </li>
  </ul>
</section>

</div>

  <jsp:include page="common/footer.jsp"></jsp:include>
</body>
</html>
