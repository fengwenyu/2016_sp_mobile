<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/pages/common/include.inc.jsp"%>

<%--头部 start--%>
    <div class="topFix">
        <section id="topMenu">
            <div class="topBack" >
            <span class="top-title"><img src="${cdn:pic(pageContext.request)}/styles/shangpin/images/splogo.png${ver}" width="46" height="21"></span>
            <ul class="alUser_icon clr">
              <li><a href="<c:url value='/index'/>"><img src="${cdn:pic(pageContext.request)}/styles/shangpin/images/home_icon.png" width="25" height="25" alt="首页"></a></li>
                <li><a href="${ctx}/cart/list"><img src="${cdn:pic(pageContext.request)}/styles/shangpin/images/order_icon.png" width="25" height="25" alt="购物车"></a></li>
                <li><a href="${ctx}/user/home"><img src="${cdn:pic(pageContext.request)}/styles/shangpin/images/user_icon.png" width="25" height="25" alt="账户"></a></li>
            </ul>
            </div>
        </section>
    </div>
<%--头部 end--%>