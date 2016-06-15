<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/pages/common/include.inc.jsp"%>

<%--头部 start--%>
    <div class="topFix">
        <section>
            <div class="topBack" >
            <a href="" class="backBtn">&nbsp;</a>
            <span class="top-title">${meet.title }</span>
            <ul class="alUser_icon clr">
                <li><a href="${ctx }/index"><img src="${cdn:pic(pageContext.request)}/styles/shangpin/images/home_icon.png" width="25" height="25" alt="首页"></a></li>
            </ul>
            </div>
        </section>
    </div>
<%--头部 end--%>