<%@ page language="java" contentType="text/html; charset=UTF-8" trimDirectiveWhitespaces="true"
    pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/pages/common/include.inc.jsp"%>

<rapid:override name="title">
	微信红包活动规则
</rapid:override>
<rapid:override name="custum">
	<link href="${cdn:css(pageContext.request) }/styles/shangpin/css/page/wechat-red/wechatRed.css${ver}" rel="stylesheet" />
	<script type="text/javascript" charset="utf-8">
		loader = SP.core
		.install("${cdn:js(pageContext.request)}/styles/shangpin/js/jquery.min.js${ver}")
		.using("${cdn:js(pageContext.request)}/styles/shangpin/js/comm.js${ver}")
		.excute();
	</script>
</rapid:override>
<rapid:override name="content">
	<div class="explain">
        <h3>【活动说明】</h3>
        <p>值此“尚品5.20”来临之际，尚品网携手微信，举办“千万红包大派送”活动；任性红包飞啊飞，动动手指，尚品现金券即刻到手！这个5月，不仅要玩的开心，更要让红包飞起来！快行动起来吧！</p>
        <h3>【红包说明】</h3>
        <p>活动时间：</p>
        <p>1、此次红包共分为10元、30元、50元现金红包；</p>
        <p>2、每个好友扫描您的二维码并关注尚品公众账号后，您可以得到您好友赠送的红包（首次关注尚品公众账号的用户还可得到尚品网赠送的红包一个）</p>
        <p>3、使用红包的订单若发生退货，在有效期内红包将返还至用户账户，可再次使用；在有效期外红包将自动失效，不做延期；</p>
        <p>4、请在优惠券试用期内使用优惠券；</p>
        <p>5、兑换红包时，请注册或绑定尚品网账号；</p>
        <p>6、此优惠券仅限在尚品/尚品奥莱移动端使用；</p>
        <p>7、本次活动，尚品网拥有最终解释券；</p>
        <h3>【如何玩转】</h3>
        <p>将您的红包页面或二维码分享到朋友圈、微信群、微信好友，让您的好友长按图片、点击“识别图中二维码”，并关注，您就可以收到好友赠送的红包啦，同时您的朋友也会收到您，尚品网送出的红包！</p>
        <h3>【红包用途】</h3>
        <p>亲，您可将您收到的红包，用于兑换等额优惠券（面额请见兑换页面点击进入）优惠券可以在尚品网/尚品奥莱移动端直接购买商品时直接使用（下载尚品APP   下载尚品奥莱APP）</p>
    </div>
</rapid:override>

<%@ include file="/WEB-INF/pages/common/weixin_packet_base.jsp" %> 