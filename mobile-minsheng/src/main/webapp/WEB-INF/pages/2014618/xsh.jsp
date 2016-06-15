<%@ page contentType="text/html;charset=UTF-8"%>
<%@taglib prefix="s" uri="/struts-tags" %>
<%String path = request.getContextPath();%>
<!doctype html>
<html lang="zh-CN">
<jsp:include page="header0.jsp"></jsp:include>
<body>
<div class="topFix">
    <section>
      <nav id="filter_box">
        <ul class="header_nav">
          <li><a href="<%=path%>/activeaction!meetpage?id=38&type=index&p=0">首页</a></li>
            <li><a href="<%=path%>/activeaction!meetpage?id=38&type=szj&p=1">时装精</a></li>
          <li><a href="<%=path%>/activeaction!meetpage?id=38&type=xnz&&p=2">型男志</a></li>
          <li ><a href="<%=path%>/activeaction!meetpage?id=38&type=sxh&&p=3">奢享汇</a></li>
          <li><a href="<%=path%>/activeaction!meetpage?id=38&type=cxg&p=4">潮鞋柜</a></li>
          <li><a href="<%=path%>/activeaction!meetpage?id=38&type=ydp&p=5">悦动派</a></li>
          <li><a href="<%=path%>/activeaction!meetpage?id=38&type=djp&p=6">点睛配</a></li>
          <li class="hover"><a href="<%=path%>/activeaction!meetpage?id=38&type=xsh&p=7">享生活</a></li>
        </ul>
      </nav>
  </section>
  </div>
 <div class="sp520_list" style="margin-top:0;">
    ${html }
  </div> 
 <jsp:include page="../common/footer.jsp"></jsp:include>
</body>
</html>
