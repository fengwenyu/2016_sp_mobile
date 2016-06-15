<%@ page language="java" contentType="text/html; charset=UTF-8"
	trimDirectiveWhitespaces="true" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/pages/common/include.inc.jsp"%>

<rapid:override name="custum">
<script src="${cdn:js(pageContext.request)}/styles/shangpin/js/jquery.min.js${ver}"  type="text/javascript" charset="utf-8"></script>
<script src="${cdn:js(pageContext.request)}/styles/shangpin/js/lazyload.js${ver}"  type="text/javascript" charset="utf-8"></script>
<script src="${cdn:js(pageContext.request)}/styles/shangpin/js/config.sim.js${ver}"  type="text/javascript" charset="utf-8"></script>
<script src="${cdn:js(pageContext.request)}/styles/shangpin/js/downloadAppShowMeet.js${ver}"  type="text/javascript" charset="utf-8"></script>
<script src="${cdn:js(pageContext.request)}/styles/shangpin/js/j.MXTimer.js${ver}"  type="text/javascript" charset="utf-8"></script>
<script src="${cdn:js(pageContext.request)}/styles/shangpin/js/xmas.dialogs.js${ver}"  type="text/javascript" charset="utf-8"></script>

<style>
@charset "utf-8";
/* CSS Document */
body{ background:#f3f3f3;}
.onBody{height:100%; overflow:hidden;}
/*悬浮头部*/
.container{ min-width:320px; max-width:640px; margin:0 auto; overflow:hidden;}
.headApp {width: 100%}
.headApp img { display: block;width: 100%}

.topFix{position:relative; height:44px;}
.topFix section {position:relative; height:44px; overflow:hidden; min-width:320px; max-width:640px;}
/*悬浮头部end*/

.conbox{
	width:100%;
	max-width:640px;
	margin:0 auto;
}

.conbox a{
	display:block;
	width:100%;
}
.conbox img{
	display:block;
	width:100%;
}

.top_img{
	width:100%;
	display:block;
	margin-bottom:0;
	
}
.conbox h3{ width:100%; margin-top:5px;}
.imgList{ margin-top:6px;}
.margin-bottom{ margin-bottom:-5px;}
.prod_list{ padding:0 4px 0 0;}
.prod_list li{ float:left; width:50%; margin-top:5px;}
.prod_list li:nth-child(2n){ float:right; margin-right:-4px;}
</style>
</rapid:override>
<%-- 浏览器标题 --%>
<rapid:override name="title">
	国庆延迟发货公告
</rapid:override>

<%-- 页标题 --%>
<rapid:override name="page_title">
	国庆延迟发货公告
</rapid:override>
<rapid:override name="content">
 <div class="conbox">
      <!--header-->
      <p class="top_img">
          <img src="${cdn:pic(pageContext.request)}/styles/shangpin/images/20150930notice/img01.jpg" />
          <a href="tel:4006900900"><img src="${cdn:pic(pageContext.request)}/styles/shangpin/images/20150930notice/img02.jpg" /></a>
      </p>
    </div>
</rapid:override>
<rapid:override name="footer">
</rapid:override>
<%@ include file="/WEB-INF/pages/common/base_meet.jsp" %> 
