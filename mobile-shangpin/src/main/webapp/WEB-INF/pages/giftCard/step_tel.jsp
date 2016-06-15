 <%@ page language="java" contentType="text/html; charset=UTF-8"
	trimDirectiveWhitespaces="true" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/pages/common/include.inc.jsp"%>

<rapid:override name="custum">

	<link href="${cdn:css(pageContext.request)}/styles/shangpin/css/page/giftcard/giftcardDetail.css${ver}" rel="stylesheet" />
	<script src="${cdn:js(pageContext.request)}/styles/shangpin/js/jquery.min.js${ver}"  type="text/javascript" charset="utf-8"></script>
	<script src="${cdn:js(pageContext.request)}/styles/shangpin/js/lazyload.js${ver}"  type="text/javascript" charset="utf-8"></script>
	<script src="${cdn:js(pageContext.request)}/styles/shangpin/js/config.sim.js${ver}"  type="text/javascript" charset="utf-8"></script>
	<script src="${cdn:js(pageContext.request)}/styles/shangpin/js/giftcard/recharge.js${ver}"  type="text/javascript" charset="utf-8"></script>
</rapid:override>
<%-- 页标题 --%>
<rapid:override name="page_title">
	礼品卡
</rapid:override>
<rapid:override name="content">
    <div class="step-box">
    	<div class="step-tip">
        	<div class="line-bg"></div>
            <ul class="clr">
              <li class="one curr"><span>1</span><em>绑定手机</em></li>
              <li class="two"><span>2</span><em>支付密码</em></li>
              <li class="three"><span>3</span><em>充值</em></li>
            </ul>
        </div>
        <form id="bingTel" action="bindTel" method="post">
        <p class="warn-text"><i><img src="${cdn:js(pageContext.request)}/styles/shangpin/images/giftcard/detail/warn_icon.png" /></i>先绑定手机，礼品卡金额改变将发送短信给您。</p>
        <input class="tip-input" name="phone" type="text" id="phone" value="${phone }" placeholder="请填写手机号" required  maxlength="11"/>
        <input  type="hidden"  id="oneKeyToken" name="oneKeyToken" value="${oneKeyToken}"/>
        <div class="yzm-box">
            <div class="login_list">                  
                <input name="verifyCode" type="text" id="verify_code" value="" placeholder="输入验证码" maxlength="6">                             
            </div>
            <em id="get_verify_code">获取验证码</em>
        </div>
        </form>
        <input type="hidden" name="msg" id="msg" value="${msg }" />
        <div class="step-btn"><a href="javascript:;" id="step_tel_btn">确定</a></div>
    </div>

	 
</rapid:override>

<%@ include file="/WEB-INF/pages/common/another_common_mall_banner.jsp" %>

