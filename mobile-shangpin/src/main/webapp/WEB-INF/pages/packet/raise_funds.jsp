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
		.using("${cdn:js(pageContext.request)}/styles/weixin/jweixin-1.0.0.js${ver}")
		.using("${cdn:js(pageContext.request)}/styles/shangpin/js/weixin/weixin_ready.js${ver}")
		.excute();
	</script>
</rapid:override>
<rapid:override name="content">
	<input id="_iswx" name="_iswx" type="hidden" value="${checkWX}"/>
	<input type="hidden" name="_shareUrl"  id="_shareUrl"  value="${basePath}/packet/share/raise?openId=${account.openId}"/>
	<input type="hidden" name="_mainTitle"  id="_mainTitle"  value="${shareTitle}"/>
	<input type="hidden" name="_mainDesc"  id="_mainDesc"  value="${shareDesc}"/>
	<input type="hidden" name="_mainImgUrl" id="_mainImgUrl"  value="${account.headImgUrl}"/>
	<input type="hidden" name="_currentUrl" id="_currentUrl"  value="${basePath}/packet/receive?openId=${account.openId}"/>
	<div class="funds_block">
        <div class="red-bg">
        	<h3>红包金额<em>${account.balance}</em>元</h3>
            <ul class="btn">
            	<li><a href="${ctx}/packet/send?openId=${account.openId}">查看送出<br>的资金</a></li>
                <li class="avatar"><a><img src="${account.headImgUrl}" /></a></li>
                <li><a href="${ctx}/packet/exchange?openId=${account.openId}">兑换<br>现金券</a></li>
            </ul>
        </div>
        <div class="raise-tip">已经有${fn:length(records)}人为您募集了资金，继续任性吧！<i class="arrow-down"></i></div>
        <ul class="friends-box">
        	<c:forEach var="record" items="${records}">
        		<li>
	                <span>${record.sendNickName}</span> 
	                <p>${record.viewTime}</p>
	                <em>+<strong>${record.packetMoney}</strong></em>      
	            </li>
        	</c:forEach>
        </ul>
        <div class="share-box">
        	<img src="${cdn:pic(pageContext.request)}/styles/shangpin/images/wechatRed/explain_img.jpg" />
            <a href="${ctx}/packet/share?openId=${account.openId}" class="share-btn">分享我的二维码</a>
        </div>
    </div>
</rapid:override>

<%@ include file="/WEB-INF/pages/common/weixin_packet_base.jsp" %> 