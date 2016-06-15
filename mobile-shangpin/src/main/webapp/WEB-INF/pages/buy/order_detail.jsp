<%@ page language="java" contentType="text/html; charset=UTF-8" trimDirectiveWhitespaces="true"
    pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/pages/common/include.inc.jsp"%>
	<c:choose>
		<c:when test="${!checkWX&&!checkAPP}">    
			<div class="topFix" id="order_header">
			    <section>
			        <div class="topBack" >
			        <a href="javascript:leave();" class="backBtn">&nbsp;</a>
			        	提交订单
			        <ul class="alUser_icon clr">
			            <li><a href="<c:url value='/index'/>"><img src="${cdn:pic(pageContext.request)}/styles/shangpin/images/home_icon.png" width="25" height="25" alt="首页"></a></li>
			        </ul>
			        </div>
			    </section>
			 </div>
			<div class="alContent order_detail">
		</c:when>
		<c:otherwise>
			<div class="alContent order_detail" style="margin-top: 0;">
		</c:otherwise>
	</c:choose>
<div class="paymet_block">
	 <form name="login" id="J_Login">
            <fieldset>
            
           		<legend class="price"><em>应付总额：<i>&nbsp;&nbsp;&yen;<b>${fn:substring(real_pay,0,fn:indexOf(real_pay,'.'))}</b></i></em><span id="payDesc">
           		微信
           		</span></legend>
             
                <legend>收货信息</legend>
                <c:if test="${haveAddress}">
                	<input type="hidden" id="addrid" name="addrid" value="${address.id}"/>
                	<input type="hidden" id="third_id" value="${address.area}"/>
                	<input type="hidden" id="fourth_id" value="${address.town}"/>
                	<p id="pats" class="select selected" ><a href="javascript:showAddress('order_address_list','order_address_list_header');">${address.name}，${address.tel}<br>${address.provName}${address.cityName}${address.areaName}${address.townName}${address.addr}</a></p>
                </c:if>
                <c:if test="${!haveAddress}">
                	<input type="hidden" id="addrid" name="addrid" value=""/>
                	<input type="hidden" id="third_id" value=""/>
                	<input type="hidden" id="fourth_id" value=""/>
                	<p id="pats" class="select"><a href="javascript:showAddress('order_address_list','order_address_list_header');">填写配送地址 </a> </p>
                </c:if>
                
                <c:forEach var="delivery" items="${buyNow.delivery}">
                	<c:choose>
                		<c:when test="${delivery.isSelected == '1'}">
                			<p class="select"><a href="#" id="select_time">收货时间<span class="delivery_mode">${delivery.desc}</span></a></p>
                		</c:when>
                	</c:choose>
                </c:forEach>
                <c:if test="${buyNow.lastDeliveryType == '0' || buyNow.lastDeliveryType == ''}">
                	<p class="select"><a href="#" id="select_time">收货时间<span class="delivery_mode">请选择收货时间</span></a></p>
                </c:if>
                <c:if test="${buyNow.postArea=='1'}">
	              <legend>发票信息</legend>
	              	<p class="select-invoice">
	                	<a href="#">是否开发票</a>
	                	<span class="select-option" id="yes">是</span>
	                    <span class="select-option cur" id="no">否</span>
	                </p>
	                <p class="select invoice" style="display: none;">
	                	<c:if test="${buyNow.lastInvoiceFlag == '1'}">
	                		<a href="javascript:showPage('order_invoice','order_invoice_header');">
	                    	<c:choose>
	                			<c:when test="${buyNow.lastInvoiceType == '1' }">
	                				发票类型：<em id="invoice_type_text">个人</em><br />
	                			</c:when>
	                			<c:otherwise>
	                				发票类型：<em id="invoice_type_text">公司</em><br />
	                			</c:otherwise>
	                		</c:choose>
					                        发票抬头：<em id="invoice_title">${buyNow.lastInvoiceTitle}</em><br />
					                        发票内容：<em id="invoice_content">${buyNow.lastInvoiceContent}</em>
	                 		</a>
	<%--                  		<input type="hidden" id="invoice_addr" name="invoiceaddrid" value="${buyNow.lastInvoiceId}"/> --%>
	                 		<input type="hidden" id="invoice_addr" name="invoiceaddrid" value="${buyNow.lastInvoiceID}"/>
	                	</c:if>
	                    <c:if test="${buyNow.lastInvoiceFlag != '1'}">
	                		<a href="javascript:showPage('order_invoice','order_invoice_header');">
	                    	发票类型：<em id="invoice_type_text">个人</em><br />
					                        发票抬头：<em id="invoice_title">个人</em><br />
					                        发票内容：<em id="invoice_content">商品明细</em>
	                 		</a>
	                 		<input type="hidden" id="invoice_addr" name="invoiceaddrid" value=""/>
	                	</c:if>
	                </p>
                </c:if>
                <legend>商品信息</legend>
                <c:forEach var="product" items="${buyNow.list}">
                	<p class="pord_info" id="${product.buyId}">
		           		<c:choose>
			            	<c:when test="${checkAPP}">
			              		<a href="javascript:;" class="clr">
			              	</c:when>
			             	 <c:otherwise>
			              		<a href="javascript:;" class="clr">
			              	</c:otherwise>
		           		</c:choose>
		                 <img src="${fn:replace(product.pic,'-10-10','-56-67')}" width="56" height="67">
		                 <ins>
		                     <i>${product.brandNameEN}<br />${product.name}</i>
		                     <c:forEach var="pro" items="${product.attribute}">
		                     	<em>${pro.name}：${pro.value}</em>
		                     </c:forEach>
		                     <br />
		                     <em>价格：&yen;${product.price}</em>
		                     <em>数量：${product.quantity}</em>
		                 </ins>
		                 </a>
		             </p>
                </c:forEach>
                <p class="select coupons"><a href="javascript:showCouponPage('order_coupons','order_coupons_header');">优惠券/折扣码 <i id="use_conpus">您有 <em>${fn:length(buyNow.coupon)}</em> 张优惠券可用</i></a></p>
                <input type="hidden" id="totalCoupon" value="${fn:length(buyNow.coupon)}"/>
                <c:if test="${buyNow.postArea == '1' && buyNow.giftCardBalance != '0'}">
                	<legend>礼品卡支付</legend>
	                <p class="giftCard"><a href="#">余额 &yen;<i id="giftCard">${buyNow.giftCardBalance}</i></a></p>
	              	<legend class="tips"></legend>
                </c:if>
              <legend>支付方式</legend>
              <p>您还需要支付剩余 &yen;<i id="surplus">${fn:substring(real_pay,0,fn:indexOf(real_pay,'.'))}</i></p>
              <input type="hidden" id="zhifu_codincart" value="${buyNow.codFlag}"/>
              <c:choose>
                	<c:when test="${cookie['ch'].value eq '102'}">
                		<p class="payment-method">
			               	    <span class="pufaPay">
			                    	<a href="javascript:;" class="cur">浦发银行</a>
			                    </span>
			                </p>
                	</c:when>
                	<c:otherwise>
	               <c:choose>
			        	<c:when test="${checkWX }">
				      	 	<p class="payment-method">
			                	<span class="weixinPay" id="zhifu">
			                    	<a href="javascript:;" class="cur">
			                            <em>微信支付</em>
			                        </a>
			                    </span>
	                		</p>
			        	</c:when>
			        	<c:otherwise> 
				        	 <p class="payment-method">
		                	<span id="zhifu" class="alipay">
		                    	<a href="javascript:;" class="cur">
		                            <em>支付宝支付</em><br />
		                          	  储蓄卡支付需开通网银
		                        </a>
		                    </span>
			                </p>
			                 	<p class="payment-method">
				                	<span class="weixinPay" id="weixinWap">
				                    	<a href="javascript:;">
				                            <em>微信支付</em>
				                        </a>
				                    </span>
		                		</p>
			                <p class="select other-payment"><a href="javascript:;">其他支付方式</a> </p>
			                <div class="other-payment-box">
			                	<p class="payment-method">
				                	<span id="zhifu" class="unionPay">
				                    	<a href="javascript:;">
				                            <em>银联支付</em><br />
				                           	 储蓄卡支付需开通网银
				                        </a>
				                    </span>
				                </p>
	               			
				                <c:choose>
				                	<c:when test="${buyNow.codFlag == '1' && haveAddress && address.cod == '1' }">
				                		<p class="hd_zhifu payment-method">
						               	  <span id="zhifu" class="cashPay">
						                    	<a href="javascript:;">
						                         	<em>货到付款现金支付</em>
						                        </a>
						                    </span>
						                </p>
						                <p class="hd_zhifu payment-method">
						               	  <span id="zhifu" class="postPay">
						                    	<a href="javascript:;">
						                           	<em> 货到付款POS机刷卡</em>
						                        </a>
						                    </span>
						                </p>
				                	</c:when>
				                	
				                	<c:otherwise>
				                		<p style="display: none" class="hd_zhifu payment-method">
						               	  <span id="zhifu" class="cashPay">
						                    	<a href="javascript:;">
						                         	<em>货到付款现金支付</em>
						                        </a>
						                    </span>
						                </p>
						                <p style="display: none" class="hd_zhifu payment-method">
						               	  <span id="zhifu" class="postPay">
						                    	<a href="javascript:;">
						                           	 <em>货到付款POS机刷卡</em>
						                        </a>
						                    </span>
						                </p>
				                	</c:otherwise>
				                </c:choose>
			                </div>
			        	</c:otherwise>
			        </c:choose>
                </c:otherwise>
              </c:choose>
             
                
              <input type="hidden" id="ori_carriage" name="ori_carriage" value="${ori_carriage.amount}"/>
           	   <input type="hidden" id="ori_nocarriage" name="ori_nocarriage" value="${ori_nocarriage.amount}"/>
           	   <input type="hidden" id="promoPrice" name="promoPrice" value="${fn:substring(buyNow.promoAmount,0,fn:indexOf(buyNow.promoAmount,'.'))}"/>
               <input type="hidden" id="orig_pay" name="orig_pay" value="${fn:substring(real_pay,0,fn:indexOf(real_pay,'.'))}"/>
               <input type="hidden" id="dis_count_code" value="0"/>
               <input type="hidden" id="detail_coupon_id" value=""/>
               <input type="hidden" id="carriageAmount" value="${fn:substring(buyNow.carriageAmount,0,fn:indexOf(buyNow.carriageAmount,'.'))}"/>
              <p class="total">
              	   <c:forEach var="priceShow" items="${buyNow.priceShow}">
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
              	   		<c:when test="${priceShow.type == '4' && priceShow.amount != '0'}">
              	   			<span id="span_pay_card">礼品卡：<i id="pay_card">&nbsp;&nbsp;-&yen;&nbsp;${priceShow.amount}<b style="font-weight: normal;"></b></i><br /></span>
              	   		</c:when>
              	   		<c:when test="${priceShow.type == '5' }">
              	   			<span id="span_carriage">${priceShow.title}：<i id="carriage">&nbsp;&nbsp;&nbsp;&yen;&nbsp;${priceShow.amount}</i><br /></span>
              	   		</c:when>
              	   		<c:when test="${priceShow.type == '6' && priceShow.amount != '0'}">
              	   			<span id="span_nocarriage">${priceShow.title}：<i id="nocarriage">&nbsp;&nbsp;-&yen;&nbsp;${priceShow.amount}</i></span>
              	   		</c:when>
              	   		<c:when test="${priceShow.type == '7' }">
              	   			<em>应付金额：<i id="real_pay">&nbsp;&nbsp;&nbsp;&yen;&nbsp;<b>${priceShow.amount}</b></i></em>
              	   		</c:when>
              	   	</c:choose>
              	   </c:forEach>
                </p>
                
                <div class="payment_submit">
                	<input type="hidden" id="invoiceflag" name="invoiceflag" value="0"/>
					<input type="hidden" id="invoicetype" name="invoicetype" value="0"/>
					<input type="hidden" id="invoicetitle" name="invoicetitle" value="${buyNow.lastInvoiceTitle}"/>
					<input type="hidden" id="invoicecontent" name="invoicecontent" value="商品明细"/>
					<input type="hidden" id="couponflag" name="couponflag" value=""/>
					<input type="hidden" id="coupon" name="coupon" value="0"/>
					<input type="hidden" name="express" id="delivery" value="${buyNow.lastDeliveryType}"/>
					<input type="hidden" id="orderfrom" name="orderfrom" value="19"/>
					<input type="hidden" id="buysIds" name="buysIds" value=""/>
					<c:choose>
			        	<c:when test="${checkWX }">
		                	
		                	<c:choose>
						      	<c:when test="${cookie['ch'].value eq '102'}">
						      		<input type="hidden" name="paytypeid" id="mainPay" value="15"/>
		                			<input type="hidden" name="paytypechildid" id="subPay" value="115"/>
						        </c:when>
						      	<c:otherwise>
							      	<input type="hidden" name="paytypeid" id="mainPay" value="27"/>
			                		<input type="hidden" name="paytypechildid" id="subPay" value="58"/>
						      	</c:otherwise>
				     		 </c:choose>
	                	</c:when>
	                	<c:otherwise>
	                		<input type="hidden" name="paytypeid" id="mainPay" value="20"/>
	                	<input type="hidden" name="paytypechildid" id="subPay" value="37"/>
	                	</c:otherwise>
	                </c:choose>
                	<input type="hidden" id="ordertype" name="ordertype" value="1"/>
					<input type="hidden" id="isUseGiftCardPay" name="isUseGiftCardPay" value="0"/>
					<input type="hidden" id="skuId" name="skuId" value="${buyNow.list[0].sku }"/>
					<input type="hidden" id="orderSource" name="orderSource" value="2"/>
					<input type="hidden" id="postArea" name="postArea" value="${buyNow.postArea}"/>
                    <div class="btn-icon clr">
                    	<a href="javascript:;" class="total_amount">总金额：<em>￥<b>${fn:substring(real_pay,0,fn:indexOf(real_pay,'.'))}</b></em></a>
                    	<a id="submit_order" href="javascript:submit();" class="payment_btn">提交订单（${buyNow.list[0].quantity }件）</a>
                    </div>
                </div>
            </fieldset>
        </form>
        <div id="select_area_overlay"></div>
		<div id="select_area_layer">
			<a href="javascript:;" class="close_btn">关闭</a>
			<h3>配送方式</h3>
		    <dl id="area_province">
		            <c:choose>
                	<c:when test="${buyNow.lastDeliveryType == '1'}">
                	 <dd><a href="#" class="cur" id="1">工作日收货</a></dd>
                	<dd><a href="#" id="2">工作日、节假日收货</a></dd>
		        	<dd><a href="#" id="3">双休日、节假日收货</a></dd>
                	</c:when>
                	<c:when test="${buyNow.lastDeliveryType == '2'}">
                		<dd><a href="#"  id="1">工作日收货</a></dd>
		                <dd><a href="#" class="cur" id="2">工作日、节假日收货</a></dd>
				        <dd><a href="#" id="3">双休日、节假日收货</a></dd>
                	</c:when>
                	<c:when test="${buyNow.lastDeliveryType == '3'}">
                		<dd><a href="#"  id="1">工作日收货</a></dd>
		                <dd><a href="#" class="cur" id="2">工作日、节假日收货</a></dd>
				        <dd><a href="#" class="cur" id="3">双休日、节假日收货</a></dd>
                	</c:when>
                	<c:otherwise>
                	 <dd><a href="#" class="cur" id="1">工作日收货</a></dd>
                	<dd><a href="#" id="2">工作日、节假日收货</a></dd>
		        	<dd><a href="#" id="3">双休日、节假日收货</a></dd>
                	</c:otherwise>
                </c:choose>
		        
		        
		        
		    </dl>
		</div>
		
		<input type="hidden" id="orderNum" name="orderNum" value=""/>
		<div id="select-area-overlay" style="height: 667px; display: none;"></div>
		<div class="select-layer" id="select_street_layer" style="display: none;">
			<a href="javascript:;" class="close_btn">关闭</a>
			<h3 style="text-align:center">请选择街道</h3>
		    <dl class="select-opt" id="orgin_area_street" title="街道">
		    </dl>
		</div>
</div>
</div>
