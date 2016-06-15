<%@ page language="java" contentType="text/html; charset=UTF-8"
         trimDirectiveWhitespaces="true" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/pages/common/include.inc.jsp"%>

</head>
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

    <div class="wapper1">


        <!--领取成功提示-->
        <!--提示结果弹层-->
        <div class="select-overlay active">
            <div class="show_window_con">
                <img src="${cdn:pic(pageContext.request)}/styles/shangpin/images/20160520/show_sucess_img.png${ver}">
                <a href="${basePath}/brand/product/list?brandNo=B1885&postArea=0&Source=weixin&Campaign=weixin&WWWWWWWWW" class="link_btn"><img src="${cdn:pic(pageContext.request)}/styles/shangpin/images/20160520/link_btn.png${ver}"></a>
                <img src="${cdn:pic(pageContext.request)}/styles/shangpin/images/20160520/sp_logo.png${ver}" class="icon_logo">

            </div>
        </div>


    </div>

</rapid:override>


<%@ include file="/WEB-INF/pages/common/star_red_packet_base.jsp" %>