<%@ page language="java" contentType="text/html; charset=UTF-8" trimDirectiveWhitespaces="true"
    pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/pages/common/include.inc.jsp"%>

<rapid:override name="custum">
	<link href="${cdn:css(pageContext.request) }/styles/shangpin/css/page/payment.css${ver}" rel="stylesheet" />
	<script type="text/javascript" charset="utf-8">
		loader = SP.core
		.install("${cdn:js(pageContext.request)}/styles/shangpin/js/jquery.min.js${ver}")
		.using("${cdn:js(pageContext.request)}/styles/shangpin/js/j.dialogs.js${ver}")
		.excute()
		.using("${cdn:js(pageContext.request)}/styles/shangpin/js/comm.js${ver}")
		.using("${cdn:js(pageContext.request)}/styles/shangpin/js/payment.js${ver}")
		.excute();
	</script>
</rapid:override>

<rapid:override name="page_title">
	提交订单
</rapid:override>

<rapid:override name="content">
		<div class="paymet_block">
			<form name="login" id="J_Login">
				<input type="hidden" id="province" name="province" value="${params.province}"/>
	        	<input type="hidden" id="provinceName" name="provinceName" value="${params.provinceName}"/>
	        	<input type="hidden" id="city" name="city" value="${params.city}"/>
	        	<input type="hidden" id="cityName" name="cityName" value="${params.cityName}"/>
	        	<input type="hidden" id="area" name="area" value="${params.area}"/>
	        	<input type="hidden" id="areaName" name="areaName" value="${params.areaName}"/>
	        	<input type="hidden" id="town" name="town" value="${params.town}"/>
	        	<input type="hidden" id="townName" name="townName" value="${params.townName}"/>
	        	<input type="hidden" id="skuId" name="skuId" value="${sku}"/>
	        	<input type="hidden" id="productId" name="productId" value="${productNo}"/>
	        	<input type="hidden" id="amount" name="amount" value="${amount}"/>
        		<c:choose>
		        	<c:when test="${checkWX }">
		        		<input type="hidden" id="payTypeId" name="payTypeId" value="${params.payTypeId == null ? '27' : params.payTypeId}"/>
        				<input type="hidden" id="payTypeChildId" name="payTypeChildId" value="${params.payTypeChildId == null ? '58' : params.payTypeChildId}"/>
		        	</c:when>
		        	<c:otherwise>
		        		<input type="hidden" id="payTypeId" name="payTypeId" value="${params.payTypeId == null ? '20' : params.payTypeId}"/>
        				<input type="hidden" id="payTypeChildId" name="payTypeChildId" value="${params.payTypeChildId == null ? '37' : params.payTypeChildId}"/>
		        	</c:otherwise>
		        </c:choose>
	        
	        	
			    <fieldset>
			        <legend>收货人信息</legend>
			        <p>
			            <label for="userName">姓名：</label>
			            <input type="text" id="J_userName" name="consigneeName" placeholder="请填写个中文名称" required value="${params.consigneeName }"/>
			        </p>
			        <p>
			            <label for="mobileNum">联系电话：</label>
			            <input type="text" id="J_mobileNum" name="tel" placeholder="请输入11位手机号码" required maxlength="11" value="${params.tel }"/>
			        </p>
			        <legend>收货地址</legend>
			        <c:choose>
			        	<c:when test="${params == '' || params == null }">
			        		<p class="select"><a href="#" id="select_area">省市区</a></p>
			        	</c:when>
			        	<c:otherwise>
			        		<p class="select"><a href="#" id="select_area">
				        	${params.provinceName}&nbsp;${params.cityName}&nbsp;${params.areaName}&nbsp;${params.townName}</a>
				        </p>
			        	</c:otherwise>
			        </c:choose>
			        <p>
			            <label for="addr">详细地址：</label>
			            <input type="text" id="J_addr" name="address" placeholder="请输入详细街道地址" required value="${params.address}"/>
			        </p>
			        <p>
			            <label for="code">邮编信息：</label>
			            <input type="text" id="J_code" name="zip" placeholder="请输入您所在地区的邮编" required maxlength="6" value="${params.zip}" />
			        </p>
			        <legend>支付方式</legend>
			       	<c:choose>
			        	<c:when test="${checkWX }">
			        	<p>
		                	<span class="weixinPay" id="weixinPay">
		                    	<a href="javascript:;" class="cur">
		                            <em>微信支付</em>
		                        </a>
		                    </span>
		                </p>
			        	
			        	</c:when>
			        	<c:otherwise>
					        <c:choose>
					        	<c:when test="${params.payTypeId == '20' && params.payTypeChildId == '37'}">
					        		<p>
							        	<span class="alipay">
							            	<a href="javascript:;" class="cur"><em>支付宝支付</em><br />储蓄卡支付需开通网银</a>
							            </span>
							        </p>
							        <p>
							        	<span class="unionPay">
							            	<a href="javascript:;"><em>银联支付</em><br />储蓄卡支付需开通网银</a>
							            </span>
							        </p>
					        	</c:when>
					        	<c:when test="${params.payTypeId == '19' && params.payTypeChildId == '49'}">
					        		<p>
							        	<span class="alipay">
							            	<a href="javascript:;"><em>支付宝支付</em><br />储蓄卡支付需开通网银</a>
							            </span>
							        </p>
							        <p>
							        	<span class="unionPay">
							            	<a href="javascript:;" class="cur"><em>银联支付</em><br />储蓄卡支付需开通网银</a>
							            </span>
							        </p>
					        	</c:when>
					        	<c:otherwise>
					        		 <p>
							        	<span class="alipay">
							            	<a href="javascript:;" class="cur"><em>支付宝支付</em><br />储蓄卡支付需开通网银</a>
							            </span>
							        </p>
							        <p>
							        	<span class="unionPay">
							            	<a href="javascript:;"><em>银联支付</em><br />储蓄卡支付需开通网银</a>
							            </span>
							        </p>
					        	</c:otherwise>
					        </c:choose>
			       		</c:otherwise>
		        	 </c:choose>
			        <p class="total">
					            商品金额：<i>&nbsp;&nbsp;&yen;${amount}</i><br />
					           运费：<i>+ &yen;20</i><br />
					    <c:if test="${amount >= 499}">         
					                   运费减免(满499包邮)：<i>- &yen;20</i>
					    </c:if> 
					    <em>应付金额：<i>&nbsp;&nbsp;&yen;${amount ge 499?amount:20+amount}</i>
					    </em>
			        </p>
			        
			        <div class="payment_submit">
			            <a href="javascript:;" onclick="quickSubmitOrder();" class="payment_btn">提交订单</a>
<!-- 			            <input type="submit" class="payment_btn" value="提交订单" onclick="" /> -->
			        </div>
			    </fieldset>
			</form>
		</div>

	<div id="area_overlay"></div>
	<div id="area_layer">
		<h3>省份</h3>
	    <dl id="area_province" title="省份">
	    	<c:forEach var="province" items="${provinces}">
	    		<dd><a href="#"id="${province.id}">${province.name}</a></dd>
	    	</c:forEach>
	    </dl>
	    <dl id="area_city" title="城市">
	       
	    </dl>
	    <dl id="area_county" title="地区">
	        
	    </dl>
	    <dl id="area_street" title="街道">
	        
	    </dl>
	</div>
</rapid:override>
<rapid:override name="footer">

</rapid:override>
<%@ include file="/WEB-INF/pages/common/common.jsp" %> 