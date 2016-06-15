<%@ page language="java" contentType="text/html; charset=UTF-8" trimDirectiveWhitespaces="true"
    pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/pages/common/include.inc.jsp"%>

<rapid:override name="title">
	${account.nickName}的红包
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
	<div class="funds_block">
        <div class="red-bg">
        	<h3><em>${account.balance}</em>元</h3>
            <ul class="btn">
                <li class="avatar"><a><img src="${account.headImgUrl}" /></a></li>
            </ul>
        </div>
        <div class="raise-tip">已经有${fn:length(records)}人为他募集了资金，继续任性吧！<i class="arrow-down"></i></div>
        <ul class="friends-box" style="display: block">
        	<c:forEach var="record" items="${records}">
        		<li>
	                <span>${record.sendNickName}</span> 
	                <p>${record.viewTime}</p>
	                <em>+<strong>${record.packetMoney}</strong></em>      
	            </li>
        	</c:forEach>
        </ul>
    </div>
</rapid:override>

<%@ include file="/WEB-INF/pages/common/weixin_packet_base.jsp" %> 