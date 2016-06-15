<%@ page language="java" contentType="text/html; charset=UTF-8" trimDirectiveWhitespaces="true"
    pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/pages/common/include.inc.jsp"%>

<rapid:override name="custum">
	<link href="${cdn:css(pageContext.request)}/styles/shangpin/css/page/overseas/order_form.css${ver}" rel="stylesheet" />
	<script src="${cdn:js(pageContext.request)}/styles/shangpin/js/jquery.min.js${ver}"  type="text/javascript" charset="utf-8"></script>
	<script src="${cdn:js(pageContext.request)}/styles/shangpin/js/comm.js${ver}"  type="text/javascript" charset="utf-8"></script>
	<script src="${cdn:js(pageContext.request)}/styles/shangpin/js/j.dialogs.js${ver}"  type="text/javascript" charset="utf-8"></script>
	<script src="${cdn:js(pageContext.request)}/styles/shangpin/js/j.appDialogs.js${ver}"  type="text/javascript" charset="utf-8"></script>
</rapid:override>
<rapid:override name="title">
	订单提交
</rapid:override>
<rapid:override name="header">
	<!--头部 start-->
	<c:choose>
		<c:when test="${!checkWX&&!checkAPP}">    
			<div class="topFix" id="order_head">
		    <section>
		        <div class="topBack" >
		        <a href="javascript:history.back(-1);" class="backBtn">&nbsp;</a>
		        <span class="top-title">订单数据错误</span>
		        <ul class="alUser_icon clr">
		           <li><a href="<c:url value='/index'/>"><img src="${cdn:pic(pageContext.request)}/styles/shangpin/images/home_icon.png" width="25" height="25" alt="首页"></a></li>
		        </ul>
		        </div>
		    </section>
	  	</div>
		<div class="alContent" id="order_container">
		</c:when>
		<c:otherwise>
			<div class="alContent" id="order_container"  style="margin-top: 0;">
		</c:otherwise>
	</c:choose>
	    
	  <!--头部 end-->
</rapid:override>
<rapid:override name="content">
	<div class="paymet_block">
	            <fieldset>
	            	<legend class="price"><em>${msg }</em></legend>
	            </fieldset>
	    </div>
  </rapid:override>  
  <rapid:override name="footer">
</rapid:override>
<%@ include file="/WEB-INF/pages/common/another_base.jsp" %> 