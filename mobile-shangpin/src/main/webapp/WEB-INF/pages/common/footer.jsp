<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/pages/common/include.inc.jsp"%>
<!-- 公共尾部 start -->
<c:choose>
	<c:when test="${checkWX }">
		<footer class="wx_foot">
			<ul class="wx_menu">
				<li><a href="${ctx}/flagshop/index">旗舰店</a></li>
				<li><a href="${ctx}/brand/list">品牌</a></li>
				<li><a href="${ctx}/category/index">品类</a></li>
				<li><a href="${ctx}/user/home">个人中心</a></li>
			</ul>
			<ul class="wx_menu">
				<li><a href="${ctx}/download">下载尚品客户端</a></li>
			</ul>
			<div class="wxalCopyright">
				客服电话：<a class="tel" href="tel:4006-900-900">4006-900-900</a>
				<!--  <p>客服电话：<a class="tel" href="tel:4006-900-900">4006-900-900</a></p>-->
			</div>
			<div class="wxCopyright">
				<span>尚品shangpin.com</span> 京ICP备09087291号-2Copyright<br /> ©
				2010-2012 Shangpin.com All Rights Reserved
			</div>
		</footer>

	</c:when>
	<c:otherwise>
		<rapid:block name="down_page">
			<div class="btm_img">
				<a href="${ctx }/download"><img
					src="${cdn:pic(pageContext.request)}/styles/shangpin/images/e.gif"
					lazy="${cdn:pic(pageContext.request)}/styles/shangpin/images/add_footer.jpg"
					height="60"></a>
			</div>
		</rapid:block>
		<div class="app_bg">
			<footer class="app_foot">

				<c:choose>
					<c:when test="${sessionScope.mshangpin_user == null }">
						<p class="app_user">
							<a href="${ctx}/login">登录</a> &nbsp;&nbsp;|&nbsp;&nbsp;<a
								href="${ctx}/toRegister">注册</a>
						</p>
					</c:when>
					<c:otherwise>
						<p class="app_user">
							<a href="${ctx}/user/home" class="userBtn" title="单击进入个人中心">${sessionScope.mshangpin_user.name }</a>
							&nbsp;&nbsp;|&nbsp;&nbsp; <a href="${ctx}/logout" class="userBtn">退出</a>
						</p>
					</c:otherwise>
				</c:choose>

				<div class="appCopyright">
					客服电话：<a href="tel:4006-900-900">4006-900-900</a>（08:00-24:00）<br />
					Copyright 2010-2014 Shangpin.com 版权所有<br /> 北京尚品百姿电子商务有限公司
				</div>
				<div class="download_bottom_app">
					<span red_url=""><img src="${cdn:pic(pageContext.request)}/styles/shangpin/images/new-mall/download_bottom.png"></span>
				</div>
			</footer>
		</div>
	</c:otherwise>

</c:choose>


<!-- 公共尾部 end -->