<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<HTML>
<HEAD>
    <TITLE>页面不存在</TITLE>
    <%@ include file="/pages/public/common.jspf"%>
</HEAD>   
<BODY>

<DIV ID="Title_bar">
    <DIV ID="Title_bar_Head">
        <DIV ID="Title_Head"></DIV>
        <DIV ID="Title"><!--页面标题-->
            <IMG BORDER="0" WIDTH="13" HEIGHT="13" SRC="${pageContext.request.contextPath}/style/images/title_arrow.gif"/> 提示
        </DIV>
        <DIV ID="Title_End"></DIV>
    </DIV>
</DIV>


<!--显示表单内容-->
<DIV ID="MainArea">
		<DIV CLASS="ItemBlock_Title1">
        </DIV> 
        
        <DIV CLASS="ItemBlockBorder" STYLE="margin-left: 15px;">
            <DIV CLASS="ItemBlock" STYLE="text-align: center; font-size: 16px;">
                404，您访问的页面不存在！
            </DIV>
        </DIV>
</DIV>

</BODY>
</HTML>