<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib prefix="s" uri="/struts-tags" %>
<%@ taglib prefix="cdn" uri="http://m.shangpin.com/cdn" %>

<%String path = request.getContextPath();%>
<!doctype html>
<html lang="zh-CN">
<head>

<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<meta name="viewport" content="width=device-width, initial-scale=1.0, user-scalable=no, minimum-scale=1.0, maximum-scale=1.0">

<!-- 开启对web app程序的支持 -->
<meta name="apple-mobile-web-app-capable" content="yes">
<!-- 全屏模式浏览 -->
<meta name="apple-touch-fullscreen" content="yes">
<!-- 改变Safari状态栏的外观 -->
<meta name="apple-mobile-web-app-status-bar-style" content="black">

<title>尚品奥莱-触屏版</title>
<link href="${cdn:css(pageContext.request)}/css/base.css" rel="stylesheet" />
<link href="${cdn:css(pageContext.request)}/css/page/account.css" rel="stylesheet" />

<!-- icon -->
<link rel="apple-touch-icon" href="${cdn:pic(pageContext.request)}/images/touch-icon-iphone.png">
<link rel="apple-touch-icon" sizes="72x72" href="${cdn:pic(pageContext.request)}/images/touch-icon-ipad.png">
<link rel="apple-touch-icon" sizes="114x114" href="${cdn:pic(pageContext.request)}/images/touch-icon-iphone4.png">
<link rel="apple-touch-icon" sizes="144x144" href="${cdn:pic(pageContext.request)}/images/touch-icon-newipad.png">
<!-- 启动自定义图片(png, 320X640) -->
<link rel="apple-touch-startup-image" sizes="72x72" href="${cdn:pic(pageContext.request)}/images/logo/loading.png">

<script src="${cdn:js(pageContext.request)}/js/core.js" type="text/javascript" charset="utf-8"></script>
<script src="${cdn:js(pageContext.request)}/jquery/jQuery-1.8.0.js" type="text/javascript" charset="utf-8"></script>
<script type="text/javascript" charset="utf-8">
	var ver = "?20121023001";
		loader = SP.core
			.install("${cdn:js(pageContext.request)}/js/zepto.min.js" + ver)
			.using("${cdn:js(pageContext.request)}/js/lazyload.js" + ver)
		    .excute()
		    .using("${cdn:js(pageContext.request)}/js/config.sim.js" + ver)
			.using("${cdn:js(pageContext.request)}/js/comm.js" + ver)
			.excute(function(){
				isHome(false);
				$(function(){		
					var st = ${statusType!=null}?"${statusType}":"";
					if(st == "1"){//全部
						$("#all").addClass("cur").siblings().removeClass("cur");
					}else if(st == '2'){//待支付
						$("#waitpay").addClass("cur").siblings().removeClass("cur");
					}else if(st == '3'){//待收货
						$("#waitconfirm").addClass("cur").siblings().removeClass("cur");
					}
					if(${null == entityJson || "" == entityJson} || ${0 == entityJson.count}) {
						$(".alOrder_listCell").show();
						return ;
					}else{
						showOrderList(${entityJson});
					}
				});
				<s:if test="platform=='android'||ch=='100001'">
					JSCallback.onPageLoaded();
				</s:if>
			});
	//展示订单，
	//entityJson为订单json格式对象
	//flag：true表示点击更多按钮;false表示点击导航
	function showOrderList(entityJson,flag){
		$(".moreButton").css("visibility", "hidden");
		if(entityJson){
	        if(entityJson.code == 0){
	        	
        		if(entityJson.haveMore !="0"){
	        		$(".loading").hide();
	        		$(".moreButton").attr("style", "");
	        	}else{
	        		$(".loading").hide();
	        		$(".moreButton").css("visibility", "hidden");
	        		$(".moreButton").show();
	        	}

	        	if(!flag){
	        	    $(".order_list").html("");
	        	}

	        	$(".moreButton").attr("pageindex",entityJson.pageindex);
	        	var list = entityJson.content.list;

	        	if(list.length <= 0){
	        		$(".moreButton").css("visibility", "hidden");
	        		$(".order_list").hide();
	        		$(".order_null").show();
	        		return;
	        	}
	        	$(list).each(function(n,v){
	        		var append = '<dl class="alOrder_listBlock">'
	        		    + '<dt>'
	        		    + '<i style="font-size:12px;color:#333">订单编号：'+v.orderid+'</i>';
	        		var proList = v.detail.list;
	        		$(proList).each(function(n1,v1){
	        			var tmp = "";

	        			if(v1.firstpropname!=""){
	        				tmp = v1.firstpropname + ':' + v1.firstpropvalue + '<br />';
	        			}
	        			if(v1.secondpropname!=""){
	        				tmp += v1.secondpropname + ':' + v1.secondpropvalue + '<br />';
	        			}
	        		    append += '<a href="<%=path%>/orderaction!orderdetail?orderVO.orderid='+v.orderid+'">'
		        		    + '<span><img src="<%=path %>/images/e.gif" lazy="'+v1.pic+'" width="60" height="80"></span>'
	        		        + '<p><i>'+v1.productname+'</i>'
	        		        + tmp
	        		       // + '商品货号：' + v1.sku + '<br />'
	        		        + '<em>&yen;'+v1.amount+' x ' + v1.count +'</em>'
	        		        + '</p>'
		        		    + '</a>';
	        		});    
	        		
	        		append += '</dt>'
	        		    + '<dd>'
	        		    + '<span>'+v.statusdesc+'</span>';
	        		 if(v.canpay==1){
	        			 
	        			 append += '<a href="<%=path%>/orderaction!payment?orderid='+v.orderid+'&amount='+v.amount+'" class="alOrder_buyBtn list_buyBtn">付款</a>';
	        		 }
	        		 append += '</dd>' + '</dl>';
	    	        $(".order_list").append(append);
	    	        $(".order_null").hide();
	    	        $(".order_list").show();
	    	        
	    	    	//配置图片延迟加载
	    	    	SP.plug.lazyload("img", null, {
	    	    		start : function(){
	    	    			this.css({opacity : 0});
	    	    		},
	    	    		end : function(){
	    	    			this.animate({opacity:1}, 200);
	    	    		}
	    	    	}).run();
	    	    });
	        }else{
	        	alert(entityJson.msg);
	        }
		}else{
    		$(".loading").hide();
    		$(".moreButton").css("visibility", "hidden");
    		$(".moreButton").show();
			if(flag){
				return;
			}
			$(".order_list").hide();
    		$(".order_null").show();
		}
	}

	// 根据订单类型，获取订单列表
	function getOrderList(statusType,flag){
		var pageindex = 0;
		var status = statusType;

	    $(".moreButton").hide();
	    $(".loading").show();
		if(flag){
			status = $(".moreButton").attr("statusType");
			pageindex = $(".moreButton").attr("pageindex");
		}else{
			$(".moreButton").css("visibility", "hidden");
		    $(".order_list").html("");
		    $(".order_null").hide();
		    $(".moreButton").attr("statusType",statusType);
		    $(".moreButton").attr("pageindex",0);
	    }

		$.ajax({
	        url:'orderaction!ajaxorderlist',
			data:{"statusType":status,"pager.offset":pageindex},
			dataType:'json',
			timeout:300000,
		    async:true,
            error: function (xmlHttpRequest, error) {
                if(flag){
        		    $(".moreButton").show();
        		    $(".loading").hide();
                }
            	if(error == "parsererror"){
            		alert("用户帐号异常，请重新登录");
            		window.location.href="<%=path%>/mobileaolai/accountaction!loginui?loginForm=2";
            	}else if(error == "error"){
              		alert("非常抱歉，有点小问题，请联系客服人员！");
              	}else{
                    alert("您的网络异常");
            	}
                return;
            },
			success:function(data){
				if(data.code != "0"){
				  alert(data.msg);
				  return;
				}
				if(flag && data.content.list.length<=0){
				  return;
				}
			    showOrderList(null != data && "" != data?data:false,flag);
			}
      });
	}

</script>

</head>

<body>

<jsp:include page="../common/header.jsp"></jsp:include>

<nav class="alNav">
  <ul>
    <li><a href="<%=path%>/">首页</a></li>
    <li><a href="<%=path%>/accountaction!info">我的账户</a></li>
    <li>订单管理</li>
  </ul>
</nav>

<div class="alContent">
  
  <section class="alOrder_list">
    <nav class="alOrder_listMenu">
      <ul>
        <li id="waitpay" class="cur"><a href="javascript:getOrderList('2',false);">待支付</a></li>
        <li id="waitconfirm"><a href="javascript:getOrderList('3',false);">待收货</a></li>
        <li id="all"><a href="javascript:getOrderList('1',false);">全部</a></li>
      </ul>
    </nav>

    <div class="alOrder_listCell order_list">
      <!-- 订单列表开始 -->
      <!-- 订单列表结束 -->
    </div>

    <div class="alOrder_listCell order_null" style="display:none;">
      <div class="alOrder_none">
        <h2>暂无此类订单</h2>
        <p><img src="<%=path%>/images/none_prod.png" width="130" height="130"></p>
        <a href="<%=path%>/aolaiindex!index" class="alOrder_buyBtn">去 购 物</a>
      </div>
    </div>
    <a class="alList_moreBtn moreButton" href="javascript:getOrderList('',true);" statusType="waitpay" style="visibility: hidden;">加载更多</a>
    <div class="alList_moreBtn loading" style="display: none;"><img alt="" src="<%=path%>/images/l1.gif"></div>
  </section>

</div>

<jsp:include page="../common/footer.jsp"></jsp:include>

</body>
</html>