<%@ page language="java" contentType="text/html; charset=UTF-8"
	trimDirectiveWhitespaces="true" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/pages/common/include.inc.jsp"%>
<div style="background-color:#${color};height:100px;margin:10px;">
	颜色值： #${color } (通过颜色值来判断是否缓存,)
	<br/>1. /cache/color?color=${color } 其中${color }是一个随机数，演示了不同的参数会形成不同的key，key不同就不会从缓存中取
	<br/>2. /cache/color 没有参数color的话，我们设置了一个随机颜色值，这个颜色用来证明是否缓存。如果颜色值没有变则缓存，如果颜色值变化则没有命中或者时间到期重新取值。
</div>