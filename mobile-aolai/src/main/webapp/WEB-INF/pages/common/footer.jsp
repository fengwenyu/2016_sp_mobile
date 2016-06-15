<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib prefix="s" uri="/struts-tags" %>
<%String path = request.getContextPath();%>
<%String ua =request.getHeader("user-agent").toLowerCase();%>
<%String micromessenger = "micromessenger";%>
<%String ShangpinIOSApp = "ShangpinIOSApp";%>
<%String AolaiIOSApp = "AolaiIOSApp";%>
<%String ShangpinAndroidApp = "ShangpinAndroidApp";%>
<%String AolaiAndroidApp = "AolaiAndroidApp";%>
 <%if(ua.indexOf(micromessenger)>-1||ua.indexOf(ShangpinIOSApp.toLowerCase())>-1||ua.indexOf(AolaiIOSApp.toLowerCase())>-1||ua.indexOf(ShangpinAndroidApp.toLowerCase())>-1||ua.indexOf(AolaiAndroidApp.toLowerCase())>-1){}else{%>
 
<s:if test="ch!=23">
<footer>
  <s:if test="platform!='android' && ch!='100001'">
  
     <dl class="alDownload_AppBtn">
	    <dd>
	    	<a href="<%=path%>/aolaiindex!toAppStore?store=android"><img style="right: auto;" src="<%=path%>/images/android-download.png" width="118" height="40" alt="Andorid"></a>
	    	<a href="<%=path%>/aolaiindex!toAppStore?store=iphone"> <img src="<%=path%>/images/iphone-download.png" width="118" height="40" alt="iPhoneapp"></a>
	    </dd>
	 </dl>
  </s:if>
 
  
  <div class="alCopyright">
    <s:if test="#session != null && #session['user'] != null">
      <p> <a href="<%=path%>/accountaction!info" class="userBtn"><s:property value="#session['user'].name"/>,<s:property value="#session['user'].level"/></a><a href="<%=path%>/accountaction!logout" class="userBtn">退出</a> </p>
    </s:if>
    <s:else>
  	  <p> <a href="<%=path%>/accountaction!loginui" >登录</a> <a href='<%=path%>/accountaction!registerui?loginFrom=<s:property value="loginFrom"/>'>注册</a></p>
    </s:else>
 <!-- 
    <p> <a href="<%=path%>/">首页</a>  <a href="<%=path%>/cartaction!showcart">购物袋</a>  <a href="<%=path%>/orderaction!orderlist">查看订单</a>  <a href="<%=path%>/accountaction!info">我的账户</a> </p>
 -->
    <p>客服电话：<a class="tel" href="tel:4006-900-900">4006-900-900</a></p>
     <a href="javascript:;" class="alScrollTop"><img src="<%=path%>/images/scrollTop_icon.png" width="13" height="14" alt="回顶部"> 回顶部</a>
  </div>

</footer>
</s:if>
   <% }%>