<%@ page language="java" import="java.util.*,com.shangpin.mobileShangpin.common.alipay.util.Base64" pageEncoding="utf-8"%>
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

<title>${cmsTopVO.name}</title>
<link href="<%=path %>/css/base.css" rel="stylesheet" />
<link href="<%=path%>/css/page/list.css" rel="stylesheet" />
<link href="<%=path%>/css/page/subject.css" rel="stylesheet" />
<!-- icon -->
<link rel="apple-touch-icon" href="<%=path%>/images/touch-icon-iphone.png">
<link rel="apple-touch-icon" sizes="72x72" href="<%=path%>/images/touch-icon-ipad.png">
<link rel="apple-touch-icon" sizes="114x114" href="<%=path%>/images/touch-icon-iphone4.png">
<link rel="apple-touch-icon" sizes="144x144" href="/images/touch-icon-newipad.png">
<!-- 启动自定义图片(png, 320X640) -->
<script src="<%=path%>/js/client.js" type="text/javascript" charset="utf-8"></script>
<script src="<%=path%>/jquery/jQuery-1.8.0.js" type="text/javascript" charset="utf-8"></script>
<script src="<%=path%>/js/core.js" type="text/javascript" charset="utf-8"></script>
<script type="text/javascript" charset="utf-8">
	var ver = "?20121023001";
		loader = SP.core
			.install("<%=path%>/js/zepto.min.js" + ver)
			.using("<%=path%>/js/dialogs.js" + ver)
			.using("<%=path%>/js/comm.js" + ver)
			.using("<%=path%>/js/lazyload.js?" + ver)
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
		function getMore(flag){
			var pageindex = 0;
		    $("#moreButton").hide();
			if(flag){
				pageindex = $("#moreButton").attr("pageIndex");
			}else{
				$("#moreButton").css("visibility", "hidden");
			    $("#moreButton").attr("pageIndex",0);
		    }
			$.ajax({				
		        url:'merchandiseaction!getMoreList',
				data:{"pageIndex":pageindex,
					"activityId" :'${topicid}'
				},
				dataType:'json',
				timeout:300000,
			    async:true,
	            error: function (xmlHttpRequest, error) {
	                if(flag){
	        		    $("#moreButton").show();
	                }
	            	if(error !=""){
	            		alert("您的网络异常");
	            	}
	                return;
	            },
				success:function(data){
					if(flag && data.merchandiseList.length<=0){
						  return;
						}
				    showOrderList(null != data && "" != data?data:false,flag);
				}
	      });
			
		}
		//展示订单，
		//entityJson为订单json格式对象
		//flag：true表示点击更多按钮;false表示点击导航
		function showOrderList(entityList,flag){
			$("#moreButton").css("visibility", "hidden");	
			if(entityList){
				var getMore=entityList.haveMore;
				if(getMore !="0"){
	        		$("#moreButton").attr("style", "");
	        	}else{
	        		$("#moreButton").css("visibility", "hidden");
	        	
	        	}
				var pageIndex=entityList.pageIndex;
				$("#moreButton").attr("pageindex",pageIndex);
					if(entityList.length <= 0){
		        		$("#moreButton").css("visibility", "hidden");
		        		return;
		        	}
					var entity=entityList.merchandiseList;
					var $ul = $(".alSubject > ul");
		        	$(entity).each(function(n,v){
		        		var append = '<li>';		        		
		        	var hrefV;		          
		              hrefV = "<%=path %>/merchandiseaction!spdetail";	
		        		append+="<a title="+v.productname+" href= " + hrefV + "?productid="+v.productid+"&topicid=${topicid}&istop=${istop}&activityName=${activityName}\">"
				        		   +'<img width="159" height="211" alt="" src="'+v.pic+'">'
		        		    	+'<div class="alBrand_list_info">'             	
		                        +'<p>'
		                        +'<em>'+v.brandname+'</em>'
		                        +'<em>'+v.productname+'</em>'
		                      	+'</p>'
		                      	
		                      	+'<span>'
		                        +' <label class="market_price">市场价<del>&yen;'+v.prices[4]+'</del></label><label class="current_price">现　价<i>&yen;';
		                        if(v.status=='000100'||v.status=='0001'){
		                        	append+=v.specialprice[0];
		                        }else{
		                        	append+=v.prices[v.priceindex];
		                        }	
		                        append+='</i></label>';
		                        if(entityList.flag==true){
		                        	if(entityList.pricename!=''){
		                        		var pricename=entityList.pricename;
		                        		append+='<label class="anny_price">'+pricename+'<em>&yen;'
		                        		+ ${product.specialprice[0]}
		                        		+'</em></label>';
		                        	}else{
			                        }
		                        }
		                       
		                        	append+='</span>'
		                            +'</div>'
		                            +'</a>'
		                          +'</li>';               		  
		                        $ul.append(append);
		    	    });
		  
			}else{
	    		$("#moreButton").css("visibility", "hidden");
	    		$("#moreButton").show();
				if(flag){
					return;
				}
			}
		}
</script>

</head>

<body>

<jsp:include page="../common/header.jsp"></jsp:include>
<nav class="alNav">
  <ul>
    <li><a href="<%=path%>/spindex!index?gender=0">首页</a></li>
    <li>${activityName}</li>
  </ul>
</nav>

<div>
<section class="alBrand_list alSubject">
<!-- 
<s:if test="flag==true">
<s:if test="cmsTopVO.o1type==\"1\"">
  <div  class="banner" >
  <a href="<%=path %>/accountaction!getSpecialCoupon?loginFrom=8&couponcode=${cmsTopVO.o1code}">
  	<img src="${cmsTopVO.o1logo}" width="320" height="45">
  </a>
  </div>
  </s:if>
  </s:if>
   -->
  <ul class="coupon_list">
    <s:iterator value="cmsTopVO.cmsSPGroupMechandiseVO" var="group" >
    <c:forEach items="${group.sPMerchandiseVO}" var="product">
    <li>
      <a title="${product.productname}" href="<%=path %>/merchandiseaction!spdetail?istop=${istop}&productid=${product.productid}&topicid=${topicid}&activityName=${activityName}">

        <c:if test="${product.count == 0}">
         <i class="saleOut">售罄</i>
        </c:if>        
        <img width="159" height="211" alt="${product.productname}" src="${product.pic}">
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
    </s:iterator>
  </ul>
  		 <p class="errorMsg">${cmsTopVO.msg}</p>
  <input type="hidden" id="activityId" value="${topicid }">
          <input type="hidden" id="haveMore" name="haveMore" value="${haveMore}"/>
        <a id="moreButton" class="alList_moreBtn moreButton" href="javascript:getMore(true);" style="visibility: hidden;">点击查看更多</a>
   		 <div class="alList_moreBtn loading" style="display: none;"><img alt="" src="<%=path%>/images/l1.gif"></div>
</section>

</div>

<jsp:include page="../common/footer.jsp"></jsp:include>

</body>
<script type="text/javascript" charset="utf-8">
			var haveMore=${haveMore};
			if(haveMore=='0'){
				$(".alList_moreBtn").css("display", "none");
			}
				if(haveMore==1){
					var pageIndex='${pageIndex}';
					$("#moreButton").attr("style", "");
					$("#moreButton").attr("pageIndex",pageIndex);
				}
</script>
</html>
