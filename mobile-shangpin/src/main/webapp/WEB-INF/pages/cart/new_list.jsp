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
		.using("${cdn:js(pageContext.request)}/styles/shangpin/js/cart/shopping_cart.js${ver}");
	</script>
</rapid:override>

<rapid:override name="header">
	<div class="fixed_top">
		<div class="errorPrompt" style="display: none">
			商品数量有限，您只能购买<span>${shopCartItem.maxQuantity}</span>件
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
 <c:if test="${shopCartItem != null && shopCartItem.cartList != null && fn:length(shopCartItem.cartList) > 0}">
 <!--内容区域 start-->
  <section class="order_block">
	<p class="classify_title">国内商品</p>
	<c:forEach var="spList" items="${shopCartItem.cartList}">
		<dl>
			<c:if test="${spList.title != ''}">
				<dt>
					<c:choose>
						<c:when test="${checkAPP}">
							<a href="shangpinapp://phone.shangpin/actiongoactivitylist?title=尚品&activityid=${spList.id}" target="_blank">
						</c:when>
						<c:otherwise>
							<a href="${ctx }/subject/product/list_${spList.id}" target="_blank">
						</c:otherwise>
					</c:choose>
			          <!-- <span>满减</span>满500元，立减<i class="saveAmount">100</i>元<em>&gt;</em> -->
			          <span>满减</span><c:if test="${shopCartItem.spareAmount != '0'}">${shopCartItem.spareAmount}</c:if><i class="saveAmount"></i>${spList.title}<em>&gt;</em>
			        </a>
			      </dt>
			</c:if>
	      <c:forEach var="cartItem" items="${spList.productList}">
	      	<c:choose>
	      		<c:when test="${cartItem.msgType == '1' || cartItem.msgType == '3' || cartItem.msgType == '4'}">
	      			<dd class="clr dd_fail" id="${cartItem.cartDetailId}" ver="${cartItem.spu}">
	      		</c:when>
	      		<c:otherwise>
	      			<dd class="clr domestic-goods" id="${cartItem.cartDetailId}" ver="${cartItem.spu}">
	      		</c:otherwise>
	      	</c:choose>
		        <a href="javascript:;" id="list_6" class="close">关闭</a>
		        	<c:choose>
			          	<c:when test="${cartItem.isChecked == '1' }">
			          		<a class="input input_curr" href="javascript:;">
			          			<input type="checkbox" class="choice_commodity" checked="checked" value="${cartItem.cartDetailId}"/>
			          	</c:when>
			          	<c:otherwise>
			          		<a class="input" href="javascript:;">
			          			<input type="checkbox" class="choice_commodity" value="${cartItem.cartDetailId}" />
			          	</c:otherwise>
			         </c:choose>
		        </a>
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
		            <span>${cartItem.brand}</span>
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
		            <b>&yen;<em class="commodity_price" comprice="${cartItem.price}">${cartItem.price}</em></b>×
		          </span>
		          <div class="fillNumber" region="${cartItem.postArea}">
		            <a href="javascript:;" class="cut">-</a>
		            <span class="prodNum">${cartItem.quantity}</span>
		            <a href="javascript:;" class="add">+</a>
		            <input type="hidden" class="numberVal" value="${cartItem.count}">
		          </div>
		        </div>
		        <c:if test="${cartItem.priceTag != '' && cartItem.priceTag != null}">
		        	<em class="premium-price">${cartItem.priceTag}</em>
		        </c:if>
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
        <span class="total_amount">总金额：<b><em>&yen;<i id="total_amount">0</i></em></b></span>
        <span class="save_amount">已节省：&yen;<em>0</em></span>
      </div>
      <a class="btn immediately no_submit" href="javascript:topay('1');">结算(<em id="total_number">0</em>)</a>
    </footer>
    <!--footer end-->
  </section>
 </c:if>
 
 <c:if test="${shopCartItem != null && shopCartItem.abroad != null && fn:length(shopCartItem.abroad) > 0}">
  <div class="oversea_block">
	<p class="classify_title">全球购商品</p>  	  
	<span class="oversea_tip">因海关政策要求，全球购商品需单件结算</span>  
	<c:forEach var="abordList" items="${shopCartItem.abroad}">
		<section class="order_block">
			<dl>
	      	  <c:forEach var="abordItem" items="${abordList.cartList}">
	      	  	<c:if test="${abordItem.title != ''}">
					<dt>
						<c:choose>
							<c:when test="${checkAPP}">
								<a href="shangpinapp://phone.shangpin/actiongoactivitylist?title=尚品&activityid=${abordItem.id}" >
		    				</c:when>
							<c:otherwise>
								<a href="${ctx }/subject/product/list_${abordItem.id}" target="_blank">
							</c:otherwise>
						</c:choose>
						  <span>满减</span><c:if test="${abordList.spareAmount != '0'}">${abordList.spareAmount}</c:if><i class="saveAmount"></i>${abordItem.title}<em>&gt;</em>
				        </a>
				      </dt>
				</c:if>
	      	  	<c:forEach var="item" items="${abordItem.productList}">
	      	  		<c:choose>
			      		<c:when test="${item.msgType == '1' || item.msgType == '3' || item.msgType == '4'}">
			      			<dd class="clr dd_fail overseas-goods">
			      		</c:when>
			      		<c:otherwise>
			      			<dd class="clr overseas-goods">
			      		</c:otherwise>
			      	</c:choose>
			        <a href="javascript:;" id="list_1" class="close">关闭</a>
			         <c:choose>
			          	<c:when test="${item.isChecked == '1' }">
			          	    <a class="input input_curr" href="javascript:;">
			          			<input type="checkbox" class="choice_commodity" checked="checked" value="${item.cartDetailId}"/>
			          	</c:when>
			          	<c:otherwise>
			          		<a class="input" href="javascript:;">
			          			<input type="checkbox" class="choice_commodity" value="${item.cartDetailId}" />
			          	</c:otherwise>
			          </c:choose>
			        </a>
			        <c:choose>
			        	<c:when test="${checkAPP}">
					        <a href="shangPinApp://phone.shangpin/actiongodetail?productid=${item.spu}" class="img">
					          <span class="fail">售罄</span>
					          <img src="${cdn:pic(pageContext.request)}/styles/shangpin/images/e.gif" lazy="${item.pic}" width="70" height="94" />
					        </a>
			        	</c:when>
			        	<c:otherwise>
					        <a href="${ctx}/product/detail?productNo=${item.spu}" class="img">
					          <span class="fail">售罄</span>
					          <img src="${cdn:pic(pageContext.request)}/styles/shangpin/images/e.gif" lazy="${item.pic}" width="70" height="94" />
					        </a>
			        	</c:otherwise>
			        </c:choose>
			       
			        <h2>
			          <a href="#">
			            <c:if test="${item.countryPic != '' && item.countryPic != null}">
			            	<span><i><img src="${item.countryPic}" width="20" height="13" /></i>${item.brand}</span>
			            </c:if>
			            <span>${item.name}</span>
			          </a>
			        </h2>
			        <p>
			          <c:forEach var="abordAttr" items="${item.attribute}">
			          	<c:choose>
			          		<c:when test="${abordAttr.name == '颜色'}">
			          			<span class="color">${abordAttr.name}：${abordAttr.value}</span>
			          		</c:when>
			          		<c:otherwise>
			          			<span>${abordAttr.name}：${abordAttr.value}</span>
			          		</c:otherwise>
			          	</c:choose>
			          </c:forEach>
			        </p>
			        <div class="clr">
			          <span class="price">
			            <b>&yen;<em class="commodity_price" comprice="${item.price}">${item.price}</em></b>×
			          </span>
			          <div class="fillNumber">
			            <a href="javascript:;" class="cut">-</a>
			            <span class="prodNum">${item.quantity}</span>
			            <a href="javascript:;" class="add">+</a>
			            <input type="hidden" class="numberVal" value="${item.count}">
			          </div>
			        </div>
			        <c:if test="${item.priceTag != '' && item.priceTag != null}">
			        	<em class="premium-price">${item.priceTag}</em>
			        </c:if>
			      </dd>
	      	  	</c:forEach>
	      	  </c:forEach>
	    	</dl>
		   <!--footer start-->
		    <footer>
		      <div>
		        <a class="input_all" href="javascript:;">
		          <input type="checkbox" class="choice_commodity">
		        </a>
		        <span class="total_amount">总金额：<b><em>&yen;<i id="total_amount">0</i></em></b></span>
		        <span class="save_amount">已节省：&yen;<em>0</em></span>
		      </div>
		      <a class="btn immediately no_submit" href="javascript:topay('2');" >结算(<em id="total_number">0</em>)</a>
		    </footer>
		    <!--footer end-->
	    </section>
	</c:forEach>
  <!--内容区域 end-->
  </div>
  <!--海外境内一起结算提示 start-->
  <div class="coupon-tip">
      <c:if test="${shopCartItem.prompt != ''}">
      	${shopCartItem.prompt}
      </c:if>
  </div>
  <!--海外境内一起结算提示 end-->
  
  <!-- 点击结算提醒弹层 --> 
  <div class="overlay" id="overlay">
    <section class="modal modal-test">
      <div class="modal-bd">
        <p>对不起，海外购商品和国内商品无法一起结算，请分开进行结算。</p>
        <em class="btn-close"><img src="${cdn:pic(pageContext.request)}/styles/shangpin/images/cart/close.png" width="20" height="20" /></em>
      </div>
      <div class="modal-ft">
        <span class="btn-modal"><a href="">结算海外购商品</a></span>
        <span class="btn-modal china-btn"><a href="">结算国内商品</a></span>
      </div>
      
    </section>		
  </div>
  
  <!-- 提示层 --> 
  <div class="tip-overlay"></div>
  <div class="tip-container" >
      <p class="tip-message">因海关政策要求，全球购商品需单件结算。</p>
  </div>
 </c:if>
 <rapid:override name="footer">
  
 </rapid:override>
<!--内容区域 end-->
</rapid:override>

<%@ include file="/WEB-INF/pages/common/common.jsp" %> 