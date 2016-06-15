<%@ page language="java" contentType="text/html; charset=UTF-8" trimDirectiveWhitespaces="true"
         pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/pages/common/include.inc.jsp"%>

<rapid:override name="title">
    520口令红包
</rapid:override>
<rapid:override name="custum">

    <link href="${cdn:css(pageContext.request)}/styles/shangpin/css/20160520command/base.css${ver}" rel="stylesheet" />
    <link href="${cdn:css(pageContext.request)}/styles/shangpin/css/20160520command/packet.css${ver}"	rel="stylesheet" />

</rapid:override>
<rapid:override name="content">
    <div class="wapper">

        <img src="${cdn:pic(pageContext.request)}/styles/shangpin/images/20160520command/img01.png" >
        <img src="${cdn:pic(pageContext.request)}/styles/shangpin/images/20160520command/img02.png" >

        <div class="submit_box">
            <a class="submit_btn" style="background: #cccccc; ">来晚了活动已结束</a>
        </div>
        <div class="submit_box">
            <a href="${basePath}/index" style="font-size:25px;color:#f53a94">去看看》</a>
        </div>


        <div id="popup_overlay"></div>
        <div id="popup_container">
            <div id="popup_message">您已经领过了</div>
        </div>

    </div>
</rapid:override>
<rapid:override name="static_file">
    <script src="${cdn:js(pageContext.request)}/styles/jquery/jquery-1.8.0.min.js${ver}" type="text/javascript" charset="utf-8"></script>
   </rapid:override>
<%@ include file="/WEB-INF/pages/common/star_red_packet_base.jsp" %>