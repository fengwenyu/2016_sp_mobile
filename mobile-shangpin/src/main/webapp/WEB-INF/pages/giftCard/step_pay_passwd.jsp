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
              <li class="one"><span>1</span><em>绑定手机</em></li>
              <li class="two curr"><span>2</span><em>支付密码</em></li>
              <li class="three"><span>3</span><em>充值</em></li>
            </ul>
        </div>
        <form id="passwdForm" action="bindPasswd" method="post">
        	<p class="warn-text"><i><img src="${cdn:js(pageContext.request)}/styles/shangpin/images/giftcard/detail/warn_icon.png" /></i>请设置礼品卡的支付密码</p>
        	<input class="tip-input" id="passwd" name="passwd" type="password"  value="" placeholder="请输入6-20位字母和数字的组合" required maxlength="20" />
        	<input class="tip-input" id="nextpasswd" name="nextpasswd" type="password"  value="" placeholder="请再次输入" required  maxlength="20" />
        	<input id="oneKeyToken" type="hidden" name="oneKeyToken" value="${oneKeyToken}">
        </form>
        <input type="hidden" name="msg" id="msg" value="${msg }" />
        <div class="step-btn"><a href="javascript:;" id="passwdBtn">确定</a></div>
    </div>
</rapid:override>

<%@ include file="/WEB-INF/pages/common/another_common_mall_banner.jsp" %>

