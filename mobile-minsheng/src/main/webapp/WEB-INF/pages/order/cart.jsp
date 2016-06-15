<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib prefix="s" uri="/struts-tags" %>
<%@ taglib uri="/tlds/activityName" prefix="spantag"%> 
<%String path = request.getContextPath();
String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + path + "/";%>
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
<link href="<%=path%>/css/page/order.css" rel="stylesheet" />

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
				
				total();
				$(".alOrder_timeOut").each(function(n,v){
					if($(v).html() > 0){
						$("#msgdd").html("");
						return;
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
			//iphone提示框
			function confirdel(skuid,gid,groupno,shoppingcartdetailid,count){
				var obj = new Object(); 
				obj.skuid = skuid; 
				obj.gid = gid; 
				obj.groupno = groupno; 
				obj.shoppingcartdetailid=shoppingcartdetailid;
				obj.count=count;
				res=obj;
				//res=obj.id;
				if(browser.versions.android==true){				
					window.MsgJs.setAlertInfo('{"title":"提示","msg":"确认要删除该商品吗?","ok_btn":"true","ok_text":"确定","ok_func":"deleteCartMerchandise(res);","cancle_text":"取消","cancle_func":""}');
				}else if(browser.versions.iPhone==true||browser.versions.iPad==true||browser.versions.ios==true){
					setWebitEvent("iphonealertfir()","05");
				}				
			return s;
			}
			function iphonealertfir(){
				var s='{"title":"提示","msg":"确认要删除该商品吗?","ok_btn":"true","ok_text":"确定","ok_func":"deleteCartMerchandise(res)","cancle_text":"取消","cancle_func":""}';
			return s;
			}
		//调用客户端提示框-end
	//购物袋中商品合计
	function total(){
	    var total = 0;
	    $(".subtotal").each(function(n,v){
	        total = total+$(this).val()*1;
	    });
	    $("#em").html(total);
	}

	//删除购物袋中商品
	function deleteCartMerchandise(obj){
		  $.ajax({
		    url:'cartaction!deletecart',
		    data:{
		    	skuid:obj.skuid,
		    	gid:obj.gid,
		    	groupno:obj.groupno,
		    	shoppingcartdetailid:obj.shoppingcartdetailid,
		    	count:obj.count
		    },
		    dataType:'json',
		    timeout: 30000,
		    async:false,
            error: function (xmlHttpRequest, error) {
            	if(error == "parsererror"){
            		alert("用户帐号异常，请重新登录");
            		window.location.href="<%=path%>/mobileshangpin/accountaction!loginui?ch=${ch}";
            	}else{
                    alert("您的网络异常");
            	}
                return;
            },
		    success:function(data){
		      if(data.success){
		        //删除页面dl对象
		        $(obj).parent().parent().remove();
		        //重新合计
		        total();
		        if($("#em").html()=="0"){
		          $(".alOrder_submitBtn").hide();
		          $(".continuLink").hide();
		          $(".totalPrice").hide();
		          $(".alOrder_none").show();
		        }
		        window.location.reload();
			  }else{
			    alert(data.msg);
			  }
		    }
		  });
	   
	}
</script>

</head>

<body onload="total();">
<jsp:include page="../common/header.jsp"></jsp:include>

<nav class="alNav">
  <ul>
    <li><a href="<%=path%>/spindex!index?gender=0&ch=${ch}">首页</a></li>
    	<li><a href="<%=path%>/accountaction!info?ch=${ch}">我的账户</a></li>
    <li>购物袋</li>
  </ul>
</nav>

<div class="alContent">
  <p class="alOrder_notice"><em>温馨提示:</em><font size="2">购物袋中的商品无法保留库存，请您及时结算。商品库存以订单提交时间为准</font></p>

    <div class="alOrder_none" <s:if test="null != merchandiseList && merchandiseList.size > 0">style="display:none;"</s:if>>
      <h2>您的购物袋中还没有任何商品</h2>
      <p><img src="<%=path %>/images/e.gif" lazy="<%=path%>/images/none_prod.jpg" width="130" height="130"></p>
      <a href="<%=path%>/spindex!index?gender=0&ch=${ch}" class="alOrder_buyBtn">开始挑选商品</a>
    </div>
  


  <section class="alOrder_list">
    <s:iterator value="merchandiseList" id="merchandiseVO" status="st">
    <dl class="alOrder_listBlock alOrder_cartList" style="background:none;">
      <dt>
        <!-- <a href="javascript:void(0);" > -->
         <h3 class="alOrder_timeOut">
           <s:if test="#merchandiseVO.errormsg != null && #merchandiseVO.errormsg != \"\"">${merchandiseVO.errormsg}</s:if>
         </h3>
         
         <span><img src="<%=path %>/images/e.gif" lazy="${merchandiseVO.pic }" width="70" height="90"></span>
         <p>${merchandiseVO.productname }<br />
         <s:if test="null != #merchandiseVO.firstpropname && \"\" != #merchandiseVO.firstpropname">
            ${merchandiseVO.firstpropname }：${merchandiseVO.firstpropvalue }<br />
         </s:if>
         <s:if test="null != #merchandiseVO.secondpropname && \"\" != #merchandiseVO.secondpropname">
            ${merchandiseVO.secondpropname }：${merchandiseVO.secondpropvalue }<br />
         </s:if>
            数量：${merchandiseVO.count }件<br />
            <em>&yen;${merchandiseVO.amount }</em>
         </p>
        <!-- </a> -->
        <div class="alOrder_handeln" id="${merchandiseVO.sku }" onclick="confirdel('${merchandiseVO.sku }','${merchandiseVO.gid }','${merchandiseVO.groupno}','${merchandiseVO.shoppingcartdetailid}','${merchandiseVO.count}');"><a href="javascript:void(0)");" class="alOrder_deletBtn" prodId="123211403">[删除]</a></div>

        <input class="subtotal" type="hidden" value="<s:property value="#merchandiseVO.count * #merchandiseVO.amount"/>">
      </dt>
    </dl>
    </s:iterator>
    <p class="totalPrice" <s:if test="null == merchandiseList || merchandiseList.size <= 0">style="display:none;"</s:if>>应付金额：<em>&yen;<span  id="em"></span></em></p>
    <!-- 
     -->
    <dl>
      <dd style="margin: 0 0 0 20px;color: #D40F15;font-size: 12px; text-align:center;" id="msgdd">${msg }</dd>
    </dl>
  </section>
  <a href="<%=path%>/orderaction!order?ch=${ch}" class="alOrder_buyBtn alOrder_submitBtn" <s:if test="null == merchandiseList || merchandiseList.size <= 0">style="display:none;"</s:if>>去结算</a>

  <p class="continuLink" <s:if test="null == merchandiseList || merchandiseList.size <= 0">style="display:none;"</s:if>><a href="<%=path%>/spindex!index?gender=0&ch=${ch}">继续购物&gt;&gt;</a></p>
  
</div>


<jsp:include page="../common/footer.jsp"></jsp:include>

</body>
</html>
