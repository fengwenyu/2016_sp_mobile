<%@ page language="java" contentType="text/html; charset=UTF-8"
         trimDirectiveWhitespaces="true" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/pages/common/include.inc.jsp"%>

<rapid:override name="custum">
    <link href="${cdn:css(pageContext.request)}/styles/shangpin/css/20160520/base.css${ver}" rel="stylesheet" />
    <link href="${cdn:css(pageContext.request)}/styles/shangpin/css/20160520/packet.css${ver}" rel="stylesheet" />


    <script src="${cdn:js(pageContext.request)}/styles/shangpin/js/jquery.min.js${ver}" type="text/javascript" charset="utf-8"></script>
    <script src="${cdn:js(pageContext.request)}/styles/shangpin/js/20160520/packet.js${ver}" type="text/javascript" charset="utf-8"></script>

    <script src="${cdn:js(pageContext.request)}/styles/weixin/jweixin-1.0.0.js${ver}" type="text/javascript" charset="utf-8"></script>
    <script src="${cdn:js(pageContext.request)}/styles/shangpin/js/star-packet/weixin_ready.js${ver}" type="text/javascript" charset="utf-8"></script>

</rapid:override>
<%-- 浏览器标题 --%>
<rapid:override name="title">
    尚品520撞衫日
</rapid:override>

<%-- 页标题 --%>
<rapid:override name="page_title">
    尚品520撞衫日
</rapid:override>

<rapid:override name="content">
    <input type="hidden" name="_shareUrl"  id="_shareUrl"  value="${basePath}/weixin/toActivify"/>
    <input type="hidden" name="_mainTitle"  id="_mainTitle"  value="520撞衫日，很高兴撞见你！TOPSHOP买一送一"/>
    <input type="hidden" name="_mainDesc"  id="_mainDesc"  value="520，还买玫瑰花吗？还烛光晚餐吗？今年,和我们一起玩点不一样的吧！"/>
    <input type="hidden" name="_mainImgUrl" id="_mainImgUrl"  value="${cdn:pic(pageContext.request)}/styles/shangpin/images/20160520/300300.jpg"/>

    <div class="wapper">

        <!--内容区域 start-->
        <section class="main">

            <div class="video_nav">
                <a href="" class="show_video" data-id="i0199hlgjmz">
                    <img src="${cdn:pic(pageContext.request)}/styles/shangpin/images/20160520/vedio_img.jpg">
                </a>

                <!--视频-->
                <div class="video_box" style="display: block;">
                    <iframe height="100%" width="100%" src="http://v.qq.com/iframe/player.html?vid=i0199hlgjmz&tiny=0&auto=0" frameborder="0" allowfullscreen=""></iframe>
                </div>
            </div>
            <ul class="sp_list">
                <li><img src="${cdn:pic(pageContext.request)}/styles/shangpin/images/20160520/img01.jpg"></li>
                <li><a href="${basePath}/brand/product/list?brandNo=B02850&postArea=0&Source=weixin&Campaign=00"><img src="${cdn:pic(pageContext.request)}/styles/shangpin/images/20160520/img02.jpg"></a></li>
            </ul>
            <a href="${basePath}/brand/product/list?brandNo=B1885&postArea=0&Source=weixin&Campaign=00"><img src="${cdn:pic(pageContext.request)}/styles/shangpin/images/20160520/img03.jpg"></a>
            <ul class="sp_list">
                <li><img src="${cdn:pic(pageContext.request)}/styles/shangpin/images/20160520/img04.jpg"></li>
                <li><a href="${basePath}/brand/product/list?brandNo=B1885&postArea=0&Source=weixin&Campaign=00"><img src="${cdn:pic(pageContext.request)}/styles/shangpin/images/20160520/img05.jpg"></a></li>
            </ul>
            <a href="${basePath}/brand/product/list?brandNo=B1885&postArea=0&Source=weixin&Campaign=00"><img src="${cdn:pic(pageContext.request)}/styles/shangpin/images/20160520/img06.jpg"></a>
            <img src="${cdn:pic(pageContext.request)}/styles/shangpin/images/20160520/img07.png">
            <img src="${cdn:pic(pageContext.request)}/styles/shangpin/images/20160520/text_img_1.png">

            <div id="js_show_box">
                <img src="${cdn:pic(pageContext.request)}/styles/shangpin/images/20160520/icon01.png${ver}">
                <div class="form-box">
                    <input class="tip-input" id="J_mobileNum" type="tel" value="" placeholder="请输入手机号" maxlength="11" required />
                    <div class="yzm-box">
                        <div class="login_list"><input id="J_yzm" type="tel" value="" placeholder="请输入验证码" maxlength="6" required></div>
                        <em id="passwordGetCode" data-waiting="秒">获取验证码</em>
                    </div>

                    <p class="prompt"></p>
                    <a class="start-btn js-start-btn" href="javascript:;">立即领取</a>

                </div>
                <img src="${cdn:pic(pageContext.request)}/styles/shangpin/images/20160520/icon02.png">
            </div>
            <img src="${cdn:pic(pageContext.request)}/styles/shangpin/images/20160520/icon03.png">
        </section>
        <!--内容区域 start-->

        <div id="popup_overlay"></div>
        <div id="popup_container">
            <div id="popup_message">您已经领过了</div>
        </div>

        <!--视频播放-->
        <!--<div class="mask"><a href="" class="close_btn">x</a></div>
        <div class="video_box"></div>-->

        <!--领取成功提示-->
        <!--提示结果弹层-->
        <div class="select-overlay">
            <div class="show_window_con">
                <img src="${cdn:pic(pageContext.request)}/styles/shangpin/images/20160520/show_sucess_img.png">
                <a href="${basePath}/subject/product/list?topicId=60512985&postArea=0&Source=weixin&Campaign=00" class="link_btn"><img src="${cdn:pic(pageContext.request)}/styles/shangpin/images/20160520/link_btn.png"></a>
            </div>
        </div>

    </div>



</rapid:override>
<%@ include file="/WEB-INF/pages/common/star_red_packet_base.jsp" %>