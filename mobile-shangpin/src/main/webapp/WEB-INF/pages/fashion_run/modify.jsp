<%@ page language="java" contentType="text/html; charset=UTF-8" trimDirectiveWhitespaces="true"
    pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/pages/common/include.inc.jsp"%>
<rapid:override name="custum">
	<link href="${cdn:css(pageContext.request)}/styles/shangpin/css/base.css${ver}" rel="stylesheet" />
	<link  href="${cdn:css(pageContext.request)}/styles/shangpin/css/font-css/font-awesome.min.css" rel="stylesheet">
	<link href="${cdn:css(pageContext.request)}/styles/shangpin/css/page/FashionRun/form.css" rel="stylesheet" />
	<script src="${cdn:css(pageContext.request)}/styles/shangpin/js/core.js" type="text/javascript" charset="utf-8"></script>
	<script type="text/javascript" charset="utf-8">
    loader = SP.core
      	.install("${cdn:css(pageContext.request)}/styles/shangpin/js/jquery.min.js${ver}")  //jquery库文件
      	.using("${cdn:css(pageContext.request)}/styles/shangpin/js/j.dialogs.js${ver}")
		.excute()
		.using("${cdn:css(pageContext.request)}/styles/shangpin/js/FashionRun/bigGlass.js${ver}")
		.using("${cdn:css(pageContext.request)}/styles/shangpin/js/FashionRun/form.js${ver}")
      
      
</script>
</rapid:override>
<%-- 浏览器标题 --%>
<rapid:override name="title">
	填写报名表
</rapid:override>

<%-- 页标题 --%>
 <rapid:override name="page_title">
	填写报名表
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

	<!--填写表单 start-->
    <div class="form-box">
    <form id="fashion_form"  method="post">
        <div class="row-box">
            <label for="userName">姓&#12288;&#12288;名：</label>
            <input type="text" id="J_userName" name="name" placeholder="输入您的名字" value="${fashionOrder.name }" required />
        </div>
        
        <div class="row-box">
            <label for="mobileNum">手机号码：</label>
            <input type="text" pattern="[0-9]*" id="J_mobileNum" name="phone" placeholder="请输入11位手机号" value="${fashionOrder.phone }" required maxlength="11" />
        </div>
        
        <div class="row-box">
            <label for="identificationNum">身份证号：</label>
            <input type="text" id="J_identificationNum" name="pid" placeholder="请输入18位身份证号" value="${fashionOrder.pid }" required maxlength="18" />
        </div>
        <input type="hidden" id="orderId" name="orderId" value="${fashionOrder.orderId }"/>
         <input type="hidden" id="id" name="id" value="${fashionOrder.id }"/>
        </form>
        <p class="tip-text">温馨提示：成功报名后将自动享受尚品网会员权益,海量时尚潮牌、运动装备随时抢购</p>
        <div class="btn-box">
            <a href="javascript:;" class="submit-btn js-modify-up">确认</a>
        </div>
        <p class="agreement"><em class="cur"></em><a href="${ctx }/fashion/run/agree">同意免责声明</a></p>
        <!-- <a class="phone" href="tel:4006-900-900"><i><img src="../../styles/shangpin/images/FashionRun/ico_phone.png" /></i>4006-900-900</p> -->
    </div>
    <!--填写表单 end-->


</div>
</rapid:override>
<rapid:override name="footer">
  
 </rapid:override>
<%@ include file="/WEB-INF/pages/common/common_no_content_banner.jsp" %> 