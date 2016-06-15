<%@ page language="java" contentType="text/html; charset=UTF-8"
	trimDirectiveWhitespaces="true" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/pages/common/include.inc.jsp"%>

<rapid:override name="custum">
<style>
.main_box img{ width:100%}
.container {height: 100%;}
</style>
<script type="text/javascript" charset="utf-8">
	loader = SP.core
	.install("${cdn:js(pageContext.request)}/styles/shangpin/js/jquery.min.js${ver}")
	.using("${cdn:js(pageContext.request)}/styles/shangpin/js/lazyload.js${ver}")
	.excute()
	.using("${cdn:js(pageContext.request)}/styles/shangpin/js/config.sim.js${ver}")
	.excute()
	.using("${cdn:js(pageContext.request)}/styles/shangpin/js/xmas.dialogs.js${ver}")
	.using("${cdn:js(pageContext.request)}/styles/shangpin/js/jQueryRotate.2.2.js${ver}")
	.using("${cdn:js(pageContext.request)}/styles/shangpin/js/jquery.easing.min.js${ver}")
    .using("${cdn:js(pageContext.request)}/styles/shangpin/js/meet/goodluck.js${ver}")
    .excute();
	</script>
<link href="${cdn:css(pageContext.request)}/styles/shangpin/css/page/20150728fashionRunGoodLuck.css${ver}" rel="stylesheet" />
</rapid:override>

<rapid:override name="header">
	<c:if test="${!checkWX&&!checkAPP}">
		<%@ include file="/WEB-INF/pages/common/header.jsp"%>
	</c:if>
</rapid:override>
<rapid:override name="content">
<body id="bodyId">

<div class="alContent">
	<h1 class="title-top">
        <img src="${cdn:pic(pageContext.request)}/styles/shangpin/images/20150728fashionRunGoodLuck/img01.png">
    </h1>
	<div class="turntable">
        <div class="turntable-box">
        	<img id="turntable_img" src="${cdn:pic(pageContext.request)}/styles/shangpin/images/20150728fashionRunGoodLuck/turntable_img_03.png">
            <div id="turntable_btn"></div>
        </div>
         <i class="shadow_line"><img src="${cdn:pic(pageContext.request)}/styles/shangpin/images/20150728fashionRunGoodLuck/shadow_line.png"></i>
    </div>
   
    <div class="start_btn">
    	<a href="javascript:;" class="start_draw">开始抽奖</a>
    </div>
    
    <!-- 验证手机-->    
    <div class="overlay" id="overlay">
        <section class="modal modal-test">
          <div class="modal-hd">手机验证</div>
          <div class="modal-bd">
            	<input class="tip-input" id="J_mobileNum" type="text" maxlength="11" value="" placeholder="请输入手机号" required />
           		 <div class="yzm-box">
                		<div class="login_list">                  
                   			 <input type="text" id="J_yzm" value="" placeholder="请输入验证码" required>                             
                		</div>
                    <em id="passwordGetCode" class="btn_gradient_gray" data-waiting="秒后可重新发送">获取验证码</em>
<!--                     <label> -->
<!-- 					<a href="#" id="passwordGetCode" class="btn_gradient_gray" data-waiting="秒后可重新发送"><em>获取验证码</em></a> -->
<!--                </label> -->
                </div> 
            <a href="#" class="close"></a>    
            <p class="prompt"></p>  
        	<div class="step-btn"><a href="#" class="start_draw_to">确认</a></div>
            
            </div>          
        </section>
    </div>    
    <!-- 验证手机end-->  
</div>

</body>
</rapid:override>

 
<rapid:override name="footer">
</rapid:override>
<%@ include file="/WEB-INF/pages/common/common.jsp" %> 
