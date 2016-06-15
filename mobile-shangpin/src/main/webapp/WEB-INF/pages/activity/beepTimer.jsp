<%@ page language="java" contentType="text/html; charset=UTF-8" trimDirectiveWhitespaces="true"
    pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/pages/common/include.inc.jsp"%>

<rapid:override name="title">
    活动开启提醒
</rapid:override>
<%-- 页标题 --%>
<rapid:override name="page_title">
	活动开启提醒
</rapid:override>
<rapid:override name="custum">
    <link href="${cdn:css(pageContext.request)}/styles/shangpin/css/star-packet/base.css${ver}" rel="stylesheet" />
    <link href="${cdn:css(pageContext.request)}/styles/shangpin/css/beepTimer.css${ver}"    rel="stylesheet" />
    <script type="text/javascript" charset="utf-8">
            loader = SP.core
                .install("${cdn:js(pageContext.request)}/styles/shangpin/js/jquery.min.js${ver}")
                .using("${cdn:js(pageContext.request)}/styles/shangpin/js/lazyload.js${ver}")
                .excute()
                .using("${cdn:js(pageContext.request)}/styles/shangpin/js/config.sim.js${ver}")
                .using("${cdn:js(pageContext.request)}/styles/shangpin/js/comm.js${ver}")
                .using("${cdn:js(pageContext.request)}/styles/shangpin/js/fixed_public.js?${ver}")
                .using("${cdn:js(pageContext.request)}/styles/shangpin/js/beepTimer.js${ver}")
    </script>
</rapid:override>
<rapid:override name="content">
        <div class="alContent">
        <p class="info_tip">请输入您的手机号，我们会在活动开启前通知您</p>
        <section class="wapper">
            <ul class="input_box">
                <li class="icon_mobile">
                    <input class="input_text" id="J_mobileNum" type="tel" placeholder="请输入手机号" maxlength="11" required="">
                </li>
                <li class="icon_yzm">
                    <div class="input_text ">
                        <input id="J_yzm" placeholder="请输入短信验证码" type="tel" maxlength="6" required="">
                    </div>
                    <em class="get_yzm_btn">获取验证码</em>
                </li>
            </ul>
            <h3 class="title_h3">提前时间</h3>
            <div class="select_time">
                <a href="javascript:;" class="cur" time="5">5分钟</a>
                <a href="javascript:;" time="10">10分钟</a>
                <a href="javascript:;" time="30">30分钟</a>
            </div>
            
            <a href="javascript:;" class="submit_btn" code="${code}">关注</a>
        </section>
        
    </div>
    
    <!--提示弹层--> 
    <div class="tip-overlay"></div>
    <div class="tip-container" >
        <p class="tip-message">领取失败，再试试~</p>
    </div>
 </rapid:override>
<%@ include file="/WEB-INF/pages/common/base_meet.jsp"%>