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
.coupons_active fieldset { width: 100%; padding: 10px 0 20px; }
.coupons_active fieldset legend { color: #2d2d2d; font-size: 16px; height: 31px; line-height: 31px; display: block; width: 100%; box-sizing: border-box; }
.coupons_active fieldset p { background-color: #fff; height: 31px; line-height: 31px; color: #999; position: relative; }
.coupons_active fieldset p input[type=text] { border: none; background: rgba(0,0,0,0); height: 29px; z-index: 0; width: 62%; font-size: 14px; padding: 0 5px; border-bottom: 1px solid #d8d8d8; }
.coupons_active fieldset p span { display: inline-block; width: 78%; color: #555; }
.coupons_active fieldset p a.coupons_submit { /* position: absolute; */ right: 0; display: inline-block; width: 30%; height: 29px; line-height: 29px; text-align: center; font-size: 14px; background-color: #aa0006; color: #fff; border: 1px solid #aaa; margin-left: 2%; }
.coupons_active fieldset p a.coupons_submited,.coupons_active fieldset p a.coupons_submit:hover, .coupons_active fieldset p a.coupons_submit:active { background-color: #aa0006; color:#fff; border: 1px solid #fff; }
.coupon_list { }

</style>
</rapid:override>
<%-- 浏览器标题 --%>
<rapid:override name="title">
	线下领取装备
</rapid:override>

<%-- 页标题 --%>
 <rapid:override name="page_title">
	线下领取装备
</rapid:override> 
 <rapid:override name="appLayer">
</rapid:override> 
<rapid:override name="downloadAppShowHead">
		
</rapid:override>
<rapid:override name="content">
<c:choose>
	<c:when test="${!checkWX&&!checkAPP}">
				<div class="alContent form-content">
	</c:when>
	<c:otherwise>
		<div class="alContent form-content" style="margin-top: 0;">
	</c:otherwise>
</c:choose>
 <div class="coupon_block">
	<div class="tabs_Cell">
	<form class="coupons_active">
		<fieldset>
			<p>
				<input type="text" id="_phone" name="_phone" placeholder="输入报名填写的手机号" required />
				<a href="javascript:findByPhone();" class="coupons_submit">确认</a>
			</p>
		</fieldset>
	</form>
	<form class="coupons_active">
		<fieldset>
			<p>
				<input type="text" id="_pid" name="_pid" placeholder="输入报名填写的身份证号" required />
				<a href="javascript:findByPid();" class="coupons_submit">确认</a>
			</p>
		</fieldset>
	</form>
</div>
</br>
	
 <form id="sign_form"class="coupons_active" style="display:none">
		<fieldset>
			<p>
				<a href="${ctx }/fashionrun/sign" class="coupons_submit" style="margin-left: 35%;">去报名</a>
			</p>
			
		</fieldset>
</form>
</div>
	 <div class="content_size content_list nobottom border_bottom" style="display:none">
  <!--   <dl>
      <dd class="order_all_list">
       	<span  style="text-align:center"> 姓名</span>：<br />
		<span style=" text-align:center"> 手机号</span>：<br />
       	<span  style=" text-align:center"> 身份证号</span>：<br />
       	<span  style=" text-align:center"> 领取状态</span>：<br />
       	<span  style=" text-align:center"> 尺码</span>：<br />
        <span style="text-align:center"> 省市区</span>：<br />
        <span  style=" text-align:center"> 地址</span>：<br />
         -->
        
        <table>
        <thead><tr><td class="left">项目</td><td>名称</td></tr></thead>
        <tbody>
        <tr><td class="left">姓名</td><td id="name"></td></tr>
        <tr><td class="left">手机号</td><td id="phone"></td></tr>
        <tr><td class="left">身份证号</td> <td id="pid"></td></tr>
        <tr><td class="left">领取状态</td><td id="status" style="color:red"></td></tr>
        <tr><td class="left">尺码</td> <td id="size"></td></tr>
        <tr><td class="left">省市区</td> <td id="place"></td></tr>
        <tr><td class="left">地址</td> <td id="addr"></td></tr>
         </tbody>
   </table>
  </div>
	<div class="coupon_block" id="fashionBtn" style="display:none">
	<div class="tabs_Cell">
	<form class="coupons_active">
		<fieldset>
			<p>
				<a href="javascript:sendPhoneCode();" class="coupons_submit" style="margin-left: 35%;">发送验证码</a><span style="margin-left: 40%;color: #140404;font-weight: bold;font-size:20px" id="phoneCode"></span>
			</p>
				<p style="margin-top:30px; position:left;">
				<a href="javascript:confirm();" class="coupons_submit" style="margin-left: 35%;">确认领取</a>
			</p>
		</fieldset>
	</form>
	 <input type="hidden" id="orderId" name="orderId" value=""/>
</div>
</div>
<div class="content_size content_list nobottom border_bottom" >
  <!--   <dl>
      <dd class="order_all_list">
       	<span  style="text-align:center"> 姓名</span>：<br />
		<span style=" text-align:center"> 手机号</span>：<br />
       	<span  style=" text-align:center"> 身份证号</span>：<br />
       	<span  style=" text-align:center"> 领取状态</span>：<br />
       	<span  style=" text-align:center"> 尺码</span>：<br />
        <span style="text-align:center"> 省市区</span>：<br />
        <span  style=" text-align:center"> 地址</span>：<br />
         -->
        
     <table>
        <thead><tr><td class="left">状态</td><td>人数</td></tr></thead>
        <tbody>
        <tr><td class="left">已支付未领取</td><td>${paidCount }</td></tr>
        <tr><td class="left">已领取未发货</td><td>${applyCount }</td></tr>
        <tr><td class="left">已发货</td> <td>${deliverCount }</td></tr>
        <tr><td class="left">总报名人数</td><td >${totalCount }</td></tr>
  
         </tbody>
  	 </table>
  	    <table>
        <thead><tr><td class="left">尺码</td><td>售卖数量</td></tr></thead>
        <tbody>
        <c:forEach var="sell" items="${sellList }">
	        <tr><td class="left">${sell[0] }</td><td>${sell[1] }</td></tr>
  		</c:forEach>
         </tbody>
  	 </table>
   <form class="coupons_active">
		<fieldset>
			<p>
				<a href="javascript:load();" class="coupons_submit" style="margin-left: 35%;">刷新页面</a>
			</p>
			
		</fieldset>
	</form>
  </div>
</rapid:override>
<rapid:override name="footer">
  
 </rapid:override>
<%@ include file="/WEB-INF/pages/common/common_no_content_banner.jsp" %> 