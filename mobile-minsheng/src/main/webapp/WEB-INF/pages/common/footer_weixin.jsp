<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib prefix="s" uri="/struts-tags" %>
<%String path = request.getContextPath();%>
<footer>
   <s:if test="#session != null && #session['ch'] != '100001'">
     <dl class="alDownload_AppBtn">
	    <!--  dd><a href="<%=path%>/spindex!toAppStore?ch=${ch}">尚品手机客户端<br />iPhone　版下载 <img src="<%=path%>/images/iphone-download.png" width="143" height="40" alt="iPhoneapp"></a></dd>-->
	    <dd><a href="<%=path%>/spindex!toAppStore?ch=${ch}&store=android"><img src="<%=path%>/images/android-download.png" width="118" height="40" alt="Andorid"></a><a href="<%=path%>/spindex!toAppStore?ch=${ch}&store=iphone"><img src="<%=path%>/images/iphone-download.png" width="118" height="40" alt="iPhoneapp"></a></dd>
	    <!--  <dd><a href="<%=path%>/spindex!toAppStore?ch=${ch}"><img src="<%=path%>/images/android-download.png" width="143" height="40" alt="Androidapp"></a></dd>
	    -->
	 </dl>
  </s:if>
  <div class="alService">
    <ul class="alNotice">
      <li><img src="<%=path%>/images/footer-promise.png" width="320"  ></li>
      <!-- 
      <li><img src="<%=path%>/images/zp_icon.png" width="30" height="30" alt="正品保证"> 正品保证</li>
      <li><img src="<%=path%>/images/my_icon.png" width="30" height="30" alt="免运费"> 免运费　</li>
      <li><img src="<%=path%>/images/sc_icon.png" width="30" height="30" alt="支持试穿"> 支持试穿</li>
       -->
    </ul>
  </div>
  
  <div class="alCopyright">
<!-- 
    <s:if test="#session != null && #session['user'] != null">
      <p> <a href="<%=path%>/accountaction!info?ch=${ch}" class="userBtn"><s:property value="#session['user'].name"/>,<s:property value="#session['user'].level"/></a><a href="<%=path%>/accountaction!logout?ch=${ch}" class="userBtn">退出</a> </p>
    </s:if>
    <s:else>
  	  <p> <a href="<%=path%>/accountaction!loginui?ch=${ch}" >登录</a> <a href="<%=path%>/accountaction!registerui?ch=${ch}" >注册</a></p>
    </s:else>
    <p> <a href="<%=path%>/spindex!index?ch=${ch}">首页</a>  <a href="<%=path%>/cartaction!showcart?ch=${ch}">购物袋</a>  <a href="<%=path%>/orderaction!orderlist?ch=${ch}">查看订单</a>  <a href="<%=path%>/accountaction!info?ch=${ch}">我的账户</a> </p>
-->
    <p>客服电话：<a class="tel" href="tel:4006-900-900">4006-900-900</a></p>
     <a href="javascript:;" class="alScrollTop"><img src="<%=path%>/images/scrollTop_icon.png" width="13" height="14" alt="回顶部"> 回顶部</a>
  </div>

</footer>