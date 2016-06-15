<%@ page language="java" contentType="text/html; charset=UTF-8" trimDirectiveWhitespaces="true"
    pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/pages/common/include.inc.jsp"%>

<rapid:override name="custum">
	<link href="${cdn:css(pageContext.request)}/styles/shangpin/css/page/overseas/public.css${ver}" rel="stylesheet" />
	<link href="${cdn:css(pageContext.request)}/styles/shangpin/css/page/overseas/pop.css${ver}" rel="stylesheet" />
	<link href="${cdn:css(pageContext.request)}/styles/shangpin/css/page/overseas/pop_small.css${ver}" rel="stylesheet" />
	<link href="${cdn:css(pageContext.request)}/styles/shangpin/css/page/overseas/address.css${ver}" rel="stylesheet" />
	<link href="${cdn:css(pageContext.request)}/styles/shangpin/css/page/overseas/order_form.css${ver}" rel="stylesheet" />
	<link href="${cdn:css(pageContext.request)}/styles/shangpin/css/page/overseas/user_coupon.css${ver}" rel="stylesheet" />
	<script src="${cdn:js(pageContext.request)}/styles/shangpin/js/jquery.min.js${ver}"  type="text/javascript" charset="utf-8"></script>
	<script src="${cdn:js(pageContext.request)}/styles/shangpin/js/comm.js${ver}"  type="text/javascript" charset="utf-8"></script>
	<script src="${cdn:js(pageContext.request)}/styles/shangpin/js/j.dialogs.js${ver}"  type="text/javascript" charset="utf-8"></script>
	<script src="${cdn:js(pageContext.request)}/styles/shangpin/js/j.appDialogs.js${ver}"  type="text/javascript" charset="utf-8"></script>
	<script src="${cdn:js(pageContext.request)}/styles/shangpin/js/overseas/validIDCard.js${ver}"  type="text/javascript" charset="utf-8"></script>
	<script src="${cdn:js(pageContext.request)}/styles/shangpin/js/overseas/order.js${ver}"  type="text/javascript" charset="utf-8"></script>
	<script src="${cdn:js(pageContext.request)}/styles/shangpin/js/overseas/order_core.js${ver}"  type="text/javascript" charset="utf-8"></script>
	<script src="${cdn:js(pageContext.request)}/styles/shangpin/js/overseas/address.js${ver}"  type="text/javascript" charset="utf-8"></script>
	<script src="${cdn:js(pageContext.request)}/styles/shangpin/js/overseas/invoice.js${ver}"  type="text/javascript" charset="utf-8"></script>
	<script src="${cdn:js(pageContext.request)}/styles/shangpin/js/overseas/user_coupon.js${ver}"  type="text/javascript" charset="utf-8"></script>
</rapid:override>
<rapid:override name="title">
	订单提交
</rapid:override>
<rapid:override name="header">
	<!--头部 start-->
	<c:choose>
		<c:when test="${!checkWX&&!checkAPP}">    
			<div class="topFix" id="order_head">
		    <section>
		        <div class="topBack" >
		        <a href="javascript:history.back(-1);" class="backBtn">&nbsp;</a>
		        <span class="top-title">提交订单</span>
		        <ul class="alUser_icon clr">
		           <li><a href="<c:url value='/index'/>"><img src="${cdn:pic(pageContext.request)}/styles/shangpin/images/home_icon.png" width="25" height="25" alt="首页"></a></li>
		        </ul>
		        </div>
		    </section>
	  	</div>
		<div class="alContent" id="order_container">
		</c:when>
		<c:otherwise>
			<div class="alContent" id="order_container"  style="margin-top: 0;">
		</c:otherwise>
	</c:choose>
	    
	  <!--头部 end-->
</rapid:override>
<rapid:override name="content">
	<div class="paymet_block">
	            <fieldset>
	              <legend style="height:0px;"> </legend>
	              <div id="showToAddr" <c:if test="${haveAddress}">style="display:none;"</c:if>>
	                <legend>收货信息</legend>
	                <p class="select"><a href="javascript:;" id="to_add_address">填写配送地址 </a> </p>
	              </div>
	              	<c:if test="${!haveAddress}">
	              		<input type="hidden" id="third_id" value=""/>
                		<input type="hidden" id="fourth_id" value=""/>
	              	</c:if>
	                <div id="showAddr" <c:if test="${!haveAddress}">style="display:none;"</c:if>>
		                	<legend>配送地址</legend>
			                <p class="selected"><a href="javascript:;" id="to_list_address"><em>${address.name }</em><i class="phone">${address.tel }</i><br>
			                <input type="hidden" id="third_id" value="${address.area}"/>
                			<input type="hidden" id="fourth_id" value="${address.town}"/>
			                	 <em id="emcardID" 
			                	 <c:if test="${address.cardID==null ||  address.cardID==''}"> style="display:none;"</c:if>
			                	 >${fn:substring(address.cardID,0,6)}***${fn:substring(address.cardID,fn:length(address.cardID)-4,fn:length(address.cardID)) }</em><br id="embr"
			                	  <c:if test="${address.cardID==null ||  address.cardID==''}"> style="display:none;"</c:if>
			                	 />
			              
			                <span>${address.provName }${address.cityName }${address.areaName }${address.townName}${address.addr}</span></a></p>
	                 </div>
	                <p class="select"><a href="javascript:;" id="select_time">配送方式<span class="delivery_mode">工作日收货</span></a></p>
	                <legend>商品信息</legend>
	                <c:forEach var="spList" items="${elements.list.spList}">
	                	<c:forEach var="product" items="${spList.cartItemList}">
	                		<p class="pord_info">
			                	<a href="javascript:;" class="clr">
			                    <img src="${product.mobileImg}" width="56" height="67">
			                    <ins>
			                        <i>
			                        <c:if test="${product.countryPic != '' }">
			                        	<span><img src="${product.countryPic}" width="20" height="13"></span>
			                        </c:if>
			                        ${product.brandEnName}<br> ${product.productName }</i>
			                        <em>${fn:split(product.mobileSkuAttrText,"|")[0]}</em>
		                        	<em>${fn:split(product.mobileSkuAttrText,"|")[1]}</em><br />
			                         <em>价格：&yen;${fn:substring(product.price,0,fn:indexOf(product.price,'.'))}</em>
			                         <em>数量：${product.quantity }</em>
			                    </ins>
			                    </a>
			                </p>
	                	</c:forEach>
	                </c:forEach>
	                <input type="hidden" id="buyId" value="${elements.list.spList[0].cartItemList[0].shoppingCartDetailId}"/>
	                <input type="hidden" id="totalAmount" value="${elements.amount}"/>
	                <input type="hidden" id="totalCoupon" value="${fn:length(elements.coupon)}"/>
	                <p class="select coupons"><a href="javascript:showCouponPage();">优惠券 <i id="use_conpus">您有 <em>${fn:length(elements.coupon)}</em> 张优惠券可用</i></a></p>
	                <legend>支付方式</legend>
	                <p id="last_pay">您还需要支付剩余 &yen;${elements.payAmount }</p>
	                <c:choose>
	                	<c:when test="${isPayOut=='1' }">
							     <c:choose>
			                	<c:when test="${checkWX }">
				                	<p class="payment-method">
					                	<span class="weixinPayOut" >
					                    	<a href="javascript:;" class="cur">
					                            <em>微信支付</em>
					                        </a>
					                    </span>
					                </p>
			                	</c:when>
			                	<c:otherwise>
			                		<p class="payment-method">
					                	<span class="alipayOut">
					                    	<a href="javascript:;"  class="cur">
					                            <em>支付宝支付</em><br />
					                            储蓄卡支付需开通网银
					                        </a>
					                    </span>
					                </p>
			                	</c:otherwise>
			                </c:choose> 
	                	</c:when>
	                	<c:otherwise>
			                <c:choose>
			                	<c:when test="${checkWX }">
				                	<p class="payment-method">
					                	<span class="weixinPayPub" >
					                    	<a href="javascript:;" class="cur">
					                            <em>微信支付</em>
					                        </a>
					                    </span>
					                </p>
			                	</c:when>
			                	<c:otherwise>
			                		<p class="payment-method">
					                	<span class="weixinPay" >
					                    	<a href="javascript:;" >
					                            <em>微信支付</em>
					                        </a>
					                    </span>
					                </p>
			                		<p class="payment-method">
					                	<span class="alipay">
					                    	<a href="javascript:;"  class="cur">
					                            <em>支付宝支付</em><br />
					                           	 储蓄卡支付需开通网银
					                        </a>
					                    </span>
					                </p>
					                <p class="payment-method">
					                	<span class="unionPay" >
					                    	<a href="javascript:;" >
					                            <em>银联支付</em><br />
					                            	储蓄卡支付需开通网银
					                        </a>
					                    </span>
					                </p>
			                	</c:otherwise>
			                </c:choose>
	                	</c:otherwise>
	                </c:choose>
	              <p class="total">
	              	<c:forEach var="priceShow" items="${elements.priceShow}">
		              	<c:choose>
	              	   		<c:when test="${priceShow.type == '1' }">
	              	   			${priceShow.title}：<i id="product_total_price">&nbsp;&nbsp;&nbsp;&yen;&nbsp;${priceShow.amount}</i><br />
	              	   		</c:when>
	              	   		<c:when test="${priceShow.type == '2' && priceShow.amount != '0' }">
	              	   			${priceShow.title}：<i id="promoto_price">&nbsp;&nbsp;-&yen;&nbsp;${priceShow.amount}</i><br />
	              	   		</c:when>
	              	   		<c:when test="${priceShow.type == '3' && priceShow.amount != '0'}">
	              	   			 <span id="span_pay_coupon">${priceShow.title}：<i id="pay_coupon">&nbsp;&nbsp;-&yen;&nbsp;<b style="font-weight: normal;"></b></i><br /></span>
	              	   		</c:when>
	              	   		<c:when test="${priceShow.type == '5' }">
	              	   			<span id="span_carriage">${priceShow.title}：<i id="carriage">&nbsp;&nbsp;&nbsp;&yen;&nbsp;${priceShow.amount}</i><br /></span>
	              	   		</c:when>
	              	   		<c:when test="${priceShow.type == '6' && priceShow.amount != '0'}">
	              	   			<span id="span_nocarriage">${priceShow.title}：<i id="nocarriage">&nbsp;&nbsp;-&yen;&nbsp;${priceShow.amount}</i></span>
	              	   		</c:when>
	              	   		<c:when test="${priceShow.type == '7' }">
	              	   			<em>${priceShow.title}：<i id="real_pay">&nbsp;&nbsp;&nbsp;&yen;&nbsp;<b>${priceShow.amount}</b></i></em>
	              	   		</c:when>
              	   		</c:choose>
	              	</c:forEach>
	               </p>
	                
	                <div class="payment_submit">
	                    <a href="javascript:;" id="so" class="payment_btn" onclick="submitOrder();">提交订单</a>
	                </div>
	            </fieldset>
	    </div>
	    </div>
	    <div id="overlay"></div>
	<div id="popup_box" >
	    <a href="javascript;" class="title_closeBtn">×</a>
	    <h1 id="popup_title" style="display: block;">填写收货人身份证号码</h1>
	    <div id="popup_content" class="share">
	        <div id="popup_message">
	        	<p>根据海关的要求，海外商品需要您填写收货人的身份证号码进行个人物品入境申报。</p>
	            <div class="identification-box">
	                <label for="identificationNum">身份证号码：</label>
	                <input type="text" id="carIDNum" name="identificationNum" placeholder="请输入身份证号码" required="" maxlength="18">
	            </div>
	        </div>
	        <div id="popup_panel">
	          <button type="button" id="popup_cancel">&nbsp;取消&nbsp;</button> 
	          <button type="button" id="popup_ok"  onclick="saveCardID();">&nbsp;保存&nbsp;</button>
	        </div>
	    </div>
	</div>
<%@ include file="order_fill_address.jsp" %> 
<%@ include file="order_fill_address_add.jsp" %> 
<%@ include file="order_fill_address_edit.jsp" %> 
<%@ include file="order_fill_coupons.jsp" %> 
<%@ include file="order_fill_form.jsp" %> 
  </rapid:override>  
<rapid:override name="footer">
	<div id="area_overlay1"></div>
	<div id="area_layer1">
		<a href="javascript:;" class="close_btn">关闭</a>
		<h3>配送方式</h3>
	    <dl id="area_province">
	        <dd><a href="javascript:;" class="cur"  timeId="1">工作日收货</a></dd>
	        <dd><a href="javascript:;"  timeId="2">工作日、节假日收货</a></dd>
	        <dd><a href="javascript:;" timeId="3">双休日、节假日收货</a></dd>
	    </dl>
	</div>
	
	<div id="select-area-overlay" style="height: 667px; display: none;"></div>
	<div class="select-layer" id="select_street_layer" style="display: none;">
		<a href="javascript:;" class="close_btn">关闭</a>
		<h3 style="text-align:center">请选择街道</h3>
	    <dl class="select-opt" id="orgin_area_street" title="街道">
	    </dl>
	</div>
</rapid:override>

<%@ include file="/WEB-INF/pages/common/another_base.jsp" %> 