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
			.using("<%=path%>/js/dialogs.js" + ver)
			.using("<%=path%>/js/account.js" + ver)
			.excute(function(){
				isHome(false);
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
			function confirdel(){
				if(browser.versions.android==true){				
					window.MsgJs.setAlertInfo('{"title":"提示","msg":"确认取消该订单吗?","ok_btn":"true","ok_text":"确定","ok_func":"cancelOrder();","cancle_text":"取消","cancle_func":""}');
				}else if(browser.versions.iPhone==true||browser.versions.iPad==true||browser.versions.ios==true){
					setWebitEvent("iphonealertfir()","05");
				}				
			return s;
			}
			function iphonealertfir(){
				var s='{"title":"提示","msg":"确认取消该订单吗?","ok_btn":"true","ok_text":"确定","ok_func":"cancelOrder()","cancle_text":"取消","cancle_func":""}';
			return s;
			}
		//调用客户端提示框-end
	function cancelOrder(){
	  $.ajax({
		    url:'orderaction!cancelorder?ch=${ch}',
		    data:{"orderVO.orderid":$(".alOrder_cancelBtn").attr("id"),"deltime":new Date()},
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
				if("0"==data.code){
				    window.location.href="<%=path%>/orderaction!orderlist?ch=${ch}";
				}else{
					alert(data.msg);
				}
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
    <li><a href="<%=path%>/orderaction!orderlist?ch=${ch}">订单管理</a></li>
    <li>详情</li>
  </ul>
</nav>

<div class="alContent">
  
  <section class="alOrder_info">
    <h3>订单编号：${orderVO.orderid }
      <s:if test="orderVO.cancancel == \"1\"">
        <a href="javascript:confirdel();" class="alOrder_cancelBtn" id="${orderVO.orderid }">[取消订单]</a>
      </s:if>
    </h3>

    <s:iterator value="orderVO.merchandiseList" id="merchandiseVO" status="st">
      <div style="display:bolck; padding:8px 10px;">
      <span><img src="<%=path %>/images/e.gif" lazy="${merchandiseVO.pic }" width="70" height="70"></span>
      <p><i>${merchandiseVO.productname }</i>
      <s:if test="#merchandiseVO.firstpropname != \"\"">${merchandiseVO.firstpropname } : ${merchandiseVO.firstpropvalue }<br /></s:if>
      <s:if test="#merchandiseVO.secondpropname != \"\"">${merchandiseVO.secondpropname } : ${merchandiseVO.secondpropvalue }<br /></s:if>
      <!-- 
           <s:if test="#merchandiseVO.firstpropname == \"\" && #merchandiseVO.secondpropname == \"\"">
             <br /><br />
           </s:if>
           <s:elseif test="#merchandiseVO.firstpropname != \"\" && #merchandiseVO.secondpropname != \"\"">
             ${merchandiseVO.firstpropname } : ${merchandiseVO.firstpropvalue }<br />
             ${merchandiseVO.secondpropname } : ${merchandiseVO.secondpropvalue }<br />
           </s:elseif>
           <s:elseif test="#merchandiseVO.firstpropname != \"\" && #merchandiseVO.secondpropname == \"\"">
             <br />${merchandiseVO.firstpropname } : ${merchandiseVO.firstpropvalue }<br />
           </s:elseif>
       -->
         <em>&yen;${merchandiseVO.amount } x ${merchandiseVO.count }</em>
      </p>
      </div>
    </s:iterator>
  </section>

  <s:if test="orderVO.canpay == \"1\"">
   <a href="<%=path%>/orderaction!payment?ch=${ch}&orderid=${orderVO.orderid}&amount=${orderVO.amount}" class="alOrder_buyBtn orderInfo_buyBtn">确定并付款</a>
  </s:if>

  <section class="alOrder_prodInfo">
    <h2>收货信息</h2>
    <dl>
      <dt>收货人：</dt><dd>${orderVO.cavo.name }</dd>
    </dl>
    <dl>
      <dt>地　址：</dt><dd>${orderVO.cavo.province }${orderVO.cavo.city }${orderVO.cavo.area }${orderVO.cavo.addr }</dd>
    </dl>
    <dl>
      <dt>手机号：</dt><dd>${orderVO.cavo.tel }</dd>
    </dl>
  </section>
  
  <section class="alOrder_prodInfo">
    <h2>订单信息</h2>
    <dl>
      <dd>
        订单状态：${orderVO.statusdesc }<br />
        下单时间：${orderVO.date }<br />
        收货时间：${orderVO.express}<br />
        发票信息：${orderVO.invoicetitle }<s:if test="orderVO.invoicetitle == null || orderVO.invoicetitle == \"\"">未开发票 </s:if><br />
        发票内容：${orderVO.invoicecontent }<br />
        支付方式：${orderVO.paymode }<br />
        <span>订单总金额：<em>&yen;${orderVO.amount}</em></span><br />
        <span>优惠金额：<em>&yen;${orderVO.discount}</em></span><br />
         <s:if test="orderVO.carriage != null && orderVO.carriage !=''">
        	<span>运费：<em>&yen;${orderVO.carriage}</em></span><br />
        </s:if>
        <s:if test="orderVO.giftcardamount >0 ">
        <span>礼品卡金额：<em>&yen;${orderVO.giftcardamount}</em></span><br />
      </s:if>
        <span>应付金额：<em>&yen;${orderVO.onlineamount}</em></span><br />
        <s:if test="orderVO.canpay == \"1\"">
          <a href="<%=path%>/orderaction!payment?orderid=${orderVO.orderid}&amount=${orderVO.amount}" class="alOrder_buyBtn prodInfo_buyBtn">确定并付款</a>
        </s:if>
       
      </dd>
    </dl>
  </section>
   <s:if test="logisticeVO.ticketNo!=null||iscanlogistics==1">
        	<a href="<%=path%>/logisticeaction!logisticeDetail?ticketNo=${logisticeVO.ticketNo}&orderId=${logisticeVO.orderId}" class="alOrder_buyBtn prodInfo_buyBtn">物流跟踪</a>
   </s:if>
</div>

<jsp:include page="../common/footer.jsp"></jsp:include>

</body>
</html>
