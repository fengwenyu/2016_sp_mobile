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
		.using("${cdn:css(pageContext.request)}/styles/shangpin/js/FashionRun/form.js${ver}")
      
      
</script>
</rapid:override>
<%-- 浏览器标题 --%>
<rapid:override name="title">
	同意免责声明
</rapid:override>

<%-- 页标题 --%>
 <rapid:override name="page_title">
	同意免责声明
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
        <p class="agreement-box">本人自愿作出以下声明：<br/>
          1、本人及携带的未成年子女自愿报名参加 fashion run 北京站活动。<br/>
          2、本人承诺报名成功后，不以任何理由退换报名费用。<br/>
          3、本人承诺在活动期间自行照看携带的未成年子女，因在跑步和活动现场出现非组办方原因导致的意外，本人自行承担所造成的一切后果。<br/>
          4、本人同意向组办方提供有效的身份证件和资料用于核实本人及携带的未成年子女的身份，并同意承担因身份证件和资料不实所产生的全部责任。<br/>
          5、本人同意组办方以本人及携带的未成年子女为被保险人投保人身意外险，具体内容已从保险说明书中知晓，本人均予以认可。<br/>
          6、本人及携带的未成年子女已在医院做好体检，身体健康，为参加活动做好充分准备。<br/>
          7、本人承诺本人及携带的未成年子女均以自己的名义报名并参与活动，绝不将报名后获得的号码牌以任何方式转让给他人。<br/>
          8、本人同意在活动前和活动期间不损害、更改及遮盖fashion run 官方号码布。<br/>
          9、本人全面理解并同意遵守组办方所制订的各项规程、规则、规定、要求及采取的措施。<br/>
          10、本人全面理解活动可能出现的风险，且已准备必要的防范措施；本人愿意承担活动期间本人及携带的未成年子女发生的自身意外风险责任，且同意组办方对于非组办方原因造成的伤害、死亡或其他任何形式的损失不承担任何形式的赔偿。<br/>
          11、本人同意接受组办方在活动期间提供的现场急救性质的医务治疗，但在医院救治等发生的相关费用由本人自理。<br/>
          12、本人授权组办方及指定媒体无偿使用本人的肖像、姓名、声音和其它个人资料用于活动的组织和推广。<br/>
          13、如果出现恶劣天气，组办方有权延迟开始fashion run 活动，如果延迟后恶劣天气持续，组办方有权延后活动。<br/>
          14、本人已认真阅读并全面理解以上内容，且对上述所有内容予以确认并承担相应的法律责任。
        </p>
    </div>
    <!--填写表单 end-->


</div>
</rapid:override>
<rapid:override name="footer">
  
 </rapid:override>
<%@ include file="/WEB-INF/pages/common/common_no_content_banner.jsp" %> 