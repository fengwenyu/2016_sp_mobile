<%@ page language="java" contentType="text/html; charset=UTF-8"
	trimDirectiveWhitespaces="true" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/pages/common/include.inc.jsp"%>

</head>
<rapid:override name="custum">
	<link
		href="${cdn:css(pageContext.request)}/styles/shangpin/css/page/weixin/account.css${ver}"
		rel="stylesheet" />
	<script type="text/javascript" charset="utf-8">
		loader = SP.core
				.install("${cdn:js(pageContext.request)}/styles/shangpin/js/jquery.min.js${ver}")
				.using("${cdn:js(pageContext.request)}/styles/shangpin/js/lazyload.js${ver}")
				.using("${cdn:js(pageContext.request)}/styles/shangpin/js/j.dialogs.js${ver}")
				.excute()
				.using("${cdn:js(pageContext.request)}/styles/shangpin/js/config.sim.js${ver}")

				.excute(function() {
					isHome(false);
					
				});
		
		//解除绑定
		function removebind(){
		   if(confirm("确认要解除绑定吗？")){
	            window.location.href="${ctx}/weixin/bind/modify?way=remove";
		    }
		}
	</script>
</rapid:override>
<%-- 浏览器标题 --%>
<rapid:override name="title">
	帐户绑定
</rapid:override>

<%-- 页标题 --%>
<rapid:override name="page_title">
	帐户绑定
</rapid:override>
<rapid:override name="content">
	 <div class="wx_bind">
    <dl>
      <dd class="bind_txt">尚品账户：<strong>${sessionScope.mshangpin_user.email}</strong><br />会员等级：<strong>${sessionScope.mshangpin_user.level}<s:property value="#session['user'].level"/></strong></dd>

      <dd class="bind_btn"><a href="${ctx }/weixin/bind/modify?way=modify" >更换帐号</a> <a href="javascript:removebind();" >解除绑定</a></dd>

    </dl>
  </div>
  
</rapid:override>
<%@ include file="/WEB-INF/pages/common/common.jsp"%>








