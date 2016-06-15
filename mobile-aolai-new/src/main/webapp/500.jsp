<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/pages/common/include.inc.jsp"%>

<rapid:override name="custum">
	<link href="${cdn:css(pageContext.request)}/styles/shangpin/css/page/order.new.css${ver}" rel="stylesheet" />
</rapid:override>

<rapid:override name="content">
<div class="alContent">
    <div class="no_result" >
      <span>咦，出错了。</span>
      <a href="${ctx}/index">返回首页</a>
    </div>
</div>

</rapid:override>
<rapid:override name="footer">
</rapid:override> 
<%@ include file="/WEB-INF/pages/common/base.jsp" %> 