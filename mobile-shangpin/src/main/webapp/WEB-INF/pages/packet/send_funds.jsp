<%@ page language="java" contentType="text/html; charset=UTF-8" trimDirectiveWhitespaces="true"
    pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/pages/common/include.inc.jsp"%>

<rapid:override name="title">
	送礼送出好心情
</rapid:override>
<rapid:override name="custum">
	<link href="${cdn:css(pageContext.request) }/styles/shangpin/css/page/wechat-red/wechatRed.css${ver}" rel="stylesheet" />
	<script type="text/javascript" charset="utf-8">
		loader = SP.core
		.install("${cdn:js(pageContext.request)}/styles/shangpin/js/jquery1.7.min.js${ver}")
		.using("${cdn:js(pageContext.request)}/styles/shangpin/js/comm.js${ver}")
		.using("${cdn:js(pageContext.request)}/styles/shangpin/js/weixin/new_coupons.js${ver}")
		.excute();
	</script>
</rapid:override>
<rapid:override name="content">
	<div class="funds_block send">
        <div class="red-bg">
        	<h3>您已送出<em class="send-money">${account.sendPacketMoney}</em>元</h3>
            <ul class="btn">
            	<li><a href="${ctx}/packet/receive?openId=${account.openId}">查看收到<br>的资金</a></li>
            </ul>
        </div>
        <div class="raise-tip">您已为<strong class="send-num" style="font-weight:normal">${fn:length(records)}</strong>位好友送出了资金！土豪就是我!</div>
        <ul class="friends-box" style="display: block;">
        	<c:forEach var="record" items="${records}">
        		<li>
	                <span>${record.receiveNickName}</span> 
	                <p>${record.viewTime}</p>
	                <em>+<strong>${record.packetMoney}</strong></em>      
	            </li>
        	</c:forEach>
        </ul>
    </div>
</rapid:override>

<%@ include file="/WEB-INF/pages/common/weixin_packet_base.jsp" %> 