<%@ page language="java" contentType="text/html; charset=UTF-8"
	trimDirectiveWhitespaces="true" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/pages/common/include.inc.jsp"%>
<style type="text/css">
table.gridtable {
	font-family: verdana,arial,sans-serif;
	font-size:11px;
	color:#333333;
	border-width: 1px;
	border-color: #666666;
	border-collapse: collapse;
}
table.gridtable th {
	border-width: 1px;
	padding: 8px;
	border-style: solid;
	border-color: #666666;
	background-color: #dedede;
	text-align: center;
}
table.gridtable td {
	border-width: 1px;
	padding: 8px;
	border-style: solid;
	border-color: #666666;
	background-color: #ffffff;
	text-align: center;
}
</style>
<rapid:override name="custum">
	<link href="${cdn:css(pageContext.request)}/styles/shangpin/css/page/index.css${ver}" rel="stylesheet" />
	<link href="${cdn:css(pageContext.request)}/styles/shangpin/css/page/add2home.css${ver}" rel="stylesheet" />
	<link href="${cdn:css(pageContext.request)}/styles/shangpin/css/page/topSearch.css${ver}" rel="stylesheet" />
	<script type="text/javascript" charset="utf-8">
		
			loader = SP.core
			.install("${cdn:js(pageContext.request)}/styles/shangpin/js/jquery.min.js${ver}")
			.using("${cdn:js(pageContext.request)}/styles/shangpin/js/lazyload.js${ver}")
			.excute()
			.using("${cdn:js(pageContext.request)}/styles/shangpin/js/config.sim.js${ver}")
			
			.excute(function(){
				isHome(false);
			});
			
	</script>

	
</rapid:override>
<%-- 浏览器标题 --%>
<rapid:override name="title">
	微信尚品网入口测试页
</rapid:override>

<%-- 页标题 --%>
<rapid:override name="page_title">
	微信尚品网入口测试页
</rapid:override>
<rapid:override name="content">
	<div class="alContent" style="padding: 0">
		<table 	style="width:100%" class="gridtable">
<tr>
	<th>最时尚</th><th>逛尚品</th><th>我</th>
</tr>
<tr>
	<td><a href="${ctx }/fashion/index">尚潮流</a></td><td><a href="${ctx }/topshop/index">TOPSHOP专卖店</a></td><td><a href="${ctx }/weixin/bind/info">账户绑定</a></td>
</tr>
<tr>
<td><a href="${ctx }/subject/index">最新活动</a></td><td><a href="${ctx }/brand/list">全部品牌</a></td><td><a href="${ctx }/user/home">个人中心</a></td></td>
</tr>
<tr>
	<td><a href="${ctx }/flagshop/index">旗舰店</a></td><td><a href="${ctx }/category/index">全部品类</a></td><td></td>
</tr>

</table>
	</div>
</rapid:override>
<%@ include file="/WEB-INF/pages/common/another_common.jsp"%>




