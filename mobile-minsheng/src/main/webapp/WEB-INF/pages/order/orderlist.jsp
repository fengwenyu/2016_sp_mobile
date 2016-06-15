<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib prefix="s" uri="/struts-tags" %>
<%@ taglib uri="/tlds/activityName" prefix="spantag"%> 
<%String path = request.getContextPath();%>
<!doctype html>
<html lang="zh-CN">
<head>

<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<meta name="viewport" content="width=device-width, initial-scale=1.0, user-scalable=no, minimum-scale=1.0, maximum-scale=1.0">
<link rel="shortcut icon" href="http://pic11.shangpin.com/shangpin/images/logo/favicon.ico" />

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
<link rel="apple-touch-icon" href="<%=path%>/images/touch-icon-iphone.png">
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
			.using("<%=path%>/js/comm.js" + ver)
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
				
				<s:if test="#session['ch'] == '100001'">
					JSCallback.onPageLoaded();
				</s:if>
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
	        		$(".alList_moreBtn").css("display", "none");
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
	        		    append += '<a href="<%=path%>/orderaction!orderdetail?ch=${ch}&iscanlogistics='+v.canlogistics+'&orderVO.orderid='+v.orderid+'">'
		        		    + '<span><img src="<%=path %>/images/e.gif" lazy="'+v1.pic+'" width="70" height="70"></span>&nbsp;&nbsp;&nbsp;'
	        		        + '<p>'+v1.productname+'<br />'
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
	        			 append += '<a href="<%=path%>/orderaction!payment?ch=${ch}&orderid='+v.orderid+'&amount='+v.amount+'" class="alOrder_buyBtn list_buyBtn">付款</a>';
	        		 }
	        		 if(v.canlogistics==1){
	        			 append += '<a href="<%=path%>/logisticeaction!logisticeByOrderDetail?ch=${ch}&orderid='+v.orderid+'" class="alOrder_buyBtn list_buyBtn" style="margin: 14px 0 15px 29px">物流跟踪</a>';
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
	        url:'orderaction!ajaxorderlist?ch=${ch}',
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
            		window.location.href="<%=path%>/mobileshangpin/accountaction!loginui?ch=${ch}&loginForm=2";
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
    <li><a href="<%=path%>/spindex!index?gender=0&ch=${ch}">首页</a></li>
    <li><a href="<%=path%>/accountaction!info?ch=${ch}">我的账户</a></li>
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
        <a href="<%=path%>/spindex!index?gender=0&ch=${ch}" class="alOrder_buyBtn">去 购 物</a>
      </div>
    </div>
    <a class="alList_moreBtn moreButton" href="javascript:getOrderList('',true);" statusType="waitpay" style="visibility: hidden;">加载更多</a>
    <div class="alList_moreBtn loading" style="display: none;"><img alt="" src="<%=path%>/images/l1.gif"></div>
  </section>

</div>

<jsp:include page="../common/footer.jsp"></jsp:include>

</body>
</html>