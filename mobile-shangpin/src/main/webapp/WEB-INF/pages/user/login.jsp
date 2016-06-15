<%@ page language="java" contentType="text/html; charset=UTF-8" trimDirectiveWhitespaces="true"
    pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/pages/common/include.inc.jsp"%> 

<rapid:override name="custum">
	<link href="${cdn:css(pageContext.request)}/styles/shangpin/css/page/login.css${ver}" rel="stylesheet" />
	<link href="${cdn:css(pageContext.request)}/styles/shangpin/css/page/index.css${ver}" rel="stylesheet" />
	<script type="text/javascript" charset="utf-8">
			loader = SP.core
				.install("${cdn:js(pageContext.request)}/styles/shangpin/js/jquery.min.js${ver}")
				.using("${cdn:js(pageContext.request)}/styles/shangpin/js/lazyload.js${ver}")
				.using("${cdn:js(pageContext.request)}/styles/shangpin/js/login.js${ver}")
				.excute()
				.using("${cdn:js(pageContext.request)}/styles/shangpin/js/config.sim.js${ver}")
				.using("${cdn:js(pageContext.request)}/styles/shangpin/js/login1.js${ver}")
				.excute()
			</script>
</rapid:override > 


<%-- 浏览器标题 --%>
<rapid:override name="title">
	登录
</rapid:override>

<%-- 页标题 --%>
<rapid:override name="page_title">
	尚品账号登录
</rapid:override>
<rapid:override name="content">
    <c:set var="logincount" value="4"/><%-- TODO 线上临时打开图形验证码。后期需删除--%>
  <form name="login" id="J_Login" method="post" action="${ctx}/login">
  <input type="hidden" name="back" value="${back}" />
   <input type="hidden" name="way" value="${way}" />
  <fieldset>
    <p class="c-form-search">
      <label for="email">账号</label><input type="text" id="J_UserNameTxt" name="userName"  placeholder="手机／邮箱"  value="${userName}" required /><button type="button"></button>
      <span class="datalist"></span>
    </p>
    <p class="c-form-search">
      <label for="password">登录密码</label><input type="password" id="J_PassWordTxt" name="password" placeholder="请输入密码" required maxlength="20" /><button type="button"></button>
    </p>
     <input type="hidden" id="logincount" value="${logincount}"/>
   <c:if test="${logincount > 2 }">
    <hgroup style="width:100%; margin:0;">
    <p class="c-form-search" style="width:55%; float:left;">
      <label for="captcha">验证码</label>
   	  <input type="text" name="captcha" id="J_Captcha" placeholder="请输入验证码" size="6" maxlength="6" style="width:50%; margin-left: 75px; margin-top: -70px;" required/>
      <button type="button"></button>
    </p>
    <img title="看不清，单击图片换一组" src="${ctx}/captcha" style="margin:0 0 0 10px;" onclick="changeImage(this);" width="95" height="40" >
    </hgroup>
 </c:if>
    <p class="login_errorMsg mobiMsg">&nbsp;${msg}</p>
    <c:choose>
	    <c:when test="${checkWX }">
	    	<input type="submit" class="login_btn" value="登 录" onclick="_smq.push(['custom', '登录', '${sessionScope.mshangpin_user.userid}']);"/>
	    </c:when>
	    <c:otherwise>
	    	 <input type="submit" class="login_btn" value="登 录" onclick="_smq.push(['custom', '登录', '${sessionScope.mshangpin_user.userid}']);" />
	    </c:otherwise>
    </c:choose>
    <div class="clr">
    <a href="${ctx}/findpwd" class="login_link" style="float:left;">忘记密码</a>
     <a href="${ctx}/toRegister" class="login_link"style="float:right;">注册</a></div>
  </fieldset>
  </form>
    <div id="others" class="clr">
        <p>第三方快速登录</p>
        <div class="other-login">
                <%--<a href="javascript:" onclick="thirdLogin('wb')"><i><img alt="微博" src="${cdn:pic(pageContext.request)}/styles/shangpin/images/login/wb.png"></i>微博登录</a>--%>
            <c:if test="${checkWX}">
                <a href="javascript:" onclick="thirdLogin('wx')"><i><img alt="微信" src="${cdn:pic(pageContext.request)}/styles/shangpin/images/login/wx.png"></i>微信登录</a>
            </c:if>
            <a href="javascript:" onclick="thirdLogin('qq')"><i><img alt="QQ" src="${cdn:pic(pageContext.request)}/styles/shangpin/images/login/qq.png"></i>QQ登录</a>
        <%-- <a href="javascript:" onclick="thirdLogin('zfb')"><i><img alt="支付宝" src="${cdn:pic(pageContext.request)}/styles/shangpin/images/login/zfb.png"></i>支付宝</a>--%>
        </div>
    </div>
</rapid:override>

 <rapid:override name="footer">
  
 </rapid:override>
<%@ include file="/WEB-INF/pages/common/common.jsp" %> 