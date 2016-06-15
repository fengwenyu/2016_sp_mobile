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
<link rel="shortcut icon" href="http://pic11.shangpin.com/shangpin/images/logo/favicon.ico" />

<title>尚品网触屏版_领先的高端时尚和奢侈品购物网站</title>
<link href="<%=path%>/css/base.css" rel="stylesheet" />
<link href="<%=path%>/css/page/order.css" rel="stylesheet" />
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
			.install("<%=path %>/js/zepto.min.js" + ver)
			.using("<%=path %>/js/comm.js" + ver)
			.excute(function(){
				isHome(false);
				$(function(){
					if(${null == entityJson || "" == entityJson} || ${-1 == entityJson.code}) {
						$(".order_null").show();
						return ;
					}
					showCouponList(${entityJson});
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
    function showCouponList(entityJson,flag){
		$(".moreButton").css("visibility", "hidden");
		if(entityJson){
	        if(entityJson.code == 0){
        		if(entityJson.haveMore !="0"){
	        		$(".loading").hide();
	        		$(".moreButton").attr("style", "");
	        	}else{
	        		$(".loading").hide();
	        		$(".moreButton").css("visibility", "hidden");
	        		
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
	        		var append = '<dl>'
	        		    + '<dd>'
	        		    + '<h2>'+v.rule+'</h2>'
	        		    + '<ul >'
	       
	        		    + '<li style="border-right:0px;">优惠券面值<br />'+v.amount+'</li>'
	        		    + '<li style="border-right:0px;">有效期<br />'+v.expirydate+'</li>'
	        		    + '<li style="border-right:0px;">状态<br /><i style="color:red;">'+v.status+'</i></li>'
	        		    + '</ul>'
	        		    + '</dd>'
	        		    + '</dl>';
	    	        $(".order_list").append(append);
	    	        $(".order_null").hide();
	    	        $(".order_list").show();
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

 // 根据优惠券类型，获取优惠券列表
	function getCouponList(statusType,flag){
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
	        url:'couponaction!ajaxcouponlist?ch=${ch}',
			data:{"couponType":status,"pager.offset":pageindex},
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
				showCouponList(null != data && "" != data?data:false,flag);
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
    <li><a href="<%=path%>/accountaction!info?ch=${ch}" title="">我的账户</a></li>
    <li>优惠券列表</li>
  
  </ul>
 
   
    
</nav>
<div>
 	<li id="msg" style="padding:0px 0px 10px 10px; color:#b51111; text-align:center; line-height:20px;"></li>
          <form action="<%=path%>/accountaction!activateCouponCode" method="post">
          <li>
              <span>优惠券充值码：</span>
              <input type="text" name="couponcode" id="couponcode" style="idth: 150px;height: 20px;line-height: 28px;border: 1px solid #dbdbdb;" id="J_AddrName" maxlength="50">
              <input type="submit" style="background: -webkit-gradient(linear,left top,left bottom,from(#f8f8f8),to(#e9e9e9));border: 1px solid #e9e9e9;-webkit-border-radius: 5px;border-radius: 5px;text-align: center;color: #666;font-size: 14px;" value="激活" "/>
            </p> <span  style="color:#b51111;">${msg }</span>
              </li>
           
     </li></form>
     </br>
 </div>
<div class="alContent">
 <nav class="alOrder_listMenu">
      <ul>
        <li class="cur"><a href="javascript:getCouponList('0',false);">未使用</a></li>
        <li id="prepare"><a href="javascript:getCouponList('1',false);">已使用</a></li>
        <li id="deliver"><a href="javascript:getCouponList('3',false);">已过期</a></li>
        <li id="deliver"><a href="javascript:getCouponList('-1',false);">全部</a></li>
      </ul>
    </nav>
  <div class="alOrder_couponList order_list">
      <!-- 优惠券列表开始 -->
      <!-- 优惠券列表结束 -->
  </div>
  
</div>
      <div class="alOrder_listCell order_null" style="display:none;">
      <div class="alOrder_none">
        <h2>暂无此类优惠券</h2>
        <p><img src="<%=path%>/images/none_prod.png" width="130" height="130"></p>
      </div>
    </div>
    <a class="alList_moreBtn moreButton" href="javascript:getCouponList('',true);" statusType="-1" style="visibility: hidden;">加载更多</a>
    <div class="alList_moreBtn loading" style="display: none;"><img alt="" src="<%=path%>/images/l1.gif"></div>

<jsp:include page="../common/footer.jsp"></jsp:include>

</body>
</html>
