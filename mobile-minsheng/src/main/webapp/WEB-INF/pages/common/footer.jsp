<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib prefix="s" uri="/struts-tags" %>
<%String path = request.getContextPath();%>
<script src="<%=path%>/js/client.js" type="text/javascript" charset="utf-8"></script>
<footer>
  
  
  <div class="alCopyright">
    <s:if test="#session != null && #session['user'] != null">
      <p> <a href="<%=path%>/accountaction!info?ch=${ch}" class="userBtn"><s:property value="#session['user'].name"/>,<s:property value="#session['user'].level"/></a></p>
    </s:if>
    <s:else>
    </s:else>
<!-- 
    <p> <a href="<%=path%>/spindex!index?ch=${ch}">首页</a>  <a href="<%=path%>/cartaction!showcart?ch=${ch}">购物袋</a>  <a href="<%=path%>/orderaction!orderlist?ch=${ch}">查看订单</a>  <a href="<%=path%>/accountaction!info?ch=${ch}">我的账户</a> </p>
 -->
    <p>客服电话：4006-900-900</p>
     <a href="javascript:;" class="alScrollTop"><img src="<%=path%>/images/scrollTop_icon.png" width="13" height="14" alt="回顶部"> 回顶部</a>
  </div>

</footer>