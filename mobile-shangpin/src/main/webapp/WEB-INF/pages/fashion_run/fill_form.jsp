<%@ page language="java" contentType="text/html; charset=UTF-8" trimDirectiveWhitespaces="true"
    pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/pages/common/include.inc.jsp"%>
<rapid:override name="custum">
	<link href="${cdn:css(pageContext.request)}/styles/shangpin/css/base.css" rel="stylesheet" />
	<link  href="${cdn:css(pageContext.request)}/styles/shangpin/css/font-css/font-awesome.min.css${ver}" rel="stylesheet">
	<link href="${cdn:css(pageContext.request)}/styles/shangpin/css/page/FashionRun/form_new.css${ver}" rel="stylesheet" />
	<script type="text/javascript" charset="utf-8">
    loader = SP.core
      	.install("${cdn:css(pageContext.request)}/styles/shangpin/js/jquery.min.js${ver}")  //jquery库文件
      	.using("${cdn:css(pageContext.request)}/styles/shangpin/js/j.dialogs.js${ver}")
		.excute()
		.using("${cdn:js(pageContext.request)}/styles/weixin/jweixin-1.0.0.js${ver}")
		.using("${cdn:js(pageContext.request)}/styles/shangpin/js/weixin/WXShareM.js${ver}")
		.using("${cdn:css(pageContext.request)}/styles/shangpin/js/FashionRun/bigGlass_new.js${ver}")
		.using("${cdn:css(pageContext.request)}/styles/shangpin/js/FashionRun/form_team.js${ver}")
      
      
</script>
</rapid:override>
<%-- 浏览器标题 --%>
<rapid:override name="title">
	填写报名表
</rapid:override>

<%-- 页标题 --%>
 <rapid:override name="page_title">
	填写报名表
</rapid:override> 
 <rapid:override name="appLayer">
</rapid:override> 
<rapid:override name="downloadAppShowHead">
		
</rapid:override>
<rapid:override name="content">
<input type="hidden" name="_shareUrl"  id="_shareUrl"  value="${basePath }/fashionrun/team"/>
	<input type="hidden" name="_mainTitle"  id="_mainTitle"  value="FashionRun北京站美女招募,8月1日奥森公园不美不跑."/>
	<input type="hidden" name="_mainDesc"  id="_mainDesc"  value="FashionRun北京站美女招募,8月1日奥森公园不美不跑."/>
	<input type="hidden" name="_mainImgUrl" id="_mainImgUrl"  value="${basePath }/styles/shangpin/images/FashionRun/fashion-run.jpg"/>
	<c:choose>
	<c:when test="${!checkWX&&!checkAPP}">
				<div class="alContent form-content">
	</c:when>
	<c:otherwise>
		<div class="alContent form-content" style="margin-top: 0;">
	</c:otherwise>
	</c:choose>
	<!--填写表单 start-->
    <div class="form-box">
        <img src="${ctx }/styles/shangpin/images/FashionRun/form_banner.png" alt="">
         <form id="fashion_form"  method="post">
        <div class="row-box">
        	<label for="groupSelect">请选择跑团</label>
            <select id="J_groupSelect" name="teamFlag">
            	<option value="0">选择跑团</option>
                <option value="1">美瑞克斯</option>
                <option value="2">黎明脚步</option>
                <option value="3">光明乐跑</option>
                <option value="4">MamaRun</option>
                <option value="5">Runningcat</option>
                <option value="6">酷跑中国</option>
                <option value="7">益跑网</option>
                <option value="8">爱败妈妈</option>
                <option value="9">大红包</option>
                <option value="10">华贸跑团</option>
                <option value="11">宜信跑团</option>
                <option value="12">天坛乐跑</option>
                <option value="13">北京跑吧 </option>
                <option value="14">北京星光跑</option>
                <option value="15">TeamRunBEIJING</option>
                <option value="16">北工大长跑俱乐部</option>
                <option value="17">李宁跑步俱乐部 </option>
                <option value="18">嗨跑</option>
                <option value="19">咕咚跑团</option>
                <option value="20">猛犸纵横跑团</option>
                <option value="21">跑团小秘粉丝团</option>
                <option value="22">必迈跑团 </option>
                <option value="23">有你真好</option>
                <option value="24">延庆跑吧</option>
                <option value="25">香山跑团</option>
                <option value="26">S奥森夜跑团</option>
                <option value="27">慢跑北京</option>
                <option value="28">搜狐跑步俱乐部</option>
                <option value="29">笨鸟跑团</option>
                <option value="30">柠檬健身</option>
                <option value="31">juiceup</option>
             <!--    <option value="32">闺蜜跑</option>
                <option value="33">新浪体育跑团</option>
                <option value="001">美女领跑团</option>
                <option value="34">芝麻RUN</option> -->
            </select>
            
        </div>
        <div class="row-box">
        	<label for="groupCode">您的跑团邀请码</label>
            <input type="text" id="J_groupCode" name="inviteCode" placeholder="请填写您的跑团邀请码" value="" required />
            <div><button type="button"></button></div>
        </div>
        <div class="row-box">
            <label for="userName">您的姓名</label>
            <input type="text" id="J_userName" name="name" placeholder="请填写您的真实姓名" value="" required />
            <div><button type="button"></button></div>
        </div>
        
        <div class="row-box">
            <label for="mobileNum">您的手机号</label>
            <input type="text" pattern="[0-9]*" id="J_mobileNum" name="phone" placeholder="请填写您的手机号码" value="" required maxlength="11" />
            <div><button type="button"></button></div>
        </div>
        
        <div class="row-box">
            <label for="identificationNum">您的身份证号码</label>
            <input type="text" id="J_identificationNum" name="pid" placeholder="请输入您的身份证号" value="" required maxlength="18" />
            <div><button type="button"></button></div>
        </div>
        </form>
        <h2 class="title">时尚装备</h2>
        <div class="pic-box">
            <img src="${ctx }/styles/shangpin/images/FashionRun/11111.jpg" />
    	<img src="${ctx }/styles/shangpin/images/FashionRun/22222.jpg" style="margin-top:10px"/>
            <p>时尚装备以收到的实物为准,装备由尚品网提供</p>
        </div>
        <h2 class="title">支付报名费</h2>
        <div class="row-box">
            <em>报名费用：</em>
            <span>84元／人（含时尚装备及保险）</span>
        </div>
        <div class="row-box">
            <em class="zhifu_way">支付方式：</em>
	        <c:choose>
	        <c:when test="${checkWX }">
	           <span class="js-pay-mode" payType="1"><img src="${ctx }/styles/shangpin/images/FashionRun/icon_pay02.png" alt="" class="cur"></span> 
	        </c:when>
	        <c:otherwise>
	         <span class="js-pay-mode" payType="2"><img src="${ctx }/styles/shangpin/images/FashionRun/icon_pay01.png" alt="" class="cur"></span>
	        	 
	        </c:otherwise>
	        </c:choose>
          
          
        </div>
        <div class="btn-box">
            <a href="javascript:;" class="submit-btn js-sign-up">立即报名</a>
        </div>
        
        <p class="agreement"><em class="cur"></em><a href="${ctx }/fashionrun/agree">同意免责声明</a></p>
         <input type="hidden" id="orderId" name="orderId" value="${fashionOrder.orderId }"/>
    
    </div>
    <!--填写表单 end-->

    <!-- 点击结算提醒弹层 --> 
    <div class="overlay" id="overlay">
        <section class="modal modal-test">
          <h3 class="modal-hd">信息确认</h3>
          <div class="modal-bd">
            <p>姓&#12288;&#12288;名：<em class="js-name-val"></em></p>
            <p>手机号码：<em class="js-mobile-val"></em></p>
            <p>身份证号：<em class="js-id-val"></em></p>
          </div>
          <div class="modal-ft">
            <span class="btn-modal js-close">返回修改</span>
            <span class="btn-modal sure"><a href="javascript:pay();">确认</a></span>
          </div>
          
        </section>      
    </div>
</rapid:override>
<rapid:override name="footer">
  
 </rapid:override>
<%@ include file="/WEB-INF/pages/common/common_no_content_no_banner.jsp" %> 