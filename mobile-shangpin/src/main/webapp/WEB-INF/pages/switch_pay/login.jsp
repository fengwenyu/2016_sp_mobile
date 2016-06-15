<%@ page language="java" contentType="text/html; charset=UTF-8" trimDirectiveWhitespaces="true"
    pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/pages/common/include.inc.jsp"%>

<rapid:override name="custum">
	<link href="${cdn:css(pageContext.request)}/styles/shangpin/css/base.css" rel="stylesheet" />
	<link href="${cdn:css(pageContext.request)}/styles/shangpin/css/page/FashionRun/login.css" rel="stylesheet" />
	<script type="text/javascript" charset="utf-8">
    loader = SP.core
    .install("${cdn:css(pageContext.request)}/styles/shangpin/js/jquery.min.js${ver}")  //jquery库文件
  	.using("${cdn:css(pageContext.request)}/styles/shangpin/js/j.dialogs.js${ver}")
	.excute()
	.using("${cdn:css(pageContext.request)}/styles/shangpin/js/comm.js${ver}")
      
</script>
</rapid:override>

<%-- 浏览器标题 --%>
<rapid:override name="title">
	后台管理首页
</rapid:override>

<%-- 页标题 --%>
   <rapid:override name="page_title">
	后台管理首页
</rapid:override> 
 <rapid:override name="appLayer">
</rapid:override> 
<rapid:override name="downloadAppShowHead">
		
</rapid:override>
<rapid:override name="content">   
  <div class="wrapper100">
      <form action="${ctx}/switch/pay/login" method="post" id="formSignIn" style="margin:0px">
      <div class="lgcontenbar">
          <div class="lgcontentbj l">
            <div class="lgcontbjbar">
              <div class="lgcontbj2 l">
                <div class="lgcontbjcnt l">
                  <dl class="lgconttit l">
                    <dt>用户名/USERNAME:</dt>
                    <dd><input name="userName" id="formSignIn_UserName" value="" type="text"></dd>
                  </dl>
                  <dl class="lgconttit lgmgtop l">
                    <dt>密码/PASSWORD:</dt>
                    <dd><input name="password" id="formSignIn_Password" type="password"></dd>
                  </dl>
                   <p class="login_errorMsg mobiMsg" style="border: none;color: #b51111;padding: 0;height: 25px;line-height: 25px;text-align: left;">&nbsp;${msg}</p>
                  <h1 class="lgbtn lgmgtop02 l"><span><input type="image" src="${ctx}/styles/shangpin/images/loginqr.gif" ></span><span class="lgbtnmglt"><input type="image" src="${ctx}/styles/shangpin/images/loginqk.gif"  onclick="javascript:document.getElementById('formSignIn_UserName').value='';document.getElementById('formSignIn_Password').value='';return false;"></span></h1>
               
                </div>
              </div>
            </div>
          </div>
        </div>
      </form>
    </div>
</rapid:override>
 <rapid:override name="footer">
  
 </rapid:override> 
<%@ include file="/WEB-INF/pages/common/base_no_container_banner.jsp" %> 
