<%@ page language="java" contentType="text/html; charset=UTF-8" trimDirectiveWhitespaces="true"
    pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/pages/common/include.inc.jsp"%>
<rapid:override name="title">
	现金券
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
	<div class="alContent">
        <div class="coupon_list">
            <li class="cash">
                <h4><img src="${cdn:pic(pageContext.request)}/styles/shangpin/images/wechatRed/cash_coupon_angle.png" width="69" height="121" /></h4>
                <div class="cash">
                    <i>&yen;300</i>
                    <em>2015新年红包300元</em>
                    <span>
                      <strong>2014.05.20 0点~2014.05.27 0点</strong>
                      首次购物返券:在尚品网、尚品奥莱购物，单笔订单满
                      1000元使用，此券不可抵用运费。
                    </span>
                </div>
                <p><img src="${cdn:pic(pageContext.request)}/styles/shangpin/images/wechatRed/cash_coupon.png" width="69" height="121" /></p>
            </li>
            <li class="sale">
                <h4><img src="${cdn:pic(pageContext.request)}/styles/shangpin/images/wechatRed/cash_coupon_angle.png" width="69" height="121" /></h4>
                <div class="cash">
                    <i>&yen;200</i>
                    <em>2015新年红包200元</em>
                    <span>
                      <strong>2014.05.20 0点~2014.05.27 0点</strong>
                      首次购物返券:在尚品网、尚品奥莱购物，单笔订单满
                      1000元使用，此券不可抵用运费。
                    </span>
                </div>
                <p><img src="${cdn:pic(pageContext.request)}/styles/shangpin/images/wechatRed/cash_coupon.png" width="69" height="121" /></p>
            </li>
        </ul>
        </div>
    </div>
</rapid:override>

<%@ include file="/WEB-INF/pages/common/weixin_packet_base.jsp" %> 
