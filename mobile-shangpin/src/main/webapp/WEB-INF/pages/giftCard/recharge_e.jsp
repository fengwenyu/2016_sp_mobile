 <%@ page language="java" contentType="text/html; charset=UTF-8"
	trimDirectiveWhitespaces="true" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/pages/common/include.inc.jsp"%>

<rapid:override name="custum">

	<link href="${cdn:css(pageContext.request)}/styles/shangpin/css/page/giftcard/giftcardDetail.css${ver}" rel="stylesheet" />
	<script src="${cdn:js(pageContext.request)}/styles/shangpin/js/jquery.min.js${ver}"  type="text/javascript" charset="utf-8"></script>
	<script src="${cdn:js(pageContext.request)}/styles/shangpin/js/lazyload.js${ver}"  type="text/javascript" charset="utf-8"></script>
	<script src="${cdn:js(pageContext.request)}/styles/shangpin/js/config.sim.js${ver}"  type="text/javascript" charset="utf-8"></script>
	<script src="${cdn:js(pageContext.request)}/styles/shangpin/js/giftcard/recharge.js${ver}"  type="text/javascript" charset="utf-8"></script>
	<script src="${cdn:js(pageContext.request)}/styles/shangpin/js/head_scroll.js${ver}"  type="text/javascript" charset="utf-8"></script>
</rapid:override>
<%-- 页标题 --%>
<rapid:override name="page_title">
	电子卡充值
</rapid:override>
<rapid:override name="content">
    <div class="step-box">
    	<div class="step-tip">
        	<div class="line-bg"></div>
            <ul class="clr">
              <li class="one"><span>1</span><em>绑定手机</em></li>
              <li class="two"><span>2</span><em>支付密码</em></li>
              <li class="three curr"><span>3</span><em>充值</em></li>
            </ul>
        </div>
        
        <p class="warn-text"><i><img src="${cdn:js(pageContext.request)}/styles/shangpin/images/giftcard/detail/warn_icon.png" /></i>完成充值后，会有5分钟的延时，请您耐心等待</p>
         <form id="recharge_eFrom" action="electronicRecharge?backFlag=erecharge" method="post">
       	 	<input class="tip-input" id="passwd"  name="passwd" type="text" value="" placeholder="请输入卡密" required />
         </form>
        <input type="hidden" name="msg" id="msg" value="${msg }" />
        <div class="step-btn"><a href="javascript:;" id="rechargeE">确定</a></div>
    </div>

	 
</rapid:override>

<%@ include file="/WEB-INF/pages/common/another_common_mall_banner.jsp" %> 

