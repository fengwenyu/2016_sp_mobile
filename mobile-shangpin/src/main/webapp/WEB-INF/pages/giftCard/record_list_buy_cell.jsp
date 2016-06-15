<%@ page language="java" contentType="text/html; charset=UTF-8"
	trimDirectiveWhitespaces="true" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/pages/common/include.inc.jsp"%>
  	
<c:choose>
	<c:when test="${giftCardRecord!=null && fn:length(giftCardRecord.list)>0 }">
	<input type="hidden" id="isHaveMore${recordType }${pageNo }" value="${isHaveMore }"/>
	<c:forEach var="record" items="${giftCardRecord.list }">
	<ul class="purchase-records-list">
    	<li class="clr">
        	<p>订单号：<strong>${record.childOrderId }</strong></p>
            <span>${record.fmtDate }</span>
        </li>
        <li class="clr">
        	<p>${record.typeDesc }</p>
            <span>￥${record.faceValue } | 礼品卡</span>
        </li>
        <li class="clr">
        	<p>状态：<strong class="red">${record.statusDesc }</strong></p>
        </li>
        <c:if test="${record.status==1 }">
    			<div class="btn-icon clr">
	                
	                <a href="${ctx }/giftCard/toRecharge?giftCardId=${record.giftCardId}&pic=${record.pic}" class="recharge-btn" style="color: #c62026;">充值于本账户</a>
			        <a href="${ctx }/giftCard/skipToGiftCardSend?giftOrder=${record.childOrderId}&giftCardId=${record.giftCardId}&faceValue=${record.faceValue}" class="share-btn" style="background-color: #c62026;color: #fff;border: 1px solid #c62026;">赠送给小伙伴</a>
                 </div>
        </c:if>
         </ul>
        	<div class="line"></div>
        </c:forEach>
     </c:when>
</c:choose>

