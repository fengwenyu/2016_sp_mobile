<%@ page language="java" contentType="text/html; charset=UTF-8" trimDirectiveWhitespaces="true"
    pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/pages/common/include.inc.jsp"%> 

<rapid:override name="custum">
	<link href="${cdn:css(pageContext.request)}/styles/shangpin/css/page/member.css${ver}" rel="stylesheet" />
	<script type="text/javascript" charset="utf-8">
			loader = SP.core
				.install("${cdn:js(pageContext.request)}/styles/shangpin/js/zepto.min.js${ver}")
				.using("${cdn:js(pageContext.request)}/styles/shangpin/js/lazyload.js${ver}")
				.excute()
				.using("${cdn:js(pageContext.request)}/styles/shangpin/js/config.sim.js${ver}")
				.excute()
				.using("${cdn:js(pageContext.request)}/styles/shangpin/js/comm.js${ver}")
				
	</script>
</rapid:override > 

<%-- 页标题 --%>
<rapid:override name="page_title">
	个人中心
</rapid:override>

<rapid:override name="content">


	
		<div class="clr account_info"> 
		 	 <c:choose>
			  	<c:when test="${sessionScope.mshangpin_user.gender==1}">
			  		<img class="account_user_photo" src="${cdn:pic(pageContext.request)}/styles/shangpin/images/man.png" width="80" height="80">
			  	</c:when>
			  	<c:otherwise>
			  		<img class="account_user_photo" src="${cdn:pic(pageContext.request)}/styles/shangpin/images/women.png" width="80" height="80">
			  	</c:otherwise>
		    </c:choose>
		    <dl>
	        	<dt>${sessionScope.mshangpin_user.name}</dt>
	            <dd>${sessionScope.mshangpin_user.level}</dd>
		    </dl>
	  </div>
	  
	  <div class="account_btn_wp">
	   <c:if test="${hasWaitPayOrder}">
	     <a class="has_message" href="<c:url value='/order/list?statusType=2'/>">待付款</a> 
	     </c:if>
	     <c:if test="${!hasWaitPayOrder}">
	     <a  href="<c:url value='/order/list?statusType=2'/>">待付款</a> 
	     </c:if>
	     <a href="${ctx}/cart/list">购物车</a>
	     <a href="${ctx}/coupon/list">优惠券</a> 
      </div>
      
      <menu>
        <li><a href="<c:url value='/order/list?statusType=1'/>">全部订单 </a></li>
        <li><a href="${ctx}/address/list">地址管理</a></li>
        <li><a href="${ctx}/collect/product/list?pageIndex=1&pageSize=20&shopType=1">我的收藏</a></li>
        <li><a href="${ctx}/findpwd">修改密码</a></li>
        <li><a href="${ctx}/help/index.html">帮助</a></li>
      </menu>
     
	  <div class="payment_submit">
	        <a href="${ctx }/logout" class="payment_btn">退出</a>
	  </div> 
	
</rapid:override > 

<%@ include file="/WEB-INF/pages/common/common.jsp" %>  