<%@ page language="java" contentType="text/html; charset=UTF-8" trimDirectiveWhitespaces="true"
    pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/pages/common/include.inc.jsp"%>

<rapid:override name="custum">
	<link href="${cdn:css(pageContext.request)}/styles/shangpin/css/page/account.css" rel="stylesheet" />
	<script type="text/javascript" charset="utf-8">
			loader = SP.core
				.install("${cdn:js(pageContext.request)}/styles/shangpin/js/zepto.min.js${ver}")
				.using("${cdn:js(pageContext.request)}/styles/shangpin/js/ajaxSettings.js${ver}")
				.using("${cdn:js(pageContext.request)}/styles/shangpin/js/lazyload.js${ver}")
				.using("${cdn:js(pageContext.request)}/styles/shangpin/js/comm.js${ver}")
				.using("${cdn:js(pageContext.request)}/styles/shangpin/js/dialogs.js${ver}")
				.using("${cdn:js(pageContext.request)}/styles/shangpin/js/address/list.js${ver}")
				;
	</script>
			
</rapid:override > 

<rapid:override name="content">
<c:import url="/nav?navId=10"></c:import>
<div class="alContent">
  <div class="address_listbox">
    <ul class="address_list">
    <c:forEach var="address" items="${addressList}" varStatus="status">
    <li <c:if test="${address.isd eq \"1\" }"> class="curr"</c:if>>
    	<div class="address_infohd">地址<span class="sort">${status.index+1 }</span>
    	<span class="default_address"><c:if test="${address.isd eq \"1\" }">默认地址</c:if></span>
    	<a href="javascript:void(0);" id="${address.id }" class="address_delbtn" onclick="deleteAddress(this);">[删除]</a>
    	</div>
    	<section class="alOrder_addr">
    	<a href="<c:url value='/user/address/edit?addressId=${address.id}'/>">
  				<dl>
                  <dt>收货人：</dt><dd>${address.name }</dd>
                </dl>
                <dl>
                  <dt>地　址：</dt><dd>${address.provname }${address.cityname }${address.areaname }${address.townname }${address.addr} </dd>
                </dl>
                <dl>
                  <dt>手机号：</dt><dd>${address.tel}</dd>
                </dl>
    	</a>
    	</section>
    </li>
    
    </c:forEach>
    </ul>

    
      <a href="${ctx}/user/address/add" class="alOrder_buyBtn alOrder_submitBtn" <c:if test="${fn:length(addressList) >= 10 }"> style="visibility: hidden;" </c:if>>新增收货地址</a>
    
  </div>
</div>
</rapid:override>

<%@ include file="/WEB-INF/pages/common/base.jsp" %> 
