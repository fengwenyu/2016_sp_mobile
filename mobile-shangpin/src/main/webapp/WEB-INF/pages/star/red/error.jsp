<%@ page language="java" contentType="text/html; charset=UTF-8" trimDirectiveWhitespaces="true"
    pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/pages/common/include.inc.jsp"%>

<rapid:override name="title">
	尚品网祝你圣诞快乐！
</rapid:override>
<rapid:override name="page_title">
	尚品网祝你圣诞快乐！！
</rapid:override>
<rapid:override name="custum">
	<link href="${cdn:css(pageContext.request)}/styles/shangpin/css/star-packet/red_1212/base.css${ver}" rel="stylesheet" />
	<link href="${cdn:css(pageContext.request)}/styles/shangpin/css/star-packet/red_1212/packet.css${ver}" rel="stylesheet" />
</rapid:override>

<rapid:override name="content">
<div class="wapper">
   <!--内容区域 start-->
  <section class="main">
  	<div class="error-con">
    	<a href="http://m.shangpin.com/red/index?star=1" style="display:block;"><i><img src="${cdn:pic(pageContext.request)}/styles/shangpin/images/star-packet/red_1212/error_img.png" ></i>
        <span style="display:block; text-align:center; color:#333; line-height:30px; padding-bottom:10px;">点我再试一次</span>
        <p style="color:#333; text-align:center;">抢红包的人太多啦，让俺休息一会再发行不</p>
        </a>
    </div>
  </section>
  <!--内容区域 start-->

</div>
</rapid:override>
<rapid:override name="static_file">
	<script src="${cdn:js(pageContext.request)}/styles/shangpin/js/star-packet/20151020/jquery.min.js${ver}" type="text/javascript" charset="utf-8"></script>
	<script src="${cdn:js(pageContext.request)}/styles/shangpin/js/star-packet/red/packet.js${ver}" type="text/javascript" charset="utf-8"></script>
</rapid:override>
<%@ include file="/WEB-INF/pages/common/star_red_packet_base.jsp" %> 