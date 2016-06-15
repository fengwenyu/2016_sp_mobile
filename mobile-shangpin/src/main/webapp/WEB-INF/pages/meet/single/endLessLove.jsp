<%@ page language="java" contentType="text/html; charset=UTF-8"
	trimDirectiveWhitespaces="true" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/pages/common/include.inc.jsp"%>

<rapid:override name="custum">
<style>
.main_box img{ width:100%}
</style>
<script type="text/javascript" charset="utf-8">
	loader = SP.core
	.install("${cdn:js(pageContext.request)}/styles/shangpin/js/jquery.min.js${ver}")
	.using("${cdn:js(pageContext.request)}/styles/shangpin/js/lazyload.js${ver}")
	.using("${cdn:js(pageContext.request)}/styles/shangpin/js/jquery.easing.min.js${ver}")
	.using("${cdn:js(pageContext.request)}/styles/shangpin/js/jQueryRotate.2.2.js${ver}")
	.excute()
	.using("${cdn:js(pageContext.request)}/styles/shangpin/js/config.sim.js${ver}")
	.excute()
	.using("${cdn:js(pageContext.request)}/styles/shangpin/js/xmas.dialogs.js${ver}")
	.using("${cdn:js(pageContext.request)}/styles/shangpin/js/downloadAppShowMeet.js${ver}")
    .using("${cdn:js(pageContext.request)}/styles/shangpin/js/meet/single/marqueeLuck.js${ver}")
    .excute(function(){
		isHome(false);
	});
	</script>
<link href="${cdn:css(pageContext.request)}/styles/shangpin/css/page/meet/single/20150817goodluck.css${ver}" rel="stylesheet" />
</rapid:override>
<rapid:override name="header">
</rapid:override>
<rapid:override name="content">
<input id="isLogin"  type="hidden"  name="isLogin"  value="${sessionScope.isLogin}"/>
<div class="alContent">
	<div class="conbox">
        <h1 class="title-top">
            <img src="${cdn:pic(pageContext.request)}/styles/shangpin/images/20150817EndLessLove/goodluck_01.jpg">
        </h1>
        <!--------转盘S------>
        <div id="lottery">
            <ul>
              <li class="clr">
                <span class="lottery-unit lottery-unit-0"><img src="${cdn:pic(pageContext.request)}/styles/shangpin/images/20150817EndLessLove/thr1.png" /><em></em></span>
                <span class="lottery-unit lottery-unit-1"><img src="${cdn:pic(pageContext.request)}/styles/shangpin/images/20150817EndLessLove/thank.png" /><em></em></span>
                <span class="lottery-unit lottery-unit-2"><img src="${cdn:pic(pageContext.request)}/styles/shangpin/images/20150817EndLessLove/thr3.png" /><em></em></span>
                <span class="lottery-unit lottery-unit-3"><img src="${cdn:pic(pageContext.request)}/styles/shangpin/images/20150817EndLessLove/cashOne.png" /><em></em></span>
              </li>
              <li class="clr">
                <span class="lottery-unit lottery-unit-9"><img src="${cdn:pic(pageContext.request)}/styles/shangpin/images/20150817EndLessLove/thr10.png" /><em></em></span>
                <span class="start">开始抽奖</span>
                <span class="lottery-unit lottery-unit-4"><img src="${cdn:pic(pageContext.request)}/styles/shangpin/images/20150817EndLessLove/thr5.png" /><em></em></span>
              </li>
              <li class="clr">
                <span class="lottery-unit lottery-unit-8"><img src="${cdn:pic(pageContext.request)}/styles/shangpin/images/20150817EndLessLove/cashTow.png" /><em></em></span>
                <span class="lottery-unit lottery-unit-7"><img src="${cdn:pic(pageContext.request)}/styles/shangpin/images/20150817EndLessLove/thr8.png" /><em></em></span>
                <span class="lottery-unit lottery-unit-6"><img src="${cdn:pic(pageContext.request)}/styles/shangpin/images/20150817EndLessLove/thank.png" /><em></em></span>
                <span class="lottery-unit lottery-unit-5"><img src="${cdn:pic(pageContext.request)}/images/e.gif" lazy="${cdn:pic(pageContext.request)}/styles/shangpin/images/20150817EndLessLove/thr6.png" /><em></em></span>
              </li>
            </ul>
            <div class="lottery-show"></div>
        </div> 
        <!--------转盘end------>
        <img src="${cdn:pic(pageContext.request)}/images/e.gif" lazy="${cdn:pic(pageContext.request)}/styles/shangpin/images/20150817EndLessLove/goodluck_02.jpg" />
        <div class="box_con">
            <div class="sroll-text">
                <div>
                <ul class="lottery_list" id="window_roll">
                </ul>
                </div>
            </div>
        </div>
	</div>
    
</div>
</rapid:override>

 
<rapid:override name="footer">
</rapid:override>
<%@ include file="/WEB-INF/pages/common/base_meet.jsp" %> 
