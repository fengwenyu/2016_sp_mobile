<%@ page language="java" contentType="text/html; charset=UTF-8" trimDirectiveWhitespaces="true"
    pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/pages/common/include.inc.jsp"%>

<rapid:override name="custum">
	<link href="${cdn:css(pageContext.request) }/styles/shangpin/css/page/giftcard/giftcard_pay.css${ver}" rel="stylesheet" />
	<script type="text/javascript" charset="utf-8">
		loader = SP.core
		.install("${cdn:js(pageContext.request)}/styles/shangpin/js/jquery.min.js${ver}")
		.using("${cdn:js(pageContext.request)}/styles/shangpin/js/j.dialogs.js${ver}")
		.excute()
		.using("${cdn:js(pageContext.request)}/styles/shangpin/js/giftcard/giftcard_pay.js${ver}")
		.excute();
	</script>
</rapid:override>
<rapid:override name="title">
	提交订单
</rapid:override>
<rapid:override name="header">
	<%@ include file="/WEB-INF/pages/common/submit_order_header.jsp"%>
</rapid:override>
<rapid:override name="content">
	<div class="paymet_block js-order-page">
        <form name="login" id="J_Login">
        	<input type="hidden" id="mainOrderId" name="mainOrderId" value="${orderResult.mainOrderId}"/>
            <fieldset>
            	<legend class="price"><em>应付总额：<i>&nbsp;&nbsp;&yen;${orderResult.payAmount}</i></em><span id="payDesc">微信支付</span></legend>
                <legend>收货信息</legend>
                <p class="selected"><a>您选购的是电子礼品卡，订单支付成功后，您可在支付成功的页面或“我的商品－礼品卡”查看购买记录。</a></p>
                <c:choose>
                	<c:when test="${orderResult.invoice != null && orderResult.invoice.name != null}">
                		<legend>发票信息</legend>
		                <p class="select-invoice">
		                	<a href="#">是否开发票</a>
                			<span class="select-option cur" id="yes">是</span>
                   			<span class="select-option" id="no">否</span>
		                    <!--<label class="iosCheck"><input type="checkbox" checked="checked"><i></i></label>-->
		                </p>
		                <input type="hidden" id="invoiceFlag" name="invoiceFlag" value="1"/>
                		<input type="hidden" id="invoiceAddrId" name="invoiceAddrId" value="${orderResult.invoice.id}"/>
	                	<input type="hidden" id="isModifyInvoice" name="isModifyInvoice" value="0"/>
	                	<input type="hidden" id="isInvoiceAddrId" name="isInvoiceAddrId" value="-1"/>
	                	<p class="select invoice js-invoice" style="display: block;">
		                   <a href="javascript:;">
					                      发票类型：<em id="invoiceType">普通发票</em><br />
					                      发票抬头：<em id="invoiceTitle">${orderResult.invoice.title}</em><br />
					                      发票内容：<em id="invoiceContent">${orderResult.invoice.context}</em><br/>
					                      邮件地址：<em id="invoiceName">${orderResult.invoice.name}</em>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<em id="invoiceTel">${orderResult.invoice.tel}</em><br />
	                        <span id="invoiceAddress" style="margin-left: 70px;display: block;color: #000;">${orderResult.invoice.provName}${orderResult.invoice.cityName}${orderResult.invoice.areaName}${orderResult.invoice.townName}${orderResult.invoice.addr}</span>
		                  </a>
		                </p>
                	</c:when>
               		<c:otherwise>
               			<input type="hidden" id="invoiceFlag" name="invoiceFlag" value="0"/>
               		</c:otherwise>
                </c:choose>
                <legend>商品信息</legend>
                <div class="pord_info_new">
                	<c:forEach var="orderList" items="${orderResult.orderList}">
                		<input type="hidden" id="orderId" name="orderId" value="${orderList.orderId}">
                		<c:forEach var="orderDetail" items="${orderList.detail}">
                			<a href="#" class="clr">
			                    <img src="${orderDetail.pic}" width="56" height="67">
			                    <div>
			                        <i><br />${orderDetail.name}</i>
			                        <em>商品金额：￥${orderDetail.price}</em>
			                        <em>数量：${orderDetail.quantity}</em>
			                    </div>
		                    </a>
                		</c:forEach>
                	</c:forEach>
                </div>

                <legend>支付方式</legend>
                <p>您的应付金额为 ¥${orderResult.payAmount}</p>
                <p class="payment-method">
                	<span class="weixinPay">
                    	<a href="javascript:;" class="cur" >
                            <em>微信支付</em>
                        </a>
                    </span>
                </p>
                <c:if test="${!checkWX}">
                	<p class="payment-method">
	                	<span class="alipay">
	                    	<a href="javascript:;" >
	                            <em>支付宝支付</em>
	                        </a>
	                    </span>
	                </p>
	               
	                <p class="payment-method">
	                	<span class="unionPay">
	                    	<a href="javascript:;">
	                            <em>银联支付</em>
	                        </a>
	                    </span>
	                </p>
                </c:if>
              	<p class="total">
              		<c:forEach var="priceShow" items="${orderResult.priceShow}">
              	   	<c:choose>
              	   		<c:when test="${priceShow.type == '1' }">
              	   			${priceShow.title}：<i id="product_total_price">&nbsp;&nbsp;&nbsp;&yen;&nbsp;${priceShow.amount}</i><br />
              	   		</c:when>
              	   		<c:when test="${priceShow.type == '2' && priceShow.amount != '0' }">
              	   			${priceShow.title}：<i id="promoto_price">&nbsp;&nbsp;-&yen;&nbsp;${priceShow.amount}</i><br />
              	   		</c:when>
              	   		<c:when test="${priceShow.type == '3' && priceShow.amount != '0'}">
              	   			 <span id="span_pay_coupon">${priceShow.title}：<i id="pay_coupon">&nbsp;&nbsp;-&yen;&nbsp;<b style="font-weight: normal;"></b></i><input type="hidden" id="detail_coupon_id" value=""/><br /></span>
              	   		</c:when>
              	   		<c:when test="${priceShow.type == '4' && priceShow.amount != '0'}">
              	   			<span id="span_pay_card">${priceShow.title}：<i id="pay_card">&nbsp;&nbsp;-&yen;&nbsp;<b style="font-weight: normal;"></b></i><br /></span>
              	   		</c:when>
              	   		<c:when test="${priceShow.type == '5' && priceShow.amount != '0'}">
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
                
                <div class="payment_submit payment_submit_bg">
                    <div class="btn-icon clr">
                    	<a href="javascript:;" class="total_amount">总金额：<em>￥${orderResult.payAmount}</em></a>
                        <a href="javascript:;" class="payment-btn">提交订单</a>
                    </div>
                </div>
            </fieldset>
            <c:choose>
            	<c:when test="${checkWX}">
            		<input type="hidden" id="mainPay" name="mainPay" value="27"/>
            		<input type="hidden" id="subPay" name="subPay" value="58"/>
            	</c:when>
            	<c:otherwise>
	            	<input type="hidden" id="mainPay" name="mainPay" value="27"/>
	            	<input type="hidden" id="subPay" name="subPay" value="117"/>
	            </c:otherwise>
            </c:choose>
        </form>
    </div>
    <!--发票详情 start-->
    <div class="paymet_block js-invoice-detail">   
        <form name="login" id="J_Login">
            <fieldset class="notBtm">
                <legend>发票详情</legend>
                <p>
                    <label for="invoiceName">发票抬头：</label>
                    <input type="text" id="J_invoiceName" name="invoiceName" placeholder="个人、公司/名称" required />
                </p>
                <p>
                    <label>发票内容：</label>
                    <span class="select-menu">
                    <select id="J_invoiceContent">
                        <option value="">请选择发票内容</option>
                        <option value="商品明细">商品明细</option>
                        <option value="箱包">箱包</option>
                        <option value="饰品">饰品</option>
                        <option value="化妆品">化妆品</option>
                        <option value="钟表">钟表</option>
                        <option value="服饰">服饰</option>
                        <option value="鞋帽">鞋帽</option>
                        <option value="眼镜">眼镜</option>
                    </select>
                    </span>
                </p>
                <legend>发票邮寄地址</legend>
                <!--<p>
                    <a href="javascript:;" class="select-address cur">
                        <em>与收货地址相同</em>
                    </a>
                </p>-->
                <p class="select other-address js-select-address">
                    <a href="#">选择地址：
                        <em></em>
                    </a>
                </p>
                
               
                <div class="payment_submit">
                    <a href="javascript:;" class="payment_btn">保存</a>
                    <input type="submit" class="payment_btn" value="保存" />
                </div>
            </fieldset>
        </form>
        <p class="coupons_tips">温馨提示:手表类商品只能开具为品牌和型号，其它内容无法开具。如您的商品由品牌方发货，发票则由尚品网单独发出。如需开增值类发票，请联系尚品客服<br>客服电话：4006-900-900</p>
    </div>
    <!--发票地址 start-->
    <div class="paymet_block js-invoice-address-list">
    	<c:forEach var="invoice" items="${orderResult.invoiceList}">
    		<p class="addr_block" id="${invoice.id}">
                <span class="js-addr-info" id="${invoice.id}">
                    <i>${invoice.name} &nbsp;&nbsp;${invoice.tel}</i>
                    <b style="font-weight:normal;">${invoice.provName}${invoice.cityName}${invoice.areaName}${invoice.townName}${invoice.addr}</b>                              
                </span>
                <span class="addr_edit">
                    <a href="javascript:;" class="editBtn js-edit-btn">编辑</a> &nbsp;&nbsp; 
                    <a href="javascript:;" class="deletBtn">删除</a>
                </span>
            </p>
    	</c:forEach>
     </div>
    <!--新增地址 start-->
    <div class="paymet_block js-add-invoice-address">
        <form name="invoice_add_login" id="invoice_addd_login">
        	<input type="hidden" id="addressId" name="id" value=""/>
        	<input type="hidden" id="province" name="province" value=""/>
	    	<input type="hidden" id="provincename" name="provname" value=""/>
	    	<input type="hidden" id="city" name="city" value=""/>
	    	<input type="hidden" id="cityname" name="cityname" value=""/>
	    	<input type="hidden" id="area" name="area" value=""/>
	    	<input type="hidden" id="areaname" name="areaname" value=""/>
	    	<input type="hidden" id="town" name="town" value=""/>
	    	<input type="hidden" id="townname" name="townname" value=""/>
            <fieldset class="notBtm">
                <legend>收货地址</legend>
                <p class="select"><a href="#" id="select_area">省市区</a></p>
                <p>
                    <label for="addr">详细地址：</label>
                    <input type="text" id="J_addr" name="addr" placeholder="请输入详细街道地址" required />
                </p>
                <p>
                    <label for="code">邮编信息：</label>
                    <input type="text" id="J_code" name="postcode" placeholder="请输入您所在地区的邮编" required />
                </p>

                <legend>收货人信息</legend>
                <p>
                    <label for="userName">姓名：</label>
                    <input type="text" id="J_userName" name="name" placeholder="请填写个中文名称" required />
                </p>
                <p>
                    <label for="mobileNum">联系电话：</label>
                    <input type="text" id="J_mobileNum" name="tel" placeholder="请输入11位手机号码" required maxlength="11" />
                </p>
				<!-- 
                <p>
                    <label style="width: 75px;" for="identificationNum">身份证号码：</label>
                    <input type="text" id="J_identificationNum" name="identificationNum" placeholder="请输入身份证号码" required maxlength="18" />
                </p>

                <ul class="id-tip">
                    <li>身份证号码仅在您购买海外商品时使用，如您购买国内商品可不填</li>
                </ul>
                 -->
                <div class="payment_submit">
                    <a href="javascript:;" class="payment_btn">保存</a>
                    <input type="submit" class="payment_btn" value="保存" />
                </div>
            </fieldset>
            <div class="explain">
                <h3>关于身份证填写的声明</h3>
                <p>1. 身份证号码仅在您购买海外商品时使用，根据海关的要求，购买海外商品时需要您填写收货人的身份证号码进行个人物品入境申报。</p>
                <p>2. 因海关会对您填写的身份证是否会收货人的身份证信息进行验证，请确保填写正确，否则商品可能无法正常通关。</p>
                <p>3. 尚品网承诺，您填写的身份证信息仅用于清关，绝不做他用，请放心填写。</p>
            </div>
        </form>
    </div>
</rapid:override>
<rapid:override name="overlay">
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

<div class="select-layer" id="area_layer">
    <a href="javascript:;" class="prev_btn">返回</a>
    <a href="javascript:;" class="close_btn">关闭</a>
    <h3>省份</h3>
    <dl class="select-opt" id="area_province" title="省份">
        <c:forEach var="province" items="${provinces}">
        	<dd><a href="#" id="${province.id}">${province.name}</a></dd>
        </c:forEach>
    </dl>
    <dl class="select-opt" id="area_city" title="城市">
        
    </dl>
    <dl class="select-opt" id="area_county" title="地区">
       
    </dl>
    <dl class="select-opt" id="area_street" title="街道">
       
    </dl>
</div>





<div id="overlay1"></div>
<div id="popup_box1" >
    <a href="#" class="title_closeBtn">×</a>
    <h1 id="popup_title1" style="display: block;">填写收货人身份证号码</h1>
    <div id="popup_content1">
        <div id="popup_message1">
        	<p>根据海关的要求，海外商品需要您填写收货人的身份证号码进行个人物品入境申报。</p>
            <div class="identification-box">
                <label for="identificationNum1">身份证号码：</label>
                <input type="text" id="J_identificationNum1" name="identificationNum1" placeholder="请输入身份证号码" required maxlength="18">
            </div>
        </div>
        <div id="popup_panel1">
          <button type="button" id="popup_cancel1">&nbsp;取消&nbsp;</button> 
          <button type="button" id="popup_ok1">&nbsp;保存&nbsp;</button>
        </div>
    </div>
</div>
</rapid:override>


<rapid:override name="footer">
	
</rapid:override>
<%@ include file="/WEB-INF/pages/common/submit_order_common.jsp" %> 
