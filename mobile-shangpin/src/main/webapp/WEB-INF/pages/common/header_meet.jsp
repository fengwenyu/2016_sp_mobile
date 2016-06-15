<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/pages/common/include.inc.jsp"%>

<%--头部 start--%>
<div class="topFix">
    <section id="topMenu">
        <div class="topBack" >
        <a href="javascript:history.go(-1);" class="backBtn">&nbsp;</a>
      	  <span class="top-title">
				 <rapid:block name="page_title">
				 尚品网触屏版
			   </rapid:block>
			 </span>
        <ul class="alUser_icon clr">
          <li><a href="<c:url value='/index'/>"><img src="${cdn:pic(pageContext.request)}/styles/shangpin/images/home_icon.png" width="25" height="25" alt="首页"></a></li>
        </ul>
        </div>
    </section>
</div>
<%--头部 end--%>