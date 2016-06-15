<%@ page language="java" contentType="text/html; charset=UTF-8" trimDirectiveWhitespaces="true"
    pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/pages/common/include.inc.jsp"%>
<div  style="height:100px;margin:10px;">
	当前时间 ： ${currentTime }<br/>
	当前时间戳：${t }
	
	<br/>时间也是来证明缓存的，如果当前时间一直没变就说明是从缓存中取得的。
	<br/>可以开两个窗口 来比较 在访问的url后加入 参数 OPT=miss和 OPT=refresh的操作缓存的不同
</div>