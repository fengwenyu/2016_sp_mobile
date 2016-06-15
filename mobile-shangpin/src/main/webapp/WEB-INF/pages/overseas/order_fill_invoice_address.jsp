<%@ page language="java" contentType="text/html; charset=UTF-8" trimDirectiveWhitespaces="true"
    pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/pages/common/include.inc.jsp"%>
<div class="container" id="invoice_address_container">
    
    <!--头部 start-->
    <div class="topFix">
        <section>
            <div class="topBack">
                <a href="javascript:;" class="backBtn">&nbsp;</a>
                <span class="top-title">发票地址</span>
                <p>
                    <a href="javascript:;" id="invoice_address_to_add">新增地址</a>
                </p>
            </div>
        </section>
    </div>
    <!--头部 end-->

    <!--收货地址 start-->
    <div class="paymet_block">
         <c:forEach var="address" items="${fillData.invoice }">
	    	<p class="addr_block"  id="addrId${address.id }">
	                <span class="click_addr"  addr_id="${address.id }"  addr_name="${address.name }" addr_tel="${address.tel }" addr="${address.provName }${address.cityName }${address.areaName }${address.townName}${address.addr}">
	                    <i>${address.name } &nbsp;&nbsp;${address.tel }</i>
	                    ${address.provName }${address.cityName }${address.areaName }${address.townName}${address.addr}
	                </span>
	                <span class="addr_edit">
	                    <a href="javascript:;" class="editBtn" onclick="backAddressInvoiceEdit('${address.id }' ,'invoice_address_edit_');">编辑</a> &nbsp;&nbsp; 
	                    <a href="javascript:;" class="deletBtn" onclick="delInvoiceAddr('${address.id }')">删除</a>
	                </span>
	            </p>
	    </c:forEach>
        </div>
    <!--收货地址 end-->
  </div>
