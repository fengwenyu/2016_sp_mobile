<%@ page language="java" contentType="text/html; charset=UTF-8"
	trimDirectiveWhitespaces="true" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/pages/common/include.inc.jsp"%>
<rapid:override name="custum">
	<link
		href="${cdn:css(pageContext.request) }/styles/shangpin/css/base.css${ver}"
		rel="stylesheet" />
	<link
		href="${cdn:css(pageContext.request) }/styles/shangpin/css/page/cart/shopping_cart520.css${ver}"
		rel="stylesheet" />

	<script src="${cdn:js(pageContext.request)}/styles/shangpin/js/core.js"
		type="text/javascript" charset="utf-8"></script>
	<script type="text/javascript" charset="utf-8">
		var ver = Math.random();
		loader = SP.core.install(
				"${cdn:js(pageContext.request)}/styles/shangpin/js/jquery.min.js?"
						+ ver).using(
				"${cdn:js(pageContext.request)}/styles/shangpin/js/lazyload.js?"
						+ ver).using(
				"${cdn:js(pageContext.request)}/styles/shangpin/js/config.sim.js?"
						+ ver).excute().using(
				"${cdn:js(pageContext.request)}/styles/shangpin/js/j.floatCalculate.js?"
						+ ver) //计算价格
		.using(
				"${cdn:js(pageContext.request)}/styles/shangpin/js/j.appDialogs.js?"
						+ ver) //弹窗
		.using(
				"${cdn:js(pageContext.request)}/styles/shangpin/js/cart/shopping_cart520.js?"
						+ ver).excute();
	</script>
</rapid:override>

<rapid:override name="header">
	<div class="fixed_top">
		<div class="errorPrompt">
			商品数量有限，您只能购买<span>${cart.maxQuantity}</span>件
		</div>
	</div>
	<c:if test="${!checkAPP&&!checkWX }">
		<div class="topFix">
			<section>
				<div class="topBack">
					<a href="javascript:history.go(-1);" class="backBtn">&nbsp;</a> <span>
						购物车 </span>

					<ul class="alUser_icon clr">
						<li><a href="<c:url value='/index'/>"><img
								src="${cdn:pic(pageContext.request)}/styles/shangpin/images/home_icon.png"
								width="25" height="25" alt="首页"></a></li>
					</ul>
				</div>
			</section>
		</div>

	</c:if>
</rapid:override>

<rapid:override name="content">
	<input type="hidden" id="checkAPP" value="${checkAPP}" />
	<input type="hidden" name="wx" value="${checkWX}">
	<form id="cartForm" action="${ctx}/cart/topay" method="get">
		<input type="hidden" id="shopids" name="shopCartDetailIds" />
	</form>
	<c:if
		test="${cart != null && cart.cartList != null && fn:length(cart.cartList) > 0 }">
		<input type="hidden" id="ctx" value="${ctx}" />
		<!--内容区域 start-->
		<section class="order_block">
			<c:forEach var="spList" items="${cart.cartList}">
				<dl>
					<c:if test="${spList.title != ''}">
						<dt>
							<c:choose>
								<c:when test="${spList.click.type =='1'}">
									<c:choose>
										<c:when test="${checkAPP }">
											<a
												href="shangpinapp://phone.shangpin/actiongoactivitylist?title=${spList.click.name}&activityid=${spList.click.refContent}">
										</c:when>
										<c:otherwise>
											<a
												href="${ctx }/subject/product/list?topicId=${spList.click.refContent}&postArea=1"
												>
										</c:otherwise>
									</c:choose>
								</c:when>

								<c:when test="${spList.click.type == '2'}">
									<c:choose>
										<c:when test="${checkAPP }">
											<a
												href="shangpinapp://phone.shangpin/actiongocatelist?title=${spList.click.name}&filters=category_${spList.click.refContent}&categoryid=${spList.click.refContent}">
										</c:when>
										<c:otherwise>
											<a
												href="${ctx }/category/product/list?categoryNo=${spList.click.refContent}&postArea=1"
												>
										</c:otherwise>
									</c:choose>
								</c:when>

								<c:when test="${spList.click.type == '3'}">
									<c:choose>
										<c:when test="${checkAPP }">
											<a
												href="shangpinapp://phone.shangpin/actiongobrandlist?title=${spList.click.name}&filters=brand_${spList.click.refContent}&brandid=${spList.click.refContent}">
										</c:when>
										<c:otherwise>
											<a
												href="${ctx}/brand/product/list?brandNo=${spList.click.refContent}"
												>
										</c:otherwise>
									</c:choose>
								</c:when>

								<c:when test="${spList.click.type == '4'}">
									<c:choose>
										<c:when test="${checkAPP }">
											<a
												href="shangpinapp://phone.shangpin/actiongodetail?title=${spList.click.name}&productid=${spList.click.refContent}">
										</c:when>
										<c:otherwise>
											<a
												href="${ctx}/product/detail?productNo=${spList.click.refContent}"
												>
										</c:otherwise>
									</c:choose>
								</c:when>

								<c:when test="${spList.click.type == '5'}">
									<c:choose>
										<c:when test="${checkAPP }">
											<a
												href="${spList.click.refContent}">
										</c:when>
										<c:otherwise>
											<a
												href="${spList.click.refContent}"
												>
										</c:otherwise>
									</c:choose>
								</c:when>
								
								<c:when test="${spList.click.type =='0'}">
									<c:choose>
										<c:when test="${checkAPP }">
											<a
												href="shangpinapp://phone.shangpin/actiongoactivitylist?title=${spList.click.name}&activityid=${spList.click.refContent}">
										</c:when>
										<c:otherwise>
											<a
												href="${ctx }/subject/product/list?topicId=${spList.click.refContent}&postArea=1"
												>
										</c:otherwise>
									</c:choose>
								</c:when>
							</c:choose>

							<span>满减</span> <i class="saveAmount">${spList.title}</i><em>&gt;</em>
							</a>
						</dt>
					</c:if>

					<c:forEach var="cartItem" items="${spList.productList}">
						<c:choose>
							<c:when test="${cartItem.msgType == '1'}">
								<dd class="clr dd_fail" id="${cartItem.cartDetailId}"
									ver="${cartItem.spu}">
							</c:when>

							<c:when test="${cartItem.msgType == '4'}">
								<dd class="clr dd_offshelf" id="${cartItem.cartDetailId}"
									ver="${cartItem.spu}">
							</c:when>

							<c:when
								test="${cartItem.msgType == '2' || cartItem.postArea =='1'}">
								<dd class="clr domestic-goods" id="${cartItem.cartDetailId}"
									ver="${cartItem.spu}">
							</c:when>
							<c:when
								test="${cartItem.msgType == '2' || cartItem.postArea =='2'}">
								<dd class="clr overseas-goods" id="${cartItem.cartDetailId}"
									ver="${cartItem.spu}">
							</c:when>

						</c:choose>

						<a href="javascript:;" id="list_2" class="close">关闭</a>

						<c:choose>
							<c:when test="${cartItem.isChecked == '1' }">
								<!--当商品被勾选中  -->
								<a class="input input_curr" href="javascript:;"> <input
									type="checkbox" class="choice_commodity" checked="checked"
									value="${cartItem.cartDetailId}"
									postArea="${cartItem.postArea}" />
								</a>
							</c:when>
							<c:otherwise>
								<a class="input" href="javascript:;"> <input type="checkbox"
									class="choice_commodity" value="${cartItem.cartDetailId}"
									postArea="${cartItem.postArea}" />
								</a>
							</c:otherwise>
						</c:choose>

						<c:choose>
							<c:when test="${checkAPP}">
								<a
									href="shangPinApp://phone.shangpin/actiongodetail?productid=${cartItem.spu}"
									class="img"> <span class="fail">售罄</span> <img
									src="${cdn:pic(pageContext.request)}/styles/shangpin/images/e.gif"
									lazy="${cartItem.pic}" width="70" height="94" />
								</a>
							</c:when>
							<c:otherwise>
								<a href="${ctx}/product/detail?productNo=${cartItem.spu}"
									class="img"> <span class="fail">售罄</span> <img
									src="${cdn:pic(pageContext.request)}/styles/shangpin/images/e.gif"
									lazy="${cartItem.pic}" width="70" height="94" />
								</a>
							</c:otherwise>
						</c:choose>

						<h2>
							<a href="#"> <span>${cartItem.brand}</span> <span>${cartItem.name}</span>
							</a>
						</h2>

						<c:if test="${cartItem.countryDesc!= ''||cartItem.postArea =='2'}">
							<p class="oversea_tag">${cartItem.countryDesc}</p>
						</c:if>

						<p class="clr">
							<c:forEach var="attr" items="${cartItem.attribute}">
								<c:choose>
									<c:when test="${attr.name == '颜色'}">
										<span class="color">${attr.name}：${attr.value}</span>
									</c:when>
									<c:otherwise>
										<span class="size">${attr.name}：${attr.value}</span>
									</c:otherwise>
								</c:choose>
							</c:forEach>
						</p>

						<div class="clr">
							<span class="price"> <b>&yen;<em
									class="commodity_price" comprice="${cartItem.price}">${cartItem.price}</em>
							</b><br /> <c:if
									test="${cartItem.priceTag != '' && cartItem.priceTag != null}">
									<em class="commodity_price_type"
										comprice="${cartItem.priceTag}">${cartItem.priceTag}</em>
								</c:if>
							</span>

							<div class="fillNumber">
								<c:choose>
									<c:when test="${cartItem.quantity=='1'}">
										<a href="javascript:;" class="cut disabled">-</a>
									</c:when>
									<c:otherwise>
										<a href="javascript:;" class="cut">-</a>
									</c:otherwise>
								</c:choose>
								<span class="prodNum">${cartItem.quantity}</span>
								<c:choose>
									<c:when test="${cartItem.quantity=='10'}">
										<a href="javascript:;" class="add disabled">+</a>
									</c:when>
									<c:otherwise>
										<a href="javascript:;" class="add">+</a>
									</c:otherwise>
								</c:choose>
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
					<a class="input_all" href="javascript:;"> <input
						type="checkbox" class="choice_commodity">
					</a> <span class="total_amount">总金额：<b><em>&yen;<i
								id="total_amount">0</i></em></b></span>
					<c:if test="${cart.totalAmountDesc != ''}">
						<span class="save_amount">(${cart.totalAmountDesc})</span>
					</c:if>

				</div>
				<a class="btn immediately no_submit" href="javascript:;"
					id="immediately">结算(<em id="total_number">0</em>)
				</a>
			</footer>
		</section>
		<!-- 提示层 -->

	</c:if>

	<!--海外境内一起结算提示 start-->
	<div class="coupon-tip" >
		<c:if test="${cart.prompt != ''}">
			${cart.prompt}
		</c:if>
	</div>
	<!--海外境内一起结算提示 end-->

	<rapid:override name="footer">

	</rapid:override>
</rapid:override>
<%@ include file="/WEB-INF/pages/common/common.jsp"%>
