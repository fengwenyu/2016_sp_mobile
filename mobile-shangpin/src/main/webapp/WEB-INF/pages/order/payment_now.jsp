<%@ page language="java" contentType="text/html; charset=UTF-8" trimDirectiveWhitespaces="true"
    pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/pages/common/include.inc.jsp"%>

<rapid:override name="custum">
	<link href="${cdn:css(pageContext.request) }/styles/shangpin/css/page/order_form_outside.css${ver}" rel="stylesheet" />
	<script type="text/javascript" charset="utf-8">
		loader = SP.core
		.install("${cdn:js(pageContext.request)}/styles/shangpin/js/jquery.min.js${ver}")
		.using("${cdn:js(pageContext.request)}/styles/shangpin/js/j.dialogs.js${ver}")
		.excute()
		.using("${cdn:js(pageContext.request)}/styles/shangpin/js/comm.js${ver}")
		.using("${cdn:js(pageContext.request)}/styles/shangpin/js/order_outside.js${ver}")
		.excute();
	</script>
</rapid:override>

<rapid:override name="page_title">
	提交订单
</rapid:override>

<rapid:override name="downloadAppShowHead">
	
</rapid:override>

<rapid:override name="header">
	
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
	        		<c:when test="${checkWX}">
	        			<input type="hidden" id="payTypeId" name="payTypeId" value="27"/>
        				<input type="hidden" id="payTypeChildId" name="payTypeChildId" value="58"/>
	        		</c:when>
	        		<c:otherwise>
	        			<input type="hidden" id="payTypeId" name="payTypeId" value="27"/>
        				<input type="hidden" id="payTypeChildId" name="payTypeChildId" value="117"/>
	        		</c:otherwise>
	        	</c:choose>
			    <fieldset>
			        <legend>收货人信息</legend>
	                <p>
	                    <label for="userName">姓名：</label>
	                    <input type="text" id="J_userName" name="consigneeName" value="${params.consigneeName}" placeholder="请填写个中文名称" required />
	                </p>
	                <p>
	                    <label for="mobileNum">联系电话：</label>
	                    <input type="tel" id="J_mobileNum" name="tel" value="" placeholder="请输入11位手机号码" required maxlength="11" />
	                </p>
	                <p class="authCode-box">
	                    <label for="authCode">验证码：</label>
	                    <input type="tel" id="J_authCode" name="authCode" placeholder="请输入短信验证码" required maxlength="6" />
	                    <em id="passwordGetCode" data-waiting="秒">获取验证码</em>
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
	                    <input type="text" id="J_addr" name="address" value="${params.address}" placeholder="请输入详细街道地址" required />
	                </p>
	                <p>
	                    <label for="code">邮编信息：</label>
	                    <input type="text" id="J_code" name="zip" value="${params.zip}" placeholder="请输入您所在地区的邮编" required maxlength="6"/>
	                </p>
<!-- 			        <p class="select"><a href="javascript:;" id="select_time">收货时间<span class="delivery_mode">工作日收货</span></a></p> -->
			        <legend>支付方式</legend>
	                <p class="payment-method">
	                	<span class="weixinPay">
	                    	<a href="javascript:;" class="cur">微信支付</a>
	                    </span>
	                </p>
			        <p class="total">
					            商品金额：<i>&nbsp;&nbsp;&yen;1</i><br />
			        </p>
			        
			        <div class="payment_submit payment_submit_bg">
	                    <div class="btn-icon clr">
	                    	<a href="javascript:;" class="total_amount">总金额：<em>￥1</em></a>
	                        <a href="javascript:;" class="payment-btn">提交订单（1件）</a>
	                    </div>
	                </div>
			    </fieldset>
			</form>
		</div>
</rapid:override>

<div id="area_overlay"></div>
    <div id="area_layer">
    	<a href="javascript:;" class="prev_btn">返回</a>
        <a href="javascript:;" class="close_btn">关闭</a>
        <h3>省份</h3>
        <dl id="area_province" title="省份">
        	<c:forEach var="provice" items="${provinces}">
        		<dd><a href="#" id="${provice.id}">${provice.name}</a></dd>
        	</c:forEach>
        </dl>
        
        <dl id="area_city" title="城市">
		       
		</dl>
	    <dl id="area_county" title="地区">
	        
	    </dl>
	    <dl id="area_street" title="街道">
	    	
	    </dl>
    </div>
  </div>
<!--end-->

<div class="select-overlay"></div>
<div class="select-layer" id="rece_time_layer">
	<a href="javascript:;" class="close_btn">关闭</a>
	<h3>收货时间</h3>
    <dl class="select-opt">
        <dd><a href="#" class="cur">工作日收货</a></dd>
        <dd><a href="#">工作日、节假日收货</a></dd>
        <dd><a href="#">双休日、节假日收货</a></dd>
    </dl>
</div>

<!--四级地址-->
<div class="select-layer" id="select_street_layer" style="height:207px;	overflow-y:auto;">
	<a href="javascript:;" class="close_btn">关闭</a>
	<h3 style="text-align:center">请选择街道</h3>
    <dl class="select-opt" id="area_street" title="街道">
        <dd><a href="#">中关村</a></dd>
        <dd><a href="#">常营乡</a></dd>
        <dd><a href="#">管庄</a></dd>
        <dd><a href="#">双桥</a></dd>
        <dd><a href="#">十里堡</a></dd>
        <dd><a href="#">劲松</a></dd>
    </dl>
</div>
<rapid:override name="footer">

</rapid:override>
<%@ include file="/WEB-INF/pages/common/bottom_base_mall_banner.jsp" %>