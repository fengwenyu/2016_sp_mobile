<%@ page language="java" contentType="text/html; charset=UTF-8"
	trimDirectiveWhitespaces="true" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/pages/common/include.inc.jsp"%>
<rapid:override name="content">
	<div>
	<table>
				<tr>
					<td width="300px">名称</td>
					<td width="100px">个数</td>
					<td width="100px">内存大小</td>
					<td width="100px">命中次数</td>
					<td width="100px">未命中次数</td>
				</tr>
		<c:forEach var="cache" items="${cacheList }">
			<tr>
					<td>${cache.name }</td>
					<td>${cache.size }</td>
					<td>${cache.memoryStoreSize }</td>
					<td>${cache.statistics.cacheHits }</td>
					<td>${cache.statistics.cacheMisses }</td>
			</tr>
			</c:forEach>
	</table>
		
	</div>
</rapid:override>

<%@ include file="/WEB-INF/pages/common/base.jsp"%>
