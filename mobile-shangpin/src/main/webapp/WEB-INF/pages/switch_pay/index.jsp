<%@ page language="java" contentType="text/html; charset=UTF-8" trimDirectiveWhitespaces="true"
    pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/pages/common/include.inc.jsp"%>
<rapid:override name="custum">
<link href="${cdn:css(pageContext.request)}/styles/shangpin/css/page/account_new.css${ver}" rel="stylesheet" />
<%-- 	<link href="${cdn:css(pageContext.request)}/styles/shangpin/css/page/product/detail.css${ver}" rel="stylesheet" /> --%>
	<script src="${cdn:css(pageContext.request)}/styles/shangpin/js/core.js" type="text/javascript" charset="utf-8"></script>
	<script type="text/javascript" charset="utf-8">
    loader = SP.core
      	.install("${cdn:css(pageContext.request)}/styles/shangpin/js/jquery.min.js${ver}")  //jquery库文件
      	.using("${cdn:css(pageContext.request)}/styles/shangpin/js/j.dialogs.js${ver}")
		.excute()
		.using("${cdn:css(pageContext.request)}/styles/shangpin/js/FashionRun/bigGlass.js${ver}")
		.using("${cdn:css(pageContext.request)}/styles/shangpin/js/FashionRun/form.js${ver}")
      	.using("${cdn:css(pageContext.request)}/styles/shangpin/js/FashionRun/face.js${ver}")

</script>

<style>
.border_bottom {
border-bottom: 1px solid #e5e5e5;
}
.nobottom {
background: none;
border-bottom: 0;
padding-bottom: 0px;
}
.content_size table{
	width:100%;
	margin-bottom:40px;
	border-top:1px solid #ccc;
}
.content_size table thead td{
	background:#f2f2f2;
}
.content_size table td{
	width:40%;
	text-align:center;
	color:#555;
	line-height:30px;
	border-bottom:1px solid #ccc;
}
.content_size table td.left{
	text-align:left;
	padding-left:15px;
}
.coupons_active  { width: 100%; padding: 10px 0 20px; text-align: center}
.coupons_active fieldset legend { color: #2d2d2d; font-size: 16px; height: 31px; line-height: 31px; display: block; width: 100%; box-sizing: border-box; }
.coupons_active fieldset p { background-color: #fff; height: 31px; line-height: 31px; color: #999; position: relative; }
.coupons_active fieldset p input[type=text] { border: none; background: rgba(0,0,0,0); height: 29px; z-index: 0; width: 62%; font-size: 14px; padding: 0 5px; border-bottom: 1px solid #d8d8d8; }
.coupons_active fieldset p span { display: inline-block; width: 78%; color: #555; }
.coupons_submit { /* position: absolute; */ right: 0; display: inline-block; width: 30%; height: 29px; line-height: 29px; text-align: center; font-size: 14px; background-color: #aa0006; color: #fff; border: 1px solid #aaa; margin-left: 2%; }
.coupons_active fieldset p a.coupons_submited,.coupons_active fieldset p a.coupons_submit:hover, .coupons_active fieldset p a.coupons_submit:active { background-color: #aa0006; color:#fff; border: 1px solid #fff; }
.coupon_list { }

</style>
</rapid:override>
<%-- 浏览器标题 --%>
<rapid:override name="title">
	切换支付
</rapid:override>

<%-- 页标题 --%>
 <rapid:override name="page_title">
	切换支付
</rapid:override> 
 <rapid:override name="appLayer">
</rapid:override> 
<rapid:override name="downloadAppShowHead">
		
</rapid:override>
<rapid:override name="content">

 
	
	
	
<div class="content_size content_list nobottom border_bottom" >
     <table>
        <thead><tr><td class="center">当前支付方式</td></tr></thead>
        <tbody>
    
        <tr><td class="center">
        <c:choose>
        <c:when test="${key== 'ALIFZAPP'}">支付宝分账</c:when>
        <c:when test="${key== 'ALIAPPSEA'}">支付宝海外</c:when>
        </c:choose>
        </td></tr>
  
         </tbody>
  	 </table>
  	  
  </div>
  
  <div class="coupon_block">
	<div class="tabs_Cell">
	<form class="coupons_active" action="${ctx }/switch/pay/update">
	<p style="padding: 10px 0 20px">	<lable>请选择切换的方式：&nbsp;&nbsp;&nbsp;</lable>
	
		<input name="payType" type="radio" value="0" />支付宝分账 &nbsp;&nbsp;&nbsp;
		<input name="payType" type="radio" value="1" />支付宝海外 
		</p>
		
		<input type="submit" class="coupons_submit" value="确定">
	<!-- 	<fieldset>
			<p>
				<a href="javascript:findByPhone();" class="coupons_submit">确认</a>
			</p>
		</fieldset> -->
	</form>
	
</div>
</div>
</rapid:override>
<rapid:override name="footer">
  
 </rapid:override>
<%@ include file="/WEB-INF/pages/common/common_no_content_banner.jsp" %> 