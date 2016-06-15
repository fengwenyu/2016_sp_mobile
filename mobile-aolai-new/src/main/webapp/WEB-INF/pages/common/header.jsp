<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/pages/common/include.inc.jsp"%>

<%--头部 start--%>
<header>
  <h1 id="alLogo">
    <a href="${ctx}/index">尚品奥莱</a>
  </h1>
  
  <nav class="alUser_icon">
   <ul>
    <li><a href="${ctx}/cart/list"><img src="${cdn:pic(pageContext.request)}/styles/shangpin/images/cart_icon.png" width="31" height="31" alt="购物袋" title="购物袋"></a></li>
    <li><a href="${ctx}/user/order/list"><img src="${cdn:pic(pageContext.request)}/styles/shangpin/images/order_icon.png" width="31" height="31" alt="订单" title="订单"></a></li>
    <li><a href="${ctx}/user/home"><img src="${cdn:pic(pageContext.request)}/styles/shangpin/images/user_icon.png" width="31" height="31" alt="账户" title="账户"></a></li>
   </ul>
  </nav>
</header>
<%--头部 end--%>