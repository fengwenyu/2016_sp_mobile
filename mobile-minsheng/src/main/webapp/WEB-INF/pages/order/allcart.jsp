<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + path + "/";
%>
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
<title>购物车</title>
<link href="<%=path%>/css/base.css" rel="stylesheet" />
<link href="<%=path%>/css/page/weixin/order.new.css" rel="stylesheet" />
<!-- icon -->
<link rel="apple-touch-icon" href="<%=path%>/images/touch-icon-iphone.png">
<link rel="apple-touch-icon" sizes="72x72" href="<%=path%>/images/touch-icon-ipad.png">
<link rel="apple-touch-icon" sizes="114x114" href="<%=path%>/images/touch-icon-iphone4.png">
<link rel="apple-touch-icon" sizes="144x144" href="<%=path%>/images/touch-icon-newipad.png">
<!-- 启动自定义图片(png, 320X640) -->
<link rel="apple-touch-startup-image" sizes="72x72" href="<%=path%>/images/logo/loading.png">
<script src="<%=path%>/js/core.js" type="text/javascript" charset="utf-8"></script>
<script type="text/javascript" charset="utf-8">
	var ver = "?20121023001";
		loader = SP.core
			.install("<%=path%>/js/jquery.min.js?" + ver)
      .using("<%=path%>/js/j.lazyload.js?" + ver)
      .excute()
      .using("<%=path%>/js/config.sim.js?" + ver)
      .using("<%=path%>/js/j.floatCalculate.js" + ver) //计算价格
      .using("<%=path%>/js/j.appDialogs.js" + ver) //弹窗
			.excute()
			.using("<%=path%>/js/weixin/order.js" + ver);
</script>
</head>
<body>

  <!--头部 start-->

     <jsp:include page="../common/header.jsp"></jsp:include>
   
  <div class="fixed_top">
    <div class="errorPrompt">
      商品数量有限，您只能购买<span></span>件
    </div>
  </div>
  <!--头部 end-->
 
  <!--内容区域 start-->
  
  <section class="order_block">
    <dl>
    <s:iterator value="productServerAllCartVO.productAllCartVO.productsPromotionVOList" id="productsPromotionVO" >
      <s:iterator value="#productsPromotionVO.productCartVO" id="productCartVO" >
      <s:if test="#productCartVO.msgType==1">
      <dd class="clr dd_fail">
      </s:if>
      <s:elseif test="#productCartVO.msgType==3">
      <dd class="clr dd_fail_gq">
      </s:elseif>
      <s:elseif test="#productCartVO.msgType==4">
      <dd class="clr dd_fail_xj">
      </s:elseif>
      <s:else>
      <dd class="clr">
      </s:else>
        <a href="javascript:;" id="<s:property value='#productCartVO.shopDetailId'/>" class="close">关闭</a>
        <a href="<%=path%>/merchandiseaction!spdetail?productid=<s:property value='#productCartVO.productNo'/>" class="img">
          <span class="fail"></span>
          <img src="<%=path%>/images/e.gif" lazy="<s:property value='#productCartVO.img'/>" width="70" height="94" />
        </a>
       
        <h2>
          <a href="#">
            <span><s:property value="#productCartVO.brandName"/></span>
            <span><s:property value="#productCartVO.productName"/></span>
          </a>
        </h2>        
        <p>
        <s:iterator value="#productCartVO.skuAttrText" id="attr" >
          <span class="color"><s:property value="#attr.name"/>：<s:property value="#attr.value"/></span>
          </s:iterator>
        </p>
        <div class="clr">
          <span class="price">
            <b>&yen;<em class="commodity_price" comprice="<s:property value='#productCartVO.price'/>"><s:property value="#productCartVO.price"/></em></b>×
          </span>
          <div class="fillNumber">
            <a href="javascript:;" class="cut" id="<s:property value='#productCartVO.shopDetailId'/>">-</a>
            <span class="prodNum"><s:property value="#productCartVO.quantity"/></span>
            <a href="javascript:;" class="add" id="<s:property value='#productCartVO.shopDetailId'/>">+</a>
            <input type="hidden" class="numberVal" value=""/>
          </div>
        </div>

      </dd>
   		</s:iterator>
   </s:iterator>                 
    </dl> 
  </section>
  <!--内容区域 start-->

  <!--footer start-->
  <footer><s:property value=""/>
    <span>总金额：<em>&yen;<i id="total_amount"></i></em>共<em id="total_number"></em>件商品</span>
<s:if test="productServerAllCartVO.productAllCartVO.shopIds!=''">
     	 <a href="<%=path%>/orderaction!order?shopDetailId=<s:property value="productServerAllCartVO.productAllCartVO.shopIds"/>">结算</a>

  </s:if>
  <s:else>
  <a href="#">结算</a>
  </s:else>
  </footer>
  <!--footer end-->

</body>
</html>