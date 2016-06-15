<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/pages/common/include.inc.jsp"%>


<!-- 公共尾部 start -->
    <footer>
    <!-- 下载客户端链接 -->
    <dl class="alDownload_AppBtn">
	    <dd>
	    	<a href="${ctx}/appDownload?store=android"><img style="right: auto;" src="${cdn:pic(pageContext.request)}/styles/shangpin/images/android-download.png" width="118" height="40" alt="Andorid"></a>
	    	<a href="${ctx}/appDownload?store=iphone"> <img src="${cdn:pic(pageContext.request)}/styles/shangpin/images/iphone-download.png" width="118" height="40" alt="iPhoneapp"></a>
	    </dd>
	 </dl>
        <div class="alCopyright">
        	<!-- 登录用户显示信息 -->
		    <c:if test="${sessionScope.maolai_user != null}">
		      <p> 
			      <a href="" class="userBtn">
			      ${sessionScope.maolai_user.name},${ sessionScope.maolai_user.level}</a>
			      <a href="${ctx}/logout" class="userBtn">退出</a> 
		      </p>
		    </c:if>
		    
		    <!-- 未登录用户显示信息 -->
		    <c:if test="${sessionScope.maolai_user==null || sessionScope.maolai_user==''}">
		  	  <p> 
		  	  <a href="${ctx}/login" >登录</a> 
		  	  <a href="${ctx}/register">注册</a></p>
		    </c:if>
		    
		    <p>客服电话：<a class="tel" href="tel:4006-900-900">4006-900-900</a></p>
		     <a href="javascript:;" class="alScrollTop">
		     <img src="${cdn:pic(pageContext.request)}/styles/shangpin/images/scrollTop_icon.png" width="13" height="14" alt="">回顶部</a>
 		 </div>
    </footer>
<!-- 公共尾部 end -->