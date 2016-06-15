<%@ page language="java" contentType="text/html; charset=UTF-8"
	trimDirectiveWhitespaces="true" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/pages/common/include.inc.jsp"%>
<rapid:override name="content">
	<div style="background-color:#${color};height:100px;margin:10px;" >
		IP : ${IP }  (此IP来判断分布式中的serverIP来源,通过多个页面来看数据是否在不同的server IP中数据是否同步缓存)<br/>
		localIp : ${localIp }  (此localIp来判断分布式中的serverIP来源,通过多个页面来看数据是否在不同的server IP中数据是否同步缓存)<br/>
		颜色值： #${color} (此颜色值为当前主页面中的数据值，用来判断当前的主页面除了引入需要缓存页面，其他部分并未缓存)
		</div>
	<%-- 颜色页面 --%>
	<c:import url="/cache/color?color=${color }" />
	<%-- 颜色页面 --%>
	<c:import url="/cache/color" />
	<%-- 时间页面 --%>
	<c:import url="/cache/datetime" />
	<%-- 随机数页面 --%>
	<c:import url="/cache/random" />
</rapid:override>

<%@ include file="/WEB-INF/pages/common/base.jsp"%>
