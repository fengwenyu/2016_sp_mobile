<%@ page language="java" contentType="text/html; charset=UTF-8" trimDirectiveWhitespaces="true"
    pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/pages/common/include.inc.jsp"%>

<rapid:override name="custum">
	<link href="${cdn:css(pageContext.request) }/styles/shangpin/css/page/return/return_logistics.css${ver}" rel="stylesheet" />
	<script type="text/javascript" charset="utf-8">
		loader = SP.core
		.install("${cdn:js(pageContext.request)}/styles/shangpin/js/jquery.min.js${ver}")
		.using("${cdn:js(pageContext.request)}/styles/shangpin/js/j.dialogs.js${ver}")
		.using("${cdn:js(pageContext.request)}/styles/shangpin/js/lazyload.js?${ver}")
		.excute()
		.using("${cdn:js(pageContext.request)}/styles/shangpin/js/config.sim.js?${ver}")
		.excute()
		.using("${cdn:js(pageContext.request)}/styles/shangpin/js/comm.js?${ver}")
		.excute()
		.using("${cdn:js(pageContext.request)}/styles/shangpin/js/UIPickerView.js?${ver}")
		.using("${cdn:js(pageContext.request)}/styles/shangpin/js/return/return_logistics.js?${ver}")
	</script>
</rapid:override>
<rapid:override name="title">
	提交成功
</rapid:override>
<rapid:override name="page_title">
	提交成功
</rapid:override>

<rapid:override name="content">
    <section class="return_sucess_info">
       <p>感谢您，信息提交成功，系统会给您更新退货进度，正常给您办理退款</p>
    </section>
</rapid:override>


<rapid:override name="footer">
	
</rapid:override>
<%@ include file="/WEB-INF/pages/common/submit_order_common.jsp" %> 