<%@ page import="com.shangpin.utils.HttpClientUtil" %><%--
  Created by IntelliJ IDEA.
  User: cuibinqiang
  Date: 2015/12/18
  Time: 10:49
  To change this template use File | Settings | File Templates.
--%>






<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
    <%

        String openId = request.getParameter("openId");
        String content = request.getParameter("content");

        String url = "http://localhost:8080/weixinapi";
        String s="<xml>" +
                "<ToUserName><![CDATA[oFHXijvkFXv7ypscJ-jl3rP3O4lY]]></ToUserName>" +
                "<FromUserName><![CDATA["+openId+"]]></FromUserName>" +
                "<CreateTime>12345678</CreateTime>" +
                "<MsgType><![CDATA[text]]></MsgType>" +
                "<Content><![CDATA["+content+"]]></Content>" +
                "</xml>";
        try {
            String result = HttpClientUtil.doPostWithJSON(url, s);
            System.out.println(result);
        } catch (Exception e) {
            e.printStackTrace();
        }
    %>


</head>
<body>
<div>

    <form >
        <input type="text" name="openId" value="oFHXijnTyBFsnD4FXBxabUlSPG1Y"></br>
        <input type="text" name="content" value="问好"></br>
        <input type="submit" value="发送"></br>
    </form>
</div>


<script>



</script>

</body>
</html>
