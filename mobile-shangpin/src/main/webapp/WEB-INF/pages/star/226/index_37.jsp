<%@ page language="java" contentType="text/html; charset=UTF-8"
	trimDirectiveWhitespaces="true" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/pages/common/include.inc.jsp"%>

<div class="wapper">
 
  <!--内容区域 start-->
  <section class="main">
     <img src="${cdn:pic(pageContext.request)}/styles/shangpin/images/star-packet/red_37/img01.png" >
     <img src="${cdn:pic(pageContext.request)}/styles/shangpin/images/star-packet/red_37/img02.png" >
     <div class="packet_box">
     	<input type="text" value="输口令抢红包" class="passwordInput" id="password">
        <div class="sure_btn">确定</div>
     </div>
     <img src="${cdn:pic(pageContext.request)}/styles/shangpin/images/star-packet/red_37/img06.png">
     <img src="${cdn:pic(pageContext.request)}/styles/shangpin/images/star-packet/red_37/img07.png" style="position:absolute; bottom:0;" >
  </section>
  <!--内容区域 start-->

  
  <div id="popup_overlay"></div>
  <div id="popup_container">
      <div id="popup_message">口令输入有误</div>
  </div>

  
</div>

