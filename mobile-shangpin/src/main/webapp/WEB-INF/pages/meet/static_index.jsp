<%@ page language="java" contentType="text/html; charset=UTF-8"
	trimDirectiveWhitespaces="true" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/pages/common/include.inc.jsp"%>

<rapid:override name="custum">
<link rel="stylesheet" href="${ctx}/styles/shangpin/css/page/font-css/font-awesome.min.css"/>
<link rel="stylesheet" href="${ctx}/styles/shangpin/css/432.css"/>

<script src="${cdn:js(pageContext.request)}/styles/shangpin/js/jquery.min.js${ver}"  type="text/javascript" charset="utf-8"></script>
<script src="${cdn:js(pageContext.request)}/styles/shangpin/js/lazyload.js${ver}"  type="text/javascript" charset="utf-8"></script>
<script src="${cdn:js(pageContext.request)}/styles/shangpin/js/config.sim.js${ver}"  type="text/javascript" charset="utf-8"></script>
<link href="${cdn:css(pageContext.request)}/styles/shangpin/js/meet/432/index.js${ver}" rel="stylesheet" />
</rapid:override>

</head>
<%-- 浏览器标题 --%>
<rapid:override name="title">
	CHRIS BY CHRISTOPHER BU
</rapid:override>

<%-- 页标题 --%>
<rapid:override name="page_title">
	CHRIS BY CHRISTOPHER BU
</rapid:override>
<rapid:override name="content">
        
    <!--内容区域 start-->
    <div class="conbox">
    
      <!--content-->
      <p class="top_img">
          <a href="http://m.shangpin.com/product/detail?productNo=30129804">
          	<img src="${cdn:pic(pageContext.request)}/styles/shangpin/images/static/img01.jpg" />
          </a>
          <a href="http://m.shangpin.com/product/detail?productNo=30029174">
              <img src="${cdn:pic(pageContext.request)}/styles/shangpin/images/static/img02.jpg" />
              <img src="${cdn:pic(pageContext.request)}/styles/shangpin/images/static/img03.jpg" />
          </a>
          <a href="http://m.shangpin.com/product/detail?productNo=01429017">
              <img src="${cdn:pic(pageContext.request)}/styles/shangpin/images/static/img04.jpg" />
              <img src="${cdn:pic(pageContext.request)}/styles/shangpin/images/static/img05.jpg" />
          </a>
          <a href="http://m.shangpin.com/product/detail?productNo=30065695">
              <img src="${cdn:js(pageContext.request)}/styles/shangpin/images/e.gif" lazy="${cdn:pic(pageContext.request)}/styles/shangpin/images/static/img06.jpg" />
              <img src="${cdn:js(pageContext.request)}/styles/shangpin/images/e.gif" lazy="${cdn:pic(pageContext.request)}/styles/shangpin/images/static/img07.jpg" />
          </a>
          <a href="http://m.shangpin.com/product/detail?productNo=30065542">
              <img src="${cdn:js(pageContext.request)}/styles/shangpin/images/e.gif" lazy="${cdn:pic(pageContext.request)}/styles/shangpin/images/static/img08.jpg" />
              <img src="${cdn:js(pageContext.request)}/styles/shangpin/images/e.gif" lazy="${cdn:pic(pageContext.request)}/styles/shangpin/images/static/img09.jpg" />
          </a>
          <a href="http://m.shangpin.com/product/detail?productNo=30129804">
              <img src="${cdn:js(pageContext.request)}/styles/shangpin/images/e.gif" lazy="${cdn:pic(pageContext.request)}/styles/shangpin/images/static/img10.jpg" />
              <img src="${cdn:js(pageContext.request)}/styles/shangpin/images/e.gif" lazy="${cdn:pic(pageContext.request)}/styles/shangpin/images/static/img11.jpg" />
          </a>
          <a href="http://m.shangpin.com/meet/432">
          <img src="${cdn:js(pageContext.request)}/styles/shangpin/images/e.gif" lazy="${cdn:pic(pageContext.request)}/styles/shangpin/images/static/img12.jpg" />
          </a>
      </p>

    </div>
</rapid:override>
		
<rapid:override name="statistics">
		
</rapid:override>
<rapid:override name="footer">
</rapid:override>

<%@ include file="/WEB-INF/pages/common/base_meet.jsp" %> 

