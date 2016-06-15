 <%@ page language="java" contentType="text/html; charset=UTF-8"
	trimDirectiveWhitespaces="true" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/pages/common/include.inc.jsp"%>
<rapid:override name="custum">

	<link href="${cdn:css(pageContext.request)}/styles/shangpin/css/page/giftcard/giftCard_send.css${ver}" rel="stylesheet" />
	<script src="${cdn:js(pageContext.request)}/styles/shangpin/js/jquery.min.js${ver}"  type="text/javascript" charset="utf-8"></script>
	<script src="${cdn:js(pageContext.request)}/styles/shangpin/js/lazyload.js${ver}"  type="text/javascript" charset="utf-8"></script>
	<script src="${cdn:js(pageContext.request)}/styles/shangpin/js/config.sim.js${ver}"  type="text/javascript" charset="utf-8"></script>
	<script src="${cdn:js(pageContext.request)}/styles/shangpin/js/giftcard/recharge.js${ver}"  type="text/javascript" charset="utf-8"></script>
	<script src="${cdn:js(pageContext.request)}/styles/shangpin/js/head_scroll.js${ver}"  type="text/javascript" charset="utf-8"></script>
</rapid:override>
<%-- 页标题 --%>
<rapid:override name="page_title">
	实物卡
</rapid:override>
<rapid:override name="content">
     <!--赠送礼品卡start-->
    <div class="alContent clr">
      
      <p class="eCard_show">
        <img src="${cdn:pic(pageContext.request)}/styles/shangpin/images/giftcard/physical_card_img01.png" alt="实物卡">
        <span class="span_val">礼品卡面值：¥${value }</span>
        <a href="${ctx}/giftCard/quickEntityRecharge" >立即充值</a> 
      </p>
      <input type="hidden" name="msg" id="msg" value="${msg }" />
      <dl class="eCard_introduction">
        <dt>尚品网礼品卡介绍：</dt>
        <dd>1、尚品网礼品卡是尚品网的定向储值卡，支持在尚品网www.shangpin.com全网站使用</dd>
        <dd>2、本卡需要充值于尚品网会员账户中方可使用，充值方式有2种：</dd>
        <dd>第一种：通过尚品网网站、app或wap站，使用礼品卡卡号和密码并按照提示充值。</dd>
        <dd>第二种：点击“立即充值”，并按照提示步骤完成充值。</dd>
        <dd>3、您登录的账户即为您充值的账户。</dd>
        <dd>4、如有问题，请拨打客户电话：4006-900-900</dd>
      </dl>
    </div>
    <!--赠送礼品卡end-->
</rapid:override>
<rapid:override name="down_page">
	
</rapid:override>
<%@ include file="/WEB-INF/pages/common/another_common_mall_banner.jsp" %> 

