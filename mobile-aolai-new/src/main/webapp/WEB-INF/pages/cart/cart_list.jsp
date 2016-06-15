<%@ page language="java" contentType="text/html; charset=UTF-8" trimDirectiveWhitespaces="true"
    pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/pages/common/include.inc.jsp"%>

<rapid:override name="title">
	购物车
</rapid:override>

<rapid:override name="custum">
	<link href="${cdn:css(pageContext.request)}/styles/shangpin/css/page/order.new.css${ver}" rel="stylesheet" />
	<script type="text/javascript" charset="utf-8">
			loader = SP.core
				.install("${cdn:js(pageContext.request)}/styles/shangpin/js/jquery.min.js${ver}")
				.using("${cdn:js(pageContext.request)}/styles/shangpin/js/j.lazyload.js${ver}")
				.excute()
				.using("${cdn:js(pageContext.request)}/styles/shangpin/js/config.sim.js${ver}")
				.using("${cdn:js(pageContext.request)}/styles/shangpin/js/j.floatCalculate.js${ver}")
				.using("${cdn:js(pageContext.request)}/styles/shangpin/js/j.appDialogs.js${ver}")
				.excute()
				.using("${cdn:js(pageContext.request)}/styles/shangpin/js/cart/cart_list.js${ver}")
	</script>
</rapid:override >

<rapid:override name="content">

	<%--添加数量时提醒 --%>
	<div class="fixed_top">
	    <div class="errorPrompt">
	     	 商品数量有限，您只能购买<span></span>件
	    </div>
    </div>
    
    <%--提交表单：隐藏域提交选中的商品ID --%>
    <form id="cartForm" action="${ctx}/cart/submit" method="post" >
	   <input type="hidden" id="shopids" name="shopDetailId" />
	</form>
	     
    <section class="order_block">
    	<dl>
    		<c:forEach items="${listCart}" var="cart">
    			<c:forEach items="${cart.cartItemList}" var="cartItem">
    			<c:choose>
    				<c:when test="${cartItem.msgType == 1}">
    					<dd class="clr dd_fail">
    				</c:when>
    				<c:when test="${cartItem.msgType == 3}">
    					<dd class="clr dd_fail_gq">
    				</c:when>
    				<c:when test="${cartItem.msgType == 4}">
    					<dd class="clr dd_fail_xj">
    				</c:when>
    				<c:otherwise>
    					<dd class="clr">
    				</c:otherwise>
    			</c:choose>
    						<a href="javascript:;" id="${cartItem.shoppingCartDetailId}" class="close">关闭</a>
    						<a class="input" href="javascript:;">
    							<input type="checkbox"  class="choice_commodity"/>
    						</a>
    						
    						<c:choose>
		  						<c:when test="${checkAPP}">
		  							<a href="shangpinapp://phone.aolai/actiongodetail?title=商品详情&productno=${cartItem.productNo}&categoryno=${cartItem.categoryNo}" class="img">
		  						</c:when>
		  						<c:otherwise>
		  							<a href="<c:url value='/activity/detail?categoryNo=${cartItem.categoryNo}&goodsId=${cartItem.productNo}&pageType=01&typeFlag=1&activityId=${cartItem.aolaiSubjectNo}'/>" class="img">
		  						</c:otherwise>
		  					</c:choose>
		  								<span class="fail"></span>
    							     	<img src="${ctx}/styles/shangpin/images/e.gif" lazy="${cartItem.mobileImg}" width="70" height="94" style="opacity: 1" >
		  							</a>
    						<h2>
					          <a href="#">
					            <span>${cartItem.brandEnName}</span>
					            <span>${cartItem.productName}</span>
					          </a>
					        </h2>    
					        
					        <p>
					        	<c:forEach var="skuAttr" items="${fn:split(cartItem.mobileSkuAttrText,'|')}">
									<span class="color">${skuAttr}</span>
								</c:forEach>
					        </p>
					        
					        <div class="clr">
					        	<span class="price">
					        		<b>&yen;<em class="commodity_price" comprice="${cartItem.price}">${fn:substringBefore(cartItem.price,".")}</em></b>×
					        	</span>
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
    
    <footer><s:property value=""/>
	     <span>总金额：<em>&yen;<i id="total_amount"></i></em>共<em id="total_number"></em>件商品</span>
	     <a href="javascript:toSubmit(${checkAPP});">结算</a>	 	 
    </footer>
</rapid:override>

<rapid:override name="footer">
</rapid:override> 
<%@ include file="/WEB-INF/pages/common/base_content.jsp" %> 