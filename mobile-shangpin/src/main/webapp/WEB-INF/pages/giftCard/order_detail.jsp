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
            <legend>收货信息</legend>
            <input type="hidden" id="type" name="type" value="${type}"/>
            <c:if test="${type==2}">
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
                	<p id="pats" class="select"><a href="javascript:showAddress('order_address_list','order_address_list_header');">请选择配送地址 </a> </p>
                </c:if>
                
                <c:choose>
                	<c:when test="${buyNow.lastDeliveryType == '1'}">
                		<p class="select"><a href="#" id="select_time">配送方式<span class="delivery_mode">工作日收货</span></a></p>
                	</c:when>
                	<c:when test="${buyNow.lastDeliveryType == '2'}">
                		<p class="select"><a href="#" id="select_time">配送方式<span class="delivery_mode">所有日期均可收货</span></a></p>
                	</c:when>
                	<c:when test="${buyNow.lastDeliveryType == '3'}">
                		<p class="select"><a href="#" id="select_time">配送方式<span class="delivery_mode">双休日、节假日收货</span></a></p>
                	</c:when>
                	<c:otherwise>
                		<p class="select"><a href="#" id="select_time">配送方式<span class="delivery_mode">工作日收货</span></a></p>
                	</c:otherwise>
                </c:choose>
              
              
                </c:if>
                <c:if test="${type==1}">
                	<p id="patsCard" class="select selected" ><a>您选购的是电子礼品卡，订单支付成功后，您可在支付成功的页面或“我的尚品-礼品卡”查看购买记录。</a></p>
                </c:if>
                <legend>发票信息</legend>
                <p class="select-invoice">
                	<a href="#">是否开发票</a>
                	<span class="select-option" id="yes">是</span>
                    <span class="select-option cur" id="no">否</span>
                </p>
                <p class="select invoice" style="display: none;">
					<a href="javascript:showPageInvoice('order_invoice','order_invoice_header');">
						发票类型：<em id="invoice_type">电子发票</em><br />
						发票抬头：<em id="invoice_title">个人</em><br />
						发票内容：<em id="invoice_content">商品全称</em>
					</a>
					<input type="hidden" id="invoice_addr" name="invoiceaddrid" value=""/>
                	<%--<c:if test="${buyNow.lastInvoiceFlag == '1'}">
                		<a href="javascript:showPageInvoice('order_invoice','order_invoice_header');">
                    	发票类型：<em id="invoice_type">电子发票</em><br />
				                        发票抬头：<em id="invoice_title">${buyNow.lastInvoiceTitle}</em><br />
				                        发票内容：<em id="invoice_content">${buyNow.lastInvoiceContent}</em>
                 		</a>
                 		 <c:choose>
				       		<c:when test="${type==1}">
				       			<input type="hidden" id="invoice_addr" name="invoiceaddrid" value=""/>
				       		</c:when>
				       		<c:otherwise>
				       			<input type="hidden" id="invoice_addr" name="invoiceaddrid" value="${buyNow.lastInvoiceID}"/>
				       		</c:otherwise>
			       		</c:choose>
                 		
                	</c:if>
                    <c:if test="${buyNow.lastInvoiceFlag != '1'}">
                		<a href="javascript:showPageInvoice('order_invoice','order_invoice_header');">
                    					发票类型：<em id="invoice_type">电子发票</em><br />
				                        发票抬头：<em id="invoice_title">个人</em><br />
				                        发票内容：<em id="invoice_content">商品全称</em>
                 		</a>
                 		<input type="hidden" id="invoice_addr" name="invoiceaddrid" value=""/>
                	</c:if>--%>
                </p>
                <legend>商品信息</legend>
                <c:forEach var="product" items="${buyNow.list}">
                	<p class="pord_info" id="${product.buyId}">
		           		<c:choose>
			            	<c:when test="${checkAPP}">
			              		<a href="#" class="clr">
			              	</c:when>
			             	 <c:otherwise>
			              		<a href="#" class="clr">
			              	</c:otherwise>
		           		</c:choose>
		                 <img src="${fn:replace(product.pic,'-10-10','-56-67')}" width="56" height="67">
		                 <ins>
		                     <i><br/>${product.name}</i>
		                     <c:forEach var="pro" items="${product.attribute}">
		                     	<em>${pro.name}：${pro.value}</em>
		                     </c:forEach>
		                     <em>价格：&yen;${product.price}</em>
		                     <em>数量：${product.quantity}</em>
		                 </ins>
		                 </a>
		             </p>
                </c:forEach>
              <legend>支付方式</legend>
                <p>您还需要支付剩余 &yen;<i id="surplus">${fn:substring(real_pay,0,fn:indexOf(real_pay,'.'))}</i></p>
               <c:choose>
		        	<c:when test="${checkWX }">
		        	
		        	<c:choose>
				      	<c:when test="${cookie['ch'].value eq '102'}">
					      	<p class="payment-method">
			               	    <span class="pufaPay">
			                    	<a href="javascript:;" class="cur">浦发银行</a>
			                    </span>
			                </p>
				        </c:when>
				      	<c:otherwise>
				      	 	<p class="payment-method">
			                	<span class="weixinPay" id="zhifu">
			                    	<a href="javascript:;" class="cur">
			                            <em>微信支付</em>
			                        </a>
			                    </span>
	                		</p>
				      	</c:otherwise>
				      </c:choose>
		        	
		        	
		        
		        	</c:when>
		        	<c:otherwise> 
			        	<p class="payment-method">
		                	<span class="weixinPay" id="weixinWap">
		                    	<a href="javascript:;" class="cur">
		                            <em>微信支付</em>
		                        </a>
		                    </span>
                		</p>
			        	 <p class="payment-method">
		                	<span id="zhifu" class="alipay">
		                    	<a href="javascript:;">
		                            <em>支付宝支付</em><br />
		                          	  储蓄卡支付需开通网银
		                        </a>
		                    </span>
		                </p>
		                <p class="payment-method">
		                	<span id="zhifu" class="unionPay">
		                    	<a href="javascript:;">
		                            <em>银联支付</em><br />
		                           	 储蓄卡支付需开通网银
		                        </a>
		                    </span>
		                </p>
		        	</c:otherwise>
		        </c:choose>
                
             <input type="hidden" id="ori_carriage" name="ori_carriage" value="${buyNow.carriage.amount == '0' ? buyNow.carriage.reductionAmount : buyNow.carriage.amount}"/>
             <input type="hidden" id="ori_nocarriage" name="ori_nocarriage" value="${buyNow.carriage.reductionAmount}"/>
                
              <p class="total">
              	   <c:forEach var="priceShow" items="${buyNow.priceShow}">
              	   	<c:choose>
              	   		<c:when test="${priceShow.type == '1' }">
              	   			商品金额：<i id="product_total_price">&nbsp;&nbsp;&nbsp;&yen;&nbsp;${priceShow.amount}</i><br />
              	   		</c:when>
              	   		<c:when test="${priceShow.type == '2' && priceShow.amount != '0' }">
              	   			满减：<i id="promoto_price">&nbsp;&nbsp;-&yen;&nbsp;${priceShow.amount}</i><br />
              	   		</c:when>
              	   		<c:when test="${priceShow.type == '3' && priceShow.amount != '0'}">
              	   			 <span id="span_pay_coupon">优惠：<i id="pay_coupon">&nbsp;&nbsp;-&yen;&nbsp;<b style="font-weight: normal;"></b></i><input type="hidden" id="detail_coupon_id" value=""/><br /></span>
              	   		</c:when>
              	   		<c:when test="${priceShow.type == '4' && priceShow.amount != '0'}">
              	   			<span id="span_pay_card">礼品卡：<i id="pay_card">&nbsp;&nbsp;-&yen;&nbsp;<b style="font-weight: normal;"></b></i><br /></span>
              	   		</c:when>
              	   		<c:when test="${priceShow.type == '5' }">
              	   			<span id="span_carriage">运费：<i id="carriage">&nbsp;&nbsp;&nbsp;&yen;&nbsp;${priceShow.amount}</i><br /></span>
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
                	<%--<input type="hidden" id="invoiceflag" name="invoiceflag" value="${buyNow.lastInvoiceFlag}"/>
					<input type="hidden" id="invoicetype" name="invoicetype" value="0"/>
					<input type="hidden" id="invoicetitle" name="invoicetitle" value="${buyNow.lastInvoiceTitle}"/>--%>

					<input type="hidden" id="invoiceflag" name="invoiceflag" value=""/>
					<input type="hidden" id="invoicetype" name="invoicetype" value="1"/>
					<input type="hidden" id="invoicetitle" name="invoicetitle" value="个人"/>
					<input type="hidden" id="invoicecontent" name="invoicecontent" value="商品全称"/>
					<input type="hidden" name="invoiceEmail" value="">
					<input type="hidden" name="invoiceTel" value="">
					<input type="hidden" name="invoiceStyle" value="1">

					<input type="hidden" id="couponflag" name="couponflag" value=""/>
					<input type="hidden" id="coupon" name="coupon" value="0"/>
					<input type="hidden" name="express" id="delivery" value="${empty buyNow.lastDeliveryType ? "1":buyNow.lastDeliveryType}"/>
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
	                		<input type="hidden" name="paytypeid" id="mainPay" value="27"/>
	                	<input type="hidden" name="paytypechildid" id="subPay" value="117"/>
	                	</c:otherwise>
	                </c:choose>
                	<input type="hidden" id="ordertype" name="ordertype" value="1"/>
					<input type="hidden" id="isUseGiftCardPay" name="isUseGiftCardPay" value="0"/>
					<input type="hidden" id="skuId" name="skuId" value="${buyNow.list[0].sku }"/>
					<input type="hidden" id="orderSource" name="orderSource" value="2"/>
					<input type="hidden" id="postArea" name="postArea" value="1"/>
					<div class="btn-icon clr">
                    	<a href="javascript:;" class="total_amount">总金额：<em>￥<b>${fn:substring(real_pay,0,fn:indexOf(real_pay,'.'))}</b></em></a>
                    	<a id="submit_order" href="javascript:submit();" class="payment_btn">提交订单（${buyNow.list[0].quantity }件）</a>
                    </div>
<!--                     <input type="submit" class="payment_btn" value="提交订单" /> -->
                </div>
            </fieldset>
        </form>
        <div id="select_area_overlay"></div>
		<div id="select_area_layer">
			<h3>配送方式</h3>
			<c:set var="lasyEmpay" value="${empty buyNow.lastDeliveryType}"></c:set>
		    <dl id="area_province">
		        <dd><a href="#"  ${lasyEmpay or buyNow.lastDeliveryType == '1' ? "class=\"cur\"":""} id="1">工作日收货</a></dd>
			    <dd><a href="#"  ${buyNow.lastDeliveryType == '2' ? "class=\"cur\"":""}id="2">所有日期均可收货</a></dd>
		        <dd><a href="#"  ${buyNow.lastDeliveryType == '3' ? "class=\"cur\"":""} id="3">双休日、节假日收货</a></dd>
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
