<%@ page language="java" contentType="text/html; charset=UTF-8" trimDirectiveWhitespaces="true"
    pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/pages/common/include.inc.jsp"%>

<rapid:override name="custum">
	<link href="${cdn:css(pageContext.request) }/styles/shangpin/css/page/member.css${ver}" rel="stylesheet" />
	<script type="text/javascript" charset="utf-8">
		loader = SP.core
		.install("${cdn:js(pageContext.request)}/styles/shangpin/js/jquery.min.js${ver}")
		.using("${cdn:js(pageContext.request)}/styles/shangpin/js/comm.js${ver}")
		.using("${cdn:css(pageContext.request) }/styles/shangpin/js/j.dialogs.js${ver}")
		.using("${cdn:css(pageContext.request) }/styles/shangpin/js/address.js${ver}")
		.excute();
	</script>
</rapid:override>



<rapid:override name="page_title">
	我的收货地址
</rapid:override>

<rapid:override name="content">
		<div class="paymet_block">
			<c:forEach var="address" items="${addresses}">
				<p class="addr_block">
				<span>
					<i>${address.name} &nbsp;&nbsp;${address.tel}</i>
					${address.provname}${address.cityname}${address.areaname}${address.townname}${address.addr}
				</span>
				<span class="addr_edit">
					<a href="<c:url value='/address/update?addressId=${address.id}'/>" class="editBtn">编辑</a> &nbsp;&nbsp; 
					<a href="javascript:deleteAddr('${address.id}');" class="deletBtn" >删除</a>
				</span>
			</p>
			</c:forEach>
			<div class="payment_submit">
			<a href="javascript:addAddress('${fn:length(addresses)}')" class="payment_btn" >新增地址</a>
			</div>
		</div>
	 	
</rapid:override>
<rapid:override name="down_page">
	
</rapid:override>
<%@ include file="/WEB-INF/pages/common/common.jsp" %> 