<%@ page language="java" contentType="text/html; charset=UTF-8" trimDirectiveWhitespaces="true"
    pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/pages/common/include.inc.jsp"%>
<div class="container" id="address_list_container">
    
    <!--头部 start-->
    <div class="topFix">
        <section>
            <div class="topBack">
                <a href="javascript:;" class="backBtn">&nbsp;</a>
                <span class="top-title">收货地址</span>
            </div>
        </section>
    </div>
    <!--头部 end-->

    <!--收货地址 start-->
    <div class="paymet_block">
	    <c:forEach var="address" items="${fillData.receive }">
	    	<p class="addr_block"  id="addrId${address.id }" area="${address.area}" town="${address.town}">
	                <span class="click_addr"  addr_id="${address.id }"  addr_name="${address.name }" addr_tel="${address.tel }" addr="${address.provName }${address.cityName }${address.areaName }${address.townName}${address.addr}" addr_cardID="<c:if test="${address.cardID!=null && address.cardID!=''}">${fn:substring(address.cardID,0,6)}***${fn:substring(address.cardID,fn:length(address.cardID)-4,fn:length(address.cardID)) }</c:if> ">
	                    <i>${address.name } &nbsp;&nbsp;${address.tel }</i>
						<c:if test="${address.cardID != ''}">
							<i>${fn:substring(address.cardID,0,6)}***${fn:substring(address.cardID,fn:length(address.cardID)-4,fn:length(address.cardID)) }</i>
						</c:if>	                    
	                    ${address.provName }${address.cityName }${address.areaName }${address.townName}${address.addr}
	                </span>
	                <span class="addr_edit">
	                    <a href="javascript:;" class="editBtn" onclick="backAddressEdit('${address.id }' ,'address_edit_');">编辑</a> &nbsp;&nbsp; 
	                    <a href="javascript:;" class="deletBtn" onclick="delAddr('${address.id }')">删除</a>
	                </span>
	            </p>
	    </c:forEach>
	      <div class="payment_submit">
	                    <a href="javascript:;" id="address_to_add" class="payment_btn">新增地址</a>
	       </div>
        </div>
    <!--收货地址 end-->
  </div>
