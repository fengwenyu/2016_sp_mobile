<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib prefix="s" uri="/struts-tags" %>
<%String path = request.getContextPath();%>
<footer>

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
    <p> <a href="<%=path%>/spindex!index?ch=${ch}">首页</a>  <a href="<%=path%>/cartaction!showcart?ch=${ch}">购物袋</a>  <a href="<%=path%>/orderaction!orderlist?ch=${ch}">查看订单</a>  <a href="<%=path%>/accountaction!info?ch=${ch}">我的账户</a> </p>
   -->
    <p>客服电话：<a class="tel" href="tel:4006-900-900">4006-900-900</a></p>
     <a href="javascript:;" class="alScrollTop"><img src="<%=path%>/images/scrollTop_icon.png" width="13" height="14" alt="回顶部"> 回顶部</a>
  </div>

</footer>