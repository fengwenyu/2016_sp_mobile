<%@ page language="java" contentType="text/html; charset=UTF-8" trimDirectiveWhitespaces="true"
    pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/pages/common/include.inc.jsp"%>
<rapid:override name="custum">
	<link href="${cdn:css(pageContext.request) }/styles/shangpin/css/page/cart/shopping_cart.css${ver}" rel="stylesheet" />
	<script type="text/javascript" charset="utf-8">
		loader = SP.core
		.install("${cdn:js(pageContext.request)}/styles/shangpin/js/jquery.min.js${ver}")
		.using("${cdn:js(pageContext.request)}/styles/shangpin/js/lazyload.js${ver}")
		.excute()
		.using("${cdn:js(pageContext.request)}/styles/shangpin/js/config.sim.js${ver}")
		.using("${cdn:js(pageContext.request)}/styles/shangpin/js/j.floatCalculate.js${ver}")
		.using("${cdn:js(pageContext.request)}/styles/shangpin/js/j.appDialogs.js${ver}")
		.excute()
		.using("${cdn:js(pageContext.request)}/styles/shangpin/js/cart/shopping_cart520.js${ver}");
	</script>
</rapid:override>

<rapid:override name="header">
	<div class="fixed_top">
		<div class="errorPrompt" style="display: none">
			商品数量有限，您只能购买<span>${cart.maxQuantity}</span>件
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
	<input type="hidden" id="checkAPP" value="${checkAPP}"/>
<!--内容区域 start-->
 <form id="cartForm" action="${ctx}/cart/topay" method="get">
 		<input type="hidden" id="shopids" name="shopCartDetailIds"/>
 		<input type="hidden" id="postArea" name="postArea" value=""/>
 </form>
 <c:if test="${cart != null && cart.cartList != null && fn:length(cart.cartList) > 0}">
 <!--内容区域 start-->
  <section class="order_block">
	<c:forEach var="spList" items="${cart.cartList}">
		<dl>
			<c:if test="${spList.title != ''}">
				<dt>
					<c:choose>
						<c:when test="${checkAPP}">
							<a href="shangpinapp://phone.shangpin/actiongoactivitylist?title=尚品&activityid=${spList.id}" target="_blank"></a>
						</c:when>
						<c:otherwise>
							<a href="${ctx }/subject/product/list_${spList.id}" target="_blank"></a>
						</c:otherwise>
					</c:choose>
			          <!-- <span>满减</span>满500元，立减<i class="saveAmount">100</i>元<em>&gt;</em> -->
			          <span>满减</span>
			          <a href="javascript:;" class="promo"></a>${spList.title}<em>&gt;</em>
			        
			      </dt>
			</c:if>

	      <c:forEach var="cartItem" items="${spList.productList}">

	      	<c:choose>
	      		<c:when test="${cartItem.msgType == '1' || cartItem.msgType == '3' || cartItem.msgType == '4'}">
	      			<dd class="clr dd_fail" id="${cartItem.cartDetailId}" ver="${cartItem.spu}">
	      		</c:when>
	      		<c:otherwise>
	      			<dd class="clr cart-goods" id="${cartItem.cartDetailId}" ver="${cartItem.spu}">
	      		</c:otherwise>
	      	</c:choose>

		        <a href="javascript:;" id="list_6" class="close">关闭</a>

		        	<c:choose>
			          	<c:when test="${cartItem.isChecked == '1' }">
                           <!--当商品被勾选中  -->
			          		<a class="input input_curr" href="javascript:;">
			          			<input type="checkbox" class="choice_commodity" checked="checked" value="${cartItem.cartDetailId}" postArea="${cartItem.postArea}"/>
			          		</a>
			          	</c:when>
			          	<c:otherwise>
			          		<a class="input" href="javascript:;">
			          			<input type="checkbox" class="choice_commodity" value="${cartItem.cartDetailId}" postArea="${cartItem.postArea}"/>
			          			</a>
			          	</c:otherwise>
			         </c:choose>

		        
		        <c:choose>
		        	<c:when test="${checkAPP}">
		        		<a href="shangPinApp://phone.shangpin/actiongodetail?productid=${cartItem.spu}" class="img">
				          <span class="fail">售罄</span>
				          <img src="${cdn:pic(pageContext.request)}/styles/shangpin/images/e.gif" lazy="${cartItem.pic}" width="70" height="94" />
				        </a>
		        	</c:when>
		        	<c:otherwise>
		        		<a href="${ctx}/product/detail?productNo=${cartItem.spu}" class="img">
				          <span class="fail">售罄</span>
				          <img src="${cdn:pic(pageContext.request)}/styles/shangpin/images/e.gif" lazy="${cartItem.pic}" width="70" height="94" />
				        </a>
		        	</c:otherwise>
		        </c:choose>

		        <h2>
		          <a href="#">
		            <span>${cartItem.countryDesc}${cartItem.brand}</span>
		            <span>${cartItem.name}</span>
		          </a>
		        </h2>
		
		        <p>
		        	<c:forEach var="attr" items="${cartItem.attribute}">
		        		<c:choose>
		        			<c:when test="${attr.name == '颜色'}">
		        				<span class="color">${attr.name}：${attr.value}</span>
		        			</c:when>
		        			<c:otherwise>
		        				<span>${attr.name}：${attr.value}</span>
		        			</c:otherwise>
		        		</c:choose>
		        	</c:forEach>
		        </p>
		
		        <div class="clr">
		          <span class="price">
		          	<c:if test="${cartItem.priceTag != '' && cartItem.priceTag != null}">
		            <b>&yen;<em class="commodity_price" comprice="${cartItem.price}">${cartItem.price}(${cartItem.priceTag})</em></b>
		            </c:if>
		          </span>
                 
                 <!--调整商品数量  -->
		          <div class="fillNumber">
		            <a href="javascript:;" class="cut">-</a>
		            <span class="prodNum">${cartItem.quantity}</span>
		            <a href="javascript:;" class="add">+</a>
		            <input type="hidden" class="numberVal" value="${cartItem.count}">
		          </div>

		        </div>


		      </dd>
	      </c:forEach>
	    </dl>
	</c:forEach>
    <!--footer start-->
    <footer>
      <div>
        <a class="input_all" href="javascript:;">
          <input type="checkbox" class="choice_commodity">
        </a>
        <span class="total_amount">总金额：<b><em>&yen;<i id="total_amount">${cart.totalAmount}</i></em></b></span>
        <c:if test="${cart.totalAmountDesc != ''}">
        ${cart.totalAmountDesc}
    </c:if>
      </div>

      <a class="btn immediately no_submit" href="javascript:topay('1');">结算(<em id="total_number">${cart.totalSettlement}</em>)</a>
    </footer>
    <!--footer end-->
  </section>
 </c:if>
 
  <!--内容区域 end-->
  <!--海外境内一起结算提示 start-->
  <div class="coupon-tip">
      <c:if test="${cart.prompt != ''}">
      	${cart.prompt}
      </c:if>
  </div>
  <!--海外境内一起结算提示 end-->
  
 <rapid:override name="footer">
  
 </rapid:override>
<!--内容区域 end-->
</rapid:override>

<%@ include file="/WEB-INF/pages/common/common.jsp" %> 