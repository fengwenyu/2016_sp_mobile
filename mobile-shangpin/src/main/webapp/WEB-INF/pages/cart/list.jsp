<%@ page language="java" contentType="text/html; charset=UTF-8" trimDirectiveWhitespaces="true"
    pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/pages/common/include.inc.jsp"%>

<rapid:override name="custum">
	<link href="${cdn:css(pageContext.request) }/styles/shangpin/css/page/order.css${ver}" rel="stylesheet" />
	<script type="text/javascript" charset="utf-8">
		loader = SP.core
		.install("${cdn:js(pageContext.request)}/styles/shangpin/js/jquery.min.js${ver}")
		.using("${cdn:js(pageContext.request)}/styles/shangpin/js/lazyload.js${ver}")
		.excute()
		.using("${cdn:js(pageContext.request)}/styles/shangpin/js/config.sim.js${ver}")
		.using("${cdn:js(pageContext.request)}/styles/shangpin/js/j.floatCalculate.js${ver}")
		.using("${cdn:js(pageContext.request)}/styles/shangpin/js/j.appDialogs.js${ver}")
		.excute()
		.using("${cdn:js(pageContext.request)}/styles/shangpin/js/order.js${ver}");
	</script>
</rapid:override>



<rapid:override name="header">
	<div class="fixed_top">
		<div class="errorPrompt" style="display: none">
			商品数量有限，您只能购买<span>10</span>件
		</div>
	</div>
	<c:if test="${!checkAPP&&!checkWX }">
		<div class="topFix">
		    <section>
		        <div class="topBack" >
		        <a href="javascript:history.go(-1);" class="backBtn">&nbsp;</a>
		        	<span>
		        		 购物车
		        	</span>
		        	
		        <ul class="alUser_icon clr">
		            <li><a href="<c:url value='/index'/>"><img src="${cdn:pic(pageContext.request)}/styles/shangpin/images/home_icon.png" width="25" height="25" alt="首页"></a></li>
		        </ul>
		        </div>
		    </section>
		</div>
	</c:if>
</rapid:override>

<rapid:override name="content">
<!--内容区域 start-->
 <section class="order_block" >
 
    <dl>
    <form id="cartForm" action="${ctx}/cart/topay" method="get">
 		<input type="hidden" id="shopids" name="shopCartDetailIds"/>
 	</form>
    	<c:forEach var="spList" items="${cartContent.spList}">
			<c:forEach var="cartItem" items="${spList.cartItemList}">
				<c:choose>
					<c:when test="${cartItem.msgType == '1'}">
						<dd class="clr dd_fail">
					</c:when>
					<c:when test="${cartItem.msgType == '3'}">
						<dd class="clr dd_fail_gq">
					</c:when>
					<c:when test="${cartItem.msgType == '4'}">
						<dd class="clr dd_fail_xj">
					</c:when>
					<c:otherwise>
						<dd class="clr">
					</c:otherwise>
				</c:choose>
					<a href="javascript:;" id="${cartItem.shoppingCartDetailId}" class="close">关闭</a>  
					<a class="input" href="javascript:;">
			          <input type="checkbox" class="choice_commodity" name="shopid" value="${cartItem.shoppingCartDetailId}"/>
			        </a>   
					<c:if test="${cartItem.msgType != '3' && cartItem.msgType != '4'}">
						<c:choose>
	                		<c:when test="${checkAPP}">
			                	<a href="shangPinApp://phone.shangpin/actiongodetail?productid=${cartItem.productNo}&topicid=${cartItem.categoryNo}" class="img">
			                </c:when>
			                <c:otherwise>
			                	<a href="<c:url value='/product/detail?productNo=${cartItem.productNo}'/>" class="img">
			                </c:otherwise>
	                	</c:choose>
					</c:if>
					<span class="fail"></span>
					<img src="${cdn:pic(pageContext.request)}/styles/shangpin/images/e.gif" lazy="${cartItem.mobileImg}" width="70" height="94" />
					</a>
					<h2><a href="#"><span>${cartItem.brandEnName}</span><span>${cartItem.productName}</span></a></h2>        
					<p>
						<span class="color">${fn:split(cartItem.mobileSkuAttrText,"|")[0]}</span>
         				<span>${fn:split(cartItem.mobileSkuAttrText,"|")[1]}</span>
					</p>
					<div class="clr">
						<span class="price"><b>&yen;<em class="commodity_price" comprice="${cartItem.price}">${fn:substring(cartItem.price,0,fn:indexOf(cartItem.price,'.'))}</em></b>×</span>
						<div class="fillNumber">
							<a href="javascript:;" class="cut" id="${cartItem.shoppingCartDetailId}">-</a>
							<span class="prodNum">${cartItem.quantity}</span>
							<a href="javascript:;" class="add" id="${cartItem.shoppingCartDetailId}">+</a>
							<input type="hidden" class="numberVal" value=""/>
						</div>
					</div>
				</dd>
			</c:forEach>
		</c:forEach>
    </dl> 
  </section>
  	<!--footer start-->
	<footer class="cart_footer">
		<span>总金额：<em>&yen;<i id="total_amount"></i></em>共<em id="total_number"></em>件商品</span>
		<a href="javascript:topay(${checkAPP});" class="accout">结算</a>
	</footer>
	<input type="hidden"  id="msg" name="msg"  value="${msg }"/>
  <rapid:override name="footer">
  
 </rapid:override>
<!--内容区域 end-->
</rapid:override>

<%@ include file="/WEB-INF/pages/common/common.jsp" %> 