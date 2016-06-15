<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/pages/common/include.inc.jsp"%>

<!-- 公共尾部 start -->
<div class="app_bg">
    <footer class="app_foot">
    
        <c:choose>
	  		<c:when test="${sessionScope.mshangpin_user == null }">
		  		<p class="app_user"><a href="${ctx}/login">登录</a> &nbsp;&nbsp;|&nbsp;&nbsp;<a href="${ctx}/toRegister">注册</a></p>
			</c:when>
			<c:otherwise>
				<p class="app_user">
				<a href="${ctx}/user/home" class="userBtn" title="单击进入个人中心">${sessionScope.mshangpin_user.name }</a>
				&nbsp;&nbsp;|&nbsp;&nbsp;
				<a href="${ctx}/logout" class="userBtn">退出</a>
				</p>
			</c:otherwise>
		</c:choose>
   <%--          <!-- 底部下载APP -->
		<div class="download_bottom_app">							
		     <span class="footerAPP "red_url="">
		     <input type="hidden" name="_favor" id="_favor" />	
		     <img id="_favorImg" src="${ctx}/styles/shangpin/images/new-mall/download_bottom01.png"></span>	
		</div> --%>
        <div class="appCopyright">
            客服电话：<a href="tel:4006-900-900">4006-900-900</a>（08:00-24:00）<br />
            Copyright  2010-2014 Shangpin.com 版权所有<br />
            北京尚品百姿电子商务有限公司
        </div>
		<div class="download_bottom_app">
			<span red_url=""><img src="${cdn:pic(pageContext.request)}/styles/shangpin/images/new-mall/download_bottom.png"></span>
		</div>
    </footer>
</div>
<!-- 公共尾部 end -->