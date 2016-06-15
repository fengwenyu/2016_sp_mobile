<%@ page contentType="text/html;charset=UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%String ua =request.getHeader("user-agent").toLowerCase();%>
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

<title>${cmsTopVO.name}</title>
<link href="<%=path %>/css/base.css" rel="stylesheet" />
<link href="<%=path%>/css/page/list.css" rel="stylesheet" />
<link href="<%=path%>/css/page/subject.css" rel="stylesheet" />

<!-- icon -->
<link rel="apple-touch-icon" href="<%=path%>/images/touch-icon-iphone.png">
<link rel="apple-touch-icon" sizes="72x72" href="<%=path%>/images/touch-icon-ipad.png">
<link rel="apple-touch-icon" sizes="114x114" href="<%=path%>/images/touch-icon-iphone4.png">
<link rel="apple-touch-icon" sizes="144x144" href="<%=path%>/images/touch-icon-newipad.png">
<!-- 启动自定义图片(png, 320X640) -->
<link rel="apple-touch-startup-image" sizes="72x72" href="<%=path%>/images/logo/loading.png">
<script src="<%=path%>/js/client.js" type="text/javascript" charset="utf-8"></script>
<script src="<%=path%>/jquery/jQuery-1.8.0.js" type="text/javascript" charset="utf-8"></script>
<script src="<%=path%>/js/core.js" type="text/javascript" charset="utf-8"></script>
<script type="text/javascript" charset="utf-8">
	var ver = "?20121023001";
		loader = SP.core
			.install("<%=path%>/js/zepto.min.js" + ver)
			.using("<%=path%>/js/dialogs.js" + ver)
			.using("<%=path%>/js/comm.js" + ver)
			.excute(function(){
				$(".list_backTopBtn").click(function(){
					var scrollTop = $(window)[0].scrollTo(0,0);
					$("html, body").animate({ scrollTop: 0 }, 120);
					return false;
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
</script>

</head>

<body>

<jsp:include page="../common/header.jsp"></jsp:include>

<!-- 
        <div class="topBack" >
    		<a href="javascript:history.go(-1);" class="backBtn">&nbsp;</a>
			<s:if test="title!=null&&title!=''">
    			${title}
    		</s:if>
    		<s:else>
    		${cmsTopVO.name}
    		</s:else>
  		</div>
 -->


<nav class="alNav">
  <ul>
    <li><a href="<%=path%>/spindex!index?gender=0">首页</a></li>
    <li>${activityName}</li>
  </ul>
</nav>

<div>
<section class="alBrand_list alSubject">
<p class="errorMsg">${cmsTopVO.msg}</p>
 <!--  <div class="banner">
  	 <img src="${cmsTopVO.headPic}" width="320" height="160">
    <a href="#" class="btn share" target="_blank">关注</a>
    <a href="${cmsTopVO.shareurl}" class="btn" target="_blank">分享</a>
  </div>
  -->

  <s:iterator value="cmsTopVO.cmsSPGroupMechandiseVO" var="group" >
  <!--  
		  <div class="floorImg">
			  <a href="#" style="height:100px;">
			  	<img src="${group.gmsTopGroupVO.grouplogo}" width="320" >
			  </a>
		  </div>
-->
  <ul>
	<c:forEach items="${group.sPMerchandiseVO}" var="product">
    <li>
   
   
		 <a title="${product.productname}" href="<%=path %>/merchandiseaction!spdetail?istop=${istop}&productid=${product.productid}&topicid=${topicid}&activityName=${activityName}">
         <c:if test="${product.count == 0}">
         <i class="saleOut">售罄</i>
        </c:if>
        <img width="159" height="211" alt="${product.brandname}" src="${product.pic}">
        <div class="alBrand_list_info">
          <p>
            <em>${product.brandname}</em>
            <em>${product.productname}</em>
          </p>
         <span>
            <label class="market_price">市场价<del>&yen;${product.prices[4]}</del></label>
            <label class="current_price">现　价<i>&yen;
            <c:choose>  
          		<c:when test="${product.status==\"000100\"||product.status==\"0001\"}">
          			${product.specialprice[0]}	        			
        		</c:when>
        		<c:otherwise>
        			${product.prices[product.priceindex]}
        		</c:otherwise>
        	</c:choose></i></label>
        	<c:if test="${flag==true && cmsTopVO.pricename!=\"\"}">
            	<label class="anny_price">${cmsTopVO.pricename }<em>&yen;${product.specialprice[0]}</em></label>
             </c:if> 
          </span>
        </div>
      </a>
    </li>
    </c:forEach>
  </ul>
 </s:iterator>
</section>
</div>

<jsp:include page="../common/footer.jsp"></jsp:include>

</body>
</html>
