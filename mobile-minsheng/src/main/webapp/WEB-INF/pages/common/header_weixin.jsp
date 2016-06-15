<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%String path = request.getContextPath();%>
<header>
  <!-- 禁止自动识别5位以上数字为电话 -->
  <meta name="format-detection" content="telephone=no">
  <h1 id="alLogo">
    <a href="<%=path%>/spindex!index?gender=0&ch=${ch}">尚品</a>
  </h1>
  <nav class="alUser_icon">
  </nav>
</header>