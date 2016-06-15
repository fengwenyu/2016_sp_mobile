<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
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
<title>尚品网触屏版_领先的高端时尚和奢侈品购物网站</title>
<link href="<%=path%>/css/base.css" rel="stylesheet" />
<link href="<%=path%>/css/page/account.css" rel="stylesheet" />

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
			.using("<%=path%>/js/comm.js" + ver)
			.excute(function(){
				isHome(false);
				var haveMore=${haveMore};
				if(haveMore=='0'){
					$(".alList_moreBtn").css("display", "none");
				}
					if(haveMore==1){;
						var pageIndex='${pageIndex}';
						$(".moreButton").attr("style", "");
						$(".moreButton").attr("pageIndex",pageIndex);
					}
				
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
		    $(".moreButton").hide();
		    $(".loading").show();
			if(flag){
				pageindex = $(".moreButton").attr("pageIndex");
			}else{
				$(".moreButton").css("visibility", "hidden");
			    $(".moreButton").attr("pageIndex",0);
		    }
			$.ajax({				
		        url:'logisticeaction!getMoreList?',
				data:{"pageIndex":pageindex},
				dataType:'json',
				timeout:300000,
			    async:true,
	            error: function (xmlHttpRequest, error) {
	                if(flag){
	        		    $(".moreButton").show();
	        		    $(".loading").hide();
	                }
	            	if(error !=""){
	            		alert("您的网络异常");
	            	}
	                return;
	            },
				success:function(data){
					if(flag && data.logisticeList.length<=0){
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
			$(".moreButton").css("visibility", "hidden");	
			if(entityList){
				var getMore=entityList.haveMore;
				if(getMore !="0"){
	        		$(".loading").hide();
	        		$(".moreButton").attr("style", "");
	        	}else{
	        		$(".loading").hide();
	        		$(".moreButton").css("visibility", "hidden");
	        	
	        	}
				var pageIndex=entityList.pageIndex;
				$(".moreButton").attr("pageindex",pageIndex);
					if(entityList.length <= 0){
		        		$(".moreButton").css("visibility", "hidden");
		        		return;
		        	}
					
					var entity=entityList.logisticeList;
		        	$(entity).each(function(n,v){
		        		var append = ' <div class="logistics_listBlock logistics">'
		        		    + ' <h3>订单编号：'+v.orderId+'<span>共'+v.totalcount+'件商品</span> </h3>';  
		        		    var merchandise=v.merchandiseVO;
		        		    $(merchandise).each(function(n1,v1){
		        		    	append+='<a href="<%=path%>/logisticeaction!logisticeDetail?ticketNo='+v.ticketNo+'&orderId='+v.orderId+'">'
				        		   +'<img src="'+v1.picurl+'" width="80" height="108">'
		        		    	+'<dl>'             	
		                        +'<dt>'+v1.brand+'</dt>'
		                        	+'<dd>'+v1.productname+'<br />'
		                        		+v1.firstpropname+' ：'+v1.firstpropvalue+'<br />'
		                            	+'<em>￥'+v1.amount+'</em> x'+v1.buyCount
		                            +'</dd>'
		                    +'</dl>';		                    
		        		    });
		        		    if(v.express!=null){
		        		    	append+='<p>'+v.express+'：'+v.desc+'</p>'
		        		    }else{
		        		    	append+='<p>物流信息暂没同步，请稍后再来查询...</p>'
		        		    }
		        		    append+='<span></span>'
			                    +'</a>'            
			                +'</div>';		        		  
		    	        $(".order_list").append(append);
		    	    });
		  
			}else{
	    		$(".loading").hide();
	    		$(".moreButton").css("visibility", "hidden");
	    		$(".moreButton").show();
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
        <li><a href="<%=path%>/spindex!index?gender=0&ch=${ch}">首页</a></li>
        <li><a href="<%=path%>/accountaction!info?ch=${ch}">我的账户</a></li>
        <li>物流查询</li>
    </ul>
</nav>
<div class="alContent">
    <section class="alOrder_list">
        <div class="alOrder_listCell order_list"> 
            <!-- 订单列表开始 -->
            <s:if test="logisticeList!=null">
            <s:iterator value="logisticeList" id="logisticeVO">
            <div class="logistics_listBlock logistics">                       
                <h3>订单编号：<s:property value="#logisticeVO.orderId"/><span>共<s:property value="#logisticeVO.totalcount"/>件商品</span> </h3>
                <s:iterator value="#logisticeVO.merchandiseVO" id="merchandiseVO">     
                <a href="<%=path%>/logisticeaction!logisticeDetail?ticketNo=${logisticeVO.ticketNo}&orderId=${logisticeVO.orderId}"> <img src="<s:property value='#merchandiseVO.picurl'/>" width="80" height="108">
                <dl>                	
                    <dt><s:property value="#merchandiseVO.brand"/></dt>
                    <dd><s:property value="#merchandiseVO.productname"/><br />
                       <s:property value="#merchandiseVO.firstpropname"/>  ：<s:property value="#merchandiseVO.firstpropvalue"/><br />
                        <em>￥<s:property value="#merchandiseVO.amount"/></em> x<s:property value="#merchandiseVO.buyCount"/> </dd>
                </dl>
                </s:iterator>
                <s:if test="#logisticeVO.express!=null">
                <p><s:property value="#logisticeVO.express"/>：<s:property value="#logisticeVO.desc"/></p>
                </s:if>
                <s:else>
                	<p style="color:#f10000;">物流信息暂没同步，请稍后再来查询...</p>
                </s:else>
                <span></span>
                </a>                
            </div>
           </s:iterator>
           </s:if>
           <s:else>
           	<P style="color:#f10000;text-align:center;">暂无物流信息</P>
           </s:else>
            <!-- 订单列表结束 --> 
        </div>
        <input type="hidden" id="haveMore" name="haveMore" value="${haveMore}"/>
        <a id="moreButton" class="alList_moreBtn moreButton" href="javascript:getMore(true);" style="visibility: hidden;">加载更多</a>
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
				if(haveMore==1){;
					var pageIndex='${pageIndex}';
					$(".moreButton").attr("style", "");
					$(".moreButton").attr("pageIndex",pageIndex);
				}
</script>
</html>
