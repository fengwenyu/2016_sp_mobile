<%@ page language="java" contentType="text/html; charset=UTF-8" import="java.util.*"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ include file="/WEB-INF/pages/common/message.jsp" %>
<%String path = request.getContextPath();%>
<footer>				
   <c:if test="${platform!='android'&&ch!='100001'}">
     <dl class="alDownload_AppBtn">
	    <dd>
	    	<a href="<%=res.getString("shangpinurl")%>download.action?p=101&ch=3&fileName=aolai_maolai.apk"><img src="<%=path%>/images/android-download.png" width="118" height="40" alt="Andorid"></a>
	    	<a href="<%=path%>/aolaiindex!toAppStore"> <img src="<%=path%>/images/iphone-download.png" width="118" height="40" alt="iPhoneapp"></a>
	    </dd>
	 </dl>
  </c:if>
  <div class="alService">
    <ul class="alNotice">
      <li><img src="<%=path%>/images/zp_icon.png" width="30" height="30" alt="正品保证"> 正品保证</li>
      <li><img src="<%=path%>/images/my_icon.png" width="30" height="30" alt="免运费"> 免运费　</li>
      <li><img src="<%=path%>/images/sc_icon.png" width="30" height="30" alt="支持试穿"> 支持试穿</li>
    </ul>
  </div>
  
  <div class="alCopyright">
    <p> <a href="<%=path%>/">首页</a>  <a href="<%=path%>/cartaction!showcart">购物袋</a>  <a href="<%=path%>/orderaction!orderlist">查看订单</a>  <a href="<%=path%>/accountaction!info">我的账户</a> </p>
    <p>客服电话：<a class="tel" href="tel:4006-900-900">4006-900-900</a></p>
     <a href="javascript:;" class="alScrollTop"><img src="<%=path%>/images/scrollTop_icon.png" width="13" height="14" alt="回顶部"> 回顶部</a>
  </div>

</footer>