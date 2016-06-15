<%@ page language="java" contentType="text/html; charset=UTF-8" trimDirectiveWhitespaces="true"
    pageEncoding="UTF-8"%>
   	<c:set var="ua" value="${header['User-Agent']}" />
	<c:set var="micromessenger" value="micromessenger" />
	<c:set var="aolaiIOSApp" value="AolaiIOSApp" />
	<c:set var="shangpinAndroidApp" value="ShangpinAndroidApp" />
	<c:set var="aolaiAndroidApp" value="AolaiAndroidApp" />
	<c:set var="shangpinIOSApp" value="ShangpinIOSApp" />
	<c:choose>
		<c:when test="${!fn:containsIgnoreCase(ua, micromessenger)&&!fn:containsIgnoreCase(ua, shangpinIOSApp)&&!fn:containsIgnoreCase(ua, aolaiAndroidApp)&&!fn:containsIgnoreCase(ua, aolaiIOSApp)&&!fn:containsIgnoreCase(ua, shangpinAndroidApp)}">    
			<!--头部 start-->
			<div class="topFix" id="order_invoice_header" style="display: none">
			   <section>
			       <div class="topBack" >
			       <a href="javascript:showPage('order_detail','order_header');" class="backBtn">&nbsp;</a>
			      填写 发票信息
			       <ul class="alUser_icon clr">
			           <li><a href="<c:url value='/index'/>"><img src="${cdn:pic(pageContext.request)}/styles/shangpin/images/home_icon.png" width="25" height="25" alt="账户"></a></li>
			       </ul>
			       </div>
			   </section>
			 </div>
			  <!--头部 end-->
			  
			<div class="alContent order_invoice" style="display: none">
		</c:when>
		<c:otherwise>
			<div class="alContent order_invoice" style="display: none;margin-top: 0;">
		</c:otherwise>
	</c:choose>
	<div class="paymet_block">
        <form name="login" id="J_Login" class="inovice_form">
        	<input type="hidden" id="invoice_province" name="province" value=""/>
	    	<input type="hidden" id="invoice_provincename" name="provname" value=""/>
	    	<input type="hidden" id="invoice_city" name="city" value=""/>
	    	<input type="hidden" id="invoice_cityname" name="cityname" value=""/>
	    	<input type="hidden" id="invoice_area" name="area" value=""/>
	    	<input type="hidden" id="invoice_areaname" name="areaname" value=""/>
	    	<input type="hidden" id="invoice_town" name="town" value=""/>
	    	<input type="hidden" id="invoice_townname" name="townname" value=""/>
            <fieldset class="notBtm">
                <legend>发票详情</legend>
                <p>
                    <label for="invoiceName">发票类型：</label>
                    <span class="select-menu">
                    <select id="J_invoiceType" onchange="invoiceType();">
                    	<c:choose>
                    		<c:when test="${elements.lastInvoiceFlag == '1' && elements.lastInvoiceType == '1'}">
                    			<option value="">请选择发票类型</option>
		                    	<option value="0" selected="selected">个人</option>
		                        <option value="1">公司</option>
                    		</c:when>
                    		<c:when test="${elements.lastInvoiceFlag == '1' && elements.lastInvoiceType == '2'}">
                    			<option value="">请选择发票类型</option>
		                    	<option value="0">个人</option>
		                        <option value="1" selected="selected">公司</option>
                    		</c:when>
                    		<c:otherwise>
                    			<option value="" selected="selected">请选择发票类型</option>
		                    	<option value="0">个人</option>
		                        <option value="1">公司</option>
                    		</c:otherwise>
                    	</c:choose>
                    </select>
                    </span>
                </p>
                <p>
                    <label for="invoiceName">发票抬头：</label>
                    <c:choose>
                    	<c:when test="${elements.lastInvoiceFlag == '1' }">
                    		<input type="text" id="J_invoiceName" name="invoiceName" value="${elements.lastInvoiceTitle}"/>
                    	</c:when>
                    	<c:otherwise>
                    		<input type="text" id="J_invoiceName" name="invoiceName" placeholder="个人、公司/名称"  />
                    	</c:otherwise>
                    </c:choose>
                </p>
                <p>
                    <label>发票内容：</label>
                    <span class="select-menu">
                    <select id="J_invoiceContent" onchange="invoiceContent();">
                        <option value="" selected="selected">请选择发票内容</option>
                    	<option value="发票AAA">商品明细</option>
                        <option value="发票BBB">箱包</option>
                        <option value="发票CCC">饰品</option>
                        <option value="发票CCC">化妆品</option>
                        <option value="发票CCC">钟表</option>
                        <option value="发票CCC">服饰</option>
                        <option value="发票CCC">鞋帽</option>
                        <option value="发票CCC">眼镜</option>
                    </select>
                    </span>
                </p>
                <legend>发票邮寄地址</legend>
                <p>
                    <a id="invoice_address_same" href="javascript:;" class="select-address cur">
                        <em>与收货地址相同</em>
                    </a>
                </p>
<!--                 class="select other-address" -->
                <p style="height: 99px;" class="addr_block">
                	<a id="other_address" href="#" class="select-address">
                        <em>其他地址</em>
                    </a>
                </p>
                <div id="invoice_list" class="paymet_block">
                	<c:forEach var="invoiceAddr" items="${elements.invoiceaddrs}">
	                	<p style="height: 99px;" id="${invoiceAddr.id}" class="addr_block">
	                		<span>
	                			<i>${invoiceAddr.name} &nbsp;&nbsp;   ${invoiceAddr.tel}</i>
	                			<em>${invoiceAddr.provName}${invoiceAddr.cityName}${invoiceAddr.areaName}${invoiceAddr.townName}${invoiceAddr.addr}</em>
	                		</span>
	                	</p>
	                </c:forEach>
	                <c:if test="${fn:length(elements.invoiceaddrs) < 10}">
	                	<div id="order_invoice_add" class="payment_submit">
		                    <a href="javascript:showAdd();" class="payment_btn">新增发票地址</a>
		                </div>
	                </c:if>
                </div>
                <div id="invoice_add" style="display: none">
                	<p id="order_invoice_name">
                    <label for="invoiceAddr">姓名：</label>
                    <input type="text" id="invoice_name" name="name" placeholder="请填写姓名" required />
	                </p>
	                <p id="order_invoice_tel">
	                    <label for="invoiceAddr">联系电话：</label>
	                    <input type="text" id="invoice_tel" name="tel" placeholder="请输入11位手机号码" required />
	                </p>
	                <p class="select other-address"><a href="#" id="select_area">省市区</a></p>
	                <p id="order_J_addr">
	                    <label for="invoiceAddr">详细地址：</label>
	                    <input type="text" id="invoice_addr_order" name="addr" placeholder="请详细街道地址" required maxlength="50" />
	                </p>
	                <p id="order_postcode">
	                    <label for="invoiceAddr">邮编：</label>
	                    <input type="text" id="invoice_code" name="postcode" placeholder="请输入你所在地区的邮编" required maxlength="6" />
	                </p>
	                <div class="payment_submit">
	                    <a href="javascript:addInvoiceAddress('order_detail','order_header');" class="payment_btn">保存</a>
	                </div>
                </div>
                
                <div class="payment_submit" id="save_invoice_info">
                    <a href="javascript:saveInvoiceInfo('order_detail','order_header');" class="payment_btn">保存</a>
                </div>
                
            </fieldset>
        </form>
        <p class="coupons_tips">温馨提示:手表类商品只能开具为品牌和型号，其它内容无法开具。如您的商品由品牌方发货，发票则由尚品网单独发出。如需开增值类发票，请联系尚品客服4006-900-900</p>
    </div>
    
    
    <div id="invoice_area_overlay"></div>
		<div id="invoice_area_layer">
			<a href="javascript:;" class="invoice_prev_btn">返回</a>
        	<a href="javascript:;" class="invoice_close_btn">关闭</a>
			<h3>省份</h3>
		    <dl id="invoice_area_province" title="省份">
		    	<c:forEach var="provice" items="${provinces}">
		  		<dd><a href="#" id="${provice.id}">${provice.name}</a></dd>
		  	</c:forEach>
		    </dl>
		    <dl id="invoice_area_city" title="城市">
		       
		    </dl>
		    <dl id="invoice_area_county" title="地区">
		        
		    </dl>
		    <dl id="invoice_area_street" title="街道">
		       
		    </dl>
		</div>
</div>